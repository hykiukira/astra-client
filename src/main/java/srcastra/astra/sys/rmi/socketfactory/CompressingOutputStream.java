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
public class CompressingOutputStream extends OutputStream{
    
    /** Creates a new instance of CompressingOutputStream */
    private OutputStream actual;
    private GZIPOutputStream delegate;
    public CompressingOutputStream(OutputStream tmp) {
        actual=tmp;
    }
    
    public void write(int param) throws java.io.IOException {
        if(null==delegate){
          delegate=new GZIPOutputStream(actual);   
        }
       //delegate.write(param);
        return;
    }
    public void write(byte[] buf, int off, int len) throws java.io.IOException {
           if(null==delegate){
          delegate=new GZIPOutputStream(actual);   
        }
	delegate.write(buf, off, len);
       //delegate.write(param);
        return;
        
    }
    public void close()throws java.io.IOException{
         if(null!=delegate){
          delegate.close();   
        }
         else{
          actual.close();   
         }
        
    }
     public void flush()throws java.io.IOException{
         if(null!=delegate){
        //  actual.flush();
         // delegate.flush();
          delegate.finish();   
        }     
    }
    
}
