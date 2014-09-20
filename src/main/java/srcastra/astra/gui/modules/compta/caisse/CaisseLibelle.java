/*

 * UserCOnfig.java

 *

 * Created on 1 juillet 2003, 14:24

 */


package srcastra.astra.gui.modules.compta.caisse;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import java.util.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.modules.config.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;

import javax.swing.table.*;

import java.awt.event.*;

import srcastra.astra.sys.classetransfert.configuration.*;

/**
 * @author Thomas
 */

public class CaisseLibelle extends javax.swing.JPanel implements ToolBarComposer, GlobalInterface, srcastra.astra.gui.components.AIframe {

    MainScreenModule parent;

    /**
     * Creates new form UserCOnfig
     */

    srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T caisselib;

    ActionToolBar toolbar;

    int mode;

    CaisseLibelletModel model;

    srcastra.astra.sys.classetransfert.configuration.User user = new srcastra.astra.sys.classetransfert.configuration.User();

    AstraComponent[] astrac;

    Loginusers_T currentUser;

    ManageFields manfields;

    GlobalRmiInterface rmiInteface;

    boolean okPass;

    int[] action;

    KeyAdapter my_key = new KeyAdapter() {

        public void keyPressed(KeyEvent evt) {

            if (evt.getKeyCode() == evt.VK_SPACE) {

                chieee(evt);

            }

        }

    };

    MouseAdapter my_click = new MouseAdapter() {

        public void mouseClicked(MouseEvent e) {

            chieee(e);

        }


    };


    ActionListener my_action = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

