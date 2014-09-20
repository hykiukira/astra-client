/*

 * ComptePanel.java

 *

 * Created on 15 septembre 2003, 12:06

 */


package srcastra.astra.gui.modules.compta.compte;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.gui.components.celleditor.*;

import srcastra.astra.gui.modules.*;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.*;

import javax.swing.table.*;

import srcastra.astra.gui.components.combobox.liste.*;

import srcastra.astra.gui.components.tva.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.test.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.sys.formVerification.*;

import java.awt.event.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.test.ColumnData;

import srcastra.astra.gui.sys.*;

import java.awt.event.*;

import javax.swing.event.*;

import srcastra.astra.sys.classetransfert.utils.*;

import javax.swing.border.*;

import java.awt.event.*;

import srcastra.astra.gui.components.date.thedate.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.gui.modules.config.*;

import javax.swing.text.*;


/**
 * @author Thomas
 */

public class ComptePanel extends javax.swing.JPanel implements srcastra.astra.gui.modules.config.GlobalInterface, ToolBarComposer {


    /**
     * Creates new form ComptePanel
     */

    CompteModel model;

    MainScreenModule parent;

    ActionToolBar toolbar;

    ManageFields manfields;

    java.awt.event.MouseAdapter my_mouse = new java.awt.event.MouseAdapter() {

        public void mouseClicked(MouseEvent evt) {

            refresh();

        }

    };

    private void refresh() {

        try {

            refreshTable();

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }


    }

