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


import java.awt.event.*;


import java.awt.*;


import java.io.*;


/**
 * AWT button with images
 */


public class RButton extends Button


{


    private java.awt.Image im;


    public RButton(String icon, String text) {


        super();


        boolean haveResource = false;


        im = new RImageFile(icon).getImage();


        MediaTracker mt = new MediaTracker(this);


        mt.addImage(im, 0);
        try {
            mt.waitForID(0);
        }


        catch (InterruptedException ie) {
        }


    }


    public void paint(Graphics g)


    {


        super.paint(g);


        Dimension btnSize = getSize();


        if (im != null)


            g.drawImage(im, (btnSize.width - im.getWidth(this)) / 2, (btnSize.height - im.getHeight(this)) / 2, this);


    }


}


