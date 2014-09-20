/*
 * InvalidSignalitiqueOperation.java
 *
 * Created on 22 août 2002, 11:17
 */

package srcastra.astra.sys.rmi.Exception;

/**
 *
 * @author  David
 */
public class InvalidSignalitiqueOperation extends java.rmi.RemoteException {
    
    /**
     * Creates a new instance of <code>InvalidSignalitiqueOperation</code> without detail message.
     */
    public InvalidSignalitiqueOperation() {
    }
    
    
    /**
     * Constructs an instance of <code>InvalidSignalitiqueOperation</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidSignalitiqueOperation(String msg) {
        super(msg);
    }
}
