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


import java.awt.Image.*;


/**
 * Prints java.awt.Image objects or RImageFile. The second is recommended because it also contain information about the image file to be used in a HTML or PDF format
 */


public class RPicture extends RObject implements java.awt.image.ImageObserver


{


    /**
     * this string (URL) will be inserted in the HTML version of the report for locating the image.  Not needed if you use a RImage Object
     */


    public String ImageHTMLAddress = "";


    /**
     * currently loaded image
     */


    public RImageFile currentImage = new RImageFile("");


    /**
     * scale the image to fit in the size of the RPicture object?
     */


    public boolean scale = false;


    /**
     * last size
     */


    private int lastwidth = -1;


    private int lastHeight = -1;


    /**
     * store last scaled image to avoid scaling the image each time. It uses the Java default scaling algorithm.
     */


    private Image scaledImage = null;


    public RPicture() {


        constant = true;


    }


    /**
     * supports HTML?
     */


    public boolean canHTML() {
        return true;
    }


    /**
     * convert value to HTML
     */


    public String toHTML(Object Value) {


        String s;


        String imageStr = this.ImageHTMLAddress;


        if (Value != null)


            if (Value instanceof RImageFile) {


                imageStr = ((RImageFile) Value).getUrlLocation();


            }


        if (Value == null) return "> </td>";


        s = "><img SRC=\"" + imageStr + "\"></td>";


        return s;


    }


    /**
     * see RObject
     */


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("VALUE") == 0) {


            currentImage.setName(val);


            if (!currentImage.getLoaded()) currentImage.loadImage();


            if (currentImage.getLoaded()) this.setdefaultValue(currentImage.getImage());


        }


        if (key.compareTo("HTMLI") == 0)


            this.ImageHTMLAddress = val;


        if (key.compareTo("SCALE") == 0)


            this.scale = (val.compareTo("1") == 0);


    }


    /**
     * method of java.awt.image.ImageObserver interface
     */


    public boolean imageUpdate(Image I, int infoflags, int x, int y, int w, int h) {


        boolean ret = true;


        boolean dopaint = false;


        long updatetime = 0;


        if ((infoflags & WIDTH) != 0) {


            dopaint = true;


        }


        if ((infoflags & HEIGHT) != 0) {


            dopaint = true;


        }


        if ((infoflags & (FRAMEBITS | ALLBITS)) != 0) {


            dopaint = true;


            ret = false;


        } else if ((infoflags & SOMEBITS) != 0) {


            dopaint = true;

            //updatetime = updateRate;


        }


        if ((infoflags & ERROR) != 0) {


            ret = false;


        }


        if (dopaint) {

            //repaint(updatetime);


        }


        return ret;


    }


    /**
     * see RObject
     */


    public void print(Graphics g, double px, double py, Object Value) {


        java.awt.Image Im;


        boolean b;


        double theWidth;

        //System.out.println("image : " + py +" "+py);


        if (Value == null) Value = defaultValue;


        if (Value != null)


            if (Value instanceof RImageFile) Value = ((RImageFile) Value).getImage();


        if (Value instanceof Image)


        {


            Im = (java.awt.Image) Value;


            if (!scale) {


                if (widthpixel < Im.getWidth(null)) widthpixel = Im.getWidth(null);


                if (heightpixel < Im.getHeight(null)) heightpixel = Im.getHeight(null);


            }

            // scale


            String v = java.lang.System.getProperty("java.version");

            /*if (v.indexOf("1.1")!=0)


                   if (this.scale) {


                       scaledImage=null;


                       if (widthpixel!=lastwidth) scaledImage=null;


                       if (heightpixel!=lastHeight) scaledImage=null;


                       if (scaledImage==null) scaledImage=Im.getScaledInstance(widthpixel,heightpixel,Image.SCALE_DEFAULT);


                       Im=scaledImage;


                       //System.out.println("image : " + Im.getWidth(null) +" "+Im.getHeight(null));


                       lastwidth=widthpixel;


                       lastHeight=heightpixel;


            }





            if ((v.indexOf("1.1")==0)  && (scale)) {


                // scaling for jdk 1.1


                b=g.drawImage(Im,(int) px,(int) py,widthpixel,heightpixel,this);


            }


            */


            if (scale) {

                // scaling for jdk 1.1


                b = g.drawImage(Im, (int) px, (int) py, widthpixel, heightpixel, this);


                return;


            }


            b = g.drawImage(Im,(int) px,(int) py,this);





		}


	}








}