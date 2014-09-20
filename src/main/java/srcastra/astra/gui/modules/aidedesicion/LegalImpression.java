/*

 * LegalImpression.java

 *

 * Created on 13 mars 2003, 15:51

 */


package srcastra.astra.gui.modules.aidedesicion;

import srcastra.astra.sys.configuration.AbstractRequete;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.gui.sys.utils.JTextAreaAdapter;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.components.AstraComponent;

import java.util.*;

import javax.swing.*;

import srcastra.astra.sys.classetransfert.dossier.produit_T;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.test.ColoredTableCellRenderer;

import javax.swing.table.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.gui.sys.listModel.dossierListModel.*;

import srcastra.astra.gui.sys.utils.*;

import srcastra.astra.gui.components.combobox.liste.*;

import java.awt.event.*;

/**
 * @author Thomas
 */

public class LegalImpression extends GeneriqueMemo {


    /**
     * Creates a new instance of LegalImpression
     */

    public LegalImpression(DossierMainScreenModule parent, ActionToolBar actionToolbar, astrainterface serveur, int typeDecision, boolean multiline, Hashtable generiqueVector) {

        super(parent, actionToolbar, serveur, typeDecision, multiline, generiqueVector);

    }

    protected void initComp() {

        compBefore = new Hashtable();

        compAfter = new Hashtable();

        compLangue = new ArrayList();

        langue = new Hashtable();

        compAfter.put(grp_Jradio_Aviation.getName(), new Object[]{grp_Jradio_Brochure, new Integer(produit_T.BRO), new Integer(produit_T.AV)});

        compAfter.put(grp_Jradio_Brochure.getName(), new Object[]{grp_Jradio_Assurance, new Integer(produit_T.AS), new Integer(produit_T.BRO)});

        compAfter.put(grp_Jradio_Assurance.getName(), new Object[]{grp_Jradio_Aviation, new Integer(produit_T.AV), new Integer(produit_T.AS)});

        // compAfter.put(grp_Jradio_Bateau.getName(),new Object[]{grp_Jradio_Hotel,new Integer(produit_T.HO),new Integer(produit_T.BA)});

        //compAfter.put(grp_Jradio_Hotel.getName(),new Object[]{grp_Jradio_Taxi,new Integer(produit_T.TAX),new Integer(produit_T.HO)});

        //compAfter.put(grp_Jradio_Taxi.getName(),new Object[]{grp_Jradio_Train,new Integer(produit_T.TR),new Integer(produit_T.TAX)});

        //compAfter.put(grp_Jradio_Train.getName(),new Object[]{grp_Jradio_Voiture,new Integer(produit_T.VO),new Integer(produit_T.TR)});

        //compAfter.put(grp_Jradio_Voiture.getName(),new Object[]{grp_Jradio_Aviation,new Integer(produit_T.AV),new Integer(produit_T.VO)});

        compBefore.put(grp_Jradio_Aviation.getName(), new Object[]{grp_Jradio_Assurance, new Integer(produit_T.AS)});

        compBefore.put(grp_Jradio_Brochure.getName(), new Object[]{grp_Jradio_Aviation, new Integer(produit_T.AV)});

        compBefore.put(grp_Jradio_Assurance.getName(), new Object[]{grp_Jradio_Brochure, new Integer(produit_T.BRO)});

        //compBefore.put(grp_Jradio_Bateau.getName(),new Object []{grp_Jradio_Assurance,new Integer(produit_T.AS)});

        //compBefore.put(grp_Jradio_Hotel.getName(),new Object[]{grp_Jradio_Bateau,new Integer(produit_T.BA)});

        //compBefore.put(grp_Jradio_Taxi.getName(),new Object []{grp_Jradio_Hotel,new Integer(produit_T.HO)});

        //compBefore.put(grp_Jradio_Train.getName(),new Object[]{grp_Jradio_Taxi,new Integer(produit_T.TAX)});

        //compBefore.put(grp_Jradio_Voiture.getName(),new Object[]{grp_Jradio_Train,new Integer(produit_T.TR)});       //compBefore.put(grp_Jradio_Aviation.getName(),grp_Jradio_Voiture);

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Fr), new Integer(1)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_NL), new Integer(2)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_En), new Integer(3)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Es), new Integer(4)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Ge), new Integer(5)});

        langue.put(new Integer(1), new JTextAreaAdapter(grp_TField_Fr));

        langue.put(new Integer(2), new JTextAreaAdapter(grp_TField_NL));

        langue.put(new Integer(3), new JTextAreaAdapter(grp_TField_En));

        langue.put(new Integer(4), new JTextAreaAdapter(grp_TField_Es));

        langue.put(new Integer(5), new JTextAreaAdapter(grp_TField_Ge));

    }

    public void enableComponent(boolean enabled) {

        grp_Jradio_Aviation.setEnabled(enabled);

        grp_Jradio_Brochure.setEnabled(enabled);

        grp_Jradio_Assurance.setEnabled(enabled);

        // grp_Jradio_Bateau.setEnabled(enabled);

        // grp_Jradio_Hotel.setEnabled(enabled);

        //grp_Jradio_Taxi.setEnabled(enabled);

        //grp_Jradio_Train.setEnabled(enabled);

        //grp_Jradio_Voiture.setEnabled(enabled);

        //grp_Jradio_Aviation.setEnabled(enabled);

        //grp_Jradio_Brochure.setEnabled(enabled);

        //grp_Jradio_Assurance.setEnabled(enabled);

        //grp_Jradio_Bateau.setEnabled(enabled);

        //grp_Jradio_Hotel.setEnabled(enabled);

        //grp_Jradio_Taxi.setEnabled(enabled);

        //grp_Jradio_Train.setEnabled(enabled);     

        grp_Table_decision.setEnabled(enabled);

    }

    protected void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;


        buttonGroup1 = new javax.swing.ButtonGroup();

        jPanel1 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_decision = new javax.swing.JTable();

        jPanel2 = new javax.swing.JPanel();

        jLabel6 = new javax.swing.JLabel();

        grp_TField_Encode = new javax.swing.JTextField();

        jPanel4 = new javax.swing.JPanel();

        grp_Jradio_Aviation = new javax.swing.JRadioButton();

        grp_Jradio_Brochure = new javax.swing.JRadioButton();

        grp_Jradio_Assurance = new javax.swing.JRadioButton();

        //   grp_Jradio_Bateau = new javax.swing.JRadioButton();

        //  grp_Jradio_Hotel = new javax.swing.JRadioButton();

        // grp_Jradio_Taxi = new javax.swing.JRadioButton();

        //grp_Jradio_Train = new javax.swing.JRadioButton();

        //grp_Jradio_Voiture = new javax.swing.JRadioButton();

        jPanel3 = new javax.swing.JPanel();

        jPanel6 = new javax.swing.JPanel();

        jPanel5 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jPanel9 = new javax.swing.JPanel();

        jScrollPane2 = new javax.swing.JScrollPane();

        grp_TField_Fr = new javax.swing.JTextArea();

        jPanel10 = new javax.swing.JPanel();

        jScrollPane3 = new javax.swing.JScrollPane();

        grp_TField_NL = new javax.swing.JTextArea();

        jPanel11 = new javax.swing.JPanel();

        jScrollPane4 = new javax.swing.JScrollPane();

        grp_TField_En = new javax.swing.JTextArea();

        jPanel7 = new javax.swing.JPanel();

        jPanel8 = new javax.swing.JPanel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jPanel12 = new javax.swing.JPanel();

        jScrollPane5 = new javax.swing.JScrollPane();

        grp_TField_Ge = new javax.swing.JTextArea();

        jPanel13 = new javax.swing.JPanel();

        jScrollPane6 = new javax.swing.JScrollPane();

        grp_TField_Es = new javax.swing.JTextArea();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("txt_legaux_title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        setPreferredSize(new java.awt.Dimension(30, 300));

        jPanel1.setLayout(new java.awt.BorderLayout());


        jPanel1.setPreferredSize(new java.awt.Dimension(10, 100));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 100));

        grp_Table_decision.setBackground(new java.awt.Color(255, 255, 255));

        grp_Table_decision.setModel(new javax.swing.table.DefaultTableModel());

        grp_Table_decision.setRequestFocusEnabled(false);

        jScrollPane1.setViewportView(grp_Table_decision);


        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel2.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("txt_legaux_stitle"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel6.setText("Recherche");

        jPanel2.add(jLabel6);


        grp_TField_Encode.setPreferredSize(new java.awt.Dimension(100, 18));

        jPanel2.add(grp_TField_Encode);


        jPanel4.setLayout(new java.awt.GridBagLayout());


        grp_Jradio_Aviation.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Aviation.setSelected(true);

        grp_Jradio_Aviation.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("txt_legaux_bcd"));

        buttonGroup1.add(grp_Jradio_Aviation);

        grp_Jradio_Aviation.setName("grp_Jradio_Aviation");

        grp_Jradio_Aviation.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusGained(java.awt.event.FocusEvent evt) {

                grp_Jradio_AviationFocusGained(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Aviation, gridBagConstraints);


        grp_Jradio_Brochure.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Brochure.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("txt_legaux_fact"));

        buttonGroup1.add(grp_Jradio_Brochure);

        grp_Jradio_Brochure.setName("grp_Jradio_Brochure");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Brochure, gridBagConstraints);


        grp_Jradio_Assurance.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Assurance.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("txt_legaux_vo"));

        buttonGroup1.add(grp_Jradio_Assurance);

        grp_Jradio_Assurance.setName("grp_Jradio_Assurance");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Assurance, gridBagConstraints);

        /*// grp_Jradio_Bateau.setFont(new java.awt.Font("Tahoma", 1, 10));

       //grp_Jradio_Bateau.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_Bateau"));

       //buttonGroup1.add(grp_Jradio_Bateau);

       //grp_Jradio_Bateau.setName("grp_Jradio_Bateau");

       gridBagConstraints = new java.awt.GridBagConstraints();

       gridBagConstraints.gridx = 3;

       gridBagConstraints.gridy = 1;

       gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

       jPanel4.add(grp_Jradio_Bateau, gridBagConstraints);



       grp_Jradio_Hotel.setFont(new java.awt.Font("Tahoma", 1, 10));

       grp_Jradio_Hotel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_hotel"));

       buttonGroup1.add(grp_Jradio_Hotel);

       grp_Jradio_Hotel.setName("grp_Jradio_Hotel");

       gridBagConstraints = new java.awt.GridBagConstraints();

       gridBagConstraints.gridx = 4;

       gridBagConstraints.gridy = 1;

       gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

       jPanel4.add(grp_Jradio_Hotel, gridBagConstraints);



       grp_Jradio_Taxi.setFont(new java.awt.Font("Tahoma", 1, 10));

       grp_Jradio_Taxi.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_taxi"));

       buttonGroup1.add(grp_Jradio_Taxi);

       grp_Jradio_Taxi.setName("grp_Jradio_Taxi");

       gridBagConstraints = new java.awt.GridBagConstraints();

       gridBagConstraints.gridx = 5;

       gridBagConstraints.gridy = 1;

       gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

       jPanel4.add(grp_Jradio_Taxi, gridBagConstraints);



       grp_Jradio_Train.setFont(new java.awt.Font("Tahoma", 1, 10));

       grp_Jradio_Train.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_train"));

       buttonGroup1.add(grp_Jradio_Train);

       grp_Jradio_Train.setName("grp_Jradio_Train");

       gridBagConstraints = new java.awt.GridBagConstraints();

       gridBagConstraints.gridx = 6;

       gridBagConstraints.gridy = 1;

       gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

       jPanel4.add(grp_Jradio_Train, gridBagConstraints);



       grp_Jradio_Voiture.setFont(new java.awt.Font("Tahoma", 1, 10));

       grp_Jradio_Voiture.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_voiture"));

       buttonGroup1.add(grp_Jradio_Voiture);

       grp_Jradio_Voiture.setName("grp_Jradio_Voiture");

       gridBagConstraints = new java.awt.GridBagConstraints();

       gridBagConstraints.gridx = 7;

       gridBagConstraints.gridy = 1;

       gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

       jPanel4.add(grp_Jradio_Voiture, gridBagConstraints);*/


        jPanel2.add(jPanel4);


        add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setLayout(new java.awt.GridLayout(1, 0));


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 200));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel5.setLayout(new java.awt.GridBagLayout());


        jLabel1.setText("FR");

        jLabel1.setPreferredSize(new java.awt.Dimension(80, 18));

        jPanel5.add(jLabel1, new java.awt.GridBagConstraints());


        jLabel2.setText("NDRL");

        jLabel2.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        jPanel5.add(jLabel2, gridBagConstraints);


        jLabel3.setText("EN");

        jLabel3.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        jPanel5.add(jLabel3, gridBagConstraints);


        jScrollPane2.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane2.setViewportView(grp_TField_Fr);


        jPanel9.add(jScrollPane2);


        jPanel5.add(jPanel9, new java.awt.GridBagConstraints());


        jScrollPane3.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane3.setViewportView(grp_TField_NL);


        jPanel10.add(jScrollPane3);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        jPanel5.add(jPanel10, gridBagConstraints);


        jScrollPane4.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane4.setViewportView(grp_TField_En);


        jPanel11.add(jScrollPane4);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        jPanel5.add(jPanel11, gridBagConstraints);


        jPanel6.add(jPanel5);


        jPanel3.add(jPanel6);


        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel8.setLayout(new java.awt.GridBagLayout());


        jLabel4.setText("GE");

        jLabel4.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);

        jPanel8.add(jLabel4, gridBagConstraints);


        jLabel5.setText("ES");

        jLabel5.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);

        jPanel8.add(jLabel5, gridBagConstraints);


        jScrollPane5.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane5.setViewportView(grp_TField_Ge);


        jPanel12.add(jScrollPane5);


        jPanel8.add(jPanel12, new java.awt.GridBagConstraints());


        jScrollPane6.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane6.setViewportView(grp_TField_Es);


        jPanel13.add(jScrollPane6);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 1;

        jPanel8.add(jPanel13, gridBagConstraints);


        jPanel7.add(jPanel8);


        jPanel3.add(jPanel7);


        add(jPanel3, java.awt.BorderLayout.SOUTH);


    }

    protected void initActions() {


        grp_Jradio_Assurance.addActionListener(this.click);

        grp_Jradio_Aviation.addActionListener(this.click);

        grp_Jradio_Brochure.addActionListener(this.click);

        requestFocus();


    }

    protected void showJradioButon(boolean visible) {

        grp_Jradio_Assurance.setVisible(visible);

        grp_Jradio_Aviation.setVisible(visible);

        grp_Jradio_Brochure.setVisible(visible);

        requestFocus();

    }

}

