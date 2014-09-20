/*

 * SignalitiquePays.java

 *

 * Created on 22 août 2002, 9:21

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

public class SignalitiqueNationalite implements srcastra.astra.sys.signalitique.Signalitique {

    

    /** Creates a new instance of SignalitiquePays */

    public SignalitiqueNationalite() {

    }

    

    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Pays");

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

        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_PAYS);

        buf.linkNewName(signature,"natDir");

        if(cas==1)

            sqlrequete="select n.nae_cleunik,n.nae_abrev,t.nae_traduction  from nationalite n,traduction_nationalite t where n.nae_cleunik=t.nae_cleunik and t.lmcleunik="+urlmcleunik+" and t.nae_cleunik="+objcleunik+";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="select n.nae_cleunik,n.nae_abrev,t.nae_traduction  from nationalite n,traduction_nationalite t where n.nae_cleunik=t.nae_cleunik and t.lmcleunik="+urlmcleunik+" and t.nae_cleunik="+objcleunik+" FOR UPDATE;";

        }

        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);

        if(tmperreur.getErreurcode()==10000) {

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0) {

                    tmpresult.first();

                    srcastra.astra.sys.classetransfert.signaletique.Pays_Tb tmpCod=new  srcastra.astra.sys.classetransfert.signaletique.Pays_Tb(tmpresult.getInt(1),tmpresult.getString(2),tmpresult.getString(3));

                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);

                    tmpCod.setdata(tmpArray);

                    tmpCod.setErreurcode(tmperreur.getErreurcode());

                    tmpCod.setErreurmessage(tmperreur.getErreurmessage());

                    returnValue=(Object)tmpCod;

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

            srcastra.astra.sys.classetransfert.signaletique.Pays_Tb tmpCod=new  srcastra.astra.sys.classetransfert.signaletique.Pays_Tb();

            tmpCod.setErreurcode(tmperreur.getErreurcode());

            tmpCod.setErreurmessage(tmperreur.getErreurmessage());

            returnValue=(Object)tmpCod;

        }

        return returnValue;

    }

    

    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {

        String sqlrequete="select p.pyscleunik,t.pystraduction from pays p,traductionpays t where p.pyscleunik=t.pyscleunik and t.lmcleunik="+urlmcleunik+" and p.pyscleunik="+ objectCleunik+";";

        ResultSet tmpresult=Transaction.execrequete(sqlrequete,connect.getConuser());

        Object returnValue = null;

        try{

            tmpresult.last();

            int tmpNbrLigne=tmpresult.getRow();

            if(tmpNbrLigne!=0) {

                tmpresult.first();

                returnValue=(String)tmpresult.getString(2);

            }

            tmpresult.close();

        }

        catch(SQLException e){

            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"Erreur dans  ChargeCObjectCombopays: "+e);

        }

        catch(Exception e1){

            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"erreur dansChargeCObjectCombopays: :"+e1);

        }

        return returnValue;

    }

    

    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.rmi.utils.ServeurBuffer buf =connect.getBuffer();

        srcastra.astra.sys.classetransfert.signaletique.Pays_Tb tmpPays=(srcastra.astra.sys.classetransfert.signaletique.Pays_Tb)objdp;

        Gestionerreur_T tmpret = null;

    

            String[] sqlrequete=new String[2];

            sqlrequete[0]="insert into pays(pysabrev) values('"+tmpPays.getPysabrev()+"');";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    for(int j=0;j<=tmpPays.getNbrLangue();j++) {

                        sqlrequete[1]="insert into traductionpays(pyscleunik,pystraduction,lmcleunik) values("+ tmpret.getTmpint()+",'"+tmpPays.getPystraduction()+"',"+(j+1)+");";

                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    }

                    Transaction.commit(connect.getConuser());

                }

                buf.invalidateBuffer("paysDir");

            }

        

        return tmpret;

    }

    

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        srcastra.astra.sys.rmi.utils.ServeurBuffer buf =connect.getBuffer();

        srcastra.astra.sys.classetransfert.signaletique.Pays_Tb tmpPays=(srcastra.astra.sys.classetransfert.signaletique.Pays_Tb)objdp;

      //  if(cas==1){

            String[] sqlrequete=new String[2];

            sqlrequete[0]="update pays set pysabrev='"+tmpPays.getPysabrev()+"' where pyscleunik="+tmpPays.getPyscleunik()+";";

            if(tmpPays.getTmpString().compareTo("none")==0)

                sqlrequete[1]="insert into traductionpays(pyscleunik,lmcleunik,pystraduction) values("+tmpPays.getPyscleunik()+","+tmpPays.getPysLangue()+",'"+tmpPays.getPystraduction()+"');";

            else

                sqlrequete[1]="update traductionpays set pystraduction='"+tmpPays.getPystraduction()+"' where pyscleunik="+tmpPays.getPyscleunik()+" and lmcleunik="+tmpPays.getPysLangue()+";";

            synchronized (buf) {

                Transaction.begin(connect.getConuser());

                Gestionerreur_T tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    if(tmpret2.getErreurcode()==10000) {

                        Transaction.commit(connect.getConuser());

                    }

                }

                buf.invalidateBuffer("paysDir");

                return tmpret;

            }

        //}

       // throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Pays");

    }

    

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for Pays");

    }

    

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        return renvSignalitiqueNationalite(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());

    }

    

    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {

        return renvcombopays(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());

    }

    

    public srcastra.astra.sys.compress.CompressArray renvcombopays(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {

        String sqlrequete=null;

        CompressArray pays;

        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_PAYS, 0, urlmcleunik, plettre, "", cas);

        if(cas==1) {

            sqlrequete="select p.pyscleunik,t.pystraduction,p.pysabrev"

            +" from pays p,traductionpays t"

            +" where p.pyscleunik=t.pyscleunik"

            +" and t.lmcleunik="+urlmcleunik

            +" and t.pystraduction like('"+plettre+"%')"

            +" order by t.pystraduction;";

            pays= Transaction.generecombostest(sqlrequete,usercon);

            if (!buf.isValid(signature)) {

                buf.linkNewName(signature,"paysDir");

                buf.setValue(signature,new Long(System.currentTimeMillis()));

            }

        }

        else if(cas==2) {

            synchronized(buf) {

                if (buf.isValid("pays"))

                    pays=buf.getValue("pays");

                else {

                    sqlrequete="select p.pyscleunik,t.pystraduction,p.pysabrev from pays p,traductionpays t where p.pyscleunik=t.pyscleunik and t.lmcleunik="+urlmcleunik+"  order by t.pystraduction;";

                    pays= Transaction.generecombostest(sqlrequete,usercon);

                    buf.setValue("pays",pays);

                    buf.linkNewName(signature,"paysDir");

                    buf.setValue(signature,new Long(System.currentTimeMillis()));

                }

            }

        }

        else

            pays=null;

        return pays;

    }

    private CompressArray renvSignalitiqueNationalite(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {

        String sqlrequete=null;

        CompressArray pays;

        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas,astrainterface.COMBO_PAYS );

        

        synchronized(buf) {

            if(cas==1) {

                if (buf.isValid("natCas1"))

                    pays=buf.getValue("natCas1");

                else {

                    sqlrequete="select n.nae_cleunik,t.nae_traduction,n.nae_abrev"

                    +" from nationalite n,traduction_nationalite t"

                    +" where n.nae_cleunik=t.nae_cleunik and t.lmcleunik="+urlmcleunik

                    +" order by t.nae_traduction;";

                    pays= Transaction.generecombostest3(sqlrequete,usercon);

                    buf.setValue("natCas1",pays);

                    buf.linkNewName(signature,"natDir");

                    buf.setValue(signature, new Long(System.currentTimeMillis()));

                }

            }

            else

                pays=null;

        }

        return pays;

    }

    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {

        ArrayList returnvalue=null;

        String sqlrequete=null;

        String sqlrequete2=null;

        int  nbrLigne=0;

        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";

        sqlrequete2="select lmcleunik,pystraduction from traductionpays where pyscleunik="+objectCleunik+" order by lmcleunik;";

        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);

        return returnvalue;

    }

    

    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {

        boolean sw=false;

        ServeurSqlFailure sqe;

        String requetepassager="SELECT pr_nat FROM passager WHERE pr_nat =? ";

        String deletepays="DELETE from nationalite WHERE nae_cleunik=?";

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

            connect.getBuffer().invalidateBuffer("natDir");

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

