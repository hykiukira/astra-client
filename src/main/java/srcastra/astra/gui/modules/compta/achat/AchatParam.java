/*



 * AchatParam.java



 *



 * Created on 23 juillet 2003, 10:40



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
 * @author thomas
 */


public class AchatParam extends javax.swing.JDialog implements ToolBarComposer, srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.InterfaceMainPanel {


    MainScreenModule parent;


    AchatParamTableModel model;


    DossierRmiInterface dossierrmi;


    ArrayList arraymodel;


    Grpdecision_T grpprod;

    boolean modification;


    /**
     * Creates new form AchatParam
     */


    ActionListener myaction;


    EditTable editable;


    ActionToolBar actionToolBar;


    KeyStrokeManager keystroke;

    int m_categorieJournal;


    int[] action;


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


    public AchatParam(java.awt.Frame parent2, boolean modal, MainScreenModule parent, EditTable editable, ActionToolBar actionToolBar, int categorieJournal, boolean mod) {


        super(parent2, modal);


        this.modification = mod;

        m_categorieJournal = categorieJournal;

        this.parent = parent;


        this.editable = editable;


        initComponents();


        this.setSize(650, 400);


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


    }


    private void initTable() {


        this.model = new AchatParamTableModel(parent, parent.getCurrentUser());


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

        /*    grp_Liste_numdos.setServeur(parent.getServeur());



        grp_Liste_numdos.setName("lc");



        grp_Liste_numdos.setLogin(parent.getCurrentUser());



        grp_Liste_numdos.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.NumDosTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_numdos.init2();



        grp_Liste_numdos.setColToSearch(2);



        grp_Liste_numdos.setCol(1);



        grp_Liste_po.setServeur(parent.getServeur());



        grp_Liste_po.setName("lc");



        grp_Liste_po.setLogin(parent.getCurrentUser());



        grp_Liste_po.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.PoTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_po.init2();



        grp_Liste_po.setCol(1);



        grp_Liste_po.setColToSearch(2);



        grp_Liste_passager.setServeur(parent.getServeur());



        grp_Liste_passager.setName("lc");



        grp_Liste_passager.setLogin(parent.getCurrentUser());



        grp_Liste_passager.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.PassagerTableModel(parent.getServeur(),parent.getCurrentUser()));



        grp_Liste_passager.init2();



        grp_Liste_passager.setCol(1);



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
        jLabel5 = new javax.swing.JLabel();
        grp_Date_datedep = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_Tfiel_numdos = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Tfiel_po = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Tfiel_passager = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 450));
        grp_Table_prod.setModel(new DefaultTableModel());
        jScrollPane1.setViewportView(grp_Table_prod);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("achat_param")));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 110));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("Num_dos"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("po_pnr"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("date_dep"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("pass"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 11);
        jPanel4.add(jLabel5, gridBagConstraints);

        grp_Date_datedep.setGrp_Comp_nextComponent(grp_Tfiel_passager);
        grp_Date_datedep.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_Date_datedep, gridBagConstraints);

        grp_Tfiel_numdos.setGrp_Comp_nextComponent(grp_Tfiel_po);
        grp_Tfiel_numdos.setPreferredSize(new java.awt.Dimension(150, 18));
        jPanel4.add(grp_Tfiel_numdos, new java.awt.GridBagConstraints());

        grp_Tfiel_po.setGrp_Comp_nextComponent(grp_Date_datedep);
        grp_Tfiel_po.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel4.add(grp_Tfiel_po, gridBagConstraints);

        grp_Tfiel_passager.setPreferredSize(new java.awt.Dimension(150, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel4.add(grp_Tfiel_passager, gridBagConstraints);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jButton1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ach_rech"));
        jButton1.setPreferredSize(new java.awt.Dimension(130, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10));
        jButton2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("ach_point"));
        jButton2.setPreferredSize(new java.awt.Dimension(130, 20));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 608) / 2, (screenSize.height - 508) / 2, 608, 508);
    }//GEN-END:initComponents


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


        doAccept(); // Add your handling code here:


    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        doPrevious();        // Add your handling code here:


    }//GEN-LAST:event_jButton1ActionPerformed


    /**
     * Closes the dialog
     */


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog


        setVisible(false);


        dispose();


    }//GEN-LAST:event_closeDialog


    /**
     * @param args the command line arguments
     */


    public static void main(String args[]) {

        //   new AchatParam(new javax.swing.JFrame(), true).show();


    }


