/*

 * CheckHistorique.java

 *

 * Created on 9 septembre 2003, 16:21

 */


package srcastra.astra.gui.modules.compta;

import com.java4less.rreport.*;

import javax.swing.*;

import srcastra.test.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.sys.classetransfert.utils.Date;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import java.sql.*;


/**
 * @author thomas
 */

public class CheckHistorique2 implements RAreaListener {


    /**
     * Creates a new instance of CheckHistorique
     */

    RReport report;

    ServeurConnect connect;

    CheckHistorique_T historique;

    JFrame frame;

    ArrayList arealiste;

    Hashtable base;

    Hashtable cp;

    public CheckHistorique2() {

        base = new Hashtable();

        cp = new Hashtable();

        this.frame = frame;

        try {

            Connection con = connectDb("root", "XkLm2000", "vise", "", 3306);

            historique = getInforHistorique(con);

            arealiste = new ArrayList();

            showHistorique();

        } catch (SQLException sn) {

            sn.printStackTrace();

        }

    }

    public static void main(String[] args) {

        new CheckHistorique2();

    }

    private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException {

        String message;

        Connection tmpcon = null;

        try {

            String jdbcDriverClassName = "org.gjt.mm.mysql.Driver";

            if (jdbcDriverClassName != null)

                Class.forName(jdbcDriverClassName);

            // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);

            tmpcon = DriverManager.getConnection("jdbc:mysql://195.144.69.10:3306/" + dbName + "?autoReconnect=true", userName, password);

            System.out.println("ok connecter");

        }

        catch (ClassNotFoundException e0) {

            e0.printStackTrace();

        }


        catch (Exception e2) {

            e2.printStackTrace();

        }       // Add your handling code here:

        return tmpcon;

    }

    public CheckHistorique_T getInforHistorique(Connection con) throws SQLException {

        CheckHistorique_T check = new CheckHistorique_T();

        ArrayList historique = new ArrayList();

        //                        1          2               3               4           5           6                   7           8           9           10              11              12       13         14          15          16          17

        String req = "SELECT h.henotcpt, h.hetransact, p.pede_numper, j.jota_abrev, h.henumpiece, h.hedatecreation, h.hetypeligne, c.ce_num, h.hecodetva, h.hevaleur, h.hevaleurbase, h.hevaleurtva,he_dc,  h.helibelle,d.dr_numdos,h.drcleunik,jxcleunik FROM journcompta j, historique2 h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik LEFT JOIN dossier d ON h.drcleunik=d.dr_cleunik, periode p, compte c WHERE c.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik  AND h.heperiode=p.pede_cleunik ORDER  BY d.dr_numdos,h.hecleunik,h.hetransact, h.henumpiece";

        String requetebase = "SELECT henumpiece,jxcleunik,SUM( hevaleur )  FROM historique2 WHERE hetypeligne IN ( 'B', 'BP','P','CPC','A','OBCC','NCB','NCAB','NCOB' ) AND henotcpt=1 group by henumpiece,jxcleunik";

        String requecp = "SELECT henumpiece,jxcleunik,SUM( hevaleurbase ) FROM historique2 WHERE hetypeligne  IN ('D','ACP','TVA','TVAV','NC','NCT','NCA','NCO','NCOT') AND henotcpt=1 group by henumpiece,jxcleunik";

        String requecp2 = "SELECT henumpiece,jxcleunik,SUM( hevaleur )  FROM historique2 WHERE hetypeligne IN ( 'CP','OCCC') AND henotcpt=1 group by henumpiece,jxcleunik";

        PreparedStatement pstmt = con.prepareStatement(req);

        //  pstmt.setInt(1,tmpool.getUrcle2());

        ResultSet result = pstmt.executeQuery();

        result.beforeFirst();

        while (result.next()) {

            Object[] tab = new Object[17];

            for (int i = 0; i < tab.length; i++) {

                if (i == 5) {

                    srcastra.astra.sys.classetransfert.utils.Date date = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i + 1));

                    tab[i] = date;

                } else if (i == 16) {

                    tab[16] = result.getObject(5).toString() + result.getObject(17).toString();

                } else

                    tab[i] = result.getObject(i + 1);

            }

