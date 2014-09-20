/*





 * decision.java





 *





 * Created on 16 januari 2003, 14:19





 */


package srcastra.astra.gui.modules.aidedesicion;


import srcastra.astra.sys.configuration.AbstractRequete;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.gui.sys.utils.JTextAreaAdapter;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.components.AstraComponent;

import java.util.*;

import javax.swing.*;

import srcastra.astra.sys.classetransfert.dossier.produit_T;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.test.ColoredTableCellRenderer;

import javax.swing.table.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.gui.sys.listModel.dossierListModel.*;

import srcastra.astra.gui.sys.utils.*;

import srcastra.astra.gui.components.combobox.liste.*;

import java.awt.event.*;


/**
 * @author Thomas
 */


public class GeneriqueMemo extends srcastra.astra.gui.modules.config.AbstractConfig implements srcastra.astra.gui.modules.InternScreenModule, ToolBarComposer, java.awt.event.KeyListener {


    /**
     * Creates new form decision
     */


    AbstractDecisionScreenManager screenManager;

    public GeneriqueMemo(DossierMainScreenModule parent, ActionToolBar actionToolbar, astrainterface serveur, int typeDecision, boolean multiline, Hashtable generiqueVector) {

        this.parent = parent;

        mode = ActionToolBar.ACT_READ;

        m_serveur = parent.getServeur();

        m_actionToolBar = actionToolbar;

        initComponents();

        m_actionToolBar.setTbComposer(this);

        componantToVerif = new JTextAreaAdapter[]{new JTextAreaAdapter(grp_TField_Fr), new JTextAreaAdapter(grp_TField_NL), new JTextAreaAdapter(grp_TField_En), new JTextAreaAdapter(grp_TField_Ge), new JTextAreaAdapter(grp_TField_Es)};

        if (!multiline)

            showJradioButon(multiline);

        //getDefaultActionToolBarMask();


        initComp();

        //decTree=new DecFatory().getBuffer();


        long t = 0;


        try {


            decTree = (AbstractBuffer) m_serveur.workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, t, typeDecision);


        }


        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            se.printStackTrace();


        }


        catch (java.rmi.RemoteException re) {


            re.printStackTrace();


        }

        //  setDocument();


        initActions();


        initTable();

        screenManager = new DecisionScreenManager(this, parent, model, grp_Table_decision, m_actionToolBar, generiqueVector, typeDecision);

        screenManager.setMultiproduct(multiline);

        // initListe();


        getDefaultActionToolBarMask();


        addKeyListener(this);


        typeProduit = 1;

        //modelA=new TypeSupReducTicketListModel(parent.getServeur(), parent.getCurrentUser(),typeProduit);

        //  aCombo1.setModel(modelA);


        srcastra.astra.gui.sys.utils.SwitchColTable.switchCol(parent.getCurrentUser().getUrlmcleunik(), grp_Table_decision);


        m_seach = new SearchInTable(this, grp_TField_Encode, grp_Table_decision, 0, new SearchInDecision(0));

        /* grp_TField_Es.addActionListener(new ActionListener(){





            public void actionPerformed(ActionEvent evt){





              doPrevious();   





            }





        });*/

        //   grp_test.setServeur(parent.getServeur());

        // grp_test.setLogin(parent.getCurrentUser());

        // this.fournisseurModel=new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser());

        //grp_test.setModel(new srcastra.astra.gui.components.combobox.liste.SupReducListeTableModel(parent.getServeur(),parent.getCurrentUser(),1));

        //grp_test.init2();

        //grp_test.setColToSearch(3);

        requestFocus();

    }

    protected void showJradioButon(boolean visible) {

        grp_Jradio_Assurance.setVisible(visible);

        grp_Jradio_Aviation.setVisible(visible);

        grp_Jradio_Bateau.setVisible(visible);

        grp_Jradio_Brochure.setVisible(visible);

        grp_Jradio_Hotel.setVisible(visible);

        grp_Jradio_Taxi.setVisible(visible);

        grp_Jradio_Train.setVisible(visible);

        grp_Jradio_Voiture.setVisible(visible);

        requestFocus();

    }

    protected void initActions() {


        grp_Jradio_Assurance.addActionListener(this.click);

        grp_Jradio_Aviation.addActionListener(this.click);

        grp_Jradio_Bateau.addActionListener(this.click);

        grp_Jradio_Brochure.addActionListener(this.click);

        grp_Jradio_Hotel.addActionListener(this.click);

        grp_Jradio_Taxi.addActionListener(this.click);

        grp_Jradio_Train.addActionListener(this.click);

        grp_Jradio_Voiture.addActionListener(this.click);

        requestFocus();


    }

    /* protected void initListe(){





        SupReducListeTableModel m_model=new  SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(),1);





        TestListeTableModel model=new TestListeTableModel(parent.getServeur(),parent.getCurrentUser(),1);





        grp_ACB_libele.setServeur(parent.getServeur());





        grp_ACB_libele.setLogin(parent.getCurrentUser());





        grp_ACB_libele.setModel( m_model);





        grp_ACB_libele.init2(); 





        grp_ACB_libele.setColToSearch(3);





             





    }*/

