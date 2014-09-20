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
public class MemoRequete extends AbstractRequete{
    
    /** Creates a new instance of MemoRequete */
    public MemoRequete(int numberOfElement) {
        super(numberOfElement);
    }
    
    public String delete1() {
        return "DELETE FROM aidedecisionmemo  WHERE aid_cleunik =?";
    }
    
    public String delete2() {
        return "DELETE FROM aidedecision_traduction_memo  WHERE aid_cleunik =?";
    }
    
    public String init() {
        return "SELECT * FROM aidedecision_traduction_memo  WHERE cate_prod =? ORDER BY aid_cleunik,lmcleunik";
    }
    
    public String insert1() {
        return "INSERT INTO aidedecisionmemo (cate_prod) VALUES (?)";
    }
    
    public String insert2() {
        return "INSERT INTO aidedecision_traduction_memo(aid_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    }
    
    public String modify1() {
        return "UPDATE aidedecision_traduction_memo set Traduction=? WHERE aid_cleunik =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "";
    }
    
    public String[] checkBeforeDelete() {
        return null;
    }
    
}
