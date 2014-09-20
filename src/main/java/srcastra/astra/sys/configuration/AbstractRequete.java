/*
 * AbstractRequete.java
 *
 * Created on 10 mars 2003, 11:18
 */

package srcastra.astra.sys.configuration;

/**
 *
 * @author  Thomas
 */
public abstract class AbstractRequete {
    
    /** Creates a new instance of AbstractRequete */
    public AbstractRequete(int numberOfElement) {
        this.numberOfElement=numberOfElement;
    }
    public abstract String delete1();
    public abstract String delete2();
    public abstract String insert1();
    public abstract String insert2();
    public abstract String modify1();
    public abstract String modify2();
    public abstract String[] checkBeforeDelete();
    public abstract String init();
    
    /** Getter for property numberOfElement.
     * @return Value of property numberOfElement.
     */
    public int getNumberOfElement() {
        return numberOfElement;
    }
    
    /** Setter for property numberOfElement.
     * @param numberOfElement New value of property numberOfElement.
     */
 
    public final static int SUP_RECUC=1;
    public final static int MEMO=2;
    public final static int IMPRESSION=3;
    public final static int TVA_TYPE=4;
    public final static int TVA_REGIME=5;
    public final static int FACTURE=6;
    public final static int TYPE_PAYEMENT=7;
    public final static int DECSRIPTIF_LOG=8;
    public final static int DIVERS=9;
    public final static int LANGUESYS=10;
    
    int numberOfElement;
    
}
