/* * FournisseurContactPane.java * * Created on 27 mars 2002, 12:34 */package srcastra.astra.gui.modules.fournisseurs;import srcastra.astra.gui.modules.InternScreenModule;import srcastra.astra.gui.modules.MainScreenModule;import srcastra.astra.gui.sys.ErreurScreenLibrary;import srcastra.astra.gui.components.actions.actionToolBar.*;import srcastra.astra.gui.components.AstraComponent;import javax.swing.JOptionPane;import srcastra.astra.gui.sys.formVerification.*;import srcastra.astra.gui.components.actions.ToolBarInteraction;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import srcastra.astra.gui.event.ValidateField;import srcastra.astra.gui.modules.Mailing.*;import srcastra.astra.sys.classetransfert.Loginusers_T;/** * * @author  S�bastien */public class FournisseurContactPane extends javax.swing.JPanel implements InternScreenModule,                                                                          ToolBarComposer,                                                                          java.awt.event.ComponentListener,MailInterface  {                                                                              //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // CONSTRUCTOR////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                       /** Creates new form FournisseurContactPane */    public FournisseurContactPane(srcastra.astra.sys.rmi.astrainterface serveur,                                   srcastra.astra.sys.classetransfert.Loginusers_T currentUser,                                   MainScreenModule parent, ActionToolBar actionToolBar, int frCleUnik) {        // initialisation des champs        this.serveur = serveur;        this.currentUser = currentUser;        this.parent = parent;        this.actionToolBar = actionToolBar;        this.frCleUnik = frCleUnik;        this.addComponentListener(this);        setLastModify(-1);            }    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // INITIALISATION////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             /** Initialise les champs graphiques ainsi qu'un tableau pour permettre d'interargir     * sur tous les �l�ments � partir d'une boucle.     * Les �l�ments graphiques et le tableau ne sera initialis� qu' 1 fois  */    private void init() {        if (!initOnce) {            // chargement des composants et de leurs propri�tes            initComponents();            init2();            setDocumentMask();            // chargement d'un tableau reprenant tous les �l�ments pour une correction            this.composantToVerif = new AstraComponent[] {                grp_TField_departement, grp_TField_nom, grp_TField_prenom,                 grp_TField_mail, grp_TField_telephone, grp_TField_Fax };                            this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);            tb_interaction.setEnvironnement(srcastra.astra.gui.components.InsertCombo.InsertCombo.ENVIRONNEMENT_PANEL_DATA);            tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_STANDART);                        //-> R�gistration des listeners pour la validit� des composants            for (int i=0; i < composantToVerif.length; i++) {                composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());            }            grp_Combo_insertCombo.addInsertComboListener(tb_interaction.getChangeInsertComboDataActionListener());            //-------------------fin de la r�gistration des listeners dans les beans --------------------------------                            // les �l�ments ne sont intialis� qu'1 seule fois                initOnce = true;                            grp_TField_Fax.addActionListener(valideAndPrevious);        }    }         private void setDocumentMask() {        grp_TField_departement.setDocument(new DefaultMask(0, 50, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));        grp_TField_nom.setDocument(new DefaultMask(1, 25, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));        grp_TField_prenom.setDocument(new DefaultMask(0, 25, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));        grp_TField_mail.setDocument(new DefaultMask(0, 100, currentUser.getLangage(), DefaultMask.CASE_UNSENSITIVE));        grp_TField_telephone.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));        grp_TField_Fax.setDocument(new DefaultMask(0, 30, currentUser.getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));    }        /** This method is called from within the constructor to     * initialize the form.     * WARNING: Do NOT modify this code. The content of this method is     * always regenerated by the Form Editor.     */    private void initComponents() {//GEN-BEGIN:initComponents        java.awt.GridBagConstraints gridBagConstraints;        buttonGroup1 = new javax.swing.ButtonGroup();        grp_Pan_Center = new javax.swing.JPanel();        grp_Pan_AfficheInsertCombo = new javax.swing.JPanel();        jPanel8 = new javax.swing.JPanel();        jPanel5 = new javax.swing.JPanel();        jPanel6 = new javax.swing.JPanel();        grp_Label_departement = new javax.swing.JLabel();        grp_Label_nom = new javax.swing.JLabel();        grp_Label_prenom = new javax.swing.JLabel();        grp_Label_mail = new javax.swing.JLabel();        grp_Label_telephone = new javax.swing.JLabel();        grp_TField_departement = new srcastra.astra.gui.components.textFields.ATextField();        grp_TField_nom = new srcastra.astra.gui.components.textFields.ATextField();        grp_TField_prenom = new srcastra.astra.gui.components.textFields.ATextField();        grp_TField_mail = new srcastra.astra.gui.components.textFields.ATextField();        grp_TField_telephone = new srcastra.astra.gui.components.textFields.ATextField();        jLabel1 = new javax.swing.JLabel();        grp_TField_Fax = new srcastra.astra.gui.components.textFields.ATextField();        grp_Pan_South = new javax.swing.JPanel();        setLayout(new java.awt.BorderLayout());        setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_titrePane")));        setEnabled(false);        grp_Pan_Center.setLayout(new java.awt.BorderLayout());        grp_Pan_AfficheInsertCombo.setLayout(new java.awt.BorderLayout());        grp_Pan_AfficheInsertCombo.setEnabled(false);        grp_Pan_AfficheInsertCombo.add(jPanel8, java.awt.BorderLayout.NORTH);        grp_Pan_Center.add(grp_Pan_AfficheInsertCombo, java.awt.BorderLayout.CENTER);        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 12));        jPanel5.setBorder(new javax.swing.border.EtchedBorder());        jPanel5.setMinimumSize(new java.awt.Dimension(184, 150));        jPanel5.setPreferredSize(new java.awt.Dimension(280, 142));        jPanel5.setEnabled(false);        jPanel6.setLayout(new java.awt.GridBagLayout());        jPanel6.setEnabled(false);        grp_Label_departement.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_departement.setForeground(java.awt.Color.black);        grp_Label_departement.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_departement"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 100);        jPanel6.add(grp_Label_departement, gridBagConstraints);        grp_Label_nom.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_nom.setForeground(java.awt.Color.black);        grp_Label_nom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_nom"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 1;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 100);        jPanel6.add(grp_Label_nom, gridBagConstraints);        grp_Label_prenom.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_prenom.setForeground(java.awt.Color.black);        grp_Label_prenom.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_prenom"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 2;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 100);        jPanel6.add(grp_Label_prenom, gridBagConstraints);        grp_Label_mail.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_mail.setForeground(java.awt.Color.black);        grp_Label_mail.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_adresseMail"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 3;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 100);        jPanel6.add(grp_Label_mail, gridBagConstraints);        grp_Label_telephone.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_telephone.setForeground(java.awt.Color.black);        grp_Label_telephone.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_telephone"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 4;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 100);        jPanel6.add(grp_Label_telephone, gridBagConstraints);        grp_TField_departement.setEnabled(false);        grp_TField_departement.setGrp_Comp_nextComponent(grp_TField_nom);        grp_TField_departement.setPreferredSize(new java.awt.Dimension(200, 18));        grp_TField_departement.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_departement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);        jPanel6.add(grp_TField_departement, gridBagConstraints);        grp_TField_nom.setEnabled(false);        grp_TField_nom.setGrp_Comp_nextComponent(grp_TField_prenom);        grp_TField_nom.setGrp_Comp_previousComponent(grp_TField_departement);        grp_TField_nom.setPreferredSize(new java.awt.Dimension(150, 18));        grp_TField_nom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_nom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 1;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);        jPanel6.add(grp_TField_nom, gridBagConstraints);        grp_TField_prenom.setEnabled(false);        grp_TField_prenom.setGrp_Comp_nextComponent(grp_TField_mail);        grp_TField_prenom.setGrp_Comp_previousComponent(grp_TField_nom);        grp_TField_prenom.setPreferredSize(new java.awt.Dimension(150, 18));        grp_TField_prenom.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_prenom.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 2;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);        jPanel6.add(grp_TField_prenom, gridBagConstraints);        grp_TField_mail.setEnabled(false);        grp_TField_mail.setGrp_Comp_nextComponent(grp_TField_telephone);        grp_TField_mail.setGrp_Comp_previousComponent(grp_TField_prenom);        grp_TField_mail.setPreferredSize(new java.awt.Dimension(170, 18));        grp_TField_mail.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_mail.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 3;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);        jPanel6.add(grp_TField_mail, gridBagConstraints);        grp_TField_telephone.setEnabled(false);        grp_TField_telephone.setGrp_Comp_nextComponent(grp_TField_Fax);        grp_TField_telephone.setGrp_Comp_previousComponent(grp_TField_mail);        grp_TField_telephone.setLastFocusedComponent(true);        grp_TField_telephone.setPreferredSize(new java.awt.Dimension(150, 18));        grp_TField_telephone.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_telephone.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 4;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);        jPanel6.add(grp_TField_telephone, gridBagConstraints);        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", parent.getCurrentUser().getLangage()).getString("CT_fax"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 5;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);        jPanel6.add(jLabel1, gridBagConstraints);        grp_TField_Fax.setEnabled(false);        grp_TField_Fax.setLastFocusedComponent(true);        grp_TField_Fax.setPreferredSize(new java.awt.Dimension(150, 18));        grp_TField_Fax.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_Fax.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 5;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel6.add(grp_TField_Fax, gridBagConstraints);        jPanel5.add(jPanel6);        grp_Pan_Center.add(jPanel5, java.awt.BorderLayout.SOUTH);        add(grp_Pan_Center, java.awt.BorderLayout.CENTER);        grp_Pan_South.setEnabled(false);        add(grp_Pan_South, java.awt.BorderLayout.SOUTH);    }//GEN-END:initComponents    private void init2() {        String[] titre=new String[]{"id",java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_nom"),java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_prenom"),java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_departement")};        grp_Combo_insertCombo = new srcastra.astra.gui.components.InsertCombo.InsertCombo(titre,0, currentUser);        grp_Combo_insertCombo.setServeur(this.serveur);        grp_Combo_insertCombo.setTypeDeCombo(srcastra.astra.sys.rmi.astrainterface.COMBO_FOURNCONTACT);        grp_Combo_insertCombo.setIParent(this);        grp_Combo_insertCombo.setEnvironnement(srcastra.astra.gui.components.InsertCombo.InsertCombo.ENVIRONNEMENT_PANEL_DATA);        grp_Pan_AfficheInsertCombo.add(grp_Combo_insertCombo);           }        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => LISTENERS////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        private void grp_But_dernierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_dernierActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_But_dernierActionPerformed    private void grp_But_SuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_SuivantActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_But_SuivantActionPerformed    private void grp_But_precedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_precedentActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_But_precedentActionPerformed    private void grp_But_premierElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_premierElementActionPerformed       // Add your handling code here:    }//GEN-LAST:event_grp_But_premierElementActionPerformed    private void grp_Cbox_FournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Cbox_FournisseurActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_Cbox_FournisseurActionPerformed    private void grp_CBox_DepartementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_CBox_DepartementActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_CBox_DepartementActionPerformed    private void grp_CBox_NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_CBox_NomActionPerformed        // Add your handling code here:    }//GEN-LAST:event_grp_CBox_NomActionPerformed      public void componentShown(java.awt.event.ComponentEvent componentEvent) {        // actionToolBar.setTbComposer(this);        parent.setCurrentPanel(this);        switch (action) {            case ActionToolBar.ACT_READ :                displayReadMode();                break;            case ActionToolBar.ACT_INSERT :                displayInsertMode();                break;        }    }        public void componentMoved(java.awt.event.ComponentEvent componentEvent) {    }        public void componentResized(java.awt.event.ComponentEvent componentEvent) {    }        public void componentHidden(java.awt.event.ComponentEvent componentEvent) {    }        private ValidateField valideAndPrevious = new ValidateField() {        public void actionPerformed(ActionEvent evt) {            if (action == ActionToolBar.ACT_INSERT || action == ActionToolBar.ACT_MODIFY) {                requestFocus();                doPrevious();            }        }    };    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE AU BEANS////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      public void chargeData() {       try {            java.util.ArrayList d = serveur.renvcombofourncontact(currentUser.getUrcleunik(), premiereLettre, 1, frCleUnik);            // setData(d);       }       catch (java.rmi.RemoteException re) {           ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);       }       catch (Exception e) {           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);       }               }            public void dbSelect() {        grp_Combo_insertCombo.setExtCleUnik(this.frCleUnik);        grp_Combo_insertCombo.setValuefromParent(1,srcastra.astra.sys.rmi.astrainterface.COMBO_FOURNCONTACT);        grp_Combo_insertCombo.chargeData(1);              grp_Combo_insertCombo.setModifyItem(getLastModify());        if (fournContact != null) setLastModify(fournContact.getFrctcleunik());        else setLastModify(-1);    }        public void dbSelectForUpdate() {       try {            Object obj = serveur.ChargeObjectPopup(0, currentUser.getUrcleunik(), contactCleUnik, 2, serveur.COMBO_FOURNCONTACT);            fournContact = (srcastra.astra.sys.classetransfert.FournContact_T) obj;            if(fournContact.getErreurcode()==1205)            {                JOptionPane.showMessageDialog(this,"Ce contact est d�j� en cours de modification par un autre utilisateur!");                checkValidity=false;            }            else            {               contactCleUnik = fournContact.getFrctcleunik();               checkValidity=true;                updateAllFields();             }                      }        catch (java.rmi.RemoteException re) {            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);       }       catch (Exception e) {           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);      }    }        public void dbInsert() {       srcastra.astra.sys.classetransfert.Gestionerreur_T erreur;       fournContact = new srcastra.astra.sys.classetransfert.FournContact_T();       fournContact.setFrcleunik(frCleUnik);       fournContact.setFrctgrpadminist(grp_TField_departement.getText());       fournContact.setFrctnom(grp_TField_nom.getText());       fournContact.setFrctprenom(grp_TField_prenom.getText());       fournContact.setFrctmail(grp_TField_mail.getText());       fournContact.setFrcttelephone(grp_TField_telephone.getText());       fournContact.setFrctfax(grp_TField_Fax.getText());       try {            erreur = serveur.insertfourncontact(fournContact, currentUser.getUrcleunik());           // if (erreur.getErreurcode() == 1062) {             //   javax.swing.JOptionPane.showMessageDialog(this, "La r�f�rence dialogue existe d�j�");            //}            //else {                action = ActionToolBar.ACT_READ;                setLastModify(erreur.getTmpint());                displayReadMode();            //}        }        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {            ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se,currentUser);                    }        catch (java.rmi.RemoteException re) {            ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re,currentUser);                    }       catch (Exception e) {           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e,currentUser);       }    }        public void dbDelete() {        JOptionPane.showMessageDialog(this,"Sorry ren pour le moment tu ne peux effacer les fournisseur que sur le premier panneaux");        // setLastModify(-1);    }        public void dbUpdate() {       srcastra.astra.sys.classetransfert.Gestionerreur_T erreur;       fournContact = new srcastra.astra.sys.classetransfert.FournContact_T();       fournContact.setFrctcleunik(contactCleUnik);       fournContact.setFrcleunik(frCleUnik);       fournContact.setFrctgrpadminist(grp_TField_departement.getText());       fournContact.setFrctnom(grp_TField_nom.getText());       fournContact.setFrctprenom(grp_TField_prenom.getText());       fournContact.setFrctmail(grp_TField_mail.getText());       fournContact.setFrcttelephone(grp_TField_telephone.getText());       fournContact.setFrctfax(grp_TField_Fax.getText());        try {            erreur = serveur.modifyfourncontact(fournContact, currentUser.getUrcleunik());          //  if (erreur.getErreurcode() == 1062) {            //    javax.swing.JOptionPane.showMessageDialog(this, "La r�f�rence dialogue existe d�j�");            //}           // else {                setLastModify(fournContact.getFrctcleunik());                displayReadMode();            //}        }      catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {            ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se,currentUser);                    }      catch (java.rmi.RemoteException re) {            ErreurScreenLibrary.displayErreur(this,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re,currentUser);                    }             catch (Exception e) {           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e,currentUser);       }   }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE A L' AFFICHAGE DES DONNEES////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      public void displayReadMode() {        parent.setCurrentActionEnabled(new int[0]);        action = ActionToolBar.ACT_READ;        parent.enabledTabbedPane(true);        init();        dbSelect();                for (int i=0; i < composantToVerif.length; i++) {            composantToVerif[i].setEnabled(false);            composantToVerif[i].setLastFocusedComponent(false);        }        grp_Combo_insertCombo.setEnabled(true);        grp_TField_Fax.setLastFocusedComponent(true);        this.requestFocus();    }       public void displayInsertMode() {       // toolbar        parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_CANCEL } );        action = ActionToolBar.ACT_INSERT;        parent.enabledTabbedPane(false);       init();        for (int i=0; i < composantToVerif.length; i++) {            composantToVerif[i].setEnabled(false);            composantToVerif[i].setText("");        }       grp_Combo_insertCombo.setEnabled(false);        grp_TField_Fax.setLastFocusedComponent(true);        grp_TField_departement.setEnabled(true);        grp_TField_departement.requestFocus();    }        public void displayUpdateMode() {        // effectuer un select for update dans insertCombo         dbSelectForUpdate();         if(!checkValidity)  {                 displayReadMode();         }         else{        // afficher les donn�es dans les champs d'insertCombo                parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_PREVIOUS,                                                   ActionToolBar.DO_CANCEL } );        action = ActionToolBar.ACT_MODIFY;        parent.enabledTabbedPane(false);        init();        for (int i=0; i < composantToVerif.length; i++) {            composantToVerif[i].setEnabled(true);            composantToVerif[i].setLastFocusedComponent(true);        }        grp_TField_departement.requestFocus();        grp_Combo_insertCombo.setEnabled(false);        grp_TField_departement.requestFocus();            }    }        public void displayDisableMode() {         //     }        public void updateAllFields() {        try {            grp_TField_departement.setText("" + fournContact.getFrctgrpadminist());            grp_TField_nom.setText("" + fournContact.getFrctnom());            grp_TField_prenom.setText("" + fournContact.getFrctprenom());            grp_TField_mail.setText("" + fournContact.getFrctmail());            grp_TField_telephone.setText("" + fournContact.getFrcttelephone());            grp_TField_Fax.setText("" + fournContact.getFrctfax());        }        catch (NullPointerException npe) {            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.NULL_POINTER_EXCEPTION, srcastra.astra.Astra.DEBUG, npe);        }    }        public void updateAllFields(Object donnee) {        fournContact = (srcastra.astra.sys.classetransfert.FournContact_T) donnee;        contactCleUnik = fournContact.getFrctcleunik();        updateAllFields();    }    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => METHODE APPARENTE AUX APPELS DE LA TOOLBAR////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      public void doCancel() {       switch (action) {            case ActionToolBar.ACT_INSERT :                displayReadMode();                    break;            case ActionToolBar.ACT_MODIFY :                try{                serveur.remoterollback(currentUser.getUrcleunik());                }                catch(java.rmi.RemoteException e)                {                    System.out.println("Exception dans remoterollback dans docancel fournisseurg�n�ralinfopane"+e.getMessage());                }                setLastModify(fournContact.getFrctcleunik());                displayReadMode();                break;            case ActionToolBar.ACT_READ :               parent.cancelModule();        }                                            }        public void doNext() {    }        public void doPrevious() {        // actions        if (action == ActionToolBar.ACT_INSERT) {            dbInsert();        }        else if (action == ActionToolBar.ACT_MODIFY) {            dbUpdate();        }    }        public void doDelete() {        dbDelete();    }        public void doCreate() {        displayInsertMode();            }        public void doHelp() {    }        public void doClose() {        parent.cancelModule();    }        public void doModify() {        displayUpdateMode();    }        public void doAccept() {                    }        public void doPrint() {    }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // => Champs de la classe////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     private srcastra.astra.sys.rmi.astrainterface serveur;        private srcastra.astra.sys.classetransfert.Loginusers_T currentUser;        private int frCleUnik;    private srcastra.astra.sys.classetransfert.FournContact_T fournContact;    private java.util.ArrayList data;    private MainScreenModule parent;    private char premiereLettre = 1;        private boolean create = false;            public boolean modif = false;        private int action;        private ActionToolBar actionToolBar;        private AstraComponent[] composantToVerif;        /** pour 1 seul initiation du panneau     */    public boolean initOnce = false;        private srcastra.astra.gui.components.InsertCombo.InsertCombo grp_Combo_insertCombo;        private int contactCleUnik;        private boolean checkValidity=false;    private ToolBarInteraction tb_interaction;    private int lastModify;//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// // STATIC VARIABLES////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      public static int PANE_NUMBER = 3;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        // => Graphic Component////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      // Variables declaration - do not modify//GEN-BEGIN:variables    private javax.swing.JLabel grp_Label_telephone;    private javax.swing.JPanel jPanel8;    private javax.swing.JPanel jPanel6;    private javax.swing.JPanel jPanel5;    private javax.swing.JPanel grp_Pan_AfficheInsertCombo;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_mail;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_departement;    private javax.swing.JLabel grp_Label_prenom;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nom;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_telephone;    private javax.swing.ButtonGroup buttonGroup1;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Fax;    private javax.swing.JLabel grp_Label_mail;    private javax.swing.JPanel grp_Pan_South;    private javax.swing.JPanel grp_Pan_Center;    private javax.swing.JLabel grp_Label_departement;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_prenom;    private javax.swing.JLabel jLabel1;    private javax.swing.JLabel grp_Label_nom;    // End of variables declaration//GEN-END:variables        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    // BEANS PROPERTIES GET/SET SUPPORT////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     public String getTitle() {        try {            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleFournisseur", currentUser.getLangage()).getString("CT_titrePane");        }        catch (java.util.MissingResourceException mre) {            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);            return "";        }    }        public void setFrCleunik(int frCleUnik) {        this.frCleUnik = frCleUnik;    }            /** Getter for property data.     * @return Value of property data.     */    public java.util.ArrayList getData() {        return data;    }        public int getAction() {        return this.action;    }         public void setAction(int action) {        this.action = action;    }        /** Sp�cifie le composant qui impl�mente cette fonction comme     * le composant qui pilote l'actionToolBar     */    public void setThisAsToolBarComponent() {        parent.setCurrentPanel(this);    }        public int[] getDefaultActionToolBarMask() {        return new int[] { actionToolBar.DO_CREATE,                            actionToolBar.DO_MODIFY,                           actionToolBar.DO_CLOSE };    }         /** Setter for property lastModify.     * @param lastModify New value of property lastModify.     */    public void setLastModify(int lastModify) {       this.lastModify = lastModify;    }         /** Getter for property lastModify.     * @return Value of property lastModify.     */   public int getLastModify() {        return lastModify;    }      public void doSwitch() {   }      public void goUp() {   }      public java.awt.Component m_getGeneriqueTable() {       return grp_Combo_insertCombo.getGrp_Table_Affiche();   }      public void doF10() {   }   /** Creates a new instance of MailInterface  */   public String[] getEmailAdres() {       return new String[]{grp_TField_mail.getText()};   }      public void addKeystroque() {        new ManageMailingFrame(this,this,parent.getSuperOwner(),srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig());   }         public Loginusers_T getUser() {       return parent.getCurrentUser();   }          public String getFormEntiteMail() {              return "";   }   public void doF7() {   }   }