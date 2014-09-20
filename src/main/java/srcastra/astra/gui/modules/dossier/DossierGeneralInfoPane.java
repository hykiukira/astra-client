/*


 * DossierGeneralInfoPane.java


 *


 * Created on 27 août 2002, 13:02


 */


package srcastra.astra.gui.modules.dossier;


import srcastra.astra.gui.modules.dossier.utils.PassagerManager;

// Interfaces


import srcastra.astra.gui.modules.InternScreenModule;


import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;


import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;

// Listeners


import java.awt.event.ComponentEvent;


import java.awt.event.ComponentListener;


import java.awt.event.FocusListener;


import java.awt.event.FocusEvent;


import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;

// srcastra importations


import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;


import srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierPassagerTableModel;


import srcastra.astra.gui.components.AstraComponent;


import srcastra.astra.gui.sys.tableModel.listSelectorTableModel.ClientTableModel;


import srcastra.astra.gui.sys.formVerification.ListSelectorMask;


import srcastra.astra.gui.components.actions.ToolBarInteraction;


import srcastra.astra.gui.components.fx.*;


import srcastra.astra.gui.sys.formVerification.*;


import srcastra.astra.sys.classetransfert.dossier.*;


import srcastra.astra.sys.rmi.DossierRmiInterface;


import srcastra.astra.sys.classetransfert.utils.*;


import srcastra.astra.gui.components.date.aDate.*;


import srcastra.astra.gui.components.combobox.listSelector.*;


import srcastra.astra.gui.sys.ErreurScreenLibrary;


import java.util.Calendar;


import javax.swing.JOptionPane;


import srcastra.astra.sys.rmi.Exception.*;


import srcastra.astra.gui.sys.utils.CursorChange;


import java.awt.*;


import srcastra.astra.sys.classetransfert.utils.*;


import srcastra.astra.gui.event.ValidateField;


import srcastra.astra.gui.sys.listModel.dossierListModel.TypeBListModel;


import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.gui.components.date.thedate.*;
import srcastra.astra.gui.sys.MessageManager;
import srcastra.astra.gui.modules.Mailing.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.*;


/**
 * @author Sébastien
 */


public class DossierGeneralInfoPane extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer, ComponentListener, InterfaceModuleDossier, srcastra.astra.sys.manipuleclient.ClientConstante, ADateListener, MailInterface {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CONSTRUCTOR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Creates new form DossierGeneralInfoPane
     */
    int cli;
    MessageManager m_manager;
    srcastra.astra.gui.components.combobox.liste2.Mediator mediator;

    public DossierGeneralInfoPane(DossierMainScreenModule parent) {
        this.parent = parent;
        this.interParent = (InterfaceModuleDossier) parent;
        init();
        new ManageMailingFrame(this, this, parent.getSuperOwner(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());
        initListes();
        m_manager = new MessageManager();
        requestFocus();
        setMediator(parent.getMainframe().getMediator2());
        grp_TField_Dossier_nbreDeJour.addKeyListener(calculDate);
        grp_LSelect_Dossier_ClientContractuel.setNbr_lettre_avant_affichage(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNbr_lettre_liste());
        grp_LSelect_Dossier_ClientFacturable.setNbr_lettre_avant_affichage(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNbr_lettre_liste());
        // grp_TBut_ClientContractuel.requestFocus();
        //parent.calculTotal();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// INITIALISATION

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void initListes() {
        grp_ACB_Dossier_TypeB.setServeur(parent.getServeur());
        grp_ACB_Dossier_TypeB.setLogin(parent.getCurrentUser());
        grp_ACB_Dossier_TypeB.setModel(new srcastra.astra.gui.components.combobox.liste.SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), produit_T.AV, parent, srcastra.astra.sys.configuration.AbstractRequete.DIVERS, parent.getMainframe().getDivers()));
        grp_ACB_Dossier_TypeB.init2();
        grp_ACB_Dossier_TypeB.setColToSearch(3);
        grp_LSelect_Dossier_ClientContractuel.setMediator(parent.getMainframe().getMediator2());
        parent.getMainframe().getMediator2().registerListe(grp_LSelect_Dossier_ClientContractuel);

        grp_LSelect_Dossier_ClientContractuel.setServeur(parent.getServeur());
        grp_LSelect_Dossier_ClientContractuel.setName("lc");
        grp_LSelect_Dossier_ClientContractuel.setLogin(parent.getCurrentUser());
        grp_LSelect_Dossier_ClientContractuel.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Dossier_ClientContractuel.init2();
        grp_LSelect_Dossier_ClientContractuel.setLeft(true);

        grp_LSelect_Dossier_ClientFacturable.setMediator(parent.getMainframe().getMediator2());
        grp_LSelect_Dossier_ClientFacturable.setName("lf");
        parent.getMainframe().getMediator2().registerListe2(grp_LSelect_Dossier_ClientFacturable);
        grp_LSelect_Dossier_ClientFacturable.setServeur(parent.getServeur());
        grp_LSelect_Dossier_ClientFacturable.setLogin(parent.getCurrentUser());
        grp_LSelect_Dossier_ClientFacturable.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Dossier_ClientFacturable.init2();
        grp_LSelect_Dossier_ClientFacturable.setLeft(true);


    }


