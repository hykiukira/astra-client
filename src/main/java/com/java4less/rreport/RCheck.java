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


import java.io.*;


/**
 * Class for printing a boolean value.
 * <p/>
 * <p/>
 * See also RObject.
 */


public class RCheck extends RPicture implements java.awt.image.ImageObserver


{


    String[] paKeys = {"0", "1"};


    boolean ILoaded = false;


    boolean ICancelled = false;


    Object[] paValue = {null, null};


    Button c = new Button();


    /**
     * Image for TRUE false. Url used for exorting to HTML.
     */


    public String imageHTMLTrue = "";


    /**
     * Image for FALSE false. Url used for exorting to HTML.
     */


    public String imageHTMLFalse = "";


    /**
     * uses the default images. If the value is false nothing is displayed, if the value is true a tick is displayed.
     */


    public RCheck() {


        super();


        constant = false;

        // load image


        RImageFile ifm = new RImageFile("true.jpg");


        ifm.loadImage();


        paValue[1] = ifm;


        this.imageHTMLTrue = "com/java4less/images/true.jpg";


        this.imageHTMLFalse = "";

        // make sure we have the image


        if (paValue[1] != null) {


            try {


                int imw = -1;


                ILoaded = false;


                while ((!ILoaded) && (!ICancelled) && (imw == -1)) {
                    imw = ((RImageFile) paValue[1]).getImage().getWidth(this);
                }


                int imh = -1;


                ILoaded = false;


                while ((!ILoaded) && (!ICancelled) && (imh == -1)) {
                    imh = ((RImageFile) paValue[1]).getImage().getHeight(this);
                }


            }


            catch (Exception e) {
            }


            try {


                java.awt.MediaTracker mt = new java.awt.MediaTracker(c);


                mt.addImage(((RImageFile) paValue[1]).getImage(), 0);


                mt.waitForID(0);
            }


            catch (Exception ie_Logo) {
            }
            ;


        }


    }


    /**
     * Uses the images you pass as parameters to display the value.
     */


    public RCheck(RImageFile TrueImage, RImageFile FalseImage) {


        super();


        String[] paKeys = new String[2];


        paKeys[0] = "0";


        paKeys[1] = "1";


        Object[] paValue = new Object[2];


        paValue[0] = FalseImage;


        paValue[1] = TrueImage;


    }


    /**
     * supports HTML?
     */


    public boolean canHTML() {
        return true;
    }


    public boolean imageUpdate(Image I, int infoflags, int x, int y, int w, int h) {


        boolean ret = true;


        boolean dopaint = false;


        long updatetime = 0;


        if ((infoflags & WIDTH) != 0) {


            dopaint = true;


            ILoaded = true;


        }


        if ((infoflags & HEIGHT) != 0) {


            dopaint = true;


            ILoaded = true;


        }


        if ((infoflags & (FRAMEBITS | ALLBITS)) != 0) {


            ret = false;


        } else if ((infoflags & SOMEBITS) != 0) {


            dopaint = true;


        }


        if ((infoflags & ERROR) != 0) {


            ret = false;


            ICancelled = true;


        }


        return ret;


    }


    /**
     * convert value to HTML
     */


    public String toHTML(Object Value) {


        String s;


        if (Value == null) Value = new Boolean(true);


        if (!(Value instanceof Boolean)) return "> </td>";


        String strValue = "-";


        if (((Boolean) Value).booleanValue()) strValue = "x";


        s = "><font SIZE=\"1\" FACE=\"Arial\" COLOR=\"#00000000\">" + strValue + "</font> </td>";


        return s;


    }


    public void print(Graphics g, double px, double py, Object Value) {


        if (Value == null) Value = new Boolean(true);


        if (Value instanceof Boolean) {


            Boolean B = (Boolean) Value;


            if (B.booleanValue())


            {


                if (paValue[1] != null)


                    if (((RImageFile) paValue[1]).getLoaded())
                        super.print(g, px, py, ((RImageFile) paValue[1]).getImage());


            } else


            {


                if (paValue[0] != null)


                    if (((RImageFile) paValue[0]).getLoaded())
                        super.print(g, px, py, ((RImageFile) paValue[0]).getImage());


            }


        }


    }


    public void setTrueImage(RImageFile f) {


        this.paValue[1] = f;


    }


    public void setFalseImage(RImageFile f) {


        this.paValue[0] = f;


    }


    public RImageFile getTrueImage() {


        return (RImageFile) paValue[1];


    }


    public RImageFile getFalseImage() {


        return (RImageFile) paValue[0];


    }


    /**
     * load properties from definition file. Used by RReportImp.
     */


    public void importLine(String key, String val) {


        if (key.compareTo("HTMLTRUE") == 0) this.imageHTMLTrue = val;


        if (key.compareTo("HTMLFALS") == 0) this.imageHTMLFalse = val;


        if (key.compareTo("IMAGETRUE") == 0)


            if (val.length() > 0) this.setTrueImage(new RImageFile(val));


        if (key.compareTo("IMAGEFALS") == 0)


            if (val.length() > 0) this.setFalseImage(new RImageFile(val));


        if (key.compareTo("VALUE") == 0) this.setdefaultValue(new Boolean(val.compareTo("1") == 0));


        else super.importLine(key, val);


    }





}


