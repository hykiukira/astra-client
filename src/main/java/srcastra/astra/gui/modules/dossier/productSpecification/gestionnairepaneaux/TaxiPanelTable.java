/* * TaxiPanelPassagerTableResume.java * * Created on 9 septembre 2002, 9:00 */package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;import srcastra.astra.gui.modules.mainScreenModule.*;import srcastra.astra.gui.components.fx.*;import javax.swing.*;import javax.swing.table.*;import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;import srcastra.astra.gui.modules.*;import srcastra.astra.gui.components.actions.actionToolBar.*;import srcastra.astra.gui.components.*;import srcastra.astra.sys.classetransfert.dossier.*;import srcastra.astra.gui.event.*;import java.awt.event.*;import srcastra.astra.gui.sys.formVerification.*;/** * * @author  S�bastien */public class TaxiPanelTable extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer,InterfacePanel {//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // CONSTRUCTOR////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             /** Creates new form TaxiPanelPassagerTableResume */    AbstractSousPanel m_parent;    public TaxiPanelTable(AbstractSousPanel parent2) {        m_parent=parent2;        this.parent = m_parent.getMainScreenModule();        init();    }        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // INITIALISATION////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      private void init() {        initComponents();        setBounds(0, 250, 708, 115);      if(!m_parent.m_config.test)            grp_Table_Passager.getTableHeader().setReorderingAllowed(false);            grp_Table_Passager.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);            initTable();      /*  componentToVerif = new AstraComponent[] { grp_CBox_aller, grp_CBox_retour, grp_TField_Passager_Gsm, grp_TField_PassagerAller_HeurePrevue,                                                  grp_TField_PassagerAller_Adresse, grp_LSelect_PassagerAller_Cp, grp_TField_PassagerRetour_HeurePrevue,                                                  grp_TField_PassagerRetour_Adresse, grp_LSelect_PassagerRetour_cp };                 setDocument();             */               }             private void initTable() {        tb_model = new DossierTaxiPassagerTableModel(parent.getServeur(), parent.getCurrentUser(), parent);                //grp_Table_Passager.get                        grp_Table_Passager.setAutoCreateColumnsFromModel(false);        grp_Table_Passager.setModel(tb_model);        for (int i=0; i < tb_model.getColumnCount(); i++) {            TableCellRenderer renderer;            if (i == 0) {                CheckAttach at = new CheckAttach();                at.setHorizontalAlignment(tb_model.m_columns[i].c_alignement);                renderer = at;            }            else if (i == 1 || i == 2) {                CheckDepRet check = new CheckDepRet();                check.setHorizontalAlignment(tb_model.m_columns[i].c_alignement);                renderer = check;            }            else {                DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();                defaultRenderer.setHorizontalAlignment(tb_model.m_columns[i].c_alignement);                renderer = defaultRenderer;            }            TableColumn column = new TableColumn(i, tb_model.m_columns[i].c_width, renderer, new DefaultCellEditor(new JTextField()));            grp_Table_Passager.addColumn(column);        }        JTableHeader header = grp_Table_Passager.getTableHeader();        header.setUpdateTableInRealTime(false);        if(grp_Table_Passager.getRowCount()>0)            grp_Table_Passager.changeSelection(0,0,false,false);    }        /** This method is called from within the constructor to     * initialize the form.     * WARNING: Do NOT modify this code. The content of this method is     * always regenerated by the Form Editor.     */    private void initComponents() {//GEN-BEGIN:initComponents        grp_Pan_listePassager = new javax.swing.JScrollPane();        grp_Table_Passager = new javax.swing.JTable();        setLayout(new java.awt.GridLayout(1, 0));        setBorder(new javax.swing.border.TitledBorder(null, "Passagers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));        grp_Table_Passager.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Table_Passager.setModel(new DefaultTableModel());        grp_Table_Passager.setRequestFocusEnabled(false);        grp_Pan_listePassager.setViewportView(grp_Table_Passager);        add(grp_Pan_listePassager);    }//GEN-END:initComponents   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => LISTENERS////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  /* private ValidateField allerCpToRetourOuValidate = new ValidateField() {     public void actionPerformed(ActionEvent evt) {         if (grp_CBox_retour.isSelected()) grp_TField_PassagerRetour_HeurePrevue.requestFocus();         else doPrevious();     } };  private ValidateField retourCpValidate = new ValidateField() {     public void actionPerformed(ActionEvent evt) {         doPrevious();     } };*/    /*  public void fxPanelDiselected() {        setFocusable(false);        grp_Table_Passager.setEnabled(false);        adaptBackgroundColor(false);            }            public void fxPanelSelected() {        setFocusable(true);        grp_Table_Passager.setEnabled(true);        adaptBackgroundColor(true);        requestFocus();    }            public void keyPressed(java.awt.event.KeyEvent evt) {        int key = evt.getKeyCode();        if (grp_Table_Passager.getRowCount() > 0) {            System.out.println("ATENTION MON NOMBRE DE LIGNES TOTALES EST ========> " + grp_Table_Passager.getRowCount());            int cur = grp_Table_Passager.getSelectedRow();            int tot = grp_Table_Passager.getRowCount();            if (key == KeyEvent.VK_DOWN && (cur < tot -1)) grp_Table_Passager.changeSelection(cur + 1, 0, false, false);              if (key == KeyEvent.VK_UP && (cur >0) ) grp_Table_Passager.changeSelection(cur - 1, 0, false, false);                        }            }        public void keyReleased(java.awt.event.KeyEvent keyEvent) {    }        public void keyTyped(java.awt.event.KeyEvent keyEvent) {    }    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE AU BEANS////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         public void reloadInfo() {        if (parent.getTaxi() != null) tb_model.setTax_cleUnik(parent.getTaxi().getTax_cleUnik());        tb_model.loadData();        grp_Table_Passager.tableChanged(new javax.swing.event.TableModelEvent(tb_model));        grp_Table_Passager.repaint();        if (grp_Table_Passager.getRowCount() > 0) grp_Table_Passager.changeSelection(0, 0, false, false);    }*/    public class CheckDepRet extends JLabel implements TableCellRenderer {        Icon m_checkIcon;        Icon m_uncheckIcon;                public CheckDepRet() {            super();            setOpaque(false);            m_checkIcon = new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/check.gif"));            m_uncheckIcon = new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/no_check.gif"));        }                public java.awt.Component getTableCellRendererComponent(javax.swing.JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {            int val = ((Integer)value).intValue();            if (val == 1) {                setIcon(m_checkIcon);                return this;            }            else {                setIcon(m_uncheckIcon);                return this;            }        }            }        public class CheckAttach extends JLabel implements TableCellRenderer {        Icon m_checkIcon;        Icon m_uncheckIcon;                public CheckAttach() {            super();            setOpaque(false);            m_checkIcon = new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/trombone.gif"));            m_uncheckIcon = null;        }                public java.awt.Component getTableCellRendererComponent(javax.swing.JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {            int val = ((Integer)value).intValue();            if (val == 1) {                setIcon(m_checkIcon);                return this;            }            else {                setIcon(m_uncheckIcon);                return this;            }        }            }        private void adaptBackgroundColor(boolean enabled) {        java.awt.Color background = enabled ? java.awt.Color.white : java.awt.Color.lightGray;        grp_Table_Passager.setBackground(background);        grp_Table_Passager.setRowSelectionAllowed(enabled);        if (enabled && grp_Table_Passager.getRowCount() > 0) grp_Table_Passager.changeSelection(0, 0, false, false);    }    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      /** Permet au parent de lancer le chargement des donn�es au     * sein de liste (Ici : ListSelector)     */    public void chargeData() {    /*    grp_CBox_aller.setSelected((passager.getPassTax_aller() == 1) ? true : false);        grp_CBox_retour.setSelected((passager.getPassTax_retour() == 1) ? true : false);        grp_TField_Passager_Gsm.setText("" + passager.getPassTax_gsm());                grp_TField_PassagerAller_HeurePrevue.setText("" + passager.getPassTax_aller_heure());        grp_TField_PassagerAller_Adresse.setText("" + passager.getPassTax_aller_adresse());        grp_LSelect_PassagerAller_Cp.setCleUnik(passager.getPassTax_aller_codep());        // grp_TField_PassagerAller_Localite                grp_TField_PassagerRetour_HeurePrevue.setText("" + passager.getPassTax_retour_heure());        grp_TField_PassagerRetour_Adresse.setText("" + passager.getPassTax_retour_adresse());        grp_LSelect_PassagerRetour_cp.setCleUnik(passager.getPassTax_retour_cp());        // grp_TField_PassagerRetour_Localite*/    }            /** Demande d'une suppression ou d'une annulation physique au serveur  */    public void dbDelete() {    }        /** Demande d'une insertion au serveur  */    public void dbInsert() {    }        /** Demande de s�lection au serveur  */    public void dbSelect() {    }        /** Demande de s�lection en vue d'une modification au serveur  */    public void dbSelectForUpdate() {    }        /** Demande d'une modification au serveur  */    public void dbUpdate() {     /*  if(!parent.getDossier().isNewreccord())        parent.getDossier().setModifreccord(true);             chargePassagerClass();*/    }        private void chargePassagerClass() {    /*    int aller = (grp_CBox_aller.isSelected()) ? 1 : 0;        int retour = (grp_CBox_retour.isSelected()) ? 1 : 0;        int attach = 0;        if (aller == 1 || retour == 1) attach = 1;        passager.setAttachedToTaxi(attach);        if (attach == 1){                        passager.setPass_taxi(parent.getTaxi().getTax_cleUnik());            parent.getTaxi().addpassager(passager);        }        else{            passager.setPass_taxi(0l);            parent.getTaxi().getPassager().remove(passager);        }                passager.setPassTax_aller(aller);        passager.setPassTax_retour(retour);        passager.setPassTax_gsm(grp_TField_Passager_Gsm.getText());                passager.setPassTax_aller_heure(grp_TField_PassagerAller_HeurePrevue.getText());        passager.setPassTax_aller_adresse(grp_TField_PassagerAller_Adresse.getText());        passager.setPassTax_aller_codep(grp_LSelect_PassagerAller_Cp.getCleUnik());        passager.setPassTax_aller_localite(grp_TField_PassagerAller_Localite.getText());                 passager.setPassTax_retour_heure(grp_TField_PassagerRetour_HeurePrevue.getText());        passager.setPassTax_retour_adresse(grp_TField_PassagerRetour_Adresse.getText());        passager.setPassTax_retour_cp(grp_LSelect_PassagerRetour_cp.getCleUnik());        passager.setPassTax_retour_localite(grp_TField_PassagerRetour_Localite.getText());               passager.setModify(true);             */       }    public boolean chooseLineInTable() {        if (grp_Table_Passager.getRowCount() < 1) return false;        else {            passager = (Passager_T) tb_model.getPassager(grp_Table_Passager.getSelectedRow());            int attach = ((Integer)tb_model.getValueAt(grp_Table_Passager.getSelectedRow(), 0)).intValue();            if (passager != null && attach != 1) return true;            else return false;        }    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE A L' AFFICHAGE DES DONNEES////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      private void chargeName() {  /*      grp_Label_nom.setText("" + passager.getPr_nom() + ", ");        grp_Label_prenom.setText("" + passager.getPr_pr�nom());*/    }              /** Affichage en Mode disable  */    public void displayDisableMode() {            }        private void closeFields() {        for (int i=0; i < componentToVerif.length; i++) {            componentToVerif[i].setEnabled(false);        }    }        /** Affichage en mode Insertion  */    public void displayInsertMode() {    }        /** Affichage en mode Lecture  */    public void displayReadMode() {      /*  action = ActionToolBar.ACT_READ;        parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_MODIFY,                                                    /*ActionToolBar.DO_ACCEPT,                                                   ActionToolBar.DO_PREVIOUS,                                                   ActionToolBar.DO_CANCEL,                                                   ActionToolBar.DO_SWITCH });                                                           requestFocus();   */                                                    }        private void openAllerFields(boolean open) {      /*  grp_TField_PassagerAller_HeurePrevue.setEnabled(open);        grp_TField_PassagerAller_Adresse.setEnabled(open);        grp_LSelect_PassagerAller_Cp.setEnabled(open);*/    }        private void openRetourFields(boolean open) {      /*  grp_TField_PassagerRetour_HeurePrevue.setEnabled(open);        grp_TField_PassagerRetour_Adresse.setEnabled(open);        grp_LSelect_PassagerRetour_cp.setEnabled(open);*/    }        /** Affichage en mode Modification  */    public void displayUpdateMode() {      /*  action = ActionToolBar.ACT_MODIFY;        showEditPanel(true);        if (passager != null && passager.getAttachedToTaxi() == 1) chargeData();        else chargeDefaultValues();        chargeName();        grp_CBox_aller.setEnabled(true);        grp_CBox_retour.setEnabled(true);        grp_TField_Passager_Gsm.setEnabled(true);        if (grp_CBox_aller.isSelected()) openAllerFields(true);        else openAllerFields(false);        if (grp_CBox_retour.isSelected()) openRetourFields(true);        else openRetourFields(false);                parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_PREVIOUS,                                                   ActionToolBar.DO_CANCEL });        grp_CBox_aller.requestFocus();*/    }    /** M�thode pour l'update de tous les champs  */    public void updateAllFields() {    }        public void updateAllFields(Object donnee) {    }        private void showEditPanel(boolean show) {     /*   if (show) {            grp_LPane_layer.moveToFront(grp_Pan_Passager);        }        else grp_LPane_layer.moveToBack(grp_Pan_Passager);*/               }    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE AUX APPELS DE LA TOOLBAR////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      public void doAccept() {    }            public void doCancel() {        switch (action) {            case ActionToolBar.ACT_READ :                break;            case ActionToolBar.ACT_MODIFY :                showEditPanel(false);                displayReadMode();                break;        }                }        public void doClose() {    }        public void doCreate() {    }        public void doDelete() {    }        public void doHelp() {    }        public void doModify() {        boolean choosed = chooseLineInTable();        if (choosed) displayUpdateMode();    }        public void doNext() {    }        public void doPrevious() {     /*   switch (action) {            case ActionToolBar.ACT_MODIFY :                closeFields();                dbUpdate();                showEditPanel(false);                displayReadMode();                break;                                                            case ActionToolBar.ACT_READ :                m_taxiParent.validateProduct();                break;        }*/    }        public void doPrint() {    }        public void doSwitch() {        fx_manager.swithPanel();    }        public int[] getDefaultActionToolBarMask() {        return new int[] { ActionToolBar.DO_MODIFY,                            /*ActionToolBar.DO_ACCEPT,*/                           ActionToolBar.DO_PREVIOUS,                           ActionToolBar.DO_CANCEL,                           ActionToolBar.DO_SWITCH };    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => Champs de la classe////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     private DossierMainScreenModule parent;    private TaxiPanel m_taxiParent;    private JPanelSelectionFxManager fx_manager;    private DossierTaxiPassagerTableModel tb_model;    private int action;    private Passager_T passager;    private ToolBarComposer tb_composer;    private AstraComponent[] componentToVerif;//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // STATIC VARIABLES////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        // => Graphic Component////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       // Variables declaration - do not modify//GEN-BEGIN:variables    private javax.swing.JScrollPane grp_Pan_listePassager;    private javax.swing.JTable grp_Table_Passager;    // End of variables declaration//GEN-END:variables////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // BEANS PROPERTIES GET/SET SUPPORT////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  /** Getter for property fx_manager.     * @return Value of property fx_manager.     */    public JPanelSelectionFxManager getFx_manager() {        return fx_manager;    }        /** Setter for property fx_manager.     * @param fx_manager New value of property fx_manager.     */    public void setFx_manager(JPanelSelectionFxManager fx_manager) {        this.fx_manager = fx_manager;    }      /** Permet � la classe qui impl�mente cette m�thode de se     * r�f�rencer aupr�s d' ActionToolBar     * @return le n� de l'action     */    public int getAction() {        return action;    }        /** Sert � recevoir le titre de son parent     * pour un cadre �ventuel     * @return le titre du panneau     */    public String getTitle() {        return "# Taxi Passager #";    }        /** Permet de pr�ciser le type d'action qu'on est occup� de faire :     * 0 pour lecture     * 1 pour cr�ation     * 2 pour modification     * @param action type d'action     */    public void setAction(int action) {        this.action = action;    }        /** Permet de recevoir la cl� unique d'un objet relatif     * au modules : cr�ation par partie ou modification     * @param frCleUnik la cl� unique     */    public void setFrCleunik(int frCleUnik) {    }        /** Sp�cifie le composant qui impl�mente cette fonction comme     * le composant qui pilote l'actionToolBar     */    public void setThisAsToolBarComponent() {    }        public void fxPanelSelected(int key) {    }            public void goUp() {    }        public java.awt.Component m_getGeneriqueTable() {        return grp_Table_Passager;    }        /** Getter for property grp_Table_Passager.     * @return Value of property grp_Table_Passager.     */    public javax.swing.JTable getGrp_Table_Passager() {        return grp_Table_Passager;    }        /** Setter for property grp_Table_Passager.     * @param grp_Table_Passager New value of property grp_Table_Passager.     */    public void setGrp_Table_Passager(javax.swing.JTable grp_Table_Passager) {        this.grp_Table_Passager = grp_Table_Passager;    }        public int doAccept(boolean sw) {        return 1;    }        public int doCancel(boolean sw) {        return 1;    }        public int doCreate(boolean sw) {return 1;    }        public int doDelete(boolean sw) {        return 1;    }        public int doModify(boolean sw) {        return 1;    }        public int doPrevious(boolean sw) {        return 1;    }        public Object getSupReduc2(int i) {        if(grp_Table_Passager.getRowCount()>0)        {            return tb_model.getPassager(grp_Table_Passager.getSelectedRow());        }        return null;    }        public java.awt.Component getTable() {        return null;    }        public void moveInTable(int direction) {    }        public void reloadTableModel() {    }        public void setSup_reduc(Object sup_reduc) {    }        public void doF10() {    }    public void addKeystroque() {    }        public void doF7() {    }        }