/* * ZipServerSocket.java * * Created on 22 mars 2002, 10:38 */
package srcastra.astra.sys.rmi.socketfactory;

/** * * @author  thomas * @version  */

import java.net.ServerSocket;


import java.net.Socket;


import java.io.IOException;

import javax.net.ssl.*;


public class SSLServerSocket extends ServerSocket implements java.io.Serializable {


    int m_port;


    public SSLServerSocket(int port) throws IOException {


        super(port);


    }


    public Socket accept() throws IOException {

        SSLSocketFactory factory =

                (SSLSocketFactory) SSLSocketFactory.getDefault();

        //  System.out.println("Creating client socket to host " + host + " port " + port);

        // return socket;


        Socket socket = new Socket();

        //SSLSocket socket2 = (SSLSocket)factory.createSocket(socket,"localhost",m_port,true);

        return socket;

        // implAccept(socket);

        //System.out.println("ok je renvois le socket");

        //return socket;


    }


}