/*
 * ComptaInseMod.java
 *
 * Created on 12 juin 2003, 14:14
 */

package srcastra.astra.sys.compta.command;
import java.sql.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.classetransfert.dossier.*;
/**
 *
 * @author  thomas
 */
public class ComptaInseMod {
    
    /** Creates a new instance of ComptaInseMod */
    public ComptaInseMod() {
    }
    public void insert(String requete,Configuration m_config, int henotcpt , int heclottva , int heclotperiode
                                            , int heclotexercice , long hetransact  , long henumpiece ,String hedatemouv,
                                            int ce_cleunik_cent , int tva_cleunik , int ce_cleunik2 ,String ce_cleunik ,double hevaleur , float hecodetva
                                            , double hevaleurbase , double hevaleurtva ,int  decleunik , String hedatedevise , double hevaleurdevise ,String helibelle 
                                            ,long drcleunik ,long lignecleunik ,long sn_cleunik ,int ctprocleunik ,int typeintervenantcleunik,
                                            long intervenantcleunik , int cate_cleunik ,String hedossiercourant , String hetypeligne , int urcleunik
                                            ,int hetypepayement ,String helibellecompta2 ,int pax ,int quantite , float pourcent , double hevaleuru , int gn_cleunik
                                            , int typegrpdec,String debcre,long heexported ) throws SQLException{
       requete="INSERT INTO historique2 ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik`,he_dc, he_exported ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, NOW() , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?,?)";
        
       PreparedStatement pstmt=m_config.getCon().prepareStatement(requete);
       pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getPeriode()); //heperiode`
       pstmt.setInt(2,henotcpt); //henotcpt
       pstmt.setInt(3,heclottva); //heclottva
       pstmt.setInt(4,0); //heclotperiode
       pstmt.setInt(5,heclotexercice); //heclotexercice
       System.out.println("Transaction "+GetData.getCompteData(""+urcleunik).getTransaction());
       pstmt.setLong(6,GetData.getCompteData(""+urcleunik).getTransaction()); //hetransact
       pstmt.setInt(7,GetData.getCompteData(""+urcleunik).getJota_cleunik()); //jxcleunik
       pstmt.setLong(8,henumpiece); //henumpiece
      // pstmt.setString(9,"NOW()"); //hedatecreation
       pstmt.setString(9,hedatemouv); //`hedatemouv    
       pstmt.setInt(10,GetData.getCompteData(""+urcleunik).getCe_cent_cleunik()); // `ce_cleunik_cent` ,
       pstmt.setInt(11,tva_cleunik);  //  `tva_cleunik` , 
       pstmt.setInt(12,GetData.getCompteData(""+urcleunik).getCe_cleunik());  //`ce_cleunik2` 
       pstmt.setString(13,GetData.getCompteData(""+urcleunik).getNumerocompte());//`ce_cleunik` ,
       pstmt.setDouble(14,hevaleur); //`hevaleur
       pstmt.setDouble(15,hecodetva); //hecodetva
       pstmt.setDouble(16,hevaleurbase); //hevaleurbase
       pstmt.setDouble(17,hevaleurtva); //`hevaleurtva
       pstmt.setDouble(18,decleunik); //`decleunik`
       pstmt.setString(19,hedatedevise); //`hedatedevise`
       pstmt.setDouble(20,hevaleurdevise); //hevaleurdevise
       pstmt.setString(21,helibelle); //helibelle`
       pstmt.setLong(22,drcleunik); //drcleunik`
       pstmt.setLong(23,lignecleunik); //lignecleunik`
       pstmt.setLong(24,sn_cleunik); //`sn_cleunik`
       pstmt.setInt(25,ctprocleunik); //ctprocleunik`
       pstmt.setInt(26,typeintervenantcleunik); //typeintervenantcleunik
       pstmt.setLong(27,intervenantcleunik); //`intervenantcleunik
       pstmt.setInt(28,cate_cleunik); //cate_cleunik
       pstmt.setString(29,hedossiercourant); //hedossiercourant`
       pstmt.setString(30,hetypeligne); //hetypeligne`
       pstmt.setInt(31,urcleunik); //`urcleunik`
       pstmt.setInt(32,hetypepayement); // hetypepaiement 
       pstmt.setString(33,helibellecompta2);   //helibellecompta2` 
       pstmt.setInt(34,pax);//`pax` 
       pstmt.setInt(35,quantite);//`quantite` ,
       pstmt.setFloat(36,pourcent); //`pourcent` , 
       pstmt.setDouble(37,hevaleuru); //`hevaleuru` , 
       pstmt.setInt(38,gn_cleunik);   // `gn_cleunik` 
       pstmt.setInt(39,typegrpdec);//`typegrpdec`
       pstmt.setInt(40,GetData.getCompteData(""+urcleunik).getExle_cleunik()); //exle_cleunik 
       pstmt.setString(41,debcre); //exle_cleunik 
       pstmt.setLong(42,heexported);
       
