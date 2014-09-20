/*
 * LoadDossierTread.java
 *
 * Created on 5 décembre 2003, 11:37
 */

package srcastra.astra.sys.rmi.utils;
import java.util.*;
import srcastra.test.*;
import java.io.*;
import srcastra.astra.sys.classetransfert.dossier.*;
/**
 *
 * @author  Thomas
 */
public class FonctionIntegrity extends Thread{
    
    /** Creates a new instance of LoadDossierTread */
    String user;
    String password;
    ArrayList array;
    ServeurConnect connect;
    LockObject parent;
    int threadnumber ;
    Hashtable dossierH;
    int x,y;
    int threadNumber;
    test t;
   
    public FonctionIntegrity(int x,int y,int threadNumber,test t,LockObject parent){
        this.x=x;
        this.y=y;
        this.threadNumber=threadNumber;
        this.t=t;
        this.parent=parent;
    }
    public void run(){
        
        t.execute(x, y,threadNumber);
       
       
      
    }
   
    
    /** Getter for property syn.
     * @return Value of property syn.
     *
     */
  
    
}
