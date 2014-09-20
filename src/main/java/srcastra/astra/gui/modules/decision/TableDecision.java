/*















 * FournisseurTableDecision.java















 *















 * Created on 28 mars 2002, 12:20















 */


package srcastra.astra.gui.modules.decision;


import srcastra.astra.sys.rmi.astrainterface;


import srcastra.astra.gui.components.tva.*;


import srcastra.astra.sys.*;


import srcastra.astra.sys.classetransfert.*;


import srcastra.astra.gui.modules.MainScreenModule;


import srcastra.astra.gui.modules.InternScreenModule;


import srcastra.astra.gui.components.actions.actionToolBar.*;


import srcastra.astra.gui.components.AstraComponent;


import srcastra.astra.gui.sys.ErreurScreenLibrary;


import srcastra.astra.gui.sys.formVerification.*;


import srcastra.astra.gui.components.actions.ToolBarInteraction;


import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.*;


import javax.swing.JOptionPane;


import srcastra.astra.gui.MainFrame;


import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.NCompteTableModel;


import srcastra.astra.sys.classetransfert.dossier.produit_T;


import srcastra.astra.sys.compta.*;


import javax.swing.*;


import srcastra.astra.gui.components.checkbox.ACheckBox;


import srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface;

import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;


/**
 * @author Sébastien
 */


public class TableDecision extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer, java.awt.event.ComponentListener {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CONSTRUCTOR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Creates new form FournisseurTableDecision
     */


    TvaMediator tvamediatorachat;


    TvaMediator tvamediatorachavente;


    TvaFrame achat;


    TvaFrame vente;

    InterfacePanel supreducPanel;


    public TableDecision() {


        this.addComponentListener(this);


        init();


    }


    /**
     * Creates new form FournisseurTableDecision
     */


    public TableDecision(astrainterface serveur, Loginusers_T currentUser, MainScreenModule parent, ActionToolBar actionToolBar, int frCleUnik, javax.swing.JPanel parentB, DecisionPane dp) {


        this.serveur = serveur;


        this.currentUser = currentUser;


        this.parent = parent;


        this.actionToolBar = actionToolBar;


        this.frCleUnik = frCleUnik;


        this.action = ActionToolBar.ACT_READ;


        this.parentB = parentB;


        this.dp = dp;


        this.addComponentListener(this);

        //parent.setCurrentActionEnabled(this);


    }


    private void initListe() {


        grp_LSelect_compteVente.setServeur(serveur);


        grp_LSelect_compteVente.setLogin(currentUser);


        grp_LSelect_compteVente.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(serveur, currentUser));


        grp_LSelect_compteVente.init2();


        grp_LSelect_compteAchat.setServeur(serveur);


        grp_LSelect_compteAchat.setLogin(currentUser);


