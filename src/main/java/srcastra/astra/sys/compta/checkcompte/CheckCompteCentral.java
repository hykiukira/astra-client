/*
 * CheckCompteCentralCase.java
 *
 * Created on 20 août 2003, 12:05
 */

package srcastra.astra.sys.compta.checkcompte;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.sql.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.GetId;
import java.util.*;
/**
 *
 * @author  Thomas
 */
public class CheckCompteCentral {
    
    /** Creates a new instance of CheckCompteCentralCase */
    GlobalRmiInterface comptecentral;
    GlobalRmiInterface entite;
    GlobalRmiInterface user;
    astraimplement serveur;
    ParamComptableInterface paramcompt;
    Poolconnection tmpool;
    int urcleunik;
    public CheckCompteCentral(astraimplement serveur, int urcleunik) {
        this.serveur=serveur;
        this.urcleunik=urcleunik;    
    }
     public void init(Poolconnection tmpool) throws java.rmi.RemoteException,ServeurSqlFailure{
        this.tmpool=tmpool;       
        comptecentral=serveur.renvCompteCentralRmiObject(urcleunik);
        paramcompt=serveur.renvParamCompta(urcleunik);
        entite=serveur.renvEntiteRmiObject(urcleunik);
        user=serveur.renvUserRmiObject(urcleunik);
    }
     //je laisse les champs inutiles au cas ou il serait interessant de superclasser avec une classe abstraite
     //ou d'étendre une interface commune  
      public int comunCase(int ce_cleunik,int urentcleunik,int category,int typecentral)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
          return checkIfExist(ce_cleunik,urentcleunik,category,typecentral); 
      }
      public int checkIfExist(int ce_cleunik,int urentcleunik,int category,int typecentral) throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
          int retval=0;
          CompteCentral central=(CompteCentral)((CompteCentralRmi)comptecentral).get2(urcleunik,ce_cleunik,0);
          if(central==null){
              retval=generateCentral(ce_cleunik,urentcleunik,category,typecentral);
          }else{
             retval=central.getCour_cleunik();
          }
          return retval;  
    }
    public int generateCentral(int ce_cleunik,int urentcleunik,int category,int typecentral)throws java.rmi.RemoteException,ServeurSqlFailure{
        CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
        Entite tmpentite=(Entite)entite.get(tmpool.getUrcleunik(),tmpool.getNumeroentite(),0);
        User tmpuser=(User)user.get(tmpool.getUrcleunik(),tmpool.getUrcle2(),0);
        if(category==CheckCompteCommun.ENTITE){
            central.setCour_intitule1(central.getCour_intitule1()+" "+tmpentite.getEeabrev());
            central.setCour_intitule2(central.getCour_intitule2()+" "+tmpentite.getEeabrev());
        }
        else if(category==CheckCompteCommun.USER){
            central.setCour_intitule1(central.getCour_intitule1()+" "+tmpentite.getEeabrev()+" "+tmpuser.getUrnom());
            central.setCour_intitule2(central.getCour_intitule2()+" "+tmpentite.getEeabrev()+" "+tmpuser.getUrnom());            
        }
        central.setCe_cleunik(ce_cleunik);
        comptecentral.insert(tmpool.getUrcleunik(),central);
        int idcentral=GetId.getLastId(tmpool.getConuser());
        return idcentral;      
    }
public final static int FOURNISSEUR=2;
public final static int CLIENT=1;
public final static int TVA_PAYE=3;
public final static int TVA_RECUP=4;   
public final static int CAISSE=5;   
public final static int BANK=6;   
public final static int CCCF=7;   
}
