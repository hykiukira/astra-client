/*
* DossierReport.java
*
* Created on 13 novembre 2003, 12:49
*/
package srcastra.astra.gui.list;

import srcastra.astra.sys.classetransfert.*;
import com.java4less.rreport.*;

import java.util.*;

import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;
import srcastra.astra.sys.rmi.*;


/**
 * @author Thomas
 */
public class DossierReport extends com.java4less.rreport.RReportJ2X {

    /**
     * Creates a new instance of DossierReport
     */
    Vector v;
    Loginusers_T user;
    RReportJWindow Win;
    java.awt.Frame owner;
    Object[][] datat;
    ArrayList drcleunikRent;
    MY_bigDecimal tot;
    MY_bigDecimal paye;
    MY_bigDecimal solded;
    MY_bigDecimal cash;
    MY_bigDecimal cheque;
    MY_bigDecimal cash2;
    MY_bigDecimal cheque2;
    MY_bigDecimal entre;
    MY_bigDecimal sortie;
    MY_bigDecimal cc;

    MY_bigDecimal totvnf1, totvf1, totncv1, totachat1, totachatnc1, rent1, totachatnf1, totachatncnf1;

    MY_bigDecimal totapayer, totbase, tottva;

    MY_bigDecimal totalvente, totalfacture, totalventeprec, totaldossier, totaldossierprec, totaldifference, totalPourcent;

    MY_bigDecimal t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;

    String from;

    public DossierReport(java.awt.Frame owner, String from) {

        super(owner);
        this.owner = owner;
        this.from = from;
        tot = new MY_bigDecimal("0.00");
        paye = new MY_bigDecimal("0.00");
        solded = new MY_bigDecimal("0.00");
        cash = new MY_bigDecimal("0.00");
        cheque = new MY_bigDecimal("0.00");
        cash2 = new MY_bigDecimal("0.00");
        cheque2 = new MY_bigDecimal("0.00");
        entre = new MY_bigDecimal("0.00");
        sortie = new MY_bigDecimal("0.00");
        cc = new MY_bigDecimal("0.00");

        totvnf1 = new MY_bigDecimal("0.00");
        totvf1 = new MY_bigDecimal("0.00");
        totncv1 = new MY_bigDecimal("0.00");
        totachat1 = new MY_bigDecimal("0.00");
        totachatnc1 = new MY_bigDecimal("0.00");
        rent1 = new MY_bigDecimal("0.00");
        totachatnf1 = new MY_bigDecimal("0.00");
        totachatncnf1 = new MY_bigDecimal("0.00");

        totapayer = new MY_bigDecimal("0.00");
        totbase = new MY_bigDecimal("0.00");
        tottva = new MY_bigDecimal("0.00");


        this.totalvente = new MY_bigDecimal("0.00");
        this.totalventeprec = new MY_bigDecimal("0.00");
        this.totaldifference = new MY_bigDecimal("0.00");
        totapayer = new MY_bigDecimal("0.00");
        this.totalPourcent = new MY_bigDecimal("0.00");
        this.totaldossier = new MY_bigDecimal("0");
        this.totaldossierprec = new MY_bigDecimal("0");
        this.totalfacture = new MY_bigDecimal("0.00");

        this.t1 = new MY_bigDecimal("0.00");
        this.t2 = new MY_bigDecimal("0.00");
        this.t3 = new MY_bigDecimal("0.00");
        this.t4 = new MY_bigDecimal("0.00");
        this.t5 = new MY_bigDecimal("0.00");
        this.t6 = new MY_bigDecimal("0.00");
        this.t7 = new MY_bigDecimal("0.00");
        this.t8 = new MY_bigDecimal("0.00");
        this.t9 = new MY_bigDecimal("0.00");
        this.t10 = new MY_bigDecimal("0.00");
        this.t11 = new MY_bigDecimal("0.00");
        this.t12 = new MY_bigDecimal("0.00");
    }

