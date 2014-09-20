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
public class RegimeTvaRequete extends AbstractRequete{
    
    /** Creates a new instance of SupreducLibelleRequete */
    public RegimeTvaRequete(int numberOfElement) {
       super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM regimetva WHERE regtva_cleunik =?";
    }
    
    public String delete2() {
        return "DELETE FROM regtva_traduction  WHERE regtva_cleunik =?";
    }
    
    public String init() {
        return "SELECT * FROM regtva_traduction  WHERE cate_prod =? ORDER BY regtva_cleunik,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO regimetva (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO regtva_traduction(regtva_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE regtva_traduction set Traduction=? WHERE regtva_cleunik =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
         return new String []{"SELECT frtvaregime FROM fournisseur WHERE  frtvaregime =?","SELECT cstvaregime FROM clients WHERE cstvaregime =?"};
         
    }
    
}
