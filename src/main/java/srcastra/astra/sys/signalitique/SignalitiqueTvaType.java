/*
 * SignalitiqueTvaType.java
 *
 * Created on 22 août 2002, 9:26
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
public class SignalitiqueTvaType implements srcastra.astra.sys.signalitique.Signalitique {
    
    /** Creates a new instance of SignalitiqueTvaType */
    public SignalitiqueTvaType() {
    }
    
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TvaType");
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
        ServeurBuffer buf=connect.getBuffer();
        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_TVA_TYPE);
        buf.linkNewName(signature,"tvaTypeDir");
        String sqlrequete=null;
        Object returnValue=null;
        Gestionerreur_T tmperreur=new Gestionerreur_T();
        if(cas==1)
            sqlrequete="select  t.vacleunik,t.vavaleurtva,t.vadatetimecrea,t.vadatetimemodif,t.snumerosessioncrea,t.snumerosessionmodif,tr.tratraduction from valeurtva t,traductiontva tr where t.vacleunik=tr.vacleunik and tr.lmcleunik="+urlmcleunik+" and t.vacleunik="+objcleunik+";";
        else if(cas==2) {
            Transaction.begin(connect.getConuser());
            sqlrequete="select  t.vacleunik,t.vavaleurtva,t.vadatetimecrea,t.vadatetimemodif,t.snumerosessioncrea,t.snumerosessionmodif,tr.tratraduction from valeurtva t,traductiontva tr where t.vacleunik=tr.vacleunik and tr.lmcleunik="+urlmcleunik+" and t.vacleunik="+objcleunik+" FOR UPDATE;";
        }
        ResultSet tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);
        if(tmperreur.getErreurcode()==10000)
            
            try{
                tmpresult.last();
                int tmpNbrLigne=tmpresult.getRow();
                if(tmpNbrLigne!=0) {
                    tmpresult.first();
                    srcastra.astra.sys.classetransfert.signaletique.TvaType_T tmpTvaType=new srcastra.astra.sys.classetransfert.signaletique.TvaType_T(tmpresult.getInt(1),tmpresult.getFloat(2),tmpresult.getDate(3),tmpresult.getDate(4),tmpresult.getString(5),tmpresult.getString(6),tmpresult.getString(7));
                    ArrayList tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);
                    tmpTvaType.setdata(tmpArray);
                    tmpTvaType.setErreurcode(tmperreur.getErreurcode());
                    tmpTvaType.setErreurmessage(tmperreur.getErreurmessage());
                    returnValue=(Object)tmpTvaType;
                }
                tmpresult.close();
            }
            catch(SQLException e){
                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeCodePostaux: "+e.getErrorCode()+"  "+e.getMessage());
            }
        catch(Exception e1){
            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"erreur dans chargecodepostaux :"+e1);
        }
        else{
            srcastra.astra.sys.classetransfert.signaletique.Langue_Tb tmpCod=new srcastra.astra.sys.classetransfert.signaletique.Langue_Tb();
            tmpCod.setErreurcode(tmperreur.getErreurcode());
            tmpCod.setErreurmessage(tmperreur.getErreurmessage());
            returnValue=(Object)tmpCod;
        }
        return returnValue;
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TvaType");
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        srcastra.astra.sys.rmi.utils.ServeurBuffer buf=connect.getBuffer();
        srcastra.astra.sys.classetransfert.signaletique.TvaType_T tmpTypTva=(srcastra.astra.sys.classetransfert.signaletique.TvaType_T)objdp;
        Gestionerreur_T tmpret =null;
        if(cas==1){
            String[] sqlrequete=new String[2];
            sqlrequete[0]="insert into valeurtva(vavaleurtva,vadatetimecrea,vadatetimemodif,snumerosessioncrea,snumerosessionmodif) values("+tmpTypTva.getVavaleurtva()+",NOW(),NOW(),'"+connect.getUrnumerosession()+"','"+connect.getUrnumerosession()+"');";
            synchronized (buf) {
                Transaction.begin(connect.getConuser());
                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());
                if(tmpret.getErreurcode()==10000) {
                    for(int j=0;j<=tmpTypTva.getNbrLangue();j++) {
                        sqlrequete[1]="insert into traductiontva(vacleunik,tratraduction,lmcleunik) values("+tmpret.getTmpint()+",'"+tmpTypTva.getTratraduction()+"',"+(j+1)+");";
                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());
                    }
                    Transaction.commit(connect.getConuser());
                    buf.invalidateBuffer("tvaTypeDir");
                }
            }
        }
        return tmpret;
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        srcastra.astra.sys.classetransfert.signaletique.TvaType_T tmpTvaType=(srcastra.astra.sys.classetransfert.signaletique.TvaType_T)objdp;
        Gestionerreur_T tmpret = null;
        ServeurBuffer buf = connect.getBuffer();
        if(cas==1){
            String[] sqlrequete=new String[2];
            sqlrequete[0]="update valeurtva set vavaleurtva='"+tmpTvaType.getVavaleurtva()+"',vadatetimemodif=NOW(),snumerosessionmodif='"+connect.getUrnumerosession()+"' where vacleunik="+tmpTvaType.getVacleunik()+";";
            if(tmpTvaType.getTmpString().compareTo("none")==0)
                sqlrequete[1]="insert into traductiontva values("+tmpTvaType.getVacleunik()+",'"+tmpTvaType.getTratraduction()+"',"+tmpTvaType.getTvalangue()+");";
            else
                sqlrequete[1]="update traductiontva set tratraduction='"+tmpTvaType.getTratraduction()+"' where vacleunik="+tmpTvaType.getVacleunik()+" and lmcleunik="+tmpTvaType.getTvalangue()+";";
            synchronized (buf) {
                Transaction.begin(connect.getConuser());
                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());
                if(tmpret.getErreurcode()==10000) {
                    Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());
                    if(tmpret2.getErreurcode()==10000) {
                        Transaction.commit(connect.getConuser());
                    }
                }
                buf.invalidateBuffer("tvaTypeDir");
            }
        }
        return tmpret;
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TvaType");
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        return renvSignalitiqueTvaType(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TvaType");
    }
    
    private CompressArray renvSignalitiqueTvaType(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {
        String tmplangue;
        String sqlrequete=null;
        CompressArray cp;
        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_TVA_TYPE );
        synchronized (buf) {
            if(cas==1)
                if (buf.isValid("signTvaTypeCas1"))
                    cp=buf.getValue("signTvaTypeCas1");
                else {
                    sqlrequete="select t.vacleunik,t.vavaleurtva,tr.tratraduction"
                    +" from valeurtva t,traductiontva tr"
                    +" where t.vacleunik=tr.vacleunik  and tr.lmcleunik="+urlmcleunik
                    +" order by t.vavaleurtva;";
                    cp=Transaction.generecombostest3(sqlrequete,usercon);
                    buf.setValue("signTvaTypeCas1",cp);
                    buf.linkNewName(signature,"tvaTypeDir");
                    buf.setValue(signature, new Long(System.currentTimeMillis()));
                }
            else if(cas==2)
                if (buf.isValid("signTvaTypeCas2"))
                    cp=buf.getValue("signTvaTypeCas2");
                else {
                    sqlrequete="select t.vacleunik,tr.tratraduction,t.vavaleurtva"
                    +" from valeurtva t,traductiontva tr"
                    +" where t.vacleunik=tr.vacleunik  and tr.lmcleunik="+urlmcleunik
                    +" order by tr.tratraduction;";
                    cp=Transaction.generecombostest3(sqlrequete,usercon);
                    buf.setValue("signTvaTypeCas2",cp);
                    buf.linkNewName(signature,"tvaTypeDir");
                    buf.setValue(signature, new Long(System.currentTimeMillis()));
                }
            else
                cp=null;
        }
        return cp;
    }
    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {
        ArrayList returnvalue=null;
        String sqlrequete=null;
        String sqlrequete2=null;
        int  nbrLigne=0;
        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";
        sqlrequete2="select lmcleunik,tratraduction from traductiontva where vacleunik="+objectCleunik+" order by lmcleunik;";
        return Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);       
    }
    
    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
    }
    
}
