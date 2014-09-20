/*
 * remoteIp.java
 *
 * Created on 22 juin 2004, 11:34
 */

package srcastra.test.rmi;
import java.rmi.*;
/**
 *
 * @author  Administrateur
 */
public interface remoteIp  extends java.rmi.Remote{
    public void setIp(String name) throws RemoteException;
    public void getIp() throws RemoteException;
}
