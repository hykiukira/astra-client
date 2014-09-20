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
public class ImpressionRequete extends AbstractRequete{
    
    /** Creates a new instance of MemoRequete */
    public ImpressionRequete(int numberOfElement) {
        super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM impression   WHERE impn_cleunik  =?";
    }
    
    public String delete2() {
        return "DELETE FROM  impression_traduction  WHERE impn_cleunik  =?";
    }
    
    public String init() {
        return "SELECT * FROM impression_traduction   WHERE cate_prod =? ORDER BY impn_cleunik,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO impression  (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO impression_traduction (impn_cleunik ,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE impression_traduction  set Traduction=? WHERE impn_cleunik  =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
        return null;
    }
    
}
