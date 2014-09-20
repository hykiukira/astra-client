/*
* DossierPassagerPane.java
*
* Created on 27 août 2002, 14:38
*/
package srcastra.astra.gui.modules.dossier;

// Interfaces

import srcastra.astra.gui.components.AstraComponent;
import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.modules.InternScreenModule;
import srcastra.astra.gui.modules.dossier.utils.PassagerManager;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.sys.formVerification.DefaultMask;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.sys.classetransfert.utils.Date;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sébastien
 */
public class DossierPassagerEditionPane extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form DossierPassagerPane
     */
    public DossierPassagerEditionPane(DossierMainScreenModule parent, DossierPassagerPane internParent) {
        this.parent = parent;
        this.internParent = internParent;
        init();
        initListe();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INITIALISATION
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void init() {
        initComponents();


        this.composantToVerif = new AstraComponent[]{
                grp_LSelect_Passager_titre, grp_TField_Passager_nom, grp_TField_Passager_adresse,
                grp_LSelect_Passager_Cp, grp_LSelect_Passager_Pays, grp_TField_Passager_telephone, grp_TField_Passager_fax,
                grp_TField_Passager_gsm, grp_TField_Passager_email, grp_TField_Passager_codeMailing, grp_ADate_Passager_DateDeNaissance,
                grp_LSelect_Passager_Nat, grp_LSelect_Passager_Langue, grp_TField_Passager_BookingClass, grp_CBox_Passager_WindowSeat,
                grp_Cbox_Passager_SmokingSeat, grp_Cbos_passprinc};

        this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);
        tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_WITH_SWITCH);

