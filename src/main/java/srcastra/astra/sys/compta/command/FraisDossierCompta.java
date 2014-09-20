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















/**







 *







 * @author  Thomas







 */







public class FraisDossierCompta extends AbstractLigneCompta implements Command{







    







    /** Creates a new instance of DossierCompta */







    produit_T produit;







    Configuration m_config;







    astraimplement m_serveur;







    







    public FraisDossierCompta(Object obj, Configuration config) {



        Frais_T produit=(Frais_T)obj;



        m_config=config;



    }  







    public void executeInsert(int urcleunik) throws java.sql.SQLException{







       /*  "  INSERT INTO `historique3` ( 1 ` heperiode`, 2 `henotcpt`, 3 `heclottva`,4  `heclotperiode`, "+







                                            " 5 `heclotexercice`,6  `hetransact`,7 `jxcleunik`,8 `henumpiece`,9 `hedatecreation`,10 `hedatemouv`,11 `hevaleur`," +







                                            " 12 `hecodetva`,13  `hevaleurbase`,14  `hevaleurtva`, 15 `decleunik`, 16 `hedatedevise`, 17 `hevaleurdevise`,18 `helibelle`,"+







                                            "19 `drcleunik`,20 `lignec leunik`, 21 `sn_cleunik`, 22 `ctprocleunik`, 23 `typeintervenantcleunik`, 24 `intervenantcleunik`,"+







                                            "25 `ce_cleunik`,26  `cate_cleunik`, 27 `hedossiercourant`, 28 `hetypeligne`, 29 `urcleunik`)"+







                                            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?,"+







                                            " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";  */







        







       // "INSERT INTO historique2 (henotcpt,hedatecreation,hevaleur,hecodetva,hevaleurbase,hevaleurtva,drcleunik,lignecleunik,







        //sn_cleunik,ctprocleunik,typeintervenantcleunik,intervenantcleunik,ce_cleunik,cate_cleunik,hedossiercourant,hetypeligne) 







        //values(?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?);");       



       /*public void insert(String requete,Configuration m_config, int henotcpt , int heclottva , int heclotperiode



                                            , int heclotexercice , long hetransact  , long henumpiece ,String hedatecreation ,String hedatemouv,



                                            int ce_cleunik_cent , int tva_cleunik , int ce_cleunik2 ,String ce_cleunik ,double hevaleur , float hecodetva



                                            , double hevaleurbase , double hevaleurtva ,int  decleunik , String hedatedevise , double hevaleurdevise ,String helibelle 



                                            ,long drcleunik ,long lignecleunik ,long sn_cleunik ,int ctprocleunik ,int typeintervenantcleunik,



                                            int intervenantcleunik , int cate_cleunik ,String hedossiercourant , String hetypeligne , int urcleunik



                                            ,int hetypepayement ,String helibellecompta2 ,int pax ,int quantite , float pourcent , double hevaleuru , int gn_cleunik



                                            , int typegrpdec ) */



       String compte="";//=m_config.getM_serveur().getCompte(produit.getGroupdec().getGncomptevente());



       new ComptaInseMod().insert(INSERT_LIGNE,m_config,0,0,0,0,0,0,"0000-00-00 00:00:00",0,0,262,"4000000",-produit.getValeur_tot_tva_inc(),0,-produit.getValeur_tot(), 



       -produit.getMontant_tva(),0,"",0,produit.getLibelleCompta(),m_config.getCledossier(),produit.getAt_cleunik(),0,0,1,m_config.getCleintervenant(),1,"O","F",urcleunik,0,"Ligne de globalisation du dossier",



       0,0,0,0,0,0,"C",0); 



       /*PreparedStatement pstmt=m_config.getCon().prepareStatement(INSERT_LIGNE);







       pstmt.setString(1,""); //heperiode` 







       pstmt.setInt(2,m_config.getDossierfacture()); //henotcpt







       pstmt.setInt(3,0); //heclottva







       pstmt.setInt(4,0); //heclotperiode







       pstmt.setInt(5,0); //heclotexercice







       pstmt.setInt(6,0); //hetransact







       pstmt.setInt(7,0); //jxcleunik







       pstmt.setInt(8,0); //henumpiece







       pstmt.setString(9,"NOW()"); //hedatecreation







       pstmt.setString(10,"");  //`hedatemouv     







       pstmt.setDouble(11,-produit.getValeur_tot_tva_inc()); //`hevaleur







       pstmt.setDouble(12,0); //hecodetva







       pstmt.setDouble(13,-produit.getValeur_tot()); //hevaleurbase







       pstmt.setDouble(14,-produit.getMontant_tva()); //`hevaleurtva







       pstmt.setDouble(15,0); //`decleunik`







       pstmt.setString(16,""); //`hedatedevise`







       pstmt.setDouble(17,0); //hevaleurdevise







       pstmt.setString(18,produit.getLibelleCompta()); //helibelle`







       pstmt.setLong(19,m_config.getCledossier()); //drcleunik`







       pstmt.setLong(20,produit.getAt_cleunik()); //lignecleunik`







       pstmt.setLong(21,0); //`sn_cleunik`







       pstmt.setInt(22,0);//produit.getTypeDeProduitCleunik()); //ctprocleunik`







       pstmt.setInt(23,1); //typeintervenantcleunik







       pstmt.setLong(24,m_config.getCleintervenant()); //`intervenantcleunik







       String compte=m_config.getM_serveur().getCompte(produit.getGroupdec().getGncomptevente());







       pstmt.setString(25,compte); //ce_cleunik







       pstmt.setInt(26,1); //cate_cleunik







       pstmt.setString(27,"O"); //hedossiercourant`







       pstmt.setString(28,"F"); //hetypeligne`







       pstmt.setInt(29,urcleunik); //`urcleunik`







       pstmt.setInt(30,0);







       pstmt.execute();  */



    }



    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {







    }





 public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 

    







}







