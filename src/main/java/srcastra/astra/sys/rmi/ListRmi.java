/*

 * LIstRmi.java

 *

 * Created on 10 novembre 2003, 15:41

 */



package srcastra.astra.sys.rmi;

import java.util.*;

import java.rmi.*;

import srcastra.astra.sys.rmi.*;

import java.sql.*;  

import srcastra.astra.sys.rmi.utils.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.sys.compta.checkcompte.*;

import srcastra.astra.sys.rmi.Exception.*;

import srcastra.astra.gui.list.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.sys.rmi.socketfactory.*;
import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;
import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;
/**

 *

 * @author  Thomas

 */

public class ListRmi extends java.rmi.server.UnicastRemoteObject implements ListRmiInterface  {

     

    /** Creates a new instance of LIstRmi */

    astraimplement serveur;

    public ListRmi(astraimplement serveur,int port)  throws java.rmi.RemoteException{ 

        super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());

        this.serveur=serveur;

    }
    
    public ArrayList getMailOut(int urcleunik, String dateretbeg, String dateretend) throws java.rmi.RemoteException{
    
        
        String requete = "select c.csmailprincip,d.dr_date_retour, c.lecleunik from dossier d, clients c where d.cscleunik = c.cscleunik and c.csmailprincip <> 'null' and c.csmailprincip <> '' and c.csmailprincip is not null and c.csmailprincip like '%@%' AND (d.dr_date_retour >= ? AND d.dr_date_retour <= ?)";
        
        ArrayList retval=null;
        
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
        
        try{
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1,dateretbeg);
            pstmt.setString(2,dateretend);
            
            ResultSet result=pstmt.executeQuery();

            result.beforeFirst();

            //int size=result.getMetaData().getColumnCount();

            while(result.next()){
                
                Object[] tmptab=new Object[3];
                tmptab[0]=new String(result.getString(1));
                tmptab[1]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(2));
                
                if(result.getInt(3)==1)
                    tmptab[2]=new String("FR");
                else
                    tmptab[2]=new String("NL");
                
                retval.add(tmptab);
                
                
            }
            
            
        }
         catch(SQLException sn){ 

          sn.printStackTrace();   

        }

        return retval;
        
    }

    public ArrayList getDossierList(int urcleunik, String datedepbeg, String datedebend, String creationbeg, String creationend, String solde, String facture, String clientc, String clientf, String fournisseur, String passager, String destination, String po, String numdos, String entite, String groupprod, String produit, String bdc, int user) throws java.rmi.RemoteException{

        ArrayList retval=null;

        Hashtable tmphash=new Hashtable();

        String requete="SELECT DISTINCT d.dr_cleunik,d.dr_numdos,c1.csnom,d.dr_datetimecrea,d.dr_date_depart,d.dr_nbrpass,d.dr_totalprix,d.dr_paye,(d.dr_totalprix-d.dr_paye),d.fournisseur,fr.frgtitrecatalog,d.dr_bdc,d.dr_facture,d.dr_annull,u.urnom FROM dossierproduit dp LEFT JOIN dossierdestination dn on (dp.doit_cleunik=dn.doit_cleunik and dn.de_cleunik LIKE(CONCAT( ?, '%' ) ))  LEFT JOIN fournisseur_grproduit fr ON(dp.frgt_cleunik=fr. frgtcleunik ) ,dossier d,clients c1, clients c2,user u ,intermediairepassager ip, passager p WHERE  d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND  c1.cscleunik LIKE(?)  AND d.cscleunik_fact = c2.cscleunik AND  c2.cscleunik LIKE(?) AND d.urcleunik = u.urcleunik AND u.eecleunik LIKE (?) and u.urcleunik=?  AND   dr_annull = 0 AND dr_numdos LIKE (CONCAT(?, '%' ))  AND dp.doit_po LIKE (CONCAT(?, '%' ) )  AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND (d.dr_datetimecrea >= ? AND d.dr_datetimecrea <= ?) AND (d.dr_date_depart >=? AND d.dr_date_depart <= ?)  AND dp.fr_cleunik LIKE (?) AND dp.frgt_cleunik LIKE (?)  AND ip.pr_cleunik = p.pr_cleunik AND ip.dr_cleunik=d.dr_cleunik AND p.pr_nom LIKE (CONCAT( ?, '%' ) ) AND d.dr_facture LIKE (?) AND d.dr_bdc LIKE (?)  AND d.dr_solde LIKE (?) ORDER BY d.dr_numdos";
        String requete1="SELECT DISTINCT d.dr_cleunik,d.dr_numdos,c1.csnom,d.dr_datetimecrea,d.dr_date_depart,d.dr_nbrpass,d.dr_totalprix,d.dr_paye,(d.dr_totalprix-d.dr_paye),d.fournisseur,fr.frgtitrecatalog,d.dr_bdc,d.dr_facture,d.dr_annull,u.urnom FROM dossierproduit dp LEFT JOIN dossierdestination dn on (dp.doit_cleunik=dn.doit_cleunik and dn.de_cleunik LIKE(CONCAT( ?, '%' ) ))  LEFT JOIN fournisseur_grproduit fr ON(dp.frgt_cleunik=fr. frgtcleunik ) ,dossier d,clients c1, clients c2,user u ,intermediairepassager ip, passager p WHERE  d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND  c1.cscleunik LIKE(?)  AND d.cscleunik_fact = c2.cscleunik AND  c2.cscleunik LIKE(?) AND d.urcleunik = u.urcleunik AND u.eecleunik LIKE (?) AND   dr_annull = 0 AND dr_numdos LIKE (CONCAT(?, '%' ))  AND dp.doit_po LIKE (CONCAT(?, '%' ) )  AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND (d.dr_datetimecrea >= ? AND d.dr_datetimecrea <= ?) AND (d.dr_date_depart >=? AND d.dr_date_depart <= ?)  AND dp.fr_cleunik LIKE (?) AND dp.frgt_cleunik LIKE (?)  AND ip.pr_cleunik = p.pr_cleunik AND ip.dr_cleunik=d.dr_cleunik AND p.pr_nom LIKE (CONCAT( ?, '%' ) ) AND d.dr_facture LIKE (?) AND d.dr_bdc LIKE (?)  AND d.dr_solde LIKE (?) ORDER BY d.dr_numdos";
        String requ;
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);

        try{
            
            requ=requete;
            
            
            if(user==0)
            {
                requ=requete1;
                        
            }
            
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requ);

            if(user==0)
            {
            pstmt.setString(1,destination);  

            pstmt.setString(4, entite);
            
            pstmt.setString(2, clientc);

            pstmt.setString(3, clientf);

            pstmt.setString(5, numdos);

            pstmt.setString(6, po);

            pstmt.setString(7, produit);

            pstmt.setString(8, creationbeg);

            pstmt.setString(9, creationend);         

            pstmt.setString(10, datedepbeg);

            pstmt.setString(11, datedebend);

            pstmt.setString(12, fournisseur);

            pstmt.setString(13,groupprod);

            pstmt.setString(14, passager);

          //  pstmt.setString(14,destination);  

            pstmt.setString(15, facture);

            pstmt.setString(16, bdc);       

            pstmt.setString(17, solde);
            } 
            else
            {
                 pstmt.setString(1,destination);  

            pstmt.setString(4, entite);

            pstmt.setInt(5, user);
            
            pstmt.setString(2, clientc);

            pstmt.setString(3, clientf);

            pstmt.setString(6, numdos);

            pstmt.setString(7, po);

            pstmt.setString(8, produit);

            pstmt.setString(9, creationbeg);

            pstmt.setString(10, creationend);         

            pstmt.setString(11, datedepbeg);

            pstmt.setString(12, datedebend);

            pstmt.setString(13, fournisseur);

            pstmt.setString(14,groupprod);

            pstmt.setString(15, passager);

          //  pstmt.setString(14,destination);  

            pstmt.setString(16, facture);

            pstmt.setString(17, bdc);       

            pstmt.setString(18, solde);
            }

            ResultSet result=pstmt.executeQuery();

            result.beforeFirst();

            int size=result.getMetaData().getColumnCount();

        while(result.next()){

            if(retval==null)

                retval=new ArrayList();

            Object[] tmptab=new Object[size];

            for(int i=0;i<tmptab.length;i++){

                tmptab[i]=result.getObject(i+1);

            }

            if( tmphash.get(tmptab[0])==null){

                tmphash.put(tmptab[0],"");

                retval.add(tmptab);          

            }

        }   

        }

        catch(SQLException sn){ 

          sn.printStackTrace();   

        }

        return retval;

    }
    public Hashtable getBspt(int urCleunik,srcastra.astra.sys.classetransfert.utils.Date startDate,srcastra.astra.sys.classetransfert.utils.Date endDate)throws java.rmi.RemoteException,ServeurSqlFailure{
        Poolconnection tmpool=serveur.getConnectionAndCheck(urCleunik, true);
        Hashtable retval=new Hashtable();
        String requete="SELECT a.at_cleunik,c.coe_abrev,a.at_num_billet,a.at_date_emission,a.at_val_facial,a.at_mode_paiement,a.at_num_cccf,s.sn_prix,a.at_val_com,a.at_annull_etat,dr_numdos,'0.00'  FROM avion_ticket a LEFT JOIN suplement_reduction s ON (a.at_cleunik =s.at_cleunik AND s.ctpro_cleunik=1 AND s.snlib_cleunik=?) LEFT JOIN compagnie c  ON(a.coe_cleunik=c.coe_cleunik)  LEFT JOIN dossier d  ON(a.dr_cleunik=d.dr_cleunik) WHERE a.at_type_billet=1 AND (a.at_annull_etat!="+Avion_ticket_T.CANCEL+") AND (at_date_emission>=  ? AND at_date_emission<=?) order by a.at_num_billet,a.at_date_emission";
        String requeteMco="SELECT a.at_cleunik,c.coe_abrev,a.at_num_billet,a.at_date_emission,a.at_val_facial,a.at_mode_paiement,a.at_num_cccf,s.sn_prix,a.at_val_com,a.at_annull_etat,dr_numdos,'0.00'  FROM avion_ticket a LEFT JOIN suplement_reduction s ON (a.at_cleunik =s.at_cleunik AND s.ctpro_cleunik=1 AND s.snlib_cleunik=?) LEFT JOIN compagnie c  ON(a.coe_cleunik=c.coe_cleunik)  LEFT JOIN dossier d  ON(a.dr_cleunik=d.dr_cleunik) WHERE a.at_type_billet=3 AND (a.at_annull_etat!="+Avion_ticket_T.CANCEL+") AND (at_date_emission>=  ? AND at_date_emission<=?) order by a.at_num_billet,a.at_date_emission";
        String requeteRefund="SELECT a.at_cleunik,c.coe_abrev,a.at_num_billet,a.at_date_emission,s3.sn_prix,a.at_mode_paiement,a.at_num_cccf,s.sn_prix,a.at_val_com,a.at_annull_etat,dr_numdos,s2.sn_prix  FROM avion_ticket a LEFT JOIN suplement_reduction s3 ON (a.at_cleunik =s3.at_cleunik AND s3.ctpro_cleunik=1 AND s3.snlib_cleunik=?) LEFT JOIN suplement_reduction s ON (a.at_cleunik =s.at_cleunik AND s.ctpro_cleunik=1 AND s.snlib_cleunik=?) LEFT JOIN suplement_reduction s2 ON (a.at_cleunik =s2.at_cleunik AND s2.ctpro_cleunik=1 AND s2.snlib_cleunik=?) LEFT JOIN compagnie c  ON(a.coe_cleunik=c.coe_cleunik)  LEFT JOIN dossier d  ON(a.dr_cleunik=d.dr_cleunik) WHERE a.at_type_billet=1 AND (a.at_annull_etat!="+Avion_ticket_T.CANCEL+" AND a.at_annull_etat="+Avion_ticket_T.REFUND+") AND (at_date_emission>=  ? AND at_date_emission<=?) order by a.at_num_billet,a.at_date_emission";
        ArrayList auto=new ArrayList();
        ArrayList mco=new ArrayList();
        ArrayList refund=new ArrayList();
        try{
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        
        endDate.setHours(12);
        endDate.setMinutes(12);
        endDate.setSeconds(12);
        
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        System.out.println(startDate.toString()+"   "+endDate.toString());
        pstmt.setInt(1, Sup_reduc_T.TAXLOCALE);
        pstmt.setString(2, startDate.toString());
        pstmt.setString(3, endDate.toString());
        ResultSet result=pstmt.executeQuery();      
        int col=result.getMetaData().getColumnCount();
        while(result.next()){
            Object[] tab=new Object[col];
            for(int i=0;i<col;i++){
                if(i==3){
                    tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));
                }
                else{
                    tab[i]=result.getObject(i+1);    
                }
            }
            auto.add(tab);
            
            
            
        }
        retval.put("auto", auto);
        pstmt=tmpool.getConuser().prepareStatement(requeteMco);
        System.out.println(startDate.toString()+"   "+endDate.toString());
        pstmt.setInt(1, Sup_reduc_T.TAXLOCALE);
     //   pstmt.setInt(2, Sup_reduc_T.TAXCOMP);
        pstmt.setString(2, startDate.toString());
        pstmt.setString(3, endDate.toString());
        result=pstmt.executeQuery();      
        col=result.getMetaData().getColumnCount();
        while(result.next()){
            Object[] tab=new Object[col];
            for(int i=0;i<col;i++){
                if(i==3){
                    tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));
                }
                else{
                    tab[i]=result.getObject(i+1);    
                }
            }
            mco.add(tab);
            
            
            
        }
        retval.put("mco",mco);
        pstmt=tmpool.getConuser().prepareStatement(requeteRefund);
        System.out.println(startDate.toString()+"   "+endDate.toString());
        pstmt.setInt(1, Sup_reduc_T.TAXREFUND);
        pstmt.setInt(2, Sup_reduc_T.TAXLOCALEBACK);
        pstmt.setInt(3, Sup_reduc_T.TAXREFUNDTAXCOMP);
        pstmt.setString(4, startDate.toString());
        pstmt.setString(5, endDate.toString());
        result=pstmt.executeQuery();      
        col=result.getMetaData().getColumnCount();
        while(result.next()){
            Object[] tab=new Object[col];
            for(int i=0;i<col;i++){
                if(i==3){
                    tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));
                }
                else{
                    tab[i]=result.getObject(i+1);    
                }
            }
            refund.add(tab);
            
            
            
        }
        retval.put("refund",refund);
         }catch(SQLException sn){

           ServeurSqlFailure se=new ServeurSqlFailure("erreur listing bsp");
           se.setSqlException(sn);
           throw se;

         }
        return retval;
    }
    public  CaisseList_T  getCaisseList(int urCleunik,srcastra.astra.sys.classetransfert.utils.Date datedeb,srcastra.astra.sys.classetransfert.utils.Date dateend,int eecleunik,int urcle) throws java.rmi.RemoteException,ServeurSqlFailure{
         Hashtable solde=null;
         ArrayList detail=null;
         CaisseList_T caisse=new CaisseList_T();
         Poolconnection tmpool=serveur.getConnectionAndCheck(urCleunik, true);
         try{
         String requete2=""; 
         PeriodeRmi periodeserveur=(PeriodeRmi)serveur.renvPeriodeRmiObject(urCleunik);
         Periode_T periode=(Periode_T)periodeserveur.getByDate(urCleunik, 0, 0,datedeb, tmpool);
         CaisseData data=new CaisseData();
          if(serveur.getConfig().getCaisseparutilisateur()==1){
                CaisseSolde.getSolde(periode.getPede_numper(), tmpool, data, 2, urcle, true, periode);
                //requete2="SELECT DISTINCT h.hedatemouv,t.tynt_categorie, d.dr_numdos,c.csnom,h.henumpiece, h.helibelle, h.hevaleur,u.urnom FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik LEFT JOIN clients c ON (h.intervenantcleunik =c.cscleunik AND h.typeintervenantcleunik=1), typepaiement t,user u,entite e WHERE  h.ce_cleunik_cent  = ? AND h.heperiode = ? AND h.urcleunik =u.urcleunik  AND h.hetypepayement = t.tynt_cleunik  AND u.urcleunik=? AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                  
          }
             else{
                CaisseSolde.getSolde(periode.getPede_numper(), tmpool, data, 1, eecleunik, true, periode); 
                //requete2="SELECT DISTINCT h.hedatemouv,t.tynt_categorie, d.dr_numdos,c.csnom,h.henumpiece, h.helibelle, h.hevaleur,u.urnom FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik LEFT JOIN clients c ON (h.intervenantcleunik =c.cscleunik AND h.typeintervenantcleunik=1), typepaiement t,user u,entite e WHERE  h.ce_cleunik_cent  = ? AND h.heperiode = ? AND h.urcleunik =u.urcleunik AND u.eecleunik=e.eecleunik AND e.eecleunik=? AND h.hetypepayement = t.tynt_cleunik AND h.urcleunik=u.urcleunik AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                
             } 
         CompteCentralRmi comptecentral=(CompteCentralRmi)serveur.renvCompteCentralRmiObject(urCleunik);
         CompteCentral comptec=(CompteCentral)comptecentral.get(urCleunik, CheckCompteCentral.CAISSE,0);
         int pedenum=periode.getPede_numper();
         solde=new Hashtable();
         solde.put(new Integer(TypePayement.CASH), new Double(data.getSoldecash()));
         solde.put(new Integer(TypePayement.CARD), new Double(0));
         solde.put(new Integer(TypePayement.CHEQUE), new Double(data.getSoldecheque()));
         PreparedStatement pstmt=null;
         ResultSet result=null; 
         if(serveur.getConfig().getCaisseparutilisateur()==1){
             requete2="SELECT DISTINCT h.hedatemouv,LEFT(t.tynt_libelle1,2), d.dr_numdos,c.csnom,h.henumpiece, h.helibelle, h.hevaleur,u.urnom,t.tynt_categorie FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik LEFT JOIN clients c ON (h.intervenantcleunik =c.cscleunik AND h.typeintervenantcleunik=1), typepaiement t,user u,entite e WHERE  h.ce_cleunik_cent  = ? AND h.heperiode = ? AND h.urcleunik =u.urcleunik  AND h.hetypepayement = t.tynt_cleunik  AND u.urcleunik=? AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                   
             pstmt=tmpool.getConuser().prepareStatement(requete2);
             pstmt.setInt(1,comptec.getCe_cleunik() );
             pstmt.setInt(2, periode.getPede_cleunik());         
             pstmt.setInt(3, urcle);
         }
         else{
             requete2="SELECT DISTINCT h.hedatemouv,LEFT(t.tynt_libelle1,2), d.dr_numdos,c.csnom,h.henumpiece, h.helibelle, h.hevaleur,u.urnom,t.tynt_categorie FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik LEFT JOIN clients c ON (h.intervenantcleunik =c.cscleunik AND h.typeintervenantcleunik=1), typepaiement t,user u,entite e WHERE  h.ce_cleunik_cent  = ? AND h.heperiode = ? AND h.urcleunik =u.urcleunik AND u.eecleunik=e.eecleunik AND e.eecleunik=? AND h.hetypepayement = t.tynt_cleunik AND h.urcleunik=u.urcleunik AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                
             pstmt=tmpool.getConuser().prepareStatement(requete2);
             pstmt.setInt(1,comptec.getCe_cleunik() );
             pstmt.setInt(2, periode.getPede_cleunik());         
             pstmt.setInt(3, eecleunik);
         }
         result=pstmt.executeQuery();
         result.beforeFirst();        
         int col=result.getMetaData().getColumnCount();
         while(result.next()){
             if(detail==null)
                 detail=new ArrayList();
             Object[] tab=new Object[col];
             for(int i=0;i<tab.length;i++){ 
                 if(i==0)
                     tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));
                 else
                     tab[i]=result.getObject(i+1);
             }
             detail.add(tab);             
         }
         if(detail!=null){
         for(int j=0;j<detail.size();j++){
            Object[] obj=(Object[])detail.get(j);
            String toaffiche="";
            for(int k=0;k<obj.length;k++){
                if(obj[k]!=null)
                toaffiche=toaffiche+" "+obj[k].toString();                
            }
            System.out.println("ligne : "+toaffiche);
         }
         }
         else System.out.println("detail null");
         caisse.setList(detail);
         caisse.setSolde(solde);
         return caisse;
         }catch(SQLException sn){
           sn.printStackTrace();   
         }
         return null;
    }
}

