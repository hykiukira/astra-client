/*
 * SignalitiqueValeurTva.java
 *
 * Created on 22 août 2002, 10:44
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
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.rmi.utils.QueryKeyGen;
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  rene
 */
public class SignalitiqueValeurTva implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueValeurTva */
    public SignalitiqueValeurTva() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ValeurTva");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        return renvcomValeurTva(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());
    }
    
    private java.util.ArrayList renvcomValeurTva(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {
        String sqlrequete=null;
        ArrayList tvaregime;
        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_VALEURTVA, 0, urlmcleunik, plettre, "", cas);
        if(cas==1) {
            sqlrequete="select t.vacleunik,t.vavaleurtva,tr.tratraduction"
            +" from valeurtva t,traductiontva tr"
            +" where t.vacleunik=tr.vacleunik"
            +" and tr.lmcleunik="+urlmcleunik
            +" and t.vavaleurtva like ('"+plettre+"%')"
            +" order by t.vavaleurtva;";
            tvaregime=Transaction.generecombostest(sqlrequete,usercon);
            if (!buf.isValid(signature)) {
                buf.linkNewName(signature,"tvaTypeDir");
                buf.setValue(signature, new Long(System.currentTimeMillis()));
            }
        }
        else if(cas==2)
            synchronized (buf) {
                if (buf.isValid("tvaRegime"))
                    tvaregime=buf.getValue("tvaRegime");
                else
                    sqlrequete="select t.vacleunik,t.vavaleurtva,tr.tratraduction"
                    +" from valeurtva t,traductiontva tr"
                    +" where t.vacleunik=tr.vacleunik  and tr.lmcleunik="+urlmcleunik
                    +" order by t.vavaleurtva;";
                tvaregime=Transaction.generecombostest(sqlrequete,usercon);
                buf.setValue("tvaRegime",tvaregime);
                if (!buf.isValid(signature)) {
                    buf.linkNewName(signature,"paysDir");
                    buf.setValue(signature, new Long(System.currentTimeMillis()));
                }
            }
        else
            tvaregime=null;
        return tvaregime;
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
