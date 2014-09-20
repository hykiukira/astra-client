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
public class SupreducLibelleRequete extends AbstractRequete{
    
    /** Creates a new instance of SupreducLibelleRequete */
    public SupreducLibelleRequete(int numberOfElement) {
       super(numberOfElement);
    }
     
    public String delete1() {
        return "DELETE FROM aidedecision WHERE aid_cleunik =?";
    }
    
    public String delete2() {
        return "DELETE FROM aidedecision_traduction  WHERE aid_cleunik =?";
    }
    
    public String init() {
        return "SELECT at.aid_cleunik, at.cate_prod, at.lmcleunik, at.Traduction, a.ce_cleunik, a.aion_sup,a.aion_def,tva_cleunik,tva_compta,tva_inside FROM aidedecision_traduction at, aidedecision a WHERE a.cate_prod = ? AND at.aid_cleunik = a.aid_cleunik ORDER  BY aid_cleunik, lmcleunik";
    } 
    
    public String insert1() {
        return "INSERT INTO aidedecision (cate_prod,ce_cleunik,aion_sup, aion_def,tva_cleunik,tva_compta,tva_inside) VALUES (?,?,?,?,?,?,?)";
    }
    
    public String insert2() {
        return "INSERT INTO aidedecision_traduction(aid_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)";
    } 
    
    public String modify1() {
        return "UPDATE aidedecision_traduction set Traduction=? WHERE aid_cleunik =? AND lmcleunik=?";
    }
    
    public String modify2() {
        return "UPDATE aidedecision  set ce_cleunik=?,aion_sup=?, aion_def =?,tva_cleunik=?,tva_compta=?,tva_inside=? WHERE aid_cleunik =?";
    }
    
    public String[] checkBeforeDelete() {
        return null;
    }
    
}
