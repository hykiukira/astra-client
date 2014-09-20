/*
 * ManageDirectory.java
 *
 * Created on 21 avril 2004, 15:42
 */

package srcastra.astra.sys;
import java.io.*;
import srcastra.astra.gui.sys.*;
/**
 *
 * @author  Administrateur
 */
public class ManageDirectory {
    
    /** Creates a new instance of ManageDirectory */
    
    public ManageDirectory() {
    }
    public static String  testDirectory(String directoryName){
       File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
       file = testOs(file);
       String files=file.toString()+"/AstraDir/"+directoryName;
       boolean success = (new File(files)).mkdirs();
       if (!success) {
        //System.out.println("directory existant");
       }
       return files;
    }
    public static String  testIpFile(String directoryName){
       File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
       file = testOs(file);
       String files="";
       if(System.getProperty("os.name").toLowerCase().substring(0,3).equals("win")){
          files=file.toString();
       }else{
         files=file.toString()+"/AstraDir/"+directoryName;
       }
       boolean success = (new File(files)).mkdirs();
       if (!success) {
        //System.out.println("directory existant");
       }
       return files;
    }

    private static File testOs(File file) {
        if(System.getProperty("os.name").toLowerCase().substring(0,3).equals("win")){
         file=file.getParentFile();
        }
        return file;
    }

    public static String  testDirectoryPath(String path){
       boolean success = (new File(path)).mkdirs();
       if (!success) {
        //System.out.println("directory existant");
       }
       return path;
    }
    public static File  testFile(String directoryName,String filename) throws Exception{
    
      // try{
           File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
           file=testOs(file);
           String files=file.toString()+File.separator+"AstraDir"+File.separator+directoryName+File.separator+filename;
           File retval=new File(files);
           if(!retval.exists()){
             return null;
           }
           return retval;       
    }  
    public static void  writeFile(String directoryName,String filename,Object obj) throws Exception{
    
      // try{
           File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
           file=testOs(file);
           String files=file.toString()+File.separator+"AstraDir"+File.separator+directoryName+File.separator+filename; 
           File retval=new File(files);
           if(retval.exists()){
              ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(retval));
              out.writeObject(obj);
              out.close();     
           }
           else{
              retval.createNewFile();
              ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(retval));
              out.writeObject(obj);
              out.close();               
           }
    }  
}
 