       pstmt.execute();       
    }
   public void insertWithTransact(String requete,Configuration m_config, int henotcpt , int heclottva , int heclotperiode
                                            , int heclotexercice , long hetransact  , long henumpiece ,String hedatemouv,
                                            int ce_cleunik_cent , int tva_cleunik , int ce_cleunik2 ,String ce_cleunik ,double hevaleur , float hecodetva
                                            , double hevaleurbase , double hevaleurtva ,int  decleunik , String hedatedevise , double hevaleurdevise ,String helibelle
                                            ,long drcleunik ,long lignecleunik ,long sn_cleunik ,int ctprocleunik ,int typeintervenantcleunik,
                                            long intervenantcleunik , int cate_cleunik ,String hedossiercourant , String hetypeligne , int urcleunik
                                            ,int hetypepayement ,String helibellecompta2 ,int pax ,int quantite , float pourcent , double hevaleuru , int gn_cleunik
                                            , int typegrpdec,String debcre ) throws SQLException{
       requete="INSERT INTO historique2 ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik`,he_dc ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, NOW() , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?)";

       PreparedStatement pstmt=m_config.getCon().prepareStatement(requete);
       pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getPeriode()); //heperiode`
       pstmt.setInt(2,henotcpt); //henotcpt
       pstmt.setInt(3,heclottva); //heclottva
       pstmt.setInt(4,0); //heclotperiode
       pstmt.setInt(5,heclotexercice); //heclotexercice
       System.out.println("Transaction "+GetData.getCompteData(""+urcleunik).getTransaction());
       pstmt.setLong(6,hetransact); //hetransact
       pstmt.setInt(7,GetData.getCompteData(""+urcleunik).getJota_cleunik()); //jxcleunik
       pstmt.setLong(8,henumpiece); //henumpiece
      // pstmt.setString(9,"NOW()"); //hedatecreation
       pstmt.setString(9,hedatemouv); //`hedatemouv
       pstmt.setInt(10,GetData.getCompteData(""+urcleunik).getCe_cent_cleunik()); // `ce_cleunik_cent` ,
       pstmt.setInt(11,tva_cleunik);  //  `tva_cleunik` ,
       pstmt.setInt(12,GetData.getCompteData(""+urcleunik).getCe_cleunik());  //`ce_cleunik2`
       pstmt.setString(13,GetData.getCompteData(""+urcleunik).getNumerocompte());//`ce_cleunik` ,
       pstmt.setDouble(14,hevaleur); //`hevaleur
       pstmt.setDouble(15,hecodetva); //hecodetva
       pstmt.setDouble(16,hevaleurbase); //hevaleurbase
       pstmt.setDouble(17,hevaleurtva); //`hevaleurtva
       pstmt.setDouble(18,decleunik); //`decleunik`
       pstmt.setString(19,hedatedevise); //`hedatedevise`
       pstmt.setDouble(20,hevaleurdevise); //hevaleurdevise
       pstmt.setString(21,helibelle); //helibelle`
       pstmt.setLong(22,drcleunik); //drcleunik`
       pstmt.setLong(23,lignecleunik); //lignecleunik`
       pstmt.setLong(24,sn_cleunik); //`sn_cleunik`
       pstmt.setInt(25,ctprocleunik); //ctprocleunik`
       pstmt.setInt(26,typeintervenantcleunik); //typeintervenantcleunik
       pstmt.setLong(27,intervenantcleunik); //`intervenantcleunik
       pstmt.setInt(28,cate_cleunik); //cate_cleunik
       pstmt.setString(29,hedossiercourant); //hedossiercourant`
       pstmt.setString(30,hetypeligne); //hetypeligne`
       pstmt.setInt(31,urcleunik); //`urcleunik`
       pstmt.setInt(32,hetypepayement); // hetypepaiement
       pstmt.setString(33,helibellecompta2);   //helibellecompta2`
       pstmt.setInt(34,pax);//`pax`
       pstmt.setInt(35,quantite);//`quantite` ,
       pstmt.setFloat(36,pourcent); //`pourcent` ,
       pstmt.setDouble(37,hevaleuru); //`hevaleuru` ,
       pstmt.setInt(38,gn_cleunik);   // `gn_cleunik`
       pstmt.setInt(39,typegrpdec);//`typegrpdec`
       pstmt.setInt(40,GetData.getCompteData(""+urcleunik).getExle_cleunik()); //exle_cleunik
       pstmt.setString(41,debcre); //exle_cleunik
       pstmt.execute();
    }
    public void insertRattrap(String requete,Configuration m_config, int henotcpt , int heclottva , int heclotperiode
                                            , int heclotexercice , long hetransact  , long henumpiece ,String hedatemouv,
                                            int ce_cleunik_cent , int tva_cleunik , int ce_cleunik2 ,String ce_cleunik ,double hevaleur , float hecodetva
                                            , double hevaleurbase , double hevaleurtva ,int  decleunik , String hedatedevise , double hevaleurdevise ,String helibelle 
                                            ,long drcleunik ,long lignecleunik ,long sn_cleunik ,int ctprocleunik ,int typeintervenantcleunik,
                                            long intervenantcleunik , int cate_cleunik ,String hedossiercourant , String hetypeligne , int urcleunik
                                            ,int hetypepayement ,String helibellecompta2 ,int pax ,int quantite , float pourcent , double hevaleuru , int gn_cleunik
                                            , int typegrpdec,String debcre ) throws SQLException{
       requete="INSERT INTO  historiquerecup ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik`,he_dc ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, NOW() , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?)";
        
       PreparedStatement pstmt=m_config.getCon().prepareStatement(requete);
       pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getPeriode()); //heperiode`
       pstmt.setInt(2,henotcpt); //henotcpt
       pstmt.setInt(3,heclottva); //heclottva
       pstmt.setInt(4,0); //heclotperiode
       pstmt.setInt(5,heclotexercice); //heclotexercice
       pstmt.setLong(6,GetData.getCompteData(""+urcleunik).getTransaction()); //hetransact
       pstmt.setInt(7,GetData.getCompteData(""+urcleunik).getJota_cleunik()); //jxcleunik
       pstmt.setLong(8,henumpiece); //henumpiece
      // pstmt.setString(9,"NOW()"); //hedatecreation
       pstmt.setString(9,hedatemouv); //`hedatemouv    
       pstmt.setInt(10,GetData.getCompteData(""+urcleunik).getCe_cent_cleunik()); // `ce_cleunik_cent` ,
       pstmt.setInt(11,tva_cleunik);  //  `tva_cleunik` , 
       pstmt.setInt(12,GetData.getCompteData(""+urcleunik).getCe_cleunik());  //`ce_cleunik2` 
       pstmt.setString(13,GetData.getCompteData(""+urcleunik).getNumerocompte());//`ce_cleunik` ,
       pstmt.setDouble(14,hevaleur); //`hevaleur
       pstmt.setDouble(15,hecodetva); //hecodetva
       pstmt.setDouble(16,hevaleurbase); //hevaleurbase
       pstmt.setDouble(17,hevaleurtva); //`hevaleurtva
       pstmt.setDouble(18,decleunik); //`decleunik`
       pstmt.setString(19,hedatedevise); //`hedatedevise`
       pstmt.setDouble(20,hevaleurdevise); //hevaleurdevise
       pstmt.setString(21,helibelle); //helibelle`
       pstmt.setLong(22,drcleunik); //drcleunik`
       pstmt.setLong(23,lignecleunik); //lignecleunik`
       pstmt.setLong(24,sn_cleunik); //`sn_cleunik`
       pstmt.setInt(25,ctprocleunik); //ctprocleunik`
       pstmt.setInt(26,typeintervenantcleunik); //typeintervenantcleunik
       pstmt.setLong(27,intervenantcleunik); //`intervenantcleunik
       pstmt.setInt(28,cate_cleunik); //cate_cleunik
       pstmt.setString(29,hedossiercourant); //hedossiercourant`
       pstmt.setString(30,hetypeligne); //hetypeligne`
       pstmt.setInt(31,urcleunik); //`urcleunik`
       pstmt.setInt(32,hetypepayement); // hetypepaiement 
       pstmt.setString(33,helibellecompta2);   //helibellecompta2` 
       pstmt.setInt(34,pax);//`pax` 
       pstmt.setInt(35,quantite);//`quantite` ,
       pstmt.setFloat(36,pourcent); //`pourcent` , 
       pstmt.setDouble(37,hevaleuru); //`hevaleuru` , 
       pstmt.setInt(38,gn_cleunik);   // `gn_cleunik` 
       pstmt.setInt(39,typegrpdec);//`typegrpdec`
       pstmt.setInt(40,GetData.getCompteData(""+urcleunik).getExle_cleunik()); //exle_cleunik 
       pstmt.setString(41,debcre); //exle_cleunik 
       pstmt.execute();       
    }
    public void delete(Configuration m_config,long cledossier,long lignecleunik,long sncleunik,int typeprod) throws SQLException{      
       PreparedStatement pstmt=m_config.getCon().prepareStatement("UPDATE  historique2 set henotcpt=2,hedossiercourant ='N' WHERE drcleunik =? AND  lignecleunik =? AND sn_cleunik =? AND ctprocleunik=? AND hedossiercourant='O'");
       pstmt.setLong(1,cledossier);    
       pstmt.setLong(2,lignecleunik);
       pstmt.setLong(3,sncleunik);
       pstmt.setInt(4,typeprod);
       pstmt.execute();
    }
    public void modify(long cledossier,long lignecleunik,long sncleunik,String requete,Object obj,Configuration m_config,int ce_cleunik_cent,int tva_cleunik,int ce_cleunik2,String ce_cleunik ,double hevaleur ,double hecodetva 
                                            ,double hevaleurbase ,double hevaleurtva ,int decleunik ,String hedatedevise , double hevaleurdevise ,String helibelle                                          
                                            ,long intervenantcleunik ,int cate_cleunik ,int urcleunik
                                            ,int hetypepayement,String helibellecompta2 , int pax , int quantite , float pourcent , double hevaleuru , int gn_cleunik,int ctprocleunik) throws SQLException{      
      
       /*"UPDATE `historique2`  set `ce_cleunik_cent`=? , `tva_cleunik`=? , `ce_cleunik2`=? , `ce_cleunik`=? , `hevaleur`=? , `hecodetva`=? "
                                            +", `hevaleurbase`=? , `hevaleurtva`=? , `decleunik`=? , `hedatedevise`=? , `hevaleurdevise`=? , `helibelle`=? "                                          
                                            +", `intervenantcleunik`=? , `cate_cleunik`=? ,`urcleunik`=? "
                                            +", `hetypepayement`=? , `helibellecompta2`=? , `pax`=? , `quantite`=? , `pourcent`=? , `hevaleuru`=? , `gn_cleunik`=? "
                                            +"  WHERE drcleunik=? AND lignecleunik=0 AND sn_cleunik=0";
        
        "UPDATE `historique2`  set `ce_cleunik_cent`=? , `tva_cleunik`=? , `ce_cleunik2`=? , `ce_cleunik`=? , `hevaleur`=? , `hecodetva`=? "
                                            +", `hevaleurbase`=? , `hevaleurtva`=? , `decleunik`=? , `hedatedevise`=? , `hevaleurdevise`=? , `helibelle`=? "                                          
                                            +", `intervenantcleunik`=? , `cate_cleunik`=? ,`urcleunik`=? "
                                            +", `hetypepayement`=? , `helibellecompta2`=? , `pax`=? , `quantite`=? , `pourcent`=? , `hevaleuru`=? , `gn_cleunik`=? "
                                            +" WHERE drcleunik=? AND lignecleunik=? AND sn_cleunik=0";
        
       "UPDATE `historique2`  set `ce_cleunik_cent`=? , `tva_cleunik`=? , `ce_cleunik2`=? , `ce_cleunik`=? , `hevaleur`=? , `hecodetva`=? "
                                            +", `hevaleurbase`=? , `hevaleurtva`=? , `decleunik`=? , `hedatedevise`=? , `hevaleurdevise`=? , `helibelle`=? "                                          
                                            +", `intervenantcleunik`=? , `cate_cleunik`=? ,`urcleunik`=? "
                                            +", `hetypepayement`=? , `helibellecompta2`=? , `pax`=? , `quantite`=? , `pourcent`=? , `hevaleuru`=? , `gn_cleunik`=? "
                                            +"  WHERE drcleunik=? AND lignecleunik=? AND sn_cleunik=?";*/                                         
       PreparedStatement pstmt=m_config.getCon().prepareStatement(requete);  
       pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getCe_cent_cleunik());     // `ce_cleunik_cent` ,
       pstmt.setInt(2,tva_cleunik);     //  `tva_cleunik` , 
       pstmt.setInt(3,GetData.getCompteData(""+urcleunik).getCe_cleunik());       //`ce_cleunik2` 
       pstmt.setString(4,GetData.getCompteData(""+urcleunik).getNumerocompte());//`ce_cleunik` ,
       pstmt.setDouble(5,hevaleur); //`hevaleur
       pstmt.setDouble(6,hecodetva); //hecodetva
       pstmt.setDouble(7,hevaleurbase); //hevaleurbase
       pstmt.setDouble(8,hevaleurtva); //`hevaleurtva
       pstmt.setDouble(9,decleunik); //`decleunik`
       pstmt.setString(10,hedatedevise); //`hedatedevise`
       pstmt.setDouble(11,hevaleurdevise); //hevaleurdevise
       pstmt.setString(12,helibelle); //helibelle`
       pstmt.setLong(13,intervenantcleunik); //`intervenantcleunik
       pstmt.setInt(14,cate_cleunik); //cate_cleunik
       pstmt.setInt(15,urcleunik); //`urcleunik`
       pstmt.setInt(16,hetypepayement); // hetypepaiement 
       pstmt.setString(17,helibellecompta2);   //helibellecompta2` 
       pstmt.setInt(18,pax);//`pax` 
       pstmt.setInt(19,quantite);//`quantite` ,
       pstmt.setFloat(20,pourcent); //`pourcent` , 
       pstmt.setDouble(21,hevaleuru); //`hevaleuru` , 
       pstmt.setInt(22,gn_cleunik);   // `gn_cleunik` 
       pstmt.setLong(23,cledossier); 
       pstmt.setLong(24,lignecleunik);
       pstmt.setLong(25,sncleunik); 
       pstmt.setInt(26,ctprocleunik); 
       pstmt.execute();         
    }
    
}
