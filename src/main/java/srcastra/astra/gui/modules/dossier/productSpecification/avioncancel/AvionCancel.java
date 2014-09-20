/*

 * AvionCancel.java

 *

 * Created on 28 octobre 2003, 14:01

 */


package srcastra.astra.gui.modules.dossier.productSpecification.avioncancel;

import srcastra.astra.sys.classetransfert.*;

import java.awt.event.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.dossier.avion.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import java.util.*;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.sys.classetransfert.utils.*;

/**
 * @author Thomas
 */

public class AvionCancel extends javax.swing.JPanel {


    /**
     * Creates new form AvionCancel
     */

    AvionRefund avref;

    AvionVoid avvo;

    AvionExchange avex;

    AvionExchange avan;

    Loginusers_T currentUser;

    Avion_ticket_T avion;

    KeyAdapter my_key = new KeyAdapter() {

        public void keyPressed(KeyEvent evt) {

            if (evt.getKeyCode() == evt.VK_SPACE) {

                if (evt.getSource() == grp_ACbox_anul.getCheckBox()) {

                    jLayeredPane1.moveToBack(avvo);

                    jLayeredPane1.moveToBack(avex);

                    jLayeredPane1.moveToBack(avref);

                    jLayeredPane1.moveToFront(avan);

                } else if (evt.getSource() == grp_ACbox_exchange.getCheckBox()) {

                    jLayeredPane1.moveToBack(avvo);

                    jLayeredPane1.moveToBack(avan);

                    jLayeredPane1.moveToBack(avref);

                    jLayeredPane1.moveToFront(avex);


                } else if (evt.getSource() == grp_ACbox_refund.getCheckBox()) {

                    jLayeredPane1.moveToBack(avvo);

                    jLayeredPane1.moveToBack(avex);

                    jLayeredPane1.moveToBack(avan);

                    jLayeredPane1.moveToFront(avref);


                } else if (evt.getSource() == grp_ACbox_void.getCheckBox()) {

                    jLayeredPane1.moveToBack(avan);

                    jLayeredPane1.moveToBack(avex);

                    jLayeredPane1.moveToBack(avref);

                    jLayeredPane1.moveToFront(avvo);

                }


            }

        }

    };

    MouseAdapter my_mouse = new MouseAdapter() {

        public void mouseClicked(MouseEvent evt) {

            if (evt.getSource() == grp_ACbox_anul.getCheckBox()) {

                jLayeredPane1.moveToBack(avvo);

                jLayeredPane1.moveToBack(avex);

                jLayeredPane1.moveToBack(avref);

                jLayeredPane1.moveToFront(avan);

            } else if (evt.getSource() == grp_ACbox_exchange.getCheckBox()) {

                jLayeredPane1.moveToBack(avvo);

                jLayeredPane1.moveToBack(avan);

                jLayeredPane1.moveToBack(avref);

                jLayeredPane1.moveToFront(avex);


            } else if (evt.getSource() == grp_ACbox_refund.getCheckBox()) {

                jLayeredPane1.moveToBack(avvo);

                jLayeredPane1.moveToBack(avex);

                jLayeredPane1.moveToBack(avan);

                jLayeredPane1.moveToFront(avref);


            } else if (evt.getSource() == grp_ACbox_void.getCheckBox()) {

                jLayeredPane1.moveToBack(avan);

                jLayeredPane1.moveToBack(avex);

                jLayeredPane1.moveToBack(avref);

                jLayeredPane1.moveToFront(avvo);

            }

        }

    };

    Dossier_T dossier;

    javax.swing.JDialog main;

    DossierMainScreenModule dossmod;

