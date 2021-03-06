/*

 * SignalitiqueTransport.java

 *

 * Created on 22 ao�t 2002, 9:27

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

public class SignalitiqueTransport implements srcastra.astra.sys.signalitique.Signalitique {

    

    /** Creates a new instance of SignalitiqueTransport */

    public SignalitiqueTransport() {

    }

    

    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Transport");

    }

    

    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        String sqlrequete=null;

        ResultSet tmpresult=null;

        ResultSet tmpresult2=null;

        String tmpcrea=null;

        String tmpmodif=null;

        ArrayList tmpArray=null;

        int tmpNbrLigne;

        Object returnValue=null;

        ServeurBuffer buf=connect.getBuffer();

        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_TRANSPORT);

        Gestionerreur_T tmperreur = new Gestionerreur_T();

        buf.linkNewName(signature,"transportDir");

        if(cas==1)

            sqlrequete="select  t.trtcleunik,t.trtlintitule,t.trtdatetimecrea,t.trtdatetimemodi,t.snumerosessioncrea,t.snumerosessionmodif,tr.tratabrev,tr.trattraduction from transport t,traductiontransport tr where t.trtcleunik=tr.trtcleunik and tr.lmcleunik="+urlmcleunik+" and t.trtcleunik="+objcleunik+";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="select  t.trtcleunik,t.trtlintitule,t.trtdatetimecrea,t.trtdatetimemodi,t.snumerosessioncrea,t.snumerosessionmodif,tr.tratabrev,tr.trattraduction from transport t,traductiontransport tr where t.trtcleunik=tr.trtcleunik and tr.lmcleunik="+urlmcleunik+" and t.trtcleunik="+objcleunik+" FOR UPDATE;";

        }

        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);

        if(tmperreur.getErreurcode()==10000)

            

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0) {

                    tmpresult.first();

                    srcastra.astra.sys.classetransfert.signaletique.Transport_T tmpTransport=new srcastra.astra.sys.classetransfert.signaletique.Transport_T(tmpresult.getInt(1),tmpresult.getString(2),tmpresult.getDate(3),tmpresult.getDate(4),tmpresult.getString(5),tmpresult.getString(6),tmpresult.getString(7),tmpresult.getString(8));

                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);

                    tmpTransport.setdata(tmpArray);

                    tmpTransport.setErreurcode(tmperreur.getErreurcode());

                    tmpTransport.setErreurmessage(tmperreur.getErreurmessage());

                    returnValue=(Object)tmpTransport;

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

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Transport");

    }

    

    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.rmi.utils.ServeurBuffer buf=connect.getBuffer();

        srcastra.astra.sys.classetransfert.signaletique.Transport_T tmpTransport=(srcastra.astra.sys.classetransfert.signaletique.Transport_T)objdp;

        String[] sqlrequete = new String[1];

        Gestionerreur_T tmpret = null;

      //  if(cas==1){

            sqlrequete=new String[2];

            sqlrequete[0]="insert into transport(trtlintitule,trtdatetimecrea,trtdatetimemodi,snumerosessioncrea,snumerosessionmodif) values('"+tmpTransport.getTrattraduction()+"',NOW(),NOW(),'"+connect.getUrnumerosession()+"','"+connect.getUrnumerosession()+"');";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    for(int j=0;j<=tmpTransport.getNbrLangue();j++) {

                        //Object[] tmpobj=(Object[])tmplist.get(j);

                        sqlrequete[1]="insert into traductiontransport(trtcleunik,trattraduction,tratabrev,lmcleunik) values("+tmpret.getTmpint()+",'"+tmpTransport.getTrattraduction()+"','"+tmpTransport.getTratabrev()+"',"+(j+1)+");";

                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    }

                    Transaction.commit(connect.getConuser());

                }

                buf.invalidateBuffer("transportDir");

            }

     //   }

        return tmpret;

    }

    

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        

        srcastra.astra.sys.classetransfert.signaletique.Transport_T tmpTransport=(srcastra.astra.sys.classetransfert.signaletique.Transport_T)objdp;

        Gestionerreur_T tmpret = null;

        ServeurBuffer buf = connect.getBuffer();

       // if(cas==1){

            String [] sqlrequete=new String[2];

            sqlrequete[0]="update transport set trtdatetimemodi="+tmpTransport.getTrtdatetimemodif()+",snumerosessionmodif='"+connect.getUrnumerosession()+"' where trtcleunik="+tmpTransport.getTrtcleunik()+";";

            if(tmpTransport.getTmpString().compareTo("none")==0)

                sqlrequete[1]="insert into traductiontransport values("+tmpTransport.getTrtcleunik()+","+tmpTransport.getTransportLangue()+",'"+tmpTransport.getTrattraduction()+"','"+tmpTransport.getTratabrev()+"');";

            else

                sqlrequete[1]="update traductiontransport set trattraduction='"+tmpTransport.getTrattraduction()+"',tratabrev='"+tmpTransport.getTratabrev()+"' where trtcleunik="+tmpTransport.getTrtcleunik()+" and lmcleunik="+tmpTransport.getTransportLangue()+";";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    if(tmpret2.getErreurcode()==10000) {

                        Transaction.commit(connect.getConuser());

                    }

                }

                buf.invalidateBuffer("transportDir");

            }

      //  }

        return tmpret;

        

    }

    

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Transport");

    }

    

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        return renvSignalitiqueTransport(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());

    }

    

    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {

        return renvcomTransport(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());

    }

    

    private CompressArray renvSignalitiqueTransport(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {

        String tmplangue;

        String sqlrequete=null;

        CompressArray cp;

        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_TRANSPORT);

        synchronized (buf) {

              sqlrequete="select t.trtcleunik,tr.tratabrev,tr.trattraduction"

                    +" from transport t,traductiontransport tr"

                    +" where t.trtcleunik=tr.trtcleunik  and tr.lmcleunik="+urlmcleunik

                    +" order by tr.tratabrev;";

            return GestionnaireSignaletique.renvsignaletique(sqlrequete,urlmcleunik,buf,astrainterface.COMBO_TRANSPORT,usercon,"signTransportCas"+urlmcleunik,"transportDir");

            /*if(cas==1)

                if (buf.isValid("signTransportCas1"))

                    cp=buf.getValue("signTransportCas1");

                else {

                    sqlrequete="select t.trtcleunik,tr.tratabrev,tr.trattraduction"

                    +" from transport t,traductiontransport tr"

                    +" where t.trtcleunik=tr.trtcleunik  and tr.lmcleunik="+urlmcleunik

                    +" order by tr.tratabrev;";

                    cp=Transaction.generecombostest3(sqlrequete,usercon);

                    buf.setValue("signTransportCas1",cp);

                    buf.linkNewName(signature,"transportDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            else if(cas==2)

                if (buf.isValid("signTransportCas2"))

                    cp=buf.getValue("signTransportCas2");

                else {

                    sqlrequete="select t.trtcleunik,tr.trattraduction,tr.tratabrev"+

                    " from transport t,traductiontransport tr"

                    +" where t.trtcleunik=tr.trtcleunik  and tr.lmcleunik="+urlmcleunik

                    +" order by tr.trattraduction;";

                    cp=Transaction.generecombostest(sqlrequete,usercon);

                    buf.setValue("signTransportCas2",cp);

                    buf.linkNewName(signature,"transportDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            else

                cp=null;*/

        }

        //return cp;

    }

    

    

    private java.util.ArrayList renvcomTransport(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {

        String sqlrequete=null;

        ArrayList tvaregime;

        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_TRANSPORT, 0, urlmcleunik, plettre, "", cas);

        synchronized (buf) {

            if (buf.isValid("transport"))

                tvaregime=buf.getValue("transport");

            else {

                if(cas==1)

                    sqlrequete = "select t.trtcleunik, tt.trattraduction, tt.tratabrev"

                    +" from transport t, traductiontransport tt"

                    +" where t.trtcleunik=tt.trtcleunik and tt.lmcleunik=" + urlmcleunik

                    +" order by tt.trattraduction;";

                else if(cas==2)

                    sqlrequete = "select t.trtcleunik, tt.trattraduction, tt.tratabrev"

                    +" from transport t, traductiontransport tt"

                    +" where t.trtcleunik=tt.trtcleunik and tt.lmcleunik=" + urlmcleunik

                    +" order by tt.trattraduction;";

                tvaregime=Transaction.generecombostest(sqlrequete,usercon);

                buf.setValue("transport",tvaregime);

                buf.linkNewName(signature,"transportDir");

                buf.setValue(signature, new Long(System.currentTimeMillis()));

            }

        }

        return tvaregime;

    }

    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {

        ArrayList returnvalue=null;

        String sqlrequete=null;

        String sqlrequete2=null;

        int  nbrLigne=0;

        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";

        sqlrequete2="select lmcleunik,tratabrev,trattraduction from traductiontransport where trtcleunik="+objectCleunik+" order by lmcleunik;";

        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);

        return returnvalue;

    }

    

    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {

        boolean sw=false;

        ServeurSqlFailure sqe;

        String requetebrochure="SELECT tecleunik_trans  FROM brochure WHERE tecleunik_trans =? ";

        //String requetehotel="SELECT pyscleunik FROM hotel WHERE pyscleunik=? OR  prestpycleunik=?";

        String deletepays="DELETE from transport WHERE trtcleunik =?";

        try{

        PreparedStatement pstmt=connect.getConuser().prepareStatement(requetebrochure);

        pstmt.setInt(1,new Long(objectCleunik).intValue());        

        ResultSet result=pstmt.executeQuery();

        result.beforeFirst();

        while(result.next()){

            sw=true;           

        }

        if(sw){

               sqe=new ServeurSqlFailure("Enregistrement li�, impossible de l'effacer");

               sqe.setErrorcode(120);

               throw sqe; 

        }

        else {

            synchronized (connect.getBuffer()) {

            Transaction.begin(connect.getConuser());

            pstmt=connect.getConuser().prepareStatement(deletepays);

            pstmt.setInt(1,new Long(objectCleunik).intValue());

            pstmt.execute();     

            Transaction.commit(connect.getConuser());

            connect.getBuffer().invalidateBuffer("transportDir");

            }

        }       

    }catch(SQLException se){

        Transaction.rollback(connect.getConuser());

        sqe=new ServeurSqlFailure("Erreur lors de la requete � la base de donn�e");

        sqe.setErrorcode(se.getErrorCode());

        throw sqe; 

        

        

    }

    }

    

}

