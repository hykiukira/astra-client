/*
 * ThreadedInput.java
 *
 * Created on 16 januari 2003, 12:14
 */

package srcastra.astra.sys.reseaux;
import java.io.*;
import java.net.*;
import srcastra.astra.sys.rmi.astraimplement;
import srcastra.astra.sys.rmi.utils.Poolconnection;

/**
 *
 * @author  Thomas
 */

    
    /** Creates a new instance of ThreadedInput */
  public class ThreadedInput extends Thread{
    public ThreadedInput(astraimplement serveur,Socket s){
        try{
            m_serveur=serveur;
            incoming=s;
            System.out.println("création de l'inputstream");
            this.run();
        }catch(Exception e){
         e.printStackTrace();                   
        }        
    }
    public void run(){
        try{
            boolean done=false;
            ObjectOutputStream out=new ObjectOutputStream(incoming.getOutputStream());
            ObjectInputStream in = new ObjectInputStream (incoming.getInputStream());
            System.out.println("InputStream lancé");
            while (!done)             
                {  
                    System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}Attente de donnée");
                    Object str = in.readObject();
                    System.out.println("{{{{{{{{{{{{{{{{{  }}}}}}}}}}Récetpion d'une classe");
                    if (str == null) done = true;
                    else
                    { 
                        if(str instanceof ClientResaux_T){
                            ClientResaux_T cli=(ClientResaux_T)str;
                            Poolconnection tmpool=m_serveur.getConnectionAndCheck(cli.getUrcleunik(),true);
                            tmpool.setUserSock(incoming);
                            cli.afficheClient();
                        }
                    }
             }
          incoming.close();            
    }catch(Exception e){
        e.printStackTrace();   
    }
    }

 InputStream inp ;   
 astraimplement m_serveur;
 Socket incoming;
}
    

