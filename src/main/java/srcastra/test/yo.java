/*
 * yo.java
 *
 * Created on 1 septembre 2003, 10:01
 */

package srcastra.test;
import java.net.*;

/**
 *
 * @author  Thomas
 */
public class yo {
    
    /** Creates a new instance of yo */
    public yo() {
        URL path=getClass().getResource("config.txt");
        System.out.println("url"+path.toString());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new yo();
    }
    
}
