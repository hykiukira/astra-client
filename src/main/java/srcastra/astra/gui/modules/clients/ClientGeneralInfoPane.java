/*
* ClientGeneralInfoPane.java
*
* Created on 14 juin 2002, 16:06
*/
package srcastra.astra.gui.modules.clients;

import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.gui.modules.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.components.AstraComponent;

import javax.swing.*;

import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.*;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import srcastra.astra.gui.event.ValidateField;
import srcastra.astra.gui.sys.listModel.dossierListModel.TypeTvaListModel;
import srcastra.astra.gui.modules.Mailing.*;
import srcastra.astra.gui.components.combobox.liste.*;
import srcastra.astra.sys.configuration.*;

/**
 * @author Sébastien
 */
public class ClientGeneralInfoPane extends javax.swing.JPanel implements InternScreenModule,
        ToolBarComposer,
        java.awt.event.ComponentListener, MailInterface {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form ClientGeneralInfoPane
     */
    public ClientGeneralInfoPane(srcastra.astra.sys.rmi.astrainterface serveur,
                                 srcastra.astra.sys.classetransfert.Loginusers_T currentUser,
                                 MainScreenModule parent, ActionToolBar actionToolBar) {
        this.serveur = serveur;
        this.currentUser = currentUser;
        this.parent = parent;
        this.actionToolBar = actionToolBar;
        this.action = actionToolBar.ACT_READ;
        this.addComponentListener(this);
        init();
        initListe();

        grp_TField_codeCotisateur.setVisible(false);
        grp_Label_codeCotisateur.setVisible(false);
        grp_ADate_dateDeCotisation.setVisible(false);
        grp_Label_dateDeCotisation.setVisible(false);

        //new ManageMailingFrame(this, this, parent.getSuperOwner(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());
    }

    private void initListe() {
//   m_model=new  SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(),m_productId,parent,srcastra.astra.sys.configuration.AbstractRequete.SUP_RECUC,parent.getMainframe().getSupReducVector());
// m_model.loadata();
        grp_LSelect_langue.setServeur(parent.getServeur());
        grp_LSelect_langue.setLogin(parent.getCurrentUser());
        grp_LSelect_langue.setModel(new srcastra.astra.gui.components.combobox.liste.LangueListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_langue.init2();
        grp_LSelect_TitrePers.setServeur(parent.getServeur());
        grp_LSelect_TitrePers.setLogin(parent.getCurrentUser());
        grp_LSelect_TitrePers.setModel(new srcastra.astra.gui.components.combobox.liste.TitrePersonneListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_TitrePers.init2();
        grp_LSelect_Compte.setServeur(parent.getServeur());
        grp_LSelect_Compte.setLogin(parent.getCurrentUser());
        grp_LSelect_Compte.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Compte.init2();
        grp_LSelect_codePostal.setServeur(parent.getServeur());
        grp_LSelect_codePostal.setLogin(parent.getCurrentUser());
        grp_LSelect_codePostal.setModel(new srcastra.astra.gui.components.combobox.liste.CpListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_codePostal.init2();
        grp_LSelect_codePostal.setFilNexComponent(true);
        grp_LSelect_codePostal.setGrp_Comp_nextStringComp(grp_TField_localite);
        grp_LSelect_pays.setServeur(parent.getServeur());
        grp_LSelect_pays.setLogin(parent.getCurrentUser());
        grp_LSelect_pays.setModel(new srcastra.astra.gui.components.combobox.liste.PaysListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_pays.init2();
        grp_ACB_TVAtype.setServeur(parent.getServeur());
        grp_ACB_TVAtype.setLogin(parent.getCurrentUser());
        grp_ACB_TVAtype.setModel(new SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), 1, parent, AbstractRequete.TVA_TYPE, ((srcastra.astra.gui.MainFrame) parent.getSuperOwner()).getTypeTvaVector()));
        grp_ACB_TVAtype.init2();
        grp_ACB_TVAtype.setColToSearch(3);
        grp_LSelect_TvaRegime.setServeur(parent.getServeur());
        grp_LSelect_TvaRegime.setLogin(parent.getCurrentUser());
        grp_LSelect_TvaRegime.setModel(new SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), 1, parent, AbstractRequete.TVA_REGIME, ((srcastra.astra.gui.MainFrame) parent.getSuperOwner()).getRegimeTvaVector()));
        grp_LSelect_TvaRegime.init2();
        grp_LSelect_TvaRegime.setColToSearch(3);


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
            initComponents0();
            initComponents();
            grp_ACB_TVAtype.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent event) {
                    setTvaNumDocumentMask();
                }
            });

            setDocumentMask();
            setTvaNumDocumentMask();
// chargement d'un tableau reprenant tous les éléments pour une correction
            this.composantToVerif = new AstraComponent[]{
                    grp_LSelect_TitrePers, grp_TField_nom, grp_TField_nom2, grp_TField_adresse,
                    grp_LSelect_codePostal, grp_LSelect_pays, grp_ACBox_Bloque, grp_TField_mailPrincipal,
                    grp_TField_mailsecondaire, grp_TField_telephonePerso, grp_TField_telephoneBureau,
                    grp_TField_fax, grp_TField_gsm, grp_LSelect_langue, grp_ADate_dateDeNaissance, grp_LSelect_Compte,
                    grp_ACB_TVAtype, grp_TField_TvaNum, grp_LSelect_TvaRegime, grp_TField_CompteBanquaire,
                    grp_TField_carteCredit, grp_TField_delaiPaiement, grp_TField_codeMailing,
                    grp_TField_Groupement, grp_TField_codeCotisateur, grp_ADate_dateDeCotisation,
                    grp_TField_montantCotisation
            };

            this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);
            tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_STANDART);

