/*
 * testFocus.java
 *
 * Created on 27 janvier 2003, 12:14
 */

package srcastra.test;

/**
 *
 * @author  thomas
 */
public class testFocus extends javax.swing.JFrame {
    
    /** Creates new form testFocus */
    public testFocus() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        liste1 = new srcastra.astra.gui.components.combobox.liste.Liste();
        liste2 = new srcastra.astra.gui.components.combobox.liste.Liste();
        liste3 = new srcastra.astra.gui.components.combobox.liste.Liste();
        liste4 = new srcastra.astra.gui.components.combobox.liste.Liste();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.add(liste1);

        jPanel1.add(liste2);

        jPanel1.add(liste3);

        jPanel1.add(liste4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(400, 300));
        setLocation((screenSize.width-400)/2,(screenSize.height-300)/2);
    }//GEN-END:initComponents
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new testFocus().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private srcastra.astra.gui.components.combobox.liste.Liste liste4;
    private srcastra.astra.gui.components.combobox.liste.Liste liste3;
    private srcastra.astra.gui.components.combobox.liste.Liste liste2;
    private srcastra.astra.gui.components.combobox.liste.Liste liste1;
    // End of variables declaration//GEN-END:variables
    
}
