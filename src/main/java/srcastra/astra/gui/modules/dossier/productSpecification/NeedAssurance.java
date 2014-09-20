/*















 * NeedAssurance.java















 *















 * Created on 4 décembre 2002, 11:23















 */
package srcastra.astra.gui.modules.dossier.productSpecification;

import srcastra.astra.gui.MainFrame;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.components.checkbox.FakeDossierModule;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
//import srcastra.astra.sys.classetransfert.utils.*;
//import srcastra.astra.gui.components.date.thedate.ADate;
import srcastra.astra.gui.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import srcastra.astra.gui.sys.MessageManager;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.dossier.Frais_T;
import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.dossier.assurance.*;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.sys.classetransfert.dossier.hotel.*;
import srcastra.astra.sys.classetransfert.dossier.bateau.*;
import srcastra.astra.sys.classetransfert.dossier.train.*;
import srcastra.astra.sys.classetransfert.dossier.car.*;
import srcastra.astra.sys.classetransfert.dossier.voitureLocation.*;
import srcastra.astra.sys.classetransfert.dossier.taxi.*;
import srcastra.astra.sys.classetransfert.dossier.divers.*;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.sys.rmi.astrainterface;

import javax.swing.*;

public class NeedAssurance extends javax.swing.JDialog {
    private long alreadyDossierFrais = -1;
    private long alreadyCommission = -1;
    private double montantFraisDossier = 0;
    private double montantCommission = 0;
    private double lastMontant = 0;
    private DossierMainScreenModule parent;
    private int actionPerformed;
    public static final int ASSU_ANNUL = 0;
    public static final int ASSU_BAG = 1;
    public static final int ASSU_COMPL = 2;
    public static final int ACTION_NONE = 0;
    public static final int ACTION_CLOSE = 1;
    public static final int ACTION_OK = 2;
    public static final int ACTION_CANCEL = 3;
    int nbj;

