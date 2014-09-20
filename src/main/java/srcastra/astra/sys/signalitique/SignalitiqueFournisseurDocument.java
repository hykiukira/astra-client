/*
 * SignalitiqueFournisseurDocument.java
 *
 * Created on 22 août 2002, 13:26
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
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  rene
 */
public class SignalitiqueFournisseurDocument implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueFournisseurDocument */
    public SignalitiqueFournisseurDocument() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        String sqlrequete=null;
        ResultSet tmpresult;
        Gestionerreur_T tmperreur= new Gestionerreur_T();
        int tmpNbrLigne;
        Object returnValue = null;
        if(cas==1)
            sqlrequete="SELECT frdtcleunik,frcleunik,frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev,snumerosessioncrea,snumerosessionmodif from fournisseur_document where frcleunik="+objcleunik+";";
        else if(cas==2) {
            Transaction.begin(connect.getConuser());
            sqlrequete="SELECT frdtcleunik,frcleunik,frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev,snumerosessioncrea,snumerosessionmodif from fournisseur_document where frcleunik="+objcleunik+" FOR UPDATE;";
        }
        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);
        if(tmperreur.getErreurcode()==10000) {
            try{
                tmpresult.last();
                tmpNbrLigne=tmpresult.getRow();
                if(tmpNbrLigne!=0) {
                    tmpresult.first();
                    String[] numSession=Transaction.renvNomUserPourSession(connect.getConuser(),tmpresult.getString(10),tmpresult.getString(11));
                    Document_T tmpdoc=new Document_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getDate(3),tmpresult.getDate(4),tmpresult.getInt(5),tmpresult.getInt(6),tmpresult.getInt(7),tmpresult.getInt(8),tmpresult.getInt(9),tmpresult.getString(10),tmpresult.getString(11),numSession[0],numSession[1]);
                    tmpdoc.setErreurcode(tmperreur.getErreurcode());
                    tmpdoc.setErreurmessage(tmperreur.getErreurmessage());
                    returnValue=(Document_T)tmpdoc;
                }
                tmpresult.close();
            }
            catch(SQLException e){
                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeCodePostaux: "+e);
            }
            catch(Exception e1){
                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"erreur dans chargecodepostaux :"+e1);
            }
        }
        else {
            Document_T docvide=new Document_T();
            docvide.setErreurcode(tmperreur.getErreurcode());
            docvide.setErreurmessage(tmperreur.getErreurmessage());
            returnValue=(Document_T)docvide;
        }
        return returnValue;
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurDocument");
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
