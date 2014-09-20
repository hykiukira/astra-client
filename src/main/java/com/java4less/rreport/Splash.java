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


import javax.swing.JFrame;

import java.awt.*;


public class Splash extends Window {


    Image splashIm;


    public Splash() {


        super(new Frame());

        // load image

        MediaTracker mt = new MediaTracker(this);

        splashIm = Toolkit.getDefaultToolkit().getImage("com/java4less/images/splash.png");

        mt.addImage(splashIm, 0);

        try {

            mt.waitForID(0);

        } catch (InterruptedException ie) {
        }

        /* Size the frame */

        setSize(splashIm.getWidth(null), splashIm.getHeight(null));

        /* Center the frame */

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle frameDim = getBounds();

        setLocation((screenDim.width - frameDim.width) / 2, (screenDim.height - frameDim.height) / 2);

        try {

            jbInit();

        }

        catch (Exception e) {

            e.printStackTrace();

        }


    }


    public void init() {

        /* Show the frame */

        setVisible(true);


        try {

            Thread.sleep(1000);

        } catch (InterruptedException ie) {
        }


    }


    public void paint(Graphics g) {


        if (splashIm != null) {

            g.drawImage(splashIm, 0, 0, this);

        }

    }

    private void jbInit() throws Exception {

        this.setBackground(Color.white);

    }


}