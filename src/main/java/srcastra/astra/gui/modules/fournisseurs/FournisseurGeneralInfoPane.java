/*
* FournisseurGeneralInfoPane.java
*
* Created on 6 mars 2002, 17:33
*/
package srcastra.astra.gui.modules.fournisseurs;

import srcastra.astra.gui.components.combobox.liste.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.configuration.AbstractRequete;
import srcastra.astra.sys.rmi.FournisseurRmiInterface;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.gui.modules.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.components.AstraComponent;
import srcastra.astra.gui.MainFrame;

import javax.swing.*;

import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.*;

import java.awt.event.ComponentListener;

import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import srcastra.astra.gui.event.ValidateField;
import srcastra.astra.gui.sys.listModel.dossierListModel.TypeTvaListModel;
import srcastra.astra.gui.components.combobox.liste.*;

import javax.swing.text.*;
import java.text.*;

import srcastra.astra.gui.modules.Mailing.*;

/**
 * @author Sébastien
 */
public class FournisseurGeneralInfoPane extends javax.swing.JPanel implements InternScreenModule,
        ToolBarComposer,
        java.awt.event.ComponentListener, MailInterface {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form FournisseurGeneralInfoPane
     *
     * @param serveur       Serveur rmi : implémentation de astraImplement
     * @param currentUser   utilisateur courrant
     * @param parent        composant principal du module
     * @param actionToolBar actionToolBar
     */
    public FournisseurGeneralInfoPane(srcastra.astra.sys.rmi.astrainterface serveur,
                                      srcastra.astra.sys.classetransfert.Loginusers_T currentUser,
                                      MainScreenModule parent, ActionToolBar actionToolBar) {
        this.serveur = serveur;
        this.currentUser = currentUser;
        this.parent = parent;
        this.actionToolBar = actionToolBar;
        this.action = actionToolBar.ACT_READ;
        this.addComponentListener(this);
        fournisseurInterface = ((MainFrame) parent.getSuperOwner()).getFournisseurRmi();
        init();
        initListe();
        mailingFrame = new ManageMailingFrame(grp_TField_Banque1, this, parent.getSuperOwner(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());
    }

    public void initListe() {
// m_model=new  SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(),m_productId,parent,srcastra.astra.sys.configuration.AbstractRequete.SUP_RECUC);
//    m_model.loadata();
        grp_LSelect_NCompte.setServeur(serveur);
        grp_LSelect_NCompte.setLogin(currentUser);
        grp_LSelect_NCompte.setModel(new NCompteListeTableModel(serveur, currentUser));
        grp_LSelect_NCompte.init2();
        grp_LSelect_cp.setServeur(parent.getServeur());
        grp_LSelect_cp.setLogin(parent.getCurrentUser());
        grp_LSelect_cp.setModel(new CpListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_cp.grp_Comp_nextStringComp = grp_TField_Localite;
        grp_LSelect_cp.init2();
        grp_LSelect_Pays.setServeur(parent.getServeur());
        grp_LSelect_Pays.setLogin(parent.getCurrentUser());
        grp_LSelect_Pays.setModel(new PaysListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Pays.init2();
        grp_LSelect_Lang.setServeur(parent.getServeur());
        grp_LSelect_Lang.setLogin(parent.getCurrentUser());
        grp_LSelect_Lang.setModel(new LangueListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Lang.init2();
        grp_ACB_TVAtype.setServeur(parent.getServeur());
        grp_ACB_TVAtype.setLogin(parent.getCurrentUser());
        grp_ACB_TVAtype.setModel(new SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), 1, parent, AbstractRequete.TVA_TYPE, ((MainFrame) parent.getSuperOwner()).getTypeTvaVector()));
        grp_ACB_TVAtype.init2();
        grp_ACB_TVAtype.setColToSearch(3);
        grp_LSelect_tvaRegime.setServeur(parent.getServeur());
        grp_LSelect_tvaRegime.setLogin(parent.getCurrentUser());
        grp_LSelect_tvaRegime.setModel(new SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), 1, parent, AbstractRequete.TVA_REGIME, ((MainFrame) parent.getSuperOwner()).getRegimeTvaVector()));
        grp_LSelect_tvaRegime.init2();
        grp_LSelect_tvaRegime.setColToSearch(3);
        grp_LSelect_Devise.setServeur(parent.getServeur());
        grp_LSelect_Devise.setLogin(parent.getCurrentUser());
        grp_LSelect_Devise.setModel(new DeviseListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Devise.init2();
        grp_CBox_fournProduit.setServeur(parent.getServeur());
        grp_CBox_fournProduit.setLogin(parent.getCurrentUser());
        grp_CBox_fournProduit.setModel(new TypeProduitFourn(parent.getServeur(), parent.getCurrentUser()));
        grp_CBox_fournProduit.init2();


    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INITIALISATION
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialise les champs graphiques ainsi qu'un tableau pour permettre d'interargir
     * sur tous les éléments à partir d'une boucle.
     * Les éléments graphiques et le tableau ne sera initialisé qu' 1 fois
     */
    private void init() {
        if (!initOnce) {
// chargement des composants et de leurs propriétes
            initComponents();

            grp_ACB_TVAtype.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent event) {

// if(action==ActionToolBar.ACT_INSERT)
                    setTvaNumDocumentMask();
                }
            });

            setDocumentMask();
            setTvaNumDocumentMask();
// chargement d'un tableau reprenant tous les éléments pour une correction
            this.composantToVerif = new AstraComponent[]{
                grp_TField_ref1, grp_TField_nom1, grp_TField_adresse, grp_LSelect_Pays,
                grp_LSelect_cp, /*grp_TField_Localite,*/ grp_TField_ref2, grp_TField_nom2,
                grp_LSelect_Lang, grp_TField_email, grp_TField_phone, grp_TField_fax, grp_LSelect_NCompte,
                grp_TField_TVAnum, grp_ACB_TVAtype, grp_LSelect_tvaRegime, grp_CheckB_carteCredit,
                grp_Cbos_domiciliation, grp_LSelect_Devise, grp_TField_PaybyDay,
                grp_TField_Banque1, grp_TField_Banque2, grp_TField_Banque3, grp_CBox_fournProduit, grp_TField_web, grp_CBox_restore};

            this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);
            tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_STANDART);

