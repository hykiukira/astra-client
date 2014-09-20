/*
 * CheckAchat.java
 *
 * Created on 22 avril 2004, 16:28
 */

package srcastra.astra.sys.export;

/**
 *
 * @author  Administrateur
 */
public class CheckAchat implements ChecContrePartie{
    
    /** Creates a new instance of CheckAchat */
    public CheckAchat() {
    }
    
    public boolean isContrePartie(Object[] tmptab) {
        if(tmptab[34].toString().equals("ACP") || tmptab[34].toString().equals("NCA")) return true;
            return false;
    }
    
}
