/*
 * SupReducPanel.java
 *
 * Created on 4 octobre 2002, 13:40
 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;
// Interfaces

import srcastra.astra.gui.modules.InternScreenModule;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
// event
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
// srcastra divers
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.components.fx.*;
import srcastra.astra.gui.components.AstraComponent;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.components.actions.*;
import srcastra.astra.gui.sys.formVerification.*;
import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;
import srcastra.astra.gui.components.combobox.liste.*;
import srcastra.astra.gui.event.ValidateField;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.dossier.assurance.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.sys.classetransfert.dossier.hotel.*;
import srcastra.astra.sys.classetransfert.dossier.bateau.*;
import srcastra.astra.sys.classetransfert.dossier.train.*;
import srcastra.astra.sys.classetransfert.dossier.voitureLocation.*;
import srcastra.astra.sys.classetransfert.dossier.taxi.*;
import srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.*;

/**
 * @author Sébastien
 */
public class SupReducPanelEdition extends javax.swing.JPanel implements InternScreenModule, ToolBarComposer, InterfacePanel {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    /////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Creates new form SupReducPanel
     */
    SupReducListeTableModel m_model;
    double initPrice;
    boolean soumisTva;

    public SupReducPanelEdition(SupreducEditionGui parent2, int typeProduct) {
        m_parent = parent2;
        m_productId = typeProduct;
        this.parent = m_parent.getMainScreenModule();
        initComponents();
        init();
        initListe();
        setBounds(236, 0, 468, 210);
        grp_Button_Grp_decision.setFocusable(false);
        clelibelle = grp_ACB_libele.getCleUnik();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INITIALISATION
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int clelibelle;

    public void init() {
        initComponents();
        this.composantToVerif = new AstraComponent[]{grp_CBox_supplement, grp_CBox_reduction, grp_CBox_SoumisTva, grp_TField_Libele,
                grp_ACB_libele, grp_TField_Prix, grp_TField_Quantite, grp_TField_Pax, grp_TField_pourcent, grp_ACB_Statut};
        //     this.tb_interaction = new ToolBarInteraction(parent, this, composantToVerif);
        //tb_interaction.setValidateActionEnvironnement(ToolBarInteraction.ACT_ENV_WITH_SWITCH);
        //-> Régistration des listeners pour la validité des composants
        //  for (int i=0; i < composantToVerif.length; i++) {
        //    composantToVerif[i].addActionListener(tb_interaction.getValidateActionListener());
        // }
        parent.setCurrentPanel(this);
        setDocument();
        buttonGroup1.add(grp_CBox_reduction.getCheckBox());
        buttonGroup1.add(grp_CBox_supplement.getCheckBox());
        grp_CBox_supplement.setSelected(true);
        // if (grp_ACB_libele.getSelectedIndex() <=0)  grp_ACB_libele.setSelectedIndex(0);
        //   grp_TField_TVA.addActionListener(m_parent.m_config.getValidate());
        //  grp_ACB_libele.addKeyListener(comboLibeleKeyListener);
        grp_TField_Prix.addKeyListener(calculTotal);
        grp_TField_Quantite.addKeyListener(calculTotal);
        grp_TField_Pax.addKeyListener(calculTotal);
        grp_TField_pourcent.addKeyListener(calculTotal);
        grp_TField_pourcent.addActionListener(m_parent.m_config.getValidate());
        grp_ACB_libele.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //   if(grp_ACB_libele.getCleUnik()!=clelibelle)
                grp_TField_Libele.setText(grp_ACB_libele.getText());
                clelibelle = grp_ACB_libele.getCleUnik();
                grp_ACB_libele.getGrp_TField_encode().setCaretPosition(0);
                if (grp_ACB_libele.getCleUnik() != 0) {
                    ((Sup_reduc_T) sup_reduc).setAclibelle(grp_ACB_libele.getCleUnik());
                    if (((Sup_reduc_T) sup_reduc).getAclibelle() != 0) {
                        CheckCptSupReduc.setSupReduc((Sup_reduc_T) sup_reduc, m_productId, ((Sup_reduc_T) sup_reduc).getAclibelle());
                        CheckCptSupReduc.checkGrpDec(((Sup_reduc_T) sup_reduc).getGroupdec(), m_productId, ((Sup_reduc_T) sup_reduc).getAclibelle());
                        reloadTableModel();
                    }
                }
            }
        });
        grp_ACB_libele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // if(grp_ACB_libele.getCleUnik()!=clelibelle)
                grp_TField_Libele.setText(grp_ACB_libele.getText());
                clelibelle = grp_ACB_libele.getCleUnik();
                grp_ACB_libele.getGrp_TField_encode().setCaretPosition(0);
                if (grp_ACB_libele.getCleUnik() != 0) {
                    ((Sup_reduc_T) sup_reduc).setAclibelle(grp_ACB_libele.getCleUnik());
                    if (((Sup_reduc_T) sup_reduc).getAclibelle() != 0) {
                        CheckCptSupReduc.checkGrpDec(((Sup_reduc_T) sup_reduc).getGroupdec(), m_productId, ((Sup_reduc_T) sup_reduc).getAclibelle());
                        reloadTableModel();
                    }
                }
            }
        });
        if (sup_reduc instanceof Sup_reduc_T)
            grp_Button_Grp_decision.setEnabled(false);
        // jButton1.setFocusable(false);
    }

    private void initListe() {
        m_model = new SupReducListeTableModel(parent.getServeur(), parent.getCurrentUser(), m_productId, parent, srcastra.astra.sys.configuration.AbstractRequete.SUP_RECUC, parent.getMainframe().getSupReducVector(), parent.getDossier().getClientContractuel().getLecleunik());
        m_model.loadata();
        grp_ACB_libele.setServeur(parent.getServeur());
        grp_ACB_libele.setLogin(parent.getCurrentUser());
        grp_ACB_libele.setModel(m_model);
        grp_ACB_libele.init2();
        grp_ACB_libele.setColToSearch(3);
    }

    private void setDocument() {
        grp_TField_Libele.setDocument(new DefaultMask(0, 44, parent.getCurrentUser().getLangage(), DefaultMask.ALL_LETTRE_IN_UPPERCASE));
        grp_TField_Prix.setDocument(new DecimalMask(6, 2, parent.getCurrentUser().getLangage()));
        grp_TField_Quantite.setDocument(new IntegerMask(1, 4, parent.getCurrentUser().getLangage()));
        grp_TField_Pax.setDocument(new DecimalMask(6, 2, parent.getCurrentUser().getLangage()));
        grp_TField_pourcent.setDocument(new DecimalMask(6, 2, parent.getCurrentUser().getLangage()));
        //grp_TField_TVA.setDocument(new DecimalMask(3, 2, parent.getCurrentUser().getLangage()));
    }

    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        grp_CBox_supplement = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        grp_CBox_reduction = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel6 = new javax.swing.JLabel();
        grp_CBox_SoumisTva = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel8 = new javax.swing.JLabel();
        grp_ACB_Statut = new srcastra.astra.gui.components.combobox.aCombo.ACombo();
        grp_ACB_libele = new srcastra.astra.gui.components.combobox.liste.Liste();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        grp_TField_Prix = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        grp_TField_Quantite = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel11 = new javax.swing.JLabel();
        grp_TField_Pax = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel9 = new javax.swing.JLabel();
        grp_TField_pourcent = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel12 = new javax.swing.JLabel();
        grp_Button_Grp_decision = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        grp_TField_Libele = new srcastra.astra.gui.components.textFields.ATextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        grp_Label_Total = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 5, true));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_ACB_libele"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel2, gridBagConstraints);

        grp_CBox_supplement.setEnabled(false);
        grp_CBox_supplement.setGrp_Comp_nextComponent(grp_CBox_reduction);
        grp_CBox_supplement.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_CBox_supplement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_CBox_supplementActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(grp_CBox_supplement, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_CBox_supplement"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel3.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_CBox_reduction"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel3.add(jLabel5, gridBagConstraints);

        grp_CBox_reduction.setEnabled(false);
        grp_CBox_reduction.setGrp_Comp_nextComponent(grp_CBox_SoumisTva);
        grp_CBox_reduction.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(grp_CBox_reduction, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_CBox_SoumisTva"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel6, gridBagConstraints);

        grp_CBox_SoumisTva.setEnabled(false);
        grp_CBox_SoumisTva.setGrp_Comp_nextComponent(grp_ACB_Statut);
        grp_CBox_SoumisTva.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_CBox_SoumisTva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_CBox_SoumisTvaActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_CBox_SoumisTva, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_ACB_Statut"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel3.add(jLabel8, gridBagConstraints);

        grp_ACB_Statut.setEnabled(false);
        grp_ACB_Statut.setGrp_Comp_nextComponent(grp_ACB_libele);
        grp_ACB_Statut.setModel(new StatusListModel(parent.getServeur(), parent.getCurrentUser()));
        grp_ACB_Statut.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel3.add(grp_ACB_Statut, gridBagConstraints);

        grp_ACB_libele.setCanbenull(true);
        grp_ACB_libele.setEnabled(false);
        grp_ACB_libele.setGrp_Comp_nextComponent(grp_TField_Libele);
        grp_ACB_libele.setPreferredSize(new java.awt.Dimension(100, 18));
        grp_ACB_libele.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_ACB_libele.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel3.add(grp_ACB_libele, gridBagConstraints);

        jPanel5.add(jPanel3);

        jPanel1.add(jPanel5);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 130));
        jPanel1.add(jSeparator1);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jPanel6.setPreferredSize(new java.awt.Dimension(208, 120));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        grp_TField_Prix.setEnabled(false);
        grp_TField_Prix.setGrp_Comp_nextComponent(grp_TField_Pax);
        grp_TField_Prix.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_Prix.setLastFocusedComponent(true);
        grp_TField_Prix.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Prix.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Prix, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Prix"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel3, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Quantite"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel10, gridBagConstraints);

        grp_TField_Quantite.setEnabled(false);
        grp_TField_Quantite.setGrp_Comp_nextComponent(grp_TField_pourcent);
        grp_TField_Quantite.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_Quantite.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Quantite.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Quantite, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_TField_Pax"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel11, gridBagConstraints);

        grp_TField_Pax.setEnabled(false);
        grp_TField_Pax.setGrp_Comp_nextComponent(grp_TField_Quantite);
        grp_TField_Pax.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_Pax.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Pax.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_Pax, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_TField_pourcent"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel7.add(jLabel9, gridBagConstraints);

        grp_TField_pourcent.setEnabled(false);
        grp_TField_pourcent.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        grp_TField_pourcent.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_pourcent.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel7.add(grp_TField_pourcent, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel12.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel7.add(jLabel12, gridBagConstraints);

        jPanel6.add(jPanel7);

        grp_Button_Grp_decision.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Button_Grp_decision.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane").getString("param"));
        grp_Button_Grp_decision.setPreferredSize(new java.awt.Dimension(100, 20));
        grp_Button_Grp_decision.setRequestFocusEnabled(false);
        grp_Button_Grp_decision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Button_Grp_decisionActionPerformed(evt);
            }
        });

        jPanel6.add(grp_Button_Grp_decision);

        jPanel1.add(jPanel6);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jPanel8.setPreferredSize(new java.awt.Dimension(400, 25));
        grp_TField_Libele.setEnabled(false);
        grp_TField_Libele.setGrp_Comp_nextComponent(grp_TField_Prix);
        grp_TField_Libele.setPreferredSize(new java.awt.Dimension(400, 18));
        grp_TField_Libele.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_Libele.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        jPanel8.add(grp_TField_Libele);

        jPanel1.add(jPanel8);

        jSeparator2.setPreferredSize(new java.awt.Dimension(400, 5));
        jPanel1.add(jSeparator2);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane").getString("sup_title"));
        jPanel2.add(jLabel1);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("grp_Label_Total"));
        jPanel4.add(jLabel7);

        grp_Label_Total.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_Label_Total.setForeground(new java.awt.Color(153, 0, 0));
        grp_Label_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grp_Label_Total.setText("0");
        grp_Label_Total.setPreferredSize(new java.awt.Dimension(70, 13));
        grp_Label_Total.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(grp_Label_Total);

        add(jPanel4, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

    private void grp_CBox_SoumisTvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_CBox_SoumisTvaActionPerformed
        modify = true;
        sup_reduc.getGroupdec().setGnfrtvaComptabiliserVente(grp_CBox_SoumisTva.isSelected() ? 1 : 0);
        sup_reduc.getGroupdec().setModifyreccord(true);
    }//GEN-LAST:event_grp_CBox_SoumisTvaActionPerformed

    boolean modify;

    private void setModifyGrpDec() {
        if (modify == true) {
            sup_reduc.getGroupdec().setGnfrtvaComptabiliserVente(grp_CBox_SoumisTva.isSelected() ? 1 : 0);
            sup_reduc.getGroupdec().setModifyreccord(true);
        }
    }

    private void grp_Button_Grp_decisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Button_Grp_decisionActionPerformed
        produit_T produit = null;
        setModifyGrpDec();
        if (sup_reduc instanceof Sup_reduc_T) {
            if (grp_ACB_libele.getCleUnik() != 0) {
                ((Sup_reduc_T) sup_reduc).setAclibelle(grp_ACB_libele.getCleUnik());
                if (((Sup_reduc_T) sup_reduc).getAclibelle() != 0) {
                    CheckCptSupReduc.checkGrpDec(((Sup_reduc_T) sup_reduc).getGroupdec(), m_productId, ((Sup_reduc_T) sup_reduc).getAclibelle());
                }
            }
        }
        parent.getMainframe().setGrpdec(sup_reduc.getGroupdec());
        parent.getMainframe().afficheNestedGroupdec(new ActionEvent(parent.getMainframe().getGrp_MenuItem_Groupe_Dec(), ActionEvent.ACTION_PERFORMED, "nnnnn"), true, this.sup_reduc, this);
    }//GEN-LAST:event_grp_Button_Grp_decisionActionPerformed

    private void grp_ACB_libeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_ACB_libeleActionPerformed
    }//GEN-LAST:event_grp_ACB_libeleActionPerformed

    private void grp_CBox_supplementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_CBox_supplementActionPerformed
