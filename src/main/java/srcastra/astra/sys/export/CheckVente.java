/*
 * CheckVente.java
 *
 * Created on 22 avril 2004, 16:25
 */

package srcastra.astra.sys.export;

/**
 *
 * @author  Administrateur
 */
public class CheckVente implements ChecContrePartie{
    
    /** Creates a new instance of CheckVente */
    public CheckVente() {
    }
    
    public boolean isContrePartie(Object[] tmptab) {
         if(tmptab[34].toString().equals("NC") || tmptab[34].toString().equals("NCO") || tmptab[34].toString().equals("D")) return true;
         return false;
    }
    
}
