/*















 * FournisseurIndex.java















 * Created on 11 avril 2002, 10:56















 */















package srcastra.astra.gui.modules.fournisseurs;















import srcastra.astra.sys.classetransfert.Loginusers_T;















import srcastra.astra.sys.rmi.astrainterface;















import srcastra.astra.gui.components.actions.actionToolBar.*;















import srcastra.astra.gui.modules.MainScreenModule;



import srcastra.astra.gui.components.InsertCombo.*;







import srcastra.astra.gui.modules.InternScreenModule;



import srcastra.astra.gui.components.AstraComponent;







import srcastra.astra.sys.classetransfert.clients.Sous_Client_T;







import srcastra.astra.gui.sys.ErreurScreenLibrary;



import srcastra.astra.sys.rmi.Exception.*;



import javax.swing.JOptionPane;







import srcastra.astra.gui.components.actions.ToolBarInteraction;



import srcastra.astra.gui.sys.tableModel.client.SousClientListeTableModel;



import javax.swing.text.*;



import srcastra.astra.gui.sys.formVerification.*;



import java.awt.event.*;



import javax.swing.*;



import javax.swing.table.*;











import srcastra.astra.sys.classetransfert.clients.Clients_T; 



import srcastra.astra.gui.modules.clients.*;



import srcastra.astra.gui.components.combobox.liste.ListeTableModel;



import srcastra.astra.gui.modules.Mailing.*;

import srcastra.astra.gui.sys.utils.CursorChange;

import java.awt.*;















































/**















 *















 * @author  Sébastien















 */















public class ClientIndex extends javax.swing.JPanel implements ToolBarComposer, InternScreenModule,  srcastra.astra.sys.rmi.DossierSqlRecherche, MouseListener,MailInterface{















     















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 















// CONSTRUCTOR















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 















      















    /** Creates new form FournisseurIndex */











    int parentModule;



    public ClientIndex(astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, MainScreenModule parent,int parentModule,srcastra.astra.gui.components.combobox.liste2.ListeTableModel model) {



        this.serveur = serveur;



        this.currentUser = currentUser;



        this.actionToolBar = actionToolBar;



        this.parent = parent;



        this.parentModule=parentModule;



        int key=0;



        if(parentModule==0)



             key= ((ClientModules)parent).getClCleunik();



        lastModify = (key > 0) ? key : -1;



        /* Combo model 2 */



        initComponents();



        grp_TField_nom.addKeyListener(navigate);



        tb_model=model;



        initComponent();

        grp_Table_ClientIndex.setSelectionBackground(new java.awt.Color(221,221,255));



        jScrollPane1.setFocusable(false);



        jPanel2.setFocusable(false);



        jPanel3.setFocusable(false);



        jPanel5.setFocusable(false);



        jPanel6.setFocusable(false);



        jPanel7.setFocusable(false);



        jPanel8.setFocusable(false);



        initTable();



        setThisAsToolBarComponent();



        enableActions(false);



        zemask = new ClientIndex.ZeMask();



        grp_TField_nom.setDocument(zemask);



        //grp_TField_nom.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){doAccept();}});



        grp_TField_nom.requestFocus();



        if(tb_model instanceof SousClientListeTableModel)



            new ManageMailingFrame(this,this,parent.getSuperOwner(),srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());



    }















    































    















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 















// INITIALISATION















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















    private void initTable() {



        //tb_model = new SousClientListeTableModel(serveur, currentUser);



        grp_Table_ClientIndex.setAutoCreateColumnsFromModel(false);



        grp_Table_ClientIndex.setModel(tb_model);



        for(int i=0; i < tb_model.getColumnCount(); i++) {



            DefaultTableCellRenderer renderer = new  srcastra.astra.gui.components.combobox.liste.ColoredListRenderer();



            renderer.setHorizontalAlignment(tb_model.getM_column()[i].c_alignement);



            TableCellEditor editor = new DefaultCellEditor(new JTextField());



            grp_Table_ClientIndex.addColumn(new TableColumn(i, tb_model.getM_column()[i].c_width, renderer, editor));



            JTableHeader header=grp_Table_ClientIndex.getTableHeader();



            header.setUpdateTableInRealTime(false);



        }



        grp_Table_ClientIndex.getTableHeader().setReorderingAllowed(false);



        grp_Table_ClientIndex.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);



