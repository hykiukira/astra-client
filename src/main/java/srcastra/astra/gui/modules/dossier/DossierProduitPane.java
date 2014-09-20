/*
* DossierProduitPane.java
*
* Created on 27 août 2002, 15:18
*/
package srcastra.astra.gui.modules.dossier;

// Interfaces

import srcastra.astra.gui.components.AstraComponent;
import srcastra.astra.gui.components.actions.Environnement;
import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel;
import srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel;
import srcastra.astra.gui.components.combobox.liste.Liste;
import srcastra.astra.gui.components.combobox.liste.TypeProduitListeTableModel;
import srcastra.astra.gui.event.NavigateInTable;
import srcastra.astra.gui.event.ValidateField;
import srcastra.astra.gui.modules.InternScreenModule;
import srcastra.astra.gui.modules.dossier.productSpecification.NeedAssurance;
import srcastra.astra.gui.modules.dossier.productSpecification.ProductAnnulationDialog;
import srcastra.astra.gui.modules.dossier.productSpecification.avioncancel.AvionCancelFrame;
import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel;
import srcastra.astra.gui.modules.dossier.utils.CheckCellRenderer;
import srcastra.astra.gui.modules.dossier.utils.Makedefinition;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.sys.formVerification.IntegerMask;
import srcastra.astra.gui.sys.utils.CursorChange;
import srcastra.astra.gui.test.ColoredTableCellRenderer;
import srcastra.astra.gui.test.ProduitTableModelGlobal;
import srcastra.astra.sys.classetransfert.dossier.InterfaceProduit;
import srcastra.astra.sys.classetransfert.dossier.ProduitAffichage;
import srcastra.astra.sys.classetransfert.dossier.assurance.Assurance_T;
import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;
import srcastra.astra.sys.classetransfert.dossier.bateau.Bateau_T;
import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;
import srcastra.astra.sys.classetransfert.dossier.car.Car_T;
import srcastra.astra.sys.classetransfert.dossier.divers.Divers_T;
import srcastra.astra.sys.classetransfert.dossier.hotel.Hotel_T;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.dossier.taxi.Taxi_T;
import srcastra.astra.sys.classetransfert.dossier.train.Train_T;
import srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

/**
 * @author Sébastien
 */
public class DossierProduitPane extends javax.swing.JPanel implements InternScreenModule, ComponentListener, ToolBarComposer, interfaceDossierProduitIndex {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form DossierProduitPane
     */
    public DossierProduitPane(DossierMainScreenModule parent) {
        this.parent = parent;
        init();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INITIALISATION
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void init() {
        initComponents();
        grp_Table_ProduitIndex.setFocusCycleRoot(false);
        componentToVerif = new AstraComponent[]{grp_LSelect_TypeProduits, grp_LSelect_Fournisseur, grp_LSelect_GrpProduits};
        config = new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.Config();
        config.test = false;
        grp_TField_nbrFactSolde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setNewDocumentToProduct();
                enableDocument(false);
                displayReadMode();
            }
        });

        initListe();
        initTable();
        setDocument();
        grp_LSelect_Fournisseur.addActionListener(putFrCleUnikActionListener);
        grp_LSelect_Fournisseur.addMouseListener(mouse);
        tb_interaction = new ToolBarInteraction(parent, this, componentToVerif);
        tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_STANDART);
        tb_interaction.setEnvironnement(Environnement.ENVIRONNEMENT_PANEL_SIGNALETIQUE);
        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
        }
        tb_interaction.enabledContainerListenerEnabled(false);
