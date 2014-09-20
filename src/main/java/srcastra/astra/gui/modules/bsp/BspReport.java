/*
 * bspReport.java
 *
 * Created on 7 juin 2004, 14:17
 */

package srcastra.astra.gui.modules.bsp;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import com.java4less.rreport.*;
import srcastra.JCFactureTest;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;
import srcastra.astra.gui.sys.ErreurScreenLibrary;

/**
 * @author Administrateur
 */
public class BspReport {
    astrainterface serveur;
    Loginusers_T user;
    RReportJ2 report;
    java.awt.Frame m_frame = new java.awt.Frame();
    MY_bigDecimal GrossCashExcluding;
    MY_bigDecimal GrossCccfExcluding;
    MY_bigDecimal generalCom;
    MY_bigDecimal TotalTaxesCash;
    MY_bigDecimal TotalTaxesCccf;
    MY_bigDecimal TotalRefundTaxesCash;
    MY_bigDecimal TotalRefundTaxesCccf;
    MY_bigDecimal TotalCancelFeeCash;
    MY_bigDecimal TotalCancelFeeCccf;
    MY_bigDecimal RefundCccf;
    MY_bigDecimal RefundCash;
    MY_bigDecimal RefundCancel;
    MY_bigDecimal commission;
    MY_bigDecimal commissionRefund;
    MY_bigDecimal netToPay;
    srcastra.astra.sys.classetransfert.utils.Date startDate;
    srcastra.astra.sys.classetransfert.utils.Date endDate;
    String[] title = new String[]{"airline", "doc", "date", "facecash", "facecredit", "taxe", "comrate", "comvalue", "vat", "cancelfee", "net", "remark", "dos"};

    /**
     * Creates a new instance of bspReport
     */
    public BspReport(astrainterface serveur, Loginusers_T user, java.awt.Frame frame, srcastra.astra.sys.classetransfert.utils.Date startDate, srcastra.astra.sys.classetransfert.utils.Date endDate) {
        this.serveur = serveur;
        this.user = user;
        m_frame = frame;
        this.startDate = startDate;
        this.endDate = endDate;
        preInit();
        GrossCashExcluding = new MY_bigDecimal("0.00");
        GrossCccfExcluding = new MY_bigDecimal("0.00");
        TotalTaxesCash = new MY_bigDecimal("0.00");
        TotalTaxesCccf = new MY_bigDecimal("0.00");
        TotalCancelFeeCash = new MY_bigDecimal("0.00");
        TotalCancelFeeCccf = new MY_bigDecimal("0.00");
        RefundCccf = new MY_bigDecimal("0.00");
        RefundCash = new MY_bigDecimal("0.00");
        commission = new MY_bigDecimal("0.00");
        commissionRefund = new MY_bigDecimal("0.00");
        generalCom = new MY_bigDecimal("0.00");
        RefundCancel = new MY_bigDecimal("0.00");
        TotalRefundTaxesCash = new MY_bigDecimal("0.00");
        TotalRefundTaxesCccf = new MY_bigDecimal("0.00");
        netToPay = new MY_bigDecimal("0.00");
        try {
            Hashtable hash = serveur.renvListRmiObject(user.getUrcleunik()).getBspt(user.getUrcleunik(), startDate, endDate);
            ArrayList auto = (ArrayList) hash.get("auto");
            ArrayList mco = (ArrayList) hash.get("mco");
            ArrayList refund = (ArrayList) hash.get("refund");
            if (auto != null) {
                normalTicket(auto);
            }
            if (mco != null) {
                mcoTicket(mco);
            }
            if (refund != null) {
                refundTicket(refund);
            }
            finalizeReport();

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn) {
            ErreurScreenLibrary.displayErreur(m_frame, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn, user);
        }
        catch (java.rmi.RemoteException en) {
            ErreurScreenLibrary.displayErreur(m_frame, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, en, user);
        }
    }

