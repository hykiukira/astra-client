/*





 * test.java





 *





 * Created on 28 november 2002, 13:11





 */


package srcastra.astra.gui.components.textFields.FormatedTextField;


import java.awt.Dimension;


import javax.swing.*;


import javax.swing.text.*;


import java.awt.Dimension;


import srcastra.astra.gui.sys.formVerification.*;


import java.awt.*;


import java.util.*;


import java.text.*;


/**
 * @author Thomas
 */


public class test extends javax.swing.JFrame {


    /**
     * Creates new form test
     */


    public test() {


        initComponents();


        f1 = new FormatedTextField();


        f2 = new FormatedTextField();


        f3 = new FormatedTextField();


        f4 = new FormatedTextField();


        f1.setPreferredSize(new Dimension(150, 18));


        f2.setPreferredSize(new Dimension(150, 18));


        f3.setPreferredSize(new Dimension(150, 18));


        f4.setPreferredSize(new Dimension(150, 18));


        f1.setValue(new Float(0.00));


        f2.setValue(new Float(0.00));


        f3.setValue(new Float(0.00));


        f4.setValue(new Float(0.00));


        DefaultFormatter fmt = new NumberFormatter(new DecimalFormat("########0.00"));


        fmt.setValueClass(f1.getValue().getClass());


        DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);


        f1.setFormatterFactory(fmtFactory);


        f2.setFormatterFactory(fmtFactory);


        f3.setFormatterFactory(fmtFactory);


        f4.setFormatterFactory(fmtFactory);


        f1.setGrp_Comp_nextComponent(f2);


        f2.setGrp_Comp_nextComponent(f3);


        f3.setGrp_Comp_nextComponent(f4);


        jPanel1.add(f1);


        jPanel1.add(f2);


        jPanel1.add(f3);


        jPanel1.add(f4);


    }


    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */


    private void initComponents() {//GEN-BEGIN:initComponents


        jPanel1 = new javax.swing.JPanel();


        addWindowListener(new java.awt.event.WindowAdapter() {


            public void windowClosing(java.awt.event.WindowEvent evt) {


                exitForm(evt);


            }


        });


        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);


        pack();


        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();


        setSize(new java.awt.Dimension(400, 300));


        setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2);


    }//GEN-END:initComponents


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


    private javax.swing.JPanel jPanel1;

    // End of variables declaration//GEN-END:variables


    FormatedTextField f1;


    FormatedTextField f2;


    FormatedTextField f3;


    FormatedTextField f4;


}