/*  protected void setDocument(){





 grp_TField_Fr.setDocument(new DefaultMask(0, 150, parent.getCurrentUser().getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));





 grp_TField_En.setDocument(new DefaultMask(0, 150, parent.getCurrentUser().getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));





 grp_TField_Es.setDocument(new DefaultMask(0, 150, parent.getCurrentUser().getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));





 grp_TField_Ge.setDocument(new DefaultMask(0, 150, parent.getCurrentUser().getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));





 grp_TField_NL.setDocument(new DefaultMask(0, 150, parent.getCurrentUser().getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));





 requestFocus();











}*/


    protected void initComp() {

        compBefore = new Hashtable();

        compAfter = new Hashtable();

        compLangue = new ArrayList();

        langue = new Hashtable();

        compAfter.put(grp_Jradio_Aviation.getName(), new Object[]{grp_Jradio_Brochure, new Integer(produit_T.BRO), new Integer(produit_T.AV)});

        compAfter.put(grp_Jradio_Brochure.getName(), new Object[]{grp_Jradio_Assurance, new Integer(produit_T.AS), new Integer(produit_T.BRO)});

        compAfter.put(grp_Jradio_Assurance.getName(), new Object[]{grp_Jradio_Bateau, new Integer(produit_T.BA), new Integer(produit_T.AS)});

        compAfter.put(grp_Jradio_Bateau.getName(), new Object[]{grp_Jradio_Hotel, new Integer(produit_T.HO), new Integer(produit_T.BA)});

        compAfter.put(grp_Jradio_Hotel.getName(), new Object[]{grp_Jradio_Taxi, new Integer(produit_T.TAX), new Integer(produit_T.HO)});

        compAfter.put(grp_Jradio_Taxi.getName(), new Object[]{grp_Jradio_Train, new Integer(produit_T.TR), new Integer(produit_T.TAX)});

        compAfter.put(grp_Jradio_Train.getName(), new Object[]{grp_Jradio_Voiture, new Integer(produit_T.VO), new Integer(produit_T.TR)});

        compAfter.put(grp_Jradio_Voiture.getName(), new Object[]{grp_Jradio_Aviation, new Integer(produit_T.AV), new Integer(produit_T.VO)});

        compBefore.put(grp_Jradio_Aviation.getName(), new Object[]{grp_Jradio_Voiture, new Integer(produit_T.VO)});

        compBefore.put(grp_Jradio_Brochure.getName(), new Object[]{grp_Jradio_Aviation, new Integer(produit_T.AV)});

        compBefore.put(grp_Jradio_Assurance.getName(), new Object[]{grp_Jradio_Brochure, new Integer(produit_T.BRO)});

        compBefore.put(grp_Jradio_Bateau.getName(), new Object[]{grp_Jradio_Assurance, new Integer(produit_T.AS)});

        compBefore.put(grp_Jradio_Hotel.getName(), new Object[]{grp_Jradio_Bateau, new Integer(produit_T.BA)});

        compBefore.put(grp_Jradio_Taxi.getName(), new Object[]{grp_Jradio_Hotel, new Integer(produit_T.HO)});

        compBefore.put(grp_Jradio_Train.getName(), new Object[]{grp_Jradio_Taxi, new Integer(produit_T.TAX)});

        compBefore.put(grp_Jradio_Voiture.getName(), new Object[]{grp_Jradio_Train, new Integer(produit_T.TR)});       //compBefore.put(grp_Jradio_Aviation.getName(),grp_Jradio_Voiture);

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Fr), new Integer(1)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_NL), new Integer(2)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_En), new Integer(3)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Es), new Integer(4)});

        compLangue.add(new Object[]{new JTextAreaAdapter(grp_TField_Ge), new Integer(5)});

        langue.put(new Integer(1), new JTextAreaAdapter(grp_TField_Fr));

        langue.put(new Integer(2), new JTextAreaAdapter(grp_TField_NL));

        langue.put(new Integer(3), new JTextAreaAdapter(grp_TField_En));

        langue.put(new Integer(4), new JTextAreaAdapter(grp_TField_Es));

        langue.put(new Integer(5), new JTextAreaAdapter(grp_TField_Ge));

    }


    public void enableComponent(boolean enabled) {

        grp_Jradio_Aviation.setEnabled(enabled);

        grp_Jradio_Brochure.setEnabled(enabled);

        grp_Jradio_Assurance.setEnabled(enabled);

        grp_Jradio_Bateau.setEnabled(enabled);

        grp_Jradio_Hotel.setEnabled(enabled);

        grp_Jradio_Taxi.setEnabled(enabled);

        grp_Jradio_Train.setEnabled(enabled);

        grp_Jradio_Voiture.setEnabled(enabled);

        grp_Jradio_Aviation.setEnabled(enabled);

        grp_Jradio_Brochure.setEnabled(enabled);

        grp_Jradio_Assurance.setEnabled(enabled);

        grp_Jradio_Bateau.setEnabled(enabled);

        grp_Jradio_Hotel.setEnabled(enabled);

        grp_Jradio_Taxi.setEnabled(enabled);

        grp_Jradio_Train.setEnabled(enabled);

        grp_Table_decision.setEnabled(enabled);

    }


    protected void initTable() {

        model = new DecisionTableModel(parent.getCurrentUser(), parent, decTree);

        model.generiqueInt = 1;

        model.loadData();

        grp_Table_decision.setAutoCreateColumnsFromModel(false);

        grp_Table_decision.setSelectionBackground(new java.awt.Color(221, 221, 255));

        grp_Table_decision.setSelectionForeground(new java.awt.Color(0, 51, 153));

        grp_Table_decision.setModel(model);

        grp_Table_decision.getTableHeader().setReorderingAllowed(false);

        grp_Table_decision.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        for (int k = 0; k < model.m_columns.length; k++) {

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

            TableColumn column;

            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            grp_Table_decision.addColumn(column);

        }


        JTableHeader header = grp_Table_decision.getTableHeader();


        header.setUpdateTableInRealTime(false);


        grp_Table_decision.getTableHeader().setReorderingAllowed(false);


        grp_Table_decision.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


        if (grp_Table_decision.getRowCount() > 0) {


            grp_Table_decision.changeSelection(0, 0, false, false);


        }


        requestFocus();


    }


    protected java.awt.event.ActionListener click = new java.awt.event.ActionListener() {


        public void actionPerformed(java.awt.event.ActionEvent evt) {


            selectComponentByclicking(evt);


        }


    };


    public void selectComponentByclicking(java.awt.event.ActionEvent evt) {

        java.awt.Component comp = (java.awt.Component) evt.getSource();

        Object[] tmp = (Object[]) getCompAfter().get(comp.getName());

        typeProduit = ((Integer) tmp[2]).intValue();

        reloadTable();

        getDefaultActionToolBarMask();

        requestFocus();

        // System.out.println("Type de produit"+typeProduit);


    }


    /**
     * This method is called from within the constructor to
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
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */


    protected void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;


        buttonGroup1 = new javax.swing.ButtonGroup();

        jPanel1 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_decision = new javax.swing.JTable();

        jPanel2 = new javax.swing.JPanel();

        jLabel6 = new javax.swing.JLabel();

        grp_TField_Encode = new javax.swing.JTextField();

        jPanel4 = new javax.swing.JPanel();

        grp_Jradio_Aviation = new javax.swing.JRadioButton();

        grp_Jradio_Brochure = new javax.swing.JRadioButton();

        grp_Jradio_Assurance = new javax.swing.JRadioButton();

        grp_Jradio_Bateau = new javax.swing.JRadioButton();

        grp_Jradio_Hotel = new javax.swing.JRadioButton();

        grp_Jradio_Taxi = new javax.swing.JRadioButton();

        grp_Jradio_Train = new javax.swing.JRadioButton();

        grp_Jradio_Voiture = new javax.swing.JRadioButton();

        jPanel3 = new javax.swing.JPanel();

        jPanel6 = new javax.swing.JPanel();

        jPanel5 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jPanel9 = new javax.swing.JPanel();

        jScrollPane2 = new javax.swing.JScrollPane();

        grp_TField_Fr = new javax.swing.JTextArea();

        jPanel10 = new javax.swing.JPanel();

        jScrollPane3 = new javax.swing.JScrollPane();

        grp_TField_NL = new javax.swing.JTextArea();

        jPanel11 = new javax.swing.JPanel();

        jScrollPane4 = new javax.swing.JScrollPane();

        grp_TField_En = new javax.swing.JTextArea();

        jPanel7 = new javax.swing.JPanel();

        jPanel8 = new javax.swing.JPanel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jPanel12 = new javax.swing.JPanel();

        jScrollPane5 = new javax.swing.JScrollPane();

        grp_TField_Ge = new javax.swing.JTextArea();

        jPanel13 = new javax.swing.JPanel();

        jScrollPane6 = new javax.swing.JScrollPane();

        grp_TField_Es = new javax.swing.JTextArea();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(null, "Aide \u00e0 la d\u00e9cision", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        setPreferredSize(new java.awt.Dimension(30, 300));

        jPanel1.setLayout(new java.awt.BorderLayout());


        jPanel1.setPreferredSize(new java.awt.Dimension(10, 100));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 100));

        grp_Table_decision.setBackground(new java.awt.Color(255, 255, 255));

        grp_Table_decision.setModel(new javax.swing.table.DefaultTableModel());

        grp_Table_decision.setRequestFocusEnabled(false);

        jScrollPane1.setViewportView(grp_Table_decision);


        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel2.setBorder(new javax.swing.border.TitledBorder(null, "Produits", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel6.setText("Recherche");

        jPanel2.add(jLabel6);


        grp_TField_Encode.setPreferredSize(new java.awt.Dimension(100, 18));

        jPanel2.add(grp_TField_Encode);


        jPanel4.setLayout(new java.awt.GridBagLayout());


        grp_Jradio_Aviation.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Aviation.setSelected(true);

        grp_Jradio_Aviation.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_aviation"));

        buttonGroup1.add(grp_Jradio_Aviation);

        grp_Jradio_Aviation.setName("grp_Jradio_Aviation");

        grp_Jradio_Aviation.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusGained(java.awt.event.FocusEvent evt) {

                grp_Jradio_AviationFocusGained(evt);

            }

        });


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Aviation, gridBagConstraints);


        grp_Jradio_Brochure.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Brochure.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_brochure"));

        buttonGroup1.add(grp_Jradio_Brochure);

        grp_Jradio_Brochure.setName("grp_Jradio_Brochure");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Brochure, gridBagConstraints);


        grp_Jradio_Assurance.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Assurance.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_assurance"));

        buttonGroup1.add(grp_Jradio_Assurance);

        grp_Jradio_Assurance.setName("grp_Jradio_Assurance");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Assurance, gridBagConstraints);


        grp_Jradio_Bateau.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Bateau.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_Bateau"));

        buttonGroup1.add(grp_Jradio_Bateau);

        grp_Jradio_Bateau.setName("grp_Jradio_Bateau");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Bateau, gridBagConstraints);


        grp_Jradio_Hotel.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Hotel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_hotel"));

        buttonGroup1.add(grp_Jradio_Hotel);

        grp_Jradio_Hotel.setName("grp_Jradio_Hotel");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Hotel, gridBagConstraints);


        grp_Jradio_Taxi.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Taxi.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_taxi"));

        buttonGroup1.add(grp_Jradio_Taxi);

        grp_Jradio_Taxi.setName("grp_Jradio_Taxi");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Taxi, gridBagConstraints);


        grp_Jradio_Train.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Train.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_train"));

        buttonGroup1.add(grp_Jradio_Train);

        grp_Jradio_Train.setName("grp_Jradio_Train");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 6;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Train, gridBagConstraints);


        grp_Jradio_Voiture.setFont(new java.awt.Font("Tahoma", 1, 10));

        grp_Jradio_Voiture.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_voiture"));

        buttonGroup1.add(grp_Jradio_Voiture);

        grp_Jradio_Voiture.setName("grp_Jradio_Voiture");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 7;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Jradio_Voiture, gridBagConstraints);


        jPanel2.add(jPanel4);


        add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setLayout(new java.awt.GridLayout(1, 0));


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 200));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel5.setLayout(new java.awt.GridBagLayout());


        jLabel1.setText("FR");

        jLabel1.setPreferredSize(new java.awt.Dimension(80, 18));

        jPanel5.add(jLabel1, new java.awt.GridBagConstraints());


        jLabel2.setText("NDRL");

        jLabel2.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        jPanel5.add(jLabel2, gridBagConstraints);


        jLabel3.setText("EN");

        jLabel3.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        jPanel5.add(jLabel3, gridBagConstraints);


        jScrollPane2.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane2.setViewportView(grp_TField_Fr);


        jPanel9.add(jScrollPane2);


        jPanel5.add(jPanel9, new java.awt.GridBagConstraints());


        jScrollPane3.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane3.setViewportView(grp_TField_NL);


        jPanel10.add(jScrollPane3);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        jPanel5.add(jPanel10, gridBagConstraints);


        jScrollPane4.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane4.setViewportView(grp_TField_En);


        jPanel11.add(jScrollPane4);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        jPanel5.add(jPanel11, gridBagConstraints);


        jPanel6.add(jPanel5);


        jPanel3.add(jPanel6);


        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));


        jPanel8.setLayout(new java.awt.GridBagLayout());


        jLabel4.setText("GE");

        jLabel4.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);

        jPanel8.add(jLabel4, gridBagConstraints);


        jLabel5.setText("ES");

        jLabel5.setPreferredSize(new java.awt.Dimension(80, 18));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);

        jPanel8.add(jLabel5, gridBagConstraints);


        jScrollPane5.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane5.setViewportView(grp_TField_Ge);


        jPanel12.add(jScrollPane5);


        jPanel8.add(jPanel12, new java.awt.GridBagConstraints());


        jScrollPane6.setPreferredSize(new java.awt.Dimension(220, 50));

        jScrollPane6.setViewportView(grp_TField_Es);


        jPanel13.add(jScrollPane6);


        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 1;

        jPanel8.add(jPanel13, gridBagConstraints);


        jPanel7.add(jPanel8);


        jPanel3.add(jPanel7);


        add(jPanel3, java.awt.BorderLayout.SOUTH);


    }


    protected void grp_Jradio_AviationFocusGained(java.awt.event.FocusEvent evt) {


        setElementFocus();        // Add your handling code here:


    }


    protected JRadioButton getSelectComponent() {

        return screenManager.getSelectComponent();

        /*

         for (Enumeration e=buttonGroup1.getElements(); e.hasMoreElements(); ) {

            JRadioButton b = (JRadioButton)e.nextElement();

            if (b.getModel() == buttonGroup1.getSelection()) {

                return b;

            }         

        }      

      return null;*/

    }

    protected void selectComponent(boolean after) {

        screenManager.selectComponent(after);

        /*    JRadioButton b=getSelectComponent();

        if(after){

            Object[] tmp=(Object[])compAfter.get(b.getName());

            ((JRadioButton)tmp[0]).setSelected(true);

            typeProduit=((Integer)tmp[1]).intValue() ;           

        }

        else{

            Object[] tmp=(Object[])compBefore.get(b.getName());

            ((JRadioButton)tmp[0]).setSelected(true);

            typeProduit=((Integer)tmp[1]).intValue() ;            }

        reloadTable();

        getDefaultActionToolBarMask();

        requestFocus();*/

    }


    public void doAccept() {


    }


    public void doCancel() {

        screenManager.doCancel();/*

      if(mode==ActionToolBar.ACT_INSERT){





         displayReadMode();





         return;





      }





      else if(mode==ActionToolBar.ACT_MODIFY){





         displayReadMode();





         return;





      }





      else if(mode==ActionToolBar.ACT_READ){





         selectComponent(false);          





      }





      requestFocus();



*/

    }


    public void doClose() {


        screenManager.doClose();

    }


    public void doCreate() {


        screenManager.doCreate();

        //  displayInsertMode();


    }


    public void doDelete() {

        screenManager.doDelete();

        /*  Object[] tmp=model.getObject(grp_Table_decision.getSelectedRow());

        idToDelete=((Integer)tmp[5]).intValue();

        try{

             Object id=parent.getServeur().workWithDecisionMemo(null,parent.getCurrentUser().getUrcleunik(),ActionToolBar.DO_DELETE,null,idToDelete,typeProduit,0);

             decTree.afficheMe();

             decTree.removeChildren(new Integer(((Insert_T)id).getM_id()).toString(),typeProduit);

             decTree.setTimestamp(((Insert_T)id).getM_timestamp());

             reloadTable();

             displayReadMode();

        }

        catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

            se.printStackTrace();           

        }

           catch(java.rmi.RemoteException re){

                re.printStackTrace();           

        }*/

    }


    public void doHelp() {


    }


    public void doModify() {


        displayUpdateMode();


    }


    public void doNext() {


    }


    public void reloadTable() {

        screenManager.reloadTable();

        /*   model.generiqueInt=typeProduit;

                model.loadData();

                grp_Table_decision.tableChanged(new javax.swing.event.TableModelEvent(model));

                if(grp_Table_decision.getRowCount()>0)

                    grp_Table_decision.changeSelection(0,0,false,false);

        */

    }


    public boolean checkLangue() {

        return screenManager.checkLangue();

        /*  JTextArea tmpText=(JTextArea)langue.get(new Integer(parent.getCurrentUser().getUrlmcleunik()));

          if(tmpText.getText()!=null && !tmpText.getText().equals(""))

              return true;

          else return false;*/

    }


    public void doPrevious() {

        screenManager.doPrevious();


    }


    public void doPrint() {


    }


    public void doSwitch() {


    }


    public int[] getDefaultActionToolBarMask() {

        return screenManager.getDefaultActionToolBarMask();

        /*

        int[] tmp=null;

        if(mode==ActionToolBar.ACT_READ){

            if(grp_Table_decision.getRowCount()!=0){

                tmp=new int[]{ActionToolBar.DO_CREATE,

                           ActionToolBar.DO_MODIFY,

                           ActionToolBar.DO_CANCEL,

                           ActionToolBar.DO_PREVIOUS,

                           ActionToolBar.DO_CLOSE,

                           ActionToolBar.DO_DELETE,

                };

                m_actionToolBar.setActionEnabled(tmp);

            }

            else {

                tmp=new int[]{  ActionToolBar.DO_CREATE,                          

                                ActionToolBar.DO_PREVIOUS,           

                                ActionToolBar.DO_CANCEL,

                                ActionToolBar.DO_CLOSE,

                };     

                m_actionToolBar.setActionEnabled(tmp);

            }

        }

        else if(mode==ActionToolBar.ACT_INSERT){

            tmp= new int[]{ActionToolBar.DO_PREVIOUS,                          

                           ActionToolBar.DO_CANCEL,

                };  

                m_actionToolBar.setActionEnabled(tmp);

        }

         else if(mode==ActionToolBar.ACT_MODIFY){

            tmp= new int[]{ActionToolBar.DO_PREVIOUS,                          

                           ActionToolBar.DO_CANCEL,

                };  

                m_actionToolBar.setActionEnabled(tmp);

        }

        requestFocus();

        return tmp;*/

    }


    public void goUp() {


    }


    public void setThisAsToolBarComponent() {


    }


    public void keyPressed(java.awt.event.KeyEvent keyEvent) {


        System.out.println("Key Pressed");


        int key = keyEvent.getKeyCode();


        if (grp_Table_decision.getRowCount() > 0) {

            // System.out.println("ATENTION MON NOMBRE DE LIGNES TOTALES EST ========> " + grp_Table_SupReduc.getRowCount());


            int cur = grp_Table_decision.getSelectedRow();


            int tot = grp_Table_decision.getRowCount();


            if (key == keyEvent.VK_DOWN && (cur < tot - 1))


            {


                grp_Table_decision.changeSelection(cur + 1, 0, false, false);


                Object[] tmp = model.getObject(grp_Table_decision.getSelectedRow());


                for (int i = 0; i < componantToVerif.length; i++)


                {


                    componantToVerif[i].setText(tmp[i].toString());


                }


            }


            if (key == keyEvent.VK_UP && (cur > 0)) {


                grp_Table_decision.changeSelection(cur - 1, 0, false, false);


                Object[] tmp = model.getObject(grp_Table_decision.getSelectedRow());


                for (int i = 0; i < componantToVerif.length; i++)


                {


                    componantToVerif[i].setText(tmp[i].toString());


                }


            }
            ;


        }


    }


    public void keyReleased(java.awt.event.KeyEvent keyEvent) {


    }


    public void keyTyped(java.awt.event.KeyEvent keyEvent) {


    }


    /**
     * Permet au parent de lancer le chargement des données au
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * sein de liste (Ici : ListSelector)
     */


    public void chargeData() {


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


    /**
     * Affichage en Mode disable
     */


    public void displayDisableMode() {


    }


    public ArrayList genereUneEntree() {

        return screenManager.genereUneEntree();

        /* Object[] tmp=null;

    String tmps="";

    JTextArea tmpText=(JTextArea)langue.get(new Integer(parent.getCurrentUser().getUrlmcleunik()));

    ArrayList data=new ArrayList();

    if(tmpText.getText()==null || tmpText.getText()=="")

        return null;

    else

        tmps=tmpText.getText();

    for (int i=0;i<compLangue.size();i++)

    {

        tmp=new Object[4];

        Object[] tmpligne=(Object[])compLangue.get(i);

        JTextArea jr=(JTextArea)tmpligne[0];

        if(mode==ActionToolBar.ACT_INSERT)

            tmp[0]=new Integer(tmpId);

        else if(mode==ActionToolBar.ACT_MODIFY)

            tmp[0]=new Integer(idToModiFy);

        tmp[1]=new Integer(typeProduit);

        tmp[2]= (Integer)tmpligne[1];

        String s= jr.getText();

        if(s==null || s.equals(""))

            s=tmps;

        tmp[3]=s;

        data.add(tmp);

    }

    return data;   */

    }


    public void addData(ArrayList entree) {

        screenManager.addData(entree);

        /*    if(mode==ActionToolBar.ACT_INSERT){

      decTree.setData( entree,tmpId,typeProduit);

      tmpId=tmpId-1;

 }

 else

      decTree.setData( entree,idToModiFy,typeProduit); */

    }


    public void addData(ArrayList entree, int id, long timestamp) {

        screenManager.addData(entree, id, timestamp);

        /* if(mode==ActionToolBar.ACT_INSERT){

      decTree.setData( entree,id,typeProduit,timestamp);

      tmpId=tmpId-1;

 }

 else

      decTree.setData( entree,idToModiFy,typeProduit); */

    }


    /**
     * Affichage en mode Insertion
     */


    public void displayInsertMode() {

        screenManager.displayInsertMode();

        /* for(int i=0;i<componantToVerif.length;i++)

                {

                    componantToVerif[i].setText("");

                    componantToVerif[i].setEnabled(true);

                }

                jScrollPane1.setEnabled(true);

                mode=ActionToolBar.ACT_INSERT;

                getDefaultActionToolBarMask();

                enableComponent(false);

                ((JTextArea)langue.get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

        */

    }


    /**
     * Affichage en mode Lecture
     */


    public void displayReadMode() {


        screenManager.displayReadMode();/*

        for(int i=0;i<componantToVerif.length;i++)

        {

        // componantToVerif[i].setText();

         componantToVerif[i].setEnabled(false);           

        }

        mode=ActionToolBar.ACT_READ;

        getDefaultActionToolBarMask();

        enableComponent(true);

        if(grp_Table_decision.getRowCount()>0){

        Object[] tmp=model.getObject(grp_Table_decision.getSelectedRow());

            for(int i=0;i<componantToVerif.length;i++)

             {

                    componantToVerif[i].setText(tmp[i].toString());

             }

        }

        requestFocus();*/

    }


    /**
     * Affichage en mode Modification
     */


    public void displayUpdateMode() {


        screenManager.displayUpdateMode();/*

       Object[] tmp=model.getObject(grp_Table_decision.getSelectedRow());

        for(int i=0;i<componantToVerif.length;i++)

        {

         componantToVerif[i].setText(tmp[i].toString());

         componantToVerif[i].setEnabled(true);           

        }

        idToModiFy=((Integer)tmp[5]).intValue();

        mode=ActionToolBar.ACT_MODIFY;

        getDefaultActionToolBarMask();

        enableComponent(false);

       ((JTextArea)langue.get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

*/

    }


    /**
     * Permet à la classe qui implémente cette méthode de se
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * référencer auprès d' ActionToolBar
     *
     * @return le n° de l'action
     */


    public int getAction() {


        return 0;


    }


    /**
     * Sert à recevoir le titre de son parent
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */


    public String getTitle() {


        return null;


    }


    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 0 pour lecture
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 1 pour création
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 2 pour modification
     *
     * @param action type d'action
     */


    public void setAction(int action) {


    }


    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * au modules : création par partie ou modification
     *
     * @param frCleUnik la clé unique
     */


    public void setFrCleunik(int frCleUnik) {


    }


    /**
     * Méthode pour l'update de tous les champs
     */


    public void updateAllFields() {


    }


    public void updateAllFields(Object donnee) {


    }


    public java.awt.Component m_getGeneriqueTable() {


        return grp_Table_decision;


    }


    boolean africaine = false;


    public void setElementFocus() {


        if (!africaine) {


            m_actionToolBar.requestFocus();


            africaine = true;


        }


    }


    public void doF10() {


    }


    public java.awt.Component getMe() {

        return this;

    }


    public Hashtable getHashCompLangue() {

        return langue;

    }


    public AbstractBuffer getAbstractBuffer() {

        return decTree;

    }


    public ArrayList getHashCompLangue2() {

        return compLangue;

    }


    public AstraComponent[] getComponantToVerif() {

        return componantToVerif;

    }


    public ButtonGroup getButtonGroup() {

        return buttonGroup1;

    }


    public Hashtable getCompAfter() {

        return compAfter;

    }


    public Hashtable getCompBefore() {

        return compBefore;

    }


    public void addKeystroque() {

    }


    public void doF7() {

    }

    // Variables declaration - do not modify

    protected javax.swing.JPanel jPanel9;

    protected javax.swing.JPanel jPanel8;

    protected javax.swing.JTextArea grp_TField_Ge;

    protected javax.swing.JPanel jPanel7;

    protected javax.swing.JTextArea grp_TField_NL;

    protected javax.swing.JPanel jPanel6;

    protected javax.swing.JPanel jPanel5;

    protected javax.swing.JPanel jPanel4;

    protected javax.swing.JPanel jPanel3;

    protected javax.swing.JRadioButton grp_Jradio_Bateau;

    protected javax.swing.JRadioButton grp_Jradio_Brochure;

    protected javax.swing.JPanel jPanel2;

    protected javax.swing.JScrollPane jScrollPane6;

    protected javax.swing.JPanel jPanel1;

    protected javax.swing.JScrollPane jScrollPane5;

    protected javax.swing.JScrollPane jScrollPane4;

    protected javax.swing.JRadioButton grp_Jradio_Aviation;

    protected javax.swing.JTextField grp_TField_Encode;

    protected javax.swing.JScrollPane jScrollPane3;

    protected javax.swing.JRadioButton grp_Jradio_Train;

    protected javax.swing.JScrollPane jScrollPane2;

    protected javax.swing.JRadioButton grp_Jradio_Taxi;

    protected javax.swing.JScrollPane jScrollPane1;

    protected javax.swing.JRadioButton grp_Jradio_Voiture;

    protected javax.swing.JTextArea grp_TField_Fr;

    protected javax.swing.JTable grp_Table_decision;

    protected javax.swing.ButtonGroup buttonGroup1;

    protected javax.swing.JRadioButton grp_Jradio_Hotel;

    protected javax.swing.JPanel jPanel13;

    protected javax.swing.JPanel jPanel12;

    protected javax.swing.JPanel jPanel11;

    protected javax.swing.JPanel jPanel10;

    protected javax.swing.JRadioButton grp_Jradio_Assurance;

    protected javax.swing.JTextArea grp_TField_Es;

    protected javax.swing.JLabel jLabel6;

    protected javax.swing.JLabel jLabel5;

    protected javax.swing.JLabel jLabel4;

    protected javax.swing.JLabel jLabel3;

    protected javax.swing.JTextArea grp_TField_En;

    protected javax.swing.JLabel jLabel2;

    protected javax.swing.JLabel jLabel1;

    // End of variables declaration


    protected DossierMainScreenModule parent;


    protected ActionToolBar m_actionToolBar;

    //protected int mode;


    protected astrainterface m_serveur;


    protected JTextAreaAdapter[] componantToVerif;


    protected Hashtable compBefore;


    protected Hashtable compAfter;


    protected ArrayList compLangue;


    protected Hashtable langue;

    //   protected int typeProduit;

    // protected int tmpId=0;

    // protected int idToModiFy;


    protected AbstractBuffer decTree;


    DecisionTableModel model;

    // int idToDelete;


    srcastra.astra.gui.sys.utils.SearchInTable m_seach;


}