//-> Régistration des listeners pour la validité des composants
            for (int i = 0; i < composantToVerif.length; i++) {
                composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
            }

//  grp_LSelect_Compte.setListSelectorTableModel(new NCompteTableModel(serveur, currentUser, 0));

// les éléments ne sont intialisé qu'1 seule fois
            initOnce = true;
            grp_TField_montantCotisation.addActionListener(validateAndDoPrevious);
            setFocusable(false);
        }


    }

    private void prepareInsert() {
        grp_LSelect_Compte.setCleUnik(635);
        grp_LSelect_TvaRegime.setCleUnik(3);
        grp_LSelect_pays.setCleUnik(20);
        grp_ACB_TVAtype.setCleUnik(1);
        grp_TField_montantCotisation.setText("0");
        grp_ADate_dateDeCotisation.setDate(new srcastra.astra.sys.classetransfert.utils.Date(1000, 1, 1));
        grp_ADate_dateDeNaissance.setDate(new srcastra.astra.sys.classetransfert.utils.Date(1000, 1, 1));

    }

    private void setDocumentMask() {
        grp_TField_reference.setDocument(new DefaultMask(1, 12, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_nom.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_nom2.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_adresse.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
// grp_LSelect_codePostal
// grp_LSelect_pays
// grp_ACBox_Bloque

        grp_TField_mailPrincipal.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_mailsecondaire.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_telephonePerso.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_telephoneBureau.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_fax.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_gsm.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
// grp_LSelect_langue
// grp_ADate_dateDeNaissance.setDocument(
// grp_ACB_TVAtype
// grp_LSelect_TvaRegime
        grp_TField_CompteBanquaire.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_carteCredit.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_delaiPaiement.setDocument(new IntegerMask(1, 4, currentUser.getLangage()));
        grp_TField_codeMailing.setDocument(new DefaultMask(0, 15, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_Groupement.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
        grp_TField_codeCotisateur.setDocument(new DefaultMask(0, 10, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
// grp_ADate_dateDeCotisation
        grp_TField_montantCotisation.setDocument(new DecimalMask(4, 2, currentUser.getLangage()));
    }

    private void setTvaNumDocumentMask() {
        // grp_TField_TvaNum.setDocument(new DefaultMask(0, 20, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));
    }

    private void initComponents0() {
        grp_Client_groupementPane = new ClientGroupementPane(this.serveur, this.currentUser, this.parent, this.actionToolBar);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        grp_Label_reference = new javax.swing.JLabel();
        grp_Label_nom2 = new javax.swing.JLabel();
        grp_Label_adresse = new javax.swing.JLabel();
        grp_Label_codePostal = new javax.swing.JLabel();
        grp_Label_pays = new javax.swing.JLabel();
        grp_Label_localite = new javax.swing.JLabel();
        grp_TField_nom2 = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_adresse = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_localite = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_nom = new javax.swing.JLabel();
        grp_TField_reference = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_nom = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Bloque = new javax.swing.JLabel();
        grp_ACBox_Bloque = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_Label_TitrePers = new javax.swing.JLabel();
        grp_LSelect_TitrePers = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_codePostal = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_pays = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        grp_Label_mailPrincipal = new javax.swing.JLabel();
        grp_Label_telephonePerso = new javax.swing.JLabel();
        grp_Label_fax = new javax.swing.JLabel();
        grp_TField_mailPrincipal = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_telephonePerso = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_fax = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_mailsecondaire = new javax.swing.JLabel();
        grp_Label_telephoneBureau = new javax.swing.JLabel();
        grp_Label_gsm = new javax.swing.JLabel();
        grp_TField_mailsecondaire = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_telephoneBureau = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_gsm = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        grp_Label_langue = new javax.swing.JLabel();
        grp_Label_dateDeNaissance = new javax.swing.JLabel();
        grp_ADate_dateDeNaissance = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_LSelect_langue = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel29 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grp_TField_TvaNum = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_TField_CompteBanquaire = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel5 = new javax.swing.JLabel();
        grp_TField_carteCredit = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel6 = new javax.swing.JLabel();
        grp_TField_delaiPaiement = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel7 = new javax.swing.JLabel();
        grp_TField_codeMailing = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel8 = new javax.swing.JLabel();
        grp_TField_Groupement = new srcastra.astra.gui.components.textFields.ATextFieldWithButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        grp_LSelect_Compte = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_ACB_TVAtype = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_TvaRegime = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        grp_Label_codeCotisateur = new javax.swing.JLabel();
        grp_TField_codeCotisateur = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_dateDeCotisation = new javax.swing.JLabel();
        grp_TField_montantCotisation = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_montantCotisation = new javax.swing.JLabel();
        grp_ADate_dateDeCotisation = new srcastra.astra.gui.components.date.thedate.ADate();
        jLabel11 = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(1, 0));

        setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_titrePane")));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 6));

        jPanel18.setEnabled(false);
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel19.setBorder(new javax.swing.border.EtchedBorder());
        jPanel19.setMinimumSize(new java.awt.Dimension(350, 203));
        jPanel19.setPreferredSize(new java.awt.Dimension(350, 203));
        jPanel19.setEnabled(false);
        jPanel20.setLayout(new java.awt.GridBagLayout());

        jPanel20.setMinimumSize(new java.awt.Dimension(323, 189));
        jPanel20.setPreferredSize(new java.awt.Dimension(323, 189));
        jPanel20.setEnabled(false);
        grp_Label_reference.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_reference.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_reference.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_reference"));
        grp_Label_reference.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_reference.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_reference, gridBagConstraints);

        grp_Label_nom2.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_nom2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_nom2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Nom2"));
        grp_Label_nom2.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_nom2.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_nom2, gridBagConstraints);

        grp_Label_adresse.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_adresse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_adresse.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Adresse"));
        grp_Label_adresse.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_adresse.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_adresse, gridBagConstraints);

        grp_Label_codePostal.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_codePostal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_codePostal.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_CodePostal"));
        grp_Label_codePostal.setMaximumSize(new java.awt.Dimension(100, 14));
        grp_Label_codePostal.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_codePostal.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_codePostal, gridBagConstraints);

        grp_Label_pays.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_pays.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_pays.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Pays"));
        grp_Label_pays.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_pays.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_pays, gridBagConstraints);

        grp_Label_localite.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_localite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_localite.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Localite"));
        grp_Label_localite.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_localite.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_localite, gridBagConstraints);

        grp_TField_nom2.setEnabled(false);
        grp_TField_nom2.setGrp_Comp_nextComponent(grp_TField_adresse);
        grp_TField_nom2.setGrp_Comp_previousComponent(grp_TField_nom);
        grp_TField_nom2.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_nom2.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_nom2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nom2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_TField_nom2, gridBagConstraints);

        grp_TField_adresse.setEnabled(false);
        grp_TField_adresse.setGrp_Comp_nextComponent(grp_LSelect_codePostal);
        grp_TField_adresse.setGrp_Comp_previousComponent(grp_TField_nom2);
        grp_TField_adresse.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_adresse.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_adresse.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_adresse.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_TField_adresse, gridBagConstraints);

        grp_TField_localite.setEnabled(false);
        grp_TField_localite.setMinimumSize(new java.awt.Dimension(170, 18));
        grp_TField_localite.setPreferredSize(new java.awt.Dimension(170, 18));
        grp_TField_localite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_localite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_TField_localite, gridBagConstraints);

        grp_Label_nom.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_nom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Nom"));
        grp_Label_nom.setMinimumSize(new java.awt.Dimension(90, 14));
        grp_Label_nom.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_nom, gridBagConstraints);

        grp_TField_reference.setEnabled(false);
        grp_TField_reference.setGrp_Comp_nextComponent(grp_LSelect_TitrePers);
        grp_TField_reference.setGrp_Comp_previousComponent(null);
        grp_TField_reference.setMinimumSize(new java.awt.Dimension(100, 18));
        grp_TField_reference.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_reference.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_TField_reference, gridBagConstraints);

        grp_TField_nom.setEnabled(false);
        grp_TField_nom.setGrp_Comp_nextComponent(grp_TField_nom2);
        grp_TField_nom.setMinimumSize(new java.awt.Dimension(230, 18));
        grp_TField_nom.setPreferredSize(new java.awt.Dimension(230, 18));
        grp_TField_nom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_TField_nom, gridBagConstraints);

        grp_Label_Bloque.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Bloque.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_Bloque"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_Bloque, gridBagConstraints);

        grp_ACBox_Bloque.setEnabled(false);
        grp_ACBox_Bloque.setGrp_Comp_nextComponent(grp_TField_mailPrincipal);
        grp_ACBox_Bloque.setVerifyInputWhenFocusTarget(false);
        grp_ACBox_Bloque.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_ACBox_Bloque, gridBagConstraints);

        grp_Label_TitrePers.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_TitrePers.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_TitrePers"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel20.add(grp_Label_TitrePers, gridBagConstraints);

        grp_LSelect_TitrePers.setEnabled(false);
        grp_LSelect_TitrePers.setGrp_Comp_nextComponent(grp_TField_nom);
        grp_LSelect_TitrePers.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_TitrePers.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_LSelect_TitrePers, gridBagConstraints);

        grp_LSelect_codePostal.setEnabled(false);
        grp_LSelect_codePostal.setGrp_Comp_nextComponent(grp_LSelect_pays);
        grp_LSelect_codePostal.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_codePostal.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_LSelect_codePostal, gridBagConstraints);

        grp_LSelect_pays.setEnabled(false);
        grp_LSelect_pays.setGrp_Comp_nextComponent(grp_ACBox_Bloque);
        grp_LSelect_pays.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_pays.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel20.add(grp_LSelect_pays, gridBagConstraints);

        jPanel19.add(jPanel20);

        jPanel18.add(jPanel19);

        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel25.setBorder(new javax.swing.border.EtchedBorder());
        jPanel25.setMinimumSize(new java.awt.Dimension(337, 140));
        jPanel25.setPreferredSize(new java.awt.Dimension(350, 140));
        jPanel25.setEnabled(false);
        jPanel26.setLayout(new java.awt.GridBagLayout());

        jPanel26.setMinimumSize(new java.awt.Dimension(323, 126));
        jPanel26.setPreferredSize(new java.awt.Dimension(323, 126));
        jPanel26.setEnabled(false);
        grp_Label_mailPrincipal.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_mailPrincipal.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_emailPrincipal"));
        grp_Label_mailPrincipal.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_mailPrincipal, gridBagConstraints);

        grp_Label_telephonePerso.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_telephonePerso.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_TelephonePerso"));
        grp_Label_telephonePerso.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_telephonePerso, gridBagConstraints);

        grp_Label_fax.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_fax.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Fax"));
        grp_Label_fax.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_fax, gridBagConstraints);

        grp_TField_mailPrincipal.setEnabled(false);
        grp_TField_mailPrincipal.setGrp_Comp_nextComponent(grp_TField_mailsecondaire);
        grp_TField_mailPrincipal.setGrp_Comp_previousComponent(grp_ACBox_Bloque);
        grp_TField_mailPrincipal.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_mailPrincipal.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_TField_mailPrincipal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                grp_TField_mailPrincipalFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_mailPrincipal, gridBagConstraints);

        grp_TField_telephonePerso.setEnabled(false);
        grp_TField_telephonePerso.setGrp_Comp_nextComponent(grp_TField_telephoneBureau);
        grp_TField_telephonePerso.setGrp_Comp_previousComponent(grp_TField_mailsecondaire);
        grp_TField_telephonePerso.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_telephonePerso.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_telephonePerso.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_telephonePerso, gridBagConstraints);

        grp_TField_fax.setEnabled(false);
        grp_TField_fax.setGrp_Comp_nextComponent(grp_TField_gsm);
        grp_TField_fax.setGrp_Comp_previousComponent(grp_TField_telephoneBureau);
        grp_TField_fax.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_fax.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_fax.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_fax, gridBagConstraints);

        grp_Label_mailsecondaire.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_mailsecondaire.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_emailSecondaire"));
        grp_Label_mailsecondaire.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_mailsecondaire, gridBagConstraints);

        grp_Label_telephoneBureau.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_telephoneBureau.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_TelephoneBureau"));
        grp_Label_telephoneBureau.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_telephoneBureau, gridBagConstraints);

        grp_Label_gsm.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_gsm.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_Gsm"));
        grp_Label_gsm.setPreferredSize(new java.awt.Dimension(90, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel26.add(grp_Label_gsm, gridBagConstraints);

        grp_TField_mailsecondaire.setEnabled(false);
        grp_TField_mailsecondaire.setGrp_Comp_nextComponent(grp_TField_telephonePerso);
        grp_TField_mailsecondaire.setGrp_Comp_previousComponent(grp_TField_mailPrincipal);
        grp_TField_mailsecondaire.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_mailsecondaire.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_mailsecondaire, gridBagConstraints);

        grp_TField_telephoneBureau.setEnabled(false);
        grp_TField_telephoneBureau.setGrp_Comp_nextComponent(grp_TField_fax);
        grp_TField_telephoneBureau.setGrp_Comp_previousComponent(grp_TField_telephonePerso);
        grp_TField_telephoneBureau.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_telephoneBureau.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_telephoneBureau.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_telephoneBureau, gridBagConstraints);

        grp_TField_gsm.setEnabled(false);
        grp_TField_gsm.setGrp_Comp_nextComponent(grp_LSelect_langue);
        grp_TField_gsm.setGrp_Comp_previousComponent(grp_TField_fax);
        grp_TField_gsm.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_gsm.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel26.add(grp_TField_gsm, gridBagConstraints);

        jPanel25.add(jPanel26);

        jPanel18.add(jPanel25);

        jPanel17.add(jPanel18, java.awt.BorderLayout.CENTER);

        add(jPanel17);

        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 6));

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel23.setBorder(new javax.swing.border.EtchedBorder());
        jPanel23.setMaximumSize(new java.awt.Dimension(350, 53));
        jPanel23.setMinimumSize(new java.awt.Dimension(350, 56));
        jPanel23.setPreferredSize(new java.awt.Dimension(350, 56));
        jPanel23.setEnabled(false);
        jPanel24.setLayout(new java.awt.GridBagLayout());

        jPanel24.setMaximumSize(new java.awt.Dimension(263, 42));
        jPanel24.setMinimumSize(new java.awt.Dimension(263, 42));
        jPanel24.setPreferredSize(new java.awt.Dimension(263, 42));
        jPanel24.setEnabled(false);
        grp_Label_langue.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_langue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_langue.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("GP_lang"));
        grp_Label_langue.setMaximumSize(new java.awt.Dimension(90, 14));
        grp_Label_langue.setMinimumSize(new java.awt.Dimension(110, 14));
        grp_Label_langue.setPreferredSize(new java.awt.Dimension(110, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel24.add(grp_Label_langue, gridBagConstraints);

        grp_Label_dateDeNaissance.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_dateDeNaissance.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_DateNaissance"));
        grp_Label_dateDeNaissance.setMinimumSize(new java.awt.Dimension(110, 14));
        grp_Label_dateDeNaissance.setPreferredSize(new java.awt.Dimension(110, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel24.add(grp_Label_dateDeNaissance, gridBagConstraints);

        grp_ADate_dateDeNaissance.setEnabled(false);
        grp_ADate_dateDeNaissance.setForm(this);
        grp_ADate_dateDeNaissance.setGrp_Comp_nextComponent(grp_LSelect_Compte);
        grp_ADate_dateDeNaissance.setUser(parent.getCurrentUser());
        grp_ADate_dateDeNaissance.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_dateDeNaissance.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel24.add(grp_ADate_dateDeNaissance, gridBagConstraints);

        grp_LSelect_langue.setEnabled(false);
        grp_LSelect_langue.setGrp_Comp_nextComponent(grp_ADate_dateDeNaissance);
        grp_LSelect_langue.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_langue.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel24.add(grp_LSelect_langue, gridBagConstraints);

        jPanel23.add(jPanel24);

        jPanel28.add(jPanel23);

        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel29.setBorder(new javax.swing.border.EtchedBorder());
        jPanel29.setEnabled(false);
        jPanel29.setMaximumSize(new java.awt.Dimension(350, 179));
        jPanel29.setMinimumSize(new java.awt.Dimension(350, 182));
        jPanel29.setPreferredSize(new java.awt.Dimension(350, 203));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Compte"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_TVAnum"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel2, gridBagConstraints);

        grp_TField_TvaNum.setEnabled(false);
        grp_TField_TvaNum.setGrp_Comp_nextComponent(grp_LSelect_TvaRegime);
        grp_TField_TvaNum.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_TvaNum.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_TField_TvaNum.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                grp_TField_TvaNumComponentHidden(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_TvaNum, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_regimeTVA"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_CompteBanquaire"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel4, gridBagConstraints);

        grp_TField_CompteBanquaire.setEnabled(false);
        grp_TField_CompteBanquaire.setGrp_Comp_nextComponent(grp_TField_carteCredit);
        grp_TField_CompteBanquaire.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_CompteBanquaire.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_CompteBanquaire, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_CarteDeCredit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel5, gridBagConstraints);

        grp_TField_carteCredit.setEnabled(false);
        grp_TField_carteCredit.setGrp_Comp_nextComponent(grp_TField_delaiPaiement);
        grp_TField_carteCredit.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_carteCredit.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_carteCredit, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_DelaiPaiement"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel6, gridBagConstraints);

        grp_TField_delaiPaiement.setEnabled(false);
        grp_TField_delaiPaiement.setGrp_Comp_nextComponent(grp_TField_codeMailing);
        grp_TField_delaiPaiement.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_delaiPaiement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_delaiPaiement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_delaiPaiement, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_CodeMailing"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel7, gridBagConstraints);

        grp_TField_codeMailing.setEnabled(false);
        grp_TField_codeMailing.setGrp_Comp_nextComponent(grp_TField_Groupement);
        grp_TField_codeMailing.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_codeMailing.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_codeMailing, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Groupement"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel8, gridBagConstraints);

        grp_TField_Groupement.setDialogDimension(new java.awt.Dimension(278, 347));
        grp_TField_Groupement.setEnabled(false);
        grp_TField_Groupement.setFrame(((ClientModules) parent).getSuperOwner());
        grp_TField_Groupement.setGrp_Comp_nextComponent(grp_TField_montantCotisation);
        grp_TField_Groupement.setGrp_Pane_InternScreenModule(grp_Client_groupementPane);
        grp_TField_Groupement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Groupement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_TField_Groupement, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_Jours"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_typeDeTva"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel3.add(jLabel10, gridBagConstraints);

        grp_LSelect_Compte.setEnabled(false);
        grp_LSelect_Compte.setGrp_Comp_nextComponent(grp_ACB_TVAtype);
        grp_LSelect_Compte.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Compte.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(grp_LSelect_Compte, gridBagConstraints);

        grp_ACB_TVAtype.setEnabled(false);
        grp_ACB_TVAtype.setGrp_Comp_nextComponent(grp_TField_TvaNum);
        grp_ACB_TVAtype.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ACB_TVAtype.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(grp_ACB_TVAtype, gridBagConstraints);

        grp_LSelect_TvaRegime.setEnabled(false);
        grp_LSelect_TvaRegime.setGrp_Comp_nextComponent(grp_TField_CompteBanquaire);
        grp_LSelect_TvaRegime.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_TvaRegime.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(grp_LSelect_TvaRegime, gridBagConstraints);

        jPanel29.add(jPanel3);

        jPanel28.add(jPanel29);

        jPanel1.setLayout(null);

        jPanel1.setBorder(new javax.swing.border.EtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(194, 98));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 77));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        grp_Label_codeCotisateur.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_codeCotisateur.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_CodeCotisateur"));
        grp_Label_codeCotisateur.setMinimumSize(new java.awt.Dimension(110, 14));
        grp_Label_codeCotisateur.setPreferredSize(new java.awt.Dimension(110, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel2.add(grp_Label_codeCotisateur, gridBagConstraints);

        grp_TField_codeCotisateur.setEnabled(false);
        grp_TField_codeCotisateur.setGrp_Comp_nextComponent(grp_ADate_dateDeCotisation);
        grp_TField_codeCotisateur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_codeCotisateur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(grp_TField_codeCotisateur, gridBagConstraints);

        grp_Label_dateDeCotisation.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_dateDeCotisation.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("GI_DateCotisation"));
        grp_Label_dateDeCotisation.setMinimumSize(new java.awt.Dimension(110, 14));
        grp_Label_dateDeCotisation.setPreferredSize(new java.awt.Dimension(110, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel2.add(grp_Label_dateDeCotisation, gridBagConstraints);

        grp_TField_montantCotisation.setEnabled(false);
        grp_TField_montantCotisation.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_montantCotisation.setLastFocusedComponent(true);
        grp_TField_montantCotisation.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_montantCotisation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(grp_TField_montantCotisation, gridBagConstraints);

        grp_Label_montantCotisation.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_montantCotisation.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.ModuleClient", currentUser.getLangage()).getString("commission"));
        grp_Label_montantCotisation.setMinimumSize(new java.awt.Dimension(100, 14));
        grp_Label_montantCotisation.setPreferredSize(new java.awt.Dimension(110, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel2.add(grp_Label_montantCotisation, gridBagConstraints);

        grp_ADate_dateDeCotisation.setEnabled(false);
        grp_ADate_dateDeCotisation.setForm(this);
        grp_ADate_dateDeCotisation.setGrp_Comp_nextComponent(grp_TField_montantCotisation);
        grp_ADate_dateDeCotisation.setUser(parent.getCurrentUser());
        grp_ADate_dateDeCotisation.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_dateDeCotisation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel2.add(grp_ADate_dateDeCotisation, gridBagConstraints);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(7, 7, 213, 62);

        jLabel11.setText("%");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(240, 30, 40, 15);

        jPanel28.add(jPanel1);

        jPanel27.add(jPanel28, java.awt.BorderLayout.CENTER);

        add(jPanel27);

    }//GEN-END:initComponents

    private void grp_TField_mailPrincipalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_mailPrincipalFocusLost
        // TODO add your handling code here:

        if (grp_TField_mailPrincipal.getText() == "")
            grp_TField_mailPrincipal.setText("NotAddress");


    }//GEN-LAST:event_grp_TField_mailPrincipalFocusLost

    private void grp_TField_TvaNumComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_grp_TField_TvaNumComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_TField_TvaNumComponentHidden
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void componentResized(java.awt.event.ComponentEvent componentEvent) {
    }

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

    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {
    }

    private ValidateField validateAndDoPrevious = new ValidateField() {
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
    public void chargeData() {
    }


    /**
     * Demande d'une modification au serveur
     */
    public void dbUpdate() {
// client = new srcastra.astra.sys.classetransfert.clients.Clients_T();
//client.setCscleunik(clCleUnik);
// Panel des noms et Adresse
////////////////////////////////////////////
//client.setCsdatenaiss(csdatenaiss
        client.setCsreference(grp_TField_reference.getText());
// if(grp_LSelect_TitrePers.getCleUnik()!=0)
        client.setTscleunik(grp_LSelect_TitrePers.getCleUnik());
        client.setCsnom(grp_TField_nom.getText());
        client.setCsnom2(grp_TField_nom2.getText());
        client.setCsadresse(grp_TField_adresse.getText());
// if(grp_LSelect_codePostal.getCleUnik()!=0)
        client.setCxcleunik(grp_LSelect_codePostal.getCleUnik());
        client.setCslocalite(grp_TField_localite.getText());
// if(grp_LSelect_pays.getCleUnik()!=0)
        client.setPyscleunik(grp_LSelect_pays.getCleUnik());
        int selected = grp_ACBox_Bloque.isSelected() ? 1 : 0;
        client.setCsbloque(selected);

// Panel des numéros de phones & emails
///////////////////////////////////////////
        client.setCsmailprincip(grp_TField_mailPrincipal.getText());
        client.setCsmailsecond(grp_TField_mailsecondaire.getText());
        client.setCstelephonep(grp_TField_telephonePerso.getText());
        client.setCstelephones(grp_TField_telephoneBureau.getText());
        client.setCsfax(grp_TField_fax.getText());
        client.setCsgsm(grp_TField_gsm.getText());

// Panel d'info sup
///////////////////////////////////////////
// if(grp_LSelect_langue.getCleUnik()!=0)
        client.setLanguenom(grp_LSelect_langue.getText());
        System.out.println("LA LANGUE UPDATE EST :" + grp_LSelect_langue.getText());
        client.setLecleunik(grp_LSelect_langue.getCleUnik());

        client.setCsdatenaiss(grp_ADate_dateDeNaissance.getDate());

        System.out.println(grp_ADate_dateDeNaissance.toString());
        client.setTvatype(grp_ACB_TVAtype.getCleUnik());
        client.setCstvanum(grp_TField_TvaNum.getText());
// if(grp_LSelect_TvaRegime.getCleUnik()!=0)
        client.setCstvaregime(grp_LSelect_TvaRegime.getCleUnik());
        client.setCsbanque(grp_TField_CompteBanquaire.getText());
        client.setCscartecredit(grp_TField_carteCredit.getText());
        client.setCsdelaipaiem(Integer.parseInt(grp_TField_delaiPaiement.getText()));
        client.setCscodemailing(grp_TField_codeMailing.getText());
        System.out.println("------>>CLE UNIQUE DES GROUPEMENTS<<-------------" + grp_TField_Groupement.getCleUnik());
        client.setCsgecleunik(grp_TField_Groupement.getCleUnik());

// Panel de cotisation
///////////////////////////////////////////
        client.setCscodecotisateur(grp_TField_codeCotisateur.getText());
// -> client.setCsdatecotisation(grp_ADate_dateDeCotisation.getDate());
        try {
            client.setCsmontcotisation(Float.parseFloat(grp_TField_montantCotisation.getText()));
        } catch (NumberFormatException nn) {
            client.setCsmontcotisation(0f);
        }
        client.setCe_cleunik(grp_LSelect_Compte.getCleUnik());

        try {
            client.updateObject(serveur, currentUser);
//this.parent.setContextCleUnik();
            displayReadMode();
        } catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        } catch (Exception e) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
    }

    /**
     * Demande de sélection en vue d'une modification au serveur
     */
    public void dbSelectForUpdate() {
        try {
            clCleUnik = client.getCscleunik();
            Object obj = client.selectObjectForUpdate(serveur, currentUser, clCleUnik);
            client = (Clients_T) obj;
            updateAllFields();
            this.action = actionToolBar.ACT_MODIFY;
        } catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, this.currentUser);
// displayReadMode();
        } catch (java.rmi.RemoteException re) {
            System.out.println(re.getClass().toString());
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
//displayReadMode();
        } catch (Exception e) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
//displayReadMode();
        }

    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
        int rep = JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", currentUser.getLangage()).getString("eff_cli") + client.getCsnom());
        if (rep == JOptionPane.YES_OPTION) {
            try {
                this.client.deleteObject(serveur, this.currentUser);
                parent.cancelModule();
            } catch (ServeurSqlFailure se) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, this.currentUser);
            } catch (java.rmi.RemoteException re) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
            } catch (Exception e) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
            }
        }
    }

    /**
     * Demande d'une insertion au serveur
     */
    public void dbInsert() {
        client = new srcastra.astra.sys.classetransfert.clients.Clients_T();
// Panel des noms et Adresse
////////////////////////////////////////////
        client.setCsreference(grp_TField_reference.getText());
        client.setTscleunik(grp_LSelect_TitrePers.getCleUnik());
        client.setCsnom(grp_TField_nom.getText());
        client.setCsnom2(grp_TField_nom2.getText());
        client.setCsadresse(grp_TField_adresse.getText());
        client.setCxcleunik(grp_LSelect_codePostal.getCleUnik());
        client.setCslocalite(grp_TField_localite.getText());
        client.setPyscleunik(grp_LSelect_pays.getCleUnik());
        int selected = grp_ACBox_Bloque.isSelected() ? 1 : 0;
        client.setCsbloque(selected);

// Panel des numéros de phones & emails
///////////////////////////////////////////
        client.setCsmailprincip(grp_TField_mailPrincipal.getText());
        client.setCsmailsecond(grp_TField_mailsecondaire.getText());
        client.setCstelephonep(grp_TField_telephonePerso.getText());
        client.setCstelephones(grp_TField_telephoneBureau.getText());
        client.setCsfax(grp_TField_fax.getText());
        client.setCsgsm(grp_TField_gsm.getText());

// Panel d'info sup
///////////////////////////////////////////
        client.setLanguenom(grp_LSelect_langue.getText());
        client.setLecleunik(grp_LSelect_langue.getCleUnik());
        client.setCsdatenaiss(grp_ADate_dateDeNaissance.getDate());
        client.setTvatype(grp_ACB_TVAtype.getCleUnik());
        client.setCstvanum(grp_TField_TvaNum.getText());
        client.setCstvaregime(grp_LSelect_TvaRegime.getCleUnik());
        client.setCsbanque(grp_TField_CompteBanquaire.getText());
        client.setCscartecredit(grp_TField_carteCredit.getText());
        try {
            client.setCsdelaipaiem(Integer.parseInt(grp_TField_delaiPaiement.getText()));
        } catch (NumberFormatException nn) {
            client.setCsdelaipaiem(0);
        }
        client.setCscodemailing(grp_TField_codeMailing.getText());
        client.setCsgecleunik(grp_TField_Groupement.getCleUnik());
        client.setCe_cleunik(grp_LSelect_Compte.getCleUnik());

// Panel de cotisation
///////////////////////////////////////////
        client.setCscodecotisateur(grp_TField_codeCotisateur.getText());
// -> client.setCsdatecotisation(grp_ADate_dateDeCotisation.getDate());
        try {
            client.setCsmontcotisation(Float.parseFloat(grp_TField_montantCotisation.getText()));
        } catch (NumberFormatException nn) {
            client.setCsmontcotisation(0);
        }

        try {
            client.insertObject(serveur, currentUser);
            clCleUnik = client.getCscleunik();

            this.parent.setContextCleUnik(clCleUnik);
            if (((ClientModules) parent).getMediator() != null) {
                ((ClientModules) parent).getMediator().moduleAction(clCleUnik, client.getCsnom());
//((ClientModules)parent).cancelModule2();
                return;
            }


            displayDisableMode();
            this.parent.nextScreen(PANE_NUMBER, true);
        } catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        } catch (Exception e) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
    }

    /**
     * Demande de sélection au serveur
     */
    public void dbSelect() {
        try {
            Object obj = new Clients_T().selectObject(serveur, currentUser, clCleUnik);
            if (obj != null) {
                client = (Clients_T) obj;
                clCleUnik = client.getCscleunik();
                updateAllFields();
            }
        } catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        } catch (Exception e) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateAllFields(Object donnee) {
    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
// toolbar
        parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_CANCEL});
