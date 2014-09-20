/*

 * UserCOnfig.java

 *

 * Created on 1 juillet 2003, 14:24

 */


package srcastra.astra.gui.modules.config.user;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import java.util.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.modules.config.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.sys.classetransfert.configuration.*;

/**
 * @author Thomas
 */

public class UserCOnfig extends javax.swing.JPanel implements ToolBarComposer, GlobalInterface {

    MainScreenModule parent;

    /**
     * Creates new form UserCOnfig
     */

    ActionToolBar toolbar;

    int mode;

    UserTableModel model = new UserTableModel();

    srcastra.astra.sys.classetransfert.configuration.User user = new srcastra.astra.sys.classetransfert.configuration.User();

    AstraComponent[] astrac;

    Loginusers_T currentUser;

    ManageFields manfields;

    GlobalRmiInterface rmiInteface;

    boolean okPass;

    Pwd pass;

    public UserCOnfig(MainScreenModule parent, ActionToolBar toolbar, ArrayList data) {

        this.currentUser = parent.getCurrentUser();

        this.toolbar = toolbar;

        this.parent = parent;

        try {

            this.rmiInteface = parent.getServeur().renvUserRmiObject(currentUser.getUrcleunik());


        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        initComponents();

        postInit();

        pass = new Pwd(parent.getSuperOwner(), true, true, this.user, this, this.currentUser);

        initListe();

        setDocumentMask();

        astrac = new AstraComponent[]{grp_TField_nom, grp_TField_pre, grp_TField_adre, grp_List_cp, grp_TField_user, grp_TField_telb, grp_TField_telp, grp_TField_emailb, grp_TField_emailp, grp_TField_gsm, grp_Liste_entite, grp_Liste_langue, grp_Liste_droit, grp_TPane_signature};


        model.setData(data);

        grp_JTable_user.setModel(model);

        Hashtable check = new Hashtable();

        check.put(new Integer(0), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_nom"));

        check.put(new Integer(1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_pre"));

        check.put(new Integer(11), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_droit"));

        check.put(new Integer(12), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_langue"));

        check.put(new Integer(12), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_entit"));

        manfields = new ManageFields(astrac, (javax.swing.JInternalFrame) parent, grp_JTable_user, toolbar, this, check, currentUser, false);

        manfields.init();

    }

    private void postInit() {

        grp_TPane_signature = new srcastra.astra.gui.components.TextPane.P_JTextPane();

        grp_TPane_signature.setEditable(false);

        jScrollPane2.setViewportView(grp_TPane_signature);

    }

    private void setDocumentMask() {

        grp_TField_nom.setDocument(new DefaultMask(1, 12, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_pre.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_adre.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_loc.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_TField_user.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_telb.setDocument(new DefaultMask(0, 15, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_telp.setDocument(new DefaultMask(0, 15, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_emailb.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_emailp.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_gsm.setDocument(new DefaultMask(0, 15, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

    }

    private void initListe() {

        grp_List_cp.setServeur(parent.getServeur());

        grp_List_cp.setLogin(parent.getCurrentUser());

        grp_List_cp.setModel(new srcastra.astra.gui.components.combobox.liste.CpListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_List_cp.init2();

        //   grp_Liste_langue.setFocusable3(false);

        grp_Liste_langue.setServeur(parent.getServeur());

        grp_Liste_langue.setLogin(parent.getCurrentUser());

        grp_Liste_langue.setModel(new srcastra.astra.gui.components.combobox.liste.LangueListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_langue.init2();


        grp_Liste_entite.setServeur(parent.getServeur());

        grp_Liste_entite.setLogin(parent.getCurrentUser());

        grp_Liste_entite.setModel(new srcastra.astra.gui.components.combobox.liste.EntiteListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_entite.init2();


        grp_Liste_droit.setServeur(parent.getServeur());

        grp_Liste_droit.setLogin(parent.getCurrentUser());

        grp_Liste_droit.setModel(new srcastra.astra.gui.components.combobox.liste.DroitListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_droit.init2();


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


        jPanel5 = new javax.swing.JPanel();

        jPanel1 = new javax.swing.JPanel();

        jPanel3 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jLabel6 = new javax.swing.JLabel();

        grp_TField_nom = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_pre = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_adre = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_telb = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_loc = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_user = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel7 = new javax.swing.JLabel();

        jLabel8 = new javax.swing.JLabel();

        grp_TField_telp = new srcastra.astra.gui.components.textFields.ATextField();

        grp_List_cp = new srcastra.astra.gui.components.combobox.liste.Liste();

        jPanel6 = new javax.swing.JPanel();

        jPanel7 = new javax.swing.JPanel();

        jLabel9 = new javax.swing.JLabel();

        jLabel10 = new javax.swing.JLabel();

        jLabel11 = new javax.swing.JLabel();

        jLabel12 = new javax.swing.JLabel();

        jLabel13 = new javax.swing.JLabel();

        jLabel14 = new javax.swing.JLabel();

        grp_TField_emailb = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_emailp = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_gsm = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Liste_entite = new srcastra.astra.gui.components.combobox.liste.Liste();

        grp_Liste_langue = new srcastra.astra.gui.components.combobox.liste.Liste();

        grp_Liste_droit = new srcastra.astra.gui.components.combobox.liste.Liste();

        jLabel15 = new javax.swing.JLabel();

        jScrollPane2 = new javax.swing.JScrollPane();

        jPanel2 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_JTable_user = new javax.swing.JTable();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(""));

        add(jPanel5, java.awt.BorderLayout.SOUTH);


        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));


        jPanel1.setBorder(new javax.swing.border.TitledBorder("Administrator"));

        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_nom"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_pre"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel2, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_adre"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel3, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_cp"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel4, gridBagConstraints);


        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_loc"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel5, gridBagConstraints);


        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_code"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel6, gridBagConstraints);


        grp_TField_nom.setGrp_Comp_nextComponent(grp_TField_pre);

        grp_TField_nom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_nom, gridBagConstraints);


        grp_TField_pre.setGrp_Comp_nextComponent(grp_TField_adre);

        grp_TField_pre.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_pre.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_pre, gridBagConstraints);


        grp_TField_adre.setGrp_Comp_nextComponent(grp_List_cp);

        grp_TField_adre.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_adre.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_adre, gridBagConstraints);


        grp_TField_telb.setGrp_Comp_nextComponent(grp_TField_telp);

        grp_TField_telb.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_telb.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_telb, gridBagConstraints);


        grp_TField_loc.setEnabled(false);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_loc, gridBagConstraints);


        grp_TField_user.setGrp_Comp_nextComponent(grp_TField_telb);

        grp_TField_user.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_user.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_user, gridBagConstraints);


        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_teleb"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel7, gridBagConstraints);


        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_telep"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 7;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel8, gridBagConstraints);


        grp_TField_telp.setGrp_Comp_nextComponent(grp_TField_emailb);

        grp_TField_telp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_telp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 7;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_telp, gridBagConstraints);


        grp_List_cp.setGrp_Comp_nextComponent(grp_TField_user);

        grp_List_cp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_List_cp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_List_cp, gridBagConstraints);


        jPanel3.add(jPanel4);


        jPanel1.add(jPanel3);


        jPanel7.setLayout(new java.awt.GridBagLayout());


        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_mailb"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel9, gridBagConstraints);


        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_mailp"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel10, gridBagConstraints);


        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_gsm"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel11, gridBagConstraints);


        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_entit"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel12, gridBagConstraints);


        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_langue"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel13, gridBagConstraints);


        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_droit"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel14, gridBagConstraints);


        grp_TField_emailb.setGrp_Comp_nextComponent(grp_TField_emailp);

        grp_TField_emailb.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_emailb.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_TField_emailb, gridBagConstraints);


        grp_TField_emailp.setGrp_Comp_nextComponent(grp_TField_gsm);

        grp_TField_emailp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_emailp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_TField_emailp, gridBagConstraints);


        grp_TField_gsm.setGrp_Comp_nextComponent(grp_Liste_entite);

        grp_TField_gsm.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_gsm.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_TField_gsm, gridBagConstraints);


        grp_Liste_entite.setGrp_Comp_nextComponent(grp_Liste_langue);

        grp_Liste_entite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_entite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_Liste_entite, gridBagConstraints);


        grp_Liste_langue.setGrp_Comp_nextComponent(grp_Liste_droit);

        grp_Liste_langue.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_langue.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_Liste_langue, gridBagConstraints);


        grp_Liste_droit.setIsLastComponent(true);

        grp_Liste_droit.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_droit.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_Liste_droit, gridBagConstraints);


        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale").getString("emailfirm"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(jLabel15, gridBagConstraints);


        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(10, 50));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 7;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.gridheight = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(jScrollPane2, gridBagConstraints);


        jPanel6.add(jPanel7);


        jPanel1.add(jPanel6);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.BorderLayout());


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 200));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        grp_JTable_user.setModel(new javax.swing.table.DefaultTableModel(

                new Object[][]{

                        {null, null, null, null},

                        {null, null, null, null},

                        {null, null, null, null},

                        {null, null, null, null}

                },

                new String[]{

                        "Title 1", "Title 2", "Title 3", "Title 4"

                }

        ));

        jScrollPane1.setViewportView(grp_JTable_user);


        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        add(jPanel2, java.awt.BorderLayout.NORTH);


    }//GEN-END:initComponents

    public void doAccept() {

    }

    public void doCancel() {

        manfields.cancel();

    }

    public void doClose() {

    }

    public void doCreate() {

        manfields.beforeCreate(false);

    }

    public void doDelete() {

    }

    public void doF10() {

    }

    public void doF7() {

    }

    public void doHelp() {

    }

    public void doModify() {


        setFields();

        manfields.beforeCreate(true);

    }

    public void doNext() {

    }

    public void askForpassword(boolean modif) {

        new Pwd(parent.getSuperOwner(), true, modif, this.user, this, this.currentUser);

    }

    public void updateFields() {

        this.user.setUrnom(grp_TField_nom.getText());

        this.user.setUrprenom(grp_TField_pre.getText());

        this.user.setCxcleunik(grp_List_cp.getCleUnik());

        this.user.setUradresse(grp_TField_adre.getText());

        this.user.setUrcode(grp_TField_user.getText());

        this.user.setUrtelephonebd(grp_TField_telb.getText());

        this.user.setUrtelephoneprive(grp_TField_telp.getText());

        this.user.setUrmailbur(grp_TField_emailb.getText());

        this.user.setUrmailprive(grp_TField_emailp.getText());

        this.user.setUrgsm(grp_TField_gsm.getText());

        this.user.setEecleunik(grp_Liste_entite.getCleUnik());

        this.user.setLmcleunik(grp_Liste_langue.getCleUnik());

        this.user.setDroits("0101");

        this.user.setUrdroit(grp_Liste_droit.getCleUnik());

        this.user.setSignature(grp_TPane_signature.getText());


    }

    public void doPrevious() {

        updateFields();

        manfields.validate();

        currentUser.setUrMailBur(grp_TField_emailb.getText());

        currentUser.setSignature(grp_TPane_signature.getText());

    }

    public void doPrint() {

    }

    public void doSwitch() {

    }

    public int[] getDefaultActionToolBarMask() {

        int[] tmp = null;

        if (manfields.mode == ActionToolBar.ACT_READ) {

            if (grp_JTable_user.getRowCount() != 0) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };

                toolbar.setActionEnabled(tmp);

            } else {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                };

                toolbar.setActionEnabled(tmp);

            }

        } else if (mode == ActionToolBar.ACT_INSERT) {

            tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_CANCEL,

            };

            toolbar.setActionEnabled(tmp);

        } else if (mode == ActionToolBar.ACT_MODIFY) {

            tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_CANCEL,

            };

            toolbar.setActionEnabled(tmp);

        }

        requestFocus();

        return tmp;

    }


    public java.awt.Component m_getGeneriqueTable() {

        return null;

    }

    public void setFields() {

        this.user = model.getObject(grp_JTable_user.getSelectedRow());

        grp_TField_nom.setText(this.user.getUrnom());

        grp_TField_pre.setText(this.user.getUrprenom());

        grp_List_cp.setCleUnik(this.user.getCxcleunik());

        grp_TField_user.setText(this.user.getUrcode());

        grp_TField_telb.setText(this.user.getUrtelephonebd());

        grp_TField_telp.setText(this.user.getUrtelephoneprive());

        grp_TField_adre.setText(this.user.getUradresse());

        grp_TField_emailb.setText(this.user.getUrmailbur());

        grp_TField_emailp.setText(this.user.getUrmailprive());

        grp_TField_gsm.setText(this.user.getUrgsm());

        grp_Liste_entite.setCleUnik(this.user.getEecleunik());

        grp_Liste_langue.setCleUnik(this.user.getLmcleunik());

        grp_Liste_droit.setCleUnik(this.user.getUrdroit());

        grp_TPane_signature.setText(this.user.getSignature());

    }


    public void setThisAsToolBarComponent() {

    }


    public Object getObject() {

        return user;

    }


    public GlobalRmiInterface getRmiInteface() {

        return rmiInteface;

    }


    public javax.swing.JTable getTable() {

        return grp_JTable_user;

    }


    /**
     * Getter for property okPass.
     *
     * @return Value of property okPass.
     */

    public boolean isOkPass() {

        return okPass;

    }


    /**
     * Setter for property okPass.
     *
     * @param okPass New value of property okPass.
     */

    public void setOkPass(boolean okPass) {

        this.okPass = okPass;

    }


    /**
     * Getter for property pass.
     *
     * @return Value of property pass.
     */

    public srcastra.astra.gui.modules.config.user.Pwd getPass() {

        return pass;

    }


    /**
     * Setter for property pass.
     *
     * @param pass New value of property pass.
     */

    public void setPass(srcastra.astra.gui.modules.config.user.Pwd pass) {

        this.pass = pass;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JTable grp_JTable_user;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_List_cp;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_droit;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_entite;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_langue;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_adre;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_emailb;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_emailp;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_gsm;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_loc;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pre;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_telb;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_telp;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_user;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JPanel jPanel7;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JScrollPane jScrollPane2;

    // End of variables declaration//GEN-END:variables

    private srcastra.astra.gui.components.TextPane.P_JTextPane grp_TPane_signature;

}

