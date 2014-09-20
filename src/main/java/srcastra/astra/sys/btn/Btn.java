/*
 * Btn.java
 *
 * Created on 5 juin 2004, 20:14
 */

package srcastra.astra.sys.btn;
import java.io.*;
import org.jdom.*;
import java.util.*;
import java.sql.*;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.btn.workflow.*;
import srcastra.astra.sys.btn.utils.*;
//import xmlgeneration.XmlDocument;
/**
 *
 * @author  administrateur
 */
public class Btn {
    boolean check=false;
    String filename;
    ArrayList elementNotFound;
    Connection con;
    final static String driver="com.mysql.jdbc.Driver";
    long id=0;
    /** Creates a new instance of Btn */
    public Btn() {
        try{
            con=testConnection("root", "XkLm2000", "astra", "3306", "localhost");
            Document schema=XmlDocument.readDocument("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml");
            Element element=schema.getRootElement();
            createTable(element);
         }catch(Exception sn){
            sn.printStackTrace();
        }
    }
    public Btn(boolean insert) {
        try{
            File[] file=getXmlFileIndirectory("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco");
            con=testConnection("root", "XkLm2000", "astra", "3306", "localhost");
            for(int i=0;i<file.length;i++){
                System.out.println(file[i].getAbsolutePath());
                Document schema=XmlDocument.readDocument(file[i].getAbsolutePath());
                Element element=schema.getRootElement();
                if(element!=null){
                List list=element.getChildren();
                Iterator ite=list.iterator();
                while(ite.hasNext()){
                    Element child=(Element)ite.next();
                    insertIntoTable(child);
                }
            }
            }
           
            
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
            //drop="DROP table "+element.getAttributeValue("name");
           // pstmt=con.prepareStatement(drop);
           // pstmt.execute();
            table="CREATE TABLE "+element.getAttributeValue("name")+"(\n";
            List list=element.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
                Element child=(Element)ite.next();
                fields=fields+child.getName()+" varchar(50),\n";
            }
            //fields=fields.substring(0,(fields.length()-2));
            if(element.getAttributeValue("name").equals("T_Reservering")){
                fields=fields+"PRIMARY KEY(id)\n";
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
            //drop="DROP table "+element.getAttributeValue("name");
           // pstmt=con.prepareStatement(drop);
           // pstmt.execute();
          //  table="CREATE TABLE "+element.getAttributeValue("name")+"(\n";
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
    public Btn(String directory,String schemapath) {
        try{
            File[] files=getFileIndirectory(directory);
            con=testConnection("root", "XkLm2000", "astra", "3306", "localhost");
            if(files!=null){
                for(int i=0;i<files.length;i++){
                    readFile(files[i].getAbsolutePath(),"C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml");
                }
            }
            else{
              System.out.println("files=null");   
            
            }
        }catch(Exception sn){
            sn.printStackTrace();
        }
        
        }
     public Btn(String schemapath){
         try{
            reorganiseMain(schemapath);
         }catch(Exception sn){
            sn.printStackTrace();
        }
     }
     public Btn(String directory,String schemapath,boolean construct) {
            File[] files=getFileIndirectory(directory);
            //System.out.println("file : "+file);
            File schemaF=new File(schemapath);
            Document schema=null;
            boolean newSchema=false;
            Element schemaRoot=null;
            try {
            if(!schemaF.exists()){
                schemaRoot=new Element("BTNROOT");
                XmlDocument.getXmlDocument().generateDocument(schemaRoot);
            
            }
            else{
                schema=XmlDocument.readDocument(schemapath);
                schemaRoot=schema.getRootElement();               
            }
            if(files!=null){
                for(int i=0;i<files.length;i++){
                    constructBtnSchema(files[i].getAbsolutePath(),schemaRoot);
                }
                  XmlDocument.writeDocument(schemapath);
            }
            else{
              System.out.println("files=null");   
            
            }
                 
     }catch(java.io.IOException in){
       in.printStackTrace();   
     }
   
        
        }
        
    private void readFile(String file,String schemaPath) throws SQLException{
     try {
        System.out.println("file : "+file);
        Document schema=XmlDocument.readDocument(schemaPath);
        Element schemaRoot=schema.getRootElement();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        Element entity=null;
        Element root=new Element("BTNROOT");
        Element field=null;
        while ((str = in.readLine()) != null) {
             Element element=createNode(str,true);
             if (element!=null){
                 checkElementValidy(element, schemaRoot);
                 addElement(element, check);               
                 check=false;
                if(element.getName().equals("entity")){
                 entity=element;
                 root.addContent(entity);
             }
             else if(!element.getName().equals("entity")){
               field=element;  
               entity.addContent(element);   
             }
             else if(element.getName().equals("addon")){
                 if(field!=null){
                   field.getChild("code_btn").setText(field.getChild("code_btn").getText()+element.getText());  
                 
                 }
             }
             }
         }
        checkError();
        getAstraCode(root, 1);
//        ReservationBrochureBuilder reser=new ReservationBrochureBuilder(root); 
     //d   System.out.println(reser.retval);
       
        XmlDocument.getXmlDocument().generateDocument(root);
        XmlDocument.writeDocument("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN"+filename+".xml");
         
     }catch(java.io.IOException in){
       in.printStackTrace();   
     }
     catch(FieldNotFoundException fn){
         fn.showUnfoundElement();
         fn.printStackTrace();
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
    private void getAstraCode(Element root,int langue) throws SQLException{
        if(root!=null){
            if(root.getAttribute("table")!=null ){
                String table=root.getAttributeValue("table");
                if(!table.equals("")){
                    String code=root.getChild("code_btn").getText();
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
    private void constructBtnSchema(String file,Element schemaRoot) throws java.io.IOException{
    
        
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        Element entity=null;
        Element root=new Element("BTNROOT");
        Element field=null;
        while ((str = in.readLine()) != null) {
             Element element=createNode(str,false);
             if (element!=null){
                
                 //addElement(element, check);               
                 //check=false;
      //       if(element.getAttributeValue("name").equals("roker")){
                 
        //     ;
          //   }
             if(element.getName().equals("entity")){
                // System.out.println("Entity "+element.getAttributeValue("name"));
                 entity=element;
                 Element tmp=addEntity(entity,schemaRoot);
                 if(tmp!=null){
                     entity=tmp;
                 }
                 else{
                     schemaRoot.addContent(entity);
                     System.out.println("NOT found "+entity.getAttributeValue("name"));
                 }
               //  if(check==false){
                 //    schemaRoot.addContent(entity);
                  //  }
                 ///check=false;
                 
                // root.addContent(entity,schemaRoot);
             }
             else {//(element.getName().equals("field")){
               field=element;  
               System.out.println("     Field "+element.getName());
               addField2(entity,field);
               //addEntity(field,schemaRoot);
               //if(check==false){   
                //  addField(entity,schemaRoot,field);
                
              // }check=false;
             }
            // else if(element.getName().equals("addon")){
                 
             //}
             }
         }
        //checkError();
     
        
      
    
 //    catch(FieldNotFoundException fn){
   //      fn.showUnfoundElement();
    // }
    }
    private void checkError() throws FieldNotFoundException{
        if(elementNotFound!=null)
            throw new FieldNotFoundException(elementNotFound,null);
    }
    private void addElement(Element element,boolean check){
         if(check==false){
                   if(elementNotFound==null)
                       elementNotFound=new ArrayList();
                   elementNotFound.add(element);                 
                 }
    }
    private File[] getFileIndirectory(String directoryName){
    File dir = new File(directoryName);
    FilenameFilter filter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.endsWith(".BTN");
        }
    };
    File[] children = dir.listFiles(filter);
    if (children == null) {
        return null;
        // Either dir does not exist or is not a directory
    } else {
       
    }    
        return children;            
    }
    private File[] getXmlFileIndirectory(String directoryName){
        File dir = new File(directoryName);
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml") && !name.equals("BTN.xml");
            }
    };
    File[] children = dir.listFiles(filter);
    if (children == null) {
        return null;
        // Either dir does not exist or is not a directory
    } else {
       
    }
    
    // It is also possible to filter the list of returned files.
    // This example does not return any files that start with `.'.
   
  
    return children;
        
    
    }
    private void checkElementValidy(Element newElement,Element schemaElement){
       // System.out.println("new element"+newElement.getName()+" schemaElement "+schemaElement.getName());
        if(newElement.getName().equals("addon"))
            check=true;
        if(check==true)return;
       
       // System.out.println("");
        //System.out.println("new element"+newElement.getName()+" schemaElement "+schemaElement.getAttributeValue("name"));
        if(!newElement.getName().equals("entity") && newElement.getName().toLowerCase().equals(schemaElement.getName().toLowerCase().trim())){
            //System.out.println("new element"+newElement.getName()+" schemaElement "+schemaElement.getAttributeValue("name"));
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
    private Element addEntity(Element entity,Element schemaElement){
      //  System.out.println(schemaElement.getAttributeValue("name"));
        
      //  if(check==true)return;
        
        if(entity.getAttributeValue("name")!=null && schemaElement.getAttributeValue("name")!=null && entity.getAttributeValue("name").toUpperCase().equals(schemaElement.getAttributeValue("name").toUpperCase())){
        //  check=true;
         // return;
         System.out.println("found "+schemaElement.getAttributeValue("name"));
         return schemaElement;
        }else{
            List list=schemaElement.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
             Element child=(Element)ite.next();
             Element tmp=addEntity(entity, child);
             if(tmp!=null)
                 return tmp;
            }
        } 
        return null;
    
    }
     private void addField(Element entity,Element schemaElement,Element field){
        if(check==true)return;
        if(entity.getAttributeValue("name").equals(schemaElement.getAttributeValue("name"))){
          schemaElement.addContent(field);
          check=true;
          return;
        }else{
            List list=schemaElement.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
             Element child=(Element)ite.next();
             addField(entity,child,field);                                                        
            }
        } 
    
    }
      private void addField2(Element entity,Element field){
      
            List list=entity.getChildren();
            Iterator ite=list.iterator();
            boolean sw=false;
            while(ite.hasNext()){
             Element child=(Element)ite.next();
             if(child.getName().toUpperCase().trim().equals(field.getName().toUpperCase().trim()))
                 sw=true;                        
        } 
            if(!sw){
                entity.addContent(field);
               // System.out.println("adding "+field.getName());
            }
    
    }
    private void checkElementValidy2(Element newElement,Element schemaElement,Element entite){
    //    System.out.println("new element"+newElement.getName()+" schemaElement "+schemaElement.getName());
        if(newElement.getName().equals("addon"))
            check=true;
        if(check==true)return;
        if(newElement.getAttributeValue("name").equals(schemaElement.getAttributeValue("name"))){
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
    public Element createNode(String str,boolean value){
        if(str!=null && !str.equals("")){
            if(str.indexOf("[")==0){
            //  System.out.println("entity"+str);   
              return entity(str,value);
            }
            else{
             // System.out.println(str);   
              return field(str,value);
            }
         
            }
        return null;
            //Element element=new Element();
            
        }
    
    
    private Element entity(String str,boolean value){
       // System.out.println(str);
        if(str.substring(0,1).equals("[")){
            str=str.substring(1,str.length());
        }
        if(str.substring(str.length()-1,str.length()).equals("]")){
            str=str.substring(0,str.length()-1);        
        }        
        StringTokenizer token=new StringTokenizer(str," ");
        Element element=new Element("entity");
        element.setAttribute("name",token.nextToken());
        //element.setAttribute("table","");
        return element;
    }
    private Element field(String str,boolean valueb){
        //System.out.println("field "+str);
        try{
        StringTokenizer token=new StringTokenizer(str,"=");
        int tokenLenght=token.countTokens();
        String text=token.nextToken().trim();
      //  System.out.println("name "+text);
        Element element=new Element(text);
        if(tokenLenght!=2){
            
            if(valueb){
                Element value=new Element("code_btn");
                str=str.substring(text.length()+1,str.length());
                value.addContent(str);
                element.addContent(value);
            }
            return element;
        }
       
       // element.setAttribute("name",token.nextToken());       
      //  if(element.getAttributeValue("name").equals("faciltext"))
        //    return null;
       // if(element.getAttributeValue("name").equals("totext"))
         //   return null;
        if(element.getName().equals("faciltext"))
            return null;
        if(element.getName().equals("totext"))
            return null;
        Element value=new Element("code_btn");
        value.addContent(token.nextToken());
      //  System.out.println("value "+value.getText());
       // value.setAttribute("table","");
         if(element.getName().equals("boekingsnr")){
            filename=value.getText();
        
        }
        if(valueb)
            element.addContent(value);
       // element.setAttribute("code_btn",token.nextToken());
        return element;
        }catch(org.jdom.IllegalNameException in){
            return null;
        }
    }
    
    //private String entityNode(){
        
    //}
    /**
     * @param args the command line arguments
     */
    public void reorganiseMain(String path) throws Exception{      
        Document schema=XmlDocument.readDocument(path);
        reorganiseBtnFile(schema.getRootElement());
     //   XmlDocument.getXmlDocument().generateDocument(schema.getRootElement());
        XmlDocument.writeDocument("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml");
    }
    private void reorganiseBtnFile(Element root){
            if(root.getName().equals("field")){
                root.setName(root.getAttributeValue("name").trim());
                System.out.println(root.getName()+"updated");
            }
            List list=root.getChildren();
            Iterator ite=list.iterator();
            while(ite.hasNext()){
             Element child=(Element)ite.next();
             reorganiseBtnFile(child);                                                        
            }
    }
    public static void main(String[] args) {
      // new Btn("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml");
    //   new Btn("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\btn_codage","C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml",true);
       // new Btn("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\btn_codage","C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\BTN.xml");
          // Btn(String directory,String schemapath,boolean construct) 
       // new Btn();
        new Btn(true) ;
    }
    public Connection testConnection(String login,String password,String database,String port,String ip) throws Exception{      
        Class.forName(driver).newInstance() ;
        Connection tmpcon = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database+"?autoReconnect=true",login,password);
       // PreparedStatement pstmt=tmpcon.prepareStatement("CONNECT "+database);        
        return tmpcon;
    }
    
}
