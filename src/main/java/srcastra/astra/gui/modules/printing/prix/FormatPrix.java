/*
 * FormatPrix.java
 *
 * Created on 28 mars 2003, 15:21
 */

package srcastra.astra.gui.modules.printing.prix;
import srcastra.astra.sys.*;
import srcastra.astra.gui.modules.printing.MyRvfield;
/**
 *
 * @author  Thomas
 */
public class FormatPrix {
    
    /** Creates a new instance of FormatPrix */
    public FormatPrix() {
    }
    public static void formatField(MyRvfield field){
     String tmp=(String)field.getruntimeValue();
     int i=tmp.indexOf(".");
     if(i!=-1){
        String sub=tmp.substring(i+1);
        Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"Substrint = "+sub);
        if(sub.length()==1){
            field.setruntimeValue(tmp+"0");
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"New value = "+field.getruntimeValue());
        }
            
     }
    }
    
}
