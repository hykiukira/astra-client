package srcastra.test;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.compta.ExerciceCompt_T;
import srcastra.astra.sys.classetransfert.compta.JournalCompta_T;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.utils.GetId;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 29-déc.-2004
 * Time: 17:11:12
 *
 * @author
 * @version $revision : $
 *          To change this template use File | Settings | File Templates.
 */
public class ReopenYear {
    private static Logger logger = Logger.getLogger(ReopenYear.class.getName());
    Connection con;

    public ReopenYear(String dbname,String login,String password) {

        String checkIfEmpty="SELECT * FROM exercicecomptable";
        String checkIf2005="SELECT * FROM exercicecomptable where exle_annee=?";
        String updateCurrentEx="UPDATE exercicecomptable SET exle_courant='N'";
        ExerciceCompt_T exercice = new ExerciceCompt_T();
        exercice.setExle_abrevfr("2005");
        exercice.setExle_abrevnl("2005");
        exercice.setExle_intitulefr("2005");
        exercice.setExle_intitulevnl("2005");
        exercice.setExle_annee("2005");
        exercice.setExle_debut(0);
        exercice.setExle_fin(11);
        exercice.calculBegAndEndDate();
        exercice.setExle_nbrperiode((exercice.getExle_fin() - exercice.getExle_debut()) + 1);

        try {

              con = connectDb(login, password, dbname, "localhost", 3306);
              Transaction.begin(con);
            PreparedStatement pstmt=con.prepareStatement(checkIfEmpty);
            ResultSet result=pstmt.executeQuery();
            boolean empty=true;
            while(result.next()) {
                empty=false;

            }

            if(!empty){
                pstmt=con.prepareStatement(checkIf2005);
                pstmt.setString(1,"2005");
                result=pstmt.executeQuery();

                boolean ex2005=false;
                while(result.next()) {
                    ex2005=true;

                }
                if(!ex2005){
                    pstmt=con.prepareStatement(updateCurrentEx);
                    pstmt.execute();
                    insertExComptable(exercice, 0);
                }
            }
              Transaction.commit(con);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServeurSqlFailure serveurSqlFailure) {
            serveurSqlFailure.printStackTrace();
            Transaction.rollback(con);

        } catch (SQLException e) {
            e.printStackTrace();
            Transaction.rollback(con);//To change body of catch statement use File | Settings | File Templates.
        }

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
        }
        return tmpcon;
    }

    public int insertExComptable(ExerciceCompt_T exercice, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        //Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);

        String requete = "INSERT INTO `exercicecomptable` ( `exle_abrevfr` , `exle_abrevnl` , `exle_intitulefr` , `exle_intitulevnl` , `exle_annee` , `exle_datedebut` , `exle_datedefin` , `exle_nbrperiode` , `exle_courant`, exle_debut,exle_fin) VALUES ( ? , ? , ? , ? , ? , ?, ?, ?, ?,? ,? )";
        try {
            PreparedStatement pstmt = con.prepareStatement(requete);
            pstmt.setString(1, exercice.getExle_abrevfr());
            pstmt.setString(2, exercice.getExle_abrevnl());
            pstmt.setString(3, exercice.getExle_intitulefr());
            pstmt.setString(4, exercice.getExle_intitulevnl());
            pstmt.setString(5, exercice.getExle_annee());
            pstmt.setString(6, exercice.getExle_datedebut().toString());
            pstmt.setString(7, exercice.getExle_datedefin().toString());
            pstmt.setInt(8, exercice.getExle_nbrperiode());
            pstmt.setString(9, "O");
            pstmt.setInt(10, exercice.getExle_debut());
            pstmt.setInt(11, exercice.getExle_fin());
            pstmt.execute();
            int exlecleunik = GetId.getLastId(con);
            int per = exercice.getExle_debut();
            for (int i = 0; i < exercice.getExle_nbrperiode(); i++) {
                String periode = "INSERT INTO `periode` ( `pede_cleunik` , `pede_numper` , `pede_de` , `pede_a` , `pede_locked` , `exle_cleunik` , `pede_actif` ) VALUES ('', ?, ?, ? , ?, ?, ?);";
                pstmt = con.prepareStatement(periode);
                srcastra.astra.sys.classetransfert.utils.Date date1 = new srcastra.astra.sys.classetransfert.utils.Date();
                srcastra.astra.sys.classetransfert.utils.Date date2 = new srcastra.astra.sys.classetransfert.utils.Date();
                CalculDate.calculPeriodeComptable(date1, date2, exercice.getExle_annee(), per, per);
                pstmt.setInt(1, i + 1);
                pstmt.setString(2, date1.toString());
                pstmt.setString(3, date2.toString());
                pstmt.setInt(4, 0);
                pstmt.setInt(5, exlecleunik);
                pstmt.setInt(6, 0);
                pstmt.execute();
                per++;
            }
            ArrayList array = getAllJournal(urcleunik);
            for (int k = 0; k < array.size(); k++) {
                JournalCompta_T journal = (JournalCompta_T) array.get(k);
                per = exercice.getExle_debut();
                for (int i = 0; i < exercice.getExle_nbrperiode(); i++) {
                    insertJournalPeriode(exercice, journal, per, i);
                    per++;
                }
            }
            //srcastra.astra.sys.init.JournalParameter journparam = new srcastra.astra.sys.init.JournalParameter(this.m_serveur);
            //journparam.updateJournalTable(urcleunik, con);
            return exlecleunik;

        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public ArrayList getAllJournal(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta";
        //    Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList retval = new ArrayList();
        try {
            PreparedStatement pstmt = con.prepareStatement(requete);
            ResultSet result = pstmt.executeQuery();
            getJournal2(result, retval);
            return retval;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }

    private ArrayList getJournal2(ResultSet result, ArrayList array) throws SQLException {
        JournalCompta_T tmp = null;
        result.beforeFirst();
        while (result.next()) {
            tmp = new JournalCompta_T();
            tmp.setJota_cleunik(result.getInt(1));
            tmp.setJota_abrev(result.getString(2));
            tmp.setJota_libelle(result.getString(3));
            tmp.setJota_abrev2(result.getString(4));
            tmp.setJota_libelle2(result.getString(5));
            tmp.setJota_categorie(result.getInt(6));
            tmp.setJota_ext(result.getString(7));
            tmp.setJota_beg(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(8)));
            tmp.setJota_end(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
            tmp.setJota_lock(result.getInt(10));
            tmp.setJota_com(result.getString(11));
            tmp.setNumdoc(result.getString(12));
            tmp.setExle_cleunik(result.getInt(13));
            tmp.setCour_cleunik(result.getInt(14));
            array.add(tmp);
//tmp.setJota
        }
        return array;
    }

    private void insertJournalPeriode(ExerciceCompt_T exercice, JournalCompta_T journal, int per, int i) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        String journper = "INSERT INTO `journcomptaper` (`jota_cleunik` , `joer_numper` , `joer_de` , `joer_a` , `joer_locked` ) VALUES ( ?, ?, ?, ? , ?)";
        PreparedStatement pstmt = con.prepareStatement(journper);
        srcastra.astra.sys.classetransfert.utils.Date date1 = new srcastra.astra.sys.classetransfert.utils.Date();
        srcastra.astra.sys.classetransfert.utils.Date date2 = new srcastra.astra.sys.classetransfert.utils.Date();
        CalculDate.calculPeriodeComptable(date1, date2, exercice.getExle_annee(), per, per);
        pstmt.setInt(1, journal.getJota_cleunik());
        pstmt.setInt(2, i + 1);
        pstmt.setString(3, date1.toString());
        pstmt.setString(4, date2.toString());
        pstmt.setInt(5, 0);
        pstmt.execute();

    }

    public static void main(String[] args) {
     //   new ReopenYear("Astra");

    }
}