    public ComptePanel(MainScreenModule parent, ActionToolBar toolbar) {

        this.parent = parent;

        this.toolbar = toolbar;

        initComponents();

        initTable();

        try {

            refreshTable();


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        manfields = new ManageFields(null, (javax.swing.JInternalFrame) parent, grp_Table_compte, toolbar, this, null, parent.getCurrentUser());

        manfields.init();

        grp_TField_rechercre.setDocument(new ComptePanel.ZeMask());

        grp_Check_inpute.addMouseListener(my_mouse);

    }

    private ArrayList refreshTable() throws srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.rmi.RemoteException {

        ArrayList array = null;

        if (!grp_Check_inpute.isSelected()) {

            array = parent.getServeur().renvCompte2RmiObject(parent.getCurrentUser().getUrcleunik()).getListeComp(parent.getCurrentUser().getUrcleunik());

        } else {

            array = parent.getServeur().renvCompte2RmiObject(parent.getCurrentUser().getUrcleunik()).getListeCompInpute(parent.getCurrentUser().getUrcleunik());

        }

        model.setData(array);

        grp_Table_compte.tableChanged(new TableModelEvent(model));

        if (grp_Table_compte.getRowCount() > 0) {

            grp_Table_compte.changeSelection(0, 0, false, false);

        }

        return array;


    }


    private void initTable() {

        grp_Table_compte.setFocusable(false);

        grp_Table_compte.setSelectionBackground(new java.awt.Color(204, 204, 255));

        this.model = new CompteModel(parent, parent.getCurrentUser());

        grp_Table_compte.setAutoCreateColumnsFromModel(false);

        grp_Table_compte.getTableHeader().setReorderingAllowed(false);

        grp_Table_compte.setModel(this.model);

        grp_Table_compte.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        for (int k = 0; k < model.m_columns.length; k++) {

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

            TableColumn column = null;

            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            column.setHeaderValue(model.m_columns[k].c_title);

            grp_Table_compte.addColumn(column);

        }

        JTableHeader header = grp_Table_compte.getTableHeader();

        header.setUpdateTableInRealTime(false);

        //  header.setResizingAllowed(false);

        if (grp_Table_compte.getRowCount() != 0) ;

        grp_Table_compte.changeSelection(0, 0, false, false);

    }

    /**
     * This method is called from within the constructor to
     * <p/>
     * initialize the form.
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * always regenerated by the Form Editor.
     */

    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel1 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_compte = new javax.swing.JTable();

        jPanel2 = new javax.swing.JPanel();

        grp_TField_rechercre = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Label_impute = new javax.swing.JLabel();

        grp_Check_inpute = new srcastra.astra.gui.components.checkbox.ACheckBox();

        jPanel3 = new javax.swing.JPanel();


        setLayout(new java.awt.BorderLayout());


        jPanel1.setLayout(new java.awt.BorderLayout());


        grp_Table_compte.setModel(new javax.swing.table.DefaultTableModel());

        jScrollPane1.setViewportView(grp_Table_compte);


        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 30));

        grp_TField_rechercre.setPreferredSize(new java.awt.Dimension(150, 18));

        jPanel2.add(grp_TField_rechercre);


        grp_Label_impute.setFont(new java.awt.Font("Tahoma", 0, 10));

        grp_Label_impute.setText("Imput\u00e9");

        jPanel2.add(grp_Label_impute);


        grp_Check_inpute.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                grp_Check_inputeActionPerformed(evt);

            }

        });


        jPanel2.add(grp_Check_inpute);


        add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));

        add(jPanel3, java.awt.BorderLayout.SOUTH);


    }//GEN-END:initComponents


    private void grp_Check_inputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_inputeActionPerformed

        try {

            refreshTable();

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

    }//GEN-LAST:event_grp_Check_inputeActionPerformed


    public Object getObject() {

        return null;

    }


    public srcastra.astra.gui.modules.config.user.Pwd getPass() {

        return null;

    }


    public GlobalRmiInterface getRmiInteface() {

        return null;

    }


    public javax.swing.JTable getTable() {

        return null;

    }


    public boolean isOkPass() {

        return true;

    }


    public void setFields() {

    }


    public void updateFields() {

    }


    public void doAccept() {

    }


    public void doCancel() {

    }


    public void doClose() {

    }


    public void doCreate() {

        manfields.nextPanelCreate();

    }


    public void doDelete() {

    }


    public void doF10() {

        try {

            refreshTable();


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

    }


    public void doF7() {

    }


    public void doHelp() {

    }


    public void doModify() {

        manfields.nextPanelModify();


    }


    public void doNext() {

    }


    public void doPrevious() {

        manfields.validate();

    }


    public void doPrint() {

    }


    public void doSwitch() {

    }


    public int[] getDefaultActionToolBarMask() {

        return null;

    }


    public java.awt.Component m_getGeneriqueTable() {

        return null;

    }


    public void setThisAsToolBarComponent() {

    }

    private class ZeMask extends DefaultMask {


        private boolean needLoad = true;

        // private int beginLoad = ((srcastra.astra.gui.MainFrame)parent.getSuperOwner()).mainconfig.getConfig().getNbr_lettre_liste();


        private int colSearch = 1;


        private boolean research = true;


        public ZeMask() {


            super();


        }


        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            String c = getText(0, getLength());

            String befO = c.substring(0, offs);

            String aftO = c.substring(offs, getLength());

            String ct = befO + str + aftO;

            if (str.equals("*#*VALIDATE*#*")) {

                return;

            }

            boolean encode = true;


            int ln = model.searchAWord(ct, 1);

            int tot = grp_Table_compte.getRowCount();

            if (ln >= 0 && ln < tot) {

                grp_Table_compte.changeSelection(ln, 0, false, false);

                String value = model.getValueAt(ln, 0).toString();

                System.out.println("==+> value = " + value.toUpperCase());

                System.out.println("==+> ct = " + ct.toUpperCase());

                if (value.toUpperCase().startsWith(ct.toUpperCase())) super.insertString(offs, str, a);


            }


        }

        public void remove(int offs, int len) throws BadLocationException {

            super.remove(offs, len);


        }

        public boolean isResearch() {

            return research;

        }

        public void setResearch(boolean research) {

            this.research = research;

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Check_inpute;

    private javax.swing.JLabel grp_Label_impute;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_rechercre;

    private javax.swing.JTable grp_Table_compte;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JScrollPane jScrollPane1;

    // End of variables declaration//GEN-END:variables


}