//-> Régistration des listeners pour la validité des composants
            for (int i = 0; i < composantToVerif.length; i++) {
                composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
            }



// les éléments ne sont intialisé qu'1 seule fois
            initOnce = true;

            grp_CBox_fournProduit.addActionListener(valideAndPrevious);
        }

    }

    private void setFormat(DefaultFormatter format, Object tmp) {
        if (format != null) {
            DefaultFormatter fmt = format;
            if (tmp == null)
                tmp = "000.000.000";
            grp_TField_TVAnum.setValue(tmp);
            fmt.setValueClass(grp_TField_TVAnum.getValue().getClass());
            DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);
            grp_TField_TVAnum.setFormatterFactory(fmtFactory);

        } else
            grp_TField_TVAnum.setFormatterFactory(null);
//grp_TField_HeureArrivee.setFormatterFactory(fmtFactory);
    }

    private void setDocumentMask() {
        grp_TField_ref1.setDocument(new DefaultMask(1, 10, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_nom1.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_web.setDocument(new DefaultMask(0, 150, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_adresse.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_ref2.setDocument(new DefaultMask(0, 10, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_nom2.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));
        grp_TField_email.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_phone.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_fax.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
// grp_TField_Domiciliation.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_PaybyDay.setDocument(new IntegerMask(0, 4, currentUser.getLangage()));
        grp_TField_Banque1.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Banque2.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Banque3.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
//  grp_TField_TVAnum.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
    }

    private void setTvaNumDocumentMask() {
        if (grp_ACB_TVAtype.getText().equals("BE")) {
            DefaultFormatter fmt = null;
            Object tmp = grp_TField_TVAnum.getValue();
            try {
                fmt = new MaskFormatter("###.###.###");
            } catch (ParseException pn) {
                pn.printStackTrace();
            }
            setFormat(fmt, tmp);
        } else
            setFormat(null, null);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel23 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grp_TField_nom1 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_adresse = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Localite = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_ref1 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_LSelect_cp = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Pays = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel25 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        grp_TField_ref2 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nom2 = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel26 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        grp_LSelect_Lang = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel28 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        grp_TField_email = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_phone = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_fax = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        grp_TField_PaybyDay = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Banque1 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Banque2 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Banque3 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_CheckB_carteCredit = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel3 = new javax.swing.JLabel();
        grp_LSelect_NCompte = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_ACB_TVAtype = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_tvaRegime = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Devise = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        grp_TField_web = new srcastra.astra.gui.components.textFields.ATextField();
        grp_CBox_restore = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_TField_TVAnum = new srcastra.astra.gui.components.textFields.FormatedTextField.FormatedTextField();
        grp_Cbos_domiciliation = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        grp_CBox_fournProduit = new srcastra.astra.gui.components.combobox.liste.Liste();

        setLayout(new java.awt.GridLayout(1, 2));

        setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GeneralPane_titrePane")));
        setPreferredSize(new java.awt.Dimension(90, 13));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setEnabled(false);
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel1.setEnabled(false);
        jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel27.setBorder(new javax.swing.border.EtchedBorder());
        jPanel27.setPreferredSize(new java.awt.Dimension(350, 151));
        jPanel27.setEnabled(false);
        jPanel29.setLayout(new java.awt.GridBagLayout());

        jPanel29.setMinimumSize(new java.awt.Dimension(323, 141));
        jPanel29.setPreferredSize(new java.awt.Dimension(323, 141));
        jPanel29.setEnabled(false);
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_ref1"));
        jLabel25.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel25.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel25, gridBagConstraints);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_nom1"));
        jLabel26.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel26.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel26, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_adress"));
        jLabel27.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel27.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel27, gridBagConstraints);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_cp"));
        jLabel28.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel28.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel28, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_pays"));
        jLabel29.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel29.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel29, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_localite"));
        jLabel2.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel29.add(jLabel2, gridBagConstraints);

        grp_TField_nom1.setEnabled(false);
        grp_TField_nom1.setGrp_Comp_nextComponent(grp_TField_adresse);
        grp_TField_nom1.setGrp_Comp_previousComponent(grp_TField_ref1);
        grp_TField_nom1.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_nom1.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_nom1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nom1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_TField_nom1, gridBagConstraints);

        grp_TField_adresse.setEnabled(false);
        grp_TField_adresse.setGrp_Comp_nextComponent(grp_LSelect_cp);
        grp_TField_adresse.setGrp_Comp_previousComponent(grp_TField_nom1);
        grp_TField_adresse.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_adresse.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_adresse.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_adresse.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_TField_adresse, gridBagConstraints);

        grp_TField_Localite.setEnabled(false);
        grp_TField_Localite.setMinimumSize(new java.awt.Dimension(170, 18));
        grp_TField_Localite.setPreferredSize(new java.awt.Dimension(170, 18));
        grp_TField_Localite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Localite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_TField_Localite, gridBagConstraints);

        grp_TField_ref1.setEnabled(false);
        grp_TField_ref1.setGrp_Comp_nextComponent(grp_TField_nom1);
        grp_TField_ref1.setMinimumSize(new java.awt.Dimension(105, 18));
        grp_TField_ref1.setPreferredSize(new java.awt.Dimension(105, 18));
        grp_TField_ref1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_ref1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_TField_ref1, gridBagConstraints);

        grp_LSelect_cp.setEnabled(false);
        grp_LSelect_cp.setGrp_Comp_nextComponent(grp_LSelect_Pays);
        grp_LSelect_cp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_cp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_LSelect_cp, gridBagConstraints);

        grp_LSelect_Pays.setEnabled(false);
        grp_LSelect_Pays.setGrp_Comp_nextComponent(grp_TField_ref2);
        grp_LSelect_Pays.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Pays.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel29.add(grp_LSelect_Pays, gridBagConstraints);

        jPanel27.add(jPanel29);

        jPanel1.add(jPanel27);

        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel25.setBorder(new javax.swing.border.EtchedBorder());
        jPanel25.setMinimumSize(new java.awt.Dimension(350, 60));
        jPanel25.setPreferredSize(new java.awt.Dimension(350, 60));
        jPanel25.setEnabled(false);
        jPanel30.setLayout(new java.awt.GridBagLayout());

        jPanel30.setMinimumSize(new java.awt.Dimension(323, 48));
        jPanel30.setPreferredSize(new java.awt.Dimension(323, 48));
        jPanel30.setEnabled(false);
        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel30.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_ref2"));
        jLabel30.setMinimumSize(new java.awt.Dimension(90, 13));
        jLabel30.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel30.add(jLabel30, gridBagConstraints);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel31.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_nom2"));
        jLabel31.setMinimumSize(new java.awt.Dimension(90, 13));
        jLabel31.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel30.add(jLabel31, gridBagConstraints);

        grp_TField_ref2.setEnabled(false);
        grp_TField_ref2.setGrp_Comp_nextComponent(grp_TField_nom2);
        grp_TField_ref2.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_ref2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_ref2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel30.add(grp_TField_ref2, gridBagConstraints);

        grp_TField_nom2.setEnabled(false);
        grp_TField_nom2.setGrp_Comp_nextComponent(grp_LSelect_Lang);
        grp_TField_nom2.setGrp_Comp_previousComponent(grp_TField_ref2);
        grp_TField_nom2.setMinimumSize(new java.awt.Dimension(230, 21));
        grp_TField_nom2.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_nom2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nom2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel30.add(grp_TField_nom2, gridBagConstraints);

        jPanel25.add(jPanel30);

        jPanel1.add(jPanel25);

        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel26.setBorder(new javax.swing.border.EtchedBorder());
        jPanel26.setPreferredSize(new java.awt.Dimension(350, 40));
        jPanel26.setEnabled(false);
        jPanel31.setLayout(new java.awt.GridBagLayout());

        jPanel31.setMinimumSize(new java.awt.Dimension(250, 27));
        jPanel31.setPreferredSize(new java.awt.Dimension(250, 27));
        jPanel31.setEnabled(false);
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_lang"));
        jLabel32.setMaximumSize(new java.awt.Dimension(90, 14));
        jLabel32.setMinimumSize(new java.awt.Dimension(90, 14));
        jLabel32.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel31.add(jLabel32, gridBagConstraints);

        grp_LSelect_Lang.setEnabled(false);
        grp_LSelect_Lang.setGrp_Comp_nextComponent(grp_TField_email);
        grp_LSelect_Lang.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Lang.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel31.add(grp_LSelect_Lang, new java.awt.GridBagConstraints());

        jPanel26.add(jPanel31);

        jPanel1.add(jPanel26);

        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel28.setBorder(new javax.swing.border.EtchedBorder());
        jPanel28.setPreferredSize(new java.awt.Dimension(350, 77));
        jPanel28.setEnabled(false);
        jPanel32.setLayout(new java.awt.GridBagLayout());

        jPanel32.setMinimumSize(new java.awt.Dimension(323, 63));
        jPanel32.setPreferredSize(new java.awt.Dimension(323, 63));
        jPanel32.setEnabled(false);
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel33.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_mail"));
        jLabel33.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel32.add(jLabel33, gridBagConstraints);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel34.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_phone1"));
        jLabel34.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel32.add(jLabel34, gridBagConstraints);

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel35.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_fax"));
        jLabel35.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel32.add(jLabel35, gridBagConstraints);

        grp_TField_email.setEnabled(false);
        grp_TField_email.setGrp_Comp_nextComponent(grp_TField_phone);
        grp_TField_email.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_email.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_email.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_email.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel32.add(grp_TField_email, gridBagConstraints);

        grp_TField_phone.setEnabled(false);
        grp_TField_phone.setGrp_Comp_nextComponent(grp_TField_fax);
        grp_TField_phone.setGrp_Comp_previousComponent(grp_TField_email);
        grp_TField_phone.setMinimumSize(new java.awt.Dimension(150, 18));
        grp_TField_phone.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_phone.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_phone.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel32.add(grp_TField_phone, gridBagConstraints);

        grp_TField_fax.setEnabled(false);
        grp_TField_fax.setGrp_Comp_nextComponent(grp_LSelect_NCompte);
        grp_TField_fax.setGrp_Comp_previousComponent(grp_TField_phone);
        grp_TField_fax.setMinimumSize(new java.awt.Dimension(150, 18));
        grp_TField_fax.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_fax.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_fax.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel32.add(grp_TField_fax, gridBagConstraints);

        jPanel28.add(jPanel32);

        jPanel1.add(jPanel28);

        jPanel23.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel23);

        jPanel24.setLayout(new java.awt.BorderLayout());

        jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel33.setBorder(new javax.swing.border.EtchedBorder());
        jPanel33.setPreferredSize(new java.awt.Dimension(325, 285));
        jPanel33.setEnabled(false);
        jPanel34.setLayout(new java.awt.GridBagLayout());

        jPanel34.setPreferredSize(new java.awt.Dimension(280, 275));
        jPanel34.setEnabled(false);
        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel36.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_TVA_num"));
        jLabel36.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel36, gridBagConstraints);

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel37.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_TVA_type"));
        jLabel37.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel37, gridBagConstraints);

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel39.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_carteCredit"));
        jLabel39.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel39, gridBagConstraints);

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel40.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_domiciliation"));
        jLabel40.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel40, gridBagConstraints);

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel41.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_devise"));
        jLabel41.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel41, gridBagConstraints);

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel42.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_paybyday"));
        jLabel42.setPreferredSize(new java.awt.Dimension(110, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel42, gridBagConstraints);

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel43.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_banque1"));
        jLabel43.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel43, gridBagConstraints);

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel44.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_banque2"));
        jLabel44.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel44, gridBagConstraints);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel45.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_banque3"));
        jLabel45.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel45, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_TVA_regime"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel1, gridBagConstraints);

        grp_TField_PaybyDay.setEnabled(false);
        grp_TField_PaybyDay.setGrp_Comp_nextComponent(grp_TField_Banque1);
        grp_TField_PaybyDay.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_PaybyDay.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_PaybyDay.setPreferredSize(new java.awt.Dimension(60, 18));
        grp_TField_PaybyDay.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_PaybyDay.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_PaybyDay, gridBagConstraints);

        grp_TField_Banque1.setEnabled(false);
        grp_TField_Banque1.setGrp_Comp_nextComponent(grp_TField_Banque2);
        grp_TField_Banque1.setGrp_Comp_previousComponent(grp_TField_PaybyDay);
        grp_TField_Banque1.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_Banque1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Banque1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_Banque1, gridBagConstraints);

        grp_TField_Banque2.setEnabled(false);
        grp_TField_Banque2.setGrp_Comp_nextComponent(grp_TField_Banque3);
        grp_TField_Banque2.setGrp_Comp_previousComponent(grp_TField_Banque1);
        grp_TField_Banque2.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_Banque2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Banque2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_Banque2, gridBagConstraints);

        grp_TField_Banque3.setEnabled(false);
        grp_TField_Banque3.setGrp_Comp_nextComponent(grp_TField_web);
        grp_TField_Banque3.setGrp_Comp_previousComponent(grp_TField_web);
        grp_TField_Banque3.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_Banque3.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Banque3.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_Banque3, gridBagConstraints);

        grp_CheckB_carteCredit.setEnabled(false);
        grp_CheckB_carteCredit.setGrp_Comp_nextComponent(grp_Cbos_domiciliation);
        grp_CheckB_carteCredit.setMinimumSize(new java.awt.Dimension(30, 18));
        grp_CheckB_carteCredit.setVerifyInputWhenFocusTarget(false);
        grp_CheckB_carteCredit.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_CheckB_carteCredit, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_NCompte"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel3, gridBagConstraints);

        grp_LSelect_NCompte.setEnabled(false);
        grp_LSelect_NCompte.setGrp_Comp_nextComponent(grp_ACB_TVAtype);
        grp_LSelect_NCompte.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_NCompte.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_LSelect_NCompte, gridBagConstraints);

        grp_ACB_TVAtype.setEnabled(false);
        grp_ACB_TVAtype.setGrp_Comp_nextComponent(grp_TField_TVAnum);
        grp_ACB_TVAtype.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ACB_TVAtype.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_ACB_TVAtype, gridBagConstraints);

        grp_LSelect_tvaRegime.setEnabled(false);
        grp_LSelect_tvaRegime.setGrp_Comp_nextComponent(grp_CheckB_carteCredit);
        grp_LSelect_tvaRegime.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_tvaRegime.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_LSelect_tvaRegime, gridBagConstraints);

        grp_LSelect_Devise.setEnabled(false);
        grp_LSelect_Devise.setGrp_Comp_nextComponent(grp_TField_PaybyDay);
        grp_LSelect_Devise.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Devise.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_LSelect_Devise, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur").getString("Fr_web"));
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur").getString("fr_restore"));
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel34.add(jLabel6, gridBagConstraints);

        grp_TField_web.setEnabled(false);
        grp_TField_web.setGrp_Comp_nextComponent(grp_CBox_fournProduit);
        grp_TField_web.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_web.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_web.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_web, gridBagConstraints);

        grp_CBox_restore.setEnabled(false);
        grp_CBox_restore.setGrp_Comp_nextComponent(grp_CBox_fournProduit);
        grp_CBox_restore.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_CBox_restore, gridBagConstraints);

        grp_TField_TVAnum.setEnabled(false);
        grp_TField_TVAnum.setGrp_Comp_nextComponent(grp_LSelect_tvaRegime);
        grp_TField_TVAnum.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_TVAnum.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel34.add(grp_TField_TVAnum, gridBagConstraints);

        grp_Cbos_domiciliation.setEnabled(false);
        grp_Cbos_domiciliation.setGrp_Comp_nextComponent(grp_LSelect_Devise);
        grp_Cbos_domiciliation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel34.add(grp_Cbos_domiciliation, gridBagConstraints);

        jPanel33.add(jPanel34);

        jPanel2.add(jPanel33);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel3.setBorder(new javax.swing.border.EtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(325, 40));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_fournProduit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 4);
        jPanel4.add(jLabel4, gridBagConstraints);

        jPanel3.add(jPanel4);

        grp_CBox_fournProduit.setEnabled(false);
        grp_CBox_fournProduit.setIsLastComponent(true);
        grp_CBox_fournProduit.setLeft(true);
        grp_CBox_fournProduit.setUp(true);
        grp_CBox_fournProduit.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_CBox_fournProduit.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel3.add(grp_CBox_fournProduit);

        jPanel2.add(jPanel3);

        jPanel24.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel24);

    }//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    }//GEN-LAST:event_jButton7ActionPerformed

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void componentShown(java.awt.event.ComponentEvent componentEvent) {
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

    private ValidateField valideAndPrevious = new ValidateField() {
        public void actionPerformed(ActionEvent evt) {
            if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {
                requestFocus();
                doPrevious();
            }
        }
    };
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void prepareForInsertion() {
    }

    /**
     * Lance le chargement des données dans les listes
     * (dans Astra : ListSelector)
     */
    public void chargeData() {
    }

    /**
     * Demande de selection au serveur
     */
    public void dbSelect() {
        try {
//   if(fournisseur==null)
            fournisseur = fournisseurInterface.selectFournisseur(currentUser.getUrcleunik(), frCleUnik);// Object obj = serveur.ChargeObject(currentUser.getUrlmcleunik(), currentUser.getUrcleunik(), frCleUnik, 1, serveur.COMBO_FOURNISSEURCOMP);
//  fournisseur = (srcastra.astra.sys.classetransfert.Fournisseur_T) obj;
            updateAllFields();
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, currentUser);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, currentUser);
        }

    }

    /**
     * demande de select for update au serveur
     */
    public void dbSelectForUpdate() {
        try {
            fournisseur = fournisseurInterface.selectFournisseurForUpdate(currentUser.getUrcleunik(), frCleUnik);
            updateAllFields();
            checkValidity = true;
/*  Object obj = serveur.ChargeObject(currentUser.getUrlmcleunik(), currentUser.getUrcleunik(), frCleUnik, 2, serveur.COMBO_FOURNISSEURCOMP);
fournisseur = (srcastra.astra.sys.classetransfert.Fournisseur_T) obj;
if(fournisseur.getErreurcode()==1205)
{
JOptionPane.showMessageDialog(this,"Ce fournisseur est déjà en cours de modification par un autre utilisateur");
checkValidity=false;
}
else
{
updateAllFields();
checkValidity=true;
}
*/
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            checkValidity = false;
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, currentUser);
        } catch (java.rmi.RemoteException re) {
            checkValidity = false;
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, currentUser);
        }
    }

    /**
     * demande d'insertion au serveur
     */
    public void dbInsert() {
        srcastra.astra.sys.classetransfert.Gestionerreur_T erreur;
        fournisseur = new srcastra.astra.sys.classetransfert.Fournisseur_T();
        fournisseur.setFrreference1(grp_TField_ref1.getText());
        fournisseur.setFrnom1(grp_TField_nom1.getText());
        fournisseur.setFradresse(grp_TField_adresse.getText());
        fournisseur.setCxcleunik(grp_LSelect_cp.getCleUnik());
        fournisseur.setPyscleunik(grp_LSelect_Pays.getCleUnik());
        fournisseur.setFrreference2(grp_TField_ref2.getText());
        fournisseur.setFrnom2(grp_TField_nom2.getText());
        fournisseur.setLecleunik(grp_LSelect_Lang.getCleUnik());
        fournisseur.setFrmail(grp_TField_email.getText());
        fournisseur.setFrtelephone1(grp_TField_phone.getText());
        fournisseur.setFax(grp_TField_fax.getText());
        fournisseur.setFrCompteCleunik(grp_LSelect_NCompte.getCleUnik());
        fournisseur.setFrtvanum((String) grp_TField_TVAnum.getValue());
        fournisseur.setTvaType2(grp_ACB_TVAtype.getCleUnik());
        fournisseur.setFrtvaregime(grp_LSelect_tvaRegime.getCleUnik());
        int cccf = (grp_CheckB_carteCredit.isSelected()) ? 1 : 0;
        fournisseur.setFrmodecccf(cccf);
        fournisseur.setFrdomiciliation(grp_Cbos_domiciliation.isSelected() ? 1 : 0);
        fournisseur.setDecleunik(grp_LSelect_Devise.getCleUnik());
        fournisseur.setFrdelaipaienbrjour(Integer.parseInt(grp_TField_PaybyDay.getText()));
        fournisseur.setFrnumbanque1(grp_TField_Banque1.getText());
        fournisseur.setFrnumbanque2(grp_TField_Banque2.getText());
        fournisseur.setFrnumbanque3(grp_TField_Banque3.getText());
        fournisseur.setAnnuler(0);
// int fprod = (grp_CBox_fournProduit.isSelected()) ? 1 : 0;
        fournisseur.setFrWeb(grp_TField_web.getText());
        fournisseur.setFrFournProduit(grp_CBox_fournProduit.getCleUnik());

        try {
            frCleUnik = fournisseurInterface.insertFournisseur(currentUser.getUrcleunik(), fournisseur);
            serveur.renvcombo('f', currentUser.getUrcleunik(), currentUser.getUrlmcleunik(), 'p', "", 1, astrainterface.COMBO_FOURNISSEURCAS1, true);
            System.out.println("\n\n[*************************]cleunikfournisseur" + frCleUnik);
            this.parent.setContextCleUnik(frCleUnik);
            displayDisableMode();
            this.parent.nextScreen(PANE_NUMBER, true);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, parent.getCurrentUser());
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, parent.getCurrentUser());
        } catch (Exception e) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e, parent.getCurrentUser());
        }
    }

    /**
     * non implémenter dans ce cas
     */
    public void dbDelete() {
    }

    /**
     * demande d'update au serveur
     */
    public void dbUpdate() {
        srcastra.astra.sys.classetransfert.Gestionerreur_T erreur;
        fournisseur = new srcastra.astra.sys.classetransfert.Fournisseur_T();
        fournisseur.setFrcleunik(frCleUnik);
        fournisseur.setFrreference1(grp_TField_ref1.getText());
        fournisseur.setFrnom1(grp_TField_nom1.getText());
        fournisseur.setFradresse(grp_TField_adresse.getText());
        fournisseur.setCxcleunik(grp_LSelect_cp.getCleUnik());
        fournisseur.setPyscleunik(grp_LSelect_Pays.getCleUnik());
        fournisseur.setFrreference2(grp_TField_ref2.getText());
        fournisseur.setFrnom2(grp_TField_nom2.getText());
        fournisseur.setLecleunik(grp_LSelect_Lang.getCleUnik());
        fournisseur.setFrmail(grp_TField_email.getText());
        fournisseur.setFrtelephone1(grp_TField_phone.getText());
        fournisseur.setFax(grp_TField_fax.getText());
        fournisseur.setFrCompteCleunik(grp_LSelect_NCompte.getCleUnik());
        fournisseur.setFrtvanum((String) grp_TField_TVAnum.getValue());
//   fournisseur.setFrtvatype(grp_ACB_TVAtype.getSelectedText());
        fournisseur.setTvaType2(grp_ACB_TVAtype.getCleUnik());
        fournisseur.setFrtvaregime(grp_LSelect_tvaRegime.getCleUnik());
        int cccf = (grp_CheckB_carteCredit.isSelected()) ? 1 : 0;
        fournisseur.setFrmodecccf(cccf);
        fournisseur.setFrdomiciliation(grp_Cbos_domiciliation.isSelected() ? 1 : 0);
        fournisseur.setDecleunik(grp_LSelect_Devise.getCleUnik());
        fournisseur.setFrdelaipaienbrjour(Integer.parseInt(grp_TField_PaybyDay.getText()));
        fournisseur.setFrnumbanque1(grp_TField_Banque1.getText());
        fournisseur.setFrnumbanque2(grp_TField_Banque2.getText());
        fournisseur.setFrnumbanque3(grp_TField_Banque3.getText());
        if (grp_CBox_restore.isEnabled()) {
            fournisseur.setAnnuler(grp_CBox_restore.isSelected() ? 0 : 1);
        }
// int fprod = (grp_CBox_fournProduit.isSelected()) ? 1 : 0;
        fournisseur.setFrFournProduit(grp_CBox_fournProduit.getCleUnik());
        fournisseur.setFrWeb(grp_TField_web.getText());
        try {
            fournisseurInterface.updateFournisseur(currentUser.getUrcleunik(), fournisseur);
            serveur.renvcombo('f', currentUser.getUrcleunik(), currentUser.getUrlmcleunik(), 'p', "", 1, astrainterface.COMBO_FOURNISSEURCAS1, true);
            displayReadMode();
/*  erreur = serveur.modifyfournisseur(fournisseur, currentUser.getUrcleunik());
if (erreur.getErreurcode() == 1062) {
javax.swing.JOptionPane.showMessageDialog(this, "La référence fournisseur existe déjà !!!");
}
else {
displayReadMode();
}
*/
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, currentUser);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, currentUser);
        }
