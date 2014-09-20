/*

 * IntegrationFrame.java

 *

 * Created on 7 octobre 2003, 9:59

 */



package srcastra.astra.gui.modules.dossier.productSpecification.integration;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.components.AIframe;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.sys.rmi.DossierRmiInterface;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierTableModelInterface;

import java.util.ArrayList;

import srcastra.astra.gui.sys.MessageManager;

import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;

import java.util.*;

import java.text.*;

import srcastra.astra.sys.classetransfert.utils.*;

import srcastra.astra.gui.sys.utils.*;

import java.awt.*;

import srcastra.astra.sys.classetransfert.dossier.assurance.*;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.sys.classetransfert.dossier.hotel.*;

import srcastra.astra.sys.classetransfert.dossier.bateau.*;

import srcastra.astra.sys.classetransfert.dossier.train.*;

import srcastra.astra.sys.classetransfert.dossier.car.*;

import srcastra.astra.sys.classetransfert.dossier.voitureLocation.*;

import srcastra.astra.sys.classetransfert.dossier.taxi.*;

import srcastra.astra.sys.compta.*;

import javax.swing.event.InternalFrameEvent;

import java.awt.event.*;

import java.io.*;

import srcastra.astra.gui.*;

import srcastra.test.*;

import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.KeyStrokeManager;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import javax.swing.KeyStroke.*;

import javax.swing.table.*;

import javax.swing.event.*;

import javax.swing.*;

import srcastra.astra.gui.modules.dossier.utils.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;

import javax.swing.border.*;

/**

 *

 * @author  Thomas

 */

public class IntegrationFrame extends javax.swing.JInternalFrame implements MainScreenModule, AIframe,ToolBarComposer, ListSelectionListener  {

    

    /** Creates new form IntegrationFrame */

     IntegrationModel model;

    IntegratedModel model2;

    Loginusers_T user;

    Loginusers_T currentUser;

    ArrayList data=new ArrayList();

    ServeurConnect connect;

    srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel  tbMod_grpDeProd;

    srcastra.astra.gui.components.combobox.liste.FeesListeTableModel  feesmodel;

    int clientcleunik;

    String clientName;

    int grpProd;

    double val_net;

    astrainterface serveur;

    MainFrame mainframe;
    
    boolean first=false;

   // KeyStrokeManager keystroke;

    MainScreenModule parent;

    boolean swclient;

     javax.swing.event.InternalFrameListener iFrameListener;

    ActionToolBar actionToolBar;

    int [] actiontab;

    int selectedPanel;

    private static int PANEL1=1;

    private static int PANEL2=2;

    private static int FIELD1=1;

    private static int FIELD2=2;

    int mode;

    Border normalBorder;

    Border selectedBorder;

