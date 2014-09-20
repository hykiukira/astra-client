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


public class RTriangle extends RObject


{

    // (x1,y1),(x2,y2) and (x3,y3) make the 3 corners


    public double x1 = 0.5;


    public double y1 = 0;


    public double x2 = 0;


    public double y2 = 1;


    public double x3 = 1;


    public double y3 = 1;


    public java.awt.Color color = java.awt.Color.black;


    public RTriangle() {


        constant = true;


    }


    public void print(Graphics g, double px, double py, Object Value) {


        int[] X = new int[3];


        int[] Y = new int[3];


        X[0] = (int) (px + (x1 * this.resolution));


        Y[0] = (int) (py + (y1 * this.resolution));


        X[1] = (int) (px + (x2 * this.resolution)); // convert from CM to pixels


        Y[1] = (int) (py + (y2 * this.resolution));


        X[2] = (int) (px + (x3 * this.resolution));


        Y[2] = (int) (py + (y3 * this.resolution));


        g.setColor(color);


        g.drawPolygon(X, Y, 3);


    }


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("COLOR") == 0) {


            this.color = convertColor(val);


        }


        if (key.compareTo("X1") == 0) this.x1 = new Double(val).doubleValue();


        if (key.compareTo("Y1") == 0) this.y1 = new Double(val).doubleValue();


        if (key.compareTo("X2") == 0) this.x2 = new Double(val).doubleValue();


        if (key.compareTo("Y2") == 0) this.y2 = new Double(val).doubleValue();


        if (key.compareTo("X3") == 0) this.x3 = new Double(val).doubleValue();


        if (key.compareTo("Y3") == 0) this.y3 = new Double(val).doubleValue();


    }


}