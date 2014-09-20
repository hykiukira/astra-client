/*
 * ManageParameter.java
 *
 * Created on 14 mai 2004, 10:42
 */

package srcastra.astra.gui.sys.parameter;
import java.io.*;
import java.util.*;
import srcastra.astra.sys.*;
import srcastra.astra.gui.sys.*;
/**
 *
 * @author  Administrateur
 */
public class ManageParameter {
    
    /** Creates a new instance of ManageParameter */
    public ManageParameter() {
    }
    public static ComptaFilePath getComptaFilePath(){
      try{
      ManageDirectory.testDirectory("parameters");   
      File file=ManageDirectory.testFile("parameters", "param.dat");
      Hashtable hash=null;
      if(file!=null){
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
        hash=(Hashtable)in.readObject();
        in.close();
      }
      if(hash!=null){
        return (ComptaFilePath)hash.get("comptaparam");   
      }
     
     }catch(Exception io){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,io);   
     }
       return null;
    }
     public static String getComptaTypeExport(){
      try{
      ManageDirectory.testDirectory("parameters");   
      File file=ManageDirectory.testFile("parameters", "param.dat");
      Hashtable hash=null;
      if(file!=null){
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
        hash=(Hashtable)in.readObject();
        in.close();
      }
      if(hash!=null){
        return (String)hash.get("type");   
      }
     
     }catch(Exception io){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,io);   
     }
       return null;
    }
    public static void writeComptaFilePath(ComptaFilePath path){
      try{
      ManageDirectory.testDirectory("parameters");   
      File file=ManageDirectory.testFile("parameters", "param.dat");
      Hashtable hash=null;
      if(file!=null){
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
        hash=(Hashtable)in.readObject();
        in.close();
      }
      if(hash==null)
          hash=new Hashtable();
          hash.put("comptaparam", path);    
     ManageDirectory.writeFile("parameters", "param.dat",hash);
     }catch(FileNotFoundException fn){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,fn);   
     }
      catch(Exception io){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,io);   
     }
     
    }
     public static void writeTypeExport(String type){
      try{
      ManageDirectory.testDirectory("parameters");   
      File file=ManageDirectory.testFile("parameters", "param.dat");
      Hashtable hash=null;
      if(file!=null){
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
        hash=(Hashtable)in.readObject();
        in.close();
      }
      if(hash==null)
          hash=new Hashtable();
          hash.put("type", type);    
     ManageDirectory.writeFile("parameters", "param.dat",hash);
     }catch(FileNotFoundException fn){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,fn);   
     }
      catch(Exception io){
          new ErreurScreenLibrary().displayErreur(null,ErreurScreenLibrary.EXCEPTION,ErreurScreenLibrary.DEBUG_MODE_ON,io);   
     }
     
    }
}
