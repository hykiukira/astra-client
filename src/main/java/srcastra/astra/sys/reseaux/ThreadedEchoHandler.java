/*
 * ThreadedEchoHandler.java
 *
 * Created on 11 december 2002, 17:37
 */

package srcastra.astra.sys.reseaux;
import java.io.*;
import java.net.*;
import java.util.Hashtable;
import srcastra.astra.sys.rmi.astraimplement;
import srcastra.astra.sys.rmi.utils.Poolconnection;
/**
 *
 * @author  Thomas
 */
public class ThreadedEchoHandler extends Thread
{  public ThreadedEchoHandler(Socket i, int c,Hashtable socket,astraimplement serveur)
   { 
   incoming = i; counter = c; m_socket=socket;
   System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}création d'un nouveau thread");
   m_serveur=serveur;
   this.run();
   }

   public void run()
   {  
        System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}Je rentre dans le RUN");
       try
      {   ObjectOutputStream out=new ObjectOutputStream(incoming.getOutputStream());
          ObjectInputStream in = new ObjectInputStream (incoming.getInputStream());
            //(new InputStreamReader(incoming.getInputStream()));
        
        // PrintWriter out = new PrintWriter
          //  (incoming.getOutputStream(), true /* autoFlush */);

//         out.println( "Hello! Enter BYE to exit." );

         boolean done = false;
         while (!done)             
         {  
            System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}Attente de donnée");
            Object str =(ClientResaux_T) in.readObject();
            System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}Récetpion d'une classe");
            if (str == null) done = true;
            else
            { // out.println("Echo (" + counter + "): " + str);
                if(str instanceof ClientResaux_T){
                    ClientResaux_T cli=(ClientResaux_T)str;
                    Poolconnection tmpool=m_serveur.getConnectionAndCheck(cli.getUrcleunik(),true);
                    tmpool.setUserSock(incoming);
                    cli.afficheClient();
                    //m_socket.put(new Integer(cli.getUrcleunik()),this);
                }
            }
         }
         incoming.close();
      }
      catch (Exception e)
      {  System.out.println(e);
      }
   }

   private Socket incoming;
   private Hashtable m_socket;
   private int counter;
   private astraimplement m_serveur;
}
