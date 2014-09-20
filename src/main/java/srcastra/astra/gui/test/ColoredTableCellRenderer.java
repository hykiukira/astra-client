/*
 * ColoredTableCellRenderer.java
 *
 * Created on 13 octobre 2002, 23:15
 */

package srcastra.astra.gui.test;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author  Administrateur
 */
public class ColoredTableCellRenderer extends DefaultTableCellRenderer{
    
    /** Creates a new instance of ColoredTableCellRenderer */
    public ColoredTableCellRenderer() {
        
    }
    public void setValue(Object value){
        if(value instanceof srcastra.astra.sys.classetransfert.dossier.ColorData){
         setForeground(((srcastra.astra.sys.classetransfert.dossier.ColorData)value).m_color);
         setText(((srcastra.astra.sys.classetransfert.dossier.ColorData)value).m_data.toString());
        }
        else 
            super.setValue(value);
        
    }
    
}
