/*
 * CalendarTableRenderer.java
 *
 * Created on 23 mars 2004, 12:02
 */

package srcastra.astra.gui.modules.agenda;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Administrateur
 */
public class CalendarTableRenderer extends DefaultTableCellRenderer {

    /**
     * Creates a new instance of CalendarTableRenderer
     */
    public CalendarTableRenderer() {
    }

    public void setValue(Object value) {
        if (value instanceof CalendarDay_T) {
            if (((CalendarDay_T) value).isTask())
                setForeground(java.awt.Color.RED);
            else
                setForeground(java.awt.Color.BLACK);
            setText(new Integer(((CalendarDay_T) value).getDay()).toString());
        } else
            super.setValue(value);
    }

}
