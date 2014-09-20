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
public class ColoredBackgTableCellRenderer extends DefaultTableCellRenderer{
    
    /** Creates a new instance of ColoredTableCellRenderer */
    public ColoredBackgTableCellRenderer() {
        
    }
    public void setValue(Object value){
        if(value instanceof srcastra.astra.gui.sys.utils.RowColor){
         setForeground(((srcastra.astra.gui.sys.utils.RowColor)value).m_color);
         setText(((srcastra.astra.gui.sys.utils.RowColor)value).m_data.toString());
        }
        else 
            super.setValue(value);
        
    }
    
}
