/*

 * AvionRefund.java

 *

 * Created on 28 octobre 2003, 14:26

 */


package srcastra.astra.gui.modules.dossier.productSpecification.avioncancel;

import srcastra.astra.sys.classetransfert.*;

/**
 * @author Thomas
 */

public class AvionRefund extends javax.swing.JPanel {


    /**
     * Creates new form AvionRefund
     */

    Loginusers_T currentUser;

    public AvionRefund(Loginusers_T currentUser) {

        setBounds(0, 0, 500, 250);

        this.currentUser = currentUser;

        initComponents();

        setDocument();

    }

    private void setDocument() {

        grp_TField_montan.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5, 2, currentUser.getLangage()));

        grp_TField_rembTax.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5, 2, currentUser.getLangage()));

        grp_TField_taxcomp.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5, 2, currentUser.getLangage()));

        // grp_TField_refund.setDocument(doc

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

        jScrollPane1 = new javax.swing.JScrollPane();

        jTextPane1 = new javax.swing.JTextPane();

        jPanel2 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        grp_TField_montan = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel3 = new javax.swing.JLabel();

        grp_TField_taxcomp = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel2 = new javax.swing.JLabel();

        grp_TField_refund = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel4 = new javax.swing.JLabel();

        grp_TField_rembTax = new srcastra.astra.gui.components.textFields.ATextField();

        jPanel3 = new javax.swing.JPanel();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_ref"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel1.setLayout(new java.awt.BorderLayout());


        jScrollPane1.setViewportView(jTextPane1);


        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel2.setMinimumSize(new java.awt.Dimension(10, 30));

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 90));

        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_montant"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);

        jPanel4.add(jLabel1, gridBagConstraints);


        grp_TField_montan.setGrp_Comp_nextComponent(grp_TField_rembTax);

        grp_TField_montan.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_montan.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        jPanel4.add(grp_TField_montan, new java.awt.GridBagConstraints());


        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("taxcomp"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(jLabel3, gridBagConstraints);


        grp_TField_taxcomp.setGrp_Comp_nextComponent(grp_TField_refund);

        grp_TField_taxcomp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_taxcomp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        jPanel4.add(grp_TField_taxcomp, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("numRefund"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(jLabel2, gridBagConstraints);


        grp_TField_refund.setGrp_Comp_nextComponent(jTextPane1);

        grp_TField_refund.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_refund.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        jPanel4.add(grp_TField_refund, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane").getString("remb_tax"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(jLabel4, gridBagConstraints);


        grp_TField_rembTax.setGrp_Comp_nextComponent(grp_TField_taxcomp);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        jPanel4.add(grp_TField_rembTax, gridBagConstraints);


        jPanel2.add(jPanel4);


        add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 100));

        add(jPanel3, java.awt.BorderLayout.SOUTH);


    }//GEN-END:initComponents


    /**
     * Getter for property grp_TField_refund.
     *
     * @return Value of property grp_TField_refund.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_refund() {

        return grp_TField_refund;

    }


    /**
     * Setter for property grp_TField_refund.
     *
     * @param grp_TField_refund New value of property grp_TField_refund.
     */

    public void setGrp_TField_refund(srcastra.astra.gui.components.textFields.ATextField grp_TField_refund) {

        this.grp_TField_refund = grp_TField_refund;

    }


    /**
     * Getter for property grp_TField_montan.
     *
     * @return Value of property grp_TField_montan.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_montan() {

        return grp_TField_montan;

    }


    /**
     * Setter for property grp_TField_montan.
     *
     * @param grp_TField_montan New value of property grp_TField_montan.
     */

    public void setGrp_TField_montan(srcastra.astra.gui.components.textFields.ATextField grp_TField_montan) {

        this.grp_TField_montan = grp_TField_montan;

    }


    /**
     * Getter for property jTextPane1.
     *
     * @return Value of property jTextPane1.
     */

    public javax.swing.JTextPane getJTextPane1() {

        return jTextPane1;

    }


    /**
     * Setter for property jTextPane1.
     *
     * @param jTextPane1 New value of property jTextPane1.
     */

    public void setJTextPane1(javax.swing.JTextPane jTextPane1) {

        this.jTextPane1 = jTextPane1;

    }


    /**
     * Getter for property grp_TField_taxcomp.
     *
     * @return Value of property grp_TField_taxcomp.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_taxcomp() {

        return grp_TField_taxcomp;

    }


    /**
     * Setter for property grp_TField_taxcomp.
     *
     * @param grp_TField_taxcomp New value of property grp_TField_taxcomp.
     */

    public void setGrp_TField_taxcomp(srcastra.astra.gui.components.textFields.ATextField grp_TField_taxcomp) {

        this.grp_TField_taxcomp = grp_TField_taxcomp;

    }


    /**
     * Getter for property grp_TField_rembTax.
     *
     * @return Value of property grp_TField_rembTax.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_rembTax() {

        return grp_TField_rembTax;

    }


    /**
     * Setter for property grp_TField_rembTax.
     *
     * @param grp_TField_rembTax New value of property grp_TField_rembTax.
     */

    public void setGrp_TField_rembTax(srcastra.astra.gui.components.textFields.ATextField grp_TField_rembTax) {

        this.grp_TField_rembTax = grp_TField_rembTax;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_montan;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_refund;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_rembTax;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_taxcomp;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JTextPane jTextPane1;

    // End of variables declaration//GEN-END:variables


}

