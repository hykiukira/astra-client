/*
 * GetData.java
 *
 * Created on 22 août 2003, 9:33
 */

package srcastra.astra.sys.compta;
import srcastra.astra.sys.compta.checkcompte.*;
/**
 *
 * @author  thomas
 */
public class GetData {
    
    /** Creates a new instance of GetData */
    private GetData() {
    }
    public static CompteData getCompteData(String key){
      return CompteData.getCompteData(key);   
    }
    public static void test(String key){
    CompteData.test(key);
}
    
}
