/*
 * MaiLing.java
 *
 * Created on 12 mai 2004, 17:25
 */

package srcastra.astra.sys;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.activation.*;
import java.io.*;
import java.awt.datatransfer.*;
/**
 *
 * @author  Administrateur
 */
public class Mailing {
    
    /** Creates a new instance of MaiLing */
    public Mailing() {
    }
    public static void sendMail(String SMTP,String tos,String froms,String sujet,String body){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP);
         
            Session mailConnection = Session.getInstance(props, null);
            final Message msg = new MimeMessage(mailConnection);
  
            Address to = new InternetAddress(tos);
            Address from = new InternetAddress(froms);
      
  
       // DataHandler datahand=new DataHandler(new com.sun.activation.viewers.TextViewer(),"text/plain");
        
       // msg.setDataHandler(datahand);
     /*   File file=new File("c:\\config.txt");
        DataSource ds=new FileDataSource(file);
        DataHandler dh=new DataHandler(ds);
        Object content=dh.getContent();
        if(content!=null){
            System.out.println( "*************Fucking file"+content.getClass().getName());
        }
        else{
            System.out.println("*****************content null");
        }*/
      //  msg.setDataHandler(getDataHandler());

       // msg.setDat
        //String str = new String("This is a test");
	//DataHandler dataHandler  = new DataHandler(str, "text/plain");
        //DataContentHandlerFactory dchf = new SimpleDCF("text/plain:PlainDCH\n");
        //dataHandler.setDataContentHandlerFactory(dchf);
       
       // DataHandler dataHandler=getDataHandler();        
        //msg.setDataHandler(dataHandler);
        //System.out.println("[*************]"+dataHandler.getContentType());
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(sujet);
        msg.setContent(body, "text/plain");
        
        // This can take a non-trivial amount of time so 
        // spawn a thread to handle it. 
        Runnable r = new Runnable() {
          public void run() {
            try {
              Transport.send(msg);
            }
            catch (Exception e) {
              e.printStackTrace(); 
            }
          } 
        };
        Thread t = new Thread(r);
        t.start();
        
       // message.setText("");
    }
      catch (Exception e) {
        // We should really bring up a more specific error dialog here.
        e.printStackTrace(); 
      }
    }
     public static void sendMail2(String SMTP,String tos,String froms,String sujet,String body,String signature) throws Exception{
      
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP);
         
        Session mailConnection = Session.getInstance(props, null);
        final Message msg = new MimeMessage(mailConnection);
  
        Address to = new InternetAddress(tos);
        Address from = new InternetAddress(froms);
        body=body+"\n\n\n"+signature;
       
        msg.setContent(body, "text/plain");
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(sujet);
        
        
        // This can take a non-trivial amount of time so 
        // spawn a thread to handle it. 
        Runnable r = new Runnable() {
          public void run() {
            try {
              Transport.send(msg);
            }
            catch (Exception e) {
              e.printStackTrace(); 
            }
          } 
        };
        Thread t = new Thread(r);
        t.start();
        
       // message.setText("");
    }
      public  static FileDataSource  setFile(String filename) {
	FileDataSource fds = new FileDataSource(filename);
	System.out.println("DCHTest: FileDataSource created");
        
	if(!fds.getContentType().equals("text/plain")) {
	    System.out.println("Type must be text/plain");
            System.out.println(fds.getContentType());
	   // System.exit(1);
	}
        else {
            
        }
        return fds;
    }
     private static DataHandler getDataHandler(){
        FileDataSource fds=setFile("c:\\config.txt");
        DataFlavor xfer_flavors[];
	Object content = null;
        
	DataHandler dh = new DataHandler(fds);
	System.out.println("DCHTest: DataHandler created");

	// now lets set a DataContentHandlerFactory
	DataContentHandlerFactory  dchf = new SimpleDCF("text/plain:PlainDCH\n");
	System.out.println("DCHTest: Simple dchf created");
	
	// now let's set the dchf in the dh
	dh.setDataContentHandlerFactory(dchf);
	System.out.println("DCHTest: DataContentHandlerFactory set in DataHandler");
	
	// get the dataflavors
	xfer_flavors = dh.getTransferDataFlavors();
	System.out.println("DCHTest: dh.getTransferDF returned " +
			   xfer_flavors.length + " data flavors.");

	// get the content:
        try {
	   content = dh.getContent();
        } catch (Exception e) { e.printStackTrace(); }

	if(content == null)
	    System.out.println("DCHTest: no content to be had!!!");
	else
	    System.out.println("DCHTest: got content of the following type: " +
			       content.getClass().getName());
        return dh;
     
     }
     public static void sendMailWithAttachement(String SMTP,String tos,String froms,String sujet,String body,String filename,String signature) throws Exception{
      
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP);
//        froms="corson.vincent@xs4all.be";
        //filename="C:\\Documents and Settings\\Administrateur\\AstraDir\\compta\\BHB400851.pdf";
        Session mailConnection = Session.getInstance(props, null);
        final Message msg = new MimeMessage(mailConnection);
  
        Address to = new InternetAddress(tos);
        Address from = new InternetAddress(froms);
      
       // msg.setContent(body, "text/plain");
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(sujet);
        
        
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(body+"\n\n\n"+signature);

	    // create the second message part
	    MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
   	    FileDataSource fds = new FileDataSource(filename);
	    mbp2.setDataHandler(new DataHandler(fds));
	    mbp2.setFileName(fds.getName());

	    // create the Multipart and add its parts to it
	    Multipart mp = new MimeMultipart();
	    mp.addBodyPart(mbp1);
	    mp.addBodyPart(mbp2);

	    // add the Multipart to the message
	    msg.setContent(mp);

	    // set the Date: header
	    msg.setSentDate(new Date());
        
        // This can take a non-trivial amount of time so 
        // spawn a thread to handle it. 
        Runnable r = new Runnable() {
          public void run() {
            try {
              Transport.send(msg);
            }
            catch (Exception e) {
              e.printStackTrace(); 
            }
          } 
        };
        Thread t = new Thread(r);
        t.start();
        
       // message.setText("");
    }
    
    
    
}