    public NeedAssurance(DossierMainScreenModule parent) {
        super(parent.getSuperOwner(), true);
        this.parent = parent;
        initComponents();
        grp_Adate_echeance.addFocusListener(calculDate1);
//grp_TField_nbj.addKeyListener(calculDate1);
        double comm = 0d;
        if (parent.getDossier().getClientFacturable() != null)
            comm = parent.getDossier().getClientFacturable().getCsmontcotisation();
        if (comm != 0) {
            grp_TField_Commission.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5, 2, parent.getCurrentUser().getLangage()));
            grp_TField_Commission.setText("" + parent.getDossier().getClientFacturable().getCsmontcotisation());
        } else grp_TField_Commission.setEnabled(false);
        actionPerformed = ACTION_NONE;
        grp_CBox_assuCompl.addActionListener(toOkButton);
        grp_But_Cancel.addKeyListener(butKeyListener);
        grp_But_Ok.addKeyListener(butKeyListener);
        // if(parent.getDossier().isNewreccord())
        //     nbj=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().
        nbj = parent.getDossier().getDr_nbj_av_eche();
        grp_TField_nbj.setDocument(new srcastra.astra.gui.sys.formVerification.IntegerMask(0, 5, parent.getCurrentUser().getLangage()));
        grp_TField_FraisDossier.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5, 2, parent.getCurrentUser().getLangage()));
        if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig() != null)
            grp_TField_FraisDossier.setText("" + srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossier());
        //  grp_TField_Commission.setDocument(new srcastra.astra.gui.sys.formVerification.DecimalMask(5,2,parent.getCurrentUser().getLangage()));
        grp_TField_nbj.addActionListener(modePayementListener);
        if (parent.getDossier().getDr_date_echeance() != null)
            grp_Adate_echeance.setDate(parent.getDossier().getDr_date_echeance());
        initFraisDossier();
        refresh();
        grp_But_Cancel.setVisible(false);
        if (parent.getMainframe().isModeGroupe()) {
            grp_TField_FraisDossier.setText("0");
            grp_TField_FraisDossier.setEnabled(false);


        } else if (parent.getDossier().getDrnumdos() != null) {
            if (parent.getDossier().getDrnumdos().length() > 8) {
                if (parent.getDossier().getDrnumdos().charAt(8) == 'G') {
                    if (parent.getDossier().getDrnumdos().charAt(9) == '0') {
                        grp_TField_FraisDossier.setText("0");
                        grp_TField_FraisDossier.setEnabled(false);
                    }
                }

            }

        }
        // grp_But_Cancel.setFocusable(true);
    }

    private void initCommission() {
        //if(parent.getDossier().getF)
    }

    private void initFraisDossier() {
        if (parent.getDossier().getFraisDossier() != null)
            for (Enumeration enu = parent.getDossier().getFraisDossier().keys(); enu.hasMoreElements(); ) {
                Frais_T frais = (Frais_T) parent.getDossier().getFraisDossier().get(enu.nextElement());
                System.out.println("*****" + frais.getLibelleCompta());
                System.out.println("****1" + frais.getValeur_tot());
                //parent.calculTotal();
                lastMontant = parent.getDossier().getDrprix();
                if (frais.isFraisDossier() == 1) {
                    alreadyDossierFrais = frais.getCleunik();
                    montantFraisDossier = frais.getValeur_tot();
                    if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraistvainclus() == 0)
                        grp_TField_FraisDossier.setText("" + frais.getValeur_tot());
                    else grp_TField_FraisDossier.setText("" + frais.getValeur_tot_tva_inc());


                } else if (frais.isFraisDossier() == 2) {
                    alreadyCommission = frais.getCleunik();
                    montantCommission = frais.getValeur_tot();

                }


            }


    }

    public void refresh() {
        if (((Clients_T) parent.getDossier().getClientFacturable()).getCsdelaipaiem() != 0)
            grp_TField_nbj.setText("" + ((Clients_T) parent.getDossier().getClientFacturable()).getCsdelaipaiem());
        else grp_TField_nbj.setText("" + parent.getDossier().getDr_nbj_av_eche());
        if (parent.getDossier().getDr_date_echeance() != null)
            grp_Adate_echeance.setDate(parent.getDossier().getDr_date_echeance());
        if (parent.getDossier().getDr_asannul() == 1) grp_CBox_assuAnnul.setSelected(true);
        if (parent.getDossier().getDr_asbagage() == 1) grp_CBox_assuBagage.setSelected(true);
        if (parent.getDossier().getDr_ascomp() == 1) grp_CBox_assuCompl.setSelected(true);
        if (parent.getDossier().getDr_visa() == 1) grp_CheckBox_visa.setSelected(true);
        if (parent.getDossier().getDr_identchild() == 1) grp_CheckBox_carte.setSelected(true);
        if (parent.getDossier().getDr_voy() == 1) grp_CheckBox_voy.setSelected(true);
        if (parent.getDossier().getDr_annul_lim() == 1) grp_CheckBox_anullim.setSelected(true);
        if (parent.getDossier().getDr_pass_ob() == 1) grp_CheckBox_pass.setSelected(true);
        if (parent.getDossier().getDr_ident() == 1) grp_CheckBox_ident.setSelected(true);
        if (parent.getDossier().getDr_cee() == 1) grp_CheckBox_cee.setSelected(true);
        if (parent.getDossier().getDr_vacc() == 1) grp_CheckBox_vac.setSelected(true);


    }

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        grp_CBox_assuAnnul = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CBox_assuBagage = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CBox_assuCompl = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        grp_TField_nbj = new srcastra.astra.gui.components.textFields.ATextField();
        grp_Adate_echeance = new srcastra.astra.gui.components.date.thedate.ADate();
        jLabel6 = new javax.swing.JLabel();
        grp_TField_FraisDossier = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        grp_CheckBox_visa = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CheckBox_carte = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        grp_CheckBox_voy = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CheckBox_pass = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CheckBox_ident = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CheckBox_cee = new srcastra.astra.gui.components.checkbox.ACheckBox();
        grp_CheckBox_vac = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel14 = new javax.swing.JLabel();
        grp_CheckBox_anullim = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jLabel15 = new javax.swing.JLabel();
        grp_TField_Commission = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        grp_But_Ok = new javax.swing.JButton();
        grp_But_Cancel = new javax.swing.JButton();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        //jPanel1.setLayout(new BorderLayout());
        jPanel3.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("title_pane"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        //jPanel1.setPreferredSize(new java.awt.Dimension(715, 545));
        jPanel3.setLayout(new java.awt.GridBagLayout());
        // jPanel3.setMinimumSize(new java.awt.Dimension(493, 310));
        //jPanel3.setPreferredSize(new java.awt.Dimension(493, 292));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("assu_anul"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel1, gridBagConstraints);
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("assu_bag"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("assu_complementaire"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);
        grp_CBox_assuAnnul.setGrp_Comp_nextComponent(grp_CBox_assuBagage);
        grp_CBox_assuAnnul.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CBox_assuAnnul, gridBagConstraints);
        grp_CBox_assuBagage.setGrp_Comp_nextComponent(grp_CBox_assuCompl);
        grp_CBox_assuBagage.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CBox_assuBagage, gridBagConstraints);
        grp_CBox_assuCompl.setGrp_Comp_nextComponent(grp_CheckBox_pass);
        grp_CBox_assuCompl.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CBox_assuCompl, gridBagConstraints);
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog").getString("grp_Label_nbj"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 241;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog").getString("grp_date_eche"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 313;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);
        grp_TField_nbj.setGrp_Comp_nextComponent(grp_But_Ok);
        grp_TField_nbj.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_TField_nbj.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_TField_nbj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grp_TField_nbjFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                grp_TField_nbjFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 96;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_TField_nbj, gridBagConstraints);
        grp_Adate_echeance.setForm(this);
        grp_Adate_echeance.setGrp_Comp_nextComponent(grp_TField_FraisDossier);
        grp_Adate_echeance.setUser(parent.getCurrentUser());
        grp_Adate_echeance.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Adate_echeance.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        grp_Adate_echeance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Adate_echeanceActionPerformed(evt);
            }
        });
        grp_Adate_echeance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                grp_Adate_echeanceFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 98;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_Adate_echeance, gridBagConstraints);
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog").getString("grp_montant_dos"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 317;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel3.add(jLabel6, gridBagConstraints);
        grp_TField_FraisDossier.setGrp_Comp_nextComponent(grp_But_Ok);
        grp_TField_FraisDossier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grp_TField_FraisDossierFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 96;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 11, 0);
        jPanel3.add(grp_TField_FraisDossier, gridBagConstraints);
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("as_visa"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 176;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel7, gridBagConstraints);
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("as_child"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 139;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel8, gridBagConstraints);
        grp_CheckBox_visa.setGrp_Comp_nextComponent(grp_CheckBox_carte);
        grp_CheckBox_visa.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_visa, gridBagConstraints);
        grp_CheckBox_carte.setGrp_Comp_nextComponent(grp_CheckBox_ident);
        grp_CheckBox_carte.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_carte, gridBagConstraints);
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("asu_voy"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 0, 0);
        jPanel3.add(jLabel9, gridBagConstraints);
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("assu_pas"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel10, gridBagConstraints);
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("assu_ident"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel11, gridBagConstraints);
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("assu_cee"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel12, gridBagConstraints);
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel13.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("assu_vac"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel13, gridBagConstraints);
        grp_CheckBox_voy.setGrp_Comp_nextComponent(grp_But_Ok);
        grp_CheckBox_voy.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 0, 0);
        jPanel3.add(grp_CheckBox_voy, gridBagConstraints);
        grp_CheckBox_pass.setGrp_Comp_nextComponent(grp_CheckBox_visa);
        grp_CheckBox_pass.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_pass, gridBagConstraints);
        grp_CheckBox_ident.setGrp_Comp_nextComponent(grp_CheckBox_cee);
        grp_CheckBox_ident.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_ident, gridBagConstraints);
        grp_CheckBox_cee.setGrp_Comp_nextComponent(grp_CheckBox_vac);
        grp_CheckBox_cee.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_cee, gridBagConstraints);
        grp_CheckBox_vac.setGrp_Comp_nextComponent(grp_TField_nbj);
        grp_CheckBox_vac.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_vac, gridBagConstraints);
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance").getString("ass_anulllim"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel14, gridBagConstraints);
        grp_CheckBox_anullim.setGrp_Comp_nextComponent(grp_CBox_assuAnnul);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 0, 0);
        jPanel3.add(grp_CheckBox_anullim, gridBagConstraints);
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/ProductAnnulationDialog").getString("grp_montant_comm"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 10;
        //gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        //gridBagConstraints.ipadx = 13;
        //gridBagConstraints.ipady = 1;
        jPanel3.add(jLabel15, gridBagConstraints);
        //jPanel1.add(jLabel15);
        //jLabel15.setBounds(10, 320, 53, 13);
        //jLabel15.setBounds(10, 320, 53, 13);
        grp_TField_Commission.setGrp_Comp_nextComponent(grp_But_Ok);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 10;
        //gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(grp_TField_Commission, gridBagConstraints);
        //jPanel1.add(grp_TField_Commission);
        //grp_TField_Commission.setBounds(410, 320, 100, 18);
        //grp_TField_Commission.setBounds(435, 320, 100, 18);
        jLabel16.setText("   %");
        //jPanel1.add(jLabel16);
        jLabel16.setBounds(490, 320, 50, 15);
        // jPanel1.add(jPanel3);
        /// jPanel3.setBounds(18, 24, 493, 292);
        // jPanel3.setBounds(18, 24, 493, 292);
        //jPanel1.add(jLabel15);
        //jLabel15.setBounds(10, 320, 53, 13);
        //jLabel15.setBounds(10, 320, 53, 13);
