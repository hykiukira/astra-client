/*
 * MemoRequete.java
 *
 * Created on 10 mars 2003, 11:28
 */

package srcastra.astra.sys.configuration;

/**
 *
 * @author  Thomas
 */
public class DescLogRequete extends AbstractRequete{
    
    /** Creates a new instance of MemoRequete */
    public DescLogRequete(int numberOfElement) {
        super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM descriptif_logement  WHERE dest_cleunik =?";
    }
    
    public String delete2() {
        return "DELETE FROM descriptif_logement_traduction  WHERE dest_cleunik =?";
    }
    
    public String init() {
        return "SELECT * FROM descriptif_logement_traduction  WHERE cate_prod =? ORDER BY dest_cleunik,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO descriptif_logement (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO descriptif_logement_traduction(dest_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE descriptif_logement_traduction set Traduction=? WHERE dest_cleunik =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
     return new String[]{   "SELECT brot_commodite FROM brochurelogement  WHERE brot_commodite=?",
                            "SELECT brot_xlit FROM brochurelogement  WHERE brot_xlit=?",
                            "SELECT brot_situation FROM brochurelogement  WHERE brot_situation=?",
                            "SELECT brot_vue FROM brochurelogement  WHERE brot_vue=?",
                            "SELECT brot_regime FROM brochurelogement  WHERE brot_regime=?", 
                            "SELECT hlt_xlit FROM hotellogement  WHERE hlt_xlit=?" ,
                            "SELECT hlt_commodite  FROM hotellogement  WHERE hlt_commodite =?" ,
                            "SELECT hlt_situation FROM hotellogement  WHERE hlt_situation=?" ,
                            "SELECT hlt_vue  FROM hotellogement  WHERE hlt_vue =?" ,
                            "SELECT hlt_regime  FROM hotellogement  WHERE hlt_regime =?" 
                        };
    }
    
}
