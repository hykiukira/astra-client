/*
 * BtnXmlToDataBase.java
 *
 * Created on 13 septembre 2004, 20:07
 */

package srcastra.astra.sys.btn.workflow;
import java.sql.*;
import org.jdom.*;
import java.io.*;
import java.util.*;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.btn.utils.*;
import srcastra.astra.sys.ManageDirectory;
/**
 *
 * @author  Administrateur
 */
public class BtnXmlToDataBase extends BtnAbstract{
    
    /** Creates a new instance of BtnXmlToDataBase */
    long id;
    FilenameFilter filter = new FilenameFilter() {
    public boolean accept(File dir, String name) {
            return name.endsWith(".xml") && !name.equals("BTN.xml");
       }
    };
    public BtnXmlToDataBase(String directory,Connection con) {
        super(con);
        String xmlFile=directory.toString()+"/bkxml";
        ManageDirectory.testDirectoryPath(xmlFile);
        File dir = new File(xmlFile+"/");
        try{
            File[] file=getFileIndirectory(directory,filter);
            System.out.println("NOW IN BTNXMLTODATABASE");
            for(int i=0;i<file.length;i++){
                System.out.println(file[i].getAbsolutePath());
                Document schema=XmlDocument.readDocument(file[i].getAbsolutePath()) ;
                Element element=schema.getRootElement();
                if(element!=null){
                List list=element.getChildren();
                Iterator ite=list.iterator();
                while(ite.hasNext()){
                    Element child=(Element)ite.next();
                    System.out.println("Inserting Element");
                    insertIntoTable(child);
                }
            }
              
           }            
            for(int i=0;i<file.length;i++){
               boolean success = moveFile(dir, file[i]);
               System.out.println("File renamed : "+success+" File path "+file[i].getAbsolutePath()+" exist :"+file[i].exists());
               if (!success) {         
                   System.out.println("impossible de copier les élément");
               } 
                
            }
         }catch(Exception sn){
            sn.printStackTrace();
        }
    }
    private void insertIntoTable(Element element)throws SQLException{
        String table="";
        String drop="";
        String fields="";
        String value="";
        
        PreparedStatement pstmt=null;
        table ="INSERT INTO  "+element.getAttributeValue("name");
        if(element!=null){
            if(element.getAttributeValue("name").toLowerCase().equals("t_reservering")){
                
            }
            else{
                fields="id_parent,";
                value=""+id+",";
            }          
            List list=element.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
                Element child=(Element)ite.next();
                fields=fields+child.getName()+",";
                if(child.getChild("id_astra")!=null){
                    value=value+child.getChild("id_astra").getText()+",";
                }
                else if(child.getChild("code_btn")!=null){
                    String tmpVal=child.getChild("code_btn").getText();
                    if(tmpVal!=null){
                        tmpVal=tmpVal.replaceAll("\""," ");
                        tmpVal=tmpVal.replace('\'', ' ');
                    }
                    value=value+"\""+tmpVal+"\",";
                }
                else value=value+"\"   \",";
            }
            fields=fields.substring(0,(fields.length()-1));
            value=value.substring(0,(value.length()-1));
            table=table+"("+fields+")"+" VALUES("+value+")";
            System.out.println(table);
            pstmt=con.prepareStatement(table);
            pstmt.execute();
            if(element.getAttributeValue("name").toLowerCase().equals("t_reservering")){
              id=Transaction.getLastInsertId2(con);   
            }
        }
       
    }
    
}