//        grp_TField_Commission.setGrp_Comp_nextComponent(grp_But_Ok);
        //jPanel1.add(grp_TField_Commission);
        //grp_TField_Commission.setBounds(410, 320, 100, 18);
        //grp_TField_Commission.setBounds(435, 320, 100, 18);
        jLabel16.setText("   %");
        //jPanel1.add(jLabel16);
        jLabel16.setBounds(490, 320, 50, 15);
        getContentPane().add(jPanel1, BorderLayout.NORTH);
        //getContentPane().add(new JPanel(), BorderLayout.WEST);
        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);
        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        grp_But_Ok.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_But_Ok.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("bt_ok"));
        grp_But_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_OkActionPerformed(evt);
            }
        });
        grp_But_Ok.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grp_But_OkFocusGained(evt);
            }
        });
        jPanel2.add(grp_But_Ok);
        grp_But_Cancel.setFont(new java.awt.Font("Tahoma", 1, 10));
        grp_But_Cancel.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/productSpecification/NeedAssurance", parent.getCurrentUser().getLangage()).getString("bt_cancel"));
        grp_But_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_CancelActionPerformed(evt);
            }
        });
        jPanel2.add(grp_But_Cancel);
        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = 600;
        int y = 450;
        setBounds((screenSize.width - x) / 2, (screenSize.height - y) / 2, x, y);
    }//GEN-END:initComponents

    private void grp_TField_nbjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_nbjFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_TField_nbjFocusGained

    private void grp_Adate_echeanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Adate_echeanceActionPerformed
        // TODO add your handling code here:
        //srcastra.astra.sys.classetransfert.utils.Date d=CalculDate.getTodayDate();
        //d.setText(CalculDate.getTodayDate().toString());
        //grp_Adate_echeance.getDate();
        Calendar c = Calendar.getInstance();
        Date d1 = new Date();
        d1.setTime(grp_Adate_echeance.getDate().getLongTime());
        //d1.setSeconds(0);
        //d1.setMinutes(0);
        Date d = new Date();
        //d.setSeconds(0);
        //d.setMinutes(0);
        d.setTime(parent.getDossier().getDr_date_depart().getLongTime());
        //System.out.println()
        double difference = d.getTime() - d1.getTime();
        // int val=(int)
        int val = (int) Math.round((difference / (1000 * 60 * 60 * 24)));
        String s = Integer.toString(val);
        System.out.println(s);
        grp_TField_nbj.setText(s);
        //Date d=parent.getDossier().getDr_date_depart().;
        //return (max.getTime() - min.getTime())/86400000;
        //CalculDate.renvDifferenceBetweenDate(parent.getDossier().getDr_date_depart(),d);
        //grp_TField_nbj.setText(Integer.toString(grp_Adate_echeance.getNbrjour()));
    }//GEN-LAST:event_grp_Adate_echeanceActionPerformed

    private void grp_TField_FraisDossierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_FraisDossierFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_TField_FraisDossierFocusGained

    private void grp_Adate_echeanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_Adate_echeanceFocusLost
        System.out.println("");
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_Adate_echeanceFocusLost

    private void grp_But_OkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_But_OkFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_But_OkFocusGained

    private void grp_TField_nbjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grp_TField_nbjFocusLost
        System.out.println("dffddf");
        try {
            //  Date d=new Date();
            int tmp = Integer.parseInt(grp_TField_nbj.getText());
            System.out.println(tmp);
            if (tmp == 0)
                grp_Adate_echeance.setDate(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate());//srcastra.astra.sys.classetransfert.utils.CalculDate.renvDateEcheance(parent.getDossier().getDr_datetimecrea(),0));
            else
                grp_Adate_echeance.setDate(srcastra.astra.sys.classetransfert.utils.CalculDate.renvDateEcheance(parent.getDossier().getDr_date_depart(), tmp));


        } catch (NumberFormatException nn) {
            nn.printStackTrace();


        }// Add your handling code here:


    }//GEN-LAST:event_grp_TField_nbjFocusLost

    private ActionListener modePayementListener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                int tmp = Integer.parseInt(grp_TField_nbj.getText());
                if (tmp == 0) {
                    if (parent.getDossier().isNewreccord())
                        grp_Adate_echeance.setDate(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate());
                    else grp_Adate_echeance.setDate(parent.getDossier().getDr_datetimecrea());
                } else
                    grp_Adate_echeance.setDate(srcastra.astra.sys.classetransfert.utils.CalculDate.renvDateEcheance(parent.getDossier().getDr_date_depart(), tmp));
                grp_TField_FraisDossier.requestFocus();


            } catch (NumberFormatException ne) {
                System.out.println("erreur de parsing");


            }


        }


    };

    private void grp_But_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_CancelActionPerformed
        actionPerformed = ACTION_CANCEL;
        grp_CBox_assuAnnul.requestFocus();
        setVisible(false);
        dispose();


    }//GEN-LAST:event_grp_But_CancelActionPerformed

    private void grp_But_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_OkActionPerformed
        actionPerformed = ACTION_OK;
        try {
            parent.getDossier().setDr_nbj_av_eche(Integer.parseInt(grp_TField_nbj.getText()));
            if (grp_CBox_assuAnnul.isSelected()) parent.getDossier().setDr_asannul(1);
            else parent.getDossier().setDr_asannul(0);
            if (grp_CBox_assuBagage.isSelected()) parent.getDossier().setDr_asbagage(1);
            else parent.getDossier().setDr_asbagage(0);
            if (grp_CBox_assuCompl.isSelected()) parent.getDossier().setDr_ascomp(1);
            else parent.getDossier().setDr_ascomp(0);
            if (grp_CheckBox_visa.isSelected()) parent.getDossier().setDr_visa(1);
            else parent.getDossier().setDr_visa(0);
            if (grp_CheckBox_carte.isSelected()) parent.getDossier().setDr_identchild(1);
            else parent.getDossier().setDr_identchild(0);
            if (grp_CheckBox_voy.isSelected()) parent.getDossier().setDr_voy(1);
            else parent.getDossier().setDr_voy(0);
            if (grp_CheckBox_anullim.isSelected()) parent.getDossier().setDr_annul_lim(1);
            else parent.getDossier().setDr_annul_lim(0);
            if (grp_CheckBox_pass.isSelected()) parent.getDossier().setDr_pass_ob(1);
            else parent.getDossier().setDr_pass_ob(0);
            if (grp_CheckBox_ident.isSelected()) parent.getDossier().setDr_ident(1);
            else parent.getDossier().setDr_ident(0);
            if (grp_CheckBox_cee.isSelected()) parent.getDossier().setDr_cee(1);
            else parent.getDossier().setDr_cee(0);
            if (grp_CheckBox_vac.isSelected()) parent.getDossier().setDr_vacc(1);
            else parent.getDossier().setDr_vacc(0);
            if (grp_Adate_echeance.getDate() != null)
                parent.getDossier().setDr_date_echeance(grp_Adate_echeance.getDate());


        } catch (NumberFormatException nn) {
            parent.getDossier().setDr_nbj_av_eche(this.nbj);


        }
        System.out.println(parent.getDossier().getDr_nbj_av_eche());
        grp_CBox_assuAnnul.requestFocus();
        setVisible(false);
        //Sup_reduc_T red = new Sup_reduc_T();
        // red.setPrix(151);
        //parent.getDossier().add
        //FraisDossier(false);
        FraisDossier();
        if (grp_TField_Commission.isEnabled()) Commission();
        if (!parent.getDossier().isNewreccord()) parent.getDossier().setModifreccord(true);
        parent.chargeStatusPanel();
        // ListArray list=parent.getDossier().getVectorProd();
        //System.out.println(list);
        dispose();


    }//GEN-LAST:event_grp_But_OkActionPerformed

    private void Commission() {
        double prix2 = 0;
        try {
            prix2 = new Double(grp_TField_Commission.getText()).doubleValue();
        } catch (NumberFormatException ne) {
            prix2 = 0;
        }
        Frais_T frais = new Frais_T();
        Grpdecision_T grpdec = new Grpdecision_T();
        grpdec.setGncomptevente(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxDest());
        //grpdec.setGncodetvavente(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());
        //grpdec.setValeurGenFloat1(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossierTva());
        grpdec.setValeurGenFloat1(0);
        grpdec.setModifyreccord(true);
        grpdec.setIsfromProduit(false);
        grpdec.setGnfrtvaComptabiliserVente(0);
        // grpdec.setGncodetvaachat(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());
        grpdec.setGncodetvaachat(4);
        frais.setGroupdec(grpdec);
        parent.decrementeTrainCompteur();
        if (alreadyCommission != -1) {
            frais.setCleunik(alreadyCommission);

        } else {
            frais.setCleunik(parent.getTrainCompteur());
            frais.setIsnewreccord(true);
        }
        try {
            frais.setMontant_annul(0);

        } catch (NumberFormatException ne) {
            //grp_TField_MontantAnnulation.setText("0");
            System.out.println("erreur de number format");


        }// supreduc.setValeur_tot(grp_TField_frais_annul.getText());
        double pourcent = parent.getDossier().getLastPrix() * (prix2 / 100);
        prix2 = pourcent;
        try {
            // double prix=new Double(grp_TField_frais_annul.getText()).doubleValue();
            if (prix2 < 0) prix2 = -prix2;
            prix2 = MathRound.roundThisToDouble(prix2);
            frais.setFrais_annulation(-prix2);
            // TvaReturnValue tva=tvaCalcul.TvaReturnValueNoTva(frais);
            //frais.setMontant_tva(tva.montant_Tva);
            //frais.setValeur_tot(tva.montantHtva);
            // frais.setValeur_tot_tva_inc(tva.montant_TvaCompris);
            frais.setMontant_tva(0);
            frais.setValeur_tot(-prix2);
            frais.setValeur_tot_tva_inc(-prix2);
            frais.setLongtime();
            //frais.setFraisDossier();
        } catch (NumberFormatException ne) {
            // grp_TField_MontantAnnulation.setText("0");
            System.out.println("erreur de number format");
        }// supreduc.setValeur_tot(grp_TField_frais_annul.getText());
        try {
            //frais.setPercent(new Float(grp_TField_pourcent.getText()).floatValue());
        } catch (NumberFormatException ne) {
            //grp_TField_pourcent.setText("0");
            System.out.println("erreur de number format");
        }// su
        //frais.setNote_crédit(note_crédit
        //parent.getDossier().getFraisDossier();
        java.util.Locale tmplocale = new java.util.Locale(parent.getDossier().getClientContractuel().getLanguenom(), "");
        frais.setFraisDossier(2);
        frais.setLibelleCompta(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", tmplocale).getString("commission") + " " + grp_TField_Commission.getText() + "%");
        frais.setLibelle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", tmplocale).getString("commission") + " " + grp_TField_Commission.getText() + "%");
        frais.prepareAffichage();
        //   frais.getProduitaff().m_delete=new Boolean(true);
        frais.setLongtime();
        if (frais.getAt_val_vente() != 0) parent.getDossier().addFraisDossier(frais);
        if (!parent.getDossier().isNewreccord()) parent.getDossier().setModifreccord(true);
        // setVisible(false);
        // srcastra.astra.gui.sys.utils.TableManage.deleteFromTable(this,m_parent,m_ta
    }

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        actionPerformed = ACTION_CLOSE;
        grp_CBox_assuAnnul.requestFocus();
        setVisible(false);
        dispose();


    }//GEN-LAST:event_closeDialog

    private void FraisDossier() {
        double montant = 0d;
        boolean amountChanged = false;
        try {
            montant = Double.parseDouble(grp_TField_FraisDossier.getText());


        } catch (NumberFormatException nn) {
            montant = 0d;


        }
        if (montant != 0d) {
            Frais_T frais = new Frais_T();
            Grpdecision_T grpdec = new Grpdecision_T();
            parent.decrementeTrainCompteur();
            if (alreadyDossierFrais != -1) {
                frais.setCleunik(alreadyDossierFrais);
                // frais.isIsnewreccord();
            } else {
                frais.setCleunik(parent.getTrainCompteur());
                frais.setIsnewreccord(true);


            }
            frais.setFrais_annulation(montant);
            frais.setSup_tva(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossierTva());
            if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteFrais() == 0)
                grpdec.setGncomptevente(635);
            else
                grpdec.setGncomptevente(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteFrais());
            grpdec.setGncodetvavente(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());
            grpdec.setValeurGenFloat1(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossierTva());
            grpdec.setModifyreccord(true);
            grpdec.setIsfromProduit(false);
            grpdec.setGnfrtvaComptabiliserVente(1);
            grpdec.setGncodetvaachat(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());
            frais.setTva_cleunik(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisdossiertvacle());
            frais.setGroupdec(grpdec);
            frais.setLongtime(System.currentTimeMillis());
            TvaReturnValue tva = null;
            if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraistvainclus() == 1) {
                tva = tvaCalcul.TvaReturnValueLess(frais);

            } else {
                tva = tvaCalcul.TvaReturnValue2(frais, srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFraisDossierTva());

            }
            frais.setMontant_tva(tva.montant_Tva);
            frais.setValeur_tot(tva.montantHtva);
            frais.setValeur_tot_tva_inc(tva.montant_TvaCompris);

            if (montantFraisDossier != frais.getValeur_tot()) amountChanged = true;
            montantFraisDossier = montant;
            // frais.setValeur_tot_tva_inc(montant);
            // frais.setLongtime();
            frais.setFraisDossier(1);
            java.util.Locale tmplocale = new java.util.Locale(parent.getDossier().getClientContractuel().getLanguenom(), "");
            frais.setLibelleCompta(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", tmplocale).getString("Frais_dossier"));
            parent.getDossier().addFraisDossier(frais);
            // parent.getDossier().calculTotalProduit();
            if (!parent.getDossier().isNewreccord()) {
                parent.getDossier().setModifreccord(true);
                if (amountChanged && parent.getDossier().getDr_facture() == 1){
                    parent.getDossier().setComptaModify(true);
                }
            }


        }


    }

    public boolean[] getAssuranceNeeded() {
        return new boolean[]{!grp_CBox_assuAnnul.isSelected(), !grp_CBox_assuBagage.isSelected(), !grp_CBox_assuCompl.isSelected()};


    }

    private ValidateField toOkButton = new ValidateField() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            grp_But_Ok.requestFocus();


        }


    };

    public int getActionPerformed() {
        return actionPerformed;


    }

    public void show() {
        initFraisDossier();
        super.show();
        grp_CBox_assuAnnul.requestFocus();


    }

    private KeyListener butKeyListener = new KeyListener() {
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (evt.getSource().equals(grp_But_Ok))
                    grp_But_OkActionPerformed(new ActionEvent(grp_But_Ok, ActionEvent.ACTION_PERFORMED, "ok"));
                else if (evt.getSource().equals(grp_But_Cancel))
                    grp_But_CancelActionPerformed(new ActionEvent(grp_But_Cancel, ActionEvent.ACTION_PERFORMED, "cancel"));


            }


        }

        public void keyReleased(KeyEvent evt) {
        }

        public void keyTyped(KeyEvent evt) {
        }


    };
    private java.awt.event.FocusAdapter calculDate1 = new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            grp_TField_nbj.setText(Integer.toString(grp_Adate_echeance.getNbrjour()));


        }
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.date.thedate.ADate grp_Adate_echeance;
    private javax.swing.JButton grp_But_Cancel;
    private javax.swing.JButton grp_But_Ok;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_assuAnnul;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_assuBagage;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CBox_assuCompl;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_anullim;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_carte;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_cee;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_ident;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_pass;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_vac;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_visa;
    private srcastra.astra.gui.components.checkbox.ACheckBox grp_CheckBox_voy;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_Commission;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_FraisDossier;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_nbj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        NeedAssurance needAssurance = new NeedAssurance(new FakeDossierModule());
        needAssurance.setVisible(true);
    }
}















