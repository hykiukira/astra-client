/*
 * FormatTexte.java
 *
 * Created on 16 octobre 2003, 10:44
 */

package srcastra.astra.gui.modules.printing.produit.produitInfo;

/**
 *
 * @author  Thomas
 */
public class FormatTexte {
    
    /** Creates a new instance of FormatTexte */
    public FormatTexte() {
        
    }
    public static String format(String toformat){
       if(toformat==null)
           return "";
       if(toformat.length()>0){
       String first=toformat.substring(0,1).toUpperCase();
       String second=toformat.substring(1).toLowerCase();
       return first+second;
       }
       else return toformat;
    }
    
}
