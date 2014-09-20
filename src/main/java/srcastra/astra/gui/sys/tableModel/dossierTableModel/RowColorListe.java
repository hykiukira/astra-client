/*
 * RowColorListe.java
 *
 * Created on 21 novembre 2002, 10:07
 */

package srcastra.astra.gui.sys.tableModel.dossierTableModel;
import java.awt.Color;
/**
 *
 * @author  Thomas
 */
public class RowColorListe implements InterfaceContanteColor{
    
    /** Creates a new instance of RowColorListe */
    public RowColorListe() {
    }
     public RowColorListe(Object data,int i) {
        
      //  m_color=(i%2==0)?GREEN:Color.blue;
        if(i==CLIP)
            m_color=CLIPC;
        else if(i==CLIM)
            m_color=CLIMC;
        else if(i==ACHAT)
            m_color=ACHATC;
        else if(i==VENTE)
            m_color=VENTEC;
        m_data=data;
    }
 
    public String toString(){
        return m_data.toString();
    }
    public Color m_color;
    public Object m_data;
    public static Color CLIPC=new Color(0,153,153);
    public static Color CLIMC=new Color(204,51,51);
    public static Color ACHATC=new Color(255,153,51);
    public static Color VENTEC=new Color(51,102,204);
    
    public static Color RED=Color.red;
   
}
    

