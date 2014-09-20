/*
 * GrpRetValue.java
 *
 * Created on 26 février 2003, 11:11
 */

package srcastra.astra.sys.rmi.groupe_dec;
import srcastra.astra.sys.classetransfert.Grpdecision_T;
/**
 *
 * @author  Thomas
 */
public class GrpRetValue {
    
    /** Creates a new instance of GrpRetValue */
    public GrpRetValue(Grpdecision_T base,Grpdecision_T own) {
        this.base=base;
        this.own=own;
    }
    
    /** Getter for property base.
     * @return Value of property base.
     */
    public Grpdecision_T getBase() {
        return base;
    }
    
    /** Setter for property base.
     * @param base New value of property base.
     */
    
    
    /** Getter for property own.
     * @return Value of property own.
     */
    public Grpdecision_T getOwn() {
        return own;
    }
    
    /** Setter for property own.
     * @param own New value of property own.
     */
   
    
    Grpdecision_T base;
    Grpdecision_T own;
    
}
