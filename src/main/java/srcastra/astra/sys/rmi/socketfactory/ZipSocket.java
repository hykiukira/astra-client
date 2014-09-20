/* * zipsocket.java * * Created on 22 mars 2002, 10:34 */
package srcastra.astra.sys.rmi.socketfactory;
/** * * @author  thomas * @version  */
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.*;
import java.net.Socket;
import java.io.IOException;
public class ZipSocket extends Socket implements java.io.Serializable{    
    private InputStream in;    
    private OutputStream out;        
    public ZipSocket() { 
        super();
    }    
    public ZipSocket(String host, int port)throws IOException {
        super(host, port);  
    }        
    public InputStream getInputStream()       {
        try{            
            if (in == null) {
            //in = new ZipInputStream(super.getInputStream());    
            //in = new InflaterInputStream(super.getInputStream());
            in=new CompressingInputStream(super.getInputStream());
            // in = super.getInputStream();
        }        
        }     
        catch(IOException e)      
            {
            e.printStackTrace();
            System.out.println("erreur dans zipsocket inputstream " + e);       
        }      
        catch(Exception e1)         
        {               
            e1.printStackTrace();
            System.out.println("erreur dans zipsocket inputstream excption" + e1);    
        }     
        return in;   
    }    
    public OutputStream getOutputStream()         {
        try{ 
            if (out == null) {  
                //out = new ZipOutputStream(super.getOutputStream());
                //out=new DeflaterOutputStream(super.getOutputStream());
                out=new CompressingOutputStream(super.getOutputStream()); 
               // out=super.getOutputStream();
            }      
        }          
        catch(IOException e)    
        {   e.printStackTrace(); 
            System.out.println("erreur dans zipsocket inputstream " + e);     
        }             catch(Exception e1)            { e1.printStackTrace();
         System.out.println("erreur dans zipsocket inputstream exception" + e1);     
        }          
        return out;   
    }     
    public synchronized void close() throws IOException {   
        if(out!=null){
            out.flush();
            out.close();
        }
        if(null!=in){
          in.close();   
        }
    }
}