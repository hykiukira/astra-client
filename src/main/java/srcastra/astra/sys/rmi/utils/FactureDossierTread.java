/*
 * LoadDossierTread.java
 *
 * Created on 5 d�cembre 2003, 11:37
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
public class FactureDossierTread extends Thread{
    
    /** Creates a new instance of LoadDossierTread */
    String user;
    String password;
    ArrayList array;
    ServeurConnect connect;
    LockObject parent;
    int threadnumber ;
    Hashtable dossierH;
    int x,y;
    public FactureDossierTread(String user, String password, ArrayList array, LockObject parent, int threadnumber, Hashtable dossierH,int x,int y) {
        this.user=user;
        this.password=password;
        this.array=array;
        this.parent=parent;
        this.threadnumber=threadnumber;
        this.dossierH=dossierH;
        this.x=x;
        this.y=y;
        System.out.println("initialise Facure Dossier Thread");
    }
    public void run(){
        System.out.println("start facture dossier thread");
        try{
        connect=new ServeurConnect();
        connect.connectServeur(user,password);
        ArrayList array2=new ArrayList();
        for(int i=0;i<array.size();i++){
          Dossier_T dossier=(Dossier_T)array.get(i);
          Dossier_T clone=cloneDossier(dossier);
          array2.add(clone);
        } 
        System.out.println("\n\n[*******]lock tread");
        parent.getSyn();
        System.out.println("\n\n[*******] nombre de dossier du thread "+threadnumber+" : "+array2.size());
        for(int i=x;i<=y;i++){
          Dossier_T dossier=(Dossier_T)array2.get(i);
          double t1=System.currentTimeMillis();
          System.out.println("\nNumero de dossier � facture "+dossier.getDrnumdos()+" Key "+dossier.getDrcleunik());
          connect.serveur2.renvDossierRmiObject(connect.tmpgestion2.getUrcleunik()).setDossierFacture(connect.tmpgestion2.getUrcleunik(), new Integer(dossier.getDrcleunik()).longValue(),40000,dossier.getDrtotalprix());
          double t2=System.currentTimeMillis();
          double t3=(t2-t1)/1000;
        //  System.out.println("Thread numbre "+threadnumber+" dossier numero "+i+" facture in "+t3+" seconds");
          t1=System.currentTimeMillis();
          ArrayList dossierback=connect.serveur2.renvDossierRmiObject(connect.tmpgestion2.getUrcleunik()).chargeDossier(connect.tmpgestion2,dossier.getDrcleunik(),false);
          if(dossierback.size()>0){
            Dossier_T dossierretval=(Dossier_T)dossierback.get(0);
            dossierH.put(new Integer(dossierretval.getDrcleunik()), dossierretval);
            t2=System.currentTimeMillis();
            t3=(t2-t1)/1000;
           
          }
          else System.out.println("size "+dossierback.size());
        } 
        System.out.println("\n\n[*******]dossier released");
        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){
          se.printStackTrace();           
        }catch(java.rmi.RemoteException rn){
          rn.printStackTrace();           
        }
      
    }
    public Dossier_T cloneDossier(Dossier_T dossier){
      try{
                ByteArrayOutputStream ByteOut=new ByteArrayOutputStream();
                //DeflaterOutputStream defOut=new DeflaterOutputStream(ByteOut);
                ObjectOutputStream ObjOut=new ObjectOutputStream(ByteOut);
                ObjOut.writeObject(dossier);
                ObjOut.flush();
                ObjOut.close();
                byte[] tmpArray=ByteOut.toByteArray();
                ByteArrayInputStream ByteIn=new ByteArrayInputStream(tmpArray);
                ObjectInputStream objIn=new ObjectInputStream(ByteIn);
                Dossier_T dossierinit=(Dossier_T)objIn.readObject();
                objIn.close();
                return dossierinit;
               /// System.out.println("dossier                  dddddddddddddd "+dossierinit.toString());
               
                }catch(java.io.IOException in){
                 in.printStackTrace();   
                }
                catch(java.lang.ClassNotFoundException cn){
                 cn.printStackTrace();   
                } 
      return null;
        
        
    }  
    
    /** Getter for property syn.
     * @return Value of property syn.
     *
     */
  
    
}
