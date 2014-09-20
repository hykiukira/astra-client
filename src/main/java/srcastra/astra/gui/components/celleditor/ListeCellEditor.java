/*

 * ListeCellEditor.java

 *

 * Created on 22 juillet 2003, 10:46

 */


package srcastra.astra.gui.components.celleditor;

import srcastra.astra.gui.components.combobox.liste.*;

import javax.swing.*;

import java.awt.event.*;

import srcastra.astra.gui.modules.compta.achat.*;


/**
 * @author thomas
 */

public class ListeCellEditor extends AbstractCellEditor implements javax.swing.table.TableCellEditor, srcastra.astra.gui.components.celleditor.CellEditorInterface {


    /**
     * Creates a new instance of ListeCellEditor
     */

    AchatTableModel model;

    Liste liste;

    int position;

    boolean tva;

    MouseAdapter mouse;

    EditTable edit;

    public ListeCellEditor(Liste liste, int position, boolean tva, AchatTableModel model, MouseAdapter mouse, EditTable edit) {

        this.position = position;

        this.liste = liste;

        this.tva = tva;

        this.mouse = mouse;

        this.liste.addActionListener(action);

        //this.liste.addKeyListener(key);

        this.edit = edit;

        //  this.liste.addMouseListener(mouse);

        this.model = model;

    }

    KeyAdapter key = new KeyAdapter() {

        private void grp_TField_comKeyPressed(java.awt.event.KeyEvent evt) {

            System.out.println("key processed");

            fireEditingStopped();

            fireEditingCanceled();

        }

    };

    ActionListener action = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

            System.out.println("fucking action processed");

            if (liste.getCleUnik() != 0)

                action(evt);

        }

    };

    private void action(ActionEvent evt) {

        edit.action(this);

    }

    public void cancelCellEditing() {

    }


    public Object getCellEditorValue() {

        return liste.getText();

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

        return this.liste;

    }

    public java.awt.Component getComponent() {

        return liste;

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


    public void reset() {

        liste.setCleUnik(0);

    }


    public boolean isTva() {

        return tva;

    }


    public boolean isListe() {

        return true;

    }


    public boolean isMontant() {

        return false;

    }


    public void interTable(int row) {

        String tmp = "";

        if (isTva()) {

            Object tmp1 = model.getValueAt2(row, 7);

            if (tmp1 != null)

                tmp = tmp1.toString();

            if (tmp.equals(""))

                liste.setCleUnik(0);

            else

                liste.setCleUnik(Integer.parseInt(tmp));

        } else {

            Object tmp1 = model.getValueAt2(row, 6);

            if (tmp1 != null)

                tmp = tmp1.toString();

            if (tmp.equals(""))

                liste.setCleUnik(0);

            else

                liste.setCleUnik(Integer.parseInt(tmp));

        }

    }

}

