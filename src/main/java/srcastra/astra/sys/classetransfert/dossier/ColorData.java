/*
 * ColorData.java
 *
 * Created on 13 octobre 2002, 23:17
 */

package srcastra.astra.sys.classetransfert.dossier;
import java.awt.Color;
import srcastra.astra.sys.compta.*;
/**
 *
 * @author  Administrateur
 */
public class ColorData {
    
    /** Creates a new instance of ColorData */
    public ColorData(Color color,Object data) {
        m_color=color;
        m_data=data;
    }
    public ColorData(Double data){
     m_color=data.doubleValue()>=0 ? GREEN : RED;
     m_data=data;
    }
     public ColorData(Float data){
        m_color=data.floatValue()>=0 ? GREEN : RED;
        m_data=data;
    }
   /*  public ColorData(Double data,boolean tva){
        m_color=data.doubleValue()>=0 ? GREEN : RED;
        m_data=SpecialTva.fromFloaToString(m_data,data);
    }*/
      public ColorData(Float data,boolean tva){
        m_color=data.floatValue()>=0 ? GREEN : RED;
        m_data=SpecialTva.fromFloaToString(m_data,data);
    }
    public String toString(){
        return m_data.toString();
    }
    public Color m_color;
    public Object m_data;
    public static Color GREEN=new Color(0,128,0);
    public static Color RED=Color.red;
    
    
}
