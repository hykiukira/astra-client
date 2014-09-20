/*



 * Facturation.java



 *



 * Created on 5 aout 2003, 10:40



 */


package srcastra.astra.gui.modules.compta.achat;


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


import srcastra.astra.gui.sys.*;


import java.awt.event.*;


import javax.swing.event.*;


import srcastra.astra.sys.classetransfert.*;


import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;


/**
 * @author Driart
 */


public class Facturation extends javax.swing.JDialog implements srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.InterfaceMainPanel {


    MainScreenModule parent;


    FacturationModel model;


    DossierRmiInterface dossierrmi;


    ArrayList arraymodel;


    Grpdecision_T grpprod;


    /**
     * Creates new form Facturation
     */


    ActionListener myaction;


    EditTable editable;


    ActionToolBar actionToolBar;


    KeyStrokeManager keystroke;


    int[] action;


    boolean isFromIntegration;


    private void action(java.awt.event.ActionEvent evt) {


    }


    private void refreshTable() {


        model.setData(arraymodel);


        grp_Table_prod.tableChanged(new TableModelEvent(model));


        if (grp_Table_prod.getRowCount() != 0)


            grp_Table_prod.changeSelection(0, 0, false, false);


        actionToolBar.setActionEnabled(getDefaultActionToolBarMask());


        keystroke.setAction(getDefaultActionToolBarMask());


    }


    public Facturation(java.awt.Frame parent2, boolean modal, MainScreenModule parent, EditTable editable, ActionToolBar actionToolBar) {


        super(parent2, modal);


        this.parent = parent;


        initComponents();


        initListe();


        initTable();


        try {


            dossierrmi = parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik());


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }


        myaction = new ActionListener() {


            public void actionPerformed(java.awt.event.ActionEvent evt) {


                action(evt);


            }


        };


        this.actionToolBar = actionToolBar;


        actionToolBar.setActionEnabled(getDefaultActionToolBarMask());


        keystroke = new KeyStrokeManager(true);


        keystroke.registerComponent(this);


        keystroke.setAction(getDefaultActionToolBarMask());


        grp_Liste_numdos.addActionListener(myaction);


        grp_Liste_po.addActionListener(myaction);

        // grp_Liste_passager.addActionListener(myaction);


