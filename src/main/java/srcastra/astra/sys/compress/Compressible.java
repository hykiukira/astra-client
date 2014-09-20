/*
 * Compressable.java
 *
 * Created on 10 juillet 2002, 14:27
 */

package srcastra.astra.sys.compress;

import java.io.*;
/**
 *
 * @author  rene
 * @version 
 */
public interface Compressible extends Externalizable {

    void compressNow();
    
    void decompressNow();
    
    boolean isCompressed();
    
}

