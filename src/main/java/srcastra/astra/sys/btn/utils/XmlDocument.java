/*
 * XmlDocument.java
 *
 * Created on 17 août 2003, 12:39
 */

package srcastra.astra.sys.btn.utils;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.XMLOutputter;
import java.math.BigInteger;
import java.io.IOException;
import java.io.*;


/**
 *
 * @author  thom
 */
public class XmlDocument {
    static Document doc;
    Element root;
    private static XmlDocument xmldocument;
    private static final Object classLock = XmlDocument.class;
    public static XmlDocument getXmlDocument(){
        synchronized (classLock) { 
            if (xmldocument == null) 
            {  
                xmldocument = new XmlDocument();
            } return xmldocument; 
        }
    }
    /** Creates a new instance of XmlDocument */
    public XmlDocument( Element element) {
        doc=new Document(element);
        //    Element root = new Element("GREETING");
          //  root.setText("Hello JDOM!");
            ///Document doc = new Document(root); 
    }
     public XmlDocument( ) {
     
        //    Element root = new Element("GREETING");
          //  root.setText("Hello JDOM!");
            ///Document doc = new Document(root); 
    }
    public  void generateDocument(Element element){    
         root= element;
         doc = new Document(root);
    }
    public  void generateDocument(String document,String user){    
         //root= XmlNode.generateProjectNode(document,user);
       /*  doc = new Document(root);

   /* BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;

    for (int i = 1; i <= 5; i++) {
      Element fibonacci = new Element("fibonacci");
      fibonacci.setAttribute("index", String.valueOf(i));
      fibonacci.setText(low.toString());
      root.addContent(fibonacci);

      BigInteger temp = high;
      high = high.add(low);
      low = temp;
    }

    
    // serialize it onto System.out
    try {
      XMLOutputter serializer = new XMLOutputter();
      BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(MainFrame.PATH+"/"+document+".xml"));
      serializer.output(doc, out);
      out.close();
    }
    catch (IOException e) {
      System.err.println(e);
    }
        */
    }
    public  static void writeDocument(){    
        /*try {
      XMLOutputter serializer = new XMLOutputter();
      System.out.println("[****]mainframe"+MainFrame.PATH);
      BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(MainFrame.PATH+"/"+doc.getRootElement().getAttribute("name").getValue()+".xml"));
      serializer.output(doc, out);
      out.close();
    }
    catch (IOException e) {
      System.err.println(e);
    }
        */
    }
     public  static void writeDocument(String file){    
      try {
      XMLOutputter serializer = new XMLOutputter();
     // System.out.println("[****]mainframe"+MainFrame.PATH);
      BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file));
      serializer.output(doc, out);
      out.close();
    }
    catch (IOException e) {
      System.err.println(e);
    }
        
    }
    public  static Document readDocument(String document)throws IOException{    
    try {
      SAXBuilder builder = new SAXBuilder();
      doc = builder.build(new File(document));
     

    }
    catch(JDOMException jn){
     jn.printStackTrace();   
    }
    return doc;
        
    }
public static void main(String[] args){
    new XmlDocument().generateDocument("ComptaTest","Thomas Dussart");
}    

/** Getter for property doc.
 * @return Value of property doc.
 *
 */
public org.jdom.Document getDoc() {
    return doc;
}

/** Setter for property doc.
 * @param doc New value of property doc.
 *
 */
public void setDoc(org.jdom.Document doc) {
    this.doc = doc;
}

}
