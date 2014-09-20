/*
 * AuthorizationFailedException.java
 *
 * Created on 8 juillet 2002, 11:00
 */

package srcastra.astra.sys.rmi.Exception;

/**
 *
 * @author  David
 * @version 1.0 Mneroto 
 */
public class AuthorizationFailedException extends java.rmi.RemoteException {
             public AuthorizationFailedException() 
             {
                 super();
             }
             public AuthorizationFailedException(String s) 
             {
                 super(s);
             }
             public AuthorizationFailedException(String s, Throwable ex)
             {
                 super(s,ex);
             }
}
