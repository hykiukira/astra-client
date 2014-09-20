/*

 * Caisse.java

 *

 * Created on 8 août 2003, 9:19

 */


package srcastra.astra.gui.modules.compta.caisse;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.gui.components.celleditor.*;

import srcastra.astra.gui.modules.*;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.*;

import javax.swing.table.*;

import srcastra.astra.gui.components.combobox.liste.*;

import srcastra.astra.gui.components.tva.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.test.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.sys.formVerification.*;

import java.awt.event.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.test.ColumnData;

import srcastra.astra.gui.sys.*;

import java.awt.event.*;

import javax.swing.event.*;

import srcastra.astra.sys.classetransfert.utils.*;

import javax.swing.border.*;

import java.awt.event.*;

import srcastra.astra.gui.components.date.thedate.*;

import srcastra.astra.sys.compta.*;


/**
 * /**
 *
 * @author Thomas
 */

public class Caisse extends javax.swing.JInternalFrame implements MainScreenModule, AIframe, ActionListener, ToolBarComposer, ADateListener {


    /**
     * Creates new form Caisse
     */

    javax.swing.event.InternalFrameListener iFrameListener;

    CaisseModel model;

    MainScreenModule parent;

    ActionToolBar actionToolBar;

    astrainterface serveur;

    Loginusers_T currentUser;

    ArrayList paneltable;

    ArrayList actiontable;

    TitledBorder selected;

    Border notSelected;

    int selectedcount = 0;

    ActionListener action;

    int[] actiont;

    CaisseData data;