    public AvionCancel(Loginusers_T currentUser, astrainterface serveur, int cscleunik, Avion_ticket_T avion, Dossier_T dossier, javax.swing.JDialog main, DossierMainScreenModule dossmod) {

        this.currentUser = currentUser;

        this.avion = avion;

        this.dossier = dossier;

        this.main = main;

        this.dossmod = dossmod;

        initComponents();

        postInit();

        avref = new AvionRefund(currentUser);

        avvo = new AvionVoid(currentUser, cscleunik, serveur);

        avex = new AvionExchange(currentUser);

        avan = new AvionExchange(currentUser);


        jLayeredPane1.add(avref, 1);

        jLayeredPane1.add(avvo, 2);

        jLayeredPane1.add(avex, 3);

        jLayeredPane1.add(avan, 4);

        jLayeredPane1.moveToBack(avvo);

        jLayeredPane1.moveToBack(avex);

        jLayeredPane1.moveToBack(avref);

        jLayeredPane1.moveToFront(avan);

        add(jLayeredPane1);
        checkIFalreadyCancel();


    }

    private void checkIFalreadyCancel() {
        if (avion.getEtatAnnulation() == Avion_ticket_T.VOID || avion.getEtatAnnulation() == Avion_ticket_T.EXCHANGE || avion.getEtatAnnulation() == Avion_ticket_T.REFUND) {
            grp_ACbox_exchange.setEnabled(false);
            grp_ACbox_refund.setEnabled(false);
            grp_ACbox_void.setEnabled(false);
        }
    }

