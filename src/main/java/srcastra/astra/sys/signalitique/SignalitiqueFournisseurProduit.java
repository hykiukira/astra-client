/*

 * SignalitiqueFournisseurProduit.java

 *

 * Created on 22 août 2002, 10:50

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

public class SignalitiqueFournisseurProduit implements srcastra.astra.sys.signalitique.Signalitique {

    

    /** Creates a new instance of SignalitiqueFournisseurProduit */

    public SignalitiqueFournisseurProduit() {

    }

    

    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {

        String sqlrequete=null;

        Object returnValue = null;

        if(cas==1)

            sqlrequete="Select frgtcleunik,frcleunik,frgtitrecatalog,frgtreference1,frgtreference2,frgtanalytique,frgtdatetimecrea,frgtdatetimemodi,snumerosessioncrea,snumerosessionmodif from fournisseur_grproduit where frgtcleunik="+objcleunik+";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="Select frgtcleunik,frcleunik,frgtitrecatalog,frgtreference1,frgtreference2,frgtanalytique,frgtdatetimecrea,frgtdatetimemodi,snumerosessioncrea,snumerosessionmodif from fournisseur_grproduit where frgtcleunik="+objcleunik+" FOR UPDATE;";

        }

        ResultSet tmpresult=Transaction.execrequete(sqlrequete,connect.getConuser());

        try{

            tmpresult.last();

            int tmpNbrLigne=tmpresult.getRow();

            if(tmpNbrLigne!=0) {

                tmpresult.first();

                String[] numSession=Transaction.renvNomUserPourSession(connect.getConuser(),tmpresult.getString(9),tmpresult.getString(10));

                returnValue=(FournGrpProduits_T)new FournGrpProduits_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getString(3),tmpresult.getString(4),tmpresult.getString(5),tmpresult.getInt(6),tmpresult.getDate(7),tmpresult.getDate(8),tmpresult.getString(9),tmpresult.getString(10),numSession[0],numSession[1]);

            }

            tmpresult.close();

        }

        catch(SQLException e){

            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeCodePostaux: "+e);

        }

        catch(Exception e1){

            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"erreur dans chargecodepostaux :"+e1);

        }

        return returnValue;

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

        String signature=QueryKeyGen.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas,astrainterface.COMBO_FOURNPROD);

        buf.linkNewName(signature,"fournisseurDir");

        if(cas==1)

            sqlrequete="Select frgtcleunik,frcleunik,frgtitrecatalog,frgtreference1,frgtreference2,aecleunik,frgtdatetimecrea,frgtdatetimemodi,snumerosessioncrea,snumerosessionmodif from fournisseur_grproduit where frgtcleunik="+objcleunik+";";

        else if(cas==2) {

            Transaction.begin(connect.getConuser());

            sqlrequete="Select frgtcleunik,frcleunik,frgtitrecatalog,frgtreference1,frgtreference2,aecleunik,frgtdatetimecrea,frgtdatetimemodi,snumerosessioncrea,snumerosessionmodif from fournisseur_grproduit where frgtcleunik="+objcleunik+" FOR UPDATE;";

        }

        tmpresult=Transaction.execrequete3(sqlrequete,connect.getConuser(),tmperreur);

        if(tmperreur.getErreurcode()==10000) {

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0) {

                    tmpresult.first();

                    String[] numSession=Transaction.renvNomUserPourSession(connect.getConuser(),tmpresult.getString(9),tmpresult.getString(10));

                    FournGrpProduits_T tmpFournProd=(FournGrpProduits_T)new FournGrpProduits_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getString(3),tmpresult.getString(4),tmpresult.getString(5),tmpresult.getInt(6),tmpresult.getDate(7),tmpresult.getDate(8),tmpresult.getString(9),tmpresult.getString(10),numSession[0],numSession[1]);

                    tmpFournProd.setErreurcode(tmperreur.getErreurcode());

                    tmpFournProd.setErreurmessage(tmperreur.getErreurmessage());

                    returnValue=(Object)tmpFournProd;

                    

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

            FournGrpProduits_T tmpFournProd=(FournGrpProduits_T)new FournGrpProduits_T();

            tmpFournProd.setErreurcode(tmperreur.getErreurcode());

            tmpFournProd.setErreurmessage(tmperreur.getErreurmessage());

            returnValue=(Object)tmpFournProd;

        }

        return returnValue;

    }

    

    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurProduit");

    }

    

    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        FournGrpProduits_T tmpGrpProd=(FournGrpProduits_T)objdp;

        Gestionerreur_T tmpret = new Gestionerreur_T();

        Grpdecision_T  grpd;

        if(cas==1){

            String[] sqlrequete=new String[2];

            sqlrequete[0]="insert into fournisseur_grproduit(frcleunik,frgtitrecatalog,frgtreference1,frgtreference2,aecleunik,frgtdatetimecrea,frgtdatetimemodi,snumerosessioncrea,snumerosessionmodif) values("+tmpGrpProd.getFrcleunik()+",'"+tmpGrpProd.getFrgtitrecatalog()+"','"+tmpGrpProd.getFrgtreference1()+"','"+tmpGrpProd.getFrgtreference2()+"','"+tmpGrpProd.getFrgtanalytique()+"',NOW(),NOW(),'"+connect.getUrnumerosession()+"','"+connect.getUrnumerosession()+"');";

            grpd=ChargeDefaultGrpFourn(connect.getConuser(),1);

            Transaction.begin(connect.getConuser());

            tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

            if(tmpret.getErreurcode()==10000) {

                sqlrequete[1]= "insert into groupedecision(frcleunik,ttcleunik,itcleunik,aecleunik,gndatetimecrea,gndatetimemodif,gncodetvavente,"+

            "gntvainclusvente,gncodetvaachat,gntvainclusachat,gnnbravanteche,gnacompteminpp,gnprodstockiata,gnprodstockautre,gnpccomvente,gncominclpvent,gnpcacompte,gnpccomachat,gnpcclerepvend,gnpcclerepconcept,gnpcclerepmmere,snumerosessioncrea,snumerosessionmodif,frgtcleunik,gncomptevente,gncompteachat,gnfracomptepc,gnfrnbda,gnfrsoldepc,gnfrnbds,gnfranchise,caecleunik,tecleunik) values("+grpd.getFrcleunik()+

                ","+grpd.getTtcleunik()+","+grpd.getItcleunik()+","+

                grpd.getAecleunik()+",NOW(),NOW(),"+grpd.getGncodetvavente()+","+grpd.getGntvainclusvente()+","+grpd.getGncodetvaachat()+","+grpd.getGntvainclusachat()+","+grpd.getGnnbravanteche()+","+grpd.getGnacompteminpp()+","+grpd.getGnprodstockiata()+","+grpd.getGnprodstockautre()+","+grpd.getGnpccomvente()+","+grpd.getGncominclpvent()+","+grpd.getGnpcacompte()+","+grpd.getGnpccomachat()+","+grpd.getGnpcclerepvend()+","+grpd.getGnpcclerepconcept()+","+grpd.getGnpcclerepmmere()+",'"+connect.getUrnumerosession()+"','"+connect.getUrnumerosession()+"',"+tmpret.getTmpint()+",'"+grpd.getGncomptevente()+"','"+grpd.getGncompteachat()+"',"+grpd.getGnfracomptepc()+","+grpd.getGnfrnbda()+","+grpd.getGnfrsoldepc()+","+grpd.getGnfrnbds()+","+grpd.getGnfranchise()+","+grpd.getCaecleunik()+","+grpd.getTecleunik()+");";

                Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"requete "+ sqlrequete[1]);

                tmpret=Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                if(tmpret.getErreurcode()==10000) {

                    Transaction.commit(connect.getConuser());

                }

            }

        }

        return tmpret;

    }

    

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {        

        srcastra.astra.sys.rmi.utils.ServeurBuffer buf =connect.getBuffer();

        FournGrpProduits_T  tmpGrpProd=(FournGrpProduits_T)objdp;

        Gestionerreur_T tmpret = new Gestionerreur_T();

        if(cas==1){

            String[] sqlrequete=new String[2];

            sqlrequete[0]="update fournisseur_grproduit set frgtitrecatalog='"+ tmpGrpProd.getFrgtitrecatalog()+"',frgtreference1='"+tmpGrpProd.getFrgtreference1()+"',frgtreference2='"+tmpGrpProd.getFrgtreference2() +"',aecleunik="+tmpGrpProd.getFrgtanalytique() +",frgtdatetimemodi=NOW(),snumerosessionmodif='"+connect.getUrnumerosession()+"' WHERE frgtcleunik ="+tmpGrpProd.getFrgtcleunik()+";";

            synchronized (buf) {

              //  Transaction.begin(connect.getConuser());

                tmpret=Transaction.execrequeteinsert(sqlrequete[0],connect.getConuser());

              /*  if(tmpret.getErreurcode()==10000) {

                    for(int j=0;j<=tmpPays.getNbrLangue();j++) {

                        sqlrequete[1]="insert into traductionpays(pyscleunik,pystraduction,lmcleunik) values("+ tmpret.getTmpint()+",'"+tmpPays.getPystraduction()+"',"+(j+1)+");";

                        Transaction.execrequeteinsert(sqlrequete[1],connect.getConuser());

                    }

                    Transaction.commit(connect.getConuser());

                }*/

                Transaction.commit(connect.getConuser());

                buf.invalidateBuffer("fournisseurDir");

            }

        }

        return tmpret;

    }

    

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurProduit");

    }

    

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {

        throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("operation not supported for FournisseurProduit");

    }

    

    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {

        return renvcomFournProd(urlmcleunik, 'p', connect.getConuser(), cas, cxcode);

    }

    

    private java.util.ArrayList renvcomFournProd(int urlmcleunik,char plettre,Connection usercon,int cas,String objectCleunik) {

        ArrayList tmp;

        String signature=QueryKeyGen.renvcombo(astrainterface.COMBOTYPE_FOURNPROD, 0, urlmcleunik, plettre, objectCleunik, cas);

        int frcleunik=Integer.parseInt(objectCleunik);

        String sqlrequete=null;

        if(cas==1)

            sqlrequete="select frgtcleunik,frgtitrecatalog,frgtreference1 from fournisseur_grproduit where frcleunik="+frcleunik+" AND annuler=0 order by frgtitrecatalog;";

        else if(cas==2)

            sqlrequete="select frgtcleunik,frgtreference1,frgtitrecatalog from fournisseur_grproduit where frcleunik="+frcleunik+" AND annuler=0 order by frgtreference1;";

        tmp=Transaction.generecombostest3(sqlrequete,usercon);

        return tmp;

    }

    private Grpdecision_T  ChargeDefaultGrpFourn(Connection usercon,int tecleunik) {

        String sqlrequet=null;

        Gestionerreur_T tmperreur=new Gestionerreur_T();

        String sqlrequete="SELECT * from groupedecisiondef where tecleunik="+tecleunik+";";

        ResultSet tmpresult=null;

        int tmpNbrLigne;

        Grpdecision_T  tmpGrpDec=new Grpdecision_T();

        tmpresult=Transaction.execrequete3(sqlrequete,usercon,tmperreur);

        if(tmperreur.getErreurcode()==10000) {

            try{

                tmpresult.last();

                tmpNbrLigne=tmpresult.getRow();

                if(tmpNbrLigne!=0) {

                    tmpresult.first();

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /* les 2 nveaux checkBox */   

                    tmpGrpDec=new Grpdecision_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getInt(3),tmpresult.getInt(4),tmpresult.getInt(5), tmpresult.getDate(6),tmpresult.getDate(7),tmpresult.getInt(8),tmpresult.getInt(9),tmpresult.getInt(10),tmpresult.getInt(11),tmpresult.getInt(12),tmpresult.getFloat(13),tmpresult.getInt(14),tmpresult.getInt(15),tmpresult.getFloat(16),tmpresult.getInt(17),tmpresult.getFloat(18),tmpresult.getFloat(19),tmpresult.getFloat(20),tmpresult.getFloat(21),tmpresult.getFloat(22),tmpresult.getString(23),tmpresult.getString(24),tmpresult.getInt(25),tmpresult.getInt(26),tmpresult.getInt(27),tmpresult.getFloat(28),tmpresult.getInt(29),tmpresult.getInt(30),tmpresult.getInt(31),tmpresult.getInt(32),tmpresult.getInt(33),tmpresult.getInt(34),"","", 0, 0);

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

        return tmpGrpDec;

    }

    

   
     public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {     
       Transaction.begin(connect.getConuser());
       String sqlrequete="UPDATE fournisseur_grproduit SET annuler=1 WHERE  frgtcleunik ="+new Long(objectCleunik).intValue();
       Transaction.execrequeteinsert(sqlrequete,connect.getConuser());
       Transaction.commit(connect.getConuser());
    }

    

}