        grp_Table_ClientIndex.addMouseListener(this);















    }















    















    /** This method is called from within the constructor to















     * initialize the form.















     * WARNING: Do NOT modify this code. The content of this method is















     * always regenerated by the Form Editor.















     */















    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        grp_Pan_inserCombo = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_Table_ClientIndex = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        grp_Label_nom = new javax.swing.JLabel();
        grp_TField_nom = new srcastra.astra.gui.components.textFields.ATextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_Label_telephone = new javax.swing.JLabel();
        grp_Label_Fax = new javax.swing.JLabel();
        grp_Label_creation = new javax.swing.JLabel();
        grp_Label_modification = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setEnabled(false);
        add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        grp_Pan_inserCombo.setLayout(new java.awt.BorderLayout());

        grp_Pan_inserCombo.setEnabled(false);
        grp_Pan_inserCombo.add(jPanel5, java.awt.BorderLayout.NORTH);

        grp_Table_ClientIndex.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Table_ClientIndex.setModel(new DefaultTableModel());
        grp_Table_ClientIndex.setRequestFocusEnabled(false);
        grp_Table_ClientIndex.setSelectionForeground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(grp_Table_ClientIndex);

        grp_Pan_inserCombo.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(grp_Pan_inserCombo, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setBorder(new javax.swing.border.EtchedBorder());
        jPanel6.setEnabled(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel7.setEnabled(false);
        grp_Label_nom.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Label_nom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("FI_NomFournisseur"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 10);
        jPanel7.add(grp_Label_nom, gridBagConstraints);

        grp_TField_nom.setPreferredSize(new java.awt.Dimension(200, 18));
        grp_TField_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel7.add(grp_TField_nom, gridBagConstraints);

        jPanel1.add(jPanel7);

        jPanel6.add(jPanel1);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 2, true));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("CI_telephone"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 2, 13);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("CI_fax"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 2, 13);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("CI_creation"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 2, 13);
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleClient", currentUser.getLangage()).getString("CI_modification"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 7, 13);
        jPanel4.add(jLabel4, gridBagConstraints);

        grp_Label_telephone.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_telephone.setForeground(new java.awt.Color(0, 102, 0));
        grp_Label_telephone.setPreferredSize(new java.awt.Dimension(125, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel4.add(grp_Label_telephone, gridBagConstraints);

        grp_Label_Fax.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_Fax.setForeground(new java.awt.Color(0, 102, 0));
        grp_Label_Fax.setPreferredSize(new java.awt.Dimension(125, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 5);
        jPanel4.add(grp_Label_Fax, gridBagConstraints);

        grp_Label_creation.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_creation.setForeground(new java.awt.Color(0, 102, 0));
        grp_Label_creation.setPreferredSize(new java.awt.Dimension(125, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 5);
        jPanel4.add(grp_Label_creation, gridBagConstraints);

        grp_Label_modification.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_modification.setForeground(new java.awt.Color(0, 102, 0));
        grp_Label_modification.setPreferredSize(new java.awt.Dimension(125, 13));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 5);
        jPanel4.add(grp_Label_modification, gridBagConstraints);

        jPanel8.add(jPanel4);

        jPanel6.add(jPanel8);

        jPanel3.add(jPanel6, java.awt.BorderLayout.NORTH);

        add(jPanel3, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents































    private void initComponent() {















          displayReadMode();















    }















    















    public void initMe() {















        parent.setCurrentPanel(this);















        enableActions(false);















        tb_model.resetData();















        grp_TField_nom.setText("");















        grp_TField_nom.requestFocus();















        















    }















    















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => LISTENERS















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 































    private KeyListener navigate = new KeyListener() {















        















        public void keyPressed(java.awt.event.KeyEvent evt) {



            int key = evt.getKeyCode();



            if (grp_Table_ClientIndex.getRowCount() > 0) {



                int cur = grp_Table_ClientIndex.getSelectedRow();



                int tot = grp_Table_ClientIndex.getRowCount();



                if (key == KeyEvent.VK_DOWN && (cur < tot -1)) {



                    grp_Table_ClientIndex.changeSelection(cur + 1, 0, false, false);  



                    zemask.setResearch(false);



                    grp_TField_nom.setText(getClientName());



                    fillInfos();



                    zemask.setResearch(true);







                }



                if (key == KeyEvent.VK_UP && (cur >0) ) {



                    grp_Table_ClientIndex.changeSelection(cur - 1, 0, false, false);



                    zemask.setResearch(false);



                    grp_TField_nom.setText(getClientName());



                    fillInfos();



                    zemask.setResearch(true);



                }



                if (key == KeyEvent.VK_ENTER) {



                   doAccept();



                }



            }



        }







        public void keyTyped(java.awt.event.KeyEvent evt) {}



        public void keyReleased(java.awt.event.KeyEvent evt) {}



    };















    















    private String getClientName() {



        int cur = grp_Table_ClientIndex.getSelectedRow();



        return tb_model.getValueAt(cur, 0).toString();



    }































   public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {















       if (grp_Table_ClientIndex.getRowCount() > 0) {















           zemask.setResearch(false);















           zemask.setResearch(false);















           grp_TField_nom.setText(getClientName());















           fillInfos();















           zemask.setResearch(true);















       }















   }















   















   public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {















   }















   















   public void mouseExited(java.awt.event.MouseEvent mouseEvent) {















   }















   















   public void mousePressed(java.awt.event.MouseEvent mouseEvent) {















   }















   















   public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {















   }















    















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => METHODE APPARENTE AU BEANS















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     















    private void setToolBar(int sizeOfArray)















    {















       















    }















    















    















    















    private void fillInfos() {















        int cur = grp_Table_ClientIndex.getSelectedRow();















        if (cur >= 0) {















            Object[] obj = (Object[]) tb_model.getM_vector().get(cur);















            grp_Label_telephone.setText(obj[SousClientListeTableModel.FIELD_PHONE].toString());















            grp_Label_Fax.setText(obj[SousClientListeTableModel.FIELD_FAX].toString());















            grp_Label_creation.setText(new srcastra.astra.sys.classetransfert.utils.Date(obj[SousClientListeTableModel.FIELD_DATECREA].toString().substring(0,19)).toString2());















            grp_Label_modification.setText(new srcastra.astra.sys.classetransfert.utils.Date(obj[SousClientListeTableModel.FIELD_DATEMODIF].toString().substring(0,19)).toString2());















        }















        else {















            grp_Label_telephone.setText("");















            grp_Label_Fax.setText("");















            grp_Label_creation.setText("");















            grp_Label_modification.setText("");















        }















            















    }















    















    public void giveMeTheFocus() {















        grp_TField_nom.requestFocus();















    }















    















    private void enableActions(boolean enabled) {















        if (enabled) {















            parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CREATE, 















                                                       ActionToolBar.DO_ACCEPT, 















                                                       ActionToolBar.DO_CLOSE,















                                                       ActionToolBar.DO_DELETE} );















        }















        else {















            parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CREATE, 















                                                       ActionToolBar.DO_CLOSE } );















        }















    }    































    private void reloadTable() {



        grp_Table_ClientIndex.tableChanged(new javax.swing.event.TableModelEvent(tb_model));



        grp_Table_ClientIndex.repaint();



        if (grp_Table_ClientIndex.getRowCount() > 0 ){ 



            grp_Table_ClientIndex.changeSelection(0,0,false,false);



            enableActions(true);



        }



            else enableActions(false);



    }



    private class ZeMask extends DefaultMask {



        private boolean needLoad = true;



        private int beginLoad = srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNbr_lettre_liste();



        private int colSearch = 1;



        private boolean research = true;



        public ZeMask() {



            super();



        }



        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            if(str.equals( DefaultMask.VALIDATE)) return;

            String c = getText(0, getLength());

            String befO = c.substring(0, offs);

            String aftO = c.substring(offs, getLength());

            String ct = befO + str + aftO;

            boolean encode = true;



          //  if (str.equals(DefaultMask.VALIDATE)) {



            //    doAccept();



              //  return;                



            //}



            if (research) {



                if (needLoad) {



                  /*  if (ct.length() == beginLoad) { 



                        tb_model.loadata(ct);



                        reloadTable();



                        needLoad = false;



                        if (grp_Table_ClientIndex.getRowCount() > 0) {



                            grp_Table_ClientIndex.changeSelection(0, 0, false, false);



                            fillInfos();



                            super.insertString(offs, ct, a);



                        }



                        else java.awt.Toolkit.getDefaultToolkit().beep();



                    } 



                  */ if (ct.length() >= beginLoad) {

                       srcastra.astra.gui.sys.utils.CursorChange.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.WAIT_CURSOR),parent.getSuperOwner(),(JInternalFrame)parent);



                        tb_model.loadata(ct.substring(0, beginLoad));



                        reloadTable();



                       // needLoad = false;



                        if (grp_Table_ClientIndex.getRowCount() > 0) {



                            grp_Table_ClientIndex.changeSelection(0, 0, false, false);



                            fillInfos();

                           // super.insertString(0, beginLoad);

                            

                          //  super.insertString(0, ct, a);



                            needLoad=false;



                        }



                        else {java.awt.Toolkit.getDefaultToolkit().beep();



                            needLoad = true;



                        }

                         srcastra.astra.gui.sys.utils.CursorChange.changeCursor(CursorChange.CHANGE_CURSOR_EVERYWHERE,new Cursor(Cursor.DEFAULT_CURSOR),parent.getSuperOwner(),(JInternalFrame)parent);



                    }

                  super.insertString(offs, str, a);



                }



                else if (ct.length() > 0 && ct.length() > beginLoad) {



                    int ln = tb_model.searchAWord(ct, colSearch);



                    int tot = grp_Table_ClientIndex.getRowCount();



                    if (ln >=0 && ln < tot) {



                        grp_Table_ClientIndex.changeSelection(ln, 0, false, false);



                        fillInfos();



                        String value = tb_model.getValueAt(ln, 0).toString();



                        System.out.println("==+> value = " + value.toUpperCase());



                        System.out.println("==+> ct = " + ct.toUpperCase());



                        if (value.toUpperCase().startsWith(ct.toUpperCase())) super.insertString(offs, str, a);



                        else java.awt.Toolkit.getDefaultToolkit().beep();



                    }



                }



            }



            else if (encode) super.insertString(offs, str, a);



            research = true;



        }



        public void remove(int offs, int len) throws BadLocationException {



            super.remove(offs, len);



            int ct = getLength();



            if (ct < beginLoad && research) {



                needLoad = true;            



                tb_model.resetData();



                reloadTable();



            }



        }



        



        /** Getter for property research.



         * @return Value of property research.







         */



        public boolean isResearch() {



            return research;



        }



        /** Setter for property research.















         * @param research New value of property research.















         */



        public void setResearch(boolean research) {



            this.research = research;



        }



    }















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















    /** Demande d'une suppression ou d'une annulation physique au serveur  */















    public void dbDelete() {



         Object[] tmp=null;



         if(grp_Table_ClientIndex.getRowCount()>0){



                 tmp=tb_model.getSelectedObject(grp_Table_ClientIndex.getSelectedRow());



         int rep=JOptionPane.showConfirmDialog(this,java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage",currentUser.getLangage()).getString("eff_cli")+tmp[6].toString());



         if(rep==JOptionPane.YES_OPTION)



         {



            try{     



              client=new Sous_Client_T();



              client.setCscleunik(Integer.parseInt(tmp[0].toString()));



              client.deleteObject(serveur,this.currentUser); 



              displayReadMode();   



            }



            catch(ServeurSqlFailure se){



               ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);  



            }



            catch(java.rmi.RemoteException re){



              ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);   



            }



            catch(Exception e){



                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);   



             }



         }



          }



       







    }















    















    /** Demande d'une modification au serveur  */















    public void dbUpdate() {















    }















    /** Demande de sélection en vue d'une modification au serveur  */















    public void dbSelectForUpdate() {















    }















    















    /** Permet au parent de lancer le chargement des données au















     * sein de liste (Ici : ListSelector)















     */















    public void chargeData() {















           















    }















    















    /** Demande d'une insertion au serveur  */















    public void dbInsert() {















    }















    /** Demande de sélection au serveur  */















    public void dbSelect() {















        if (client != null) setLastModify(client.getCscleunik());















        else setLastModify(-1);















    }































