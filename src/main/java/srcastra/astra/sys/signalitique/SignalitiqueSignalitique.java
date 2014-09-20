/*
 * SignalitiqueSignalitique.java
 *
 * Created on 22 août 2002, 13:00
 */

package srcastra.astra.sys.signalitique;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compress.CompressArray;
import java.util.ArrayList;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.ServeurBuffer;
import srcastra.astra.sys.classetransfert.signaletique.Signalitique_T;
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  rene
 */
public class SignalitiqueSignalitique implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueSignalitique */
    public SignalitiqueSignalitique() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobject not supported for Signalitique");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobjectpopup not supported for Signalitique");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobjectcombo not supported for Signalitique");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        Signalitique_T tmpSign=(Signalitique_T)objdp;
        String[] sqlrequete;
        String sqlrequete2;
        Gestionerreur_T tmpret=null;
        ServeurBuffer buf=connect.getBuffer();
        ArrayList tmplist=null;
        if(cas==1){
            Transaction.begin(connect.getConuser());
            int id=Transaction.genereId("insert into test(test) values(2);",connect.getConuser());
            for(int j=0;j<=tmpSign.getNbrLangue();j++) {
                Object[] tmpobj=(Object[])tmplist.get(j);
                sqlrequete2="insert into traductionintitule(caecleunik,lmcleunik,tecleunik,teabrev,tetraduction) values("+ tmpSign.getCaecleunik()+","+tmpobj[0]+","+id+",'"+tmpobj[1]+"','"+tmpobj[2]+"');";
                Transaction.execrequeteinsert(sqlrequete2,connect.getConuser());
            }
            Transaction.commit(connect.getConuser());
        }
        return null;
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Signalitique");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Signalitique");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Signalitique");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Signalitique");
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
