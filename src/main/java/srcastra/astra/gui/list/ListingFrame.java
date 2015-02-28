/*

 * ListingFrame.java

 *

 * Created on 7 novembre 2003, 9:52

 */


package srcastra.astra.gui.list;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.components.AIframe;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.sys.rmi.DossierRmiInterface;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierTableModelInterface;

import java.util.ArrayList;

import srcastra.astra.gui.sys.MessageManager;

import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;

import java.util.*;

import java.text.*;

import srcastra.astra.sys.classetransfert.utils.*;

import srcastra.astra.gui.sys.utils.*;

import java.awt.*;

import srcastra.astra.sys.classetransfert.dossier.assurance.*;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.sys.classetransfert.dossier.hotel.*;

import srcastra.astra.sys.classetransfert.dossier.bateau.*;

import srcastra.astra.sys.classetransfert.dossier.train.*;

import srcastra.astra.sys.classetransfert.dossier.car.*;

import srcastra.astra.sys.classetransfert.dossier.voitureLocation.*;

import srcastra.astra.sys.classetransfert.dossier.taxi.*;

import srcastra.astra.sys.compta.*;

import javax.swing.event.InternalFrameEvent;

import java.awt.event.*;

import java.io.*;

import srcastra.astra.gui.*;

import srcastra.test.*;

import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.KeyStrokeManager;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import javax.swing.KeyStroke.*;

import javax.swing.table.*;

import javax.swing.event.*;

import javax.swing.*;

import srcastra.astra.gui.modules.dossier.utils.*;

import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;

import javax.swing.border.*;

import srcastra.astra.sys.classetransfert.configuration.*;

/**
 * @author Thomas
 */

public class ListingFrame extends javax.swing.JInternalFrame implements MainScreenModule, AIframe, ToolBarComposer, ListSelectionListener {


    /**
     * Creates new form ListingFrame
     */

    int panelSelected = 0;

    ActionToolBar actionToolBar;

    Listchoicedossier listchoice;

    astrainterface serveur;

    Loginusers_T currentUser;

    JGoodiesConfigDossier configdos;

    JGoodiesConfigControle configdos1;

    JGoodiesConfigCaisse configcaisse;

    JGoodiesConfigStatFourn configstatfourn;

    JGoodiesConfigChiffreAffaireClient configchiffreaffaire;

    JGoodiesConfigJournaux configjournaux;

    JGoodiesConfigFourn configfourn;

    JGoodiesConfigAchat configachat;

    JGoodiesConfigChiffreAffaireFournisseur configchiffreaffairef;


    JGoodiesConfigDebEmb configembdeb;

    java.awt.Frame superOwner;

    javax.swing.event.InternalFrameListener iFrameListener;

    public ListingFrame(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener, MainFrame mainframe) {

        initComponents();

        jProgressBar1.setVisible(false);

        setBounds(0, 0, 650, 520);

        this.superOwner = superOwner;

        listchoice = new Listchoicedossier(serveur, currentUser, this);

        configdos = listchoice.getConfigdossier();

        configcaisse = listchoice.getConfigcaisse();

        configdos1 = listchoice.getConfigControle();

        configstatfourn = listchoice.getConfigStatFourn();

        configjournaux = listchoice.getConfigJournaux();

        configfourn = listchoice.getConfigFourn();

        configachat = listchoice.getConfigAchat();

        configchiffreaffaire = listchoice.getConfigChiffreAffaire();

        configchiffreaffairef = listchoice.getConfigChiffreAffaireF();

        configembdeb = listchoice.getConfigEmbDeb();

        jPanel2.add(listchoice);

        this.actionToolBar = actionToolBar;

        actionToolBar.setTbComposer(this);

        panelSelected = 1;

        this.serveur = serveur;

        this.currentUser = currentUser;

        initToolBarAction();

        this.iFrameListener = iFrameListener;

        this.addInternalFrameListener(iFrameListener);

        if (currentUser.getUrrights() != 1) {

            configdos.getGrp_prod().setGrp_Comp_nextComponent(configdos.getBdcCheck());

        }


    }

