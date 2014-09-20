/*
* ComptaPanel.java
*
* Created on 19 décembre 2003, 9:19
*/
package srcastra.astra.gui.modules.export;

import javax.swing.*;
import java.io.*;

import srcastra.astra.gui.sys.parameter.*;

/**
 * @author Thomas
 */
public class ComptaPanel extends javax.swing.JPanel {

    /**
     * Creates new form ComptaPanel
     */
    String type;
    JFileChooser chooser;
    ComptaFilePath path;
    ExportComptable parentframe;

    public ComptaPanel(ExportComptable parentframe) {
        initComponents();
        jProgressBar1.setVisible(false);
        this.parentframe = parentframe;
        chooser = new JFileChooser(new java.io.File("C:/"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        checkFileValidity();
    }

    private boolean checksTmpReccords(int number, String message, JLabel label) {
        boolean retval = true;
        if (number > 0) {
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + message);
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            retval = false;
        }
        return true;
    }

    public void writetype() {
        if (type == null) {
            String tmp = "1";
            if (grp_Check_client.isSelected()) {
                tmp = "2";
            }
            ManageParameter.writeTypeExport(tmp);
        }
    }

    public void checkFileValidity() {
        boolean[] retval = new boolean[6];
        path = ManageParameter.getComptaFilePath();
        type = ManageParameter.getComptaTypeExport();
        if (type != null) {
            if (type.equals("1")) {
                grp_Check_dossier.setSelected(true);
                grp_Check_client.setEnabled(false);
            } else if (type.equals("2")) {
                grp_Check_client.setSelected(true);
                grp_Check_dossier.setEnabled(false);

            }
        }
        grp_TPa_messages.setText("");
        if (path == null) {
            checkVente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            checkAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            checkFinancier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            checkOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            checkClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            checkFourn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("vente_notfound"));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("achat_notfound"));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fin_notfound"));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("od_notfound"));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fourn_notfound"));
            grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("client_notfound"));

        } else {
            if (path.vente != null && !path.vente.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.vente);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("vente_notempty"), checkVente)) {
                    checkVente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[0] = true;
                }
                grp_la_vente.setText(path.vente);
            } else {
                checkVente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("vente_notfound"));
            }
            if (path.achat != null && !path.achat.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.achat);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("achat_notempty"), checkAchat)) {
                    checkAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[1] = true;
                }
                grp_la_acha.setText(path.achat);

            } else {
                checkAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("achat_notfound"));
            }
            if (path.financier != null && !path.financier.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.financier);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fin_notempty"), checkFinancier)) {
                    checkFinancier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[2] = true;
                }
                grp_la_financier.setText(path.financier);
            } else {
                checkFinancier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fin_notfound"));
            }
            if (path.od != null && !path.od.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.od);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("od_notempty"), checkOD)) {
                    checkOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[3] = true;
                }
                grp_la_od.setText(path.od);
            } else {
                checkOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("od_notfound"));
            }
            if (path.fournisseur != null && !path.fournisseur.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.fournisseur);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fourn_notempty"), checkFourn)) {
                    checkFourn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[4] = true;
                }
                grp_la_fournisseur.setText(path.fournisseur);
            } else {
                checkFourn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("fourn_notfound"));
            }
            if (path.client != null && !path.client.equals("")) {
                int num = srcastra.astra.sys.export.DbfManager2.isEmpty(path.client);
                if (checksTmpReccords(num, java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("client_notempty"), checkClient)) {
                    checkClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/valid_15_15 copie.gif")));
                    retval[5] = true;
                }
                grp_la_client.setText(path.client);
            } else {
                checkClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
                grp_TPa_messages.setText(grp_TPa_messages.getText() + "\n" + java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("client_notfound"));
            }


        }
        for (int i = 0; i < retval.length; i++) {
            if (retval[i] == false) {
                parentframe.reloadToolBarInfo(false);
                return;
            }
        }
        parentframe.reloadToolBarInfo(true);
    }