// fin de toolbar
        init();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setText("");
        }

// -> sélection du premier élément dans la liste des groupements
        grp_Client_groupementPane.setClGroupementCleUnik(-2);

        action = actionToolBar.ACT_INSERT;
        grp_TField_montantCotisation.setLastFocusedComponent(true);
        grp_LSelect_TitrePers.setEnabled(true);
        tb_interaction.enableValidateActionListener(true);
        requestFocus();
        updateUI();
        prepareInsert();
        grp_LSelect_TitrePers.requestFocus();
    }

    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
        tb_interaction.enableValidateActionListener(false);
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
        }
    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
        grp_TField_reference.setText("" + client.getCsreference());
        grp_TField_nom.setText("" + client.getCsnom());
        grp_TField_nom2.setText("" + client.getCsnom2());
        grp_TField_adresse.setText("" + client.getCsadresse());
        grp_LSelect_Compte.setCleUnik(client.getCe_cleunik());
//  grp_LSelect_TitrePers.setText(client.getTitrenom());
        grp_LSelect_TitrePers.setCleUnik(client.getTscleunik());
//grp_LSelect_codePostal.setText(client.getCodenom());
        grp_LSelect_codePostal.setCleUnik(client.getCxcleunik());
        grp_TField_localite.setText("" + client.getCslocalite());