    public void doAccept() {

        if (modification) {
            Object[] tmp = (Object[]) model.getObjectValue(grp_Table_prod.getSelectedRow());
            // editable[editable.getTable().getSelectedRow()]
            System.out.println(editable.getTable().getValueAt(editable.getTable().getSelectedRow(), 6));

            //(Achat_T)Achat.getAchat().
            //Achat.getAchat().getAchat().setCledossier((Long)tmp[10]);
            //Achat.getAchat().getAchat().set(editable.getTable().getSelectedRow(),achatcp);

            AchatCp ach = new AchatCp();

            ArrayList achc = Achat.getAchat().getAchat();

            //editable.getTable().getSelected


            System.out.println("row:" + editable.getTable2().getSelectedRow());

            ach = (AchatCp) achc.get(editable.getTable().getSelectedRow());


            ach.setCledossier(Long.valueOf(tmp[10].toString()).longValue());
            ach.setNumdos(tmp[0].toString());

            Achat.getAchat().setDrcleunik(Integer.valueOf(tmp[10].toString()).intValue());


            Achat.getAchat().getAchat().set(editable.getTable().getSelectedRow(), ach);

            editable.getTable().setValueAt(tmp[0], editable.getTable().getSelectedRow(), 6);

            //editable.getTable().

            //editable.resume();

            //editable.getTable().setValueAt(tmp[0],editable.getTable().getSelectedRow(),1);

            System.out.println(editable.getTable().getValueAt(editable.getTable().getSelectedRow(), 6));


            this.setVisible(false);
            this.dispose();
        } else {

            Object[] tmp = (Object[]) model.getObjectValue(grp_Table_prod.getSelectedRow());
            int frgtcleunik = ((Integer) tmp[6]).intValue();
            long cleunik = ((Long) tmp[2]).longValue();
            int typeprod = ((Long) tmp[11]).intValue();
            String dossier = tmp[0].toString();
            Long cledossier = (Long) tmp[10];
            Integer fournisseur = (Integer) tmp[4];
            try {

                //Achat_T ach=new Achat_T();


                this.grpprod = dossierrmi.getGroupDec(cleunik, typeprod, frgtcleunik, parent.getCurrentUser().getUrcleunik());

                String dc = "D";

                if (m_categorieJournal == ParamComptable.JOURNAL_ACHAT)
                    dc = "D";
                else if (m_categorieJournal == ParamComptable.JOURNAL_NCACHAT)
                    dc = "C";
                AchatCp achatcp = new AchatCp();

                achatcp.setDc(dc);
                achatcp.setCe_cleunik(grpprod.getGncompteachat());
                achatcp.getTva1().setTva_id(grpprod.getGncodetvaachat());
                achatcp.setCleprod(cleunik);
                achatcp.setCledossier(cledossier.longValue());
                achatcp.setFrcleunik(Achat.getAchat().getFrcleunik());
                achatcp.setNumdos(dossier);
                achatcp.setPoPnr(tmp[3].toString());
                achatcp.setCatProd(typeprod);
                //cledossier,new Integer(typeprod),fournisseur,dossier};
                // Object[] newtab=new Object[]{null,null,new Double(0),dc,null,tmp[3],null,new Integer(grpprod.getGncompteachat()),new Integer(grpprod.getGncodetvaachat()),null,null,new Long(cleunik),cledossier,new Integer(typeprod),fournisseur,dossier};

                if (editable.getTable().getRowCount() > 0) {

                    if (editable.getModel().getValueAt2(editable.getTable().getSelectedRow(), 0) == null || editable.getModel().getValueAt2(editable.getTable().getSelectedRow(), 1) == null) {
                        Achat.getAchat().getAchat().set(editable.getTable().getSelectedRow(), achatcp);
                        //  editable.getModel().getData().set(editable.getTable().getSelectedRow(),newtab);


                    } else {
                        Achat.getAchat().getAchat().add(achatcp);
                    }

                    //         editable.getModel().addData(newtab);


                } else {

                    Achat.getAchat().getAchat().add(achatcp);

                    //   editable.getModel().addData(newtab);


                }
                editable.getTable().tableChanged(new TableModelEvent(editable.getTable().getModel()));
                editable.getTable().changeSelection(editable.getTable().getRowCount() - 1, 0, false, false);
                editable.interTableListe(editable.getTable().getRowCount() - 1, 0, new Integer(grpprod.getGncompteachat()).intValue(), true);
                editable.getTable().changeSelection(editable.getTable().getRowCount() - 1, 0, false, false);
                srcastra.astra.gui.components.celleditor.CellEditorInterface celledit = (srcastra.astra.gui.components.celleditor.CellEditorInterface) editable.getTable().getCellEditor(editable.getTable().getRowCount() - 1, 0);//getColumnModel().getColumn(col).getCellEditor();
                editable.updateListe(celledit);
                editable.getTable().changeSelection(editable.getTable().getRowCount() - 1, 0, false, false);
                editable.interTableListe(editable.getTable().getRowCount() - 1, 1, new Integer(grpprod.getGncodetvaachat()).intValue(), true);
                editable.getTable().changeSelection(editable.getTable().getRowCount() - 1, 0, false, false);
                // ArrayList resum=dossierrmi.getHistoriqueSelonProduit(parent.getCurrentUser().getUrcleunik(),cleunik);
                // Achat.getAchat().setResum(resum);
                VenteRentabilite resum = dossierrmi.getPreviousFacture(cledossier.longValue(), parent.getCurrentUser().getUrcleunik(), parent.getCurrentUser().getUrlmcleunik(), achatcp.getCleprod(), achatcp.getCatProd(), false);
                // Achat.getAchat().setResume(resum);
                achatcp.setVenteRent(resum);
                editable.resume();
                //ArrayList resumvente=dossierrmi.getHistoriqueVenteSelonProduit(parent.getCurrentUser().getUrcleunik(),cleunik,cledossier.longValue(),typeprod);
                // ((srcastra.astra.gui.modules.compta.achat.Achat)parent).setRentabiliteValue(resum,resumvente,""+cleunik+typeprod);
                this.setVisible(false);
                this.dispose();
            } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


            }


            catch (java.rmi.RemoteException re) {


                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


            }


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


        try {


            String date;


            if (grp_Date_datedep.getDate().isOpen() || grp_Date_datedep.getDate().isUnknow())


                date = "";


            else


                date = grp_Date_datedep.getDate().toString();


            String numdos = grp_Tfiel_numdos.getText();


            String po = grp_Tfiel_po.getText();


            String passager = grp_Tfiel_passager.getText();


            System.out.println("Date " + date + "  numdos " + numdos + "  po " + po + "  passager " + passager);


            boolean sw = false;


            arraymodel = dossierrmi.getProduitsInfoSelonToutes(parent.getCurrentUser().getUrcleunik(), date, numdos, po, passager);

            for (int i = 0; i < arraymodel.size(); i++) {
                Object[] tmp = (Object[]) arraymodel.get(i);

                if (tmp[0].toString().length() > 9) {

                    if (tmp[0].toString().charAt(8) == 'G' && tmp[0].toString().charAt(9) != '0') {
                        arraymodel.remove(i);
                        i--;
                    }
                }

            }


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


        }


        catch (java.rmi.RemoteException re) {


            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);


        }