//-> Régistration des listeners pour la validité des composants
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
        }
        setDocument();

    }

    public void initListe() {
        grp_LSelect_Passager_Nat.setServeur(parent.getServeur());
        grp_LSelect_Passager_Nat.setLogin(parent.getCurrentUser());
// this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());
        grp_LSelect_Passager_Nat.setModel(new srcastra.astra.gui.components.combobox.liste.NationaliteListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Passager_Nat.init2();
        grp_LSelect_Passager_titre.setServeur(parent.getServeur());
        grp_LSelect_Passager_titre.setLogin(parent.getCurrentUser());
// this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());
        grp_LSelect_Passager_titre.setModel(new srcastra.astra.gui.components.combobox.liste.TitrePersonneListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Passager_titre.init2();
        grp_LSelect_Passager_Pays.setServeur(parent.getServeur());
        grp_LSelect_Passager_Pays.setLogin(parent.getCurrentUser());
// this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());
        grp_LSelect_Passager_Pays.setModel(new srcastra.astra.gui.components.combobox.liste.PaysListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Passager_Pays.init2();
        grp_LSelect_Passager_Cp.setServeur(parent.getServeur());
        grp_LSelect_Passager_Cp.setLogin(parent.getCurrentUser());

// this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());
        grp_LSelect_Passager_Cp.setModel(new srcastra.astra.gui.components.combobox.liste.CpListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Passager_Cp.init2();
        grp_LSelect_Passager_Cp.grp_Comp_nextStringComp = this.grp_TField_Passager_localite;
        grp_LSelect_Passager_Langue.setServeur(parent.getServeur());
        grp_LSelect_Passager_Langue.setLogin(parent.getCurrentUser());

// this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());
        grp_LSelect_Passager_Langue.setModel(new srcastra.astra.gui.components.combobox.liste.LangueListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Passager_Langue.init2();

    }

    private void setDocument() {
        grp_TField_Passager_adresse.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_nom.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_Passager_telephone.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_fax.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_gsm.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_email.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_codeMailing.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Passager_BookingClass.setDocument(new DefaultMask(0, 25, parent.getCurrentUser().getLangage(), DefaultMask.CASE_UNSENSITIVE));
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        grp_Label_Passager_nom = new javax.swing.JLabel();
        grp_TField_Passager_nom = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Passager_nom.setHorizontalAlignment(JTextField.LEFT);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        grp_TField_Passager_localite = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        grp_TField_Passager_adresse = new srcastra.astra.gui.components.textFields.ATextField();
        grp_LSelect_Passager_titre = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Passager_Cp = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Passager_Pays = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        grp_Label_Passager_telephone = new javax.swing.JLabel();
        grp_TField_Passager_telephone = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_fax = new javax.swing.JLabel();
        grp_TField_Passager_fax = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_gsm = new javax.swing.JLabel();
        grp_TField_Passager_gsm = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_email = new javax.swing.JLabel();
        grp_TField_Passager_email = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_codeMailing = new javax.swing.JLabel();
        grp_TField_Passager_codeMailing = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_DateDeNaissance = new javax.swing.JLabel();
        grp_Label_Passager_Nat = new javax.swing.JLabel();
        grp_LSelect_Passager_Nat = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_ADate_Passager_DateDeNaissance = new srcastra.astra.gui.components.date.thedate.ADate();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        grp_Label_Passager_Langue = new javax.swing.JLabel();
        grp_Label_Passager_BookingClass = new javax.swing.JLabel();
        grp_TField_Passager_BookingClass = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Passager_WindowSeat = new javax.swing.JLabel();
        grp_Label_Passager_SmokingSeat = new javax.swing.JLabel();
        grp_CBox_Passager_WindowSeat = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_Cbox_Passager_SmokingSeat = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_LSelect_Passager_Langue = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel6 = new javax.swing.JLabel();
        grp_Cbos_passprinc = new srcastra.astra.gui.components.checkbox.ACheckBox();
        setLayout(new java.awt.BorderLayout());
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        jPanel2.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.setLayout(new java.awt.GridBagLayout());
        grp_Label_Passager_nom.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_nom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_nom"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(grp_Label_Passager_nom, gridBagConstraints);
        grp_TField_Passager_nom.setEnabled(false);
        grp_TField_Passager_nom.setGrp_Comp_nextComponent(grp_TField_Passager_adresse);
        grp_TField_Passager_nom.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_Passager_nom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Passager_nom, gridBagConstraints);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Passager_titre"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel1, gridBagConstraints);
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Passager_Cp"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel2, gridBagConstraints);
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_localite"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel3, gridBagConstraints);
        grp_TField_Passager_localite.setEnabled(false);
        grp_TField_Passager_localite.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Passager_localite, gridBagConstraints);
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Passager_Pays"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel4, gridBagConstraints);
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_adresse"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel5, gridBagConstraints);
        grp_TField_Passager_adresse.setEnabled(false);
        grp_TField_Passager_adresse.setGrp_Comp_nextComponent(grp_LSelect_Passager_Cp);
        grp_TField_Passager_adresse.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_Passager_adresse.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_adresse.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Passager_adresse, gridBagConstraints);
        grp_LSelect_Passager_titre.setEnabled(false);
        grp_LSelect_Passager_titre.setGrp_Comp_nextComponent(grp_TField_Passager_nom);
        grp_LSelect_Passager_titre.setUp(true);
        grp_LSelect_Passager_titre.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Passager_titre.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_LSelect_Passager_titre, gridBagConstraints);
        grp_LSelect_Passager_Cp.setCanbenull(true);
        grp_LSelect_Passager_Cp.setEnabled(false);
        grp_LSelect_Passager_Cp.setFilNexComponent(true);
        grp_LSelect_Passager_Cp.setGrp_Comp_nextComponent(grp_LSelect_Passager_Pays);
        grp_LSelect_Passager_Cp.setUp(true);
        grp_LSelect_Passager_Cp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Passager_Cp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_LSelect_Passager_Cp, gridBagConstraints);
        grp_LSelect_Passager_Pays.setCanbenull(true);
        grp_LSelect_Passager_Pays.setEnabled(false);
        grp_LSelect_Passager_Pays.setGrp_Comp_nextComponent(grp_TField_Passager_telephone);
        grp_LSelect_Passager_Pays.setUp(true);
        grp_LSelect_Passager_Pays.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Passager_Pays.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_LSelect_Passager_Pays, gridBagConstraints);
        jPanel5.add(jPanel7);
        jPanel2.add(jPanel5);
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel8.setLayout(new java.awt.GridBagLayout());
        grp_Label_Passager_telephone.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_telephone.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_telephone"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_telephone, gridBagConstraints);
        grp_TField_Passager_telephone.setEnabled(false);
        grp_TField_Passager_telephone.setGrp_Comp_nextComponent(grp_TField_Passager_fax);
        grp_TField_Passager_telephone.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_telephone.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_TField_Passager_telephone, gridBagConstraints);
        grp_Label_Passager_fax.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_fax.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_fax"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_fax, gridBagConstraints);
        grp_TField_Passager_fax.setEnabled(false);
        grp_TField_Passager_fax.setGrp_Comp_nextComponent(grp_TField_Passager_gsm);
        grp_TField_Passager_fax.setGrp_Comp_previousComponent(grp_TField_Passager_telephone);
        grp_TField_Passager_fax.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_fax.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_TField_Passager_fax, gridBagConstraints);
        grp_Label_Passager_gsm.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_gsm.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_gsm"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_gsm, gridBagConstraints);
        grp_TField_Passager_gsm.setEnabled(false);
        grp_TField_Passager_gsm.setGrp_Comp_nextComponent(grp_TField_Passager_email);
        grp_TField_Passager_gsm.setGrp_Comp_previousComponent(grp_TField_Passager_fax);
        grp_TField_Passager_gsm.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_gsm.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_TField_Passager_gsm, gridBagConstraints);
        grp_Label_Passager_email.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_email.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_email"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_email, gridBagConstraints);
        grp_TField_Passager_email.setEnabled(false);
        grp_TField_Passager_email.setGrp_Comp_nextComponent(grp_TField_Passager_codeMailing);
        grp_TField_Passager_email.setGrp_Comp_previousComponent(grp_TField_Passager_gsm);
        grp_TField_Passager_email.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_Passager_email.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_email.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_TField_Passager_email, gridBagConstraints);
        grp_Label_Passager_codeMailing.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_codeMailing.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_codeMailing"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_codeMailing, gridBagConstraints);
        grp_TField_Passager_codeMailing.setEnabled(false);
        grp_TField_Passager_codeMailing.setGrp_Comp_nextComponent(grp_ADate_Passager_DateDeNaissance);
        grp_TField_Passager_codeMailing.setGrp_Comp_previousComponent(grp_TField_Passager_email);
        grp_TField_Passager_codeMailing.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_codeMailing.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_TField_Passager_codeMailing, gridBagConstraints);
        grp_Label_Passager_DateDeNaissance.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_DateDeNaissance.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_ADate_Passager_DateDeNaissance"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_DateDeNaissance, gridBagConstraints);
        grp_Label_Passager_Nat.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_Nat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Passager_Nat"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel8.add(grp_Label_Passager_Nat, gridBagConstraints);
        grp_LSelect_Passager_Nat.setCanbenull(true);
        grp_LSelect_Passager_Nat.setEnabled(false);
        grp_LSelect_Passager_Nat.setGrp_Comp_nextComponent(grp_LSelect_Passager_Langue);
        grp_LSelect_Passager_Nat.setUp(true);
        grp_LSelect_Passager_Nat.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_LSelect_Passager_Nat, gridBagConstraints);
        grp_ADate_Passager_DateDeNaissance.setEnabled(false);
        grp_ADate_Passager_DateDeNaissance.setGrp_Comp_nextComponent(grp_LSelect_Passager_Nat);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel8.add(grp_ADate_Passager_DateDeNaissance, gridBagConstraints);
        jPanel6.add(jPanel8);
        jPanel2.add(jPanel6);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.setLayout(new java.awt.GridBagLayout());
        grp_Label_Passager_Langue.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_Langue.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Passager_Langue"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(grp_Label_Passager_Langue, gridBagConstraints);
        grp_Label_Passager_BookingClass.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_BookingClass.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Passager_BookingClass"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(grp_Label_Passager_BookingClass, gridBagConstraints);
        grp_TField_Passager_BookingClass.setEnabled(false);
        grp_TField_Passager_BookingClass.setGrp_Comp_nextComponent(grp_CBox_Passager_WindowSeat);
        grp_TField_Passager_BookingClass.setGrp_Comp_previousComponent(grp_ADate_Passager_DateDeNaissance);
        grp_TField_Passager_BookingClass.setLastFocusedComponent(true);
        grp_TField_Passager_BookingClass.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Passager_BookingClass.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_Passager_BookingClass, gridBagConstraints);
        grp_Label_Passager_WindowSeat.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_WindowSeat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_CBox_Passager_WindowSeat"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(grp_Label_Passager_WindowSeat, gridBagConstraints);
        grp_Label_Passager_SmokingSeat.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Passager_SmokingSeat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane", parent.getCurrentUser().getLangage()).getString("grp_Cbox_Passager_SmokingSeat"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(grp_Label_Passager_SmokingSeat, gridBagConstraints);
        grp_CBox_Passager_WindowSeat.setEnabled(false);
        grp_CBox_Passager_WindowSeat.setGrp_Comp_nextComponent(grp_Cbox_Passager_SmokingSeat);
        grp_CBox_Passager_WindowSeat.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_CBox_Passager_WindowSeat, gridBagConstraints);
        grp_Cbox_Passager_SmokingSeat.setEnabled(false);
        grp_Cbox_Passager_SmokingSeat.setGrp_Comp_nextComponent(grp_Cbos_passprinc);
        grp_Cbox_Passager_SmokingSeat.setLastFocusedComponent(true);
        grp_Cbox_Passager_SmokingSeat.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_Cbox_Passager_SmokingSeat, gridBagConstraints);
        grp_LSelect_Passager_Langue.setCanbenull(true);
        grp_LSelect_Passager_Langue.setEnabled(false);
        grp_LSelect_Passager_Langue.setGrp_Comp_nextComponent(grp_TField_Passager_BookingClass);
        grp_LSelect_Passager_Langue.setLeft(true);
        grp_LSelect_Passager_Langue.setUp(true);
        grp_LSelect_Passager_Langue.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Passager_Langue.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_LSelect_Passager_Langue, gridBagConstraints);
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierPassagerPane").getString("passageprinc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel6, gridBagConstraints);
        grp_Cbos_passprinc.setEnabled(false);
        grp_Cbos_passprinc.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_Cbos_passprinc, gridBagConstraints);
        jPanel1.add(jPanel3);
        jPanel2.add(jPanel1);
        add(jPanel2, java.awt.BorderLayout.CENTER);
    }//GEN-END:initComponents

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ActionListener validateAndDoPrevious = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {
                requestFocus();
                if (!parent.getDossier().isNewreccord())
                    parent.getDossier().setModifreccord(true);
                internParent.doPrevious();
            }
        }
    };
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isGoodForInsertion() {
        return !grp_TField_Passager_nom.getText().equals("");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Permet au parent de lancer le chargement des données au
     * sein de liste (Ici : ListSelector)
     */
    public void chargeData() {
    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
    }

    /**
     * Demande d'une insertion au serveur
     */
    private void chargePassagerClass(Passager_T tmpPassager) {
        tmpPassager.setTscleunik(grp_LSelect_Passager_titre.getCleUnik());
        tmpPassager.setTitrepassager(grp_LSelect_Passager_titre.getText());
        tmpPassager.setPr_nom(grp_TField_Passager_nom.getText());
        tmpPassager.setPr_adrese(grp_TField_Passager_adresse.getText());
        tmpPassager.setCscleunik(grp_LSelect_Passager_Cp.getCleUnik());
        tmpPassager.setCodenom(grp_LSelect_Passager_Cp.getText());
        tmpPassager.setPyscleunik(grp_LSelect_Passager_Pays.getCleUnik());
        tmpPassager.setPaysnom(grp_LSelect_Passager_Pays.getText());
        tmpPassager.setPr_tel(grp_TField_Passager_telephone.getText());
        tmpPassager.setPr_fax(grp_TField_Passager_fax.getText());
        tmpPassager.setPr_gsm(grp_TField_Passager_gsm.getText());
        tmpPassager.setPr_email(grp_TField_Passager_email.getText());
        tmpPassager.setPr_code_mailing(grp_TField_Passager_codeMailing.getText());
        tmpPassager.setPr_datenaissance(grp_ADate_Passager_DateDeNaissance.getDate());
        tmpPassager.setPr_nat(grp_LSelect_Passager_Nat.getCleUnik());
        tmpPassager.setPr_nationalité(grp_LSelect_Passager_Nat.getText());
        tmpPassager.setLecleunik(grp_LSelect_Passager_Langue.getCleUnik());
        tmpPassager.setLanguePassager(grp_LSelect_Passager_Langue.getText());
        tmpPassager.setPr_bookingclass(grp_TField_Passager_BookingClass.getText());
        tmpPassager.setMainPassager(grp_Cbos_passprinc.isSelected() ? 1 : 0);
        if (grp_CBox_Passager_WindowSeat.isSelected())
            tmpPassager.setPr_windowseat(1);
        else
            tmpPassager.setPr_windowseat(0);
        if (grp_Cbox_Passager_SmokingSeat.isSelected())
            tmpPassager.setPr_smoking(1);
        else
            tmpPassager.setPr_smoking(0);
    }

    public void dbInsert() {
        Dossier_T tmpdossier = parent.getDossier();
        Passager_T tmpPassager = pmanager.insertPassager(tmpdossier.getClientContractuel().getCscleunik());
        parent.decrementeTaxiCompteur();
        chargePassagerClass(tmpPassager);
        tmpPassager.setNewReccord(true);
        tmpPassager.setPr_cleunik(parent.getTaxiCompteur());
        tmpdossier.addPassager(tmpPassager);
        tmpdossier.setListPassagerModif(true);
//parent.setDossier(tmpdossier);
//tmpPassager.setPr_(grp_TField_Passager_telephone.getTe);
    }

    /**
     * Demande de sélection au serveur
     */
    public void dbSelect() {
    }

    /**
     * Demande de sélection en vue d'une modification au serveur
     */
    public void dbSelectForUpdate() {
    }

    /**
     * Demande d'une modification au serveur
     */
    public void dbUpdate() {
        chargePassagerClass(this.passager);
        parent.getDossier().setListPassagerModif(true);
        if (!this.passager.isNewReccord())
            this.passager.setModify(true);

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
        tb_interaction.enableValidateActionListener(false);
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
        tb_interaction.enableValidateActionListener(true);
        action = ActionToolBar.ACT_INSERT;
//parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CANCEL });
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(true);
            composantToVerif[i].setLastFocusedComponent(true);
            if (composantToVerif[i] instanceof srcastra.astra.gui.components.combobox.liste.Liste)
                ((srcastra.astra.gui.components.combobox.liste.Liste) composantToVerif[i]).setCleUnik(0);
            else
                composantToVerif[i].setText("");
        }
        checkPassagerPrincinsert();
// grp_ADate_Passager_DateDeNaissance.clearField();
        grp_TField_Passager_localite.setText("");
        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});