//   catch (Exception e) {
//     ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
//}
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * règle ces composants pour un mode de lecture
     */
    public void displayReadMode() {
// toolbar
        parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_MODIFY,
                                                 actionToolBar.DO_CANCEL});
        action = actionToolBar.ACT_READ;
        parent.enabledTabbedPane(true);
        init();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
        dbSelect();
        grp_TField_TVAnum.setEnabled(false);
        grp_CBox_fournProduit.setLastFocusedComponent(true);
        this.requestFocus();
        parent.chargeStatusPanel(new String[]{fournisseur.getFrreference1(),
                                              fournisseur.getFrnom1(),
                                              fournisseur.getFrdatetimecrea().toString(),
                                              fournisseur.getFrmail(),
                                              fournisseur.getFrtelephone1(),
                                              fournisseur.getFrfax()});
        grp_TField_ref1.requestFocus();
    }

    /**
     * règle ces composants pour un mode de création
     */
    public void displayInsertMode() {
// toolbar
        parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_CANCEL});
// fin de toolbar
        init();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            if (composantToVerif[i] instanceof Liste)
                ;
            else
                composantToVerif[i].setText("");
        }
        grp_CBox_restore.setEnabled(false);
        action = actionToolBar.ACT_INSERT;
        grp_CBox_fournProduit.setLastFocusedComponent(true);
        grp_TField_ref1.setEnabled(true);
        grp_TField_PaybyDay.setText("30");
        grp_TField_ref1.requestFocus();
    }

    /**
     * règle ces composants pour un mode de modification
     */
    public void displayUpdateMode() {
// toolbar
        dbSelectForUpdate();
        if (!checkValidity) {
            displayReadMode();
        } else {
            parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_PREVIOUS,
                                                     actionToolBar.DO_CANCEL});
