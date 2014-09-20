/*
 * ClientResaux_T.java
 *
 * Created on 12 december 2002, 15:41
 */

package srcastra.astra.sys.reseaux;

/**
 *
 * @author  Thomas
 */
public class ClientResaux_T implements java.io.Serializable{
    
    /** Creates a new instance of ClientResaux_T */
    public ClientResaux_T(int cleunik) {
        this.urcleunik=cleunik;
    }
    public void afficheClient(){
     System.out.println("{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}client id= "+this.urcleunik);   
    }
    
    /** Getter for property urcleunik.
     * @return Value of property urcleunik.
     */
    public int getUrcleunik() {
        return urcleunik;
    }
    
    /** Setter for property urcleunik.
     * @param urcleunik New value of property urcleunik.
     */
    public void setUrcleunik(int urcleunik) {
        this.urcleunik = urcleunik;
    }
    
    int urcleunik;
    
}