//grp_TField_Passager_BookingClass.setLastFocusedComponent(true);
//grp_LSelect_Passager_titre.setEnabled(true);
// fillClientPassagerClient();
        grp_LSelect_Passager_titre.requestFocus();
    }

    public void displayInsertMode(boolean sw, int passagerSelect) {
        displayInsertMode();
// if(passagerSelect==1 || passagerSelect==2)
        if (pmanager.isAskForList()) {
            try {
                java.util.ArrayList tmp = parent.getServeurDossier().chargeRemotePassager(parent.getCurrentUser(), parent.getCurrentUser().getUrlmcleunik(), parent.getDossier().getClientContractuel().getCscleunik(), passagerSelect, pmanager.isAskForClient());
                if (tmp != null)
                    parent.getDossier().setPassager(tmp);
            } catch (java.rmi.RemoteException re) {
                re.printStackTrace();

            }
        }
        if (pmanager.isAskForClient()) {
            fillClientPassagerClient();
            grp_LSelect_Passager_titre.requestFocus();
        } else {
            grp_LSelect_Passager_titre.requestFocus();
        }


    }

    private void fillClientPassagerClient() {
        grp_LSelect_Passager_titre.setCleUnik(parent.getDossier().getClientContractuel().getTscleunik());
//grp_LSelect_Passager_titre.setText(""+parent.getDossier().getClientContractuel().getTitrenom());
        grp_TField_Passager_nom.setText("" + parent.getDossier().getClientContractuel().getCsnom());
        grp_TField_Passager_nom.moveCaretPosition(0);
        grp_TField_Passager_adresse.setText("" + parent.getDossier().getClientContractuel().getCsadresse());
        grp_LSelect_Passager_Cp.setCleUnik(parent.getDossier().getClientContractuel().getCxcleunik());
// grp_LSelect_Passager_Cp.setText(""+parent.getDossier().getClientContractuel().getCodenom());
        grp_LSelect_Passager_Pays.setCleUnik(parent.getDossier().getClientContractuel().getPyscleunik());
// grp_LSelect_Passager_Pays.setText(""+parent.getDossier().getClientContractuel().getPysnom());
        grp_TField_Passager_telephone.setText("" + parent.getDossier().getClientContractuel().getCstelephonep());
        grp_TField_Passager_fax.setText("" + parent.getDossier().getClientContractuel().getCsfax());
        grp_TField_Passager_gsm.setText("" + parent.getDossier().getClientContractuel().getCsgsm());
        grp_TField_Passager_email.setText("" + parent.getDossier().getClientContractuel().getCsmailprincip());
        Date date = parent.getDossier().getClientContractuel().getCsdatenaiss();
        grp_ADate_Passager_DateDeNaissance.setDate(date);
//        grp_LSelect_Passager_Nat.setCleUnik(passager.getPr_nat());
// grp_LSelect_Passager_Nat.setText(""+passager.getNatnom());
        grp_LSelect_Passager_Langue.setCleUnik(parent.getDossier().getClientContractuel().getLecleunik());
        grp_Cbos_passprinc.setSelected(true);
// grp_LSelect_Passager_Langue.setText(""+parent.getDossier().getClientContractuel().getLanguenom());

    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
        tb_interaction.enableValidateActionListener(false);

        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_MODIFY,
                ActionToolBar.DO_CANCEL,
                ActionToolBar.DO_DELETE});
        chargePassager();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
        this.requestFocus();

    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
        tb_interaction.enableValidateActionListener(true);
        action = ActionToolBar.ACT_MODIFY;
        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});

        chargePassager();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(true);
            composantToVerif[i].setLastFocusedComponent(true);
        }

        checkPassagerPrincUpdate();
        grp_LSelect_Passager_titre.requestFocus();
        grp_LSelect_Passager_titre.requestFocus();
        updateUI();

    }

    private void checkPassagerPrincUpdate() {
        boolean sw = false;
        int cle = 0;
        if (parent.getDossier().getPassager() != null) {
            java.util.ArrayList tmp = parent.getDossier().getPassager();
            for (int i = 0; i < tmp.size(); i++) {
                Passager_T passtmp = (Passager_T) tmp.get(i);
                if (passtmp.getMainPassager() == 1) {
                    cle = new Double(passtmp.getPr_cleunik()).intValue();
                    sw = true;
                    break;
                }

            }
        }
        if (sw == true) {
            if (passager.getPr_cleunik() == cle) {
                grp_Cbos_passprinc.setEnabled(true);
                grp_Cbos_passprinc.addActionListener(validateAndDoPrevious);
                grp_Cbox_Passager_SmokingSeat.removeActionListener(validateAndDoPrevious);
            } else {
                grp_Cbos_passprinc.setEnabled(false);
                grp_Cbos_passprinc.removeActionListener(validateAndDoPrevious);
                grp_Cbox_Passager_SmokingSeat.addActionListener(validateAndDoPrevious);
                grp_Cbox_Passager_SmokingSeat.setGrp_Comp_nextComponent(null);
            }
        } else {
            grp_Cbos_passprinc.setEnabled(true);
            grp_Cbos_passprinc.addActionListener(validateAndDoPrevious);
            grp_Cbox_Passager_SmokingSeat.removeActionListener(validateAndDoPrevious);
        }
    }

    private void checkPassagerPrincinsert() {
        boolean sw = false;
        int cle = 0;
        if (parent.getDossier().getPassager() != null) {
            java.util.ArrayList tmp = parent.getDossier().getPassager();
            for (int i = 0; i < tmp.size(); i++) {
                Passager_T passtmp = (Passager_T) tmp.get(i);
                if (passtmp.getMainPassager() == 1) {
                    cle = new Double(passtmp.getPr_cleunik()).intValue();
                    sw = true;
                    break;
                }

            }
        }
        grp_Cbos_passprinc.setSelected(false);
        if (sw == false) {
            grp_Cbos_passprinc.setEnabled(true);
            grp_Cbos_passprinc.addActionListener(validateAndDoPrevious);
            grp_Cbox_Passager_SmokingSeat.removeActionListener(validateAndDoPrevious);
        } else {
            grp_Cbos_passprinc.setEnabled(false);
            grp_Cbos_passprinc.removeActionListener(validateAndDoPrevious);
            grp_Cbox_Passager_SmokingSeat.addActionListener(validateAndDoPrevious);
            grp_Cbox_Passager_SmokingSeat.setGrp_Comp_nextComponent(null);
        }

    }

    private void chargePassager() {
        grp_LSelect_Passager_titre.setCleUnik(passager.getTscleunik());
//  grp_LSelect_Passager_titre.setText(""+passager.getTitrepassager());
        grp_TField_Passager_nom.setText("" + passager.getPr_nom());
        grp_TField_Passager_nom.moveCaretPosition(0);
        grp_TField_Passager_adresse.setText("" + passager.getPr_adrese());
        grp_LSelect_Passager_Cp.setCleUnik(passager.getCscleunik());
// grp_LSelect_Passager_Cp.setText(""+passager.getCodenom());
        grp_LSelect_Passager_Pays.setCleUnik(passager.getPyscleunik());
// grp_LSelect_Passager_Pays.setText(""+passager.getPaysnom());
        grp_TField_Passager_telephone.setText("" + passager.getPr_tel());
        grp_TField_Passager_fax.setText("" + passager.getPr_fax());
        grp_TField_Passager_gsm.setText("" + passager.getPr_gsm());
        grp_TField_Passager_email.setText("" + passager.getPr_email());
        grp_TField_Passager_codeMailing.setText("" + passager.getPr_code_mailing());
        System.out.println("Passager date naissange " + passager.getPr_datenaissance().toString2());
        grp_ADate_Passager_DateDeNaissance.setDate(passager.getPr_datenaissance());
        grp_LSelect_Passager_Nat.setCleUnik(passager.getPr_nat());
// grp_LSelect_Passager_Nat.setText(""+passager.getNatnom());
        grp_LSelect_Passager_Langue.setCleUnik(passager.getLecleunik());
// grp_LSelect_Passager_Langue.setText(""+passager.getLanguePassager());
        grp_TField_Passager_BookingClass.setText("" + passager.getPr_bookingclass());
        grp_Cbos_passprinc.setSelected(passager.getMainPassager() == 1 ? true : false);
        if (passager.getPr_windowseat() == 1)
            grp_CBox_Passager_WindowSeat.setSelected(true);
        if (passager.getPr_smoking() == 1)
            grp_Cbox_Passager_SmokingSeat.setSelected(true);

    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Spécifie le composant qui implémente cette fonction comme
     * le composant qui pilote l'actionToolBar
     */
    public void setThisAsToolBarComponent() {
    }

    public void doAccept() {
/*  switch (action) {
case ActionToolBar.ACT_INSERT :
grp_Cbox_Passager_SmokingSeat.requestFocus();
repaint();
updateUI();
dbInsert();
action = ActionToolBar.ACT_READ;
internParent.requestFocus();
internParent.doCancel();
break;
case ActionToolBar.ACT_MODIFY :
dbUpdate();
displayReadMode();
break;

}*/
    }


    public void doCancel() {
/* switch (action) {
case ActionToolBar.ACT_INSERT :
action = ActionToolBar.ACT_READ;
internParent.doCancel();
break;
case ActionToolBar.ACT_MODIFY :
displayReadMode();
break;
case ActionToolBar.ACT_READ :
internParent.doCancel();
break;
}*/
    }

    public void doClose() {
    }

    public void doCreate() {
    }

    public void doDelete() {
    }

    public void doHelp() {
    }

    public void doModify() {
// displayUpdateMode();
    }

    public void doNext() {
    }

    public void doPrevious() {
    }

    public void doPrint() {
    }

    public void doSwitch() {
    }

    public int[] getDefaultActionToolBarMask() {
        return new int[0];
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int action;
    private int dr_cleunik;
    private DossierMainScreenModule parent;
    private AstraComponent[] composantToVerif;
    private ToolBarInteraction tb_interaction;
    private DossierPassagerPane internParent;
    private Passager_T passager;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_Passager_DateDeNaissance;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_Passager_WindowSeat;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbos_passprinc;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_Passager_SmokingSeat;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Passager_Cp;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Passager_Langue;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Passager_Nat;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Passager_Pays;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Passager_titre;
    private javax.swing.JLabel grp_Label_Passager_BookingClass;
    private javax.swing.JLabel grp_Label_Passager_DateDeNaissance;
    private javax.swing.JLabel grp_Label_Passager_Langue;
    private javax.swing.JLabel grp_Label_Passager_Nat;
    private javax.swing.JLabel grp_Label_Passager_SmokingSeat;
    private javax.swing.JLabel grp_Label_Passager_WindowSeat;
    private javax.swing.JLabel grp_Label_Passager_codeMailing;
    private javax.swing.JLabel grp_Label_Passager_email;
    private javax.swing.JLabel grp_Label_Passager_fax;
    private javax.swing.JLabel grp_Label_Passager_gsm;
    private javax.swing.JLabel grp_Label_Passager_nom;
    private javax.swing.JLabel grp_Label_Passager_telephone;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_BookingClass;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_adresse;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_codeMailing;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_email;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_fax;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_gsm;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_localite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_nom;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Passager_telephone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
// End of variables declaration//GEN-END:variables

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BEANS PROPERTIES GET/SET SUPPORT

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Permet à la classe qui implémente cette méthode de se
     * référencer auprès d' ActionToolBar
     *
     * @return le n° de l'action
     */
    public int getAction() {
        return action;
    }

    /**
     * Sert à recevoir le titre de son parent
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        return "# Passager #";
    }

    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * 0 pour lecture
     * 1 pour création
     * 2 pour modification
     *
     * @param action type d'action
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * au modules : création par partie ou modification
     *
     * @param frCleUnik la clé unique
     */
    public void setFrCleunik(int frCleUnik) {
        this.dr_cleunik = frCleUnik;
    }

    /**
     * Getter for property passager.
     *
     * @return Value of property passager.
     */
    public srcastra.astra.sys.classetransfert.dossier.Passager_T getPassager() {
        return passager;
    }

    /**
     * Setter for property passager.
     *
     * @param passager New value of property passager.
     */
    public void setPassager(srcastra.astra.sys.classetransfert.dossier.Passager_T passager) {
        this.passager = passager;
    }

    public void setLibellé(String libellé) {
    }

    public void goUp() {
    }


    public java.awt.Component m_getGeneriqueTable() {
        return null;
    }

    /**
     * Getter for property pmanager.
     *
     * @return Value of property pmanager.
     */
    public srcastra.astra.gui.modules.dossier.utils.PassagerManager getPmanager() {
        return pmanager;
    }

    /**
     * Setter for property pmanager.
     *
     * @param pmanager New value of property pmanager.
     */
    public void setPmanager(srcastra.astra.gui.modules.dossier.utils.PassagerManager pmanager) {
        this.pmanager = pmanager;
    }

    public void doF10() {
    }

    public void addKeystroque() {
    }

    public void doF7() {
    }

    PassagerManager pmanager;
}
