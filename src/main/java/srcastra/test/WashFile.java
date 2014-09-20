/*
 * WashFile.java
 *
 * Created on 13 mai 2003, 17:28
 */

package srcastra.test;
import java.io.*;

/**
 *
 * @author  thomas
 */
public class WashFile {
    
    /** Creates a new instance of WashFile */
    public WashFile() {
        try {
        BufferedReader in = new BufferedReader(new FileReader("infilename"));
        String str;
        while ((str = in.readLine()) != null) {
            process(str);
        }
        in.close();
    } catch (IOException e) {
    }

    }
private void process(String str){
   System.out.println("l "+str.toString());   
}
}
