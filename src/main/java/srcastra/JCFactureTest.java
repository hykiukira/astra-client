/*
 * JCFactureTest.java
 *
 * Created on 29 février 2004, 09:45
 */

package srcastra;

import srcastra.astra.sys.classetransfert.configuration.*;

//import com.klg.jclass.page.*;
//import com.klg.jclass.page.awt.*;
import java.io.StringReader;
import java.io.BufferedReader;
import java.util.*;
//import com.klg.jclass.page.JCFlow;
//import com.klg.jclass.page.JCPrinter;
//import com.klg.jclass.page.JCDocument;
//import com.klg.jclass.page.adobe.pdf.JCPDFPrinter;
import java.awt.Font;

import com.java4less.rreport.*;
import srcastra.astra.gui.modules.config.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.gui.sys.*;
import srcastra.astra.gui.sys.formVerification.*;

import javax.swing.*;
import java.awt.event.*;

import java.lang.Runtime.*;

import java.io.File;

/**
 * @author Administrateur
 */
public class JCFactureTest extends javax.swing.JDialog implements srcastra.astra.gui.modules.Mailing.MailInterface {

    /**
     * Creates new form JCFactureTest
     */
    // JClassTest test;
    RReport rep;
    ConfigMediator m_mediator;
    astrainterface m_serveur;
    Loginusers_T m_user;
    AstraPrint dossierprint;
    String pathForMail;
    String mailEntite;
    //String emailAdresse;
    String[] emailAdresse;
    java.awt.Frame frame;