// fin de toolbar
            this.action = actionToolBar.ACT_MODIFY;
            parent.enabledTabbedPane(false);
            for (int i = 0; i < composantToVerif.length; i++) {
                composantToVerif[i].setEnabled(true);
                composantToVerif[i].setLastFocusedComponent(true);
            }
            if (fournisseur.getAnnuler() == 0)
                grp_CBox_restore.setEnabled(false);
            setTvaNumDocumentMask();
            grp_TField_ref1.requestFocus();
        }
    }

    public void displayDisableMode() {
        parent.setCurrentActionEnabled(new int[0]);
        this.action = -1;
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
        }
    }

    public void updateAllFields() {
        try {
            grp_TField_ref1.setText("" + fournisseur.getFrreference1());
            grp_TField_nom1.setText("" + fournisseur.getFrnom1());
            grp_TField_adresse.setText("" + fournisseur.getFradresse());
            grp_LSelect_Pays.setCleUnik(fournisseur.getPyscleunik());
            grp_LSelect_cp.setCleUnik(fournisseur.getCxcleunik());
            grp_TField_ref2.setText("" + fournisseur.getFrreference2());
            grp_TField_nom2.setText("" + fournisseur.getFrnom2());
            grp_LSelect_Lang.setCleUnik(fournisseur.getLecleunik());
            grp_TField_email.setText("" + fournisseur.getFrmail());
            grp_TField_phone.setText("" + fournisseur.getFrtelephone1());
            grp_TField_fax.setText("" + fournisseur.getFrfax());
            grp_LSelect_NCompte.setCleUnik(fournisseur.getFrCompteCleunik());
            grp_TField_TVAnum.setText("" + fournisseur.getFrtvanum());
            grp_ACB_TVAtype.setCleUnik(fournisseur.getTvaType2());
            grp_LSelect_tvaRegime.setCleUnik(fournisseur.getFrtvaregime());
            int cb = fournisseur.getFrmodecccf();
            switch (cb) {
                case 0:
                    grp_CheckB_carteCredit.setSelected(false);
                    break;
                case 1:
                    grp_CheckB_carteCredit.setSelected(true);
                    break;
            }
            grp_CheckB_carteCredit.setSelected(fournisseur.getFrdomiciliation() == 1 ? true : false);
            grp_LSelect_Devise.setCleUnik(fournisseur.getDecleunik());
// grp_LSelect_Devise.setText(fournisseur.getDevise());
            grp_TField_PaybyDay.setText("" + fournisseur.getFrdelaipaienbrjour());
            grp_TField_Banque1.setText("" + fournisseur.getFrnumbanque1());
            grp_TField_Banque2.setText("" + fournisseur.getFrnumbanque2());
            grp_TField_Banque3.setText("" + fournisseur.getFrnumbanque3());
//  boolean fprod = (fournisseur.getFrFournProduit() == 1) ? true : false;
            grp_CBox_fournProduit.setCleUnik(fournisseur.getFrFournProduit());
            grp_TField_web.setText("" + fournisseur.getFrWeb());
//    if(fournisseur.getAnnuler()==1)
//      grp_CBox_restore.setSelected(true);
        } catch (NullPointerException npe) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.NULL_POINTER_EXCEPTION, srcastra.astra.Astra.DEBUG, npe);
