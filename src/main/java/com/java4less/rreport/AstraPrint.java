/* Classe AstraPrint
*
*Crée le 19 aout 2003
*
*par Driart
*
*Cette classe permet d'imprimer (afficher) les listings (rapports des dossiers)
*/
package com.java4less.rreport;

import srcastra.JCFactureTest;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.modules.MainScreenModule;
import srcastra.astra.gui.modules.aidedesicion.AbstractBuffer;
import srcastra.astra.gui.modules.aidedesicion.getBufferData;
import srcastra.astra.gui.modules.dossier.DossierIndex;
import srcastra.astra.gui.modules.dossier.MemoFrame;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.ResumeClasse;
import srcastra.astra.sys.classetransfert.dossier.Memo_T;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.compta.MathRound;
import srcastra.astra.sys.configuration.AbstractRequete;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;
import srcastra.astra.sys.utils.Rentabilite;
import srcastra.test.ServeurConnect;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

public class AstraPrint /*extends Frame*/ implements RAreaListener {

    Loginusers_T user;
    ServeurConnect connect;
    DossierRmiInterface dossier;
    DossierIndex fenetre; //pour les parcours des dossiers
/*public static void main(String[] args) {

AstraPrint f = new AstraPrint();
f.setVisible(true);
f.init();

} */

    java.awt.Frame frame;
    MY_bigDecimal achat;
    MY_bigDecimal vente;
    MY_bigDecimal caisse;
    MY_bigDecimal rentabilite;
    JTable dossierindex;
    RReportJ2 report;
    JCFactureTest Win;
    //RReportWindow Win;
    MainScreenModule parent;
    AbstractBuffer buffer;
    String messageAnomalie = "";
    String messageQuantite = "";
    String numdos = "";
    int dossiercleunik;
    boolean anomalie = false;

    public AstraPrint(int dossiercleunik, java.awt.Frame frame, MainScreenModule parent, JTable dossierindex, boolean anomalie, String drnumdos) {
        this.dossierindex = dossierindex;
        this.parent = parent;
        this.user = parent.getCurrentUser();
        this.frame = frame;
        this.anomalie = anomalie;
        this.numdos = drnumdos;
        this.dossiercleunik = dossiercleunik;

        achat = new MY_bigDecimal("0");
        vente = new MY_bigDecimal("0");
        caisse = new MY_bigDecimal("0");
        rentabilite = new MY_bigDecimal("0");
        try {
            buffer = (AbstractBuffer) parent.getServeur().workWithDecision(null, user.getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, 0, AbstractRequete.DECSRIPTIF_LOG);
            dossier = parent.getServeur().renvDossierRmiObject(user.getUrcleunik());

            if (drnumdos.length() > 8) {
                if (drnumdos.charAt(8) == 'G')
                    messageAnomalie = dossier.getQuantite(user.getUrcleunik(), drnumdos);
                else

                    messageAnomalie = dossier.getAnomalieDossier(user.getUrcleunik(), dossiercleunik, false);

            } else
                messageAnomalie = dossier.getAnomalieDossier(user.getUrcleunik(), dossiercleunik, false);

            //System.out.println(cc);


        } catch (java.rmi.RemoteException rn) {
            rn.printStackTrace();
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn) {
            sn.printStackTrace();
        }
        columnsData = getInfo(dossiercleunik);
        columnsData2 = getInfo2(dossiercleunik);
//modif
        temp = getInfo1(dossiercleunik);
        if (temp != null)
            columnsData1 = temp;

        //end modif
/*      
columnsData1 = getInfo1(dossiercleunik);
//  columnsData1 = temp;
*/
    }

    public void setHeaderLanguage() {
        report.getReportHeader().getItemByName("lbClient1").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbClient1"));
        report.getReportHeader().getItemByName("lbAdresse").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbAdresse"));
        report.getReportHeader().getItemByName("lbref").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbref"));
        report.getReportHeader().getItemByName("lbtel").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbtel"));
        report.getReportHeader().getItemByName("lbgsm").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbgsm"));
        report.getReportHeader().getItemByName("lbemail").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbemail"));
        report.getReportHeader().getItemByName("lbreg").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbreg"));
        report.getReportHeader().getItemByName("lbDossier").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbDossier"));
        report.getReportHeader().getItemByName("lbDepart").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbDepart"));
        report.getReportHeader().getItemByName("lbRetour").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbRetour"));
        report.getReportHeader().getItemByName("lbNbJour").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbNbJour"));
        report.getReportHeader().getItemByName("lbType").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbType"));
        report.getReportHeader().getItemByName("lbStatut").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbStatut"));
    }

