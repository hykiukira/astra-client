/* * liste.java * * Created on 7 novembre 2002, 9:41 */package srcastra.astra.gui.components.combobox.liste2;import java.awt.Dimension;import javax.swing.*;import java.awt.Dimension;import java.awt.Font;import java.awt.Color;import javax.swing.border.LineBorder;//import java.awt.Component;import java.awt.FlowLayout;import java.awt.GridBagLayout;import java.awt.GridBagConstraints;import javax.swing.border.EmptyBorder;import srcastra.astra.sys.rmi.*;import java.util.*;import srcastra.astra.gui.test.*;import srcastra.astra.sys.classetransfert.*;import javax.swing.table.*;import srcastra.astra.gui.sys.formVerification.*;import srcastra.astra.gui.sys.utils.*;import java.awt.event.*;import javax.swing.event.EventListenerList;import srcastra.astra.gui.event.ValidateField;import java.awt.event.ActionListener;/** * @author Thomas */public class Liste2 extends javax.swing.JPanel implements srcastra.astra.sys.manipuleclient.ClientConstante, java.io.Serializable, srcastra.astra.gui.components.AstraComponent {    /**     * Creates new form liste     */    Mediator mediator;    int col = 6;    int typeprod;    public Liste2() {        m_listenerList = new EventListenerList();        //  setFocusable(true);        //setFocusCycleRoot(true);        initComponents();        // this.serveur=serveur;        //this.array=array;        //this.login=login;        // dim=new Dimension(150,18);        // this.setSize(dim);        //this.setPreferredSize(dim);        postinit();        grp_PopMenu_popup.setFocusable(false);        grp_PopMenu_popup.setName("thispopup");        grp_Table_table.setFocusable(false);        grp_Table_table.setName("thistable");        grp_ScrollPan_popupScrollPane.setFocusable(false);        grp_ScrollPan_popupScrollPane.setName("thisscrollpane");        grp_ScrollPan_popupScrollPane.getVerticalScrollBar().setName("thisvertical");        grp_ScrollPan_popupScrollPane.getHorizontalScrollBar().setName("thishorizontal");        grp_TField_encode.setName("thisfield");        grp_TField_encode.setHorizontalAlignment(JTextField.LEFT);        grp_Label_warning.setFocusable(false);        super.setFocusable(false);        /*   grp_PopMenu_popup.setFocusable(false);        grp_ScrollPan_popupScrollPane.setFocusable(false);        grp_Table_table.setFocusable(false);        grp_Label_warning.setFocusable(false);        this.setFocusable(false);*/        //  init2();        correctInput = false;        focusFromTable = false;        m_validateList = new EventListenerList();    }    protected void fireValidFieldListeners() {        Object[] listeners = m_validateList.getListenerList();        for (int i = 0; i < listeners.length; i++) {            System.out.println("[fireValidFieldListeners] listeners : " + listeners[i].getClass());            if (ValidateField.class.isAssignableFrom(listeners[i].getClass())) {                if (m_actionEvent == null) {                    m_actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "validate");                }                ((ValidateField) listeners[i]).actionPerformed(m_actionEvent);            }        }    }    public void clearIcon() {    }    public void init2() {        initTable();        Locale locale = new Locale("fr", "be");        // if(model instanceof srcastra.astra.gui.test.ClientTableModel)        //{        //  m_listemask=new srcastra.astra.gui.sys.formVerification.ListeMask(this.serveur,0,20,locale,0,model,this,this.getNbr_lettre_avant_affichage());        // }else{        m_listemask = new ListeMask2(this.serveur, 0, 50, locale, 0, model, this, this.getNbr_lettre_avant_affichage());        //}        grp_TField_encode.setDocument(m_listemask);    }    public void setColToSearch(int col) {        m_listemask.setColToSearch(col);    }    public static void main(String[] args) {        /*   System.out.println("ouverture");        javax.swing.JFrame frame=new javax.swing.JFrame();        frame.setSize(800,600);        frame.getContentPane().add(new liste());        frame.show();        System.out.println("reduc du panel");       // this.setSize(dim);      //  new javax.swing.JFrame().getContentPane().add(new liste()).show();        System.out.println("fermeture");*/    }    public void addValidateFieldListener(ValidateField listener) {        m_validateList.add(ValidateField.class, listener);    }    public void removeValidateFieldListener(ValidateField listener) {        m_validateList.remove(ValidateField.class, listener);    }    public void setEnabled(boolean enabled) {        super.setEnabled(enabled);        grp_TField_encode.setEnabled(enabled);        grp_Label_warning.setEnabled(enabled);        adaptBackgroundColor(enabled);        grp_TField_encode.setFocusable(enabled);    }    private void adaptBackgroundColor(boolean enabled) {        Color background = enabled ? Color.white : Color.lightGray;        grp_TField_encode.setBackground(background);    }    public void requestFocus() {        grp_TField_encode.requestFocus();    }    private void initTable() {        grp_Table_table.setAutoCreateColumnsFromModel(false);        grp_Table_table.setSelectionBackground(new java.awt.Color(221, 221, 255));        grp_Table_table.setModel(model);        for (int k = 0; k < model.getColumnCount(); k++) {            // DefaultTableCellRenderer renderer2=new DefaultTableCellRenderer(jcheck;            // TableCellEditor editor=new javax.swing.DefaultCellEditor(jtextField);            DefaultTableCellRenderer renderer = new ColoredListRenderer();            renderer.setHorizontalAlignment(model.getM_column()[k].c_alignement);            TableColumn column;            column = new TableColumn(k, model.getM_column()[k].c_width, renderer, null);            grp_Table_table.addColumn(column);        }        JTableHeader header = grp_Table_table.getTableHeader();        header.setUpdateTableInRealTime(true);        //header.addMouseListener(model.new ColumnListener(grp_Table_table));        // header.setReorderingAllowed(false);        // model.sortByColonne(0,grp_Table_table);        //if(grp_Table_table==null){        //  grp_Table_table=new JTable();        // }        if (grp_Table_table.getRowCount() > 0) {            grp_Table_table.changeSelection(0, 0, false, false);        }        grp_Table_table.addMouseListener(new java.awt.event.MouseAdapter() {            public void mouseClicked(java.awt.event.MouseEvent evt) {                jTable1MouseClicked(evt);            }        });    }    private void postinit() {        grp_PopMenu_popup = new JPopupMenu();        ;        grp_Table_table = new JTable();        grp_ScrollPan_popupScrollPane = new JScrollPane();        grp_Table_table.setPreferredScrollableViewportSize(new Dimension(300, 200));        //grp_Table_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        //grp_Table_table.setRequestFocusEnabled(false);        //grp_Table_table.setBorder(new EmptyBorder(1,0,0,0));        //javax.swing.table.DefaultTableCellRenderer cell = new javax.swing.table.DefaultTableCellRenderer();        //cell.setBorder (new javax.swing.border.EmptyBorder(1,1,1,1));        //cell.setOpaque(false);        //grp_Table_table.getTableHeader().setDefaultRenderer(cell);        /* Scroll Pane */        grp_ScrollPan_popupScrollPane.setPreferredSize(new Dimension(500, 200));        grp_ScrollPan_popupScrollPane.setViewportView(grp_Table_table);        grp_ScrollPan_popupScrollPane.setAutoscrolls(true);        grp_ScrollPan_popupScrollPane.setBorder(new EmptyBorder(2, 0, 0, 0));        grp_ScrollPan_popupScrollPane.setRequestFocusEnabled(false);        // grp_ScrollPan_popupScrollPane.add(grp_Table_table);        /* Popup Menu */        GridBagLayout gridbag = new GridBagLayout();        grp_PopMenu_popup.setLayout(gridbag);        GridBagConstraints c = new GridBagConstraints();        c.gridx = 0;        c.anchor = GridBagConstraints.WEST;        c = new GridBagConstraints();        c.gridx = 0;        c.insets = new java.awt.Insets(5, 0, 0, 0);        // grp_PopMenu_popup.setBackground(Color.blue);        grp_PopMenu_popup.add(grp_ScrollPan_popupScrollPane, c);        grp_TField_encode.addActionListener(actionListener);        //init2();        new srcastra.astra.gui.components.fx.JComponentBorderFx(grp_TField_encode);        grp_Table_table.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 10));        //      grp_PopMenu_popup.setFocusable(false);        //    grp_ScrollPan_popupScrollPane.setFocusable(false);        //  grp_Label_warning.setFocusable(false);        // this.setFocusable(false);    }    private void changeSise(int width, int heigth) {        //dim.setSize(width,heigth);    }    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {        if (evt.getClickCount() == 2) {            int row = grp_Table_table.getSelectedRow();            setInformation(row);            grp_TField_encode.requestFocus();        }    }    /**     * This method is called from within the constructor to     * initialize the form.     * WARNING: Do NOT modify this code. The content of this method is     * always regenerated by the Form Editor.     */    private void initComponents() {//GEN-BEGIN:initComponents        jPopupMenu1 = new javax.swing.JPopupMenu();        grp_TField_encode = new javax.swing.JTextField();        grp_Label_warning = new javax.swing.JLabel();        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));        setBackground(new java.awt.Color(204, 204, 204));        setPreferredSize(new java.awt.Dimension(150, 18));        setOpaque(false);        grp_TField_encode.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_TField_encode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));        grp_TField_encode.setDisabledTextColor(new java.awt.Color(0, 0, 0));        grp_TField_encode.setPreferredSize(new java.awt.Dimension(132, 18));        grp_TField_encode.addFocusListener(new java.awt.event.FocusAdapter() {            public void focusGained(java.awt.event.FocusEvent evt) {                grp_TField_encodeFocusGained(evt);            }            public void focusLost(java.awt.event.FocusEvent evt) {                grp_TField_encodeFocusLost(evt);            }        });        grp_TField_encode.addKeyListener(new java.awt.event.KeyAdapter() {            public void keyPressed(java.awt.event.KeyEvent evt) {                grp_TField_encodeKeyPressed(evt);            }            public void keyReleased(java.awt.event.KeyEvent evt) {                grp_TField_encodeKeyReleased(evt);            }            public void keyTyped(java.awt.event.KeyEvent evt) {                grp_TField_encodeKeyTyped(evt);            }        });        add(grp_TField_encode);        grp_Label_warning.setMaximumSize(new java.awt.Dimension(20, 20));        grp_Label_warning.setPreferredSize(new java.awt.Dimension(18, 18));        add(grp_Label_warning);    }//GEN-END:initComponents    private void manageMenu() {        if (grp_PopMenu_popup.isVisible()) {            Object[] tmp = (Object[]) m_listemask.getObject(grp_Table_table.getSelectedRow());            this.position = grp_Table_table.getSelectedRow();            System.out.println("position" + position);            grp_TField_encode.setText(tmp[1].toString());            int cle = 0;            cle = ((Integer) tmp[0]).intValue();            setCleUnik2(cle);            grp_PopMenu_popup.setVisible(false);            if (!isLastComponent) ;            // grp_Comp_nextComponent.requestFocus();        } else {        }        grp_Label_warning.setIcon(null);    }    private void grp_TField_encodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_encodeFocusLost        if (!evt.isTemporary()) {            // Component tevt=evt.getOppositeComponent();            // System.out.println("tevt"+tevt);            if (evt.getOppositeComponent() != null) if (evt.getOppositeComponent().getName() != null) {                if (evt.getOppositeComponent().getName().equals("thispopup") || evt.getOppositeComponent().getName().equals("thistable") || evt.getOppositeComponent().getName().equals("thisscrollpane") || evt.getOppositeComponent().getName().equals("thishorizontal") || !evt.getOppositeComponent().getName().equals("thisvertical") || !evt.getOppositeComponent().getName().equals("thisfield"))                    return;            }            manageMenu();        } else System.out.println("temporarylost");        //grp_Label_warning.setIcon(null);        // Add your handling code here:    }//GEN-LAST:event_grp_TField_encodeFocusLost    private void grp_TField_encodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_encodeFocusGained        if (workingIcon != null) grp_Label_warning.setIcon(this.workingIcon);        if (!grp_PopMenu_popup.isVisible()) grp_TField_encode.selectAll();        //}        //else        //  setFocusFromTable(false);// Add your handling code here:    }//GEN-LAST:event_grp_TField_encodeFocusGained    private void grp_TField_encodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_encodeKeyReleased        /*  System.out.println("tfield x ;"+grp_TField_encode.getX()+"popupBorder :"+grp_PopMenu_popup.getX());int x=grp_PopMenu_popup.getBorder().getBorderInsets(grp_PopMenu_popup).right-grp_TField_encode.getX();grp_PopMenu_popup.show(this, (grp_TField_encode.getX()+grp_TField_encode.getWidth())-grp_PopMenu_popup.getWidth(),                                      grp_TField_encode.getY()+18);// model.loadData(this.array);grp_Table_table.repaint();grp_PopMenu_popup.repaint();grp_TField_encode.requestFocus();position=model.searchAWord(grp_TField_encode.getText(),0);if(position>=0 && position<grp_Table_table.getRowCount())  grp_Table_table.setRowSelectionInterval(position,position);System.out.println("position dans la table :"+position);// Add your handling code here:*/    }//GEN-LAST:event_grp_TField_encodeKeyReleased    private void grp_TField_encodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_encodeKeyTyped    }//GEN-LAST:event_grp_TField_encodeKeyTyped    private void grp_TField_encodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_TField_encodeKeyPressed        srcastra.astra.gui.components.combobox.liste.MangeSpecialKey.closePopup(evt, getGrp_PopMenu_popup());        if (evt.getKeyCode() == evt.VK_ENTER) {            setInformation(grp_Table_table.getSelectedRow());            System.out.println("Enter performed");        } else if (evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN) {            System.out.println("Fleche performed");            if (grp_PopMenu_popup.isVisible()) TableManage.scrollIntoTable(evt.getKeyCode(), grp_Table_table);            else {                m_listemask.affichePopup(true);                System.out.println("position" + position);                grp_Table_table.changeSelection(position, 0, false, false);                grp_TField_encode.requestFocus();            }        } else if (evt.getKeyCode() == evt.VK_F2) {            System.out.println("F2performed");            if (mediator != null) mediator.listeAction(evt.getKeyCode(), this.getName());            // m_listemask.setValidate(true);            /*        // grp_PopMenu_popup.setVisible(false);                        if(interfaceManipulePanel!=null)                            interfaceManipulePanel.getSignaletique(this.clesignalitique);                       // interfaceManipulePanel.openPannel(1);                        System.out.println("ok j'ouvre le panneaux");            */        }    }//GEN-LAST:event_grp_TField_encodeKeyPressed    public void nextComp() {        if (!isLastComponent) {            grp_Comp_nextComponent.setEnabled(true);            grp_Comp_nextComponent.requestFocus();        }        fireValidFieldListeners();    }    private void setInformation(int position) {        if (grp_TField_encode.getText().equals("") && !grp_PopMenu_popup.isVisible()) {            if (!canbenull) grp_Label_warning.setIcon(this.warningIcon);            else {                grp_Label_warning.setIcon(null);                if (!isLastComponent) {                    grp_Comp_nextComponent.setEnabled(true);                    grp_Comp_nextComponent.requestFocus();                }                setCleUnik2(0);                fireValidFieldListeners();            }        } else {            if (grp_PopMenu_popup.isVisible()) {                Object[] tmp = (Object[]) m_listemask.getObject(grp_Table_table.getSelectedRow());                if (tmp != null)                    if (tmp[5] != null) if (tmp[5] instanceof Integer) typeprod = ((Integer) tmp[5]).intValue();                this.position = grp_Table_table.getSelectedRow();                System.out.println("position" + position);                //   modedisplay=true;                grp_TField_encode.setText(tmp[col].toString());                // modedisplay=false;                int cle = 0;                if (tmp[0] instanceof Long) cle = ((Long) tmp[0]).intValue();                else cle = ((Integer) tmp[0]).intValue();                setCleUnik2(cle);                //  if(grp_PopMenu_popup.isVisible()){                grp_PopMenu_popup.setVisible(false);                grp_Label_warning.setIcon(null);                if (!isLastComponent) {                    grp_Comp_nextComponent.setEnabled(true);                    grp_Comp_nextComponent.requestFocus();                } else {                    fireValidFieldListeners();                }            } else {                if (!isLastComponent) {                    grp_Comp_nextComponent.setEnabled(true);                    grp_Comp_nextComponent.requestFocus();                } else {                    //  if(interfaceManipulePanel!=null)                    // interfaceManipulePanel.closePanne();                    fireValidFieldListeners();                }            }        }        /*  if(position!=-1)      m_listemask.setPosition(position);  transfert=(InterfaceClasseTransfert)m_listemask.getObject();  if(transfert!=null && grp_PopMenu_popup.isVisible()){      this.cleunik=((Integer)transfert.getCleunik()).intValue();      this.intitule=transfert.getLibele().toString();      m_listemask.setValidate(true);      grp_TField_encode.setText(""+this.intitule);      setCorrectInput(true);      if(!isLastComponent){         next();      }      else{          if(readModePanel){            passValueToParent();            interfaceManipulePanel.setReadModeToPanel();            System.out.println("ok panneaux en read mode");          }      }  }  else{          if(!correctInput){              if(warningIcon!=null)                grp_Label_warning.setIcon(warningIcon);          }          else{              if(!isLastComponent){                 next();          }          else{              if(readModePanel){                passValueToParent();                interfaceManipulePanel.setReadModeToPanel();                System.out.println("ok panneaux en read mode");              }          }        }  }      */    }    private void next() {        passValueToParent();        fillnextComponent();        grp_Comp_nextComponent.setEnabled(true);        grp_Comp_nextComponent.requestFocus();    }    /**     * Getter for property grp_Table_table.     *     * @return Value of property grp_Table_table.     */    public Icon getWarningIcon() {        return warningIcon;    }    /**     * Setter for property warningIcon.     *     * @param warningIcon New value of property warningIcon.     */    public void setWarningIcon(Icon warningIcon) {        this.warningIcon = warningIcon;    }    /**     * Getter for property workingIcon.     *     * @return Value of property workingIcon.     */    public Icon getWorkingIcon() {        return workingIcon;    }    /**     * Setter for property workingIcon.     *     * @param workingIcon New value of property workingIcon.     */    public void setWorkingIcon(Icon workingIcon) {        this.workingIcon = workingIcon;    }    public javax.swing.JTable getGrp_Table_table() {        return grp_Table_table;    }    /**     * Getter for property grp_PopMenu_popup.     *     * @return Value of property grp_PopMenu_popup.     */    public javax.swing.JPopupMenu getGrp_PopMenu_popup() {        return grp_PopMenu_popup;    }    /**     * Setter for property grp_PopMenu_popup.     *     * @param grp_PopMenu_popup New value of property grp_PopMenu_popup.     */    public void setGrp_PopMenu_popup(javax.swing.JPopupMenu grp_PopMenu_popup) {        this.grp_PopMenu_popup = grp_PopMenu_popup;    }    /**     * Getter for property grp_TField_encode.     *     * @return Value of property grp_TField_encode.     */    public javax.swing.JTextField getGrp_TField_encode() {        return grp_TField_encode;    }    /**     * Setter for property grp_TField_encode.     *     * @param grp_TField_encode New value of property grp_TField_encode.     */    public void setGrp_TField_encode(javax.swing.JTextField grp_TField_encode) {        this.grp_TField_encode = grp_TField_encode;    }    public Loginusers_T getLogin() {        return this.login;    }    /**     * Getter for property nbr_lettre_avant_affichage.     *     * @return Value of property nbr_lettre_avant_affichage.     */    public int getNbr_lettre_avant_affichage() {        return nbr_lettre_avant_affichage;    }    /**     * Setter for property nbr_lettre_avant_affichage.     *     * @param nbr_lettre_avant_affichage New value of property nbr_lettre_avant_affichage.     */    public void setNbr_lettre_avant_affichage(int nbr_lettre_avant_affichage) {        this.nbr_lettre_avant_affichage = nbr_lettre_avant_affichage;    }    /**     * Getter for property affiche_panel.     *     * @return Value of property affiche_panel.     */    public boolean isAffiche_panel() {        return affiche_panel;    }    /**     * Setter for property affiche_panel.     *     * @param affiche_panel New value of property affiche_panel.     */    public void setAffiche_panel(boolean affiche_panel) {        this.affiche_panel = affiche_panel;    }    /**     * Setter for property grp_Comp_nextComponent.     *     * @param grp_Comp_nextComponent New value of property grp_Comp_nextComponent.     */    public void setGrp_Comp_nextComponent(JComponent grp_Comp_nextComponent) {        this.grp_Comp_nextComponent = grp_Comp_nextComponent;    }    /**     * Getter for property grp_Comp_nextComponent.     *     * @return Value of property grp_Comp_nextComponent.     */    public JComponent getGrp_Comp_nextComponent() {        return grp_Comp_nextComponent;    }    public void setMaximumSize(Dimension dim) {        super.setMaximumSize(dim);        int width = dim.width;        int height = dim.height;        grp_TField_encode.setMaximumSize(new Dimension(width - (height), height));        //    grp_But_Button.setMaximumSize(new Dimension(height, height));        grp_Label_warning.setMaximumSize(new Dimension(height, height));    }    public void setMinimumSize(Dimension dim) {        super.setMinimumSize(dim);        int width = dim.width;        int height = dim.height;        grp_TField_encode.setMinimumSize(new Dimension(width - (height), height));        //   grp_But_Button.setMinimumSize(new Dimension(height, height));        grp_Label_warning.setMinimumSize(new Dimension(height, height));    }    public void setPreferredSize(Dimension dim) {        super.setPreferredSize(dim);        int width = dim.width;        int height = dim.height;        grp_TField_encode.setPreferredSize(new Dimension(width - (height), height));        // grp_But_Button.setPreferredSize(new Dimension(height, height));        grp_Label_warning.setPreferredSize(new Dimension(height, height));    }    /**     * Getter for property serveur.     *     * @return Value of property serveur.     */    public srcastra.astra.sys.rmi.astrainterface getServeur() {        return serveur;    }    /**     * Setter for property serveur.     *     * @param serveur New value of property serveur.     */    public void setServeur(srcastra.astra.sys.rmi.astrainterface serveur) {        this.serveur = serveur;    }    /**     * Getter for property array.     *     * @return Value of property array.     */    public java.util.ArrayList getArray() {        return array;    }    /**     * Setter for property array.     *     * @param array New value of property array.     */    public void setArray(java.util.ArrayList array) {        this.array = array;    }    /**     * Setter for property login.     *     * @param login New value of property login.     */    public void setLogin(srcastra.astra.sys.classetransfert.Loginusers_T login) {        this.login = login;    }    /**     * Getter for property cleUnik.     *     * @return Value of property cleUnik.     */    public int getCleUnik() {        return this.cleunik;    }    public String getText2() {        //return this.intitule;        return grp_TField_encode.getText();    }    public String getText() {        //return this.intitule;        return grp_TField_encode.getText();    }    /**     * Setter for property cleUnik.     *     * @param cleUnik New value of property cleUnik.     */    public void setCleUnik2(int cleUnik) {        this.cleunik = cleUnik;    }    public void setCleUnik(int cleUnik) {        if (!modedisplay) {            this.cleunik = cleUnik;            if (cleunik != 0) position = m_listemask.searchByKey(cleUnik);        } else {            setCleUnik2(cleUnik);        }    }    public void setText(String text) {        m_listemask.setValidate(true);        // if(modedisplay)        // m_listemask.setFirst(true);        grp_TField_encode.setText(text);        grp_TField_encode.moveCaretPosition(0);     //   grp_TField_encode.setHorizontalAlignment(JTextField.LEFT);        this.intitule = text;        correctInput = true;    }    public void setGoodIcon(boolean sw) {        if (sw) grp_Label_warning.setIcon(workingIcon);        else grp_Label_warning.setIcon(warningIcon);    }    /**     * Getter for property readModePanel.     *     * @return Value of property readModePanel.     */    public boolean isReadModePanel() {        return readModePanel;    }    /**     * Setter for property readModePanel.     *     * @param readModePanel New value of property readModePanel.     */    public void setReadModePanel(boolean readModePanel) {        this.readModePanel = readModePanel;    }    /** Getter for property interfaceManipulePanel.     * @return Value of property interfaceManipulePanel.     */    /**     * Setter for property interfaceManipulePanel.     *     * @param interfaceManipulePanel New value of property interfaceManipulePanel.     *                               /** Getter for property isLastComponent.     * @return Value of property isLastComponent.     */    public boolean isIsLastComponent() {        return isLastComponent;    }    /**     * Setter for property isLastComponent.     *     * @param isLastComponent New value of property isLastComponent.     */    public void setIsLastComponent(boolean isLastComponent) {        this.isLastComponent = isLastComponent;    }    /**     * Getter for property transfert.     *     * @return Value of property transfert.     */    public srcastra.astra.sys.classetransfert.InterfaceClasseTransfert getTransfert() {        return transfert;    }    /**     * Setter for property transfert.     *     * @param transfert New value of property transfert.     */    public void setTransfert(srcastra.astra.sys.classetransfert.InterfaceClasseTransfert transfert) {        this.transfert = transfert;    }    /**     * Getter for property correctInput.     *     * @return Value of property correctInput.     */    public boolean isCorrectInput() {        return correctInput;    }    /**     * Setter for property correctInput.     *     * @param correctInput New value of property correctInput.     */    public void setCorrectInput(boolean correctInput) {        this.correctInput = correctInput;    }    /**     * Getter for property focusFromTable.     *     * @return Value of property focusFromTable.     */    public boolean isFocusFromTable() {        return focusFromTable;    }    /**     * Setter for property focusFromTable.     *     * @param focusFromTable New value of property focusFromTable.     */    public void setFocusFromTable(boolean focusFromTable) {        this.focusFromTable = focusFromTable;    }    public boolean getverif() {        if (canbenull) return true;        else if (!canbenull) {            return !grp_TField_encode.getText().equals("");        }        return true;    }    public boolean isLastFocusedComponent() {        return false;    }    public void removeActionListener(ActionListener listener) {    }    public void setLastFocusedComponent(boolean lastFocusedComponent) {    }    /**     * Getter for property parentFrame.     *     * @return Value of property parentFrame.     */    public java.awt.Frame getParentFrame() {        return parentFrame;    }    /**     * Setter for property parentFrame.     *     * @param parentFrame New value of property parentFrame.     */    public void setParentFrame(java.awt.Frame parentFrame) {        this.parentFrame = parentFrame;    }    private void fillnextComponent() {        if (filNexComponent) {            Liste2 nextc = (Liste2) grp_Comp_nextComponent;            nextc.setCleUnik2(this.cleunik);            nextc.setText(this.intitule);            nextc.setTransfert(this.transfert);        }    }    private void passValueToParent() {        //  if(passvalueToParent)        //    interfaceManipulePanel.fillValueFromComponent(this.transfert,this.getName());    }    /**     * Getter for property passvalueToParent.     *     * @return Value of property passvalueToParent.     */    public boolean isPassvalueToParent() {        return passvalueToParent;    }    /**     * Setter for property passvalueToParent.     *     * @param passvalueToParent New value of property passvalueToParent.     */    public void setPassvalueToParent(boolean passvalueToParent) {        this.passvalueToParent = passvalueToParent;    }    /**     * Getter for property filNexComponent.     *     * @return Value of property filNexComponent.     */    public boolean isFilNexComponent() {        return filNexComponent;    }    /**     * Setter for property filNexComponent.     *     * @param filNexComponent New value of property filNexComponent.     */    public void setFilNexComponent(boolean filNexComponent) {        this.filNexComponent = filNexComponent;    }    /**     * Getter for property model.     *     * @return Value of property model.     */    public ListeModelInterface getModel() {        return model;    }    /**     * Setter for property model.     *     * @param model New value of property model.     */    public void setModel(ListeModelInterface model) {        this.model = model;    }    /**     * Getter for property canbenull.     *     * @return Value of property canbenull.     */    public boolean isCanbenull() {        return canbenull;    }    /**     * Setter for property canbenull.     *     * @param canbenull New value of property canbenull.     */    public void setCanbenull(boolean canbenull) {        this.canbenull = canbenull;    }    /**     * Getter for property left.     *     * @return Value of property left.     */    public boolean isLeft() {        return left;    }    /**     * Setter for property left.     *     * @param left New value of property left.     */    public void setLeft(boolean left) {        this.left = left;    }    public void addActionListener(ActionListener listener) {        m_listenerList.add(ActionListener.class, listener);    }    protected ActionListener actionListener = new ActionListener() {        public void actionPerformed(ActionEvent evt) {            fireStateChanged(true);            System.out.println("Action Performed");        }    };    protected void fireStateChanged(boolean validate) {        Object[] listeners = m_listenerList.getListenerList();        for (int i = 0; i < listeners.length; i++) {            //  System.out.println("[FIRESTATECHANGED] listeners : " + listeners[i].getClass());            if (ValidateField.class.isAssignableFrom(listeners[i].getClass())) {                if (!validate) return;                if (m_actionEvent == null) {                    m_actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "validate");                }                ((ValidateField) listeners[i]).actionPerformed(m_actionEvent);            } else if (ActionListener.class.isAssignableFrom(listeners[i].getClass())) {                //   System.out.println("[FIRESTATECHANGED] pass�");                if (m_actionEvent == null) {                    m_actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "validate");                }                ((ActionListener) listeners[i]).actionPerformed(m_actionEvent);            }        }    }    /**     * Getter for property bgcolor.     *     * @return Value of property bgcolor.     */    public java.awt.Color getBgcolor() {        return bgcolor;    }    /*  public void setPreferredSize(Dimension dim) {       super.setPreferredSize(dim);              int width = dim.width;       int height = dim.height;              grp_TField_encode.setPreferredSize(new Dimension(width - height, height));      // grp_But_Button.setPreferredSize(new Dimension(height, height));       grp_Label_warning.setPreferredSize(new Dimension(height, height));   }    */    /**     * Setter for property bgcolor.     *     * @param bgcolor New value of property bgcolor.     */    public void setBgcolor(java.awt.Color bgcolor) {        this.bgcolor = bgcolor;        super.setBackground(bgcolor);        grp_Label_warning.setBackground(bgcolor);    }    /**     * Getter for property up.     *     * @return Value of property up.     */    public boolean isUp() {        return up;    }    /**     * Setter for property up.     *     * @param up New value of property up.     */    public void setUp(boolean up) {        this.up = up;    }    /**     * Setter for property interfaceManipulePanel.     *     * @param interfaceManipulePanel New value of property interfaceManipulePanel.     */    public void setInterfaceManipulePanel(srcastra.astra.gui.MainFrame interfaceManipulePanel) {        this.interfaceManipulePanel = interfaceManipulePanel;    }    /**     * Getter for property clesignalitique.     *     * @return Value of property clesignalitique.     */    public int getClesignalitique() {        return clesignalitique;    }    /**     * Setter for property clesignalitique.     *     * @param clesignalitique New value of property clesignalitique.     */    public void setClesignalitique(int clesignalitique) {        this.clesignalitique = clesignalitique;    }    /**     * Getter for property modedisplay.     *     * @return Value of property modedisplay.     */    public boolean isModedisplay() {        return modedisplay;    }    /**     * Setter for property modedisplay.     *     * @param modedisplay New value of property modedisplay.     */    public void setModedisplay(boolean modedisplay) {        this.modedisplay = modedisplay;    }    /**     * Getter for property mediator.     *     * @return Value of property mediator.     */    public srcastra.astra.gui.components.combobox.liste2.Mediator getMediator() {        return mediator;    }    /**     * Setter for property mediator.     *     * @param mediator New value of property mediator.     */    public void setMediator(srcastra.astra.gui.components.combobox.liste2.Mediator mediator) {        this.mediator = mediator;    }    /**     * Getter for property col.     *     * @return Value of property col.     */    public int getCol() {        return col;    }    /**     * Setter for property col.     *     * @param col New value of property col.     */    public void setCol(int col) {        this.col = col;    }    /**     * Getter for property typeprod.     *     * @return Value of property typeprod.     */    public int getTypeprod() {        return typeprod;    }    /**     * Setter for property typeprod.     *     * @param typeprod New value of property typeprod.     */    public void setTypeprod(int typeprod) {        this.typeprod = typeprod;    }    // public    /**     * Setter for property grp_Table_table.     *     * @param grp_Table_table New value of property grp_Table_table.     */    // Variables declaration - do not modify//GEN-BEGIN:variables    private javax.swing.JTextField grp_TField_encode;    private javax.swing.JLabel grp_Label_warning;    private javax.swing.JPopupMenu jPopupMenu1;    // End of variables declaration//GEN-END:variables    private Dimension dim;    protected JPopupMenu grp_PopMenu_popup;    protected JScrollPane grp_ScrollPan_popupScrollPane;    protected JTable grp_Table_table;    private astrainterface serveur;    private ArrayList array;    private ListeModelInterface model;    private Loginusers_T login;    private int position;    private int cleunik;    private String intitule;    ListeMask2 m_listemask;    InterfaceClasseTransfert transfert;    private int nbr_lettre_avant_affichage;    private boolean affiche_panel;    Icon warningIcon;    Icon workingIcon;    JComponent grp_Comp_nextComponent;    srcastra.astra.gui.MainFrame interfaceManipulePanel;    boolean readModePanel;    boolean isLastComponent;    boolean correctInput;    boolean focusFromTable;    java.awt.Frame parentFrame;    boolean passvalueToParent;    boolean filNexComponent;    boolean canbenull;    boolean left;    protected EventListenerList m_listenerList;    protected ActionEvent m_actionEvent;    private Color bgcolor;    boolean up;    boolean valid;    protected EventListenerList m_validateList;    private int clesignalitique;    private boolean modedisplay;    // private Color backColor;}