            doPrevious();

        }

    };

    private void chieee(MouseEvent evt) {

        if (evt.getSource() == grp_Check_client.getCheckBox()) {


            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_cli"));

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(true);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Client);


        } else if (evt.getSource() == grp_Check_fournisseur.getCheckBox()) {

            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_four"));

            // grp_Liste_cate.setServeur(parent.getServeur());

            //grp_Liste_cate.setLogin(parent.getCurrentUser());

            grp_liste_Fournisseur.setVisible(true);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Fournisseur);

            //  grp_Liste_cate.init2();  

        } else if (evt.getSource() == grp_Check_gene.getCheckBox()) {

            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_gen"));

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(true);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_Liste_cate);

        }

    }


    private void chieee(KeyEvent evt) {

        if (evt.getSource() == grp_Check_client.getCheckBox()) {


            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_cli"));

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(true);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Client);


        } else if (evt.getSource() == grp_Check_fournisseur.getCheckBox()) {

            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_four"));

            // grp_Liste_cate.setServeur(parent.getServeur());

            //grp_Liste_cate.setLogin(parent.getCurrentUser());

            grp_liste_Fournisseur.setVisible(true);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Fournisseur);

            //  grp_Liste_cate.init2();  

        } else if (evt.getSource() == grp_Check_gene.getCheckBox()) {

            jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_gen"));

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(true);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_Liste_cate);

        }

    }

    public CaisseLibelle(MainScreenModule parent, ActionToolBar toolbar, ArrayList data) {

        this.currentUser = parent.getCurrentUser();

        this.toolbar = toolbar;

        this.parent = parent;

        caisselib = new Caisselibelle_T();

        try {

            this.rmiInteface = parent.getServeur().renvCaisseLibelleRmiObject(currentUser.getUrcleunik());


        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        initComponents();

        postInit();

        //pass=new Pwd(parent.getSuperOwner(),true,true,this.user,this,this.currentUser);

        initListe();

        setDocumentMask();

        astrac = new AstraComponent[]{grp_Tfield_Intitule1, grp_Tfield_Intitule2, grp_Check_client, grp_Check_fournisseur, grp_Check_gene, grp_Liste_cate, grp_liste_Fournisseur, grp_liste_Client};

        try {

            data = rmiInteface.getList(currentUser.getUrcleunik(), 0);

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        if (data == null)

            data = new ArrayList();

        model = new CaisseLibelletModel(parent, parent.getCurrentUser());

        model.setData(data);

        initTable();

        toolbar.setTbComposer(this);

        activeToolBarAction(ActionToolBar.ACT_READ);

        //grp_JTable_user.setModel(model);      

        Hashtable check = new Hashtable();

        check.put(new Integer(0), java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai1"));

        check.put(new Integer(1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai2"));

        check.put(new Integer(2), java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typaycat"));

        check.put(new Integer(3), java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("compte"));

        // check.put(new Integer(12),java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("u_entit"));

        manfields = new ManageFields(astrac, (javax.swing.JInternalFrame) parent, grp_Table_CaissLibelle, toolbar, this, check, currentUser);

        manfields.init();

        grp_Check_client.setSelected(true);

    }

    public void activeToolBarAction(int type) {

        if (type == ActionToolBar.ACT_READ) {

            if (grp_Table_CaissLibelle.getRowCount() == 0)

                action = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_CREATE};

            else

                action = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_CREATE, ActionToolBar.DO_MODIFY};

        } else if (type == ActionToolBar.ACT_INSERT) {

            action = new int[]{ActionToolBar.DO_CREATE, ActionToolBar.DO_CANCEL, ActionToolBar.DO_PREVIOUS};

        }
        ;

        toolbar.setActionEnabled(action);


    }

    private void postInit() {

        buttonGroup.add(grp_Check_client.getCheckBox());

        buttonGroup.add(grp_Check_gene.getCheckBox());

        buttonGroup.add(grp_Check_fournisseur.getCheckBox());

        grp_Check_client.addMouseListener(my_click);
        grp_Check_gene.addMouseListener(my_click);
        grp_Check_fournisseur.addMouseListener(my_click);

        grp_Check_client.addKeyListener(my_key);

        grp_Check_gene.addKeyListener(my_key);

        grp_Check_fournisseur.addKeyListener(my_key);

        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 204, 102));

        grp_Table_CaissLibelle.getTableHeader().setBackground(new java.awt.Color(204, 0, 0));

        grp_Table_CaissLibelle.getTableHeader().setForeground(new java.awt.Color(255, 204, 102));

        grp_Table_CaissLibelle.getTableHeader().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));

        grp_liste_Fournisseur.setVisible(false);

        grp_liste_Client.setVisible(true);

        grp_Liste_cate.setVisible(false);

        grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Client);

        grp_liste_Client.addActionListener(my_action);

        grp_liste_Fournisseur.addActionListener(my_action);

        grp_Liste_cate.addActionListener(my_action);

    }

    private void setDocumentMask() {

        grp_Tfield_Intitule1.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_Tfield_Intitule2.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

    }

    private void initTable() {

        grp_Table_CaissLibelle.setFocusable(false);

        grp_Table_CaissLibelle.setSelectionBackground(new java.awt.Color(204, 204, 255));

        grp_Table_CaissLibelle.setAutoCreateColumnsFromModel(false);

        grp_Table_CaissLibelle.getTableHeader().setReorderingAllowed(false);

        grp_Table_CaissLibelle.setModel(this.model);

        grp_Table_CaissLibelle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        for (int k = 0; k < model.m_columns.length; k++) {

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

            TableColumn column = null;

            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            column.setHeaderValue(model.m_columns[k].c_title);

            grp_Table_CaissLibelle.addColumn(column);

        }

        JTableHeader header = grp_Table_CaissLibelle.getTableHeader();

        header.setUpdateTableInRealTime(false);

        header.setResizingAllowed(false);

        if (grp_Table_CaissLibelle.getRowCount() != 0) ;

        grp_Table_CaissLibelle.changeSelection(0, 0, false, false);

    }


    private void initListe() {

        grp_Liste_cate.setServeur(parent.getServeur());

        grp_Liste_cate.setLogin(parent.getCurrentUser());

        grp_Liste_cate.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_cate.init2();

        grp_liste_Fournisseur.setServeur(parent.getServeur());

        grp_liste_Fournisseur.setLogin(parent.getCurrentUser());

        grp_liste_Fournisseur.setModel(new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_liste_Fournisseur.init2();

        grp_liste_Client.setServeur(parent.getServeur());

        grp_liste_Client.setName("lc");

        grp_liste_Client.setLogin(parent.getCurrentUser());

        grp_liste_Client.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_liste_Client.init2();

        //grp_LSelect_Dossier_ClientContractuel.setLeft(true);


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


        buttonGroup = new javax.swing.ButtonGroup();

        jPanel5 = new javax.swing.JPanel();

        jPanel1 = new javax.swing.JPanel();

        jPanel3 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        grp_Tfield_Intitule1 = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel9 = new javax.swing.JLabel();

        jLabel14 = new javax.swing.JLabel();

        grp_Tfield_Intitule2 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Liste_cate = new srcastra.astra.gui.components.combobox.liste.Liste();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        grp_Check_client = new srcastra.astra.gui.components.checkbox.ACheckBox();

        grp_Check_fournisseur = new srcastra.astra.gui.components.checkbox.ACheckBox();

        grp_Check_gene = new srcastra.astra.gui.components.checkbox.ACheckBox();

        grp_liste_Fournisseur = new srcastra.astra.gui.components.combobox.liste.Liste();

        grp_liste_Client = new srcastra.astra.gui.components.combobox.liste2.Liste2();

        jPanel6 = new javax.swing.JPanel();

        jPanel7 = new javax.swing.JPanel();

        jPanel2 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_CaissLibelle = new javax.swing.JTable();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(""));

        add(jPanel5, java.awt.BorderLayout.SOUTH);


        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));


        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai1"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel1, gridBagConstraints);


        grp_Tfield_Intitule1.setEnabled(false);

        grp_Tfield_Intitule1.setGrp_Comp_nextComponent(grp_Tfield_Intitule2);

        grp_Tfield_Intitule1.setPreferredSize(new java.awt.Dimension(300, 18));

        grp_Tfield_Intitule1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfield_Intitule1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Tfield_Intitule1, gridBagConstraints);


        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai2"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel9, gridBagConstraints);


        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_cli"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel14, gridBagConstraints);


        grp_Tfield_Intitule2.setEnabled(false);

        grp_Tfield_Intitule2.setGrp_Comp_nextComponent(grp_Check_client);

        grp_Tfield_Intitule2.setPreferredSize(new java.awt.Dimension(300, 18));

        grp_Tfield_Intitule2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfield_Intitule2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Tfield_Intitule2, gridBagConstraints);


        grp_Liste_cate.setEnabled(false);

        grp_Liste_cate.setIsLastComponent(true);

        grp_Liste_cate.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_cate.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Liste_cate, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_cli"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel2, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_four"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel3, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("calib_gen"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 5);

        jPanel4.add(jLabel4, gridBagConstraints);


        grp_Check_client.setGrp_Comp_nextComponent(grp_Check_fournisseur);

        grp_Check_client.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Check_client, gridBagConstraints);


        grp_Check_fournisseur.setGrp_Comp_nextComponent(grp_Check_gene);

        grp_Check_fournisseur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Check_fournisseur, gridBagConstraints);


        grp_Check_gene.setGrp_Comp_nextComponent(grp_Liste_cate);

        grp_Check_gene.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);

        jPanel4.add(grp_Check_gene, gridBagConstraints);


        grp_liste_Fournisseur.setEnabled(false);

        grp_liste_Fournisseur.setIsLastComponent(true);

        grp_liste_Fournisseur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_liste_Fournisseur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_liste_Fournisseur, gridBagConstraints);


        grp_liste_Client.setEnabled(false);

        grp_liste_Client.setIsLastComponent(true);

        grp_liste_Client.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_liste_Client.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_liste_Client, gridBagConstraints);


        jPanel3.add(jPanel4);


        jPanel1.add(jPanel3);


        jPanel7.setLayout(new java.awt.GridBagLayout());


        jPanel6.add(jPanel7);


        jPanel1.add(jPanel6);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.BorderLayout());


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 200));

        grp_Table_CaissLibelle.setModel(new javax.swing.table.DefaultTableModel());

        jScrollPane1.setViewportView(grp_Table_CaissLibelle);


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

        try {

            caisselib = (srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T) model.getData().get(grp_Table_CaissLibelle.getSelectedRow());

            rmiInteface.delete(currentUser.getUrcleunik(), caisselib.getCale_cleunik());

            manfields.loadTable();

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }


    }

    public void doF10() {

    }

    public void doF7() {

    }

    public void doHelp() {

    }

    public void doModify() {

        caisselib = (srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T) model.getData().get(grp_Table_CaissLibelle.getSelectedRow());

        setFields();

        manfields.beforeCreate(true);

    }

    public void doNext() {

    }

    public void askForpassword(boolean modif) {

        //  new Pwd(parent.getSuperOwner(),true,modif,this.user,this,this.currentUser);

    }

    public void updateFields() {

        caisselib.setCale_lib1(grp_Tfield_Intitule1.getText());

        caisselib.setCale_lib2(grp_Tfield_Intitule2.getText());

        if (grp_Check_client.isSelected()) {

            caisselib.setCale_categorie(Caisselibelle_T.CLIENT);

            caisselib.setCe_cleunik(grp_liste_Client.getCleUnik());

        }

        if (grp_Check_fournisseur.isSelected()) {

            caisselib.setCale_categorie(Caisselibelle_T.FOURNISSEUR);

            caisselib.setCe_cleunik(grp_liste_Fournisseur.getCleUnik());


        }

        if (grp_Check_gene.isSelected()) {

            caisselib.setCale_categorie(Caisselibelle_T.GENE);

            caisselib.setCe_cleunik(grp_Liste_cate.getCleUnik());

        }

    }

    public void doPrevious() {

        if (grp_Check_client.isSelected()) {

            if (grp_liste_Client.getCleUnik() == 0)

                return;

        } else if (grp_Check_fournisseur.isSelected()) {

            if (grp_liste_Fournisseur.getCleUnik() == 0)

                return;


        } else if (grp_Check_gene.isSelected()) {

            if (grp_Liste_cate.getCleUnik() == 0)

                return;

        }

        updateFields();

        manfields.validate();

    }

    public void doPrint() {

    }

    public void doSwitch() {

    }

    public int[] getDefaultActionToolBarMask() {

        int[] tmp = null;

        if (manfields.mode == ActionToolBar.ACT_READ) {

            if (grp_Table_CaissLibelle.getRowCount() != 0) {

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

        grp_Tfield_Intitule1.setText(caisselib.getCale_lib1());

        grp_Tfield_Intitule2.setText(caisselib.getCale_lib2());

        if (caisselib.getCale_categorie() == caisselib.CLIENT) {

            grp_Check_client.setSelected(true);

            grp_liste_Client.setCleUnik(caisselib.getCe_cleunik());

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(true);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Client);


        }

        if (caisselib.getCale_categorie() == caisselib.FOURNISSEUR) {

            grp_Check_fournisseur.setSelected(true);

            grp_liste_Fournisseur.setCleUnik(caisselib.getCe_cleunik());

            grp_liste_Fournisseur.setVisible(true);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(false);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_liste_Fournisseur);


        }

        if (caisselib.getCale_categorie() == caisselib.GENE) {

            grp_Check_gene.setSelected(true);

            grp_Liste_cate.setCleUnik(caisselib.getCe_cleunik());

            grp_liste_Fournisseur.setVisible(false);

            grp_liste_Client.setVisible(false);

            grp_Liste_cate.setVisible(true);

            grp_Check_gene.setGrp_Comp_nextComponent(grp_Liste_cate);

        }


    }


    public void setThisAsToolBarComponent() {

    }


    public Object getObject() {

        return caisselib;

    }


    public GlobalRmiInterface getRmiInteface() {

        return rmiInteface;

    }


    public javax.swing.JTable getTable() {

        return grp_Table_CaissLibelle;

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

        //  return pass;

        return null;

    }


    /**
     * Setter for property pass.
     *
     * @param pass New value of property pass.
     */

    public void setPass(srcastra.astra.gui.modules.config.user.Pwd pass) {

        //  this.pass = pass;

    }


    public void reloadToolBarInfo() {

        toolbar.setActionEnabled(this.action);

    }


    public void saveToolBarInfo() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JLabel jLabel4;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfield_Intitule1;

    private javax.swing.JTable grp_Table_CaissLibelle;

    private javax.swing.ButtonGroup buttonGroup;

    private javax.swing.JScrollPane jScrollPane1;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_cate;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JPanel jPanel2;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfield_Intitule2;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JLabel jLabel9;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Check_fournisseur;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Check_gene;

    private javax.swing.JPanel jPanel7;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_Fournisseur;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel6;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Check_client;

    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_liste_Client;

    // End of variables declaration//GEN-END:variables


}

