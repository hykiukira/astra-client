/*
 * CustomTableCellRender.java
 *
 * Created on 28 juin 2006, 13:56
 */

package srcastra.astra.gui.modules.dossier;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.*;


/**
 * @author vincent
 */
public class CustomTableCellRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent
            (JTable table, Object value, boolean isSelected,
             boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent
                (table, value, isSelected, hasFocus, row, column);

        boolean first = false;

        if (column == 0) {
            if (value.toString().length() > 8 && (value.toString().charAt(8) == 'F' || value.toString().charAt(8) == 'N')) {
                cell.setBackground(Color.CYAN);
                cell.setForeground(Color.black);
                first = true;
            } else if (value.toString().length() > 8 && (value.toString().charAt(8) == 'G')) {
                if (value.toString().charAt(9) == '0')
                    cell.setBackground(Color.black);
                else
                    cell.setBackground(Color.RED);

                cell.setForeground(Color.white);
                first = true;
            } else {
                cell.setBackground(Color.white);
                cell.setForeground(Color.black);
            }
        } else {
            cell.setBackground(Color.white);
            cell.setForeground(Color.black);
        }

        //setSelectionBackground();
        if (isSelected) {
            if (!first)
                cell.setBackground(new java.awt.Color(221, 221, 255));
        }

//return(this); 

        return cell;


    }
}