////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















    public void updateAllFields() {















        















    }















    public void updateAllFields(Object donnee) {















       















        















    }















    















    /** Affichage en mode Lecture  */















    public void displayReadMode() {



        dbSelect();



        reloadTable();



        grp_TField_nom.requestFocus();



    }















    















    /** Affichage en mode Modification  */















    public void displayUpdateMode() {















    }































    /** Affichage en Mode disable  */















    public void displayDisableMode() {















    }















    















    /** Affichage en mode Insertion  */















    public void displayInsertMode() {















    }















    















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















    public void doCancel() {















    }    















    public void doNext() {















    }















    public void doPrevious() {















    }















    public void doDelete() {

         Object[] tmp=null;

         if(grp_Table_ClientIndex.getRowCount()>0){

          try{



                 tmp=tb_model.getSelectedObject(grp_Table_ClientIndex.getSelectedRow());



                int rep=JOptionPane.showConfirmDialog(this,java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage",currentUser.getLangage()).getString("eff_cli")+tmp[6].toString());



                if(parentModule==0 && rep==0){



                    Clients_T cli=new Clients_T();



                      cli.setCscleunik(Integer.parseInt(tmp[0].toString()));



                      cli.deleteObject(serveur,this.currentUser); 



                }else if(parentModule==1 && rep==0) { 



                    ((srcastra.astra.gui.MainFrame)parent.getSuperOwner()).getFournisseurRmi().deleteFournisseur(currentUser.getUrcleunik(),tb_model.getCleUnik(grp_Table_ClientIndex.getSelectedRow()));                                      

                    serveur.renvcombo('f',currentUser.getUrcleunik(),currentUser.getUrlmcleunik(),'p',"", 1,astrainterface.COMBO_FOURNISSEURCAS1,true);

                }



                 tmp[tmp.length-2]=new Integer(1);



                 displayReadMode();  



          }



            catch(ServeurSqlFailure se){



               ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);  



            }



            catch(java.rmi.RemoteException re){



              ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);   



            }



            catch(Exception e){



                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);   



             }







        }















    }















    public void doCreate() {















        parent.displayCreateSequence();















    }















    public void doHelp() {















    }















    public void doClose() {















        parent.closeModule();















    }































    public void doModify() {















    }















    public void doAccept() {















        //-> à modifier j'ai fait cette astuce pour relire correctement un client depuis le Serialver



        int key = tb_model.getCleUnik(grp_Table_ClientIndex.getSelectedRow());



        if (key > 0) {



            zemask.needLoad=true;



            parent.changeCursor(srcastra.astra.gui.sys.utils.CursorChange.CHANGE_CURSOR_EVERYWHERE, new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));



            parent.displayReadSequence(key);       



            parent.changeCursor(srcastra.astra.gui.sys.utils.CursorChange.CHANGE_CURSOR_EVERYWHERE, new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));



        }



    }















    public void doPrint() {















    }















    















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// => Champs de la classe















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 















    private Loginusers_T currentUser;    















    private astrainterface serveur;    















    private ActionToolBar actionToolBar;















    private MainScreenModule parent;















