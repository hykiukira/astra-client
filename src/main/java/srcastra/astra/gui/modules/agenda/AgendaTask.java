/*


 * AgendaTask.java


 *


 * Created on 27 août 2003, 11:42


 *


 * Cette classe permet d'afficher lengthdialog box pour l'agenda


 * 


 */


package srcastra.astra.gui.modules.agenda;


import java.awt.*;


import srcastra.astra.sys.rmi.*;


import srcastra.test.ServeurConnect;


import srcastra.astra.sys.classetransfert.Loginusers_T;


import srcastra.astra.sys.classetransfert.utils.*;


import srcastra.astra.sys.classetransfert.dossier.*;


import srcastra.astra.sys.classetransfert.dossier.avion.*;


import java.sql.*;


import srcastra.astra.sys.rmi.astraimplement;


import srcastra.astra.sys.rmi.utils.Poolconnection;


import java.rmi.*;


import srcastra.astra.sys.Transaction;


import srcastra.astra.sys.Logger;


import java.util.ArrayList;


import java.util.Hashtable;


import srcastra.astra.sys.classetransfert.utils.Date;


import srcastra.astra.sys.classetransfert.clients.*;


import srcastra.astra.sys.classetransfert.Loginusers_T;


import srcastra.astra.sys.rmi.Exception.*;


import srcastra.astra.sys.produit.*;


import srcastra.astra.sys.compta.*;


import srcastra.astra.sys.rmi.Recherche.*;


import srcastra.astra.sys.compress.CompressArray;


import srcastra.astra.sys.classetransfert.*;


import srcastra.astra.sys.compta.*;


import srcastra.astra.gui.modules.printing.classelangueuser.*;


import srcastra.astra.sys.printing.PrintingInfo;


import srcastra.astra.gui.modules.printing.produit.produitInfo.*;


import srcastra.astra.gui.modules.compta.achat.*;


import srcastra.astra.sys.rmi.groupe_dec.*;


import java.io.*;


import srcastra.astra.gui.modules.*;


/**
 * @author Driart
 */


public class AgendaTask extends javax.swing.JDialog {


    Loginusers_T user;


    ServeurConnect connect;


    DossierRmiInterface dossier;


    final static int FACTURE = 1;


    final static int ECHEANCE = 2;


    final static int ACOMPTE = 3;


    final static int CONFIRMATION = 4;


    final static int STATUT = 5;


    java.awt.Frame frame;


    /**
     * Creates new form AgendaTask
     */


    public AgendaTask(java.awt.Frame parent, boolean modal, MainScreenModule blla) {

        // super(parent, modal);


        this.user = blla.getCurrentUser();


        this.frame = frame;


        initComponents();


        try {


            dossier = blla.getServeur().renvDossierRmiObject(user.getUrcleunik());


        } catch (java.rmi.RemoteException rn) {


            rn.printStackTrace();


        }


        getInfo("2003-09-02");


        setVisible(true); //pour la tache d'une journée


    }

    //la méthode pour remplissage de la liste des taches


    public void getInfo(String date) {


        ArrayList result = new ArrayList();


        try {


            result = dossier.getAgendaPourDate(user.getUrcleunik(), date);


        }


        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


            se.printStackTrace();


            System.out.println("Erreur lors de getInfo");


        }


        catch (java.rmi.RemoteException re) {


            re.printStackTrace();


            System.out.println("Erreur lors de getInfo");


        }

        //remplissage de la 1ère colonne avec les numéros des dossiers


        for (int i = 0; i < result.size(); i++) {


            Object[] tab = (Object[]) result.get(i);


            jTable1.setValueAt(tab[2], i, 0);


            System.out.println("Le tab[0] = " + tab[0]);


            System.out.println("Le tab[1] = " + tab[1]);


            System.out.println("Le tab[2] = " + tab[2]);


            System.out.println("Le tab[3] = " + tab[3]);


            System.out.println("Le tab[4] = " + tab[4]);


        }

        //remplissage de la 2ème colonne avec les numéros des achats


        for (int i = 0; i < result.size(); i++) {


            Object[] tab = (Object[]) result.get(i);


            jTable1.setValueAt(tab[3], i, 1);


            System.out.println("Le tab[3] = " + tab[3]);


            System.out.println("Le result.size() est: " + result.size());

            //remplissage du text field


            jTextArea1.setText(tab[4].toString());


        }


