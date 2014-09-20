/*















 * BrochurePanel.java















 *















 * Created on 5 septembre 2002, 8:42















 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;
// Interfaces

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.modules.dossier.DossierProduitPane;
import srcastra.astra.gui.modules.InternScreenModule;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.TransportTableModel;
import srcastra.astra.gui.components.fx.*;
import srcastra.astra.gui.components.AstraComponent;

import javax.swing.event.TableModelEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// srcastra classes import
import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.sys.formVerification.*;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierDescriptionLogementTableModel;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.gui.sys.listModel.dossierListModel.descriptionLogement.*;
import srcastra.astra.gui.sys.listModel.*;
import srcastra.astra.gui.sys.*;

import java.awt.*;

import srcastra.astra.gui.sys.utils.*;
import srcastra.astra.gui.event.*;
import srcastra.astra.gui.sys.listModel.dossierListModel.*;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;
import srcastra.astra.gui.modules.dossier.productSpecification.InterfaceKeyProduit;
import srcastra.astra.sys.*;

/**
 * @author Sébastien
 */
public class BrochurePanel extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer, ComponentListener, InterfaceKeyProduit, InterfacePanel {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form BrochurePanel
     */
    public BrochurePanel(BrochureGui parent2) {
        m_parent = parent2;
        parent = m_parent.getMainScreenModule();
        init();
        m_parent.m_config.textarea = grp_TArea_ComplementDescriptif;
        this.setBounds(0, 0, 708, 365);
        displayReadMode();


    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INITIALISATION
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        // new javax.swing.JFrame().add(new BrochurePanel(null,null)).show();
    }

