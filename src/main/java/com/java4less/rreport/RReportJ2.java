package com.java4less.rreport;

//  RReport

//  Copyright (C)

//

//  Java4Less@Confluencia.net

//  All rights reserved

//

// Adquisition , use and distribution of this code is subject to restriction:

//  - You may modify the source code in order to adapt it to your needs.

//  - You may not copy and paste any code into external files.

//  - Redistribution of this (or a modified version) source cPDode is prohibited. You may only redistribute compiled versions.

//  - You may redistribute the compiled version as part of your application, not a new java component with the same purpose as this one.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material


import java.awt.*;

import java.util.*;

import java.awt.event.*;

import java.awt.print.*;

import java.sql.*;

import srcastra.astra.gui.modules.printing.*;


/**
 * Subclass of RReportImp that uses Java 2 printing API.
 */

public class RReportJ2 extends RReportImp implements Printable, Pageable {


    private PrinterJob printerJob = null;

    private PageFormat pageFormat = null;


    private int copies = 1;

    private PageFormat pf = null;

    private progressWindow progressW = null;

    private boolean printingNow = false;

    boolean print;


    /**
     * Creates a report. Owner may be null.
     */

    public RReportJ2(Frame Owner) {

        super(Owner);

        this.firstCreateObjects = true;

    }


    /**
     * get current connection to database. Will be automatically set if DBActive=true in *.rep file.
     */

    public Connection getDBCon() {
        return super.getDBCon();
    }

    /**
     * sets current connection to database.
     */

    public void setDBCon(Connection c) {
        super.setDBCon(c);
    }


    /**
     * method of PRINTABLE interface
     */

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

        // update progress window

        if (this.showProgressWindow) {

            progressW.setCurrentPage(pageIndex + 1, true);

            if (progressW.isCancelled()) return Printable.NO_SUCH_PAGE;


            this.progressW.paintAll(this.progressW.getGraphics());

        }


        if (pageIndex >= maxPages) return Printable.NO_SUCH_PAGE;


        if (Pages.size() <= pageIndex) {

            System.out.println("Page " + pageIndex + " not found");

            return Printable.NO_SUCH_PAGE;

        }

        // print requested page

        RPage p = (RPage) Pages.elementAt(pageIndex);

        p.rePrint(graphics);


