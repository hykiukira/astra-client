/*

 * SignalitiqueTitrePersonne.java

 *

 * Created on 22 août 2002, 10:37

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

public class SignalitiqueTitrePersonne implements srcastra.astra.sys.signalitique.Signalitique {

    

    /** Creates a new instance of SignalitiqueTitrePersonne */

    public SignalitiqueTitrePersonne() {

    }

    

    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TitrePersonne");

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

        Gestionerreur_T tmperreur = new Gestionerreur_T();

        ServeurBuffer buf=connect.getBuffer();

        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_TITREPERSONNES);

        buf.linkNewName(signature,"titrePersDir");

        if(cas==1)

            sqlrequete="select t.tscleunik, t.tsdatetimecrea, t.tsdatetimemodif, t.snumerosessioncrea, t.snumerosessionmodif, ts.lmcleunik, ts.tsintitule, ts.tsabrege from titrepers t, traductiontitrepers ts where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik + " and t.tscleunik =" + objcleunik + ";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="select t.tscleunik, t.tsdatetimecrea, t.tsdatetimemodif, t.snumerosessioncrea, t.snumerosessionmodif, ts.lmcleunik, ts.tsintitule, ts.tsabrege from titrepers t, traductiontitrepers ts where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik + " and t.tscleunik =" + objcleunik + " FOR UPDATE;";

        }

        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);

        if(tmperreur.getErreurcode()==10000)

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0){

                    tmpresult.first();

                    srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T tmpCod = new srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T(tmpresult.getInt(1), tmpresult.getInt(6), tmpresult.getString(7), tmpresult.getString(8), tmpresult.getDate(2), tmpresult.getDate(3), tmpresult.getString(4), tmpresult.getString(5));

                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);

                    tmpCod.setdata(tmpArray);

                    tmpCod.setErreurcode(tmperreur.getErreurcode());

                    tmpCod.setErreurmessage(tmperreur.getErreurmessage());

                    returnValue=(Object)tmpCod;

                }

                tmpresult.close();

            }

            catch(SQLException e){

                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  dans chargeObjectPopup -> case : titrePersonnes "+e.getErrorCode()+"  "+e.getMessage());

            }

        catch(Exception e1){

            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  dans chargeObjectPopup -> case : titrePersonnes :"+e1);

        }

        else{

            srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T tmpCod=new srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T();

            tmpCod.setErreurcode(tmperreur.getErreurcode());

            tmpCod.setErreurmessage(tmperreur.getErreurmessage());

            returnValue=(Object)tmpCod;

        }

        return returnValue;

    }

    

    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TitrePersonne");

    }

    

    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T tmpTitrePers=(srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T)objdp;

        ServeurBuffer buf = connect.getBuffer();

        Gestionerreur_T tmpret = new Gestionerreur_T();

      //  if(cas==1){

            String [] sqlrequete=new String[2];

            sqlrequete[0] = "insert into titrepers(tsdatetimecrea, tsdatetimemodif, snumerosessioncrea, snumerosessionmodif) values (NOW(), NOW(), '" + connect.getUrnumerosession() + "','" + connect.getUrnumerosession() + "')";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    for(int j=0;j<=tmpTitrePers.getNbrLangue();j++) {

                        //Object[] tmpobj=(Object[])tmplist.get(j);

                        sqlrequete[1] = "insert into traductiontitrepers (tscleunik, lmcleunik, tsintitule, tsabrege) values(" + tmpret.getTmpint() + "," + (j+1) + ",'" + tmpTitrePers.getTsintitule() + "','" + tmpTitrePers.getTsabrege() + "');";

                        System.out.println("[******************et qwe vici]"+sqlrequete[1]);

                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    }

                    Transaction.commit(connect.getConuser());

                }

                buf.invalidateBuffer("titrePersDir");

            }

        

        return tmpret;

    }

    

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T tmpTitrePers=(srcastra.astra.sys.classetransfert.signaletique.TitrePersonne_T)objdp;

        ServeurBuffer buf = connect.getBuffer();

        Gestionerreur_T tmpret = new Gestionerreur_T();

     //   if(cas==1){

            String[] sqlrequete=new String[2];

            sqlrequete[0] = "update titrepers set tsdatetimemodif=" + tmpTitrePers.getTsdatetimemodif() + ", snumerosessionmodif='" + connect.getUrnumerosession() + "' where tscleunik=" + tmpTitrePers.getTscleunik() + ";";

            if(tmpTitrePers.getTmpString().compareTo("none")==0)

                sqlrequete[1] = "insert into traductiontitrepers values (" + tmpTitrePers.getTscleunik() + "," + tmpTitrePers.getLmcleunik() + ",'" + tmpTitrePers.getTsintitule() + "','" + tmpTitrePers.getTsabrege() + "')";

            else

                sqlrequete[1] = "update traductiontitrepers set tsintitule='" + tmpTitrePers.getTsintitule() + "', tsabrege='" + tmpTitrePers.getTsabrege() + "' where tscleunik=" + tmpTitrePers.getTscleunik() + " and lmcleunik=" + tmpTitrePers.getLmcleunik() + ";";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    if(tmpret2.getErreurcode()==10000) {

                        Transaction.commit(connect.getConuser());

                    }

                }

                buf.invalidateBuffer("titrePersDir");

            }

      //  }

        return tmpret;

    }

    

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for TitrePersonne");

    }

    

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        return renvSignalitiqueTitrePers(urlmcleunik, connect.getConuser(), cas, connect.getBuffer());

    }

    

    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {

        return renvcomTitrePers(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());

    }

    

    private CompressArray renvSignalitiqueTitrePers(int urlmcleunik, Connection usercon, int cas, ServeurBuffer buf) {
        String tmplangue;
        String sqlrequete = null;
        CompressArray cp;
        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_TITREPERSONNES );
        synchronized (buf) {
            sqlrequete = "select t.tscleunik, ts.tsintitule, ts.tsabrege"
                    +" from titrepers t, traductiontitrepers ts"
                    +" where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik
                    + " order by  ts.tsintitule;";
             return GestionnaireSignaletique.renvsignaletique(sqlrequete,urlmcleunik,buf,astrainterface.COMBO_TITREPERSONNES,usercon,"signTitrePersCas"+urlmcleunik,"titrePersDir");

          /*  if(cas==1)

                if (buf.isValid("signTitrePersCas1"))

                    cp=buf.getValue("signTitrePersCas1");

                else {

                    sqlrequete = "select t.tscleunik, ts.tsintitule, ts.tsabrege"

                    +" from titrepers t, traductiontitrepers ts"

                    +" where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik

                    + " order by  ts.tsintitule;";

                    cp=Transaction.generecombostest(sqlrequete, usercon);

                    buf.setValue("signTitrePersCas1",cp);

                    buf.linkNewName(signature,"titrePersDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            

            else if (cas==2)

                if (buf.isValid("signTitrePersCas2"))

                    cp=buf.getValue("signTitrePersCas2");

                else {

                    sqlrequete = "select t.tscleunik,ts.tsabrege, ts.tsintitule "

                    +" from titrepers t, traductiontitrepers ts"

                    +" where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik

                    + " order by ts.tsabrege;";

                    cp=Transaction.generecombostest(sqlrequete, usercon);

                    buf.setValue("signTitrePersCas2",cp);

                    buf.linkNewName(signature,"titrePersDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            else

                cp=null;*/

        }

      //  return cp;

    }

    

    private java.util.ArrayList renvcomTitrePers(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {

        String sqlrequete=null;

        ArrayList tvaregime;

        String signature=QueryKeyGen.renvcombo(srcastra.astra.sys.rmi.astrainterface.COMBOTYPE_TITREPERS, 0, urlmcleunik, plettre, "", cas);

        synchronized (buf) // In this case, values 1 and 2 of cas can be cached together :)

        {

            if (buf.isValid("titrePers"))

                tvaregime=buf.getValue("titrePers");

            else {

                if(cas==1)

                    // sqlrequete="select t.vacleunik,t.vavaleurtva,tr.tratraduction from valeurtva t,traductiontva tr where t.vacleunik=tr.vacleunik  and tr.lmcleunik="+urlmcleunik+"  t.vavaleurtva like ('"+plettre+"%') order by t.vavaleurtva;";

                    sqlrequete = "select t.tscleunik, ts.tsintitule, ts.tsabrege"

                    +" from titrepers t, traductiontitrepers ts"

                    +" where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik

                    +" order by ts.tsintitule;";

                else if(cas==2)

                    // sqlrequete="select t.vacleunik,t.vavaleurtva,tr.tratraduction from valeurtva t,traductiontva tr where t.vacleunik=tr.vacleunik  and tr.lmcleunik="+urlmcleunik+" order by t.vavaleurtva;";

                    sqlrequete = "select t.tscleunik,ts.tsabrege,ts.tsintitule"

                    +" from titrepers t, traductiontitrepers ts"

                    +" where t.tscleunik=ts.tscleunik and ts.lmcleunik=" + urlmcleunik

                    +" order by ts.tsabrege;";

                tvaregime=Transaction.generecombostest(sqlrequete,usercon);

                buf.setValue("titrePers",tvaregime);

                buf.linkNewName(signature,"titrePersDir");

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

        sqlrequete2 = "select lmcleunik, tsintitule, tsabrege from traductiontitrepers where tscleunik=" + objectCleunik + " order by lmcleunik;";

        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);

        

        return returnvalue;

    }

    

    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {

         boolean sw=false;

        ServeurSqlFailure sqe;

        String requetepassager="SELECT tscleunik  FROM passager WHERE tscleunik =? ";

        //String requetehotel="SELECT pyscleunik FROM hotel WHERE pyscleunik=? OR  prestpycleunik=?";

        String deletepays="DELETE from titrepers WHERE tscleunik =?";

        try{

        PreparedStatement pstmt=connect.getConuser().prepareStatement(requetepassager);

        pstmt.setInt(1,new Long(objectCleunik).intValue());        

        ResultSet result=pstmt.executeQuery();

        result.beforeFirst();

        while(result.next()){

            sw=true;           

        }

        if(sw){ 

               sqe=new ServeurSqlFailure("Enregistrement lié, impossible de l'effacer");

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

            connect.getBuffer().invalidateBuffer("titrePersDir");

            }

        }       

    }catch(SQLException se){

        Transaction.rollback(connect.getConuser());

        sqe=new ServeurSqlFailure("Erreur lors de la requete à la base de donnée");

        sqe.setErrorcode(se.getErrorCode());

        throw sqe;         

    }

    }

    

  

    

}

