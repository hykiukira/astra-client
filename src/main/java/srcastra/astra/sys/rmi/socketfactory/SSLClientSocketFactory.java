/* * ZipCliSockF.java * * Created on 22 mars 2002, 10:41 */



package srcastra.astra.sys.rmi.socketfactory;



import java.io.IOException; import java.io.Serializable;



import java.net.Socket;



import java.rmi.server.RMIClientSocketFactory;
import java.net.*;
import javax.swing.*;
import  srcastra.astra.gui.modules.param.*;
import srcastra.astra.sys.rmi.astraimplement;
import java.io.*;

public class SSLClientSocketFactory implements RMIClientSocketFactory, Serializable {

    static SSLClientSocketFactory  clientfactory;
   // static Socket socket;    
    int firewall;
    String ip;
    static ServerIp serverip;
    //final static String host;
    public SSLClientSocketFactory(int firewall,String ip){
        this.ip=ip;
        this.firewall=firewall;
    
    }
     public SSLClientSocketFactory(){
     
    
    }
    public static SSLClientSocketFactory   getClientFactory(int firewall,String ip){

        if(clientfactory==null)

            clientfactory=new SSLClientSocketFactory(firewall,ip);

        return clientfactory;

    }
    public static SSLClientSocketFactory   getClientFactory(){

        if(clientfactory==null)

            clientfactory=new SSLClientSocketFactory();

        return clientfactory;

    }

    public Socket createSocket(String host, int port)       



    {            



      //  this.host=host;
        Socket socket=null;
        try{   
         //  if(socket==null){
         /* if(serverip==null){
              String path=srcastra.astra.sys.ManageDirectory.testDirectory("config");
              File file2=new File(path+"\\server.dat");
              if(file2.exists()){
                  System.out.println("[********]file existe");
                  ObjectInputStream in=new ObjectInputStream(new FileInputStream(file2));
                  serverip=(ServerIp)in.readObject();                
                  in.close(); 
                 if(serverip.getFireWall()==1){
                    host=serverip.getIp().trim();
                }
              }
              
          }
         */
          // InetAddress localaddr = InetAddress.getLocalHost();
           //JOptionPane.showMessageDialog(null,localaddr.getHostAddress());      
          // host="195.144.70.242";
           String tmp=null;
           System.out.println("before error "+host+" "+port);
         //  System.out.println(tmp.substring(0,4));
           if(host.equals("127.0.0.1")){
               host="10.0.25.5";
           }
         //  host="localhost";
           System.out.println("After change "+host+" "+port);
           socket = new Socket(srcastra.astra.Astra.getIp(), port);     
           System.out.println("connected to "+host+":"+port);
           //}

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

 //   public boolean equals(Object obj) {

//	return getClass() == obj.getClass() ;

  //  }



}