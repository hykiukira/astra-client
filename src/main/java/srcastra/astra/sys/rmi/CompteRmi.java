package srcastra.astra.sys.rmi;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T;
import srcastra.astra.sys.classetransfert.configuration.Compte;
import srcastra.astra.sys.classetransfert.utils.GetId;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.rmi.Exception.ManageServSQLExcption;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.rmi.socketfactory.SSLClientSocketFactory;
import srcastra.astra.sys.rmi.socketfactory.SSLServerSocketFactory;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Thomas
 */
public class CompteRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface, CompteRmiInterface {
    astraimplement serveur;

    public CompteRmi(astraimplement serveur, int port) throws RemoteException {
        //  super(port);
        super(port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        this.serveur = serveur;
    }

    public void insert(int urcleunik, Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure {
        Compte obj = (Compte) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            String requete = "INSERT into compte(ce_num,cate_cleunik,ce_lettrable,ce_dc,ce_categorie,ce_analityk,tva_cleunik) VALUES(? ,?,?,?,?,?,?)";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1, new Long(obj.getCe_num()).toString());
            pstmt.setInt(2, obj.getCate_cleunik());
            pstmt.setInt(3, obj.getLettrable());
            pstmt.setInt(4, obj.getDc());
            pstmt.setInt(5, obj.getCategorie());
            pstmt.setInt(6, obj.getAnalityque());
            pstmt.setInt(7, obj.getTva_cleunik());
            pstmt.execute();
            int cle = GetId.getLastId(tmpool.getConuser());
            int lmcleunik = tmpool.getLmcleunik();
            int otherlm = 0;
            if (lmcleunik == 1)
                otherlm = 2;
            else
                otherlm = 1;
            requete = "INSERT into traduction_compte (ce_cleunik,trate_traduction,lmcleunik)VALUES(?,?,?)";
            pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cle);
            pstmt.setString(2, obj.getLibelle1());
            pstmt.setInt(3, lmcleunik);
            pstmt.execute();
            pstmt.setInt(1, cle);
            pstmt.setString(2, obj.getLibelle2());
            pstmt.setInt(3, otherlm);
            pstmt.execute();
            synchronized (tmpool.getBuffer()) {
                tmpool.getBuffer().invalidateBuffer("compte");
            }


        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            if (sn.getErrorCode() == 1062) {
                ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
                sqe.setSqlException(sn);
                sqe.setErrorcode(5202);
                // sqe.setMessagePerso(message);
                throw sqe;
            } else {
                String[] message = new String[1];
                message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
                ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
            }
        }

    }

    public void modify(int urcleunik, Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure {
        Compte obj = (Compte) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            boolean inhistorique = checkIfINHistorique(tmpool, obj.getCe_cleunik());
            String requete = null;
            if (!inhistorique)
                requete = "UPDATE compte SET ce_num=? ,cate_cleunik=? ,ce_lettrable=? ,ce_dc=?,ce_categorie=? ,ce_analityk=? ,tva_cleunik=? WHERE ce_cleunik=?";
            else
                requete = "UPDATE compte SET ce_num=? ,cate_cleunik=? ,ce_lettrable=? ,ce_dc=?,ce_categorie=? ,ce_analityk=? ,tva_cleunik=? WHERE ce_cleunik=? AND ce_num=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1, new Long(obj.getCe_num()).toString());
            pstmt.setInt(2, obj.getCate_cleunik());
            pstmt.setInt(3, obj.getLettrable());
            pstmt.setInt(4, obj.getDc());
            pstmt.setInt(5, obj.getCategorie());
            pstmt.setInt(6, obj.getAnalityque());
            pstmt.setInt(7, obj.getTva_cleunik());
            pstmt.setInt(8, obj.getCe_cleunik());
            if (inhistorique)
                pstmt.setString(9, new Long(obj.getCe_num()).toString());
            int row = pstmt.executeUpdate();
            if (row == 0) {
                Transaction.rollback(tmpool.getConuser());
                ServeurSqlFailure sqe = new ServeurSqlFailure("Des opérations comptables sont passées sur ce compte");
                sqe.setErrorcode(5201);
                throw sqe;
            }
            int lmcleunik = tmpool.getLmcleunik();
            int otherlm = 0;
            if (lmcleunik == 1)
                otherlm = 2;
            else
                otherlm = 1;
            requete = "UPDATE traduction_compte SET trate_traduction=? WHERE ce_cleunik=? AND lmcleunik=?";
            pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(2, obj.getCe_cleunik());
            pstmt.setString(1, obj.getLibelle1());
            pstmt.setInt(3, lmcleunik);
            pstmt.execute();
            pstmt.setInt(2, obj.getCe_cleunik());
            pstmt.setString(1, obj.getLibelle2());
            pstmt.setInt(3, otherlm);
            pstmt.execute();
            synchronized (tmpool.getBuffer()) {
                tmpool.getBuffer().invalidateBuffer("compte");
            }
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            if (sn.getErrorCode() == 1062) {
                ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
                sqe.setSqlException(sn);
                sqe.setErrorcode(5200);
                // sqe.setMessagePerso(message);
                throw sqe;
            } else {
                message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
                ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
            }
        }

    }

    public Object get(int urcleunik, int cleunik, int update) throws java.rmi.RemoteException, ServeurSqlFailure {
        Compte obj = new Compte();
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            String requete = "SELECT * FROM compte WHERE ce_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                obj.setCe_cleunik(result.getInt(1));
                obj.setCe_num(Long.parseLong(result.getString(2)));
                obj.setCate_cleunik(result.getInt(3));
                obj.setLettrable(result.getInt(4));
                obj.setDc(result.getInt(5));
                obj.setCategorie(result.getInt(6));
                obj.setAnalityque(result.getInt(7));
                obj.setTva_cleunik(result.getInt(8));
            }
            pstmt = tmpool.getConuser().prepareStatement("SELECT trate_traduction FROM traduction_compte  WHERE ce_cleunik=? ORDER BY lmcleunik");
            pstmt.setInt(1, cleunik);
            result = pstmt.executeQuery();
            result.first();
            obj.setLibelle1(result.getString(1));
            result.next();
            obj.setLibelle2(result.getString(1));
            return obj;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

    public void delete(int urcleunik, int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "UPDATE compte SET annuler=1 WHERE ce_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cleunik);
            ;

            pstmt.execute();
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

    }

    public java.util.ArrayList getList(int urcleunik, int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "SELECT * FROM compte ORDER BY ce_num";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);

            ArrayList array = new ArrayList();
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Compte obj = new Compte();
                obj.setCe_cleunik(result.getInt(1));
                obj.setCe_num(Long.parseLong(result.getString(2)));
                obj.setCate_cleunik(result.getInt(3));

                array.add(obj);
            }
            Transaction.commit(tmpool.getConuser());

            return array;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

        return null;
    }

    public java.util.ArrayList getList2(int urcleunik, int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure {

        CompressArray cp;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        return cp = Transaction.generecombostest3("SELECT ce_cleunik,ce_num,cate_cleunik FROM compte ORDER BY ce_num", tmpool.getConuser());
    }

    public boolean checkIfINHistorique(Poolconnection tmpool, int clecompte) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement("SELECT hecleunik from historique2 WHERE ce_cleunik_cent=? OR ce_cleunik2=?");
        pstmt.setInt(1, clecompte);
        pstmt.setInt(2, clecompte);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            return true;
        }
        return false;
    }

    public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
        return false;
    }

    public ArrayList getListeComp(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT c.ce_cleunik, c.ce_num, t.trate_traduction, c.ce_categorie, s.sode_solded, s.sode_soldec " +
                "FROM traduction_compte t, compte c " +
                "LEFT JOIN solde s ON ( c.ce_cleunik = sode_divcleunik AND sode_cat = ? ) " +
                "WHERE c.ce_cleunik = t.ce_cleunik AND t.lmcleunik = ? AND length(ce_num)> 5 ORDER BY c.ce_num  ";
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, Caisselibelle_T.GENE);
            pstmt.setInt(2, tmpool.getLmcleunik());
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                if (array == null)
                    array = new ArrayList();
                Object[] tmp = new Object[6];
                for (int i = 0; i < 6; i++) {
                    if (result.getObject(i + 1) == null)
                        tmp[i] = "0";
                    else
                        tmp[i] = result.getObject(i + 1);
                }
                array.add(tmp);

            }
            return array;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

    public ArrayList getListeCompInpute(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT c.ce_cleunik, c.ce_num, t.trate_traduction, c.ce_categorie, s.sode_solded, s.sode_soldec FROM traduction_compte t, compte c ,solde s WHERE c.ce_cleunik = s.sode_divcleunik AND sode_cat = ? AND c.ce_cleunik = t.ce_cleunik AND t.lmcleunik = ? AND length(ce_num)> 5 ORDER BY c.ce_num ";
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, Caisselibelle_T.GENE);
            pstmt.setInt(2, tmpool.getLmcleunik());
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                if (array == null)
                    array = new ArrayList();
                Object[] tmp = new Object[6];
                for (int i = 0; i < 6; i++) {
                    if (result.getObject(i + 1) == null)
                        tmp[i] = "0";
                    else
                        tmp[i] = result.getObject(i + 1);
                }
                array.add(tmp);

            }
            return array;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

}