//grp_LSelect_pays.setText(client.getPysnom());
        grp_LSelect_pays.setCleUnik(client.getPyscleunik());
        int cb = client.getCsbloque();
        switch (cb) {
            case 0:
                grp_ACBox_Bloque.setSelected(false);
                break;
            case 1:
                grp_ACBox_Bloque.setSelected(true);
                break;
        }
        grp_TField_mailPrincipal.setText("" + client.getCsmailprincip());
        grp_TField_mailsecondaire.setText("" + client.getCsmailsecond());
        grp_TField_telephonePerso.setText("" + client.getCstelephonep());
        grp_TField_telephoneBureau.setText("" + client.getCstelephones());
        grp_TField_fax.setText("" + client.getCsfax());
        grp_TField_gsm.setText("" + client.getCsgsm());
        System.out.println("LA LANGUE DANS UPDATEB ALL FIELDS EST :" + client.getLanguenom());
// grp_LSelect_langue.setText(client.getLanguenom());
        grp_LSelect_langue.setCleUnik(client.getLecleunik());

        System.out.println(client.getCsdatenaiss().toString());


        grp_ADate_dateDeNaissance.setDate(client.getCsdatenaiss());
        grp_ACB_TVAtype.setCleUnik(client.getTvatype());
        grp_TField_TvaNum.setText("" + client.getCstvanum());