    public Object[][] setPassagerLanguage() {
        String test = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("nom");
        System.out.println("[***************]passage " + test);
        Object[][] tmptab = new Object[1][4];
        tmptab[0][0] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("nom");
        tmptab[0][1] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("prenom");
        tmptab[0][2] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("passagers");
        tmptab[0][3] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("adresse");

        return tmptab;
    }

    public Object[][] setProduitLanguage() {
        String test = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("nom");
        System.out.println("[***************]passage " + test);
        Object[][] tmptab = new Object[1][8];
        tmptab[0][0] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("type");
        tmptab[0][1] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("fournisseur");
        tmptab[0][2] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("grp");
        tmptab[0][3] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("RVField512");
        tmptab[0][4] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("dest");
        tmptab[0][5] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("localite");
        tmptab[0][6] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("RVField83");
        tmptab[0][7] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("RVField84");
        return tmptab;

    }

    public Object[][] setComptaLanguage() {
        String test = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("nom");
        System.out.println("[***************]passage " + test);
        Object[][] tmptab = new Object[1][10];
        tmptab[0][0] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("RVField787");
        tmptab[0][1] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("note");
        tmptab[0][2] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("journal");
        tmptab[0][3] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("num");
        tmptab[0][4] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("creation");
        tmptab[0][5] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("mouvement");
        tmptab[0][6] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("valeur");
        tmptab[0][7] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("codeTVA");
        tmptab[0][8] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("valeurTVA");
        tmptab[0][9] = java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("libelle");
        return tmptab;

    }


    private boolean isNotservice(String doss) {
        boolean retour = true;

        if (doss.length() > 8) {
            if (doss.charAt(8) == 'F' || doss.charAt(8) == 'N')
                retour = false;
            if (doss.charAt(8) == 'G')
                if (doss.charAt(9) != '0')
                    retour = false;


        }

        return retour;


    }

    public void next() {
        if (dossierindex.getRowCount() > 0) {
            int row = dossierindex.getSelectedRow();
            if (row < dossierindex.getRowCount() - 1) {


                dossierindex.changeSelection((row + 1), 0, false, false);
                Object[] tmp = ((srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierIndexTableModel) dossierindex.getModel()).getClassAffichage(dossierindex.getSelectedRow());

                String doss = (String) tmp[1].toString();

                boolean aff = true;


                if (anomalie && messageAnomalie.length() == 0)
                    aff = false;

                if (aff) {

                    while (!isNotservice(doss) && (dossierindex.getSelectedRow() < dossierindex.getRowCount() - 1)) {

                        dossierindex.changeSelection((row + 1), 0, false, false);
                        tmp = ((srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierIndexTableModel) dossierindex.getModel()).getClassAffichage(dossierindex.getSelectedRow());
                        row = dossierindex.getSelectedRow();
                        doss = (String) tmp[1].toString();
                        try {
                            if (doss.length() > 8) {
                                if (doss.charAt(8) == 'G')
                                    messageAnomalie = dossier.getQuantite(user.getUrcleunik(), doss);
                                else
                                    messageAnomalie = dossier.getAnomalieDossier(user.getUrcleunik(), dossiercleunik, false);

                            } else
                                messageAnomalie = dossier.getAnomalieDossier(user.getUrcleunik(), dossiercleunik, false);
                        } catch (Exception e) {
                        }

                    }


                    achat = new MY_bigDecimal("0");
                    vente = new MY_bigDecimal("0");
                    caisse = new MY_bigDecimal("0");
                    rentabilite = new MY_bigDecimal("0");
                    autreDossier();

                } else
                    next();
                //dossierindex.changeSelection((row + 1), 0, false, false);
            }
        }
    }

    public void previous() {
        if (dossierindex.getRowCount() > 0) {
            int row = dossierindex.getSelectedRow();
            if (row > 0) {
                dossierindex.changeSelection((row - 1), 0, false, false);

                Object[] tmp = ((srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierIndexTableModel) dossierindex.getModel()).getClassAffichage(dossierindex.getSelectedRow());

                String doss = (String) tmp[1].toString();
                ;
                while (!isNotservice(doss) && (dossierindex.getSelectedRow() > 0)) {

                    dossierindex.changeSelection((row - 1), 0, false, false);
                    tmp = ((srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierIndexTableModel) dossierindex.getModel()).getClassAffichage(dossierindex.getSelectedRow());
                    row = dossierindex.getSelectedRow();
                    doss = (String) tmp[1].toString();


                }


                achat = new MY_bigDecimal("0");
                vente = new MY_bigDecimal("0");
                caisse = new MY_bigDecimal("0");
                rentabilite = new MY_bigDecimal("0");
                autreDossier();
            }
        }
    }