        refreshTable();


    }


    public void doPrint() {


    }


    public void doSwitch() {


    }


    public int[] getDefaultActionToolBarMask() {


        if (grp_Table_prod.getRowCount() == 0) {


            action = new int[]{ActionToolBar.DO_PREVIOUS};


        } else {


            action = new int[]{ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_ACCEPT, ActionToolBar.DO_DOWN, ActionToolBar.DO_UP};


        }


        return action;


    }


    public java.awt.Component m_getGeneriqueTable() {


        return null;


    }


    public void setThisAsToolBarComponent() {


    }


    public void registerKeyboardAction(ActionListener action, KeyStroke keystroke, int focusType) {


        this.jPanel1.registerKeyboardAction(action, keystroke, focusType);


    }


    public void resetKeyboardActions() {


    }


    public void doDown() {


        if (grp_Table_prod.getSelectedRow() != grp_Table_prod.getRowCount() - 1) {


            grp_Table_prod.changeSelection(grp_Table_prod.getSelectedRow() + 1, 0, false, false);


        }


    }


    public void doUp() {


        if (grp_Table_prod.getSelectedRow() != 0) {


            grp_Table_prod.changeSelection(grp_Table_prod.getSelectedRow() - 1, 0, false, false);


        }


    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.date.thedate.ADate grp_Date_datedep;
    private javax.swing.JTable grp_Table_prod;
    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_numdos;
    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_passager;
    private srcastra.astra.gui.components.textFields.ATextField grp_Tfiel_po;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}