        return this.PAGE_EXISTS;


    }


    /**
     * method of PAGEABLE interface
     */

    public int getNumberOfPages() {

        return maxPages;

    }


    /**
     * method of PAGEABLE interface
     */

    public PageFormat getPageFormat(int pageIndex) {

        return pf;

    }


    /**
     * method of PAGEABLE interface
     */

    public Printable getPrintable(int pageIndex) {

        return this;

    }


    /**
     * finish printing
     */

    protected void endPrintJob() {

    }


    /**
     * start report
     */

    public boolean prepare() {

        // show progress window

        if (this.showProgressWindow) {

            if (progressW == null) progressW = new progressWindow();

            //progressW.setModal(false);

            //progressW.setVisible(true);

            progressW.lblProgress.setText("Preparing pages ... ");

            progressW.setMaxPages(0);

            progressW.setCurrentPage(0, false);

            progressW.allowCancel(false);

            progressW.setModal(false);

            progressW.setVisible(true);

        }


        printingNow = false;


        if ((!this.disabledPrinting) || (bPreview)) {

            printerJob = PrinterJob.getPrinterJob();

            if (printerJob == null) return false;


            printerJob.setJobName(this.getTitle());

            // Create page format

            pf = printerJob.defaultPage();

            if (this.getOrientation() == this.ORIENTATION_HORIZONTAL) pf.setOrientation(PageFormat.LANDSCAPE);


            pf.setPaper(createPaper());


            if (this.showPageDialog) {

                pf = printerJob.pageDialog(pf);

                pf = printerJob.validatePage(pf);


                this.setDefaultPageSize((pf.getWidth() * 2.56) / 72, (pf.getHeight() * 2.56) / 72);

                this.marginLeft = (pf.getImageableX() * 2.56) / 72;

                this.marginTop = (pf.getImageableY() * 2.56) / 72;


                this.marginRight = ((pf.getWidth() - (pf.getImageableX() + pf.getImageableWidth())) * 2.56) / 72;

                this.marginBottom = ((pf.getHeight() - (pf.getImageableY() + pf.getImageableHeight())) * 2.56) / 72;


                pf.setPaper(createPaper());


            }


        }


        if (printSetup != null)

            if (printSetup instanceof RPrintSetupJDK13) {

                // configuration

                this.showPrintDialog = ((RPrintSetupJDK13) printSetup).showDialog;

                this.copies = ((RPrintSetupJDK13) printSetup).copies;


            }


        return super.prepare();

    }

    public boolean preparePrinterJob() {

        printerJob = PrinterJob.getPrinterJob();

        if (printerJob == null) return false;

        printerJob.setJobName(this.getTitle());

        return true;

    }

    private Paper createPaper() {


        Paper p = new Paper();


        if (this.getOrientation() == this.ORIENTATION_HORIZONTAL) {

            p.setSize(72 * (this.mPageHeightCM / 2.56), 72 * (this.mPageWidthCM / 2.56));

            double imX = (this.marginLeft / 2.56) * 72;

            double imY = (this.marginTop / 2.56) * 72;

            double imW = 72 * ((this.mPageWidthCM - this.marginLeft - this.marginRight) / 2.56);

            double imH = 72 * ((this.mPageHeightCM - this.marginTop - this.marginBottom) / 2.56);

            p.setImageableArea(imY - 2, imX - 2, imH + 10, imW + 10);

        } else {

            p.setSize(72 * (this.mPageWidthCM / 2.56), 72 * (this.mPageHeightCM / 2.56));

            double imX = (this.marginLeft / 2.56) * 72;

            double imY = (this.marginTop / 2.56) * 72;

            double imW = 72 * ((this.mPageWidthCM - this.marginLeft - this.marginRight) / 2.56);

            double imH = 72 * ((this.mPageHeightCM - this.marginTop - this.marginBottom) / 2.56);

            p.setImageableArea(imX - 2, imY - 2, imW + 10, imH + 10);

        }


        return p;


    }


    /**
     * new page
     */

    protected RPage newPage(boolean preview, boolean firstPage) {

        // update progress window

        if (this.showProgressWindow) {

            this.progressW.setCurrentPage(this.currentPageNumber + 1, false);

        }


        return super.newPage(preview, firstPage);

    }


    /**
     * finish report
     */

    public void endReport() {

        super.endReport();

        // trigger now printing

        if ((!this.disabledPrinting) && (!bPreview)) printNow();

        // close progess window

        if (this.showProgressWindow) {

            this.progressW.setVisible(false);

        }


    }


    /**
     * Call this method if you want to print the preview. In this case you must add the RReport (RReport is a panel) to your preview window.
     */

    public boolean printPreview(int[] PagesToPrint, int Count) {

        System.out.println("[******************]dans preview");

        printNow();

        // ((Report1)this).toHtml();

        return true;

    }


    /**
     * print report here
     */

    private void printNow() {

        try {

            System.out.println("[******************]dans printnow");

            printingNow = true;

            System.out.println("[******************]avant setPageable");

            if (print) {

                printerJob.setPageable(this);

                System.out.println("[******************]après setPageable");

                System.out.println("ca pue1");

                //printerJob.setPrintable(this,pf);


                if (this.showPrintDialog) {

                    System.out.println("ca pue2");

                    printerJob.setCopies(this.copies);

                    if (!printerJob.printDialog()) return;

                }

                /*  if(((Report1)this).getFacture()==3){



                       // String numfact=((Report1)this).getServeur().renvParamCompta(((Report1)this).getUser().getUrcleunik()).checkNumeroFacture(((Report1)this).getUser().getUrcleunik());

                        ((Report1)this).getServeur().renvDossierRmiObject(((Report1)this).getUser().getUrcleunik()).setDossierFacture(((Report1)this).getUser().getUrcleunik(),((Report1)this).getDossier().getDrcleunik(),Long.parseLong(((Report1)this).getDossier().getNumfact()))   ;

                        ((Report1)this).getDossier().setDr_facture(1);

                        ((Report1)this).getServeur().renvParamCompta(((Report1)this).getUser().getUrcleunik()).commitNumpiece(((Report1)this).getUser().getUrcleunik());



                }*/

                printThread t = new printThread();

                ((Thread) t).start();

                // show progress window

                if (this.showProgressWindow) {

                    progressW.setModal(true);

                    progressW.allowCancel(true);

                    progressW.setMaxPages(maxPages);

                    progressW.setCurrentPage(0, true);

                    progressW.setVisible(true);

                }


                t.join();

                // cancel printing?

                if (this.showProgressWindow)

                    if (progressW.isCancelled()) printerJob.cancel();


            }

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("Error when printing: " + e.getMessage());


        }

    }


    /**
     * Getter for property print.
     *
     * @return Value of property print.
     */

    public boolean isPrint() {

        return print;

    }


    /**
     * Setter for property print.
     *
     * @param print New value of property print.
     */

    public void setPrint(boolean print) {

        this.print = print;

    }


    class printThread extends Thread {

        public void run() {

            try {

                printerJob.print();

                // close progess window

                if (showProgressWindow) progressW.setVisible(false);


            } catch (Exception e) {

                System.out.println("Error when printing: " + e.getMessage());

            }

        }

    }


}