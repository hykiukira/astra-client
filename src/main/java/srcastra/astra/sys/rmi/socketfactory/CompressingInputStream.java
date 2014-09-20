/*
 * CompressingOutputStream.java
 *
 * Created on 2 octobre 2003, 17:53
 */

package srcastra.astra.sys.rmi.socketfactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.*;
import java.net.Socket;
import java.io.IOException;
/**
 *
 * @author  Thomas
 */
public class CompressingInputStream extends InputStream{
    
    /** Creates a new instance of CompressingOutputStream */
    private InputStream actual;
    private GZIPInputStream delegate;
    public CompressingInputStream(InputStream tmp) {
        actual=tmp;
    }
    
   
    public void close()throws java.io.IOException{
         if(null!=delegate){
          delegate.close();   
        }
         else{
          actual.close();   
         }
        
    }
     /*public void flush()throws java.io.IOException{
         if(null!=delegate){
          delegate.ffinish();   
        }
        
    }*/
    
     public int read() throws java.io.IOException {
        if(null==delegate){
          delegate=new GZIPInputStream(actual);   
        }
        return delegate.read();
        
     }
     public int read(byte[] buf, int off, int len) throws java.io.IOException {
        if(null==delegate){
          delegate=new GZIPInputStream(actual);   
        }
        return delegate.read(buf,off,len);
        
     }
     
}