    public void autreDossier() {
        if (dossierindex.getRowCount() > 0) {
            int ligne = dossierindex.getSelectedRow();
            Object[] tmp = ((srcastra.astra.gui.sys.tableModel.dossierTableModel.DossierIndexTableModel) dossierindex.getModel()).getClassAffichage(dossierindex.getSelectedRow());
            if (tmp != null) {
                int cle = ((Long) tmp[0]).intValue();
                columnsData = getInfo(cle);
                columnsData2 = getInfo2(cle);
//modif
                temp = getInfo1(cle);
                if (temp != null)
                    columnsData1 = temp;
                init(cle);
            }

        }
    }

    // data arrays
    public static String[] columnsNames = {"3", "5", "6", "7", "8",
            "14", "15", "17", "18"};
    public static String[] columnsNames1 = {"type", "fourn", "group", "dest", "loc", "po", "Valeur"};
    public static String[] columnsNames2 = {"1", "2", "3"};
    public Object[][] columnsData;
    public Object[][] columnsData1 = {{"null", "null", "null", "null", "null", "null", "null"}};

    public Object[][] columnsData2;
    public Object[][] temp = {{"rien", "rien", "rien", "rien", "rien", "rien", "rien"}};

    public static String[] columnsNames3 = {"label"};
    public Object[][] columnsData3 = {{"Rien"}};


    //getting info for operations comptables
    public Object[][] getInfo(int dossiercleunik) {
        ArrayList info = new ArrayList();
        try {
            System.out.println("user" + user);
            info = dossier.getListing(user.getUrcleunik(), dossiercleunik);

            for (int i = 0; i < info.size(); i++) {
                Object[] temp = (Object[]) info.get(i);

                if (i > 0 && Integer.valueOf(temp[2].toString()).longValue() == 0) {
                    info.remove(i);
                    i--;
                }
                System.out.println(temp);
            }

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
            System.out.println("Erreur lors de getListing");
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            System.out.println("Erreur lors de getListing");
        }
        Object[][] blla = null;
        if (info.size() != 0) {
            blla = new Object[info.size()][21];

            for (int i = 0; i < info.size(); i++) {
                Object[] temp = (Object[]) info.get(i);


                somme(temp);
                for (int j = 0; j < temp.length; j++) {
                    if (j == temp.length - 1)
                        ;
                    else
                        blla[i][j] = temp[j];


                }

            }
        }
        return blla;
    }

    /* private void somme(Object tmp[]){
    double val=((Double)tmp[5]).doubleValue();
    //  tmp[5]=new Double(Math.abs(val));
    if(tmp[tmp.length-1].toString().equals("B"))
    vente=vente+((Double)tmp[5]).doubleValue();
    else if(tmp[tmp.length-1].toString().equals("ACP"))
    achat=achat+((Double)tmp[5]).doubleValue();
    else if(tmp[tmp.length-1].toString().equals("NCA"))
    achat=achat-Math.abs(((Double)tmp[5]).doubleValue());
    else if(tmp[tmp.length-1].toString().equals("NCB") || tmp[tmp.length-1].toString().equals("NCOB"))
    vente=vente-Math.abs(((Double)tmp[5]).doubleValue());
    else if(tmp[tmp.length-1].toString().equals("BP") || tmp[tmp.length-1].toString().equals("OCCC")){
    caisse=caisse+((Double)tmp[5]).doubleValue();
    if(tmp[tmp.length-1].toString().equals("OCCC"))
    achat=achat-((Double)tmp[5]).doubleValue();
    }
    }*/
    private void somme(Object tmp[]) {
        MY_bigDecimal val = new MY_bigDecimal(tmp[5].toString());
//  tmp[5]=new Double(Math.abs(val));
        if (tmp[tmp.length - 1].toString().equals("B") || tmp[tmp.length - 1].toString().equals("NCB") || tmp[tmp.length - 1].toString().equals("NCOB"))
            vente = vente.add(val);
        else if (tmp[tmp.length - 1].toString().equals("ACP") || tmp[tmp.length - 1].toString().equals("NCA")) {
// val=val*-1;
            achat = achat.add(val);
        }
//  else if(tmp[tmp.length-1].toString().equals("NCA"))
//    achat=achat-Math.abs(((Double)tmp[5]).doubleValue());
// else if(tmp[tmp.length-1].toString().equals("NCB") || tmp[tmp.length-1].toString().equals("NCOB"))
//    vente=vente-Math.abs(((Double)tmp[5]).doubleValue());
        else if (tmp[tmp.length - 1].toString().equals("BP") || tmp[tmp.length - 1].toString().equals("OCCC")) {
            caisse = caisse.add(val);
            if (tmp[tmp.length - 1].toString().equals("OCCC"))
                achat = achat.add(val);
//}
        }
    }

