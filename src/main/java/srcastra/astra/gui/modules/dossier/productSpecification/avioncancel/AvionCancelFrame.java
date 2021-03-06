/*

 * AvionCancelFrame.java

 *

 * Created on 28 octobre 2003, 15:17

 */


package srcastra.astra.gui.modules.dossier.productSpecification.avioncancel;

import srcastra.astra.sys.classetransfert.*;

import srcastra.test.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.dossier.avion.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.gui.modules.mainScreenModule.*;

/**
 * @author Thomas
 */

public class AvionCancelFrame extends javax.swing.JDialog {


    /**
     * Creates new form AvionCancelFrame
     */

    Loginusers_T currentUser;

    public AvionCancelFrame(java.awt.Frame parent, boolean modal, astrainterface serveur, Loginusers_T user, int cscleunik, Avion_ticket_T avion, Dossier_T dossier, DossierMainScreenModule dossiermod) {

        super(parent, modal);

        //ServeurConnect connect=new ServeurConnect();

        //connect.connectServeur();       

        currentUser = new Loginusers_T();

        currentUser.setUrlmabrev("Fr");

        initComponents();

        getContentPane().add(new AvionCancel(user, serveur, cscleunik, avion, dossier, this, dossiermod));

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


        setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", currentUser.getLangage()).getString("caav_annul"));

        setLocationRelativeTo(this);

        setModal(true);

        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {

                closeDialog(evt);

            }

        });


        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - 540) / 2, (screenSize.height - 400) / 2, 540, 400);

    }//GEN-END:initComponents


    /**
     * Closes the dialog
     */

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog

        setVisible(false);

        dispose();

    }//GEN-LAST:event_closeDialog

    public void close() {

        setVisible(false);

        dispose();


    }

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {

        //  new AvionCancelFrame(new javax.swing.JFrame(), true).show();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables


}

