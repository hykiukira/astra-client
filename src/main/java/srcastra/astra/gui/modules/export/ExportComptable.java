/*

 * ExportComptable.java

 *

 * Created on 19 d�cembre 2003, 9:18

 */



package srcastra.astra.gui.modules.export;

import javax.swing.*;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule.*;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.*;

import  java.awt.event.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.sys.*;

import javax.swing.event.*;

import java.rmi.*;

import srcastra.astra.sys.rmi.Exception.*;

import  srcastra.astra.sys.export.*;
import srcastra.astra.gui.sys.*;
import srcastra.astra.sys.utils.*;

/**

 *

 * @author  Thomas

 */



public class ExportComptable extends javax.swing.JInternalFrame implements MainScreenModule, AIframe,ActionListener,ToolBarComposer {

    

    /** Creates new form ExportComptable */

   ComptaPanel panel; 

   ActionToolBar actionToolBar;

   javax.swing.event.InternalFrameListener iFrameListener;

   int[] actionTab;

   astrainterface serveur;

   Loginusers_T user;

   public ExportComptable(java.awt.Frame superOwner,astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener) {

        initComponents();

        this.actionToolBar = actionToolBar;

        this.actionToolBar.setActionEnabled(activeToolbar(false));

        panel=new ComptaPanel(this);        

        getContentPane().add(panel);

        this.iFrameListener=iFrameListener;

        this.addInternalFrameListener(iFrameListener);

       

        this.actionToolBar.setTbComposer(this);        

        this.serveur=serveur;

        this.user=currentUser;

    }

   private int[] activeToolbar(boolean enabled){

      if(enabled){

        actionTab=new int[]{ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_CLOSE};

      }else{

          actionTab=new int[]{ActionToolBar.DO_CLOSE};

      }

      return actionTab; 

   }

    public void doDefaultCloseAction(){

        try{

            serveur.remoterollback(getCurrentUser().getUrcleunik());

        }catch(java.rmi.RemoteException re){

           ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);  

        }

       InternalFrameEvent closeWindow=new InternalFrameEvent(this,InternalFrameEvent.INTERNAL_FRAME_CLOSING);

       this.iFrameListener.internalFrameClosing(closeWindow);