        jLabel1.setText("La tache était généré le: " + CalculDate.getTodayDate().toString()); //on met soit la date quand c'était généré, soit la date d'aujourd'hui, soit les deux


    }

    //la méthode pour l'insertion de la liste des taches publiques pour une date donnée

    //il peut y avoir 2 insertions: taches publiques et taches perssonelles


    public void insertPublicInfo(String date) {


        for (int i = 0; i < jTable1.getRowCount(); i++)


            try {


                dossier.insertPublicAgendaPourDate(user.getUrcleunik(), date, jTextArea1.getText(), jTable1.getEditingColumn(), jTable1.getEditingColumn());


            } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                se.printStackTrace();


                System.out.println("Erreur lors de l'insertion");


            }


            catch (java.rmi.RemoteException re) {


                re.printStackTrace();


                System.out.println("Erreur lors de l'insertion");


            }


    }

    //la méthode pour l'insertion de la liste des taches personnels pour une date donnée


    public void insertPersonalInfo(String date) {


        for (int i = 0; i < jTable1.getRowCount(); i++)


            try {


                dossier.insertPersonalAgendaPourDate(user.getUrcleunik(), date, jTextArea1.getText(), new Integer(jTable1.getValueAt(i, 0).toString()).intValue(), new Integer(jTable1.getValueAt(i, 1).toString()).intValue());


            } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                se.printStackTrace();


                System.out.println("Erreur lors de l'insertion");


            }


            catch (java.rmi.RemoteException re) {


                re.printStackTrace();


                System.out.println("Erreur lors de l'insertion");


            }


    }

    //la méthode pour la modification de la liste des taches


    public void modifyInfo(String date, int cle_unik) {


        for (int i = 0; i < jTable1.getRowCount(); i++)


            try {


                dossier.updateAgendaPourDate(user.getUrcleunik(), date, jTextArea1.getText(), jTable1.getEditingColumn(), jTable1.getEditingColumn(), cle_unik, jTable1.getEditingColumn());


            } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


                se.printStackTrace();


                System.out.println("Erreur lors de la mise à jour");


            }


            catch (java.rmi.RemoteException re) {


                re.printStackTrace();


                System.out.println("Erreur lors de la mise à jour");


            }


    }


    /**
     * This method is called from within the constructor to
     * <p/>
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * <p/>
     * always regenerated by the Form Editor.
     */


    private void initComponents() {//GEN-BEGIN:initComponents


        jPanel1 = new javax.swing.JPanel();


        jScrollPane2 = new javax.swing.JScrollPane();


        jTextArea1 = new javax.swing.JTextArea();


        jPanel2 = new javax.swing.JPanel();


        jScrollPane1 = new javax.swing.JScrollPane();


        jTable1 = new javax.swing.JTable();


        jPanel3 = new javax.swing.JPanel();


        jLabel1 = new javax.swing.JLabel();


        setModal(true);


        addWindowListener(new java.awt.event.WindowAdapter() {


            public void windowClosing(java.awt.event.WindowEvent evt) {


                closeDialog(evt);


            }


        });


        jPanel1.setLayout(new java.awt.BorderLayout());


        jPanel1.setBackground(new java.awt.Color(204, 0, 0));


        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));


        jScrollPane2.setMinimumSize(new java.awt.Dimension(100, 160));


        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 150));


        jTextArea1.setBackground(new java.awt.Color(255, 255, 204));


        jTextArea1.setFont(new java.awt.Font("Verdana", 0, 12));


        jTextArea1.setLineWrap(true);


        jTextArea1.setRows(20);


        jTextArea1.setTabSize(20);


        jTextArea1.setWrapStyleWord(true);


        jTextArea1.setMinimumSize(new java.awt.Dimension(20, 16));


        jScrollPane2.setViewportView(jTextArea1);


        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);


        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);


        jPanel2.setLayout(new java.awt.BorderLayout());


        jPanel2.setBackground(new java.awt.Color(204, 0, 0));


        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));


        jPanel2.setPreferredSize(new java.awt.Dimension(10, 140));


        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));


        jScrollPane1.setFont(new java.awt.Font("Verdana", 1, 12));


        jTable1.setBackground(new java.awt.Color(255, 204, 102));


        jTable1.setModel(new javax.swing.table.DefaultTableModel(


                new Object[][]{


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null},


                        {null, null}


                },


                new String[]{


                        "Numéro du dossier", "Status: traité/non traité"


                }


        ) {


            Class[] types = new Class[]{


                    java.lang.Integer.class, java.lang.Integer.class


            };


            public Class getColumnClass(int columnIndex) {


                return types[columnIndex];


            }


        });


        jTable1.setPreferredSize(new java.awt.Dimension(300, 432));


        jScrollPane1.setViewportView(jTable1);


        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);


        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);


        jPanel3.setLayout(new java.awt.BorderLayout());


        jPanel3.setBackground(new java.awt.Color(204, 0, 0));


        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));


        jPanel3.setMinimumSize(new java.awt.Dimension(10, 20));


        jPanel3.setPreferredSize(new java.awt.Dimension(10, 15));


        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12));


        jLabel1.setForeground(new java.awt.Color(255, 255, 255));


        jLabel1.setText("jLabel1");


        jPanel3.add(jLabel1, java.awt.BorderLayout.SOUTH);


        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);


        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();


        setBounds((screenSize.width - 568) / 2, (screenSize.height - 410) / 2, 568, 410);


    }//GEN-END:initComponents


    /**
     * Closes the dialog
     */


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog


        setVisible(false);


        dispose();


    }//GEN-LAST:event_closeDialog


    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables


    private javax.swing.JLabel jLabel1;


    private javax.swing.JPanel jPanel1;


    private javax.swing.JPanel jPanel2;


    private javax.swing.JPanel jPanel3;


    private javax.swing.JScrollPane jScrollPane1;


    private javax.swing.JScrollPane jScrollPane2;


    private javax.swing.JTable jTable1;


    private javax.swing.JTextArea jTextArea1;

    // End of variables declaration//GEN-END:variables


}


