/*
 * Param.java
 *
 * Created on 14 juin 2004, 14:17
 */

package srcastra.astra.gui.modules.param;
import javax.swing.*;
import java.io.*;
import  srcastra.astra.sys.export.*;
import java.util.*;
import xBaseJ.*;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.gui.sys.*;
/**
 *
 * @author  Administrateur
 */
public class Param extends javax.swing.JPanel {
    
    /** Creates new form Param */
      JFileChooser chooser;
      Param.ImportClient thread;
      astrainterface serveur;
      Loginusers_T user;
    public Param(astrainterface serveur,Loginusers_T user) {
        this.serveur=serveur;
        this.user=user;
        initComponents();
        chooser=new JFileChooser();
        grp_label_clipath.setText("C:\\Documents and Settings\\Administrateur\\Mes documents\\Mes fichiers re�us\\test_import\\v_client.dbf");
        initListe() ;
        jProgressBar1.setVisible(false); 
    }
    private void initListe(){
          grp_Liste_achat.setServeur(serveur);
          grp_Liste_achat.setLogin(user);
          grp_Liste_achat.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(serveur, user));
          grp_Liste_achat.init2(); 
          grp_Liste_vente.setServeur(serveur);
          grp_Liste_vente.setLogin(user);
          grp_Liste_vente.setModel(new srcastra.astra.gui.components.combobox.liste.NCompteListeTableModel(serveur, user));
          grp_Liste_vente.init2();             
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_Liste_vente = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_Liste_achat = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_But_validCompte = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        grp_label_clipath = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        grp_but_importcli = new javax.swing.JButton();
        grp_but_stop = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setLayout(new java.awt.BorderLayout());

        setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(new javax.swing.border.TitledBorder(null, "Compte par d\u00e9faut", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Compte de vente par d\u00e9faut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Compte d'achat par d\u00e9faut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel4.add(jLabel4, gridBagConstraints);

        grp_Liste_vente.setGrp_Comp_nextComponent(grp_Liste_achat);
        grp_Liste_vente.setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));
        grp_Liste_vente.setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_Liste_vente, gridBagConstraints);

        grp_Liste_achat.setGrp_Comp_nextComponent(grp_But_validCompte);
        grp_Liste_achat.setWarningIcon(new javax.swing.ImageIcon("Classpath: /srcastra/astra/gui/img/aTextfield/warning.gif"));
        grp_Liste_achat.setWorkingIcon(new javax.swing.ImageIcon("/srcastra/astra/gui/img/aTextfield/working.gif"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_Liste_achat, gridBagConstraints);

        grp_But_validCompte.setText("OK");
        grp_But_validCompte.setPreferredSize(new java.awt.Dimension(49, 23));
        grp_But_validCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_But_validCompteActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_But_validCompte, gridBagConstraints);

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/param").getString("imp_cli"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/param").getString("clients"));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel2.add(jLabel1, gridBagConstraints);

        grp_label_clipath.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel2.add(grp_label_clipath, gridBagConstraints);

        jLabel3.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(jLabel3, new java.awt.GridBagConstraints());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/loupe_15_15 copie.gif")));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1, new java.awt.GridBagConstraints());

        grp_but_importcli.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_but_importcli.setText("OK");
        grp_but_importcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_importcliActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel2.add(grp_but_importcli, gridBagConstraints);

        grp_but_stop.setText("STOP");
        grp_but_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_but_stopActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel2.add(grp_but_stop, gridBagConstraints);

        jPanel3.add(jPanel2);

        add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(10, 30));
        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setName("t");
        jProgressBar1.setString("Importation client");
        jPanel5.add(jProgressBar1, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

    private void grp_But_validCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_But_validCompteActionPerformed
        try{
            if(grp_Liste_achat.getCleUnik()==0 || grp_Liste_vente.getCleUnik()==0){
                JOptionPane.showMessageDialog((java.awt.Component)this,"Les comptes ne peuvent pas �tre vide !");
                return;
            }
            serveur.renvDossierRmiObject(user.getUrcleunik()).updateCompteParDefaut(user.getUrcleunik(),grp_Liste_vente.getCleUnik(),grp_Liste_achat.getCleUnik());
            JOptionPane.showMessageDialog((java.awt.Component)this,"Op�ration effectu�e !");
       }catch(ServeurSqlFailure se){
             ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG,se,user);    
          }
          catch(java.rmi.RemoteException rn)
          {
              ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG,rn,user);    
          }
    }//GEN-LAST:event_grp_But_validCompteActionPerformed

    private void grp_but_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_stopActionPerformed
       thread.stop();
    }//GEN-LAST:event_grp_but_stopActionPerformed

    private void grp_but_importcliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_but_importcliActionPerformed
      if(!grp_label_clipath.getText().equals("")){
          thread=new Param.ImportClient(grp_label_clipath.getText(),this);
          thread.start();          
      }
    }//GEN-LAST:event_grp_but_importcliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chooser.addChoosableFileFilter(new MyFilter("v_client"));
        chooser.showOpenDialog(this);  
        File file=chooser.getSelectedFile();
        if(file!=null){
            grp_label_clipath.setText(file.getAbsolutePath());
            
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed
    class ImportClient extends Thread{
       String filePath;
       java.awt.Component parent;
        public ImportClient(String filePath,java.awt.Component parent){
           this.filePath=filePath;
           this.parent=parent;
       
       }
        public void run(){
          jProgressBar1.setVisible(true); 
          ArrayList array=readClientFile(filePath);
          try{
          if(array!=null){
            serveur.importClient(user.getUrcleunik(),array);
            jProgressBar1.setVisible(false); 
            JOptionPane.showMessageDialog(null,"Import client termin� !");
          }
          }catch(ServeurSqlFailure se){
             ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG,se,user);    
          }
          catch(java.rmi.RemoteException rn)
          {
              ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG,rn,user);    
          }
          catch(srcastra.astra.sys.importastra.EntityNotFoundException  en){
              JOptionPane.showMessageDialog(parent,"Erreur lors de l'import client, l'entite "+en.getEntite()+"\nn'a pas �t� trouv�e dans la liste !"); 
              
          }
          
      
      }
        
    
    }
    class MyFilter extends javax.swing.filechooser.FileFilter {

        String type;
            
        public MyFilter(String type){

            this.type=type;

        }      
        public boolean accept(java.io.File file) {

            if(file.isDirectory())

                return true;

            String filename = file.getName(); 
          

               
                return filename.endsWith(".DBF");

            

            

        }

        

        public String getDescription() {

            return "*.DBF";

        }

     
        
    }
    private ArrayList readClientFile(String filepath){
        DbfManager2 dbfmanager=new DbfManager2(true);
        try{
        ArrayList array=dbfmanager.readClientFromFile(filepath,'r');     
        if(array!=null){
        for(int i=0;i<array.size();i++){
            Object[] tmp=(Object[] )array.get(i);
            if(tmp!=null){
                String affiche="";
                for(int j=0;j<tmp.length;j++){
                    if(tmp[j]!=null)
                        affiche=affiche+tmp[j].toString();
                }
            System.out.println("affiche "+affiche);
            }
            
        }
        }
         return array;
        }catch(xBaseJException xn){
         ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG,xn,user);
        
        
        }catch(IOException in){
         ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG,in,user);
        
        }
        return null;
       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton grp_But_validCompte;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_achat;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_Liste_vente;
    private javax.swing.JButton grp_but_importcli;
    private javax.swing.JButton grp_but_stop;
    private javax.swing.JLabel grp_label_clipath;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    
}
