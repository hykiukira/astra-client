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
public class DiversRequete extends AbstractRequete{
    
    /** Creates a new instance of MemoRequete */
    public DiversRequete(int numberOfElement) {
        super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM divers  WHERE divs_cleunik  =?";
    }
    
    public String delete2() {
        return "DELETE FROM divers_traduction  WHERE divs_cleunik  =?";
    }
    
    public String init() {
        return "SELECT * FROM divers_traduction  WHERE cate_prod =? ORDER BY divs_cleunik ,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO divers (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO divers_traduction(divs_cleunik ,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE divers_traduction set Traduction=? WHERE divs_cleunik  =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
       return new String[]{ "SELECT at_mode_paiement FROM avion_ticket  WHERE at_mode_paiement=?",
                            "SELECT hetypepayement FROM historique2 WHERE hetypepayement=?",
                            "SELECT dr_typbooking FROM dossier WHERE dr_typbooking =?"};
    }
    
}