    private void postInit() {

        buttonGroup1.add(grp_ACbox_anul.getCheckBox());

        buttonGroup1.add(grp_ACbox_exchange.getCheckBox());

        buttonGroup1.add(grp_ACbox_refund.getCheckBox());

        buttonGroup1.add(grp_ACbox_void.getCheckBox());

        grp_ACbox_anul.addKeyListener(my_key);

        grp_ACbox_exchange.addKeyListener(my_key);

        grp_ACbox_refund.addKeyListener(my_key);

        grp_ACbox_void.addKeyListener(my_key);

        grp_ACbox_anul.addMouseListener(my_mouse);

        grp_ACbox_exchange.addMouseListener(my_mouse);

        grp_ACbox_refund.addMouseListener(my_mouse);

        grp_ACbox_void.addMouseListener(my_mouse);

        grp_ACbox_anul.setSelected(true);

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


        jLayeredPane1 = new javax.swing.JLayeredPane();

        buttonGroup1 = new javax.swing.ButtonGroup();

        jPanel1 = new javax.swing.JPanel();

        jPanel2 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel1 = new javax.swing.JLabel();

        grp_ACbox_anul = new srcastra.astra.gui.components.checkbox.ACheckBox();

        jLabel2 = new javax.swing.JLabel();

        grp_ACbox_void = new srcastra.astra.gui.components.checkbox.ACheckBox();

        jLabel3 = new javax.swing.JLabel();

        grp_ACbox_exchange = new srcastra.astra.gui.components.checkbox.ACheckBox();

        jLabel4 = new javax.swing.JLabel();

        grp_ACbox_refund = new srcastra.astra.gui.components.checkbox.ACheckBox();

        jPanel3 = new javax.swing.JPanel();

        jButton1 = new javax.swing.JButton();

        jButton2 = new javax.swing.JButton();


        setLayout(new java.awt.BorderLayout());


        setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 30));

        add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 40));

        jPanel4.setLayout(new java.awt.GridBagLayout());


        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_anul"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel4.add(jLabel1, gridBagConstraints);


        grp_ACbox_anul.setGrp_Comp_nextComponent(grp_ACbox_void);

        grp_ACbox_anul.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);

        jPanel4.add(grp_ACbox_anul, gridBagConstraints);


        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_void"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel4.add(jLabel2, gridBagConstraints);


        grp_ACbox_void.setGrp_Comp_nextComponent(grp_ACbox_exchange);

        grp_ACbox_void.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);

        jPanel4.add(grp_ACbox_void, gridBagConstraints);


        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_exchange"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel4.add(jLabel3, gridBagConstraints);


        grp_ACbox_exchange.setGrp_Comp_nextComponent(grp_ACbox_refund);

        grp_ACbox_exchange.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);

        jPanel4.add(grp_ACbox_exchange, gridBagConstraints);


        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));

        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_ref"));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel4.add(jLabel4, gridBagConstraints);


        grp_ACbox_refund.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 9);

        jPanel4.add(grp_ACbox_refund, gridBagConstraints);


        jPanel2.add(jPanel4);


        add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12));

        jButton1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_accept"));

        jButton1.setPreferredSize(new java.awt.Dimension(130, 18));

        jButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton1ActionPerformed(evt);

            }

        });


        jPanel3.add(jButton1);


        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12));

        jButton2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_cancel"));

        jButton2.setPreferredSize(new java.awt.Dimension(130, 18));

        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton2ActionPerformed(evt);

            }

        });


        jPanel3.add(jButton2);


        add(jPanel3, java.awt.BorderLayout.SOUTH);


    }//GEN-END:initComponents


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        doCancel();

    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        doPrevious();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void deletePassager() {
        java.util.ArrayList array = dossier.getPassager();
        if (array != null)
            for (int i = 0; i < array.size(); i++) {

                Passager_T passager = (Passager_T) array.get(i);

                if (passager.getPr_cleunik() == avion.getPassager()) {

                    if (passager.isNewReccord()) {

                        dossier.getPassager().remove(passager);

                    } else {

                        passager.setModify(false);

                        passager.setNewReccord(false);

                        passager.setDeleted(true);

                    }

                }

                ((srcastra.astra.gui.modules.dossier.DossierModules) dossmod).refreshTable();

            }


    }

    private void doPrevious() {

        if (grp_ACbox_anul.isSelected()) {

            avion.setModify(true);

            dossier.setComptaModify(true);

            dossier.setModifreccord(true);

            avion.setEtatAnnulation(avion.CANCEL);

            avion.setLibelleCompta(avion.getLibelleCompta() + " : CANCEL");

            avion.setAt_memo(avan.getJTextPane1().getText());

            avion.setAt_annul_date(CalculDate.getTodayDate());

            avion.setCancel(true);

            avion.setDeleted(true);

            deletePassager();

            // deleTeSupReduc();


        } else if (grp_ACbox_exchange.isSelected()) {

            avion.setModify(true);

            //dossier.setComptaModify(true);

            dossier.setModifreccord(true);

            avion.setEtatAnnulation(avion.EXCHANGE);

            avion.setLibelleCompta(avion.getLibelleCompta() + " : EXCHANGE");

            avion.setAt_memo(avex.getJTextPane1().getText());

        } else if (grp_ACbox_void.isSelected()) {

            avion.setModify(true);

            dossier.setComptaModify(true);

            dossier.setModifreccord(true);

            avion.setEtatAnnulation(avion.VOID);

            avion.setLibelleCompta(avion.getLibelleCompta() + " : VOID");

            avion.setAt_memo(avvo.getJTextPane1().getText());

            avion.setAt_annul_date(CalculDate.getTodayDate());

            avion.setCancel(true);

            setToZero(true);

            double prix = 0;

            try {

                prix = Double.parseDouble(avvo.getGrp_TField_montant().getText());

            } catch (NumberFormatException nn) {

                prix = 0;

            }

            addSupReduc(2, prix, true);

            avion.calculMontantTotal();

        } else if (grp_ACbox_refund.isSelected()) {

            avion.setModify(true);

            dossier.setComptaModify(true);

            dossier.setModifreccord(true);

            avion.setEtatAnnulation(avion.REFUND);

            avion.setLibelleCompta(avion.getLibelleCompta() + " : REFUND");

            avion.setAt_memo(avref.getJTextPane1().getText());

            avion.setAt_numrefund(avref.getGrp_TField_refund().getText());

            // setToZero();

            double prix = 0;

            try {

                prix = Double.parseDouble(avref.getGrp_TField_montan().getText());

            } catch (NumberFormatException nn) {

                prix = 0;

            }

            double tcomp = 0;

            try {

                tcomp = Double.parseDouble(avref.getGrp_TField_taxcomp().getText());

            } catch (NumberFormatException nn) {

                tcomp = 0;

            }

            double rembtax = 0;

            try {

                rembtax = Double.parseDouble(avref.getGrp_TField_rembTax().getText());

            } catch (NumberFormatException nn) {

                rembtax = 0;

            }
            addSupReduc(1, prix, false);
            addSupReduc(3, tcomp, false);
            addSupReduc(4, rembtax, false);

            avion.calculMontantTotal();

        }

        doCancel();

    }

    private void doCancel() {

        main.setVisible(false);

        main.dispose();


    }

    public final static int TVA21 = 288;

    private void addSupReduc(int type, double prix, boolean fees) {

        if (prix != 0) {

            Sup_reduc_T supReduc = new Sup_reduc_T();

            dossmod.decrementSupReducCompteur();

            supReduc.setAt_cleunik(dossmod.getSupReducCompteur());

            supReduc.setNewreccord(true);

            supReduc.setParent(avion);

            supReduc.getParent().setTypeDeProduitCleunik(produit_T.AV);
            try {
                supReduc.setGroupdec((srcastra.astra.sys.classetransfert.Grpdecision_T) avion.getGroupdecBase().clone());
            } catch (CloneNotSupportedException cn) {
                cn.printStackTrace();
            }

            //supReduc.setAt_cleunik(aclibelle*-1);

            if (type == 1) {

                supReduc.setReduc(1);

                supReduc.setAt_val_vente(-prix);

                supReduc.setFreeLibelle("REFUND AMOUNT");

                supReduc.setAclibelle(supReduc.TAXREFUND);

            } else if (type == 2) {

                supReduc.setAt_val_vente(prix);

                supReduc.setSup(1);

                supReduc.setFreeLibelle("VOID FEES");

                supReduc.setAclibelle(supReduc.TAXVOIDFEES);

            } else if (type == 3) {

                supReduc.setAt_val_vente(prix);

                supReduc.setSup(1);

                supReduc.setFreeLibelle("COMPANY FEES");

                supReduc.setAclibelle(supReduc.TAXREFUNDTAXCOMP);

            } else if (type == 4) {
                supReduc.setReduc(1);
                supReduc.setAt_val_vente(-prix);
                supReduc.setFreeLibelle("LOCAL TAX BACK ON REFUND");
                supReduc.setAclibelle(supReduc.TAXLOCALEBACK);
                if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxLoc() != supReduc.getGroupdec().getGncomptevente()) {
                    supReduc.getGroupdec().setGncomptevente(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxLoc());
                    supReduc.getGroupdec().setModifyreccord(true);
                }
            }
            if (fees) {
                supReduc.getGroupdec().setModifyreccord(true);
                supReduc.getGroupdec().setGnfrtvaComptabiliserVente(1);
                supReduc.getGroupdec().setGntvainclusvente(0);
                supReduc.getGroupdec().setGncodetvavente(TVA21);
                supReduc.getGroupdec().setValeurGenFloat1(21f);
                supReduc.setSoumis_tva(0);
            }
            supReduc.calculMontantTotal();
            avion.addSup_Reduc(supReduc);
        }

    }


    private void setToZero(boolean voidb) {

        avion.setAt_val_vente(0);

        avion.calculMontantTotal();

        avion.setModify(true);

        Hashtable supreduc = avion.getSup_reduc();

        if (supreduc != null) {

            for (Enumeration enu = supreduc.keys(); enu.hasMoreElements();) {

                Sup_reduc_T sup = (Sup_reduc_T) supreduc.get(enu.nextElement());

                if (sup.getAclibelle() == -4 && voidb && srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getAnnullfees() == 0) {

                    ;

                } else {

                    sup.setAt_val_vente(0);

                    sup.calculMontantTotal();

                    sup.setModify(true);

                }

            }

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.ButtonGroup buttonGroup1;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_ACbox_anul;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_ACbox_exchange;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_ACbox_refund;

    private srcastra.astra.gui.components.checkbox.ACheckBox grp_ACbox_void;

    private javax.swing.JButton jButton1;

    private javax.swing.JButton jButton2;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLayeredPane jLayeredPane1;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    // End of variables declaration//GEN-END:variables


}

