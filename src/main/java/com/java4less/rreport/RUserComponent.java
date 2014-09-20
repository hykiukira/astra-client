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
 * This class is used to print Swing components (JLabel, JTable ...).<BR>
 */

public class RUserComponent extends RObject {


    /**
     * image for double buffering
     */

    private Image image = null;


    /**
     * JComponent to be printed
     */

    javax.swing.JComponent component = new javax.swing.JLabel("Here comes your component");


    private boolean paint = false;


    public RUserComponent() {

        super();

        constant = false;


    }


    /**
     * return image
     */

    protected Image getImage() {
        return image;
    }


    /**
     * print component
     */

    public void print(Graphics g, double px, double py, Object Value) {


        String v = java.lang.System.getProperty("java.version");

        if (v.startsWith("1.1")) {

            g.setColor(java.awt.Color.black);

            g.drawString("JDK 1.1 not supported", 10, 10);

            return;

        }


        if (defaultValue instanceof javax.swing.JComponent) component = (javax.swing.JComponent) defaultValue;


        if (runtimeValue != null)

            if (runtimeValue instanceof javax.swing.JComponent) component = (javax.swing.JComponent) runtimeValue;

        // reset image if we have changed the size

        if (image != null) {

            if ((image.getWidth(null) != widthpixel) || (image.getHeight(null) != heightpixel))

                image = null;


        }

        // create image

        if (image == null) {

            image = new java.awt.image.BufferedImage((int) widthpixel, (int) heightpixel, java.awt.image.BufferedImage.TYPE_INT_RGB);

            paint = true;

        }

        // paint JComponent

        if ((paint) && (component != null)) {

            component.setBackground(java.awt.Color.white);

            component.setSize(widthpixel, heightpixel);

            Graphics gi = image.getGraphics();

            gi.setColor(java.awt.Color.white);

            gi.fillRect(0, 0, (int) widthpixel, (int) heightpixel);

            component.setLocation((int) 0, (int) 0);

            component.print(gi);

            paint = false;

        }


        g.drawImage(image, (int) px, (int) py, null);

    }


    public void importLine(String key, String val) {

        super.importLine(key, val);

    }


    public boolean canHTML() {
        return false;}



}