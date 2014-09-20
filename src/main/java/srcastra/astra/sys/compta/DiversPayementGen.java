/*
 * ClientPayement.java
 *
 * Created on 8 septembre 2003, 17:48
 */

package srcastra.astra.sys.compta;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import java.sql.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.compta.*;
import srcastra.astra.sys.compta.checkcompte.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compta.command.*;
import srcastra.astra.sys.classetransfert.utils.*;

/**
 *
 * @author  Thomas
 */
public class DiversPayementGen extends TransfertPayement{
    
    /** Creates a new instance of ClientPayement */
    Caisselibelle_T caisse;
    String decre="";
    public DiversPayementGen(Check check, Payement_T payement, Configuration config,Caisselibelle_T caisse) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException{
        super(check,payement,config);
        //super.getInfo();
        payement.setDatePayement(CalculDate.getTodayDate()); 
        this.caisse=caisse;
    }
   
    public void generatHistorique ()throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
        if(payement.getPrix()>0){
            decre="D";
//            if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),payement.getPrix(),"D",payement));
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(0,Caisselibelle_T.CLIENT,new Long(m_config.getCleintervenant()).intValue(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement);
        }
        else if(payement.getPrix()<0){
            decre="C";
        //  if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),Math.abs(payement.getPrix()),"C",payement));
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(0,Caisselibelle_T.CLIENT,new Long(m_config.getCleintervenant()).intValue(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement);
        }
       super.insertPayement("P","+",decre);
       /*new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE,m_config,1,0,0,0,0,Long.parseLong(numpiece),datepayement,0,0,0,"",payement.getPrix(),0,0, 
       0,0,"",0,payement.getLibelle(),m_config.getCledossier(),-1,-1,0,1,m_config.getCleintervenant(),1,"O","P",urcleunik,payement.getM_typepayement(),"Ligne de globalisation du dossier",
       0,0,0,0,0,0);  */
       check.commun(caisse.getCe_cleunik(),ParamComptableInterface.JOURNAL_VENTE);  
        if(payement.getPrix()>0){
            decre="C";
        //  if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),payement.getPrix(),"C",null));
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
        }
        else if(payement.getPrix()<0){
            decre="D";
        //  if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),Math.abs(payement.getPrix()),"D",null));
          //  m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
        }
        super.insertPayement("CP","-",decre);
      /* new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE,m_co SommeCaisenfig,1,0,0,0,0,Long.parseLong(numpiece),datepayement,0,0,0,"",-payement.getPrix(),0,0, 
       0,0,"",0,"",m_config.getCledossier(),-1,-1,0,1,m_config.getCleintervenant(),1,"O","CP",urcleunik,payement.getM_typepayement(),"Ligne de globalisation du dossier",
       0,0,0,0,0,0);*/   
    }
    
}
