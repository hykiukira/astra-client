/*
 * CheckCompte.java
 *
 * Created on 19 août 2003, 16:12
 */

package srcastra.astra.sys.compta.checkcompte;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.sql.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.GetId;
import java.util.*;
import java.util.logging.Logger;

import srcastra.astra.sys.compta.GetData;
import srcastra.test.FuckinNullPointer;
/**
 *
 * @author  Thomas
 */
   
public class CheckCompteCommun { 
    /** Creates a new instance of CheckCompte */
    GlobalRmiInterface compte;
    GlobalRmiInterface comptecentral;
    GlobalRmiInterface souscomptecentral;
    GlobalRmiInterface traductioncompte;
    GlobalRmiInterface entite;
    GlobalRmiInterface user;
    Poolconnection tmpool;
    int urcleunik;
    astraimplement serveur;
    public CheckCompteCommun(astraimplement serveur, int urcleunik) {
        this.serveur=serveur;
        this.urcleunik=urcleunik;    
    }
    public void init(Poolconnection tmpool) throws java.rmi.RemoteException,ServeurSqlFailure{
        this.tmpool=tmpool;
        compte=serveur.renvCompteRmiObject(urcleunik);
        comptecentral=serveur.renvCompteCentralRmiObject(urcleunik);
        traductioncompte=serveur.renvTraductionCompteRmiObject(urcleunik);
        entite=serveur.renvEntiteRmiObject(urcleunik);
        user=serveur.renvUserRmiObject(urcleunik);
    }

