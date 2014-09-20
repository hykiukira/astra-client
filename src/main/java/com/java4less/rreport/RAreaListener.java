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


/**
 * Provides a user exit before each repetition  of the area is printed.
 */


public interface RAreaListener


{


    /**
     * this method is called before each repetition of the area is printed. You can use this method to programatically set fields in the area or for you internal calculations. In exampleOrder.java this is used for calculating the amount of each line and the total amuount of the purchase order.
     */


    public void beforePrintingArea(RArea area);


}