    private void init() {
        initComponents();
        componentToVerif = new AstraComponent[]{grp_TField_Contact, grp_TField_PO, grp_LSelect_Transport, grp_LSelect_Accomodation, grp_LSelect_Destination, grp_TField_Localite, grp_TField_logement, grp_ADate_Embarquement, grp_LSelect_LieuEmbarquement, grp_ADate_DateDebarquement, grp_LSelect_LieuDebarquement, grp_TField_NBrochure, grp_TField_RefCatalogue, grp_Cbox_AssuranceAnnulation, grp_Cbox_AssuranceBagages, grp_Cbox_AssuranceParticulier, grp_ACB_statut};
        if (!m_parent.m_config.test) {
            initListe();
            tb_interaction = new ToolBarInteraction(parent, this, componentToVerif);
            tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_WITH_SWITCH_PROD);
            for (int i = 0; i < componentToVerif.length; i++) {
                componentToVerif[i].addActionListener(tb_interaction.getValidateActionListener());


            }


        }
        // Set the border Manager & fx listeners
        //     fx_manager = new JPanelSelectionFxManager();
        javax.swing.border.LineBorder lb = new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 5);
        grp_TArea_ComplementDescriptif.addFocusListener(new BrochurePanel.FocusBorder(jScrollPane2, new javax.swing.border.LineBorder(java.awt.Color.black, 3)));
        grp_ACB_statut.addValidateFieldListener(ToMemo);
        grp_TArea_ComplementDescriptif.addKeyListener(memoKeyListener);
        setDocument();


    }

    public void requestOwnFocus() {
    }

    private void setDocument() {
        // Attention à modifier !!!
        grp_TField_Contact.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_PO.setDocument(new DefaultMask(1, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_Localite.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_logement.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_NBrochure.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_RefCatalogue.setDocument(new DefaultMask(0, 50, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));


    }

    private void chargeFxSelection() {
    }

    private void initTable() {
        grp_Table_DescriptionLogement = new BrochurePanel.Table();
        grp_Table_DescriptionLogement.setAutoCreateColumnsFromModel(false);
        grp_Table_DescriptionLogement.setModel(tb_logement);
        for (int i = 0; i < tb_logement.getColumnCount(); i++) {
            TableCellRenderer renderer;
            TableCellEditor editor;
            int type = tb_logement.getComboType(i);
            if (type != DossierDescriptionLogementTableModel.DATAID_QUANTITE) {
                BrochurePanel.Combo combo = new BrochurePanel.Combo(type);
                renderer = new DefaultTableCellRenderer();
                editor = new DefaultCellEditor(combo);


            } else {
                renderer = new DefaultTableCellRenderer();
                JTextField field = new JTextField();
                field.setDocument(new IntegerMask(1, 2));
                editor = new DefaultCellEditor(field);


            }
            TableColumn column = new TableColumn(i, 50, renderer, editor);
            grp_Table_DescriptionLogement.addColumn(column);


        }
        JTableHeader header = grp_Table_DescriptionLogement.getTableHeader();
        header.setUpdateTableInRealTime(false);


    }

    private void initListe() {
        grp_LSelect_Transport.setServeur(parent.getServeur());
        grp_LSelect_Transport.setLogin(parent.getCurrentUser());
        grp_LSelect_Transport.setModel(new srcastra.astra.gui.components.combobox.liste.TransportListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Transport.setClesignalitique(astrainterface.COMBO_TRANSPORT);
        grp_LSelect_Transport.setInterfaceManipulePanel(parent.getMainframe());
        grp_LSelect_Transport.init2();
        grp_LSelect_Accomodation.setServeur(parent.getServeur());
        grp_LSelect_Accomodation.setLogin(parent.getCurrentUser());
        grp_LSelect_Accomodation.setModel(new srcastra.astra.gui.components.combobox.liste.LogementListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Accomodation.setClesignalitique(astrainterface.COMBO_LOGEMENT);
        grp_LSelect_Accomodation.setInterfaceManipulePanel(parent.getMainframe());
        grp_LSelect_Accomodation.init2();
        grp_LSelect_Destination.setServeur(parent.getServeur());
        grp_LSelect_Destination.setLogin(parent.getCurrentUser());
        grp_LSelect_Destination.setModel(new srcastra.astra.gui.components.combobox.liste.DestinationtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Destination.setClesignalitique(astrainterface.COMBO_DESTINATION);
        grp_LSelect_Destination.setInterfaceManipulePanel(parent.getMainframe());
        grp_LSelect_Destination.init2();
        grp_LSelect_Destination.setFreeModeAllow(true);
        grp_LSelect_LieuDebarquement.setServeur(parent.getServeur());
        grp_LSelect_LieuDebarquement.setLogin(parent.getCurrentUser());
        grp_LSelect_LieuDebarquement.setModel(new srcastra.astra.gui.components.combobox.liste.EmbardDebarqtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_LieuDebarquement.setClesignalitique(astrainterface.COMBO_EMBARQDEBARQ);
        grp_LSelect_LieuDebarquement.setInterfaceManipulePanel(parent.getMainframe());
        grp_LSelect_LieuDebarquement.init2();
        grp_LSelect_LieuDebarquement.setFreeModeAllow(true);
        grp_LSelect_LieuEmbarquement.setServeur(parent.getServeur());
        grp_LSelect_LieuEmbarquement.setLogin(parent.getCurrentUser());
        grp_LSelect_LieuEmbarquement.setModel(new srcastra.astra.gui.components.combobox.liste.EmbardDebarqtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_LieuEmbarquement.setClesignalitique(astrainterface.COMBO_EMBARQDEBARQ);
        grp_LSelect_LieuEmbarquement.setInterfaceManipulePanel(parent.getMainframe());
        grp_LSelect_LieuEmbarquement.init2();
        grp_LSelect_LieuEmbarquement.setFreeModeAllow(true);

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;
        jPanel1 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        grp_Label_AssuranceAnnulation = new javax.swing.JLabel();
        grp_Cbox_AssuranceAnnulation = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jPanel24 = new javax.swing.JPanel();
        grp_Label_AssuranceBagages = new javax.swing.JLabel();
        grp_Cbox_AssuranceBagages = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jPanel25 = new javax.swing.JPanel();
        grp_Label_AssuranceParticulier = new javax.swing.JLabel();
        grp_Cbox_AssuranceParticulier = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jPanel12 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        grp_ACB_statut = new srcastra.astra.gui.components.combobox.aCombo.ACombo();
        jPanel31 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_TArea_ComplementDescriptif = new javax.swing.JTextPane();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        grp_Label_TO = new javax.swing.JLabel();
        grp_Label_GrpProduit = new javax.swing.JLabel();
        grp_Label_Contact = new javax.swing.JLabel();
        grp_TField_Contact = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_fournisseur = new javax.swing.JLabel();
        grp_Label_groupeProduit = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        grp_Label_Transport = new javax.swing.JLabel();
        grp_Label_Accomodation = new javax.swing.JLabel();
        grp_Label_Logement = new javax.swing.JLabel();
        grp_TField_logement = new srcastra.astra.gui.components.textFields.ATextField();
        grp_LSelect_Transport = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Accomodation = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_Pan_Broch3 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        grp_Label_Destination = new javax.swing.JLabel();
        grp_Label_Localite = new javax.swing.JLabel();
        grp_TField_Localite = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_PO = new javax.swing.JLabel();
        grp_TField_PO = new srcastra.astra.gui.components.textFields.ATextField();
        grp_LSelect_Destination = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        grp_Pan_Broch5 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        grp_Label_Embarquement = new javax.swing.JLabel();
        grp_ADate_Embarquement = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_LSelect_LieuEmbarquement = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel17 = new javax.swing.JPanel();
        grp_Label_DateDebarquement = new javax.swing.JLabel();
        grp_ADate_DateDebarquement = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_LSelect_LieuDebarquement = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel10 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        grp_Label_RefCatalogue = new javax.swing.JLabel();
        grp_TField_RefCatalogue = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_NBrochure = new javax.swing.JLabel();
        grp_TField_NBrochure = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel27 = new javax.swing.JPanel();
        setLayout(new java.awt.BorderLayout());
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setPreferredSize(new java.awt.Dimension(696, 45));
        jPanel32.setLayout(new java.awt.BorderLayout());
        jPanel32.setBorder(new javax.swing.border.EtchedBorder());
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel18.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("jLabel18"));
        jPanel29.add(jLabel18);
        jPanel9.add(jPanel29, java.awt.BorderLayout.NORTH);
        jPanel28.setLayout(new java.awt.GridLayout(1, 0));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        grp_Label_AssuranceAnnulation.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_AssuranceAnnulation.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_Cbox_AssuranceAnnulation"));
        jPanel23.add(grp_Label_AssuranceAnnulation);
        grp_Cbox_AssuranceAnnulation.setEnabled(false);
        grp_Cbox_AssuranceAnnulation.setGrp_Comp_nextComponent(grp_Cbox_AssuranceBagages);
        grp_Cbox_AssuranceAnnulation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel23.add(grp_Cbox_AssuranceAnnulation);
        jPanel28.add(jPanel23);
        grp_Label_AssuranceBagages.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_AssuranceBagages.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_Cbox_AssuranceBagages"));
        jPanel24.add(grp_Label_AssuranceBagages);
        grp_Cbox_AssuranceBagages.setEnabled(false);
        grp_Cbox_AssuranceBagages.setGrp_Comp_nextComponent(grp_Cbox_AssuranceParticulier);
        grp_Cbox_AssuranceBagages.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel24.add(grp_Cbox_AssuranceBagages);
        jPanel28.add(jPanel24);
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        grp_Label_AssuranceParticulier.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_AssuranceParticulier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_Cbox_AssuranceParticulier"));
        jPanel25.add(grp_Label_AssuranceParticulier);
        grp_Cbox_AssuranceParticulier.setEnabled(false);
        grp_Cbox_AssuranceParticulier.setGrp_Comp_nextComponent(grp_ACB_statut);
        grp_Cbox_AssuranceParticulier.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel25.add(grp_Cbox_AssuranceParticulier);
        jPanel28.add(jPanel25);
        jPanel9.add(jPanel28, java.awt.BorderLayout.CENTER);
        jPanel32.add(jPanel9, java.awt.BorderLayout.CENTER);
        jPanel12.setLayout(new java.awt.BorderLayout());
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 20));
        jPanel30.add(jSeparator1);
        grp_ACB_statut.setEnabled(false);
        grp_ACB_statut.setModel(new StatusListModel(parent.getServeur(), parent.getCurrentUser()));
        grp_ACB_statut.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel30.add(grp_ACB_statut);
        jPanel12.add(jPanel30, java.awt.BorderLayout.CENTER);
        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_ACB_statut"));
        jPanel31.add(jLabel1);
        jPanel12.add(jPanel31, java.awt.BorderLayout.NORTH);
        jPanel32.add(jPanel12, java.awt.BorderLayout.EAST);
        jPanel1.add(jPanel32, java.awt.BorderLayout.NORTH);
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.setBorder(new javax.swing.border.EtchedBorder());
        jPanel8.setMinimumSize(new java.awt.Dimension(126, 45));
        jPanel8.setPreferredSize(new java.awt.Dimension(696, 80));
        jPanel18.setLayout(new java.awt.BorderLayout());
        jPanel18.add(jPanel20, java.awt.BorderLayout.WEST);
        jPanel18.add(jPanel21, java.awt.BorderLayout.EAST);
        jPanel18.add(jPanel22, java.awt.BorderLayout.SOUTH);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(3, 20));
        grp_TArea_ComplementDescriptif.setFont(new java.awt.Font("Courier New", 0, 11));
        jScrollPane2.setViewportView(grp_TArea_ComplementDescriptif);
        jPanel18.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        jPanel8.add(jPanel18, java.awt.BorderLayout.CENTER);
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TArea_ComplementDescriptif"));
        jPanel19.add(jLabel7);
        jPanel8.add(jPanel19, java.awt.BorderLayout.NORTH);
        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);
        add(jPanel1, java.awt.BorderLayout.CENTER);
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.setPreferredSize(new java.awt.Dimension(696, 140));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.setPreferredSize(new java.awt.Dimension(696, 72));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));
        jPanel6.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        jPanel6.setPreferredSize(new java.awt.Dimension(686, 52));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel13.setLayout(new java.awt.GridBagLayout());
        grp_Label_TO.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_TO.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_TO"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel13.add(grp_Label_TO, gridBagConstraints);
        grp_Label_GrpProduit.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_GrpProduit.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_GrpProduit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel13.add(grp_Label_GrpProduit, gridBagConstraints);
        grp_Label_Contact.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Contact.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_Contact"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel13.add(grp_Label_Contact, gridBagConstraints);
        grp_TField_Contact.setEnabled(false);
        grp_TField_Contact.setGrp_Comp_nextComponent(grp_LSelect_Transport);
        grp_TField_Contact.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Contact.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel13.add(grp_TField_Contact, gridBagConstraints);
        grp_Label_fournisseur.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_fournisseur.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_fournisseur.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel13.add(grp_Label_fournisseur, gridBagConstraints);
        grp_Label_groupeProduit.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_groupeProduit.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_groupeProduit.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel13.add(grp_Label_groupeProduit, gridBagConstraints);
        jPanel5.add(jPanel13);
        jPanel6.add(jPanel5);
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel14.setLayout(new java.awt.GridBagLayout());
        grp_Label_Transport.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Transport.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Transport"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel14.add(grp_Label_Transport, gridBagConstraints);
        grp_Label_Accomodation.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Accomodation.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Accomodation"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel14.add(grp_Label_Accomodation, gridBagConstraints);
        grp_Label_Logement.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Logement.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Logement"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel14.add(grp_Label_Logement, gridBagConstraints);
        grp_TField_logement.setEnabled(false);
        grp_TField_logement.setGrp_Comp_nextComponent(grp_TField_Localite);
        grp_TField_logement.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel14.add(grp_TField_logement, gridBagConstraints);
        grp_LSelect_Transport.setAffiche_panel(true);
        grp_LSelect_Transport.setEnabled(false);
        grp_LSelect_Transport.setGrp_Comp_nextComponent(grp_LSelect_Accomodation);
        grp_LSelect_Transport.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Transport.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel14.add(grp_LSelect_Transport, gridBagConstraints);
        grp_LSelect_Accomodation.setAffiche_panel(true);
        grp_LSelect_Accomodation.setEnabled(false);
        grp_LSelect_Accomodation.setGrp_Comp_nextComponent(grp_TField_logement);
        grp_LSelect_Accomodation.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Accomodation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel14.add(grp_LSelect_Accomodation, gridBagConstraints);
        jPanel11.add(jPanel14);
        jPanel6.add(jPanel11);
        grp_Pan_Broch3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel15.setLayout(new java.awt.GridBagLayout());
        grp_Label_Destination.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Destination.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Destination"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel15.add(grp_Label_Destination, gridBagConstraints);
        grp_Label_Localite.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Localite.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_Localite"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel15.add(grp_Label_Localite, gridBagConstraints);
        grp_TField_Localite.setEnabled(false);
        grp_TField_Localite.setGrp_Comp_nextComponent(grp_LSelect_Destination);
        grp_TField_Localite.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_Localite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Localite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel15.add(grp_TField_Localite, gridBagConstraints);
        grp_Label_PO.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_PO.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_PO"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel15.add(grp_Label_PO, gridBagConstraints);
        grp_TField_PO.setEnabled(false);
        grp_TField_PO.setGrp_Comp_nextComponent(grp_ADate_Embarquement);
        grp_TField_PO.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_PO.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel15.add(grp_TField_PO, gridBagConstraints);
        grp_LSelect_Destination.setAffiche_panel(true);
        grp_LSelect_Destination.setEnabled(false);
        grp_LSelect_Destination.setGrp_Comp_nextComponent(grp_TField_PO);
        grp_LSelect_Destination.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Destination.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel15.add(grp_LSelect_Destination, gridBagConstraints);
        grp_Pan_Broch3.add(jPanel15);
        jPanel6.add(grp_Pan_Broch3);
        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);
        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(696, 150));
        jPanel7.setLayout(new java.awt.GridLayout(2, 1));
        jPanel7.setPreferredSize(new java.awt.Dimension(696, 69));
        grp_Pan_Broch5.setLayout(new java.awt.GridLayout(1, 0));
        grp_Pan_Broch5.setBorder(new javax.swing.border.EtchedBorder());
        grp_Pan_Broch5.setMaximumSize(new java.awt.Dimension(32767, 20));
        grp_Pan_Broch5.setMinimumSize(new java.awt.Dimension(236, 20));
        grp_Pan_Broch5.setPreferredSize(new java.awt.Dimension(752, 20));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel16.setMinimumSize(new java.awt.Dimension(116, 5));
        jPanel16.setPreferredSize(new java.awt.Dimension(374, 5));
        grp_Label_Embarquement.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Embarquement.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_ADate_Embarquement"));
        jPanel16.add(grp_Label_Embarquement);
        grp_ADate_Embarquement.setCheckUptodate(true);
        grp_ADate_Embarquement.setEnabled(false);
        grp_ADate_Embarquement.setForm(this);
        grp_ADate_Embarquement.setGrp_Comp_nextComponent(grp_LSelect_LieuEmbarquement);
        grp_ADate_Embarquement.setUser(parent.getCurrentUser());
        grp_ADate_Embarquement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_Embarquement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel16.add(grp_ADate_Embarquement);
        grp_LSelect_LieuEmbarquement.setAffiche_panel(true);
        grp_LSelect_LieuEmbarquement.setCanbenull(true);
        grp_LSelect_LieuEmbarquement.setEnabled(false);
        grp_LSelect_LieuEmbarquement.setGrp_Comp_nextComponent(grp_ADate_DateDebarquement);
        grp_LSelect_LieuEmbarquement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_LieuEmbarquement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel16.add(grp_LSelect_LieuEmbarquement);
        grp_Pan_Broch5.add(jPanel16);
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        grp_Label_DateDebarquement.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_DateDebarquement.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_ADate_DateDebarquement"));
        jPanel17.add(grp_Label_DateDebarquement);
        grp_ADate_DateDebarquement.setDateBeforeComp(grp_ADate_Embarquement);
        grp_ADate_DateDebarquement.setEnabled(false);
        grp_ADate_DateDebarquement.setForm(this);
        grp_ADate_DateDebarquement.setGrp_Comp_nextComponent(grp_LSelect_LieuDebarquement);
        grp_ADate_DateDebarquement.setUser(parent.getCurrentUser());
        grp_ADate_DateDebarquement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_DateDebarquement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel17.add(grp_ADate_DateDebarquement);
        grp_LSelect_LieuDebarquement.setAffiche_panel(true);
        grp_LSelect_LieuDebarquement.setCanbenull(true);
        grp_LSelect_LieuDebarquement.setEnabled(false);
        grp_LSelect_LieuDebarquement.setGrp_Comp_nextComponent(grp_TField_RefCatalogue);
        grp_LSelect_LieuDebarquement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_LieuDebarquement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel17.add(grp_LSelect_LieuDebarquement);
        grp_Pan_Broch5.add(jPanel17);
        jPanel7.add(grp_Pan_Broch5);
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));
        jPanel10.setBorder(new javax.swing.border.EtchedBorder());
        jPanel10.setPreferredSize(new java.awt.Dimension(416, 20));
        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        grp_Label_RefCatalogue.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_RefCatalogue.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_RefCatalogue"));
        jPanel26.add(grp_Label_RefCatalogue);
        grp_TField_RefCatalogue.setEnabled(false);
        grp_TField_RefCatalogue.setGrp_Comp_nextComponent(grp_TField_NBrochure);
        grp_TField_RefCatalogue.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_RefCatalogue.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel26.add(grp_TField_RefCatalogue);
        grp_Label_NBrochure.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_NBrochure.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/BrochurePanel", parent.getCurrentUser().getLangage()).getString("grp_TField_NBrochure"));
        jPanel26.add(grp_Label_NBrochure);
        grp_TField_NBrochure.setEnabled(false);
        grp_TField_NBrochure.setGrp_Comp_nextComponent(grp_Cbox_AssuranceAnnulation);
        grp_TField_NBrochure.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_NBrochure.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel26.add(grp_TField_NBrochure);
        jPanel10.add(jPanel26);
        jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanel10.add(jPanel27);
        jPanel7.add(jPanel10);
        jPanel4.add(jPanel7);
        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);
        add(jPanel2, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {
    }

    public void componentMoved(java.awt.event.ComponentEvent componentEvent) {
    }

    public void componentResized(java.awt.event.ComponentEvent componentEvent) {
    }

    public void componentShown(java.awt.event.ComponentEvent componentEvent) {
        //parent.setCurrentPanel(this);
        switch (action) {
            case ActionToolBar.ACT_READ:
                displayReadMode();
                break;
            case ActionToolBar.ACT_INSERT:
                displayInsertMode();
                break;


        }


    }

    public ValidateField ToTable = new ValidateField() {
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
            grp_Table_DescriptionLogement.changeSelection(0, 0, false, false);
            grp_Table_DescriptionLogement.requestFocus();


        }


    };
    public ValidateField ToMemo = new ValidateField() {
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
            grp_TArea_ComplementDescriptif.requestFocus();


        }


    };
    public KeyListener memoKeyListener = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F12) {
                grp_Cbox_AssuranceAnnulation.requestFocus();


            }


        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }


    };
