/*
 * testThreads.java
 *
 * Created on 6 december 2002, 15:53
 */

package srcastra.test;
import java.lang.*;
import srcastra.astra.sys.classetransfert.signaletique.CodePost_Tb;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.test.*;
import java.rmi.*;
import srcastra.astra.sys.classetransfert.clients.*;
import java.util.ArrayList;
/**
 *
 * @author  Thomas
 */
public class testThreads {
    
   //ThreadGroup=new ThreadGroup("group");
   
    /** Creates a new instance of testThreads */
    public testThreads() {
        System.out.println("je rentre dedans");
        connect=new ServeurConnect();
        connect.connectServeur();
        test[] testtab=new test[20];
        for(int i=25;i<testtab.length;i++){
           long code=99999999+i;
           testtab[i]=new test(new CodePost_Tb(0,new Long(code).toString(),"Mars",1));
           testtab[i].start();
        }
    }
    public static void main(String[] args){
        new testThreads();
        
    }
    public  synchronized void affiche(CodePost_Tb code){
       i++;
       if(i==24)
           this.notifyAll();
       else
         try{
             this.wait();
        }catch(InterruptedException in){
         in.printStackTrace();   
        }
       try{
        connect.serveur2.insertObjectPopup(code,connect.tmpgestion2.getUrcleunik(),astrainterface.COMBO_CODE_POST,1);    
       }catch(RemoteException re){
        re.printStackTrace();   
       }
    }
    class test extends java.lang.Thread{
        public test(CodePost_Tb code){
            this.code =code;
            
        }
        public void run(){
            long t1=System.currentTimeMillis();
            affiche(this.code);
            long t2=System.currentTimeMillis();
            long t3=t2-t1;
            System.out.println("temp d'insertion: "+t3);
            
        }
        
    testThreads parent;    
    CodePost_Tb code;
    }
    int i=0;
   astrainterface serveur;
  Loginusers_T login;
  ServeurConnect connect;
}