    public Caisse(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener) {

        parent = this;

        this.serveur = serveur;

        this.actionToolBar = actionToolBar;

        this.currentUser = currentUser;

        action = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                doPrevious();

            }

        };

        initComponents();

        postinit();

        initListeners();

        initListe();

        setDocument();

        this.iFrameListener = iFrameListener;

        this.addInternalFrameListener(iFrameListener);

        actionToolBar.setTbComposer(this);

        initTable();

        paneltable = new ArrayList();

        actiontable = new ArrayList();

        actiontable.add(new UserAction(this, serveur, this.currentUser));

        actiontable.add(new TransfertAction(this, serveur, this.currentUser));

        actiontable.add(new DiversAction(this, serveur, this.currentUser));

        // actiontable.add(new EditionAction(this,serveur,this.currentUser));

        paneltable.add(grp_pane_user);

        paneltable.add(grp_pane_transfert);

        paneltable.add(grp_pane_encais);

        // paneltable.add(grp_pane_edition);

        JPanel tmpanel = (JPanel) paneltable.get(selectedcount);

        ((TitledBorder) tmpanel.getBorder()).setTitleColor(new java.awt.Color(255, 0, 0));

        activeToolBarAction(ActionToolBar.ACT_READ);

        grp_liste_entite.setCleUnik(currentUser.getUreecleunik());

        grp_liste_user.setCleUnik(currentUser.getCle2());

        grp_date_date.setDate(CalculDate.getTodayDate());

        srcastra.astra.gui.modules.compta.caisse.Action action = getAction();

        action.doPrevious();


    }

    public void postinit() {

        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 204, 102));

        grp_Table_Caisse.getTableHeader().setBackground(new java.awt.Color(204, 0, 0));

        grp_Table_Caisse.getTableHeader().setForeground(new java.awt.Color(255, 204, 102));

        grp_Table_Caisse.getTableHeader().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));

    }

    private void setDocument() {

        grp_TField_ECheque.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));

        grp_TField_Ecash.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));

        grp_TField_SoCash.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));

        grp_TField_SoCheq.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));

        grp_TField_entre.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));

        grp_TextField_sortie.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(6, 2, parent.getCurrentUser().getLangage()));


    }

    public void refreshTable(ArrayList data) {

        this.model.setData(data);

        grp_Table_Caisse.tableChanged(new TableModelEvent(this.model));

        if (model.getRowCount() > 0) {

            grp_Table_Caisse.changeSelection((model.getRowCount() - 1), 0, false, false);

        }

    }

    private void initListe() {

        grp_liste_entite.setServeur(parent.getServeur());

        grp_liste_entite.setLogin(parent.getCurrentUser());

        grp_liste_entite.setModel(new srcastra.astra.gui.components.combobox.liste.EntiteListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_liste_entite.init2();

        grp_liste_user.setServeur(parent.getServeur());

        grp_liste_user.setLogin(parent.getCurrentUser());

        grp_liste_user.setModel(new srcastra.astra.gui.components.combobox.liste.UserListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_liste_user.init2();

        grp_liste_typedepense.setServeur(parent.getServeur());

        grp_liste_typedepense.setLogin(parent.getCurrentUser());

        grp_liste_typedepense.setModel(new srcastra.astra.gui.components.combobox.liste.CaisseLibelleTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_liste_typedepense.init2();

        grp_liste_typedepense.setFreeModeAllow(true);

    }


    private void initListeners() {

        grp_date_date.addActionListener(action);

        //  grp_TField_ECheque.addActionListener(action);

        // grp_TextField_sortie.addActionListener(action);

        //  grp_date_fin.addActionListener(action);

    }


    private void nextpanel() {

        JPanel tmpanel = (JPanel) paneltable.get(selectedcount);

        ((TitledBorder) tmpanel.getBorder()).setTitleColor(new java.awt.Color(0, 0, 0));

        tmpanel.repaint();

        if (selectedcount != (paneltable.size() - 1))

            selectedcount++;

        else selectedcount = 0;

        tmpanel = (JPanel) paneltable.get(selectedcount);

        notSelected = ((JPanel) paneltable.get(selectedcount)).getBorder();

        System.out.println("changecolor of panel " + selectedcount);

        ((TitledBorder) tmpanel.getBorder()).setTitleColor(new java.awt.Color(255, 0, 0));

        tmpanel.repaint();

    }


    public void activeToolBarAction(int type) {

        if (type == ActionToolBar.ACT_READ) {

            actiont = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_SWITCH, ActionToolBar.DO_CREATE};

        } else if (type == ActionToolBar.ACT_INSERT) {

            actiont = new int[]{ActionToolBar.DO_CREATE, ActionToolBar.DO_CANCEL, ActionToolBar.DO_PREVIOUS};

        }
        ;

        actionToolBar.setActionEnabled(actiont);


    }

    private void initTable() {

        grp_Table_Caisse.setFocusable(false);

        grp_Table_Caisse.setSelectionBackground(new java.awt.Color(204, 204, 255));

        this.model = new CaisseModel(parent, parent.getCurrentUser());

        grp_Table_Caisse.setAutoCreateColumnsFromModel(false);

        grp_Table_Caisse.getTableHeader().setReorderingAllowed(false);

        grp_Table_Caisse.setModel(this.model);

        grp_Table_Caisse.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        for (int k = 0; k < model.m_columns.length; k++) {

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

            TableColumn column = null;

            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            column.setHeaderValue(model.m_columns[k].c_title);

            grp_Table_Caisse.addColumn(column);

        }

        JTableHeader header = grp_Table_Caisse.getTableHeader();

        header.setUpdateTableInRealTime(false);

        //  header.setResizingAllowed(false);

        if (grp_Table_Caisse.getRowCount() != 0) ;

        grp_Table_Caisse.changeSelection(0, 0, false, false);

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

        jPanel2 = new javax.swing.JPanel();

        jPanel5 = new javax.swing.JPanel();

        grp_pane_transfert = new javax.swing.JPanel();

        jPanel10 = new javax.swing.JPanel();

        jLabel4 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        grp_TField_SoCheq = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_Ecash = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel5 = new javax.swing.JLabel();

        grp_TField_SoCash = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        grp_TField_ECheque = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TField_Scash = new javax.swing.JLabel();

        grp_TField_Scheq = new javax.swing.JLabel();

        grp_TField_transfertlib = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel7 = new javax.swing.JLabel();

        grp_pane_encais = new javax.swing.JPanel();

        jPanel11 = new javax.swing.JPanel();

        jLabel10 = new javax.swing.JLabel();

        jLabel13 = new javax.swing.JLabel();

        jLabel12 = new javax.swing.JLabel();

        grp_TField_entre = new srcastra.astra.gui.components.textFields.ATextField();

        grp_TextField_sortie = new srcastra.astra.gui.components.textFields.ATextField();

        grp_liste_typedepense = new srcastra.astra.gui.components.combobox.liste.Liste();

        jLabel6 = new javax.swing.JLabel();

        grp_TFiel_lib = new srcastra.astra.gui.components.textFields.ATextField();

        jPanel6 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_Caisse = new javax.swing.JTable();

        grp_pane_user = new javax.swing.JPanel();

        jPanel13 = new javax.swing.JPanel();

        jLabel15 = new javax.swing.JLabel();

        grp_liste_entite = new srcastra.astra.gui.components.combobox.liste.Liste();

        jLabel16 = new javax.swing.JLabel();

        jLabel17 = new javax.swing.JLabel();

        jLabel18 = new javax.swing.JLabel();

        grp_liste_user = new srcastra.astra.gui.components.combobox.liste.Liste();

        grp_date_date = new srcastra.astra.gui.components.date.thedate.ADate();

        jPanel4 = new javax.swing.JPanel();

        jPanel3 = new javax.swing.JPanel();

        jLabel20 = new javax.swing.JLabel();

        jLabel21 = new javax.swing.JLabel();

        grp_Label_solde = new javax.swing.JLabel();

        grp_Lable_soEntre = new javax.swing.JLabel();

        grp_Lable_soSortie = new javax.swing.JLabel();

        grp_Lable_CC = new javax.swing.JLabel();

        jPanel7 = new javax.swing.JPanel();


        setClosable(true);

        setForeground(java.awt.Color.lightGray);

        setIconifiable(true);

        setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_cai"));

        setName("caisse");

        setPreferredSize(new java.awt.Dimension(740, 530));

        jPanel1.setLayout(new java.awt.BorderLayout());


        jPanel2.setLayout(new java.awt.GridBagLayout());


        jPanel2.setPreferredSize(new java.awt.Dimension(30, 300));

        jPanel5.setLayout(new java.awt.GridLayout(3, 0));


        jPanel5.setPreferredSize(new java.awt.Dimension(260, 395));

        grp_pane_transfert.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        grp_pane_transfert.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_tran"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        grp_pane_transfert.setPreferredSize(new java.awt.Dimension(360, 93));

        jPanel10.setLayout(new java.awt.GridBagLayout());


        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_sol"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(jLabel4, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_sot"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(jLabel3, gridBagConstraints);


        grp_TField_SoCheq.setEnabled(false);

        grp_TField_SoCheq.setGrp_Comp_nextComponent(grp_TField_ECheque);

        grp_TField_SoCheq.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TField_SoCheq.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_SoCheq.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(grp_TField_SoCheq, gridBagConstraints);


        grp_TField_Ecash.setEnabled(false);

        grp_TField_Ecash.setGrp_Comp_nextComponent(grp_TField_SoCheq);

        grp_TField_Ecash.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TField_Ecash.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_Ecash.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(grp_TField_Ecash, gridBagConstraints);


        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_ent"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(jLabel5, gridBagConstraints);


        grp_TField_SoCash.setEnabled(false);

        grp_TField_SoCash.setGrp_Comp_nextComponent(grp_TField_Ecash);

        grp_TField_SoCash.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TField_SoCash.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_SoCash.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(grp_TField_SoCash, gridBagConstraints);


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_che"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel10.add(jLabel1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_cash"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel10.add(jLabel2, gridBagConstraints);


        grp_TField_ECheque.setEnabled(false);

        grp_TField_ECheque.setGrp_Comp_nextComponent(grp_TField_transfertlib);

        grp_TField_ECheque.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TField_ECheque.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_ECheque.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        grp_TField_ECheque.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {

                grp_TField_EChequeKeyReleased(evt);

            }

            public void keyPressed(java.awt.event.KeyEvent evt) {

                grp_TField_EChequeKeyPressed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel10.add(grp_TField_ECheque, gridBagConstraints);


        grp_TField_Scash.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_TField_Scash.setForeground(new java.awt.Color(204, 0, 0));

        grp_TField_Scash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        grp_TField_Scash.setPreferredSize(new java.awt.Dimension(50, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);

        jPanel10.add(grp_TField_Scash, gridBagConstraints);


        grp_TField_Scheq.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_TField_Scheq.setForeground(new java.awt.Color(204, 0, 0));

        grp_TField_Scheq.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        grp_TField_Scheq.setPreferredSize(new java.awt.Dimension(50, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);

        jPanel10.add(grp_TField_Scheq, gridBagConstraints);


        grp_TField_transfertlib.setEnabled(false);

        grp_TField_transfertlib.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_transfertlib.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        grp_TField_transfertlib.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyPressed(java.awt.event.KeyEvent evt) {

                grp_TField_transfertlibKeyPressed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        jPanel10.add(grp_TField_transfertlib, gridBagConstraints);


        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_lib"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel10.add(jLabel7, gridBagConstraints);


        grp_pane_transfert.add(jPanel10);


        jPanel5.add(grp_pane_transfert);


        grp_pane_encais.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        grp_pane_encais.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_encdec"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel11.setLayout(new java.awt.GridBagLayout());


        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_typ"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel11.add(jLabel10, gridBagConstraints);


        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_sot"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel11.add(jLabel13, gridBagConstraints);


        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_ent"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel11.add(jLabel12, gridBagConstraints);


        grp_TField_entre.setEnabled(false);

        grp_TField_entre.setGrp_Comp_nextComponent(grp_TextField_sortie);

        grp_TField_entre.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TField_entre.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TField_entre.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        grp_TField_entre.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyPressed(java.awt.event.KeyEvent evt) {

                grp_TField_entreKeyPressed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel11.add(grp_TField_entre, gridBagConstraints);


        grp_TextField_sortie.setEnabled(false);

        grp_TextField_sortie.setGrp_Comp_nextComponent(grp_TField_entre);

        grp_TextField_sortie.setPreferredSize(new java.awt.Dimension(75, 18));

        grp_TextField_sortie.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TextField_sortie.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        grp_TextField_sortie.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyPressed(java.awt.event.KeyEvent evt) {

                grp_TextField_sortieKeyPressed(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel11.add(grp_TextField_sortie, gridBagConstraints);


        grp_liste_typedepense.setEnabled(false);

        grp_liste_typedepense.setGrp_Comp_nextComponent(grp_TFiel_lib);

        grp_liste_typedepense.setPreferredSize(new java.awt.Dimension(200, 18));

        grp_liste_typedepense.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_liste_typedepense.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel11.add(grp_liste_typedepense, gridBagConstraints);


        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("com_libelle"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 2);

        jPanel11.add(jLabel6, gridBagConstraints);


        grp_TFiel_lib.setEnabled(false);

        grp_TFiel_lib.setGrp_Comp_nextComponent(grp_TextField_sortie);

        grp_TFiel_lib.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_TFiel_lib.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);

        jPanel11.add(grp_TFiel_lib, gridBagConstraints);


        grp_pane_encais.add(jPanel11);


        jPanel5.add(grp_pane_encais);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel2.add(jPanel5, gridBagConstraints);


        jPanel6.setLayout(new java.awt.BorderLayout());


        jPanel6.setPreferredSize(new java.awt.Dimension(460, 395));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 403));

        grp_Table_Caisse.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Table_Caisse.setModel(new DefaultTableModel());

        jScrollPane1.setViewportView(grp_Table_Caisse);


        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;

        jPanel2.add(jPanel6, gridBagConstraints);


        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);


        grp_pane_user.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        grp_pane_user.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_user"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        grp_pane_user.setPreferredSize(new java.awt.Dimension(10, 50));

        jPanel13.setLayout(new java.awt.GridBagLayout());


        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_user"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel13.add(jLabel15, gridBagConstraints);


        grp_liste_entite.setEnabled(false);

        grp_liste_entite.setGrp_Comp_nextComponent(grp_liste_user);

        grp_liste_entite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_liste_entite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        jPanel13.add(grp_liste_entite, gridBagConstraints);


        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel16.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_entit"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel13.add(jLabel16, gridBagConstraints);


        jLabel17.setFont(new java.awt.Font("Dialog", 1, 10));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 8;

        gridBagConstraints.gridy = 0;

        jPanel13.add(jLabel17, gridBagConstraints);


        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel18.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("cai_col_date"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 7;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel13.add(jLabel18, gridBagConstraints);


        grp_liste_user.setCanbenull(true);

        grp_liste_user.setEnabled(false);

        grp_liste_user.setGrp_Comp_nextComponent(grp_date_date);

        grp_liste_user.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_liste_user.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        jPanel13.add(grp_liste_user, gridBagConstraints);


        grp_pane_user.add(jPanel13);


        grp_date_date.setEnabled(false);

        grp_date_date.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_date_date.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        grp_pane_user.add(grp_date_date);


        jPanel1.add(grp_pane_user, java.awt.BorderLayout.NORTH);


        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));


        jPanel4.setPreferredSize(new java.awt.Dimension(10, 35));

        jPanel3.setLayout(new java.awt.GridBagLayout());


        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel20.setPreferredSize(new java.awt.Dimension(70, 13));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);

        jPanel3.add(jLabel20, gridBagConstraints);


        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel21.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ca_solde"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);

        jPanel3.add(jLabel21, gridBagConstraints);


        grp_Label_solde.setFont(new java.awt.Font("Tahoma", 1, 12));

        grp_Label_solde.setForeground(new java.awt.Color(204, 0, 0));

        grp_Label_solde.setPreferredSize(new java.awt.Dimension(70, 13));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);

        jPanel3.add(grp_Label_solde, gridBagConstraints);


        grp_Lable_soEntre.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Lable_soEntre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        grp_Lable_soEntre.setPreferredSize(new java.awt.Dimension(50, 16));

        jPanel3.add(grp_Lable_soEntre, new java.awt.GridBagConstraints());


        grp_Lable_soSortie.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Lable_soSortie.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        grp_Lable_soSortie.setPreferredSize(new java.awt.Dimension(50, 16));

        jPanel3.add(grp_Lable_soSortie, new java.awt.GridBagConstraints());


        grp_Lable_CC.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Lable_CC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        grp_Lable_CC.setPreferredSize(new java.awt.Dimension(50, 16));

        jPanel3.add(grp_Lable_CC, new java.awt.GridBagConstraints());


        jPanel4.add(jPanel3);


        jPanel4.add(jPanel7);


        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);


        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);


        pack();

    }//GEN-END:initComponents


    private void grp_TField_entreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_entreKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {

            doPrevious();

        }

    }//GEN-LAST:event_grp_TField_entreKeyPressed


    private void grp_TField_transfertlibKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_transfertlibKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {

            doPrevious();

        }

    }//GEN-LAST:event_grp_TField_transfertlibKeyPressed


    private void grp_TextField_sortieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TextField_sortieKeyPressed


    }//GEN-LAST:event_grp_TextField_sortieKeyPressed


    private void grp_TField_EChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_EChequeKeyPressed


    }//GEN-LAST:event_grp_TField_EChequeKeyPressed


    private void grp_TField_EChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_EChequeKeyReleased


    }//GEN-LAST:event_grp_TField_EChequeKeyReleased


    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

    }


    /**
     * Fermeture de l'écran courrant et passage
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * à l'écran d'index du module
     */

    public void cancelModule() {

    }


    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {

    }


    /**
     * Permet de charger le panel des statuts
     */

    public void chargeStatusPanel(String[] statuts) {

    }


    /**
     * Ferme le module
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * (dans Astra -> JInternalFrame)
     */

    public void closeModule() {

    }


    /**
     * Lance la séquence de création
     */

    public void displayCreateSequence() {

    }


    /**
     * Lance la séquence de suppression
     */

    public void displayDeleteSequence() {

    }


    /**
     * Lance la séquence de lecture
     */

    public void displayReadSequence(int cleUnik) {

    }


    public void doAccept() {

    }


    public void doCancel() {

        srcastra.astra.gui.modules.compta.caisse.Action action = getAction();

        if (action != null)

            action.doCancel();

        else

            System.out.println("action null pour" + selectedcount);

    }

    public void doClose() {

        doDefaultCloseAction();

    }

    public void doDefaultCloseAction() {

        try {

            serveur.remoterollback(getCurrentUser().getUrcleunik());

        } catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        InternalFrameEvent closeWindow = new InternalFrameEvent(this, InternalFrameEvent.INTERNAL_FRAME_CLOSING);

        this.iFrameListener.internalFrameClosing(closeWindow);

        super.doDefaultCloseAction();

    }

    private srcastra.astra.gui.modules.compta.caisse.Action getAction() {

        if (selectedcount < actiontable.size())

            return (Action) actiontable.get(selectedcount);

        return null;

    }

    public srcastra.astra.gui.modules.compta.caisse.Action getAction(int i) {

        return (Action) actiontable.get(i);

    }

    public void doCreate() {

        srcastra.astra.gui.modules.compta.caisse.Action action = getAction();

        if (action != null) {
            action.doCreate();

            if (currentUser.getUrrights() != 1) {
                grp_liste_user.setEnabled(false);
            }
        } else

            System.out.println("action null pour" + selectedcount);

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

    }


    public void doNext() {

    }


    public void doPrevious() {

        srcastra.astra.gui.modules.compta.caisse.Action action = getAction();

        if (action != null)

            action.doPrevious();

        else

            System.out.println("action null pour" + selectedcount);

    }


    public void doPrint() {

    }


    public void doSwitch() {

        nextpanel();

    }


    /**
     * Permet de changer l'état de la TabbedPane dans le pricipal
     */

    public void enabledTabbedPane(boolean enabled) {

    }


    public Loginusers_T getCurrentUser() {

        return currentUser;

    }


    public int[] getDefaultActionToolBarMask() {

        return null;

    }

    public boolean getNestedSignaletique() {

        return true;

    }


    public java.awt.Frame getOwner() {

        return null;

    }


    public astrainterface getServeur() {

        return serveur;

    }


    public DossierRmiInterface getServeurDossier() {

        return null;

    }


    public java.awt.Frame getSuperOwner() {

        return null;

    }


    public java.awt.Component m_getGeneriqueTable() {

        return null;

    }


    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */

    public void nextScreen(int currentScreen) {


    }


    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */

    public void nextScreen(int currentScreen, boolean affich) {

    }


    public void nextScreen(int currentScreen, int insert) {

    }


    public void registerTable(javax.swing.JTable generique_table) {

    }

    public void reloadToolBarInfo() {

        actionToolBar.setTbComposer(this);

        actionToolBar.setActionEnabled(actiont);


    }


    public void saveToolBarInfo() {

    }


    /**
     * Fixe la clé unique dans le module parent
     *
     * @param ContextCleUnik clé unique
     */

    public void setContextCleUnik(int ContextCleUnik) {

    }


    /**
     * permet d'établir une liste d'action choisie comme étant les actions de la ToobBar
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * + permet à la classe principale du modules de pouvoir sauvegarder le tableau des actions
     */

    public void setCurrentActionEnabled(int[] actionEnabled) {

    }


    /**
     * permet d'établir un panneau comme panneau gestionnaire de la toolbar (voir tbComposer) +
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * permet à la classe principale du modules de pouvoir sauvegarder l'objet TbComposer
     */

    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {

    }


    public void setNestedSignaletique(boolean netstedSignletique) {

    }


    public void setThisAsToolBarComponent() {

    }


    /**
     * Getter for property grp_TField_Ecash.
     *
     * @return Value of property grp_TField_Ecash.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_Ecash() {

        return grp_TField_Ecash;

    }


    /**
     * Setter for property grp_TField_Ecash.
     *
     * @param grp_TField_Ecash New value of property grp_TField_Ecash.
     */

    public void setGrp_TField_Ecash(srcastra.astra.gui.components.textFields.ATextField grp_TField_Ecash) {

        this.grp_TField_Ecash = grp_TField_Ecash;

    }


    /**
     * Getter for property grp_TField_ECheque.
     *
     * @return Value of property grp_TField_ECheque.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_ECheque() {

        return grp_TField_ECheque;

    }


    /**
     * Setter for property grp_TField_ECheque.
     *
     * @param grp_TField_ECheque New value of property grp_TField_ECheque.
     */

    public void setGrp_TField_ECheque(srcastra.astra.gui.components.textFields.ATextField grp_TField_ECheque) {

        this.grp_TField_ECheque = grp_TField_ECheque;

    }

    /** Getter for property grp_TField_Scash.

     * @return Value of property grp_TField_Scash.

     */


    /**
     * Getter for property grp_TField_Scheq.
     *
     * @return Value of property grp_TField_Scheq.
     */


    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_SoCheq() {

        return grp_TField_SoCheq;

    }


    /**
     * Setter for property grp_TField_SoCheq.
     *
     * @param grp_TField_SoCheq New value of property grp_TField_SoCheq.
     */

    public void setGrp_TField_SoCheq(srcastra.astra.gui.components.textFields.ATextField grp_TField_SoCheq) {

        this.grp_TField_SoCheq = grp_TField_SoCheq;

    }


    /**
     * Getter for property grp_TField_SoCash.
     *
     * @return Value of property grp_TField_SoCash.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_SoCash() {

        return grp_TField_SoCash;

    }


    /**
     * Setter for property grp_TField_SoCash.
     *
     * @param grp_TField_SoCash New value of property grp_TField_SoCash.
     */

    public void setGrp_TField_SoCash(srcastra.astra.gui.components.textFields.ATextField grp_TField_SoCash) {

        this.grp_TField_SoCash = grp_TField_SoCash;

    }


    /**
     * Getter for property grp_liste_user.
     *
     * @return Value of property grp_liste_user.
     */

    public srcastra.astra.gui.components.combobox.liste.Liste getGrp_liste_user() {

        return grp_liste_user;

    }


    /**
     * Setter for property grp_liste_user.
     *
     * @param grp_liste_user New value of property grp_liste_user.
     */

    public void setGrp_liste_user(srcastra.astra.gui.components.combobox.liste.Liste grp_liste_user) {

        this.grp_liste_user = grp_liste_user;

    }


    /**
     * Getter for property grp_liste_entite.
     *
     * @return Value of property grp_liste_entite.
     */

    public srcastra.astra.gui.components.combobox.liste.Liste getGrp_liste_entite() {

        return grp_liste_entite;

    }


    /**
     * Setter for property grp_liste_entite.
     *
     * @param grp_liste_entite New value of property grp_liste_entite.
     */

    public void setGrp_liste_entite(srcastra.astra.gui.components.combobox.liste.Liste grp_liste_entite) {

        this.grp_liste_entite = grp_liste_entite;

    }


    /**
     * Getter for property grp_date_date.
     *
     * @return Value of property grp_date_date.
     */

    public srcastra.astra.gui.components.date.thedate.ADate getGrp_date_date() {

        return grp_date_date;

    }


    /**
     * Setter for property grp_date_date.
     *
     * @param grp_date_date New value of property grp_date_date.
     */

    public void setGrp_date_date(srcastra.astra.gui.components.date.thedate.ADate grp_date_date) {

        this.grp_date_date = grp_date_date;

    }

    /** Getter for property grp_date_debut.

     * @return Value of property grp_date_debut.

     */

    /** Setter for property grp_date_fin.

     * @param grp_date_fin New value of property grp_date_fin.

     */


    /**
     * Getter for property grp_TextField_libelle.
     *
     * @return Value of property grp_TextField_libelle.
     */


    public srcastra.astra.gui.components.textFields.ATextField getGrp_TextField_sortie() {

        return grp_TextField_sortie;

    }


    /**
     * Setter for property grp_TextField_sortie.
     *
     * @param grp_TextField_sortie New value of property grp_TextField_sortie.
     */

    public void setGrp_TextField_sortie(srcastra.astra.gui.components.textFields.ATextField grp_TextField_sortie) {

        this.grp_TextField_sortie = grp_TextField_sortie;

    }


    /**
     * Getter for property grp_TField_entre.
     *
     * @return Value of property grp_TField_entre.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_entre() {

        return grp_TField_entre;

    }


    /**
     * Setter for property grp_TField_entre.
     *
     * @param grp_TField_entre New value of property grp_TField_entre.
     */

    public void setGrp_TField_entre(srcastra.astra.gui.components.textFields.ATextField grp_TField_entre) {

        this.grp_TField_entre = grp_TField_entre;

    }


    /**
     * Getter for property grp_liste_typedepense.
     *
     * @return Value of property grp_liste_typedepense.
     */

    public srcastra.astra.gui.components.combobox.liste.Liste getGrp_liste_typedepense() {

        return grp_liste_typedepense;

    }


    /**
     * Setter for property grp_liste_typedepense.
     *
     * @param grp_liste_typedepense New value of property grp_liste_typedepense.
     */

    public void setGrp_liste_typedepense(srcastra.astra.gui.components.combobox.liste.Liste grp_liste_typedepense) {

        this.grp_liste_typedepense = grp_liste_typedepense;

    }


    /**
     * Getter for property grp_Label_solde.
     *
     * @return Value of property grp_Label_solde.
     */

    public javax.swing.JLabel getGrp_Label_solde() {

        return grp_Label_solde;

    }


    /**
     * Setter for property grp_Label_solde.
     *
     * @param grp_Label_solde New value of property grp_Label_solde.
     */

    public void setGrp_Label_solde(javax.swing.JLabel grp_Label_solde) {

        this.grp_Label_solde = grp_Label_solde;

    }


    public void dateSmallerThenCurrenTime(ADateEvent evt) {

    }


    /**
     * Getter for property grp_TField_Scheq.
     *
     * @return Value of property grp_TField_Scheq.
     */

    public javax.swing.JLabel getGrp_TField_Scheq() {

        return grp_TField_Scheq;

    }


    /**
     * Setter for property grp_TField_Scheq.
     *
     * @param grp_TField_Scheq New value of property grp_TField_Scheq.
     */

    public void setGrp_TField_Scheq(javax.swing.JLabel grp_TField_Scheq) {

        this.grp_TField_Scheq = grp_TField_Scheq;

    }


    /**
     * Getter for property grp_TField_Scash.
     *
     * @return Value of property grp_TField_Scash.
     */

    public javax.swing.JLabel getGrp_TField_Scash() {

        return grp_TField_Scash;

    }


    /**
     * Setter for property grp_TField_Scash.
     *
     * @param grp_TField_Scash New value of property grp_TField_Scash.
     */

    public void setGrp_TField_Scash(javax.swing.JLabel grp_TField_Scash) {

        this.grp_TField_Scash = grp_TField_Scash;

    }


    /**
     * Getter for property grp_TField_transfertlib.
     *
     * @return Value of property grp_TField_transfertlib.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_transfertlib() {

        return grp_TField_transfertlib;

    }


    /**
     * Setter for property grp_TField_transfertlib.
     *
     * @param grp_TField_transfertlib New value of property grp_TField_transfertlib.
     */

    public void setGrp_TField_transfertlib(srcastra.astra.gui.components.textFields.ATextField grp_TField_transfertlib) {

        this.grp_TField_transfertlib = grp_TField_transfertlib;

    }


    /**
     * Getter for property data.
     *
     * @return Value of property data.
     */

    public srcastra.astra.sys.compta.CaisseData getData() {

        return data;

    }


    /**
     * Setter for property data.
     *
     * @param data New value of property data.
     */

    public void setData(srcastra.astra.sys.compta.CaisseData data) {

        this.data = data;

    }


    /**
     * Getter for property grp_Lable_soEntre.
     *
     * @return Value of property grp_Lable_soEntre.
     */

    public javax.swing.JLabel getGrp_Lable_soEntre() {

        return grp_Lable_soEntre;

    }


    /**
     * Setter for property grp_Lable_soEntre.
     *
     * @param grp_Lable_soEntre New value of property grp_Lable_soEntre.
     */

    public void setGrp_Lable_soEntre(javax.swing.JLabel grp_Lable_soEntre) {

        this.grp_Lable_soEntre = grp_Lable_soEntre;

    }


    /**
     * Getter for property grp_Lable_soSortie.
     *
     * @return Value of property grp_Lable_soSortie.
     */

    public javax.swing.JLabel getGrp_Lable_soSortie() {

        return grp_Lable_soSortie;

    }


    /**
     * Setter for property grp_Lable_soSortie.
     *
     * @param grp_Lable_soSortie New value of property grp_Lable_soSortie.
     */

    public void setGrp_Lable_soSortie(javax.swing.JLabel grp_Lable_soSortie) {

        this.grp_Lable_soSortie = grp_Lable_soSortie;

    }


    /**
     * Getter for property grp_Lable_CC.
     *
     * @return Value of property grp_Lable_CC.
     */

    public javax.swing.JLabel getGrp_Lable_CC() {

        return grp_Lable_CC;

    }


    /**
     * Setter for property grp_Lable_CC.
     *
     * @param grp_Lable_CC New value of property grp_Lable_CC.
     */

    public void setGrp_Lable_CC(javax.swing.JLabel grp_Lable_CC) {

        this.grp_Lable_CC = grp_Lable_CC;

    }


    /**
     * Getter for property grp_TFiel_lib.
     *
     * @return Value of property grp_TFiel_lib.
     */

    public srcastra.astra.gui.components.textFields.ATextField getGrp_TFiel_lib() {

        return grp_TFiel_lib;

    }


    /**
     * Setter for property grp_TFiel_lib.
     *
     * @param grp_TFiel_lib New value of property grp_TFiel_lib.
     */

    public void setGrp_TFiel_lib(srcastra.astra.gui.components.textFields.ATextField grp_TFiel_lib) {

        this.grp_TFiel_lib = grp_TFiel_lib;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JLabel grp_Label_solde;

    private javax.swing.JLabel grp_Lable_CC;

    private javax.swing.JLabel grp_Lable_soEntre;

    private javax.swing.JLabel grp_Lable_soSortie;

    private srcastra.astra.gui.components.textFields.ATextField grp_TFiel_lib;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_ECheque;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Ecash;

    private javax.swing.JLabel grp_TField_Scash;

    private javax.swing.JLabel grp_TField_Scheq;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_SoCash;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_SoCheq;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_entre;

    private srcastra.astra.gui.components.textFields.ATextField grp_TField_transfertlib;

    private javax.swing.JTable grp_Table_Caisse;

    private srcastra.astra.gui.components.textFields.ATextField grp_TextField_sortie;

    private srcastra.astra.gui.components.date.thedate.ADate grp_date_date;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_entite;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_typedepense;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_user;

    private javax.swing.JPanel grp_pane_encais;

    private javax.swing.JPanel grp_pane_transfert;

    private javax.swing.JPanel grp_pane_user;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel16;

    private javax.swing.JLabel jLabel17;

    private javax.swing.JLabel jLabel18;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel20;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel10;

    private javax.swing.JPanel jPanel11;

    private javax.swing.JPanel jPanel13;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JPanel jPanel7;

    private javax.swing.JScrollPane jScrollPane1;

    // End of variables declaration//GEN-END:variables


}

