/*
 * ManageBtnDataBase.java
 *
 * Created on 13 septembre 2004, 20:26
 */

package srcastra.astra.sys.btn.utils;
import java.sql.*;
import org.jdom.*;
import java.io.*;
import java.util.*;
import srcastra.astra.sys.Transaction;
/**
 *
 * @author  Administrateur
 */
public class ManageBtnDataBase extends UtilAbstract{
    
    /** Creates a new instance of ManageBtnDataBase */
    Connection con;
    Hashtable intFields;
   
    public ManageBtnDataBase(String xmlConfig) {
         try{
            intFields=new Hashtable();
            intFields.put("tocode", "tocode");
            intFields.put("tobrochcode", "tobrochcode");
            
            con=testConnection("root", "XkLm2000", "astra", "3306", "localhost");
            Document schema=XmlDocument.readDocument(xmlConfig);
            Element element=schema.getRootElement();
            createTable(element);
         }catch(Exception sn){
            sn.printStackTrace();
        }
         
    }
     private void createTable(Element element)throws SQLException{
        if(element!=null){
            List list=element.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
                Element child=(Element)ite.next();
                constructTable(child);
            }
        }
    }
    private void constructTable(Element element)throws SQLException{
        String table="";
        String drop="";
        String fields="";
        PreparedStatement pstmt=null;
        if(element.getAttributeValue("name").equals("T_Reservering")){
         fields="id bigint(20) NOT NULL AUTO_INCREMENT,\n";
        }
        else{
           fields="id bigint(20) NOT NULL AUTO_INCREMENT,\n"; 
           fields=fields+"id_parent bigint(20) NOT NULL,\n"; 
        }
        if(element!=null){         
            table="CREATE TABLE "+element.getAttributeValue("name")+"(\n";
            List list=element.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
                Element child=(Element)ite.next();
                if(intFields.get(child.getName())==null){
                    fields=fields+child.getName()+" varchar(50),\n";
                }
                else{
                    fields=fields+child.getName()+" mediumint(9),\n";
                }
            }          
            if(element.getAttributeValue("name").equals("T_Reservering")){
                fields=fields+"integrated mediumint(9) Default 0,\nPRIMARY KEY(id)\n";
            }
            else{
                fields=fields+"PRIMARY KEY(id),\n";
                fields=fields+"INDEX par_id (id_parent),\n";
                fields=fields+"FOREIGN KEY (id_parent) REFERENCES T_Reservering(id) \n";
            }
            table=table+fields+")Type=InnoDB;";
            System.out.println(table);
            pstmt=con.prepareStatement(table);
            pstmt.execute();
        }
       
    }
    public static void main(String[] args){
        new ManageBtnDataBase("c:/test/BTN.xml");
    }
}
