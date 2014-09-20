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

import java.util.*;

import srcastra.astra.sys.classetransfert.compta.*;

import srcastra.astra.sys.compta.checkcompte.*;

import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.*;







/**



 *



 * @author  Thomas



 */



public class NcCompta extends AbstractLigneCompta implements Command{



    



    /** Creates a new instance of DossierCompta */



   // Dossier_T dossier;



    Configuration m_config;



    astraimplement m_serveur;

    long cledossier;

    Check check;



    



    public NcCompta(long cledossier, Configuration config)  throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

        this.cledossier=cledossier;

        m_config=config;

        check=new Check(config.getCle2(),config);

        check.init();

    }  

    public NcCompta(int cleUser,Configuration config)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
     
         m_config=config;
        if(config!=null){
          m_config=config;
            check=new Check(config.getCle2(),config);
            check.init();
        }
        else{
            m_config=new Configuration(); 
            m_config.setM_urcleunik(cleUser);    
        }
    }

 
    public void inverseODcccf(int urcleunik,long avionkey)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
        //check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
         check.base4(CheckCompteCentral.CCCF,ParamComptableInterface.JOURNAL_OCCCF);
         String numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_OCCCF,0,0);
         PreparedStatement pstmt;
         ArrayList array=new ArrayList();
         long transac=Check.getTransaction2(m_config.getCon(),m_config.getM_serveur().getTransactSyn(),check.getTmpool(),m_config.getM_serveur());
         //pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE hetypeligne!='P' AND hetypeligne!='CP' AND  hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE (hetypeligne='OBCC' OR hetypeligne='OCCC') AND hedossiercourant='O' AND  lignecleunik=?   ORDER BY hecleunik");
         pstmt.setLong(1,avionkey);
         ResultSet result=pstmt.executeQuery();
         Object[] tab=m_config.getM_serveur().renvParamCompta(urcleunik).getPeriodeCompta(ParamComptableInterface.JOURNAL_ACHAT,urcleunik,null);
         int periode=((Integer)tab[0]).intValue();
          while (result.next()){ 
           I_ligneComptable tmp= new I_ligneComptable(    
                                                                    result.getObject(2),result.getObject(3),result.getObject(4),result.getObject(5),
                                                                    result.getObject(6),result.getObject(7),result.getObject(8),
                                                                    result.getObject(9),result.getObject(10),result.getObject(11),
                                                                    result.getObject(12),result.getObject(13),result.getObject(14),result.getObject(15),result.getObject(16),
                                                                    result.getObject(17),result.getObject(18),result.getObject(19),result.getObject(20),result.getObject(21),
                                                                    result.getObject(22),result.getObject(23),result.getObject(24),result.getObject(25),result.getObject(26),
                                                                    result.getObject(27),result.getObject(28),result.getObject(29),result.getObject(30),result.getObject(31),
                                                                    result.getObject(32),result.getObject(33),result.getObject(34),result.getObject(35),result.getObject(36),
                                                                    result.getObject(37),result.getObject(38),result.getObject(39),result.getObject(40),result.getObject(41),
                                                                    result.getObject(42),result.getObject(45));
           array.add(tmp);                    
        //  insertLigneNoteCrédit(tmp,con,pstmt);
         }
       for(int j=0;j<array.size();j++){
          /*  "INSERT INTO `historique2` ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik2`   ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, ? , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?)";*/
           NcCompta.I_ligneComptable ligne=(NcCompta.I_ligneComptable)array.get(j); 
           setSoldeValue(true,ligne);
           String datetmp=CalculDate.getTodayDate().toString();
           pstmt=m_config.getCon().prepareStatement(INSERT_LIGNE);
           pstmt.setInt(1,periode); //heperiode` 
           pstmt.setInt(2,ligne.m_henotcpt.intValue()); //henotcpt
           pstmt.setInt(3,ligne.m_heclottva.intValue()); //heclottva
           pstmt.setInt(4,ligne.m_heclotperiode.intValue()); //heclotperiode
           pstmt.setInt(5,ligne.m_heclotexercice.intValue()); //heclotexercice
           pstmt.setInt(6,new Long(transac).intValue()); //hetransact
           pstmt.setLong(7,ligne.m_jxcleunik.longValue()); //jxcleunik
           pstmt.setInt(8,Integer.parseInt(numpiece)); //henumpiece
           pstmt.setString(9,datetmp); //hedatecreation
           pstmt.setString(10,datetmp);  //`hedatemouv     
           pstmt.setInt(11,ligne.m_ce_cleunik_cent.intValue());  //`hedatemouv     
           pstmt.setInt(12,ligne.m_tva_cleunik.intValue());  //`hedatemouv     
           pstmt.setInt(13,ligne.m_ce_cleunik2.intValue());  //`hedatemouv     
           pstmt.setString(14,ligne.m_ce_cleunik);  //`hedatemouv     
           pstmt.setDouble(15,ligne.m_hevaleur.doubleValue()); //`hevaleur
           pstmt.setFloat(16,ligne.m_hecodetva.floatValue()); //hecodetva
           pstmt.setDouble(17,ligne.m_heValeurBase.doubleValue()); //hevaleurbase 
           pstmt.setDouble(18,ligne.m_heValeurTva.doubleValue()); //`hevaleurtva
           pstmt.setDouble(19,ligne.m_decleunik.intValue()); //`decleunik`
           pstmt.setString(20,ligne.m_hedatedevise); //`hedatedevise`
           pstmt.setDouble(21,ligne.m_hevaleurdevise.doubleValue()); //hevaleurdevise
           pstmt.setString(22,ligne.m_helibelle); //helibelle`
           pstmt.setLong(23,ligne.m_drCleunik.longValue()); //drcleunik` 
           pstmt.setLong(24,ligne.m_lecleunik.longValue()); //lignecleunik`
           pstmt.setLong(25,ligne.m_sn_cleunik.longValue()); //`sn_cleunik`
           pstmt.setInt(26,ligne.m_ct_cleunik.intValue()); //ctprocleunik`
           pstmt.setInt(27,ligne.m_typeIntervenant.intValue()); //typeintervenantcleunik
           pstmt.setLong(28,ligne.m_intervenantCleunik.intValue()); //`intervenantcleunik
           pstmt.setInt(29,ligne.m_cate_cleunik.intValue()); //cate_cleunik
           pstmt.setString(30,"O"); //hedossiercourant`
           pstmt.setString(31,ligne.m_he_dossier_lignetype); //hetypeligne`
           pstmt.setInt(32,ligne.m_urcleunik.intValue()); //`urcleunik`
           pstmt.setInt(33,ligne.m_hetypePayement.intValue());
           pstmt.setString(34,ligne.m_helibellecompta);
           pstmt.setInt(35,ligne.m_pax.intValue());
           pstmt.setInt(36,ligne.m_quantite.intValue());
           pstmt.setFloat(37,ligne.m_pourcent.floatValue());
           pstmt.setDouble(38,ligne.m_hevaleur.doubleValue());
           pstmt.setInt(39,ligne.m_gn_cleunik.intValue());
           pstmt.setInt(40,ligne.m_typegrpdec.intValue());
           pstmt.setInt(41,ligne.m_exle_cleunik.intValue());
           pstmt.setString(42, ligne.m_debcre);
           pstmt.execute();  
       }
    }
    public void executeInsert(int urcleunik) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

       /*  "  INSERT INTO `historique3` ( 1 ` heperiode`, 2 `henotcpt`, 3 `heclottva`,4  `heclotperiode`, "+

                                            " 5 `heclotexercice`,6  `hetransact`,7 `jxcleunik`,8 `henumpiece`,9 `hedatecreation`,10 `hedatemouv`,11 `hevaleur`," +

                                            " 12 `hecodetva`,13  `hevaleurbase`,14  `hevaleurtva`, 15 `decleunik`, 16 `hedatedevise`, 17 `hevaleurdevise`,18 `helibelle`,"+

                                            "19 `drcleunik`,20 `lignecleunik`, 21 `sn_cleunik`, 22 `ctprocleunik`, 23 `typeintervenantcleunik`, 24 `intervenantcleunik`,"+

                                            "25 `ce_cleunik`,26  `cate_cleunik`, 27 `hedossiercourant`, 28 `hetypeligne`, 29 `urcleunik`)"+

                                            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?,"+

                                            " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";  */ 
       String numpiece="";
       JournalCompta_T obj=null;
       check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
      // numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
       obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
       //m_config.setNumnc(numpiece);
         PreparedStatement pstmt;
         //pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE hetypeligne!='P' AND hetypeligne!='CP' AND  hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt.setLong(1,cledossier);
         ResultSet result=pstmt.executeQuery();
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCO' WHERE  hetypeligne='NC'  AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCOB' WHERE  hetypeligne='NCB' AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCOT' WHERE  hetypeligne='NCT' AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
        // pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hedossiercourant ='N' WHERE hedossiercourant='O' AND hetypeligne!='P' AND hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='CP' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND drcleunik =?");
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hedossiercourant ='N' WHERE hedossiercourant='O' AND (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();         
         result.beforeFirst();
         ArrayList array=new ArrayList();
         long transac=Check.getTransaction2(m_config.getCon(),m_config.getM_serveur().getTransactSyn(),check.getTmpool(),m_config.getM_serveur());
         while (result.next()){
           I_ligneComptable tmp= new I_ligneComptable(   
                                                                    result.getObject(2),result.getObject(3),result.getObject(4),result.getObject(5),
                                                                    result.getObject(6),result.getObject(7),result.getObject(8),
                                                                    result.getObject(9),result.getObject(10),result.getObject(11),
                                                                    result.getObject(12),result.getObject(13),result.getObject(14),result.getObject(15),result.getObject(16),
                                                                    result.getObject(17),result.getObject(18),result.getObject(19),result.getObject(20),result.getObject(21),
                                                                    result.getObject(22),result.getObject(23),result.getObject(24),result.getObject(25),result.getObject(26),
                                                                    result.getObject(27),result.getObject(28),result.getObject(29),result.getObject(30),result.getObject(31),
                                                                    result.getObject(32),result.getObject(33),result.getObject(34),result.getObject(35),result.getObject(36),
                                                                    result.getObject(37),result.getObject(38),result.getObject(39),result.getObject(40),result.getObject(41),
                                                                    result.getObject(42),result.getObject(45));
           array.add(tmp);                    
        //  insertLigneNoteCrédit(tmp,con,pstmt);
         }
       for(int j=0;j<array.size();j++){
          /*  "INSERT INTO `historique2` ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik2`   ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, ? , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?)";*/
           String nc="NCB";
         /*  if(j!=0){
               nc="NC";
           }
           else{
               check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
               numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
               obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
               m_config.setNumnc(numpiece);  
           }*/
           NcCompta.I_ligneComptable ligne=(NcCompta.I_ligneComptable)array.get(j); 
            if(j!=0){
               nc="NC";
           }
           else{ 
               if(ligne.m_hevaleur.doubleValue()!=0d){
              //  check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
                numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
               // obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
                m_config.setNumnc(numpiece);  
               }
               else{
                   
                            numpiece=new Integer(0).toString();
               }
           }
           setSoldeValue(true,ligne);
           String datetmp=CalculDate.getTodayDate().toString();
           pstmt=m_config.getCon().prepareStatement(INSERT_LIGNE);
           pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getPeriode()); //heperiode` 
           pstmt.setInt(2,ligne.m_henotcpt.intValue()); //henotcpt
           pstmt.setInt(3,ligne.m_heclottva.intValue()); //heclottva
           pstmt.setInt(4,ligne.m_heclotperiode.intValue()); //heclotperiode
           pstmt.setInt(5,ligne.m_heclotexercice.intValue()); //heclotexercice
           pstmt.setInt(6,new Long(transac).intValue()); //hetransact
           pstmt.setLong(7,obj.getJota_cleunik()); //jxcleunik
           pstmt.setInt(8,Integer.parseInt(numpiece)); //henumpiece
           pstmt.setString(9,datetmp); //hedatecreation
           pstmt.setString(10,datetmp);  //`hedatemouv     
           pstmt.setInt(11,ligne.m_ce_cleunik_cent.intValue());  //`hedatemouv     
           pstmt.setInt(12,ligne.m_tva_cleunik.intValue());  //`hedatemouv     
           pstmt.setInt(13,ligne.m_ce_cleunik2.intValue());  //`hedatemouv     
           pstmt.setString(14,ligne.m_ce_cleunik);  //`hedatemouv     
           pstmt.setDouble(15,ligne.m_hevaleur.doubleValue()); //`hevaleur
           pstmt.setFloat(16,ligne.m_hecodetva.floatValue()); //hecodetva
           pstmt.setDouble(17,ligne.m_heValeurBase.doubleValue()); //hevaleurbase
           pstmt.setDouble(18,ligne.m_heValeurTva.doubleValue()); //`hevaleurtva
           pstmt.setDouble(19,ligne.m_decleunik.intValue()); //`decleunik`
           pstmt.setString(20,ligne.m_hedatedevise); //`hedatedevise`
           pstmt.setDouble(21,ligne.m_hevaleurdevise.doubleValue()); //hevaleurdevise
           pstmt.setString(22,ligne.m_helibelle); //helibelle`
           pstmt.setLong(23,ligne.m_drCleunik.longValue()); //drcleunik` 
           pstmt.setLong(24,ligne.m_lecleunik.longValue()); //lignecleunik`
           pstmt.setLong(25,ligne.m_sn_cleunik.longValue()); //`sn_cleunik`
           pstmt.setInt(26,ligne.m_ct_cleunik.intValue()); //ctprocleunik`
           pstmt.setInt(27,ligne.m_typeIntervenant.intValue()); //typeintervenantcleunik
           pstmt.setLong(28,ligne.m_intervenantCleunik.intValue()); //`intervenantcleunik
           pstmt.setInt(29,ligne.m_cate_cleunik.intValue()); //cate_cleunik
           pstmt.setString(30,"N"); //hedossiercourant`
           if(ligne.m_he_dossier_lignetype.equals("TVAV"))
               nc="NCT";
           pstmt.setString(31,nc); //hetypeligne`
           pstmt.setInt(32,ligne.m_urcleunik.intValue()); //`urcleunik`
           pstmt.setInt(33,ligne.m_hetypePayement.intValue());
           pstmt.setString(34,ligne.m_helibellecompta);
           pstmt.setInt(35,ligne.m_pax.intValue());
           pstmt.setInt(36,ligne.m_quantite.intValue());
           pstmt.setFloat(37,ligne.m_pourcent.floatValue());
           pstmt.setDouble(38,ligne.m_hevaleur.doubleValue());
           pstmt.setInt(39,ligne.m_gn_cleunik.intValue());
           pstmt.setInt(40,ligne.m_typegrpdec.intValue());
           pstmt.setInt(41,ligne.m_exle_cleunik.intValue());
           pstmt.setString(42, ligne.m_debcre);
           pstmt.execute();  
       }
    }
    
    public void executeInsertNCService(int urcleunik) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

       /*  "  INSERT INTO `historique3` ( 1 ` heperiode`, 2 `henotcpt`, 3 `heclottva`,4  `heclotperiode`, "+

                                            " 5 `heclotexercice`,6  `hetransact`,7 `jxcleunik`,8 `henumpiece`,9 `hedatecreation`,10 `hedatemouv`,11 `hevaleur`," +

                                            " 12 `hecodetva`,13  `hevaleurbase`,14  `hevaleurtva`, 15 `decleunik`, 16 `hedatedevise`, 17 `hevaleurdevise`,18 `helibelle`,"+

                                            "19 `drcleunik`,20 `lignecleunik`, 21 `sn_cleunik`, 22 `ctprocleunik`, 23 `typeintervenantcleunik`, 24 `intervenantcleunik`,"+

                                            "25 `ce_cleunik`,26  `cate_cleunik`, 27 `hedossiercourant`, 28 `hetypeligne`, 29 `urcleunik`)"+

                                            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?,"+

                                            " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";  */ 
       String numpiece="";
       JournalCompta_T obj=null;
       check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
      // numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
       obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
       //m_config.setNumnc(numpiece);
         PreparedStatement pstmt;
         //pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE hetypeligne!='P' AND hetypeligne!='CP' AND  hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt.setLong(1,cledossier);
         ResultSet result=pstmt.executeQuery();
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCO' WHERE  hetypeligne='NC'  AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCOB' WHERE  hetypeligne='NCB' AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
         pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hetypeligne ='NCOT' WHERE  hetypeligne='NCT' AND drcleunik =?");
         pstmt.setLong(1,cledossier);
         pstmt.execute();        
        // pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hedossiercourant ='N' WHERE hedossiercourant='O' AND hetypeligne!='P' AND hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='CP' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND drcleunik =?");
        // pstmt=m_config.getCon().prepareStatement("UPDATE historique2 set hedossiercourant ='N' WHERE hedossiercourant='O' AND (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND drcleunik =?");
        // pstmt.setLong(1,cledossier);
       //  pstmt.execute();         
      //   result.beforeFirst();
         ArrayList array=new ArrayList();
         long transac=Check.getTransaction2(m_config.getCon(),m_config.getM_serveur().getTransactSyn(),check.getTmpool(),m_config.getM_serveur());
         while (result.next()){
           I_ligneComptable tmp= new I_ligneComptable(   
                                                                    result.getObject(2),result.getObject(3),result.getObject(4),result.getObject(5),
                                                                    result.getObject(6),result.getObject(7),result.getObject(8),
                                                                    result.getObject(9),result.getObject(10),result.getObject(11),
                                                                    result.getObject(12),result.getObject(13),result.getObject(14),result.getObject(15),result.getObject(16),
                                                                    result.getObject(17),result.getObject(18),result.getObject(19),result.getObject(20),result.getObject(21),
                                                                    result.getObject(22),result.getObject(23),result.getObject(24),result.getObject(25),result.getObject(26),
                                                                    result.getObject(27),result.getObject(28),result.getObject(29),result.getObject(30),result.getObject(31),
                                                                    result.getObject(32),result.getObject(33),result.getObject(34),result.getObject(35),result.getObject(36),
                                                                    result.getObject(37),result.getObject(38),result.getObject(39),result.getObject(40),result.getObject(41),
                                                                    result.getObject(42),result.getObject(45));
           array.add(tmp);                    
        //  insertLigneNoteCrédit(tmp,con,pstmt);
         }
       for(int j=0;j<array.size();j++){
          /*  "INSERT INTO `historique2` ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik2`   ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, ? , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?)";*/
           String nc="NCB";
         /*  if(j!=0){
               nc="NC";
           }
           else{
               check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
               numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
               obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
               m_config.setNumnc(numpiece);  
           }*/
           NcCompta.I_ligneComptable ligne=(NcCompta.I_ligneComptable)array.get(j); 
            if(j!=0){
               nc="NC";
           }
           else{ 
               if(ligne.m_hevaleur.doubleValue()!=0d){
              //  check.checkJournalt(CheckCompteCentral.CLIENT,ParamComptable.JOURNAL_NCVENTE);
                numpiece=m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,m_config.getM_urcleunik(),m_config.getEecleunik());
               // obj=m_config.getM_serveur().renvParamCompta(urcleunik).getGlobalJournal(m_config.getCle2(),ParamComptableInterface.JOURNAL_NCVENTE,0);
                m_config.setNumnc(numpiece);  
               }
               else{
                   
                            numpiece=new Integer(0).toString();
               }
           }
           setSoldeValue(true,ligne);
           String datetmp=CalculDate.getTodayDate().toString();
           pstmt=m_config.getCon().prepareStatement(INSERT_LIGNE);
           pstmt.setInt(1,GetData.getCompteData(""+urcleunik).getPeriode()); //heperiode` 
           pstmt.setInt(2,ligne.m_henotcpt.intValue()); //henotcpt
           pstmt.setInt(3,ligne.m_heclottva.intValue()); //heclottva
           pstmt.setInt(4,ligne.m_heclotperiode.intValue()); //heclotperiode
           pstmt.setInt(5,ligne.m_heclotexercice.intValue()); //heclotexercice
           pstmt.setInt(6,new Long(transac).intValue()); //hetransact
           pstmt.setLong(7,obj.getJota_cleunik()); //jxcleunik
           pstmt.setInt(8,Integer.parseInt(numpiece)); //henumpiece
           pstmt.setString(9,datetmp); //hedatecreation
           pstmt.setString(10,datetmp);  //`hedatemouv     
           pstmt.setInt(11,ligne.m_ce_cleunik_cent.intValue());  //`hedatemouv     
           pstmt.setInt(12,ligne.m_tva_cleunik.intValue());  //`hedatemouv     
           pstmt.setInt(13,ligne.m_ce_cleunik2.intValue());  //`hedatemouv     
           pstmt.setString(14,ligne.m_ce_cleunik);  //`hedatemouv     
           pstmt.setDouble(15,ligne.m_hevaleur.doubleValue()); //`hevaleur
           pstmt.setFloat(16,ligne.m_hecodetva.floatValue()); //hecodetva
           pstmt.setDouble(17,ligne.m_heValeurBase.doubleValue()); //hevaleurbase
           pstmt.setDouble(18,ligne.m_heValeurTva.doubleValue()); //`hevaleurtva
           pstmt.setDouble(19,ligne.m_decleunik.intValue()); //`decleunik`
           pstmt.setString(20,ligne.m_hedatedevise); //`hedatedevise`
           pstmt.setDouble(21,ligne.m_hevaleurdevise.doubleValue()); //hevaleurdevise
           pstmt.setString(22,ligne.m_helibelle); //helibelle`
           pstmt.setLong(23,ligne.m_drCleunik.longValue()); //drcleunik` 
           pstmt.setLong(24,ligne.m_lecleunik.longValue()); //lignecleunik`
           pstmt.setLong(25,ligne.m_sn_cleunik.longValue()); //`sn_cleunik`
           pstmt.setInt(26,ligne.m_ct_cleunik.intValue()); //ctprocleunik`
           pstmt.setInt(27,ligne.m_typeIntervenant.intValue()); //typeintervenantcleunik
           pstmt.setLong(28,ligne.m_intervenantCleunik.intValue()); //`intervenantcleunik
           pstmt.setInt(29,ligne.m_cate_cleunik.intValue()); //cate_cleunik
           pstmt.setString(30,"N"); //hedossiercourant`
           if(ligne.m_he_dossier_lignetype.equals("TVAV"))
               nc="NCT";
           pstmt.setString(31,nc); //hetypeligne`
           pstmt.setInt(32,ligne.m_urcleunik.intValue()); //`urcleunik`
           pstmt.setInt(33,ligne.m_hetypePayement.intValue());
           pstmt.setString(34,ligne.m_helibellecompta);
           pstmt.setInt(35,ligne.m_pax.intValue());
           pstmt.setInt(36,ligne.m_quantite.intValue());
           pstmt.setFloat(37,ligne.m_pourcent.floatValue());
           pstmt.setDouble(38,ligne.m_hevaleur.doubleValue());
           pstmt.setInt(39,ligne.m_gn_cleunik.intValue());
           pstmt.setInt(40,ligne.m_typegrpdec.intValue());
           pstmt.setInt(41,ligne.m_exle_cleunik.intValue());
           pstmt.setString(42, ligne.m_debcre);
           pstmt.execute();  
       }
    }
    
    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {

    }

