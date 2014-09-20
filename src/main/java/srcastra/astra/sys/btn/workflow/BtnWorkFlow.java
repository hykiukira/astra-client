/*
 * BtnWorkFlow.java
 *
 * Created on 19 septembre 2004, 14:47
 */

package srcastra.astra.sys.btn.workflow;
import java.sql.*;
//import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import srcastra.astra.sys.classetransfert.utils.*;
import java.sql.*;
import srcastra.astra.sys.*;
import org.jdom.*;
import java.util.*;
import srcastra.astra.sys.btn.utils.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class BtnWorkFlow {
    
    /** Creates a new instance of BtnWorkFlow */
    Connection con;
    String directory;
    String schemapath;
    Timer timer;
    String database;
    String host;    
    public BtnWorkFlow(String directory,String schemapath,String database,String host) {
        this.directory=directory;
        this.schemapath=schemapath;    
        this.database=database;
        this.host=host;
        timer = new Timer();
        timer.schedule(new RemindTask(),
	               0,        //initial delay
	               120000);  //subsequent rate
    }
    class RemindTask extends TimerTask {
	
        public void run() {
           try{
            try{              
             con=connectDb("root", "XkLm2000", database,host, 3306);
             Transaction.begin(con);
             new TransformBTNFiles(directory,schemapath,con) ;
             new BtnXmlToDataBase(directory,con);
             Transaction.commit(con);
             con.close();
      //  
	  
        }catch(FieldNotFoundException fn){
            fn.showUnfoundElement();
            fn.printStackTrace();
            Transaction.rollback(con);
            ArrayList badFields=fn.getArray();
            try{
            if(badFields!=null){
                for(int i=0;i<badFields.size();i++){
                    ElementNotFound elem=(ElementNotFound)badFields.get(i);
                    addElementToSchema(elem, schemapath);
                    addColumnToTable(elem,con);
                }
            }
            
            con.close();
            }catch(java.io.IOException in){
              in.printStackTrace(); 
              con.close();
              
           }
        }
        catch(Exception en){
            en.printStackTrace();
            con.close();
        }
        }  
           catch(SQLException sn){
              sn.printStackTrace(); 
              
           }
           
        }
        private void addElementToSchema(ElementNotFound elementNotF,String schemapath)throws java.io.IOException{
            Element parent=elementNotF.getParent();
            Element child=elementNotF.getChild();
            if(parent.getName().equals("entity")){
                if(parent.getAttributeValue("name")!=null){
                    Document schema=XmlDocument.readDocument(schemapath);
                    Element schemaRoot=schema.getRootElement();
                    List children=schemaRoot.getChildren();
                    if(children!=null){
                        Iterator ite=children.iterator();
                        while(ite.hasNext()){
                            Element childEle=(Element)ite.next();
                            if(childEle.getAttributeValue("name").equals(parent.getAttributeValue("name"))){
                               childEle.addContent(new Element(child.getName())); 
                            }
                        }
                    }
                }
                XmlDocument.writeDocument(schemapath);
        }
        }
        private void addColumnToTable(ElementNotFound elementNotF,Connection con) throws SQLException{
            Element parent=elementNotF.getParent();
            Element child=elementNotF.getChild();
            if(parent.getName().equals("entity")){
                if(parent.getAttributeValue("name")!=null){
                    String requete="ALTER TABLE "+parent.getAttributeValue("name")+" ADD "+child.getName()+" varchar(50);";
                    PreparedStatement pstmt=con.prepareStatement(requete);
                    pstmt.execute();
                }
        }
        }
     }
     private static Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try { 
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)    
                Class.forName(jdbcDriverClassName);
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();            
            }          
            catch(Exception e2) {
                e2.printStackTrace();              
            }       // Add your handling code here:
            return tmpcon;
        }    
}
