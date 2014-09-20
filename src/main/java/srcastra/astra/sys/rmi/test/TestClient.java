/*
 * TestClient.java
 *
 * Created on 2 octobre 2003, 15:43
 */

package srcastra.astra.sys.rmi.test;
import java.rmi.*;
import java.net.*;
import srcastra.astra.sys.*;
import java.rmi.registry.*;
/**
 *
 * @author  Thomas
 */
public class TestClient {
    
    /** Creates a new instance of TestClient */
    TestServeurRmiInterface serveur;
    public TestClient() throws RemoteException,NotBoundException{
        connect();
        String test=serveur.getTest();
        System.out.println(test);
    }
    public void connect() throws RemoteException,NotBoundException{
            Registry repertoire = (Registry) LocateRegistry.getRegistry("192.168.1.53");
            serveur = (TestServeurRmiInterface) repertoire.lookup("testserveur");
            System.out.println("ok connected");
           
    }
    public static void main(String [] args){
        try{
       new TestClient();   
        }catch(java.rmi.RemoteException rn){
            rn.printStackTrace();
             
        }
        catch(java.rmi.NotBoundException nn){
            nn.printStackTrace();
        }
        catch(java.io.IOException in){
            in.printStackTrace();
        }
    }
    
}