    //getting info for passgers
    public Object[][] getInfo2(int dossiercleunik) {
        ArrayList info = new ArrayList();
        try {
            System.out.println("user" + user);//j'a'
            info = dossier.getPassagers(user, dossiercleunik);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
            System.out.println("Erreur lors de getListing");
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            System.out.println("Erreur lors de getListing");
        }

//putting values from info to blla cause blla is an object
        Object[][] blla = null;
        if (info.size() != 0) {
            blla = new Object[info.size()][3];
            for (int i = 0; i < info.size(); i++) {
                Object[] temp = (Object[]) info.get(i);
                for (int j = 0; j < temp.length; j++) {
                    if (temp[j] == null)
                        temp[j] = " ";
                    blla[i][j] = temp[j];
                }
            }
        }
        return blla;
    }

    //getting infos for produits
    private void getLogement(ResumeClasse resum, ArrayList array) {
        if (resum != null) {
            ArrayList descriptif = resum.getDescriptif();
            if (descriptif != null) {
                for (int i = 0; i < descriptif.size(); i++) {
                    Object[] tmp = (Object[]) descriptif.get(i);
                    Object[] logement = new Object[7];
                    logement[0] = tmp[0];
                    logement[1] = getBufferData.getElement(buffer, 1, Integer.parseInt(tmp[1].toString()), user.getUrlmcleunik());
                    logement[2] = getBufferData.getElement(buffer, 3, Integer.parseInt(tmp[5].toString()), user.getUrlmcleunik());
                    logement[3] = getBufferData.getElement(buffer, 8, Integer.parseInt(tmp[3].toString()), user.getUrlmcleunik());
                    logement[4] = getBufferData.getElement(buffer, 5, Integer.parseInt(tmp[4].toString()), user.getUrlmcleunik());
                    logement[5] = getBufferData.getElement(buffer, 2, Integer.parseInt(tmp[2].toString()), user.getUrlmcleunik());
                    logement[6] = tmp[6];
                    array.add(logement);
                }
            }
        }
    }

    public Object[][] getInfo1(int dossiercleunik) {
        ArrayList info = new ArrayList();
        try {
            System.out.println("user" + user);
            info = dossier.getProduitData(user.getUrcleunik(), dossiercleunik, false);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
            System.out.println("Erreur lors de getInfo1");
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            System.out.println("Erreur lors de getInfo1");
        }

        System.out.println("Le info.size() vaut: " + info.size());
//putting values from info to blla cause blla is an object
        Object[][] blla = null;
//pour memo
        Object[][] memo = null;
//end memo
        if (info.size() != 0) {
            blla = new Object[info.size()][8];
            memo = new Object[info.size()][1];
            ArrayList tmpa = null;
            for (int i = 0; i < info.size(); i++) {
                if (tmpa == null)
                    tmpa = new ArrayList();
                Object[] tmptab = new Object[7];
                ResumeClasse resum = (ResumeClasse) info.get(i);
                tmptab[0] = resum.getType();
                tmptab[1] = resum.getFournisseur();
                tmptab[2] = resum.getGroep();
                tmptab[3] = resum.getDestination();
                tmptab[4] = resum.getLogement();
                tmptab[5] = resum.getPoPnr();
                tmptab[6] = new Double(resum.getPrix());
                tmpa.add(tmptab);
                getLogement(resum, tmpa);
                Object[] vide = new Object[]{"", "", "", "", "", "", "", ""};
                tmpa.add(vide);

//  Object [] temp =(Object[])info.get(i);

// blla[i][7]=resum.getType();
// for(int j=0;j<temp.length;j++){
//   blla[i][j]=temp[j];
//}
                memo[i][0] = resum.getMemo();
            }
            blla = new Object[tmpa.size()][8];
            if (tmpa != null) {
                for (int i = 0; i < tmpa.size(); i++) {
                    Object[] tab = (Object[]) tmpa.get(i);
                    for (int j = 0; j < tab.length; j++) {
                        blla[i][j] = tab[j];
                    }
                }
            }
        }
        columnsData3 = memo;

        return blla;
    }