///// FOCUSBORDER ////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected class FocusBorder implements java.awt.event.FocusListener {
        private javax.swing.border.Border m_default_border;
        private javax.swing.border.Border m_focused_border;
        private javax.swing.JComponent m_focused_comp;

        public FocusBorder(javax.swing.JComponent focusedComp, javax.swing.border.Border focusedBorder) {
            m_default_border = focusedComp.getBorder();
            m_focused_border = focusedBorder;
            m_focused_comp = focusedComp;


        }

        public void focusGained(java.awt.event.FocusEvent focusEvent) {
            m_focused_comp.setBorder(m_focused_border);


        }

        public void focusLost(java.awt.event.FocusEvent focusEvent) {
            m_focused_comp.setBorder(m_default_border);


        }


    }
///// MEMOENABLEDCOLOR ////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // --> désaffiche le panneau des suppléments

    public void displaySegmentPanel() {
        //   grp_LPane_layer.moveToBack(grp_Pan_SupReduc);
    }

    public void displaySupReducEditionPanel(boolean show, int action) {

    }

    public SupReducPanelEdition getGrp_Pan_SupReducPanelEdition() {
        return null;//grp_Pan_SupReducEdition;


    }

    public DossierProduitPane getGrp_Pane_produit() {
        return m_parentproduit;


    }
    // --> affiche le panneau des suppléments

    public void removeSegmentPanel() {
        //  grp_LPane_layer.moveToFront(grp_Pan_SupReduc);
    }

    public void retrieveLogementDescriptionData() {
    }

    public void reloadTableModel() {
        retrieveLogementDescriptionData();

    }

    public void enableMemo(boolean enabled) {
        java.awt.Color bg = (enabled) ? java.awt.Color.white : java.awt.Color.lightGray;
        java.awt.Color fg = java.awt.Color.black;
        grp_TArea_ComplementDescriptif.setBackground(bg);
        grp_TArea_ComplementDescriptif.setForeground(fg);
        grp_TArea_ComplementDescriptif.setEditable(enabled);
        grp_TArea_ComplementDescriptif.setEnabled(enabled);
        if (grp_TArea_ComplementDescriptif.getText().equals("null")) grp_TArea_ComplementDescriptif.setText("");


    }

    public void enabledTable(boolean enabled) {
    }

    protected class Combo extends javax.swing.JComboBox /*implements TableCellRenderer*/ {
        private int m_dataID;

        public Combo(int dataId) {
            super();
            this.m_dataID = dataId;
//           setModel(tb_logement.getComboModel(dataId));
            //setRenderer(this);
        }

        private String getAbreviation(int key) {
            Abrev abrev = (Abrev) getModel();
            return abrev.loadAbrev(key);


        }

    }

    protected class Table extends javax.swing.JTable implements java.awt.event.KeyListener {
        public Table() {
            super();
            addKeyListener(this);


        }

        public void addNewRow() {
            DescriptionLogement_T desc = new DescriptionLogement_T();
            desc.setDlt_quantité(1);
            desc.setDlt_xlit(1);
            desc.setDlt_commodite(1);
            desc.setDlt_situation(1);
            desc.setDlt_vue(1);
            desc.setDlt_regime(1);
            parent.getBrochure().addDescriptionLogement(desc);
            reloadInfo();


        }

        public void deleteRow(int index) {
            if (tb_logement.getRowCount() > 1) {
                DescriptionLogement_T obj = (DescriptionLogement_T) tb_logement.renvObject(index);
                parent.getBrochure().removeDescriptionLogement(obj);
                reloadInfo();


            }


        }

        public void reloadInfo() {
            tb_logement.retrieveData();
            tableChanged(new javax.swing.event.TableModelEvent(tb_logement));
            repaint();


        }

        public void keyPressed(java.awt.event.KeyEvent keyEvent) {
            if (keyEvent.isAltDown()) {
                if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_F2) {
                    addNewRow();
                    changeSelection(tb_logement.getRowCount() - 1, 0, false, false);
                    getCellEditor(tb_logement.getRowCount() - 1, 0).cancelCellEditing();


                }
                if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_F9) {
                    int row = getSelectedRow();
                    if (row >= 0) {
                        deleteRow(row);
                        changeSelection(((row > 0) ? row - 1 : 0), 0, false, false);


                    }


                }
                if (keyEvent.getKeyCode() == java.awt.event.KeyEvent.VK_F12) {
                    grp_ADate_Embarquement.requestFocus();


                }


            }


        }

        public void keyReleased(java.awt.event.KeyEvent keyEvent) {
        }

        public void keyTyped(java.awt.event.KeyEvent keyEvent) {
        }


    }

    private boolean isValidValeur() {
        return true;

    }

    public void displayDescriptionLogementPanel() {
        // grp_LPane_layer.moveToFront(grp_Pan_Brochure);
        // grp_LPane_layer.moveToFront(grp_Pan_descLogement);
    }

    public void removeDescriptionLogementPanel() {
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permet au parent de lancer le chargement des données au
     * sein de liste (Ici : ListSelector)
     */
    public void chargeData() {
        Brochure_T broch = parent.getBrochure();
        grp_Label_fournisseur.setText("" + broch.getFrnom());
        grp_Label_groupeProduit.setText("" + broch.getGroupe_produit_nom());
        grp_TField_Contact.setText("" + broch.getBro_contact());
        grp_LSelect_Transport.setCleUnik(broch.getTecleunik_trans());
        grp_LSelect_Accomodation.setCleUnik(broch.getBro_accomodation());
        grp_TField_logement.setText("" + broch.getBro_logement());
        grp_TField_Localite.setText("" + broch.getBro_localite());
        srcastra.astra.gui.components.combobox.liste.ManageFreeListe.setValue(broch.getDe_cleunik(), broch.getFree_destination(), grp_LSelect_Destination);
        //grp_LSelect_Destination.setCleUnik(broch.getDe_cleunik());
        grp_TField_PO.setText("" + broch.getBro_po());
        grp_ADate_Embarquement.setDate(broch.getBro_embarq());
        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[***************************]ID embarq :" + broch.getBro_lieuEmbarq_cleUnik() + " ID debarq" + broch.getBro_lieuDebarq_cleUnik());
        srcastra.astra.gui.components.combobox.liste.ManageFreeListe.setValue(broch.getBro_lieuEmbarq_cleUnik(), broch.getBro_lieuEmbarq_libele(), grp_LSelect_LieuEmbarquement);
        // grp_LSelect_LieuEmbarquement.setCleUnik(broch.getBro_lieuEmbarq_cleUnik());
        grp_ADate_DateDebarquement.setDate(broch.getBro_debarq());
        srcastra.astra.gui.components.combobox.liste.ManageFreeListe.setValue(broch.getBro_lieuDebarq_cleUnik(), broch.getBro_lieuDebarq_libele(), grp_LSelect_LieuDebarquement);
        // grp_LSelect_LieuDebarquement.setCleUnik(broch.getBro_lieuDebarq_cleUnik());
        grp_TField_NBrochure.setText("" + broch.getBro_num());
        grp_TField_RefCatalogue.setText("" + broch.getBro_ref_catalog());
        grp_TArea_ComplementDescriptif.setText("" + broch.getBro_memo());
        grp_Cbox_AssuranceAnnulation.setSelected(broch.getBro_ass_anul() == 1);
        grp_Cbox_AssuranceBagages.setSelected(broch.getBro_ass_bag() == 1);
        grp_Cbox_AssuranceParticulier.setSelected(broch.getBro_ass_part() == 1);
        grp_ACB_statut.setSelectedCleUnik(broch.getStatut());


    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
    }

    /**
     * Demande d'une insertion au serveur
     */
    public void dbInsert() {
        chargeBrochureClassInsert();
        parent.getBrochure().setLocalyModify(true);
        parent.calculTotalUnProduit(parent.getBrochure());
        displayReadMode();


    }

    private void chargeBrochureClassInsert() {
        if (!parent.getDossier().isNewreccord()) parent.getDossier().setModifreccord(true);
        parent.getBrochure().setAt_cleunik(parent.getBrochureCompteur());
        parent.decrementeBrochureCompteur();
        parent.getBrochure().setIsnewreccord(true);
        parent.getBrochure().setLongtime();
        chargeBrochureClass();


    }

    private Brochure_T chargeBrochureClass() {
        parent.getBrochure().setBro_contact(grp_TField_Contact.getText());
        parent.getBrochure().setTecleunik_trans(grp_LSelect_Transport.getCleUnik());
        parent.getBrochure().setBro_transport(grp_LSelect_Transport.getText());
        parent.getBrochure().setBro_accomodation(grp_LSelect_Accomodation.getCleUnik());
        parent.getBrochure().setBro_accomodation_libele(grp_LSelect_Accomodation.getText());
        parent.getBrochure().setBro_logement(grp_TField_logement.getText());
        parent.getBrochure().setBro_localite(grp_TField_Localite.getText());
        parent.getBrochure().setDe_cleunik(grp_LSelect_Destination.getCleUnik());
        parent.getBrochure().setBro_destination(grp_LSelect_Destination.getText());
        parent.getBrochure().setFree_destination(grp_LSelect_Destination.getFreetexte());
        parent.getBrochure().setBro_po(grp_TField_PO.getText());
        parent.getBrochure().setBro_embarq(grp_ADate_Embarquement.getDate());
        parent.getBrochure().setBro_lieuEmbarq_cleUnik(grp_LSelect_LieuEmbarquement.getCleUnik());
        parent.getBrochure().setBro_lieuEmbarq_libele(grp_LSelect_LieuEmbarquement.getFreetexte());
        parent.getBrochure().setBro_debarq(grp_ADate_DateDebarquement.getDate());
        parent.getBrochure().setBro_lieuDebarq_cleUnik(grp_LSelect_LieuDebarquement.getCleUnik());
        parent.getBrochure().setBro_lieuDebarq_libele(grp_LSelect_LieuDebarquement.getFreetexte());
        parent.getBrochure().setBro_num(grp_TField_NBrochure.getText());
        parent.getBrochure().setBro_ref_catalog(grp_TField_RefCatalogue.getText());
        parent.getBrochure().setBro_memo(grp_TArea_ComplementDescriptif.getText());
        int tmp = (grp_Cbox_AssuranceAnnulation.isSelected()) ? 1 : 0;
        parent.getBrochure().setBro_ass_anul(tmp);
        tmp = (grp_Cbox_AssuranceBagages.isSelected()) ? 1 : 0;
        parent.getBrochure().setBro_ass_bag(tmp);
        tmp = (grp_Cbox_AssuranceParticulier.isSelected()) ? 1 : 0;
        parent.getBrochure().setBro_ass_part(tmp);
        parent.getBrochure().setStatut(grp_ACB_statut.getSelectedCleUnik());
        parent.getBrochure().calculMontantTotal();
        parent.getBrochure().prepareAffichage();
        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[********************************]Embarquement 1 :" + parent.getBrochure().getBro_lieuEmbarq_cleUnik() + " debarque :" + parent.getBrochure().getBro_lieuDebarq_cleUnik());
        return brochure;

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
        if (!parent.getDossier().isNewreccord()) parent.getDossier().setModifreccord(true);
        if (!parent.getBrochure().isIsnewreccord()) parent.getBrochure().setModify(true);
        chargeBrochureClass();
        displayReadMode();

    }

    public void validateProduct() {
        System.out.println("\n\n\n¨******************ISNERECORD?:" + parent.getBrochure().isIsnewreccord());
        System.out.println("\n\n\n¨******************ISATTACHED?:" + parent.getBrochure().isIsattached());
        System.out.println("\n\n\n¨******************ISMODIFY?:" + parent.getBrochure().isModify());
        //if(parent.getTicket().isIsnewreccord()|| parent.getTicket().isModify()){
        System.out.println("\n\n[okokkokokoInsert]");
        parent.getBrochure().setIsattached(true);
        if (parent.getDossier().getPoBilletIntitule() != null && parent.getDossier().getFournIntitule() != null && parent.getDossier().getDestIntitule() != null) {
            if (parent.getDossier().getPoBilletIntitule().equals(""))
                parent.getDossier().setPoBilletIntitule(parent.getBrochure().getBro_po());
            if (parent.getDossier().getFournIntitule().equals(""))
                parent.getDossier().setFournIntitule(parent.getBrochure().getFrnom());
            if (parent.getDossier().getDestIntitule().equals(""))
                parent.getDossier().setDestIntitule(parent.getBrochure().getBro_destination());


        }
        parent.getDossier().addBrochure(parent.getBrochure());
        parent.getBrochure().setLocalyModify(false);
        m_parentproduit.showProductPanel(0, 0);
        parent.chargeStatusPanel();
        //  }
        // else System.out.println("\n\n[okokkokokoRien]");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void chargeDefaultValue() {
        for (int i = 0; i < componentToVerif.length; i++) {
            if (componentToVerif[i] instanceof srcastra.astra.gui.components.combobox.liste.Liste) ;
            else componentToVerif[i].setText("");


        }
        //    grp_Cbox_AssuranceAnnulation.setSelected(true);
        if (!m_parent.m_config.test) {
            grp_Label_fournisseur.setText(parent.getBrochure().getFrnom());
            grp_Label_groupeProduit.setText(parent.getBrochure().getGroupe_produit_nom());
            if (parent.getDossier().getDr_date_depart() != null)
                grp_ADate_Embarquement.setDate(parent.getDossier().getDr_date_depart());
            if (parent.getDossier().getDr_date_retour() != null)
                grp_ADate_DateDebarquement.setDate(parent.getDossier().getDr_date_retour());


        }
        grp_ACB_statut.setSelectedIndex(0);


    }

    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
        if (!m_parent.m_config.test) tb_interaction.enableValidateActionListener(false);
        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].setEnabled(false);
            componentToVerif[i].setLastFocusedComponent(false);


        }


    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
        chargeDefaultValue();
        //   chargeFxSelection();
