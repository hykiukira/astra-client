/*
 * SettingsPane.java
 *
 * Created on 11 juin 2003, 13:03
 */

/**
 *
 * @author  Administrateur
 */
package srcastra.test;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class SettingsPane extends javax.swing.JDialog {
    
    private Settings settings;
    private boolean change = true;
    
    /** Creates new form SettingsPane */
    public SettingsPane(java.awt.Frame parent, boolean modal, Settings settings) {
        super(parent, modal);
        this.settings = settings;
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PAN_general = new javax.swing.JPanel();
        jPanel411 = new javax.swing.JPanel();
        jSeparator111 = new javax.swing.JSeparator();
        jLabel111 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JSL_time = new javax.swing.JSlider();
        COMBO_typeTime = new javax.swing.JComboBox();
        LBL_time = new javax.swing.JLabel();
        PAN_mail = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        CBOX_emailLogSend = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TF_email_smtpServer = new javax.swing.JTextField();
        TF_email_addr = new javax.swing.JTextField();
        TF_email_senderName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PAN_proxy = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        CBOX_proxyEnabled = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TF_proxyServerName = new javax.swing.JTextField();
        TF_proxyServerPort = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        BT_OK = new javax.swing.JButton();
        BT_cancel = new javax.swing.JButton();
        BT_apply = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 10));
        PAN_general.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        PAN_general.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                PAN_Shown(evt);
            }
        });

        jPanel411.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel411.add(jSeparator111, gridBagConstraints);

        jLabel111.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel111.setText("tout(e)s les ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel411.add(jLabel111, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10));
        jLabel3.setText("V\u00e9rification adresse WAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel411.add(jLabel3, gridBagConstraints);

        JSL_time.setMaximum(59);
        JSL_time.setMinimum(5);
        JSL_time.setValue(5);
        JSL_time.setPreferredSize(new java.awt.Dimension(180, 16));
        JSL_time.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JSL_timeStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 4);
        jPanel411.add(JSL_time, gridBagConstraints);

        COMBO_typeTime.setFont(new java.awt.Font("Dialog", 1, 10));
        COMBO_typeTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minutes", "Heures", "Jours" }));
        COMBO_typeTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                COMBO_typeTimeItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel411.add(COMBO_typeTime, gridBagConstraints);

        LBL_time.setFont(new java.awt.Font("Dialog", 1, 10));
        LBL_time.setText("" + JSL_time.getValue());
        LBL_time.setPreferredSize(new java.awt.Dimension(37, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 3);
        jPanel411.add(LBL_time, gridBagConstraints);

        PAN_general.add(jPanel411);

        jTabbedPane1.addTab("G\u00e9n\u00e9ral", PAN_general);

        PAN_mail.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        PAN_mail.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                PAN_Shown(evt);
            }
        });

        jPanel41.setLayout(new java.awt.GridBagLayout());

        CBOX_emailLogSend.setFont(new java.awt.Font("Dialog", 0, 10));
        CBOX_emailLogSend.setText("Activer l'envoi de log par mail");
        CBOX_emailLogSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBOX_emailLogSendActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel41.add(CBOX_emailLogSend, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel41.add(jSeparator11, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel11.setText("Nom du serveur SMTP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(jLabel11, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel21.setText("Adresse email pour l'envoi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(jLabel21, gridBagConstraints);

        TF_email_smtpServer.setFont(new java.awt.Font("Dialog", 0, 10));
        TF_email_smtpServer.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(TF_email_smtpServer, gridBagConstraints);

        TF_email_addr.setFont(new java.awt.Font("Dialog", 0, 10));
        TF_email_addr.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(TF_email_addr, gridBagConstraints);

        TF_email_senderName.setFont(new java.awt.Font("Dialog", 0, 10));
        TF_email_senderName.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(TF_email_senderName, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10));
        jLabel4.setText("Nom de l'envoyeur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel41.add(jLabel4, gridBagConstraints);

        PAN_mail.add(jPanel41);

        jTabbedPane1.addTab("Envoi de mail", PAN_mail);

        PAN_proxy.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        PAN_proxy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                PAN_Shown(evt);
            }
        });

        jPanel4.setLayout(new java.awt.GridBagLayout());

        CBOX_proxyEnabled.setFont(new java.awt.Font("Dialog", 0, 10));
        CBOX_proxyEnabled.setText("Enable Proxy Server settings");
        CBOX_proxyEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBOX_proxyEnabledActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel4.add(CBOX_proxyEnabled, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jSeparator1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel1.setText("Proxy Serveur adress");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel2.setText("Proxy Serveur port");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel2, gridBagConstraints);

        TF_proxyServerName.setFont(new java.awt.Font("Dialog", 0, 10));
        TF_proxyServerName.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(TF_proxyServerName, gridBagConstraints);

        TF_proxyServerPort.setFont(new java.awt.Font("Dialog", 0, 10));
        TF_proxyServerPort.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(TF_proxyServerPort, gridBagConstraints);

        PAN_proxy.add(jPanel4);

        jTabbedPane1.addTab("Proxy", PAN_proxy);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        BT_OK.setFont(new java.awt.Font("Dialog", 1, 10));
        BT_OK.setText("ok");
        BT_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_OKActionPerformed(evt);
            }
        });

        jPanel5.add(BT_OK);

        BT_cancel.setFont(new java.awt.Font("Dialog", 1, 10));
        BT_cancel.setText("cancel");
        BT_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_cancelActionPerformed(evt);
            }
        });

        jPanel5.add(BT_cancel);

        BT_apply.setFont(new java.awt.Font("Dialog", 1, 10));
        BT_apply.setText("apply");
        BT_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_applyActionPerformed(evt);
            }
        });

        jPanel5.add(BT_apply);

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(400, 300));
        setLocation((screenSize.width-400)/2,(screenSize.height-300)/2);
    }//GEN-END:initComponents

    private void PAN_Shown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_PAN_Shown
        if (change) { 
            loadSettings();
            change = false;
        }
    }//GEN-LAST:event_PAN_Shown

    private void BT_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_applyActionPerformed
        saveSettings();
        if (!settings.saveSettings()) JOptionPane.showMessageDialog(this, "je sais pas sauvegarder");
    }//GEN-LAST:event_BT_applyActionPerformed

    private void BT_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_BT_cancelActionPerformed

    private void BT_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_OKActionPerformed
        saveSettings();
        settings.saveSettings();
        this.dispose();
    }//GEN-LAST:event_BT_OKActionPerformed

    private void CBOX_emailLogSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOX_emailLogSendActionPerformed
        boolean enabled = CBOX_emailLogSend.isSelected();
        TF_email_senderName.setEditable(enabled);
        TF_email_addr.setEditable(enabled);
        TF_email_smtpServer.setEditable(enabled);
    }//GEN-LAST:event_CBOX_emailLogSendActionPerformed

    private void JSL_timeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JSL_timeStateChanged
        LBL_time.setText("" + JSL_time.getValue());
    }//GEN-LAST:event_JSL_timeStateChanged

    private void COMBO_typeTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_COMBO_typeTimeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String typeTime = (String) evt.getItem();
            int min = 0;
            int max = 0;
            if (typeTime.equals("Minutes")) {
                min = 5;
                max = 59;
            }
            else if (typeTime.equals("Heures")) {
                min = 1;
                max = 23;
            }
            else if (typeTime.equals("Jours")) {
                min = 1;
                max = 31;
            }
            JSL_time.setMinimum(min);
            JSL_time.setMaximum(max);
        }
    }//GEN-LAST:event_COMBO_typeTimeItemStateChanged

    private void CBOX_proxyEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOX_proxyEnabledActionPerformed
        // active ou d�sactive les champs de texte du panel Proxy Settings
        boolean checkedopt = CBOX_proxyEnabled.isSelected();
        TF_proxyServerName.setEditable(checkedopt);
        TF_proxyServerPort.setEditable(checkedopt);
    }//GEN-LAST:event_CBOX_proxyEnabledActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    private void loadSettings() {
        // settings G�n�rales
        int delay = settings.getMmsTimeDelay() / 60000;
        System.out.println("delay => " + delay);
        int oneMn = 3600;
        int mmsTime = 0;
        if (delay < 60) {
            mmsTime = delay;
            COMBO_typeTime.setSelectedItem("Minutes");
        }
        else if (delay < 1440) {
            mmsTime = delay / 60;
            COMBO_typeTime.setSelectedItem("Heures");
        }
        else {
            mmsTime = delay / 1440;
            COMBO_typeTime.setSelectedItem("Jours");
        }
        JSL_time.setValue(mmsTime);
        
        // settings Email
        CBOX_emailLogSend.setSelected(settings.isEmailLogEnabled());
        TF_email_senderName.setText(settings.getMail_senderName());
        TF_email_smtpServer.setText(settings.getMail_smtpServer());
        TF_email_addr.setText(settings.getMail_emailAddr());
        CBOX_emailLogSendActionPerformed(new ActionEvent(CBOX_emailLogSend, ActionEvent.ACTION_PERFORMED, "cmd"));
        
        // settings du  proxy
        CBOX_proxyEnabled.setSelected(settings.isProxySettings());
        TF_proxyServerName.setText(settings.getProxyServerName());
        TF_proxyServerPort.setText(settings.getProxyServerPort());
        CBOX_proxyEnabledActionPerformed(new ActionEvent(CBOX_proxyEnabled, ActionEvent.ACTION_PERFORMED, "cmd"));
    }
    
    private void saveSettings() {
        // settings G�n�rales
        String typeTime = COMBO_typeTime.getSelectedItem().toString();
        int mmsTime = JSL_time.getValue();
        int oneMn = 60000;
        int totDelay = 0;
        if (typeTime.equals("Minutes")) totDelay = oneMn * mmsTime;
        else if (typeTime.equals("Heures")) totDelay = oneMn * 60 * mmsTime;
        else if (typeTime.equals("Jours")) totDelay = oneMn * 1440 * mmsTime; 
        settings.setMmsTimeDelay(totDelay);
        System.out.println("[save] delay => " + totDelay);
        
        // settings Email
        boolean emailEnabled = CBOX_emailLogSend.isSelected();
        settings.setEmailLogEnabled(emailEnabled);
        if (emailEnabled) {
            settings.setMail_senderName(TF_email_senderName.getText());
            settings.setMail_emailAddr(TF_email_addr.getText());
            settings.setMail_smtpServer(TF_email_smtpServer.getText());
        }
       
        
        // settings du proxy
        boolean proxyEnabled = CBOX_proxyEnabled.isSelected();
        settings.setProxySettings(proxyEnabled);
        if (proxyEnabled) {
            settings.setProxyServerName(TF_proxyServerName.getText());
            settings.setProxyServerPort(TF_proxyServerPort.getText());
        }
        change = true;
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JCheckBox CBOX_proxyEnabled;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField TF_email_smtpServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider JSL_time;
    private javax.swing.JPanel PAN_general;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton BT_OK;
    private javax.swing.JTextField TF_proxyServerName;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JPanel PAN_mail;
    private javax.swing.JSeparator jSeparator111;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton BT_cancel;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel411;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JLabel LBL_time;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JTextField TF_email_senderName;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JComboBox COMBO_typeTime;
    private javax.swing.JButton BT_apply;
    private javax.swing.JTextField TF_proxyServerPort;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel PAN_proxy;
    private javax.swing.JCheckBox CBOX_emailLogSend;
    private javax.swing.JTextField TF_email_addr;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}
