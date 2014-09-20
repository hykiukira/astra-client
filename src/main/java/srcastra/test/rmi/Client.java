/*
 * Client.java
 *
 * Created on 22 juin 2004, 11:27
 */

package srcastra.test.rmi;
import java.rmi.*;
import srcastra.astra.sys.rmi.*;
import javax.swing.*;
import java.rmi.registry.*;
/**
 *
 * @author  Administrateur
 */
public class Client {
    remoteIp remote;
    private Registry repertoire;
    /** Creates a new instance of Client */
    public Client() {
        try {
            repertoire = (Registry) LocateRegistry.getRegistry("localhost");
            remote = (remoteIp) repertoire.lookup("test");
           //remote.test();
            
        }
        catch (RemoteException re) {
            System.out.println("yoy yoy");
            re.printStackTrace();
           // ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
           // return false;
        }
        catch (NotBoundException nbe) {
            System.out.println("yoy yoy not bound");
            System.out.println(nbe);
            //return false;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Client();
        // TODO code application logic here
    }
    
}