//doCancel();
        }
    }

    public void updateAllFields(Object donnee) {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Annule l'opération en cours
     * ici : fait appel à FournisseurModules pour repasser à
     * l'écran d'index des Fournisseurs
     */
    public void doCancel() {
        switch (action) {
            case ActionToolBar.ACT_READ:
                parent.cancelModule();
                break;
            case ActionToolBar.ACT_INSERT:
                parent.cancelModule();
                break;
            case ActionToolBar.ACT_MODIFY:
                try {
                    serveur.remoterollback(currentUser.getUrcleunik());
                } catch (java.rmi.RemoteException e) {
                    System.out.println("Exception dans remoterollback dans docancel fournisseurgénéralinfopane" + e.getMessage());
                }
                displayReadMode();
                break;
        }
    }

    /**
     * appel à FournisseurModule pour passer à l'écran suivant
     */
    public void doNext() {
    }

    /**
     * appel à FournisseurModule pour passer à l'écran suivant
     */
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

    /**
     * non implémenter dans ce cas
     */
    public void doDelete() {
        JOptionPane.showMessageDialog(this, "Sorry ren pour le moment tu ne peux effacer les fournisseur que sur le premier panneaux");
    }

    /**
     * non implémenter dans ce cas
     */
    public void doCreate() {
    }

    /**
     */
    public void doHelp() {
    }

    /**
     * non implémenter dans ce cas
     */
    public void doClose() {
    }

    /**
     * non implémenter dans ce cas
     */
    public void doModify() {
        displayUpdateMode();
    }

    /**
     * lance la fonction d'insertion ou de
     * modification (selon int action)
     */
    public void doAccept() {

    }

    /**
     * non implémenter dans ce cas
     */
    public void doPrint() {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * pour 1 seul initiation du panneau
     */
    public boolean initOnce = false;
    private srcastra.astra.sys.classetransfert.Fournisseur_T fournisseur;
    private astrainterface serveur;
    private Loginusers_T currentUser;
    private MainScreenModule parent;
    private ActionToolBar actionToolBar;
    private AstraComponent[] composantToVerif;
    private int action;
    private int frCleUnik;
    private boolean checkValidity = false;
    private ToolBarInteraction tb_interaction;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// static value
    /**
     * numéro du panneau (cfr séquence)
     */
    public static int PANE_NUMBER = 0;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.combobox.liste.Liste grp_ACB_TVAtype;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_CBox_fournProduit;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_restore;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbos_domiciliation;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckB_carteCredit;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Devise;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Lang;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_NCompte;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Pays;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_cp;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_tvaRegime;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Banque1;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Banque2;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Banque3;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Localite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_PaybyDay;
    private srcastra.astra.gui.components.textFields.FormatedTextField.FormatedTextField grp_TField_TVAnum;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_adresse;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_email;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_fax;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom1;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom2;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_phone;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_ref1;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_ref2;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_web;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BEANS PROPERTIES GET/SET SUPPORT
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
     * Getter property
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        try {
            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GeneralPane_titrePane");
        } catch (java.util.MissingResourceException mre) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);
            return "";
        }
    }

    /**
     * Setter pour la clé unique du fournisseur
     *
     * @param frCleUnik la clé unique du fournisseur
     */
    public void setFrCleunik(int frCleUnik) {
        this.frCleUnik = frCleUnik;
    }

    /**
     * Renvoi les actions par défault du panneau une fois
     * celui-ci lancé.
     * (ici on renvoi à ActionToolBar les actions par défault)
     *
     * @return les actions du panneau
     */
    public int[] getDefaultActionToolBarMask() {
        return new int[]{ActionToolBar.DO_CANCEL};
    }

    /**
     * S'établit comme panneau pilote de la ToolBar
     */
    public void setThisAsToolBarComponent() {
        parent.setCurrentPanel(this);
    }

    /**
     * Getter for property action.
     *
     * @return Value of property action.
     */
    public int getAction() {
        return action;
    }

    /**
     * Setter for property action.
     *
     * @param action New value of property action.
     */
    public void setAction(int action) {
        this.action = action;
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

    /**
     * Creates a new instance of MailInterface
     */
    public String[] getEmailAdres() {
        return new String[]{grp_TField_email.getText()};
    }

    
     public String getFormEntiteMail() {
       
       return "";
   }
    
    public void addKeystroque() {

    }

    public Loginusers_T getUser() {
        return parent.getCurrentUser();
    }

    public void doF7() {
    }

    ManageMailingFrame mailingFrame;
    FournisseurRmiInterface fournisseurInterface;
}