    public void init(int dossiercleunik) {

//loading the dossier values
        ArrayList res = new ArrayList();

        try {
            res = dossier.getDossier(user.getUrcleunik(), dossiercleunik);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
            System.out.println("Erreur lors de getDossier");
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            System.out.println("Erreur lors de getDossier");
        }
//loading the client values
        ArrayList res2 = new ArrayList();
        try {

            res2 = dossier.getClient(user.getUrcleunik(), dossiercleunik, user.getUrlmcleunik());
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
            System.out.println("Erreur lors de getClient");
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            System.out.println("Erreur lors de getClient");
        }

// load report from file
        if (report == null) {
            report = new RReportJ2(frame);
            URL url = getClass().getResource("/srcastra/astra/listing/frrentaglob1.rep");
            URL url2 = getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif");
            if (url != null) {
                System.out.println("url : " + url.getPath());
            } else {
                System.out.println("URL NULL");
            }
            if (!report.importReport(getClass().getResource("/srcastra/astra/listing/frrentaglob1.rep"))) return;
            setHeaderLanguage();

        }

// this will skip the printer dialog!
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = false;
        pt.horizontal = false;
        report.setPrintSetup(pt);
//les manipulations pour remplir les fields
        report.getAreaByName("Detail").setListener(this);
        report.getAreaByName("Group 8 detail").setListener(this);
        report.getAreaByName("Group 10 detail").setListener(this);
// set the values for the header programatically
//setting the values for the dossier
        Object[] blla = null;
        System.out.println("res2.size() est: " + res2.size());
        if (res2 != null && res2.size() > 0)
            blla = (Object[]) res2.get(0);
        if (blla != null) {
            String tmp = "";
            if (blla[0] != null)
                tmp = blla[0].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("Client").setruntimeValue(tmp);
            if (blla[2] != null)
                tmp = blla[2].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("Adresse").setruntimeValue(tmp);
            if (blla[1] != null)
                tmp = blla[1].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("ref").setruntimeValue(tmp);
            if (blla[3] != null)
                tmp = blla[3].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("tel").setruntimeValue(tmp);
            if (blla[4] != null)
                tmp = blla[4].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("GSM").setruntimeValue(tmp);
            if (blla[5] != null)
                tmp = blla[5].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("e-mail").setruntimeValue(tmp);
            if (blla[6] != null)
                tmp = blla[6].toString();
            else
                tmp = "";
            report.getReportHeader().getItemByName("reg").setruntimeValue(tmp);
            if (blla[7] != null)
                tmp = blla[7].toString();
            else
                tmp = "";
            if (blla[8] != null)
                tmp = tmp + " " + blla[8].toString();
            else
                tmp = tmp + "";
            report.getReportHeader().getItemByName("loc").setruntimeValue(tmp);
        }
//setting the values for the dossier
        Object[] tab = null;
        System.out.println("res.size() est: " + res.size());
        if (res != null && res.size() > 0) {
            tab = (Object[]) res.get(0);
            Object tmpmemo = res.get(1);
            if (tmpmemo != null && !tmpmemo.toString().equals("dumy")) {
                Memo_T memo = (Memo_T) tmpmemo;
                if (memo.getSpecialdossier() == 1) {
                    new MemoFrame(parent.getSuperOwner(), true, memo).show();
                }
            }
        }
        if (tab != null) {
            report.getReportHeader().getItemByName("Dossier").setruntimeValue(tab[0].toString());
            if (tab[1] != null) {
                Date date = (Date) tab[1];
                report.getReportHeader().getItemByName("Départ").setruntimeValue(date.toString2());
            }
            if (tab[2] != null) {
                Date date = (Date) tab[2];
                report.getReportHeader().getItemByName("Retour").setruntimeValue(date.toString2());
            }

            report.getReportHeader().getItemByName("NbJour").setruntimeValue(tab[3].toString());
            report.getReportHeader().getItemByName("Type").setruntimeValue(tab[4].toString());
            report.getReportHeader().getItemByName("Statut").setruntimeValue(tab[5].toString());
        }
//report.showProgressWindow = true;
//end manipulations$
        //  report.getAreaByName("Group 10 footer").setDataSource(new RArraySource(columnsNames3, columnsData3));
        report.getAreaByName("Group 10 detail").setDataSource(new RArraySource(columnsNames1, columnsData1));
        String[] produitname = new String[]{"produit", "fournisseur", "grp", "RVField512", "dest", "localite", "RVField83", "RVField84"};
        report.getAreaByName("Group 10 header").setDataSource(new RArraySource(produitname, setProduitLanguage()));
        String[] comptaName = new String[]{"RVField787", "note", "journal", "num", "creation", "mouvement", "valeur", "codeTVA", "valeurTVA", "libelle"};
        report.getAreaByName("Group 8 header").setDataSource(new RArraySource(comptaName, setComptaLanguage()));
        report.getAreaByName("Group 8 detail").setDataSource(new RArraySource(columnsNames, columnsData));
        report.getAreaByName("Group 13 detail").setDataSource(new RArraySource(columnsNames2, columnsData2));
        String[] name = new String[]{"nom", "prenom", "passagers", "adresse"};
        report.getAreaByName("Group 13 header").setDataSource(new RArraySource(name, setPassagerLanguage()));
//achat=Math.abs(achat);
        report.getAreaByName("Footer").getItemByName("achat").setruntimeValue("" + achat);
        report.getAreaByName("Footer").getItemByName("vente").setruntimeValue("" + vente);
        report.getAreaByName("Footer").getItemByName("paiement").setruntimeValue("" + caisse);
        report.getAreaByName("Footer").getItemByName("solde").setruntimeValue("" + vente.subtract(caisse));
        report.getAreaByName("Footer").getItemByName("lbTotal").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbTotal"));
        report.getAreaByName("Footer").getItemByName("lbTotal2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbTotal2"));
        report.getAreaByName("Footer").getItemByName("lbPaiement").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbPaiement"));
        report.getAreaByName("Footer").getItemByName("lbResultat").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbResultat"));
        report.getAreaByName("Footer").getItemByName("lbSolde").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/f10edition", parent.getCurrentUser().getLangage()).getString("lbSolde"));
        rentabilite = Rentabilite.getRentabilite(achat, vente);
        report.getAreaByName("Footer").getItemByName("rentab").setruntimeValue("" + rentabilite + " %");
        report.getAreaByName("Footer").getItemByName("anomalies").setruntimeValue("" + messageAnomalie + "");

//this will print the Header Area and all dependent areas (the detail area)
//setPassagerLanguage();
        RArea a = report.getAreaByName("Group 13 header");
        for (java.util.Enumeration e = a.items.elements(); e.hasMoreElements();) {
            RObject Obj = (RObject) e.nextElement();
            System.out.println("before prepare header fields value " + Obj.getruntimeValue());
        }
        report.prepare();
        a = report.getAreaByName("Group 13 header");
        for (java.util.Enumeration e = a.items.elements(); e.hasMoreElements();) {
            RObject Obj = (RObject) e.nextElement();
            System.out.println("after prepare header fields value " + Obj.getruntimeValue());
        }
        report.printArea(report.getAreaByName("Group 13 detail"));
        report.printArea(report.getAreaByName("Group 10 detail"));
        report.printArea(report.getAreaByName("Group 10 footer"));
        report.printArea(report.getAreaByName("Group 8 detail"));
        //report.endReport();

        if (Win == null) {
            Win = new JCFactureTest(report, null, parent.getServeur(), parent.getCurrentUser(), frame, 0);
            Win.setDossierprint(this);
            Win.show();
        } else {
            Win.setVisible(false);
            Win.endReport();
            Win.setVisible(true);
        }


    }

    public void beforePrintingArea(RArea area) {
//  setPassagerLanguage();
    }

    /* public MY_bigDecimal calculrentabilite(MY_bigDecimal vente,MY_bigDecimal achat) {
      if(vente==null || achat==null){
          rentabilite = new MY_bigDecimal("-10");
      }
      else if (achat.doubleValue() == 0d) {
          rentabilite = new MY_bigDecimal("100");
      }
      else{
      MY_bigDecimal quotient=vente.divide(new MY_bigDecimal("100"),2);
      MY_bigDecimal diff=vente.subtract(achat);
      MY_bigDecimal rentabilite=diff.divide(quotient,2);

      }
      return rentabilite;
  }  */
}