//grp_LSelect_TvaRegime.setText(client.getTvaregimenom());
        grp_LSelect_TvaRegime.setCleUnik(client.getCstvaregime());
        grp_TField_CompteBanquaire.setText("" + client.getCsbanque());
        grp_TField_carteCredit.setText("" + client.getCscartecredit());
        grp_TField_delaiPaiement.setText("" + client.getCsdelaipaiem());
        grp_TField_codeMailing.setText("" + client.getCscodemailing());
        grp_TField_Groupement.loadContentString(client.getCsgecleunik());
        grp_TField_codeCotisateur.setText("" + client.getCscodecotisateur());
        grp_ADate_dateDeCotisation.setDate(client.getCsdatecotisation());
        grp_TField_montantCotisation.setText("" + client.getCsmontcotisation());
    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
// toolbar
        tb_interaction.enableValidateActionListener(false);
        parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_MODIFY,
                actionToolBar.DO_CANCEL,
//actionToolBar.DO_PREVIOUS,
                actionToolBar.DO_DELETE});
        action = actionToolBar.ACT_READ;
        parent.enabledTabbedPane(true);
// fin de toolbar
        init();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
        grp_TField_montantCotisation.setLastFocusedComponent(true);
        this.requestFocus();
        dbSelect();

// -> sélection du premier élément dans la liste des groupements
// grp_Client_groupementPane.setClGroupementCleUnik();

        parent.chargeStatusPanel(new String[]{client.getCsreference(),
                client.getCsnom(),
                "",
                client.getCsmailprincip(),
                client.getCstelephonep(),
                client.getCsfax()});

    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
