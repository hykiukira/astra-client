/*
 * TvaReturnValue.java
 *
 * Created on 7 février 2003, 14:34
 */

package srcastra.astra.sys.compta;

/**
 *
 * @author  Thomas
 */
public class TvaReturnValue {
    
    /** Creates a new instance of TvaReturnValue */
    public TvaReturnValue() {
    }
    public double montant_Tva;
    public double montant_TvaCompris;
    public double montantHtva;
    public double prixu;
    
    public void checkMe(){
     System.out.println("tesstttttttttttttttttttttttttttttttttTVAAAAAAAAAAAAAAAAAAAAAa");
     System.out.println("tva: "+montant_Tva);   
     System.out.println("HTVA: "+montantHtva);   
     System.out.println("MTVAC: "+montant_TvaCompris);   
    }
    
}
