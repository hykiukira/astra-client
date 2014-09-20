/* * ZipServerSocketFactory.java * * Created on 22 mars 2002, 10:45 */

package srcastra.astra.sys.rmi.socketfactory;

/** * * @author  thomas * @version  */

import java.io.IOException; 

import java.io.Serializable; 

import java.net.ServerSocket;

import java.rmi.server.RMIServerSocketFactory;  

public class SSLServerSocketFactory implements RMIServerSocketFactory, Serializable { 

    

//    public ZipServerSocketFactory(int port){

  //      this.m_port=port;

   // }
    static SSLServerSocketFactory serveur;
    public static SSLServerSocketFactory getServeurFactory(){
        if(serveur==null)
            serveur=new SSLServerSocketFactory();
        return serveur; 
    }
    public ServerSocket createServerSocket(int port)        {          

      try{

         
     ServerSocket server=new ServerSocket(port);
    //ZipServerSocket server = new ZipServerSocket(port);           

    return server;    

     }catch(IOException in){

         in.printStackTrace();

     }

    return null;

    } 
   // public boolean equals(Object obj) {
	//return getClass() == obj.getClass() ;
    //}

}/* * ZipServerSocket.java * * Created on 22 mars 2002, 10:38 */