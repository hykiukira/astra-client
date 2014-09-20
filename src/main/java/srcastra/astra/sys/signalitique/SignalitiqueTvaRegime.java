/*
 * SignalitiqueTvaRegime.java
 *
 * Created on 22 août 2002, 10:40
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
public class SignalitiqueTvaRegime implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueTvaRegime */
    public SignalitiqueTvaRegime() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobject not supported for TvaRegime");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobjectpopup not supported for TvaRegime");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        String sqlrequete="select t.tetraduction from traductionintitule t where t.lmcleunik="+urlmcleunik+" and t.tecleunik="+objectCleunik+" and t.caecleunik=1;";
        ResultSet tmpresult=Transaction.execrequete(sqlrequete,connect.getConuser());
        Object returnValue = null;
        try{
            tmpresult.last();
            int tmpNbrLigne=tmpresult.getRow();
            if(tmpNbrLigne!=0) {
                tmpresult.first();
                returnValue=(String)tmpresult.getString(1);
            }
            tmpresult.close();
        }
        catch(SQLException e){
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"Erreur dans  ChargeCObjectComboCp : "+e);
        }
        catch(Exception e1){
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"erreur dans ChargeCObjectComboCp :"+e1);
        }
        return returnValue;
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("insertobjectpopup not supported for TvaRegime");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("modifyobjectpopup not supported for TvaRegime");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvintitule not supported for TvaRegime");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        return renvSignalitiqueTvaRegime(urlmcleunik, connect.getConuser(), cas, connect.getBuffer());
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        return renvcombotvaregime(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());
    }
    
    private CompressArray renvSignalitiqueTvaRegime(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {
        String tmplangue;
        String sqlrequete=null;
        CompressArray cp;
        synchronized (buf) {
            if(cas==1)
                if (buf.isValid("signTvaRegimeCas1"))
                    cp=buf.getValue("signTvaRegimeCas1");
                else {
                    sqlrequete="select d.decleunik,d.decode,t.tretraduction"
                    +" from devise d,traductiondevise t"
                    +" where d.decleunik=t.decleunik  and t.lmcleunik="+urlmcleunik
                    +" order by d.decode;";
                    cp=Transaction.generecombostest(sqlrequete,usercon);
                    buf.setValue("signTvaRegimeCas1",cp);
                }
            else if(cas==2)
                if (buf.isValid("signTvaRegimeCas1"))
                    cp=buf.getValue("signTvaRegimeCas1");
                else {
                    sqlrequete="select d.decleunik,t.tretraduction,d.decode"
                    +" from devise d,traductiondevise t"
                    +" where d.decleunik=t.decleunik  and t.lmcleunik="+urlmcleunik
                    +" order by t.tretraduction;";
                    cp=Transaction.generecombostest(sqlrequete,usercon);
                    buf.setValue("signTvaRegimeCas2",cp);
                }
            else
                cp=null;
        }
        return cp;
    }
    
    private CompressArray renvcombotvaregime(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {
        String sqlrequete=null;
        CompressArray devise;
        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_TVAREGIME, 0, urlmcleunik, plettre, "", cas);
        synchronized (buf) {
            if (buf.isValid("tvaRegime"))
                devise=buf.getValue("tvaRegime");
            else {
                if(cas==1)
                    sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where t.lmcleunik="+urlmcleunik+" and t.caecleunik=1 ORDER BY t.tetraduction;";
                else if(cas==2)
                    sqlrequete="select t.tecleunik,t.tetraduction from traductionintitule t where t.lmcleunik="+urlmcleunik+" and t.caecleunik=1 ORDER BY t.tetraduction;";
                devise=Transaction.generecombostest(sqlrequete,usercon);
                buf.setValue("tvaRegime",devise);
                buf.linkNewName(signature,"tvaRegimeDir");
                buf.setValue(signature, new Long(System.currentTimeMillis()));
            }
        }
        return devise;
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