    public int[] comunCase(int ce_cleunik,int urentcleunik,int category)throws java.rmi.RemoteException,ServeurSqlFailure{
        int[] comptes=new int[2];
        comptes[0]=ce_cleunik;
        if(category==CheckCompteCommun.USER && serveur.getConfig().getCaisseparutilisateur()==1){
            int tmp=checkIfExist(ce_cleunik,urentcleunik,category);
            if(tmp==0){
                comptes[1]=generateNewCompte(ce_cleunik,urentcleunik,category);
            }
            else{
                comptes[1]=tmp;
            }            
        }
        else if(serveur.getConfig().getMultibureaux()==1 && category==CheckCompteCommun.ENTITE){
            int tmp=checkIfExist(ce_cleunik,urentcleunik,category);
            if(tmp==0){
                comptes[1]=generateNewCompte(ce_cleunik,urentcleunik,category);
            }
            else{
                comptes[1]=tmp;
            }
        }else{
          comptes[1]=ce_cleunik;

          int tmp=checkIfExist(ce_cleunik,urentcleunik,category);
          if(tmp==0){
              logger.info("");
            linkCompte(ce_cleunik,urentcleunik,category,ce_cleunik);
          }
        }
        GetData.getCompteData(""+tmpool.getUrcle2()).setNumerocompte(serveur.getCompte( comptes[1],new Long(tmpool.getUrcleunik()).intValue()));
        return comptes;  
    }
    public int checkIfExist(int ce_cleunik,int urentcleunik,int category)throws java.rmi.RemoteException,ServeurSqlFailure{
      int retval=0;
      try{
        //comptecentral.get(urcleunik
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement("SELECT * FROM userentitecompte WHERE uste_cat=? AND ureecleunik=? AND ce_cleunik_cat=?");
        pstmt.setInt(1,category);
        pstmt.setInt(2,urentcleunik);
        pstmt.setInt(3,ce_cleunik);
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
            retval=result.getInt(4);
            return retval;
        }
        pstmt=tmpool.getConuser().prepareStatement("SELECT ce_cleunik FROM userentitecompte WHERE ce_cleunik=? ");
        pstmt.setInt(1,ce_cleunik);
        result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
            retval=result.getInt(1);
            return retval;
        }
        return retval;
      }catch(SQLException sn){
        sn.printStackTrace();   
      }
      return retval;  
    }
    private static Logger logger = Logger.getLogger(FuckinNullPointer.class.getName());
    private int generateNewCompte(int ce_cleunik,int urentcleunik,int category)throws java.rmi.RemoteException,ServeurSqlFailure{
        String urcle;
        String entitecle; 
        Compte tmpcompte=(Compte)this.compte.get(tmpool.getUrcleunik(),ce_cleunik,0);          
        String newCompte="";
        int urcleunik=tmpool.getUrcle2();
        int eecleunik=tmpool.getNumeroentite();
        if(urcleunik<10)
            urcle="0"+new Integer(urcleunik).toString();
        else
            urcle=new Integer(urcleunik).toString();
        
        
        if(eecleunik<10)
            entitecle="0"+new Integer(eecleunik).toString();
        else
            entitecle=new Integer(eecleunik).toString();
        
        
         if(category==this.ENTITE && serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik()).getMultibureaux()==1){
            newCompte=tmpcompte.getCe_num()+entitecle;
         }
         else if(category==this.USER  && serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik()).getCaisseparutilisateur()==1){
            String compte=new Long(tmpcompte.getCe_num()).toString();
            compte=compte.substring(0,4);
            if(serveur.getConfig().getMultibureaux()==1)
                newCompte=compte+urcle+entitecle;
            else
               newCompte=compte+urcle; 
         }
         else{
             newCompte=""+tmpcompte.getCe_num()            ;
         }
                    
         tmpcompte.setCe_num(Long.parseLong(newCompte));
         compte.insert(tmpool.getUrcleunik(),tmpcompte);
         int idnewcompte=GetId.getLastId(tmpool.getConuser());
         tmpcompte.setCe_cleunik(idnewcompte);
         generateNewCompteTraduction(ce_cleunik,urentcleunik,category,idnewcompte);
         linkCompte(ce_cleunik,urentcleunik,category,idnewcompte);
         return idnewcompte;
    }
    private void linkCompte(int ce_cleunik,int urentcleunik,int category,int newcompte)throws java.rmi.RemoteException,ServeurSqlFailure{
         try{
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement("INSERT INTO userentitecompte(uste_cat , ureecleunik ,ce_cleunik_cat,ce_cleunik )  VALUES(?, ?, ?, ?)");
            logger.info("category "+category);
            logger.info("urentcleunik "+urentcleunik);
             logger.info("ce_cleunik "+ce_cleunik);
             logger.info("newcompte "+newcompte);
             pstmt.setInt(1,category);
            pstmt.setInt(2,urentcleunik);
            pstmt.setInt(3,ce_cleunik);
            pstmt.setInt(4,newcompte);
            pstmt.execute();
             }catch(SQLException sn){
                 srcastra.astra.sys.Transaction.rollback(tmpool.getConuser());
                 String[] message=new String[1];
                 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
                 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
        
      }
    }
    private void generateNewCompteTraduction(int ce_cleunik,int urentcleunik,int category,int newcompte)throws java.rmi.RemoteException,ServeurSqlFailure{
           ArrayList tmparray=(ArrayList)traductioncompte.get(urcleunik,ce_cleunik,0);    
           Entite tmpentite=(Entite)entite.get(tmpool.getUrcleunik(),tmpool.getNumeroentite(),0);
           User tmpuser=(User)user.get(tmpool.getUrcleunik(),tmpool.getUrcle2(),0);
           Traduction_compte tmptra=new Traduction_compte();
           Traduction_compte tmptra2=new Traduction_compte();
           tmptra.setLmcleunik(1);
           tmptra.setCe_cleunik(newcompte);
            if(category==this.ENTITE){
                tmptra.setTrate_traduction(((Traduction_compte)tmparray.get(0)).getTrate_traduction()+" "+tmpentite.getEeabrev());
                tmptra2.setTrate_traduction(((Traduction_compte)tmparray.get(1)).getTrate_traduction()+" "+tmpentite.getEeabrev());
            }
            else if(category==this.USER){
                tmptra.setTrate_traduction(((Traduction_compte)tmparray.get(0)).getTrate_traduction()+" "+tmpentite.getEeabrev()+" "+tmpuser.getUrnom());
                tmptra2.setTrate_traduction(((Traduction_compte)tmparray.get(1)).getTrate_traduction()+" "+tmpentite.getEeabrev()+" "+tmpuser.getUrnom());
            }        
           tmptra2.setLmcleunik(2);
           tmptra2.setCe_cleunik(newcompte);
           
           traductioncompte.insert(urcleunik,tmptra);
           traductioncompte.insert(urcleunik,tmptra2);         
    }
    
    public final static int ENTITE=1; 
    public final static int USER=2;
}
