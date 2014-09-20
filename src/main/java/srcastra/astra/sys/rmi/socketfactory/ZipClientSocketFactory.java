/* * ZipCliSockF.java * * Created on 22 mars 2002, 10:41 */

package srcastra.astra.sys.rmi.socketfactory;

import java.io.IOException; import java.io.Serializable;

import java.net.Socket;

import java.rmi.server.RMIClientSocketFactory;

public class ZipClientSocketFactory implements RMIClientSocketFactory, Serializable {

    public Socket createSocket(String host, int port)       

    {            

        Socket socket=null;     

        try{   

            socket = new ZipSocket(host, port);     

        }            

        catch(IOException e){    

            e.printStackTrace();

            System.out.println("erreur dans zipclient socket factory :"+e);      

        }           

        catch(Exception e1)     

        {   e1.printStackTrace();    

            System.out.println("erreur dans zipclient socket factory exception "+e1);   

        }            

        return socket;

    }

}