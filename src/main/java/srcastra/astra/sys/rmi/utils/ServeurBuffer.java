/*
 * ServeurBufferinterf.java
 *
 * Created on 10 juillet 2002, 14:55
 */

package srcastra.astra.sys.rmi.utils;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.compress.*;
/**
 *
 * @author  rene
 * @version 1.0
 */
public interface ServeurBuffer {
    
    void setValue(String name, Object buf);
    CompressArray getValue(String name);
    Object getObjectValue(String name);
    long getTimestampValue(String name);
    void invalidateBuffer(String bufferName);
    void invalidate ();
    void invalidate (String bufferName);
    boolean isValid (String name);
    void importDirectory(String DirectoryName, ServeurBuffer buf);
    ServeurBuffer getDirectory(String DirectoryName);
    void linkNewName (String directoryName, String name);
    /** Setter for property time.
     * @param time New value of property time.
     */ 
     
    
    String[] getKeys();
    String dumpString(String prefix);
    
}
