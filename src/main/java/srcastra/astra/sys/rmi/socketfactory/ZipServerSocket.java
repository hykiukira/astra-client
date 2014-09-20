/* * ZipServerSocket.java * * Created on 22 mars 2002, 10:38 */package srcastra.astra.sys.rmi.socketfactory;
/** * * @author  thomas * @version  */import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
public class ZipServerSocket extends ServerSocket implements java.io.Serializable{ 
    int m_port;
    public ZipServerSocket(int port) throws IOException {       
        super(port); 
        
    }   
  
    public Socket  accept() throws IOException {     
        Socket socket = new ZipSocket();    
        implAccept(socket);   
        System.out.println("ok je renvois le socket");
        return socket; 
    }
}