        grp_LSelect_compteAchat.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(serveur, currentUser));


        grp_LSelect_compteAchat.init2();


    }


    public TableDecision(astrainterface serveur, Loginusers_T currentUser, MainScreenModule parent, ActionToolBar actionToolBar, int frCleUnik, javax.swing.JPanel parentB, DecisionPane dp, srcastra.astra.sys.classetransfert.dossier.InterfaceProduit produit, InterfacePanel panel) {


        this.serveur = serveur;


        this.currentUser = currentUser;

        this.supreducPanel = panel;


        this.parent = parent;


        this.actionToolBar = actionToolBar;


        this.frCleUnik = frCleUnik;


        this.action = ActionToolBar.ACT_READ;


        this.parentB = parentB;


        this.dp = dp;


        this.addComponentListener(this);


        this.produit = produit;

        //parent.setCurrentActionEnabled(this);


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// INITIALISATION

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Initialise les champs graphiques ainsi qu'un tableau pour permettre d'interargir
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * sur tous les éléments à partir d'une boucle.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * Les éléments graphiques et le tableau ne sera initialisé qu' 1 fois
     */


    private void init() {


        if (!initOnce) {


            parent.setCurrentPanel(this);

            // chargement des composants et de leurs propriétes


            tvamediatorachat = new TvaMediator();


            tvamediatorachavente = new TvaMediator();


            achat = new TvaFrame(parent.getSuperOwner(), false, parent.getServeur(), parent.getCurrentUser(), 1, parent);


            vente = new TvaFrame(parent.getSuperOwner(), false, parent.getServeur(), parent.getCurrentUser(), 2, parent);


            achat.setTvamediator(tvamediatorachat);


            vente.setTvamediator(tvamediatorachavente);


            initComponents();


            initListe();


            setDocumentMask();

            // chargement d'un tableau reprenant tous les éléments pour une correction


            this.composantToVerif = new AstraComponent[]{


                    grp_LSelect_codeTvaAchat, grp_TField_commissionAchat, grp_CBox_prixAchatTvaInclus,


                    grp_LSelect_compteAchat, grp_LSelect_codeTvaVente, grp_CBox_prixVenteTvaInclus, grp_CBox_tvaAComptabliserVente,


                    grp_CBox_CommissionTVAVente, grp_TField_nbrJourAvantEcheance, grp_TField_pcAccompteMinPPers, grp_TField_commissionVente,


                    grp_CBox_commissionInclusVente, grp_TField_pcAccompteVente, grp_LSelect_compteVente,


                    grp_TField_pcRepartitionBeneficeCreateur, grp_TField_pcRepartitionBeneficeMMère,


                    grp_TField_pcRepartitionBeneficeVendeur, grp_CBox_gestionStockBilletIATA,


                    grp_CBox_gestionAutreStock, grp_CBox_utiliseFranchise, grp_TField_pcAccompteFranchise, grp_TField_nbreJourFranchise,


                    grp_TField_soldeFranchise, grp_TField_nbreDeJourFranchiseSolde};


            this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);


            tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_STANDART);

            //-> Régistration des listeners pour la validité des composants


            for (int i = 0; i < composantToVerif.length; i++) {


                composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());


            }

            // les éléments ne sont intialisé qu'1 seule fois


            initOnce = true;

            /*if (parentB == null) {















                parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_MODIFY,















                                                           ActionToolBar.DO_CLOSE });















            }*/

            //  grp_LSelect_compteAchat.setListSelectorTableModel(new NCompteTableModel(serveur, currentUser, 0));

            //  grp_LSelect_compteVente.setListSelectorTableModel(new NCompteTableModel(serveur, currentUser, 0));


        }


        System.out.println("Clé unique du grp de prod dans TableDecision : " + frCleUnik);


    }


    private void setDocumentMask() {


        grp_TField_commissionAchat.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_nbrJourAvantEcheance.setDocument(new IntegerMask(0, 3, currentUser.getLangage()));


        grp_TField_pcAccompteMinPPers.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_commissionVente.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_pcAccompteVente.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_pcRepartitionBeneficeCreateur.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_pcRepartitionBeneficeMMère.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_pcRepartitionBeneficeVendeur.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_pcAccompteFranchise.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


        grp_TField_nbreJourFranchise.setDocument(new IntegerMask(1, 2, currentUser.getLangage()));


        grp_TField_soldeFranchise.setDocument(new DecimalMask(2, 2, currentUser.getLangage()));


    }


    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */


    private void initComponents() {


        jPanel1 = new javax.swing.JPanel();


        jPanel4 = new javax.swing.JPanel();


        jPanel3 = new javax.swing.JPanel();


        jPanel7 = new javax.swing.JPanel();


        grp_Label_codeTvaAchat = new javax.swing.JLabel();


        grp_Label_commissionAchat = new javax.swing.JLabel();


        grp_Label_prixAchatTvaInclus = new javax.swing.JLabel();


        grp_Label_pc02 = new javax.swing.JLabel();


        grp_TField_commissionAchat = new srcastra.astra.gui.components.textFields.ATextField();


        grp_LSelect_codeTvaAchat = new srcastra.astra.gui.components.combobox.liste.Liste();


        grp_Label_compteAchat = new javax.swing.JLabel();


        grp_LSelect_compteAchat = new srcastra.astra.gui.components.combobox.liste.Liste();


        grp_CBox_prixAchatTvaInclus = new srcastra.astra.gui.components.checkbox.ACheckBox();


        jPanel2 = new javax.swing.JPanel();


        jPanel8 = new javax.swing.JPanel();


        grp_Label_codeTvaVente = new javax.swing.JLabel();


        grp_Label_nbrJourAvantEcheance = new javax.swing.JLabel();


        grp_Label_pcAccompteMinPPers = new javax.swing.JLabel();


        grp_Label_commissionVente = new javax.swing.JLabel();


        grp_Label_pcAccompteVente = new javax.swing.JLabel();


        grp_Label_prixVenteTvaInclus = new javax.swing.JLabel();


        grp_Label_pc05 = new javax.swing.JLabel();


        grp_Label_pc06 = new javax.swing.JLabel();


        grp_Label_pc07 = new javax.swing.JLabel();


        grp_Label_commissionInclusVente = new javax.swing.JLabel();


        grp_TField_nbrJourAvantEcheance = new srcastra.astra.gui.components.textFields.ATextField();


        grp_TField_pcAccompteMinPPers = new srcastra.astra.gui.components.textFields.ATextField();


        grp_TField_commissionVente = new srcastra.astra.gui.components.textFields.ATextField();


        grp_TField_pcAccompteVente = new srcastra.astra.gui.components.textFields.ATextField();


        grp_LSelect_codeTvaVente = new srcastra.astra.gui.components.combobox.liste.Liste();


        grp_Label_compteVente = new javax.swing.JLabel();


        grp_LSelect_compteVente = new srcastra.astra.gui.components.combobox.liste.Liste();


        grp_CBox_prixVenteTvaInclus = new srcastra.astra.gui.components.checkbox.ACheckBox();


        grp_CBox_commissionInclusVente = new srcastra.astra.gui.components.checkbox.ACheckBox();


        jPanel5 = new javax.swing.JPanel();


        jPanel11 = new javax.swing.JPanel();


        jPanel12 = new javax.swing.JPanel();


        grp_Label_pcRepartitionBeneficeCreateur = new javax.swing.JLabel();


        grp_Label_pcRepartitionBeneficeMMère = new javax.swing.JLabel();


        grp_Label_pcRepartitionBeneficeVendeur = new javax.swing.JLabel();


        grp_Label_pc09 = new javax.swing.JLabel();


        grp_Label_pc10 = new javax.swing.JLabel();


        grp_Label_pc11 = new javax.swing.JLabel();


        grp_TField_pcRepartitionBeneficeCreateur = new srcastra.astra.gui.components.textFields.ATextField();


        grp_TField_pcRepartitionBeneficeMMère = new srcastra.astra.gui.components.textFields.ATextField();


        grp_TField_pcRepartitionBeneficeVendeur = new srcastra.astra.gui.components.textFields.ATextField();


        jPanel6 = new javax.swing.JPanel();


        jPanel9 = new javax.swing.JPanel();


        grp_Label_gestionStockBilletIATA = new javax.swing.JLabel();


        grp_Label_gestionAutreStock = new javax.swing.JLabel();


        grp_CBox_gestionStockBilletIATA = new srcastra.astra.gui.components.checkbox.ACheckBox();


        grp_CBox_gestionAutreStock = new srcastra.astra.gui.components.checkbox.ACheckBox();


        jPanel13 = new javax.swing.JPanel();


        jPanel14 = new javax.swing.JPanel();


        grp_Label_pcAccompteFranchise = new javax.swing.JLabel();


        grp_TField_pcAccompteFranchise = new srcastra.astra.gui.components.textFields.ATextField();


        grp_Label_nbreJourFranchise = new javax.swing.JLabel();


        grp_TField_nbreJourFranchise = new srcastra.astra.gui.components.textFields.ATextField();


        grp_Label_soldeFranchise = new javax.swing.JLabel();


        grp_TField_soldeFranchise = new srcastra.astra.gui.components.textFields.ATextField();


        jLabel5 = new javax.swing.JLabel();


        jLabel6 = new javax.swing.JLabel();


        grp_Label_utiliseFranchise = new javax.swing.JLabel();


        grp_CBox_utiliseFranchise = new srcastra.astra.gui.components.checkbox.ACheckBox();


        grp_TField_nbreDeJourFranchiseSolde = new srcastra.astra.gui.components.textFields.ATextField();


        grp_Label_nbreDeJourFranchiseSolde = new javax.swing.JLabel();


        jPanel50 = new JPanel();


        jPanel51 = new JPanel();


        jLabel50 = new JLabel();


        jLabel51 = new JLabel();


        jLabel52 = new JLabel();


        jLabel53 = new JLabel();


        grp_CBox_tvaAComptabliserVente = new ACheckBox();


        grp_CBox_CommissionTVAVente = new ACheckBox();


        setLayout(new java.awt.BorderLayout());


        setEnabled(false);


        jPanel1.setLayout(new java.awt.GridLayout(1, 2));


        jPanel4.setEnabled(false);


        jPanel3.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_titreAchat")));


        jPanel3.setPreferredSize(new java.awt.Dimension(341, 120));


        jPanel3.setEnabled(false);


        jPanel7.setLayout(new java.awt.GridBagLayout());


        java.awt.GridBagConstraints gridBagConstraints1;


        jPanel7.setEnabled(false);


        grp_Label_codeTvaAchat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_codeTvaAchat"));


        grp_Label_codeTvaAchat.setForeground(java.awt.Color.black);


        grp_Label_codeTvaAchat.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_codeTvaAchat.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_codeTvaAchat.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_codeTvaAchat.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 0;


        gridBagConstraints1.gridy = 0;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_Label_codeTvaAchat, gridBagConstraints1);


        grp_Label_commissionAchat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcCommissionAchat"));


        grp_Label_commissionAchat.setForeground(java.awt.Color.black);


        grp_Label_commissionAchat.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_commissionAchat.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionAchat.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionAchat.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 0;


        gridBagConstraints1.gridy = 1;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_Label_commissionAchat, gridBagConstraints1);


        grp_Label_prixAchatTvaInclus.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_prixAchatTvaInclus"));


        grp_Label_prixAchatTvaInclus.setForeground(java.awt.Color.black);


        grp_Label_prixAchatTvaInclus.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_prixAchatTvaInclus.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_prixAchatTvaInclus.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_prixAchatTvaInclus.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 0;


        gridBagConstraints1.gridy = 2;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_Label_prixAchatTvaInclus, gridBagConstraints1);


        grp_Label_pc02.setText("%");


        grp_Label_pc02.setForeground(java.awt.Color.black);


        grp_Label_pc02.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 2;


        gridBagConstraints1.gridy = 1;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_Label_pc02, gridBagConstraints1);


        grp_TField_commissionAchat.setEnabled(false);


        grp_TField_commissionAchat.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_commissionAchat.setMinimumSize(new java.awt.Dimension(50, 18));


        grp_TField_commissionAchat.setGrp_Comp_nextComponent(grp_CBox_prixAchatTvaInclus);


        grp_TField_commissionAchat.setGrp_Comp_previousComponent(grp_LSelect_codeTvaAchat);


        grp_TField_commissionAchat.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_commissionAchat.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 1;


        gridBagConstraints1.gridy = 1;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_TField_commissionAchat, gridBagConstraints1);

        // grp_LSelect_codeTvaAchat.setsetListSelectorTableModel(new TypeTvaTableModel(serveur, currentUser));

        // grp_LSelect_codeTvaAchat.setTypeTextValue(ListSelectorMask.TYPEVALUE_DECIMAL);


        grp_LSelect_codeTvaAchat.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_LSelect_codeTvaAchat.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_LSelect_codeTvaAchat.setMaximumSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaAchat.setMinimumSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaAchat.setPreferredSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaAchat.setGrp_Comp_nextComponent(grp_TField_commissionAchat);


        grp_LSelect_codeTvaAchat.setServeur(parent.getServeur());


        grp_LSelect_codeTvaAchat.setLogin(parent.getCurrentUser());

        //this.typeProdmodel=new srcastra.astra.gui.components.combobox.liste.TypeProduitListeTableModel(parent.getServeur(),parent.getCurrentUser());


        grp_LSelect_codeTvaAchat.setModel(tvamediatorachat.getModel());//new srcastra.astra.gui.components.combobox.liste.TypeTvaListeTableModel(parent.getServeur(),parent.getCurrentUser()));


        grp_LSelect_codeTvaAchat.setTvamediator(tvamediatorachat);


        grp_LSelect_codeTvaAchat.init2();


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 1;


        gridBagConstraints1.gridy = 0;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_LSelect_codeTvaAchat, gridBagConstraints1);


        grp_Label_compteAchat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_compteAchat"));


        grp_Label_compteAchat.setForeground(java.awt.Color.black);


        grp_Label_compteAchat.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_compteAchat.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_compteAchat.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_compteAchat.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 0;


        gridBagConstraints1.gridy = 3;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_Label_compteAchat, gridBagConstraints1);


        grp_LSelect_compteAchat.setEnabled(false);


        grp_LSelect_compteAchat.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_LSelect_compteAchat.setMinimumSize(new java.awt.Dimension(100, 18));


        grp_LSelect_compteAchat.setPreferredSize(new java.awt.Dimension(100, 18));


        grp_LSelect_compteAchat.setGrp_Comp_nextComponent(grp_LSelect_codeTvaVente);

        //   grp_LSelect_compteAchat.setGrp_Comp_previousComponent(grp_CBox_prixAchatTvaInclus);


        grp_LSelect_compteAchat.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_LSelect_compteAchat.setVerifyInputWhenFocusTarget(false);

        //  grp_LSelect_compteAchat.setTypeTextValue(ListSelectorMask.TYPEVALUE_ALPHANUM);


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 1;


        gridBagConstraints1.gridy = 3;


        gridBagConstraints1.gridwidth = 2;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_LSelect_compteAchat, gridBagConstraints1);


        grp_CBox_prixAchatTvaInclus.setEnabled(false);


        grp_CBox_prixAchatTvaInclus.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_prixAchatTvaInclus.setRequestFocusEnabled(false);


        grp_CBox_prixAchatTvaInclus.setVerifyInputWhenFocusTarget(false);


        grp_CBox_prixAchatTvaInclus.setGrp_Comp_previousComponent(grp_CBox_prixAchatTvaInclus);


        grp_CBox_prixAchatTvaInclus.setGrp_Comp_nextComponent(grp_LSelect_compteAchat);


        grp_CBox_prixAchatTvaInclus.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                grp_CBox_prixAchatTvaInclusActionPerformed(evt);


            }


        });


        gridBagConstraints1 = new java.awt.GridBagConstraints();


        gridBagConstraints1.gridx = 1;


        gridBagConstraints1.gridy = 2;


        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel7.add(grp_CBox_prixAchatTvaInclus, gridBagConstraints1);


        jPanel3.add(jPanel7);


        jPanel4.add(jPanel3);


        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_titreVente")));


        jPanel2.setPreferredSize(new java.awt.Dimension(341, 210));


        jPanel2.setEnabled(false);


        jPanel8.setLayout(new java.awt.GridBagLayout());


        java.awt.GridBagConstraints gridBagConstraints2;


        jPanel8.setEnabled(false);


        grp_Label_codeTvaVente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_codeTvaVente"));


        grp_Label_codeTvaVente.setForeground(java.awt.Color.black);


        grp_Label_codeTvaVente.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_codeTvaVente.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_codeTvaVente.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_codeTvaVente.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_codeTvaVente, gridBagConstraints2);


        grp_Label_nbrJourAvantEcheance.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_nbrJourAvantEcheance"));


        grp_Label_nbrJourAvantEcheance.setForeground(java.awt.Color.black);


        grp_Label_nbrJourAvantEcheance.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_nbrJourAvantEcheance.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_nbrJourAvantEcheance.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_nbrJourAvantEcheance.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 2;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_nbrJourAvantEcheance, gridBagConstraints2);


        grp_Label_pcAccompteMinPPers.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcAccompteMinPPers"));


        grp_Label_pcAccompteMinPPers.setForeground(java.awt.Color.black);


        grp_Label_pcAccompteMinPPers.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_pcAccompteMinPPers.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_pcAccompteMinPPers.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_pcAccompteMinPPers.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 3;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_pcAccompteMinPPers, gridBagConstraints2);


        grp_Label_commissionVente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_commissionVente"));


        grp_Label_commissionVente.setForeground(java.awt.Color.black);


        grp_Label_commissionVente.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_commissionVente.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionVente.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionVente.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 4;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_commissionVente, gridBagConstraints2);


        grp_Label_pcAccompteVente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcaccompteVente"));


        grp_Label_pcAccompteVente.setForeground(java.awt.Color.black);


        grp_Label_pcAccompteVente.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_pcAccompteVente.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_pcAccompteVente.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_pcAccompteVente.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 6;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_pcAccompteVente, gridBagConstraints2);

        /*grp_Label_prixVenteTvaInclus.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_prixVenteTvaInclus"));















       grp_Label_prixVenteTvaInclus.setForeground(java.awt.Color.black);















       grp_Label_prixVenteTvaInclus.setFont(new java.awt.Font("Tahoma", 0, 10));















       grp_Label_prixVenteTvaInclus.setPreferredSize(new java.awt.Dimension(197, 14));















       grp_Label_prixVenteTvaInclus.setMinimumSize(new java.awt.Dimension(197, 14));















       grp_Label_prixVenteTvaInclus.setMaximumSize(new java.awt.Dimension(197, 14));















       gridBagConstraints2 = new java.awt.GridBagConstraints();















       gridBagConstraints2.gridx = 0;















       gridBagConstraints2.gridy = 1;















       gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);















       gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;















       jPanel8.add(grp_Label_prixVenteTvaInclus, gridBagConstraints2);*/


        grp_Label_pc05.setText("%");


        grp_Label_pc05.setForeground(java.awt.Color.black);


        grp_Label_pc05.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 2;


        gridBagConstraints2.gridy = 3;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_pc05, gridBagConstraints2);


        grp_Label_pc06.setText("%");


        grp_Label_pc06.setForeground(java.awt.Color.black);


        grp_Label_pc06.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 2;


        gridBagConstraints2.gridy = 4;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_pc06, gridBagConstraints2);


        grp_Label_pc07.setText("%");


        grp_Label_pc07.setForeground(java.awt.Color.black);


        grp_Label_pc07.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 2;


        gridBagConstraints2.gridy = 6;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_pc07, gridBagConstraints2);


        grp_Label_commissionInclusVente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_commissionInclusVente"));


        grp_Label_commissionInclusVente.setForeground(java.awt.Color.black);


        grp_Label_commissionInclusVente.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_commissionInclusVente.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionInclusVente.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_commissionInclusVente.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 5;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_commissionInclusVente, gridBagConstraints2);


        grp_TField_nbrJourAvantEcheance.setEnabled(false);


        grp_TField_nbrJourAvantEcheance.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_nbrJourAvantEcheance.setMinimumSize(new java.awt.Dimension(50, 18));


        grp_TField_nbrJourAvantEcheance.setGrp_Comp_nextComponent(grp_TField_pcAccompteMinPPers);


        grp_TField_nbrJourAvantEcheance.setGrp_Comp_previousComponent(grp_CBox_prixVenteTvaInclus);


        grp_TField_nbrJourAvantEcheance.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_nbrJourAvantEcheance.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 2;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_TField_nbrJourAvantEcheance, gridBagConstraints2);


        grp_TField_pcAccompteMinPPers.setEnabled(false);


        grp_TField_pcAccompteMinPPers.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcAccompteMinPPers.setMinimumSize(new java.awt.Dimension(50, 18));


        grp_TField_pcAccompteMinPPers.setGrp_Comp_nextComponent(grp_TField_commissionVente);


        grp_TField_pcAccompteMinPPers.setGrp_Comp_previousComponent(grp_TField_nbrJourAvantEcheance);


        grp_TField_pcAccompteMinPPers.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcAccompteMinPPers.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 3;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_TField_pcAccompteMinPPers, gridBagConstraints2);


        grp_TField_commissionVente.setEnabled(false);


        grp_TField_commissionVente.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_commissionVente.setMinimumSize(new java.awt.Dimension(50, 18));


        grp_TField_commissionVente.setGrp_Comp_nextComponent(grp_CBox_commissionInclusVente);


        grp_TField_commissionVente.setGrp_Comp_previousComponent(grp_TField_pcAccompteMinPPers);


        grp_TField_commissionVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_commissionVente.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 4;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_TField_commissionVente, gridBagConstraints2);


        grp_TField_pcAccompteVente.setEnabled(false);


        grp_TField_pcAccompteVente.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcAccompteVente.setMinimumSize(new java.awt.Dimension(50, 18));


        grp_TField_pcAccompteVente.setGrp_Comp_nextComponent(grp_LSelect_compteVente);


        grp_TField_pcAccompteVente.setGrp_Comp_previousComponent(grp_TField_commissionVente);


        grp_TField_pcAccompteVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcAccompteVente.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 6;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_TField_pcAccompteVente, gridBagConstraints2);

        //  grp_LSelect_codeTvaVente.setListSelectorTableModel(new TypeTvaTableModel(serveur, currentUser));

        // grp_LSelect_codeTvaVente.setTypeTextValue(ListSelectorMask.TYPEVALUE_DECIMAL);


        grp_LSelect_codeTvaVente.setEnabled(false);


        grp_LSelect_codeTvaVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_LSelect_codeTvaVente.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_LSelect_codeTvaVente.setVerifyInputWhenFocusTarget(false);

        //grp_LSelect_codeTvaVente.setGrp_Comp_previousComponent(grp_TField_commissionAchat);


        grp_LSelect_codeTvaVente.setMaximumSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaVente.setMinimumSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaVente.setPreferredSize(new java.awt.Dimension(70, 18));


        grp_LSelect_codeTvaVente.setGrp_Comp_nextComponent(grp_CBox_prixVenteTvaInclus);


        grp_LSelect_codeTvaVente.setServeur(parent.getServeur());


        grp_LSelect_codeTvaVente.setLogin(parent.getCurrentUser());

        //this.typeProdmodel=new srcastra.astra.gui.components.combobox.liste.TypeProduitListeTableModel(parent.getServeur(),parent.getCurrentUser());


        grp_LSelect_codeTvaVente.setModel(tvamediatorachavente.getModel());//new srcastra.astra.gui.components.combobox.liste.TypeTvaListeTableModel(parent.getServeur(),parent.getCurrentUser()));


        grp_LSelect_codeTvaVente.init2();


        grp_LSelect_codeTvaVente.setTvamediator(tvamediatorachavente);


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 0;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_LSelect_codeTvaVente, gridBagConstraints2);


        grp_Label_compteVente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_compteVente"));


        grp_Label_compteVente.setForeground(java.awt.Color.black);


        grp_Label_compteVente.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_compteVente.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_compteVente.setMinimumSize(new java.awt.Dimension(197, 14));


        grp_Label_compteVente.setMaximumSize(new java.awt.Dimension(197, 14));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 0;


        gridBagConstraints2.gridy = 7;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_Label_compteVente, gridBagConstraints2);


        grp_LSelect_compteVente.setEnabled(false);


        grp_LSelect_compteVente.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_LSelect_compteVente.setGrp_Comp_nextComponent(grp_TField_pcRepartitionBeneficeCreateur);

        // grp_LSelect_compteVente.setGrp_Comp_previousComponent(grp_TField_pcAccompteVente);


        grp_LSelect_compteVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_LSelect_compteVente.setVerifyInputWhenFocusTarget(false);

        //  grp_LSelect_compteVente.setTypeTextValue(ListSelectorMask.TYPEVALUE_ALPHANUM);


        grp_LSelect_compteVente.setPreferredSize(new java.awt.Dimension(100, 18));


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 7;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_LSelect_compteVente, gridBagConstraints2);

        /*grp_CBox_prixVenteTvaInclus.setEnabled(false);















       grp_CBox_prixVenteTvaInclus.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));















       grp_CBox_prixVenteTvaInclus.setRequestFocusEnabled(false);















       grp_CBox_prixVenteTvaInclus.setVerifyInputWhenFocusTarget(false);















       grp_CBox_prixVenteTvaInclus.setGrp_Comp_previousComponent(grp_LSelect_codeTvaVente);















       grp_CBox_prixVenteTvaInclus.setGrp_Comp_nextComponent(grp_TField_nbrJourAvantEcheance);















       grp_CBox_prixVenteTvaInclus.addActionListener(new java.awt.event.ActionListener() {















           public void actionPerformed(java.awt.event.ActionEvent evt) {















               grp_CBox_prixVenteTvaInclusActionPerformed(evt);















           }















       });































       gridBagConstraints2 = new java.awt.GridBagConstraints();















       gridBagConstraints2.gridx = 1;















       gridBagConstraints2.gridy = 1;















       gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);















       gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;















       jPanel8.add(grp_CBox_prixVenteTvaInclus, gridBagConstraints2);*/


        java.awt.FlowLayout flow = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);


        flow.setHgap(0);


        flow.setVgap(0);


        flow.setAlignment(java.awt.FlowLayout.LEFT);


        jPanel50.setLayout(flow);


        java.awt.GridBagConstraints gridbA = new java.awt.GridBagConstraints();


        gridbA.gridx = 0;


        gridbA.gridy = 1;


        gridbA.fill = java.awt.GridBagConstraints.VERTICAL;


        gridbA.gridwidth = 3;


        jPanel8.add(jPanel50, gridbA);


        jPanel51.setLayout(new java.awt.GridBagLayout());


        jPanel50.add(jPanel51);


        jLabel50.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_tva"));


        jLabel50.setForeground(java.awt.Color.black);


        jLabel50.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 10));


        java.awt.GridBagConstraints gridbB = new java.awt.GridBagConstraints();


        gridbB.gridx = 0;


        gridbB.gridy = 0;


        gridbB.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbB.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(jLabel50, gridbB);


        jLabel51.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_tvaCompVente"));


        jLabel51.setForeground(java.awt.Color.black);


        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 10));


        java.awt.GridBagConstraints gridbC = new java.awt.GridBagConstraints();


        gridbC.gridx = 1;


        gridbC.gridy = 0;


        gridbC.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbC.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(jLabel51, gridbC);


        grp_CBox_prixVenteTvaInclus.setEnabled(false);


        grp_CBox_prixVenteTvaInclus.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_prixVenteTvaInclus.setRequestFocusEnabled(false);


        grp_CBox_prixVenteTvaInclus.setVerifyInputWhenFocusTarget(false);


        grp_CBox_prixVenteTvaInclus.setGrp_Comp_nextComponent(grp_CBox_tvaAComptabliserVente);


        java.awt.GridBagConstraints gridbD = new java.awt.GridBagConstraints();


        gridbD.gridx = 2;


        gridbD.gridy = 0;


        gridbD.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbD.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(grp_CBox_prixVenteTvaInclus, gridbD);


        jLabel52.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_tvaCompt"));


        jLabel52.setForeground(java.awt.Color.black);


        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 10));


        java.awt.GridBagConstraints gridbE = new java.awt.GridBagConstraints();


        gridbE.gridx = 3;


        gridbE.gridy = 0;


        gridbE.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbE.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(jLabel52, gridbE);


        grp_CBox_tvaAComptabliserVente.setEnabled(false);


        grp_CBox_tvaAComptabliserVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_tvaAComptabliserVente.setRequestFocusEnabled(false);


        grp_CBox_tvaAComptabliserVente.setVerifyInputWhenFocusTarget(false);


        grp_CBox_tvaAComptabliserVente.setGrp_Comp_nextComponent(grp_CBox_CommissionTVAVente);


        java.awt.GridBagConstraints gridbF = new java.awt.GridBagConstraints();


        gridbF.gridx = 4;


        gridbF.gridy = 0;


        gridbF.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbF.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(grp_CBox_tvaAComptabliserVente, gridbF);


        jLabel53.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_TvaComVente"));


        jLabel53.setForeground(java.awt.Color.black);


        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 10));


        java.awt.GridBagConstraints gridbI = new java.awt.GridBagConstraints();


        gridbI.gridx = 5;


        gridbI.gridy = 0;


        gridbI.insets = new java.awt.Insets(0, 0, 0, 4);


        gridbI.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(jLabel53, gridbI);


        grp_CBox_CommissionTVAVente.setEnabled(false);


        grp_CBox_CommissionTVAVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_CommissionTVAVente.setRequestFocusEnabled(false);


        grp_CBox_CommissionTVAVente.setVerifyInputWhenFocusTarget(false);


        grp_CBox_CommissionTVAVente.setGrp_Comp_nextComponent(grp_TField_nbrJourAvantEcheance);


        java.awt.GridBagConstraints gridbH = new java.awt.GridBagConstraints();


        gridbH.gridx = 6;


        gridbH.gridy = 0;


        gridbH.insets = new java.awt.Insets(0, 0, 0, 0);


        gridbH.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel51.add(grp_CBox_CommissionTVAVente, gridbH);


        grp_CBox_commissionInclusVente.setEnabled(false);


        grp_CBox_commissionInclusVente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_commissionInclusVente.setRequestFocusEnabled(false);


        grp_CBox_commissionInclusVente.setVerifyInputWhenFocusTarget(false);


        grp_CBox_commissionInclusVente.setGrp_Comp_previousComponent(grp_TField_commissionVente);


        grp_CBox_commissionInclusVente.setGrp_Comp_nextComponent(grp_TField_pcAccompteVente);


        grp_CBox_commissionInclusVente.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                grp_CBox_commissionInclusVenteActionPerformed(evt);


            }


        });


        gridBagConstraints2 = new java.awt.GridBagConstraints();


        gridBagConstraints2.gridx = 1;


        gridBagConstraints2.gridy = 5;


        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel8.add(grp_CBox_commissionInclusVente, gridBagConstraints2);


        jPanel2.add(jPanel8);


        jPanel4.add(jPanel2);


        jPanel1.add(jPanel4);


        jPanel5.setEnabled(false);


        jPanel11.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_titreRepartition")));


        jPanel11.setPreferredSize(new java.awt.Dimension(341, 100));


        jPanel12.setLayout(new java.awt.GridBagLayout());


        java.awt.GridBagConstraints gridBagConstraints3;


        grp_Label_pcRepartitionBeneficeCreateur.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcRepartitionBeneficeCreateur"));


        grp_Label_pcRepartitionBeneficeCreateur.setForeground(java.awt.Color.black);


        grp_Label_pcRepartitionBeneficeCreateur.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_pcRepartitionBeneficeCreateur.setPreferredSize(new java.awt.Dimension(197, 14));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 0;


        gridBagConstraints3.gridy = 2;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 20);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pcRepartitionBeneficeCreateur, gridBagConstraints3);


        grp_Label_pcRepartitionBeneficeMMère.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcRepartitionBeneficeMM\u00E8re"));


        grp_Label_pcRepartitionBeneficeMMère.setForeground(java.awt.Color.black);


        grp_Label_pcRepartitionBeneficeMMère.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 0;


        gridBagConstraints3.gridy = 3;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 20);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pcRepartitionBeneficeMMère, gridBagConstraints3);


        grp_Label_pcRepartitionBeneficeVendeur.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_pcRepartitionBeneficeVendeur"));


        grp_Label_pcRepartitionBeneficeVendeur.setForeground(java.awt.Color.black);


        grp_Label_pcRepartitionBeneficeVendeur.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_pcRepartitionBeneficeVendeur.setPreferredSize(new java.awt.Dimension(197, 14));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 0;


        gridBagConstraints3.gridy = 4;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 20);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pcRepartitionBeneficeVendeur, gridBagConstraints3);


        grp_Label_pc09.setText("%");


        grp_Label_pc09.setForeground(java.awt.Color.black);


        grp_Label_pc09.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 2;


        gridBagConstraints3.gridy = 2;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pc09, gridBagConstraints3);


        grp_Label_pc10.setText("%");


        grp_Label_pc10.setForeground(java.awt.Color.black);


        grp_Label_pc10.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 2;


        gridBagConstraints3.gridy = 3;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pc10, gridBagConstraints3);


        grp_Label_pc11.setText("%");


        grp_Label_pc11.setForeground(java.awt.Color.black);


        grp_Label_pc11.setFont(new java.awt.Font("Tahoma", 0, 10));


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 2;


        gridBagConstraints3.gridy = 4;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_Label_pc11, gridBagConstraints3);


        grp_TField_pcRepartitionBeneficeCreateur.setEnabled(false);


        grp_TField_pcRepartitionBeneficeCreateur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcRepartitionBeneficeCreateur.setGrp_Comp_nextComponent(grp_TField_pcRepartitionBeneficeMMère);


        grp_TField_pcRepartitionBeneficeCreateur.setGrp_Comp_previousComponent(grp_LSelect_compteVente);


        grp_TField_pcRepartitionBeneficeCreateur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcRepartitionBeneficeCreateur.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 1;


        gridBagConstraints3.gridy = 2;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_TField_pcRepartitionBeneficeCreateur, gridBagConstraints3);


        grp_TField_pcRepartitionBeneficeMMère.setEnabled(false);


        grp_TField_pcRepartitionBeneficeMMère.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcRepartitionBeneficeMMère.setGrp_Comp_nextComponent(grp_TField_pcRepartitionBeneficeVendeur);


        grp_TField_pcRepartitionBeneficeMMère.setGrp_Comp_previousComponent(grp_TField_pcRepartitionBeneficeCreateur);


        grp_TField_pcRepartitionBeneficeMMère.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcRepartitionBeneficeMMère.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 1;


        gridBagConstraints3.gridy = 3;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_TField_pcRepartitionBeneficeMMère, gridBagConstraints3);


        grp_TField_pcRepartitionBeneficeVendeur.setEnabled(false);


        grp_TField_pcRepartitionBeneficeVendeur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcRepartitionBeneficeVendeur.setGrp_Comp_nextComponent(grp_CBox_gestionStockBilletIATA);


        grp_TField_pcRepartitionBeneficeVendeur.setGrp_Comp_previousComponent(grp_TField_pcRepartitionBeneficeMMère);


        grp_TField_pcRepartitionBeneficeVendeur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcRepartitionBeneficeVendeur.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints3 = new java.awt.GridBagConstraints();


        gridBagConstraints3.gridx = 1;


        gridBagConstraints3.gridy = 4;


        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel12.add(grp_TField_pcRepartitionBeneficeVendeur, gridBagConstraints3);


        jPanel11.add(jPanel12);


        jPanel5.add(jPanel11);


        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel6.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_titreDivers")));


        jPanel6.setPreferredSize(new java.awt.Dimension(341, 82));


        jPanel6.setEnabled(false);


        jPanel9.setLayout(new java.awt.GridBagLayout());


        java.awt.GridBagConstraints gridBagConstraints4;


        jPanel9.setEnabled(false);


        grp_Label_gestionStockBilletIATA.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_gestionStockBilletIATA"));


        grp_Label_gestionStockBilletIATA.setForeground(java.awt.Color.black);


        grp_Label_gestionStockBilletIATA.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_gestionStockBilletIATA.setPreferredSize(new java.awt.Dimension(197, 14));


        gridBagConstraints4 = new java.awt.GridBagConstraints();


        gridBagConstraints4.gridx = 0;


        gridBagConstraints4.gridy = 0;


        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 3, 20);


        gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel9.add(grp_Label_gestionStockBilletIATA, gridBagConstraints4);


        grp_Label_gestionAutreStock.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_gestionAutreStock"));


        grp_Label_gestionAutreStock.setForeground(java.awt.Color.black);


        grp_Label_gestionAutreStock.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_gestionAutreStock.setPreferredSize(new java.awt.Dimension(197, 14));


        gridBagConstraints4 = new java.awt.GridBagConstraints();


        gridBagConstraints4.gridx = 0;


        gridBagConstraints4.gridy = 1;


        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 3, 20);


        gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel9.add(grp_Label_gestionAutreStock, gridBagConstraints4);


        grp_CBox_gestionStockBilletIATA.setEnabled(false);


        grp_CBox_gestionStockBilletIATA.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_gestionStockBilletIATA.setRequestFocusEnabled(false);


        grp_CBox_gestionStockBilletIATA.setVerifyInputWhenFocusTarget(false);


        grp_CBox_gestionStockBilletIATA.setGrp_Comp_previousComponent(grp_Label_pcRepartitionBeneficeVendeur);


        grp_CBox_gestionStockBilletIATA.setGrp_Comp_nextComponent(grp_CBox_gestionAutreStock);


        grp_CBox_gestionStockBilletIATA.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                grp_CBox_gestionStockBilletIATAActionPerformed(evt);


            }


        });


        gridBagConstraints4 = new java.awt.GridBagConstraints();


        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel9.add(grp_CBox_gestionStockBilletIATA, gridBagConstraints4);


        grp_CBox_gestionAutreStock.setEnabled(false);


        grp_CBox_gestionAutreStock.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_gestionAutreStock.setRequestFocusEnabled(false);


        grp_CBox_gestionAutreStock.setVerifyInputWhenFocusTarget(false);


        grp_CBox_gestionAutreStock.setGrp_Comp_previousComponent(grp_CBox_gestionStockBilletIATA);


        grp_CBox_gestionAutreStock.setGrp_Comp_nextComponent(grp_CBox_utiliseFranchise);


        grp_CBox_gestionAutreStock.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                grp_CBox_gestionAutreStockActionPerformed(evt);


            }


        });


        gridBagConstraints4 = new java.awt.GridBagConstraints();


        gridBagConstraints4.gridx = 1;


        gridBagConstraints4.gridy = 1;


        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel9.add(grp_CBox_gestionAutreStock, gridBagConstraints4);


        jPanel6.add(jPanel9);


        jPanel5.add(jPanel6);


        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel13.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision", currentUser.getLangage()).getString("TD_titreFranchise")));


        jPanel13.setPreferredSize(new java.awt.Dimension(341, 140));


        jPanel14.setLayout(new java.awt.GridBagLayout());


        java.awt.GridBagConstraints gridBagConstraints5;


        grp_Label_pcAccompteFranchise.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_pcAccompteFranchise"));


        grp_Label_pcAccompteFranchise.setForeground(java.awt.Color.black);


        grp_Label_pcAccompteFranchise.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_pcAccompteFranchise.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_pcAccompteFranchise.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 0;


        gridBagConstraints5.gridy = 1;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_Label_pcAccompteFranchise, gridBagConstraints5);


        grp_TField_pcAccompteFranchise.setEnabled(false);


        grp_TField_pcAccompteFranchise.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_pcAccompteFranchise.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_pcAccompteFranchise.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 1;


        gridBagConstraints5.gridy = 1;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_TField_pcAccompteFranchise, gridBagConstraints5);


        grp_Label_nbreJourFranchise.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_nbreJour"));


        grp_Label_nbreJourFranchise.setForeground(java.awt.Color.black);


        grp_Label_nbreJourFranchise.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_nbreJourFranchise.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_nbreJourFranchise.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 0;


        gridBagConstraints5.gridy = 2;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_Label_nbreJourFranchise, gridBagConstraints5);


        grp_TField_nbreJourFranchise.setEnabled(false);


        grp_TField_nbreJourFranchise.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_nbreJourFranchise.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_nbreJourFranchise.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 1;


        gridBagConstraints5.gridy = 2;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_TField_nbreJourFranchise, gridBagConstraints5);


        grp_Label_soldeFranchise.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_pcSolde"));


        grp_Label_soldeFranchise.setForeground(java.awt.Color.black);


        grp_Label_soldeFranchise.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_soldeFranchise.setPreferredSize(new java.awt.Dimension(197, 14));


        grp_Label_soldeFranchise.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 0;


        gridBagConstraints5.gridy = 3;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_Label_soldeFranchise, gridBagConstraints5);


        grp_TField_soldeFranchise.setEnabled(false);


        grp_TField_soldeFranchise.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_soldeFranchise.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_soldeFranchise.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 1;


        gridBagConstraints5.gridy = 3;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_TField_soldeFranchise, gridBagConstraints5);


        jLabel5.setText("%");


        jLabel5.setForeground(java.awt.Color.black);


        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));


        jLabel5.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 2;


        gridBagConstraints5.gridy = 3;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(jLabel5, gridBagConstraints5);


        jLabel6.setText("%");


        jLabel6.setForeground(java.awt.Color.black);


        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));


        jLabel6.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 2;


        gridBagConstraints5.gridy = 1;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(jLabel6, gridBagConstraints5);


        grp_Label_utiliseFranchise.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_utiliseFranchise"));


        grp_Label_utiliseFranchise.setForeground(java.awt.Color.black);


        grp_Label_utiliseFranchise.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_utiliseFranchise.setPreferredSize(new java.awt.Dimension(197, 14));


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_Label_utiliseFranchise, gridBagConstraints5);


        grp_CBox_utiliseFranchise.setEnabled(false);


        grp_CBox_utiliseFranchise.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_CBox_utiliseFranchise.setVerifyInputWhenFocusTarget(false);


        grp_CBox_utiliseFranchise.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                grp_CBox_utiliseFranchiseActionPerformed(evt);


            }


        });


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_CBox_utiliseFranchise, gridBagConstraints5);


        grp_TField_nbreDeJourFranchiseSolde.setEnabled(false);


        grp_TField_nbreDeJourFranchiseSolde.setLastFocusedComponent(true);


        grp_TField_nbreDeJourFranchiseSolde.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));


        grp_TField_nbreDeJourFranchiseSolde.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));


        grp_TField_nbreDeJourFranchiseSolde.setVerifyInputWhenFocusTarget(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 1;


        gridBagConstraints5.gridy = 4;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 0);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_TField_nbreDeJourFranchiseSolde, gridBagConstraints5);


        grp_Label_nbreDeJourFranchiseSolde.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/TableDecision").getString("TD_nbreDeJourSolde"));


        grp_Label_nbreDeJourFranchiseSolde.setForeground(java.awt.Color.black);


        grp_Label_nbreDeJourFranchiseSolde.setFont(new java.awt.Font("Tahoma", 0, 10));


        grp_Label_nbreDeJourFranchiseSolde.setEnabled(false);


        gridBagConstraints5 = new java.awt.GridBagConstraints();


        gridBagConstraints5.gridx = 0;


        gridBagConstraints5.gridy = 4;


        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 3, 3);


        gridBagConstraints5.anchor = java.awt.GridBagConstraints.NORTHWEST;


        jPanel14.add(grp_Label_nbreDeJourFranchiseSolde, gridBagConstraints5);


        jPanel13.add(jPanel14);


        jPanel5.add(jPanel13);


        jPanel1.add(jPanel5);


        add(jPanel1, java.awt.BorderLayout.CENTER);


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => LISTENERS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void grp_CBox_commissionInclusVenteActionPerformed(java.awt.event.ActionEvent evt) {

        // Add your handling code here:


    }


    private void grp_CBox_prixVenteTvaInclusActionPerformed(java.awt.event.ActionEvent evt) {

        // Add your handling code here:


    }


    private void grp_CBox_prixAchatTvaInclusActionPerformed(java.awt.event.ActionEvent evt) {

        // Add your handling code here:


    }


    private void grp_CBox_utiliseFranchiseActionPerformed(java.awt.event.ActionEvent evt) {


        if (grp_CBox_utiliseFranchise.isSelected()) {


            setEnabledFranchise(true);


        } else {


            setEnabledFranchise(false);


        }


    }


    private void grp_CBox_gestionAutreStockActionPerformed(java.awt.event.ActionEvent evt) {

        // Add your handling code here:


    }


    private void grp_CBox_gestionStockBilletIATAActionPerformed(java.awt.event.ActionEvent evt) {


    }


    public void componentShown(java.awt.event.ComponentEvent componentEvent) {


        System.out.println("COMPONENT SHOWN COMPONENT SHOWN COMPONENT SHOWN COMPONENT SHOWN");


        parent.setCurrentPanel(this);


        switch (action) {


            case ActionToolBar.ACT_READ:


                displayReadMode();


                break;


            case ActionToolBar.ACT_INSERT:


                displayInsertMode();


                break;


        }


    }


    public void componentMoved(java.awt.event.ComponentEvent componentEvent) {


    }


    public void componentResized(java.awt.event.ComponentEvent componentEvent) {


    }


    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE AU BEANS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void setEnabledFranchise(boolean enabled) {

        //-> label


        grp_Label_pcAccompteFranchise.setEnabled(enabled);


        grp_Label_nbreJourFranchise.setEnabled(enabled);


        grp_Label_soldeFranchise.setEnabled(enabled);


        grp_Label_nbreDeJourFranchiseSolde.setEnabled(enabled);

        //-> champs


        grp_TField_pcAccompteFranchise.setEnabled(enabled);


        grp_TField_nbreJourFranchise.setEnabled(enabled);


        grp_TField_soldeFranchise.setEnabled(enabled);


        grp_TField_nbreDeJourFranchiseSolde.setEnabled(enabled);


    }


    public void loadModele(int id) {


        try {


            Object obj = serveur.ChargeObject(currentUser.getUrlmcleunik(), currentUser.getUrcleunik(), id, 1, serveur.GRP_PROD_DEF);


            grpDecision = (srcastra.astra.sys.classetransfert.Grpdecision_T) obj;


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }


        catch (Exception e) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);


        }


        updateAllFields();


        System.out.println("MODELE LOADE");


    }


    private void ComputeRepartition(Double createur, Double meMere, Double vendeur, int field) {


        if (createur != null && meMere != null && vendeur != null) {


            double result = 100;

            /* si c'est le premier champs (createur) on fait un calcul sur les autres à partir















         * de celui-ci */


            if (field == 1) {


                result -= createur.doubleValue();


                result = result / 2;


                meMere = new Double(result);


                vendeur = new Double(result);


            } else if (field == 2) {


                result -= createur.doubleValue() + meMere.doubleValue();


                vendeur = new Double(result);


            } else if (field == 3) {


                result -= createur.doubleValue() + meMere.doubleValue() + vendeur.doubleValue();


                if (result != 0) {


                    System.out.println("la répartition totale doit être égale à 100 %");


                }


            }


        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void chargeData() {


    }


    public void dbSelect() {


        if (parentB == null)


        {


            if (produit != null)


                grpDecision = produit.getGroupdec();


        } else {


            try {

                // GrpGroupDecRmiInterface  = serveur.renvGrpDecRmiObject(urCleunikChargeObject(0, currentUser.getUrcleunik(), frCleUnik, 1, serveur.COMBO_FOURGRPDEC);


                GrpGroupDecRmiInterface grprmi = serveur.renvGrpDecRmiObject(currentUser.getUrcleunik());


                grpDecision = grprmi.selectFournisseur(frCleUnik, 0, currentUser.getUrcleunik());

                //grpDecision = (srcastra.astra.sys.classetransfert.Grpdecision_T) obj;


                grpDecisionCleUnik = grpDecision.getGncleunik();


            }


            catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


            }


            catch (java.rmi.RemoteException re) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


            }


            catch (Exception e) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);


            }


        }


        updateAllFields();


    }


    public void dbSelectForUpdate() {


        action = ActionToolBar.ACT_MODIFY;


        if (parentB == null) {


            updateAllFields();


            grpDecision.setModifyreccord(true);


            checkValidity = true;


        } else {


            try {

                // GrpGroupDecRmiInterface  = serveur.renvGrpDecRmiObject(urCleunikChargeObject(0, currentUser.getUrcleunik(), frCleUnik, 1, serveur.COMBO_FOURGRPDEC);


                GrpGroupDecRmiInterface grprmi = serveur.renvGrpDecRmiObject(currentUser.getUrcleunik());


                grpDecision = grprmi.selectFournisseur(frCleUnik, 1, currentUser.getUrcleunik());

                //grpDecision = (srcastra.astra.sys.classetransfert.Grpdecision_T) obj;


                grpDecisionCleUnik = grpDecision.getGncleunik();


                checkValidity = true;


            }


            catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, currentUser);


            }


            catch (java.rmi.RemoteException re) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


            }


            catch (Exception e) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);


            }

            //updateAllFields();


        }


    }


    private float getTvaPrct(int cleunik, TvaFrame frame) {


        Object[] tmpachat = (Object[]) frame.getAchathash().get(new Integer(cleunik));

        float tva = 0.0f;

        try {

            tva = Float.parseFloat(tmpachat[5].toString());

            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[OOOOOOOOOOOOOO] valeur tva " + tva + "   Object " + tmpachat[5].toString());

        }

        catch (NumberFormatException nn) {

            nn.printStackTrace();

        }

        return tva;

    }


    public void dbInsert() {


    }


    public void dbDelete() {


    }


    public void dbUpdate() {

        //    if(parentB!=null){

        // grpDecision = new Grpdecision_T();

        //   grpDecision.setGncleunik(grpDecisionCleUnik);

        //}


        grpDecision.setModifyreccord(true);

        /////////////////////////////

        // ACHAT

        /////////////////////////////

        //-> code de tva


        grpDecision.setGncodetvaachat(grp_LSelect_codeTvaAchat.getCleUnik());


        grpDecision.setValeurGenFloat2(getTvaPrct(grp_LSelect_codeTvaAchat.getCleUnik(), achat));

        //-> commision


        grpDecision.setGnpccomachat(Float.parseFloat(grp_TField_commissionAchat.getText()));

        //-> tva incluse


        int selected = (grp_CBox_prixAchatTvaInclus.isSelected()) ? 1 : 0;


        grpDecision.setGntvainclusachat(selected);

        //-> compte d'achat


        grpDecision.setGncompteachat(grp_LSelect_compteAchat.getCleUnik());

        //  grpDecision.setIntitulecomptea(Integer.parseInt(grp_LSelect_compteAchat.getText()));

        // grpDecision.setGncomp

        /////////////////////////////

        // VENTE

        /////////////////////////////

        //-> code de tva


        grpDecision.setGncodetvavente(grp_LSelect_codeTvaVente.getCleUnik());

        //   grpDecision.setValeurGenFloat1(Float.parseFloat(grp_LSelect_codeTvaVente.getText()));


        grpDecision.setValeurGenFloat1(getTvaPrct(grp_LSelect_codeTvaVente.getCleUnik(), vente));//SpecialTva.fromStringToFloat(grp_LSelect_codeTvaVente.getText()));

        /*  try{















             grpDecision.setValeurGenFloat1(Float.parseFloat(grp_LSelect_codeTvaVente.getText()));















        }catch(NumberFormatException nn){















           String tmp=grp_LSelect_codeTvaVente.getText();















           float valeur=0;















           if(tmp.equals("6/21"))















               valeur=1.26f;















           else if(tmp.equals("8/21"))















                valeur=1.68f;















           else if(tmp.equals("13/21"))















                valeur=2.73f;















           else if(tmp.equals("18/21"))















                valeur=3.78f;















           System.out.println("[********]valeur:"+valeur);















           grpDecision.setValeurGenFloat1(valeur);















        }*/

        //-> inclure la tva


        selected = (grp_CBox_prixVenteTvaInclus.isSelected()) ? 1 : 0;


        grpDecision.setGntvainclusvente(selected);


        selected = (grp_CBox_tvaAComptabliserVente.isSelected()) ? 1 : 0;


        grpDecision.setGnfrtvaComptabiliserVente(selected);


        selected = (grp_CBox_CommissionTVAVente.isSelected()) ? 1 : 0;


        grpDecision.setGnfrtvaCommissionVente(selected);

        //-> nbre de jour avant la date d'échéance


        grpDecision.setGnnbravanteche(Integer.parseInt(grp_TField_nbrJourAvantEcheance.getText()));

        //-> % accompte minimal par personne


        grpDecision.setGnacompteminpp(Float.parseFloat(grp_TField_pcAccompteMinPPers.getText()));

        //-> commission à la vente


        grpDecision.setGnpccomvente(Float.parseFloat(grp_TField_commissionVente.getText()));

        //-> inclure la commission à la vente


        selected = (grp_CBox_commissionInclusVente.isSelected()) ? 1 : 0;


        grpDecision.setGncominclpvent(selected);

        //-> % accompte vente


        grpDecision.setGnpcacompte(Float.parseFloat(grp_TField_pcAccompteVente.getText()));

        //-> compte de vente


        grpDecision.setGncomptevente(grp_LSelect_compteVente.getCleUnik());

        // grpDecision.setIntitulecomptev(Integer.parseInt(grp_LSelect_compteVente.getText()));

        /////////////////////////////

        // REPARTITION

        /////////////////////////////

        //-> createur


        grpDecision.setGnpcclerepconcept(Float.parseFloat(grp_TField_pcRepartitionBeneficeCreateur.getText()));

        //-> maison mère


        grpDecision.setGnpcclerepmmere(Float.parseFloat(grp_TField_pcRepartitionBeneficeMMère.getText()));

        //-> vendeur


        grpDecision.setGnpcclerepvend(Float.parseFloat(grp_TField_pcRepartitionBeneficeVendeur.getText()));

        /////////////////////////////

        // DIVERS

        /////////////////////////////

        //-> gestion de stock de billets IATA


        selected = (grp_CBox_gestionStockBilletIATA.isSelected()) ? 1 : 0;


        grpDecision.setGnprodstockiata(selected);

        //-> autre stock


        selected = (grp_CBox_gestionAutreStock.isSelected()) ? 1 : 0;


        grpDecision.setGnprodstockautre(selected);


        selected = (grp_CBox_tvaAComptabliserVente.isSelected()) ? 1 : 0;


        grpDecision.setGnfrtvaComptabiliserVente(selected);


        selected = (grp_CBox_CommissionTVAVente.isSelected()) ? 1 : 0;


        grpDecision.setGnfrtvaCommissionVente(selected);

        /////////////////////////////

        // FRANCHISE

        /////////////////////////////


        if (grp_CBox_utiliseFranchise.isSelected()) {

            //-> utilise la franchise


            selected = (grp_CBox_utiliseFranchise.isSelected()) ? 1 : 0;


            grpDecision.setGnfranchise(selected);

            //-> % accompte de franchise


            grpDecision.setGnfracomptepc(Float.parseFloat(grp_TField_pcAccompteFranchise.getText()));

            //-> nbre de jour pour l'accompte


            grpDecision.setGnfrnbda(Integer.parseInt(grp_TField_nbreJourFranchise.getText()));

            //-> solde


            grpDecision.setGnfrsoldepc(Float.parseFloat(grp_TField_soldeFranchise.getText()));

            //-> nbre de jour pour solde


            grpDecision.setGnfrnbds(Integer.parseInt(grp_TField_nbreDeJourFranchiseSolde.getText()));


        }


        if (parentB != null)


        {


            try {


                serveur.renvGrpDecRmiObject(currentUser.getUrcleunik()).modifyFourn(grpDecision, 0, 0, currentUser.getUrcleunik());


                displayReadMode();

                // if (erreur.getErreurcode() == 1062) {

                //   javax.swing.JOptionPane.showMessageDialog(this, "La référence dialogue existe déjà");

                //}

                // else {

                // displayReadMode();

                //}


            }


            catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, currentUser);


            }


            catch (java.rmi.RemoteException re) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


            }


            catch (Exception e) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);


            }


        } else {

            // System.out.println("[***********INSERTION groupe_decision pour une ligne de base]");


            this.produit.setGroupdec(grpDecision);

            this.produit.getGroupdec().setModifyreccord(true);

            if (!produit.isIsnewreccord())

                this.produit.setModify(true);

            this.supreducPanel.reloadTableModel();

            parent.closeModule();

        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void displayReadMode() {

        // toolbar


        parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_MODIFY,

                //actionToolBar.DO_PREVIOUS,

                //actionToolBar.DO_NEXT,


                (parentB != null) ? actionToolBar.DO_CANCEL : actionToolBar.DO_CLOSE});


        action = ActionToolBar.ACT_READ;


        if (parent != null) {


            parent.enabledTabbedPane(false);


        }

        // composants


        init();


        for (int i = 0; i < composantToVerif.length; i++) {


            composantToVerif[i].setEnabled(false);


            composantToVerif[i].setLastFocusedComponent(false);


        }

        // dp.setEnabledGrp_ACB_modele(false);


        setEnabledFranchise(false);


        dbSelect();


    }


    public void displayInsertMode() {

        // toolbar


        parent.setCurrentActionEnabled(new int[0]);


        action = ActionToolBar.ACT_INSERT;

        // composants


        init();


        for (int i = 0; i < composantToVerif.length; i++) {


            composantToVerif[i].setEnabled(false);


            composantToVerif[i].setText("");


        }


        grp_CBox_commissionInclusVente.setEnabled(true);


        grp_CBox_gestionStockBilletIATA.setEnabled(true);


        grp_CBox_prixAchatTvaInclus.setEnabled(true);


        grp_CBox_prixVenteTvaInclus.setEnabled(true);


        grp_LSelect_codeTvaAchat.setEnabled(true);


        grp_LSelect_codeTvaAchat.requestFocus();


    }


    public void displayUpdateMode() {


        dbSelectForUpdate();


        if (!checkValidity)


        {


            displayReadMode();


        } else {

            // toolbar


            parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_PREVIOUS,


                    actionToolBar.DO_CANCEL});


            action = ActionToolBar.ACT_MODIFY;


            parent.enabledTabbedPane(false);

            // composants


            init();


            for (int i = 0; i < composantToVerif.length; i++) {


                composantToVerif[i].setEnabled(true);


                composantToVerif[i].setLastFocusedComponent(true);


            }

            //   dp.setEnabledGrp_ACB_modele(true);


            setEnabledFranchise(false);


            grp_LSelect_codeTvaAchat.requestFocus();


        }


    }


    public void displayDisableMode() {

        // toolbar


        parent.setCurrentActionEnabled(new int[0]);


        action = -1;

        // composants


        init();


        for (int i = 0; i < composantToVerif.length; i++) {


            composantToVerif[i].setEnabled(false);


        }


        grp_CBox_commissionInclusVente.setEnabled(false);


        grp_CBox_gestionAutreStock.setEnabled(false);


        grp_CBox_gestionStockBilletIATA.setEnabled(false);


        grp_CBox_prixAchatTvaInclus.setEnabled(false);


        grp_CBox_prixVenteTvaInclus.setEnabled(false);


    }


    public void updateAllFields() {

        /////////////////////////////

        // ACHAT

        /////////////////////////////

        //-> code de tva

        //  grp_LSelect_codeTvaAchat.setText("" + grpDecision.getValeurGenFloat2());


        grp_LSelect_codeTvaAchat.setCleUnik(grpDecision.getGncodetvaachat());

        //-> % de commission


        grp_TField_commissionAchat.setText("" + grpDecision.getGnpccomachat());

        //-> inclure la tva


        int cb = grpDecision.getGntvainclusachat();


        switch (cb) {


            case 0:


                grp_CBox_prixAchatTvaInclus.setSelected(false);


                break;


            case 1:


                grp_CBox_prixAchatTvaInclus.setSelected(true);


                break;


        }

        //-> compte d'achat


        grp_LSelect_compteAchat.setCleUnik(grpDecision.getGncompteachat());

        // grp_LSelect_compteAchat.setText("" + grpDecision.getIntitulecomptea());


        grp_LSelect_compteVente.setCleUnik(grpDecision.getGncomptevente());

        // grp_LSelect_compteVente.setText("" + grpDecision.getIntitulecomptev());

        //////////////////////////////

        // VENTE

        /////////////////////////////

        //-> code de tva

        // grp_LSelect_codeTvaVente.setText("" + grpDecision.getValeurGenFloat1());


        grp_LSelect_codeTvaVente.setCleUnik(grpDecision.getGncodetvavente());

        //-> inclure la tva


        cb = grpDecision.getGntvainclusvente();


        switch (cb) {


            case 0:


                grp_CBox_prixVenteTvaInclus.setSelected(false);


                break;


            case 1:


                grp_CBox_prixVenteTvaInclus.setSelected(true);


                break;


        }


        grp_CBox_tvaAComptabliserVente.setSelected(grpDecision.getGnfrtvaComptabiliserVente() == 1);


        grp_CBox_CommissionTVAVente.setSelected(grpDecision.getGnfrtvaCommissionVente() == 1);

        //-> nbre de jour avant la date d'échéance


        grp_TField_nbrJourAvantEcheance.setText("" + grpDecision.getGnnbravanteche());

        //-> % d'accompte minimal par personne


        grp_TField_pcAccompteMinPPers.setText("" + grpDecision.getGnacompteminpp());

        //-> % de commision à la vente


        grp_TField_commissionVente.setText("" + grpDecision.getGnpccomvente());

        //-> inclure la commission


        cb = grpDecision.getGncominclpvent();


        switch (cb) {


            case 0:


                grp_CBox_commissionInclusVente.setSelected(false);


                break;


            case 1:


                grp_CBox_commissionInclusVente.setSelected(true);


                break;


        }

        //-> % d'accompte


        grp_TField_pcAccompteVente.setText("" + grpDecision.getGnpcacompte());

        //-> compte de vente

        // grp_LSelect_compteVente.setText("" + grpDecision.getGncomptevente());

        //////////////////////////////

        // REPARTITION

        /////////////////////////////

        //-> répartition du bénéfice pour le créateur


        grp_TField_pcRepartitionBeneficeCreateur.setText("" + grpDecision.getGnpcclerepconcept());

        //-> répartition du bénéfice pour la maison mère


        grp_TField_pcRepartitionBeneficeMMère.setText("" + grpDecision.getGnpcclerepmmere());

        //-> répartition du bénéfice pour le vendeur


        grp_TField_pcRepartitionBeneficeVendeur.setText("" + grpDecision.getGnpcclerepvend());

        //////////////////////////////

        // DIVERS

        /////////////////////////////

        //-> gestion de stock de billet IATA


        cb = grpDecision.getGnprodstockiata();


        switch (cb) {


            case 0:


                grp_CBox_gestionStockBilletIATA.setSelected(false);


                break;


            case 1:


                grp_CBox_gestionStockBilletIATA.setSelected(true);


                break;


        }

        //-> gestion de stock d'autre billet


        cb = grpDecision.getGnprodstockautre();


        switch (cb) {


            case 0:


                grp_CBox_gestionAutreStock.setSelected(false);


                break;


            case 1:


                grp_CBox_gestionAutreStock.setSelected(true);


                break;


        }

        //////////////////////////////

        // FRANCHISE

        /////////////////////////////

        //-> choix de la franchise


        cb = grpDecision.getGnfranchise();


        switch (cb) {


            case 0:


                grp_CBox_utiliseFranchise.setSelected(false);


                break;


            case 1:


                grp_CBox_utiliseFranchise.setSelected(true);


                break;


        }

        //-> % d'accompte de franchise


        grp_TField_pcAccompteFranchise.setText("" + grpDecision.getGnfracomptepc());

        //-> nbre de jour accompte


        grp_TField_nbreJourFranchise.setText("" + grpDecision.getGnfrnbda());

        //-> % de solde


        grp_TField_soldeFranchise.setText("" + grpDecision.getGnfrsoldepc());

        //-> nbre de jour solde


        grp_TField_nbreDeJourFranchiseSolde.setText("" + grpDecision.getGnfrnbds());


    }


    public void updateAllFields(Object donnee) {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void doCancel() {


        System.out.println("THIS IS A Do CANCEL");


        switch (action) {


            case ActionToolBar.ACT_READ:


                if (parentB != null)


                    parentB.removeAll();


                break;


            case ActionToolBar.ACT_MODIFY:


                displayReadMode();


                break;


        }


    }


    public void doNext() {


    }


    public void doPrevious() {


        switch (action) {


            case ActionToolBar.ACT_INSERT:


                dbInsert();


                break;


            case ActionToolBar.ACT_MODIFY:


                dbUpdate();


                break;


        }


    }


    public void doDelete() {


    }


    public void doCreate() {


    }


    public void doHelp() {


    }


    public void doClose() {


        if (parentB == null)


            parent.closeModule();


    }


    public void doModify() {


        displayUpdateMode();


    }


    public void doAccept() {


    }


    public void doPrint() {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Champs de la classe

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private astrainterface serveur;


    private Loginusers_T currentUser;


    private MainScreenModule parent;


    private int frCleUnik;


    private Grpdecision_T grpDecision;


    private Gestionerreur_T erreur;


    private javax.swing.JPanel parentB;


    private int action;


    private ActionToolBar actionToolBar;


    private AstraComponent[] composantToVerif;


    /**
     * pour 1 seul initiation du panneau
     */


    public boolean initOnce = false;


    private int grpDecisionCleUnik;


    private DecisionPane dp;


    private Double createur = new Double(0);


    private Double meMere = new Double(0);


    private Double vendeur = new Double(0);


    private ToolBarInteraction tb_interaction;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// STATIC VARIABLES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static int PANE_NUMBER = 2;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Graphic Component

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Variables declaration - do not modify


    private boolean checkValidity;


    private javax.swing.JPanel jPanel1;


    private javax.swing.JPanel jPanel4;


    private javax.swing.JPanel jPanel3;


    private javax.swing.JPanel jPanel7;


    private javax.swing.JLabel grp_Label_codeTvaAchat;


    private javax.swing.JLabel grp_Label_commissionAchat;


    private javax.swing.JLabel grp_Label_prixAchatTvaInclus;


    private javax.swing.JLabel grp_Label_pc02;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_commissionAchat;


    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_codeTvaAchat;


    private javax.swing.JLabel grp_Label_compteAchat;


    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_compteAchat;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_prixAchatTvaInclus;


    private javax.swing.JPanel jPanel2;


    private javax.swing.JPanel jPanel8;


    private javax.swing.JLabel grp_Label_codeTvaVente;


    private javax.swing.JLabel grp_Label_nbrJourAvantEcheance;


    private javax.swing.JLabel grp_Label_pcAccompteMinPPers;


    private javax.swing.JLabel grp_Label_commissionVente;


    private javax.swing.JLabel grp_Label_pcAccompteVente;


    private javax.swing.JLabel grp_Label_prixVenteTvaInclus;


    private javax.swing.JLabel grp_Label_pc05;


    private javax.swing.JLabel grp_Label_pc06;


    private javax.swing.JLabel grp_Label_pc07;


    private javax.swing.JLabel grp_Label_commissionInclusVente;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbrJourAvantEcheance;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcAccompteMinPPers;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_commissionVente;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcAccompteVente;


    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_codeTvaVente;


    private javax.swing.JLabel grp_Label_compteVente;


    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_compteVente;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_prixVenteTvaInclus;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_commissionInclusVente;


    private javax.swing.JPanel jPanel5;


    private javax.swing.JPanel jPanel11;


    private javax.swing.JPanel jPanel12;


    private javax.swing.JLabel grp_Label_pcRepartitionBeneficeCreateur;


    private javax.swing.JLabel grp_Label_pcRepartitionBeneficeMMère;


    private javax.swing.JLabel grp_Label_pcRepartitionBeneficeVendeur;


    private javax.swing.JLabel grp_Label_pc09;


    private javax.swing.JLabel grp_Label_pc10;


    private javax.swing.JLabel grp_Label_pc11;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcRepartitionBeneficeCreateur;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcRepartitionBeneficeMMère;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcRepartitionBeneficeVendeur;


    private javax.swing.JPanel jPanel6;


    private javax.swing.JPanel jPanel9;


    private javax.swing.JLabel grp_Label_gestionStockBilletIATA;


    private javax.swing.JLabel grp_Label_gestionAutreStock;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_gestionStockBilletIATA;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_gestionAutreStock;


    private javax.swing.JPanel jPanel13;


    private javax.swing.JPanel jPanel14;


    private javax.swing.JLabel grp_Label_pcAccompteFranchise;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pcAccompteFranchise;


    private javax.swing.JLabel grp_Label_nbreJourFranchise;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbreJourFranchise;


    private javax.swing.JLabel grp_Label_soldeFranchise;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_soldeFranchise;


    private javax.swing.JLabel jLabel5;


    private javax.swing.JLabel jLabel6;


    private javax.swing.JLabel grp_Label_utiliseFranchise;


    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_utiliseFranchise;


    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbreDeJourFranchiseSolde;


    private javax.swing.JLabel grp_Label_nbreDeJourFranchiseSolde;


    private srcastra.astra.sys.classetransfert.dossier.InterfaceProduit produit;


    private JPanel jPanel50;


    private JPanel jPanel51;


    private JLabel jLabel50;


    private JLabel jLabel51;


    private JLabel jLabel52;


    private JLabel jLabel53;


    private ACheckBox grp_CBox_tvaAComptabliserVente;


    private ACheckBox grp_CBox_CommissionTVAVente;

    // End of variables declaration

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => CLASSES INTERNES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public class RepartitionListener implements javax.swing.event.DocumentListener {


        public void removeUpdate(javax.swing.event.DocumentEvent e) {


            calculateValue(e);


        }


        public void insertUpdate(javax.swing.event.DocumentEvent e) {


            calculateValue(e);


        }


        public void changedUpdate(javax.swing.event.DocumentEvent documentEvent) {


        }


        private void calculateValue(javax.swing.event.DocumentEvent e) {


            javax.swing.text.Document whatsUp = e.getDocument();


            int field = 0;


            if (whatsUp.getProperty("name").equals("createur")) {


                createur = ((DecimalMask) grp_TField_pcRepartitionBeneficeCreateur.getDocument()).getValue();


                field = 1;


            } else if (whatsUp.getProperty("name").equals("meMere")) {


                meMere = ((DecimalMask) grp_TField_pcRepartitionBeneficeMMère.getDocument()).getValue();


                field = 2;


            } else if (whatsUp.getProperty("name").equals("vendeur")) {


                vendeur = ((DecimalMask) grp_TField_pcRepartitionBeneficeVendeur.getDocument()).getValue();


                field = 3;


            }


        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// BEANS PROPERTIES GET/SET SUPPORT

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getTitle() {


        return "Groupe de décision";


    }


    public void setFrCleunik(int frCleUnik) {


        this.frCleUnik = frCleUnik;


    }


    public int getAction() {


        return action;


    }


    public void setAction(int action) {


        this.action = action;


    }


    /**
     * Spécifie le composant qui implémente cette fonction comme
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * le composant qui pilote l'actionToolBar
     */


    public void setThisAsToolBarComponent() {

        //actionToolBar.setTbComposer(this);


    }


    public int[] getDefaultActionToolBarMask() {


        return new int[0];


    }


    /**
     * Getter for property serveur.
     *
     * @return Value of property serveur.
     */


    public srcastra.astra.sys.rmi.astrainterface getServeur() {


        return serveur;


    }


    /**
     * Setter for property serveur.
     *
     * @param serveur New value of property serveur.
     */


    public void setServeur(srcastra.astra.sys.rmi.astrainterface serveur) {


        this.serveur = serveur;


    }


    /**
     * Getter for property currentUser.
     *
     * @return Value of property currentUser.
     */


    public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser() {


        return currentUser;


    }


    /**
     * Setter for property currentUser.
     *
     * @param currentUser New value of property currentUser.
     */


    public void setCurrentUser(srcastra.astra.sys.classetransfert.Loginusers_T currentUser) {


        this.currentUser = currentUser;


    }


    /**
     * Getter for property parent.
     *
     * @return Value of property parent.
     */


    public srcastra.astra.gui.modules.MainScreenModule getMainScreen() {


        return parent;


    }


    /**
     * Setter for property parent.
     *
     * @param parent New value of property parent.
     */


    public void setMainScreen(srcastra.astra.gui.modules.MainScreenModule parent) {


        this.parent = parent;


    }


    public void doSwitch() {


    }


    public void goUp() {


    }


    public java.awt.Component m_getGeneriqueTable() {


        return null;


    }


    public void doF10() {


    }


    public void addKeystroque() {


    }


    public void doF7() {


    }


} 















































 