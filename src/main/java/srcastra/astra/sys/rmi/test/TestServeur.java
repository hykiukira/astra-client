/*
 * TestServeur.java
 *
 * Created on 2 octobre 2003, 15:43
 */

package srcastra.astra.sys.rmi.test;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import srcastra.astra.sys.*;
import java.rmi.registry.*;
import srcastra.astra.sys.rmi.socketfactory.*;
import java.io.*;


/**
 *
 * @author  Thomas
 */
public class TestServeur extends java.rmi.server.UnicastRemoteObject implements TestServeurRmiInterface{
    
    /** Creates a new instance of TestServeur */
    public TestServeur(int port) throws RemoteException{
           super(port,new ZipClientSocketFactory(),new ZipServerSocketFactory());    
    }
    public static void main(String [] args){
       try {
            TestServeur obj = new TestServeur(38000);
          //  RemoteStub stub=UnicastRemoteObject.exportObject(obj);
            registerToRegistry("testserveur", obj, true);
            try{
                InetAddress myadress= InetAddress.getLocalHost();
                System.out.println("Mon adresse est :"+myadress);
            }catch(java.net.UnknownHostException he)
            {
               he.printStackTrace(); 
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }   
    }
   public static void registerToRegistry(String name, java.rmi.Remote obj, boolean create) throws RemoteException, MalformedURLException{
        if (name == null) throw new IllegalArgumentException("registration name can not be null");
        try {
            Naming.rebind(name, obj);
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"objet lié au registre");
        } catch (RemoteException ex){
            if (create) {
                System.out.println("numero de registre"+Registry.REGISTRY_PORT);
                Registry r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                r.rebind(name, obj);
                Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"Nouveau registre créé, objet lié au registre");
            } else throw ex;



        }
    }    
   
   public String getTest() throws java.rmi.RemoteException{
       return "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP";
   }
   
}
