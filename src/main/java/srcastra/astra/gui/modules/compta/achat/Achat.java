/*
* Achat.java
*
* Created on 30 juin 2003, 9:08
*/
package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.gui.components.AIframe;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.components.celleditor.ListeCellEditor;
import srcastra.astra.gui.components.celleditor.TextFieldEditor;
import srcastra.astra.gui.components.combobox.liste.Liste;
import srcastra.astra.gui.components.textFields.ATextField;
import srcastra.astra.gui.components.tva.TvaFrame;
import srcastra.astra.gui.components.tva.TvaMediator;
import srcastra.astra.gui.modules.MainScreenModule;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.MessageManager;
import srcastra.astra.gui.sys.formVerification.DecimalMask;
import srcastra.astra.gui.sys.formVerification.DefaultMask;
import srcastra.astra.gui.test.ColoredTableCellRenderer;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.configuration.Periode_T;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.sys.rmi.ParamComptable;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author thomas
 */
public class Achat extends javax.swing.JInternalFrame implements MainScreenModule, AIframe, ActionListener, ToolBarComposer {

    /**
     * Creates new form Achat
     */
    MainScreenModule parent;
    AchatTableModel model;
    TvaTableModel modeltva;
    Liste tva;
    Liste compte;
    TvaMediator tvamediatorachat;
    TvaMediator tvamediatorachavente;
    TvaFrame achat;
    astrainterface serveur;
    Loginusers_T currentUser;
    java.awt.Frame superOwner;
    TableCellEditor editorListe;
    EditTable editable;
    ActionToolBar actionToolBar;
    Object[] journalcompta;
    static Achat_T achatc;
    javax.swing.event.InternalFrameListener iFrameListener;
    Object[] periode;
    Periode_T periodCourante;
    int[] actiontab;
    int mode = ActionToolBar.ACT_READ;
    int categoriejournal;
    int journalkey = 0;
    int periodekey = 0;
    FacturationModel modelfact;
    Hashtable rentabilite = new Hashtable();
    static boolean cellEditable;

    boolean modifAchat = false;

    public static boolean getCellEditable() {
        return cellEditable;
    }

