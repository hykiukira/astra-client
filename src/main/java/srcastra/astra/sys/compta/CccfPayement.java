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
public class CccfPayement extends Payement{
    
    /** Creates a new instance of ClientPayement */
    String decre="";
    Check check;
    public CccfPayement(Check check, Payement_T payement, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException{
        super(check,payement,config);
        this.check=check;
        payement.setDatePayement(CalculDate.getTodayDate()); 
        getInfo();
    }
    protected void getInfo()throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
     
        typepay=(TypePayement)((TypePayementRmi)m_config.getM_serveur().renvTypePayementRmiObject(m_config.getCle2())).get(m_config.getCle2(),payement.getM_typepayement(),0);
        payement.setCategorie(typepay.getTynt_categorie());
        payement.setM_typepayement(typepay.getTynt_cleunik());      
        payement.setPrix(payement.getPrix()*-1);
     
    }
    public void generatHistorique ()throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
        check.base4(CheckCompteCentral.CLIENT,ParamComptableInterface.JOURNAL_OCCCF);
        long transac=check.getTransaction2(m_config.getCon(),m_config.getM_serveur().getTransactSyn(),check.getTmpool(),m_config.getM_serveur());
        GetData.getCompteData(""+m_config.getM_urcleunik()).setTransaction(new Long(transac).intValue());     
        if(payement.getPrix()>0){
            decre="D";
            if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),payement.getPrix(),"D",payement));
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.CLIENT,new Long(m_config.getCleintervenant()).intValue(),payement.getPrix(),"C",null));
            //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
        
            }
        }
        else if(payement.getPrix()<0){
            decre="C";
            if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),Math.abs(payement.getPrix()),"C",payement));
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.CLIENT,new Long(m_config.getCleintervenant()).intValue(),payement.getPrix(),"D",null));
            // m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
        }}
        super.insertPayement("OBCC","+", decre);
     /*  new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE,m_config,1,0,0,0,0,Long.parseLong(numpiece),datepayement,0,0,0,"",payement.getPrix(),0,0, 
       0,0,"",0,payement.getLibelle(),m_config.getCledossier(),-1,-1,0,1,m_config.getCleintervenant(),1,"O","P",urcleunik,payement.getM_typepayement(),"Ligne de globalisation du dossier",
       0,0,0,0,0,0);  */
       check.commun(typepay.getCe_cleunik(),ParamComptableInterface.JOURNAL_OCCCF);
        if(payement.getPrix()>0){
            decre="C";
            if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),payement.getPrix(),"C",null));
          //  m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
        }}
        else if(payement.getPrix()<0){
            decre="D";
          if(!m_config.getDossier().isRattrap()){
            GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),Math.abs(payement.getPrix()),"D",null));
           // m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
        }}
       super.insertPayement("OCCC","-",decre);
      /* new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE,m_config,1,0,0,0,0,Long.parseLong(numpiece),datepayement,0,0,0,"",-payement.getPrix(),0,0, 
       0,0,"",0,"",m_config.getCledossier(),-1,-1,0,1,m_config.getCleintervenant(),1,"O","CP",urcleunik,payement.getM_typepayement(),"Ligne de globalisation du dossier",
       0,0,0,0,0,0);  */ 
    }
    
}