private Sous_Client_T client;















    private int clientCleUnik;















    private AstraComponent[] composantToVerif;















    private ToolBarInteraction tb_interaction;















    private int lastModify;















    private srcastra.astra.gui.components.combobox.liste2.ListeTableModel tb_model;















    private ClientIndex.ZeMask zemask;































//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 















// STATIC VARIABLES















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















































////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        















// => Graphic Component















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  















 















    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel grp_Label_Fax;
    private javax.swing.JLabel grp_Label_creation;
    private javax.swing.JLabel grp_Label_modification;
    private javax.swing.JLabel grp_Label_nom;
    private javax.swing.JLabel grp_Label_telephone;
    private javax.swing.JPanel grp_Pan_inserCombo;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom;
    private javax.swing.JTable grp_Table_ClientIndex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables































////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    















// BEANS PROPERTIES GET/SET SUPPORT















//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 































    public int[] getDefaultActionToolBarMask() {















        return new int[0];















    }















    















    public void setThisAsToolBarComponent() {















        parent.setCurrentPanel(this);















    }















    















    /** Permet à la classe qui implémente cette méthode de se















     * référencer auprès d' ActionToolBar















     * @return le n° de l'action















     */















    public int getAction() {















        return 0;















    }















    















    /** Permet de préciser le type d'action qu'on est occupé de faire :















     * 0 pour lecture















     * 1 pour création















     * 2 pour modification















     * @param action type d'action















     */















    public void setAction(int action) {















    }















    /** Sert à recevoir le titre de son parent















     * pour un cadre éventuel















     * @return le titre du panneau















     */















    public String getTitle() {















        return "";















    }















    















    /** Permet de recevoir la clé unique d'un objet relatif















     * au modules : création par partie ou modification















     * @param frCleUnik la clé unique















     */















    public void setFrCleunik(int frCleUnik) {















    }















    















     /** Setter for property lastModify.















     * @param lastModify New value of property lastModify.















     */















    public void setLastModify(int lastModify) {







       this.lastModify = lastModify;







    }















    















     /** Getter for property lastModify.















     * @return Value of property lastModify.















     */















   public int getLastModify() {



        return lastModify;



    }















   















   public void doSwitch() {















   }















   















   public void goUp() {















   }   















   















   public java.awt.Component m_getGeneriqueTable() {















       return this.grp_Table_ClientIndex;















   }   















   















   public void doF10() {















   }







   /** Getter for property grp_TField_nom.



    * @return Value of property grp_TField_nom.



    */



   public srcastra.astra.gui.components.textFields.ATextField getGrp_TField_nom() {



       return grp_TField_nom;



   }   







   /** Setter for property grp_TField_nom.



    * @param grp_TField_nom New value of property grp_TField_nom.



    */



   public void setGrp_TField_nom(srcastra.astra.gui.components.textFields.ATextField grp_TField_nom) {



       this.grp_TField_nom = grp_TField_nom;



   }   







   public void addKeystroque() {



   }   



   



   /** Creates a new instance of MailInterface  */

 public String getFormEntiteMail() {
       
       return "";
   }

   public String[] getEmailAdres() {



       if(grp_Table_ClientIndex.getRowCount()>0){



            int i=grp_Table_ClientIndex.getSelectedRow();



            Object[] tab=tb_model.getSelectedObject(i);



            return new String[]{tab[3].toString()};



       }



   else



       return null;



           



       



   }   







   public Loginusers_T getUser() {



       return parent.getCurrentUser();



   }   







   public void doF7() {



   }   







}















