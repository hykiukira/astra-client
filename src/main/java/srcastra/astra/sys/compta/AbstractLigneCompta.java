/*



 * AbstractLigneCompta.java



 *



 * Created on 6 février 2003, 17:02



 */







package srcastra.astra.sys.compta;







/**



 *



 * @author  Thomas



 */



public class AbstractLigneCompta {



    



    /** Creates a new instance of AbstractLigneCompta */



 public AbstractLigneCompta() {



    }



 



 /** Getter for property m_config.



  * @return Value of property m_config.



  */



 public srcastra.astra.sys.compta.Configuration getM_config() {



     return m_config;



 }



 



 /** Setter for property m_config.



  * @param m_config New value of property m_config.



  */



 public void setM_config(srcastra.astra.sys.compta.Configuration m_config) {



     this.m_config = m_config;



 }



 



 public static final String INSERT_LIGNE=   "INSERT INTO `historique2` ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "

                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "

                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "

                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "

                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "

                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "

                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "

                                            +", `typegrpdec`,`exle_cleunik`,he_dc    ) VALUES (? , ? , ? , ? , ? , ? , ?, ?, ? , ?"

                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"

                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?)";

 public static final String UPDATE_LIGNE=  
 
             "UPDATE `historique2`  set `ce_cleunik_cent`=? , `tva_cleunik`=? , `ce_cleunik2`=? , `ce_cleunik`=? , `hevaleur`=? , `hecodetva`=? "

                                            +", `hevaleurbase`=? , `hevaleurtva`=? , `decleunik`=? , `hedatedevise`=? , `hevaleurdevise`=? , `helibelle`=? "                                          

                                            +", `intervenantcleunik`=? , `cate_cleunik`=? ,`urcleunik`=? "

                                            +", `hetypepayement`=? , `helibellecompta2`=? , `pax`=? , `quantite`=? , `pourcent`=? , `hevaleuru`=? , `gn_cleunik`=? "

                                            +"  WHERE drcleunik=? AND lignecleunik=? AND sn_cleunik=? AND ctprocleunik=? AND hedossiercourant='O' AND (hetypeligne='B' OR hetypeligne='D')";

 public static final String NOTE_CREDIT=  "UPDATE historique2 set hedossiercourant ='N' WHERE hedossiercourant='O' AND hetypeligne!='P' AND drcleunik =?";   
 protected Configuration m_config;



}