// Open file dialog.


    class MyFilter extends javax.swing.filechooser.FileFilter {
        String type;

        public MyFilter(String type) {
            this.type = type;
        }

        public boolean accept(File file) {
            if (file.isDirectory())
                return true;
            String filename = file.getName();
            if (filename.length() > 5) {
                System.out.println(filename.substring(0, 5));
                return filename.substring(0, 5).equals(type) && (filename.endsWith(".TMP") || filename.endsWith(".DBF"));
            }
            return false;
        }

        public String getDescription() {
            return "*.TMP";
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        grp_Check_dossier = new javax.swing.JRadioButton();
        grp_Check_client = new javax.swing.JRadioButton();
        grp_Label_path = new javax.swing.JLabel();
        grp_la_financier = new javax.swing.JLabel();
        vente = new javax.swing.JLabel();
        grp_la_vente = new javax.swing.JLabel();
        achat = new javax.swing.JLabel();
        grp_la_acha = new javax.swing.JLabel();
        financier = new javax.swing.JLabel();
        grp_la_od = new javax.swing.JLabel();
        od = new javax.swing.JLabel();
        grp_but_vente = new javax.swing.JButton();
        grp_but_achat = new javax.swing.JButton();
        grp_but_financier = new javax.swing.JButton();
        grp_but_od = new javax.swing.JButton();
        fournisseur = new javax.swing.JLabel();
        grp_la_fournisseur = new javax.swing.JLabel();
        client = new javax.swing.JLabel();
        grp_la_client = new javax.swing.JLabel();
        grp_but_fournisseur = new javax.swing.JButton();
        grp_but_client = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_TPa_messages = new javax.swing.JTextPane();
        checkVente = new javax.swing.JLabel();
        checkAchat = new javax.swing.JLabel();
        checkFinancier = new javax.swing.JLabel();
        checkOD = new javax.swing.JLabel();
        checkFourn = new javax.swing.JLabel();
        checkClient = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        setLayout(new java.awt.BorderLayout());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("paramexport")));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        grp_Check_dossier.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_dossier.setSelected(true);
        grp_Check_dossier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("refdos"));
        buttonGroup1.add(grp_Check_dossier);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel2.add(grp_Check_dossier, gridBagConstraints);
        grp_Check_client.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Check_client.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("refclient"));
        buttonGroup1.add(grp_Check_client);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel2.add(grp_Check_client, gridBagConstraints);
        grp_Label_path.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel2.add(grp_Label_path, gridBagConstraints);
        grp_la_financier.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_financier, gridBagConstraints);
        vente.setFont(new java.awt.Font("Tahoma", 0, 10));
        vente.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_vente"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(vente, gridBagConstraints);
        grp_la_vente.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_vente, gridBagConstraints);
        achat.setFont(new java.awt.Font("Tahoma", 0, 10));
        achat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_achat"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(achat, gridBagConstraints);
        grp_la_acha.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_acha, gridBagConstraints);
        financier.setFont(new java.awt.Font("Tahoma", 0, 10));
        financier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_financier"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(financier, gridBagConstraints);
        grp_la_od.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_od, gridBagConstraints);
        od.setFont(new java.awt.Font("Tahoma", 0, 10));
        od.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_od"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(od, gridBagConstraints);
        grp_but_vente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_vente.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_vente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_venteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        jPanel2.add(grp_but_vente, gridBagConstraints);
        grp_but_achat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_achat.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_achat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_achatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        jPanel2.add(grp_but_achat, gridBagConstraints);
        grp_but_financier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_financier.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_financier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_financierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        jPanel2.add(grp_but_financier, gridBagConstraints);
        grp_but_od.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_od.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_od.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_odActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        jPanel2.add(grp_but_od, gridBagConstraints);
        fournisseur.setFont(new java.awt.Font("Tahoma", 0, 10));
        fournisseur.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_fournisseur"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(fournisseur, gridBagConstraints);
        grp_la_fournisseur.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_fournisseur, gridBagConstraints);
        client.setFont(new java.awt.Font("Tahoma", 0, 10));
        client.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("file_client"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 4);
        jPanel2.add(client, gridBagConstraints);
        grp_la_client.setFont(new java.awt.Font("Tahoma", 1, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel2.add(grp_la_client, gridBagConstraints);
        grp_but_fournisseur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_fournisseur.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_fournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_fournisseurActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        jPanel2.add(grp_but_fournisseur, gridBagConstraints);
        grp_but_client.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        grp_but_client.setPreferredSize(new java.awt.Dimension(60, 20));
        grp_but_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_clientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        jPanel2.add(grp_but_client, gridBagConstraints);
        jScrollPane1.setBorder(new javax.swing.border.EtchedBorder());
        jScrollPane1.setPreferredSize(new java.awt.Dimension(10, 70));
        grp_TPa_messages.setBackground(new java.awt.Color(204, 204, 204));
        grp_TPa_messages.setBorder(new javax.swing.border.CompoundBorder());
        grp_TPa_messages.setEnabled(false);
        jScrollPane1.setViewportView(grp_TPa_messages);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel2.add(jScrollPane1, gridBagConstraints);
        checkVente.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkVente, gridBagConstraints);
        checkAchat.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkAchat, gridBagConstraints);
        checkFinancier.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkFinancier, gridBagConstraints);
        checkOD.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkOD, gridBagConstraints);
        checkFourn.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkFourn, gridBagConstraints);
        checkClient.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(checkClient, gridBagConstraints);
        jProgressBar1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(148, 20));
        jProgressBar1.setString(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("export"));
        jProgressBar1.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 1, 0);
        jPanel2.add(jProgressBar1, gridBagConstraints);
        jPanel1.add(jPanel2);
        add(jPanel1, java.awt.BorderLayout.CENTER);
    }//GEN-END:initComponents

    private void grp_but_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_clientActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTSC"));
        chooser.showOpenDialog(this);
        String pathclient = setPath(grp_la_client);
        if (pathclient != null)
            path.client = pathclient;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();
    }//GEN-LAST:event_grp_but_clientActionPerformed

    private void grp_but_fournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_fournisseurActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTSF"));
        chooser.showOpenDialog(this);
        String pathfourn = setPath(grp_la_fournisseur);
        if (pathfourn != null)
            path.fournisseur = pathfourn;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();
    }//GEN-LAST:event_grp_but_fournisseurActionPerformed

    private void grp_but_odActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_odActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTJO"));
        chooser.showOpenDialog(this);
        String pathod = setPath(grp_la_od);
        if (pathod != null)
            path.od = pathod;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();
    }//GEN-LAST:event_grp_but_odActionPerformed

    private void grp_but_financierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_financierActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTJF"));
        chooser.showOpenDialog(this);
        String pathfin = setPath(grp_la_financier);
        if (path == null)
            path = new ComptaFilePath();
        if (pathfin != null)
            path.financier = pathfin;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();
    }//GEN-LAST:event_grp_but_financierActionPerformed

    private void grp_but_achatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_achatActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTJA"));
        chooser.showOpenDialog(this);
        String pathachat = setPath(grp_la_acha);
        if (path == null)
            path = new ComptaFilePath();
        if (pathachat != null)
            path.achat = pathachat;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();
    }//GEN-LAST:event_grp_but_achatActionPerformed

    private void grp_but_venteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_venteActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("CPTJV"));
        chooser.showOpenDialog(this);
        String pathvente = setPath(grp_la_vente);
        if (path == null)
            path = new ComptaFilePath();
        if (pathvente != null)
            path.vente = pathvente;
        ManageParameter.writeComptaFilePath(path);
        checkFileValidity();


    }//GEN-LAST:event_grp_but_venteActionPerformed

    private String setPath(JLabel label) {
        File file = chooser.getSelectedFile();
        if (file != null) {
            label.setText(file.getAbsolutePath());
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * Getter for property grp_Label_path.
     *
     * @return Value of property grp_Label_path.
     */
    public javax.swing.JLabel getGrp_Label_path() {
        return grp_Label_path;
    }

    /**
     * Setter for property grp_Label_path.
     *
     * @param grp_Label_path New value of property grp_Label_path.
     */
    public void setGrp_Label_path(javax.swing.JLabel grp_Label_path) {
        this.grp_Label_path = grp_Label_path;
    }

    /**
     * Getter for property path.
     *
     * @return Value of property path.
     */
    public srcastra.astra.gui.sys.parameter.ComptaFilePath getPath() {
        return path;
    }

    /**
     * Setter for property path.
     *
     * @param path New value of property path.
     */
    public void setPath(srcastra.astra.gui.sys.parameter.ComptaFilePath path) {
        this.path = path;
    }

    /**
     * Getter for property jProgressBar1.
     *
     * @return Value of property jProgressBar1.
     */
    public javax.swing.JProgressBar getJProgressBar1() {
        return jProgressBar1;
    }

    /**
     * Setter for property jProgressBar1.
     *
     * @param jProgressBar1 New value of property jProgressBar1.
     */
    public void setJProgressBar1(javax.swing.JProgressBar jProgressBar1) {
        this.jProgressBar1 = jProgressBar1;
    }

    /**
     * Getter for property grp_Check_client.
     *
     * @return Value of property grp_Check_client.
     */
    public javax.swing.JRadioButton getGrp_Check_client() {
        return grp_Check_client;
    }

    /**
     * Setter for property grp_Check_client.
     *
     * @param grp_Check_client New value of property grp_Check_client.
     */
    public void setGrp_Check_client(javax.swing.JRadioButton grp_Check_client) {
        this.grp_Check_client = grp_Check_client;
    }

    /**
     * Getter for property grp_Check_dossier.
     *
     * @return Value of property grp_Check_dossier.
     */
    public javax.swing.JRadioButton getGrp_Check_dossier() {
        return grp_Check_dossier;
    }

    /**
     * Setter for property grp_Check_dossier.
     *
     * @param grp_Check_dossier New value of property grp_Check_dossier.
     */
    public void setGrp_Check_dossier(javax.swing.JRadioButton grp_Check_dossier) {
        this.grp_Check_dossier = grp_Check_dossier;
    }

// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel achat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel checkAchat;
    private javax.swing.JLabel checkClient;
    private javax.swing.JLabel checkFinancier;
    private javax.swing.JLabel checkFourn;
    private javax.swing.JLabel checkOD;
    private javax.swing.JLabel checkVente;
    private javax.swing.JLabel client;
    private javax.swing.JLabel financier;
    private javax.swing.JLabel fournisseur;
    private javax.swing.JRadioButton grp_Check_client;
    private javax.swing.JRadioButton grp_Check_dossier;
    private javax.swing.JLabel grp_Label_path;
    private javax.swing.JTextPane grp_TPa_messages;
    private javax.swing.JButton grp_but_achat;
    private javax.swing.JButton grp_but_client;
    private javax.swing.JButton grp_but_financier;
    private javax.swing.JButton grp_but_fournisseur;
    private javax.swing.JButton grp_but_od;
    private javax.swing.JButton grp_but_vente;
    private javax.swing.JLabel grp_la_acha;
    private javax.swing.JLabel grp_la_client;
    private javax.swing.JLabel grp_la_financier;
    private javax.swing.JLabel grp_la_fournisseur;
    private javax.swing.JLabel grp_la_od;
    private javax.swing.JLabel grp_la_vente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel od;
    private javax.swing.JLabel vente;
// End of variables declaration//GEN-END:variables

}
