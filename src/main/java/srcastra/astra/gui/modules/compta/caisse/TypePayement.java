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

/**
 * @author Thomas
 */

public class TypePayement extends javax.swing.JPanel implements ToolBarComposer, GlobalInterface, srcastra.astra.gui.components.AIframe {

    MainScreenModule parent;

    /**
     * Creates new form UserCOnfig
     */

    srcastra.astra.sys.classetransfert.configuration.TypePayement pay;

    ActionToolBar toolbar;

    int mode;

    TypePaiementModel model;

    srcastra.astra.sys.classetransfert.configuration.User user = new srcastra.astra.sys.classetransfert.configuration.User();

    AstraComponent[] astrac;

    Loginusers_T currentUser;

    ManageFields manfields;

    GlobalRmiInterface rmiInteface;

    boolean okPass;

    int[] action;

    public TypePayement(MainScreenModule parent, ActionToolBar toolbar, ArrayList data) {

        this.currentUser = parent.getCurrentUser();

        this.toolbar = toolbar;

        this.parent = parent;

        try {

            this.rmiInteface = parent.getServeur().renvTypePayementRmiObject(currentUser.getUrcleunik());


        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        initComponents();

        //pass=new Pwd(parent.getSuperOwner(),true,true,this.user,this,this.currentUser);

        initListe();

        setDocumentMask();

        astrac = new AstraComponent[]{grp_Tfield_Intitule1, grp_Tfield_Intitule2, grp_Liste_compte, grp_Liste_cate};

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

        model = new TypePaiementModel(parent, parent.getCurrentUser());

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

        manfields = new ManageFields(astrac, (javax.swing.JInternalFrame) parent, grp_Table_typepaiement, toolbar, this, check, currentUser);

        manfields.init();

        pay = new srcastra.astra.sys.classetransfert.configuration.TypePayement();

    }

    public void activeToolBarAction(int type) {


        if (type == ActionToolBar.ACT_READ) {

            if (grp_Table_typepaiement.getRowCount() == 0)

                action = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_CREATE};

            else

                action = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_CREATE, ActionToolBar.DO_MODIFY};

        } else if (type == ActionToolBar.ACT_INSERT) {

            action = new int[]{ActionToolBar.DO_CREATE, ActionToolBar.DO_CANCEL, ActionToolBar.DO_PREVIOUS};

        }
        ;

