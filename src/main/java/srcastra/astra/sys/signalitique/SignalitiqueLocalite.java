/*
 * SignalitiqueLocalite.java
 *
 * Created on 22 août 2002, 10:26
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
 * @author  David
 */
public class SignalitiqueLocalite implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueLocalite */
    public SignalitiqueLocalite() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Localite");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        return renvcombolocalite(urlmcleunik, plettre, cxcode, connect.getConuser(), cas, connect.getBuffer());
    }
    
    private java.util.ArrayList renvcombolocalite(int urlmcleunik,char plettre,String pays,Connection usercon,int cas, ServeurBuffer buf){
        String tmplangue;
        String sqlrequete=null;
        ArrayList cp;
        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_LOCALITE, 0, urlmcleunik, plettre, pays, cas);
        switch (urlmcleunik) {
            case 1:
                tmplangue="N";
                break;
            case 2:
                tmplangue="F";
                break;
            default:
                tmplangue="N";
                break;
        }
        if(cas==1) {
            sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction"
            +" from codepostaux c,traductioncodpostaux tx"
            +" where c.cxcleunik=tx.cxcleunik"
            +" and  tx.txtraduction like('"+plettre+"%')"
            +" and tx.lmcleunik not in('"+tmplangue+"');";
            cp=Transaction.generecombostest(sqlrequete, usercon);
            if (!buf.isValid(signature)) {
                buf.linkNewName(signature,"localiteDir");
                buf.setValue(signature,new Long(System.currentTimeMillis()));
            }
        }
        else if(cas==2)
            synchronized (buf) {
                if (buf.isValid("localite"))
                    cp=buf.getValue("localite");
                else {
                    sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction"
                    +" from codepostaux c,traductioncodpostaux tx"
                    +" where c.cxcleunik=tx.cxcleunik"
                    +"  and tx.lmcleunik not in('"+tmplangue+"');";
                    cp=Transaction.generecombostest(sqlrequete, usercon);
                    buf.setValue("localite", cp);
                    buf.linkNewName(signature,"localiteDir");
                    buf.setValue(signature,new Long(System.currentTimeMillis()));
                }
            }
        else
            cp=null;
        return cp;
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
