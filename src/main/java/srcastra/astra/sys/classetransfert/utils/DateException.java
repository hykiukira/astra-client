/*
 * DateException.java
 *
 * Created on 26 août 2002, 11:42
 */

package srcastra.astra.sys.classetransfert.utils;

/**
 *
 * @author  Sébastien
 */
public class DateException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DateException</code> without detail message.
     */
    public DateException() {
    }
    
    
    /**
     * Constructs an instance of <code>DateException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DateException(String msg) {
        super(msg);
    }
}
