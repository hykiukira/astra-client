package srcastra.astra.sys.rmi;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.configuration.Periode_T;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.rmi.Exception.ManageServSQLExcption;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.rmi.socketfactory.SSLClientSocketFactory;
import srcastra.astra.sys.rmi.socketfactory.SSLServerSocketFactory;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Thomas
 */
public class PeriodeRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface {
    astraimplement serveur;

    public PeriodeRmi(astraimplement serveur, int port) throws RemoteException {
//super(port);
        super(port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        this.serveur = serveur;
    }

    public void insert(int urcleunik, Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure {
        Periode_T obj = (Periode_T) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "INSERT into periode(pede_numper,pede_de,pede_a,pede_locked,exle_cleunik,pede_actif) VALUES(? ,? ,? ,? ,? ,?)";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, obj.getPede_numper());
            pstmt.setString(2, obj.getPede_de().toString());
            pstmt.setString(3, obj.getPede_a().toString());
            pstmt.setInt(4, obj.getPede_locked());
            pstmt.setInt(5, obj.getExle_cleunik());
            pstmt.setInt(6, obj.getPede_actif());

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
        Periode_T obj = (Periode_T) obj1;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "UPDATE periode SET pede_numper=? ,pede_de=? ,pede_a=? ,pede_locked=? ,exle_cleunik=? ,pede_actif=? WHERE pede_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, obj.getPede_numper());
            pstmt.setString(2, obj.getPede_de().toString());
            pstmt.setString(3, obj.getPede_a().toString());
            pstmt.setInt(4, obj.getPede_locked());
            pstmt.setInt(5, obj.getExle_cleunik());
            pstmt.setInt(6, obj.getPede_actif());
            pstmt.setInt(7, obj.getPede_cleunik());

            pstmt.execute();
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

    }

    public Object get(int urcleunik, int cleunik, int update) throws java.rmi.RemoteException, ServeurSqlFailure {
        Periode_T obj = new Periode_T();
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "SELECT * FROM periode WHERE pede_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                obj.setPede_cleunik(result.getInt(1));
                obj.setPede_numper(result.getInt(2));
                obj.setPede_de(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(3)));
                obj.setPede_a(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(4)));
                obj.setPede_locked(result.getInt(5));
                obj.setExle_cleunik(result.getInt(6));
                obj.setPede_actif(result.getInt(7));

            }
            Transaction.commit(tmpool.getConuser());

            return obj;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

        return null;
    }

    public Object getByDate(int urcleunik, int cleunik, int update, srcastra.astra.sys.classetransfert.utils.Date datep, Poolconnection tmpool) throws java.rmi.RemoteException, ServeurSqlFailure {
        srcastra.astra.sys.classetransfert.utils.Date date;
        if (datep == null || datep.isOpen() || datep.isUnknow()) {
            Calendar c = Calendar.getInstance();
            date = new srcastra.astra.sys.classetransfert.utils.Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        } else {
            date = datep;
        }
        String requete = "SELECT p.pede_cleunik,p.pede_numper,p.pede_de,p.pede_a,p.pede_locked,p.exle_cleunik,p.pede_actif FROM periode p,exercicecomptable e  WHERE ? BETWEEN pede_de  AND pede_a AND p.exle_cleunik=e.exle_cleunik AND e.exle_courant='O'";
        Periode_T obj = new Periode_T();
        try {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1, date.toString());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                obj.setPede_cleunik(result.getInt(1));
                obj.setPede_numper(result.getInt(2));
                obj.setPede_de(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(3)));
                obj.setPede_a(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(4)));
                obj.setPede_locked(result.getInt(5));
                obj.setExle_cleunik(result.getInt(6));
                obj.setPede_actif(result.getInt(7));
            }

            return obj;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }

        return null;
    }

    public Object getByDate(int urcleunik, int cleunik, int update, srcastra.astra.sys.classetransfert.utils.Date datep, Connection con) throws java.rmi.RemoteException, ServeurSqlFailure {
        srcastra.astra.sys.classetransfert.utils.Date date;
        if (datep == null || datep.isOpen() || datep.isUnknow()) {
            Calendar c = Calendar.getInstance();
            date = new srcastra.astra.sys.classetransfert.utils.Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        } else {
            date = datep;
        }
        String requete = "SELECT p.pede_cleunik,p.pede_numper,p.pede_de,p.pede_a,p.pede_locked,p.exle_cleunik,p.pede_actif FROM periode p,exercicecomptable e  WHERE ? BETWEEN pede_de  AND pede_a AND p.exle_cleunik=e.exle_cleunik AND e.exle_courant='O'";
        Periode_T obj = new Periode_T();
        try {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            PreparedStatement pstmt = con.prepareStatement(requete);
            pstmt.setString(1, date.toString());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                obj.setPede_cleunik(result.getInt(1));
                obj.setPede_numper(result.getInt(2));
                obj.setPede_de(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(3)));
                obj.setPede_a(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(4)));
                obj.setPede_locked(result.getInt(5));
                obj.setExle_cleunik(result.getInt(6));
                obj.setPede_actif(result.getInt(7));
            }

            return obj;
        } catch (SQLException sn) {
            Transaction.rollback(con);
            String[] message = new String[1];
            message[0] = " ";//java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, con);
        }

        return null;
    }

    public void delete(int urcleunik, int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            String requete = "UPDATE periode SET annuler=1 WHERE pede_cleunik=?";
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
            String requete = "SELECT * FROM periode";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);

            ArrayList array = new ArrayList();
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Periode_T obj = new Periode_T();
                obj.setPede_cleunik(result.getInt(1));
                obj.setPede_numper(result.getInt(2));
                obj.setPede_de(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(3)));
                obj.setPede_a(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(4)));
                obj.setPede_locked(result.getInt(5));
                obj.setExle_cleunik(result.getInt(6));
                obj.setPede_actif(result.getInt(7));

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
        return cp = Transaction.generecombostest3("SELECT pede_cleunik,cale_lib1,cale_lib2 FROM periode ORDER BY cale_lib1", tmpool.getConuser());
    }

    public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
        return false;
    }

}