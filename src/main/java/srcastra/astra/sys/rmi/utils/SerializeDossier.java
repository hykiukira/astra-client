/*
 * SerializeDossier.java
 *
 * Created on 5 décembre 2003, 9:32
 */

package srcastra.astra.sys.rmi.utils;
import java.io.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import java.util.*;
/**
 *
 * @author  Thomas
 */
public class SerializeDossier {
    
    /** Creates a new instance of SerializeDossier */
    private final static String PATH="C:\\dossier";
    private final static String PATHINSERTED="C:\\dossierInserted";
    private final static String PATHFACT="C:\\dossierFacture";
    File dossierSez;
    ObjectOutputStream out;
    ObjectInputStream in;
    public SerializeDossier() {
    }
    public void serialiseDossier(Dossier_T dossier) throws java.io.IOException,ClassNotFoundException{
        ArrayList array=null;
        dossierSez=new File(PATH);
        if(!dossierSez.exists()){
            dossierSez.createNewFile();
            array=new ArrayList();
            writeDossier(array,dossier);     
        }else{
           in=new ObjectInputStream(new FileInputStream(dossierSez));
           array=(ArrayList)in.readObject();
           in.close();
           System.out.println("Nombre de dossier "+array.size());
           writeDossier(array,dossier);     
          
        }        
    }
     public void serialiseDossierInserted(Dossier_T dossier) throws java.io.IOException,ClassNotFoundException{
        ArrayList array=null;
        dossierSez=new File(PATHINSERTED);
        if(!dossierSez.exists()){
            dossierSez.createNewFile();
            array=new ArrayList();
            writeDossier(array,dossier);     
        }else{
           in=new ObjectInputStream(new FileInputStream(dossierSez));
           array=(ArrayList)in.readObject();
           in.close();
           System.out.println("Nombre de dossier "+array.size());
           writeDossier(array,dossier);     
          
        }
     }
        public void serialiseDossierFacture(Dossier_T dossier) throws java.io.IOException,ClassNotFoundException{
        ArrayList array=null;
        dossierSez=new File(PATHFACT);
        if(!dossierSez.exists()){
            dossierSez.createNewFile();
            array=new ArrayList();
            writeDossier(array,dossier);     
        }else{
           in=new ObjectInputStream(new FileInputStream(dossierSez));
           array=(ArrayList)in.readObject();
           in.close();
           System.out.println("Nombre de dossier "+array.size());
           writeDossier(array,dossier);     
          
        }   
            
    }
    public void writeDossier(ArrayList array,Dossier_T dossier)throws java.io.IOException,ClassNotFoundException{
        array.add(dossier);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dossierSez,false));
        oos.writeObject(array);
        oos.close();
        
    }
    public ArrayList getArray()throws java.io.IOException,ClassNotFoundException{
         dossierSez=new File(PATH);
         in=new ObjectInputStream(new FileInputStream(dossierSez));
         ArrayList array=(ArrayList)in.readObject();
         in.close();   
         return array;
        
    }
    public ArrayList getArrayInserted()throws java.io.IOException,ClassNotFoundException{
         dossierSez=new File(PATHINSERTED);
         in=new ObjectInputStream(new FileInputStream(dossierSez));
         ArrayList array=(ArrayList)in.readObject();
         in.close();
         dossierSez.delete();
         return array;
        
    }
     public ArrayList getArrayFacture()throws java.io.IOException,ClassNotFoundException{
         dossierSez=new File(PATHFACT);
         in=new ObjectInputStream(new FileInputStream(dossierSez));
         ArrayList array=(ArrayList)in.readObject();
         in.close();
         dossierSez.delete();
         return array;
        
    }
   
    
}
