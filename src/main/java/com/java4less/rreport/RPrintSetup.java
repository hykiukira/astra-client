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
 * Printer and page set up properties. If you use jdk 1.3 or later you can use RPrintSetupJDK13.<BR>
 * <p/>
 * <p/>
 * Only if you use printing API 1.1 (printJob).
 */


public class RPrintSetup


{


    /**
     * get printer job with requeted properties
     */


    public PrintJob getPrintJob(Frame f) {


        return Toolkit.getDefaultToolkit().getPrintJob(f, "Printer", null); // java 1.1 code


    }





}


