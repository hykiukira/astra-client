/*

 * JournauxCompta.java

 *

 * Created on 21 mai 2003, 13:28

 */


package srcastra.astra.gui.modules.compta;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.gui.sys.formVerification.*;

import javax.swing.*;

import javax.swing.table.*;

import srcastra.astra.gui.modules.dossier.utils.*;

import srcastra.astra.gui.test.*;

import java.awt.event.*;

import srcastra.astra.gui.sys.*;

/**
 * @author thomas
 */

public class JournauxCompta extends javax.swing.JPanel {


    /**
     * Creates new form JournauxCompta
     */

    MainScreenModule parent;

    JournauxTableModel model;

    JournauxPerTableModel modelper;

    MouseAdapter my_mouse = new MouseAdapter() {

        public void mouseClicked(MouseEvent evt) {

            System.out.println("click click");

            //  mouseclick(evt);

        }

    };

    ActionListener my_action = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

            System.out.println("click click");

            mouseclick(evt);

        }

    };

    // ParamComptableInterface paramcompta;

    public JournauxCompta(MainScreenModule parent) {

        //  this.paramcompta=paramcompta;

        this.parent = parent;

        initComponents();

        setModel();

        initTables();

        reloadData();

        initListe();

    }

    private void mouseclick(ActionEvent evt) {

        if (!modelper.alreadyClosed(grp_Table_journauxPeriode.getSelectedRow())) {

            int rep = new MessageManager().showConfirmDialog(this, "del_journper", "del_journper_titre", parent.getCurrentUser());

            if (rep == 0) {

                int id = modelper.getId(grp_Table_journauxPeriode.getSelectedRow());

                try {

                    parent.getServeur().renvParamCompta(parent.getCurrentUser().getUrcleunik()).cloturePeriode(id, parent.getCurrentUser().getUrcleunik());

                } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

                }

                catch (java.rmi.RemoteException re) {

                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

                }

            }

            reloadData();

        }

    }

    public void initListe() {

        grp_Liste_compte.setServeur(parent.getServeur());

        grp_Liste_compte.setLogin(parent.getCurrentUser());

        grp_Liste_compte.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_compte.init2();

    }

    public void initTables() {

        // grp_Table_journaux.setAutoCreateColumnsFromModel(false);

        grp_Table_journauxPeriode.setFocusable(false);

        grp_Table_journaux.setFocusable(false);

        grp_Table_journauxPeriode.setAutoCreateColumnsFromModel(false);

        grp_Table_journaux.getTableHeader().setReorderingAllowed(false);

        grp_Table_journaux.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        grp_Table_journauxPeriode.getTableHeader().setReorderingAllowed(false);

        grp_Table_journauxPeriode.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        model = new JournauxTableModel(parent.getServeur(), parent.getCurrentUser());

        modelper = new JournauxPerTableModel(parent.getServeur(), parent.getCurrentUser());

        grp_Table_journaux.setModel(model);

        //   grp_Table_journauxPeriode.setModel(null);

        grp_Table_journauxPeriode.setModel(modelper);

        JCheckBox jcheck = new JCheckBox();

        jcheck.addActionListener(this.my_action);

        TableCellEditor editor = new javax.swing.DefaultCellEditor(jcheck);

        TableCellRenderer renderer2;

        renderer2 = new CheckCellRenderer();

        //  ((CheckCellRenderer)renderer2).addActionListener(this.my_action);

        for (int k = 0; k < modelper.getColumnCount(); k++) {

            // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;

            // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(JLabel.CENTER);

            // renderer2.setHorizontalAlignment(JCheckBox.CENTER);

            TableColumn column;

            if (k == 2)

                column = new TableColumn(k, 50, renderer2, editor);

            else

                column = new TableColumn(k, 50, renderer, null);

            grp_Table_journauxPeriode.addColumn(column);

        }

    }


    public void reloadData() {

        try {

            model.loadData();

            modelper.loadData();

            grp_Table_journaux.tableChanged(new javax.swing.event.TableModelEvent(model));

            grp_Table_journauxPeriode.tableChanged(new javax.swing.event.TableModelEvent(modelper));

            if (grp_Table_journaux.getRowCount() > 0)

                grp_Table_journaux.changeSelection(0, 0, false, false);

        }

        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

    }

    public void setModel() {

        grp_TField_abrev1.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_abrev2.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_intitule1.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_intitule2.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

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

        java.awt.GridBagConstraints gridBagConstraints;


        jPanel1 = new javax.swing.JPanel();

        jPanel2 = new javax.swing.JPanel();

        jPanel5 = new javax.swing.JPanel();

        grp_TField_intitule2 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_intitule1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_abrev1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_abrev2 = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        grp_Liste_compte = new srcastra.astra.gui.components.combobox.liste.Liste();

        jLabel5 = new javax.swing.JLabel();

        jPanel6 = new javax.swing.JPanel();

        jPanel3 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_journaux = new javax.swing.JTable();

        jPanel4 = new javax.swing.JPanel();

        jScrollPane2 = new javax.swing.JScrollPane();

        grp_Table_journauxPeriode = new javax.swing.JTable();


        setLayout(new java.awt.BorderLayout());


        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));


        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel2.setPreferredSize(new java.awt.Dimension(456, 70));

        jPanel5.setLayout(new java.awt.GridBagLayout());


        grp_TField_intitule2.setEnabled(false);

        grp_TField_intitule2.setGrp_Comp_nextComponent(grp_Liste_compte);

        grp_TField_intitule2.setPreferredSize(new java.awt.Dimension(150, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        jPanel5.add(grp_TField_intitule2, gridBagConstraints);


        grp_TField_intitule1.setEnabled(false);

        grp_TField_intitule1.setGrp_Comp_nextComponent(grp_TField_abrev2);

        grp_TField_intitule1.setPreferredSize(new java.awt.Dimension(150, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        jPanel5.add(grp_TField_intitule1, gridBagConstraints);


        grp_TField_abrev1.setEnabled(false);

        grp_TField_abrev1.setGrp_Comp_nextComponent(grp_TField_intitule1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        jPanel5.add(grp_TField_abrev1, gridBagConstraints);


        grp_TField_abrev2.setEnabled(false);

        grp_TField_abrev2.setGrp_Comp_nextComponent(grp_TField_intitule2);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        jPanel5.add(grp_TField_abrev2, gridBagConstraints);


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("abrev_journl1"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanel5.add(jLabel1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("abrev_journl2"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanel5.add(jLabel2, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("Intitule_journl1"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanel5.add(jLabel3, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("Intitule_journl2"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanel5.add(jLabel4, gridBagConstraints);


        grp_Liste_compte.setEnabled(false);

        grp_Liste_compte.setIsLastComponent(true);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel5.add(grp_Liste_compte, gridBagConstraints);


        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("compte"));

        jLabel5.setPreferredSize(new java.awt.Dimension(90, 13));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanel5.add(jLabel5, gridBagConstraints);


        jPanel2.add(jPanel5);


        jPanel6.setLayout(new java.awt.GridBagLayout());


        jPanel2.add(jPanel6);


        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);


        jPanel3.setLayout(new java.awt.BorderLayout());


        jPanel3.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("titre_journaux"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 100));

        grp_Table_journaux.setModel(new javax.swing.table.DefaultTableModel());

        jScrollPane1.setViewportView(grp_Table_journaux);


        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);


        jPanel4.setLayout(new java.awt.BorderLayout());


        jPanel4.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("per_compta"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel4.setPreferredSize(new java.awt.Dimension(10, 130));

        grp_Table_journauxPeriode.setModel(new DefaultTableModel());

        jScrollPane2.setViewportView(grp_Table_journauxPeriode);


        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);


        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);


        add(jPanel1, java.awt.BorderLayout.CENTER);


    }//GEN-END:initComponents


    /**
     * Getter for property grp_TField_abrev1.
     *
     * @return Value of property grp_TField_abrev1.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_abrev1() {

        return grp_TField_abrev1;

    }


    /**
     * Setter for property grp_TField_abrev1.
     *
     * @param grp_TField_abrev1 New value of property grp_TField_abrev1.
     */

    public void setGrp_TField_abrev1(srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev1) {

        this.grp_TField_abrev1 = grp_TField_abrev1;

    }


    /**
     * Getter for property grp_TField_abrev2.
     *
     * @return Value of property grp_TField_abrev2.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_abrev2() {

        return grp_TField_abrev2;

    }


    /**
     * Setter for property grp_TField_abrev2.
     *
     * @param grp_TField_abrev2 New value of property grp_TField_abrev2.
     */

    public void setGrp_TField_abrev2(srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev2) {

        this.grp_TField_abrev2 = grp_TField_abrev2;

    }


    /**
     * Getter for property grp_TField_intitule1.
     *
     * @return Value of property grp_TField_intitule1.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_intitule1() {

        return grp_TField_intitule1;

    }


    /**
     * Setter for property grp_TField_intitule1.
     *
     * @param grp_TField_intitule1 New value of property grp_TField_intitule1.
     */

    public void setGrp_TField_intitule1(srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule1) {

        this.grp_TField_intitule1 = grp_TField_intitule1;

    }


    /**
     * Getter for property grp_TField_intitule2.
     *
     * @return Value of property grp_TField_intitule2.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_intitule2() {

        return grp_TField_intitule2;

    }


    /**
     * Setter for property grp_TField_intitule2.
     *
     * @param grp_TField_intitule2 New value of property grp_TField_intitule2.
     */

    public void setGrp_TField_intitule2(srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule2) {

        this.grp_TField_intitule2 = grp_TField_intitule2;

    }


    /**
     * Getter for property insertMode.
     *
     * @return Value of property insertMode.
     */

    public int getInsertMode() {

        return insertMode;

    }


    /**
     * Setter for property insertMode.
     *
     * @param insertMode New value of property insertMode.
     */

    public void setInsertMode(int insertMode) {

        this.insertMode = insertMode;

    }


    /**
     * Getter for property grp_Liste_compte.
     *
     * @return Value of property grp_Liste_compte.
     */

    public srcastra.astra.gui.components.combobox.liste.Liste getGrp_Liste_compte() {

        return grp_Liste_compte;

    }


    /**
     * Setter for property grp_Liste_compte.
     *
     * @param grp_Liste_compte New value of property grp_Liste_compte.
     */

    public void setGrp_Liste_compte(srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_compte) {

        this.grp_Liste_compte = grp_Liste_compte;

    }


    /**
     * Getter for property model.
     *
     * @return Value of property model.
     */

    public srcastra.astra.gui.modules.compta.JournauxTableModel getModel() {

        return model;

    }


    /**
     * Setter for property model.
     *
     * @param model New value of property model.
     */

    public void setModel(srcastra.astra.gui.modules.compta.JournauxTableModel model) {

        this.model = model;

    }


    /**
     * Getter for property grp_Table_journaux.
     *
     * @return Value of property grp_Table_journaux.
     */

    public javax.swing.JTable getGrp_Table_journaux() {

        return grp_Table_journaux;

    }


    /**
     * Setter for property grp_Table_journaux.
     *
     * @param grp_Table_journaux New value of property grp_Table_journaux.
     */

    public void setGrp_Table_journaux(javax.swing.JTable grp_Table_journaux) {

        this.grp_Table_journaux = grp_Table_journaux;

    }


    int insertMode;

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule2;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule1;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JScrollPane jScrollPane1;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_compte;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev2;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev1;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JTable grp_Table_journaux;

    private javax.swing.JTable grp_Table_journauxPeriode;

    // End of variables declaration//GEN-END:variables


}