    public JCFactureTest(RReport r, ConfigMediator mediator, astrainterface serveur, Loginusers_T user, java.awt.Frame frame, int type) {

        super(frame, true);
        this.frame = frame;
        rep = r;
        m_mediator = mediator;
        m_serveur = serveur;
        m_user = user;
        initComponents();
        if (user.getUrrights() == 1)
            enableComponents(true);
        else
            enableComponents(false);

        //test=new JClassTest();

        centerScroll.setViewportView(r);
        jSplitPane1.setDividerLocation(100);
        pack();
        setSize(800, 600);
        postInit();
        updatePageNumber();

        // rep.prepare();
        rep.endReport();
        ((RReportJ2) rep).setPrint(true);
        if (type == 1) {
            initCheckBox();
            setDocument();
        } else {
            setVisible2(false);
        }
        postInit(type);
        //     jPanel6.setVisible(false);
        //   jPanel7.setVisible(false);
        //  jPanel8.setVisible(false);
        //  jPanel9.setVisible(false);
        //rep.disablePrinting(false);
        // rep.prepare();
        // rep.endReport();
        //  jButton3.setVisible(false);
        jButton4.setVisible(true);
        jButton5.setVisible(false);

        // boolean state = grp_Check_Bd.isSelected();

        if (type == 1 && (grp_Check_bg.isSelected() || grp_Check_Bd.isSelected())) {
            if (grp_Check_Bd.isSelected() && !grp_Check_bg.isSelected()) {
                m_mediator.setRigthBlocVisible1(true);
                //m_mediator.setLeftBlocVisible1(true);
                m_mediator.setRigthBlocVisible(false);
            }

            if (!grp_Check_Bd.isSelected() && grp_Check_bg.isSelected()) {
                m_mediator.setLeftBlocVisible1(true);
                //m_mediator.setLeftBlocVisible1(true);
                m_mediator.setLeftBlocVisible(false);
            }

            if (grp_Check_Bd.isSelected() && grp_Check_bg.isSelected()) {
                // m_mediator.setLeftBlocVisible(false);}
                // if(grp_Check_bg.isSelected()){
                m_mediator.setRigthBlocVisible1(true);
                m_mediator.setLeftBlocVisible1(true);
                m_mediator.setRigthBlocVisible(false);
                m_mediator.setLeftBlocVisible(false);
            }

        }
        // state = grp_Check_bg.isSelected();

        //m_mediator.setLeftBlocVisible(true);
        // m_mediator.setLeftBlocVisible(state);


    }

    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == grp_JBut_next) {
                if (dossierprint != null) {
                    dossierprint.next();
                }
            } else if (evt.getSource() == grp_JBut_previous) {
                if (dossierprint != null) {
                    dossierprint.previous();
                }
            }
        }
    };

    public void endReport() {
        if (rep != null) {
            ((RReportJ2) rep).setPrint(false);
            rep.endReport();
            ((RReportJ2) rep).setPrint(true);
        }
    }

    private void postInit(int type) {
        if (type == 0) {
            grp_JBut_previous = new javax.swing.JButton();
            grp_JBut_previous.addActionListener(listener);

            grp_JBut_next = new javax.swing.JButton();
            grp_JBut_next.addActionListener(listener);
            grp_JBut_previous.setText("PREVIOUS");
            grp_JBut_next.setText("NEXT");
            jPanel2.add(grp_JBut_previous);
            jPanel2.add(grp_JBut_next);
        }

    }

    private void setVisible2(boolean visible) {
        grp_JBut_downBDC.setVisible(visible);
        grp_JBut_upBDC.setVisible(visible);
        grp_JBut_leftBDC.setVisible(visible);
        grp_JBut_rightBDC.setVisible(visible);

        grp_JBut_downCLI.setVisible(visible);
        grp_JBut_upCLI.setVisible(visible);
        grp_JBut_leftCLI.setVisible(visible);
        grp_JBut_rightCLI.setVisible(visible);

        grp_JBut_downBDC2.setVisible(visible);
        grp_JBut_upBDC2.setVisible(visible);
        grp_JBut_leftBDC2.setVisible(visible);
        grp_JBut_rightBDC2.setVisible(visible);

        grp_JBut_downBDC3.setVisible(visible);
        grp_JBut_upBDC3.setVisible(visible);
        grp_JBut_leftBDC3.setVisible(visible);
        grp_JBut_rightBDC3.setVisible(visible);

        grp_JBut_downBDC4.setVisible(visible);
        grp_JBut_upBDC4.setVisible(visible);
        grp_JBut_leftBDC4.setVisible(visible);
        grp_JBut_rightBDC4.setVisible(visible);

        grp_JBut_mail.setVisible(visible);

        //     grp_TField_Bottom = new javax.swing.JTextField();
        //   btnUp = new javax.swing.JButton();
        //    btnDown = new javax.swing.JButton();

        grp_Check_detailinvisible.setVisible(visible);
        grp_Check_passagerInvisible.setVisible(visible);
        grp_Check_detailPrixInvisible.setVisible(visible);
        grp_Check_bg.setVisible(visible);
        grp_Check_Bd.setVisible(visible);
        grp_button_valid.setVisible(visible);
        grp_TField_Bottom.setVisible(visible);
        grp_TextField_top.setVisible(visible);
        jPanel7.setVisible(visible);
        jPanel8.setVisible(visible);
        jPanel9.setVisible(visible);
        jPanel11.setVisible(visible);
        jPanel6.setVisible(visible);
        jLabel1.setVisible(visible);
        jLabel2.setVisible(visible);
    }

    private void enableComponents(boolean enable) {
        grp_JBut_downBDC.setEnabled(enable);
        grp_JBut_upBDC.setEnabled(enable);
        grp_JBut_leftBDC.setEnabled(enable);
        grp_JBut_rightBDC.setEnabled(enable);

        grp_JBut_downCLI.setEnabled(enable);
        grp_JBut_upCLI.setEnabled(enable);
        grp_JBut_leftCLI.setEnabled(enable);
        grp_JBut_rightCLI.setEnabled(enable);

        grp_JBut_downBDC2.setEnabled(enable);
        grp_JBut_upBDC2.setEnabled(enable);
        grp_JBut_leftBDC2.setEnabled(enable);
        grp_JBut_rightBDC2.setEnabled(enable);

        grp_JBut_downBDC3.setEnabled(enable);
        grp_JBut_upBDC3.setEnabled(enable);
        grp_JBut_leftBDC3.setEnabled(enable);
        grp_JBut_rightBDC3.setEnabled(enable);

        grp_JBut_downBDC4.setEnabled(enable);
        grp_JBut_upBDC4.setEnabled(enable);
        grp_JBut_leftBDC4.setEnabled(enable);
        grp_JBut_rightBDC4.setEnabled(enable);

        //     grp_TField_Bottom = new javax.swing.JTextField();
        //   btnUp = new javax.swing.JButton();
        //    btnDown = new javax.swing.JButton();

        grp_Check_detailinvisible.setEnabled(enable);
        grp_Check_passagerInvisible.setEnabled(enable);
        grp_Check_detailPrixInvisible.setEnabled(enable);
        grp_Check_bg.setEnabled(enable);
        grp_Check_Bd.setEnabled(enable);
        grp_button_valid.setEnabled(enable);
        grp_TField_Bottom.setEnabled(enable);
        grp_TextField_top.setEnabled(enable);


    }

    private void setDocument() {
        grp_TField_Bottom.setDocument(new DecimalMask(1, 2, m_user.getLangage()));
        grp_TextField_top.setDocument(new DecimalMask(1, 2, m_user.getLangage()));
        grp_TField_Bottom.setText("" + srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBottomargin());
        grp_TextField_top.setText("" + srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getTopmargin());
    }

    private void initCheckBox() {
        if (((Report1) rep).getFacture() == 0) {
            grp_Check_detailPrixInvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailPrixVisibleBDC() == 0 ? false : true);
            grp_Check_detailinvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailProduitVisibleBDC() == 0 ? false : true);
            grp_Check_passagerInvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleBDC() == 0 ? false : true);
        } else if (((Report1) rep).getFacture() == 1) {
            grp_Check_detailPrixInvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailPrixVisibleFact() == 0 ? false : true);
            grp_Check_detailinvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailProduitVisibleFact() == 0 ? false : true);
            grp_Check_passagerInvisible.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleFact() == 0 ? false : true);
        }
        grp_Check_bg.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bloc_g() == 0 ? true : false);
        grp_Check_Bd.setSelected(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bloc_d() == 0 ? true : false);
    }

    private void postInit() {
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/print.gif")));
        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/right.gif")));
        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/left.gif")));
        btnOnePage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/onep.gif")));
        btn2page.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/twop.gif")));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/exit.gif")));
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        grp_JBut_downBDC = new javax.swing.JButton();
        grp_JBut_upBDC = new javax.swing.JButton();
        grp_JBut_leftBDC = new javax.swing.JButton();
        grp_JBut_rightBDC = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        grp_JBut_downCLI = new javax.swing.JButton();
        grp_JBut_upCLI = new javax.swing.JButton();
        grp_JBut_leftCLI = new javax.swing.JButton();
        grp_JBut_rightCLI = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        grp_JBut_downBDC2 = new javax.swing.JButton();
        grp_JBut_upBDC2 = new javax.swing.JButton();
        grp_JBut_leftBDC2 = new javax.swing.JButton();
        grp_JBut_rightBDC2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        grp_JBut_downBDC3 = new javax.swing.JButton();
        grp_JBut_upBDC3 = new javax.swing.JButton();
        grp_JBut_leftBDC3 = new javax.swing.JButton();
        grp_JBut_rightBDC3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        grp_JBut_downBDC4 = new javax.swing.JButton();
        grp_JBut_upBDC4 = new javax.swing.JButton();
        grp_JBut_leftBDC4 = new javax.swing.JButton();
        grp_JBut_rightBDC4 = new javax.swing.JButton();
        grp_button_valid = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        centerScroll = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnOnePage = new javax.swing.JButton();
        btn2page = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        grp_JBut_mail = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        grp_TextField_top = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        grp_TField_Bottom = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        g_la_pagenum = new javax.swing.JLabel();
        grp_Check_detailinvisible = new javax.swing.JCheckBox();
        grp_Check_passagerInvisible = new javax.swing.JCheckBox();
        grp_Check_detailPrixInvisible = new javax.swing.JCheckBox();
        grp_Check_bg = new javax.swing.JCheckBox();
        grp_Check_Bd = new javax.swing.JCheckBox();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jSplitPane1.setDividerLocation(110);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(20, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_bdc"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel6.setPreferredSize(new java.awt.Dimension(90, 90));
        grp_JBut_downBDC.setText("jButton1");
        grp_JBut_downBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_downBDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_downBDCActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(grp_JBut_downBDC, gridBagConstraints);

        grp_JBut_upBDC.setText("jButton2");
        grp_JBut_upBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_upBDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_upBDCActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(grp_JBut_upBDC, gridBagConstraints);

        grp_JBut_leftBDC.setText("jButton3");
        grp_JBut_leftBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_leftBDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_leftBDCActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(grp_JBut_leftBDC, gridBagConstraints);

        grp_JBut_rightBDC.setText("jButton4");
        grp_JBut_rightBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_rightBDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_rightBDCActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(grp_JBut_rightBDC, gridBagConstraints);

        jPanel4.add(jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_nomclient"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel7.setPreferredSize(new java.awt.Dimension(90, 90));
        grp_JBut_downCLI.setText("jButton1");
        grp_JBut_downCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_downCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_downCLIActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_JBut_downCLI, gridBagConstraints);

        grp_JBut_upCLI.setText("jButton2");
        grp_JBut_upCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_upCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_upCLIActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_JBut_upCLI, gridBagConstraints);

        grp_JBut_leftCLI.setText("jButton3");
        grp_JBut_leftCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_leftCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_leftCLIActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_JBut_leftCLI, gridBagConstraints);

        grp_JBut_rightCLI.setText("jButton4");
        grp_JBut_rightCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_rightCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_rightCLIActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(grp_JBut_rightCLI, gridBagConstraints);

        jPanel4.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_blg"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel8.setPreferredSize(new java.awt.Dimension(90, 90));
        grp_JBut_downBDC2.setText("jButton1");
        grp_JBut_downBDC2.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_downBDC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_downBDC2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(grp_JBut_downBDC2, gridBagConstraints);

        grp_JBut_upBDC2.setText("jButton2");
        grp_JBut_upBDC2.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_upBDC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_upBDC2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(grp_JBut_upBDC2, gridBagConstraints);

        grp_JBut_leftBDC2.setText("jButton3");
        grp_JBut_leftBDC2.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_leftBDC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_leftBDC2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(grp_JBut_leftBDC2, gridBagConstraints);

        grp_JBut_rightBDC2.setText("jButton4");
        grp_JBut_rightBDC2.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_rightBDC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_rightBDC2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(grp_JBut_rightBDC2, gridBagConstraints);

        jPanel4.add(jPanel8);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_bld"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel9.setPreferredSize(new java.awt.Dimension(90, 90));
        grp_JBut_downBDC3.setText("jButton1");
        grp_JBut_downBDC3.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_downBDC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_downBDC3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_JBut_downBDC3, gridBagConstraints);

        grp_JBut_upBDC3.setText("jButton2");
        grp_JBut_upBDC3.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_upBDC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_upBDC3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_JBut_upBDC3, gridBagConstraints);

        grp_JBut_leftBDC3.setText("jButton3");
        grp_JBut_leftBDC3.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_leftBDC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_leftBDC3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_JBut_leftBDC3, gridBagConstraints);

        grp_JBut_rightBDC3.setText("jButton4");
        grp_JBut_rightBDC3.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_rightBDC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_rightBDC3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(grp_JBut_rightBDC3, gridBagConstraints);

        jPanel4.add(jPanel9);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("corp"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jPanel11.setPreferredSize(new java.awt.Dimension(90, 90));
        grp_JBut_downBDC4.setText("jButton1");
        grp_JBut_downBDC4.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_downBDC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_downBDC4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_JBut_downBDC4, gridBagConstraints);

        grp_JBut_upBDC4.setText("jButton2");
        grp_JBut_upBDC4.setPreferredSize(new java.awt.Dimension(20, 20));
        grp_JBut_upBDC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_upBDC4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_JBut_upBDC4, gridBagConstraints);

        grp_JBut_leftBDC4.setText("jButton3");
        grp_JBut_leftBDC4.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_JBut_leftBDC4, gridBagConstraints);

        grp_JBut_rightBDC4.setText("jButton4");
        grp_JBut_rightBDC4.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(grp_JBut_rightBDC4, gridBagConstraints);

        jPanel4.add(jPanel11);

        grp_button_valid.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale").getString("config_bur_acc"));
        grp_button_valid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_button_validActionPerformed(evt);
            }
        });

        jPanel4.add(grp_button_valid);

        jSplitPane1.setLeftComponent(jPanel4);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel10.add(centerScroll, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel10);

        jPanel1.add(jSplitPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 30));
        btnPrint.setPreferredSize(new java.awt.Dimension(26, 26));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jPanel2.add(btnPrint);

        btnOnePage.setPreferredSize(new java.awt.Dimension(26, 26));
        btnOnePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnePageActionPerformed(evt);
            }
        });

        jPanel2.add(btnOnePage);

        btn2page.setPreferredSize(new java.awt.Dimension(26, 26));
        btn2page.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2pageActionPerformed(evt);
            }
        });

        jPanel2.add(btn2page);

        btnExit.setPreferredSize(new java.awt.Dimension(26, 26));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel2.add(btnExit);

        jButton5.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton5);


        grp_JBut_mail.setText("MAIL-OUTLOOK");
        grp_JBut_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JBut_mailActionPerformed(evt);
            }
     });

        jPanel2.add(grp_JBut_mail);

        jButton4.setText("PDF");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton4);

        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("top"));
        jPanel2.add(jLabel1);

        grp_TextField_top.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel2.add(grp_TextField_top);

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("bot"));
        jPanel2.add(jLabel2);

        grp_TField_Bottom.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel2.add(grp_TField_Bottom);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 2));

        jPanel3.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnUp.setPreferredSize(new java.awt.Dimension(26, 26));
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(btnUp, gridBagConstraints);

        btnDown.setPreferredSize(new java.awt.Dimension(26, 26));
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel5.add(btnDown, gridBagConstraints);

        g_la_pagenum.setFont(new java.awt.Font("Dialog", 3, 12));
        g_la_pagenum.setPreferredSize(new java.awt.Dimension(100, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel5.add(g_la_pagenum, gridBagConstraints);

        grp_Check_detailinvisible.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_detailinvisible.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("det_inv"));
        grp_Check_detailinvisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Check_detailinvisibleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel5.add(grp_Check_detailinvisible, gridBagConstraints);

        grp_Check_passagerInvisible.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_passagerInvisible.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("pas_inv"));
        grp_Check_passagerInvisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Check_passagerInvisibleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel5.add(grp_Check_passagerInvisible, gridBagConstraints);

        grp_Check_detailPrixInvisible.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_detailPrixInvisible.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("det_pri_invisible"));
        grp_Check_detailPrixInvisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Check_detailPrixInvisibleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel5.add(grp_Check_detailPrixInvisible, gridBagConstraints);

        grp_Check_bg.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_bg.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("g_inv"));
        grp_Check_bg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Check_bgActionPerformed(evt);
            }
        });

        jPanel5.add(grp_Check_bg, new java.awt.GridBagConstraints());

        grp_Check_Bd.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_Bd.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("d_inv"));
        grp_Check_Bd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Check_BdActionPerformed(evt);
            }
        });

        jPanel5.add(grp_Check_Bd, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel5);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
    }//GEN-END:initComponents

   private void grp_JBut_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_mailActionPerformed
       String temp = this.pathForMail;


        String temp1 = temp.replaceAll(".pdf", ".pdf");
        if (new File(temp1).exists())
            temp = temp1;


        temp = temp.replaceAll("/", "\\\\");

        System.out.println(temp);

        String temp2[];


        temp2 = getEmailAdres();

        String temp3= temp2[0].toString();

        System.out.println(temp3);




        try {


           Process proc = Runtime.getRuntime().exec("C:\\HTSMAIL\\HTSMAIL.exe " + temp+ "$" +temp3 + "$");

        }

        catch (Exception e) {


        }


    }//GEN-LAST:event_jButton4ActionPerformed

    /*    Entite e = null;


        try {

            ArrayList data = m_serveur.renvEntiteRmiObject(m_user.getUrcleunik()).getList(m_user.getUrcleunik(), m_user.getUreecleunik());

            int cpt = 0;
           boolean found = false;

           while (!found && cpt < data.size()) {
               e = (Entite) data.get(cpt);

                if (e.getEecleunik() == m_user.getUreecleunik())
                    found = true;

                cpt++;
            }

            mailEntite = e.getEemail();

            new srcastra.astra.gui.modules.Mailing.MaiLing(this.frame, true, this, srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig(), this.pathForMail).show();


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }


    }//GEN-LAST:event_grp_JBut_mailActionPerformed
     */
    private void grp_button_validActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_button_validActionPerformed
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bdc_cli_x(StaticFields.getClient().x);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bdc_cli_y(StaticFields.getClient().y);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bdc_x(StaticFields.getBonCommande().x);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bdc_y(StaticFields.getBonCommande().y);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBloquedupx(StaticFields.getBloqueD().x);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBloquedupy(StaticFields.getBloqueD().y);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBloquegupx(StaticFields.getBloqueG().x);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBloquegupy(StaticFields.getBloqueG().y);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setCorpy(StaticFields.getCorp().y);
        try {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBottomargin(new Double(grp_TField_Bottom.getText()).doubleValue());
        } catch (NumberFormatException nn) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBottomargin(0.5d);
        }
        try {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setTopmargin(new Double(grp_TextField_top.getText()).doubleValue());
        } catch (NumberFormatException nn) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setTopmargin(0.5d);
        }
        if (grp_Check_bg.isSelected())
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bloc_g(0);
        else srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bloc_g(1);
        if (grp_Check_Bd.isSelected())
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bloc_d(0);
        else srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setBdc_bloc_d(1);
        try {
            m_serveur.renvConfigRmiObject(m_user.getUrcleunik()).modifyConfig(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig(), m_user.getUrcleunik());
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }        // Ad
    }//GEN-LAST:event_grp_button_validActionPerformed

    private void grp_Check_detailPrixInvisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_detailPrixInvisibleActionPerformed
        if (((Report1) rep).getFacture() == 0) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setDetailPrixVisibleBDC(grp_Check_detailPrixInvisible.isSelected() ? 1 : 0);
        } else if (((Report1) rep).getFacture() == 1) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setDetailPrixVisibleFact(grp_Check_detailPrixInvisible.isSelected() ? 1 : 0);
        }
    }//GEN-LAST:event_grp_Check_detailPrixInvisibleActionPerformed

    private void grp_Check_passagerInvisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_passagerInvisibleActionPerformed
        if (((Report1) rep).getFacture() == 0) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setPassagerVisibleBDC(grp_Check_passagerInvisible.isSelected() ? 1 : 0);
        } else if (((Report1) rep).getFacture() == 1) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setPassagerVisibleFact(grp_Check_passagerInvisible.isSelected() ? 1 : 0);
        }
    }//GEN-LAST:event_grp_Check_passagerInvisibleActionPerformed

    private void grp_Check_detailinvisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_detailinvisibleActionPerformed
        if (((Report1) rep).getFacture() == 0) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setDetailProduitVisibleBDC(grp_Check_detailinvisible.isSelected() ? 1 : 0);
        } else if (((Report1) rep).getFacture() == 1) {
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setDetailProduitVisibleFact(grp_Check_detailinvisible.isSelected() ? 1 : 0);
        }
    }//GEN-LAST:event_grp_Check_detailinvisibleActionPerformed

    private void grp_JBut_downBDC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_downBDC4ActionPerformed
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setNombrebloc(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNombrebloc() + 1);
        m_mediator.moveCorp("DOWN");
    }//GEN-LAST:event_grp_JBut_downBDC4ActionPerformed

    private void grp_JBut_upBDC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_upBDC4ActionPerformed
        srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().setNombrebloc(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNombrebloc() - 1);
        m_mediator.moveCorp("UP");
    }//GEN-LAST:event_grp_JBut_upBDC4ActionPerformed

    private void grp_JBut_leftBDC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_leftBDC3ActionPerformed
        m_mediator.moveBloqueDroit("LEFT");
    }//GEN-LAST:event_grp_JBut_leftBDC3ActionPerformed

    private void grp_JBut_downBDC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_downBDC3ActionPerformed
        m_mediator.moveBloqueDroit("DOWN");
    }//GEN-LAST:event_grp_JBut_downBDC3ActionPerformed

    private void grp_JBut_rightBDC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_rightBDC3ActionPerformed
        m_mediator.moveBloqueDroit("RIGTH");
    }//GEN-LAST:event_grp_JBut_rightBDC3ActionPerformed

    private void grp_JBut_upBDC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_upBDC3ActionPerformed
        m_mediator.moveBloqueDroit("UP");
    }//GEN-LAST:event_grp_JBut_upBDC3ActionPerformed

    private void grp_JBut_downBDC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_downBDC2ActionPerformed
        m_mediator.moveBlocGauche("DOWN");
    }//GEN-LAST:event_grp_JBut_downBDC2ActionPerformed

    private void grp_JBut_rightBDC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_rightBDC2ActionPerformed
        m_mediator.moveBlocGauche("RIGTH");
    }//GEN-LAST:event_grp_JBut_rightBDC2ActionPerformed

    private void grp_JBut_upBDC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_upBDC2ActionPerformed
        m_mediator.moveBlocGauche("UP");
    }//GEN-LAST:event_grp_JBut_upBDC2ActionPerformed

    private void grp_JBut_leftBDC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_leftBDC2ActionPerformed
        m_mediator.moveBlocGauche("LEFT");
    }//GEN-LAST:event_grp_JBut_leftBDC2ActionPerformed

    private void grp_Check_BdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_BdActionPerformed
        m_mediator.setRigthBlocVisible(grp_Check_Bd.isSelected() ? false : true);
    }//GEN-LAST:event_grp_Check_BdActionPerformed

    private void grp_Check_bgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Check_bgActionPerformed
        m_mediator.setLeftBlocVisible(grp_Check_bg.isSelected() ? false : true);
    }//GEN-LAST:event_grp_Check_bgActionPerformed

    private void grp_JBut_leftCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_leftCLIActionPerformed
        m_mediator.moveClient("LEFT");
    }//GEN-LAST:event_grp_JBut_leftCLIActionPerformed

    private void grp_JBut_downCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_downCLIActionPerformed
        m_mediator.moveClient("DOWN");
    }//GEN-LAST:event_grp_JBut_downCLIActionPerformed

    private void grp_JBut_rightCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_rightCLIActionPerformed
        m_mediator.moveClient("RIGTH");
    }//GEN-LAST:event_grp_JBut_rightCLIActionPerformed

    private void grp_JBut_upCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_upCLIActionPerformed
        m_mediator.moveClient("UP");
    }//GEN-LAST:event_grp_JBut_upCLIActionPerformed

    private void grp_JBut_rightBDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_rightBDCActionPerformed
        m_mediator.moveBon("RIGTH");
    }//GEN-LAST:event_grp_JBut_rightBDCActionPerformed

    private void grp_JBut_downBDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_downBDCActionPerformed
        m_mediator.moveBon("DOWN");        // Add your handling code here:
    }//GEN-LAST:event_grp_JBut_downBDCActionPerformed

    private void grp_JBut_upBDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_upBDCActionPerformed
        m_mediator.moveBon("UP");
    }//GEN-LAST:event_grp_JBut_upBDCActionPerformed

    private void grp_JBut_leftBDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JBut_leftBDCActionPerformed
        m_mediator.moveBon("LEFT"); // Add your handling code here:
    }//GEN-LAST:event_grp_JBut_leftBDCActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);


        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btn2pageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2pageActionPerformed
        rep.setShowPages(2);
        updateZoomText();
    }//GEN-LAST:event_btn2pageActionPerformed

    private void updateZoomText() {
        //	if (rep.getShowPages()==1)
        //	   txtZoom.setText((int) (rep.getScale()*100) + " %");
        //	else
        //         txtZoom.setText((int) (rep.getScalePages()*100) + " %");
    }

    private void btnOnePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnePageActionPerformed
        rep.setShowPages(1);
        updateZoomText();
    }//GEN-LAST:event_btnOnePageActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        printPreview();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        if (rep.getPageNumber() > 1) {
            rep.setPageNumber(rep.getPageNumber() - 1);
            updatePageNumber();
        }
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        if (rep.getPageNumber() < rep.getMaxPages()) {
            rep.setPageNumber(rep.getPageNumber() + 1);
            updatePageNumber();
        }// Add your handling code here:
    }//GEN-LAST:event_btnDownActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String temp = this.pathForMail;
        String temp1 = temp.replaceAll(".pdf", ".pdf");
        if (new File(temp1).exists())
            temp = temp1;


        temp = temp.replaceAll("/", "\\\\");


        System.out.println(temp);

        String temp2[];


        temp2 = getEmailAdres();
        String temp3= temp2[0].toString();

        System.out.println(temp3);




        try {

           Process proc = Runtime.getRuntime().exec("explorer.exe "+ temp);
        }

        catch (Exception e) {


        }

        //test.

        //  test.doPdf();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void updatePageNumber() {
        g_la_pagenum.setText(rep.getPageNumber() + "/" + rep.getMaxPages());
    }

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        // System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     */
    public void setFacture() {

    }

    public static void main(String args[]) {
        //  new JCFactureTest().show();
    }

    private void printPreview() {
        // build list of pages to print
        int[] Pages = new int[rep.getMaxPages()];
        int PagesCount = rep.getMaxPages();
        for (int i = 0; i < rep.getMaxPages(); i++) Pages[i] = 1;
        rep.printPreview(Pages, PagesCount);

    }

    /**
     * Getter for property dossierprint.
     *
     * @return Value of property dossierprint.
     */
    public com.java4less.rreport.AstraPrint getDossierprint() {
        return dossierprint;
    }

    /**
     * Setter for property dossierprint.
     *
     * @param dossierprint New value of property dossierprint.
     */
    public void setDossierprint(com.java4less.rreport.AstraPrint dossierprint) {
        this.dossierprint = dossierprint;
    }

    /**
     * Getter for property pathForMail.
     *
     * @return Value of property pathForMail.
     */
    public java.lang.String getPathForMail() {
        return pathForMail;
    }

    public java.lang.String getFormEntite() {

        return mailEntite;
    }

    /**
     * Setter for property pathForMail.
     *
     * @param pathForMail New value of property pathForMail.
     */
    public void setPathForMail(java.lang.String pathForMail) {
        this.pathForMail = pathForMail;
    }

    /**
     * Getter for property emailAdresse.
     *
     * @return Value of property emailAdresse.
     */
    public java.lang.String[] getEmailAdresse() {
        return this.emailAdresse;
    }

    /**
     * Setter for property emailAdresse.
     *
     * @param emailAdresse New value of property emailAdresse.
     */
    public void setEmailAdresse(java.lang.String[] emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public String[] getEmailAdres() {
        return emailAdresse;
    }

    public Loginusers_T getUser() {
        return m_user;
    }

    public String getFormEntiteMail() {
        return mailEntite;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn2page;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOnePage;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnUp;
    private javax.swing.JScrollPane centerScroll;
    private javax.swing.JLabel g_la_pagenum;
    private javax.swing.JCheckBox grp_Check_Bd;
    private javax.swing.JCheckBox grp_Check_bg;
    private javax.swing.JCheckBox grp_Check_detailPrixInvisible;
    private javax.swing.JCheckBox grp_Check_detailinvisible;
    private javax.swing.JCheckBox grp_Check_passagerInvisible;
    private javax.swing.JButton grp_JBut_downBDC;
    private javax.swing.JButton grp_JBut_downBDC2;
    private javax.swing.JButton grp_JBut_downBDC3;
    private javax.swing.JButton grp_JBut_downBDC4;
    private javax.swing.JButton grp_JBut_downCLI;
    private javax.swing.JButton grp_JBut_leftBDC;
    private javax.swing.JButton grp_JBut_leftBDC2;
    private javax.swing.JButton grp_JBut_leftBDC3;
    private javax.swing.JButton grp_JBut_leftBDC4;
    private javax.swing.JButton grp_JBut_leftCLI;
    private javax.swing.JButton grp_JBut_mail;
    private javax.swing.JButton grp_JBut_rightBDC;
    private javax.swing.JButton grp_JBut_rightBDC2;
    private javax.swing.JButton grp_JBut_rightBDC3;
    private javax.swing.JButton grp_JBut_rightBDC4;
    private javax.swing.JButton grp_JBut_rightCLI;
    private javax.swing.JButton grp_JBut_upBDC;
    private javax.swing.JButton grp_JBut_upBDC2;
    private javax.swing.JButton grp_JBut_upBDC3;
    private javax.swing.JButton grp_JBut_upBDC4;
    private javax.swing.JButton grp_JBut_upCLI;
    private javax.swing.JTextField grp_TField_Bottom;
    private javax.swing.JTextField grp_TextField_top;
    private javax.swing.JButton grp_button_valid;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JButton grp_JBut_next;
    private javax.swing.JButton grp_JBut_previous;
}