            historique.add(tab);

        }

        check.setHistorique(historique);

        pstmt = con.prepareStatement(requetebase);

        result = pstmt.executeQuery();

        result.beforeFirst();

        while (result.next()) {

            base.put(result.getString(1) + result.getString(2), new Double(result.getDouble(3)));

        }

        pstmt = con.prepareStatement(requecp);

        result = pstmt.executeQuery();

        result.beforeFirst();

        result = pstmt.executeQuery();

        result.beforeFirst();

        while (result.next()) {

            cp.put(result.getString(1) + result.getString(2), new Double(result.getDouble(3)));

        }

        pstmt = con.prepareStatement(requecp2);

        result = pstmt.executeQuery();

        result.beforeFirst();

        result = pstmt.executeQuery();

        result.beforeFirst();

        while (result.next()) {

            cp.put(result.getString(1) + result.getString(2), new Double(result.getDouble(3)));

        }


        return check;


    }


    private void titre(RArea area, boolean areab) {


        RField[] tab = new RField[14];

        String[] tabs = new String[]{"Co", "tr", "Per", "Journal", "Nump", "Date cre", "Date mouv", "Compte", "C. TVA", "Valeur", "Val Base", "Val Tva", "D/C", "Lib", "Doss."};

        double x = 0;

        double y = 0;

        for (int i = 0; i < tab.length; i++) {

            tab[i] = new RField();

            tab[i].x = x;

            tab[i].y = y;

            tab[i].setruntimeValue(tabs[i]);

            tab[i].setdefaultValue(tab[i]);

            x = incrementX(i, x);

            area.add(tab[i]);

        }

    }

    private double incrementX(int i, double x) {

        if (i == 0 || i == 1 || i == 2)

            x = x + 0.7;

        else if (i == 5 || i == 6 || i == 7)

            x = x + 2.8;

        else if (i == 11)

            x = x + 0.5;

        else if (i == 13)

            x = x + 3;

        else x = x + 1.8;

        return x;


    }

    private void setSoldeTitre(RArea area, double y) {

        double x = 0;

        if (historique.getSolde() != null) {

            //for(int j=0;j<historique.getSolde().size();j++){

            Object[] tab2 = new Object[]{"Compte / Tiers", "debit", "credit"};

            RField[] tabdata = new RField[3];

            x = 0;

            for (int i = 0; i < tab2.length; i++) {

                tabdata[i] = new RField();

                tabdata[i].x = x;

                tabdata[i].y = y;

                tabdata[i].setruntimeValue(tab2[i].toString());

                tabdata[i].setdefaultValue(tab2[i].toString());

                x = x + 4;

                area.add(tabdata[i]);

            }

            y = y + 1;

            area.height = y;

            //}

        }

    }

    private void setSoldeHistorique(RArea area, double y) {

        double x = 0;

        if (historique.getSolde() != null) {

            for (int j = 0; j < historique.getSolde().size(); j++) {

                Object[] tab2 = (Object[]) historique.getSolde().get(j);

                RField[] tabdata = new RField[3];

                x = 0;

                for (int i = 0; i < tab2.length; i++) {

                    tabdata[i] = new RField();

                    tabdata[i].x = x;

                    tabdata[i].y = y;

                    tabdata[i].setruntimeValue(tab2[i].toString());

                    tabdata[i].setdefaultValue(tab2[i].toString());

                    x = x + 4;

                    area.add(tabdata[i]);

                }

                y = y + 0.5;

                area.height = y;

            }

        }

    }

    private int addDébitCrédit(String key) {

        double debit = 0;

        double credit = 0;

        double totalv = 0;

        RArea areadc = new RArea();

        RField fieldlibtrans = new RField();

        fieldlibtrans.setruntimeValue("Transaction :");

        fieldlibtrans.x = 0;

        fieldlibtrans.y = 0;

        RField fieldtrans = new RField();

        fieldlibtrans.setruntimeValue(key.toString());

        fieldlibtrans.x = 2;

        fieldlibtrans.y = 0;

        RField fieldlibd = new RField();

        fieldlibd.setruntimeValue("Débit :");

        fieldlibd.x = 4;

        fieldlibd.y = 0;

        RField fieldlibc = new RField();

        fieldlibc.x = 8;

        fieldlibc.y = 0;

        fieldlibc.setruntimeValue("Crédit");

        RField fielddata = new RField();

        fielddata.setruntimeValue(base.get(key));

        if (base.get(key) != null) {

            debit = new Double(base.get(key).toString()).doubleValue();

            debit = MathRound.roundThisToDouble(debit);

        }

        if (cp.get(key) != null) {

            credit = new Double(cp.get(key).toString()).doubleValue();

            credit = MathRound.roundThisToDouble(credit);

        }


        totalv = debit + credit;

        fielddata.x = 6;

        fielddata.y = 0;

        RField fieldcdata = new RField();

        fieldcdata.setruntimeValue(cp.get(key));

        fieldcdata.x = 10;

        fieldcdata.y = 0;

        RField totalib = new RField();

        totalib.setruntimeValue("Total");

        totalib.x = 12;

        totalib.y = 0;

        RField total = new RField();

        total.setruntimeValue(new Double(totalv));

        total.x = 14;

        total.y = 0;


        areadc.add(fieldlibtrans);

        areadc.add(fieldtrans);

        areadc.add(fieldlibd);

        areadc.add(fielddata);

        areadc.add(fieldlibc);

        areadc.add(fieldcdata);

        areadc.add(totalib);

        if (totalv != 0) {

            total.FontColor = java.awt.Color.RED;

            total.FontType = new java.awt.Font("tahoma", java.awt.Font.BOLD, 12);

            areadc.add(total);

            areadc.height = 0.5;

            arealiste.add(areadc);

            return 1;


        } else {

            total.FontColor = java.awt.Color.BLUE;

            total.FontType = new java.awt.Font("tahoma", java.awt.Font.BOLD, 12);

            return 0;

        }

        // areadc.add(total);

        // areadc.height=0.5;

        // arealiste.add(areadc);

        // areadc.add(total);

        // areadc.height=0.5;

        //arealiste.add(areadc);


    }

    private double setDataHistorique() {

        double y = 0.5;

        double x = 0;

        boolean sw = false;

        String key = null;

        boolean sw2 = false;

        int retval = 0;

        if (historique.getHistorique() != null) {

            Object[] tmptab = (Object[]) historique.getHistorique().get(0);

            key = tmptab[16].toString();

            retval = addDébitCrédit(key);

            for (int j = 0; j < historique.getHistorique().size(); j++) {

                System.out.println("size" + historique.getHistorique().size());

                System.out.println("j " + j);


                Object[] tab2 = (Object[]) historique.getHistorique().get(j);

                if (j < historique.getHistorique().size() - 1) {

                    Object[] tabtmp = (Object[]) historique.getHistorique().get(j + 1);

                    if (!tabtmp[16].toString().equals(tab2[16].toString())) {

                        key = tabtmp[16].toString();

                        sw = true;

                    }

                    if (tabtmp[16] == null || tab2[16] == null) {

                        sw2 = true;

                    } else if (!tabtmp[16].toString().equals(tab2[16].toString())) {

                        sw2 = true;

                    }

                }

                RField[] tabdata = new RField[16];

                y = y + 0.5;

                //       area.height=y;

                x = 0;

                RArea area = new RArea();

                area.height = 0.5;

                if (sw)

                    area.height = 0.7;

                if (sw2)

                    area.height = 1.2;

                for (int i = 0; i < (tab2.length - 1); i++) {

                    tabdata[i] = new RField();

                    tabdata[i].x = x;

                    tabdata[i].y = 0;

                    if (i == 7 || i == 9 || i == 10 || i == 11) {

                        tabdata[i].FontType = new java.awt.Font("Tahoma", java.awt.Font.BOLD, 10);

                        if (i == 7)

                            tabdata[i].width = 2.5;

                        else {

                            tabdata[i].width = 2.2;

                            tabdata[i].Align = RField.ALIGN_RIGHT;

                        }

                    }

                    if (i == 5) {

                        Date date = (Date) tab2[i];

                        tab2[i] = date.toString2();

                    }

                    String value = "";

                    if (tab2[i] != null)

                        value = tab2[i].toString();

                    tabdata[i].setruntimeValue(value);

                    tabdata[i].setdefaultValue(value);

                    x = incrementX(i, x);

                    area.add(tabdata[i]);


                }


                if (arealiste == null)

                    arealiste = new ArrayList();

                if (sw2 == true)

                    retval = addDébitCrédit(key);

                if (retval == 1) {

                    arealiste.add(area);

                    System.out.println("j'ajoute l'area");

                }

                sw = false;

                sw2 = false;

            }

        }

        return y;


    }

    public void showHistorique() {

        report = new RReportJ2(null);

        report.marginBottom = 1d;

        report.marginTop = 1.5d;

        report.marginLeft = 1.0d;

        report.marginRight = 1.0d;

        report.mPageWidthCM = 21d;

        report.mPageHeightCM = 29.7d;

        report.setOrientation(1);

        //report.

        // report.setP

        RArea rarea = new RArea();

        RArea detail = new RArea();


        RArea pagefooter = new RArea();

        detail.setFrameType(RArea.FRAME_PAGE);

        detail.setFrameStyle(new RLineStyle(0.2f, java.awt.Color.black, RLineStyle.LINE_NORMAL));

        // detail.

        pagefooter.height = 1;

        rarea.height = 1;

        detail.height = 5;

        /// rarea.x=0;

        //rarea.y=0;

        // rarea.width=10;

        // rarea.setBackground(java.awt.Color.blue);

        // detail.setBackground(java.awt.Color.red);

        //   detail.x=6;

        //   detail.y=4;

        //  detail.width=20;

        //  detail.height=5;

        RArea footer = new RArea();

        //footer.height=10;

        //footer.width=20;

        report.addArea(detail);

        titre(rarea, false);

        //  titre(detail,true);

        double y = setDataHistorique();

        //report.setPageFooter(pagefooter);

        // report.setReportFooter(footer);

        footer.x = 0;

        footer.y = 0;

        setSoldeTitre(footer, 0);

        setSoldeHistorique(footer, 1);

        report.addArea(footer);

        report.setPageHeader(rarea);


        RPrintSetupJDK13 pt = new RPrintSetupJDK13();

        pt.showDialog = true;

        pt.horizontal = true;


        report.setPrintSetup(pt);

        // report.disablePrinting(true);

        report.prepare();

        for (int i = 0; i < arealiste.size(); i++) {

            report.printArea((RArea) arealiste.get(i));

        }

        report.printArea(footer);

        report.endReport();

        RReportJWindow win = new RReportJWindow(report, (java.awt.Frame) frame, null, "", "");

        win.show();

    }


    /**
     * this method is called before each repetition of the area is printed. You can use this method to programatically set fields in the area or for you internal calculations. In exampleOrder.java this is used for calculating the amount of each line and the total amuount of the purchase order.
     */

    public void beforePrintingArea(RArea area) {

    }


}

