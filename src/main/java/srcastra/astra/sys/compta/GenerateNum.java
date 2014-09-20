/*
 * GenerateNum.java
 *
 * Created on 8 décembre 2003, 9:31
 */

package srcastra.astra.sys.compta;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.sql.*;

/**
 * @author Thomas
 */
public class GenerateNum {

    /**
     * Creates a new instance of GenerateNum
     */
    String host;
    Poolconnection tmpool;

    public GenerateNum(String host, Poolconnection tmpool) {
        this.host = host;
        this.tmpool = tmpool;
        
    }

    public String generateNumdossier(int urcleunik) throws ServeurSqlFailure {
        Connection con = null;
        String retval = "";
        try {
            con = connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(), this.tmpool.getDatabaseName(), host, 3306);
            Transaction.begin(con);
            retval = generenumdossier(con, urcleunik);
            Transaction.commit(con);
            return retval;
        } catch (SQLException se) {
            se.printStackTrace();
            Transaction.rollback(con);
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(se);
            sqe.setErrorcode(se.getErrorCode());
            throw sqe;
        }

    }

    public long getTransactionNumber() throws SQLException {
        Connection con = null;
        long retval = 0;
        try {
            con = connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(), this.tmpool.getDatabaseName(), host, 3306);
            Transaction.begin(con);
            retval = generateTransactionNumber(con);
            Transaction.commit(con);
            return retval;
        } catch (SQLException se) {
            Transaction.rollback(con);
            se.printStackTrace();
            throw se;
        }
    }
     public long getTransactionNumber2() throws SQLException {
        Connection con = null;
        long retval = 0;
        try {
            if(this.tmpool!=null){
                if(this.tmpool.getConuser()!=null){
                    con=tmpool.getConuser();    
                }
            }
            else{
                con = connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(), this.tmpool.getDatabaseName(), host, 3306);
            }
            Transaction.begin(con);
            retval = generateTransactionNumber(con);
            Transaction.commit(con);
            return retval;
        } catch (SQLException se) {
            Transaction.rollback(con);
            se.printStackTrace();
            throw se;
        }
    }

    public static long generateTransactionNumber(Connection con) throws SQLException {
        long transact = 0;
        String requete = "SELECT * FROM compteur WHERE comur_cat=? FOR UPDATE";
        PreparedStatement pstmt = con.prepareStatement(requete);
        pstmt.setInt(1, 1);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        boolean sw = false;
        while (result.next()) {
            transact = result.getLong(2);
            sw = true;
        }
        if (!sw) {
            transact = insertTransaction(con);
        }
        transact = transact + 1;
        pstmt = con.prepareStatement("UPDATE compteur SET comur_transact =? WHERE comur_cat=?");
        pstmt.setInt(2, 1);
        pstmt.setLong(1, transact);
        pstmt.execute();
        return transact;
    }

    public static long generateTransactionNumber(Connection con, Object synch) throws SQLException {
        long transact = 0;
        synchronized (synch) {

            String requete = "SELECT * FROM compteur WHERE comur_cat=? FOR UPDATE";
            PreparedStatement pstmt = con.prepareStatement(requete);
            pstmt.setInt(1, 1);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            boolean sw = false;
            while (result.next()) {
                transact = result.getLong(2);
                sw = true;
            }
            if (!sw) {
                transact = insertTransaction(con);
            }
            transact = transact + 1;
            pstmt = con.prepareStatement("UPDATE compteur SET comur_transact =? WHERE comur_cat=?");
            pstmt.setInt(2, 1);
            pstmt.setLong(1, transact);
            pstmt.execute();
        }
        return transact;
    }

    private static int insertTransaction(Connection con) {
        String insert = "Insert into compteur values(1,1)";
        try {
            PreparedStatement pstmt = con.prepareStatement(insert);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    private String generenumdossier(Connection con, int urcleunik) throws SQLException {

        PreparedStatement pstmt;
        pstmt = con.prepareStatement("SELECT u.urcleunik,e.eeabrev,e.eecompteur,e.eecleunik FROM entite e,user u WHERE u.eecleunik=e.eecleunik AND u.urcleunik=? FOR UPDATE");
        //"SELECT u.urcleunik,e.eeabrev,e.eecompteur,e.eecleunik FROM entite e,user u WHERE u.eecleunik=e.eecleunik AND u.urcleunik=?";
        pstmt.setInt(1, urcleunik);
        ResultSet result = pstmt.executeQuery();
        result.first();
        String entiteabrev = result.getString(2);
        String année = "" + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String abrevannée = année.substring(3, 4);
        int numcompteur = result.getInt(3);
        int entite = result.getInt(4);
        String numero = "";
        if (numcompteur < 10)
            numero = "0000" + numcompteur;
        else if (numcompteur < 100)
            numero = "000" + numcompteur;
        else if (numcompteur < 1000)
            numero = "00" + numcompteur;
        else if (numcompteur < 10000)
            numero = "0" + numcompteur;
        else if (numcompteur < 100000)
            numero = "" + numcompteur;
        else if (numcompteur == 100000) {
            numcompteur = 1;
            numero = "00000" + 1;
        }
        pstmt = con.prepareStatement("UPDATE entite SET eecompteur=? WHERE eecleunik=?");
        pstmt.setInt(1, (numcompteur + 1));
        pstmt.setInt(2, entite);
        pstmt.execute();
        String numeroDossier = entiteabrev + abrevannée + numero;
        System.out.println("[*************numero du dossier" + numeroDossier);

        return numeroDossier;

    }

    private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException {
        String message;
        Connection tmpcon = null;
        try {
            String jdbcDriverClassName = "org.gjt.mm.mysql.Driver";
            if (jdbcDriverClassName != null)
                Class.forName(jdbcDriverClassName);
            tmpcon = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?autoReconnect=true", userName, password);
            System.out.println("ok connecter");

        } catch (ClassNotFoundException e0) {
            e0.printStackTrace();

        } catch (Exception e2) {
            e2.printStackTrace();

        }       // Add your handling code here:
        return tmpcon;
    }
}   

