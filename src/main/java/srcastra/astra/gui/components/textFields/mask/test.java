/*

 * test.java

 *

 * Created on 17 juillet 2003, 15:34

 */


package srcastra.astra.gui.components.textFields.mask;

import srcastra.astra.gui.components.textFields.*;


/**
 * @author thomas
 */

public class test extends javax.swing.JFrame {


    /**
     * Creates new form test
     */


    public test() {

        initComponents();

        jTextField1.setDocument(new Decimal(jTextField1));

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

        jTextField1 = new javax.swing.JTextField();


        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {

                exitForm(evt);

            }

        });


        jTextField1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jTextField1ActionPerformed(evt);

            }

        });


        getContentPane().add(jTextField1, java.awt.BorderLayout.NORTH);


        pack();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setSize(new java.awt.Dimension(400, 300));

        setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2);

    }//GEN-END:initComponents


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        // Add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed


    /**
     * Exit the Application
     */

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm

        System.exit(0);

    }//GEN-LAST:event_exitForm


    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {

        new test().show();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JTextField jTextField1;

    // End of variables declaration//GEN-END:variables


}

