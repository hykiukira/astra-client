/*
 * TicketIntegration.java
 *
 * Created on 24 septembre 2003, 14:41
 */

package srcastra.astra.gui.modules.dossier.productSpecification.integration;
import srcastra.astra.sys.classetransfert.*;
import javax.swing.*;
import javax.swing.table.*;
import srcastra.astra.gui.modules.dossier.utils.*;
import srcastra.astra.gui.test.*;
import java.util.*;
import javax.swing.event.*;
import srcastra.test.*;
import java.awt.event.*;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.utils.*;
import srcastra.astra.sys.rmi.*;
import java.awt.*;
import srcastra.astra.gui.*;
import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.KeyStrokeManager;
import srcastra.astra.gui.components.actions.actionToolBar.*;

/**
 *
 * @author  Thomas
 */
public class TicketIntegration extends javax.swing.JDialog implements srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.InterfaceMainPanel{
    
    /** Creates new form TicketIntegration */
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
   // KeyStrokeManager keystroke;
    public TicketIntegration(java.awt.Frame parent, boolean modal,astrainterface serveur,Loginusers_T user) {
        super(parent, modal);
       
        mainframe=(MainFrame)parent;
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
        currentUser=this.user;
        System.out.println(currentUser.getName());
        initComponents();
     //  test();
        initTable();
        initTable2();
        initListe();
        setDocument();
        model.setData(data);
        grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));  
       // jButton1.setMnemonic(KeyEvent.VK_ENTER);
       // jButton3.setMnemonic(KeyEvent.VK_F5);
       
       
        jButton1.setEnabled(false);
        jCheckBox1.addActionListener(checkboxAction);
      //   keystroke=new KeyStrokeManager(true);
       //  keystroke.registerComponent(this);
        //  keystroke.setAction(new int[]{ActionToolBar.DO_PREVIOUS});
    }
    java.awt.event.ActionListener checkboxAction=new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  int row=grp_Table_TicketChoice.getSelectedRow();
                 if(((JCheckBox)evt.getSource()).isSelected()){
                   if(evt.getSource()==jCheckBox1)
                        model.setSelected(true);
                    jButton1.setEnabled(true);
            }
             else{
                if(evt.getSource()==jCheckBox1)
                    model.setSelected(false);          
                jButton1.setEnabled(false);
            }
            grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));    
            grp_Table_TicketChoice.changeSelection(row,row,false,false);
            }
        };
    private void test(){
      Object[] tab=new Object[]{new Integer(1),new Boolean(false),"po56","ticket78","passager tamere","10-10-2003","100","0","","0","0",""};
      data.add(tab);
      tab=new Object[]{new Integer(1),new Boolean(false),"po56","ticket78","passager tamere","10-10-2003","100","0","","0","0",""};
      data.add(tab);     
    }
    private void initListe(){
        grp_LSelect_GrpProduits.setServeur(serveur);
        grp_LSelect_GrpProduits.setLogin(user);
        tbMod_grpDeProd=new srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel(serveur,user);
        grp_LSelect_GrpProduits.setModel(this.tbMod_grpDeProd);
        grp_LSelect_GrpProduits.init2();    
        tbMod_grpDeProd.setM_frcleunik(26);
        tbMod_grpDeProd.loadata();
        
        feesmodel=new srcastra.astra.gui.components.combobox.liste.FeesListeTableModel(serveur,user);
        grp_liste_fees.setServeur(serveur);
        grp_liste_fees.setLogin(user);   
        grp_liste_fees.setModel(feesmodel);
        grp_liste_fees.init2();         
        grp_liste_fees.setFreeModeAllow(true);
        
        grp_LSelect_Dossier_ClientContractuel.setServeur(serveur);
        grp_LSelect_Dossier_ClientContractuel.setName("lc");
        grp_LSelect_Dossier_ClientContractuel.setLogin(user);
        grp_LSelect_Dossier_ClientContractuel.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur,user));   
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
    }
    private void setDocument(){
        grp_Formated_payement.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_Formated_payement.getGrp_JText_encode()));
        grp_tfield_rem.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_rem.getGrp_JText_encode()));
        grp_tfield_tcomp.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tcomp.getGrp_JText_encode()));
        grp_tfield_tdest.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tdest.getGrp_JText_encode()));
        grp_tfield_tloc.getGrp_JText_encode().setDocument(new srcastra.astra.gui.components.textFields.mask.Decimal(grp_tfield_tloc.getGrp_JText_encode()));
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
              jcheck.addActionListener(checkboxAction);
              TableCellEditor editor=new javax.swing.DefaultCellEditor(jcheck);
              TableCellRenderer renderer2;
              renderer2=new CheckCellRenderer();
             // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;
             // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);
              DefaultTableCellRenderer renderer=new ColoredTableCellRenderer();
              renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);
              TableColumn column;
              if(k==0)
                  column=new TableColumn(k,model.m_columns[k].c_width,renderer2,editor);
              else                  
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
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_Table_integrated = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_TicketChoice = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        grp_LSelect_Dossier_ClientContractuel = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        jLabel3 = new javax.swing.JLabel();
        grp_LSelect_GrpProduits = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_la_fac = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        grp_la_net = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        grp_la_com = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        grp_liste_fees = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        grp_tfield_rem = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel13 = new javax.swing.JLabel();
        grp_tfield_tcomp = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel15 = new javax.swing.JLabel();
        grp_tfield_tdest = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel14 = new javax.swing.JLabel();
        grp_tfield_tloc = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel4 = new javax.swing.JLabel();
        grp_Formated_payement = new srcastra.astra.gui.components.textFields.ATextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        grp_TField_po = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        grp_la_numdos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel9.setPreferredSize(new java.awt.Dimension(10, 40));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("sup"));
        jButton2.setPreferredSize(new java.awt.Dimension(94, 20));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel9.add(jButton2);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel10.setBorder(new javax.swing.border.TitledBorder(null, "Tickets \u00e0 int\u00e9grer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 115));
        grp_Table_integrated.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(grp_Table_integrated);

        jPanel10.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(new javax.swing.border.EtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(10, 200));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("ticketinteg"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel6.setPreferredSize(new java.awt.Dimension(10, 30));
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

        jPanel7.setPreferredSize(new java.awt.Dimension(10, 30));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jCheckBox1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("selection"));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jPanel7.add(jCheckBox1);

        jPanel5.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel8.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("cli"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel2, gridBagConstraints);

        grp_LSelect_Dossier_ClientContractuel.setGrp_Comp_nextComponent(grp_LSelect_GrpProduits);
        grp_LSelect_Dossier_ClientContractuel.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_LSelect_Dossier_ClientContractuel, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("grpdec"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel3, gridBagConstraints);

        grp_LSelect_GrpProduits.setGrp_Comp_nextComponent(grp_liste_fees);
        grp_LSelect_GrpProduits.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_LSelect_GrpProduits, gridBagConstraints);

        grp_la_fac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_fac.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_la_fac, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("vfac"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel7, gridBagConstraints);

        grp_la_net.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_net.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_la_net, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("vnet"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel9, gridBagConstraints);

        grp_la_com.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_la_com.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_la_com, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("com"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jLabel11, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel16.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("fees"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel11.add(jLabel16, gridBagConstraints);

        grp_liste_fees.setCanbenull(true);
        grp_liste_fees.setGrp_Comp_nextComponent(grp_tfield_tloc);
        grp_liste_fees.setPreferredSize(new java.awt.Dimension(70, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        jPanel11.add(grp_liste_fees, gridBagConstraints);

        jPanel8.add(jPanel11);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("taxdes"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel12.add(jLabel12, gridBagConstraints);

        grp_tfield_rem.setGrp_Comp_nextComponent(grp_Formated_payement);
        grp_tfield_rem.setPreferredSize(new java.awt.Dimension(70, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_tfield_rem, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("tloc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel12.add(jLabel13, gridBagConstraints);

        grp_tfield_tcomp.setGrp_Comp_nextComponent(grp_tfield_rem);
        grp_tfield_tcomp.setPreferredSize(new java.awt.Dimension(70, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_tfield_tcomp, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("tcomp"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel12.add(jLabel15, gridBagConstraints);

        grp_tfield_tdest.setGrp_Comp_nextComponent(grp_tfield_tcomp);
        grp_tfield_tdest.setPreferredSize(new java.awt.Dimension(70, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_tfield_tdest, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("rem"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel12.add(jLabel14, gridBagConstraints);

        grp_tfield_tloc.setGrp_Comp_nextComponent(grp_tfield_tdest);
        grp_tfield_tloc.setPreferredSize(new java.awt.Dimension(70, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel12.add(grp_tfield_tloc, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("valvent"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel12.add(jLabel4, gridBagConstraints);

        grp_Formated_payement.setGrp_Comp_nextComponent(jButton1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel12.add(grp_Formated_payement, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("ajouter"));
        jButton1.setPreferredSize(new java.awt.Dimension(70, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        jPanel12.add(jButton1, gridBagConstraints);

        jPanel8.add(jPanel12);

        jPanel5.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel5, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 30));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("pnr"));
        jPanel2.add(jLabel1);

        grp_TField_po.setPreferredSize(new java.awt.Dimension(120, 20));
        grp_TField_po.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_TField_poActionPerformed(evt);
            }
        });

        jPanel2.add(grp_TField_po);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("numdos"));
        jPanel2.add(jLabel5);

        grp_la_numdos.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_la_numdos.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel2.add(grp_la_numdos);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jCheckBox2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("fact"));
        jPanel3.add(jCheckBox2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("nouvdos"));
        jButton3.setPreferredSize(new java.awt.Dimension(127, 20));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton3);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/integration", currentUser.getLangage()).getString("dosex"));
        jButton4.setPreferredSize(new java.awt.Dimension(127, 20));
        jButton4.setEnabled(false);
        jPanel3.add(jButton4);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(718, 432));
        setLocation((screenSize.width-718)/2,(screenSize.height-432)/2);
    }//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            doPrevious();       //changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.WAIT_CURSOR));

    }//GEN-LAST:event_jButton3ActionPerformed
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
    private void grp_TField_poActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_TField_poActionPerformed
        try{
            model.setData(serveur.renvDossierRmiObject(user.getUrcleunik()).getIntegrationListe(user.getUrcleunik(),grp_TField_po.getText()));
            grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));
            if(grp_Table_TicketChoice.getRowCount()>0){
                 grp_Table_TicketChoice.changeSelection(0,0,false,false); 
                 updateField();
            }
            
            grp_LSelect_Dossier_ClientContractuel.requestFocus();
        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){
                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
                
                }catch(java.rmi.RemoteException re){
                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
                }
    }//GEN-LAST:event_grp_TField_poActionPerformed
    boolean swclient=false;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       boolean selected=false;
       int i=0;
        if(grp_Table_TicketChoice.getRowCount()>0){
            model2.addData(model.getDataTab(grp_Table_TicketChoice.getSelectedRow()));
              grp_Table_integrated.tableChanged(new TableModelEvent(model2));
               if(grp_Table_integrated.getRowCount()>0)
                grp_Table_integrated.changeSelection(grp_Table_integrated.getSelectedRow()+1,0,false,false);
       }
        if(grp_Table_TicketChoice.getSelectedRow()<grp_Table_TicketChoice.getRowCount()-1){
           ArrayList tmp=model.getData();
           int row=grp_Table_TicketChoice.getSelectedRow()+1;
           for(i=row;i<tmp.size();i++){
               Object[] tab=(Object[])tmp.get(i);
               if(((Boolean)tab[1]).booleanValue()==true){
                   selected=true;
                   break;
               }
           }
           if(selected==true){
            grp_Table_TicketChoice.changeSelection(i,0,false,false);
            updateField();
            swclient=true;
           if(swclient==false){
            grp_LSelect_Dossier_ClientContractuel.setEnabled(true);
            grp_LSelect_Dossier_ClientContractuel.requestFocus();
           }
           else{
            grp_LSelect_Dossier_ClientContractuel.setEnabled(false);
            grp_LSelect_GrpProduits.requestFocus();       
           }
           }
       }
       System.out.println("acc");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void grp_Table_TicketChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_Table_TicketChoiceMouseClicked
      updateField();
    }//GEN-LAST:event_grp_Table_TicketChoiceMouseClicked
    private void updateField(){
         if(grp_Table_TicketChoice.getRowCount()>0){
           int row=grp_Table_TicketChoice.getSelectedRow();
         ArrayList array=model.getData();
         Object[] tab=model.getDataTab(row);
         if(((Boolean)tab[1]).booleanValue()==true){
                   jButton1.setEnabled(true);
               }
         else{
             jButton1.setEnabled(false);
         }
         System.out.println("tab 7"+tab[7].toString());
         if(Integer.parseInt(tab[7].toString())!=0){
            grp_LSelect_Dossier_ClientContractuel.setCleUnik(Integer.parseInt(tab[7].toString()));
            grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);
            grp_LSelect_Dossier_ClientContractuel.setText(tab[8].toString());
         }
         else{
            grp_LSelect_Dossier_ClientContractuel.setCleUnik(clientcleunik); 
            grp_LSelect_Dossier_ClientContractuel.setModedisplay(true);
            grp_LSelect_Dossier_ClientContractuel.setText(clientName);
         }
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
         
       }
       grp_LSelect_Dossier_ClientContractuel.requestFocus();
        
    }
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new TicketIntegration(new javax.swing.JFrame(), true,null,null).show();
    }
    
    public void doAccept() {
    }
    
    public void doCancel() {
    }
    
    public void doClose() {
    }
    
    public void doCreate() {
    }
    
    public void doDelete() {
    }
    
    public void doDown() {
    }
    
    public void doHelp() {
    }
    
    public void doModify() {
    }
    
    public void doNext() {
    }
    
    public void doPrevious() {
                if(model2.getRowCount()>0){
        this.getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Hashtable value=null;
        try{
            if(model2.getRowCount()==0)
                return ;   
        for(int i=0;i<model2.getRowCount();i++){
            if(value==null)
                value=new Hashtable();
           Object[] tmp=model2.getDataTab(i);   
           Object[] valuetab=new Object[10];
           valuetab[0]=new Integer(tmp[0].toString()); //tioncleunik
           valuetab[1]=new Integer(tmp[7].toString()); //clientcleunik
           valuetab[2]=new Integer(tmp[9].toString()); //grpdcleunik
           valuetab[3]=new Double(tmp[10].toString()); //valvente
           valuetab[4]=new Integer(26);
           valuetab[5]=new Double(tmp[13].toString());//taxloc
           valuetab[6]=new Double(tmp[14].toString());//taxcomp
           valuetab[7]=new Double(tmp[15].toString());//taxdest
           valuetab[8]=new Double(tmp[17].toString());//remise
           valuetab[9]=new Double(tmp[19].toString());//fees
          // valuetab[5]=new Double(tmp[13].toString());
           value.put(new Integer(tmp[0].toString()),valuetab);
        }
        Hashtable retval=serveur.renvDossierRmiObject(user.getUrcleunik()). mainIntegration(user.getUrcleunik(),"",value,this.user,jCheckBox2.isSelected()?1:0);
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
        grp_Table_TicketChoice.tableChanged(new TableModelEvent(model));
        grp_Table_integrated.tableChanged(new TableModelEvent(model2));
        if(grp_Table_TicketChoice.getRowCount()>0)
            grp_Table_TicketChoice.changeSelection(0,0,false,false);
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
         this.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         this.setVisible(false);
         if(mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier()==null || !mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().isVisible()){
             mainframe.getGrp_mcp_mainpan().displayDossier(mainframe.getGrp_MenuItem_Dossier());
          //   mainframe.getGrp_mcp_mainpan().getGrp_Mod_dossier().getGrp_Pan_DossierIndex().openDossier(cledossier);
            
         }
         this.dispose();
        }
        catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){
                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
                
        }catch(java.rmi.RemoteException re){
                     ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        }
    }
    
    public void doPrint() {
    }
    
    public void doSwitch() {
    }
    
    public void doUp() {
    }
    
    public int[] getDefaultActionToolBarMask() {
        return null;
    }
    
    public void registerKeyboardAction(ActionListener action, KeyStroke keystroke, int focusType) {
        this.jPanel1.registerKeyboardAction(action,keystroke,focusType);
    }
    
    public void resetKeyboardActions() {
    }
    
    public void setThisAsToolBarComponent() {
    }
    
    public void doF10() {
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
              
          }
           else if(evt.getSource()==grp_liste_fees){
              Double value=null;
              try{
                  value=new Double(grp_liste_fees.getText());
               
              }catch(NumberFormatException nn){
                value=new Double(0); 
              }
              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),19);
              model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);
              
          }
        }
        }
    };
    
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
                if(((Boolean)tab[1]).booleanValue()==true){
                    jButton1.setEnabled(true);
                }
                else{
                    jButton1.setEnabled(false);
                }         
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
              model.setValueAt2(value,grp_Table_TicketChoice.getSelectedRow(),19);
              model.setValueAt2(new Integer(grp_liste_fees.getCleUnik()),grp_Table_TicketChoice.getSelectedRow(),18);
              
          }
        }
        }
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JTable grp_Table_integrated;
    private srcastra.astra.gui.components.textFields.ATextField grp_Formated_payement;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JTable grp_Table_TicketChoice;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_liste_fees;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel grp_la_com;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JCheckBox jCheckBox2;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tcomp;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_rem;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_LSelect_Dossier_ClientContractuel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel grp_la_fac;
    private javax.swing.JButton jButton1;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tloc;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JTextField grp_TField_po;
    private srcastra.astra.gui.components.textFields.ATextField grp_tfield_tdest;
    private javax.swing.JLabel jLabel7;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_GrpProduits;
    private javax.swing.JLabel grp_la_net;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel grp_la_numdos;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jLabel15;
    // End of variables declaration//GEN-END:variables
   
}