    private void initToolBarAction() {


        actionToolBar.setActionEnabled(new int[]{ActionToolBar.DO_PREVIOUS, ActionToolBar.DO_CLOSE, ActionToolBar.DO_ACCEPT});


    }

    public void doDefaultCloseAction() {

        try {

            serveur.remoterollback(getCurrentUser().getUrcleunik());

        } catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        InternalFrameEvent closeWindow = new InternalFrameEvent(this, InternalFrameEvent.INTERNAL_FRAME_CLOSING);

        this.iFrameListener.internalFrameClosing(closeWindow);

        super.doDefaultCloseAction();

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
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("MnItem_List"));
        setFrameIcon(null);
        setName("listingframe");
        setPreferredSize(new java.awt.Dimension(650, 520));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString(java.util.ResourceBundle.getBundle("srcastra/astra/locale/alertmessage").getString("impor_list"));
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 460));
        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        setBounds(0, 0, 730, 520);
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


    public void doAccept() {

        try {

            if (panelSelected == 1) {

                ArrayList array = serveur.renvListRmiObject(currentUser.getUrcleunik()).getDossierList(currentUser.getUrcleunik(), configdos.getDepartDeb(), configdos.getDepartFin(), configdos.getCreationDeb(), configdos.getCreationFin(),

                        configdos.getSolde(), configdos.getFacture(), configdos.getClient(), configdos.getClientCont(), configdos.getFournisseur(), configdos.getPassager(),

                        configdos.getDestination(), configdos.getPo(), configdos.getNumdos(), configdos.getEntite(),

                        configdos.getGrpProd(), configdos.getProd(), configdos.getBdc(), configdos.getUser());

                String[][] tab = transformArray(array);

                // new ChartTypeChange().test(tab);

            }

            //  }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

            //            ErreurScreenLibrary.displayErreur(this,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


        } catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        catch (Exception en) {

            en.printStackTrace();

        }

    }

    public void doCancel() {

    }


    public void doClose() {

        doDefaultCloseAction();

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

    class ThreadList extends java.lang.Thread {

        JComponent component;

        public ThreadList(JComponent component) {

            this.component = component;

        }

        public void run() {

            String from;
            Entite e = null;

            try {

                ArrayList data = serveur.renvEntiteRmiObject(currentUser.getUrcleunik()).getList(currentUser.getUrcleunik(), currentUser.getUreecleunik());


                int cpt = 0;
                boolean found = false;


                while (!found && cpt < data.size()) {
                    e = (Entite) data.get(cpt);

                    if (e.getEecleunik() == currentUser.getUreecleunik())
                        found = true;

                    cpt++;
                }

                from = e.getEemail();


                if (panelSelected == 1) {

                    System.out.println(configdos.getSolde() + " " + configdos.getSoldeText());


                    ArrayList array = serveur.renvListRmiObject(currentUser.getUrcleunik()).getDossierList(currentUser.getUrcleunik(), configdos.getDepartDeb(), configdos.getDepartFin(), configdos.getCreationDeb(), configdos.getCreationFin(),

                            configdos.getSolde(), configdos.getFacture(), configdos.getClient(), configdos.getClientCont(), configdos.getFournisseur(), configdos.getPassager(),

                            configdos.getDestination(), configdos.getPo(), configdos.getNumdos(), configdos.getEntite(),

                            configdos.getGrpProd(), configdos.getProd(), configdos.getBdc(), configdos.getUser());

                    new DossierReport(superOwner, from).prepareDossier(currentUser, configdos.getDepartDebText(), configdos.getDepartFinText(), configdos.getCreationDebText(), configdos.getCreationFinText(),

                            configdos.getSoldeText(), configdos.getFactureText(), configdos.getClientText(), configdos.getClientContText(), configdos.getFournisseurText(), configdos.getPassager(),

                            configdos.getDestinationText(), configdos.getPo(), configdos.getNumdos(), configdos.getEntiteText(),

                            configdos.getGrpProdText(), configdos.getProd(), configdos.getBdcText(), "", array);

                } else if (panelSelected == 2) {

                    if (configcaisse.getDatebeg().isOpen() || configcaisse.getDatebeg().isUnknow()) {

                        new MessageManager().showMessageDialog(component, "date_deb", "date_deb_title", currentUser);

                        return;

                    }

                    CaisseList_T liste = serveur.renvListRmiObject(currentUser.getUrcleunik()).getCaisseList(currentUser.getUrcleunik(), configcaisse.getDatebeg(), configcaisse.getDateend(), configcaisse.getEntite(), configcaisse.getUser());

                    new DossierReport(superOwner, from).prepareDossierCaisse(currentUser, configcaisse.getDatebeg(), configcaisse.getDateend(), configcaisse.getUserText(), configcaisse.getEntiteText(), liste);

                } else if (panelSelected == 3) {
                    //ArrayList array=new ArrayList();
                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    //System.out.println("apoil");

                    ArrayList info = new ArrayList();
                    ArrayList info1 = new ArrayList();

                    //System.out.println("user" + user);
                    info = dossier.getListingRentabilite(currentUser.getUrcleunik(), configdos1.getDepartDeb(), configdos1.getDepartFin(), configdos1.getEntiteText(), configdos1.getCreationDeb(), configdos1.getCreationFin());
                    info1 = dossier.getListingRentabilite1(currentUser.getUrcleunik(), configdos1.getDepartDeb(), configdos1.getDepartFin(), configdos1.getEntiteText(), configdos1.getCreationDeb(), configdos1.getCreationFin());

                    //System.out.println("info rentabilite");


                    new DossierReport(superOwner, from).prepareControle(currentUser, configdos1.getDepartDebText(), configdos1.getDepartFinText(), configdos1.getEntiteText(), configdos1.getCreationDebText(), configdos1.getCreationFinText(), configdos1.getPivotText(), info, info1, dossier);

                    //new DossierReport(superOwner).prepareControle( currentUser, configdos1.getDepartDebText(), configdos1.getDepartFinText(), configdos.getCreationDebText(),configdos.getCreationFinText(),

                    //                        configdos1.getSoldeText(), configdos1.getFactureText(), configdos1.getClientText(), configdos1.getClientContText(), configdos1.getFournisseurText(), configdos1.getPassager(),

                    //                         configdos1.getDestinationText(), configdos1.getPo(), configdos1.getNumdos(),configdos1.getEntiteText(),

                    //                          configdos1.getGrpProdText(),configdos1.getProd(), configdos1.getBdcText(),"",array);

                } else if (panelSelected == 4) {
                    //ArrayList array=new ArrayList();
                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    //System.out.println("apoil");

                    ArrayList info = new ArrayList();
                    ArrayList info1 = new ArrayList();

                    //System.out.println("user" + user);
                    info = dossier.getListingRentabilite(currentUser.getUrcleunik(), configstatfourn.getDepartDeb(), configstatfourn.getDepartFin(), configstatfourn.getEntiteText(), configstatfourn.getCreationDeb(), configstatfourn.getCreationFin());
                    info1 = dossier.getListingRentabilite1(currentUser.getUrcleunik(), configstatfourn.getDepartDeb(), configstatfourn.getDepartFin(), configstatfourn.getEntiteText(), configstatfourn.getCreationDeb(), configstatfourn.getCreationFin());

                    //   info = dossier.getListingRentabilite(currentUser.getUrcleunik(),configdos1.getDepartDeb(),configdos1.getDepartFin(),configdos1.getEntiteText(),configdos1.getCreationDeb(),configdos1.getCreationFin());
                    //info1= dossier.getListingRentabilite1(currentUser.getUrcleunik(),configdos1.getDepartDeb(),configdos1.getDepartFin(),configdos1.getEntiteText(),configdos1.getCreationDeb(),configdos1.getCreationFin());

                    //System.out.println("info rentabilite");


                    new DossierReport(superOwner, from).prepareStatFourn(currentUser, configstatfourn.getDepartDebText(), configstatfourn.getDepartFinText(), configstatfourn.getEntiteText(), configstatfourn.getCreationDebText(), configstatfourn.getCreationFinText(), info, info1);

                    //new DossierReport(superOwner).prepareControle( currentUser, configdos1.getDepartDebText(), configdos1.getDepartFinText(), configdos.getCreationDebText(),configdos.getCreationFinText(),

                    //                        configdos1.getSoldeText(), configdos1.getFactureText(), configdos1.getClientText(), configdos1.getClientContText(), configdos1.getFournisseurText(), configdos1.getPassager(),

                    //                         configdos1.getDestinationText(), configdos1.getPo(), configdos1.getNumdos(),configdos1.getEntiteText(),

                    //                          configdos1.getGrpProdText(),configdos1.getProd(), configdos1.getBdcText(),"",array);

                } else if (panelSelected == 5) {
                    //ArrayList array=new ArrayList();
                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    //System.out.println("apoil");

                    ArrayList info = new ArrayList();

                    boolean nc = true;

                    switch (configjournaux.getSelectedType()) {

                        case 0:
                            info = dossier.getListingJournalV(currentUser.getUrcleunik(), configjournaux.getDepartDeb(), configjournaux.getDepartFin(), configjournaux.getEntiteText(), false);
                            break;
                        case 1:
                            info = dossier.getListingJournalV(currentUser.getUrcleunik(), configjournaux.getDepartDeb(), configjournaux.getDepartFin(), configjournaux.getEntiteText(), true);
                            break;
                        case 2:
                            info = dossier.getListingJournalA(currentUser.getUrcleunik(), configjournaux.getDepartDeb(), configjournaux.getDepartFin(), configjournaux.getEntiteText(), false);
                            break;
                        case 3:
                            info = dossier.getListingJournalA(currentUser.getUrcleunik(), configjournaux.getDepartDeb(), configjournaux.getDepartFin(), configjournaux.getEntiteText(), true);
                            break;
                        case 4:
                            info = dossier.getListingJournalOD(currentUser.getUrcleunik(), configjournaux.getDepartDeb(), configjournaux.getDepartFin(), configjournaux.getEntiteText());
                            break;
                    }

                    new DossierReport(superOwner, from).prepareJourn(currentUser, configjournaux.getDepartDebText(), configjournaux.getDepartFinText(), configjournaux.getEntiteText(), configjournaux.getLibelleType(), info);

                } else if (panelSelected == 6) {

                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    ArrayList info = new ArrayList();


                    info = dossier.getListingFournisseur(currentUser.getUrcleunik(), configfourn.getDe(), configfourn.getA(), currentUser.getUrlmcleunik());


                    new DossierReport(superOwner, from).prepareFourn(currentUser, info);


                } else if (panelSelected == 7) {

                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    ArrayList info = new ArrayList();

                    int type = 2;

                    if (configachat.getChoix1() && configachat.getChoix2())
                        type = 2;

                    if (configachat.getChoix1() && !configachat.getChoix2())
                        type = 1;

                    if (!configachat.getChoix1() && configachat.getChoix2())
                        type = 3;


                    info = dossier.getListingIntegrationFacture(currentUser.getUrcleunik(), type);

                    //dossier.getListing

                    new DossierReport(superOwner, from).prepareIntegratedAchat(currentUser, info);


                } else if (panelSelected == 8) {

                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    ArrayList info = new ArrayList();
                    ArrayList info1 = new ArrayList();

                    srcastra.astra.sys.classetransfert.utils.Date datedeb = configchiffreaffaire.dCreationD.getDate();
                    datedeb.setYear(datedeb.getYear() - 1);
                    datedeb.setHours(0);
                    datedeb.setMinutes(0);
                    datedeb.setSeconds(0);

                    srcastra.astra.sys.classetransfert.utils.Date datefin = configchiffreaffaire.dCreationF.getDate();
                    datefin.setYear(datefin.getYear() - 1);
                    datefin.setHours(0);
                    datefin.setMinutes(0);
                    datefin.setSeconds(0);

                    info = dossier.getListingChiffreAffaireClient(currentUser.getUrcleunik(), configchiffreaffaire.getCreationDeb(), configchiffreaffaire.getCreationFin(), configchiffreaffaire.getEntiteText());
                    info1 = dossier.getListingChiffreAffaireClient(currentUser.getUrcleunik(), datedeb.toString(), datefin.toString(), configchiffreaffaire.getEntiteText());

                    //System.out.println(info.size()+" "+info1.size());

                    //info = dossier.getListingIntegrationFacture(currentUser.getUrcleunik(),type);

                    //dossier.getListing

                    new DossierReport(superOwner, from).prepareChiffreAffaireClient(currentUser, info, info1, configchiffreaffaire.getCreationDeb(), configchiffreaffaire.getCreationFin(), configchiffreaffaire.getEntiteText());


                } else if (panelSelected == 9) {

                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    ArrayList info = new ArrayList();
                    ArrayList info1 = new ArrayList();

                    srcastra.astra.sys.classetransfert.utils.Date datedeb = configchiffreaffairef.departD.getDate();
                    datedeb.setYear(datedeb.getYear() - 1);
                    datedeb.setHours(0);
                    datedeb.setMinutes(0);
                    datedeb.setSeconds(0);

                    srcastra.astra.sys.classetransfert.utils.Date datefin = configchiffreaffairef.departF.getDate();
                    datefin.setYear(datefin.getYear() - 1);
                    datefin.setHours(0);
                    datefin.setMinutes(0);
                    datefin.setSeconds(0);

                    info = dossier.getListingChiffreAffaireFournisseur(currentUser.getUrcleunik(), configchiffreaffairef.getDepartDeb(), configchiffreaffairef.getDepartFin(), configchiffreaffairef.getFournisseurText(), configchiffreaffairef.getFournisseurText2(), configchiffreaffairef.getEntiteText());
                    info1 = dossier.getListingChiffreAffaireFournisseur(currentUser.getUrcleunik(), datedeb.toString(), datefin.toString(), configchiffreaffairef.getFournisseurText(), configchiffreaffairef.getFournisseurText2(), configchiffreaffairef.getEntiteText());


                    System.out.println(info.size() + " " + info1.size());

                    //info = dossier.getListingIntegrationFacture(currentUser.getUrcleunik(),type);

                    //dossier.getListing

                    new DossierReport(superOwner, from).prepareChiffreAffaireFournisseur(currentUser, info, info1, configchiffreaffairef.getDepartDeb(), configchiffreaffairef.getDepartFin(), configchiffreaffairef.getEntiteText());
                } else if (panelSelected == 10) {
                    DossierRmiInterface dossier;

                    dossier = serveur.renvDossierRmiObject(currentUser.getUrcleunik());

                    ArrayList info = new ArrayList();
                    ArrayList info1 = new ArrayList();

                    ArrayList emb = new ArrayList();
                    ArrayList deb = new ArrayList();

                    info = dossier.getListingEmbarquement(currentUser.getUrcleunik(), configembdeb.getDossier(), currentUser.getUrlmcleunik());
                    info1 = dossier.getListingDebarquement(currentUser.getUrcleunik(), configembdeb.getDossier(), currentUser.getUrlmcleunik());
                    for (int i = 0; i < info.size(); i++) {
                        Object[] tmp = new Object[4];
                        Object[] tmp2 = new Object[4];
                        Object[] tmp1 = (Object[]) info.get(i);
                        Object[] tmp3 = (Object[]) info1.get(i);

                        tmp[0] = tmp1[1];
                        tmp[1] = tmp1[0] + "-" + tmp1[7];
                        tmp2[0] = tmp3[1];
                        tmp2[1] = tmp3[0];


                        if (tmp1[3].toString().compareTo("NO") != 0) {
                            tmp[2] = tmp1[3];
                            //tmp2[3]=tmp1[3];
                        } else {
                            tmp[2] = tmp1[4];
                            //tmp2[3]=tmp1[4];
                        }

                        if (tmp3[3].toString().compareTo("NO") != 0) {
                            //tmp[2]=tmp1[3];
                            tmp2[3] = tmp3[3];
                        } else {
                            //tmp[2]=tmp1[4];
                            tmp2[3] = tmp3[4];
                        }

                        if (tmp1[5].toString().compareTo("NO") != 0) {
                            tmp[3] = tmp1[5];
                            tmp2[2] = tmp1[5];
                        } else {
                            tmp[3] = tmp1[6];
                            tmp2[2] = tmp1[6];
                        }

                        if (tmp3[5].toString().compareTo("NO") != 0) {
                            //tmp[3]=tmp1[5];
                            tmp2[2] = tmp3[5];
                        } else {
                            //tmp[3]=tmp1[6];
                            tmp2[2] = tmp3[6];
                        }


                        emb.add(tmp);
                        deb.add(tmp2);

                    }


                    new DossierReport(superOwner, from).prepareEmbDeb(currentUser, emb, true);
                    new DossierReport(superOwner, from).prepareEmbDeb(currentUser, deb, false);


                }

                jProgressBar1.setVisible(false);

            } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

                jProgressBar1.setVisible(false);

                ErreurScreenLibrary.displayErreur(component, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);


            } catch (java.rmi.RemoteException re) {

                jProgressBar1.setVisible(false);

                ErreurScreenLibrary.displayErreur(component, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

            }

        }

    }

    public void doPrevious() {

        jProgressBar1.setVisible(true);

        ThreadList thread = new ListingFrame.ThreadList(this);

        thread.start();

    }


    public void doPrint() {

    }

    private String[][] transformArray(ArrayList array) {

        String[][] datat = null;

        if (array != null) {

            datat = new String[array.size()][];

            for (int i = 0; i < array.size(); i++) {

                Object[] tab = (Object[]) array.get(i);

                //  tot=tot+Double.parseDouble(tab[6].toString());

                // paye=paye+Double.parseDouble(tab[7].toString());

                // solded=solded+Double.parseDouble(tab[8].toString());

                if (tab != null) {

                    String[] tmp = new String[12];

                    for (int j = 1; j < tab.length; j++) {

                        if (j == 11) {

                            if (tab[11].toString() == "1" && tab[12].toString() == "0" && tab[13].toString() == "0") {

                                tmp[j - 1] = "B";

                            } else if (tab[12].toString() == "1" && tab[13].toString() == "0") {

                                tmp[j - 1] = "F";

                            } else if (tab[13].toString() == "1") {

                                tmp[j - 1] = "A";

                            } else tmp[j - 1] = " ";

                        } else if (j == 12 || j == 13) ;

                        else if (j == 14) tmp[11] = tab[j].toString();

                        else tmp[j - 1] = tab[j].toString();

                    }

                    if (tmp != null)

                        datat[i] = tmp;

                }

            }

        }

        return datat;

    }


    public void doSwitch() {

    }


    public void enabledTabbedPane(boolean enabled) {


    }


    public Loginusers_T getCurrentUser() {

        return currentUser;

    }


    public int[] getDefaultActionToolBarMask() {

        return null;

    }


    public boolean getNestedSignaletique() {

        return false;

    }


    public java.awt.Frame getOwner() {

        return null;

    }


    public astrainterface getServeur() {

        return null;

    }


    public DossierRmiInterface getServeurDossier() {

        return null;

    }


    public java.awt.Frame getSuperOwner() {

        return null;

    }


    public java.awt.Component m_getGeneriqueTable() {

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

        initToolBarAction();

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


    public void setThisAsToolBarComponent() {

    }


    public void saveToolBarInfo() {

    }


    public void valueChanged(ListSelectionEvent e) {

    }


    /**
     * Getter for property panelSelected.
     *
     * @return Value of property panelSelected.
     */

    public int getPanelSelected() {

        return panelSelected;

    }


    /**
     * Setter for property panelSelected.
     *
     * @param panelSelected New value of property panelSelected.
     */

    public void setPanelSelected(int panelSelected) {

        this.panelSelected = panelSelected;

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables


}

