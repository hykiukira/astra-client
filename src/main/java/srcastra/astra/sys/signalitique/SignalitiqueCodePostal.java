/*















 * SignalitiqueCodePostal.java















 *















 * Created on 22 ao�t 2002, 9:21















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















public class SignalitiqueCodePostal implements srcastra.astra.sys.signalitique.Signalitique {















    















    /** Creates a new instance of SignalitiqueCodePostal */















    public SignalitiqueCodePostal() {















    }















    















    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas,Poolconnection connect) throws RemoteException {















        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for codepostal");















    }















    















    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas,Poolconnection connect) throws RemoteException {















        















        String sqlrequete=null;















        ResultSet tmpresult=null;















        ResultSet tmpresult2=null;















        String tmpcrea=null;















        String tmpmodif=null;















        ArrayList tmpArray=null;















        int tmpNbrLigne;















        Object returnValue=null;















        boolean isOk=false;       /*mis � true si la requete est ok*/















        Statement select;















        ServeurBuffer buf=connect.getBuffer();















        Gestionerreur_T tmperreur=new Gestionerreur_T();















        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_CODE_POST);















        buf.linkNewName(signature,"codePostalDir");















        if(cas==1)















            sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction,tx.lmcleunik from codepostaux c,traductioncodpostaux tx where c.cxcleunik=tx.cxcleunik and tx.lmcleunik="+urlmcleunik+" and c.cxcleunik="+objcleunik+";";















        else if(cas==2) {















            Transaction.begin(connect.getConuser());















            sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction,tx.lmcleunik from codepostaux c,traductioncodpostaux tx where c.cxcleunik=tx.cxcleunik and tx.lmcleunik="+urlmcleunik+" and c.cxcleunik="+objcleunik+" FOR UPDATE;";















        }















        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);















        if(tmperreur.getErreurcode()==10000)















            try{















                tmpresult.last();















                tmpNbrLigne=tmpresult.getRow();















                if(tmpNbrLigne!=0) {















                    tmpresult.first();















                    srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb tmpCod=new srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb(tmpresult.getInt(1),tmpresult.getString(2),tmpresult.getString(3),tmpresult.getInt(4));















                    tmpArray=ChargePopupTraduction(connect.getConuser(),cas,objcleunik);















                    tmpCod.setdata(tmpArray);















                    isOk=true;















                    tmpCod.setErreurcode(tmperreur.getErreurcode());















                    tmpCod.setErreurmessage(tmperreur.getErreurmessage());















                    returnValue=(Object)tmpCod;















                }















                tmpresult.close();















            }















            catch(SQLException e){















                Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"Erreur dans  ChargeCodePostaux: "+e.getErrorCode()+"  "+e.getMessage());















            }















        catch(Exception e1){















            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"erreur dans chargecodepostaux :"+e1);















        }















        else{















            srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb tmpCod=new srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb();















            tmpCod.setErreurcode(tmperreur.getErreurcode());















            tmpCod.setErreurmessage(tmperreur.getErreurmessage());















            returnValue=(Object)tmpCod;















        }















        return returnValue;















    }















    















    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik,Poolconnection connect) throws RemoteException {















        String sqlrequete=null;















        ResultSet tmpresult=null;















        ResultSet tmpresult2=null;















        String tmpcrea=null;















        String tmpmodif=null;















        int tmpNbrLigne;















        Object returnValue=null;















        Statement select;















        sqlrequete="select cxcode from codepostaux where cxcleunik="+ objectCleunik+";";















        tmpresult=Transaction.execrequete(sqlrequete,connect.getConuser());















        try{















            tmpresult.last();















            tmpNbrLigne=tmpresult.getRow();















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















    















    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas,Poolconnection connect) throws RemoteException {




        boolean x=false;















        String[] sqlrequete;















        String sqlrequete2;















        Gestionerreur_T tmpret=null;















        ServeurBuffer buf=connect.getBuffer();















        ArrayList tmplist=null;















        srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb tmpCdpt=(srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb)objdp;















      //  if(cas==1){















            sqlrequete=new String[2];















            sqlrequete[0]="insert into codepostaux(cxcode,cxintitulegen) values('"+tmpCdpt.getCxcode()+"','"+tmpCdpt.getCxlocalite()+"');";















            synchronized (buf)   // Ensure buffer is thread safe
            {
                Transaction.begin(connect.getConuser());
                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());
                if(tmpret.getErreurcode()==10000) {
                    for(int j=0;j<=tmpCdpt.getNbrLangue();j++) {
                        sqlrequete[1]="insert into traductioncodpostaux(cxcleunik,txtraduction,lmcleunik) values("+tmpret.getTmpint()+",'"+tmpCdpt.getCxlocalite()+"',"+(j+1)+");";
                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());
                    }















                    Transaction.commit(connect.getConuser());















                    buf.invalidateBuffer("codePostalDir");















                }















            }















      // }















        return tmpret;















    }















    















    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas,Poolconnection connect) throws RemoteException {

        boolean x=false;

        String[] sqlrequete;

        String sqlrequete2=null;

        Gestionerreur_T tmpret=null;

        Gestionerreur_T tmpret2=null;

        ServeurBuffer buf = connect.getBuffer();

        ArrayList tmplist=null;

        srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb tmpCdpt=(srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb)objdp;

            sqlrequete=new String[2];

            sqlrequete[0]="update codepostaux set cxcode='"+tmpCdpt.getCxcode()+"' where cxcleunik="+tmpCdpt.getCxcleunik()+";";

            if(tmpCdpt.getTmpString().compareTo("none")==0)

                sqlrequete[1]="insert into traductioncodpostaux values("+tmpCdpt.getCxcleunik()+",'"+tmpCdpt.getCxlocalite()+"',"+tmpCdpt.getCxlangue()+");";

            else

                sqlrequete[1]="update traductioncodpostaux set txtraduction='"+tmpCdpt.getCxlocalite()+"' where cxcleunik="+tmpCdpt.getCxcleunik()+" and lmcleunik="+tmpCdpt.getCxlangue()+";";

            synchronized (buf) // Ensure buffer is thread safe

            {

                Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    tmpret2=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    if(tmpret2.getErreurcode()==10000) {

                        Transaction.commit(connect.getConuser());

                        buf.invalidateBuffer("codePostalDir");

                    }

                }

            }















      //  }















        return tmpret;















    }















    















    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik,Poolconnection connect) throws RemoteException {















        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for ChargeObject");















    }















    















    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas,Poolconnection connect) throws RemoteException {















        return renvSignalitiqueCodepostaux(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());















    }















    















    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas,Poolconnection connect) throws RemoteException {















        return renvcombocodepostaux(urlmcleunik, plettre, connect.getConuser(), cas, connect.getBuffer());















    }















    















    private java.util.ArrayList renvcombocodepostaux(int urlmcleunik,char plettre,Connection usercon,int cas, ServeurBuffer buf) {















        String tmplangue;















        String sqlrequete=null;















        ArrayList cp;















        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_CODEPOST, 0, urlmcleunik, plettre,"", cas);















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















            sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction from codepostaux c,traductioncodpostaux tx where c.cxcleunik=tx.cxcleunik and  c.cxcode like('"+plettre+"%') and tx.lmcleunik="+urlmcleunik+" order by cxcode;";















            cp=Transaction.generecombostest(sqlrequete,usercon);















            if (!buf.isValid(signature)) {















                buf.linkNewName(signature,"codePostalDir");















                buf.setValue(signature, new Long(System.currentTimeMillis()));















            }















        }















        else if(cas==2) {















            synchronized (buf)                   // a buffered request















            {















                if (buf.isValid("codePostal"))       // a buffer exist















                    cp=buf.getValue("codePostal");















                else                                 // Create the buffer















                {















                    sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction from codepostaux c,traductioncodpostaux tx where c.cxcleunik=tx.cxcleunik and tx.lmcleunik="+urlmcleunik+" order by cxcode;";















                    cp=Transaction.generecombostest(sqlrequete,usercon);















                    buf.setValue("codePostal",cp);















                    buf.linkNewName(signature,"codePostalDir");















                    buf.setValue(signature, new Long(System.currentTimeMillis()));















                }















            }















        }















        else















            cp=null;















        // else if(cas==2)















        // sqlrequete="select c.cxcleunik,tx.txtraduction,c.cxcode from codepostaux c,traductioncodpostaux tx where c.cxcleunik=tx.cxcleunik and tx.lmcleunik="+urlmcleunik+" order by tx.txtraduction;";















        return cp;















    }















    















    private CompressArray renvSignalitiqueCodepostaux(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf) {       



        String tmplangue;



        String sqlrequete=null;



        CompressArray cp;



       // String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_CODE_POST);



        synchronized(buf) {



            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"renv signalitique code postaux:"+cas);



            sqlrequete="select c.cxcleunik,c.cxcode,tx.txtraduction"

                    +" from codepostaux c,traductioncodpostaux tx"

                    +" where c.cxcleunik=tx.cxcleunik"

                    +" and tx.lmcleunik="+urlmcleunik 

                    +" order by cxcode;";

           return GestionnaireSignaletique.renvsignaletique(sqlrequete,urlmcleunik,buf,astrainterface.COMBO_CODE_POST,usercon,"signCodePostalCas"+urlmcleunik,"codePostalDir");







       }















      //  return cp;















    }















    private ArrayList ChargePopupTraduction(Connection usercon,int cas,int objectCleunik) {















        ArrayList returnvalue=null;















        String sqlrequete=null;















        String sqlrequete2=null;















        int  nbrLigne=0;















        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";















        sqlrequete2="select lmcleunik,txtraduction from traductioncodpostaux where cxcleunik="+objectCleunik+" order by lmcleunik;";















        returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















        















        return returnvalue;















    }















    















    public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {















        boolean sw=false;















        ServeurSqlFailure sqe;















        String requetepassager="SELECT cxcleunik FROM passager WHERE cxcleunik=? ";















        String requeteintermediairepasstaxi="SELECT pass_aller_cp FROM intermediairepassagertaxi  WHERE pass_aller_cp=? OR  pas_tax_retour_cp=?";















        String deletepays="DELETE from codepostaux  WHERE cxcleunik=?";















        try{















        PreparedStatement pstmt=connect.getConuser().prepareStatement(requetepassager);















        pstmt.setInt(1,new Long(objectCleunik).intValue());        















        ResultSet result=pstmt.executeQuery();















        result.beforeFirst();















        while(result.next()){ 















            sw=true;           















        }















        pstmt=connect.getConuser().prepareStatement(requeteintermediairepasstaxi);















        pstmt.setInt(1,new Long(objectCleunik).intValue());















        pstmt.setInt(2,new Long(objectCleunik).intValue()); 















        result=pstmt.executeQuery();















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















            connect.getBuffer().invalidateBuffer("codePostalDir");















            }















        }       















    }catch(SQLException se){















        Transaction.rollback(connect.getConuser());















        sqe=new ServeurSqlFailure("Erreur lors de la requete � la base de donn�e");















        sqe.setErrorcode(se.getErrorCode());















        throw sqe; 















        















        















    }















   }















   















  















    















    















    















    















    /////////////////////////////////////:















    ////////////////////////////////////::















    /////////////////////////////////////:















    /*private ArrayList ChargePopupTraduction(int  typeDePopup,Connection usercon,int cas,int objectCleunik) {















        ArrayList returnvalue=null;















        String sqlrequete=null;















        String sqlrequete2=null;















        int  nbrLigne=0;















        sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";















        switch(typeDePopup) {















            case srcastra.astra.sys.rmi.astrainterface.COMBO_CODE_POST:















                sqlrequete2="select lmcleunik,txtraduction from traductioncodpostaux where cxcleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_PAYS:















                sqlrequete2="select lmcleunik,pystraduction from traductionpays where pyscleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_DEVISE:















                sqlrequete2="select lmcleunik,tretraduction from traductiondevise where decleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_LANGUE:















                sqlrequete2="select lmcleunik,letraduction from traductionlangues where lecleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_TVA_TYPE:















                sqlrequete2="select lmcleunik,tratraduction from traductiontva where vacleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,1);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_TRANSPORT:















                sqlrequete2="select lmcleunik,tratabrev,trattraduction from traductiontransport where trtcleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_LOGEMENT:















                sqlrequete2="select lmcleunik,ltintitule, ltabrege from traductionlogement where ltcleunik="+objectCleunik+" order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);















                break;















            case srcastra.astra.sys.rmi.astrainterface.COMBO_TITREPERSONNES:















                sqlrequete2 = "select lmcleunik, tsintitule, tsabrege from traductiontitrepers where tscleunik=" + objectCleunik + " order by lmcleunik;";















                returnvalue=Transaction.selecttraduction(sqlrequete,sqlrequete2,usercon,2);















                break;















     















        }















        return returnvalue;















    }*/















    















    















}