        grp_Date_datedep.addActionListener(myaction);


    }


    private void initTable() {


        this.model = new FacturationModel(parent, parent.getCurrentUser());


        grp_Table_prod.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


        grp_Table_prod.setAutoCreateColumnsFromModel(false);


        grp_Table_prod.getTableHeader().setReorderingAllowed(false);


        grp_Table_prod.setSelectionBackground(new java.awt.Color(204, 204, 255));


        grp_Table_prod.setModel(model);


        grp_Table_prod.setFocusable(false);


        for (int k = 0; k < model.m_columns.length; k++) {


            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();


            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);


            TableColumn column = null;


            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);


            column.setHeaderValue(model.m_columns[k].c_title);


            grp_Table_prod.addColumn(column);


        }


        JTableHeader header = grp_Table_prod.getTableHeader();


        header.setUpdateTableInRealTime(false);


        header.setResizingAllowed(false);


    }


    private void initListe() {

        /*   grp_Liste_numdos.setServeur(parent.getServeur());



        grp_Liste_numdos.setName("lc");



        grp_Liste_numdos.setLogin(parent.getCurrentUser());



        grp_Liste_numdos.setModel(new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_numdos.init2();



      //  grp_Liste_numdos.setColToSearch(2);



      //  grp_Liste_numdos.setCol(1);



      /*  grp_Liste_po.setServeur(parent.getServeur());



        grp_Liste_po.setName("lc");



        grp_Liste_po.setLogin(parent.getCurrentUser());



        grp_Liste_po.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.PoTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_po.init2();



        grp_Liste_po.setCol(1);



        grp_Liste_po.setColToSearch(2);*/

        /* grp_Liste_passager.setServeur(parent.getServeur());



        grp_Liste_passager.setName("lc");



        grp_Liste_passager.setLogin(parent.getCurrentUser());



        grp_Liste_passager.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.PassagerTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_passager.init2();



        grp_Liste_passager.setCol(1);SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND f.frcleunik = ? AND h.hetypeligne = 'A'  ";



        grp_Liste_passager.setColToSearch(2);*/


    }


    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */


    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_prod = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_Date_datedep = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_Liste_po = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Liste_numdos = new srcastra.astra.gui.components.combobox.liste.Liste();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        grp_Table_prod.setModel(new DefaultTableModel());
        jScrollPane1.setViewportView(grp_Table_prod);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(null);

        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("facturation_param")));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 110));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("rech_fourn"));
        jLabel1.setToolTipText("Num\u00e9ro de fournisseur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("rech_piece"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("rech_facture_date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel4, gridBagConstraints);

        grp_Date_datedep.setGrp_Comp_nextComponent(grp_Date_datedep);
        grp_Date_datedep.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Date_datedep.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_Date_datedep, gridBagConstraints);

        grp_Liste_po.setGrp_Comp_nextComponent(grp_Date_datedep);
        grp_Liste_po.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Liste_po.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(grp_Liste_po, gridBagConstraints);

        grp_Liste_numdos.setCanbenull(true);
        grp_Liste_numdos.setGrp_Comp_nextComponent(grp_Liste_po);
        grp_Liste_numdos.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Liste_numdos.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel4.add(grp_Liste_numdos, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel4);
        jPanel4.setBounds(11, 26, 264, 54);

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("achnm"));
        jButton1.setBorder(new javax.swing.border.MatteBorder(new java.awt.Insets(1, 1, 1, 1), new java.awt.Color(255, 51, 51)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1);
        jButton1.setBounds(300, 40, 250, 17);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel2.getAccessibleContext().setAccessibleName("Param\u00e8tres de recherche pour la facturation");

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 600) / 2, (screenSize.height - 477) / 2, 600, 477);
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            arraymodel = dossierrmi.getFactureBidon(parent.getCurrentUser().getUrcleunik());

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }

        isFromIntegration = true;

        refreshTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    /**
     * Closes the dialog
     */


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog


        setVisible(false);


        dispose();


    }//GEN-LAST:event_closeDialog


    public int[] getDefaultActionToolBarMask() {


        if (grp_Table_prod.getRowCount() == 0) {


            action = new int[]{ActionToolBar.DO_PREVIOUS};


        } else {


            action = new int[]{ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_ACCEPT, ActionToolBar.DO_DOWN, ActionToolBar.DO_UP};


        }


        return action;


    }


    /**
     * @param args the command line arguments
     */


    public static void main(String args[]) {

        //   new AchatParam(new javax.swing.JFrame(), true).show();


    }


    public java.awt.Component m_getGeneriqueTable() {


        return null;


    }


    public void doAccept() {


        AchatCp tmp = (AchatCp) model.getObjectValue(grp_Table_prod.getSelectedRow());


        long hecleunik = tmp.getClefacture();

        long cleunikFact = -1;


        Achat_T facture;

        //  Object cle=this.model.getValueAt2(grp_Table_prod.getSelectedRow(),0);


        try {


            facture = dossierrmi.getFactureInfoFromHistorique(parent.getCurrentUser().getUrcleunik(), hecleunik);


            ((srcastra.astra.gui.modules.compta.achat.Achat) parent).setAchatc(facture);
            ((srcastra.astra.gui.modules.compta.achat.Achat) parent).isFromIntegration = this.isFromIntegration;

            // facture.get

            ((srcastra.astra.gui.modules.compta.achat.Achat) parent).cleunikfact = tmp.getCleunikFact();


            this.setVisible(false);


            this.dispose();


            System.out.println("Drinnnnnnnnnnnnnnnnnnnnnnnnniiiiiiiiiiiiiiiii");

            /*    this.grpprod = dossierrmi.getFactureInfoFromHistorique(parent.getCurrentUser().getUrcleunik(), hecleunik);



                editable.getModel().addData(new Object[]{null,null,new Double(0),"D",null,tmp[3],null,new Integer(grpprod.getGncompteachat()),new Integer(grpprod.getGncodetvaachat()),null,null,new Long(cleunik),cledossier,new Integer(typeprod),fournisseur});



                editable.getTable().tableChanged(new TableModelEvent(editable.getTable().getModel()));



                editable.getTable().changeSelection(editable.getTable().getRowCount()-1,0,false,false);



                editable.interTableListe(editable.getTable().getRowCount()-1,0,new Integer(grpprod.getGncompteachat()).intValue(),true);



                editable.getTable().changeSelection(editable.getTable().getRowCount()-1,0,false,false);



                srcastra.astra.gui.components.celleditor.CellEditorInterface celledit=(srcastra.astra.gui.components.celleditor.CellEditorInterface)   editable.getTable().getCellEditor(editable.getTable().getRowCount()-1,0);//getColumnModel().getColumn(col).getCellEditor();



                editable.updateListe(celledit);



                editable.getTable().changeSelection(editable.getTable().getRowCount()-1,0,false,false);



                editable.interTableListe(editable.getTable().getRowCount()-1,1,new Integer(grpprod.getGncodetvaachat()).intValue(),true);



                editable.getTable().changeSelection(editable.getTable().getRowCount()-1,0,false,false);



                this.setVisible(false);



                this.dispose();







            //  editable.interTableListe(grp_Table_prod.getRowCount()-1,1,new Integer(grpprod.getGncodetvaachat()).intValue(),true);











            */


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }


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


        if (grp_Table_prod.getSelectedRow() != grp_Table_prod.getRowCount() - 1) {


            grp_Table_prod.changeSelection(grp_Table_prod.getSelectedRow() + 1, 0, false, false);


        }


    }


    public void doHelp() {


    }


    public void doModify() {


    }


    public void doNext() {


    }


    public void doPrevious() {
        try {


            String date;


            if (grp_Date_datedep.getDate().isOpen() || grp_Date_datedep.getDate().isUnknow())


                date = "";


            else


                date = grp_Date_datedep.getDate().toString();


            String numf = grp_Liste_numdos.getText();


            String piece = grp_Liste_po.getText();


            System.out.println("Date " + date + "  numf " + numf + "  piece " + piece);


            boolean sw = false;


            arraymodel = dossierrmi.getHistoriqueInfoSelonToutes(parent.getCurrentUser().getUrcleunik(), numf, piece, date);


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }


        this.isFromIntegration = false;

        refreshTable();


    }


    public void doPrint() {


    }


    public void doSwitch() {


    }


    public void doUp() {


        if (grp_Table_prod.getSelectedRow() != 0) {


            grp_Table_prod.changeSelection(grp_Table_prod.getSelectedRow() - 1, 0, false, false);


        }


    }


    public void registerKeyboardAction(ActionListener action, KeyStroke keystroke, int focusType) {


        this.jPanel1.registerKeyboardAction(action, keystroke, focusType);


    }


    public void resetKeyboardActions() {


    }


    public void setThisAsToolBarComponent() {


    }


    public void doF10() {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.date.thedate.ADate grp_Date_datedep;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_numdos;
    private srcastra.astra.gui.components.textFields.ATextField grp_Liste_po;
    private javax.swing.JTable grp_Table_prod;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}