    public IntegrationFrame(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener,MainFrame mainframe)  {

        parent=this;

        mode=ActionToolBar.ACT_READ;

        selectedPanel=PANEL1;

        

        this.currentUser=currentUser;

        this.iFrameListener=iFrameListener;

        this.actionToolBar=actionToolBar;

        this.actionToolBar.setTbComposer(this);

        

       

        this.addInternalFrameListener(iFrameListener);

        initComponents();

        selectedBorder=new javax.swing.border.LineBorder(new java.awt.Color(0,0,102), 5);

        normalBorder=jPanel5.getBorder();

        

      

        this.mainframe=mainframe;

        if(serveur==null && user==null){

            connect=new ServeurConnect();

            connect.connectServeur();

            this.serveur=connect.serveur2;

            this.user=connect.tmpgestion2;

        }

        else{

            this.serveur=serveur;

            this.user=user;

            

        }

       

        System.out.println(currentUser.getName());

        initComponents();

        enableFields(FIELD1,false);

        enableFields(FIELD2,false);

     //  test();

        initTable();

        initTable2();

        initListe();

        setDocument();

        model.setData(data);

        grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));  

       // jButton1.setMnemonic(KeyEvent.VK_ENTER);

       // jButton3.setMnemonic(KeyEvent.VK_F5);

       

        activeToolBarAction();

        selectPanel(PANEL1);

        grp_Table_TicketChoice.getSelectionModel().addListSelectionListener(this);

        grp_button_refund.setVisible(false);



     

     //    keystroke=new KeyStrokeManager(true);

       //  keystroke.registerComponent(this);

         // keystroke.setAction(new int[]{ActionToolBar.DO_PREVIOUS});

    }

    public void enableFields(int type,boolean enable){

           // grp_LSelect_Dossier_ClientContractuel.setCleUnik(0); 
           // grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);
           // grp_LSelect_Dossier_ClientContractuel.setText("");
        
      if(type==FIELD1){

          grp_TField_po.setEnabled(enable);           

          if(enable){

              grp_TField_po.requestFocus();

              adaptBackgroundColorTicketChoice(false);

              if(grp_LSelect_Dossier_ClientContractuel.getCleUnik()==0)

                grp_LSelect_Dossier_ClientContractuel.setEnabled(enable);

          }

          else

              grp_LSelect_Dossier_ClientContractuel.setEnabled(enable);

      }else if(type==FIELD2){

          grp_tfield_fees.setEnabled(enable);

          grp_tfield_rem.setEnabled(enable);

          grp_tfield_tdest.setEnabled(enable);

          grp_tfield_tloc.setEnabled(enable);

          grp_LSelect_GrpProduits.setEnabled(enable);       

          grp_tfield_tcomp.setEnabled(enable);       

          grp_liste_fees.setEnabled(enable); 
          
          //add by me
          grp_liste_fees.setVisible(false);

          grp_Formated_payement.setEnabled(enable);

          if(enable)

              grp_Formated_payement.requestFocus();

      }

      if(enable)

              adaptBackgroundColorTicketChoice(false);

      else

          adaptBackgroundColorTicketChoice(true);

    }

    private void selectPanel(int panel){

      if(panel==PANEL1){

         jPanel5.setBorder(selectedBorder);

         jPanel4.setBorder(normalBorder);     

         selectedPanel=PANEL1;

      }else{

         jPanel5.setBorder(normalBorder);

         jPanel4.setBorder(selectedBorder);                   

         selectedPanel=PANEL2;

      }

    }

    private void voidTicket(){ // permet de voider un billet qui n'est pas encore intégrer 

                                              //afin d'avoir un bsp cohérant

        //              0         1        2           3       4           5        6        7        8           9         10        11        12          13        14    

        //SELECT tion_cleunik,v_po_pnr,num_billet,passager,date_dep,val_facial,cnj_billet,val_vente,val_net,tax_locale,tax_compag,tax_destin,val_commis,val_remise,num_cccf

        int row=grp_Table_TicketChoice.getSelectedRow();

        Object[] tab=(Object[])model.getDataTab(row);

        long tion_cleunik=Long.parseLong(tab[0].toString());

        Hashtable table=new Hashtable();

        Object[] valuetab=new Object[10];

           valuetab[0]=new Integer(tab[0].toString()); //tioncleunik

           valuetab[1]=new Integer(0); //clientcleunik

           valuetab[2]=new Integer(0); //grpdcleunik

           valuetab[3]=new Double(tab[7].toString()); //valvente

           valuetab[4]=new Integer(26);

           valuetab[5]=new Double(tab[9].toString());//taxloc

           valuetab[6]=new Double(tab[10].toString());//taxcomp

           valuetab[7]=new Double(0);//taxdest

           valuetab[8]=new Double(0);//remise

           valuetab[9]=new Double(0);//fees

           table.put(new Long(tion_cleunik),valuetab);

        try{

           serveur.renvDossierRmiObject(currentUser.getUrcleunik()).voidTicket(currentUser.getUrcleunik(), table, currentUser);

           model.getArray().remove(tab);

           grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));

           if(grp_Table_TicketChoice.getRowCount()>0){

                grp_Table_TicketChoice.changeSelection(0,0,false,false);

            }

           

        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);                

        }catch(java.rmi.RemoteException re){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

                }        

    } 

     public void adaptBackgroundColorTicketChoice(boolean enabled) {

        java.awt.Color background = enabled ? java.awt.Color.white : java.awt.Color.lightGray;

        grp_Table_TicketChoice.setBackground(background);

        grp_Table_TicketChoice.setRowSelectionAllowed(enabled);

        if (enabled && grp_Table_TicketChoice.getRowCount() > 0) grp_Table_TicketChoice.changeSelection(0, 0, false, false);

    }    

      public void adaptBackgroundColorTicketIntegrated(boolean enabled) {

        java.awt.Color background = enabled ? java.awt.Color.white : java.awt.Color.lightGray;

        grp_Table_integrated.setBackground(background);

        grp_Table_integrated.setRowSelectionAllowed(enabled);

        if (enabled && grp_Table_integrated.getRowCount() > 0) grp_Table_integrated.changeSelection(0, 0, false, false);

    }

     private void test(){

      Object[] tab=new Object[]{new Integer(1),new Boolean(false),"po56","ticket78","passager tamere","10-10-2003","100","0","","0","0",""};

      data.add(tab);

      tab=new Object[]{new Integer(1),new Boolean(false),"po56","ticket78","passager tamere","10-10-2003","100","0","","0","0",""};

      data.add(tab);     

    }

    private void initListe(){

        grp_LSelect_GrpProduits.setServeur(serveur);

        grp_LSelect_GrpProduits.setLogin(currentUser);

        tbMod_grpDeProd=new srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel(serveur,currentUser);

        grp_LSelect_GrpProduits.setModel(this.tbMod_grpDeProd);

        grp_LSelect_GrpProduits.init2();    

        tbMod_grpDeProd.setM_frcleunik(26);

        tbMod_grpDeProd.loadata();

        

        feesmodel=new srcastra.astra.gui.components.combobox.liste.FeesListeTableModel(serveur,currentUser);

        grp_liste_fees.setServeur(serveur);

        grp_liste_fees.setLogin(user);   

        grp_liste_fees.setModel(feesmodel);

        grp_liste_fees.init2();         

      //  grp_liste_fees.setFreeModeAllow(true);

        

        grp_LSelect_Dossier_ClientContractuel.setServeur(serveur);

        grp_LSelect_Dossier_ClientContractuel.setName("lc");

        grp_LSelect_Dossier_ClientContractuel.setLogin(currentUser);

        grp_LSelect_Dossier_ClientContractuel.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur,currentUser));   

        grp_LSelect_Dossier_ClientContractuel.init2();   

        

        

        grp_LSelect_Dossier_ClientContractuel.addActionListener(my_action);

        grp_LSelect_Dossier_ClientContractuel.addMouseListener(my_mouse);

        grp_LSelect_GrpProduits.addActionListener(my_action);

        grp_LSelect_GrpProduits.addMouseListener(my_mouse);

        grp_Formated_payement.addActionListener(my_action);

        grp_tfield_rem.addActionListener(my_action);

        grp_tfield_tcomp.addActionListener(my_action);

        grp_tfield_tdest.addActionListener(my_action);

        grp_tfield_tloc.addActionListener(my_action);

        grp_liste_fees.addActionListener(my_action);

        grp_liste_fees.addMouseListener(my_mouse);

        //grp_TField_po.addActionListener(my_action2);

        //grp_LSelect_Dossier_ClientContractuel.addActionListener(my_action2);

    }

    private void setDocument(){

        grp_Formated_payement.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_Formated_payement.getGrp_JText_encode()));

        grp_tfield_rem.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_rem.getGrp_JText_encode()));

        grp_tfield_tcomp.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tcomp.getGrp_JText_encode()));

        grp_tfield_tdest.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tdest.getGrp_JText_encode()));

        grp_tfield_tloc.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tloc.getGrp_JText_encode()));

    }

    private void refreshTicketChoice(int row,int col){

      

    }

     private void initTable( ) 

    {

            grp_Table_TicketChoice.setSelectionBackground(new java.awt.Color(221,221,255));

            model=new IntegrationModel();

            model.loadData();

            grp_Table_TicketChoice.setAutoCreateColumnsFromModel(false);

            grp_Table_TicketChoice.setModel(model);

            grp_Table_TicketChoice.getTableHeader().setReorderingAllowed(false);

            grp_Table_TicketChoice.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

            for(int k=0;k<model.m_columns.length;k++){

              JCheckBox jcheck=new JCheckBox();

            //  jcheck.addActionListener(checkboxAction);

              TableCellEditor editor=new javax.swing.DefaultCellEditor(jcheck);

              TableCellRenderer renderer2;

              renderer2=new CheckCellRenderer();

             // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;

             // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);

              DefaultTableCellRenderer renderer=new ColoredTableCellRenderer();

              renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

              TableColumn column;

             // if(k==0)

               //   column=new TableColumn(k,model.m_columns[k].c_width,renderer2,editor);

             // else                  

                  column=new TableColumn(k,model.m_columns[k].c_width,renderer,null);

                  grp_Table_TicketChoice.addColumn(column);          

            }

            JTableHeader header=grp_Table_TicketChoice.getTableHeader();

            header.setUpdateTableInRealTime(false);

            if(grp_Table_TicketChoice.getRowCount()>0){

                grp_Table_TicketChoice.changeSelection(0,0,false,false);

            }

    }

      private void initTable2( ) 

    {

            grp_Table_integrated.setSelectionBackground(new java.awt.Color(221,221,255));

            model2=new IntegratedModel();

        //    model2.loadData();

            grp_Table_integrated.setAutoCreateColumnsFromModel(false);

            grp_Table_integrated.setModel(model2);

            grp_Table_integrated.getTableHeader().setReorderingAllowed(false);

            grp_Table_integrated.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

            for(int k=0;k<model2.m_columns.length;k++){

              JCheckBox jcheck=new JCheckBox();

              TableCellEditor editor=new javax.swing.DefaultCellEditor(jcheck);

              TableCellRenderer renderer2;

              renderer2=new CheckCellRenderer();

             // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;

             // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);

              DefaultTableCellRenderer renderer=new ColoredTableCellRenderer();

              renderer.setHorizontalAlignment(model2.m_columns[k].c_alignement);

              TableColumn column;

                      

                  column=new TableColumn(k,model2.m_columns[k].c_width,renderer,null);

                  grp_Table_integrated.addColumn(column);          

            }

            JTableHeader header=grp_Table_integrated.getTableHeader();

            header.setUpdateTableInRealTime(false);

            if(grp_Table_integrated.getRowCount()>0){

                grp_Table_integrated.changeSelection(0,0,false,false);

            }

    }

    

    /** This method is called from within the constructor to

     * initialize the form.

     * WARNING: Do NOT modify this code. The content of this method is

     * always regenerated by the Form Editor.

     */

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        grp_But_sup = new javax.swing.JButton();
        grp_But_rech = new javax.swing.JButton();
        grp_But_sel = new javax.swing.JButton();
        grp_button_void = new javax.swing.JButton();
        grp_button_refund = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_Table_integrated = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_TicketChoice = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        grp_la_fac = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        grp_la_net = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        grp_la_com = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        grp_Formated_payement = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel4 = new javax.swing.JLabel();
        grp_tfield_tloc = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        grp_tfield_tdest = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel12 = new javax.swing.JPanel();
        grp_tfield_rem = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        grp_LSelect_GrpProduits = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel16 = new javax.swing.JLabel();
        grp_liste_fees = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_tfield_fees = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel15 = new javax.swing.JLabel();
        grp_tfield_tcomp = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        grp_TField_po = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel2 = new javax.swing.JLabel();
        grp_LSelect_Dossier_ClientContractuel = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        jLabel5 = new javax.swing.JLabel();
        grp_la_numdos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        grp_But_valid = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setName("integration");
        setPreferredSize(new java.awt.Dimension(730, 523));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(730, 520));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel9.setPreferredSize(new java.awt.Dimension(10, 40));
        grp_But_sup.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_But_sup.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("sup"));
        grp_But_sup.setPreferredSize(new java.awt.Dimension(110, 20));
        grp_But_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_supActionPerformed(evt);
            }
        });

        jPanel9.add(grp_But_sup);

        grp_But_rech.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_But_rech.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("rech"));
        grp_But_rech.setPreferredSize(new java.awt.Dimension(110, 20));
        grp_But_rech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_rechActionPerformed(evt);
            }
        });

        jPanel9.add(grp_But_rech);

        grp_But_sel.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_But_sel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("select"));
        grp_But_sel.setPreferredSize(new java.awt.Dimension(110, 20));
        grp_But_sel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_selActionPerformed(evt);
            }
        });

        jPanel9.add(grp_But_sel);

        grp_button_void.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_button_void.setText("Void F6");
        grp_button_void.setPreferredSize(new java.awt.Dimension(110, 20));
        grp_button_void.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_button_voidActionPerformed(evt);
            }
        });

        jPanel9.add(grp_button_void);

        grp_button_refund.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_button_refund.setText("Refund F7");
        grp_button_refund.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel9.add(grp_button_refund);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel10.setBorder(new javax.swing.border.TitledBorder(null, "Tickets \u00e0 int\u00e9grer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 170));
        grp_Table_integrated.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(grp_Table_integrated);

        jPanel10.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel11.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(10, 220));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("ticketinteg"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel6.setPreferredSize(new java.awt.Dimension(10, 60));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        grp_Table_TicketChoice.setModel(new DefaultTableModel());
        grp_Table_TicketChoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_Table_TicketChoiceMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(grp_Table_TicketChoice);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setPreferredSize(new java.awt.Dimension(10, 25));
        jPanel5.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel8.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel111.setLayout(new java.awt.GridBagLayout());

        grp_la_fac.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_la_fac.setForeground(new java.awt.Color(153, 0, 0));
        grp_la_fac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_fac.setPreferredSize(new java.awt.Dimension(70, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel111.add(grp_la_fac, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("valvent"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel111.add(jLabel7, gridBagConstraints);

        grp_la_net.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_la_net.setForeground(new java.awt.Color(153, 0, 0));
        grp_la_net.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_net.setPreferredSize(new java.awt.Dimension(70, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel111.add(grp_la_net, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("vnet"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel111.add(jLabel9, gridBagConstraints);

        grp_la_com.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_la_com.setForeground(new java.awt.Color(153, 0, 0));
        grp_la_com.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_com.setPreferredSize(new java.awt.Dimension(70, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel111.add(grp_la_com, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("com"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel111.add(jLabel11, gridBagConstraints);

        grp_Formated_payement.setGrp_Comp_nextComponent(grp_tfield_tloc);
        grp_Formated_payement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Formated_payement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel111.add(grp_Formated_payement, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("vfac"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel111.add(jLabel4, gridBagConstraints);

        grp_tfield_tloc.setGrp_Comp_nextComponent(grp_tfield_tdest);
        grp_tfield_tloc.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_tfield_tloc.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_tfield_tloc.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        jPanel111.add(grp_tfield_tloc, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("taxdes"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel111.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("tloc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel111.add(jLabel13, gridBagConstraints);

        grp_tfield_tdest.setGrp_Comp_nextComponent(grp_tfield_rem);
        grp_tfield_tdest.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_tfield_tdest.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_tfield_tdest.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        jPanel111.add(grp_tfield_tdest, gridBagConstraints);

        jPanel8.add(jPanel111);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        grp_tfield_rem.setGrp_Comp_nextComponent(grp_LSelect_GrpProduits);
        grp_tfield_rem.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_tfield_rem.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_tfield_rem.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_tfield_rem, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("rem"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel12.add(jLabel14, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("grpdec"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel12.add(jLabel3, gridBagConstraints);

        grp_LSelect_GrpProduits.setGrp_Comp_nextComponent(grp_tfield_fees);
        grp_LSelect_GrpProduits.setPreferredSize(new java.awt.Dimension(100, 18));
        grp_LSelect_GrpProduits.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_GrpProduits.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel12.add(grp_LSelect_GrpProduits, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel16.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("fees"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel12.add(jLabel16, gridBagConstraints);

        grp_liste_fees.setCanbenull(true);
        grp_liste_fees.setGrp_Comp_nextComponent(grp_tfield_fees);
        grp_liste_fees.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_liste_fees.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_liste_fees.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_liste_fees, gridBagConstraints);

        jPanel8.add(jPanel12);

        grp_tfield_fees.setGrp_Comp_nextComponent(grp_tfield_tcomp);
        grp_tfield_fees.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_tfield_fees.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel8.add(grp_tfield_fees);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("tcomp"));
        jPanel8.add(jLabel15);

        grp_tfield_tcomp.setGrp_Comp_nextComponent(grp_tfield_rem);
        grp_tfield_tcomp.setPreferredSize(new java.awt.Dimension(70, 18));
        grp_tfield_tcomp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_tfield_tcomp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_tfield_tcomp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grp_tfield_tcompKeyPressed(evt);
            }
        });

        jPanel8.add(grp_tfield_tcomp);

        jPanel5.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel11.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 30));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("pnr"));
        jPanel2.add(jLabel1);

        grp_TField_po.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientContractuel);
        grp_TField_po.setPreferredSize(new java.awt.Dimension(150, 18));
        grp_TField_po.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_po.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel2.add(grp_TField_po);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("cli"));
        jPanel2.add(jLabel2);

        grp_LSelect_Dossier_ClientContractuel.setIsLastComponent(true);
        grp_LSelect_Dossier_ClientContractuel.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_LSelect_Dossier_ClientContractuel.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel2.add(grp_LSelect_Dossier_ClientContractuel);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("nouvdos"));
        jPanel2.add(jLabel5);

        grp_la_numdos.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_la_numdos.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel2.add(grp_la_numdos);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jCheckBox2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("fact"));
        jPanel3.add(jCheckBox2);

        grp_But_valid.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_But_valid.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", parent.getCurrentUser().getLangage()).getString("valid"));
        grp_But_valid.setPreferredSize(new java.awt.Dimension(127, 20));
        grp_But_valid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_validActionPerformed(evt);
            }
        });

        jPanel3.add(grp_But_valid);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void grp_tfield_tcompKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_tfield_tcompKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            doPrevious();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_grp_tfield_tcompKeyPressed



    private void grp_button_voidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_button_voidActionPerformed

        doNext();

    }//GEN-LAST:event_grp_button_voidActionPerformed



    private void grp_But_validActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_validActionPerformed

        doPrevious();        // Add your handling code here:

    }//GEN-LAST:event_grp_But_validActionPerformed



    private void grp_But_selActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_selActionPerformed

        doAccept();        // Add your handling code here:

    }//GEN-LAST:event_grp_But_selActionPerformed



    private void grp_But_rechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_rechActionPerformed

        doF10();        // Add your handling code here:

    }//GEN-LAST:event_grp_But_rechActionPerformed





    private void grp_Table_TicketChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_Table_TicketChoiceMouseClicked

        updateField();        // Add your handling code here:

    }//GEN-LAST:event_grp_Table_TicketChoiceMouseClicked



    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

        // Add your handling code here:

    }//GEN-LAST:event_jScrollPane1MouseClicked



    private void grp_But_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_supActionPerformed

    doDelete();

    }//GEN-LAST:event_grp_But_supActionPerformed

     public void changeCursor(int changeLocation, java.awt.Cursor cursor) {

        srcastra.astra.gui.sys.utils.CursorChange.changeCursor(changeLocation, cursor, (Frame)getOwner(),null);

    }

    private void chargeTicket(ArrayList result,int conjonction,int x){

        boolean sw=false;

        String numbillet="";

       

        for(int j=x;j<result.size();j++){

        Object[] tab=null;

            do{

             tab=(Object[])result.get(j);

             if(sw==false){

                 numbillet=tab[2].toString();

                 conjonction=Integer.parseInt(tab[3].toString());

                 sw=true;

             }

             System.out.println("tickect  "+tab[2]);

             j++;

            }while(tab[2].toString().equals(numbillet) && j<result.size());

        sw=true;

        }

        

       

         /*for(int i=0;i<result.size();i++){

             Avion_ticket_T=new Avion_ticket_T

                 Object[] tab=(Object[])result.get(i);

                 String toaffiche="";

                 for(int j=0;j<tab.length;j++){

                  Avion_ticket_T ticket=new Avion_ticket_T();

                  toaffiche=toaffiche+tab[j].toString();   

                 }*/

    } 

    private void updateField(){

         if(grp_Table_TicketChoice.getRowCount()>0){

           int row=grp_Table_TicketChoice.getSelectedRow();

         ArrayList array=model.getData();

         if(row==-1)

             grp_Table_TicketChoice.changeSelection(0,0,false,false);

         Object[] tab=model.getDataTab(grp_Table_TicketChoice.getSelectedRow());

         System.out.println("tab 7"+tab[7].toString());

        /* if(Integer.parseInt(tab[7].toString())!=0){

            grp_LSelect_Dossier_ClientContractuel.setCleUnik(Integer.parseInt(tab[7].toString()));

            grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);

            grp_LSelect_Dossier_ClientContractuel.setText(tab[8].toString());

         }

         else{*/

            grp_LSelect_Dossier_ClientContractuel.setCleUnik(clientcleunik); 

            grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);

           grp_LSelect_Dossier_ClientContractuel.setText(clientName);

        // }

         grp_LSelect_Dossier_ClientContractuel.setModedisplay(false);

         if(Integer.parseInt(tab[9].toString())!=0){

            grp_LSelect_GrpProduits.setCleUnik(Integer.parseInt(tab[9].toString()));

            

         }

         else{

            grp_LSelect_GrpProduits.setCleUnik(grpProd);

     }

         grp_Formated_payement.setText(tab[10].toString());

         grp_la_com.setText(tab[16].toString());

         grp_la_fac.setText(tab[6].toString());

         grp_la_net.setText(tab[12].toString());

         grp_tfield_rem.setText(tab[17].toString());

         grp_tfield_tcomp.setText(tab[14].toString());

         grp_tfield_tloc.setText(tab[13].toString());

         grp_tfield_tdest.setText(tab[15].toString());

         srcastra.astra.gui.components.combobox.liste.ManageFreeListe.setValue(Integer.parseInt(tab[18].toString()),tab[19].toString(),grp_liste_fees);

         grp_tfield_fees.setText(tab[19].toString());
         
         //add by me
         //grp_tfield_fees.setText(tab[14].toString());
       }

        

       grp_Table_TicketChoice.requestFocus();

        

    }

    public void doPrevious() {        
        try{
        ArrayList res=serveur.renvDossierRmiObject(currentUser.getUrcleunik()).getIntegrationListe(currentUser.getUrcleunik(),grp_TField_po.getText());
   
            if((res.size()==1) && !first)
            {
                Object[] tab=(Object[])res.get(0);
                String s=(String)tab[8];
                if (!s.equals("") && !s.equals("null|null"))
                {
                
                first=true;
                
                if(grp_LSelect_Dossier_ClientContractuel.getCleUnik()==0)
                {
                
                int cle = Integer.valueOf(s.substring(0, s.lastIndexOf("|"))).intValue();
                String name = s.substring(s.lastIndexOf("|")+1,s.length());
                
                
                grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);
                grp_LSelect_Dossier_ClientContractuel.setCleUnik(cle);
                grp_LSelect_Dossier_ClientContractuel.setText(name);
                }
                
                }           
            } 
          
         }   catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

                

            }catch(java.rmi.RemoteException re){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

                } 
        
        
         if(clientcleunik==0){

             clientcleunik=grp_LSelect_Dossier_ClientContractuel.getCleUnik(); 

              if(clientcleunik==0){

                  new MessageManager().showMessageDialog(this,"inte_client","inte_client_title", currentUser);

                  return;

                

              }

         }

        this.getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));

        if(selectedPanel==PANEL1 && mode==ActionToolBar.ACT_MODIFY){ // si on est en mode recherche dans le panneaux supérieur

                                                                     //->on veut voir la liste des billets intégrables

            if(grp_LSelect_Dossier_ClientContractuel.getCleUnik()==0)

                return;

          try{

            model.setData(serveur.renvDossierRmiObject(currentUser.getUrcleunik()).getIntegrationListe(currentUser.getUrcleunik(),grp_TField_po.getText()));

            grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));

            clientcleunik=grp_LSelect_Dossier_ClientContractuel.getCleUnik();

            clientName=grp_LSelect_Dossier_ClientContractuel.getText();

            if(grp_Table_TicketChoice.getRowCount()>0){

                 grp_Table_TicketChoice.changeSelection(0,0,false,false); 

                 updateField(); 

            }

        }   catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

                

            }catch(java.rmi.RemoteException re){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

                } 

          enableFields(FIELD1,false);

          mode=ActionToolBar.ACT_READ;

          activeToolBarAction();

          

        }else if(selectedPanel==PANEL1 && mode==ActionToolBar.ACT_INSERT){

                                                                     // si on est en mode d'insertion dans le panneaux central

                                                                     //->on veut valider les paramètres du billet que l'on va intégrer

            int row=0;

              boolean selected=false;

                int i=0;

        if(grp_Table_TicketChoice.getRowCount()>0){

            if(grp_LSelect_GrpProduits.getCleUnik()==0){

                new MessageManager().showMessageDialog(this, "inte_grp", "inte_grp_title",this.currentUser);

                 this.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            return;

            }

            setValue();

            model2.addData(model.getDataTab(grp_Table_TicketChoice.getSelectedRow()));

            grp_Table_integrated.tableChanged(new TableModelEvent(model2));

            if(grp_Table_integrated.getRowCount()>0){

                grp_Table_integrated.changeSelection(grp_Table_integrated.getSelectedRow()+1,0,false,false);

                grp_TField_po.setGrp_Comp_nextComponent(null);

               }

       }

        if(grp_Table_TicketChoice.getSelectedRow()<grp_Table_TicketChoice.getRowCount()-1){

           ArrayList tmp=model.getData();

            row=grp_Table_TicketChoice.getSelectedRow()+1;

            //grp_Table_TicketChoice.changeSelection(i,0,false,false);

            updateField();        

           }

       mode=ActionToolBar.ACT_READ;

       enableFields(FIELD2,false);

       activeToolBarAction();

       System.out.println("acc");

       grp_Table_TicketChoice.changeSelection(row,0,false,false);

            

        } // on veut valider l'insertion du billet dans le système

        

        else{

                if(model2.getRowCount()>0){

       

        Hashtable value=null;

        try{

            if(model2.getRowCount()==0)

                return ;   

        for(int i=0;i<model2.getRowCount();i++){

            if(value==null)

                value=new Hashtable();

           Object[] tmp=model2.getDataTab(i);   

           Object[] valuetab=new Object[11];

           valuetab[0]=new Integer(tmp[0].toString()); //tioncleunik

           valuetab[1]=new Integer(clientcleunik); //clientcleunik

           valuetab[2]=new Integer(tmp[9].toString()); //grpdcleunik

           valuetab[3]=new Double(tmp[10].toString()); //valvente

           valuetab[4]=new Integer(26);

           valuetab[5]=new Double(tmp[13].toString());//taxloc

           valuetab[6]=new Double(tmp[14].toString());//taxcomp

           valuetab[7]=new Double(tmp[15].toString());//taxdest

           valuetab[8]=new Double(tmp[17].toString());//remise

           valuetab[9]=new Double(tmp[19].toString());//fees

           if(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier()!=null){

               mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().decrementeTicketcompteur(); 

               valuetab[10]=new Long(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getTicketcompteur());//fees

           }

           else

               valuetab[10]=new Long(0);

          // valuetab[5]=new Double(tmp[13].toString());

           value.put(new Integer(tmp[0].toString()),valuetab);

        }

     /*   Hashtable retval=serveur.renvDossierRmiObject(currentUser.getUrcleunik()). mainIntegration(currentUser.getUrcleunik(),"",value,this.currentUser,jCheckBox2.isSelected()?1:0);

        int cledossier=0;

        String numdos="";

        if(retval!=null){

            cledossier=((Integer)retval.get("id")).intValue();

            numdos=retval.get("numdos").toString();     

        }

        grp_la_numdos.setText(numdos);

        for(int i=0;i<model2.getRowCount();i++){

           Object[] tmp=model2.getDataTab(i);   

           model.getData().remove(tmp);

        }

        model2.setData(null); 

        if(grp_Table_TicketChoice.getRowCount()>0)

          grp_Table_TicketChoice.changeSelection(0,0,false,false);

        grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));

        grp_Table_integrated.tableChanged(new TableModelEvent(model2));

        grp_LSelect_Dossier_ClientContractuel.setCleUnik(0);

        grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);

        grp_LSelect_Dossier_ClientContractuel.setText("");

        grp_LSelect_Dossier_ClientContractuel.setModedisplay(false);

        grp_TField_po.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientContractuel);*/

       

       /* if(result!=null){

            for(int i=0;i<result.size();i++){

                 Object[] tab=(Object[])result.get(i);

                 if(tab!=null){

                 String toaffiche="";

                 for(int j=0;j<tab.length;j++){

                    if(tab[j]!=null)

                    toaffiche=toaffiche+"  "+tab[j].toString();   

                 }

                 System.out.println("ligne n° : "+i+" : "+toaffiche);        

                 }

            }

        }

            chargeTicket(result,1,0);*/

        // changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));

         int cledossier=0;

         String numdos="";

      

         if(mainframe!=null){

        // this.setVisible(false);

        if(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier()==null || !mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().isVisible()){ //soit dans un nouveau dossier

                 Hashtable retval=serveur.renvDossierRmiObject(currentUser.getUrcleunik()). mainIntegration(currentUser.getUrcleunik(),"",value,this.currentUser,jCheckBox2.isSelected()?1:0);

                 if(retval!=null){

                    cledossier=((Integer)retval.get("id")).intValue();

                    numdos=retval.get("numdos").toString();     

                    }

                 mainframe.getGrp_mcp_mainpan().displayDossier(mainframe.getGrp_MenuItem_Dossier());

                 mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getGrp_Pan_DossierIndex().openDossier(cledossier,true,1);



             /*   if(retval!=null){

                    cledossier=((Integer)retval.get("id")).intValue();

                    numdos=retval.get("numdos").toString();     

                }

                grp_la_numdos.setText(numdos);

                for(int i=0;i<model2.getRowCount();i++){

                   Object[] tmp=model2.getDataTab(i);   

                   model.getData().remove(tmp);

                }

                model2.setData(null);

                if(grp_Table_TicketChoice.getRowCount()>0)

                  grp_Table_TicketChoice.changeSelection(0,0,false,false);

                grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));

                grp_Table_integrated.tableChanged(new TableModelEvent(model2));

                grp_LSelect_Dossier_ClientContractuel.setCleUnik(0);

                grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);

                grp_LSelect_Dossier_ClientContractuel.setText("");

                grp_LSelect_Dossier_ClientContractuel.setModedisplay(false);

                grp_TField_po.setGrp_Comp_nextComponent(grp_LSelect_Dossier_ClientContractuel);*/

                  

         }

         else if(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getSelectedPanel()==srcastra.astra.gui.modules.dossier.DossierModules.INDEX) {//soit dans un nouveau dossier

               Hashtable retval=serveur.renvDossierRmiObject(currentUser.getUrcleunik()). mainIntegration(currentUser.getUrcleunik(),"",value,this.currentUser,jCheckBox2.isSelected()?1:0);

                 if(retval!=null){

                    cledossier=((Integer)retval.get("id")).intValue();

                    numdos=retval.get("numdos").toString();     

                    }

              // mainframe.getGrp_mcp_mainpan().displayDossier(mainframe.getGrp_MenuItem_Dossier());

               mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getGrp_Pan_DossierIndex().openDossier(cledossier,true,0);
               
         }     

         else{ 
                   
           //soit dans un dossier existant

              Dossier_T dossier=serveur.renvDossierRmiObject(currentUser.getUrcleunik()).getProductToIntegrate(currentUser.getUrcleunik(),"",value,this.currentUser,jCheckBox2.isSelected()?1:0);

              String message=java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("inte_dos1");

              message=message+" "+mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().getDrnumdos()+" !";

              message=message+java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("inte_dos2");

              int rep=new JOptionPane().showConfirmDialog(this,message,java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("inte_dos_title"),JOptionPane.YES_NO_OPTION);

              if(rep==0){

                  if(dossier!=null){

                     Hashtable avionticket=(Hashtable)dossier.getAvionTicket();

                     for(Enumeration enu=avionticket.keys();enu.hasMoreElements();){

                        Avion_ticket_T avion=(Avion_ticket_T)avionticket.get(enu.nextElement());

                        String billet=avion.getAt_num_billet();

                        if(testBillet(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().getAvionTicket(),billet)){

                            new JOptionPane().showMessageDialog(this,"The ticket "+billet+" is already in file !");

                            this.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                            return ;

                            

                        }                            

                        mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().addTicket(avion);

                        mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().calculTotalProduit(); 

                        srcastra.astra.gui.modules.dossier.utils.FillDossierInfo.validateProductAvion(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier(),(Avion_ticket_T)avion);

                     }

                     ArrayList passager=(ArrayList)dossier.getPassager();

                     for(int i=0;i<passager.size();i++){

                         mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().addPassager((Passager_T)passager.get(i));                         

                     }

                        TreeMap payement=(TreeMap)dossier.getPayement();

                        if(payement!=null){

                        Set set=payement.keySet();

                        Iterator ite=set.iterator();

                        while(ite.hasNext()){

                           Payement_T payem=(Payement_T)payement.get((Long)ite.next());

                           mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().addPayement2(payem);                       

                        }

                        }

                     

                  }

                  mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().setComptaModify(true);

                  mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getDossier().setModifreccord(true);

                  mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().refreshTable();

                 // mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().updateDossier();

                  System.out.println("ok");

              }

              else {

                    this.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                return ;   

              }

         }

         }

        }

        catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

                

        }catch(java.rmi.RemoteException re){

                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        }

                mode=ActionToolBar.ACT_READ;

                activeToolBarAction();

                doClose();

        }

         this.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

          

      

    }

    private boolean testBillet(Hashtable array,String billet){

      boolean retval=false;

        if(array!=null){            

        for(Enumeration enu=array.keys();enu.hasMoreElements();){

            Avion_ticket_T avion=(Avion_ticket_T)array.get(enu.nextElement());

            if(avion.getAt_num_billet().equals(billet))

                retval=true;

        }

      }

      return retval;

    }

    public void doPrint() {

    }

    

    public void doSwitch() {

        if(selectedPanel==PANEL1){

            selectPanel(PANEL2);

            adaptBackgroundColorTicketChoice(false);

            adaptBackgroundColorTicketIntegrated(true);

        }

        else{

            selectPanel(PANEL1);

            adaptBackgroundColorTicketChoice(true);

            adaptBackgroundColorTicketIntegrated(false);

        }

        activeToolBarAction();

    }

    

    public void doUp() {

    }

    

    public int[] getDefaultActionToolBarMask() {

        return null;

    }

    

    public void registerKeyboardAction(ActionListener action, javax.swing.KeyStroke keystroke, int focusType) {

        this.jPanel1.registerKeyboardAction(action,keystroke,focusType);

    }

    

    public void resetKeyboardActions() {

    }

    

    public void setThisAsToolBarComponent() {

    }

    

    public void keyPressed(java.awt.event.KeyEvent keyEvent) {

    }

    

    public void keyReleased(java.awt.event.KeyEvent keyEvent) {

    }

    

    public void keyTyped(java.awt.event.KeyEvent keyEvent) {

    }

    

    /** Fermeture de l'écran courrant et passage

     *

     *

     * à l'écran d'index du module

     *

     *

     */

    public void cancelModule() {

    }

    

    /** Permet de charger le panel des statuts  */

    public void chargeStatusPanel(String[] statuts) {

    }

    

    /** Ferme le module

     *

     *

     * (dans Astra -> JInternalFrame)

     *

     *

     */

    public void closeModule() {

    }

    

    /** Lance la séquence de création

     *

     *

     */

    public void displayCreateSequence() {

    }

    

    /** Lance la séquence de suppression

     *

     *

     */

    public void displayDeleteSequence() {

    }

    

    /** Lance la séquence de lecture

     *

     *

     */

    public void displayReadSequence(int cleUnik) {

    }

    

    /** Permet de changer l'état de la TabbedPane dans le pricipal  */

    public void enabledTabbedPane(boolean enabled) {

    }

    

    public Loginusers_T getCurrentUser() {

        return currentUser;

    }

    

    public boolean getNestedSignaletique() {

        return false;

    }

    

    public java.awt.Frame getOwner() {

        return mainframe;

    }

    

    public astrainterface getServeur() {

        return serveur;

    }

    

    public DossierRmiInterface getServeurDossier() {

        return null;

    }

    

    public java.awt.Frame getSuperOwner() {

        return mainframe;

    }

    

    /** Passage à l'écran suivant

     *

     *

     * @param currentScreen numéro de l'écran courrant

     *

     *

     */

    public void nextScreen(int currentScreen) {

    }

    

    /** Passage à l'écran suivant

     *

     *

     * @param currentScreen numéro de l'écran courrant

     *

     *

     */

    public void nextScreen(int currentScreen, boolean affich) {

    }

    

    public void nextScreen(int currentScreen, int insert) {

    }

    

    public void registerTable(javax.swing.JTable generique_table) {

    }

    

    public void reloadToolBarInfo() {

          this.actionToolBar.setActionEnabled(actiontab);

         actionToolBar.setTbComposer(this);

    }

     public int[] activeToolBarAction(){

        if(selectedPanel==PANEL1){

            switch(mode){

                case ActionToolBar.ACT_INSERT:{

                         grp_But_rech.setEnabled(false);

                         grp_But_sel.setEnabled(false);

                         grp_But_valid.setEnabled(true);

                         grp_But_sup.setEnabled(false);

                         grp_button_void.setEnabled(false);

                         actiontab= new int[]{ActionToolBar.DO_PREVIOUS,ActionToolBar.DO_CANCEL}; 

                         adaptBackgroundColorTicketChoice(false);

                         adaptBackgroundColorTicketIntegrated(false);

                        break;

                }

                 case ActionToolBar.ACT_READ:{

                        if(grp_Table_TicketChoice.getRowCount()>0 && grp_Table_integrated.getRowCount()==0){

                            grp_But_rech.setEnabled(true);

                            grp_But_sel.setEnabled(true);

                            grp_But_valid.setEnabled(false);

                            grp_But_sup.setEnabled(false);

                            grp_button_void.setEnabled(true);

                            actiontab= new int[]{ActionToolBar.DO_SWITCH,ActionToolBar.DO_ACCEPT,ActionToolBar.DO_F10,ActionToolBar.DO_CLOSE,ActionToolBar.DO_NEXT};                         

                        }

                        else if(grp_Table_TicketChoice.getRowCount()>0 && grp_Table_integrated.getRowCount()>0){

                            

                                grp_But_rech.setEnabled(true);

                                grp_But_sel.setEnabled(true);

                                grp_But_valid.setEnabled(true);

                                grp_But_sup.setEnabled(false);

                                grp_button_void.setEnabled(false);

                                

                                 actiontab=new int[]{ActionToolBar.DO_SWITCH,ActionToolBar.DO_ACCEPT,ActionToolBar.DO_F10,ActionToolBar.DO_PREVIOUS,ActionToolBar.DO_CLOSE};   

                        }

                        else if(grp_Table_TicketChoice.getRowCount()==0 && grp_Table_integrated.getRowCount()>0){

                                grp_But_rech.setEnabled(true);

                                grp_But_sel.setEnabled(false);

                                grp_But_valid.setEnabled(true);

                                grp_But_sup.setEnabled(false);

                                grp_button_void.setEnabled(false);

                            

                             actiontab= new int[]{ActionToolBar.DO_SWITCH,ActionToolBar.DO_F10,ActionToolBar.DO_PREVIOUS,ActionToolBar.DO_CLOSE};   

                            

                        }

                        else if(grp_Table_TicketChoice.getRowCount()==0 && grp_Table_integrated.getRowCount()==0){

                                grp_But_rech.setEnabled(true);

                                grp_But_sel.setEnabled(false);

                                grp_But_valid.setEnabled(false);

                                grp_But_sup.setEnabled(false);

                                grp_button_void.setEnabled(false);

                                

                             actiontab= new int[]{ActionToolBar.DO_F10,ActionToolBar.DO_SWITCH};                            

                        }

                        adaptBackgroundColorTicketChoice(true);

                        grp_Table_TicketChoice.requestFocus();

                        

                        break;

                }

                 case ActionToolBar.ACT_MODIFY:{

                        grp_But_rech.setEnabled(false);

                        grp_But_sel.setEnabled(false);

                        grp_But_valid.setEnabled(true);

                        grp_But_sup.setEnabled(false);

                        grp_button_void.setEnabled(false);

                        actiontab= new int[]{ActionToolBar.DO_PREVIOUS,ActionToolBar.DO_CANCEL,ActionToolBar.DO_CLOSE}; 

                        adaptBackgroundColorTicketChoice(false);

                        adaptBackgroundColorTicketIntegrated(false);

                        break;

                }             

            }

            

        }

        else if(selectedPanel==PANEL2){

            switch(mode){

               

                 case ActionToolBar.ACT_READ:{

                     if(grp_Table_integrated.getRowCount()>0){

                        grp_But_rech.setEnabled(false);

                        grp_But_sel.setEnabled(false);

                        grp_But_valid.setEnabled(true);

                        grp_But_sup.setEnabled(true);

                        grp_button_void.setEnabled(false);

                        actiontab= new int[]{ActionToolBar.DO_PREVIOUS,ActionToolBar.DO_DELETE,ActionToolBar.DO_SWITCH};      

                     }

                     else{

                        grp_But_rech.setEnabled(false);

                        grp_But_sel.setEnabled(false);

                        grp_But_valid.setEnabled(false);

                        grp_But_sup.setEnabled(false);

                        grp_button_void.setEnabled(false);

                        actiontab= new int[]{ActionToolBar.DO_SWITCH};                          

                     }

                       grp_Table_integrated.requestFocus();

                        break;

                }

                     

            }

            

        }

        actionToolBar.setActionEnabled(actiontab);

        return actiontab;

    }

    /** Fixe la clé unique dans le module parent

     *

     *

     * @param ContextCleUnik clé unique

     *

     *

     */

    public void setContextCleUnik(int ContextCleUnik) {

    }

    

    /** permet d'établir une liste d'action choisie comme étant les actions de la ToobBar

     *

     *

     *  + permet à la classe principale du modules de pouvoir sauvegarder le tableau des actions  */

    public void setCurrentActionEnabled(int[] actionEnabled) {

    }

    

    /** permet d'établir un panneau comme panneau gestionnaire de la toolbar (voir tbComposer) +

     *

     *

     *  permet à la classe principale du modules de pouvoir sauvegarder l'objet TbComposer

     *

     *

     */

    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {

    }

    

    public void setNestedSignaletique(boolean netstedSignletique) {

    }

    

    public void saveToolBarInfo() {

    }

    

     private  java.awt.event.MouseAdapter my_mouse=new java.awt.event.MouseAdapter(){

        public void mouseClicked(java.awt.event.MouseEvent evt){

          if(grp_Table_TicketChoice.getRowCount()>0){

          if(evt.getSource()==grp_LSelect_GrpProduits){

             model.setValueAt(new Integer(grp_LSelect_GrpProduits.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),9);

             model.setValueAt(grp_LSelect_GrpProduits.getText(),grp_Table_TicketChoice.getSelectedRow(),11);

             grpProd=grp_LSelect_GrpProduits.getCleUnik();

          }

          else if(evt.getSource()==grp_LSelect_Dossier_ClientContractuel){

               model.setValueAt(new Integer(grp_LSelect_Dossier_ClientContractuel.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),7);

               model.setValueAt(new Integer(grp_LSelect_Dossier_ClientContractuel.getText()),grp_Table_TicketChoice.getSelectedRow(),8);

               clientcleunik=grp_LSelect_Dossier_ClientContractuel.getCleUnik();

               clientName=grp_LSelect_Dossier_ClientContractuel.getText();

               feesmodel.setM_frcleunik(grp_LSelect_Dossier_ClientContractuel.getCleUnik());

               feesmodel.loadata();            

          }

           else if(evt.getSource()==grp_liste_fees){

              Double value=null;

              try{

                  value=new Double(grp_liste_fees.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              grp_tfield_fees.setText(""+value.doubleValue());

            //  model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),19);

              model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);

              

          }

        }

        }

    };

      private ActionListener my_action2 = new ActionListener() {

           public void actionPerformed(ActionEvent evt) {      

               if(evt.getSource()==grp_TField_po){

                   if(grp_LSelect_Dossier_ClientContractuel.getCleUnik()!=0)

                    doPrevious();                 

           }

           else

               if(grp_LSelect_Dossier_ClientContractuel.getCleUnik()==0)

                    doPrevious();

           }

      };

    private void  setValue(){

       //  model.setValueAt2(clientName,grp_Table_TicketChoice.getSelectedRow(), 4);

       //  model.setValueAt2(grp_LSelect_GrpProduits.getText(),grp_Table_TicketChoice.getSelectedRow(), 5);

        

         model.setValueAt2(new Integer(grp_LSelect_GrpProduits.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),9);

         model.setValueAt2(grp_LSelect_GrpProduits.getText(),grp_Table_TicketChoice.getSelectedRow(),11);

         grpProd=grp_LSelect_GrpProduits.getCleUnik();

         model.setValueAt2(new Integer(grp_LSelect_Dossier_ClientContractuel.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),7);

         model.setValueAt2(grp_LSelect_Dossier_ClientContractuel.getText(),grp_Table_TicketChoice.getSelectedRow(),8);       

         clientcleunik=grp_LSelect_Dossier_ClientContractuel.getCleUnik();

         clientName=grp_LSelect_Dossier_ClientContractuel.getText();

          Double value=null;

              try{

                  value=new Double(grp_Formated_payement.getText());            

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

               model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),10);

               val_net=new Double(grp_Formated_payement.getText()).doubleValue();

      

              try{

                  value=new Double(grp_tfield_rem.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

               model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),17);

         

              try{

                  value=new Double(grp_tfield_tcomp.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),14);

         

              try{

                  value=new Double(grp_tfield_tdest.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),15);

     

              try{

                  value=new Double(grp_tfield_tloc.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),13);   

      

              try{

                  value=new Double(grp_liste_fees.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);

        

              try{

                  value=new Double(grp_tfield_fees.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),19);

        

    }

    private ActionListener my_action = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

           if(grp_Table_TicketChoice.getRowCount()>0){

          if(evt.getSource()==grp_LSelect_GrpProduits){

             model.setValueAt2(new Integer(grp_LSelect_GrpProduits.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),9);

             model.setValueAt(grp_LSelect_GrpProduits.getText(),grp_Table_TicketChoice.getSelectedRow(),11);

             grpProd=grp_LSelect_GrpProduits.getCleUnik();

          }

          else if(evt.getSource()==grp_LSelect_Dossier_ClientContractuel){

               model.setValueAt2(new Integer(grp_LSelect_Dossier_ClientContractuel.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),7);

               model.setValueAt2(grp_LSelect_Dossier_ClientContractuel.getText(),grp_Table_TicketChoice.getSelectedRow(),8);       

               clientcleunik=grp_LSelect_Dossier_ClientContractuel.getCleUnik();

               clientName=grp_LSelect_Dossier_ClientContractuel.getText();

               feesmodel.setM_frcleunik(grp_LSelect_Dossier_ClientContractuel.getCleUnik());

               feesmodel.loadata();            

          }

           else if(evt.getSource()==grp_Formated_payement){

              Double value=null;

              try{

                  value=new Double(grp_Formated_payement.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

               model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),10);

               val_net=new Double(grp_Formated_payement.getText()).doubleValue();

               Object[] tab=model.getDataTab(grp_Table_TicketChoice.getSelectedRow());

          }

          else if(evt.getSource()==grp_tfield_rem){

              Double value=null;

              try{

                  value=new Double(grp_tfield_rem.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

               model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),17);

              

           }

          else if(evt.getSource()==grp_tfield_tcomp){

              Double value=null;

              try{

                  value=new Double(grp_tfield_tcomp.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),14);

              

          }

          else if(evt.getSource()==grp_tfield_tdest){

               Double value=null;

              try{

                  value=new Double(grp_tfield_tdest.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),15);

              

          }

          else if(evt.getSource()==grp_tfield_tloc){

               Double value=null;

              try{

                  value=new Double(grp_tfield_tloc.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),13);

              

          }

          else if(evt.getSource()==grp_liste_fees){

               Double value=null;

              try{

                  value=new Double(grp_liste_fees.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              grp_tfield_fees.setText(""+value.doubleValue());

              model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);

              

          }

           else if(evt.getSource()==grp_tfield_fees){

               Double value=null;

              try{

                  value=new Double(grp_tfield_fees.getText());

               

              }catch(NumberFormatException nn){

                value=new Double(0); 

              }

              

              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),19);

             // model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);

              

          }

        }

        }

    };

     public void doDefaultCloseAction(){



        try{

            serveur.remoterollback(getCurrentUser().getUrcleunik());

        }catch(java.rmi.RemoteException re){

           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);  

        }

       InternalFrameEvent closeWindow=new InternalFrameEvent(this,InternalFrameEvent.INTERNAL_FRAME_CLOSING);

       this.iFrameListener.internalFrameClosing(closeWindow);

       super.doDefaultCloseAction();

   }

     

     public void doAccept() {

         mode=ActionToolBar.ACT_INSERT;

         feesmodel.setM_frcleunik(grp_LSelect_Dossier_ClientContractuel.getCleUnik());

         feesmodel.loadata();            

         activeToolBarAction();

         enableFields(FIELD2,true);

     }

     

     public void doCancel() {

         if(mode==ActionToolBar.ACT_MODIFY){

            enableFields(FIELD1,false);

            mode=ActionToolBar.ACT_READ;

            activeToolBarAction();

         }

         else if(mode==ActionToolBar.ACT_INSERT){

            enableFields(FIELD2,false);

            mode=ActionToolBar.ACT_READ;

            activeToolBarAction();

         }

     }

     

     public void doClose() {

         doDefaultCloseAction();

     }

     

     public void doCreate() {

     }

     

     public void doDelete() {

         

          if(model2.getRowCount()>0){

          model2.getArray().remove(grp_Table_integrated.getSelectedRow()); 

          if(model2.getRowCount()==0){

            grp_LSelect_Dossier_ClientContractuel.setEnabled(true);

            swclient=false;    

       }

       }

        grp_Table_integrated.tableChanged(new TableModelEvent(model2));

        if(grp_Table_integrated.getRowCount()>0)

        grp_Table_integrated.changeSelection(0,0,false,false);

        activeToolBarAction();

     }

     

     public void doF10() {

         mode=ActionToolBar.ACT_MODIFY;

         activeToolBarAction();

         enableFields(FIELD1,true);

     }

     

     public void doF7() {

     }

     

     public void doHelp() {

     }

     

     public void doModify() {

     }

     

     public void doNext() {

        int rep=new JOptionPane().showConfirmDialog(this,java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("voidtick"),java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage", parent.getCurrentUser().getLangage()).getString("voidticktitle"),JOptionPane.YES_NO_OPTION);

        if(rep==0){    

            voidTicket();

        }

     }     

     public java.awt.Component m_getGeneriqueTable() {

         return null;

     }

     public void valueChanged(javax.swing.event.ListSelectionEvent listSelectionEvent) {

        updateField();

     }

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton grp_But_rech;
    private javax.swing.JButton grp_But_sel;
    private javax.swing.JButton grp_But_sup;
    private javax.swing.JButton grp_But_valid;
    private srcastra.astra.gui.components.textFields.ATextField grp_Formated_payement;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_LSelect_Dossier_ClientContractuel;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_GrpProduits;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_po;
    private javax.swing.JTable grp_Table_TicketChoice;
    private javax.swing.JTable grp_Table_integrated;
    private javax.swing.JButton grp_button_refund;
    private javax.swing.JButton grp_button_void;
    private javax.swing.JLabel grp_la_com;
    private javax.swing.JLabel grp_la_fac;
    private javax.swing.JLabel grp_la_net;
    private javax.swing.JLabel grp_la_numdos;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_fees;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_fees;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_rem;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tcomp;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tdest;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tloc;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    

}

