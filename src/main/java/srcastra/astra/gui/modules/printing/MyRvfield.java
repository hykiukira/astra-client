/*
 * MyRvfield.java
 *
 * Created on 14 mars 2003, 11:27
 */

package srcastra.astra.gui.modules.printing;
import com.java4less.rreport.*;
/**
 *
 * @author  Thomas
 */
public class MyRvfield extends RField{
    
    /** Creates a new instance of MyRvfield */
    public boolean sousproduit;
    public MyRvfield(int fcase,int color,String value,int igenerique,String sgenerique,java.awt.Font font) {
       super();
       this.font=font;
      // super.multiLine=true;
      // super.Expand=false;
       super.setruntimeValue(value);
       super.FontType=font;
     
    }
     public MyRvfield(int fcase,int color,String value,int igenerique,String sgenerique,java.awt.Font font,boolean sousproduit) {
       super();
       this.font=font;
      // super.multiLine=true;
      // super.Expand=false;
       super.setruntimeValue(value);
       super.FontType=font;
       this.sousproduit=sousproduit;
     
    }
    
    /** Getter for property font.
     * @return Value of property font.
     */
    public java.awt.Font getFont() {
        return font;
    }
    
    /** Setter for property font.
     * @param font New value of property font.
     */
    public void setFont(java.awt.Font font) {
        this.font = font;
    }
    
    java.awt.Font font;
    public boolean ajust=true;
}
