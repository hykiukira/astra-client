/*
 * SwitchColTable.java
 *
 * Created on 6 février 2003, 9:36
 */

package srcastra.astra.gui.sys.utils;

/**
 *
 * @author  Thomas
 */
public class SwitchColTable {
    
    /** Creates a new instance of SwitchColTable */
    public SwitchColTable() {
    }
    public static void switchCol(int langue,javax.swing.JTable table)
    {
     table.getColumnModel().moveColumn(langue-1,0);           
    }
    
}
