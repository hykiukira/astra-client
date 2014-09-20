/*
 * bsp.java
 *
 * Created on 8 juin 2004, 12:22
 */

package srcastra.astra.gui.modules.bsp;

import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;

/**
 * @author Administrateur
 */
public class BspFrame extends javax.swing.JDialog {

    /**
     * Creates new form bsp
     */
    Loginusers_T m_user;
    astrainterface m_serveur;
    java.awt.Frame m_frame;

    public BspFrame(java.awt.Frame parent, boolean modal, astrainterface serveur, Loginusers_T user) {
        super(parent, modal);
        m_user = user;
        m_serveur = serveur;
        m_frame = parent;
        initComponents();
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
        grp_But_ok = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grp_ADate_start = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_ADate_end = new srcastra.astra.gui.components.date.thedate.ADate();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BSP");
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        grp_But_ok.setText("OK");
        grp_But_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_okActionPerformed(evt);
            }
        });

        jPanel1.add(grp_But_ok);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/edition/bsp").getString("periode")));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/edition/bsp").getString("de"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 12);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/edition/bsp").getString("a"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 12);
        jPanel2.add(jLabel2, gridBagConstraints);

        grp_ADate_start.setGrp_Comp_nextComponent(grp_ADate_end);
        jPanel2.add(grp_ADate_start, new java.awt.GridBagConstraints());

        grp_ADate_end.setGrp_Comp_nextComponent(grp_But_ok);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel2.add(grp_ADate_end, gridBagConstraints);

        jPanel3.add(jPanel2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2, 400, 200);
    }//GEN-END:initComponents

    private void grp_But_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_okActionPerformed
        if (grp_ADate_start.getDate() == null || grp_ADate_start.getDate().isOpen() || grp_ADate_start.getDate().isUnknow() || grp_ADate_end.getDate() == null || grp_ADate_end.getDate().isOpen() || grp_ADate_end.getDate().isUnknow()) {
            System.out.println("date incorrecte");
        } else {
            setVisible(false);
            new BspReport(m_serveur, m_user, m_frame, grp_ADate_start.getDate(), grp_ADate_end.getDate());
            dispose();
        }

    }//GEN-LAST:event_grp_But_okActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //   new bsp(new javax.swing.JFrame(), true).show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_end;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_start;
    private javax.swing.JButton grp_But_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
