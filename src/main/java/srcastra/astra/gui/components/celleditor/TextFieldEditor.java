/*

 * ListeCellEditor.java

 *

 * Created on 22 juillet 2003, 10:46

 */


package srcastra.astra.gui.components.celleditor;

import srcastra.astra.gui.components.combobox.liste.*;

import javax.swing.*;

import srcastra.astra.gui.components.textFields.*;

import java.util.*;

import java.awt.event.*;

import srcastra.astra.gui.modules.compta.achat.*;


/**
 * @author thomas
 */

public class TextFieldEditor extends AbstractCellEditor implements javax.swing.table.TableCellEditor, srcastra.astra.gui.components.celleditor.CellEditorInterface {


    /**
     * Creates a new instance of ListeCellEditor
     */

    ATextField text;

    int position;

    ArrayList cellListener = new ArrayList();

    MouseAdapter mouse;

    boolean montant;

    AchatTableModel model;

    EditTable edit;

    KeyAdapter key = new KeyAdapter() {

        public void keyPressed(java.awt.event.KeyEvent evt) {

            if (evt.getKeyCode() == evt.VK_ENTER) {

                key(evt);

            }

        }

    };

    private void key(java.awt.event.KeyEvent evt) {

        System.out.println("fucking key pressed");

        edit.action(this);

    }

    FocusAdapter focus = new FocusAdapter() {

        public void focusLost() {

            fireEditingStopped();


        }

    };

    public TextFieldEditor(ATextField text, int position, boolean montant, AchatTableModel model, MouseAdapter mouse, EditTable edit) {

        this.text = text;

        this.position = position;

        this.text.addKeyListener(key);

        this.montant = montant;

        this.model = model;

        this.mouse = mouse;

        this.edit = edit;

        //text.addMouseListener(mouse);

        //text.addFocusListener(focus);

    }

    // public void addCellEditorListener(javax.swing.event.CellEditorListener cellEditorListener) {

    //   cellListener.add(cellEditorListener);

    // }


    public void cancelCellEditing() {

    }

    public Object getCellEditorValue() {

        System.out.println("valeur du putain de champs de texte" + text.getText() + " " + text.getText2());

        return text.getText();

    }

    public boolean isCellEditable(java.util.EventObject eventObject) {
        if (!Achat.getCellEditable())
            return false;

        if (eventObject instanceof MouseEvent) {

            int clickCount;

            // For single-click activation

            // For double-click activation

            clickCount = 2;

            return ((MouseEvent) eventObject).getClickCount() >= clickCount;

        }

        return true;

    }


    public void removeCellEditorListener(javax.swing.event.CellEditorListener cellEditorListener) {

    }

    public boolean shouldSelectCell(java.util.EventObject eventObject) {

        return true;

    }

    public boolean stopCellEditing() {

        super.stopCellEditing();

        return true;

    }

    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable jTable, Object obj, boolean param, int param3, int param4) {

        // liste.setCleUnik(((Integer)obj).intValue());

        if (true)

            return this.text;

        else return null;

    }


    /**
     * Getter for property position.
     *
     * @return Value of property position.
     */

    public int getPosition() {

        return position;

    }


    /**
     * Setter for property position.
     *
     * @param position New value of property position.
     */

    public void setPosition(int position) {

        this.position = position;

    }

    public java.awt.Component getComponent() {

        return text;

    }


    public void reset() {

        text.setText("");

    }


    public boolean isTva() {

        return false;

    }


    public boolean isListe() {

        return false;

    }


    public boolean isMontant() {

        return montant;

    }


    public void interTable(int row) {

        Object tmp1 = model.getValueAt2(row, position);

        String tmp = "";

        if (tmp1 != null)

            tmp = tmp1.toString();

        text.setText(tmp);

    }


}

