/*

 * EntitePane.java

 *

 * Created on 4 juillet 2003, 16:17

 */


package srcastra.astra.gui.modules.config.entite;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import java.util.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.modules.config.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.*;

/**
 * @author thomas
 */

public class EntitePane extends javax.swing.JPanel implements ToolBarComposer, GlobalInterface {


    /**
     * Creates new form EntitePane
     */

    MainScreenModule parent;

    ActionToolBar toolbar;

    EntiteTableModel model = new EntiteTableModel();

    AstraComponent[] astrac;

    Loginusers_T currentUser;

    ManageFields manfields;

    int mode;

    Entite entite = new Entite();

    GlobalRmiInterface rmiInteface;

    public EntitePane(MainScreenModule parent, ActionToolBar toolbar, ArrayList data) {

        this.toolbar = toolbar;

        this.parent = parent;

        this.currentUser = parent.getCurrentUser();

        try {

            this.rmiInteface = parent.getServeur().renvEntiteRmiObject(currentUser.getUrcleunik());


        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        mode = ActionToolBar.ACT_READ;

        initComponents();

        setDocumentMask();

        astrac = new AstraComponent[]{grp_Tfiel_nom, grp_Tfiel_abrev, grp_Tfiel_adresse, grp_Liste_code, grp_Tfiel_tel, grp_Tfiel_tel21, grp_Tfiel_fax1, grp_Tfiel_email1, grp_Tfiel_tva1, grp_Tfiel_bank1, grp_Liste_langue, grp_TField_smtp};

        model.setData(data);

        grp_Table_entite.setModel(model);

        initListe();

        Hashtable check = new Hashtable();

        check.put(new Integer(0), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_nom"));

        check.put(new Integer(1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_abrev"));

        check.put(new Integer(10), java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_langue"));

        manfields = new ManageFields(astrac, (javax.swing.JInternalFrame) parent, grp_Table_entite, toolbar, this, check, currentUser);

        manfields.init();


    }

    private void setDocumentMask() {

        grp_Tfiel_nom.setDocument(new DefaultMask(1, 30, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_Tfiel_abrev.setDocument(new DefaultMask(1, 2, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_Tfiel_adresse.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        //grp_Liste_code.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_Tfiel_localite.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_TField_smtp.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_tel.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_tel21.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_fax1.setDocument(new DefaultMask(0, 15, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_email1.setDocument(new DefaultMask(0, 70, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_tva1.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

        grp_Tfiel_bank1.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));

    }

    private void initListe() {

        //   grp_Liste_code.setFocusable3(false);

        grp_Liste_code.setServeur(parent.getServeur());

        grp_Liste_code.setLogin(parent.getCurrentUser());

        grp_Liste_code.setModel(new srcastra.astra.gui.components.combobox.liste.CpListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_code.init2();

        //   grp_Liste_langue.setFocusable3(false);

        grp_Liste_langue.setServeur(parent.getServeur());

        grp_Liste_langue.setLogin(parent.getCurrentUser());

        grp_Liste_langue.setModel(new srcastra.astra.gui.components.combobox.liste.LangueListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_langue.init2();

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

        jPanel3 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jLabel6 = new javax.swing.JLabel();

        grp_Tfiel_nom = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_abrev = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_adresse = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Liste_code = new srcastra.astra.gui.components.combobox.liste.Liste();

        grp_Tfiel_localite = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_tel = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel8 = new javax.swing.JLabel();

        grp_TField_smtp = new srcastra.astra.gui.components.textFields.ATextField();

        jPanel51 = new javax.swing.JPanel();

        jPanel61 = new javax.swing.JPanel();

        jLabel71 = new javax.swing.JLabel();

        jLabel81 = new javax.swing.JLabel();

        jLabel91 = new javax.swing.JLabel();

        jLabel101 = new javax.swing.JLabel();

        jLabel111 = new javax.swing.JLabel();

        grp_Tfiel_tel21 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_fax1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_email1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_tva1 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Tfiel_bank1 = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel7 = new javax.swing.JLabel();

        grp_Liste_langue = new srcastra.astra.gui.components.combobox.liste.Liste();

        jPanel2 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_entite = new javax.swing.JTable();


        setLayout(new java.awt.BorderLayout());


        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));


        jPanel1.setMinimumSize(new java.awt.Dimension(176, 138));

        jPanel1.setPreferredSize(new java.awt.Dimension(468, 138));

        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_nom"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_abrev"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel2, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_adresse"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel3, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_codepostal"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel4, gridBagConstraints);


        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_telp"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel5, gridBagConstraints);


        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_loc"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel6, gridBagConstraints);


        grp_Tfiel_nom.setGrp_Comp_nextComponent(grp_Tfiel_abrev);

        grp_Tfiel_nom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfiel_nom, gridBagConstraints);


        grp_Tfiel_abrev.setGrp_Comp_nextComponent(grp_Tfiel_adresse);

        grp_Tfiel_abrev.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_abrev.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfiel_abrev, gridBagConstraints);


        grp_Tfiel_adresse.setGrp_Comp_nextComponent(grp_Liste_code);

        grp_Tfiel_adresse.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_adresse.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfiel_adresse, gridBagConstraints);


        grp_Liste_code.setGrp_Comp_nextComponent(grp_Tfiel_tel);

        grp_Liste_code.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_code.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Liste_code, gridBagConstraints);


        grp_Tfiel_localite.setEnabled(false);

        grp_Tfiel_localite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_localite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfiel_localite, gridBagConstraints);


        grp_Tfiel_tel.setGrp_Comp_nextComponent(grp_TField_smtp);

        grp_Tfiel_tel.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_tel.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfiel_tel, gridBagConstraints);


        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel8.setText("SMTP");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(jLabel8, gridBagConstraints);


        grp_TField_smtp.setGrp_Comp_nextComponent(grp_Tfiel_tel21);

        grp_TField_smtp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_smtp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_TField_smtp, gridBagConstraints);


