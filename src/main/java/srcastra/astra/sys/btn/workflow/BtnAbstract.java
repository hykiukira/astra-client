/*
 * BtnAbstract.java
 *
 * Created on 13 septembre 2004, 19:49
 */

package srcastra.astra.sys.btn.workflow;
import java.sql.*;
import org.jdom.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class BtnAbstract {
    
    /** Creates a new instance of BtnAbstract */
    protected String filename;
    protected Connection con;
    public BtnAbstract() {
    }
    public BtnAbstract(Connection con) {
        this.con=con;
    }
    protected File[] getFileIndirectory(String directoryName,FilenameFilter filter){
        File dir = new File(directoryName);        
        File[] children = dir.listFiles(filter);
        if (children == null) {
            return null;
        } else {

        }
        return children;
    }
    protected boolean moveFile(File destination,File file){
        return file.renameTo(new File(destination, file.getName()));
    }
    public Element createNode(String str,boolean value){
        if(str!=null && !str.equals("")){
            if(str.indexOf("[")==0){           
              return entity(str,value);
            }
            else{           
              return field(str,value);
            }         
            }
        return null;           
        }
     private Element field(String str,boolean valueb){        
        try{
        StringTokenizer token=new StringTokenizer(str,"=");
        int tokenLenght=token.countTokens();
        String text=token.nextToken().trim();
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
        if(element.getName().equals("faciltext"))
            return null;
        if(element.getName().equals("totext"))
            return null;
        Element value=new Element("code_btn");
        value.addContent(token.nextToken());
        if(element.getName().equals("boekingsnr")){
            filename=value.getText();        
        }
        if(valueb)
            element.addContent(value);      
        return element;
        }catch(org.jdom.IllegalNameException in){
            return null;
        }
    }
        private Element entity(String str,boolean value){       
            if(str.substring(0,1).equals("[")){
                str=str.substring(1,str.length());
            }
            if(str.substring(str.length()-1,str.length()).equals("]")){
                str=str.substring(0,str.length()-1);        
            }        
            StringTokenizer token=new StringTokenizer(str," ");
            Element element=new Element("entity");
            element.setAttribute("name",token.nextToken());
            return element;
    }
     private StringTokenizer getToken(String str,String tokeniser){
         StringTokenizer token=new StringTokenizer(str,tokeniser);
         return token;
     }
}