    private void normalTicket(ArrayList auto) {
        MY_bigDecimal faceCash = new MY_bigDecimal("0.00");
        MY_bigDecimal faceCredit = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCash = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCccf = new MY_bigDecimal("0.00");
        MY_bigDecimal com = new MY_bigDecimal("0.00");
        MY_bigDecimal netToPaid = new MY_bigDecimal("0.00");
        MY_bigDecimal cancelFee = new MY_bigDecimal("0.00");


        Object[][] data = new Object[auto.size()][14];
        for (int i = 0; i < auto.size(); i++) {
            BspObject bspobj = new BspObject((Object[]) auto.get(i));
            taxeCash = bspobj.addTaxeCash(taxeCash);
            taxeCccf = bspobj.addTaxeCredit(taxeCccf);
            com = com.add(bspobj.getCommission());
            //GrossCashExcluding=bspobj.addFacialCash(GrossCashExcluding);

            netToPaid = netToPaid.add(bspobj.getNetToPay());
            faceCash = bspobj.addFacialCash(faceCash);
            faceCredit = bspobj.addFacialCredit(faceCredit);
            Object[] tmp = bspobj.getString();
            String affiche = "";
            for (int j = 0; j < tmp.length; j++) {
                data[i][j] = tmp[j];

            }
            System.out.println(affiche);
            data[i] = tmp;

        }
        GrossCashExcluding = GrossCashExcluding.add(faceCash);
        GrossCccfExcluding = GrossCccfExcluding.add(faceCredit);
        generalCom = generalCom.add(com);
        TotalTaxesCash = TotalTaxesCash.add(taxeCash);
        TotalTaxesCccf = TotalTaxesCccf.add(taxeCccf);
        netToPay = netToPay.add(netToPaid);
        report.getAreaByName("ATBDetail").setDataSource(new RArraySource(title, data));
        report.getAreaByName("ATBFooter").getItemByName("totcash").setruntimeValue(faceCash.toString());
        report.getAreaByName("ATBFooter").getItemByName("totcc").setruntimeValue(faceCredit.toString());
        report.getAreaByName("ATBFooter").getItemByName("totaxe").setruntimeValue(taxeCash.toString());
        report.getAreaByName("ATBFooter").getItemByName("totTaxeCre").setruntimeValue(taxeCccf.toString());
        report.getAreaByName("ATBFooter").getItemByName("totcom").setruntimeValue(com.toString());
        report.getAreaByName("ATBFooter").getItemByName("totnet").setruntimeValue(netToPaid.toString());
        report.getAreaByName("ATBFooter").getItemByName("cancelfee").setruntimeValue(cancelFee.toString());
    }

    private void mcoTicket(ArrayList auto) {
        MY_bigDecimal faceCash = new MY_bigDecimal("0.00");
        MY_bigDecimal faceCredit = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCash = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCccf = new MY_bigDecimal("0.00");
        MY_bigDecimal com = new MY_bigDecimal("0.00");
        MY_bigDecimal netToPaid = new MY_bigDecimal("0.00");
        MY_bigDecimal cancelFee = new MY_bigDecimal("0.00");


        Object[][] data = new Object[auto.size()][14];
        for (int i = 0; i < auto.size(); i++) {
            BspObject bspobj = new BspObject((Object[]) auto.get(i));
            taxeCash = bspobj.addTaxeCash(taxeCash);

            taxeCccf = bspobj.addTaxeCredit(taxeCccf);

            com = com.add(bspobj.getCommission());

            netToPaid = netToPaid.add(bspobj.getNetToPay());

            faceCash = bspobj.addFacialCash(faceCash);

            faceCredit = bspobj.addFacialCredit(faceCredit);

            Object[] tmp = bspobj.getString();
            String affiche = "";
            for (int j = 0; j < tmp.length; j++) {
                data[i][j] = tmp[j];

            }
            System.out.println(affiche);
            data[i] = tmp;

        }
        GrossCashExcluding = GrossCashExcluding.add(faceCash);
        GrossCccfExcluding = GrossCccfExcluding.add(faceCredit);
        generalCom = generalCom.add(com);
        TotalTaxesCash = TotalTaxesCash.add(taxeCash);
        TotalTaxesCccf = TotalTaxesCccf.add(taxeCccf);
        netToPay = netToPay.add(netToPaid);
        report.getAreaByName("MCODetail").setDataSource(new RArraySource(title, data));
        report.getAreaByName("MCOFooter").getItemByName("totcash").setruntimeValue(faceCash.toString());
        report.getAreaByName("MCOFooter").getItemByName("totcc").setruntimeValue(faceCredit.toString());
        report.getAreaByName("MCOFooter").getItemByName("totaxe").setruntimeValue(taxeCash.toString());
        report.getAreaByName("MCOFooter").getItemByName("totTaxeCre").setruntimeValue(taxeCccf.toString());
        report.getAreaByName("MCOFooter").getItemByName("totcom").setruntimeValue(com.toString());
        report.getAreaByName("MCOFooter").getItemByName("totnet").setruntimeValue(netToPaid.toString());
        report.getAreaByName("MCOFooter").getItemByName("cancelfee").setruntimeValue(cancelFee.toString());
    }

