/*
 * test.java
 *
 * Created on 28 f�vrier 2003, 16:51
 */

package srcastra.testhelp;
import java.awt.event.*;
import java.awt.*;
import java.util.Hashtable;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.help.*;
/**
 *
 * @author  Thomas
 */
public class test extends javax.swing.JFrame {
   JHelpNavigator nav;
  JHelpContentViewer viewer;
  HelpSet hs;
  HelpBroker hb;


    /** Creates new form test */
    public test() {
        initComponents();
      try {
      URL hsURL = getClass().getResource("HelpSet.hs");
      hs = new HelpSet(null, hsURL);
      System.out.println("Found help set at " + hsURL);
    }
    catch (Exception ee) {
      System.out.println("HelpSet not found");
      System.exit(0);
    }

    // create HelpBroker from HelpSet
    hb = hs.createHelpBroker(  );
    // enable function key F1
    hb.enableHelpKey(getRootPane(  ), "overview", hs);
    viewer = new JHelpContentViewer(hs);
    viewer.setPreferredSize(new Dimension(200,220));
//    viewer.setCurrentID("typefaces");
   
    // add a navigator with a table of contents view
    nav = (JHelpNavigator)
      hs.getNavigatorView("TOC").createNavigator(viewer.getModel(  ));
    nav.setPreferredSize(new Dimension(200,220));
    // CSH.setHelpIDString(TypeFacer.this.getRootPane(  ), "typefaces");
     CSH.setHelpIDString(helpItem, "typefaces");


        // update the embedded help content panel
//        viewer.setCurrentID("typefaces");
        // CSH.setHelpIDString(TypeFacer.this.getRootPane(  ), "colors");
        CSH.setHelpIDString(helpItem, "colors");


        // update the embedded help content panel
//        viewer.setCurrentID("colors");
   ActionListener helper = new CSH.DisplayHelpFromSource(hb);
    helpItem.addActionListener(helper);

    // assign map IDs for field-level context-sensitive help

   



    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jMenuBar1 = new javax.swing.JMenuBar();
        helpItem = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        helpItem.setText("Menu");
        jMenuItem1.setText("Item");
        helpItem.add(jMenuItem1);
        jMenuBar1.add(helpItem);
        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents
    
    /** Exit the Application */
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
    private javax.swing.JMenu helpItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
    
}