    private void init() {


        initComponents();


        postInit();

        // -> composant to verif


        composantToVerif = new AstraComponent[]{grp_ADate_Dossier_DateDepart, grp_ADate_Dossier_DateFin, grp_TField_Dossier_nbreDeJour,


                grp_TField_Dossier_NbreDeNuite, grp_ACB_Dossier_TypeB,


                grp_LSelect_Dossier_ClientContractuel, grp_LSelect_Dossier_ClientFacturable};
        tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);
        tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_WITH_SWITCH);
        // for (int i=0; i < composantToVerif.length; i++) {
        //   composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
        //}                                                
        this.addComponentListener(this);
        // Set the border Manager & fx listeners
        fx_manager = new JPanelSelectionFxManager();


        javax.swing.border.LineBorder lb = new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 5);


        javax.swing.border.TitledBorder tb_dossier = new javax.swing.border.TitledBorder(lb, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("Dossier_titre"));


        javax.swing.border.TitledBorder tb_passager = new javax.swing.border.TitledBorder(lb, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("Passager_titre"));


        tb_dossier.setTitleFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 10));


        tb_passager.setTitleFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 10));


        JPanelSelectionFx fx_dossier = new JPanelSelectionFx(parent, grp_Pan_Dossier, tb_dossier, this);


        JPanelSelectionFx fx_passager = new JPanelSelectionFx(parent, grp_Pan_Passager, tb_passager, null);


        fx_manager.addJPanelSelectionFx(fx_dossier);


        fx_manager.addJPanelSelectionFx(fx_passager);


        grp_Pan_Passager.setFx_manager(fx_manager);

        // end of setting the border Manager & fx listeners


        grp_ADate_Dossier_DateDepart.addAdateListener(this);
        grp_ADate_Dossier_DateFin.addAdateListener(this);
        //  grp_LSelect_Dossier_ClientContractuel.addListSelectorListener(this.listSelectorListener);
        //grp_LSelect_Dossier_ClientFacturable.addListSelectorListener(this.listSelectorListener);
        grp_LSelect_Dossier_ClientContractuel.addActionListener(clientContractuelListener);
        grp_LSelect_Dossier_ClientFacturable.addValidateFieldListener(validateAndDoPrevious);
        setDocument();
    }


    public void setDocument() {
        grp_TField_Dossier_nbreDeJour.setDocument(new IntegerMask(1, 10, parent.getCurrentUser().getLangage()));
        grp_TField_Dossier_NbreDeNuite.setDocument(new IntegerMask(1, 10, parent.getCurrentUser().getLangage()));
    }


    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        grp_Pan_Dossier = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        grp_Label_DateFin = new javax.swing.JLabel();
        grp_Label_DateDepart = new javax.swing.JLabel();
        grp_ADate_Dossier_DateDepart = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_ADate_Dossier_DateFin = new srcastra.astra.gui.components.date.thedate.ADate();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        grp_TField_Dossier_nbreDeJour = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel2 = new javax.swing.JLabel();
        grp_TField_Dossier_NbreDeNuite = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_TField_Dossier_User = new srcastra.astra.gui.components.textFields.ATextField();
        grp_ACB_Dossier_TypeB = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        grp_Label_ClientContractuel = new javax.swing.JLabel();
        grp_Label_ClientFacturable = new javax.swing.JLabel();
        grp_LSelect_Dossier_ClientContractuel = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        grp_LSelect_Dossier_ClientFacturable = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        grp_Pan_Detail = new javax.swing.JPanel();
        grp_Pan_Clients = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        grp_TBut_ClientContractuel = new javax.swing.JToggleButton();
        grp_TBut_ClientFacturable = new javax.swing.JToggleButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        grp_Label_Client_reference = new javax.swing.JLabel();
        grp_Label_Client_nom = new javax.swing.JLabel();
        grp_Label_Client_adresse = new javax.swing.JLabel();
        grp_Label_Client_codepostal = new javax.swing.JLabel();
        grp_Label_Client_localité = new javax.swing.JLabel();
        grp_TField_Client_reference = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_nom = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_adresse = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_codepostal = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_localité = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        grp_Label_Client_email = new javax.swing.JLabel();
        grp_Label_Client_telephone = new javax.swing.JLabel();
        grp_Label_Client_langue = new javax.swing.JLabel();
        grp_Label_Client_dateDeNaissance = new javax.swing.JLabel();
        grp_TField_Client_email = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_telephone = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_langue = new srcastra.astra.gui.components.textFields.ATextField();
        grp_TField_Client_dateDeNaissance = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Label_Client_pays = new javax.swing.JLabel();
        grp_TField_Client_pays = new srcastra.astra.gui.components.textFields.ATextField();

        setLayout(new java.awt.BorderLayout());

        grp_Pan_Dossier.setLayout(new java.awt.GridLayout(1, 0));

        grp_Pan_Dossier.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("Dossier_titre"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel4.setLayout(new java.awt.GridBagLayout());

        grp_Label_DateFin.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_DateFin.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_ADate_Dossier_DateFin"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel4.add(grp_Label_DateFin, gridBagConstraints);

        grp_Label_DateDepart.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_DateDepart.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_ADate_Dossier_DateDepart"));
        grp_Label_DateDepart.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel4.add(grp_Label_DateDepart, gridBagConstraints);

        grp_ADate_Dossier_DateDepart.setCheckUptodate(true);
        grp_ADate_Dossier_DateDepart.setEnabled(false);
        grp_ADate_Dossier_DateDepart.setForm(this);
        grp_ADate_Dossier_DateDepart.setGrp_Comp_nextComponent(grp_ADate_Dossier_DateFin);
        grp_ADate_Dossier_DateDepart.setUser(parent.getCurrentUser());
        grp_ADate_Dossier_DateDepart.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_Dossier_DateDepart.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_ADate_Dossier_DateDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_ADate_Dossier_DateDepartActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel4.add(grp_ADate_Dossier_DateDepart, gridBagConstraints);

        grp_ADate_Dossier_DateFin.setDateBeforeComp(grp_ADate_Dossier_DateDepart);
        grp_ADate_Dossier_DateFin.setEnabled(false);
        grp_ADate_Dossier_DateFin.setForm(this);
        grp_ADate_Dossier_DateFin.setGrp_Comp_nextComponent(grp_TField_Dossier_nbreDeJour);
        grp_ADate_Dossier_DateFin.setUser(parent.getCurrentUser());
        grp_ADate_Dossier_DateFin.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ADate_Dossier_DateFin.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_ADate_Dossier_DateFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_ADate_Dossier_DateFinActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel4.add(grp_ADate_Dossier_DateFin, gridBagConstraints);

        jPanel2.add(jPanel4);

        grp_Pan_Dossier.add(jPanel2);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Dossier_nbreDeJour"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel5.add(jLabel1, gridBagConstraints);

        grp_TField_Dossier_nbreDeJour.setEnabled(false);
        grp_TField_Dossier_nbreDeJour.setGrp_Comp_nextComponent(grp_TField_Dossier_NbreDeNuite);
        grp_TField_Dossier_nbreDeJour.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_Dossier_nbreDeJour.setPreferredSize(new java.awt.Dimension(50, 18));
        grp_TField_Dossier_nbreDeJour.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Dossier_nbreDeJour.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 11);
        jPanel5.add(grp_TField_Dossier_nbreDeJour, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Dossier_NbreDeNuite"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel5.add(jLabel2, gridBagConstraints);

        grp_TField_Dossier_NbreDeNuite.setEnabled(false);
        grp_TField_Dossier_NbreDeNuite.setGrp_Comp_nextComponent(grp_ACB_Dossier_TypeB);
        grp_TField_Dossier_NbreDeNuite.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_Dossier_NbreDeNuite.setPreferredSize(new java.awt.Dimension(50, 18));
        grp_TField_Dossier_NbreDeNuite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Dossier_NbreDeNuite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 11);
        jPanel5.add(grp_TField_Dossier_NbreDeNuite, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_ACB_Dossier_TypeB"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Dossier_User"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel5.add(jLabel4, gridBagConstraints);

        grp_TField_Dossier_User.setEnabled(false);
        grp_TField_Dossier_User.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientContractuel);
        grp_TField_Dossier_User.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_TField_Dossier_User.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Dossier_User.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_TField_Dossier_User, gridBagConstraints);

        grp_ACB_Dossier_TypeB.setCanbenull(true);
        grp_ACB_Dossier_TypeB.setEnabled(false);
        grp_ACB_Dossier_TypeB.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientContractuel);
        grp_ACB_Dossier_TypeB.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_ACB_Dossier_TypeB.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ACB_Dossier_TypeB.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel5.add(grp_ACB_Dossier_TypeB, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel5);

        grp_Pan_Dossier.add(jPanel3);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel14.setLayout(new java.awt.GridBagLayout());

        grp_Label_ClientContractuel.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_ClientContractuel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_LSelect_Dossier_ClientContractuel"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel14.add(grp_Label_ClientContractuel, gridBagConstraints);

        grp_Label_ClientFacturable.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_ClientFacturable.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_but_Facturable"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel14.add(grp_Label_ClientFacturable, gridBagConstraints);

        grp_LSelect_Dossier_ClientContractuel.setEnabled(false);
        grp_LSelect_Dossier_ClientContractuel.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientFacturable);
        grp_LSelect_Dossier_ClientContractuel.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Dossier_ClientContractuel.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel14.add(grp_LSelect_Dossier_ClientContractuel, gridBagConstraints);

        grp_LSelect_Dossier_ClientFacturable.setEnabled(false);
        grp_LSelect_Dossier_ClientFacturable.setIsLastComponent(true);
        grp_LSelect_Dossier_ClientFacturable.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Dossier_ClientFacturable.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel14.add(grp_LSelect_Dossier_ClientFacturable, gridBagConstraints);

        jPanel13.add(jPanel14);

        grp_Pan_Dossier.add(jPanel13);

        add(grp_Pan_Dossier, java.awt.BorderLayout.NORTH);

        grp_Pan_Detail.setLayout(new java.awt.BorderLayout());

        grp_Pan_Clients.setLayout(new java.awt.GridLayout(1, 0));

        grp_Pan_Clients.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("Client_titre"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel16.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(grp_TBut_ClientContractuel);
        grp_TBut_ClientContractuel.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_TBut_ClientContractuel.setSelected(true);
        grp_TBut_ClientContractuel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_but_contractuel"));
        grp_TBut_ClientContractuel.setRequestFocusEnabled(false);
        grp_TBut_ClientContractuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_TBut_ClientContractuelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel16.add(grp_TBut_ClientContractuel, gridBagConstraints);

        buttonGroup1.add(grp_TBut_ClientFacturable);
        grp_TBut_ClientFacturable.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_TBut_ClientFacturable.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_but_Facturable"));
        grp_TBut_ClientFacturable.setRequestFocusEnabled(false);
        grp_TBut_ClientFacturable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_TBut_ClientFacturableActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel16.add(grp_TBut_ClientFacturable, gridBagConstraints);

        jPanel15.add(jPanel16);

        grp_Pan_Clients.add(jPanel15);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel12.setLayout(new java.awt.GridBagLayout());

        grp_Label_Client_reference.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_reference.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_reference"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel12.add(grp_Label_Client_reference, gridBagConstraints);

        grp_Label_Client_nom.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_nom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_nom"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel12.add(grp_Label_Client_nom, gridBagConstraints);

        grp_Label_Client_adresse.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_adresse.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_adresse"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel12.add(grp_Label_Client_adresse, gridBagConstraints);

        grp_Label_Client_codepostal.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_codepostal.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_codepostal"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel12.add(grp_Label_Client_codepostal, gridBagConstraints);

        grp_Label_Client_localité.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_localité.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_localité"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel12.add(grp_Label_Client_localité, gridBagConstraints);

        grp_TField_Client_reference.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel12.add(grp_TField_Client_reference, gridBagConstraints);

        grp_TField_Client_nom.setEnabled(false);
        grp_TField_Client_nom.setPreferredSize(new java.awt.Dimension(130, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel12.add(grp_TField_Client_nom, gridBagConstraints);

        grp_TField_Client_adresse.setEnabled(false);
        grp_TField_Client_adresse.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel12.add(grp_TField_Client_adresse, gridBagConstraints);

        grp_TField_Client_codepostal.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel12.add(grp_TField_Client_codepostal, gridBagConstraints);

        grp_TField_Client_localité.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel12.add(grp_TField_Client_localité, gridBagConstraints);

        jPanel10.add(jPanel12);

        grp_Pan_Clients.add(jPanel10);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel9.setLayout(new java.awt.GridBagLayout());

        grp_Label_Client_email.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_email.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_email"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel9.add(grp_Label_Client_email, gridBagConstraints);

        grp_Label_Client_telephone.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_telephone.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_telephone"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel9.add(grp_Label_Client_telephone, gridBagConstraints);

        grp_Label_Client_langue.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_langue.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_langue"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel9.add(grp_Label_Client_langue, gridBagConstraints);

        grp_Label_Client_dateDeNaissance.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_dateDeNaissance.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_dateDeNaissance"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel9.add(grp_Label_Client_dateDeNaissance, gridBagConstraints);

        grp_TField_Client_email.setEnabled(false);
        grp_TField_Client_email.setPreferredSize(new java.awt.Dimension(130, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel9.add(grp_TField_Client_email, gridBagConstraints);

        grp_TField_Client_telephone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel9.add(grp_TField_Client_telephone, gridBagConstraints);

        grp_TField_Client_langue.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel9.add(grp_TField_Client_langue, gridBagConstraints);

        grp_TField_Client_dateDeNaissance.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel9.add(grp_TField_Client_dateDeNaissance, gridBagConstraints);

        grp_Label_Client_pays.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_Client_pays.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierGeneralInfoPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Client_pays"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel9.add(grp_Label_Client_pays, gridBagConstraints);

        grp_TField_Client_pays.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel9.add(grp_TField_Client_pays, gridBagConstraints);

        jPanel11.add(jPanel9);

        grp_Pan_Clients.add(jPanel11);

        grp_Pan_Detail.add(grp_Pan_Clients, java.awt.BorderLayout.NORTH);

        add(grp_Pan_Detail, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    private void grp_ADate_Dossier_DateDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_ADate_Dossier_DateDepartActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_grp_ADate_Dossier_DateDepartActionPerformed

    private void grp_ADate_Dossier_DateFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_ADate_Dossier_DateFinActionPerformed
        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "ok je suis dans l'action");
        if ((grp_ADate_Dossier_DateFin.isUnknown() || grp_ADate_Dossier_DateFin.isOpen()) && (!grp_ADate_Dossier_DateDepart.isUnknown() && !grp_ADate_Dossier_DateDepart.isOpen())) {
            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "ok next component for date changed");
            grp_ADate_Dossier_DateFin.setGrp_Comp_nextComponent(grp_TField_Dossier_nbreDeJour);
        }// Add your handling code here:
    }//GEN-LAST:event_grp_ADate_Dossier_DateFinActionPerformed


    private void grp_TBut_ClientFacturableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_TBut_ClientFacturableActionPerformed


        resetDossierClientFacturable(); // Add your handling code here:


    }//GEN-LAST:event_grp_TBut_ClientFacturableActionPerformed


    private void grp_TBut_ClientContractuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_TBut_ClientContractuelActionPerformed


        resetDossierClientContract();// Add your handling code here:


    }//GEN-LAST:event_grp_TBut_ClientContractuelActionPerformed

    public void refreshPassager() {
        if (grp_Pan_Passager != null)
            grp_Pan_Passager.refreshTable();
    }


    public void postInit() {


        grp_Pan_Passager = new DossierPassagerPane(parent, this);


        grp_Pan_Passager.setPmanager(new PassagerManager());


        grp_Pan_Detail.add(grp_Pan_Passager, java.awt.BorderLayout.CENTER);


    }

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


        fx_manager.needReselect();


        if (fx_manager.getSelectedPanel().getPanel().equals(grp_Pan_Dossier) && action != ActionToolBar.ACT_INSERT) {


            displayReadMode();


        }


        if (action == ActionToolBar.ACT_INSERT) {


            grp_ADate_Dossier_DateDepart.requestFocus();


        }


    }


    private FocusListener focusThePanel = new FocusListener() {


        public void focusGained(FocusEvent evt) {


        }


        public void focusLost(FocusEvent evt) {


        }


    };

    /*private AdateListener adateListener=new AdateListener(){


       public void dayFieldSkiped(AdateEvent evt){


           dayFieldSkipedinside(evt);         


       }


    };*/


    private ListSelectorListener listSelectorListener = new ListSelectorListener() {


        public void focusLost(ListSelectorEvent evt) {


            listSelectorfocusLost(evt);


        }


        public void focusGained(ListSelectorEvent evt) {


        }


    };


    private void loadClient() {
        try {
            Dossier_T tmpDossier = getDossier();
            tmpDossier.setClientContractuel(interParent.getServeurDossier().getDossierClient(grp_LSelect_Dossier_ClientContractuel.getCleUnik(), interParent.getCurrentUser()));
            tmpDossier.setClientFacturable(tmpDossier.getClientContractuel());

            resetDossierClientContract();
            tmpDossier.setClientFacturable(interParent.getServeurDossier().getDossierClient(grp_LSelect_Dossier_ClientFacturable.getCleUnik(), interParent.getCurrentUser()));
            resetDossierClientFacturable();
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }

    }

    private void listSelectorfocusLost(ListSelectorEvent evt) {


        System.out.println("\n\n\n listSelectorFocusLost : " + evt.getSource().getClass().getName());


        Dossier_T tmpDossier = getDossier();


        if (grp_LSelect_Dossier_ClientContractuel.equals(evt.getSource())) {


            try {


                tmpDossier.setClientContractuel(interParent.getServeurDossier().getDossierClient(grp_LSelect_Dossier_ClientContractuel.getCleUnik(), interParent.getCurrentUser()));
                tmpDossier.setClientFacturable(tmpDossier.getClientContractuel());
                setDossier(tmpDossier);
                resetDossierClientContract();
            }
            catch (java.rmi.RemoteException re) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
            }
        } else if (grp_LSelect_Dossier_ClientFacturable.equals(evt.getSource())) {
            System.out.println("---> source = client facturable !!!");
            if (grp_LSelect_Dossier_ClientFacturable.getCleUnik() != grp_LSelect_Dossier_ClientContractuel.getCleUnik()) {
                try {
                    System.out.println("---> client fact != client contr");
                    System.out.println("cle du facturable" + interParent.getServeurDossier().getDossierClient(grp_LSelect_Dossier_ClientFacturable.getCleUnik(), interParent.getCurrentUser()).getCscleunik());
                    tmpDossier.setClientFacturable(interParent.getServeurDossier().getDossierClient(grp_LSelect_Dossier_ClientFacturable.getCleUnik(), interParent.getCurrentUser()));
                    System.out.println("---> Mon client facturable est : " + tmpDossier.getClientFacturable());
                    setDossier(tmpDossier);
                }
                catch (java.rmi.RemoteException re) {
                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
                }


            } else {


                System.out.println("client fact = client contr");


                tmpDossier.setClientFacturable(tmpDossier.getClientContractuel());


                setDossier(tmpDossier);


            }


        }


    }

    /*  if(evt.getSource()==grp_ADate_Dossier_DateDepart)


            this.date1=grp_ADate_Dossier_DateDepart.getDate();


        if(evt.getSource()==grp_ADate_Dossier_DateFin)


        {


            this.date2=grp_ADate_Dossier_DateDepart.getDate();


            if(this.date1!=null)


            {


             int nbrjour[]=CalculDate.renvDifferenceBetweenDate(this.date1,this.date2);


             grp_TField_Dossier_NbreDeNuite.setText(""+nbrjour[0]);


             grp_TField_Dossier_nbreDeJour.setText(""+nbrjour[1]);


            }





        }


    }*/

    // private srcastra.astra.sys.classetransfert.utils.Date date1;

    //private srcastra.astra.sys.classetransfert.utils.Date date2;

    /* private void dayFieldSkipedinside(AdateEvent evt){


        if(grp_ADate_Dossier_DateDepart.equals(evt.getSource()))


           {


               //System.out.println("[dayFieldSkipedinside] date de départ !");


                this.date1=grp_ADate_Dossier_DateDepart.getDate();


                grp_ADate_Dossier_DateDepart.getGrp_Comp_nextComponent().setEnabled(true);


                grp_ADate_Dossier_DateDepart.getGrp_Comp_nextComponent().requestFocus();


           }


           if(grp_ADate_Dossier_DateFin.equals(evt.getSource()))// && !grp_ADate_Dossier_DateFin.isOpen() && !grp_ADate_Dossier_DateFin.isUnknown())


           {


               //System.out.println("[dayFieldSkipedinside] date de fin !");


               this.date2=grp_ADate_Dossier_DateFin.getDate();


               if(this.date1!=null) {


                  // System.out.println("[dayFieldSkipedinside] date1 pas null !");


                // System.out.println("[daaaaaaaaaaate] "+this.date1.toString()+" "+this.date2.toString());


                   if (grp_ADate_Dossier_DateFin.isOpen() || grp_ADate_Dossier_DateFin.isUnknown()) {


                       grp_ADate_Dossier_DateFin.setStateIcon(0);


                       grp_TField_Dossier_NbreDeNuite.setText("0");


                       grp_TField_Dossier_nbreDeJour.setText("0"); 


                       grp_TField_Dossier_NbreDeNuite.setEnabled(true);


                       grp_TField_Dossier_NbreDeNuite.requestFocus();               


                   }


                   else {


                       int nbrjour[]=CalculDate.renvDifferenceBetweenDate(this.date1,this.date2);


                       if(nbrjour[0]>=0) {


                           //System.out.println("[dayFieldSkipedinside] nbre de jour > = 0!  ==> " + nbrjour[0] + ", " + nbrjour[1]);


                           grp_ADate_Dossier_DateFin.setStateIcon(0);


                           grp_TField_Dossier_NbreDeNuite.setText(""+nbrjour[0]);


                           grp_TField_Dossier_nbreDeJour.setText(""+nbrjour[1]); 


                           grp_TField_Dossier_NbreDeNuite.setEnabled(true);


                           grp_TField_Dossier_NbreDeNuite.requestFocus();               


                       }  


                       else {


                            //System.out.println("[dayFieldSkipedinside] nbre de jour < 0 !");


                            grp_ADate_Dossier_DateFin.setStateIcon(1);


                       }


                   }              


                }


        


              }


    }*/
    private java.awt.event.KeyAdapter calculDate = new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (!grp_ADate_Dossier_DateDepart.getDate().isOpen() && !grp_ADate_Dossier_DateDepart.getDate().isUnknow()) {
                int nbrjour = 0;
                try {
                    nbrjour = Integer.parseInt(grp_TField_Dossier_nbreDeJour.getText());
                } catch (NumberFormatException nn) {
                    nbrjour = 0;
                }
                if (nbrjour != 0)
                    grp_ADate_Dossier_DateFin.setDate(CalculDate.renvDateEcheance(grp_ADate_Dossier_DateDepart.getDate(), (-nbrjour + 1)));


                int nbrnuit = nbrjour - 1;
                grp_TField_Dossier_NbreDeNuite.setText("" + nbrnuit);
            }
        }
    };


    private ActionListener clientContractuelListener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            setNextListe(true);


        }
    };

    public void setNextListe(boolean sw) {
        if (sw) {
            String str = grp_LSelect_Dossier_ClientContractuel.getText();
            int key = grp_LSelect_Dossier_ClientContractuel.getCleUnik();
            grp_LSelect_Dossier_ClientFacturable.setModedisplay(true);
            grp_LSelect_Dossier_ClientFacturable.setText(str);
            grp_LSelect_Dossier_ClientFacturable.setCleUnik(key);
            grp_LSelect_Dossier_ClientFacturable.setModedisplay(false);
            grp_LSelect_Dossier_ClientFacturable.setEnabled(true);
        } else
            listeFactAction();


    }


    private ValidateField validateAndDoPrevious = new ValidateField() {


        public void actionPerformed(ActionEvent evt) {


            actionPerformed2(evt);


        }

        /*   System.out.println("[validateAndDoPrevious] ok j'y rentre");


            if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {


                System.out.println("[validateAndDoPrevious] ok j'execute !");


                requestFocus();


                try{


                    int[] tmp=new int[1];


                    tmp[0]=grp_LSelect_Dossier_ClientContractuel.getCleUnik();


                    parent.getDossier().setClientContractuel((Clients_T)parent.getServeur().ChargeClient(CHARGE_CLIENT,tmp,parent.getCurrentUser().getUrcleunik(),parent.getCurrentUser().getUrlmcleunik()));


                    if(grp_LSelect_Dossier_ClientFacturable.getCleUnik()==grp_LSelect_Dossier_ClientContractuel.getCleUnik())


                        parent.getDossier().setClientFacturable(parent.getDossier().getClientContractuel());


                    else{


                        tmp[0]=grp_LSelect_Dossier_ClientFacturable.getCleUnik();


                        parent.getDossier().setClientFacturable((Clients_T)parent.getServeur().ChargeClient(CHARGE_CLIENT,tmp,parent.getCurrentUser().getUrcleunik(),parent.getCurrentUser().getUrlmcleunik()));





                    }


                }


                catch(java.rmi.RemoteException rn)


                {


                  ErreurScreenLibrary.displayErreur(, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG,rn);


                }


                doPrevious();


            }


        }*/


    };

    public void listeFactAction() {
        System.out.println("[validateAndDoPrevious] ok j'y rentre");
        if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {
            System.out.println("[validateAndDoPrevious] ok j'execute !");
            requestFocus();
            try {
                if (grp_LSelect_Dossier_ClientContractuel.getCleUnik() == 0 || grp_LSelect_Dossier_ClientFacturable.getCleUnik() == 0) {
                    grp_LSelect_Dossier_ClientContractuel.requestFocus();
                    return;
                }
                int[] tmp = new int[1];
                tmp[0] = grp_LSelect_Dossier_ClientContractuel.getCleUnik();
                parent.getDossier().setClientContractuel((Clients_T) parent.getServeur().ChargeClient(CHARGE_CLIENT, tmp, parent.getCurrentUser().getUrcleunik(), parent.getCurrentUser().getUrlmcleunik()));
                if (grp_LSelect_Dossier_ClientFacturable.getCleUnik() == grp_LSelect_Dossier_ClientContractuel.getCleUnik())
                    parent.getDossier().setClientFacturable(parent.getDossier().getClientContractuel());
                else {
                    tmp[0] = grp_LSelect_Dossier_ClientFacturable.getCleUnik();
                    parent.getDossier().setClientFacturable((Clients_T) parent.getServeur().ChargeClient(CHARGE_CLIENT, tmp, parent.getCurrentUser().getUrcleunik(), parent.getCurrentUser().getUrlmcleunik()));
                }
            }
            catch (ServeurSqlFailure se) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
            }
            catch (java.rmi.RemoteException rn) {
                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);
            }
            doPrevious();
        }

    }

    public void actionPerformed2(ActionEvent evt) {
        listeFactAction();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

// => METHODE APPARENTE AU BEANS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void chargeData() {


    }


    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */


    public void dbDelete() {


        if (parent.getDossier().isNewreccord())


        {


            int rep = parent.getMessageManager().showConfirmDialog(this, "dos_annul_?2", "dos_annul", parent.getCurrentUser());


            if (rep == 0)


            {


                parent.setDossier(new Dossier_T());


                parent.cancelModule();


            }


        } else {


            int rep = parent.getMessageManager().showConfirmDialog(this, "dos_annul_?", "dos_annul", parent.getCurrentUser());


            if (rep == 0)


            {


                try {


                    parent.getServeurDossier().annulDossier(parent.getCurrentUser().getUrcleunik(), parent.getDossier().getDrcleunik());


                    parent.getMessageManager().showMessageDialog(this, "dos_anul_ok", "dos_annul", parent.getCurrentUser());


                    parent.cancelModule();


                } catch (ServeurSqlFailure se) {


                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


                }


                catch (java.rmi.RemoteException re) {


                    re.printStackTrace();


                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


                }// Add your handling code here:


            }


        }


    }


    /**
     * Demande d'une insertion au serveur
     */


    public void dbInsert() {

        /*    try{


                    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.WAIT_CURSOR));


                    java.util.Hashtable returnvalue=parent.getServeurDossier().insertDossier(parent.getCurrentUser().getUrcleunik(),parent.getDossier());


                    java.util.ArrayList returndossier=parent.getServeurDossier().chargeDossier(parent.getCurrentUser(),Integer.parseInt(returnvalue.get("id").toString()));


                    parent.setDossier((Dossier_T)returndossier.get(0));


                    parent.getDossier().setNewreccord(false);





                   // parent.getDossier().setDrcleunik(Integer.parseInt(returnvalue.get("id").toString()));


                   // parent.getDossier().setDrnumdos((String)returnvalue.get("numdos"));


                    parent.chargeStatusPanel();


                    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));


                    parent.getMessageManager().showMessageDialog(this,"dos_ins_ok","dos_ins",parent.getCurrentUser());


                }catch(ServeurSqlFailure se){


                  ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


                }


                catch (java.rmi.RemoteException re){


                    re.printStackTrace();


                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


                }// Add your handling code here:
        */

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
     * Demande d'une mrodification au serveur
     */


    public void dbUpdate() {

        /*   try{


                  parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.WAIT_CURSOR));


                  parent.getServeurDossier().modifyDossier(parent.getCurrentUser().getUrcleunik(),parent.getDossier(),parent.getCurrentUser());


                  java.util.ArrayList returndossier=parent.getServeurDossier().chargeDossier(parent.getCurrentUser(),parent.getDossier().getDrcleunik());


                  parent.setDossier((Dossier_T)returndossier.get(0));


                  parent.chargeStatusPanel();


                  parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));


                  parent.getMessageManager().showMessageDialog(this,"dos_mod_ok","dos_mod",parent.getCurrentUser());


                }catch(ServeurSqlFailure se){


                  parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));


                  ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


                }


                catch (java.rmi.RemoteException re) {


                    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));


                     re.printStackTrace();


                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


                }// Add your handling code here:







        */
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


        tb_interaction.enableValidateActionListener(true);


        action = ActionToolBar.ACT_INSERT;


        for (int i = 0; i < composantToVerif.length; i++) {


            composantToVerif[i].setEnabled(false);


            composantToVerif[i].setLastFocusedComponent(false);


        }


        grp_LSelect_Dossier_ClientFacturable.setLastFocusedComponent(true);


        grp_ADate_Dossier_DateDepart.setEnabled(true);


        grp_ADate_Dossier_DateDepart.requestFocus();


        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});


        grp_LSelect_Dossier_ClientContractuel.setModedisplay(false);


        grp_LSelect_Dossier_ClientFacturable.setModedisplay(false);


    }


    /**
     * Affichage en mode Lecture
     */


    public void displayReadMode() {


        System.out.println("######################## DISPLAY READ MODE YYYYYYOOOOOOOOOOOOOOOO ##############################################");


        tb_interaction.enableValidateActionListener(false);


        grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);


        grp_LSelect_Dossier_ClientFacturable.setModedisplay(true);


        updateAllFields();


        System.out.println("[\n\n\n\n\n\n\n" + action);


        fx_manager.needReselect();


        action = ActionToolBar.ACT_READ;


        if (parent.isAfterCreation()) {


            fx_manager.swithPanel();


            parent.setAfterCreation(false);


        } else {

            if (parent.getDossier().getAnnuler() == 0 && parent.getDossier().getServicefact() == 0 && !parent.getDossier().isReadMode()) {
                parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_MODIFY,


                        ActionToolBar.DO_CANCEL,


                        ActionToolBar.DO_PREVIOUS,


                        ActionToolBar.DO_SWITCH});
            } else {
                parent.setCurrentActionEnabled(new int[]{
                        ActionToolBar.DO_CANCEL,
                        ActionToolBar.DO_PREVIOUS
                });

            }

        }


        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
        grp_TBut_ClientContractuel.requestFocus();


    }


    /**
     * Affichage en mode Modification
     */


    public void displayUpdateMode() {


        action = ActionToolBar.ACT_MODIFY;


        for (int i = 0; i < composantToVerif.length; i++) {


            composantToVerif[i].setEnabled(true);


            composantToVerif[i].setLastFocusedComponent(true);


        }


        grp_LSelect_Dossier_ClientContractuel.setLastFocusedComponent(false);


        grp_LSelect_Dossier_ClientFacturable.setLastFocusedComponent(false);


        grp_ADate_Dossier_DateDepart.requestFocus();


        parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});


        tb_interaction.enableValidateActionListener(true);


        grp_LSelect_Dossier_ClientContractuel.setModedisplay(false);


        grp_LSelect_Dossier_ClientFacturable.setModedisplay(false);


    }


    /**
     * Méthode pour l'update de tous les champs
     */


    public void updateAllFields() {


        Dossier_T dossiertmp = getDossier();


        grp_ADate_Dossier_DateDepart.setDate(dossiertmp.getDr_date_depart());


        grp_ADate_Dossier_DateFin.setDate(dossiertmp.getDr_date_retour());


        grp_TField_Dossier_NbreDeNuite.setText("" + dossiertmp.getDr_nbrenuit());


        grp_ACB_Dossier_TypeB.setCleUnik(dossiertmp.getDr_type_booking());


        grp_LSelect_Dossier_ClientContractuel.setCleUnik(dossiertmp.getCscleunik());


        grp_LSelect_Dossier_ClientContractuel.setText("" + dossiertmp.getClientContractuel().getCsnom());


        grp_LSelect_Dossier_ClientFacturable.setCleUnik(dossiertmp.getCscleunik_fact());

        cli = grp_LSelect_Dossier_ClientFacturable.getCleUnik();

        grp_LSelect_Dossier_ClientFacturable.setText("" + dossiertmp.getClientFacturable().getCsnom());

        // int[]tmpval=CalculDate.renvDifferenceBetweenDate(grp_ADate_Dossier_DateDepart.getDate(), grp_ADate_Dossier_DateFin.getDate());


        grp_TField_Dossier_nbreDeJour.setText("" + dossiertmp.getDr_nbrjour());


        grp_TField_Dossier_NbreDeNuite.setText("" + dossiertmp.getDr_nbrenuit());


        resetDossierClientContract();


        parent.chargeStatusPanel();


    }


    public void updateAllFields(Object donnee) {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Spécifie le composant qui implémente cette fonction comme
     * <p/>
     * <p/>
     * le composant qui pilote l'actionToolBar
     */


    public void setThisAsToolBarComponent() {


    }


    public void doAccept() {

        /*switch(action) {


            /*case ActionToolBar.ACT_READ :


                parent.displayReadSequence(0);


                parent.cancelModule();


                break;


            case ActionToolBar.ACT_INSERT :


                chargeDossierInsert();


               // dbInsert();


                parent.setAfterCreation(true);


                // parent.displayReadSequence(0);


                displayReadMode();


                sw=true;


               // resetDossier();


                break;


            case ActionToolBar.ACT_MODIFY :


                chargeDossierModif();


                parent.displayReadSequence(0);              


                displayReadMode();


                sw=false;


                break;


        }*/


    }


    public void chargeDossier(Dossier_T dossiertmp)


    {


        dossiertmp.setDr_date_depart(grp_ADate_Dossier_DateDepart.getDate());
        dossiertmp.setDr_date_retour(grp_ADate_Dossier_DateFin.getDate());
        try {
            dossiertmp.setDr_nbrenuit(Integer.parseInt(grp_TField_Dossier_NbreDeNuite.getText()));
        } catch (NumberFormatException nn) {
            dossiertmp.setDr_nbrenuit(0);
        }
        try {
            dossiertmp.setDr_nbrjour(Integer.parseInt(grp_TField_Dossier_nbreDeJour.getText()));
        } catch (NumberFormatException nn) {
            dossiertmp.setDr_nbrjour(0);
        }
        dossiertmp.setDr_type_booking(grp_ACB_Dossier_TypeB.getCleUnik());
        dossiertmp.setCscleunik(grp_LSelect_Dossier_ClientContractuel.getCleUnik());
        dossiertmp.setCsContractuel(grp_LSelect_Dossier_ClientContractuel.getText());
        dossiertmp.setCscleunik_fact(grp_LSelect_Dossier_ClientFacturable.getCleUnik());


        dossiertmp.setCsFacturable(grp_LSelect_Dossier_ClientFacturable.getText());
    }


    public void chargeDossierInsert() {
        Dossier_T dossiertmp = getDossier();
        chargeDossier(dossiertmp);
        dossiertmp.setNewreccord(true);
    }

    public void chargeDossierModif() {
        Dossier_T dossiertmp = getDossier();
        chargeDossier(dossiertmp);
        dossiertmp.setModifreccord(true);
    }

    public void resetDossierClientContract() {
        Dossier_T dossiertmp = getDossier();
        if (dossiertmp.getClientContractuel() != null) {
            System.out.println("CLIENT CONTRACT JE PASSE");
            grp_TField_Client_reference.setText("" + dossiertmp.getClientContractuel().getCsreference());
            grp_TField_Client_nom.setText("" + dossiertmp.getClientContractuel().getCsnom());
            grp_TField_Client_adresse.setText("" + dossiertmp.getClientContractuel().getCsadresse());
            grp_TField_Client_codepostal.setText("" + dossiertmp.getClientContractuel().getCodenom());
            grp_TField_Client_localité.setText("" + dossiertmp.getClientContractuel().getCslocalite());
            grp_TField_Client_pays.setText("" + dossiertmp.getClientContractuel().getPysnom());
            grp_TField_Client_email.setText("" + dossiertmp.getClientContractuel().getCsmailprincip());
            grp_TField_Client_telephone.setText("" + dossiertmp.getClientContractuel().getCstelephonep());
            grp_TField_Client_langue.setText("" + dossiertmp.getClientContractuel().getLanguenom());
        }
    }

    public void resetDossierClientFacturable() {
        Dossier_T dossiertmp = getDossier();
        if (dossiertmp.getClientFacturable() != null) {
            System.out.println("CLIENT FACT JE PASSE");
            grp_TField_Client_reference.setText("" + dossiertmp.getClientFacturable().getCsreference());
            grp_TField_Client_nom.setText("" + dossiertmp.getClientFacturable().getCsnom());
            grp_TField_Client_adresse.setText("" + dossiertmp.getClientFacturable().getCsadresse());
            grp_TField_Client_codepostal.setText("" + dossiertmp.getClientFacturable().getCodenom());
            grp_TField_Client_localité.setText("" + dossiertmp.getClientFacturable().getCslocalite());
            grp_TField_Client_pays.setText("" + dossiertmp.getClientFacturable().getPysnom());
            grp_TField_Client_email.setText("" + dossiertmp.getClientFacturable().getCsmailprincip());
            grp_TField_Client_telephone.setText("" + dossiertmp.getClientFacturable().getCstelephonep());
            grp_TField_Client_langue.setText("" + dossiertmp.getClientFacturable().getLanguenom());
        }
    }

    public void doCancel() {
        switch (action) {
            case ActionToolBar.ACT_READ:
                if (parent.getDossier().isNewreccord()) {
                    String annulTitle = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("dosmodifnonrec");
                    String annulText = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("dosnewnonrec");
                    // int resp = parent.getMessageManager().showConfirmDialog(this, "dos_annul_new", "dos_annul", parent.getCurrentUser());
                    DossierMessage dossnew = new DossierMessage(parent.getSuperOwner(), true, annulTitle, annulText, DossierMessage.FOCUSCANCEL);
                    int resp = dossnew.retval;
                    dossnew.dispose();
                    requestFocus();
                    if (resp != JOptionPane.YES_OPTION) {
                        return;
                    }
                } else if (parent.getDossier().isModifreccord()) {
                    String annulTitle = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("dosmodifnonrectitle");
                    String annulText = java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("dosmodifnonrec");
                    DossierMessage dossnew = new DossierMessage(parent.getSuperOwner(), true, annulTitle, annulText, DossierMessage.FOCUSCANCEL);
                    int resp = dossnew.retval;
                    dossnew.dispose();
                    requestFocus();
                    if (resp != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                parent.cancelModule();
                break;
            case ActionToolBar.ACT_INSERT:
                parent.cancelModule();
                break;
            case ActionToolBar.ACT_MODIFY:
                displayReadMode();
                break;
        }
    }


    public void doClose() {


    }


    public void doCreate() {


    }


    public void doDelete() {


        dbDelete();


    }


    public void doHelp() {


    }


    public void doModify() {


        displayUpdateMode();


    }


    public void doNext() {


    }


    public void applyDossierChange() {

        //if(parent.getDossier().isNewreccord()==true){

        // System.out.println("\n\n\n\n\n[**************ok je suis en mode insert");


        if (parent.getDossier().getPassager() == null || parent.getDossier().getPassager().size() == 0) {


            parent.getMessageManager().showMessageDialog(this, "dos_no_pass_text", "dos_no_pass", parent.getCurrentUser());


        } else {


            parent.nextSpecificScreen(1);

            //dbInsert();

            // System.out.println("CREATING SEQUENCE =======> " + parent.isCreatingSequence());


            if (parent.isCreatingSequence()) {


                System.out.println("***********************DISPLAY READ SEQUENCE !!!!!**********************************");


                parent.displayReadSequence(0);


            }


        }


    }

    /*     else {


        // System.out.println("\n\n\n\n\n[**************ok je suis en mode modification");


        if(parent.getDossier().isModifreccord()==false && parent.getDossier().isListPassagerModif()==false)


            parent.getMessageManager().showMessageDialog(this,"dos_no_mod_text","dos_no_mod",parent.getCurrentUser());


        else


            dbUpdate();





    }*/

    //}


    public void doPrevious() {


        switch (action) {

            /*case ActionToolBar.ACT_READ :


            parent.displayReadSequence(0);


            parent.cancelModule();


            break;*/


            case ActionToolBar.ACT_INSERT:
                chargeDossierInsert();
                parent.setAfterCreation(true);
                displayReadMode();
                sw = true;
                break;


            case ActionToolBar.ACT_MODIFY:


                chargeDossierModif();
                System.out.println("Old:" + cli + "New:" + grp_LSelect_Dossier_ClientFacturable.getCleUnik());

                if (grp_LSelect_Dossier_ClientFacturable.getCleUnik() != cli)
                    parent.getDossier().setComptaModify(true);

                // parent.displayReadSequence(0);


                displayReadMode();


                sw = false;


                break;


            case ActionToolBar.ACT_READ:

                // applyDossierChange();


                try {


                    parent.nextSpecificScreen(1);


                } catch (IllegalArgumentException in) {


                    if (parent.getDossier().getPassager() != null) {


                        if (parent.getDossier().getPassager().size() > 0) {

                            // if(parent.isCreatingSequence())


                            parent.nextScreen(0);


                        } else {


                            parent.getMessageManager().showMessageDialog(this, "dos_no_pass_text", "dos_no_pass", parent.getCurrentUser());


                            fx_manager.swithPanel();


                        }


                    } else {


                        parent.getMessageManager().showMessageDialog(this, "dos_no_pass_text", "dos_no_pass", parent.getCurrentUser());


                        fx_manager.swithPanel();


                    }


                }


                break;


        } // end of switch


    }


    public void doPrint() {


    }


    public void doSwitch() {


        int act = action;


        if (act != ActionToolBar.ACT_READ) doPrevious();


        if (act != ActionToolBar.ACT_INSERT) fx_manager.swithPanel();


    }


    public int[] getDefaultActionToolBarMask() {


        int[] actionEnabled = new int[0];


        switch (action) {


            case ActionToolBar.ACT_READ:


                actionEnabled = new int[]{ActionToolBar.DO_MODIFY,


                        ActionToolBar.DO_CANCEL,


                        ActionToolBar.DO_PREVIOUS,


                        ActionToolBar.DO_SWITCH};


                break;


            default:


                actionEnabled = new int[]{ActionToolBar.DO_CANCEL};


        }


        return actionEnabled;


    }


    public srcastra.astra.gui.modules.MainScreenModule getMainScrennModule() {
        return this.parent;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

// => Champs de la classe

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private int action;


    private int dr_cleunik;


    private DossierMainScreenModule parent;


    private AstraComponent[] composantToVerif;


    private JPanelSelectionFxManager fx_manager;


    private Dossier_T dossier;


    private InterfaceModuleDossier interParent;


    private srcastra.astra.sys.classetransfert.utils.Date date1;


    private srcastra.astra.sys.classetransfert.utils.Date date2;


    private boolean sw;


    ToolBarInteraction tb_interaction;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// STATIC VARIABLES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Graphic Component

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private DossierPassagerPane grp_Pan_Passager;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_ACB_Dossier_TypeB;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_Dossier_DateDepart;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_Dossier_DateFin;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_LSelect_Dossier_ClientContractuel;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_LSelect_Dossier_ClientFacturable;
    private javax.swing.JLabel grp_Label_ClientContractuel;
    private javax.swing.JLabel grp_Label_ClientFacturable;
    private javax.swing.JLabel grp_Label_Client_adresse;
    private javax.swing.JLabel grp_Label_Client_codepostal;
    private javax.swing.JLabel grp_Label_Client_dateDeNaissance;
    private javax.swing.JLabel grp_Label_Client_email;
    private javax.swing.JLabel grp_Label_Client_langue;
    private javax.swing.JLabel grp_Label_Client_localité;
    private javax.swing.JLabel grp_Label_Client_nom;
    private javax.swing.JLabel grp_Label_Client_pays;
    private javax.swing.JLabel grp_Label_Client_reference;
    private javax.swing.JLabel grp_Label_Client_telephone;
    private javax.swing.JLabel grp_Label_DateDepart;
    private javax.swing.JLabel grp_Label_DateFin;
    private javax.swing.JPanel grp_Pan_Clients;
    private javax.swing.JPanel grp_Pan_Detail;
    private javax.swing.JPanel grp_Pan_Dossier;
    private javax.swing.JToggleButton grp_TBut_ClientContractuel;
    private javax.swing.JToggleButton grp_TBut_ClientFacturable;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_adresse;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_codepostal;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_dateDeNaissance;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_email;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_langue;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_localité;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_nom;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_pays;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_reference;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Client_telephone;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Dossier_NbreDeNuite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Dossier_User;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Dossier_nbreDeJour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// BEANS PROPERTIES GET/SET SUPPORT

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Permet à la classe qui implémente cette méthode de se
     * <p/>
     * <p/>
     * référencer auprès d' ActionToolBar
     *
     * @return le n° de l'action
     */


    public int getAction() {


        return action;


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


        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/Dossier", parent.getCurrentUser().getLangage()).getString("tab_dossier");


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


        this.dr_cleunik = frCleUnik;


    }


    public Dossier_T getDossier() {


        if (interParent.getDossier() == null)


            this.dossier = new Dossier_T();


        else


            this.dossier = interParent.getDossier();


        return this.dossier;


    }


    public DossierRmiInterface getServeurDossier() {


        return null;


    }


    public void setDossier(Dossier_T dossier) {


        interParent.setDossier(this.dossier);


    }


    public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser() {


        return null;


    }


    public void setLibellé(String libellé) {


    }


    public void goUp() {


    }


    public java.awt.Component m_getGeneriqueTable() {


        return null;


    }


    public void doF10() {


    }

    public void dateSmallerThenCurrenTime(ADateEvent evt) {
        if (evt.getCode() == evt.CALCUL_DAY) {
            grp_TField_Dossier_nbreDeJour.setText("" + grp_ADate_Dossier_DateFin.getNbrjour());
            grp_TField_Dossier_NbreDeNuite.setText("" + grp_ADate_Dossier_DateFin.getNbrnigth());
        }
    }

    /**
     * Creates a new instance of MailInterface
     */
    public String[] getEmailAdres() {
        return new String[]{grp_TField_Client_email.getText()};
    }

    public String getFormEntiteMail() {

        return "";
    }

    public void addKeystroque() {
        new ManageMailingFrame(this, this, parent.getSuperOwner(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());
    }

    public Loginusers_T getUser() {
        return parent.getCurrentUser();
    }

    /**
     * Getter for property mediator.
     *
     * @return Value of property mediator.
     */
    public srcastra.astra.gui.components.combobox.liste2.Mediator getMediator() {
        return mediator;
    }

    /**
     * Setter for property mediator.
     *
     * @param mediator New value of property mediator.
     */
    public void setMediator(srcastra.astra.gui.components.combobox.liste2.Mediator mediator) {
        this.mediator = mediator;
        if (mediator != null)
            mediator.registerDossierg(this);
    }

    /** Getter for property parent.
     * @return Value of property parent.
     */


    /**
     * Setter for property parent.
     *
     * @param parent New value of property parent.
     */
    public void setParent(srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule parent) {
        this.parent = parent;
    }

    public void doF7() {
    }

}
