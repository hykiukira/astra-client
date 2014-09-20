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
 * Uses the x,y,width,height fields of Robject to draw the line.
 */


public class RLine extends RObject


{


    /**
     * style of the line
     */


    public RLineStyle Style = new RLineStyle(0.2f, java.awt.Color.black, RLineStyle.LINE_NORMAL);


    /**
     * color of the line
     */


    public java.awt.Color color = java.awt.Color.black;


    /**
     * can be USERDEFINED, HORIZONTAL or VERTICAL.
     */


    public int lineDirection = 0;


    /**
     * use provided coordinates.
     */


    public static int USERDEFINED = 0;


    /**
     * draw a horizontal line.
     */


    public static int HORIZONTAL = 1;


    /**
     * draw a vertical line.
     */


    public static int VERTICAL = 2;


    public RLine() {


        constant = true;


    }


    protected void drawSimpleLine(java.awt.Graphics g, int x1, int y1, int x2, int y2) {


        int pixelsWidth = 1;

        //calculate line width in pixels


        pixelsWidth = (int) (Style.Width * (java.awt.Toolkit.getDefaultToolkit().getScreenResolution() / 72));


        if (pixelsWidth < 1) pixelsWidth = 1;

        // draw central line


        g.drawLine(x1, y1, x2, y2);


        if (pixelsWidth == 1) {

            // nothing else to draw


            return;


        }


        if (pixelsWidth > 1) {

            // draw extra lines


            int xwidth = 0;


            int ywidth = 0;


            int h = java.lang.Math.abs(y2 - y1);


            int w = java.lang.Math.abs(x2 - x1);


            double hipo = java.lang.Math.sqrt((h * h) + (w * w));


            double Cos = w / hipo;


            xwidth = 1;


            ywidth = 0;

            // < 60%


            if (Cos > java.lang.Math.cos(3.1415 / 3)) {


                xwidth = 0;


                ywidth = 1;


            }

            // < 30%


            if (Cos > java.lang.Math.cos(3.1415 / 6)) {


                xwidth = 0;


                ywidth = 1;


            }


            int side = 1;


            int distanceToCenter = 0;

            // draw additional lines to achieve the width effect


            for (int i = 2; i <= pixelsWidth; i++) {


                if (side == 1) distanceToCenter++;


                g.drawLine(x1 + (side * xwidth * distanceToCenter), y1 + (side * ywidth * distanceToCenter), x2 + (side * xwidth * distanceToCenter), y2 + (side * ywidth * distanceToCenter));


                side = 1 * (-1);


            }


        }


    }


    /**
     * protected
     */


    public void drawLineWithStyle(java.awt.Graphics g, int x1, int y1, int x2, int y2) {


        if (Style.lineType == Style.LINE_NORMAL) drawSimpleLine(g, x1, y1, x2, y2);


        else {

            // draw DASHED or DOTS line


            int segment = 10;


            int segmentspace = 10;


            if (Style.lineType == Style.LINE_DOTS) {


                segment = 1;


                segmentspace = 5;


            }


            int h = java.lang.Math.abs(y2 - y1);


            int w = java.lang.Math.abs(x2 - x1);


            double hipo = java.lang.Math.sqrt((h * h) + (w * w));


            double Cos = w / hipo;


            double Sin = h / hipo;


            int Xslope = 1;


            int Yslope = 1;


            if (x2 < x1) Xslope = -1;


            if (y2 < y1) Yslope = -1;


            int subx1 = 0;


            int suby1 = 0;


            int subx2 = 0;


            int suby2 = 0;


            int subsegment = 0;

            // draw line segments


            while (true) {


                suby2 = (int) ((Sin) * (subsegment + segment)) * Yslope;


                subx2 = (int) ((Cos) * (subsegment + segment)) * Xslope;


                suby1 = (int) ((Sin) * (subsegment)) * Yslope;


                subx1 = (int) ((Cos) * (subsegment)) * Xslope;


                if (w < java.lang.Math.abs(subx1)) break;


                if (h < java.lang.Math.abs(suby1)) break;


                if (w < java.lang.Math.abs(subx2)) subx2 = w * Xslope;


                if (h < java.lang.Math.abs(suby2)) suby2 = h * Yslope;


                drawSimpleLine(g, x1 + subx1, y1 + suby1, x1 + subx2, y1 + suby2);


                subsegment = subsegment + segment + segmentspace;


            }


        }


    }


    public void print(Graphics g, double px, double py, Object Value) {


        if (Style != null) g.setColor(Style.Color);


        else g.setColor(java.awt.Color.black);


        int tmpH = heightpixel;


        int tmpW = widthpixel;


        if (this.lineDirection == this.HORIZONTAL) tmpH = 0;


        if (this.lineDirection == this.VERTICAL) tmpW = 0;


        String v = java.lang.System.getProperty("java.version");


        if (v.indexOf("1.1") != 0) {

            // print using jdk 1.2


            if (g instanceof java.awt.Graphics2D) {

                // draw line using 2D


                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;


                float strokeWidth = 0.5f;


                if (Style != null) strokeWidth = Style.getWidth();


                java.awt.Stroke st = null;


                java.awt.geom.Line2D sh = new java.awt.geom.Line2D.Double((double) px, (double) py, (double) px + tmpW, (double) py + tmpH);


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


        drawLineWithStyle(g, (int) px, (int) py, (int) px + tmpW, (int) py + tmpH);

        // g.drawLine((int) px,(int) py,(int) px+widthpixel,(int) py+heightpixel);


    }


    /**
     * load properties from definition file. Used by RReportImp.
     */


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("COLOR") == 0) {


            this.Style.setColor(this.convertColor(val));


            this.color = (this.convertColor(val));


        }


        if (key.compareTo("STYLE") == 0) this.Style.setType(new Integer(val).intValue());


        if (key.compareTo("SWIDTH") == 0) this.Style.setWidth((float) new Double(val).doubleValue());


        if (key.compareTo("DIR") == 0) this.lineDirection = (new Integer(val).intValue());

        //if (key.compareTo("COLOR")==0) this.setPropColor(this.convertColor(val));





	}





}