/* * ZipServerSocketFactory.java * * Created on 22 mars 2002, 10:45 */
package srcastra.astra.sys.rmi.socketfactory;
/** * * @author  thomas * @version  */
import java.io.IOException; 
import java.io.Serializable; 
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;  
public class ZipServerSocketFactory    implements RMIServerSocketFactory, Serializable { 
    
//    public ZipServerSocketFactory(int port){
  //      this.m_port=port;
   // }
    public ServerSocket createServerSocket(int port)        {          
      try{
         
    ZipServerSocket server = new ZipServerSocket(port);           
    return server;    
     }catch(IOException in){
         in.printStackTrace();
     }
    return null;
    } 
}/* * ZipServerSocket.java * * Created on 22 mars 2002, 10:38 */