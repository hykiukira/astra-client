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
 * Class for printing a string or image base on a key. see also RObject.
 */


public class RCombo extends RObject


{


    /**
     * values to ne painted, can contain strings, numbers and images (or RImageFile)
     */


    public Object[] Values;


    /**
     * key used to select the value from the "Values" array.
     */


    public String[] Keys;


    /**
     * font of the value
     */


    public Font FontType = new Font("SansSerif", Font.PLAIN, 10);


    /**
     * color of the value
     */


    public java.awt.Color FontColor = java.awt.Color.black;


    /**
     * font of the key
     */


    public Font KeyFontType = new Font("SansSerif", Font.PLAIN, 10);


    /**
     * color of the key
     */


    public java.awt.Color KeyFontColor = java.awt.Color.black;


    /**
     * If false, the key will not be printed, only the value assigned to it.
     */


    public boolean printKey = false;


    /**
     * Valid values are ALIGN_LEFT, ALIGN_RIGHT and ALIGN_CENTER.
     */


    public int Align = ALIGN_LEFT;


    /**
     * internal use.
     */


    public String lastPrintedValue = null;


    public RCombo() {


        super();


        constant = false;


    }


    /**
     * The pValues parameter can be an array of strings or images.
     */


    public RCombo(String[] pKeys, Object[] pValues) {


        super();


        Keys = (String[]) pKeys.clone();


        Values = (Object[]) pValues.clone();


        constant = false;


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


        int i;


        String k;


        String desc = "";


        Object descObj = null;


        if (Value == null) Value = defaultValue;


        if (Value == null) return "> </td>";


        if (!(Value instanceof String)) return "> </td>";


        k = (String) Value;


        String printk = "";


        if (printKey) printk = k;


        for (i = 0; i < Keys.length; i++) {


            if (k.compareTo(Keys[i]) == 0) {
                descObj = Values[i];
                break;
            }


        }


        if (descObj instanceof String) {


            desc = printk + " " + ((String) descObj);


        } else desc = printk;


        s = "";


        if (Align == ALIGN_RIGHT) s = s + " ALIGN=\"RIGHT\"";


        if (Align == ALIGN_CENTER) s = s + " ALIGN=\"CENTER\"";


        if (Align == ALIGN_LEFT) s = s + " ALIGN=\"LEFT\"";


        s = s + ">";


        s = s + "<font SIZE=\"" + super.fontSizetoHTML(FontType) + "\" FACE=\"" + FontType.getName() + "\" COLOR=\"" + super.fontColortoHTML(FontColor) + "\">" + super.fontValuetoHTML(FontType, desc) + "</font></td>";


        return s;


    }


    /**
     * prints object at px,py using Value as runtime value
     */


    public void print(Graphics g, double px, double py, Object Value) {


        int i;


        int keyWidth = 0;


        int keyHeight = 0;


        String k;


        int fontHeight;


        int fontWidth;


        int marginx = 0;


        int marginy = 0;


        if (Value == null) Value = defaultValue;


        if (Value == null) return;


        if (Value instanceof Boolean)


            if (((Boolean) Value).booleanValue()) Value = "1";


            else Value = "0";


        if (Value instanceof Integer) Value = ((Integer) Value).toString();


        if (Value instanceof Long) Value = ((Long) Value).toString();


        if (Value instanceof Double) Value = ((Double) Value).toString();


        if (!(Value instanceof String)) return;


        k = (String) Value;


        keyWidth = 0;


        keyHeight = 0;


        if (printKey) {


            keyWidth = g.getFontMetrics().stringWidth(((String) Value) + " ");


            keyHeight = g.getFontMetrics().getHeight();


        }

        // look for the key


        for (i = 0; i < Keys.length; i++) {


            if (k.compareTo(Keys[i]) == 0) break;


        }


        if (i >= Keys.length) return; // key not found


        if (i >= Values.length) return; // check errors


        if (Values[i] == null) return;

        // elements of the combo are strings


        if (Values[i] instanceof String) {


            if (FontType != null) g.setFont(FontType);


            g.setColor(FontColor);


            String s = (String) Values[i];


            fontHeight = g.getFontMetrics().getHeight();


            fontWidth = (g.getFontMetrics().stringWidth(s) + keyWidth);


            if ((heightpixel) < fontHeight) heightpixel = fontHeight;


            else marginy = (int) ((heightpixel - fontHeight) / 2);


            if ((widthpixel) < fontWidth) widthpixel = fontWidth;


            else {


                marginx = (int) ((widthpixel - fontWidth) / 2);


                if (Align == ALIGN_RIGHT) marginx = (int) ((widthpixel - fontWidth) - 4);


                if (Align == ALIGN_LEFT) marginx = (int) 4;


            }


            if (heightpixel < keyHeight) heightpixel = keyHeight;

            // print key


            if (printKey) {


                if (KeyFontType != null) g.setFont(KeyFontType);


                g.setColor(KeyFontColor);


                g.drawString(k, (int) px + marginx, (int) (py + heightpixel) - marginy);


            }


            g.setFont(FontType);


            g.setColor(FontColor);


            g.drawString(s, (int) px + keyWidth + marginx, (int) (py + heightpixel) - marginy);


        }

        // elements of the combo are images


        if ((Values[i] instanceof Image) || (Values[i] instanceof RImageFile)) {


            Image I = null;


            if ((Values[i] instanceof RImageFile)) I = ((RImageFile) Values[i]).getImage();


            else I = (Image) Values[i];


            if (printKey) {


                if (KeyFontType != null) g.setFont(KeyFontType);


                g.setColor(KeyFontColor);


                g.drawString(k, (int) px, (int) (py + heightpixel));


            }


            if (widthpixel < (I.getWidth(null) + keyWidth)) widthpixel = (I.getWidth(null) + keyWidth);


            if (heightpixel < I.getHeight(null)) heightpixel = I.getHeight(null);


            if (heightpixel < keyHeight) heightpixel = keyHeight;


            g.drawImage(I, (int) px + keyWidth, (int) py, null);


        }


    }


    /**
     * load properties from definition file. Used by RReportImp.
     */


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("FCOLOR") == 0) this.FontColor = (this.convertColor(val));


        if (key.compareTo("FONT") == 0) this.FontType = (this.convertFont(val));


        if (key.compareTo("KFCOLOR") == 0) this.KeyFontColor = (this.convertColor(val));


        if (key.compareTo("KFONT") == 0) this.KeyFontType = (this.convertFont(val));


        if (key.compareTo("ALIGN") == 0) this.Align = (new Integer(val).intValue());


        if (key.compareTo("KEY") == 0) this.printKey = ((val.compareTo("1") == 0));


        if (key.compareTo("VALUES") == 0) this.Values = (this.convertList(val));


        if (key.compareTo("VALUE") == 0) this.setdefaultValue(val);


		if (key.compareTo("KEYS")==0) this.Keys=(this.convertList(val));





	}








}


