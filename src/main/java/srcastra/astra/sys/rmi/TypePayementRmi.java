package srcastra.astra.sys.rmi;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.configuration.TypePayement;
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
public class TypePayementRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface {
    astraimplement serveur;
    CompteRmi compte;

    public TypePayementRmi(astraimplement serveur, int port) throws RemoteException {
        //super(port);
        super(port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        this.serveur = serveur;


    }

    public void insert(int urcleunik, Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure {
        TypePayement obj = (TypePayement) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "INSERT into typepaiement(tynt_libelle1,tynt_libelle2,tynt_categorie,ce_cleunik) VALUES(? ,? ,? ,?)";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1, obj.getTynt_libelle1());
            pstmt.setString(2, obj.getTynt_libelle2());
            pstmt.setInt(3, obj.getTynt_categorie());
            pstmt.setInt(4, obj.getCe_cleunik());

            pstmt.execute();
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

    }

    public void modify(int urcleunik, Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure {
        TypePayement obj = (TypePayement) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        PreparedStatement pstmt = null;
        if (compte == null)
            compte = (CompteRmi) serveur.renvCompteRmiObject(urcleunik);

        try {
            Transaction.begin(tmpool.getConuser());
            if (!compte.checkIfINHistorique(tmpool, obj.getOld_ce_cleunik())) {
                String requete = "UPDATE typepaiement SET tynt_libelle1=? ,tynt_libelle2=? ,tynt_categorie=? ,ce_cleunik=? WHERE tynt_cleunik=?";
                pstmt = tmpool.getConuser().prepareStatement(requete);
                pstmt.setString(1, obj.getTynt_libelle1());
                pstmt.setString(2, obj.getTynt_libelle2());
                pstmt.setInt(3, obj.getTynt_categorie());
                pstmt.setInt(4, obj.getCe_cleunik());
                pstmt.setInt(5, obj.getTynt_cleunik());
                pstmt.execute();
            } else {
                String requete = "UPDATE typepaiement SET tynt_libelle1=? ,tynt_libelle2=? ,tynt_categorie=? ,ce_cleunik=? WHERE tynt_cleunik=? AND ce_cleunik=?";
                pstmt = tmpool.getConuser().prepareStatement(requete);
                pstmt.setString(1, obj.getTynt_libelle1());
                pstmt.setString(2, obj.getTynt_libelle2());
                pstmt.setInt(3, obj.getTynt_categorie());
                pstmt.setInt(4, obj.getCe_cleunik());
                pstmt.setInt(5, obj.getTynt_cleunik());
                pstmt.setInt(6, obj.getCe_cleunik());
                int rowupdated = pstmt.executeUpdate();
                if (rowupdated == 0) {
                    Transaction.rollback(tmpool.getConuser());
                    ServeurSqlFailure sqe = new ServeurSqlFailure("Des opérations sur ce compte ont déjà été passées !\n Impossible de modifier ce la valeur du compte !");
                    sqe.setErrorcode(5201);
                    throw sqe;

                }
            }
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

    }

    public Object get(int urcleunik, int cleunik, int update) throws java.rmi.RemoteException, ServeurSqlFailure {
        TypePayement obj=null ;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
         //   Transaction.begin(tmpool.getConuser());
            String requete = "SELECT * FROM typepaiement WHERE tynt_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                if(obj==null){
                   obj=new TypePayement();
                }
                obj.setTynt_cleunik(result.getInt(1));
                obj.setTynt_libelle1(result.getString(2));
                obj.setTynt_libelle2(result.getString(3));
                obj.setTynt_categorie(result.getInt(4));
                obj.setCe_cleunik(result.getInt(5));

            }
            //Transaction.commit(tmpool.getConuser());

            return obj;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

        return null;
    }

    public Object getByCategorie(int urcleunik, int cleunik, int update) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        TypePayement obj = new TypePayement();
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        Transaction.begin(tmpool.getConuser());
        String requete = "SELECT * FROM typepaiement WHERE tynt_categorie=?";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, cleunik);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            obj.setTynt_cleunik(result.getInt(1));
            obj.setTynt_libelle1(result.getString(2));
            obj.setTynt_libelle2(result.getString(3));
            obj.setTynt_categorie(result.getInt(4));
            obj.setCe_cleunik(result.getInt(5));
        }
        return obj;
    }
    public Object getCash(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        TypePayement obj = new TypePayement();
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        Transaction.begin(tmpool.getConuser());
        String requete = "SELECT * FROM typepaiement WHERE tynt_libelle1=?";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setString(1, "cash");
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        boolean found=false;
        while (result.next()) {
            obj.setTynt_cleunik(result.getInt(1));
            obj.setTynt_libelle1(result.getString(2));
            obj.setTynt_libelle2(result.getString(3));
            obj.setTynt_categorie(result.getInt(4));
            obj.setCe_cleunik(result.getInt(5));
            found=true;
        }
        if(!found){
            throw new ServeurSqlFailure("NO CASH FOUND IN PAYEMENT TYPE");
        }
        return obj;
    }


    public void delete(int urcleunik, int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            TypePayement pay = (TypePayement) get(urcleunik, cleunik, 0);
            if (compte == null)
                compte = (CompteRmi) serveur.renvCompteRmiObject(urcleunik);
            if (!compte.checkIfINHistorique(tmpool, pay.getCe_cleunik())) {
                String requete = "DELETE from typepaiement  WHERE tynt_cleunik=?";

                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
                pstmt.setInt(1, cleunik);
                pstmt.execute();
                Transaction.commit(tmpool.getConuser());
            } else {
                Transaction.rollback(tmpool.getConuser());
                ServeurSqlFailure sqe = new ServeurSqlFailure("Des opérations sur ce compte ont déjà été passées !\n Impossible de modifier ce la valeur du compte !");
                sqe.setErrorcode(5201);
                throw sqe;

            }
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
            String requete = "SELECT t.tynt_cleunik,t.ce_cleunik, t.tynt_libelle1 ,t.tynt_libelle2 ,t.tynt_categorie,c.ce_num FROM typepaiement t,compte c WHERE t.ce_cleunik=c.ce_cleunik ORDER BY tynt_libelle1";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);

            ArrayList array = new ArrayList();
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                TypePayement obj = new TypePayement();
                obj.setTynt_cleunik(result.getInt(1));
                obj.setCe_cleunik(result.getInt(2));
                obj.setTynt_libelle1(result.getString(3));
                obj.setTynt_libelle2(result.getString(4));
                obj.setTynt_categorie(result.getInt(5));
                obj.setCompteIntitule(result.getString(6));
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
        String requete = "SELECT tynt_cleunik ,tynt_libelle1,tynt_libelle2  FROM typepaiement ORDER BY tynt_libelle1";
        return cp = Transaction.generecombostest3(requete, tmpool.getConuser());
    }

    public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
        return false;
    }
}