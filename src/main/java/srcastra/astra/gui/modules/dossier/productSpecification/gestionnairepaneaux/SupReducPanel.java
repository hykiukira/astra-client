/*















 * SupReducPanel.java















 *















 * Created on 4 octobre 2002, 13:40















 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;
// swing import

import javax.swing.JOptionPane;
// Interfaces
import srcastra.astra.gui.modules.InternScreenModule;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
// srcastra divers
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.components.fx.*;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.gui.test.ProduitTableModel;

import javax.swing.table.*;

import srcastra.astra.gui.test.*;
import srcastra.astra.gui.sys.formVerification.*;

import java.awt.*;

import java.awt.event.*;

/**
 * @author Sébastien
 */
public class SupReducPanel extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer, InterfacePanel {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates new form SupReducPanel
     */
    public SupReducPanel(SupreducGui parent2) {
        m_parent = parent2;
        this.parent = m_parent.getMainScreenModule();
        setSize(708, 157);
        setBounds(0, 208, 708, 157);
        init();
        grp_Table_SupReduc.setFocusable(false);
        //  grp_Table_SupReduc.setSelectionBackground(new java.awt.Color(221,221,255));
        jLabel1.setFocusable(false);
        jPanel2.setFocusable(false);
        jScrollPane1.setFocusable(false);
        //  addKeyListener(this);
        this.m_productID = produit_T.AV;
        //adaptBackgroundColor(false);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INITIALISATION
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void init() {
        initComponents();
        initTable();
        grp_Table_SupReduc.getTableHeader().setReorderingAllowed(false);
        grp_Table_SupReduc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


    }

    public java.awt.Component getTable() {
        return grp_Table_SupReduc;


    }

    private void FieldFocusLost(java.awt.event.FocusEvent evt) {
        int row = grp_Table_SupReduc.getSelectedRow();
        ProduitAffichage affiche = (ProduitAffichage) this.model.getClassAffichage(row);
        this.model.setValueAt(affiche.calculValeurTot(), row, 11);
        this.model.setValueAt(new Float(affiche.getParent().updateFieldsFromTable(affiche.qua.intValue(), affiche.nbrpa.intValue(), affiche.prct.floatValue(), Float.parseFloat(affiche.prixtot.m_data.toString()))), row, 14);
        parent.calculTotalUnProduit(parent.getTicket());
        grp_Table_SupReduc.updateUI();
        if (!parent.getDossier().isNewreccord()) parent.getDossier().setModifreccord(true);


    }

    private void FieldFocusGained(java.awt.event.FocusEvent evt) {
        ((javax.swing.JTextField) evt.getSource()).selectAll();


    }

    private void initTable() {
        /*focusadatper=new java.awt.event.FocusAdapter() {















            public void focusLost(java.awt.event.FocusEvent evt) {















                FieldFocusLost(evt);















            }















            public void focusGained(java.awt.event.FocusEvent evt) {















                FieldFocusGained(evt);















            }















        };*/
        this.model = new ProduitTableModel(parent, parent.getCurrentUser(), m_parent.m_config.typeProduct);
        grp_Table_SupReduc.setAutoCreateColumnsFromModel(false);
        grp_Table_SupReduc.setModel(this.model);
        for (int k = 0; k < model.m_columns.length; k++) {
            DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
            javax.swing.JTextField jtextField = new javax.swing.JTextField();
            if (k == 9) jtextField.setDocument(new DecimalMask(2, 2, parent.getCurrentUser().getLangage()));
            else if (k == 7 || k == 8)
                jtextField.setDocument(new IntegerMask(1, 2, parent.getCurrentUser().getLangage()));
            jtextField.addFocusListener(this.focusadatper);
            TableCellEditor editor = new javax.swing.DefaultCellEditor(jtextField);
            renderer.setHorizontalAlignment(model.m_columns[k].c_alignement);
            TableColumn column = null;
            if (k == 7 || k == 8 || k == 9) column = new TableColumn(k, model.m_columns[k].c_width, renderer, editor);
            else column = new TableColumn(k, model.m_columns[k].c_width, renderer, null);
            grp_Table_SupReduc.addColumn(column);


        }
        JTableHeader header = grp_Table_SupReduc.getTableHeader();
        header.setUpdateTableInRealTime(false);
        // grp_Table_SupReduc.setModel(model);
        if (grp_Table_SupReduc.getRowCount() != 0) ;
        grp_Table_SupReduc.changeSelection(0, 0, false, false);


    }

    public void reloadTableModel() {
        model.loadData();
        grp_Table_SupReduc.tableChanged(new javax.swing.event.TableModelEvent(model));
        grp_Table_SupReduc.repaint();
        if (grp_Table_SupReduc.getRowCount() > 0) grp_Table_SupReduc.changeSelection(0, 0, false, false);
        System.out.println("========>NOMBRE DE LIGNES DANS LE VECTEUR ???? ====>" + model.getRowCount());


    }

    private void changeAction() {
        /* if (grp_Table_SupReduc.getRowCount() > 0) {















            // if (grp_Table_SupReduc.getValueAtgetSelectedRow()















        }















        else {















            parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CREATE,















                                                       ActionToolBar.DO_CANCEL };















        }















        */
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_SupReduc = new javax.swing.JTable();
        setLayout(new java.awt.BorderLayout());
        setBorder(new javax.swing.border.EtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane").getString("sup_title"));
        jPanel2.add(jLabel1);
        add(jPanel2, java.awt.BorderLayout.NORTH);
        grp_Table_SupReduc.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Table_SupReduc.setModel(new DefaultTableModel());
        grp_Table_SupReduc.setRequestFocusEnabled(false);
        grp_Table_SupReduc.setSelectionBackground(new java.awt.Color(204, 204, 255));
        grp_Table_SupReduc.setSelectionForeground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(grp_Table_SupReduc);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);


    }//GEN-END:initComponents

    public void initSupReducEditionPanel() {
        // grp_Pan_edition = new SupReducPanelEdition(parent, this);
        // this.add(grp_Pan_edition);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void fxPanelDiselected() {
        /*   setFocusable(false);















        grp_Table_SupReduc.setEnabled(false);















        adaptBackgroundColor(false);















        parentPane.displaySegmentPanel();*/
    }

    public void fxPanelSelected() {
        takeFocus();
        requestFocus();


    }

    private void takeFocus() {
        /*  setFocusable(true);















    grp_Table_SupReduc.setEnabled(true);















    adaptBackgroundColor(true);















    parentPane.removeSegmentPanel();















    grp_Table_SupReduc.changeSelection(0, 0, false, false);















    //requestFocus();    */
    }

    public void adaptBackgroundColor(boolean enabled) {
        java.awt.Color background = enabled ? java.awt.Color.white : java.awt.Color.lightGray;
        grp_Table_SupReduc.setBackground(background);
        grp_Table_SupReduc.setRowSelectionAllowed(enabled);
        if (enabled && grp_Table_SupReduc.getRowCount() > 0) grp_Table_SupReduc.changeSelection(0, 0, false, false);


    }
    /*private KeyListener upAndDown = new KeyListener() {















        public void keyPressed(KeyEvent evt) {















            int totL = grp_Table_SupReduc.getRowCount();















            int cur = (grp_Table_SupReduc.getSelectedRow() >= 0) ? grp_Table_SupReduc.getSelectedRow() : 0;















            















            if (evt.getKeyCode() == KeyEvent.VK_DOWN && (cur < totL-1)) {















                grp_Table_SupReduc.changeSelection(cur + 1, 0, false, false);















            }















            else if (evt.getKeyCode() == KeyEvent.VK_UP && (cur > 0)) {















                grp_Table_SupReduc.changeSelection(cur -1, 0, false, false);















            }















        }















        public void keyReleased(KeyEvent evt) {















        }















        public void keyTyped(KeyEvent evt) {















        }















    };*/

    public void keyPressed(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        if (grp_Table_SupReduc.getRowCount() > 0) {
            System.out.println("ATENTION MON NOMBRE DE LIGNES TOTALES EST ========> " + grp_Table_SupReduc.getRowCount());
            int cur = grp_Table_SupReduc.getSelectedRow();
            int tot = grp_Table_SupReduc.getRowCount();
            if (key == KeyEvent.VK_DOWN && (cur < tot - 1))
                grp_Table_SupReduc.changeSelection(cur + 1, 0, false, false);
            if (key == KeyEvent.VK_UP && (cur > 0)) grp_Table_SupReduc.changeSelection(cur - 1, 0, false, false);


        }


    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent) {
    }

    public void keyTyped(java.awt.event.KeyEvent keyEvent) {
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void reloadInfo() {
    }

    public void setTableEnabled(boolean enabled) {
        adaptBackgroundColor(enabled);


    }

    public void giveMeTheFocus() {
        //grp_TField_invisible.requestFocus();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permet au parent de lancer le chargement des données au
     * sein de liste (Ici : ListSelector)
     */
    public void chargeData() {
    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
        String rep = srcastra.astra.gui.sys.utils.TableManage.deleteFromTable(this, parent, grp_Table_SupReduc);
        if (rep.equals("B")) {
            // parentPane.getGrp_Pane_produit().showProductPanel(0, 0);
        }


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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
        if (grp_Table_SupReduc.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Aucun suppléments ou réduction dans ce dossier");


        } else {
            action = ActionToolBar.ACT_INSERT;
            if (getSupReduc(ActionToolBar.ACT_READ)) {
                //parent.enabledTabbedPane(false);
            } else displayReadMode();
            // this.removeAll();
            //parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((
            //grp_Pan_edition.setPassager((Passager_T)parent.renvObjectSelectedInTable(grp_Table_passager));
            // Long l=new Long(((ProduitTableModel)grp_Table_SupReduc.getModel()).getClassAffichage(grp_Table_SupReduc.getSelectedRow()).getParentkey());
            // parent.getTicket().getSup_reduc().get(l);
            //parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(getSupReduc());
            //parentPane.displaySupReducEditionPanel(true, ActionToolBar.ACT_READ);
            //this.updateUI();
        }


    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
        //parent.enabledTabbedPane(false);
        action = ActionToolBar.ACT_INSERT;
        //  parentPane.displaySupReducEditionPanel(true, action);
        // grp_Pan_edition.displayInsertMode();
        this.updateUI();


    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
        //parent.enabledTabbedPane(true);
        action = ActionToolBar.ACT_READ;
        reloadInfo();
        //    parentPane.displaySupReducEditionPanel(false, 0);
        /*jScrollPane1.removeAll();















        jScrollPane1.add(grp_Table_SupReduc);















        jScrollPane1.updateUI();*/
        // if (m_productID != InterfaceProduit.AV) {
        for (int i = 0; i < fx_manager.getNumberOfFxPanel(); i++) {
            fx_manager.swithPanel();


        }
        //}
        //else fx_manager.needReselect();
        // null is returned if none of the components in this application has the focus
        //java.awt.Component compFocusOwner = java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        //System.out.println("le composant qui a le focus est :" + ((compFocusOwner != null) ? compFocusOwner.getClass().getName() : "null"));
        // null is returned if none of the windows in this application has the focus
        //java.awt.Window windowFocusOwner = java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
        //System.out.println("la fenetre qui a le focus est :" + ((windowFocusOwner != null) ? windowFocusOwner.getClass().getName() : "null"));
        //java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        //requestFocus();
        //if (fx_manager != null) fx_manager.needReselect();
        //  requestFocus();
        //  parent.requestOwnFocus();
        //fx_manager.swithPanel("supreduc");
        //requestFocus();
    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
        if (grp_Table_SupReduc.getRowCount() == 0) {
            parent.getMessageManager().showMessageDialog(this, "dos_no_reduc_text", "dos_no_reduc", parent.getCurrentUser());

        } else {
            if (getSupReduc(ActionToolBar.ACT_MODIFY)) {
                //parent.enabledTabbedPane(false);
                action = ActionToolBar.ACT_MODIFY;


            } else displayReadMode();


        }


    }

    public boolean getSupReduc(int action) {
        /* ProduitAffichage prod=((ProduitTableModel)grp_Table_SupReduc.getModel()).getClassAffichage(grp_Table_SupReduc.getSelectedRow());















        if (prod != null) {















            if(prod.isReducsup()) {















                 Long l=new Long(prod.getParent().getAt_cleunik());















                 Sup_reduc_T sup = null;















                 switch (m_productID) {















                     case InterfaceProduit.AS :















                         parent.getAssurance().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getAssurance().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.AV :















                         parent.getTicket().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getTicket().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.BA :















                         parent.getBateau().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getBateau().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.BRO :















                         parent.getBrochure().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getBrochure().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.HO :















                         parent.getHotel().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getHotel().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.TAX :















                         parent.getTaxi().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getTaxi().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.TR :















                         parent.getTrain().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getTrain().getSup_reduc().get(l));















                         break;















                     case InterfaceProduit.VO :















                         parent.getVoitureLocation().getSup_reduc().get(l);















                         parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc((Sup_reduc_T)parent.getVoitureLocation().getSup_reduc().get(l));















                         break;















                 }































                 parentPane.displaySupReducEditionPanel(true, action);















                 if (action != ActionToolBar.ACT_READ) parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_CANCEL, ActionToolBar.DO_SWITCH } );















                 this.updateUI();















                 return true;















             }















            else {















                switch (m_productID) {















                    case InterfaceProduit.AS :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getAssurance());















                        break;















                    case InterfaceProduit.AV :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getTicket());















                        break;















                    case InterfaceProduit.BRO :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getBrochure());















                        break;















                    case InterfaceProduit.HO :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getHotel());















                        break;















                    case InterfaceProduit.BA :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getBateau());















                        break;















                    case InterfaceProduit.TR :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getTrain());















                        break;















                    case InterfaceProduit.VO :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getVoitureLocation());















                        break;















                    case InterfaceProduit.TAX :















                        parentPane.getGrp_Pan_SupReducPanelEdition().setSup_reduc(parent.getTaxi());















                        break;















                }































                parentPane.displaySupReducEditionPanel(true, action);















                if (action != ActionToolBar.ACT_READ) parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_CANCEL, ActionToolBar.DO_SWITCH } );















                this.updateUI();































                return true;































            }















        }















        */
        return false;


    }
    /// Méthode pour l'update de tous les champs

    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Spécifie le composant qui implémente cette fonction comme
     * le composant qui pilote l'actionToolBar
     */
    public void setThisAsToolBarComponent() {
    }

    public void doAccept() {
        displayDisableMode();
        /*switch (action) {















                case ActionToolBar.ACT_INSERT :















                    parentPane.getGrp_Pan_SupReducPanelEdition().dbInsert();















                    parentPane.getGrp_Pan_SupReducPanelEdition().displayDisableMode();















                    parentPane.displaySupReducEditionPanel(false, ActionToolBar.ACT_READ);















                     if(!parent.getTicket().isIsnewreccord())















                        parent.getTicket().setModify(true);















                    displayReadMode();















                    if (fx_manager != null) fx_manager.needReselect();















                    break;















                case ActionToolBar.ACT_MODIFY :















                    parentPane.getGrp_Pan_SupReducPanelEdition().dbUpdate();















                    parentPane.displaySupReducEditionPanel(false, ActionToolBar.ACT_READ);















                     if(!parent.getTicket().isIsnewreccord())















                        parent.getTicket().setModify(true);















                    displayReadMode();















                    break;















                default:















                    displayDisableMode();















        }*/
    }

    public void doCancel() {
        /*   switch(action) {















        case ActionToolBar.ACT_INSERT :















                requestFocus();















                parentPane.getGrp_Pan_SupReducPanelEdition().displayDisableMode();















                parentPane.displaySupReducEditionPanel(false, 0);















                requestFocus();















                displayReadMode();















                if (fx_manager != null) fx_manager.needReselect();















                break;















            case ActionToolBar.ACT_READ :















                parentPane.doCancel();















                break;















            case ActionToolBar.ACT_MODIFY :















                requestFocus();















                parentPane.getGrp_Pan_SupReducPanelEdition().displayDisableMode();















                parentPane.displaySupReducEditionPanel(false, 0);















                requestFocus();















                displayReadMode();















                if (fx_manager != null) fx_manager.needReselect();















                break;















                















        }*/
    }

    public void doClose() {
    }

    public void doCreate() {
    }

    public void doDelete() {
        /*  int rep=parent.getMessageManager().showConfirmDialog(parent.getSuperOwner(),"eff_produit_text","eff_produit",parent.getCurrentUser());















        if(rep==0){















           dbDelete();















        }*/
    }

    public void doHelp() {
    }

    public void doModify() {
        displayUpdateMode();

    }

    public void doNext() {
        /*System.out.println("\n\n\n¨******************ISNERECORD?:"+parent.getTicket().isIsnewreccord());















       System.out.println("\n\n\n¨******************ISATTACHED?:"+parent.getTicket().isIsattached());















       System.out.println("\n\n\n¨******************ISMODIFY?:"+parent.getTicket().isModify());















       parentPane.doNext();















       /* if(parent.getTicket().isIsnewreccord()){















            System.out.println("\n\n[okokkokokoInsert]");















                parent.getTicket().setIsattached(true);















                parent.getDossier().addTicket(parent.getTicket());















                parentPane.getGrp_Pane_produit().activeBoutonProduit(false,null);















                parentPane.getGrp_Pane_produit().showProductPanel(0);















        }















        else if(parent.getTicket().isModify()){   















            System.out.println("\n\n[okokkokokoModif]");















                parent.getDossier().addTicket(parent.getTicket());















                parentPane.getGrp_Pane_produit().activeBoutonProduit(false,null);















                parentPane.getGrp_Pane_produit().showProductPanel(0);       















        }















        else















        {















          System.out.println("\n\n[okokkokokoRien]");  















        }*/
    }

    public void doPrevious() {
        /*  parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.WAIT_CURSOR));















     switch (action) {















         case ActionToolBar.ACT_INSERT :















                 requestFocus();















             if (parentPane.getGrp_Pan_SupReducPanelEdition().isGoodForInsertion()) {















                 parentPane.getGrp_Pan_SupReducPanelEdition().dbInsert();















                 parentPane.getGrp_Pan_SupReducPanelEdition().displayDisableMode();















                 parentPane.displaySupReducEditionPanel(false, ActionToolBar.ACT_READ);















                 requestFocus();















                 displayReadMode();















             }















             else {















                 parent.getMessageManager().showMessageDialog(parentPane.getGrp_Pan_SupReducPanelEdition(), "supReducErreurLibele", "supReducErreurLibele_title", parent.getCurrentUser());















                 parentPane.getGrp_Pan_SupReducPanelEdition().selectLibele();















             }































             break;















         case ActionToolBar.ACT_MODIFY :































                requestFocus();















             if (parentPane.getGrp_Pan_SupReducPanelEdition().isGoodForInsertion()) {















                 parentPane.getGrp_Pan_SupReducPanelEdition().dbUpdate();















                 parentPane.displaySupReducEditionPanel(false, ActionToolBar.ACT_READ);















                 requestFocus();















                 displayReadMode();















































             }















             else {















                 parent.getMessageManager().showMessageDialog(parentPane.getGrp_Pan_SupReducPanelEdition(), "supReducErreurLibele", "supReducErreurLibele_title", parent.getCurrentUser());















                 parentPane.getGrp_Pan_SupReducPanelEdition().selectLibele();















             }















             break;















         case ActionToolBar.ACT_READ :















             requestFocus();















             parentPane.validateProduct();















             break;































     }































    parent.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR));   */
    }

    public void doPrint() {
    }

    public srcastra.astra.sys.classetransfert.dossier.InterfaceProduit getSelectedRow() {
        if (grp_Table_SupReduc.getRowCount() > 0) {
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage tmp = model.getClassAffichage(grp_Table_SupReduc.getSelectedRow());
            return tmp.getParent();


        } else return null;


    }

    public void doSwitch() {
        /*  try{















        if (action != ActionToolBar.ACT_READ) {















            doPrevious();















            if (parentPane.getGrp_Pan_SupReducPanelEdition().isGoodForInsertion()) {















                fx_manager.swithPanel();















            }















        }















        else {















            requestFocus();         















           // fx_manager.swithPanel();















           















        }















         }catch(Exception en){















           System.out.println("WAAAAAAAAAAAAAAAAAAAAAAAAAAZZZZZZZZZZZZZZZZZAAAAAAAAAAAAAAAA");















           en.printStackTrace();















        }*/
    }

    public boolean isManagingFocus() {
        return true;


    }

    public int[] getDefaultActionToolBarMask() {
        System.out.println("ééééééééééééééééééééééééééééééhhhh on a demander mes actions !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new int[]{ActionToolBar.DO_ACCEPT, ActionToolBar.DO_MODIFY, ActionToolBar.DO_CREATE, ActionToolBar.DO_CANCEL, ActionToolBar.DO_DELETE, ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_SWITCH};


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SupreducGui m_parent;
    private DossierMainScreenModule parent;
    private int action;
    private int dr_cleUnik;
    private JPanelSelectionFxManager fx_manager;
    //  private SupReducParent parentPane;
    private ProduitTableModel model;
    private java.awt.event.FocusAdapter focusadatper;
    private int m_productID;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  private SupReducPanelEdition grp_Pan_edition;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable grp_Table_SupReduc;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BEANS PROPERTIES GET/SET SUPPORT
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permet à la classe qui implémente cette méthode de se
     * référencer auprès d' ActionToolBar
     *
     * @return le n° de l'action
     */
    public int getAction() {
        return this.action;


    }

    /**
     * Sert à recevoir le titre de son parent
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        return "# Suppléments & Réductions #";


    }

    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * 0 pour lecture
     * 1 pour création
     * 2 pour modification
     *
     * @param action type d'action
     */
    public void setAction(int action) {
        this.action = action;


    }

    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * au modules : création par partie ou modification
     *
     * @param frCleUnik la clé unique
     */
    public void setFrCleunik(int frCleUnik) {
        this.dr_cleUnik = frCleUnik;


    }

    /**
     * Getter for property fx_manager.
     *
     * @return Value of property fx_manager.
     */
    /*   public srcastra.astra.gui.components.fx.JPanelSelectionFxManager getFx_manager() {















    return fx_manager;















}































public void setFx_manager(srcastra.astra.gui.components.fx.JPanelSelectionFxManager fx_manager) {















    this.fx_manager = fx_manager;















}































public void fxPanelSelected(int key) {















     takeFocus();















    // requestFocus();















     ProduitAffichage prod=((ProduitTableModel)grp_Table_SupReduc.getModel()).getClassAffichage(0);















     produit_T prod2=(produit_T)prod.getParent();















     if(prod2.getAt_val_vente()==0)















        doModify();















     else















         requestFocus();















}    */
    public void moveInTable(int direction) {
        if (grp_Table_SupReduc.getRowCount() > 0) {
            System.out.println("ATENTION MON NOMBRE DE LIGNES TOTALES EST ========> " + grp_Table_SupReduc.getRowCount());
            int cur = grp_Table_SupReduc.getSelectedRow();
            int tot = grp_Table_SupReduc.getRowCount();
            if (direction == KeyEvent.VK_DOWN && (cur < tot - 1))
                grp_Table_SupReduc.changeSelection(cur + 1, 0, false, false);
            if (direction == KeyEvent.VK_UP && (cur > 0)) grp_Table_SupReduc.changeSelection(cur - 1, 0, false, false);


        }


    }

    public void goUp() {
    }

    public java.awt.Component m_getGeneriqueTable() {
        return grp_Table_SupReduc;


    }

    public int doAccept(boolean sw) {
        //  displayDisableMode();
        return 1;


    }

    public int doCancel(boolean sw) {
        return 1;


    }

    public int doCreate(boolean sw) {
        displayInsertMode();
        return 1;


    }

    public int doDelete(boolean sw) {
/*    Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMM]Brochure "+parent.getBrochure().getBro_cleUnik()+"  "+parent.getBrochure().getBro_po());



for(java.util.Enumeration enu=parent.getBrochure().getSup_reduc().keys();enu.hasMoreElements();){



  Object w=enu.nextElement();



  Sup_reduc_T tmp=(Sup_reduc_T)parent.getBrochure().getSup_reduc().get(w);



  Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMM]Supreduc  "+tmp.getAt_cleunik()+"  "+tmp.getFreeLibelle()+" hashtablekey"+w);







}*/
        int retval = 1;
        int rep = parent.getMessageManager().showConfirmDialog(parent.getSuperOwner(), "eff_produit_text", "eff_produit", parent.getCurrentUser());
        if (rep == 0) {
            String rep2 = srcastra.astra.gui.sys.utils.TableManage.deleteFromTable(this, parent, grp_Table_SupReduc);
            if (rep2.equals("B")) retval = 0;


        }
        return retval;


    }

    public int doModify(boolean sw) {
        return 1;

    }

    public int doPrevious(boolean sw) {
        return 1;


    }

    public Object getSupReduc2(int i) {
        if (grp_Table_SupReduc.getRowCount() > 0) {
            int row = grp_Table_SupReduc.getSelectedRow();
            if (i == 1) row = 0;
            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage tmp = model.getClassAffichage(row);
            return tmp.getParent();


        } else return null;


    }

    public void setSup_reduc(Object sup_reduc) {
    }

    public void doF10() {
    }

    public void addKeystroque() {
    }

    public void doF7() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        // paint the component as opaque
        setOpaque(true);
        super.paintComponent(g);
        setOpaque(false);
    }
}