//        if (grp_Table_DescriptionLogement.getRowCount() < 1) grp_Table_DescriptionLogement.addNewRow();
        openFields(ActionToolBar.ACT_INSERT);
        action = ActionToolBar.ACT_INSERT;
        if (!m_parent.m_config.test) tb_interaction.enableValidateActionListener(true);
        //  reloadTableModel();
        grp_TField_Contact.requestFocus();


    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
        action = ActionToolBar.ACT_READ;
        if (!m_parent.m_config.test) {
            tb_interaction.enableValidateActionListener(false);
            chargeData();


        }
        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].setEnabled(false);
            componentToVerif[i].setLastFocusedComponent(false);


        }
        enableMemo(false);
        enabledTable(false);
        /*   parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_MODIFY,















       ActionToolBar.DO_CANCEL,















       ActionToolBar.DO_PREVIOUS,















       ActionToolBar.DO_SWITCH });*/
        //   retrieveLogementDescriptionData();
        grp_TArea_ComplementDescriptif.setEnabled(false);
        // euh ! faudrait -il empêcher la table d'etre éditable en mode read ????
        // grp_Pan_descLogement.reloadTableInfo();
        //    fx_manager.needReselect();
    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
        //tb_interaction.enableValidateActionListener(true);
        openFields(ActionToolBar.ACT_MODIFY);
        action = ActionToolBar.ACT_MODIFY;
        /*     parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_PREVIOUS,















   ActionToolBar.DO_CANCEL });    */
    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }

    private void openFields(int action) {
        //  fx_manager.setActivated(false);
        action = action;
        //   if (action != ActionToolBar.ACT_INSERT) chargeData();
        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].setEnabled(true);
            componentToVerif[i].setLastFocusedComponent(true);


        }
        enableMemo(true);
        enabledTable(true);
        grp_TField_Contact.requestFocus();
        // parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CANCEL });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void doAccept() {
    }

    public void doCancel() {
        parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.WAIT_CURSOR));
        switch (action) {
            case ActionToolBar.ACT_INSERT:
                tb_interaction.enableValidateActionListener(false);
                m_parentproduit.showProductPanel(0, 0);
                break;
            case ActionToolBar.ACT_MODIFY:
                displayReadMode();
                break;
            case ActionToolBar.ACT_READ:
                if (parent.getBrochure().isLocalyModify()) {
                    int rep = parent.getMessageManager().showConfirmDialog(this, "produit_annul_texte", "produit_annul", parent.getCurrentUser());
                    if (rep == 0) {
                        if (tmpBrochure != null) {
                            parent.setBrochure(tmpBrochure);
                            parent.getDossier().addBrochure(parent.getBrochure());


                        }
                        m_parentproduit.showProductPanel(0, 0);


                    }


                } else m_parentproduit.showProductPanel(0, 0);
                break;


        }
        parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.DEFAULT_CURSOR));


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
    }

    public void doNext() {
    }

    public void doPrevious() {
    }

    public void doPrint() {
    }

    public void doSwitch() {
        if (action != ActionToolBar.ACT_READ) {
            doPrevious();


        }
        if (action != ActionToolBar.ACT_INSERT) {
            fx_manager.swithPanel();


        }


    }

    public void doSwitch(String key) {
        fx_manager.swithPanel(key);


    }

    public int[] getDefaultActionToolBarMask() {
        return new int[]{ActionToolBar.DO_MODIFY, ActionToolBar.DO_CANCEL, ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_SWITCH};


    }

    /**
     * Spécifie le composant qui implémente cette fonction comme
     * le composant qui pilote l'actionToolBar
     */
    public void setThisAsToolBarComponent() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private BrochureGui m_parent;
    private DossierMainScreenModule parent;
    private DossierProduitPane m_parentproduit;
    private AstraComponent[] componentToVerif;
    private JPanelSelectionFxManager fx_manager;
    private ToolBarInteraction tb_interaction;
    private JPanelSelectionFx fx_brochure;
    private JPanelSelectionFx fx_SupReduc;
    private JPanelSelectionFx fx_descLogement;
    private int action;
    private DossierDescriptionLogementTableModel tb_logement;
    private Brochure_T brochure;
    private Brochure_T tmpBrochure;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private BrochurePanel.Table grp_Table_DescriptionLogement;
    private SupReducPanel grp_Pan_SupReduc;
    private SupReducPanelEdition grp_Pan_SupReducEdition;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.combobox.aCombo.ACombo grp_ACB_statut;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_DateDebarquement;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_Embarquement;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_AssuranceAnnulation;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_AssuranceBagages;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_AssuranceParticulier;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Accomodation;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Destination;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_LieuDebarquement;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_LieuEmbarquement;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Transport;
    private javax.swing.JLabel grp_Label_Accomodation;
    private javax.swing.JLabel grp_Label_AssuranceAnnulation;
    private javax.swing.JLabel grp_Label_AssuranceBagages;
    private javax.swing.JLabel grp_Label_AssuranceParticulier;
    private javax.swing.JLabel grp_Label_Contact;
    private javax.swing.JLabel grp_Label_DateDebarquement;
    private javax.swing.JLabel grp_Label_Destination;
    private javax.swing.JLabel grp_Label_Embarquement;
    private javax.swing.JLabel grp_Label_GrpProduit;
    private javax.swing.JLabel grp_Label_Localite;
    private javax.swing.JLabel grp_Label_Logement;
    private javax.swing.JLabel grp_Label_NBrochure;
    private javax.swing.JLabel grp_Label_PO;
    private javax.swing.JLabel grp_Label_RefCatalogue;
    private javax.swing.JLabel grp_Label_TO;
    private javax.swing.JLabel grp_Label_Transport;
    private javax.swing.JLabel grp_Label_fournisseur;
    private javax.swing.JLabel grp_Label_groupeProduit;
    private javax.swing.JPanel grp_Pan_Broch3;
    private javax.swing.JPanel grp_Pan_Broch5;
    private javax.swing.JTextPane grp_TArea_ComplementDescriptif;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Contact;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Localite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_NBrochure;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_PO;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_RefCatalogue;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_logement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
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
        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_brochure");


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
    }

    /**
     * Getter for property brochure.
     *
     * @return Value of property brochure.
     */
    public Brochure_T getBrochure() {
        return brochure;


    }

    /**
     * Setter for property brochure.
     *
     * @param brochure New value of property brochure.
     */
    public void setBrochure(Brochure_T brochure) {
        this.brochure = brochure;


    }

    /**
     * Getter for property tmpBrochure.
     *
     * @return Value of property tmpBrochure.
     */
    public Brochure_T getTmpBrochure() {
        return tmpBrochure;


    }

    /**
     * Setter for property tmpBrochure.
     *
     * @param tmpBrochure New value of property tmpBrochure.
     */
    public void setTmpBrochure(Brochure_T tmpBrochure) {
        this.tmpBrochure = tmpBrochure;


    }

    public void goUp() {
    }

    public java.awt.Component m_getGeneriqueTable() {
        return null;


    }

    public int doAccept(boolean sw) {
        return 1;


    }

    public int doCancel(boolean sw) {
        displayReadMode();
        return 1;


    }

    public int doCreate(boolean sw) {
        //setDefaultDecimalValue();
        displayInsertMode();
        // openFields(ActionToolBar.ACT_INSERT);
        // action=ActionToolBar.ACT_INSERT;
        return 1;


    }

    public int doDelete(boolean sw) {
        return 1;


    }

    public int doModify(boolean sw) {
        displayUpdateMode();
        return 1;


    }

    public int doPrevious(boolean sw) {
        int retval = 1;
        switch (action) {
            case ActionToolBar.ACT_INSERT:
                //requestFocus();
                if (isValidValeur()) {
                    // displayDisableMode();
                    //requestFocus();
                    if (!m_parent.m_config.test) dbInsert();
                    displayReadMode();
                    //requestFocus();
                    //doSwitch();
                } else {
                    retval = 0;
                    parent.getMessageManager().showMessageDialog(this, "bro_no_val_libele", "bro_no_val_titre", parent.getCurrentUser());
                    // requestFocus();
//                    grp_TField_Valeur.requestFocus();
                }
                break;
            case ActionToolBar.ACT_MODIFY:
                // requestFocus();
                if (isValidValeur()) {
                    if (!m_parent.m_config.test) dbUpdate();
                    displayReadMode();
                    // doSwitch();
                } else {
                    parent.getMessageManager().showMessageDialog(this, "bro_no_val_libele", "bro_no_val_titre", parent.getCurrentUser());
                    retval = 0;
                    // requestFocus();
//                    grp_TField_Valeur.requestFocus();
                }
                break;
            case ActionToolBar.ACT_READ:
                // requestFocus();
                // doSwitch(this.SUP);
                //validateProduct();
                break;


        }
        return retval;


    }

    public Object getSupReduc2(int i) {
        return null;


    }

    public java.awt.Component getTable() {
        return null;


    }

    public void moveInTable(int direction) {
    }

    public void setSup_reduc(Object sup_reduc) {
    }

    /**
     * Getter for property tb_interaction.
     *
     * @return Value of property tb_interaction.
     */
    public srcastra.astra.gui.components.actions.ToolBarInteraction getTb_interaction() {
        return tb_interaction;


    }

    /**
     * Setter for property tb_interaction.
     *
     * @param tb_interaction New value of property tb_interaction.
     */
    public void setTb_interaction(srcastra.astra.gui.components.actions.ToolBarInteraction tb_interaction) {
        this.tb_interaction = tb_interaction;


    }

    public void doF10() {
    }

    public void addKeystroque() {
    }

    public void doF7() {
    }


}
