/*
 * AtomicOperationFailed.java
 *
 * Created on 23 août 2002, 9:57
 */

package srcastra.astra.sys.rmi.Exception;

/**
 *
 * @author  David
 */
public class AtomicOperationFailed extends java.rmi.RemoteException {
    
    /**
     * Creates a new instance of <code>AtomicOperationFailed</code> without detail message.
     */
    public AtomicOperationFailed() {
    }
    
    
    /**
     * Constructs an instance of <code>AtomicOperationFailed</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AtomicOperationFailed(String msg) {
        super(msg);
    }
}