    public void prepareFourn(Loginusers_T user, ArrayList data) {

        //
        String[] colname = new String[]{"RVLNOM", "RVLREF", "RVLREF2", "RVLADRESSE", "RVLCATALOGUE", "RVLECHEANCE"};
        this.user = user;


        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingFournisseurs.rep"))) return;

        ///   setHeaderJournal(datedepbeg, datedebend,entite,type);
        transformArrayFourn(data);

        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));

        }

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\fournisseur\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);

        setPrintSetup(pt);
        prepare();

        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
        }
        endReport();


        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();


    }

    public void prepareChiffreAffaireFournisseur(Loginusers_T user, ArrayList data, ArrayList data1, String datecreationdeb, String datecreationfin, String entite) {

        String[] colname = new String[]{"RVFOURNISSEUR", "RVBROCHURE", "RVTotal", "RVTotalPrec", "RVDifference", "RVPourcent"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingChiffreAffaireFournisseur.rep")))
            return;

        setHeaderChiffreAffaireF(datecreationdeb, datecreationfin, entite, "", "");
        this.transformArrayChiffreAffaireFournisseur(data, data1);
        //System.out.println(datat);

        if (datat != null) {
            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("TOTVENTE").setruntimeValue(totalvente.toString());
            // getAreaByName("Footer").getItemByName("TOTFACTURE").setruntimeValue(totalfacture.toString());
            //     getAreaByName("Footer").getItemByName("TOTDOSSIER").setruntimeValue(totaldossier.toString());
            getAreaByName("Footer").getItemByName("TOTVENTEPREC").setruntimeValue(totalventeprec.toString());
            //   getAreaByName("Footer").getItemByName("TOTDOSSIERPREC").setruntimeValue(totaldossierprec.toString());
            getAreaByName("Footer").getItemByName("TOTDIFFERENCE").setruntimeValue(totaldifference.toString());
            getAreaByName("Footer").getItemByName("TOTPOURCENT").setruntimeValue(this.totalPourcent.toString());
        }

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\chiffreAffaireCli\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);


        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();
    }

    public void prepareEmbDeb(Loginusers_T user, ArrayList data, boolean embarquement) {
        String[] colname = new String[]{"RVPASS", "RVVOUCH", "RVFROM", "RVTO"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/embdeb.rep"))) return;
        this.setHeaderEmbDeb(embarquement);
        this.transformArrayEmbDeb(data);

        if (datat != null) {
            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("NBPART").setruntimeValue("NB:" + datat.length);
        }

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\embdeb\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);


        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();


    }


    public void prepareChiffreAffaireClient(Loginusers_T user, ArrayList data, ArrayList data1, String datecreationdeb, String datecreationfin, String entite) {

        String[] colname = new String[]{"RVCLIENT", "RVTotalVente", "RVTotalFacture", "RVNbDossier", "RVTotalAnneePrec", "RVNbDossierAnneePrec", "RVDifference", "RVPourcent"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingChiffreAffaireClient.rep"))) return;

        setHeaderChiffreAffaire(datecreationdeb, datecreationfin, entite, "", "");
        this.transformArrayChiffreAffaire(data, data1);
        //System.out.println(datat);

        if (datat != null) {
            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("TOTVENTE").setruntimeValue(totalvente.toString());
            getAreaByName("Footer").getItemByName("TOTFACTURE").setruntimeValue(totalfacture.toString());
            //     getAreaByName("Footer").getItemByName("TOTDOSSIER").setruntimeValue(totaldossier.toString());
            getAreaByName("Footer").getItemByName("TOTVENTEPREC").setruntimeValue(totalventeprec.toString());
            //   getAreaByName("Footer").getItemByName("TOTDOSSIERPREC").setruntimeValue(totaldossierprec.toString());
            getAreaByName("Footer").getItemByName("TOTDIFFERENCE").setruntimeValue(totaldifference.toString());
            getAreaByName("Footer").getItemByName("TOTPOURCENT").setruntimeValue(this.totalPourcent.toString());
        }

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\chiffreAffaireCli\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);


        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();
    }

    public void prepareJourn(Loginusers_T user, String datedepbeg, String datedebend, String entite, String type, ArrayList data) {

        String[] colname = new String[]{"RVLNumPiece", "RVLDateMouv", "RVLNumDoss", "RVLDateDepart", "RVLCLIENT", "RVLAPayer", "RVLVALEURBASE", "RVLTVA", "star"};
        this.user = user;


        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingJourn.rep"))) return;

        setHeaderJournal(datedepbeg, datedebend, entite, type);
        transformArrayJourn(data);

        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("TOTAPAYER").setruntimeValue(totapayer.toString());
            getAreaByName("Footer").getItemByName("TOTBASE").setruntimeValue(totbase.toString());
            getAreaByName("Footer").getItemByName("TOTTVA").setruntimeValue(tottva.toString());

        }

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\journ\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;

        setPrintSetup(pt);
        prepare();

        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();

    }

    public void prepareStatFourn(Loginusers_T user, String datedepbeg, String datedebend, String entite, String datecreationd, String datecreationf, ArrayList data, ArrayList data1) {

        String[] colname = new String[]{"RVLnumdos", "RVFourn", "TOTVNF", "TOTVF", "TOTNCV", "TOTACHAT", "TOTACHATNC", "TOTACHATNF", "TOTACHATNCNF", "RENT"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingStatFourn1.rep"))) return;
        // setHeader(datedepbeg, datedebend, creationbeg, creationend, solde, facture, clientc,
        //         clientf, fournisseur, passager, destination, po, numdos, entite,
        //         groupprod, produit, bdc, annuler);

        setHeaderStatFourn(datedepbeg, datedebend, entite, datecreationd, datecreationf);

        transformArrayStatFourn(data, data1);

        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("TOTVNF").setruntimeValue(totvnf1.toString());
            getAreaByName("Footer").getItemByName("TOTVF").setruntimeValue(totvf1.toString());
            getAreaByName("Footer").getItemByName("TOTNCV").setruntimeValue(totncv1.toString());
            getAreaByName("Footer").getItemByName("TOTACHAT").setruntimeValue(totachat1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNC").setruntimeValue(totachatnc1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNCNF").setruntimeValue(totachatncnf1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNF").setruntimeValue(totachatnf1.toString());

            double totv = totvnf1.doubleValue() + totvf1.doubleValue() + totncv1.doubleValue();


            double rent = 0;

            if (totv != 0) {
                double totach = totachat1.doubleValue() + totachatnc1.doubleValue() + totachatncnf1.doubleValue() + totachatnf1.doubleValue();

                rent = 100 - ((totach / totv) * 100);

            }

            rent1 = new MY_bigDecimal(String.valueOf(rent));


            getAreaByName("Footer").getItemByName("RENT").setruntimeValue(rent1.toString() + "%");

            /*   double totv=totvnf1.doubleValue()+totvf1.doubleValue()+totncv1.doubleValue();
    
            
            double rent=0;
            
            if(totv!=0)
            {
                
                double totach=totachat1.doubleValue()+totachatnc1.doubleValue();
                
                rent=100-((totach/totv)*100);
                
            }
            
            rent1 = new MY_bigDecimal(String.valueOf(rent));
            
            
            
            getAreaByName("Footer").getItemByName("RENT").setruntimeValue(rent1.toString()+"%");*/
            //getAreaByName("Group 2 footer").getItemByName("RVPaye").setruntimeValue(paye.toString());
            //getAreaByName("Group 2 footer").getItemByName("RVSolde").setruntimeValue(solded.toString());
        }
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\statfourn\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);


        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();
    }

    public void prepareIntegratedAchat(Loginusers_T user, ArrayList data) {
        String[] colname = new String[]{"RVFACTTO", "RVPO", "RVDATEDEP", "RVCLIENT", "RVMONTANT", "RVVENTE", "RVCOM", "RVETAT", "RVDOSSIER"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingIntegrationAchat.rep"))) return;

        getPageHeader().getItemByName("Titre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("titreintegration"));
        getPageHeader().getItemByName("factto").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("factto"));
        getPageHeader().getItemByName("PO").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("PO"));
        getPageHeader().getItemByName("DATEDEP").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("DATEDEP"));
        getPageHeader().getItemByName("CLIENT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("CLIENT"));
        getPageHeader().getItemByName("MONTANT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("MONTANT"));
        getPageHeader().getItemByName("ETAT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("ETAT"));
        getPageHeader().getItemByName("DOSSIER").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing").getString("DOSSIER"));

        transformArrayAchat(data);

        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));

        }

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\intachat\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);

        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();

    }

    public void prepareControle(Loginusers_T user, String datedepbeg, String datedebend, String entite, String datecreationd, String datecreationf, String pivot, ArrayList data, ArrayList data1, DossierRmiInterface dossier) {
        String[] colname = new String[]{"RVLdepart", "RVLnumdos", "RVFourn", "TOTVNF", "TOTVF", "TOTNCV", "TOTACHAT", "TOTACHATNC", "TOTACHATNF", "TOTACHATNCNF", "RENT"};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingControle.rep"))) return;
        // setHeader(datedepbeg, datedebend, creationbeg, creationend, solde, facture, clientc,
        //         clientf, fournisseur, passager, destination, po, numdos, entite,
        //         groupprod, produit, bdc, annuler);

        setHeaderControle(datedepbeg, datedebend, entite, datecreationd, datecreationf);
        transformArrayControle(data, data1, dossier, user.getUrcleunik(), pivot);

        System.out.println(t1.doubleValue());
        System.out.println(t2.doubleValue());
        System.out.println(t3.doubleValue());
        System.out.println(t4.doubleValue());
        System.out.println(t5.doubleValue());
        System.out.println(t6.doubleValue());
        System.out.println(t7.doubleValue());
        System.out.println(t8.doubleValue());
        System.out.println(t9.doubleValue());
        System.out.println(t10.doubleValue());
        System.out.println(t11.doubleValue());
        System.out.println(t12.doubleValue());
        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Footer").getItemByName("TOTVNF").setruntimeValue(totvnf1.toString());
            getAreaByName("Footer").getItemByName("TOTVF").setruntimeValue(totvf1.toString());
            getAreaByName("Footer").getItemByName("TOTNCV").setruntimeValue(totncv1.toString());
            getAreaByName("Footer").getItemByName("TOTACHAT").setruntimeValue(totachat1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNC").setruntimeValue(totachatnc1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNCNF").setruntimeValue(totachatncnf1.toString());
            getAreaByName("Footer").getItemByName("TOTACHATNF").setruntimeValue(totachatnf1.toString());

            double totv = totvnf1.doubleValue() + totvf1.doubleValue() + totncv1.doubleValue();


            double rent = 0;

            if (totv != 0) {
                double totach = totachat1.doubleValue() + totachatnc1.doubleValue() + totachatncnf1.doubleValue() + totachatnf1.doubleValue();

                rent = 100 - ((totach / totv) * 100);

            }

            rent1 = new MY_bigDecimal(String.valueOf(rent));


            getAreaByName("Footer").getItemByName("RENT").setruntimeValue(rent1.toString() + "%");
            getAreaByName("Footer").getItemByName("RVPivot").setruntimeValue(dPivot.toString());
            getAreaByName("Footer").getItemByName("RV1").setruntimeValue(t1.toString());
            getAreaByName("Footer").getItemByName("RV2").setruntimeValue(t2.toString());
            getAreaByName("Footer").getItemByName("RV3").setruntimeValue(t3.toString());
            getAreaByName("Footer").getItemByName("RV4").setruntimeValue(t4.toString());
            getAreaByName("Footer").getItemByName("RV5").setruntimeValue(t5.toString());
            getAreaByName("Footer").getItemByName("RV6").setruntimeValue(t6.toString());
            getAreaByName("Footer").getItemByName("RV7").setruntimeValue(t7.toString());
            getAreaByName("Footer").getItemByName("RV8").setruntimeValue(t8.toString());
            getAreaByName("Footer").getItemByName("RV9").setruntimeValue(t9.toString());
            getAreaByName("Footer").getItemByName("RV10").setruntimeValue(t10.toString());
            getAreaByName("Footer").getItemByName("RV11").setruntimeValue(t11.toString());
            getAreaByName("Footer").getItemByName("RV12").setruntimeValue(t12.toString());
            getAreaByName("Footer").getItemByName("TOTVNF1").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvnf"));
            getAreaByName("Footer").getItemByName("TOTVF1").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvf"));
            getAreaByName("Footer").getItemByName("TOTA1").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tota"));
            getAreaByName("Footer").getItemByName("TOTANF1").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totanf"));

            //getAreaByName("Group 2 footer").getItemByName("RVPaye").setruntimeValue(paye.toString());
            //getAreaByName("Group 2 footer").getItemByName("RVSolde").setruntimeValue(solded.toString());
        }
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\controle\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);
//this.disabledPrinting=true;
        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            //printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();


    }


    public void prepareDossier(Loginusers_T user, String datedepbeg, String datedebend, String creationbeg, String creationend, String solde, String facture, String clientc, String clientf, String fournisseur, String passager, String destination, String po, String numdos, String entite, String groupprod, String produit, String bdc, String annuler, ArrayList data) {
// String[] colname=new String[]{"RVLnumdos","RVLclient","RVLdcreation","RVLdepart","RVLpass","RVLtotdos","RVLtotpaye","RVLtotacha","RVFourn","RVGrpProd","RVStatut","RVvendeur"};
        String[] colname = new String[]{"RVLnumdos", "RVLclient", "RVLdcreation", "RVLdepart", "RVLpass", "RVLtotdos", "RVLtotpaye", "RVLtotacha", "RVFourn", "RVGrpProd", "RVStatut", "RVvendeur"};
//String[] colname=new String[]{"","","","","","","","","","","",""};
        this.user = user;
        if (!importReport(getClass().getResource("/srcastra/astra/listing/ListingDossiernl.rep"))) return;

        setHeader(datedepbeg, datedebend, creationbeg, creationend, solde, facture, clientc,
                clientf, fournisseur, passager, destination, po, numdos, entite,
                groupprod, produit, bdc, annuler);
        transformArray(data, solde, facture);
        if (datat != null) {

            getAreaByName("Group 2 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));
            getAreaByName("Group 2 footer").getItemByName("RVTotal").setruntimeValue(tot.toString());
            getAreaByName("Group 2 footer").getItemByName("RVPaye").setruntimeValue(paye.toString());
            getAreaByName("Group 2 footer").getItemByName("RVSolde").setruntimeValue(solded.toString());
        }
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\dossier\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);

        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            printArea(getAreaByName("Group 2 detail"));
            printArea(getAreaByName("Group 2 footer"));
        }
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();
    }


    public void prepareDossierCaisse(Loginusers_T user, srcastra.astra.sys.classetransfert.utils.Date datedepbeg, srcastra.astra.sys.classetransfert.utils.Date datedebend, String users, String entite, CaisseList_T liste) {
// String[] colname=new String[]{"RVLnumdos","RVLclient","RVLdcreation","RVLdepart","RVLpass","RVLtotdos","RVLtotpaye","RVLtotacha","RVFourn","RVGrpProd","RVStatut","RVvendeur"};
        String[] colname = new String[]{"RVLdate", "RVLtype", "RVLnumdos", "RVLclient", "RVLpiece", "RVLlibelle", "RVLentre", "RVLsortie", "RVLcc", "RVLuser"};
//String[] colname=new String[]{"","","","","","","","","","","",""};
        this.user = user;

        if (!importReport(getClass().getResource("/srcastra/astra/listing/caisseListingfr.rep"))) {
            System.out.println(getClass().getResource("/srcastra/astra/listing/caisseListingfr.rep").getFile());
            return;
        }

        setHeaderCaisse(datedepbeg.toString2(), datedebend.toString2(), users, entite);
        Hashtable hash = null;

        transformArrayCaisse(liste.getList(), datedepbeg, datedebend);
        caculSolde(liste);
        getAreaByName("Group 1 footer").getItemByName("totentre").setruntimeValue(entre.toString());
        getAreaByName("Group 1 footer").getItemByName("totsortie").setruntimeValue(sortie.toString());
        getAreaByName("Group 1 footer").getItemByName("totcc").setruntimeValue(cc.toString());
        getAreaByName("Group 1 footer").getItemByName("cai_tot_caiselect").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_tot_caiselect"));
        getAreaByName("Group 1 footer").getItemByName("cai_tot_caiselect2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_tot_caiselect2"));
        getAreaByName("Group 1 footer").getItemByName("cashselect").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CASH"));
        getAreaByName("Group 1 footer").getItemByName("checkselect").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CHEQUE"));
        getAreaByName("Group 1 footer").getItemByName("totselectcash").setruntimeValue(cash2.toString());
        getAreaByName("Group 1 footer").getItemByName("totselectcheque").setruntimeValue(cheque2.toString());

        getPageHeader().getItemByName("cai_rech").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_rech"));
        getPageHeader().getItemByName("cai_Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_Bureau"));
        getPageHeader().getItemByName("cai_User").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_User"));
        getPageHeader().getItemByName("cai_per").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_per"));
        getPageHeader().getItemByName("cai_de").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_de"));
        getPageHeader().getItemByName("cai_au").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_au"));
        getPageHeader().getItemByName("cai_sor").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_sor"));
        getPageHeader().getItemByName("CASH").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CASH"));
        getPageHeader().getItemByName("CHEQUE").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CHEQUE"));
        getPageHeader().getItemByName("cai_date").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_date"));
        getPageHeader().getItemByName("cai_type").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_type"));
        getPageHeader().getItemByName("cai_numdos").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_numdos"));
        getPageHeader().getItemByName("cai_cli").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_cli"));
        getPageHeader().getItemByName("cai_piece").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_piece"));
        getPageHeader().getItemByName("cai_lib").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_lib"));
        getPageHeader().getItemByName("cai_entre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_entre"));
        getPageHeader().getItemByName("caititle").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("caititle"));
        getPageHeader().getItemByName("cai_so").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_sortie"));
        getPageHeader().getItemByName("cai_cc").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_cc"));
        getPageHeader().getItemByName("cai_user").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_user"));
        getReportFooter().getItemByName("RVscash").setruntimeValue(cash.add(cash2).toString());
        getReportFooter().getItemByName("RVscheque").setruntimeValue(cheque.add(cheque2).toString());

        if (datat != null) {
            getAreaByName("Group 1 detail").setDataSource(new RArraySource(colname, (Object[][]) datat));

        }
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.showDialog = true;
        pt.horizontal = true;
