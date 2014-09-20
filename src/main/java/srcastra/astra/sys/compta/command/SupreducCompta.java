/*







 * DossierCompta.java







 *







 * Created on 10 février 2003, 15:15







 */















package srcastra.astra.sys.compta.command;







import srcastra.astra.sys.compta.*;







import srcastra.astra.sys.classetransfert.dossier.*;







import java.sql.*;







import srcastra.astra.sys.rmi.*;



import srcastra.astra.sys.compta.checkcompte.*;



import srcastra.astra.sys.classetransfert.configuration.*;















/**







 *







 * @author  Thomas







 */







public class SupreducCompta extends AbstractLigneCompta  implements Command{







    







    /** Creates a new instance of DossierCompta */







    Sup_reduc_T  produit;



    Configuration m_config;



    astraimplement m_serveur;



    float codetva;



    String compte;



    int codetvavente=0;



    int codeCompteVente=0;



    int codeComteCentral=0;



    int gncleunik=0;



    Check check;



    public SupreducCompta(Object obj, Configuration config) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

        produit=(Sup_reduc_T)obj;

        m_config=config;

        check=new Check(config.getCle2(),config);

        check.init();

        check.commun(produit.getGroupdec().getGncomptevente(),GetData.getCompteData(""+m_config.getM_urcleunik()).getTypecentral());

        codetva=produit.getGroupdec().getValeurGenFloat1();    

        compte=m_config.getM_serveur().getCompte(GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),new Long(m_config.getCle2()).intValue());

    }  







    public void executeInsert(int urcleunik) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

       if(!produit.isDeleted()){

            codetva=produit.getGroupdec().getValeurGenFloat1();

            String debcre="";

            if(produit.getSup()==1)

                debcre="C";

            else

               debcre="D";

           new ComptaInseMod().insert(INSERT_LIGNE,m_config,0,0,0,0,0l,0l,"0000-00-00 00:00:00",codeComteCentral,produit.getGroupdec().getGncodetvavente(),codeCompteVente,compte,-produit.getValeur_tot_tva_inc(),codetva,-produit.getValeur_tot(), 

           -produit.getMontant_tva(),0,"",0d,produit.getFreeLibelle(),m_config.getCledossier(),m_config.getCleproduit(),produit.getAt_cleunik(),produit.getTypeDeProduitCleunik(),1,m_config.getCleintervenant(),1,"O","D",urcleunik,0,"Ligne de globalisation du dossier",

           produit.getPax(),produit.getQua(),produit.getPrct(),produit.getAt_val_vente(),produit.getGroupdec().getGncleunik(),1,debcre,0);   

       }

    }







    







    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {



         /*  modify(String requete,Object obj,Configuration m_config,int ce_cleunik_cent,int tva_cleunik,int ce_cleunik2,String ce_cleunik ,double hevaleur ,double hecodetva 



                                            ,double hevaleurbase ,double hevaleurtva ,int decleunik ,String hedatedevise , double hevaleurdevise ,String helibelle                                          



                                            ,int intervenantcleunik ,int cate_cleunik ,int urcleunik



                                            ,int hetypepayement,String helibellecompta2 , int pax , int quantite , float pourcent , double hevaleuru , int gn_cleunik) throws SQLException{      */



    if(produit.isDeleted()){



        new ComptaInseMod().delete(m_config,m_config.getCledossier(),m_config.getCleproduit(),produit.getAt_cleunik(),m_config.getTypeProduit()); 



        



    }else{

        if(!produit.isIsnewreccord()){

            new ComptaInseMod().modify(m_config.getCledossier(),m_config.getCleproduit(),produit.getAt_cleunik(),UPDATE_LIGNE,produit, m_config,codeComteCentral,produit.getGroupdec().getGncodetvavente(),codeCompteVente,compte ,-produit.getValeur_tot_tva_inc(),codetva 

                                            ,-produit.getValeur_tot(),-produit.getMontant_tva() ,0 ,"" , 0 ,produit.getFreeLibelle()                                          

                                            ,m_config.getCleintervenant() ,0 , urcleunik

                                            ,0,"" , produit.getPax() , produit.getQua() , produit.getPrct() , produit.getAt_val_vente() ,produit.getGroupdec().getGncleunik(),m_config.getTypeProduit()) ;   

     }

     else{

          String debcre=""; 

            if(produit.getSup()==1)

                debcre="C";

            else 

               debcre="D";

    /*   new ComptaInseMod().insert(INSERT_LIGNE,m_config,0,0,0,0,0l,0l,"0000-00-00 00:00:00",0,produit.getGroupdec().getGncodetvavente(),produit.getGroupdec().getGncomptevente(),compte,-produit.getValeur_tot_tva_inc(),codetva,-produit.getValeur_tot(), 

       -produit.getMontant_tva(),0,"",0d,produit.getFreeLibelle(),m_config.getCledossier(),m_config.getCleproduit(),produit.getAt_cleunik(),produit.getTypeDeProduitCleunik(),1,m_config.getCleintervenant(),1,"O","D",urcleunik,0,"Ligne de globalisation du dossier",

       produit.getPax(),produit.getQua(),produit.getPrct(),produit.getAt_val_vente(),produit.getGroupdec().getGncleunik(),1);     */

       new ComptaInseMod().insert(INSERT_LIGNE,m_config,0,0,0,0,0l,0l,"0000-00-00 00:00:00",codeComteCentral,produit.getGroupdec().getGncodetvavente(),codeCompteVente,compte,-produit.getValeur_tot_tva_inc(),codetva,-produit.getValeur_tot(), 

       -produit.getMontant_tva(),0,"",0d,produit.getFreeLibelle(),m_config.getCledossier(),m_config.getCleproduit(),produit.getAt_cleunik(),produit.getTypeDeProduitCleunik(),1,m_config.getCleintervenant(),1,"O","D",urcleunik,0,"Ligne de globalisation du dossier",

       produit.getPax(),produit.getQua(),produit.getPrct(),produit.getAt_val_vente(),produit.getGroupdec().getGncleunik(),1,debcre,0);   

     }

     }

    }
    
     public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 

}