// dos_prod_tableModel.addDataContainerListener(tb_interaction.getContainerListener());
        addComponentListener(this);
        int j = grp_Table_ProduitIndex.getRowCount();
        System.out.println("\n\n[************LONGEUR DE LA TABLE]" + j);
        if (grp_Table_ProduitIndex.getRowCount() != 0) {
            grp_Table_ProduitIndex.setRowSelectionInterval(0, 0);
            setListSelector();

        }

        grp_LSelect_GrpProduits.addValidateFieldListener(validateAndDoPrevious);
        innitPopupVector();
        grp_TField_Invisible.addKeyListener(new NavigateInTable(grp_Table_ProduitIndex));
        grp_TField_Invisible.addKeyListener(new DossierProduitPane.keyTableListener());
        grp_TField_Invisible.requestFocus();
    }

    private void initListe() {
        grp_LSelect_TypeProduits.setFocusable3(false);
        grp_LSelect_TypeProduits.setServeur(parent.getServeur());
        grp_LSelect_TypeProduits.setLogin(parent.getCurrentUser());
        this.typeProdmodel = new srcastra.astra.gui.components.combobox.liste.TypeProduitListeTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_LSelect_TypeProduits.setModel(this.typeProdmodel);
        grp_LSelect_TypeProduits.init2();
        grp_LSelect_Fournisseur.setFocusable3(false);
        grp_LSelect_Fournisseur.setServeur(parent.getServeur());
        grp_LSelect_Fournisseur.setLogin(parent.getCurrentUser());
        this.fournisseurModel = new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_LSelect_Fournisseur.setModel(this.fournisseurModel);
        grp_LSelect_Fournisseur.init2();
        grp_LSelect_GrpProduits.setFocusable3(false);
        grp_LSelect_GrpProduits.setServeur(parent.getServeur());
        grp_LSelect_GrpProduits.setLogin(parent.getCurrentUser());
        this.tbMod_grpDeProd = new srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_LSelect_GrpProduits.setModel(this.tbMod_grpDeProd);
        grp_LSelect_GrpProduits.init2();

    }

    private void innitPopupVector() {
        this.popupVector = null;
        this.popupVector = new java.util.Vector();
        this.popupVector.add(grp_LSelect_TypeProduits.getGrp_PopMenu_popup());
        this.popupVector.add(grp_LSelect_Fournisseur.getGrp_PopMenu_popup());
        this.popupVector.add(grp_LSelect_GrpProduits.getGrp_PopMenu_popup());
        parent.getPopupVector(this.popupVector, 1);
    }

    private void initTable() {
        grp_Table_ProduitIndex.setSelectionBackground(new java.awt.Color(221, 221, 255));
        model = new ProduitTableModelGlobal(parent, parent.getCurrentUser());
        model.loadData();
        grp_Table_ProduitIndex.setAutoCreateColumnsFromModel(false);
        grp_Table_ProduitIndex.setModel(model);
        grp_Table_ProduitIndex.getTableHeader().setReorderingAllowed(false);
        grp_Table_ProduitIndex.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int k = 0; k < model.m_columns.length; k++) {
            JCheckBox jcheck = new JCheckBox();
            TableCellEditor editor = new javax.swing.DefaultCellEditor(jcheck);
            TableCellRenderer renderer2;
            renderer2 = new CheckCellRenderer();
// DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;
// TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);
            TableColumn column;
            if (k == 15)
                column = new TableColumn(k, model.m_columns[k].c_width, renderer2, editor);
            else
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            grp_Table_ProduitIndex.addColumn(column);
        }
        JTableHeader header = grp_Table_ProduitIndex.getTableHeader();
        header.setUpdateTableInRealTime(false);
        if (grp_Table_ProduitIndex.getRowCount() > 0) {
            grp_Table_ProduitIndex.changeSelection(0, 0, false, false);
            setListSelector();
        }
    }

    public void setListSelector() {
        int row = grp_Table_ProduitIndex.getSelectedRow();
        Long l = new Long(model.getClassAffichage(row).getParentkey());
        int type = model.getClassAffichage(row).getParent().getTypeDeProduitCleunik();
        if (type != produit_T.FRAIS) {
            Hashtable table = parent.getDossier().returnCorrectArrayList(type);
            InterfaceProduit tmpProd = (InterfaceProduit) table.get(l);
            grp_LSelect_Fournisseur.setCleUnik(tmpProd.getFrcleunik());
            tbMod_grpDeProd.setM_frcleunik(tmpProd.getFrcleunik());
            tbMod_grpDeProd.loadata();
            grp_LSelect_GrpProduits.setCleUnik(tmpProd.getFrgtcleunik());
            grp_LSelect_TypeProduits.setCleUnik(tmpProd.getTypeDeProduitCleunik());
        }
    }

    public void setDocument() {
        grp_TField_nbrConfirm.setDocument(new IntegerMask(0, 3, parent.getCurrentUser().getLangage()));
        grp_TField_nbrDoc.setDocument(new IntegerMask(0, 3, parent.getCurrentUser().getLangage()));
        grp_TField_nbrFact.setDocument(new IntegerMask(0, 3, parent.getCurrentUser().getLangage()));
        grp_TField_nbrFactSolde.setDocument(new IntegerMask(0, 3, parent.getCurrentUser().getLangage()));
        grp_TField_nbrNoteCredit.setDocument(new IntegerMask(0, 3, parent.getCurrentUser().getLangage()));
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        grp_Pan_IndexCombo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_ProduitIndex = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        grp_TField_nbrDoc = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nbrConfirm = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nbrFact = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nbrNoteCredit = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nbrFactSolde = new srcastra.astra.gui.components.textFields.ATextField();
        jSeparator5 = new javax.swing.JSeparator();
        grp_Label_NbrTotDoc = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        grp_Pan_ProductSelection = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        grp_Label_TypeProduits = new javax.swing.JLabel();
        grp_TField_Invisible = new javax.swing.JTextField();
        grp_LSelect_TypeProduits = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        grp_Label_Fournisseur = new javax.swing.JLabel();
        grp_LSelect_Fournisseur = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        grp_Label_GrpProduits = new javax.swing.JLabel();
        grp_LSelect_GrpProduits = new srcastra.astra.gui.components.combobox.liste.Liste();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_produit"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        grp_Pan_IndexCombo.setLayout(new java.awt.BorderLayout());

        grp_Table_ProduitIndex.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Table_ProduitIndex.setModel(new DefaultTableModel());
        grp_Table_ProduitIndex.setRequestFocusEnabled(false);
        grp_Table_ProduitIndex.setSelectionForeground(new java.awt.Color(0, 102, 0));
        grp_Table_ProduitIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_Table_ProduitIndexMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(grp_Table_ProduitIndex);

        grp_Pan_IndexCombo.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(grp_Pan_IndexCombo, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("DP_nbrDoc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("DP_nbrConfirm"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("DP_nbrFact"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("DP_nbrNcredit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 3, 6);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("DP_nbreFactSolde"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 3, 6);
        jPanel1.add(jLabel5, gridBagConstraints);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jSeparator1, gridBagConstraints);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jSeparator2, gridBagConstraints);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jSeparator3, gridBagConstraints);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jSeparator4, gridBagConstraints);

        grp_TField_nbrDoc.setEnabled(false);
        grp_TField_nbrDoc.setGrp_Comp_nextComponent(grp_TField_nbrConfirm);
        grp_TField_nbrDoc.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        grp_TField_nbrDoc.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbrDoc.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 3, 0);
        jPanel1.add(grp_TField_nbrDoc, gridBagConstraints);

        grp_TField_nbrConfirm.setEnabled(false);
        grp_TField_nbrConfirm.setGrp_Comp_nextComponent(grp_TField_nbrFact);
        grp_TField_nbrConfirm.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        grp_TField_nbrConfirm.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbrConfirm.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 3, 0);
        jPanel1.add(grp_TField_nbrConfirm, gridBagConstraints);

        grp_TField_nbrFact.setEnabled(false);
        grp_TField_nbrFact.setGrp_Comp_nextComponent(grp_TField_nbrNoteCredit);
        grp_TField_nbrFact.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        grp_TField_nbrFact.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbrFact.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 3, 0);
        jPanel1.add(grp_TField_nbrFact, gridBagConstraints);

        grp_TField_nbrNoteCredit.setEnabled(false);
        grp_TField_nbrNoteCredit.setGrp_Comp_nextComponent(grp_TField_nbrFactSolde);
        grp_TField_nbrNoteCredit.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        grp_TField_nbrNoteCredit.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbrNoteCredit.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 3, 0);
        jPanel1.add(grp_TField_nbrNoteCredit, gridBagConstraints);

        grp_TField_nbrFactSolde.setEnabled(false);
        grp_TField_nbrFactSolde.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        grp_TField_nbrFactSolde.setLastFocusedComponent(true);
        grp_TField_nbrFactSolde.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbrFactSolde.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 3, 0);
        jPanel1.add(grp_TField_nbrFactSolde, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jSeparator5, gridBagConstraints);

        grp_Label_NbrTotDoc.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_NbrTotDoc.setForeground(new java.awt.Color(204, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(grp_Label_NbrTotDoc, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("total_document"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jSeparator6, gridBagConstraints);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jSeparator7, gridBagConstraints);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jSeparator8, gridBagConstraints);

        jPanel2.add(jPanel1);

        add(jPanel2, java.awt.BorderLayout.SOUTH);

        grp_Pan_ProductSelection.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setLayout(new java.awt.GridBagLayout());

        grp_Label_TypeProduits.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_TypeProduits.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_TypeProduits"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_Label_TypeProduits, gridBagConstraints);

        grp_TField_Invisible.setText("jTextField1");
        grp_TField_Invisible.setMinimumSize(new java.awt.Dimension(0, 0));
        grp_TField_Invisible.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel7.add(grp_TField_Invisible, new java.awt.GridBagConstraints());

        grp_LSelect_TypeProduits.setEnabled(false);
        grp_LSelect_TypeProduits.setGrp_Comp_nextComponent(grp_LSelect_Fournisseur);
        grp_LSelect_TypeProduits.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_TypeProduits.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel7.add(grp_LSelect_TypeProduits, gridBagConstraints);

        jPanel4.add(jPanel7);

        grp_Pan_ProductSelection.add(jPanel4);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        grp_Label_Fournisseur.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Fournisseur.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Fournisseur"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(grp_Label_Fournisseur, gridBagConstraints);

        grp_LSelect_Fournisseur.setEnabled(false);
        grp_LSelect_Fournisseur.setGrp_Comp_nextComponent(grp_LSelect_GrpProduits);
        grp_LSelect_Fournisseur.setLeft(true);
        grp_LSelect_Fournisseur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Fournisseur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel8.add(grp_LSelect_Fournisseur, gridBagConstraints);

        jPanel5.add(jPanel8);

        grp_Pan_ProductSelection.add(jPanel5);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        grp_Label_GrpProduits.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_GrpProduits.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_GrpProduits"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_Label_GrpProduits, gridBagConstraints);

        grp_LSelect_GrpProduits.setEnabled(false);
        grp_LSelect_GrpProduits.setIsLastComponent(true);
        grp_LSelect_GrpProduits.setLeft(true);
        grp_LSelect_GrpProduits.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_GrpProduits.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel9.add(grp_LSelect_GrpProduits, gridBagConstraints);

        jPanel6.add(jPanel9);

        grp_Pan_ProductSelection.add(jPanel6);

        add(grp_Pan_ProductSelection, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void grp_Table_ProduitIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_Table_ProduitIndexMouseClicked
        System.out.println("\n\n\nquoi tu rentre");
        afficheDoc();
        setListSelector();
        grp_TField_Invisible.requestFocus();
    }//GEN-LAST:event_grp_Table_ProduitIndexMouseClicked

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getTheRightPanel(produit_T prod, int typeprod, ProductLayeredPanel pane) {
        displayReadMode();
        parent.enabledTabbedPane(false);
        Makedefinition.makeProduitDefinition(parent, prod, this, grp_LSelect_GrpProduits.getCleUnik(), grp_LSelect_Fournisseur.getCleUnik());
        jScrollPane1.setVisible(false);
        jPanel2.setVisible(false);
        grp_Pan_ProductSelection.setVisible(false);
        pane = new ProductLayeredPanel(parent, parent.getToolBar(), ActionToolBar.ACT_READ, this, config, typeprod);
        grp_Pan_IndexCombo.add(pane);
        pane.read();
        parent.setCurrentPanel(pane);
        pane.repaint();
        setTitle(pane.getTitle());
    }

    public void showProductPanel(int idpanneaux, int mode) {
        switch (idpanneaux) {
            case 0:
/*if(parent.getDossier().getHotel()!=null)
for(Enumeration enu=parent.getDossier().getHotel().keys();enu.hasMoreElements();){
produit_T tmp=(produit_T)parent.getDossier().getHotel().get(enu.nextElement());
if(tmp.getSup_reduc()==null)
Logger.getDefaultLogger().log(Logger.LOG_WARNING,"\nYO\nYO\nYOvaleur des sup null");
else
Logger.getDefaultLogger().log(Logger.LOG_WARNING,"\nYO\nYO\nYOvaleur des sup"+tmp.getSup_reduc().size());
}*/
                removeComponentListener(currentComponentListener);
                addComponentListener(this);
                parent.enabledTabbedPane(true);
                switchPanel(jScrollPane1);
                displayReadMode();
                parent.setCurrentPanel(this);
                parent.chargeStatusPanel();
                setTitle("");
                grp_TField_Invisible.requestFocus();
                break;
            case 1:
                getTheRightPanel(parent.getTicket(), produit_T.AV, grp_Pan_Aviation);
                break;
            case 2:
                getTheRightPanel(parent.getBrochure(), produit_T.BRO, grp_Pan_Brochure);
                break;
            case 3:
                getTheRightPanel(parent.getHotel(), produit_T.HO, grp_Pan_Hotel);
                break;
            case 4:
                getTheRightPanel(parent.getTaxi(), produit_T.TAX, grp_Pan_Taxi);
                break;
            case 5:
                getTheRightPanel(parent.getBateau(), produit_T.BA, grp_Pan_Bateau);
                break;
            case 6:
                getTheRightPanel(parent.getVoitureLocation(), produit_T.VO, grp_Pan_VoitureLocation);
                break;
            case 7:
                getTheRightPanel(parent.getTrain(), produit_T.TR, grp_Pan_Train);
                break;
            case 8:
                getTheRightPanel(parent.getAssurance(), produit_T.AS, grp_Pan_Assurance);
                break;
            case 10:
                getTheRightPanel(parent.getCar(), produit_T.CAR, grp_Pan_VoitureLocation);
                break;
            case 11:
                getTheRightPanel(parent.getDiverst(), produit_T.DIV, grp_Pan_Divers);
                break;
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------
/*   private void makeTicketDefinition(){
try{
if(parent.getTicket().getGroupdec()==null){
parent.getTicket().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getTicket());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getTicket().getGroupdec());

}

private void makeAssuranceDefinition(){
try{
if(parent.getAssurance().getGroupdec()==null){
parent.getAssurance().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getAssurance());
}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getAssurance().getGroupdec());

}

private void makeBrochureDefinition(){
try{
if(parent.getBrochure().getGroupdec()==null){
parent.getBrochure().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getBrochure());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getBrochure().getGroupdec());

}

private void makeHotelDefinition(){
try{
if(parent.getHotel().getGroupdec()==null){
parent.getHotel().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getHotel());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getHotel().getGroupdec());

}

private void makeBateauDefinition(){
try{
if(parent.getBateau().getGroupdec()==null){
parent.getBateau().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getBateau());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getBateau().getGroupdec());

}

private void makeTrainDefinition() {
try{
if(parent.getTrain().getGroupdec()==null){
parent.getTrain().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getTrain());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getTrain().getGroupdec());
}

private void makeVoitureLocationDefinition() {
try{
if(parent.getVoitureLocation().getGroupdec()==null){
parent.getVoitureLocation().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getVoitureLocation());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getVoitureLocation().getGroupdec());
}

private void makeTaxiDefinition() {
try{
if(parent.getTaxi().getGroupdec()==null){
parent.getTaxi().setGroupdec(  (Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),
grp_LSelect_GrpProduits.getCleUnik(), 1, parent.getServeur().COMBO_FOURGRPDEC));
setProdutFournAndGpdec((InterfaceProduit)parent.getTaxi());}
}
catch (java.rmi.RemoteException re) {
ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
}
parent.setMenuProduit(true);
parent.getMainframe().setGrpdec(parent.getTaxi().getGroupdec());
}*/
//---------------------------------------------------------------------------------------------------------------------------------------------

    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {
        tb_interaction.enabledContainerListenerEnabled(false);
//  grp_LSelect_Fournisseur.closePopup();
// grp_LSelect_GrpProduits.closePopup();
// grp_LSelect_TypeProduits.closePopup();
    }

    public void componentMoved(java.awt.event.ComponentEvent componentEvent) {
    }

    public void componentResized(java.awt.event.ComponentEvent componentEvent) {
    }

    public void componentShown(java.awt.event.ComponentEvent componentEvent) {
        parent.setCurrentPanel(this);
        changeAction();
        model.loadData();
        grp_Table_ProduitIndex.updateUI();
//grp_Table_ProduitIndex.setModel(dos_prod_tableModel);
        switch (action) {
            case ActionToolBar.ACT_READ:
                displayReadMode();
                break;
            case ActionToolBar.ACT_INSERT:
                displayInsertMode();
                break;
        }
    }

    private java.awt.event.MouseAdapter mouse = new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (grp_LSelect_Fournisseur.getCleUnik() != 0) {
                fr_cleUnik = grp_LSelect_Fournisseur.getCleUnik();
                tbMod_grpDeProd.setM_frcleunik(fr_cleUnik);
                tbMod_grpDeProd.loadata();

            }
        }
    };
    private ActionListener putFrCleUnikActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            fr_cleUnik = ((Liste) e.getSource()).getCleUnik();
            if (fr_cleUnik > 0) {
//   tbMod_grpDeProd.setFrCleUnik(fr_cleUnik);
// tbMod_grpDeProd.resetData();
                tbMod_grpDeProd.setM_frcleunik(fr_cleUnik);
                tbMod_grpDeProd.loadata();

            }
        }
    };
    private ValidateField validateAndDoPrevious = new ValidateField() {
        public void actionPerformed(ActionEvent evt) {
            if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {
                parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.WAIT_CURSOR));
// grp_LSelect_GrpProduits.closePopup();
                requestFocus();
                doPrevious();
                parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    };
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addComponentListener(ComponentListener listener) {
        super.addComponentListener(listener);
        currentComponentListener = listener;
    }

    public void removeComponentListener(ComponentListener listener) {
        super.removeComponentListener(listener);
        currentComponentListener = null;
    }


    private void chargeDocument() {
    }

    private void switchPanel(javax.swing.JComponent panel) {
/*  if(panel instanceof srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel){
if(grp_Pan_Aviation==null)
grp_Pan_Aviation = new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.AV);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Aviation.repaint();
grp_Pan_Aviation.setVisible(true);
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
// grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof BrochurePanel)
{
if(grp_Pan_Brochure==null)
grp_Pan_Brochure = new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.BRO);
//  grp_Pan_Brochure = new BrochurePanel(parent,this);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Hotel!=null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Brochure.setVisible(true);
grp_Pan_Brochure.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof AssurancePane)
{
if(grp_Pan_Assurance==null)
grp_Pan_Assurance =  new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.AS);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if(grp_Pan_Hotel!=null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Assurance.setVisible(true);
grp_Pan_Assurance.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof HotelPane)
{
if(grp_Pan_Hotel==null)
grp_Pan_Hotel= new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.HO);
// grp_Pan_Hotel = new HotelPane(parent,this);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Hotel.setVisible(true);
grp_Pan_Hotel.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}

else if(panel instanceof BateauPane)
{
if(grp_Pan_Bateau==null)
grp_Pan_Bateau =  new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.BA);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if (grp_Pan_Hotel != null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Bateau.setVisible(true);
grp_Pan_Bateau.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof TrainPane)
{
if(grp_Pan_Train==null)
grp_Pan_Train =  new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.TR);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if (grp_Pan_Hotel != null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_Train.setVisible(true);
grp_Pan_Train.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof VoitureLocationPane)
{
if(grp_Pan_VoitureLocation==null)
grp_Pan_VoitureLocation = new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.VO);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if (grp_Pan_Hotel != null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);
grp_Pan_VoitureLocation.setVisible(true);
grp_Pan_VoitureLocation.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof TaxiPanel) {
if(grp_Pan_Taxi==null)
grp_Pan_Taxi =  new srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.ProductLayeredPanel(parent,parent.getToolBar(),ActionToolBar.ACT_INSERT,this,config,produit_T.TAX);
if(grp_Pan_Aviation!=null)
grp_Pan_Aviation.setVisible(false);
if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
if(grp_Pan_Brochure!=null)
grp_Pan_Brochure.setVisible(false);
if (grp_Pan_Hotel != null)
grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
grp_Pan_Taxi.setVisible(true);
grp_Pan_Taxi.repaint();
grp_Pan_IndexCombo.repaint();
jScrollPane1.setVisible(false);
//grp_Pan_IndexCombo.add(panel);
grp_Pan_ProductSelection.setVisible(false);
jPanel2.setVisible(false);
}
else if(panel instanceof JScrollPane)
{   //if(grp_Pan_Aviation!=null)
//grp_Pan_Aviation.setVisible(false);
//if(grp_Pan_Brochure!=null)
//  grp_Pan_Brochure.setVisible(false);
/*   if(grp_Pan_Assurance!=null)
grp_Pan_Assurance.setVisible(false);
//if(grp_Pan_Hotel!=null)
// grp_Pan_Hotel.setVisible(false);
if (grp_Pan_Bateau != null)
grp_Pan_Bateau.setVisible(false);
if (grp_Pan_Train != null)
grp_Pan_Train.setVisible(false);
if (grp_Pan_VoitureLocation != null)
grp_Pan_VoitureLocation.setVisible(false);
if (grp_Pan_Taxi != null)
grp_Pan_Taxi.setVisible(false);*/
        model.loadData();
        grp_Table_ProduitIndex.tableChanged(new javax.swing.event.TableModelEvent(model));
        grp_Pan_IndexCombo.repaint();
        grp_Table_ProduitIndex.repaint();
        jScrollPane1.repaint();
        jScrollPane1.setVisible(true);
        jPanel2.setVisible(true);
        grp_Pan_ProductSelection.setVisible(true);
        if (grp_Table_ProduitIndex.getRowCount() != 0) {
            grp_Table_ProduitIndex.setRowSelectionInterval(0, 0);
            setListSelector();
        }


    }

    private void changeAction() {
        if (grp_Table_ProduitIndex.getModel().getRowCount() > 0) {
            parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CREATE,
                    ActionToolBar.DO_ACCEPT,
                    ActionToolBar.DO_PREVIOUS,
                    ActionToolBar.DO_DELETE,
                    ActionToolBar.DO_MODIFY,
                    ActionToolBar.DO_CANCEL});
        } else {
            parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CREATE,
                    ActionToolBar.DO_CANCEL});
        }

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

    public Object setProdutFournAndGpdec(InterfaceProduit tmp) {
        tmp.setFrcleunik(grp_LSelect_Fournisseur.getCleUnik());
        tmp.setFrnom(grp_LSelect_Fournisseur.getText());
//  tmp.setFrgtcleunik(grp_LSelect_GrpProduits.getCleUnik());
        tmp.setGroupe_produit_nom(grp_LSelect_GrpProduits.getText());
//tmp.setTypeDeProduitCleunik(grp_LSelect_TypeProduits.getCleUnik());
        tmp.setTypeDeProduitNom(grp_LSelect_TypeProduits.getText());
        return tmp;
    }

    /**
     * Demande d'une insertion au serveur
     */
    private void insertTheRightPanel(produit_T prod, int typrod, ProductLayeredPanel pane) {
        Makedefinition.makeProduitDefinition(parent, prod, this, grp_LSelect_GrpProduits.getCleUnik(), grp_LSelect_Fournisseur.getCleUnik());
        if (parent.getDossier().getPassager() != null)
            if (prod instanceof Assurance_T)
                prod.setPax(1);
            else
                prod.setPax(parent.getDossier().getPassager().size());
        jScrollPane1.setVisible(false);
        grp_Pan_ProductSelection.setVisible(false);
        jPanel2.setVisible(false);
        parent.enabledTabbedPane(false);
        pane = new ProductLayeredPanel(parent, parent.getToolBar(), ActionToolBar.ACT_INSERT, this, config, typrod);
        grp_Pan_IndexCombo.add(pane);
        parent.setCurrentPanel(pane);
        pane.doCreate();
        setTitle(pane.getTitle());
        pane.repaint();
    }

    public void dbInsert() {
        displayReadMode();
        switch (grp_LSelect_TypeProduits.getCleUnik()) {
            case InterfaceProduit.AV:
                parent.setTicket(new Avion_ticket_T());
                parent.getTicket().setIsnewreccord(true);
                insertTheRightPanel(parent.getTicket(), produit_T.AV, grp_Pan_Aviation);
                break;
            case InterfaceProduit.AS:
                parent.setAssurance(new Assurance_T());
                parent.getAssurance().setIsnewreccord(true);
                insertTheRightPanel(parent.getAssurance(), produit_T.AS, grp_Pan_Assurance);
                break;
            case InterfaceProduit.BRO:
                parent.setBrochure(new Brochure_T());
                parent.getBrochure().setIsnewreccord(true);
                insertTheRightPanel(parent.getBrochure(), produit_T.BRO, grp_Pan_Brochure);
                break;
            case InterfaceProduit.HO:
                parent.setHotel(new Hotel_T());
                parent.getHotel().setIsnewreccord(true);
                insertTheRightPanel(parent.getHotel(), produit_T.HO, grp_Pan_Hotel);
                break;
            case InterfaceProduit.BA:
                parent.setBateau(new Bateau_T());
                parent.getBateau().setIsnewreccord(true);
                insertTheRightPanel(parent.getBateau(), produit_T.BA, grp_Pan_Bateau);
                break;
            case InterfaceProduit.TR:
                parent.setTrain(new Train_T());
                parent.getTrain().setIsnewreccord(true);
                insertTheRightPanel(parent.getTrain(), produit_T.TR, grp_Pan_Train);
                break;
            case InterfaceProduit.VO:
                parent.setVoitureLocation(new VoitureLocation_T());
                parent.getVoitureLocation().setIsnewreccord(true);
                insertTheRightPanel(parent.getVoitureLocation(), produit_T.VO, grp_Pan_VoitureLocation);
                break;
            case InterfaceProduit.CAR:
                parent.setCar(new srcastra.astra.sys.classetransfert.dossier.car.Car_T());
                parent.getCar().setIsnewreccord(true);
                insertTheRightPanel(parent.getCar(), produit_T.CAR, grp_Pan_VoitureLocation);
                break;
            case InterfaceProduit.TAX:
                parent.setTaxi(new Taxi_T());
                parent.getTaxi().setIsnewreccord(true);
                insertTheRightPanel(parent.getTaxi(), produit_T.TAX, grp_Pan_Taxi);
                break;
            case InterfaceProduit.DIV:
                parent.setDiverst(new Divers_T());
                parent.getDiverst().setIsnewreccord(true);
                insertTheRightPanel(parent.getDiverst(), produit_T.DIV, grp_Pan_Divers);
                break;
        }
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
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
        grp_LSelect_Fournisseur.setFocusable3(true);
        grp_LSelect_GrpProduits.setFocusable3(true);
        grp_LSelect_TypeProduits.setFocusable3(true);
        action = ActionToolBar.ACT_INSERT;
        tb_interaction.enableValidateActionListener(true);
        tb_interaction.enabledContainerListenerEnabled(false);
        grp_Table_ProduitIndex.setEnabled(false);

        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].setEnabled(false);
            componentToVerif[i].setLastFocusedComponent(false);
            componentToVerif[i].setText("");
        }
        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});
        grp_LSelect_GrpProduits.setLastFocusedComponent(true);
        grp_LSelect_TypeProduits.setEnabled(true);
        grp_LSelect_TypeProduits.requestFocus();
    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
        parent.calculTotal();
        action = ActionToolBar.ACT_READ;
        tb_interaction.enableValidateActionListener(false);
        tb_interaction.enabledContainerListenerEnabled(true);
        grp_Table_ProduitIndex.setEnabled(true);
        for (int i = 0; i < componentToVerif.length; i++) {
            componentToVerif[i].setEnabled(false);
            componentToVerif[i].setLastFocusedComponent(false);
        }
        if (parent.getDossier().getAnnuler() == 0 && parent.getDossier().getServicefact() == 0 && !parent.getDossier().isReadMode()) {
            if (grp_Table_ProduitIndex.getRowCount() > 0) {
                parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CREATE,
                        ActionToolBar.DO_ACCEPT,
                        ActionToolBar.DO_PREVIOUS,
                        ActionToolBar.DO_CANCEL,
                        ActionToolBar.DO_MODIFY,
                        ActionToolBar.DO_DELETE});
            } else {
                parent.setCurrentActionEnabled(new int[]{
                        ActionToolBar.DO_CREATE,
                        ActionToolBar.DO_PREVIOUS,
                        ActionToolBar.DO_CANCEL,
                        ActionToolBar.DO_DELETE
                });
            }
        } else {
            if (grp_Table_ProduitIndex.getRowCount() > 0) {
                parent.setCurrentActionEnabled(new int[]{
                        ActionToolBar.DO_ACCEPT,
                        ActionToolBar.DO_PREVIOUS,
                        ActionToolBar.DO_CANCEL
                });
            } else {
                parent.setCurrentActionEnabled(new int[]{
                        ActionToolBar.DO_PREVIOUS,
                        ActionToolBar.DO_CANCEL
                });
            }
        }
        afficheDoc();
        grp_TField_Invisible.requestFocus();
    }

    public void afficheDoc() {
        srcastra.astra.sys.classetransfert.Document_T doc = null;
        if (grp_Table_ProduitIndex.getRowCount() == 0) {
            System.out.println("table vide");
            return;
        }
        ProduitAffichage tmp = model.getClassAffichage(grp_Table_ProduitIndex.getSelectedRow());
        if (!tmp.isReducsup()) {
            InterfaceProduit tmp2 = tmp.getParent();
            doc = tmp2.getDoc();
        }
        setThisDocument(doc);
    }

    private void setNewDocumentToProduct() {
        srcastra.astra.sys.classetransfert.Document_T doc = null;
        if (grp_Table_ProduitIndex.getRowCount() == 0) {
            System.out.println("table vide");
            return;
        }
        ProduitAffichage tmp = model.getClassAffichage(grp_Table_ProduitIndex.getSelectedRow());
        if (!tmp.isReducsup()) {
            InterfaceProduit tmp2 = tmp.getParent();
            doc = tmp2.getDoc();
            doc.setModify(true);
            try {
                doc.setFrdtnbrconfprev(Integer.parseInt(grp_TField_nbrConfirm.getText()));
            } catch (NumberFormatException nn) {
                doc.setFrdtnbrconfprev(0);
            }
            try {
                doc.setFrdtnbrdocprev(Integer.parseInt(grp_TField_nbrDoc.getText()));
            } catch (NumberFormatException nn) {
                doc.setFrdtnbrdocprev(0);
            }
            try {
                doc.setFrdtnbrfactprev(Integer.parseInt(grp_TField_nbrFact.getText()));
            } catch (NumberFormatException nn) {
                doc.setFrdtnbrfactprev(0);
            }
            try {
                doc.setFrdtnbrfactsprev(Integer.parseInt(grp_TField_nbrFactSolde.getText()));
            } catch (NumberFormatException nn) {
                doc.setFrdtnbrfactsprev(0);
            }
            try {
                doc.setFrdtnbrcprev(Integer.parseInt(grp_TField_nbrNoteCredit.getText()));
            } catch (NumberFormatException nn) {
                doc.setFrdtnbrcprev(0);
            }
            if (!parent.getDossier().isNewreccord())
                parent.getDossier().setModifreccord(true);
            if (!tmp2.isIsnewreccord())
                tmp2.setModify(true);
        }
    }

    private void setThisDocument(srcastra.astra.sys.classetransfert.Document_T doc) {
        if (doc != null) {
            grp_TField_nbrConfirm.setText("" + doc.getFrdtnbrconfprev());
            grp_TField_nbrDoc.setText("" + doc.getFrdtnbrdocprev());
            grp_TField_nbrFact.setText("" + doc.getFrdtnbrfactprev());
            grp_TField_nbrFactSolde.setText("" + doc.getFrdtnbrfactsprev());
            grp_TField_nbrNoteCredit.setText("" + doc.getFrdtnbrcprev());
        } else {
            grp_TField_nbrConfirm.setText("");
            grp_TField_nbrDoc.setText("");
            grp_TField_nbrFact.setText("");
            grp_TField_nbrFactSolde.setText("");
            grp_TField_nbrNoteCredit.setText("");
        }
    }

    private void enableDocument(boolean enabled) {
        grp_TField_nbrConfirm.setEnabled(enabled);
        grp_TField_nbrDoc.setEnabled(enabled);
        grp_TField_nbrFact.setEnabled(enabled);
        grp_TField_nbrFactSolde.setEnabled(enabled);
        grp_TField_nbrNoteCredit.setEnabled(enabled);
        if (enabled)
            grp_TField_nbrDoc.requestFocus();
        else
            grp_TField_Invisible.requestFocus();


    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
        tb_interaction.enableValidateActionListener(true);
        tb_interaction.enabledContainerListenerEnabled(false);
        grp_Table_ProduitIndex.setEnabled(false);
    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }


    private Hashtable getSupReducClone(Hashtable supreduc) {
        if (supreduc != null)
            return (Hashtable) supreduc.clone();
        else
            return null;

    }

    private int selectObjectInTable() {
        model.loadData();
        Hashtable supreduc = null;
        produit_T tmpprod;
        int cleparent = grp_Table_ProduitIndex.getSelectedRow();
        ProduitAffichage pd_affichage = (ProduitAffichage) ((ProduitTableModelGlobal) grp_Table_ProduitIndex.getModel()).getObject(cleparent);
        Long l = new Long(model.getClassAffichage(cleparent).getParentkey());
        switch (pd_affichage.getParentType()) {
            case InterfaceProduit.AV:
                try {
                    supreduc = getSupReducClone(((Avion_ticket_T) parent.getDossier().getAvionTicket().get(l)).getSup_reduc());
                    Avion_ticket_T tmpticket = (Avion_ticket_T) ((Avion_ticket_T) parent.getDossier().getAvionTicket().get(l)).clone();
                    tmpticket.setSup_reduc(supreduc);
                    tmpticket.setLocalyModify(false);
                    parent.setTicket(tmpticket);
                } catch (CloneNotSupportedException cl) {
                    cl.printStackTrace();
                }
                break;
            case InterfaceProduit.AS:
                try {
                    supreduc = getSupReducClone(((Assurance_T) parent.getDossier().getAssurance().get(l)).getSup_reduc());
                    Assurance_T tmpassu = (Assurance_T) ((Assurance_T) parent.getDossier().getAssurance().get(l)).clone();
                    tmpassu.setSup_reduc(supreduc);
                    tmpassu.setLocalyModify(false);
                    parent.setAssurance(tmpassu);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.BRO:
                try {
                    supreduc = getSupReducClone(((Brochure_T) parent.getDossier().getBrochure().get(l)).getSup_reduc());
                    Brochure_T tmpbroch = (Brochure_T) ((Brochure_T) parent.getDossier().getBrochure().get(l)).clone();
                    tmpbroch.setSup_reduc(supreduc);
                    tmpbroch.setLocalyModify(false);
                    parent.setBrochure(tmpbroch);
                } catch (CloneNotSupportedException cl) {
                    cl.printStackTrace();
                }
                break;
            case InterfaceProduit.HO:
                try {
                    supreduc = getSupReducClone(((Hotel_T) parent.getDossier().getHotel().get(l)).getSup_reduc());
                    Hotel_T tmpHot = (Hotel_T) ((Hotel_T) parent.getDossier().getHotel().get(l)).clone();
                    tmpHot.setSup_reduc(supreduc);
                    tmpHot.setLocalyModify(false);
                    parent.setHotel(tmpHot);
                } catch (CloneNotSupportedException cl) {
                    cl.printStackTrace();
                }
                break;
            case InterfaceProduit.BA:
                try {
                    supreduc = getSupReducClone(((Bateau_T) parent.getDossier().getBateau().get(l)).getSup_reduc());
                    Bateau_T tmpBat = (Bateau_T) ((Bateau_T) parent.getDossier().getBateau().get(l)).clone();
                    tmpBat.setSup_reduc(supreduc);
                    tmpBat.setLocalyModify(false);
                    parent.setBateau(tmpBat);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.TR:
                try {
                    supreduc = getSupReducClone(((Train_T) parent.getDossier().getTrain().get(l)).getSup_reduc());
                    Train_T tmpTrain = (Train_T) ((Train_T) parent.getDossier().getTrain().get(l)).clone();
                    tmpTrain.setSup_reduc(supreduc);
                    tmpTrain.setLocalyModify(false);
                    parent.setTrain(tmpTrain);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.VO:
                try {
                    supreduc = getSupReducClone(((VoitureLocation_T) parent.getDossier().getVoitureLocation().get(l)).getSup_reduc());
                    VoitureLocation_T tmpVoit = (VoitureLocation_T) ((VoitureLocation_T) parent.getDossier().getVoitureLocation().get(l)).clone();
                    tmpVoit.setSup_reduc(supreduc);
                    tmpVoit.setLocalyModify(false);
                    parent.setVoitureLocation(tmpVoit);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.CAR:
                try {
                    supreduc = getSupReducClone(((Car_T) parent.getDossier().getCar().get(l)).getSup_reduc());
                    Car_T tmpVoit = (Car_T) ((Car_T) parent.getDossier().getCar().get(l)).clone();
                    tmpVoit.setSup_reduc(supreduc);
                    tmpVoit.setLocalyModify(false);
                    parent.setCar(tmpVoit);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.DIV:
                try {
                    supreduc = getSupReducClone(((Divers_T) parent.getDossier().getDivers().get(l)).getSup_reduc());
                    Divers_T tmpVoit = (Divers_T) ((Divers_T) parent.getDossier().getDivers().get(l)).clone();
                    tmpVoit.setSup_reduc(supreduc);
                    tmpVoit.setLocalyModify(false);
                    parent.setDiverst(tmpVoit);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;
            case InterfaceProduit.TAX:
                try {
                    supreduc = getSupReducClone(((Taxi_T) parent.getDossier().getTaxi().get(l)).getSup_reduc());
                    Taxi_T tmpTax = (Taxi_T) ((Taxi_T) parent.getDossier().getTaxi().get(l)).clone();
                    tmpTax.setSup_reduc(supreduc);
                    tmpTax.setLocalyModify(false);
                    parent.setTaxi(tmpTax);
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
                break;

        }
        return pd_affichage.getParentType();
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
        switch (action) {
            case ActionToolBar.ACT_INSERT:
                dbInsert();
                break;
            case ActionToolBar.ACT_READ:
                if (grp_Table_ProduitIndex.getRowCount() != 0) {
                    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.WAIT_CURSOR));
                    m_Creation = false;
                    int p = selectObjectInTable();
                    if (p == produit_T.FRAIS) {
                        parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.DEFAULT_CURSOR));
                        return;
                    }

                    if (p != grp_LSelect_TypeProduits.getCleUnik())
                        setListSelector();
                    showProductPanel(grp_LSelect_TypeProduits.getCleUnik(), ActionToolBar.ACT_READ);
                    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE, new Cursor(Cursor.DEFAULT_CURSOR));
                }
                break;
        }
    }

    public void doCancel() {
        switch (action) {
            case ActionToolBar.ACT_INSERT:
                displayReadMode();
                break;
            case ActionToolBar.ACT_READ:
                parent.nextSpecificScreen(0);
                break;
            case ActionToolBar.ACT_MODIFY:
                enableDocument(false);
                displayReadMode();
        }
    }

    public void doClose() {
    }

    public void doCreate() {
        displayInsertMode();
    }

    private void annulAvion() {

    }

    public void doDelete() {
        int j = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) model.getClassAffichage(i);
            if (produitaff.getParent().getTypeDeProduitCleunik() == produit_T.AV && !produitaff.getParent().isIsnewreccord()) {
                if (!produitaff.isReducsup()) {
                    if (produitaff.m_delete.booleanValue() == true) {
                        if (!parent.getDossier().isNewreccord()) {
                            if (((Avion_ticket_T) produitaff.getParent()).getEtatAnnulation() != Avion_ticket_T.CANCEL && ((Avion_ticket_T) produitaff.getParent()).getEtatAnnulation() != Avion_ticket_T.VOID) {
                                new AvionCancelFrame(parent.getSuperOwner(), true, parent.getServeur(), parent.getCurrentUser(), parent.getDossier().getCscleunik(), (Avion_ticket_T) produitaff.getParent(), parent.getDossier(), parent).show();
                                parent.calculTotalUnProduit((Avion_ticket_T) produitaff.getParent());
                                parent.calculTotal();
                                refreshTable();
                                return;
                            } else
                                return;
                        }
                    }
                }
            }
            if (produitaff.m_delete.booleanValue() == true) {
                j++;
            }
        }
        if (j == 0) {
// int rep=parent.getMessageManager().showConfirmDialog(this,"annul_aucun_prod_text","annul_aucun_prod",parent.getCurrentUser());
            String annulTitle = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("annul_aucun_prod");
            String annulText = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("annul_aucun_prod_text");
            DossierMessage dossnew = new DossierMessage(parent.getSuperOwner(), true, annulTitle, annulText, DossierMessage.FOCUSCANCEL);
            int rep = dossnew.retval;
            dossnew.dispose();
            requestFocus();
            if (rep != JOptionPane.YES_OPTION) {
                return;
            } else {
                annulDossier();
            }

        } else {
            if (parent.getDossier().getDr_facture() == 1)
                annulCompta();
            else
                annulNotCompta();
        }
    }

    private void annulCompta() {
        double montant_annul = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) model.getClassAffichage(i);
            if (produitaff.m_delete.booleanValue() == true) {
                produitaff.getParent().setDeleted(true);
                if (parent.getDossier().getDr_facture() == 1) {
                    parent.getDossier().setComptaModify(true);
                    montant_annul = montant_annul + ((Double) produitaff.m_total_tva_Inc.m_data).doubleValue();
                    requestFocus();
                }


            }
        }
        parent.getDossier().setModifreccord(true);
        new ProductAnnulationDialog(parent.getSuperOwner(), true, parent, montant_annul, model, grp_Table_ProduitIndex).show();
        refreshTable();
        requestFocus();

    }

    private void annulDossier() {
        double montant_annul = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) model.getClassAffichage(i);


            produitaff.getParent().setDeleted(true);
            if (parent.getDossier().getDr_facture() == 1) {
                parent.getDossier().setComptaModify(true);
                montant_annul = montant_annul + ((Double) produitaff.m_total_tva_Inc.m_data).doubleValue();
                requestFocus();
            }

        }
        parent.getDossier().setModifreccord(true);
        new ProductAnnulationDialog(parent.getSuperOwner(), true, parent, montant_annul, model, grp_Table_ProduitIndex).show();
        refreshTable();
        requestFocus();

    }

    private void annulNotCompta() {
        for (int i = 0; i < model.getRowCount(); i++) {
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) model.getClassAffichage(i);
            if (produitaff.m_delete.booleanValue() == true) {

                produitaff.getParent().setDeleted(true);
                if (produitaff.getParent() instanceof produit_T) {
                    ((produit_T) produitaff.getParent()).annul();
                }

            }
        }
        parent.getDossier().setModifreccord(true);
        refreshTable();
        requestFocus();
    }

    public void doHelp() {
    }

    public void doModify() {
        if (grp_Table_ProduitIndex.getRowCount() > 0) {
            ProduitAffichage tmp = model.getClassAffichage(grp_Table_ProduitIndex.getSelectedRow());
            if (!tmp.isReducsup() && tmp.getParent().getTypeDeProduitCleunik() != produit_T.FRAIS) {
                action = ActionToolBar.ACT_MODIFY;
                enableDocument(true);
            }
        }
    }

    public void doNext() {
    }

    public void doPrevious() {
        requestFocus();
        switch (action) {
            case ActionToolBar.ACT_INSERT:
//if (grp_LSelect_Fournisseur.getCleUnik() > 0 && grp_LSelect_GrpProduits.getCleUnik() > 0 && grp_LSelect_TypeProduits.getCleUnik() > 0 &&
//  !grp_LSelect_Fournisseur.getText().equals("") && !grp_LSelect_GrpProduits.getText().equals("") && !grp_LSelect_TypeProduits.getText().equals("") && !pas2fois) {
                if (grp_LSelect_Fournisseur.getCleUnik() > 0 && grp_LSelect_GrpProduits.getCleUnik() > 0 && grp_LSelect_TypeProduits.getCleUnik() > 0 && !pas2fois) {
// !grp_LSelect_Fournisseur.getText().equals("") && !grp_LSelect_GrpProduits.getText().equals("") && !grp_LSelect_TypeProduits.getText().equals("") && !pas2fois) {
                    System.out.println("yyyyyyyyoyoyoyyiyiyiyiyiyiyooyoyoiyoiyoyo");
                    pas2fois = true;
                    m_Creation = true;
                    dbInsert();
                    pas2fois = false;
                } else if (!pas2fois) {
                    pas2fois = true;
                    parent.getMessageManager().showMessageDialog(this, "prod_not_selectCorrectly", "prod_not_selectCorrecty_titre", parent.getCurrentUser());
                    pas2fois = false;
                }
//  showProductPanel(1);
                break;
            case ActionToolBar.ACT_READ:
                if ((parent.getDossier().isNewreccord() || parent.getDossier().isModifreccord())) {
                    if (grp_Table_ProduitIndex.getRowCount() > 0) {
                        requestFocus();

                        if (parent.getDossier().isNcFactService() || parent.getDossier().isGroupe())
                            assu = true;

// int resp = parent.getMessageManager().showConfirmDialog(this, "prod_Assurance_mess", "prod_Assurance_titre", parent.getCurrentUser());
                        if (!assu) {
                            parent.lockedToolBar(true);
                            parent.calculTotal();


                            grp_Pan_needAssu = new NeedAssurance(parent);

                            grp_Pan_needAssu.show();
// grp_Pan_needAssu.refresh();
                            boolean[] assus = grp_Pan_needAssu.getAssuranceNeeded();
                            assu = true;
//if ((assus[NeedAssurance.ASSU_ANNUL] || assus[NeedAssurance.ASSU_BAG] || assus[NeedAssurance.ASSU_COMPL]) && grp_Pan_needAssu.getActionPerformed() == NeedAssurance.ACTION_OK) {
//grp_Table_ProduitIndex.tableChanged(new javax.swing.event.TableModelEvent(model));

                            refreshTable();
                            requestFocus();
                            parent.lockedToolBar(false);
                        } else {
                            requestFocus();
                            assu = false;
                            if (!parent.getDossier().isNcFactService() && !parent.getDossier().isGroupe()) {
                                if (parent.getDossier().isNewreccord())
                                    parent.nextScreen(1, 1);
                                else
                                    parent.nextScreen(1, 0);
                            } else {
                                if (parent.getDossier().getDrnumdos().charAt(8) == 'F') {

                                    parent.insertDossier();
                                    parent.setCurrentActionEnabled(new int[]{


                                            ActionToolBar.DO_CANCEL,
                                            ActionToolBar.DO_CLOSE,


                                    });
                                    doFacture();
                                } else if (parent.getDossier().getDrnumdos().charAt(8) == 'G') {

                                    /*parent.updateDossier();

                                     parent.setCurrentActionEnabled(new int[] {



                                                        ActionToolBar.DO_CANCEL,
                                                        ActionToolBar.DO_CLOSE,


                                                       });

                                    doVoucher();*/
                                    parent.nextScreen(1, 0);


                                } else if (parent.getDossier().getDrnumdos().charAt(8) == 'N') {
                                    parent.insertDossier();
                                    parent.setCurrentActionEnabled(new int[]{


                                            ActionToolBar.DO_CANCEL,
                                            ActionToolBar.DO_CLOSE,


                                    });
                                    try {

                                        /* srcastra.astra.gui.modules.dossier.DateFacturation dateFacturation = new srcastra.astra.gui.modules.dossier.DateFacturation(parent.getSuperOwner(),parent,true);
                                          dateFacturation.setSize(150,100);
                                           dateFacturation.setVisible(true);
                                          srcastra.astra.sys.classetransfert.utils.Date d=((srcastra.astra.gui.components.date.thedate.ADate)dateFacturation.getComponent(0)).getDate();
                                        dateFacturation.dispose();*/


                                        parent.getDossier().setNumnc(parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).NCService(parent.getDossier().getDrcleunik(), parent.getCurrentUser().getUrcleunik(), parent.getDossier(), parent.getCurrentUser()));
                                        //parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).setDateNCService(parent.getCurrentUser().getUrcleunik(), d,parent.getDossier().getDrcleunik(),parent.getDossier().getNumnc(),parent.getCurrentUser().getUreecleunik(),false);
                                        //parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).setDateNCService(parent.getCurrentUser().getUrcleunik(),d, parent.getDossier().getDrcleunik());
                                        new srcastra.astra.gui.modules.printing.PrintingPreview2(parent.getSuperOwner(), parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()), parent.getDossier(), parent.getCurrentUser(), ((srcastra.astra.gui.MainFrame) parent.getSuperOwner()).getMediator(), parent.getServeur(), 3, true);
                                    } catch (Exception e) {
                                    }

                                }
                            }

                        }
                    }

                } else {
                    System.out.println("next Screen");
                    parent.nextSpecificScreen(2);

                }
                break;
            case ActionToolBar.ACT_MODIFY:
                setNewDocumentToProduct();
                enableDocument(false);
                displayReadMode();
                break;
        }
    }

    boolean assu;

    public void doPrint() {
    }

    public void doVoucher() {


        new srcastra.astra.gui.modules.printing.PrintingPreview2(parent.getSuperOwner(), parent.getServeurDossier(), parent.getDossier(), parent.getCurrentUser(), parent.getMainframe().getMediator(), parent.getServeur(), 4, true);        // Add your handling code here:

    }


    public void doFacture() {


        try {
            if (parent.getDossier().getDr_facture() == 0) {

                srcastra.astra.gui.modules.dossier.DateFacturation dateFacturation = new srcastra.astra.gui.modules.dossier.DateFacturation(parent.getSuperOwner(), parent, true);
                dateFacturation.setSize(150, 100);
                dateFacturation.setVisible(true);
                srcastra.astra.sys.classetransfert.utils.Date d = ((srcastra.astra.gui.components.date.thedate.ADate) dateFacturation.getComponent(0)).getDate();
                dateFacturation.dispose();

                parent.getDossier().setDr_date_facturation(d);

                //parent.getDossier().setDr_date_facturation(dr_date_facturation)
                String numfact = parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).setDossierFacture(parent.getCurrentUser().getUrcleunik(), parent.getDossier().getDrcleunik(), Long.parseLong(parent.getDossier().getNumfact()), parent.getDossier().getDrtotalprix());
                parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).setDateNCService(parent.getCurrentUser().getUrcleunik(), d, parent.getDossier().getDrcleunik(), numfact, parent.getCurrentUser().getUreecleunik(), true);
                parent.getDossier().setNumfact(numfact);
                parent.getDossier().setDr_facture(1);
                //  parent.getServeur().renvParamCompta(parent.getCurrentUser().getUrcleunik()).commitNumpiece(parent.getCurrentUser().getUrcleunik());
            }
            new srcastra.astra.gui.modules.printing.PrintingPreview2(parent.getSuperOwner(), parent.getServeurDossier(), parent.getDossier(), parent.getCurrentUser(), parent.getMainframe().getMediator(), parent.getServeur(), 1, true);        // Add your handling code here:
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            //ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            //ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        //new FacturePrompt(parent.getSuperOwner(),true,parent).show();


    }

    public void doSwitch() {
    }

    public int[] getDefaultActionToolBarMask() {
        return new int[0];
    }

    public void refreshTable() {
        model.loadData();
        grp_Table_ProduitIndex.tableChanged(new javax.swing.event.TableModelEvent(model));
        if (grp_Table_ProduitIndex.getRowCount() > 0)
            grp_Table_ProduitIndex.changeSelection(0, 0, false, false);
        updateUI();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int action;
    private int dr_cleunik;
    private int fr_cleUnik;
    private AstraComponent[] componentToVerif;
    private DossierMainScreenModule parent;
//private DossierProduitIndexTableModel dos_prod_tableModel;
    //private GrpDeProduitTableModel tbMod_grpDeProd;
    private GrpProdListeTableModel tbMod_grpDeProd;
    private TypeProduitListeTableModel typeProdmodel;
    private FournisseurListeTableModel fournisseurModel;
    private ProduitTableModelGlobal model;
    private ComponentListener currentComponentListener;         // contient le ComponentListener actuellement utilisé
    private ToolBarInteraction tb_interaction;
    private boolean m_Creation = false;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private java.util.Vector popupVector;
    private ProductLayeredPanel grp_Pan_Aviation;
    private ProductLayeredPanel grp_Pan_Brochure;
    private ProductLayeredPanel grp_Pan_Hotel;
    private ProductLayeredPanel grp_Pan_Taxi;
    private ProductLayeredPanel grp_Pan_Divers;
    private ProductLayeredPanel grp_Pan_Bateau;
    private ProductLayeredPanel grp_Pan_VoitureLocation;
    private ProductLayeredPanel grp_Pan_Train;
    private ProductLayeredPanel grp_Pan_Assurance;
    private boolean pas2fois = false;
    private NeedAssurance grp_Pan_needAssu;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Fournisseur;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_GrpProduits;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_TypeProduits;
    private javax.swing.JLabel grp_Label_Fournisseur;
    private javax.swing.JLabel grp_Label_GrpProduits;
    private javax.swing.JLabel grp_Label_NbrTotDoc;
    private javax.swing.JLabel grp_Label_TypeProduits;
    private javax.swing.JPanel grp_Pan_IndexCombo;
    private javax.swing.JPanel grp_Pan_ProductSelection;
    private javax.swing.JTextField grp_TField_Invisible;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrConfirm;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrDoc;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrFact;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrFactSolde;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrNoteCredit;
    private javax.swing.JTable grp_Table_ProduitIndex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
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
        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/Dossier", parent.getCurrentUser().getLangage()).getString("tab_produit");
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
     * Getter for property m_Creation.
     *
     * @return Value of property m_Creation.
     */
    public boolean isM_Creation() {
        return m_Creation;
    }

    /**
     * Setter for property m_Creation.
     *
     * @param m_Creation New value of property m_Creation.
     */
    public void setM_Creation(boolean m_Creation) {
        this.m_Creation = m_Creation;
    }


    public class keyTableListener implements KeyListener {

        public void keyPressed(java.awt.event.KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                System.out.println("keyPressed");
                setListSelector();
                afficheDoc();
                grp_TField_Invisible.requestFocus();
            }
        }

        public void keyReleased(java.awt.event.KeyEvent keyEvent) {
        }

        public void keyTyped(java.awt.event.KeyEvent keyEvent) {
        }

    }

    public void setTitle(String title) {
        java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleDossier.DossierProduitPane");
        ((javax.swing.border.TitledBorder) getBorder()).setTitle(rb.getString("Titre_Pane_produit") + ((title.equals("")) ? "" : "  -  ") + title);
    }

    public void goUp() {
    }

    public java.awt.Component m_getGeneriqueTable() {
        return grp_Table_ProduitIndex;
    }

    public void doF10() {
    }

    public void addKeystroque() {
    }

    public void doF7() {
    }

    srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.Config config;
}