    ActionListener action = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == compte) {
                grp_Table_Achat.editCellAt(grp_Table_Achat.getSelectedRow(), 1);
                tva.requestFocus();
            } else if (evt.getSource() == grp_Liste_journal) {
                journalcompta = grp_Liste_journal.getData();
                if (journalcompta != null) {
                    editable.setJota_categorie(Integer.parseInt(journalcompta[journalcompta.length - 2].toString()));
                    categoriejournal = Integer.parseInt(journalcompta[journalcompta.length - 2].toString());
                }
            }
        }
    };

    public String formatNumber(double data) {
        NumberFormat form = new DecimalFormat("##########.##");
        System.out.println("format" + ((DecimalFormat) form).toPattern());
        return form.format(data);
    }

    public void action(KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (grp_Cbox_Frais.isSelected()) {
                model.addData();
                grp_Table_Achat.tableChanged(new javax.swing.event.TableModelEvent(model));
                grp_Table_Achat.changeSelection(0, 0, false, false);
                editable.interTable(0, 0);
            } else {
                actiontab = new int[]{ActionToolBar.DO_ACCEPT, ActionToolBar.DO_PREVIOUS};
                actionToolBar.setActionEnabled(actiontab);
                achatParam(false);
            }
        }
    }

    public void achatParam(boolean mod) {

        new AchatParam(parent.getSuperOwner(), true, this, editable, actionToolBar, getCategoriejournal(), mod).show();// Add your handling code here:

//modelfact.setData(achatc.getResume());

//       grp_Label_achat.setText(achatc.getMontantFactPre().toString());
        activeToolbar(0);
        change();
    }

    private void change() {
        Object cleprod = model.getValueAt2(grp_Table_Achat.getSelectedRow(), 11);
        Object typeprod = model.getValueAt2(grp_Table_Achat.getSelectedRow(), 13);
        if (cleprod != null && typeprod != null) {
            String key = cleprod.toString() + typeprod.toString();
            if (rentabilite != null) {
                Object[] rent = (Object[]) rentabilite.get(key);
                if (rent != null) {
                    modelfact.setData((ArrayList) rent[3]);
                    grp_Table_resume.tableChanged(new TableModelEvent(modelfact));
                    grp_Label_achat.setText("" + formatNumber(Math.abs(((Double) rent[1]).doubleValue())));
                    grp_Label_vente.setText("" + formatNumber(Math.abs(((Double) rent[0]).doubleValue())));
                    grp_Label_rent.setText("" + formatNumber(calculrentabilite(((Double) rent[0]).doubleValue(), ((Double) rent[1]).doubleValue())) + " %");
                }
            }
        }
    }

    public void facturation() {
        new Facturation(parent.getSuperOwner(), true, this, editable, actionToolBar).show();// Add your handling code here:
    }

    public double calculrentabilite(double vente, double achat) {
        double quotient = vente / 100;
        double diff = vente - achat;
        double rentabilite = diff / quotient;
        return rentabilite;
    }

    public void activeToolbar(int type) {
        if (type == 0) {
            if (grp_Cbox_Frais.isSelected())
                actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_DELETE, ActionToolBar.DO_CANCEL};
            else
                actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_MODIFY, ActionToolBar.DO_DELETE, ActionToolBar.DO_CANCEL};
        } else if (type == 1) {
            if (grp_Cbox_Frais.isSelected())
                actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_CANCEL};
            else
                actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_MODIFY, ActionToolBar.DO_CANCEL};
        } else if (type == 3) {
            actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_F10, ActionToolBar.DO_CREATE};
        } else if (type == 4) {
            actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_MODIFY, ActionToolBar.DO_F7, ActionToolBar.DO_PREVIOUS};

        } else if (type == 5) {
            actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_F7, ActionToolBar.DO_PREVIOUS};

        }

        this.actionToolBar.setActionEnabled(actiontab);
    }

    /*   private void affichePeriode(){
    srcastra.astra.sys.classetransfert.utils.Date date=(srcastra.astra.sys.classetransfert.utils.Date)periode[1];
    int per=((Integer)periode[0]).intValue();
    grp_Label_periode.setText(per+"  "+date.getYear());
    }*/
    public Achat(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener) {
        this.currentUser = currentUser;
        this.serveur = serveur;
        this.superOwner = superOwner;
        parent = this;
        achatc = new Achat_T();
        initComponents();
// grp_TField_com.addActionListener(this);
        setDocument();
        initListeBasicListe();
// initListe();
        editable = new EditTable();
        initTable();
        initTableTva();
        initTableResume();
        editable.setTable(grp_Table_Achat);
        editable.setTvaf(achat);
        editable.setTable2(grp_Table_Tva);
        editable.setModeltva(modeltva);
        editable.setModel(model);
        editable.setFrame(this);
        this.iFrameListener = iFrameListener;
        this.addInternalFrameListener(iFrameListener);
        this.actionToolBar = actionToolBar;
        this.actionToolBar.setTbComposer(this);
        grp_Liste_journal.addActionListener(action);
        grp_Table_resume.setFocusable(false);
//grp_Liste_journal.setCleUnik(1);
//   this.actionToolBar.(getDefaultActionToolBarMask());
        try {
            this.periode = parent.getServeur().renvParamCompta(parent.getCurrentUser().getUrcleunik()).getPeriodeCompta(ParamComptable.JOURNAL_ACHAT, parent.getCurrentUser().getUrcleunik(), null);
            this.periodCourante = (Periode_T) periode[3];//(Periode_T)parent.getServeur().renvPeriodeRmiObject(parent.getCurrentUser().getUrcleunik()).get(parent.getCurrentUser().getUrcleunik(), 0,0);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
// affichePeriode();
        grp_Date_date.setDate(CalculDate.getTodayDate());
        grp_Date_recept.setDate(CalculDate.getTodayDate());
//grp_Liste_journal.requestFocus();
        setEnabledAction(false);
        activeToolbar(3);
    }

    private void setDocument() {
        grp_TField_montanteur.setDocument(new DecimalMask(7, 2, parent.getCurrentUser().getLangage()));

    }

    private void initListeBasicListe() {
        grp_Liste_journal.setServeur(parent.getServeur());
        grp_Liste_journal.setLogin(parent.getCurrentUser());
        grp_Liste_journal.setModel(new srcastra.astra.gui.components.combobox.liste.JournauxCTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_Liste_journal.init2();

        grp_li_periode.setServeur(parent.getServeur());
        grp_li_periode.setLogin(parent.getCurrentUser());
        grp_li_periode.setModel(new srcastra.astra.gui.components.combobox.liste.PeriodListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_li_periode.init2();
        grp_Liste_tiers.setServeur(parent.getServeur());
        grp_Liste_tiers.setLogin(parent.getCurrentUser());
        grp_Liste_tiers.setModel(new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_Liste_tiers.init2();

        /* grp_LSelect_Dossier_ClientFacturable.setMediator(parent.getMainframe().getMediator2());
        grp_LSelect_Dossier_ClientFacturable.setName("lf");
        parent.getMainframe().getMediator2().registerListe2(grp_LSelect_Dossier_ClientFacturable);
        grp_LSelect_Dossier_ClientFacturable.setServeur(parent.getServeur());
        grp_LSelect_Dossier_ClientFacturable.setLogin(parent.getCurrentUser());
        grp_LSelect_Dossier_ClientFacturable.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(parent.getServeur(),parent.getCurrentUser()));
        grp_LSelect_Dossier_ClientFacturable.init2();
        grp_LSelect_Dossier_ClientFacturable.setLeft(true);*/

    }

    private Liste instanciateListeCompte() {
        Liste tmp = new Liste();
        tmp.setIsLastComponent(true);
//    tmp.addActionListener(editable.getAction());
        tmp.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        tmp.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        tmp.setVerifyInputWhenFocusTarget(false);
        tmp.setPreferredSize(new java.awt.Dimension(100, 18));
        tmp.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(serveur, currentUser));
        tmp.setServeur(serveur);
        tmp.setLogin(currentUser);
        tmp.init2();
        return tmp;
    }

    private ATextField instanciateTextField(int type) {
        ATextField text = new ATextField();
        text.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        text.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        if (type == 1) {
            text.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));
        } else if (type == 2) {
            text.setDocument(new DecimalMask(5, 2, currentUser.getLangage()));
        } else if (type == 3) {
            text.setDocument(new DefaultMask(1, 1, currentUser.getLangage(), DefaultMask.FIRST_LETTRE_IN_UPPERCASE));
            text.setText("D");
        }
        return text;
    }

    private void initTableResume() {
        this.modelfact = new FacturationModel(parent, parent.getCurrentUser());
        grp_Table_resume.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grp_Table_resume.setAutoCreateColumnsFromModel(false);
        grp_Table_resume.getTableHeader().setReorderingAllowed(false);
        grp_Table_resume.setSelectionBackground(new java.awt.Color(204, 204, 255));
        grp_Table_resume.setModel(modelfact);
        grp_Table_resume.setFocusable(false);
        for (int k = 0; k < modelfact.m_columns.length; k++) {
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            renderer.setHorizontalAlignment(modelfact.m_columns[k].c_alignement);
            TableColumn column = null;
            JLabel per;
            column = new TableColumn(k, modelfact.m_columns[k].c_width, renderer, null);
            column.setHeaderValue(modelfact.m_columns[k].c_title);
            grp_Table_resume.addColumn(column);
        }
        JTableHeader header = grp_Table_resume.getTableHeader();
        header.setUpdateTableInRealTime(false);
        header.setResizingAllowed(false);
    }

    private Liste instanciateListeTva() {
        Liste tva = new Liste();
        achat = new TvaFrame(parent.getSuperOwner(), false, parent.getServeur(), parent.getCurrentUser(), 1, parent);
        tvamediatorachat = new TvaMediator();
        achat.setTvamediator(tvamediatorachat);
        tva.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        tva.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        tva.setVerifyInputWhenFocusTarget(false);
        tva.setPreferredSize(new java.awt.Dimension(100, 18));
        tva.setModel(tvamediatorachat.getModel());//new srcastra.astra.gui.components.combobox.liste.TypeTvaListeTableModel(parent.getServeur(),parent.getCurrentUser()));
        tva.setTvamediator(tvamediatorachat);
        tva.setServeur(serveur);
        tva.setLogin(currentUser);
        tva.setIsLastComponent(true);
        tva.init2();
        return tva;
    }


    private void initTableTva() {
        this.modeltva = new TvaTableModel(parent, parent.getCurrentUser());
        grp_Table_Tva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        grp_Table_Tva.setAutoCreateColumnsFromModel(false);
        grp_Table_Tva.getTableHeader().setReorderingAllowed(false);
        grp_Table_Tva.setSelectionBackground(new java.awt.Color(204, 204, 255));
        grp_Table_Tva.setModel(modeltva);
        grp_Table_Tva.setFocusable(false);
        for (int k = 0; k < modeltva.m_columns.length; k++) {
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            TableColumn column = null;
            renderer.setHorizontalAlignment(modeltva.m_columns[k].c_alignement);
            if (k == 0) {
                TableCellEditor editor = new ListeCellEditor(instanciateListeTva(), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
                column = new TableColumn(k, modeltva.m_columns[k].c_width, renderer, null);
                column.setHeaderValue(modeltva.m_columns[k].c_title);
            } else if (k == 1 || k == 2) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(1), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
                column = new TableColumn(k, modeltva.m_columns[k].c_width, renderer, null);
                column.setHeaderValue(modeltva.m_columns[k].c_title);
            }
            grp_Table_Tva.addColumn(column);
        }
        JTableHeader header = grp_Table_Tva.getTableHeader();
        header.setUpdateTableInRealTime(false);
        header.setResizingAllowed(false);
    }

    private void initTable() {
//  grp_Table_Achat.setFocusable(false);
        grp_Table_Achat.setSelectionBackground(new java.awt.Color(204, 204, 255));
        this.model = new AchatTableModel(parent, parent.getCurrentUser());
        grp_Table_Achat.setAutoCreateColumnsFromModel(false);
        grp_Table_Achat.getTableHeader().setReorderingAllowed(false);
        grp_Table_Achat.setModel(this.model);
        grp_Table_Achat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int k = 0; k < model.m_columns.length; k++) {
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);
            TableColumn column = null;
            if (k == 0) {
                TableCellEditor editor = new ListeCellEditor(instanciateListeCompte(), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 1) {
                TableCellEditor editor = new ListeCellEditor(instanciateListeTva(), k, true, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 2) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(2), k, true, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 3) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(3), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 4) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(1), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 5) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(1), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            } else if (k == 6) {
                TableCellEditor editor = new TextFieldEditor(instanciateTextField(1), k, false, model, editable.getMouse(), editable);
                editor.addCellEditorListener(editable);
                column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            }
            column.setHeaderValue(model.m_columns[k].c_title);
            grp_Table_Achat.addColumn(column);
        }
        JTableHeader header = grp_Table_Achat.getTableHeader();
        header.setUpdateTableInRealTime(false);
        header.setResizingAllowed(false);
        if (grp_Table_Achat.getRowCount() != 0) ;
        grp_Table_Achat.changeSelection(0, 0, false, false);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        AchatPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        grp_Cbox_Frais = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel7 = new javax.swing.JLabel();
        grp_TField_com = new srcastra.astra.gui.components.textFields.ATextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        grp_Table_resume = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        grp_Liste_journal = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_TField_numpiece = new javax.swing.JLabel();
        grp_li_periode = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        grp_Date_date = new srcastra.astra.gui.components.date.thedate.ADate();
        jLabel10 = new javax.swing.JLabel();
        grp_Date_recept = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_Liste_tiers = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        grp_TField_montanteur = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Date_ech = new srcastra.astra.gui.components.date.thedate.ADate();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        grp_Label_dosVente = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        grp_Label_dosAchat = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        grp_Label_dosRent = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        grp_Label_prodVente = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        grp_Label_prodAchat = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        grp_Label_prodRent = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_Achat = new srcastra.astra.gui.components.frame.MY_Table();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        grp_Label_vente = new javax.swing.JLabel();
        grp_Label_rent = new javax.swing.JLabel();
        grp_Label_achat = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_Table_Tva = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        grp_Label_base = new javax.swing.JLabel();
        grp_Label_Tva = new javax.swing.JLabel();

        setClosable(true);
        setName("achat");
        setPreferredSize(new java.awt.Dimension(730, 520));
        AchatPanel.setLayout(new java.awt.BorderLayout());

        AchatPanel.setPreferredSize(new java.awt.Dimension(580, 400));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(580, 20));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel17.setPreferredSize(new java.awt.Dimension(730, 20));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("frais"));
        jPanel17.add(jLabel8);

        grp_Cbox_Frais.setGrp_Comp_nextComponent(grp_TField_com);
        grp_Cbox_Frais.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel17.add(grp_Cbox_Frais);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_comment"));
        jPanel17.add(jLabel7);

        grp_TField_com.setPreferredSize(new java.awt.Dimension(400, 18));
        grp_TField_com.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_com.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_TField_com.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grp_TField_comKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                grp_TField_comKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                grp_TField_comKeyTyped(evt);
            }
        });

        jPanel17.add(grp_TField_com);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(705, 80));
        grp_Table_resume.setModel(new DefaultTableModel());
        jScrollPane3.setViewportView(grp_Table_resume);

        jPanel17.add(jScrollPane3);

        jPanel2.add(jPanel17, java.awt.BorderLayout.WEST);

        AchatPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.X_AXIS));

        jPanel3.setBorder(new javax.swing.border.TitledBorder(null, "Achat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 85));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_journ"));
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel8.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_per"));
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel8.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_dict"));
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel8.add(jLabel3, gridBagConstraints);

        grp_Liste_journal.setGrp_Comp_nextComponent(grp_li_periode);
        grp_Liste_journal.setPreferredSize(new java.awt.Dimension(100, 18));
        grp_Liste_journal.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Liste_journal.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel8.add(grp_Liste_journal, gridBagConstraints);

        grp_TField_numpiece.setFont(new java.awt.Font("Dialog", 1, 14));
        grp_TField_numpiece.setForeground(new java.awt.Color(153, 0, 0));
        grp_TField_numpiece.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(grp_TField_numpiece, gridBagConstraints);

        grp_li_periode.setGrp_Comp_nextComponent(grp_Liste_tiers);
        grp_li_periode.setPreferredSize(new java.awt.Dimension(100, 18));
        grp_li_periode.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_li_periode.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel8.add(grp_li_periode, gridBagConstraints);

        jPanel4.add(jPanel8);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel7.add(jLabel9, gridBagConstraints);

        grp_Date_date.setGrp_Comp_nextComponent(grp_Date_recept);
        grp_Date_date.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Date_date.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_Date_date, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("Recept"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel7.add(jLabel10, gridBagConstraints);

        grp_Date_recept.setGrp_Comp_nextComponent(grp_Date_ech);
        grp_Date_recept.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Date_recept.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel7.add(grp_Date_recept, gridBagConstraints);

        grp_Liste_tiers.setGrp_Comp_nextComponent(grp_Date_date);
        grp_Liste_tiers.setPreferredSize(new java.awt.Dimension(100, 18));
        grp_Liste_tiers.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Liste_tiers.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel7.add(grp_Liste_tiers, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_ref"));
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel7.add(jLabel4, gridBagConstraints);

        jPanel5.add(jPanel7);

        jPanel3.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("A_montantE"));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jLabel6, gridBagConstraints);

        grp_TField_montanteur.setGrp_Comp_nextComponent(grp_Cbox_Frais);
        grp_TField_montanteur.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_montanteur.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_TField_montanteur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grp_TField_montanteurKeyPressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_TField_montanteur, gridBagConstraints);

        grp_Date_ech.setGrp_Comp_nextComponent(grp_TField_montanteur);
        grp_Date_ech.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Date_ech.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel9.add(grp_Date_ech, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", parent.getCurrentUser().getLangage()).getString("eche"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel9.add(jLabel5, gridBagConstraints);

        jPanel6.add(jPanel9);

        jPanel3.add(jPanel6);

        AchatPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(10, 300));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(0, 50));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel21.setBorder(new javax.swing.border.TitledBorder(null, "Dossier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_vente"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 9);
        jPanel21.add(jLabel11, gridBagConstraints);

        grp_Label_dosVente.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_dosVente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_dosVente.setText("0.00");
        grp_Label_dosVente.setPreferredSize(new java.awt.Dimension(80, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 13);
        jPanel21.add(grp_Label_dosVente, gridBagConstraints);

        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_ach"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);
        jPanel21.add(jLabel13, gridBagConstraints);

        grp_Label_dosAchat.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_dosAchat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_dosAchat.setText("0.00");
        grp_Label_dosAchat.setPreferredSize(new java.awt.Dimension(80, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel21.add(grp_Label_dosAchat, gridBagConstraints);

        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_rent"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);
        jPanel21.add(jLabel15, gridBagConstraints);

        grp_Label_dosRent.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_dosRent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_dosRent.setText("0.00");
        grp_Label_dosRent.setPreferredSize(new java.awt.Dimension(50, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(grp_Label_dosRent, gridBagConstraints);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("%");
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel21.add(jLabel17, gridBagConstraints);

        jPanel20.add(jPanel21);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jPanel22.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("produit"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_vente"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 9);
        jPanel22.add(jLabel12, gridBagConstraints);

        grp_Label_prodVente.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_prodVente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_prodVente.setText("0.00");
        grp_Label_prodVente.setPreferredSize(new java.awt.Dimension(80, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 13);
        jPanel22.add(grp_Label_prodVente, gridBagConstraints);

        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_ach"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);
        jPanel22.add(jLabel14, gridBagConstraints);

        grp_Label_prodAchat.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_prodAchat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_prodAchat.setText("0.00");
        grp_Label_prodAchat.setPreferredSize(new java.awt.Dimension(80, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel22.add(grp_Label_prodAchat, gridBagConstraints);

        jLabel16.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("ach_rent"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);
        jPanel22.add(jLabel16, gridBagConstraints);

        grp_Label_prodRent.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_prodRent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_prodRent.setText("0.00");
        grp_Label_prodRent.setPreferredSize(new java.awt.Dimension(50, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel22.add(grp_Label_prodRent, gridBagConstraints);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("%");
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel22.add(jLabel18, gridBagConstraints);

        jPanel20.add(jPanel22);

        jPanel13.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.X_AXIS));

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel11.setPreferredSize(new java.awt.Dimension(460, 210));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(480, 200));
        grp_Table_Achat.setModel(new DefaultTableModel());
        grp_Table_Achat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_Table_AchatMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(grp_Table_Achat);

        jPanel11.add(jScrollPane1);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jPanel14.setPreferredSize(new java.awt.Dimension(400, 25));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        grp_Label_vente.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_Label_vente.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_vente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grp_Label_vente.setPreferredSize(new java.awt.Dimension(80, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(grp_Label_vente, gridBagConstraints);

        grp_Label_rent.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_Label_rent.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_rent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_rent.setPreferredSize(new java.awt.Dimension(80, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(grp_Label_rent, gridBagConstraints);

        grp_Label_achat.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_Label_achat.setForeground(new java.awt.Color(204, 0, 0));
        grp_Label_achat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_achat.setText("0.00");
        grp_Label_achat.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(grp_Label_achat, gridBagConstraints);

        jPanel14.add(jPanel16);

        jPanel14.add(jPanel18);

        jPanel11.add(jPanel14);

        jPanel19.add(jPanel11);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jPanel12.setPreferredSize(new java.awt.Dimension(210, 235));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(220, 200));
        grp_Table_Tva.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(grp_Table_Tva);

        jPanel12.add(jScrollPane2);

        jPanel15.setPreferredSize(new java.awt.Dimension(170, 25));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        grp_Label_base.setFont(new java.awt.Font("Dialog", 1, 14));
        grp_Label_base.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_base.setText("0.00");
        grp_Label_base.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 21);
        jPanel1.add(grp_Label_base, gridBagConstraints);

        grp_Label_Tva.setFont(new java.awt.Font("Dialog", 1, 14));
        grp_Label_Tva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_Tva.setText("0.00");
        grp_Label_Tva.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(grp_Label_Tva, gridBagConstraints);

        jPanel15.add(jPanel1);

        jPanel12.add(jPanel15);

        jPanel19.add(jPanel12);

        jPanel10.add(jPanel19, java.awt.BorderLayout.NORTH);

        AchatPanel.add(jPanel10, java.awt.BorderLayout.SOUTH);

        getContentPane().add(AchatPanel, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void grp_Table_AchatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_Table_AchatMouseClicked

        if (mode == ActionToolBar.ACT_INSERT) {
            if (evt.getClickCount() == 1) {
                editable.resume();
            }
        } else if (mode == ActionToolBar.ACT_READ) {
            if (grp_Table_Achat.getRowCount() > 0) {
                editable.resume();
            }
        }

/*          System.out.println("double click");
Object tmp=null;
String value="";
int cle=0;
change();
CellEditorInterface inter=(CellEditorInterface)grp_Table_Achat.getCellEditor(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn());
tmp=model.getValueAt2(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn());
if(inter.isListe()){
if(inter.isTva())
tmp=model.getValueAt2(grp_Table_Achat.getSelectedRow(),7);
else
tmp=model.getValueAt2(grp_Table_Achat.getSelectedRow(),6);
if(tmp!=null)
cle=((Integer)tmp).intValue();
editable.interTableListe(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn(),cle,false);
}
else{
tmp=model.getValueAt2(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn());
if(tmp!=null)
value=tmp.toString();
editable.interTable(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn(),value,false);
}  */
    }//GEN-LAST:event_grp_Table_AchatMouseClicked

    AbstractCellEditor editor;
    DefaultTableCellRenderer renderer;

    private void grp_TField_montanteurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_montanteurKeyPressed
        editable.setSommeGlobal(getMontanEuro());
// Add your handling code here:
    }//GEN-LAST:event_grp_TField_montanteurKeyPressed

    private double getMontanEuro() {
        double montanteu = Double.parseDouble(grp_TField_montanteur.getText());
        return categoriejournal == ParamComptable.JOURNAL_ACHAT ? montanteu : -montanteu;
    }

    private double getTvaEuro(double tva) {
        return categoriejournal == ParamComptable.JOURNAL_ACHAT ? tva : -tva;
    }

    private double getBaseEuro(double base) {
        return categoriejournal == ParamComptable.JOURNAL_ACHAT ? base : -base;
    }

    private void grp_TField_comKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_comKeyPressed
        if (grp_Table_Achat.getRowCount() == 0)
            action(evt);
    }//GEN-LAST:event_grp_TField_comKeyPressed

    private void grp_TField_comKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_comKeyReleased

    }//GEN-LAST:event_grp_TField_comKeyReleased

    private void grp_TField_comKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_comKeyTyped

    }//GEN-LAST:event_grp_TField_comKeyTyped

    /**
     * Fermeture de l'écran courrant et passage
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

    /**
     * Permet de changer l'état de la TabbedPane dans le pricipal
     */
    public void enabledTabbedPane(boolean enabled) {
    }

    public Loginusers_T getCurrentUser() {
        return this.currentUser;
    }

    public boolean getNestedSignaletique() {
        return false;
    }

    public java.awt.Frame getOwner() {
        return null;

    }

    public astrainterface getServeur() {
        return this.serveur;
    }

    public DossierRmiInterface getServeurDossier() {
        return null;
    }

    public java.awt.Frame getSuperOwner() {
        return superOwner;
    }

    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */
    public void nextScreen(int currentScreen) {
    }

    public void nextScreen(int currentScreen, int insert) {
    }

    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */
    public void nextScreen(int currentScreen, boolean affich) {
    }

    public void registerTable(javax.swing.JTable generique_table) {
    }

    public void reloadToolBarInfo() {
        this.actionToolBar.setActionEnabled(actiontab);
        actionToolBar.setTbComposer(this);
    }

    /**
     * Fixe la clé unique dans le module parent
     *
     * @param ContextCleUnik clé unique
     */

    public void setEnabledAction(boolean enabled) {
// grp_TField_numpiece.setEnabled(enabled);
        grp_TField_com.setEnabled(enabled);
        grp_Cbox_Frais.setEnabled(enabled);
        grp_Liste_journal.setEnabled(enabled);
//grp_Liste_periode.setEnabled(enabled);
        grp_Liste_tiers.setEnabled(enabled);
        grp_Date_date.setEnabled(enabled);
        grp_Date_ech.setEnabled(enabled);
        grp_Date_recept.setEnabled(enabled);
        grp_TField_montanteur.setEnabled(enabled);
        grp_li_periode.setEnabled(enabled);
        if (!enabled) {
            activeToolbar(3);
            grp_Table_Achat.setBackground(java.awt.Color.lightGray);
            grp_Table_Tva.setBackground(java.awt.Color.lightGray);
//((AbstractCellEditor )grp_Table_Achat.getCellEditor(grp_Table_Achat.getSelectedRow(),grp_Table_Achat.getSelectedColumn())).cancelCellEditing();
            if (grp_Table_Achat.getRowCount() > 0) {
                int row = grp_Table_Achat.getSelectedRow();
                int col = grp_Table_Achat.getSelectedColumn();
                ((AbstractCellEditor) grp_Table_Achat.getCellEditor(grp_Table_Achat.getSelectedRow(), grp_Table_Achat.getSelectedColumn())).stopCellEditing();
                grp_Table_Achat.tableChanged(new TableModelEvent(model));
                if (grp_Table_Achat.getRowCount() > 0) {
                    grp_Table_Achat.changeSelection(0, 0, false, false);
                    editable.resume();
                }
            }
// ((AbstractCellEditor )grp_Table_Achat.getCellEditor()).cancelCellEditing();
        } else {
            activeToolbar(0);
            grp_Table_Achat.setBackground(java.awt.Color.white);
            grp_Table_Tva.setBackground(java.awt.Color.white);
        }
//  grp_Table_Achat.setFocusable(enabled);
//  grp_Table_Achat.setEnabled(enabled);

    }

    public void setContextCleUnik(int ContextCleUnik) {
    }

    /**
     * permet d'établir une liste d'action choisie comme étant les actions de la ToobBar
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
     * permet à la classe principale du modules de pouvoir sauvegarder l'objet TbComposer
     */
    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {
    }

    public void setNestedSignaletique(boolean netstedSignletique) {
    }

    public void saveToolBarInfo() {
    }

    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

    }

    public void doAccept() {
    }


    public void doCancel() {

        setEnabledAction(false);
        refreshFields();
        mode = ActionToolBar.ACT_READ;
        editable = new EditTable();
        grp_Table_Achat = new srcastra.astra.gui.components.frame.MY_Table();
        grp_Table_Achat.setModel(new DefaultTableModel());
        grp_Table_Achat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_Table_AchatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grp_Table_Achat);
        grp_Table_Tva = new JTable();
        grp_Table_resume = new JTable();
        grp_Table_Tva.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(grp_Table_Tva);
        grp_Table_resume.setModel(new DefaultTableModel());
        jScrollPane3.setViewportView(grp_Table_resume);
        initTable();
        initTableTva();
        initTableResume();
        editable.setTable(grp_Table_Achat);
        editable.setTvaf(achat);
        editable.setTable2(grp_Table_Tva);
        editable.setModeltva(modeltva);
        editable.setModel(model);
        editable.setFrame(this);
        grp_Liste_journal.requestFocus();
        cellEditable = false;
    }

    public void doClose() {
        doDefaultCloseAction();
    }

    public void doCreate() {
        cellEditable = true;
        achatc = new Achat_T();
//   grp_Table_resume.tableChanged(new TableModelEvent(model));
// grp_Table_resume.tableChanged(new TableModelEvent(model));
        this.grp_TField_numpiece.setText("");

        setEnabledAction(true);
        refreshFields();
        mode = ActionToolBar.ACT_INSERT;
        return;

    }

    public void refreshFields() {
// grp_Label_Base.setText("0.00");
        achatc = new Achat_T();
        grp_Label_Tva.setText("0.00");
        grp_Label_dosRent.setText("0.00");
        grp_Label_dosAchat.setText("0.00");
        grp_Label_dosVente.setText("0.00");
        grp_Label_prodAchat.setText("0.00");
        grp_Label_prodRent.setText("0.00");
        grp_Label_prodVente.setText("0.00");
        grp_Label_base.setText("0.00");
        grp_TField_com.setText("");
// grp_TField_numpiece.setText("");
        model.setData(new ArrayList());
        modeltva.setData(new ArrayList());
        modelfact.setData(new ArrayList());
        grp_Liste_journal.setCleUnik(0);
        grp_Liste_tiers.setCleUnik(0);
        grp_Date_date.setDate(null);
        grp_Date_ech.setDate(null);
        grp_Date_recept.setDate(null);
        grp_TField_montanteur.setText("");
        grp_Label_achat.setText("0.00");
        grp_Table_Achat.tableChanged(new TableModelEvent(model));
        grp_Table_Tva.tableChanged(new TableModelEvent(modeltva));
        grp_Table_resume.tableChanged(new TableModelEvent(modelfact));
//grp_Liste_journal.requestFocus();
        if (periodCourante != null)
            grp_li_periode.setCleUnik(periodCourante.getPede_cleunik());
// grp_Liste_journal.setCleUnik(1);
        setFactuBis();
    }

    public void setFactuBis() {
        if (journalkey != 0 && periodekey != 0) {
            grp_li_periode.setCleUnik(periodekey);
            grp_Liste_journal.setCleUnik(journalkey);
            grp_Liste_tiers.requestFocus();
        } else {
            grp_Liste_journal.requestFocus();
        }

    }

    public void doDelete() {
        if (grp_Table_Achat.getRowCount() != 0) {
            int row = grp_Table_Achat.getSelectedRow();
            achatc.getAchat().remove(row);
            editable.updateTvaListe();
            editable.getSommeGlobal();
            grp_Table_Achat.tableChanged(new TableModelEvent(model));
            if (grp_Table_Achat.getRowCount() > 0)
                grp_Table_Achat.changeSelection(0, 0, false, false);
            else
                grp_TField_com.requestFocus();
        }
    }

    //ajout de Driart le 5 aout 2003
    public void doF10() {
        facturation();
        if (achatc == null)
            achatc = new Achat_T();
        this.grp_Liste_journal.setCleUnik(achatc.getJota_cleunik());
//  this.grp_Label_periode.setText(""+achatc.getJota_periode());
        this.grp_li_periode.setCleUnik(achatc.getJota_periode());
        this.grp_TField_numpiece.setText(achatc.getNumpiece());
        this.grp_Date_date.setDate(achatc.getDate());
        this.grp_Date_recept.setDate(achatc.getRecept());
        this.grp_Date_ech.setDate(achatc.getEcheance());
        this.grp_Liste_tiers.setCleUnik(achatc.getFrcleunik());
        grp_TField_com.setText(achatc.getCommentaire());
        if (achatc.getFraisgen() == 1)
        this.grp_Cbox_Frais.enable();
//  this.model.setData(achatc.getAchat());
//  this.modeltva.setData(achatc.getTvamontant().toString());

        this.grp_TField_montanteur.setText("" + achatc.getMontanteuro().toString());
        modelfact.setData(achatc.getResume());
        modeltva.setData(null);
        grp_Table_resume.tableChanged(new TableModelEvent(modelfact));
        grp_Table_Achat.tableChanged(new TableModelEvent(model));
        grp_Table_Tva.tableChanged(new TableModelEvent(modeltva));
        if (grp_Table_Achat.getRowCount() > 0) {
            grp_Table_Achat.changeSelection(0, 0, false, false);
            editable.resume();
        }

        if (achatc.getExported() == 0) {
            cellEditable = true;
            activeToolbar(4);
        } else {
            cellEditable = false;
            activeToolbar(5);
        }


        mode = ActionToolBar.ACT_INSERT;

        modifAchat = true;
        System.out.println(achatc.getHeTransact());
        System.out.println("OKI");


    }

    public static Achat_T getAchat() {
        if (achatc == null)
            achatc = new Achat_T();
        return achatc;

    }

    public static void setAchat(Achat_T achat) {
        achatc = achat;
    }

    //end ajout
    public void doF7() {

        achatParam(true);
        if (achatc.getExported() == 0) {
            cellEditable = true;
            activeToolbar(5);
        } else {
            cellEditable = false;
            activeToolbar(4);
        }

    }

    public void doHelp() {
    }

    public void doModify() {
        if (mode == ActionToolBar.ACT_READ) {
            setEnabledAction(true);
            //refreshFields();
            mode = ActionToolBar.ACT_INSERT;
            return;
        } else if (mode == ActionToolBar.ACT_INSERT) {
            achatParam(false);
        }
        /*else if(mode == ActionToolBar.ACT_DISABLE)
        {
        
            doPrevious();
            
        
        }*/

        //achatParam(true);
        //doPrevious();

    }

    public void doNext() {
    }

    public void setDefaultJournal() {
        if (journalkey == 0)
            journalkey = grp_Liste_journal.getCleUnik();
        if (periodekey == 0)
            periodekey = grp_li_periode.getCleUnik();
    }

    public void doPrevious() {
        MY_bigDecimal som = editable.getNewSommeGlobal();
        if (som.doubleValue() != 0d) {
            new MessageManager().showMessageDialog(this, "soldeerreur", "soldeerreurtitle", this.currentUser);
            return;

        }
        if (grp_li_periode.getCleUnik() == 0 || grp_Liste_journal.getCleUnik() == 0 || grp_TField_montanteur.getText().equals("")) {
            System.out.println("periode " + grp_li_periode.getCleUnik());
            System.out.println("journal " + grp_Liste_journal.getCleUnik());
            System.out.println("montant " + grp_TField_montanteur.getText());
            return;
        }
        if (achatc == null)
            achatc = new Achat_T();


        achatc.setJota_cleunik(grp_Liste_journal.getCleUnik());
        System.out.println(grp_Liste_journal.getCleUnik());
        achatc.setJota_periode(grp_li_periode.getCleUnik());
        System.out.println(grp_li_periode.getCleUnik());
        achatc.setNumpiece(grp_TField_numpiece.getText());
        System.out.println(grp_TField_numpiece.getText());
        achatc.setDate(grp_Date_date.getDate());
        achatc.setRecept(grp_Date_recept.getDate());
        achatc.setEcheance(grp_Date_ech.getDate());
        achatc.setFrcleunik(grp_Liste_tiers.getCleUnik());

        for (int i = 0; i < achatc.getAchat().size(); i++)
            ((AchatCp) achatc.getAchat().get(i)).setFrcleunik(grp_Liste_tiers.getCleUnik());


        achatc.setCleunikFact(this.cleunikfact);
        System.out.println(grp_Liste_tiers.getCleUnik());
        if (grp_Cbox_Frais.isSelected())
            achatc.setFraisgen(1);
        else
            achatc.setFraisgen(0);

        System.out.println(achatc.getDrcleunik());

        achatc.setJota_categorie(categoriejournal);
        System.out.println(categoriejournal);
        achatc.setCommentaire(grp_TField_com.getText());

        String numpiece = "";

        //if(modifAchat)  
        //    numpiece=grp_TField_numpiece.getText();

        //String numpiece = "";
        try {
            if (!modifAchat)
                numpiece = parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).insertAchat(achatc, parent.getCurrentUser().getUrcleunik());
            else {
                numpiece = parent.getServeur().renvDossierRmiObject(parent.getCurrentUser().getUrcleunik()).modifyAchat(achatc, parent.getCurrentUser().getUrcleunik());
                cellEditable = false;
                activeToolbar(4);
            }
            this.isFromIntegration = false;

            setDefaultJournal();
            grp_TField_numpiece.setText(numpiece);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        editable.setValeur(null);
        setEnabledAction(false);
        mode = ActionToolBar.ACT_READ;
        cellEditable = false;
        modifAchat = false;

    }

    public void doPrint() {
    }

    public void doSwitch() {
    }

    public int[] getDefaultActionToolBarMask() {
        actiontab = new int[]{ActionToolBar.DO_CLOSE, ActionToolBar.DO_F10};
        return actiontab;
    }

    public java.awt.Component m_getGeneriqueTable() {
        return null;
    }

    public void setThisAsToolBarComponent() {
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
    /** Getter for property grp_Label_Base.
     * @return Value of property grp_Label_Base.
     */
    /**
     * Getter for property achatc.
     *
     * @return Value of property achatc.
     */
    public srcastra.astra.gui.modules.compta.achat.Achat_T getAchatc() {
        return achatc;
    }

    /**
     * Setter for property achatc.
     *
     * @param achatc New value of property achatc.
     */
    public void setAchatc(srcastra.astra.gui.modules.compta.achat.Achat_T achatc) {
        this.achatc = achatc;
    }

    /**
     * Getter for property grp_Label_Tva.
     *
     * @return Value of property grp_Label_Tva.
     */
    public javax.swing.JLabel getGrp_Label_Tva() {
        return grp_Label_Tva;
    }

    /**
     * Setter for property grp_Label_Tva.
     *
     * @param grp_Label_Tva New value of property grp_Label_Tva.
     */
    public void setGrp_Label_Tva(javax.swing.JLabel grp_Label_Tva) {
        this.grp_Label_Tva = grp_Label_Tva;
    }

    /**
     * Getter for property grp_Label_base.
     *
     * @return Value of property grp_Label_base.
     */
    public javax.swing.JLabel getGrp_Label_base() {
        return grp_Label_base;
    }

    /**
     * Setter for property grp_Label_base.
     *
     * @param grp_Label_base New value of property grp_Label_base.
     */
    public void setGrp_Label_base(javax.swing.JLabel grp_Label_base) {
        this.grp_Label_base = grp_Label_base;
    }

    /**
     * Getter for property grp_TField_montanteur.
     *
     * @return Value of property grp_TField_montanteur.
     */
    public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_montanteur() {
        return grp_TField_montanteur;
    }

    /**
     * Setter for property grp_TField_montanteur.
     *
     * @param grp_TField_montanteur New value of property grp_TField_montanteur.
     */
    public void setGrp_TField_montanteur(srcastra.astra.gui.components.textFields.ATextField grp_TField_montanteur) {
        this.grp_TField_montanteur = grp_TField_montanteur;
    }

    /**
     * Getter for property grp_Cbox_Frais.
     *
     * @return Value of property grp_Cbox_Frais.
     */
    public srcastra.astra.gui.components.checkbox.ACheckBox getGrp_Cbox_Frais() {
        return grp_Cbox_Frais;
    }

    /**
     * Setter for property grp_Cbox_Frais.
     *
     * @param grp_Cbox_Frais New value of property grp_Cbox_Frais.
     */
    public void setGrp_Cbox_Frais(srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_Frais) {
        this.grp_Cbox_Frais = grp_Cbox_Frais;
    }

    /**
     * Getter for property categoriejournal.
     *
     * @return Value of property categoriejournal.
     */
    public int getCategoriejournal() {
        journalcompta = grp_Liste_journal.getData();
        if (journalcompta != null) {
            editable.setJota_categorie(Integer.parseInt(journalcompta[journalcompta.length - 2].toString()));
            categoriejournal = Integer.parseInt(journalcompta[journalcompta.length - 2].toString());
            ;
        }
        return categoriejournal;
    }

    /**
     * Setter for property categoriejournal.
     *
     * @param categoriejournal New value of property categoriejournal.
     */
    public void setCategoriejournal(int categoriejournal) {
        this.categoriejournal = categoriejournal;
    }

    /** Getter for property resume.
     * @return Value of property resume.
     */


    /**
     * Getter for property resumeVente.
     *
     * @return Value of property resumeVente.
     */

    public void setRentabiliteValue(ArrayList achat, ArrayList vente, String key) {
        double totalvente = 0;
        double totalachat = 0;
        if (vente != null)
            for (int i = 0; i < vente.size(); i++) {
                Object[] tmp = (Object[]) vente.get(i);
                totalvente = totalvente + ((Double) tmp[3]).doubleValue();
            }
        if (achat != null)
            for (int i = 0; i < achat.size(); i++) {
                Object[] tmp = (Object[]) achat.get(i);
                totalachat = totalachat + ((Double) tmp[3]).doubleValue();
            }
        Object[] tmp = new Object[5];
        tmp[0] = new Double(totalvente);
        tmp[1] = new Double(totalachat);
        tmp[2] = new Double(calculrentabilite(totalvente, totalachat));
        tmp[3] = achat;
        tmp[4] = vente;
        rentabilite.put(key, tmp);
    }

    /**
     * Getter for property grp_Label_achat.
     *
     * @return Value of property grp_Label_achat.
     */
    public javax.swing.JLabel getGrp_Label_achat() {
        return grp_Label_achat;
    }

    /**
     * Setter for property grp_Label_achat.
     *
     * @param grp_Label_achat New value of property grp_Label_achat.
     */
    public void setGrp_Label_achat(javax.swing.JLabel grp_Label_achat) {
        this.grp_Label_achat = grp_Label_achat;
    }

    /**
     * Getter for property grp_Label_dosAchat.
     *
     * @return Value of property grp_Label_dosAchat.
     */
    public javax.swing.JLabel getGrp_Label_dosAchat() {
        return grp_Label_dosAchat;
    }

    /**
     * Setter for property grp_Label_dosAchat.
     *
     * @param grp_Label_dosAchat New value of property grp_Label_dosAchat.
     */
    public void setGrp_Label_dosAchat(javax.swing.JLabel grp_Label_dosAchat) {
        this.grp_Label_dosAchat = grp_Label_dosAchat;
    }

    /**
     * Getter for property grp_Label_dosRent.
     *
     * @return Value of property grp_Label_dosRent.
     */
    public javax.swing.JLabel getGrp_Label_dosRent() {
        return grp_Label_dosRent;
    }

    /**
     * Setter for property grp_Label_dosRent.
     *
     * @param grp_Label_dosRent New value of property grp_Label_dosRent.
     */
    public void setGrp_Label_dosRent(javax.swing.JLabel grp_Label_dosRent) {
        this.grp_Label_dosRent = grp_Label_dosRent;
    }

    /**
     * Getter for property grp_Label_dosVente.
     *
     * @return Value of property grp_Label_dosVente.
     */
    public javax.swing.JLabel getGrp_Label_dosVente() {
        return grp_Label_dosVente;
    }

    /**
     * Setter for property grp_Label_dosVente.
     *
     * @param grp_Label_dosVente New value of property grp_Label_dosVente.
     */
    public void setGrp_Label_dosVente(javax.swing.JLabel grp_Label_dosVente) {
        this.grp_Label_dosVente = grp_Label_dosVente;
    }

    /**
     * Getter for property grp_Table_Achat.
     *
     * @return Value of property grp_Table_Achat.
     */
    public srcastra.astra.gui.components.frame.MY_Table getGrp_Table_Achat() {
        return grp_Table_Achat;
    }

    /**
     * Setter for property grp_Table_Achat.
     *
     * @param grp_Table_Achat New value of property grp_Table_Achat.
     */
    public void setGrp_Table_Achat(srcastra.astra.gui.components.frame.MY_Table grp_Table_Achat) {
        this.grp_Table_Achat = grp_Table_Achat;
    }

    /**
     * Getter for property modelfact.
     *
     * @return Value of property modelfact.
     */
    public srcastra.astra.gui.modules.compta.achat.FacturationModel getModelfact() {
        return modelfact;
    }

    /**
     * Setter for property modelfact.
     *
     * @param modelfact New value of property modelfact.
     */
    public void setModelfact(srcastra.astra.gui.modules.compta.achat.FacturationModel modelfact) {
        this.modelfact = modelfact;
    }

    /**
     * Getter for property grp_Table_resume.
     *
     * @return Value of property grp_Table_resume.
     */
    public javax.swing.JTable getGrp_Table_resume() {
        return grp_Table_resume;
    }

    /**
     * Setter for property grp_Table_resume.
     *
     * @param grp_Table_resume New value of property grp_Table_resume.
     */
    public void setGrp_Table_resume(javax.swing.JTable grp_Table_resume) {
        this.grp_Table_resume = grp_Table_resume;
    }

    /**
     * Getter for property grp_Label_prodAchat.
     *
     * @return Value of property grp_Label_prodAchat.
     */
    public javax.swing.JLabel getGrp_Label_prodAchat() {
        return grp_Label_prodAchat;
    }

    /**
     * Setter for property grp_Label_prodAchat.
     *
     * @param grp_Label_prodAchat New value of property grp_Label_prodAchat.
     */
    public void setGrp_Label_prodAchat(javax.swing.JLabel grp_Label_prodAchat) {
        this.grp_Label_prodAchat = grp_Label_prodAchat;
    }

    /**
     * Getter for property grp_Label_prodRent.
     *
     * @return Value of property grp_Label_prodRent.
     */
    public javax.swing.JLabel getGrp_Label_prodRent() {
        return grp_Label_prodRent;
    }

    /**
     * Setter for property grp_Label_prodRent.
     *
     * @param grp_Label_prodRent New value of property grp_Label_prodRent.
     */
    public void setGrp_Label_prodRent(javax.swing.JLabel grp_Label_prodRent) {
        this.grp_Label_prodRent = grp_Label_prodRent;
    }

    /**
     * Getter for property grp_Label_prodVente.
     *
     * @return Value of property grp_Label_prodVente.
     */
    public javax.swing.JLabel getGrp_Label_prodVente() {
        return grp_Label_prodVente;
    }

    /**
     * Setter for property grp_Label_prodVente.
     *
     * @param grp_Label_prodVente New value of property grp_Label_prodVente.
     */
    public void setGrp_Label_prodVente(javax.swing.JLabel grp_Label_prodVente) {
        this.grp_Label_prodVente = grp_Label_prodVente;
    }

    /**
     * Setter for property resumeVente.
     *
     * @param resumeVente New value of property resumeVente.
     */

    public boolean isFromIntegration;
    public long cleunikfact;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AchatPanel;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_Cbox_Frais;
    private srcastra.astra.gui.components.date.thedate.ADate grp_Date_date;
    private srcastra.astra.gui.components.date.thedate.ADate grp_Date_ech;
    private srcastra.astra.gui.components.date.thedate.ADate grp_Date_recept;
    private javax.swing.JLabel grp_Label_Tva;
    private javax.swing.JLabel grp_Label_achat;
    private javax.swing.JLabel grp_Label_base;
    private javax.swing.JLabel grp_Label_dosAchat;
    private javax.swing.JLabel grp_Label_dosRent;
    private javax.swing.JLabel grp_Label_dosVente;
    private javax.swing.JLabel grp_Label_prodAchat;
    private javax.swing.JLabel grp_Label_prodRent;
    private javax.swing.JLabel grp_Label_prodVente;
    private javax.swing.JLabel grp_Label_rent;
    private javax.swing.JLabel grp_Label_vente;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_journal;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_tiers;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_com;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_montanteur;
    private javax.swing.JLabel grp_TField_numpiece;
    private srcastra.astra.gui.components.frame.MY_Table grp_Table_Achat;
    private javax.swing.JTable grp_Table_Tva;
    private javax.swing.JTable grp_Table_resume;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_li_periode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
