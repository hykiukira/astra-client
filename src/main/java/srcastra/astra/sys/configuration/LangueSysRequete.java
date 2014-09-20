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
public class LangueSysRequete extends AbstractRequete{
    
    /** Creates a new instance of MemoRequete */
    public LangueSysRequete(int numberOfElement) {
        super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM  languesys  WHERE lans_cleunik  =?";
    }
    
    public String delete2() {
        return "DELETE FROM languesys_traduction  WHERE lans_cleunik  =?";
    }
    
    public String init() {
        return "SELECT * FROM alanguesys_traduction  WHERE cate_prod =? ORDER BY lans_cleunik ,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO  languesys (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO languesys_traduction(lans_cleunik ,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE languesys_traduction set Traduction=? WHERE lans_cleunik  =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
        return null;
    }
    
}