// toolbar
        dbSelectForUpdate();
        if (this.action == actionToolBar.ACT_MODIFY) {
            parent.setCurrentActionEnabled(new int[]{actionToolBar.DO_PREVIOUS,
                    actionToolBar.DO_CANCEL});
// fin de toolbar
// this.action = actionToolBar.ACT_MODIFY;
            parent.enabledTabbedPane(false);
            for (int i = 0; i < composantToVerif.length; i++) {

                composantToVerif[i].setEnabled(true);
                composantToVerif[i].setLastFocusedComponent(true);

            }
            tb_interaction.enableValidateActionListener(true);
            grp_LSelect_TitrePers.requestFocus();
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void doPrint() {
    }

    public void doClose() {
    }

    public void doModify() {
        displayUpdateMode();
    }

    public void doNext() {
    }

    public void doCancel() {
        switch (action) {
            case ActionToolBar.ACT_READ:
                requestFocus();
                parent.cancelModule();
                break;
            case ActionToolBar.ACT_INSERT:
                displayDisableMode();
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

    public void doCreate() {
    }

    public void doHelp() {
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

    public void doAccept() {

    }

    public void doDelete() {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int action;
    private int clCleUnik;
    private ActionToolBar actionToolBar;
    /**
     * pour 1 seul initiation du panneau
     */
    public boolean initOnce = false;
    private AstraComponent[] composantToVerif;
    private srcastra.astra.sys.classetransfert.clients.Clients_T client;
    private Loginusers_T currentUser;
    private boolean checkValidity = false;
    private MainScreenModule parent;
    private astrainterface serveur;
    private ClientGroupementPane grp_Client_groupementPane;
    private ToolBarInteraction tb_interaction;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * numéro du panneau (cfr séquence)
     */
    public static int PANE_NUMBER = 0;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.combobox.liste.Liste grp_ACB_TVAtype;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_ACBox_Bloque;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_dateDeCotisation;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_dateDeNaissance;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Compte;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_TitrePers;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_TvaRegime;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_codePostal;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_langue;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_pays;
    private javax.swing.JLabel grp_Label_Bloque;
    private javax.swing.JLabel grp_Label_TitrePers;
    private javax.swing.JLabel grp_Label_adresse;
    private javax.swing.JLabel grp_Label_codeCotisateur;
    private javax.swing.JLabel grp_Label_codePostal;
    private javax.swing.JLabel grp_Label_dateDeCotisation;
    private javax.swing.JLabel grp_Label_dateDeNaissance;
    private javax.swing.JLabel grp_Label_fax;
    private javax.swing.JLabel grp_Label_gsm;
    private javax.swing.JLabel grp_Label_langue;
    private javax.swing.JLabel grp_Label_localite;
    private javax.swing.JLabel grp_Label_mailPrincipal;
    private javax.swing.JLabel grp_Label_mailsecondaire;
    private javax.swing.JLabel grp_Label_montantCotisation;
    private javax.swing.JLabel grp_Label_nom;
    private javax.swing.JLabel grp_Label_nom2;
    private javax.swing.JLabel grp_Label_pays;
    private javax.swing.JLabel grp_Label_reference;
    private javax.swing.JLabel grp_Label_telephoneBureau;
    private javax.swing.JLabel grp_Label_telephonePerso;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_CompteBanquaire;
    private srcastra.astra.gui.components.textFields.ATextFieldWithButton grp_TField_Groupement;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_TvaNum;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_adresse;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_carteCredit;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_codeCotisateur;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_codeMailing;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_delaiPaiement;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_fax;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_gsm;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_localite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_mailPrincipal;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_mailsecondaire;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_montantCotisation;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom2;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_reference;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_telephoneBureau;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_telephonePerso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
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

    public int[] getDefaultActionToolBarMask() {
        return new int[0];
    }

    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * <p/>
     * <p/>
     * 0 pour lecture
     * <p/>
     * <p/>
     * 1 pour création
     * <p/>
     * <p/>
     * 2 pour modification
     *
     * @param action type d'action
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * <p/>
     * <p/>
     * au modules : création par partie ou modification
     *
     * @param frCleUnik la clé unique
     */
    public void setFrCleunik(int frCleUnik) {
        this.clCleUnik = frCleUnik;
    }

    /**
     * Sert à recevoir le titre de son parent
     * <p/>
     * <p/>
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        try {
            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("GI_titrePane");
        } catch (java.util.MissingResourceException mre) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);
            return "";
        }
    }

    public void setThisAsToolBarComponent() {
        System.out.println("sa race en short");
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
        return new String[]{grp_TField_mailPrincipal.getText(), grp_TField_mailsecondaire.getText()};
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
}

