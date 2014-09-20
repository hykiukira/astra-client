/*
 * SupreducLibelleRequete.java
 *
 * Created on 10 mars 2003, 11:21
 */

package srcastra.astra.sys.configuration;

/**
 *
 * @author  Thomas
 */
public class TypeTvaRequete extends AbstractRequete{
    
    /** Creates a new instance of SupreducLibelleRequete */
    public TypeTvaRequete(int numberOfElement) {
       super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM typetva WHERE typtva_cleunik =?";
    }
    
    public String delete2() {
        return "DELETE FROM typetva_traduction  WHERE typtva_cleunik =?";
    } 
    
    public String init() {
        return "SELECT * FROM typetva_traduction  WHERE cate_prod =? ORDER BY typtva_cleunik,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO typetva (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO typetva_traduction(typtva_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE typetva_traduction set Traduction=? WHERE typtva_cleunik =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
        return new String[]{"SELECT frtvatype FROM fournisseur  WHERE frtvatype=?","SELECT cstvatype FROM clients WHERE cstvatype =?"};  
    }
    
}
