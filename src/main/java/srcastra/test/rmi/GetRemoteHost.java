/*
 * GetRemoteHost.java
 *
 * Created on 22 juin 2004, 11:27
 */

package srcastra.test.rmi;
import java.rmi.server.*;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.*;
import java.sql.*;
/**
 *
 * @author  Administrateur
 */
public class GetRemoteHost  extends java.rmi.server.UnicastRemoteObject implements remoteIp{
    
    /** Creates a new instance of GetRemoteHost */
    public GetRemoteHost()  throws RemoteException,MalformedURLException{
        super(10000);
        registerToRegistry("test", this,true);
    }
     public static void registerToRegistry(String name, Remote obj, boolean create) throws RemoteException, MalformedURLException{
        if (name == null) throw new IllegalArgumentException("registration name can not be null");
        try {
            Naming.rebind(name, obj);
          
        } catch (RemoteException ex){
            try{
              Registry  r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
              r.rebind(name, obj);
               
            }catch(Exception e){
              e.printStackTrace();  
              System.out.println("et oui fallait si attendre");
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new GetRemoteHost();
        }catch(RemoteException rn){
            rn.printStackTrace();
        
        }catch(MalformedURLException mn){
            mn.printStackTrace();
        }
        // TODO code application logic here
    }
    
    public void setIp(String name) throws RemoteException {
  /*      try{
        System.out.println("client hoste "+ getClientHost());
        Connection con=connectDb("root","XkLm2000","centrum","195.144.69.10",3306);
        String requete="INSERT INTO ip 
        PreparedStatement pstmt=con.prepareStatement(requete);
        ResultSet result
        
        }catch(ServerNotActiveException sn){
            sn.printStackTrace();
            
        }*/
    }    
    
    public void getIp() throws RemoteException {
    }
     private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try {
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)
                Class.forName(jdbcDriverClassName) ;
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();           
            }
           
            catch(Exception e2) {
                e2.printStackTrace();              
            }       // Add your handling code here:
            return tmpcon;
        }
    
}