//    this.disabledPrinting=true;

        String path;
        String fileName;


        path = srcastra.astra.sys.ManageDirectory.testDirectory("liste\\caisse\\");
        //fileName="\\F"
        fileName = Long.toString(System.currentTimeMillis());
        path = path + fileName + ".pdf";

        this.setPDFFile(path);
        setPrintSetup(pt);
        prepare();
// printArea(getAreaByName("Group 2 header"));
        if (datat != null) {
            if (datat[0] != null) {
                printArea(getAreaByName("Group 1 detail"));
            }
            printArea(getAreaByName("Group 1 footer"));
        }

        getReportFooter().getItemByName("cai_tot").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("cai_tot"));
        getReportFooter().getItemByName("CASH").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CASH"));
        getReportFooter().getItemByName("CHEQUE").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("CHEQUE"));
        endReport();
        Win = new RReportJWindow(this, owner, user, path, from);
        Win.show();
    }

    private void caculSolde(CaisseList_T liste) {
        MY_bigDecimal tmpcash = null;
        MY_bigDecimal tmpcheque = null;
        Hashtable hash = null;
        if (liste != null)
            hash = liste.getSolde();

        if (hash != null) {
            tmpcash = new MY_bigDecimal(((Double) hash.get(new Integer(TypePayement.CASH))).toString());
            tmpcheque = new MY_bigDecimal(((Double) hash.get(new Integer(TypePayement.CHEQUE))).toString());
            if (tmpcash != null) {
                cash = cash.add(tmpcash);
            }
            if (tmpcheque != null) {
                cheque = cheque.add(tmpcheque);
//getPageHeader().getItemByName("RVField34").setruntimeValue(cheque);
            }
        }
        System.out.println(cash + "  " + cheque);
        getPageHeader().getItemByName("RVscash").setruntimeValue(cash.toString());
        getPageHeader().getItemByName("RVscheque").setruntimeValue(cheque.toString());
    }

    private void transformArrayCaisse(ArrayList array, srcastra.astra.sys.classetransfert.utils.Date datedeb, srcastra.astra.sys.classetransfert.utils.Date datefin) {
        ArrayList tmp2 = new ArrayList();
        if (array != null) {
            datat = new Object[array.size()][];
            for (int i = 0; i < array.size(); i++) {
                Object[] tab = (Object[]) array.get(i);
                if (tab != null) {
                    Object[] tmp = new Object[10];
                    for (int j = 0; j < (tab.length - 1); j++) {
                        if (j == 6) {
                            if (Integer.parseInt(tab[8].toString()) == TypePayement.CARD) {
                                MY_bigDecimal tmpbi = new MY_bigDecimal(tab[6].toString());
                                tab[6] = tmpbi;
                                tmp[8] = tab[6];
                            } else {
                                if (Double.parseDouble(tab[6].toString()) < 0) {
                                    MY_bigDecimal tmpbi = new MY_bigDecimal(tab[6].toString());
                                    tab[6] = tmpbi;
                                    tmp[7] = tab[6];
                                } else {
                                    MY_bigDecimal tmpbi = new MY_bigDecimal(tab[6].toString());
                                    tab[6] = tmpbi;
                                    tmp[6] = tab[6];
                                }
                            }
                        } else if (j == 7) {
                            tmp[9] = tab[7];
                        } else if (j == 1) {
                            tmp[1] = tab[1];
                        } else if (j == 0) {
                            tmp[j] = ((srcastra.astra.sys.classetransfert.utils.Date) tab[0]).toString2();
                        } else
                            tmp[j] = tab[j];
                    }
                    int result = checkDate((srcastra.astra.sys.classetransfert.utils.Date) tab[0], datedeb, datefin);
                    if (result == 1) {
                        if (Integer.parseInt(tab[8].toString()) == TypePayement.CASH) {
                            cash2 = cash2.add((MY_bigDecimal) tab[6]);
                        } else if (Integer.parseInt(tab[8].toString()) == TypePayement.CHEQUE) {
                            cheque2 = cheque2.add((MY_bigDecimal) tab[6]);
                        }
                        if (tmp[8] != null) {
                            cc = cc.add((MY_bigDecimal) tmp[8]);
//cc=MathRound.roundThisToDouble(cc);
                        }
                        if (tmp[7] != null) {
                            sortie = sortie.add((MY_bigDecimal) tmp[7]);
//sortie=MathRound.roundThisToDouble(sortie);
                        }
                        if (tmp[6] != null) {
                            entre = entre.add((MY_bigDecimal) tmp[6]);
//entre=MathRound.roundThisToDouble(entre);
                        }
                        tmp2.add(tmp);
                    } else if (result == 2) {
                        if (Integer.parseInt(tab[8].toString()) == TypePayement.CASH) {
                            cash = cash.add((MY_bigDecimal) tab[6]);
                        } else if (Integer.parseInt(tab[8].toString()) == TypePayement.CHEQUE) {
                            cheque = cheque.add((MY_bigDecimal) tab[6]);
                        }
                    }
//  datat[i]=tmp;
                }

            }
            if (tmp2.size() > 0) {
                datat = new Object[tmp2.size()][];
                for (int i = 0; i < tmp2.size(); i++) {
                    datat[i] = (Object[]) tmp2.get(i);

                }
            }
        }
    }

    private int checkDate(srcastra.astra.sys.classetransfert.utils.Date date, srcastra.astra.sys.classetransfert.utils.Date datedeb, srcastra.astra.sys.classetransfert.utils.Date datefin) {
        if (datedeb.isOpen() || datedeb.isUnknow())
            datedeb = new srcastra.astra.sys.classetransfert.utils.Date("0000-00-00 00:00:00");
        if (datefin.isOpen() || datefin.isUnknow())
            datefin = new srcastra.astra.sys.classetransfert.utils.Date("9999-01-01 00:00:00");
        int i = CalculDate.renvBiggerDate(date, datedeb);
        int j = CalculDate.renvBiggerDate(date, datefin);
        if ((i == 1 || i == 0) && (j == -1 || j == 0))
            return 1;
        else if (i == -1)
            return 2;
        return 0;

    }

    private int searchFournisseur(Object[] elt) {

        boolean trouve = false;

        int i = 0;
        System.out.println("$$size" + v.size());
        while (!trouve && i < v.size()) {

            Object[] lu = (Object[]) v.get(i);

            String comp = lu[0].toString() + "|" + lu[1].toString();
            String comp1 = elt[5].toString() + "|" + elt[7].toString();

            //if(lu[0].toString().equals(elt[5].toString()))
            //{

            if (comp.equals(comp1))
                trouve = true;
            else
                i++;
            //}
            //else
            //i++;   
        }


        if (i < v.size())
            return i;
        else
            return -1;


    }


    private int searchDossier(Object[] elt) {

        boolean trouve = false;

        int i = 0;

        while (!trouve && i < v.size()) {

            Object[] lu = (Object[]) v.get(i);


            if (lu[1].toString().equals(elt[0].toString())) {


                if (lu[2].toString().equals(elt[5].toString()))
                    trouve = true;
                else
                    i++;
            } else
                i++;
        }


        if (i < v.size())
            return i;
        else
            return -1;

    }


    private void addEltAchat(Object[] elt) {
        Object[] out = new Object[14];


        out[0] = elt[1];
        out[1] = elt[0];
        out[2] = elt[5];

        out[3] = new Double(0);
        out[4] = new Double(0);
        out[5] = new Double(0);
        out[6] = new Double(0);
        out[7] = new Double(0);
        out[8] = new Double(0);
        out[9] = new Double(0);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("ACP")) {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[6] = montant;
                    pivot(elt[10].toString(), 2, montant);
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[8] = montant;
                    pivot(elt[10].toString(), 1, montant);
                }

            } else {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    pivot(elt[10].toString(), 2, montant);

                    out[7] = montant;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    pivot(elt[10].toString(), 1, montant);
                    out[9] = montant;


                }
            }

        }

        //if((Double)elt[])


        out[10] = new Double(0);
        out[11] = elt[9];
        out[12] = elt[10];
        out[13] = elt[11];


        v.add(out);
    }

    private void addEltAchatStatF(Object[] elt) {
        Object[] out = new Object[10];

        out[0] = elt[5];
        out[1] = elt[7];

        out[2] = new Double(0);


        out[3] = new Double(0);
        out[4] = new Double(0);
        out[5] = new Double(0);
        out[6] = new Double(0);
        out[7] = new Double(0);
        out[8] = new Double(0);
        out[9] = new Double(0);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("ACP")) {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[5] = montant;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[7] = montant;

                }

            } else {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[6] = montant;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[8] = montant;
                }
            }

        }

        //if((Double)elt[])


        out[9] = new Double(0);

        v.add(out);
    }

    private void addEltVenteStatF(Object[] elt) {
        Object[] out = new Object[10];

        out[0] = elt[5];
        out[1] = elt[7];

        out[2] = new Double(0);
        out[3] = new Double(0);
        out[4] = new Double(0);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("D")) {
                System.out.println(elt[2].toString().equals("0"));
                if (elt[8].toString().equals("0")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());


                    out[2] = montant;
                } else {
                    System.out.println("fact");
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    out[3] = montant;
                }
            } else {
                MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                out[4] = montant;
            }
        }
        //if((Double)elt[])

        out[5] = new Double(0);
        out[6] = new Double(0);
        out[7] = new Double(0);
        out[8] = new Double(0);
        out[9] = new Double(0);

        v.add(out);


    }

    private void pivot(String elt, int type, MY_bigDecimal montant) {
        //System.out.println(elt[10]);
        // Date d=new Date();
        srcastra.astra.sys.classetransfert.utils.Date dd = new srcastra.astra.sys.classetransfert.utils.Date();
        srcastra.astra.sys.classetransfert.utils.Date dd1 = new srcastra.astra.sys.classetransfert.utils.Date();

        System.out.println(dPivot + " " + elt);

        dd.setYear(Integer.valueOf(elt.substring(0, 4)).intValue());
        dd.setMonth(Integer.valueOf(elt.substring(5, 7)).intValue());
        dd.setDay(Integer.valueOf(elt.substring(8, 10)).intValue());

        dd1.setYear(Integer.valueOf(dPivot.substring(6, 10)).intValue());
        dd1.setMonth(Integer.valueOf(dPivot.substring(3, 5)).intValue());
        dd1.setDay(Integer.valueOf(dPivot.substring(0, 2)).intValue());


        int res = CalculDate.renvBiggerDate(dd, dd1);


        switch (type) {
            case 1:
                switch (res) {
                    case 1:
                        t9 = t9.add(montant);

                        break;
                    case 0:
                        t5 = t5.add(montant);
                        break;
                    case -1:
                        t1 = t1.add(montant);
                        break;

                }
                break;
            case 2:
                switch (res) {
                    case 1:
                        t10 = t10.add(montant);

                        break;
                    case 0:
                        t6 = t6.add(montant);
                        break;
                    case -1:
                        t2 = t2.add(montant);
                        break;

                }
                break;


            case 3:
                switch (res) {
                    case 1:
                        t11 = t11.add(montant);

                        break;
                    case 0:
                        t7 = t7.add(montant);
                        break;
                    case -1:
                        t3 = t3.add(montant);
                        break;

                }
                break;

            case 4:

                switch (res) {
                    case 1:
                        t12 = t12.add(montant);

                        break;
                    case 0:
                        t8 = t8.add(montant);
                        break;
                    case -1:
                        t4 = t4.add(montant);
                        break;

                }
                break;

        }
        //CalculDate.getTodayDate()

    }

    private void addEltVente(Object[] elt) {
        Object[] out = new Object[14];


        out[0] = elt[1];
        out[1] = elt[0];
        out[2] = elt[5];

        out[3] = new Double(0);
        out[4] = new Double(0);
        out[5] = new Double(0);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("D")) {
                System.out.println(elt[2].toString().equals("0"));
                if (elt[8].toString().equals("0")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    // apparement le 4 correspond a non facturé
                    pivot(elt[10].toString(), 4, montant);
                    out[3] = montant;


                } else {


                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    System.out.println("fact" + elt[0].toString() + montant);

                    pivot(elt[11].toString(), 3, montant);
                    out[4] = montant;
                }
            } else {
                MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                out[5] = montant;
            }
        }
        //if((Double)elt[])

        out[6] = new Double(0);
        out[7] = new Double(0);
        out[8] = new Double(0);
        out[9] = new Double(0);
        out[10] = new Double(0);
        out[11] = elt[9];
        out[12] = elt[10];
        out[13] = elt[11];


        v.add(out);
    }

    private void modEltVente(Object[] elt, int ind) {
        //vente

        Object[] out = (Object[]) v.elementAt(ind);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("D")) {
                System.out.println(elt[2].toString().equals("0"));
                //VNF
                if (elt[8].toString().equals("0")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[3].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[10].toString(), 4, montant);
                    out[3] = res;
                }
                //vf
                else {
                    System.out.println("fact");
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[4].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[11].toString(), 3, montant);
                    out[4] = res;
                }
            } else {
                //ncv
                MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                MY_bigDecimal montant1 = new MY_bigDecimal(out[5].toString());
                MY_bigDecimal res = montant.add(montant1);
                out[5] = res;
            }

            v.remove(ind);
            v.insertElementAt(out, ind);
        }
    }

    private void modEltVenteStatF(Object[] elt, int ind) {
        //vente

        Object[] out = (Object[]) v.elementAt(ind);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("D")) {
                System.out.println(elt[2].toString().equals("0"));
                if (elt[8].toString().equals("0")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[2].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[2] = res;
                } else {
                    System.out.println("fact");
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[3].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[3] = res;
                }
            } else {
                MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                MY_bigDecimal montant1 = new MY_bigDecimal(out[4].toString());
                MY_bigDecimal res = montant.add(montant1);
                out[4] = res;
            }

            v.remove(ind);
            v.insertElementAt(out, ind);
        }
    }


    private void modEltAchat(Object[] elt, int ind) {
        //vente

        Object[] out = (Object[]) v.elementAt(ind);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("ACP")) {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[6].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[10].toString(), 2, montant);
                    out[6] = res;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[8].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[10].toString(), 1, montant);
                    out[8] = res;

                }

            } else {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[7].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[10].toString(), 2, montant);
                    out[7] = res;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[9].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    pivot(elt[10].toString(), 1, montant);
                    out[9] = res;
                }

            }

            v.remove(ind);
            v.insertElementAt(out, ind);
        }
    }

    private void modEltAchatStatF(Object[] elt, int ind) {
        //vente

        Object[] out = (Object[]) v.elementAt(ind);

        if (!elt[6].toString().equals("2")) {
            if (elt[4].toString().equals("ACP")) {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[5].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[5] = res;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[7].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[7] = res;

                }

            } else {
                if (elt[8].toString().equals("1")) {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[6].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[6] = res;
                } else {
                    MY_bigDecimal montant = new MY_bigDecimal(elt[3].toString());
                    MY_bigDecimal montant1 = new MY_bigDecimal(out[8].toString());
                    MY_bigDecimal res = montant.add(montant1);
                    out[8] = res;
                }

            }

            v.remove(ind);
            v.insertElementAt(out, ind);
        }
    }

    private void transformArrayFourn(ArrayList array) {

        if (array != null)
            datat = new Object[array.size()][];
        String lastFourn = "";

        for (int i = 0; i < array.size(); i++) {


            Object[] tmp = (Object[]) array.get(i);
            Object[] out = new Object[6];

            if (!tmp[0].toString().equals(lastFourn)) {
                out[0] = tmp[0];
                out[1] = tmp[1];
                out[2] = tmp[2];
                out[3] = tmp[3] + " " + tmp[4] + " " + tmp[5];
            } else {
                out[0] = new String("");
                out[1] = new String("");
                out[2] = new String("");
                out[3] = new String("");
            }


            out[4] = tmp[6];
            out[5] = tmp[7];

            datat[i] = (Object[]) out;

            lastFourn = tmp[0].toString();
        }


    }

    private int rechercheAnneePrec(ArrayList array, int cli) {

        int i = 0;
        int index = -1;
        boolean trouve = false;

        while (i < array.size() && !trouve) {
            Object[] tmp = (Object[]) array.get(i);

            if (Integer.valueOf(tmp[0].toString()).intValue() == cli) {
                trouve = true;
                index = i;
            } else
                i++;

        }

        return index;

    }

    private int rechercheAnneePrecF(ArrayList array, int fourn) {

        int i = 0;
        int index = -1;
        boolean trouve = false;

        while (i < array.size() && !trouve) {
            Object[] tmp = (Object[]) array.get(i);
            if (tmp[7] != null) {
                if (Integer.valueOf(tmp[7].toString()).intValue() == fourn) {
                    trouve = true;
                    index = i;
                } else
                    i++;
            } else i++;
        }

        return index;

    }

    private void transformArrayEmbDeb(ArrayList array) {

        if (array != null) {
            datat = new Object[array.size()][];


            for (int i = 0; i < array.size(); i++) {


                Object[] tmp = (Object[]) array.get(i);


                datat[i] = (Object[]) tmp;

            }
        }
    }


    private void transformArrayChiffreAffaire(ArrayList array, ArrayList array1) {
        //ArrayList temp=new ArrayList();

        if (array != null) {
            datat = new Object[array.size()][];

            for (int i = 0; i < array.size(); i++) {
                Object[] tmp = (Object[]) array.get(i);

                tmp[1] = tmp[1].toString() + " " + tmp[2].toString() + " " + tmp[3].toString() + " " + tmp[4].toString();

                int id = rechercheAnneePrec(array1, Integer.valueOf(tmp[0].toString()).intValue());

                MY_bigDecimal tot = new MY_bigDecimal(tmp[5].toString());
                this.totalvente = this.totalvente.add(tot);


                tot = new MY_bigDecimal(tmp[6].toString());
                this.totalfacture = this.totalfacture.add(tot);

                tot = new MY_bigDecimal(tmp[7].toString());
                this.totaldossier = this.totaldossier.add(tot);


                if (id != -1) {
                    Object[] anneePrec = (Object[]) array1.get(id);

                    tmp[8] = anneePrec[5];
                    tmp[9] = anneePrec[7];

                    double pourcent = Double.valueOf(tmp[5].toString()).doubleValue();
                    pourcent = pourcent - Double.valueOf(anneePrec[5].toString()).doubleValue();

                    double différence = pourcent;

                    pourcent = pourcent / Double.valueOf(tmp[5].toString()).doubleValue();
                    pourcent = pourcent * 100;
                    //Math.rou
                    pourcent = Math.round(pourcent * 100);
                    pourcent /= 100;
                    différence = Math.round(différence * 100);
                    différence /= 100;


                    tmp[10] = new Double(différence);
                    tmp[11] = new String(Double.toString(pourcent) + "%");

                    tot = new MY_bigDecimal(Double.toString(pourcent));
                    this.totalPourcent = this.totalPourcent.add(tot);


                } else {
                    tmp[8] = new Double(0);
                    tmp[11] = "";
                    tmp[9] = new Integer(0);
                    tmp[10] = new Double(0);

                }

                tot = new MY_bigDecimal(tmp[8].toString());
                this.totalventeprec = this.totalventeprec.add(tot);


                tot = new MY_bigDecimal(tmp[9].toString());
                this.totaldossierprec = this.totaldossierprec.add(tot);

                tot = new MY_bigDecimal(tmp[10].toString());
                this.totaldifference = this.totaldifference.add(tot);


                tmp[0] = tmp[1];


                for (int ii = 5; ii <= 11; ii++) {
                    tmp[ii - 4] = tmp[ii];

                }

                double tempo = Double.valueOf(tmp[1].toString()).doubleValue();
                tempo = Math.round(tempo * 100);
                tempo /= 100;
                tmp[1] = new Double(tempo);

                tempo = Double.valueOf(tmp[2].toString()).doubleValue();
                tempo = Math.round(tempo * 100);
                tempo /= 100;
                tmp[2] = new Double(tempo);

                datat[i] = (Object[]) tmp;

                // Arrays.sort(datat,)

            }

            Comparator DossierComparator = new Comparator() {

                public int compare(Object o1, Object o2) {

                    Object[] obj1 = (Object[]) o1;

                    Object[] obj2 = (Object[]) o2;

                    double a = Double.valueOf(obj1[1].toString()).doubleValue();
                    double b = Double.valueOf(obj2[1].toString()).doubleValue();

                    return Double.compare(b, a);

                    //if(obj1[1].toString().equals(obj2[1].toString()))
                    //    return(obj1[1].toString().compareTo(obj2[1].toString()));
                    //else
                    //  return(obj1[1].toString().compareTo(obj2[1].toString()));
                }
            };

            Arrays.sort(datat, DossierComparator);


        }


    }

    private void transformArrayChiffreAffaireFournisseur(ArrayList array, ArrayList array1) {
        //ArrayList temp=new ArrayList();


        datat = new Object[array.size()][];

        for (int i = 0; i < array.size(); i++) {
            Object[] tmp = (Object[]) array.get(i);

            if (tmp[1].toString().equals("")) {
                tmp[1] = "----->";
                double somme = Double.valueOf(tmp[2].toString()).doubleValue();
                somme = Math.round(somme * 100);
                somme = somme / 100;

                tmp[2] = new Double(somme);
                tmp[3] = new String("");
                tmp[4] = new String("");
                tmp[5] = new String("");

                tot = new MY_bigDecimal(Double.toString(somme));
                this.totalvente = this.totalvente.add(tot);

            } else {
                int id = rechercheAnneePrecF(array1, Integer.valueOf(tmp[7].toString()).intValue());
                double différence = 0;
                double pourcent = 0;
                if (id != -1) {
                    Object[] elt = (Object[]) array1.get(id);

                    tmp[3] = elt[2];

                    tot = new MY_bigDecimal(elt[2].toString());
                    this.totalventeprec = this.totalventeprec.add(tot);


                    if (Double.valueOf(elt[2].toString()).doubleValue() != 0) {
                        pourcent = Double.valueOf(tmp[2].toString()).doubleValue();


                        pourcent = pourcent - Double.valueOf(elt[2].toString()).doubleValue();

                        différence = pourcent;

                        pourcent = pourcent / Double.valueOf(tmp[2].toString()).doubleValue();
                        pourcent = pourcent * 100;


                        pourcent = Math.round(pourcent * 100);


                        pourcent /= 100;

                        tot = new MY_bigDecimal(Double.toString(pourcent));
                        this.totalPourcent = this.totalPourcent.add(tot);

                        différence = Math.round(différence * 100);
                        différence /= 100;

                        tot = new MY_bigDecimal(Double.toString(différence));
                        this.totaldifference = this.totaldifference.add(tot);
                        tmp[4] = new Double(différence);
                        tmp[5] = new String(Double.toString(pourcent) + "%");
                    } else {
                        tmp[4] = new Integer(0);
                        tmp[5] = new String("");

                    }


                } else {

                    tmp[3] = new Double(0);
                    tmp[4] = new Integer(0);
                    tmp[5] = new String("");

                }

            }

            //  for  (int j=1;j<=3;j++)
            //  tmp[j-1]=tmp[j];

            datat[i] = (Object[]) tmp;
        }


    }

    private void transformArrayJourn(ArrayList array) {

        if (array != null) {
            datat = new Object[array.size()][];

            for (int i = 0; i < array.size(); i++) {

                Object[] tmp = (Object[]) array.get(i);

                if (tmp[8].toString().equals("0"))

                    tmp[8] = new String("");
                else
                    tmp[8] = new String("*");


                MY_bigDecimal totap = new MY_bigDecimal(tmp[5].toString());
                totapayer = totapayer.add(totap);


                MY_bigDecimal totb = new MY_bigDecimal(tmp[6].toString());
                totbase = totbase.add(totb);

                MY_bigDecimal tott = new MY_bigDecimal(tmp[7].toString());
                tottva = tottva.add(tott);

                datat[i] = (Object[]) tmp;


            }

        }


    }

    private void transformArrayStatFourn(ArrayList array, ArrayList array1) {
        v = new Vector();
        for (int i = 0; i < array.size(); i++) {
            Object[] o = (Object[]) array.get(i);

            int ret = searchFournisseur(o);

            System.out.println(ret);

            if (ret == -1)
                addEltVenteStatF(o);
            else
                modEltVenteStatF(o, ret);

            //if(ret==-1)
            //addElt(o);
            //else
            //modEltVente(o,ret);
        }

        for (int i = 0; i < array1.size(); i++) {
            Object[] o = (Object[]) array1.get(i);

            int ret = searchFournisseur(o);

            if (ret == -1)
                addEltAchatStatF(o);
            else
                modEltAchatStatF(o, ret);
        }

        datat = new Object[v.size()][];


        Comparator DossierComparator = new Comparator() {

            public int compare(Object o1, Object o2) {

                Object[] obj1 = (Object[]) o1;

                Object[] obj2 = (Object[]) o2;

                if (obj1[0].toString().equals(obj2[0].toString()))
                    return (obj1[1].toString().compareTo(obj2[1].toString()));
                else
                    return (obj1[0].toString().compareTo(obj2[0].toString()));
            }
        };

        Collections.sort(v, DossierComparator);

        String lastFourn = "";


        for (int i = 0; i < v.size(); i++) {

            if (((Object[]) v.get(i))[0].toString().equals(lastFourn)) {
                //((Object[])v.get(i))[1]="";
                ((Object[]) v.get(i))[0] = "";
            } else {
                lastFourn = (String) ((Object[]) v.get(i))[0];
            }


            MY_bigDecimal totvnf = new MY_bigDecimal(((Object[]) v.get(i))[2].toString());
            totvnf1 = totvnf1.add(totvnf);
            MY_bigDecimal totvf = new MY_bigDecimal(((Object[]) v.get(i))[3].toString());
            totvf1 = totvf1.add(totvf);
            MY_bigDecimal totncv = new MY_bigDecimal(((Object[]) v.get(i))[4].toString());
            totncv1 = totncv1.add(totncv);
            MY_bigDecimal tota = new MY_bigDecimal(((Object[]) v.get(i))[5].toString());
            totachat1 = totachat1.add(tota);
            MY_bigDecimal totnca = new MY_bigDecimal(((Object[]) v.get(i))[6].toString());
            totachatnc1 = totachatnc1.add(totnca);
            MY_bigDecimal totanf = new MY_bigDecimal(((Object[]) v.get(i))[7].toString());
            totachatnf1 = totachatnf1.add(totanf);
            MY_bigDecimal totncanf = new MY_bigDecimal(((Object[]) v.get(i))[8].toString());
            totachatncnf1 = totachatncnf1.add(totncanf);


            double totv = totvnf.doubleValue() + totvf.doubleValue() + totncv.doubleValue();


            double rent = 0;

            if (totv != 0) {

                double totach = tota.doubleValue() + totnca.doubleValue() + totanf.doubleValue() + totncanf.doubleValue();

                rent = 100 - ((totach / totv) * 100);

            }

            MY_bigDecimal r = new MY_bigDecimal(String.valueOf(rent));


            ((Object[]) v.get(i))[9] = r + "%";

            datat[i] = (Object[]) v.elementAt(i);
        }


    }


    private void transformArrayControle(ArrayList array, ArrayList array1, DossierRmiInterface dossier, int urcleunik, String pivot) {
        //Map m=new HashMap();

        // Ve.
        this.dPivot = pivot;
        ArrayList detail = new ArrayList();
        ArrayList contenu = new ArrayList();
        //drcleunikRent = new ArrayList();

        v = new Vector();
        String drcleunik = "";
        for (int i = 0; i < array.size(); i++) {
            Object[] o = (Object[]) array.get(i);


            int ret = searchDossier(o);

            System.out.println(ret);

            if (ret == -1)
                addEltVente(o);
            else
                modEltVente(o, ret);

            //if(ret==-1)
            //addElt(o);
            //else
            //modEltVente(o,ret);
        }
        System.out.println(array1.size());
        for (int i = 0; i < array1.size(); i++) {
            Object[] o = (Object[]) array1.get(i);

            int ret = searchDossier(o);
            if (ret == -1)
                addEltAchat(o);
            else
                modEltAchat(o, ret);


        }


        datat = new Object[v.size()][];


        Comparator DossierComparator = new Comparator() {

            public int compare(Object o1, Object o2) {

                Object[] obj1 = (Object[]) o1;

                Object[] obj2 = (Object[]) o2;

                if (obj1[0].toString().equals(obj2[0].toString()))
                    return (obj1[1].toString().compareTo(obj2[1].toString()));
                else
                    return (obj1[0].toString().compareTo(obj2[0].toString()));
            }
        };


        Collections.sort(v, DossierComparator);

        String lastDoss = "";
        boolean rupt = false;
        boolean first = true;


        for (int i = 0; i < v.size(); i++) {
            if (((Object[]) v.get(i))[1].toString().equals(lastDoss)) {
                ((Object[]) v.get(i))[1] = "";
                ((Object[]) v.get(i))[0] = "";
            } else {
                lastDoss = (String) ((Object[]) v.get(i))[1];
                drcleunik = ((Object[]) v.get(i))[11].toString();
                //drcleunikRent.add(drcleunik);
                try {


                    if (!first) {
                        for (int q = 0; q < detail.size(); q++) {
                            Object[] obj1 = (Object[]) detail.get(q);
                            Object[] tmp = new Object[12];
                            tmp[0] = "";
                            tmp[1] = obj1[1] + " " + obj1[2];
                            tmp[3] = "";
                            tmp[2] = obj1[5];
                            tmp[4] = "";
                            tmp[5] = "";
                            tmp[6] = "";
                            tmp[7] = "";
                            tmp[8] = "";
                            tmp[9] = "";
                            tmp[10] = "";
                            contenu.add(tmp);

                            //System.out.println(obj1[1]+" "+obj1[2]+" "+obj1[3]+" "+obj1[4]+" "+obj1[5]);


                        }
                    }
                    detail = dossier.getListing(urcleunik, Integer.valueOf(drcleunik).intValue());

                } catch (Exception e) {
                }

                //System.out.println("DRCLEUNIK::::::"+drcleunik);
            }


            MY_bigDecimal totvnf = new MY_bigDecimal(((Object[]) v.get(i))[3].toString());
            totvnf1 = totvnf1.add(totvnf);
            MY_bigDecimal totvf = new MY_bigDecimal(((Object[]) v.get(i))[4].toString());
            totvf1 = totvf1.add(totvf);
            MY_bigDecimal totncv = new MY_bigDecimal(((Object[]) v.get(i))[5].toString());
            totncv1 = totncv1.add(totncv);
            MY_bigDecimal tota = new MY_bigDecimal(((Object[]) v.get(i))[6].toString());
            totachat1 = totachat1.add(tota);
            MY_bigDecimal totnca = new MY_bigDecimal(((Object[]) v.get(i))[7].toString());
            totachatnc1 = totachatnc1.add(totnca);
            System.out.println("DOssier " + lastDoss + ((Object[]) v.get(i))[8]);
            MY_bigDecimal totanf = new MY_bigDecimal(((Object[]) v.get(i))[8].toString());
            totachatnf1 = totachatnf1.add(totanf);
            MY_bigDecimal totncanf = new MY_bigDecimal(((Object[]) v.get(i))[9].toString());
            totachatncnf1 = totachatncnf1.add(totncanf);


            double totv = totvnf.doubleValue() + totvf.doubleValue() + totncv.doubleValue();


            double rent = 0;

            if (totv != 0) {

                double totach = tota.doubleValue() + totnca.doubleValue() + totanf.doubleValue() + totncanf.doubleValue();

                rent = 100 - ((totach / totv) * 100);

            }

            MY_bigDecimal r = new MY_bigDecimal(String.valueOf(rent));


            ((Object[]) v.get(i))[10] = r + "%";

            contenu.add((Object[]) v.elementAt(i));


            first = false;

            //datat[i]=(Object[])v.elementAt(i);
        }
        for (int q = 0; q < detail.size(); q++) {
            Object[] obj1 = (Object[]) detail.get(q);
            Object[] tmp = new Object[12];
            tmp[0] = "";
            tmp[1] = obj1[1] + " " + obj1[2];
            tmp[3] = "";
            tmp[2] = obj1[5];
            tmp[4] = "";
            tmp[5] = "";
            tmp[6] = "";
            tmp[7] = "";
            tmp[8] = "";
            tmp[9] = "";
            tmp[10] = "";
            contenu.add(tmp);
        }
        datat = new Object[contenu.size()][];

        for (int i = 0; i < contenu.size(); i++)
            datat[i] = (Object[]) contenu.get(i);
        //datat=((Object[][])m.values().toArray());

        //for(int i=0;i<v.size();i++)
        //    System.out.println((((Object[]) v.get(i))[1])+" "+(((Object[]) v.get(i))[2])+" "+(((Object[]) v.get(i))[3])+" "+(((Object[]) v.get(i))[4])+" "+(((Object[]) v.get(i))[6])+" "+" "+(((Object[]) v.get(i))[7]));

        //datat=((Object[][])m.values().toArray());

        /*   for (int i=0;i < array.size();i++) {




           tab=(Object[]) array.get(i);
           Object temp=tab[0];
           tab[0]=tab[1];
           tab[1]=temp;

           temp=tab[2];
           tab[2]=tab[5];
           tab[5]=temp;
           datat[i]=tab;
           //j++;
       }


        for (int i=0;i < array1.size();i++) {




           tab=(Object[]) array1.get(i);
           Object temp=tab[0];
           tab[0]=tab[1];
           tab[1]=temp;

           temp=tab[2];
           tab[2]=tab[5];
           tab[5]=temp;
           // datat[j+i]=tab;
       } */

        //    System.out.println(m.toString());

        //    Collection c=m.values();

        //     datat=c.toArray();

    }


    private void transformArrayAchat(ArrayList array) {

        if (array != null) {

            datat = new Object[array.size()][];


            for (int i = 0; i < array.size(); i++) {
                Object[] tmp = new Object[9];

                Object[] tab = (Object[]) array.get(i);


                for (int j = 0; j <= 8; j++) {
                    if (j == 7) {
                        if (Long.valueOf(tab[j].toString()).longValue() == 1)
                            tmp[j] = new String("OK     -->");
                        else if (Long.valueOf(tab[j].toString()).longValue() == 2)
                            tmp[j] = new String("EXIST  -->");
                        else
                            tmp[j] = new String("?      -->");

                    } else if (j == 8) {
                        if (tab[j + 1] == null)
                            tab[j + 1] = new String("");

                        if (tab[j] == null)
                            tab[j] = new String("");

                        tmp[j] = new String(tab[j].toString() + " [" + tab[j + 1].toString() + "] " + tab[j + 3].toString() ) ;

                    } else
                        tmp[j] = tab[j];


                }


                datat[i] = tmp;


            }

        }


    }

    private void transformArray(ArrayList array, String soldee, String facture) {

        int jj = -1;

        Object datat1[][] = null;


        if (array != null) {
            datat1 = new Object[array.size()][];
            for (int i = 0; i < array.size(); i++) {
                Object[] tab = (Object[]) array.get(i);

                boolean suite = true;

                if (tab[1].toString().length() > 8) {
                    if (tab[1].toString().charAt(8) == 'F' || tab[1].toString().charAt(8) == 'N')
                        suite = false;

                    if (tab[1].toString().charAt(8) == 'G')
                        if (tab[1].toString().charAt(9) == '0')
                            if (!facture.equals("TOUS"))
                                suite = false;

                }


                if (suite) {
                    MY_bigDecimal montant = new MY_bigDecimal(tab[6].toString());
                    tab[6] = montant;
                    tot = tot.add(montant);
//tot=tot+Double.parseDouble(tab[6].toString());
                    MY_bigDecimal payetmp = new MY_bigDecimal(tab[7].toString());
                    tab[7] = payetmp;
                    paye = paye.add(payetmp);
//paye=paye+Double.parseDouble(tab[7].toString());
//solded=solded+Double.parseDouble(tab[8].toString());
                    MY_bigDecimal soldetmp = new MY_bigDecimal(tab[8].toString());
                    tab[8] = soldetmp;
                    solded = solded.add(soldetmp);
// tab[7]=payetmp;
// paye=paye.add(payetmp);
//tot=MathRound.roundThisToDouble(tot);
// paye=MathRound.roundThisToDouble(paye);
//solded=MathRound.roundThisToDouble(solded);
                    if (tab != null) {
                        Object[] tmp = new Object[14];
                        for (int j = 1; j < tab.length; j++) {
                            if (j == 11) {
                                if (tab[11].toString() == "1" && tab[12].toString() == "0" && tab[13].toString() == "0") {
                                    tmp[j - 1] = "B";
                                } else if (tab[12].toString() == "1" && tab[13].toString() == "0") {
                                    tmp[j - 1] = "F";
                                } else if (tab[13].toString() == "1") {
                                    tmp[j - 1] = "A";
                                } else
                                    tmp[j - 1] = " ";
                            } else if (j == 12 || j == 13)
                                ;
                            else if (j == 14)
                                tmp[11] = tab[j];
                            else
                                tmp[j - 1] = tab[j];
                        }

                        // System.out.println(soldee+" "+Double.toString(soldetmp.doubleValue()));


                        if (soldee.equals("TOUS")) {
                            if (tmp != null) {
                                //jj++;

                                if (facture.equals("TOUS")) {
                                    jj++;

                                    datat1[jj] = tmp;
                                } else {
                                    double val = Double.valueOf(tmp[5].toString()).doubleValue();

                                    if (val > 0) {
                                        jj++;

                                        datat1[jj] = tmp;

                                    }
                                }

                            }
                        } else {
                            System.out.println(Math.round(soldetmp.doubleValue() * 100) / 100);
                            if (Math.round(soldetmp.doubleValue() * 100) / 100 != 0) {
                                if (tmp != null) {

                                    if (facture.equals("TOUS")) {
                                        double val = Double.valueOf(tmp[5].toString()).doubleValue();

                                        if (val > 0) {
                                            jj++;

                                            datat1[jj] = tmp;
                                        }
                                    } else {
                                        double val = Double.valueOf(tmp[5].toString()).doubleValue();

                                        if (val > 0) {
                                            jj++;

                                            datat1[jj] = tmp;
                                        }
                                    }

                                }

                            }/*else {
                               
                                double val = Double.valueOf(tmp[5].toString()).doubleValue();
                                    
                                    if(val>0)
                                    {
                                       jj++;
                                    
                                       datat1[jj] = tmp;
                                    }
                           }  */
                        }


                    }
                }

            }
        }

        datat = new Object[jj + 1][];

        for (int j = 0; j <= jj; j++)
            datat[j] = datat1[j];

    }

    private void setHeaderEmbDeb(boolean emb) {
        getPageHeader().getItemByName("pass").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("pass"));
        getPageHeader().getItemByName("voucher").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("voucher"));
        getPageHeader().getItemByName("FROM").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("from"));
        getPageHeader().getItemByName("TO").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("to"));

        if (emb)
            getPageHeader().getItemByName("Titre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("titre1"));
        else
            getPageHeader().getItemByName("Titre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("titre2"));
    }

    private void setHeaderChiffreAffaire(String datedepbeg, String datedebend, String entite, String now, String prec) {
        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVCreationD").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVCreationF").setruntimeValue(datedebend);


        if (entite.equals(""))
            entite = "All";

        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);
        getPageHeader().getItemByName("Liste_cha").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_cha"));
        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));

    }

    private void setHeaderChiffreAffaireF(String datedepbeg, String datedebend, String entite, String now, String prec) {
        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVCreationD").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVCreationF").setruntimeValue(datedebend);


        if (entite.equals(""))
            entite = "All";

        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);
        getPageHeader().getItemByName("Liste_chaf").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_chaf"));
        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));
        getPageHeader().getItemByName("FOURNISSEUR").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Fournisseur"));
        getPageHeader().getItemByName("GROUPEPRODUIT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("gp"));
    }

    private void setHeaderJournal(String datedepbeg, String datedebend, String entite, String type) {
        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVCreationD").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVCreationF").setruntimeValue(datedebend);

        getPageHeader().getItemByName("TOTNCV").setruntimeValue("");

        if (entite.equals(""))
            entite = "All";

        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);
        getPageHeader().getItemByName("Liste_journaux").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_journ"));
        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));

        getPageHeader().getItemByName("Type").setruntimeValue(type);

        getPageHeader().getItemByName("Date_Mouvement").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("dmouv"));
        getPageHeader().getItemByName("DateDep").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Datedep2"));


        getPageHeader().getItemByName("NumDoss").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("NumDos2"));
        getPageHeader().getItemByName("DateMouv").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("dmouv"));
        getPageHeader().getItemByName("NumPiece").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("NumPiece"));
        getPageHeader().getItemByName("CLIENT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Client"));
        getPageHeader().getItemByName("ValPaye").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("ValPaye"));
        getPageHeader().getItemByName("ValBase").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("ValBase"));
        getPageHeader().getItemByName("ValTVA").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("ValTVA"));
    }


    private void setHeaderStatFourn(String datedepbeg, String datedebend, String entite, String datecreationd, String datecreationf) {

        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVdepartb").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVdeparte").setruntimeValue(datedebend);

        getPageHeader().getItemByName("RVCreationD").setruntimeValue(datecreationd);
        getPageHeader().getItemByName("RVCreationF").setruntimeValue(datecreationf);

        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);

        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));
        getPageHeader().getItemByName("Liste_controle").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_statfourn"));
        getPageHeader().getItemByName("Date_dep").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_dep"));
        getPageHeader().getItemByName("Date_cre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_cre"));
        getPageHeader().getItemByName("RVField391").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tot"));
        getPageHeader().getItemByName("RVField39").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tot"));
        getPageHeader().getItemByName("NumDos2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Fournisseur"));
        getPageHeader().getItemByName("fournisseur2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("gp"));

        getPageHeader().getItemByName("TOTVNF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvnf"));
        getPageHeader().getItemByName("TOTVF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvf"));
        getPageHeader().getItemByName("TOTNCV").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totncv"));
        getPageHeader().getItemByName("TOTA").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tota"));
        getPageHeader().getItemByName("TOTNCA").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totnca"));
        getPageHeader().getItemByName("TOTANF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totanf"));
        getPageHeader().getItemByName("TOTNCANF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totncanf"));
        getPageHeader().getItemByName("RENT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("rent"));


    }

    private void setHeaderControle(String datedepbeg, String datedebend, String entite, String datecreationd, String datecreationf) {
        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVdepartb").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVdeparte").setruntimeValue(datedebend);

        getPageHeader().getItemByName("RVCreationD").setruntimeValue(datecreationd);
        getPageHeader().getItemByName("RVCreationF").setruntimeValue(datecreationf);


        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);

        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));
        getPageHeader().getItemByName("Liste_controle").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_controle"));
        getPageHeader().getItemByName("Date_dep").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_dep"));
        getPageHeader().getItemByName("Date_cre").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_cre"));

        getPageHeader().getItemByName("RVField391").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tot"));
        getPageHeader().getItemByName("RVField39").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tot"));
        getPageHeader().getItemByName("Datedep2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Datedep2"));
        getPageHeader().getItemByName("NumDos2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("NumDos2"));
        getPageHeader().getItemByName("fournisseur2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Fournisseur"));


        getPageHeader().getItemByName("TOTVNF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvnf"));
        getPageHeader().getItemByName("TOTVF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totvf"));
        getPageHeader().getItemByName("TOTNCV").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totncv"));
        getPageHeader().getItemByName("TOTA").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("tota"));
        getPageHeader().getItemByName("TOTNCA").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totnca"));
        getPageHeader().getItemByName("TOTANF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totanf"));
        getPageHeader().getItemByName("TOTNCANF").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("totncanf"));
        getPageHeader().getItemByName("RENT").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("rent"));


    }

    private void setHeader(String datedepbeg, String datedebend, String creationbeg, String creationend, String solde, String facture, String clientc,
                           String clientf, String fournisseur, String passager, String destination, String po, String numdos, String entite,
                           String groupprod, String produit, String bdc, String annuler) {

        if (solde.equals("TOUS"))
            solde = "ALL";
        else
            solde = "N";

        if (bdc.equals("TOUS"))
            bdc = "ALL";
        else
            bdc = "N";

        if (facture.equals("TOUS"))
            facture = "ALL";
        else
            facture = "N";

        if (entite.length() == 0)
            entite = "/";

        getPageHeader().getItemByName("RVcreationd").setruntimeValue(creationbeg);
        getPageHeader().getItemByName("reportdate").setruntimeValue(CalculDate.getTodayDate().toString2());
        getPageHeader().getItemByName("RVcreatione").setruntimeValue(creationend);
        getPageHeader().getItemByName("RVdepartb").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVdeparte").setruntimeValue(datedebend);
        getPageHeader().getItemByName("RVEntite").setruntimeValue(entite);
        getPageHeader().getItemByName("RVCli").setruntimeValue(clientc);
        getPageHeader().getItemByName("RVClifact").setruntimeValue(clientf);
        getPageHeader().getItemByName("RVFourn").setruntimeValue(fournisseur);
        getPageHeader().getItemByName("RVGrprod").setruntimeValue(groupprod);
        getPageHeader().getItemByName("RVTypeprod").setruntimeValue(produit);
        getPageHeader().getItemByName("RVPassager").setruntimeValue(passager);
        getPageHeader().getItemByName("RVPO").setruntimeValue(po);
        getPageHeader().getItemByName("RVFacture").setruntimeValue(facture);
        getPageHeader().getItemByName("RVAnnul").setruntimeValue(annuler);
        getPageHeader().getItemByName("RVSolde").setruntimeValue(solde);
        getPageHeader().getItemByName("RVBdc").setruntimeValue(bdc);
        getPageHeader().getItemByName("RVDestin").setruntimeValue(destination);
        getPageHeader().getItemByName("RVNumdos").setruntimeValue(numdos);

        getPageHeader().getItemByName("Liste_dossiers").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Liste_dossiers"));
        getPageHeader().getItemByName("Criteres_recherche").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Criteres_recherche"));
        getPageHeader().getItemByName("Date_crea").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_crea"));
        getPageHeader().getItemByName("Date_dep").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Date_dep"));
        getPageHeader().getItemByName("Fournisseur").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Fournisseur"));
        getPageHeader().getItemByName("Passager").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Pass"));
        getPageHeader().getItemByName("Destination").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Destination"));
        getPageHeader().getItemByName("Num_dossier").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Num_dossier"));
        getPageHeader().getItemByName("N_Solde").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("N_Solde"));
        getPageHeader().getItemByName("N_Bdc").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("N_Bdc"));
        getPageHeader().getItemByName("Grprod").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Grprod"));
        getPageHeader().getItemByName("Bureau").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Bureau"));
        getPageHeader().getItemByName("Clientcon").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Clientcon"));
        getPageHeader().getItemByName("Clientfact").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Clientfact"));
        getPageHeader().getItemByName("Typeprod").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Typeprod"));
        getPageHeader().getItemByName("PO").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("PO"));
        getPageHeader().getItemByName("Annul").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Annul"));
        getPageHeader().getItemByName("NumDos2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("NumDos2"));
        getPageHeader().getItemByName("Client").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Client"));
        getPageHeader().getItemByName("Datecrea2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Datecrea2"));
        getPageHeader().getItemByName("Datedep2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Datedep2"));
        getPageHeader().getItemByName("Pass2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Pass2"));
        getPageHeader().getItemByName("Totdoss").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Totdoss"));
        getPageHeader().getItemByName("Totpay").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Totpay"));
        getPageHeader().getItemByName("Solde").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Solde"));
        getPageHeader().getItemByName("Groupprod2").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Groupprod2"));
        getPageHeader().getItemByName("Vendeur").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("Vendeur"));
        getPageHeader().getItemByName("N_Fact").setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/edition/liste").getString("N_Fact"));
    }

    private void setHeaderCaisse(String datedepbeg, String datedebend, String user, String entite) {
        getPageHeader().getItemByName("RVBureau").setruntimeValue(entite);
        getPageHeader().getItemByName("RVUser").setruntimeValue(user);
        getPageHeader().getItemByName("RVdebut").setruntimeValue(datedepbeg);
        getPageHeader().getItemByName("RVFin").setruntimeValue(datedebend);
    }

    private String dPivot;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

}