       super.doDefaultCloseAction();

   }

    

    /** This method is called from within the constructor to

     * initialize the form.

     * WARNING: Do NOT modify this code. The content of this method is

     * always regenerated by the Form Editor.

     */

    private void initComponents() {//GEN-BEGIN:initComponents

        setClosable(true);
        setIconifiable(true);
        setName("exportcompta");
        setBounds(0, 0, 730, 340);
    }//GEN-END:initComponents



    public void cancelModule() {

    }    

    

    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {

    }    

    

    public void chargeStatusPanel(String[] statuts) {

    }

    

    public void closeModule() {

    }

    

    public void displayCreateSequence() {

    }

    

    public void displayDeleteSequence() {

    }

    

    public void displayReadSequence(int cleUnik) {

    }

    

    public void enabledTabbedPane(boolean enabled) {

    }

    

    public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser() {

        return user;

    }

    

    public boolean getNestedSignaletique() {

        return false;

    }

    

    public java.awt.Frame getOwner() {

        return null;

    }

    

    public srcastra.astra.sys.rmi.astrainterface getServeur() {

        return null;

    }

    

    public srcastra.astra.sys.rmi.DossierRmiInterface getServeurDossier() {

        return null;

    }

    

    public java.awt.Frame getSuperOwner() {

        return null;

    }

    

    public void nextScreen(int currentScreen) {

        

    }

    

    public void nextScreen(int currentScreen, boolean affich) {

    }

    

    public void nextScreen(int currentScreen, int insert) {

    }

    

    public void registerTable(javax.swing.JTable generique_table) {

    }

    

    public void reloadToolBarInfo() {

         this.actionToolBar.setActionEnabled(actionTab);

         actionToolBar.setTbComposer(this);

    }

    public void reloadToolBarInfo(boolean enabled){

            activeToolbar(enabled);

            this.actionToolBar.setActionEnabled(actionTab);

            actionToolBar.setTbComposer(this);   

    }

    

    

    public void setContextCleUnik(int ContextCleUnik) {

    }

    

    public void setCurrentActionEnabled(int[] actionEnabled) {

    }

    

    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {

    }

    

    public void setNestedSignaletique(boolean netstedSignletique) {

    }

    

    public void saveToolBarInfo() {

    }

    

    public void actionPerformed(ActionEvent e) {

    }

    

    public void doAccept() {

    }

    

    public void doCancel() {

    }

    

    public void doClose() {

    }

    

    public void doCreate() {

    }

    

    public void doDelete() {

    }

    

    public void doF10() {

    }

    

    public void doF7() {

    }

    

    public void doHelp() {

    }

    

    public void doModify() {

    }

    

    public void doNext() {

    }

    class ExportThread extends java.lang.Thread{

      JComponent component;

      public ExportThread(JComponent component){

          this.component=component;

      }

      public void run ()  {

          try{

          try{
            boolean bydossier=panel.getGrp_Check_dossier().isSelected();
            Cubic_T cubic=serveur.renvParamCompta(getCurrentUser().getUrcleunik()).exportCubic(getCurrentUser().getUrcleunik(),bydossier);

            if(cubic!=null){

                System.out.println("cubic not null");

            DbfManager2 dbfmanager=new srcastra.astra.sys.export.DbfManager2(true);

            String path=panel.getGrp_Label_path().getText();

             if(cubic.getVente()!=null){

                System.out.println("vente not null");

                dbfmanager.writeToDBF2(panel.getPath().vente,cubic.getVente()); 

            } 

            if(cubic.getAchat()!=null){

                   System.out.println("achat not null"); 

                     dbfmanager.writeToDBF2(panel.getPath().achat,cubic.getAchat()); 

            }

            if(cubic.getFinancier()!=null){

                   System.out.println("fiancier not null"); 

                   dbfmanager.writeToDBF2(panel.getPath().financier,cubic.getFinancier()); 

            }

            if(cubic.getOd()!=null){

                    System.out.println("od not null");

                    dbfmanager.writeToDBF2(panel.getPath().od,cubic.getOd());  

            }

            if(cubic.getFournisseur()!=null){

                     System.out.println("fournisseur not null");

                     dbfmanager.writeToDBFHash(panel.getPath().fournisseur,cubic.getFournisseur(),DbfManager2.FOURNISSEUR); 

             }

            if(cubic.getClient()!=null){ 

                    System.out.println("client not null");

                    dbfmanager.writeToDBFHash(panel.getPath().client,cubic.getClient(),DbfManager2.CLIENT); 

            }

            }

            Object tmp=null;

//            tmp.toString();

            panel.getJProgressBar1().setVisible(false);

            serveur.remoteCommit(getCurrentUser().getUrcleunik());

            JOptionPane.showMessageDialog(component,java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("export_finish"),java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("export_finish_title"),JOptionPane.INFORMATION_MESSAGE);
         
            panel.writetype();
            panel.checkFileValidity();
            
          }catch(MyXbaseException mn){
             My_Logger.warning(mn);
             My_Logger.warning(mn.toString());
             ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, mn);
          }catch(SoldeComptException  sn){

            serveur.remoterollback(getCurrentUser().getUrcleunik());           
            String message="";
            if(sn.getErreur()!=null){

               for(int i=0;i<sn.getErreur().size();i++){

                     
                  message=message+sn.getErreur().get(i).toString()+"\n";
               }

            }

             panel.getJProgressBar1().setVisible(false);

             checkError();

             sn.printStackTrace();   
             My_Logger.warning(sn);
             My_Logger.warning(message);
           //  ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, sn);

            

        

        }catch(RemoteException rn){

            serveur.remoterollback(getCurrentUser().getUrcleunik());

            rn.printStackTrace();  

            checkError();
            My_Logger.warning(rn);
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

        }catch(ServeurSqlFailure se){

            serveur.remoterollback(getCurrentUser().getUrcleunik());

          se.printStackTrace();   

           panel.getJProgressBar1().setVisible(false);

          checkError();
          My_Logger.warning(se);
          ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
          
        }

       catch(xBaseJ.xBaseJException xe){

           serveur.remoterollback(getCurrentUser().getUrcleunik());

          xe.printStackTrace();  

           panel.getJProgressBar1().setVisible(false);

          checkError();
          ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, xe);

        }

      catch(java.io.IOException in){

          serveur.remoterollback(getCurrentUser().getUrcleunik());

          in.printStackTrace();   

          panel.getJProgressBar1().setVisible(false);
          My_Logger.warning(in);
          checkError();

        }

      catch(Exception en){

          serveur.remoterollback(getCurrentUser().getUrcleunik());

          en.printStackTrace();   

          panel.getJProgressBar1().setVisible(false);
          My_Logger.warning(en);
          checkError();
          ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, en);

        }

        

       }catch(RemoteException rn){

           //serveur.remoterollback(getCurrentUser().getUrcleunik());

           rn.printStackTrace();    

           panel.getJProgressBar1().setVisible(false);
           My_Logger.warning(rn);
           checkError();
           ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

        }

      }

    }

    public void doPrevious() {

         panel.getJProgressBar1().setVisible(true);

         ExportThread thread=new ExportComptable.ExportThread(this);

         thread.start();

    }

    private void checkError(){

      /*  DbfManager2.EmptyFile(panel.getPath().vente);

        DbfManager2.EmptyFile(panel.getPath().achat);

        DbfManager2.EmptyFile(panel.getPath().financier);

        DbfManager2.EmptyFile(panel.getPath().od);

        DbfManager2.EmptyFile(panel.getPath().fournisseur);

        DbfManager2.EmptyFile(panel.getPath().client);*/

        JOptionPane.showMessageDialog(this,java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("error_export"),java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("error_export_title"),JOptionPane.INFORMATION_MESSAGE);

    }

    public void doPrint() {

    }

    

    public void doSwitch() {

    }

    

    public int[] getDefaultActionToolBarMask() {

        return null;

    }

    

    public java.awt.Component m_getGeneriqueTable() {

        return null;

    }

    

    public void setThisAsToolBarComponent() {

    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    

}

