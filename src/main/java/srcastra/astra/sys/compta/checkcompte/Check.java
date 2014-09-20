/*
 * Check.java
 *
 * Created on 21 août 2003, 14:54
 */

package srcastra.astra.sys.compta.checkcompte;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.sql.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.GetId;
import java.util.*;
import srcastra.astra.sys.compta.GetData;
import srcastra.astra.sys.classetransfert.dossier.*;
/**
 *
 * @author  Thomas
 */
public class Check {
    
    /** Creates a new instance of Check */
    GlobalRmiInterface comptecentral;
    GlobalRmiInterface entite;
    GlobalRmiInterface user;
    static astraimplement serveur;
    ParamComptableInterface paramcompt;
    Poolconnection tmpool;
    int urcleunik;
    CheckCompteCommun checkcompte;
    CheckJournalCompta checkjournal;
    CheckCompteCentral checkcentral;
    int caisseutilisateur;
    int category;
    int urentcleunik;
    srcastra.astra.sys.compta.Configuration config;
    public Check(int urcleunik,srcastra.astra.sys.compta.Configuration config){  
        this.serveur=config.getM_serveur();
        this.urcleunik=urcleunik;         
        this.config=config; 
        checkcentral=new CheckCompteCentral(serveur,urcleunik);
        checkjournal=new CheckJournalCompta(serveur,urcleunik);
        checkcompte=new CheckCompteCommun(serveur,urcleunik);
    }
     public void init() throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
         tmpool=serveur.getConnectionAndCheck(urcleunik,true);
         if(serveur.getConfig()==null)
             serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik());
         caisseutilisateur=serveur.getConfig().getCaisseparutilisateur();
         comptecentral=serveur.renvCompteCentralRmiObject(urcleunik);
         paramcompt=serveur.renvParamCompta(urcleunik);
         Object[] tab=paramcompt.getPeriodeCompta(ParamComptableInterface.JOURNAL_ACHAT,urcleunik,null);
         //ExerciceCompt_T exerc
         GetData.getCompteData(""+tmpool.getUrcle2()).setPeriode(((Integer)tab[0]).intValue());    
         GetData.getCompteData(""+tmpool.getUrcle2()).setExle_cleunik(((Integer)tab[2]).intValue());    
         checkcentral.init(tmpool);
         checkjournal.init(tmpool);
         checkcompte.init(tmpool);           
    } 
     public  static long getTransaction(Poolconnection tmpool,Object transactO,astraimplement serveur)throws SQLException{
       long transact=0;
       transact=new srcastra.astra.sys.compta.GenerateNum(serveur.getHost(),tmpool).getTransactionNumber();
       GetData.getCompteData(""+tmpool.getUrcle2()).setTransaction(new Long(transact).intValue());
       return transact;
       }
     
      public static long getTransaction2(Connection con,Object transactO,Poolconnection tmpool,astraimplement serveur)throws SQLException{
       long transact=0;
       transact=new srcastra.astra.sys.compta.GenerateNum(serveur.getHost(),tmpool).getTransactionNumber2();
       return transact;
      /* String requete="SELECT * FROM compteur WHERE comur_cat=?";
       PreparedStatement pstmt=con.prepareStatement(requete);
       synchronized(transactO){
       pstmt.setInt(1,1);
       ResultSet result=pstmt.executeQuery();
       result.beforeFirst();
       long transact=0;
       while(result.next()){
         transact=result.getLong(2);
       }
       transact=transact+1;
       pstmt=con.prepareStatement("UPDATE compteur SET comur_transact =? WHERE comur_cat=?");
       pstmt.setInt(2,1);
       pstmt.setLong(1,transact);
       pstmt.execute();
     //  GetData.getCompteData(""+tmpool.getUrcle2()).setTransaction(new Long(transact).intValue());
       return transact;
       }*/
     }
     public int commun(int ce_cleunik,int typecentral)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
       checkCategory(typecentral);
       int[] tmp=checkcompte.comunCase(ce_cleunik,urentcleunik,category);   
       GetData.getCompteData(""+tmpool.getUrcle2()).setCe_cent_cleunik(tmp[0]);
       GetData.getCompteData(""+tmpool.getUrcle2()).setCe_cleunik(tmp[1]);
       return tmp[1];
     }
     public void Centralisateur(int ce_cleunik,int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
         checkCategory(typecentral);
         int central=checkcentral.comunCase(ce_cleunik,urentcleunik,category,typecentral);
         int journalcle=checkjournal.comunCase(central,urentcleunik,category,journal);     
         GetData.getCompteData(""+tmpool.getUrcle2()).setJota_cleunik(journalcle);
         GetData.getCompteData(""+tmpool.getUrcle2()).setTypecentral(typecentral);
     }
      public void Centralisateur2(int ce_cleunik,int typecentral,int journalcategorie,int jota_cleunik)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
         checkCategory(typecentral);
         int central=checkcentral.comunCase(ce_cleunik,urentcleunik,category,typecentral);
         int journalcle=checkjournal.checkIfExist3(ce_cleunik,urentcleunik,category,journalcategorie,jota_cleunik);//(central,urentcleunik,category,journal);     
         GetData.getCompteData(""+tmpool.getUrcle2()).setJota_cleunik(journalcle);
         GetData.getCompteData(""+tmpool.getUrcle2()).setTypecentral(typecentral);
     }
     public void  base4(int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
       checkCategory(typecentral);
       CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
       int journalcle=journal;//checkjournal.comunCase(central.getCour_cleunik(),urentcleunik,category,journal); 
       int ce_cleunik2=commun(central.getCe_cleunik(),typecentral);
       int central2=checkcentral.comunCase(ce_cleunik2,urentcleunik,category,typecentral);
       GetData.getCompteData(""+tmpool.getUrcle2()).setJota_cleunik(journalcle);
       GetData.getCompteData(""+tmpool.getUrcle2()).setTypecentral(typecentral);   
     }
     public void base(int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
        CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
        int ce_cleunik2=commun(central.getCe_cleunik(),typecentral);
        Centralisateur(ce_cleunik2,typecentral,journal);      
     }
      public void base5(int typecentral,int journalcate,int jota_cleunik)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
        CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
        int ce_cleunik2=commun(central.getCe_cleunik(),typecentral);
        Centralisateur2(ce_cleunik2,typecentral,journalcate,jota_cleunik);      
     }
     public void base2(int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
          CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
          int ce_cleunik2=commun(central.getCe_cleunik(),typecentral);
     }
     public void base3(int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
          CompteCentral central=(CompteCentral)comptecentral.get(tmpool.getUrcleunik(),typecentral,0);
          int ce_cleunik2=commun(central.getCe_cleunik(),typecentral);
          checkcentral.comunCase(ce_cleunik2,urentcleunik,category,typecentral);
     }
     public void checkJournalt(int typecentral,int journal)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
       checkCategory(typecentral);
       checkjournal.checkIfExist2(0,urentcleunik,category,journal);        
     }
     private void checkCategory(int typecentral){
       category=CheckCompteCommun.ENTITE;
       urentcleunik=tmpool.getNumeroentite();
       if(typecentral==CheckCompteCentral.CAISSE)
          if(caisseutilisateur==1){
             category=CheckCompteCommun.USER;
             urentcleunik=tmpool.getUrcle2();
          }
         }
     public void updateSoldeJournal(int jota_cleunik,int periode,Payement_T pay)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException{
         paramcompt.updateSoldeJournal(jota_cleunik,periode,pay,tmpool);
     }
     
     /** Getter for property tmpool.
      * @return Value of property tmpool.
      *
      */
     public srcastra.astra.sys.rmi.utils.Poolconnection getTmpool() {
         return tmpool;
     }
     
     /** Setter for property tmpool.
      * @param tmpool New value of property tmpool.
      *
      */
     public void setTmpool(srcastra.astra.sys.rmi.utils.Poolconnection tmpool) {
         this.tmpool = tmpool;
     }
     
}
