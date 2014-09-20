/*

 * EntitePanel.java

 *

 * Created on 20 mai 2003, 14:47

 */


package srcastra.astra.gui.modules.compta;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.classetransfert.compta.*;

/**
 * @author Thomas
 */

public class ExerciceCompt extends javax.swing.JPanel {


    /**
     * Creates new form EntitePanel
     */

    MainScreenModule parent;

    String[] periode;

    javax.swing.ComboBoxModel model1;

    javax.swing.ComboBoxModel model2;

    public ExerciceCompt(MainScreenModule parent) {

        this.parent = parent;

        initComponents();

        setModel();

        // jComboBox1.setPreferredSize(new java.awt.Dimension(100, 21));

    }

    private void setModel() {

        periode = new String[]{

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("janvier") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("fev") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("mar") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("avr") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("mai") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("juin") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("juil") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("aout") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("sept") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("oct") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("nov") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("dec") + " " + grp_Combo_annee.getSelectedItem().toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("janvier") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("fev") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("mar") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("avr") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("mai") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("juin") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("juil") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("aout") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("sept") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("oct") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("nov") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

                java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("dec") + " " + grp_Combo_annee.getItemAt(grp_Combo_annee.getSelectedIndex() + 1).toString(),

        };

        model1 = new javax.swing.DefaultComboBoxModel(periode);

        model2 = new javax.swing.DefaultComboBoxModel(periode);

        grp_Combo_de.setModel(model1);

        grp_Combo_a.setModel(model2);


    }

    public void loadFields(ExerciceCompt_T exercice) {

        if (exercice != null) {

            grp_TField_abrev1.setText(exercice.getExle_abrevfr());

            grp_TField_abrev2.setText(exercice.getExle_abrevnl());

            grp_TField_intitule1.setText(exercice.getExle_intitulefr());

            grp_TField_intitule2.setText(exercice.getExle_intitulevnl());

            grp_Combo_annee.setSelectedItem(exercice.getExle_annee());

            grp_Combo_de.setSelectedIndex(exercice.getExle_debut());

            grp_Combo_a.setSelectedIndex(exercice.getExle_fin());

        }

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

        grp_TField_abrev1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_intitule2 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_abrev2 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_intitule1 = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jLabel6 = new javax.swing.JLabel();

        jLabel7 = new javax.swing.JLabel();

        grp_Combo_annee = new javax.swing.JComboBox();

        grp_Combo_de = new javax.swing.JComboBox();

        grp_Combo_a = new javax.swing.JComboBox();


        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        setPreferredSize(new java.awt.Dimension(500, 136));

        jPanel1.setLayout(new java.awt.GridBagLayout());


        grp_TField_abrev1.setGrp_Comp_nextComponent(grp_TField_abrev2);

        grp_TField_abrev1.setPreferredSize(new java.awt.Dimension(150, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_TField_abrev1, gridBagConstraints);


        grp_TField_intitule2.setGrp_Comp_nextComponent(grp_Combo_annee);

        grp_TField_intitule2.setPreferredSize(new java.awt.Dimension(150, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_TField_intitule2, gridBagConstraints);


        grp_TField_abrev2.setGrp_Comp_nextComponent(grp_TField_intitule1);

        grp_TField_abrev2.setPreferredSize(new java.awt.Dimension(150, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_TField_abrev2, gridBagConstraints);


        grp_TField_intitule1.setGrp_Comp_previousComponent(grp_TField_intitule2);

        grp_TField_intitule1.setPreferredSize(new java.awt.Dimension(150, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_TField_intitule1, gridBagConstraints);


        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_abrevfr"));

        jLabel1.setPreferredSize(new java.awt.Dimension(120, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_abrevnl"));

        jLabel2.setPreferredSize(new java.awt.Dimension(120, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel2, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_intitulel1"));

        jLabel3.setPreferredSize(new java.awt.Dimension(120, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel3, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_intitulel2"));

        jLabel4.setPreferredSize(new java.awt.Dimension(120, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel4, gridBagConstraints);


        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_annee"));

        jLabel5.setPreferredSize(new java.awt.Dimension(100, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel5, gridBagConstraints);


        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_debut"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);

        jPanel1.add(jLabel6, gridBagConstraints);


        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ex_compta_a"));

        jLabel7.setPreferredSize(new java.awt.Dimension(40, 16));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel1.add(jLabel7, gridBagConstraints);


        grp_Combo_annee.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010"}));

        grp_Combo_annee.setPreferredSize(new java.awt.Dimension(100, 21));

        grp_Combo_annee.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                grp_Combo_anneeActionPerformed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_Combo_annee, gridBagConstraints);


        grp_Combo_de.setPreferredSize(new java.awt.Dimension(100, 21));

        grp_Combo_de.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                grp_Combo_deActionPerformed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);

        jPanel1.add(grp_Combo_de, gridBagConstraints);


        grp_Combo_a.setPreferredSize(new java.awt.Dimension(100, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel1.add(grp_Combo_a, gridBagConstraints);


        add(jPanel1);


    }//GEN-END:initComponents


    private void grp_Combo_deActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Combo_deActionPerformed

        // Add your handling code here:

    }//GEN-LAST:event_grp_Combo_deActionPerformed


    private void grp_Combo_anneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Combo_anneeActionPerformed

        setModel();        // Add your handling code here:

    }//GEN-LAST:event_grp_Combo_anneeActionPerformed


    /**
     * Getter for property grp_Combo_a.
     *
     * @return Value of property grp_Combo_a.
     */

    public javax.swing.JComboBox getGrp_Combo_a() {

        return grp_Combo_a;

    }


    /**
     * Setter for property grp_Combo_a.
     *
     * @param grp_Combo_a New value of property grp_Combo_a.
     */

    public void setGrp_Combo_a(javax.swing.JComboBox grp_Combo_a) {

        this.grp_Combo_a = grp_Combo_a;

    }


    /**
     * Getter for property grp_Combo_annee.
     *
     * @return Value of property grp_Combo_annee.
     */

    public javax.swing.JComboBox getGrp_Combo_annee() {

        return grp_Combo_annee;

    }


    /**
     * Setter for property grp_Combo_annee.
     *
     * @param grp_Combo_annee New value of property grp_Combo_annee.
     */

    public void setGrp_Combo_annee(javax.swing.JComboBox grp_Combo_annee) {

        this.grp_Combo_annee = grp_Combo_annee;

    }


    /**
     * Getter for property grp_Combo_de.
     *
     * @return Value of property grp_Combo_de.
     */

    public javax.swing.JComboBox getGrp_Combo_de() {

        return grp_Combo_de;

    }


    /**
     * Setter for property grp_Combo_de.
     *
     * @param grp_Combo_de New value of property grp_Combo_de.
     */

    public void setGrp_Combo_de(javax.swing.JComboBox grp_Combo_de) {

        this.grp_Combo_de = grp_Combo_de;

    }


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

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule2;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule1;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JComboBox grp_Combo_a;

    private javax.swing.JComboBox grp_Combo_annee;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev2;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_abrev1;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JComboBox grp_Combo_de;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel1;

    // End of variables declaration//GEN-END:variables


}