        toolbar.setActionEnabled(action);


    }

    private void setDocumentMask() {

        grp_Tfield_Intitule1.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

        grp_Tfield_Intitule2.setDocument(new DefaultMask(1, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));

    }

    private void initTable() {

        grp_Table_typepaiement.setFocusable(false);

        grp_Table_typepaiement.setSelectionBackground(new java.awt.Color(204, 204, 255));

        grp_Table_typepaiement.setAutoCreateColumnsFromModel(false);

        grp_Table_typepaiement.getTableHeader().setReorderingAllowed(false);

        grp_Table_typepaiement.setModel(this.model);

        grp_Table_typepaiement.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        for (int k = 0; k < model.m_columns.length; k++) {

            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();

            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);

            TableColumn column = null;

            column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);

            column.setHeaderValue(model.m_columns[k].c_title);

            grp_Table_typepaiement.addColumn(column);

        }

        JTableHeader header = grp_Table_typepaiement.getTableHeader();

        header.setUpdateTableInRealTime(false);

        header.setResizingAllowed(false);

        if (grp_Table_typepaiement.getRowCount() != 0) ;

        grp_Table_typepaiement.changeSelection(0, 0, false, false);

    }

    private void initListe() {

        grp_Liste_cate.setServeur(parent.getServeur());

        grp_Liste_cate.setLogin(parent.getCurrentUser());

        grp_Liste_cate.setModel(new srcastra.astra.gui.components.combobox.liste.PaymentCategorieTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_cate.init2();

        //   grp_Liste_langue.setFocusable3(false);

        grp_Liste_compte.setServeur(parent.getServeur());

        grp_Liste_compte.setLogin(parent.getCurrentUser());

        grp_Liste_compte.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(parent.getServeur(), parent.getCurrentUser()));

        grp_Liste_compte.init2();

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

        grp_Tfield_Intitule1 = new srcastra.astra.gui.components.textFields.ATextField();

        jLabel2 = new javax.swing.JLabel();

        grp_Liste_compte = new srcastra.astra.gui.components.combobox.liste.Liste();

        jPanel6 = new javax.swing.JPanel();

        jPanel7 = new javax.swing.JPanel();

        jLabel9 = new javax.swing.JLabel();

        jLabel14 = new javax.swing.JLabel();

        grp_Tfield_Intitule2 = new srcastra.astra.gui.components.textFields.ATextField();

        grp_Liste_cate = new srcastra.astra.gui.components.combobox.liste.Liste();

        jPanel2 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        grp_Table_typepaiement = new javax.swing.JTable();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(""));

        add(jPanel5, java.awt.BorderLayout.SOUTH);


        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));


        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai1"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel1, gridBagConstraints);


        grp_Tfield_Intitule1.setEnabled(false);

        grp_Tfield_Intitule1.setGrp_Comp_nextComponent(grp_Tfield_Intitule2);

        grp_Tfield_Intitule1.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfield_Intitule1.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel4.add(grp_Tfield_Intitule1, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("col_compte"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel4.add(jLabel2, gridBagConstraints);


        grp_Liste_compte.setEnabled(false);

        grp_Liste_compte.setGrp_Comp_nextComponent(grp_Liste_cate);

        grp_Liste_compte.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_compte.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        jPanel4.add(grp_Liste_compte, gridBagConstraints);


        jPanel3.add(jPanel4);


        jPanel1.add(jPanel3);


        jPanel7.setLayout(new java.awt.GridBagLayout());


        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typpai2"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel9, gridBagConstraints);


        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("typaycat"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);

        jPanel7.add(jLabel14, gridBagConstraints);


        grp_Tfield_Intitule2.setEnabled(false);

        grp_Tfield_Intitule2.setGrp_Comp_nextComponent(grp_Liste_compte);

        grp_Tfield_Intitule2.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Tfield_Intitule2.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_Tfield_Intitule2, gridBagConstraints);


        grp_Liste_cate.setEnabled(false);

        grp_Liste_cate.setIsLastComponent(true);

        grp_Liste_cate.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

        grp_Liste_cate.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel7.add(grp_Liste_cate, gridBagConstraints);


        jPanel6.add(jPanel7);


        jPanel1.add(jPanel6);


        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.BorderLayout());


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 200));

        grp_Table_typepaiement.setModel(new javax.swing.table.DefaultTableModel());

        jScrollPane1.setViewportView(grp_Table_typepaiement);


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

        pay = (srcastra.astra.sys.classetransfert.configuration.TypePayement) model.getData().get(grp_Table_typepaiement.getSelectedRow());

        pay.setOld_ce_cleunik(pay.getCe_cleunik());

        setFields();

        manfields.beforeCreate(true);

    }

    public void doNext() {

    }

    public void askForpassword(boolean modif) {

        //  new Pwd(parent.getSuperOwner(),true,modif,this.user,this,this.currentUser);

    }

    public void updateFields() {

        pay.setCe_cleunik(grp_Liste_compte.getCleUnik());

        pay.setTynt_categorie(grp_Liste_cate.getCleUnik());

        pay.setTynt_libelle1(grp_Tfield_Intitule1.getText());

        pay.setTynt_libelle2(grp_Tfield_Intitule2.getText());

        /*  this.user.setUrnom(grp_TField_nom.getText());

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

        this.user.setUrdroit(grp_Liste_droit.getCleUnik());*/

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

        if (manfields.mode == ActionToolBar.ACT_READ) {

            if (grp_Table_typepaiement.getRowCount() != 0) {

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

        grp_Liste_cate.setCleUnik(pay.getTynt_categorie());

        grp_Liste_compte.setCleUnik(pay.getCe_cleunik());

        grp_Tfield_Intitule1.setText(pay.getTynt_libelle1());

        grp_Tfield_Intitule2.setText(pay.getTynt_libelle2());

        /*  this.user=model.getObject(grp_JTable_user.getSelectedRow());

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

        grp_Liste_droit.setCleUnik(this.user.getUrdroit());*/

    }


    public void setThisAsToolBarComponent() {

    }


    public Object getObject() {

        return pay;

    }


    public GlobalRmiInterface getRmiInteface() {

        return rmiInteface;

    }


    public javax.swing.JTable getTable() {

        return grp_Table_typepaiement;

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

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfield_Intitule1;

    private javax.swing.JTable grp_Table_typepaiement;

    private javax.swing.JScrollPane jScrollPane1;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_cate;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JPanel jPanel2;

    private srcastra.astra.gui.components.textFields.ATextField grp_Tfield_Intitule2;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JLabel jLabel9;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_compte;

    private javax.swing.JPanel jPanel7;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel6;

    // End of variables declaration//GEN-END:variables


}

