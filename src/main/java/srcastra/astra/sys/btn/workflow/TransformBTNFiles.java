/*
 * TransformBTNFiles.java
 *
 * Created on 13 septembre 2004, 19:45
 */

package srcastra.astra.sys.btn.workflow;
import java.sql.*;
import org.jdom.*;
import java.io.*;
import java.util.*;
import srcastra.astra.sys.btn.utils.XmlDocument;
import java.nio.channels.*;
import srcastra.astra.sys.ManageDirectory;
/**
 *
 * @author  Administrateur
 */
public class TransformBTNFiles extends BtnAbstract{
    
    /** Creates a new instance of TransformBTNFiles */
    boolean check=false;
    String directory;
    ArrayList elementNotFound;
    String fileStr;
    FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".BTN");
            }
        };
    public TransformBTNFiles(String  directory,String schemapath,Connection con) throws FieldNotFoundException,SQLException{
        super(con);
      
            //File file=new File(directory);
            this.directory=directory;
            System.out.println("directory "+directory);
            String btnFile=directory.toString()+"/bkbtn";
            
            ManageDirectory.testDirectoryPath(btnFile);
            
            File dir = new File(btnFile+"/");
            
            File[] files=getFileIndirectory(directory,filter);            
            if(files!=null){
                for(int i=0;i<files.length;i++){
                    System.out.println("File name"+files[i].getAbsolutePath());
                    filename=files[i].getName().substring(0,(files[i].getName().length()-4));
                 //   FileChannel channel = new RandomAccessFile(files[i], "rw").getChannel();
                  //  FileLock lock = channel.lock();
                  //  lock = channel.tryLock();
                    readFile(files[i].getAbsolutePath(),schemapath);
                   // BufferedWriter out = new BufferedWriter(new FileWriter(btnFile+"\\"+files[i].getName()));
                    //out.write(fileStr);
                    //boolean deleted=files[i].getAbsoluteFile().delete();            
                    

                    
                   // lock.release();
                   // channel.close();
                }
                checkError(null);
                for(int i=0;i<files.length;i++){
                    boolean success = moveFile(dir, files[i]);
                    System.out.println("File renamed : "+success+" File path "+files[i].getAbsolutePath()+" exist :"+files[i].exists());
                    if (!success) {
                        System.out.println("Impossible de copier les fichiers");
                        
                    }
                }
               
            }
            else{
              System.out.println("files=null");               
            }
         
          
    }
     private void readFile(String file,String schemaPath) throws SQLException, FieldNotFoundException{
     try {
        System.out.println("file : "+file);
        Document schema=XmlDocument.readDocument(schemaPath);
        Element schemaRoot=schema.getRootElement();
        BufferedReader in = new BufferedReader(new FileReader(file)); 
        String str;
        Element entity=null;
        Element root=new Element("BTNROOT");
        Element field=null;
        Element parent=null;
        fileStr="";
        while ((str = in.readLine()) != null) {
          //  fileStr=fileStr+str;
            Element element=createNode(str,true);
             if (element!=null){
                 checkElementValidy(element, schemaRoot);                 
                if(element.getName().equals("entity")){
                 entity=element;
                 root.addContent(entity);
                 parent=root;
             }
             else if(!element.getName().equals("entity")){
               field=element;  
               entity.addContent(element);   
               parent=entity;
             }           
                 addElement(element, check,parent);               
                 check=false;
            }
         }
        in.close();        
        getAstraCode(root, 1);
        XmlDocument.getXmlDocument().generateDocument(root);
        String towrite=directory+"/BTN"+filename+".xml";
        System.out.println("writing "+towrite);
        XmlDocument.writeDocument(towrite);
         
     }catch(java.io.IOException in){
       in.printStackTrace();   
     }   
    }
      private void checkElementValidy(Element newElement,Element schemaElement){       
        if(newElement.getName().equals("addon"))
            check=true;
        if(check==true)return;
        if(!newElement.getName().equals("entity") && newElement.getName().toLowerCase().equals(schemaElement.getName().toLowerCase().trim())){            
            if(schemaElement.getAttribute("table")!=null){
              String table=schemaElement.getAttributeValue("table");
              if(table!=null){           
                    newElement.setAttribute("table",table);                                
              }
          }
          check=true;
          return;
        }else if(newElement.getName().equals("entity") && schemaElement.getAttributeValue("name")!=null && newElement.getAttributeValue("name").toLowerCase().equals(schemaElement.getAttributeValue("name").toLowerCase().trim())){
          if(schemaElement.getAttribute("table")!=null){
              String table=schemaElement.getAttributeValue("table");
              if(table!=null){           
                    newElement.setAttribute("table",table);                
              }
          }
          check=true;
          return;
        }else{
            List list=schemaElement.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
             Element child=(Element)ite.next();
             checkElementValidy(newElement, child);                                                        
            }
        }            
    }
      private void addElement(Element element,boolean check,Element parent){
         if(check==false){
                   if(elementNotFound==null)
                       elementNotFound=new ArrayList();
                   ElementNotFound elementNotF=new ElementNotFound(element,parent);
                   elementNotFound.add(elementNotF);                 
                 }
    }
      private void checkError(Element root) throws FieldNotFoundException{
        if(elementNotFound!=null)
            throw new FieldNotFoundException(elementNotFound,null);
    }
      private void getAstraCode(Element root,int langue) throws SQLException{
        if(root!=null){
            if(root.getAttribute("table")!=null ){
                String table=root.getAttributeValue("table");
                if(!table.equals("")){
                    String code=root.getChild("code_btn").getText();
                    if(code.equals("2600")){
                        System.out.println("tamere");
                    }
                    if(table.equals("plaatsna")){
                        root.addContent(getDestinationId(code));
                    }else if(table.equals("carrier")){
                        root.addContent(getCarrierId(code));
                    }
                    else if(table.equals("land")){
                        root.addContent(getPaysId(code));
                    }
                    else if(table.equals("to1")){
                        root.addContent(getFournId(code));
                    }
                    else if(table.equals("tobroch1")){
                        root.addContent(getGrpDecId(code));
                    }
                     else if(table.equals("titel")){
                        root.addContent(getTitleCode(code));
                    }                    
                    else{                    
                        String colone="tradfr";
                        if(langue==2)
                            colone="tradnl";
                        String requete="SELECT id,"+colone+" FROM "+table+" WHERE code=?";
                        System.out.println("requete "+requete);
                        PreparedStatement pstmt=con.prepareStatement(requete);
                        pstmt.setString(1, code);
                        ResultSet result=pstmt.executeQuery();
                        int id=0;
                        String traduction="";
                        while(result.next()){
                            id=result.getInt(1);
                            traduction=result.getString(2);                    
                        }
                        
                        Element element=new Element("code_astra");
                        element.setText(traduction);
                       // root.addContent(elementid);
                        root.addContent(element);
                    }
                }
            }
            List list=root.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
             Element child=(Element)ite.next();             
             getAstraCode(child,langue);
        }
    }
    }
      private Element getPaysId(String btncode) throws SQLException{
        String requete="SELECT pyscleunik from pays WHERE btncode=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
        
    }
    private Element getFournId(String btncode) throws SQLException{
        String requete="SELECT frcleunik from fournisseur WHERE btncode=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
    }
    private Element getGrpDecId(String btncode)throws SQLException{
        String requete="SELECT frgtcleunik from fournisseur_grproduit WHERE btncode=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
    }
    private Element getCarrierId(String btncode)throws SQLException{
        String requete="SELECT coe_cleunik from compagnie WHERE btncode=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
        
    }
    private Element getTitleCode(String btncode)throws SQLException{
        String requete="SELECT tscleunik from traductiontitrepers WHERE tsabrege=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
        
    }
    private Element getDestinationId(String btncode)throws SQLException{
        String requete="SELECT  dn_cleunik FROM destination WHERE btncode=?";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,btncode);
        ResultSet result=pstmt.executeQuery();
        int id=0;
        while(result.next()){
            id=result.getInt(1);
        }
        Element elementid=new Element("id_astra");
        elementid.setText(new Integer(id).toString());
        return elementid;
        
    }
}
