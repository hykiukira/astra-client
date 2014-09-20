/*
 * CheckCellRenderer.java
 *
 * Created on 6 novembre 2002, 8:56
 */

package srcastra.astra.gui.modules.dossier.utils;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author  Thomas
 */
public class CheckCellRenderer extends JCheckBox implements TableCellRenderer{
    
    /** Creates a new instance of CheckCellRenderer */

protected static Border m_noFocusBorder;

  public CheckCellRenderer() {
    super();
    m_noFocusBorder = new EmptyBorder(1, 2, 1, 2);
    setOpaque(true);
    setBorder(m_noFocusBorder);
  }

  public Component getTableCellRendererComponent(JTable table,
   Object value, boolean isSelected, boolean hasFocus, 
   int row, int column) 
  {
    if (value instanceof Boolean) {
      Boolean b = (Boolean)value;
      setSelected(b.booleanValue());
    }

    setBackground(isSelected && !hasFocus ? 
      table.getSelectionBackground() : table.getBackground());
    setForeground(isSelected && !hasFocus ? 
      table.getSelectionForeground() : table.getForeground());
        
    setFont(table.getFont());
    setBorder(hasFocus ? UIManager.getBorder(
      "Table.focusCellHighlightBorder") : m_noFocusBorder);

    return this;
  }



}
