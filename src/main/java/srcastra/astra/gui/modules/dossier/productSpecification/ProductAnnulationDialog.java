/* * ProductAnnulationDialog.java * * Created on 4 novembre 2002, 12:43 */package srcastra.astra.gui.modules.dossier.productSpecification;import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;import srcastra.astra.gui.sys.formVerification.*;import srcastra.astra.sys.classetransfert.dossier.*;import srcastra.astra.gui.test.*;import srcastra.astra.gui.event.ValidateField;import java.awt.event.*;import srcastra.astra.sys.*;import srcastra.astra.sys.compta.*;import srcastra.astra.sys.classetransfert.*;import srcastra.astra.sys.classetransfert.utils.*;/** * @author S�bastien */public class ProductAnnulationDialog extends javax.swing.JDialog {    public DossierMainScreenModule m_parent;    /**     * Creates new form ProductAnnulationDialog     */    public ProductAnnulationDialog(java.awt.Frame parent, boolean modal, DossierMainScreenModule Parent, double montant_annul, ProduitTableModelGlobal model, javax.swing.JTable table) {        super(parent, modal);        this.m_parent = Parent;        initComponents();        setDocument();        grp_ADate_dateAnnulation.setDate(CalculDate.getTodayDate());        m_montant_annul = montant_annul;        m_model = model;        m_table = table;        initFields();    }    private void setDocument() {        grp_TField_MontantAnnulation.setDocument(new DecimalMask(10, 2, m_parent.getCurrentUser().getLangage()));        grp_TField_pourcent.setDocument(new DecimalMask(3, 2, m_parent.getCurrentUser().getLangage()));        grp_TField_pourcent.setDocument(new DecimalMask(10, 2, m_parent.getCurrentUser().getLangage()));    }    private void initFields() {        grp_ADate_dateAnnulation.setDate(CalculDate.getTodayDate());        grp_TField_MontantAnnulation.setText("" + m_montant_annul);        grp_TField_pourcent.setText("" + srcastra.astra.sys.configuration.MainConfig.getMainConfig().m_dossier.m_pourcent_annulation);        double fraisannul = (srcastra.astra.sys.configuration.MainConfig.getMainConfig().m_dossier.m_pourcent_annulation * m_montant_annul) / 100d;        fraisannul = MathRound.roundThisToDouble(fraisannul);        grp_TField_frais_annul.setText("" + fraisannul);        grp_TField_pourcent.clearIcon();        grp_ADate_dateAnnulation.requestFocus();    }    /**     * This method is called from within the constructor to     * <p/>     * <p/>     * initialize the form.     * <p/>     * <p/>     * WARNING: Do NOT modify this code. The content of this method is     * <p/>     * <p/>     * always regenerated by the Form Editor.     */    private void initComponents() {//GEN-BEGIN:initComponents        java.awt.GridBagConstraints gridBagConstraints;        jPanel1 = new javax.swing.JPanel();        jPanel2 = new javax.swing.JPanel();        jPanel4 = new javax.swing.JPanel();        jLabel1 = new javax.swing.JLabel();        jLabel2 = new javax.swing.JLabel();        grp_TField_MontantAnnulation = new srcastra.astra.gui.components.textFields.ATextField();        grp_Label_pourcent = new javax.swing.JLabel();        grp_Label_montant = new javax.swing.JLabel();        grp_TField_pourcent = new srcastra.astra.gui.components.textFields.ATextField();        grp_TField_frais_annul = new srcastra.astra.gui.components.textFields.ATextField();        grp_Libele_intitule = new javax.swing.JLabel();        grp_TField_intitule = new srcastra.astra.gui.components.textFields.ATextField();        grp_ADate_dateAnnulation = new srcastra.astra.gui.components.date.thedate.ADate();        jPanel3 = new javax.swing.JPanel();        grp_But_Valider = new javax.swing.JButton();        grp_But_Annuler = new javax.swing.JButton();        addWindowListener(new java.awt.event.WindowAdapter() {            public void windowClosing(java.awt.event.WindowEvent evt) {                closeDialog(evt);            }        });        jPanel2.setBorder(new javax.swing.border.EtchedBorder());        jPanel4.setLayout(new java.awt.GridBagLayout());        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_ADate_dateAnnulation"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 6);        jPanel4.add(jLabel1, gridBagConstraints);        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_TField_MontantAnnulation"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 1;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 6);        jPanel4.add(jLabel2, gridBagConstraints);        grp_TField_MontantAnnulation.setEnabled(false);        grp_TField_MontantAnnulation.setGrp_Comp_nextComponent(null);        grp_TField_MontantAnnulation.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_MontantAnnulation.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 1;        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel4.add(grp_TField_MontantAnnulation, gridBagConstraints);        grp_Label_pourcent.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_pourcent.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_Label_pourcent"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 2;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 6);        jPanel4.add(grp_Label_pourcent, gridBagConstraints);        grp_Label_montant.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Label_montant.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_Label_montant"));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 3;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 6);        jPanel4.add(grp_Label_montant, gridBagConstraints);        grp_TField_pourcent.setGrp_Comp_nextComponent(grp_TField_frais_annul);        grp_TField_pourcent.setGrp_Comp_previousComponent(grp_ADate_dateAnnulation);        grp_TField_pourcent.setPreferredSize(new java.awt.Dimension(60, 18));        grp_TField_pourcent.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_pourcent.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 2;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel4.add(grp_TField_pourcent, gridBagConstraints);        grp_TField_frais_annul.setGrp_Comp_nextComponent(grp_TField_intitule);        grp_TField_frais_annul.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_frais_annul.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 3;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel4.add(grp_TField_frais_annul, gridBagConstraints);        grp_Libele_intitule.setFont(new java.awt.Font("Tahoma", 0, 10));        grp_Libele_intitule.setText("Libell\u00e9");        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 0;        gridBagConstraints.gridy = 4;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 6);        jPanel4.add(grp_Libele_intitule, gridBagConstraints);        grp_TField_intitule.setGrp_Comp_nextComponent(null);        grp_TField_intitule.setPreferredSize(new java.awt.Dimension(150, 18));        grp_TField_intitule.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));        grp_TField_intitule.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.gridx = 1;        gridBagConstraints.gridy = 4;        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel4.add(grp_TField_intitule, gridBagConstraints);        grp_ADate_dateAnnulation.setGrp_Comp_nextComponent(grp_TField_pourcent);        gridBagConstraints = new java.awt.GridBagConstraints();        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);        jPanel4.add(grp_ADate_dateAnnulation, gridBagConstraints);        jPanel2.add(jPanel4);        jPanel1.add(jPanel2);        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));        grp_But_Valider.setFont(new java.awt.Font("Tahoma", 1, 10));        grp_But_Valider.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_But_Valider"));        grp_But_Valider.addActionListener(new java.awt.event.ActionListener() {            public void actionPerformed(java.awt.event.ActionEvent evt) {                grp_But_ValiderActionPerformed(evt);            }        });        jPanel3.add(grp_But_Valider);        grp_But_Annuler.setFont(new java.awt.Font("Tahoma", 1, 10));        grp_But_Annuler.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog", m_parent.getCurrentUser().getLangage()).getString("grp_But_Annuler"));        grp_But_Annuler.addActionListener(new java.awt.event.ActionListener() {            public void actionPerformed(java.awt.event.ActionEvent evt) {                grp_But_AnnulerActionPerformed(evt);            }        });        jPanel3.add(grp_But_Annuler);        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();        setBounds((screenSize.width - 408) / 2, (screenSize.height - 203) / 2, 408, 203);    }//GEN-END:initComponents    private void grp_But_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_AnnulerActionPerformed        closeFrame();    }//GEN-LAST:event_grp_But_AnnulerActionPerformed    private void grp_But_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_ValiderActionPerformed        double prix2 = 0;        try {            prix2 = new Double(grp_TField_frais_annul.getText()).doubleValue();        } catch (NumberFormatException ne) {            prix2 = 0;        }        if (grp_TField_intitule.getText().equals("") && prix2 != 0) {            m_parent.getMessageManager().showMessageDialog(this, "lib_ob", "lib_obtitre", m_parent.getCurrentUser());            grp_TField_intitule.requestFocus();        } else {            Frais_T frais = new Frais_T();            Grpdecision_T grpdec = new Grpdecision_T();            // grpdec.setFrcleunik(20);            // grpdec.setFrgtcleunik(38);            grpdec.setGncomptevente(635);            grpdec.setGncodetvavente(4);//(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());            grpdec.setValeurGenFloat1(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossierTva());            grpdec.setModifyreccord(true);            grpdec.setIsfromProduit(false);            grpdec.setGnfrtvaComptabiliserVente(0);            grpdec.setGncodetvaachat(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());            frais.setGroupdec(grpdec);            m_parent.decrementeTrainCompteur();            frais.setCleunik(m_parent.getTrainCompteur());            try {                frais.setMontant_annul(new Double(grp_TField_MontantAnnulation.getText()).doubleValue());            }            catch (NumberFormatException ne) {                grp_TField_MontantAnnulation.setText("0");                System.out.println("erreur de number format");            }// supreduc.setValeur_tot(grp_TField_frais_annul.getText());            try {                double prix = new Double(grp_TField_frais_annul.getText()).doubleValue();                if (prix < 0) prix = -prix;                prix = MathRound.roundThisToDouble(prix);                frais.setFrais_annulation(prix);                TvaReturnValue tva = tvaCalcul.TvaReturnValueNoTva(frais);                frais.setMontant_tva(tva.montant_Tva);                frais.setValeur_tot(tva.montantHtva);                frais.setValeur_tot_tva_inc(tva.montant_TvaCompris);                frais.setLongtime();            }            catch (NumberFormatException ne) {                grp_TField_MontantAnnulation.setText("0");                System.out.println("erreur de number format");            }// supreduc.setValeur_tot(grp_TField_frais_annul.getText());            try {                frais.setPercent(new Float(grp_TField_pourcent.getText()).floatValue());            }            catch (NumberFormatException ne) {                grp_TField_pourcent.setText("0");                System.out.println("erreur de number format");            }// su            //frais.setNote_cr�dit(note_cr�dit            frais.setLibelle(grp_TField_intitule.getText());            frais.setLibelleCompta(grp_TField_intitule.getText());            frais.prepareAffichage();            //   frais.getProduitaff().m_delete=new Boolean(true);            requestFocus();            int rep = 0;            requestFocus();            if (rep == 0) {                for (int i = 0; i < this.m_model.getRowCount(); i++)                {                    srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) m_model.getClassAffichage(i);                    Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[PPPPPPPPPPPPPPP]parentKey :" + produitaff.getParent().getTypeDeProduitCleunik() + "  parent nom " + produitaff.getParent().getTypeDeProduitNom());                    if (produitaff.m_delete.booleanValue() == true)                    {                        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "\n[PPPPPPPPPPPPPPP2]parentKey :" + produitaff.getParent().getTypeDeProduitCleunik() + "  parent nom " + produitaff.getParent().getTypeDeProduitNom());                        if (produitaff.getParent().getTypeDeProduitCleunik() != produit_T.FRAIS)                            produitaff.getParent().setDeleted(true);                    }                }                frais.setIsnewreccord(true);                frais.setLongtime();                if (frais.getAt_val_vente() != 0)                    m_parent.getDossier().addFraisDossier(frais);                m_parent.calculTotal();                if (!m_parent.getDossier().isNewreccord())                    m_parent.getDossier().setModifreccord(true);                setVisible(false);                // srcastra.astra.gui.sys.utils.TableManage.deleteFromTable(this,m_parent,m_table);                m_table.repaint();                dispose();            } else {                for (int i = 0; i < this.m_model.getRowCount(); i++)                {                    srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) m_model.getClassAffichage(i);                    produitaff.m_delete = new Boolean(false);                }                setVisible(false);                m_table.repaint();                dispose();            }        }    }//GEN-LAST:event_grp_But_ValiderActionPerformed    /**     * Closes the dialog     */    private void closeFrame() {        setVisible(false);        for (int i = 0; i < this.m_model.getRowCount(); i++)        {            srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff = (srcastra.astra.sys.classetransfert.dossier.ProduitAffichage) m_model.getClassAffichage(i);            produitaff.m_delete = new Boolean(false);        }        setVisible(false);        m_table.repaint();        dispose();    }    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog        closeFrame();    }//GEN-LAST:event_closeDialog    /**     * @param args the command line arguments     */    public static void main(String args[]) {    }    // Variables declaration - do not modify//GEN-BEGIN:variables    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_dateAnnulation;    private javax.swing.JButton grp_But_Annuler;    private javax.swing.JButton grp_But_Valider;    private javax.swing.JLabel grp_Label_montant;    private javax.swing.JLabel grp_Label_pourcent;    private javax.swing.JLabel grp_Libele_intitule;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_MontantAnnulation;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_frais_annul;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_intitule;    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pourcent;    private javax.swing.JLabel jLabel1;    private javax.swing.JLabel jLabel2;    private javax.swing.JPanel jPanel1;    private javax.swing.JPanel jPanel2;    private javax.swing.JPanel jPanel3;    private javax.swing.JPanel jPanel4;    // End of variables declaration//GEN-END:variables    private double m_montant_annul;    private double m_frais_annulation;    private ProduitTableModelGlobal m_model;    private javax.swing.JTable m_table;    private ValidateField validate = new ValidateField() {        public void actionPerformed(ActionEvent evt) {            grp_But_ValiderActionPerformed(evt);        }    };}