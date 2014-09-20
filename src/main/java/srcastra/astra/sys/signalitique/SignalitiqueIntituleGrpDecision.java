/*
 * SignalitiqueIntituleGrpDecision.java
 *
 * Created on 22 août 2002, 10:39
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
public class SignalitiqueIntituleGrpDecision implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueIntituleGrpDecision */
    public SignalitiqueIntituleGrpDecision() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        return renvIntituleGrpdecision(urlmcleunik, connect.getConuser(), cas, caecleunik);
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for IntutileGrpDecision");
    }
    
    private java.util.ArrayList renvIntituleGrpdecision(int urlmcleunik,Connection usercon,int cas,int caecleunik) {
        String tmplangue;
        String sqlrequete=null;
        ArrayList tmpList;
        if(cas==1)
            sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where  t.lmcleunik="+ urlmcleunik+" and t.caecleunik="+caecleunik+" order by t.tetraduction;";
        else if(cas==2)
            sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where  t.lmcleunik="+ urlmcleunik+" and t.caecleunik="+caecleunik+" order by t.tetraduction;";
        tmpList=Transaction.generecombostest(sqlrequete,usercon);
        return tmpList;
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
