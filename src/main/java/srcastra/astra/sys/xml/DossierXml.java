/*
 * DossierXml.java
 *
 * Created on 31 mars 2004, 14:14
 */

package srcastra.astra.sys.xml;
import org.jdom.input.*; 
import org.jdom.*;
import java.io.*;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
/**
 *
 * @author  Administrateur
 */
public class DossierXml {
    
    /** Creates a new instance of DossierXml */
    public DossierXml() {
        try{
         builder=new SAXBuilder();
         doc=builder.build(new File("C:\\Documents and Settings\\Administrateur\\Mes documents\\astra\\dossierxml.xml"));
        }catch(JDOMException jn){
          jn.printStackTrace();   
        }
        catch(IOException in){
          in.printStackTrace();   
        }
    }
    public static void  main(String [] args){
        new DossierXml();
    }
    public void run(Element root,String type){
        
        
        
    }
    SAXBuilder builder;
    Document  doc;
    public class BrochureCommand{
      Element element;
      Brochure_T brochure;
      GeneralePrinting printinginfo;
      public void run(){
          element.clone(); 
        //  element.setAttribute("organisateur",brochure.
          
      };   
    }
}
