/*

 * SignalitiqueLogement.java

 *

 * Created on 22 août 2002, 10:33

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

public class SignalitiqueLogement implements srcastra.astra.sys.signalitique.Signalitique {

    

    /** Creates a new instance of SignalitiqueLogement */

    public SignalitiqueLogement() {

    }
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobject not supported for Logement");
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

        Gestionerreur_T tmperreur=new Gestionerreur_T();

        ServeurBuffer buf=connect.getBuffer();

        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_LOGEMENT);

        buf.linkNewName(signature,"logementDir");

        if(cas==1)

            sqlrequete="select l.ltcleunik, l.ltdatetimecrea, l.ltdatetimemodi, l.snumerosessioncrea, l.snumerosessionmodif, lt.lmcleunik, lt.ltintitule, lt.ltabrege from logement l, traductionlogement lt where l.ltcleunik=lt.ltcleunik and lt.lmcleunik="+urlmcleunik+" and l.ltcleunik="+objcleunik+";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="select l.ltcleunik, l.ltdatetimecrea, l.ltdatetimemodi, l.snumerosessioncrea, l.snumerosessionmodif, lt.lmcleunik, lt.ltintitule, lt.ltabrege from logement l, traductionlogement lt where l.ltcleunik=lt.ltcleunik and lt.lmcleunik="+urlmcleunik+" and l.ltcleunik="+objcleunik+" FOR UPDATE;";

        }

        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);

        if(tmperreur.getErreurcode()==10000)

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0){

                    tmpresult.first();

                    srcastra.astra.sys.classetransfert.signaletique.Logement_T tmpCod = new srcastra.astra.sys.classetransfert.signaletique.Logement_T(tmpresult.getInt(1), 0, tmpresult.getInt(6), tmpresult.getString(7), tmpresult.getString(8), tmpresult.getDate(2), tmpresult.getDate(3), tmpresult.getString(4), tmpresult.getString(5));

                    

                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);

                    tmpCod.setdata(tmpArray);

                    tmpCod.setErreurcode(tmperreur.getErreurcode());

                    tmpCod.setErreurmessage(tmperreur.getErreurmessage());

                    returnValue=(Object)tmpCod;

                }

                tmpresult.close();

            }

            catch(SQLException e){

                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  dans chargeObjectPopup -> case : logement  "+e.getErrorCode()+"  "+e.getMessage());

            }

        catch(Exception e1){

            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  dans chargeObjectPopup -> case : logement :"+e1);

        }

        else{

            srcastra.astra.sys.classetransfert.signaletique.Logement_T tmpCod=new srcastra.astra.sys.classetransfert.signaletique.Logement_T();

            tmpCod.setErreurcode(tmperreur.getErreurcode());

            tmpCod.setErreurmessage(tmperreur.getErreurmessage());

            returnValue=(Object)tmpCod;

        }

        return returnValue;

    }

    

    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobjectcombo not supported for Logement");

    }

    

    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.classetransfert.signaletique.Logement_T tmpLogement=(srcastra.astra.sys.classetransfert.signaletique.Logement_T)objdp;

        ServeurBuffer buf = connect.getBuffer();

        Gestionerreur_T tmpret = new Gestionerreur_T();

       

            String[] sqlrequete=new String[2];

            sqlrequete[0] = "insert into logement(ltdatetimecrea, ltdatetimemodi, snumerosessioncrea, snumerosessionmodif) values (NOW(), NOW(), '" + connect.getUrnumerosession() + "','" + connect.getUrnumerosession() + "');";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    for(int j=0;j<=tmpLogement.getNbrLangue();j++) {

                        //Object[] tmpobj=(Object[])tmplist.get(j);

                        sqlrequete[1] = "insert into traductionlogement (ltcleunik, lmcleunik, ltintitule, ltabrege) values(" + tmpret.getTmpint() + "," + (j+1) + ",'" + tmpLogement.getLtintitule() + "','" + tmpLogement.getLtabrege() + "');";

                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    }

                    Transaction.commit(connect.getConuser());

                }

                buf.invalidateBuffer("logementDir");

            }

        

        return tmpret;

    }

    

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.classetransfert.signaletique.Logement_T tmpLogement=(srcastra.astra.sys.classetransfert.signaletique.Logement_T)objdp;

        ServeurBuffer buf = connect.getBuffer();

        Gestionerreur_T tmpret=new Gestionerreur_T();

        if(cas==1){

            String[] sqlrequete=new String[2];

            sqlrequete[0] = "update logement set ltdatetimemodi="+tmpLogement.getLtdatetimemodif()+", snumerosessionmodif='"+connect.getUrnumerosession()+"' where ltcleunik="+tmpLogement.getLtcleunik()+";";

            if(tmpLogement.getTmpString().compareTo("none")==0)

                sqlrequete[1] = "insert into traductionlogement values (" + tmpLogement.getLtcleunik() + "," + tmpLogement.getLmcleunik() + ",'" + tmpLogement.getLtintitule() + "','" + tmpLogement.getLtabrege() + "');";

            else

                sqlrequete[1] = "update traductionlogement set ltintitule='" + tmpLogement.getLtintitule() + "', ltabrege='" + tmpLogement.getLtabrege() + "' where ltcleunik=" + tmpLogement.getLtcleunik() + " and lmcleunik=" + tmpLogement.getLmcleunik() + ";";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    if(tmpret2.getErreurcode()==10000) {

                        Transaction.commit(connect.getConuser());

                    }

                }

                buf.invalidateBuffer("logementDir");

            }

        }

        return tmpret;

    }

    

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvIntitule not supported for Logement");

    }

    

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        return renvSignalitiqueLogement(urlmcleunik, connect.getConuser(), cas, connect.getBuffer());

    }

    

    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {

        return renvcomLogement(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());

    }

    

    private CompressArray renvSignalitiqueLogement(int urlmcleunik, Connection usercon, int cas, ServeurBuffer buf) {

        String tmplangue;

        String sqlrequete = null;

        CompressArray cp;

        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_LOGEMENT );

        synchronized (buf) {

                  sqlrequete = "select l.ltcleunik, lt.ltintitule, lt.ltabrege"

                    +" from logement l, traductionlogement lt"

                    +" where l.ltcleunik=lt.ltcleunik and lt.lmcleunik="+urlmcleunik

                    +" order by lt.ltintitule;";

                 return GestionnaireSignaletique.renvsignaletique(sqlrequete,urlmcleunik,buf,astrainterface.COMBO_LOGEMENT,usercon,"signLogementCas"+urlmcleunik,"logementDir");

          /*  if(cas==1)

                if (buf.isValid("signLogementCas1"))

                    cp=buf.getValue("signLogementCas1");

                else {

                    sqlrequete = "select l.ltcleunik, lt.ltintitule, lt.ltabrege"

                    +" from logement l, traductionlogement lt"

                    +" where l.ltcleunik=lt.ltcleunik and lt.lmcleunik="+urlmcleunik

                    +" order by lt.ltintitule;";

                    cp=Transaction.generecombostest3(sqlrequete, usercon);

                    buf.setValue("signLogementCas1",cp);

                    buf.linkNewName(signature,"logementDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            else if (cas==2)

                if (buf.isValid("signLogementCas2"))

                    cp=buf.getValue("signLogementCas2");

                else {

                    sqlrequete = "select l.ltcleunik,lt.ltabrege,lt.ltintitule"

                    +" from logement l, traductionlogement lt"

                    +" where l.ltcleunik=lt.ltcleunik and lt.lmcleunik="+urlmcleunik

                    +" order by lt.ltabrege;";

                    cp=Transaction.generecombostest3(sqlrequete, usercon);

                    buf.setValue("signLogementCas2",cp); 

                    buf.linkNewName(signature,"logementDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            else

                cp=null;*/

        }

        //return cp;

    }

    

    public java.util.ArrayList renvcomLogement(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {

        String sqlrequete=null;

        ArrayList tvaregime;

        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_LOGEMENT, 0, urlmcleunik, plettre, "", cas);

        synchronized (buf) {

            if (buf.isValid("logement"))

                tvaregime=buf.getValue("logement");

            else {

                if(cas==1)

                    sqlrequete = "select l.ltcleunik, lt.ltintitule, lt.ltabrege"

                    +" from logement l, traductionlogement lt"

                    +" where l.ltcleunik=lt.ltcleunik and lt.lmcleunik=" + urlmcleunik

                    +" order by lt.ltintitule;";

                else if(cas==2)

                    sqlrequete = "select l.ltcleunik, lt.ltintitule, lt.ltabrege"

                    +" from logement l, traductionlogement lt"

                    +" where l.ltcleunik=lt.ltcleunik and lt.lmcleunik=" + urlmcleunik

                    +" order by lt.ltintitule;";

                tvaregime=Transaction.generecombostest(sqlrequete,usercon);

                buf.setValue("logement",tvaregime);

                buf.linkNewName(signature,"langueDir");

                buf.setValue(signature, new Long(System.currentTimeMillis()));

            }

            return tvaregime;

        }

    }

    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {

        ArrayList returnvalue=null;

        String sqlrequete=null;

        String sqlrequete2=null;

        int  nbrLigne=0;

        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";

        sqlrequete2="select lmcleunik,ltintitule, ltabrege from traductionlogement where ltcleunik="+objectCleunik+" order by lmcleunik;";

        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);

        

        return returnvalue;

    }

    

    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {

        boolean sw=false;

        ServeurSqlFailure sqe;

        String requetebrochure="SELECT tecleunik_log   FROM brochure WHERE tecleunik_log  =? ";

        String requetehotel="SELECT ltcleunik  FROM hotel WHERE ltcleunik =? ";

        String deletepays="DELETE from logement WHERE ltcleunik =?";

        try{

        PreparedStatement pstmt=connect.getConuser().prepareStatement(requetebrochure);

        pstmt.setInt(1,new Long(objectCleunik).intValue());        

        ResultSet result=pstmt.executeQuery();

        result.beforeFirst();

        while(result.next()){ 

            sw=true;           

        }

        pstmt=connect.getConuser().prepareStatement(requetehotel); 

        pstmt.setInt(1,new Long(objectCleunik).intValue());        

        result=pstmt.executeQuery();

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

            connect.getBuffer().invalidateBuffer("logementDir");

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

