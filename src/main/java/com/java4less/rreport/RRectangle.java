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
 * Uses the x,y,width,height fields of Robject to draw the rectangle.
 */


public class RRectangle extends RObject


{


    /**
     * style of the frame
     */


    public RLineStyle Style = new RLineStyle(0.2f, java.awt.Color.black, RLineStyle.LINE_NORMAL);


    /**
     * color of the frame
     */


    public java.awt.Color color = java.awt.Color.black;


    /**
     * filling color of the rectangle
     */


    public java.awt.Color fillColor = java.awt.Color.white;


    /**
     * if true, rectangle will be solid
     */


    public boolean fill = false;


    public RRectangle() {


        constant = true;


    }


    public void print(Graphics g, double px, double py, Object Value) {

        // first paint background


        if (fill) {


            g.setColor(fillColor);


            g.fillRect((int) px, (int) py, (int) widthpixel - 1, (int) heightpixel - 1);


        }

        // paint border if any


        if (Style == null) return;


        g.setColor(Style.Color);


        String v = java.lang.System.getProperty("java.version");


        if (v.indexOf("1.1") != 0) {

            // print using jdk 1.2


            if (g instanceof java.awt.Graphics2D) {

                // draw line using 2D


                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;


                float strokeWidth = 0.5f;


                if (Style != null) strokeWidth = Style.getWidth();


                java.awt.Stroke st = null;


                java.awt.geom.Rectangle2D sh = new java.awt.geom.Rectangle2D.Double((double) px, (double) py, (double) widthpixel, (double) heightpixel);


                int LineType = RLineStyle.LINE_NORMAL;


                if (Style != null) LineType = Style.getType();


                float dash1[] = {10.0f};


                float dash2[] = {2.0f};


                if (LineType == RLineStyle.LINE_NORMAL) st = new java.awt.BasicStroke(strokeWidth);


                if (LineType == RLineStyle.LINE_DASHED)
                    st = new java.awt.BasicStroke(strokeWidth, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);


                if (LineType == RLineStyle.LINE_DOTS)
                    st = new java.awt.BasicStroke(strokeWidth, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10.0f, dash2, 0.0f);


                g2.setStroke(st);


                g2.draw(sh);


                return;


            }


        }


        g.drawRect((int) px, (int) py, (int) widthpixel - 1, (int) heightpixel - 1);


    }


    /**
     * load properties from definition file. Used by RReportImp.
     */


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("FCOLOR") == 0) this.fillColor = convertColor(val);


        if (key.compareTo("COLOR") == 0) {


            this.color = convertColor(val);


            this.Style.setColor(convertColor(val));


        }


        if (key.compareTo("FILL") == 0) this.fill = (val.compareTo("1") == 0);


        if (key.compareTo("STYLE") == 0) this.Style.setType(new Integer(val).intValue());


        if (key.compareTo("SWIDTH") == 0) this.Style.setWidth((float) new Double(val).doubleValue());

        //if (key.compareTo("COLOR")==0) this.setPropColor(this.convertColor(val));


    }


    public void convertToPixels(int r) {


        super.convertToPixels(r);


        heightpixel = (int) Math.floor((y + height) * resolution) - ypixel;


    }


}