private void setSoldeValue(boolean nc,NcCompta.I_ligneComptable ligne){    

          double value=0;

          value=ligne.m_heValeurBase.doubleValue();

          if(nc==true){

               if(ligne.m_he_dossier_lignetype.equals("B")){

                  GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.CLIENT,ligne.m_intervenantCleunik.intValue(),-ligne.m_hevaleur.doubleValue(),"C",null)); 
                  GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),-ligne.m_hevaleur.doubleValue(),"C",null)); 

               }

               else{

                  if(value<0){

                     GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),Math.abs(value),"C",null));  

                  }

                  else{

                       GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),Math.abs(value),"D",null));                 

                  }

               }



          }else{

               if(ligne.m_he_dossier_lignetype.equals("B")){

                  GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.CLIENT,ligne.m_intervenantCleunik.intValue(),-ligne.m_hevaleur.doubleValue(),"D",null)); 

                  GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),-ligne.m_hevaleur.doubleValue(),"D",null)); 

               }

               else{

                    if(value<0){

                        GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),value,"D",null));  

                    }

                    else{

                        GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,ligne.m_ce_cleunik2.intValue(),value,"C",null));                 

                }

               }                      

           }

     

 }



 public class I_ligneComptable{


                             //      1              2               3                 4                     5                   6                  7                    8                   9                  11
 public I_ligneComptable(Object heperiode,Object henotcpt,Object heclottva,Object heclotperiode,Object heclotexercice,Object hetransact, Object jxcleunik, Object henumpiece, Object hedatecreation,Object hedatemouv ,Object ce_cleunik_cent,Object tva_cleunik,Object ce_cleunik2,Object ce_cleunik,Object hevaleur,Object hecodetva,Object heValeurBase,Object heValeurTva,Object decleunik,Object hedatedevise,Object hevaleurdevise,Object helibele,Object drCleunik,

                                Object lecleunik,Object sn_cleunik,Object ct_cleunik,Object typeIntervenant,Object intervenantCleunik,

                                Object cate_cleunik,Object hedossiercourant,Object he_dossier_lignetype,Object urcleunik,Object  hetypePayement,Object helibellecompta,Object pax,Object quantite,Object pourcent,Object hevaleuru,Object gn_cleunik, Object typegrpdec,Object exle_cleunik,Object debcre){                                            

           m_ce_cleunik_cent=(Integer)ce_cleunik_cent;

           m_tva_cleunik=(Integer)tva_cleunik;

           m_ce_cleunik2=(Integer)ce_cleunik2;

           m_heperiode=heperiode.toString();      

           m_heclottva=(Integer)heclottva;

           m_heclotperiode=(Integer)heclotperiode;

           m_heclotexercice=(Integer)heclotexercice;

           m_hetransact=(Integer)hetransact;

           m_jxcleunik=(Integer)jxcleunik;

           m_henumpiece=(Long)henumpiece;
           if(hedatecreation!=null){
           m_hedatecreation=hedatecreation.toString();
           }
           else {
             m_hedatecreation="";  
           }
           if(hedatemouv!=null){
            m_hedatemouv= hedatemouv.toString() ;
           }else{
               m_hedatemouv="";
           }


           m_henotcpt=(Integer)henotcpt;

           m_hevaleur=new Double(-((Double)hevaleur).doubleValue());

           m_hecodetva=(Float)hecodetva;

           m_heValeurBase=new Double(-((Double)heValeurBase).doubleValue());

           m_heValeurTva=new Double(-((Double)heValeurTva).doubleValue());

           m_decleunik=(Integer)decleunik;
           //m_hedatedevise
           if(hedatedevise!=null){
            m_hedatedevise=hedatedevise.toString();
           }
            else{
               m_hedatedevise="";
           }

           m_hevaleurdevise=(Double)hevaleurdevise;

           if(helibele!=null)

             m_helibelle=helibele.toString();

           else m_helibelle="";

           m_drCleunik=(Long)drCleunik;

           m_lecleunik=(Long)lecleunik;

           m_sn_cleunik=(Long)sn_cleunik;

           m_ct_cleunik=(Integer)ct_cleunik;

           m_typeIntervenant=(Integer)typeIntervenant;

           m_intervenantCleunik=(Integer)intervenantCleunik;

           m_ce_cleunik=ce_cleunik.toString();

           m_cate_cleunik=(Integer)cate_cleunik;

           m_he_dossier_lignetype=he_dossier_lignetype.toString();

           m_he_dossier_courant=hedossiercourant.toString();

           m_urcleunik=(Integer)urcleunik;

           m_hetypePayement=(Integer)hetypePayement;

           m_helibellecompta=helibellecompta.toString();

           m_pax=(Integer)pax;

           m_quantite=(Integer)quantite;

           m_pourcent=(Float)pourcent;

           m_hevaleuru=(Double)hevaleuru;

           m_gn_cleunik=(Integer)gn_cleunik;

           m_typegrpdec=(Integer)typegrpdec;

           m_exle_cleunik=(Integer)exle_cleunik;    
           m_debcre=debcre.toString().equals("D")?"C":"D";

         } 

        public Integer m_ce_cleunik_cent;

        public Integer m_tva_cleunik;

        public Integer m_ce_cleunik2;      

        public String m_heperiode;

        public Integer m_henotcpt;

        public Integer m_heclottva;

        public Integer m_heclotperiode;

        public Integer m_heclotexercice;

        public Integer  m_hetransact;

        public Integer m_jxcleunik;

        public Long m_henumpiece;

        public String m_hedatecreation;

        public String m_hedatemouv;

        public Double m_hevaleur;

        public Float m_hecodetva;

        public Double m_heValeurBase;

        public Double m_heValeurTva;

        public Integer m_decleunik;

        public String m_hedatedevise;

        public Double m_hevaleurdevise;

        public String m_helibelle;

        public Long m_drCleunik;

        public Long m_lecleunik;

        public Long m_sn_cleunik;

        public Integer m_ct_cleunik;

        public Integer m_typeIntervenant;

        public Integer m_intervenantCleunik;   

        public String m_ce_cleunik;

        public Integer m_cate_cleunik;

        public String  m_he_dossier_courant;

        public String m_he_dossier_lignetype;

        public Integer m_urcleunik;

        public Integer m_hetypePayement;

        public String m_helibellecompta;

        public Integer m_pax;

        public Integer m_quantite;

        public Float m_pourcent;

        public Double m_hevaleuru;

        public Integer m_gn_cleunik;

        public Integer m_typegrpdec;

        public Integer m_exle_cleunik;     
        public String m_debcre;   



     } 

 public void setSolde(Connection con,long cledossier)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

    // PreparedStatement pstmt=con.prepareStatement("SELECT  *  FROM historique2 WHERE hetypeligne!='P' AND hetypeligne!='CP' AND hetypeligne!='A' AND hetypeligne!='ACP' AND  hetypeligne!='BP' AND hetypeligne!='CPC' AND hedossiercourant='O' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND drcleunik=? ORDER BY hecleunik");
     PreparedStatement pstmt=con.prepareStatement("SELECT  *  FROM historique2 WHERE (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
     pstmt.setLong(1,cledossier);
     ResultSet result=pstmt.executeQuery();
     while (result.next()){
           I_ligneComptable tmp= new I_ligneComptable(   
                                                                    result.getObject(2),result.getObject(3),result.getObject(4),result.getObject(5),
                                                                    result.getObject(6),result.getObject(7),result.getObject(8),

                                                                    result.getObject(9),result.getObject(10),result.getObject(11),

                                                                    result.getObject(12),result.getObject(13),result.getObject(14),result.getObject(15),result.getObject(16),

                                                                    result.getObject(17),result.getObject(18),result.getObject(19),result.getObject(20),result.getObject(21),

                                                                    result.getObject(22),result.getObject(23),result.getObject(24),result.getObject(25),result.getObject(26),

                                                                    result.getObject(27),result.getObject(28),result.getObject(29),result.getObject(30),result.getObject(31),

                                                                    result.getObject(32),result.getObject(33),result.getObject(34),result.getObject(35),result.getObject(36),

                                                                    result.getObject(37),result.getObject(38),result.getObject(39),result.getObject(40),result.getObject(41),

                                                                    result.getObject(42),result.getObject(45));

            setSoldeValue(false,tmp);

                              

        //  insertLigneNoteCrédit(tmp,con,pstmt);

         }

     

 }



}



