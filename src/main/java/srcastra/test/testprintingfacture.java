/*
 * testprintingfacture.java
 *
 * Created on 27 juin 2003, 14:53
 */

package srcastra.test;
import java.io.*;

/**
 *
 * @author  Thomas
 */
public class testprintingfacture extends javax.swing.JFrame {
    
    /** Creates new form testprintingfacture */
    public testprintingfacture() {
        initComponents();
        jEditorPane1.setEditable(false);
        try{
            
      //  jEditorPane1.setPage(new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory()+"/fact3000049.html").getAbsolutePath());
              jEditorPane1.setPage("file:/C:/fact3000049.html");
              // jEditorPane1.setPage("http://www.google.com");
        }catch(java.io.IOException in){
            in.printStackTrace();
            
        }
        }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jScrollPane1.setViewportView(jEditorPane1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(800, 600));
        setLocation((screenSize.width-800)/2,(screenSize.height-600)/2);
    }//GEN-END:initComponents
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new testprintingfacture().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane jEditorPane1;
    // End of variables declaration//GEN-END:variables
    
}
