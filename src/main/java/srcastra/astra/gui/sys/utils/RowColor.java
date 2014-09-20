/*
 * RowColor.java
 *
 * Created on 7 novembre 2002, 15:46
 */

package srcastra.astra.gui.sys.utils;
import java.awt.Color;

/**
 *
 * @author  Thomas
 */
public class RowColor {
    
    /** Creates a new instance of RowColor */
    public RowColor(Object data,int i) {
        
        m_color=(i%2==0)?GREEN:Color.blue;
        m_data=data;
    }
    public RowColor(Float data){
     m_color=data.floatValue()>=0 ? GREEN : RED;
     m_data=data;
    }
    public String toString(){
        return m_data.toString();
    }
    public Color m_color;
    public Object m_data;
    public static Color GREEN=new Color(0,128,0);
    public static Color RED=Color.red;
    
}
