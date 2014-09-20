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

//  - Redistribution of this ( or a modified version) source code is prohibited. You may only redistribute compiled versions.

//  - You may redistribute the compiled version as part of your application, not a new java component with the same purpose as this one.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material


import java.awt.*;


/**
 * Printer and page set up properties for jdk 1.3. <BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * Only if you use printing API 1.1 (printJob).
 */


public class RPrintSetupJDK13 extends RPrintSetup


{


    /**
     * page layout
     */


    public boolean horizontal = false;


    /**
     * set it to false in order to hide the printer dialog
     */


    public boolean showDialog = true;


    /**
     * printer resolution. Leave 0 for default
     */


    public int printerResolution = 0;


    /**
     * printer name
     */


    public String printer = "";


    /**
     * number of copies to print
     */


    public int copies = 1;


    /**
     * size of page
     */


    public Object media = null;


    /**
     * set printer mode in COLOR
     */


    public boolean colorPrint = false;


    /**
     * get printer job with requeted properties
     */


    public PrintJob getPrintJob(Frame f) {

        // set page attributes


        java.awt.PageAttributes pa = new PageAttributes();


        if (horizontal) pa.setOrientationRequested(PageAttributes.OrientationRequestedType.LANDSCAPE);


        else pa.setOrientationRequested(PageAttributes.OrientationRequestedType.PORTRAIT);


        if (colorPrint) pa.setColor(java.awt.PageAttributes.ColorType.COLOR);


        if (media != null) pa.setMedia((PageAttributes.MediaType) media);


        if (printerResolution > 0) pa.setPrinterResolution(printerResolution);

        // job attributes


        java.awt.JobAttributes ja = new java.awt.JobAttributes();


        if (copies > 1) ja.setCopies(copies);


        if (!showDialog) ja.setDialog(java.awt.JobAttributes.DialogType.NONE);


        else ja.setDialog(java.awt.JobAttributes.DialogType.NATIVE);


        if (printer.length() > 0) ja.setPrinter(printer);


        return Toolkit.getDefaultToolkit().getPrintJob(f, "Printer", ja, pa);

        //return null;


	}





}