        jPanel3.add(jPanel4);


        jPanel1.add(jPanel3);


        jPanel61.setLayout(new java.awt.GridBagLayout());


        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel71.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_tel2"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel61.add(jLabel71, gridBagConstraints);


        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel81.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_fax"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel61.add(jLabel81, gridBagConstraints);


        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel91.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_mail"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel61.add(jLabel91, gridBagConstraints);


        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel101.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_tva"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel61.add(jLabel101, gridBagConstraints);


        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel111.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_bank"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel61.add(jLabel111, gridBagConstraints);


        grp_Tfiel_tel21.setGrp_Comp_nextComponent(grp_Tfiel_fax1);

        grp_Tfiel_tel21.setPreferredSize(new java.awt.Dimension(150, 18));

        grp_Tfiel_tel21.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_tel21.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Tfiel_tel21, gridBagConstraints);


        grp_Tfiel_fax1.setGrp_Comp_nextComponent(grp_Tfiel_email1);

        grp_Tfiel_fax1.setPreferredSize(new java.awt.Dimension(150, 18));

        grp_Tfiel_fax1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_fax1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Tfiel_fax1, gridBagConstraints);


        grp_Tfiel_email1.setGrp_Comp_nextComponent(grp_Tfiel_tva1);

        grp_Tfiel_email1.setPreferredSize(new java.awt.Dimension(150, 18));

        grp_Tfiel_email1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_email1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Tfiel_email1, gridBagConstraints);


        grp_Tfiel_tva1.setGrp_Comp_nextComponent(grp_Tfiel_bank1);

        grp_Tfiel_tva1.setPreferredSize(new java.awt.Dimension(150, 18));

        grp_Tfiel_tva1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_tva1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Tfiel_tva1, gridBagConstraints);


        grp_Tfiel_bank1.setGrp_Comp_nextComponent(grp_Liste_langue);

        grp_Tfiel_bank1.setPreferredSize(new java.awt.Dimension(150, 18));

        grp_Tfiel_bank1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfiel_bank1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Tfiel_bank1, gridBagConstraints);


        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("ee_langue"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(jLabel7, gridBagConstraints);


        grp_Liste_langue.setIsLastComponent(true);

        grp_Liste_langue.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_langue.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel61.add(grp_Liste_langue, gridBagConstraints);


        jPanel51.add(jPanel61);


        jPanel1.add(jPanel51);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.BorderLayout());


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 180));

        grp_Table_entite.setModel(new javax.swing.table.DefaultTableModel(

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

        jScrollPane1.setViewportView(grp_Table_entite);


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

    public void updateFields() {

        this.entite.setEenom(grp_Tfiel_nom.getText());

        this.entite.setEeabrev(grp_Tfiel_abrev.getText());

        this.entite.setEeadresse(grp_Tfiel_adresse.getText());

        this.entite.setCxcleunik(grp_Liste_code.getCleUnik());

        this.entite.setEetelephones(grp_Tfiel_tel.getText());

        this.entite.setEetelephonep(grp_Tfiel_tel21.getText());

        this.entite.setEefax(grp_Tfiel_fax1.getText());

        this.entite.setEemail(grp_Tfiel_email1.getText());

        this.entite.setEetva(grp_Tfiel_tva1.getText());

        this.entite.setEecomptebancaire(grp_Tfiel_bank1.getText());

        this.entite.setLmcleunik(grp_Liste_langue.getCleUnik());

        this.entite.setEesmtp(grp_TField_smtp.getText());

    }

    public void setFields() {

        this.entite = model.getObject(grp_Table_entite.getSelectedRow());

        grp_Tfiel_nom.setText(this.entite.getEenom());

        grp_Tfiel_abrev.setText(this.entite.getEeabrev());

        grp_Tfiel_adresse.setText(this.entite.getEeadresse());

        grp_Liste_code.setCleUnik(this.entite.getCxcleunik());

        grp_Tfiel_tel.setText(this.entite.getEetelephones());

        grp_Tfiel_tel21.setText(this.entite.getEetelephonep());

        grp_Tfiel_fax1.setText(this.entite.getEefax());

        grp_Tfiel_email1.setText(this.entite.getEemail());

        grp_Tfiel_tva1.setText(this.entite.getEetva());

        grp_Tfiel_bank1.setText(this.entite.getEecomptebancaire());

        grp_Liste_langue.setCleUnik(this.entite.getLmcleunik());

        grp_TField_smtp.setText(this.entite.getEesmtp());

    }

    public void doPrevious() {

        updateFields();

        manfields.validate();


    }

    public void doPrint() {

    }

    public void doSwitch() {

    }

    public int[] getDefaultActionToolBarMask() {

        int[] tmp = null;

        if (mode == ActionToolBar.ACT_READ) {

            if (grp_Table_entite.getRowCount() != 0) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };

                toolbar.setActionEnabled(tmp);

            } else {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_CANCEL

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

    public void setThisAsToolBarComponent() {

    }


    /**
     * Getter for property entite.
     *
     * @return Value of property entite.
     */

    public srcastra.astra.sys.classetransfert.configuration.Entite getEntite() {

        return entite;

    }


    /**
     * Setter for property entite.
     *
     * @param entite New value of property entite.
     */

    public void setEntite(srcastra.astra.sys.classetransfert.configuration.Entite entite) {

        this.entite = entite;

    }


    public Object getObject() {

        return entite;

    }


    public GlobalRmiInterface getRmiInteface() {

        return rmiInteface;

    }


    public javax.swing.JTable getTable() {

        return grp_Table_entite;

    }


    public boolean isOkPass() {

        return true;

    }


    public srcastra.astra.gui.modules.config.user.Pwd getPass() {

        return null;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_code;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_langue;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_smtp;

    private javax.swing.JTable grp_Table_entite;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_abrev;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_adresse;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_bank1;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_email1;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_fax1;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_localite;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_nom;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_tel;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_tel21;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_tva1;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel101;

    private javax.swing.JLabel jLabel111;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel71;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel81;

    private javax.swing.JLabel jLabel91;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel51;

    private javax.swing.JPanel jPanel61;

    private javax.swing.JScrollPane jScrollPane1;

    // End of variables declaration//GEN-END:variables


}

