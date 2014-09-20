/*



 * SignalitiqueDevise.java



 *



 * Created on 22 ao�t 2002, 9:24



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



public class SignalitiqueDevise implements srcastra.astra.sys.signalitique.Signalitique {



    



    /** Creates a new instance of SignalitiqueDevise */



    public SignalitiqueDevise() {



    }



    



    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {



        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeobject not supported for devise");



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



        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_DEVISE);



        ServeurBuffer buf = connect.getBuffer();



        Gestionerreur_T tmperreur=new Gestionerreur_T();



        buf.linkNewName(signature,"deviseDir");



        if(cas==1)



            sqlrequete="select d.decleunik,d.decode,d.devtaux,t.tretraduction,d.dedatetimecrea,d.dedatetimemodi,d.snumerosessioncrea,d.snumerosessionmodif,t.lmcleunik from devise d,traductiondevise t where d.decleunik=t.decleunik and t.lmcleunik="+urlmcleunik+" and d.decleunik="+objcleunik+";";



        else if(cas==2) {



            Transaction.begin(connect.getConuser());



            sqlrequete="select d.decleunik,d.decode,d.devtaux,t.tretraduction,d.dedatetimecrea,d.dedatetimemodi,d.snumerosessioncrea,d.snumerosessionmodif,t.lmcleunik from devise d,traductiondevise t where d.decleunik=t.decleunik and t.lmcleunik="+urlmcleunik+" and d.decleunik="+objcleunik+" FOR UPDATE;";



        }



        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);



        if(tmperreur.getErreurcode()==10000)



            try{



                tmpresult.last();



                tmpNbrLigne=tmpresult.getRow();



                if(tmpNbrLigne!=0) {



                    tmpresult.first();



                    srcastra.astra.sys.classetransfert.signaletique.Devise_Tb tmpCod=new srcastra.astra.sys.classetransfert.signaletique.Devise_Tb(tmpresult.getInt(1),tmpresult.getString(2),tmpresult.getDouble(3),tmpresult.getString(4),tmpresult.getDate(5),tmpresult.getDate(6),tmpresult.getString(7),tmpresult.getString(8));



                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);



                    tmpCod.setdata(tmpArray);



                    tmpCod.setErreurcode(tmperreur.getErreurcode());



                    tmpCod.setErreurmessage(tmperreur.getErreurmessage());



                    returnValue=(Object)tmpCod;



                }



                tmpresult.close();



            }



            catch(SQLException e){



                Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeDevise dans chargepopupcodepostaux: "+e.getErrorCode()+"  "+e.getMessage());



            }



        catch(Exception e1){



            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeDevise dans chargepopupcodepostaux: :"+e1);



        }



        else{



            srcastra.astra.sys.classetransfert.signaletique.Devise_Tb tmpCod=new srcastra.astra.sys.classetransfert.signaletique.Devise_Tb();



            tmpCod.setErreurcode(tmperreur.getErreurcode());



            tmpCod.setErreurmessage(tmperreur.getErreurmessage());



            returnValue=(Object)tmpCod;



        }



        return returnValue;



    }



    



    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {



        String sqlrequete="select decode from devise where decleunik="+ objectCleunik+";";



        ResultSet tmpresult=Transaction.execrequete(sqlrequete,connect.getConuser());



        Object returnValue=null;



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



            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"Erreur dans  ChargeCObjectComboDevise: "+e);



        }



        catch(Exception e1){



            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"erreur dans ChargeCObjectComboDevise:"+e1);



        }



        return returnValue;



    }



    



    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {



        srcastra.astra.sys.classetransfert.signaletique.Devise_Tb devise=(srcastra.astra.sys.classetransfert.signaletique.Devise_Tb)objdp;



        ServeurBuffer buf = connect.getBuffer();



        Gestionerreur_T tmpret = null;



      



            String[] sqlrequete=new String[2];



            sqlrequete[0]="insert into devise(decode,devtaux,dedatetimecrea,dedatetimemodi,snumerosessioncrea,snumerosessionmodif) values('"+devise.getDecode()+"',"+devise.getDevtaux()+",NOW(),NOW(),'"+connect.getUrnumerosession()+"','"+connect.getUrnumerosession()+"');";



            synchronized (buf) {



                Transaction.begin(connect.getConuser());



                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());



                if(tmpret.getErreurcode()==10000) {



                    for(int j=0;j<=devise.getNbrLangue();j++) {



                        sqlrequete[1]="insert into traductiondevise(decleunik,tretraduction,lmcleunik) values("+ tmpret.getTmpint()+",'"+devise.getDevtraduction()+"',"+(j+1)+");";



                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());



                    }



                    Transaction.commit(connect.getConuser());



                }



                buf.invalidateBuffer("deviseDir");



            }



        



        return tmpret;



    }



    



    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {



        srcastra.astra.sys.classetransfert.signaletique.Devise_Tb tmpDev=(srcastra.astra.sys.classetransfert.signaletique.Devise_Tb)objdp;



        Gestionerreur_T tmpret=null;



        ServeurBuffer buf = connect.getBuffer();



       



            String[] sqlrequete=new String[2];



            sqlrequete[0]="update devise set decode='"+tmpDev.getDecode()+"',devtaux="+tmpDev.getDevtaux()+",dedatetimemodi=NOW(),snumerosessionmodif='"+connect.getUrnumerosession()+"'  where decleunik="+tmpDev.getDecleunik()+";";



            if(tmpDev.getTmpString().compareTo("none")==0)



                sqlrequete[1]="insert into traductiondevise values("+tmpDev.getDecleunik()+","+tmpDev.getDevlangue()+",'"+tmpDev.getDevtraduction()+"');";



            else



                sqlrequete[1]="update traductiondevise set tretraduction='"+tmpDev.getDevtraduction()+"' where decleunik="+tmpDev.getDecleunik()+" and lmcleunik="+tmpDev.getDevlangue()+";";



            



            Transaction.begin(connect.getConuser());



            tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());



            if(tmpret.getErreurcode()==10000) {



                Gestionerreur_T tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());



                if(tmpret2.getErreurcode()==10000) {



                    String req="INSERT INTO historiquedevise(decleunik,hedate,hetaux) values("+tmpDev.getDecleunik()+",NOW(),"+tmpDev.getDevtaux()+")";



                    tmpret2=Transaction.execrequeteinsert(req,connect.getConuser());



                    if(tmpret2.getErreurcode()==10000) {



                        Transaction.commit(connect.getConuser());



                        synchronized (buf) {



                            buf.invalidateBuffer("deviseDir");



                        }



                    }



                }



            }



        



        return tmpret;



    }



    



    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {



        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvintitule not supported for devise");



    }



    



    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {



        return renvSignalitiqueDevise(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());



    }



    



    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {



        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvcombo not supported for devise");



    }



    



    private CompressArray renvSignalitiqueDevise(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {



        String tmplangue;
        String sqlrequete=null;
        CompressArray cp;
      //  String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_DEVISE );
        synchronized (buf) {
                    sqlrequete="select d.decleunik,d.decode,t.tretraduction"
                    +" from devise d,traductiondevise t"
                    +" where d.decleunik=t.decleunik  and t.lmcleunik="+urlmcleunik
                    +" order by d.decode;";

       return GestionnaireSignaletique.renvsignaletique(sqlrequete,urlmcleunik,buf,astrainterface.COMBO_DEVISE,usercon,astrainterface.COMBO_DEVISECAS+urlmcleunik,"deviseDir");
        }
     /*               cp=Transaction.generecombostest(sqlrequete,usercon);



                    buf.setValue("signDeviseCas1",cp);



                    buf.linkNewName(signature,"deviseDir");



                    buf.setValue(signature, new Long(System.currentTimeMillis()));



                    



                }



            }



            else if(cas==2) {



                if (buf.isValid("signDeviseCas2"))



                    cp=buf.getValue("signDeviseCas2");



                else {



                    sqlrequete="select d.decleunik,t.tretraduction,d.decode"



                    +" from devise d,traductiondevise t"



                    +" where d.decleunik=t.decleunik  and t.lmcleunik="+urlmcleunik



                    +" order by t.tretraduction;";



                    cp=Transaction.generecombostest(sqlrequete,usercon);



                    buf.setValue("signDeviseCas2",cp);



                    buf.linkNewName(signature,"deviseDir");



                    buf.setValue(signature, new Long(System.currentTimeMillis()));



                }



            }



            else



                cp=null;



        }



        return cp;

*/

    }



    



    private java.util.ArrayList renvcombodevise(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {



        String sqlrequete=null;



        ArrayList devise;



        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_DEVISE, 0, urlmcleunik, plettre, "", cas);



        if(cas==1) {



            sqlrequete="select d.decleunik,d.decode,d.devtaux,t.tretraduction"



            +" from devise d,traductiondevise t"



            +" where d.decleunik=t.decleunik"



            +" and d.decode like ('"+plettre+"%')"



            +" order by d.decode ;";



            devise=Transaction.generecombostest(sqlrequete,usercon);



            if (!buf.isValid(signature)) {



                buf.linkNewName(signature,"deviseDir");



                buf.setValue(signature, new Long(System.currentTimeMillis()));



            }



        }



        else if(cas==2)



            synchronized(buf) {



                if (buf.isValid("devise"))



                    devise=buf.getValue("devise");



                else {



                    sqlrequete="select d.decleunik,d.decode,d.devtaux,t.tretraduction"



                    +" from devise d,traductiondevise t where d.decleunik=t.decleunik"



                    +" order by d.decode ;";



                    devise=Transaction.generecombostest(sqlrequete,usercon);



                    buf.setValue("devise",devise);



                    buf.linkNewName(signature,"deviseDir");



                    buf.setValue(signature, new Long(System.currentTimeMillis()));



                }



            }



        else



            devise= null;



        return devise;



    }



    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {



        ArrayList returnvalue=null;



        String sqlrequete=null;



        String sqlrequete2=null;



        int  nbrLigne=0;



        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";



        sqlrequete2="select lmcleunik,tretraduction from traductiondevise where decleunik="+objectCleunik+" order by lmcleunik;";



        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);



        return returnvalue;



    }



    



    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {



    }



    



}