    private void refundTicket(ArrayList auto) {
        MY_bigDecimal faceCash = new MY_bigDecimal("0.00");
        MY_bigDecimal faceCredit = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCash = new MY_bigDecimal("0.00");
        MY_bigDecimal taxeCccf = new MY_bigDecimal("0.00");
        MY_bigDecimal com = new MY_bigDecimal("0.00");
        MY_bigDecimal netToPaid = new MY_bigDecimal("0.00");
        MY_bigDecimal cancelFee = new MY_bigDecimal("0.00");


        Object[][] data = new Object[auto.size()][14];
        for (int i = 0; i < auto.size(); i++) {
            BspObject bspobj = new BspObject((Object[]) auto.get(i));
            taxeCash = bspobj.addTaxeCash(taxeCash);
            // taxeCash.abs();
            taxeCccf = bspobj.addTaxeCredit(taxeCccf);
            // taxeCccf.abs();
            com = com.add(bspobj.getCommission());
            //com.abs();
            netToPaid = netToPaid.add(bspobj.getNetToPay());
            //netToPaid.abs();
            cancelFee = cancelFee.add(bspobj.getCancelFee());
            faceCash = bspobj.addFacialCash(faceCash);
            //faceCash.abs();
            faceCredit = bspobj.addFacialCredit(faceCredit);
            //faceCredit.abs();
            Object[] tmp = bspobj.getString();
            String affiche = "";
            for (int j = 0; j < tmp.length; j++) {
                data[i][j] = tmp[j];

            }
            System.out.println(affiche);
            data[i] = tmp;

        }
        //netToPaid=netToPaid.subtract(cancelFee);
        RefundCash = RefundCash.add(faceCash);
        TotalRefundTaxesCash = TotalRefundTaxesCash.add(taxeCash);
        TotalRefundTaxesCccf = TotalRefundTaxesCccf.add(taxeCccf);
        commissionRefund = commissionRefund.add(com);
        //  RefundCash=RefundCash.add(taxeCash);
        RefundCccf = RefundCccf.add(faceCredit);
        RefundCancel = RefundCancel.add(cancelFee);
        netToPay = netToPay.subtract(netToPaid);
        report.getAreaByName("REFUNDetail").setDataSource(new RArraySource(title, data));
        report.getAreaByName("REFUNDFooter").getItemByName("totcash").setruntimeValue(faceCash.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("totcc").setruntimeValue(faceCredit.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("totaxe").setruntimeValue(taxeCash.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("totTaxeCre").setruntimeValue(taxeCccf.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("totcom").setruntimeValue(com.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("totnet").setruntimeValue(netToPaid.toString());
        report.getAreaByName("REFUNDFooter").getItemByName("cancelfee").setruntimeValue(cancelFee.toString());
    }


    private void preInit() {
        report = new RReportJ2(m_frame);
        if (!report.importReport(getClass().getResource("/srcastra/astra/listing/bsp.rep"))) System.exit(0);

    }

    private void finalizeReport() {

        report.getPageHeader().getItemByName("start").setruntimeValue(startDate.toString2());
        report.getPageHeader().getItemByName("end").setruntimeValue(endDate.toString2());
        report.prepare();

        report.printArea(report.getAreaByName("ATBDetail"));
        report.printArea(report.getAreaByName("ATBFooter"));

        report.printArea(report.getAreaByName("MCODetail"));
        report.printArea(report.getAreaByName("MCOFooter"));

        report.printArea(report.getAreaByName("REFUNDetail"));
        report.printArea(report.getAreaByName("REFUNDFooter"));
        MY_bigDecimal GrossCashIncluding = new MY_bigDecimal("0.00");
        MY_bigDecimal GrossCreditIncluding = new MY_bigDecimal("0.00");
        MY_bigDecimal RefundCashIncluding = new MY_bigDecimal("0.00");
        MY_bigDecimal RefundCccfIncluding = new MY_bigDecimal("0.00");
        GrossCashIncluding = GrossCashExcluding.add(TotalTaxesCash);
        GrossCreditIncluding = GrossCccfExcluding.add(TotalTaxesCccf);
        RefundCashIncluding = RefundCash.add(TotalRefundTaxesCash);
        RefundCashIncluding = RefundCashIncluding.subtract(RefundCancel);
        RefundCccfIncluding = RefundCccf.add(TotalRefundTaxesCccf);
        RefundCashIncluding.abs();
        RefundCccfIncluding.abs();
        //  netToPay=netToPay.add(GrossCashIncluding);
        // netToPay=netToPay.subtract(GrossCreditIncluding);
        // netToPay=netToPay.subtract(RefundCashIncluding);
        // netToPay=netToPay.add(RefundCccfIncluding);
        // netToPay=netToPay.subtract(generalCom);
        // netToPay=netToPay.add(commissionRefund);
        report.getReportFooter().getItemByName("issuecash").setruntimeValue(GrossCashIncluding.toString());

        report.getReportFooter().getItemByName("issuecredit").setruntimeValue(GrossCreditIncluding.toString());
        report.getReportFooter().getItemByName("refundcash").setruntimeValue(RefundCashIncluding.toString());
        report.getReportFooter().getItemByName("refundcredit").setruntimeValue(RefundCccfIncluding.toString());
        report.getReportFooter().getItemByName("gencom").setruntimeValue(generalCom.toString());
        report.getReportFooter().getItemByName("comref").setruntimeValue(commissionRefund.toString());
        report.getReportFooter().getItemByName("netopay").setruntimeValue(netToPay.toString());
        report.getPageHeader().getItemByName("start").setruntimeValue(startDate.toString2());
        report.getPageHeader().getItemByName("end").setruntimeValue(endDate.toString2());
        JCFactureTest Win = new JCFactureTest(report, null, serveur, user, m_frame, 0);
        // Win.setDossierprint(this);
        Win.show();
    }


}
