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
 * Defines color, width and style of a line.<BR>
 */


public class RLineStyle


{


    int lineType;


    float Width;


    java.awt.Color Color;


    public final static int LINE_NORMAL = 1;


    public final static int LINE_DASHED = 2;


    public final static int LINE_DOTS = 3;


    /**
     * Creates a line style. The parameters mean:<BR>
     * <p/>
     * <p/>
     * - Width.<BR>
     * <p/>
     * <p/>
     * - Color.<BR>
     * <p/>
     * <p/>
     * - Type: LINE_NORMAL,LINE_DASHED or LINE_DOTS.<BR>
     * <p/>
     * <p/>
     * <BR>
     * <p/>
     * <p/>
     * Example: new RLineStyle(0.2f,java.awt.Color.black,RLineStyle.LINE_NORMAL);<BR>
     */


    public RLineStyle(float width, java.awt.Color c, int t) {


        lineType = t;


        Width = width;


        Color = c;


    }


    /**
     * returns line style.
     */


    public int getType() {


        return lineType;


    }


    /**
     * sets line style
     */


    public void setType(int t) {


        lineType = t;


    }


    /**
     * line color
     */


    public java.awt.Color getColor() {


        return Color;


    }


    /**
     * line color
     */


    public void setColor(java.awt.Color c) {


        Color = c;


    }


    /**
     * line width
         */


	public float getWidth() {


		 return Width;


	}








        /**
         * line width
         */


	public void setWidth(float f) {


		 Width=f;


	}





}


