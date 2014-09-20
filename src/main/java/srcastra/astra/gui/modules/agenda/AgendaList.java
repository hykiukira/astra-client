/*
 * AgendaList.java
 *
 * Created on 10 mars 2004, 16:03
 */

package srcastra.astra.gui.modules.agenda;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.rmi.Exception.*;

import java.rmi.*;

import srcastra.astra.gui.sys.*;

import javax.swing.*;
import javax.swing.table.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;
import srcastra.astra.gui.modules.dossier.utils.*;

import java.awt.event.*;


/**
 * /**
 *
 * @author Administrateur
 */
public class AgendaList extends javax.swing.JPanel {

    /**
     * Creates new form AgendaList
     */
    TaskListTableModel model;
    astrainterface m_serveur;
    Loginusers_T m_user;
    srcastra.astra.sys.classetransfert.utils.Date m_date;

    public AgendaList(astrainterface serveur, Loginusers_T user, srcastra.astra.sys.classetransfert.utils.Date date) {
        m_serveur = serveur;
        m_user = user;
        m_date = date;
        initComponents();
        setBounds(0, 0, 480, 430);
        initTable();
    }

    MouseAdapter my_mouse = new MouseAdapter() {
        public void mouseReleased(MouseEvent evt) {
            Task_T task = (Task_T) model.getM_vector().get(grp_table_list.getSelectedRow());
            if (((JCheckBox) evt.getSource()).isSelected())
                task.setTask_etat(3);
            else
                task.setTask_etat(2);
            try {
                m_serveur.renvDossierRmiObject(m_user.getUrcleunik()).modifyTask(task, m_user.getUrcleunik());
            }
            catch (ServeurSqlFailure se) {
                ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, 0, se, m_user);
            }
            catch (RemoteException rn) {
                ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, 0, rn, m_user);

            }
            refresh(m_date);


        }
    };

    private void initTable() {
        grp_table_list.setSelectionBackground(new java.awt.Color(221, 221, 255));
        model = new TaskListTableModel(m_serveur, m_user);
        model.refresh(m_date);
        grp_table_list.setAutoCreateColumnsFromModel(false);
        grp_table_list.setModel(model);
        grp_table_list.getTableHeader().setReorderingAllowed(false);
        grp_table_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grp_table_list.setFocusable(false);
        for (int k = 0; k < model.m_columns.length; k++) {
            JCheckBox jcheck = new JCheckBox();
            jcheck.addMouseListener(my_mouse);
            TableCellEditor editor = new javax.swing.DefaultCellEditor(jcheck);
            TableCellRenderer renderer2;
            renderer2 = new CheckCellRenderer();
            // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;
            // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);
            TableColumn column;
            if (k == 4)
                column = new TableColumn(k, model.m_columns[k].c_width, renderer2, editor);
            else
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);
            grp_table_list.addColumn(column);
        }
        JTableHeader header = grp_table_list.getTableHeader();
        header.setUpdateTableInRealTime(false);
        changeSelection(0);
    }

    private void changeSelection(int row) {
        if (grp_table_list.getRowCount() > 0) {
            grp_table_list.changeSelection(row, row, false, false);
            Task_T tmp = model.getTask(row);
            if (tmp != null)
                if (tmp.getTask_memo() != null)
                    grp_TArea_memo.setText(tmp.getTask_memo());

        }
    }

    public void changetSelectionRow(boolean down) {
        if (grp_table_list.getRowCount() > 0) {
            if (down) {
                if (grp_table_list.getSelectedRow() != grp_table_list.getRowCount() - 1) {
                    grp_table_list.changeSelection(grp_table_list.getSelectedRow() + 1, 0, false, false);
                }
                int row = grp_table_list.getSelectedRow();
                changeSelection(row);
            } else {
                if (grp_table_list.getSelectedRow() != 0) {
                    grp_table_list.changeSelection(grp_table_list.getSelectedRow() - 1, 0, false, false);
                }
                int row = grp_table_list.getSelectedRow();
                changeSelection(row);

            }
        }
    }

    public void refreshMemo() {
        changeSelection(grp_table_list.getSelectedRow());

    }

    public Task_T getSelecteTask() {
        if (grp_table_list.getRowCount() > 0)
            return model.getTask(grp_table_list.getSelectedRow());
        return null;
    }

    public void refresh(srcastra.astra.sys.classetransfert.utils.Date date) {
        model.refresh(date);
        grp_table_list.tableChanged(new javax.swing.event.TableModelEvent(model));
        if (grp_table_list.getSelectedRow() != 0) {
            grp_table_list.changeSelection(0, 0, false, false);
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_TArea_memo = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_table_list = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("memo")));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(grp_TArea_memo);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("tasks")));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 300));
        grp_table_list.setModel(new DefaultTableModel());
        grp_table_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_table_listMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(grp_table_list);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void grp_table_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_table_listMouseClicked
        refreshMemo();        // Add your handling code here:
    }//GEN-LAST:event_grp_table_listMouseClicked

    /**
     * Getter for property model.
     *
     * @return Value of property model.
     */
    public srcastra.astra.gui.modules.agenda.TaskListTableModel getModel() {
        return model;
    }

    /**
     * Setter for property model.
     *
     * @param model New value of property model.
     */
    public void setModel(srcastra.astra.gui.modules.agenda.TaskListTableModel model) {
        this.model = model;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane grp_TArea_memo;
    private javax.swing.JTable grp_table_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