// Add your handling code here:
    }//GEN-LAST:event_grp_CBox_supplementActionPerformed
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => LISTENERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* private KeyListener comboLibeleKeyListener = new KeyListener() {
    public void keyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    if (grp_ACB_libele.getSelectedCleUnik() == 0) {
    if (!grp_TField_Libele.isEnabled()) grp_TField_Libele.setEnabled(true);
    grp_TField_Libele.requestFocus();
    }
    else {
    if (!grp_TField_Prix.isEnabled()) grp_TField_Prix.setEnabled(true);
    grp_TField_Prix.requestFocus();
    }
    }
    }
    public void keyReleased(KeyEvent evt) {
    }
    public void keyTyped(KeyEvent evt) {
    }
    };*/
    private KeyListener calculTotal = new KeyListener() {
        public void keyPressed(KeyEvent evt) {
        }

        public void keyReleased(KeyEvent evt) {
            calcul();
        }

        public void keyTyped(KeyEvent evt) {
        }
    };

    private void calcul() {
        double prix = 0d;
        double qua = 0d;
        double pax = 0d;
        double pc = 0d;
        try {
            prix = Double.parseDouble(grp_TField_Prix.getText());
            if (grp_CBox_reduction.isSelected())
                prix = -prix;
            qua = Double.parseDouble(grp_TField_Quantite.getText());
            pax = Double.parseDouble(grp_TField_Pax.getText());
            pc = Double.parseDouble(grp_TField_pourcent.getText());
        }
        catch (Exception e) {
        }
        finally {
            total = ((prix * qua * pax) / 100d) * pc;
            total = srcastra.astra.sys.compta.MathRound.roundThisToDouble(total);
            grp_Label_Total.setText("" + total);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AU BEANS

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getLibele(int cleUnik) {
        grp_ACB_libele.setCleUnik(cleUnik);
        return grp_ACB_libele.getText();
    }

    public void removeElem(int cleUnik) {
//   ((TypeSupReducTicketListModel) grp_ACB_libele.getModel()).removeData(cleUnik);
/*java.util.Hashtable htbl = parent.getDossier().returnCorrectArrayList(produit_T.AV);
java.util.Iterator it = htbl.values().iterator();
srcastra.astra.sys.classetransfert.dossier.produit_T prod = null;
while(it.hasNext()) {
prod = (srcastra.astra.sys.classetransfert.dossier.produit_T) it.next();
prod.
*/
    }

    public void resetLibele() {
//    TypeSupReducTicketListModel tsr = (TypeSupReducTicketListModel) grp_ACB_libele.getModel();
//  tsr.clearList();
// tsr.needData();
    }

    public void fxPanelDiselected() {
//parentPane.removeSupReducPanel();
    }

    public void fxPanelSelected() {
//parentPane.displaySupReducPanel();
    }

    public boolean isGoodForInsertion() {
        if (grp_TField_Libele.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void selectLibele() {
        grp_TField_Libele.requestFocus();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => FONCTIONS APPARENTES AU TRANSFERT DE DONNEE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Permet au parent de lancer le chargement des données au
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * sein de liste (Ici : ListSelector)
 */
    public void chargeData() {
        parent.getMainframe().setGrpdec(sup_reduc.getGroupdec());
        if (sup_reduc.getGroupdec().getGnfrtvaComptabiliserVente() == 1) {
            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]ok soumis à la tva");
            grp_CBox_SoumisTva.setSelected(true);
        } else {
            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]non soumis à la tva");
            grp_CBox_SoumisTva.setSelected(false);
        }
        if (sup_reduc instanceof Sup_reduc_T) {
            Sup_reduc_T sup = (Sup_reduc_T) sup_reduc;
            if (sup.getReduc() == 1) grp_CBox_reduction.setSelected(true);
            if (sup.getSup() == 1) grp_CBox_supplement.setSelected(true);
            grp_TField_Libele.setText("" + sup.getFreeLibelle());
            grp_ACB_libele.setCleUnik(sup.getAclibelle());
            grp_TField_Prix.setText("" + Math.abs(MathRound.roundThisToDouble(sup.getPrix())));
            grp_TField_Quantite.setText("" + sup.qua);
            grp_TField_Pax.setText("" + sup.pax);
            grp_TField_pourcent.setText("" + sup.prct);
            grp_ACB_Statut.setSelectedCleUnik(sup.getSup_statutCleuUnik());
            initPrice = sup.getValeur_tot();
        } else {
            produit_T ass = (produit_T) sup_reduc;
            grp_CBox_supplement.setSelected(false);
            grp_CBox_reduction.setSelected(false);
            String stras = "";
            try {
                stras = java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/SupReducPane", parent.getCurrentUser().getLangage()).getString("base");
            }
            finally {
                grp_TField_Libele.setText(stras);
            }
            grp_TField_Prix.setText("" + ass.getAt_val_vente());
            grp_TField_Quantite.setText("" + ass.getQua());
            grp_TField_Pax.setText("" + ass.getPax());
            grp_TField_pourcent.setText("" + ass.getPrct());
// grp_TField_TVA.setText("" + ass.getSup_tva());
            grp_ACB_Statut.setSelectedCleUnik(ass.getStatut());
            initPrice = ass.getValeur_tot();
        }
    }

    private void chargeSup_reduc_classe() {
        produit_T prod = getTherightProduct();
        if (sup_reduc instanceof Sup_reduc_T) {
            Sup_reduc_T sup = (Sup_reduc_T) sup_reduc;
            sup.setParent(prod);
            setModifyGrpDec();
            if (grp_CBox_reduction.isSelected()) {
                sup.setReduc(1);
                sup.setSup(0);
            }
            if (grp_CBox_supplement.isSelected()) {
                sup.setSup(1);
                sup.setReduc(0);
            }
            double prix;
            if (grp_TField_Prix.equals("")) grp_TField_Prix.setText("0");
            try {
                prix = Double.parseDouble(grp_TField_Prix.getText());
                if (grp_CBox_reduction.isSelected())
                    prix = -prix;
            }
            catch (NumberFormatException e) {
                prix = 0;
            }
            sup.setPrix(prix);
            if (grp_CBox_SoumisTva.isSelected()) {
                sup.getGroupdec().setGnfrtvaComptabiliserVente(1);
            } else {
                sup.getGroupdec().setGnfrtvaComptabiliserVente(0);
            }
            sup.setValeur_tot(this.total);
            sup.setFreeLibelle(grp_TField_Libele.getText());
            if (((Sup_reduc_T) sup_reduc).getAclibelle() > -1) {
                sup.setAclibelle(grp_ACB_libele.getCleUnik());
            }
            float tar = 0f;
            sup.setSup_statutCleuUnik(grp_ACB_Statut.getSelectedCleUnik());
            int a = 0;
            try {
                sup.qua = Integer.parseInt(grp_TField_Quantite.getText());
            }
            catch (NumberFormatException nn) {
                sup.qua = 1;
            }
            int b = 0;
            try {
                sup.pax = Integer.parseInt(grp_TField_Pax.getText());
            }
            catch (NumberFormatException nn) {
                sup.pax = 1;
            }
            float c = 0;
            try {
                sup.prct = Float.parseFloat(grp_TField_pourcent.getText());
            }
            catch (NumberFormatException nn) {
                sup.prct = 100;
            }
        } else {
            int pa;
            int qua;
            float prct;
            try {
                qua = Integer.parseInt(grp_TField_Quantite.getText());
            }
            catch (NumberFormatException nn) {
                qua = 1;
            }
            int b = 0;
            try {
                pa = Integer.parseInt(grp_TField_Pax.getText());
            }
            catch (NumberFormatException nn) {
                pa = 1;
            }
            float c = 0;
            try {
                prct = Float.parseFloat(grp_TField_pourcent.getText());
            }
            catch (NumberFormatException nn) {
                prct = 1;
            }
            double prix;
            if (grp_TField_Prix.equals("")) grp_TField_Prix.setText("0");
            try {
                prix = Double.parseDouble(grp_TField_Prix.getText());
            }
            catch (NumberFormatException e) {
                prix = 0;
            }
            if (grp_CBox_SoumisTva.isSelected()) {
                prod.getGroupdec().setGnfrtvaComptabiliserVente(1);
            } else {
                prod.getGroupdec().setGnfrtvaComptabiliserVente(0);
            }
            if (modify)
                prod.getGroupdec().setModifyreccord(true);
            prod.setAt_val_vente(prix);
            prod.setPax(pa);
            prod.setQua(qua);
            prod.setPrct(prct);
            prod.setLibelleCompta(grp_TField_Libele.getText());
/*  switch (m_productId) {
case InterfaceProduit.AS :
((Assurance_T)prod).setAce_montantAssurance(prix);
((Assurance_T)prod).setPax(pa);
((Assurance_T)prod).setQua(qua);
((Assurance_T)prod).setPrct(prct);
break;
case InterfaceProduit.AV :
((Avion_ticket_T)prod).setAt_val_vente(prix);
((Avion_ticket_T)prod).setPax(pa);
((Avion_ticket_T)prod).setQua(qua);
((Avion_ticket_T)prod).setPrct(prct);
break;
case InterfaceProduit.BA :
((Bateau_T)prod).setBat_montant(new Float(prix).doubleValue());
((Bateau_T)prod).setPax(pa);
((Bateau_T)prod).setQua(qua);
((Bateau_T)prod).setPrct(prct);
break;
case InterfaceProduit.BRO :
((Brochure_T)prod).setBro_valeur(prix);
((Brochure_T)prod).setPax(pa);
((Brochure_T)prod).setQua(qua);
((Brochure_T)prod).setPrct(prct);
break;
case InterfaceProduit.HO :
((Hotel_T)prod).setHl_valeur(prix);
((Hotel_T)prod).setPax(pa);
((Hotel_T)prod).setQua(qua);
((Hotel_T)prod).setPrct(prct);
break;
case InterfaceProduit.TAX :
((Taxi_T)prod).setTax_montant(prix);
((Taxi_T)prod).setPax(pa);
((Taxi_T)prod).setQua(qua);
((Taxi_T)prod).setPrct(prct);
break;
case InterfaceProduit.TR :
((Train_T)prod).setTrn_montant(prix);
((Train_T)prod).setPax(pa);
((Train_T)prod).setQua(qua);
((Train_T)prod).setPrct(prct);
break;
case InterfaceProduit.VO :
((VoitureLocation_T)prod).setVl_montant(prix);
((VoitureLocation_T)prod).setPax(pa);
((VoitureLocation_T)prod).setQua(qua);
((VoitureLocation_T)prod).setPrct(prct);
break;
}*/
            prod.setValeur_tot(total);
            float sar = 0f;
            prod.setStatut(grp_ACB_Statut.getSelectedCleUnik());
            this.sup_reduc = prod;
/*   switch (m_productId) {
case InterfaceProduit.AS :
((Assurance_T)prod).setSup_statutCleuUnik(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.AV:
((Avion_ticket_T)prod).setAt_statutCleuUnik(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.BA:
((Bateau_T)prod).setBat_statut(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.BRO:
((Brochure_T)prod).setBro_statutCleUnik(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.HO:
((Hotel_T)prod).setHl_statutCleUnik(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.TAX:
((Taxi_T)prod).setTax_statut(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.TR:
((Train_T)prod).setTrn_statut(grp_ACB_Statut.getSelectedCleUnik());
break;
case InterfaceProduit.VO:
((VoitureLocation_T)prod).setVl_statut(grp_ACB_Statut.getSelectedCleUnik());
break;
}*/
/*    int a = 0;
try {
a = Integer.parseInt(grp_TField_Quantite.getText());
}
finally {
prod.setQua(a);;
}
int b = 0;
try {
b = Integer.parseInt(grp_TField_Pax.getText());
}
finally {
prod.setPax(b);
}
float c = 0;
try {
c = Float.parseFloat(grp_TField_pourcent.getText());
}
finally {
prod.setPrct(c);
}*/
        }
    }

    private void chargeSup_reduc_classe_insert() {
        chargeSup_reduc_classe();
        Sup_reduc_T sup = (Sup_reduc_T) sup_reduc;
        if (sup.getAclibelle() != 0) {
            CheckCptSupReduc.checkGrpDec(sup.getGroupdec(), m_productId, sup.getAclibelle());
        }
        sup.setSup_reduc_cleunik(parent.getSupReducCompteur());
        parent.decrementSupReducCompteur();
        sup.setNewreccord(true);
    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
    }

    private void setBoolean() {
        produit_T prod = null;
        switch (m_productId) {
            case InterfaceProduit.AV:
                prod = parent.getTicket();
                break;
            case InterfaceProduit.AS:
                prod = parent.getAssurance();
                break;
            case InterfaceProduit.BA:
                prod = parent.getBateau();
                break;
            case InterfaceProduit.BRO:
                prod = parent.getBrochure();
                break;
            case InterfaceProduit.HO:
                prod = parent.getHotel();
                break;
            case InterfaceProduit.TR:
                prod = parent.getTrain();
                break;
            case InterfaceProduit.VO:
                prod = parent.getVoitureLocation();
                break;
            case InterfaceProduit.TAX:
                prod = parent.getTaxi();
                break;
            case InterfaceProduit.CAR:
                prod = parent.getCar();
                break;
            case InterfaceProduit.DIV:
                prod = parent.getDiverst();
                break;
        }
        if (!parent.getDossier().isNewreccord())
            parent.getDossier().setModifreccord(true);
        if (!prod.isIsnewreccord())
            prod.setModify(true);
        prod.setLocalyModify(true);
    }

    public produit_T returnParentProduct() {
        produit_T prod = null;
        switch (m_productId) {
            case InterfaceProduit.AV:
                prod = parent.getTicket();
                break;
            case InterfaceProduit.AS:
                prod = parent.getAssurance();
                break;
            case InterfaceProduit.BA:
                prod = parent.getBateau();
                break;
            case InterfaceProduit.BRO:
                prod = parent.getBrochure();
                break;
            case InterfaceProduit.HO:
                prod = parent.getHotel();
                break;
            case InterfaceProduit.TR:
                prod = parent.getTrain();
                break;
            case InterfaceProduit.VO:
                prod = parent.getVoitureLocation();
                break;
            case InterfaceProduit.TAX:
                prod = parent.getTaxi();
                break;
            case InterfaceProduit.DIV:
                prod = parent.getDiverst();
                break;
        }
        return prod;
    }

    public void dbInsert() {
        produit_T prod = getTherightProduct();
        grp_TField_Prix.requestFocus();
        chargeSup_reduc_classe_insert();
        Sup_reduc_T tmpSupReduc = (Sup_reduc_T) this.sup_reduc;
        tmpSupReduc.setNewreccord(true);
        tmpSupReduc.calculMontantTotal();
        tmpSupReduc.setParent(prod);
        prod.addSup_Reduc(tmpSupReduc);
        setBoolean();
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
    private produit_T getTherightProduct() {
        switch (m_productId) {
            case InterfaceProduit.AV:
                return parent.getTicket();
            case InterfaceProduit.AS:
                return parent.getAssurance();
            case InterfaceProduit.BA:
                return parent.getBateau();
            case InterfaceProduit.BRO:
                return parent.getBrochure();
            case InterfaceProduit.HO:
                return parent.getHotel();
            case InterfaceProduit.TR:
                return parent.getTrain();
            case InterfaceProduit.VO:
                return parent.getVoitureLocation();
            case InterfaceProduit.TAX:
                return parent.getTaxi();
            case InterfaceProduit.CAR:
                return parent.getCar();
            case InterfaceProduit.DIV:
                return parent.getDiverst();
        }
        return null;
    }

    private void setComptaModify(InterfaceProduit prod) {
        if (soumisTva != grp_CBox_SoumisTva.isSelected()) {
            sup_reduc.getGroupdec().setGnfrtvaComptabiliserVente(grp_CBox_SoumisTva.isSelected() ? 1 : 0);
            prod.getGroupdec().setModifyreccord(true);
        }
        if (prod.getValeur_tot() != initPrice) {
            parent.getDossier().setComptaModify(true);
            parent.getDossier().setModifreccord(true);
        }
        if (prod.getGroupdec().isModifyreccord()) {
            parent.getDossier().setComptaModify(true);
            parent.getDossier().setModifreccord(true);
        }

    }

    public void dbUpdate() {
        chargeSup_reduc_classe();
        setComptaModify(sup_reduc);
        if (sup_reduc instanceof Sup_reduc_T) {
            Sup_reduc_T sup = (Sup_reduc_T) sup_reduc;
            sup.calculMontantTotal();
            if (!sup.isIsnewreccord())
                sup.setModifyreccord(true);
            getTherightProduct().addSup_Reduc(sup);
        } else {
            if (!sup_reduc.isIsnewreccord())
                sup_reduc.setModify(true);
            switch (m_productId) {
                case InterfaceProduit.AV:
                    parent.getTicket().calculMontantTotal();
                    parent.getTicket().prepareAffichage();
                    break;
                case InterfaceProduit.CAR:
                    parent.getCar().calculMontantTotal();
                    parent.getCar().prepareAffichage();
                    break;
                case InterfaceProduit.AS:
                    parent.getAssurance().calculMontantTotal();
                    parent.getAssurance().prepareAffichage();
                    break;
                case InterfaceProduit.BA:
                    parent.getBateau().calculMontantTotal();
                    parent.getBateau().prepareAffichage();
                    break;
                case InterfaceProduit.BRO:
                    parent.getBrochure().calculMontantTotal();
                    parent.getBrochure().prepareAffichage();
                    break;
                case InterfaceProduit.HO:
                    parent.getHotel().calculMontantTotal();
                    parent.getHotel().prepareAffichage();
                    break;
                case InterfaceProduit.TR:
                    parent.getTrain().calculMontantTotal();
                    parent.getTrain().prepareAffichage();
                    break;
                case InterfaceProduit.VO:
                    parent.getVoitureLocation().calculMontantTotal();
                    parent.getVoitureLocation().prepareAffichage();
                    break;
                case InterfaceProduit.DIV:
                    parent.getDiverst().calculMontantTotal();
                    parent.getDiverst().prepareAffichage();
                    break;
                case InterfaceProduit.TAX:
                    parent.getTaxi().calculMontantTotal();
                    parent.getTaxi().prepareAffichage();
                    break;
            }
        }
        setBoolean();
//displayReadMode();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE A L' AFFICHAGE DES DONNEES

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Affichage en Mode disable
 */
    public void displayDisableMode() {
//  grp_TField_Prix.removeActionListener(validateAndDoPrevious);
        tb_interaction.enableValidateActionListener(false);
        action = ActionToolBar.ACT_DISABLE;
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
    }

    public void salutParDespoir() {
        parent.requestOwnFocus();
    }

    private void chargeDefaultValue() {
        grp_TField_Prix.setText("0");
        grp_TField_Quantite.setText("1");
        grp_TField_pourcent.setText("100");
        if (parent.getDossier().getPassager() != null)
            grp_TField_Pax.setText("" + parent.getDossier().getPassager().size());
    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
        total = 0;
        action = ActionToolBar.ACT_INSERT;
        sup_reduc = new Sup_reduc_T();
        try {
            sup_reduc.setGroupdec((Grpdecision_T) getTherightProduct().getGroupdecBase().clone());
        } catch (CloneNotSupportedException cn) {
            cn.printStackTrace();
        }
        openFields(ActionToolBar.ACT_INSERT);
        calcul();
    }

    public void openFields(int mode) {
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(true);
            composantToVerif[i].setLastFocusedComponent(true);
            if (composantToVerif[i] instanceof Liste) ;
            else
                composantToVerif[i].setText("");
            composantToVerif[i].clearIcon();
        }
        if (mode == ActionToolBar.ACT_INSERT) {
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            grp_CBox_supplement.setVisible(true);
            grp_CBox_reduction.setVisible(true);
            grp_ACB_Statut.setGrp_Comp_nextComponent(grp_ACB_libele);
            chargeDefaultValue();
            if (sup_reduc.getGroupdec().getGnfrtvaComptabiliserVente() == 1) {
                Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]ok soumis à la tva");
                grp_CBox_SoumisTva.setSelected(true);
            } else {
                grp_CBox_SoumisTva.setSelected(false);
                Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]non soumis à la tva");
            }
            for (int i = 0; i < composantToVerif.length; i++) {
                composantToVerif[i].clearIcon();
            }
            grp_CBox_supplement.requestFocus();
        } else if (mode == ActionToolBar.ACT_MODIFY) {
            chargeData();
            if (!(sup_reduc instanceof Sup_reduc_T)) {
                jLabel4.setVisible(false);
                jLabel5.setVisible(false);
                grp_CBox_supplement.setVisible(false);
                grp_CBox_reduction.setVisible(false);
                ((produit_T) sup_reduc).resetLibelleCompta(parent.getDossier().getClientContractuel().getLecleunik());
                grp_TField_Libele.setText(((produit_T) sup_reduc).getLibelleCompta());
                grp_ACB_libele.setEnabled(false);
                grp_ACB_Statut.setGrp_Comp_nextComponent(grp_TField_Libele);
                for (int i = 0; i < composantToVerif.length; i++) {
                    composantToVerif[i].clearIcon();
                }
                grp_CBox_SoumisTva.requestFocus();
            } else {
                jLabel4.setVisible(true);
                jLabel5.setVisible(true);
                grp_CBox_supplement.setVisible(true);
                grp_CBox_reduction.setVisible(true);
                if (((Sup_reduc_T) sup_reduc).getAclibelle() > -1) {//si le sup reduc est une taxe local un fees, etc déjà encodé dans le bille alors on ne peut plus modifier son libelle
                    grp_ACB_libele.setEnabled(true);
                    grp_ACB_libele.getGrp_TField_encode().setCaretPosition(0);
                    grp_ACB_Statut.setGrp_Comp_nextComponent(grp_ACB_libele);
                } else {
                    grp_ACB_Statut.setGrp_Comp_nextComponent(grp_TField_Libele);
                    grp_ACB_libele.setEnabled(false);
                }
                for (int i = 0; i < composantToVerif.length; i++) {
                    composantToVerif[i].clearIcon();
                }
                grp_CBox_supplement.requestFocus();
            }
        }
    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
//  tb_interaction.enableValidateActionListener(false);
        action = ActionToolBar.ACT_READ;
// chargeData();
// chargePassager();
        for (int i = 0; i < composantToVerif.length; i++) {
            composantToVerif[i].setEnabled(false);
            composantToVerif[i].setLastFocusedComponent(false);
        }
/*  parent.setCurrentActionEnabled(new int[] { ActionToolBar.DO_MODIFY,
ActionToolBar.DO_CANCEL,
ActionToolBar.DO_DELETE });*/
// this.requestFocus();
        calcul();
    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
        total = 0;
        action = ActionToolBar.ACT_MODIFY;
        openFields(ActionToolBar.ACT_MODIFY);
        calcul();
    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => METHODE APPARENTE AUX APPELS DE LA TOOLBAR

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Spécifie le composant qui implémente cette fonction comme
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * le composant qui pilote l'actionToolBar
 */
    public void setThisAsToolBarComponent() {
    }

    public void doAccept() {
        switch (action) {
            case ActionToolBar.ACT_INSERT:
                dbInsert();
                if (!parent.getDossier().isNewreccord())
                    parent.getDossier().setModifreccord(true);
//if(parent.getTicket().isIsattached())
                if (!parent.getTicket().isIsnewreccord())
                    parent.getTicket().setModify(true);
                displayInsertMode();
                break;
            case ActionToolBar.ACT_MODIFY:
                dbUpdate();
//if(parent.getTicket().isIsattached())
                if (!parent.getDossier().isNewreccord())
                    parent.getDossier().setModifreccord(true);
                if (!parent.getTicket().isIsnewreccord())
                    parent.getTicket().setModify(true);
                displayReadMode();
                break;
        }
    }

    public void doCancel() {
/*   switch (action) {
case ActionToolBar.ACT_INSERT :
action = ActionToolBar.ACT_READ;
internParent.doCancel();
break;
case ActionToolBar.ACT_MODIFY :
displayReadMode();
break;
case ActionToolBar.ACT_READ :
internParent.doCancel();
break;
}*/
        displayReadMode();
    }

    public void doClose() {
    }

    public void doCreate() {
    }

    public void doDelete() {
    }

    public void doHelp() {
    }

    public void doModify() {
    }

    public void doNext() {
    }

    public void doPrevious() {
    }

    public void doPrint() {
    }

    public void doSwitch() {
    }

    public int[] getDefaultActionToolBarMask() {
        return new int[0];
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Champs de la classe
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SupreducEditionGui m_parent;
    private DossierMainScreenModule parent;
    private int action;
    private int dr_cleUnik;
    private JPanelSelectionFxManager fx_manager;
    private ToolBarComposer internParent;
    private AstraComponent[] composantToVerif;
    private ToolBarInteraction tb_interaction;
    private InterfaceProduit sup_reduc;
    private ToolBarComposer supReduc;
    private int m_productId;
    private double total;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// => Graphic Component
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private srcastra.astra.gui.components.combobox.aCombo.ACombo grp_ACB_Statut;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_ACB_libele;
    private javax.swing.JButton grp_Button_Grp_decision;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_SoumisTva;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_reduction;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_supplement;
    private javax.swing.JLabel grp_Label_Total;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Libele;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Pax;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Prix;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Quantite;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_pourcent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BEANS PROPERTIES GET/SET SUPPORT

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Permet à la classe qui implémente cette méthode de se
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * référencer auprès d' ActionToolBar
 *
 * @return le n° de l'action
 */
    public int getAction() {
        return this.action;
    }

    /**
     * Sert à recevoir le titre de son parent
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        return "# Suppléments & Réductions #";
    }

    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 0 pour lecture
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 1 pour création
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 2 pour modification
     *
     * @param action type d'action
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
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
    public srcastra.astra.gui.components.fx.JPanelSelectionFxManager getFx_manager() {
        return fx_manager;
    }

    /**
     * Setter for property fx_manager.
     *
     * @param fx_manager New value of property fx_manager.
     */
    public void setFx_manager(srcastra.astra.gui.components.fx.JPanelSelectionFxManager fx_manager) {
        this.fx_manager = fx_manager;
    }

    /**
     * Getter for property sup_reduc.
     *
     * @return Value of property sup_reduc.
     */
    public srcastra.astra.sys.classetransfert.dossier.InterfaceProduit getSup_reduc() {
        return sup_reduc;
    }

    /**
     * Setter for property sup_reduc.
     *
     * @param sup_reduc New value of property sup_reduc.
     */
    public void setSup_reduc(Object sup_reduc) {
        this.sup_reduc = (srcastra.astra.sys.classetransfert.dossier.InterfaceProduit) sup_reduc;
        System.out.println("[****\nnnSupereDucadded");
        if (this.sup_reduc != null) {
            soumisTva = this.sup_reduc.getGroupdec().getGnfrtvaComptabiliserVente() == 1 ? true : false;
        }
    }

    public void goUp() {
    }

    public java.awt.Component m_getGeneriqueTable() {
        return null;
    }

    public void moveInTable(int direction) {
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
        return 1;
    }

    public int doModify(boolean sw) {
        displayUpdateMode();
        return 1;
    }

    public int doPrevious(boolean sw) {
        int retval = 1;
        if (isGoodForInsertion()) {
            if (action == ActionToolBar.ACT_MODIFY) {
                dbUpdate();
                action = ActionToolBar.ACT_READ;
            } else if (action == ActionToolBar.ACT_INSERT) {
                if (!m_parent.m_config.test)
                    dbInsert();
                parent.getDossier().setComptaModify(true);
                action = ActionToolBar.ACT_READ;
            }
        } else {
            parent.getMessageManager().showMessageDialog(m_parent.m_parent.getOwner(), "supReducErreurLibele", "supReducErreurLibele_title", parent.getCurrentUser());
            selectLibele();
            retval = 0;
        }
        return retval;
    }

    public Object getSupReduc2(int i) {
        return null;
    }

    public java.awt.Component getTable() {
        return null;
    }

    public void reloadTableModel() {
        if (sup_reduc.getGroupdec().getGnfrtvaComptabiliserVente() == 1) {
            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]ok soumis à la tva");
            grp_CBox_SoumisTva.setSelected(true);
        } else {
            grp_CBox_SoumisTva.setSelected(false);
            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[****************]non soumis à la tva");
        }
        if (sup_reduc instanceof Sup_reduc_T) {
            if (((Sup_reduc_T) sup_reduc).getSup() == 1) {
                grp_CBox_supplement.setSelected(true);
            } else if (((Sup_reduc_T) sup_reduc).getReduc() == 1) {
                grp_CBox_reduction.setSelected(true);
            }
        }
    }

    public void doF10() {
    }

    public void addKeystroque() {
    }

    public void doF7() {
    }
}
