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
 * Forces a page break after the area is printed. If you need to force a page break we advise you to put this object in a empty area and then print the are when you need a new page.
 */


public class RPageBreak extends RObject


{


    public RPageBreak() {


        constant = true;


    }


    public void print(Graphics g, double px, double py, Object Value) {


    }


}


