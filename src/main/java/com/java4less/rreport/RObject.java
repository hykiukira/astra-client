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
 * All elements in a report are descendant from RObject. You can create new elements by extending RObject.
 */


public class RObject


{


    public final static int ALIGN_RIGHT = 0;


    public final static int ALIGN_LEFT = 1;


    public final static int ALIGN_CENTER = 2;


    /**
     * area where this object is located
     */


    protected RArea area;


    /**
     * position and size of the object in centimeters.
     */


    public double x;


    /**
     * position and size of the object in centimeters.
     */


    public double y;


    /**
     * position and size of the object in centimeters.
     */


    public double width;


    /**
     * position and size of the object in centimeters.
     */


    public double height;


    int xpixel;


    int ypixel;


    int widthpixel;


    int heightpixel;


    /**
     * link for the element when exporting to DHTML
     */


    public String DHTMLLink = "";


    /**
     * can this object be selected by the user in the preview window? (for tooltips and actions)
     */


    public boolean selectable = true;


    /**
     * name of the object
     */


    public String name = "";


    /**
     * does this object has a constant value?
     */


    public boolean constant;


    /**
     * name of associate field in the database
     */


    public String fieldName = "";


    /**
     * default value for printing.
     */


    protected Object defaultValue;


    /**
     * current value for printing.
     */


    protected Object runtimeValue;


    protected int resolution = 0;


    /**
     * returns whether this object has a constant value.
     */


    public boolean isConstant() {
        return constant;
    }


    /**
     * sets whether this object has a constant value.
     */


    public void setConstant(boolean b) {
        constant = b;
    }


    /**
     * returns whether this object is visible
     */


    public boolean isVisible() {
        return visible;
    }


    /**
     * sets whether this object is visible
     */


    public void setVisible(boolean b) {
        visible = b;
    }


    /**
     * is this object going to be visible?
     */


    public boolean visible = true;


    /**
     * tool tip for preview
     */


    public String tooltip = "";


    /**
     * trigger action?
     */


    public boolean triggerAction = false;


    /**
     * returns whether this object can trigger  events if the user clicks on the object (in preview mode)
     */


    public boolean getActivateAction() {
        return triggerAction;
    }


    /**
     * sets whether this object can trigger  events if the user clicks on the object (in preview mode)
     */


    public void setActivateAction(boolean b) {
        triggerAction = b;
    }


    /**
     * print object at the specified location (in pixels) using the specified value.
     */


    public void print(Graphics g, double px, double py, Object Value) {


    }


    /**
     * return image of the object. (Only if the object does not have a html and pdf equivalent.
     */


    protected Image getImage() {
        return null;
    }


    /**
     * returns the HTML font for a java Font.
     */


    protected String fontValuetoHTML(Font f, String v) {


        if (f.getStyle() == Font.BOLD) {
            return "<STRONG>" + v + "</STRONG>";


        }


        if (f.getStyle() == Font.ITALIC) {
            return "<EM>" + v + "</EM>";


        }


        return v;


    }


    /**
     * returns the HTML font size for a java Font.
     */


    protected String fontSizetoHTML(Font f) {


        String s = "1";


        if (f.getSize() > 10) s = "2";


        if (f.getSize() > 12) s = "3";


        if (f.getSize() > 16) s = "4";


        if (f.getSize() > 20) s = "5";


        if (f.getSize() > 22) s = "6";


        if (f.getSize() > 30) s = "7";


        return s;


    }


    /**
     * returns the HTML font color for a java color.
     */


    protected String fontColortoHTML(java.awt.Color fc) {


        String s;


        s = Integer.toHexString(fc.getRGB());


        if (s.length() > 6) s = s.substring(s.length() - 6, s.length());


        s = "#" + s.toUpperCase();


        if (fc == java.awt.Color.green) return "#008000";


        if (fc == java.awt.Color.blue) return "#0000FF";


        if (fc == java.awt.Color.yellow) return "#FFFF00";


        if (fc == java.awt.Color.red) return "#FF0000";


        if (fc == java.awt.Color.gray) return "#808080";


        if (fc == java.awt.Color.lightGray) return "#C0C0C0";


        if (fc == java.awt.Color.magenta) return "#FF00FF";


        if (fc == java.awt.Color.orange) return "#FF8000";


        if (fc == java.awt.Color.pink) return "#FF00FF";


        if (fc == java.awt.Color.cyan) return "#00FFFF";


        if (fc == java.awt.Color.white) return "#FFFFFF";


        if (fc == java.awt.Color.black) return "#000000";


        return s;


    }


    /**
     * returns the HTML version of the element.
     */


    public String toHTML(Object Value) {


        return "";


    }


    /**
     * returns whether the class support HTML.
     */


    public boolean canHTML() {
        return false;
    }


    /**
     * used by RReport only
     */


    public void convertToPixels(int r) {


        resolution = r;


        xpixel = (int) Math.floor(x * resolution);


        ypixel = (int) Math.floor(y * resolution);


        widthpixel = (int) Math.floor(width * resolution);


        heightpixel = (int) Math.floor(height * resolution);


    }


    /**
     * used by RReport only
     */


    public void convertToCM(int r) {


        resolution = r;


        x = (double) xpixel / resolution;


        y = (double) ypixel / resolution;


        height = (double) heightpixel / resolution;


        width = (double) widthpixel / resolution;


    }


    /**
     * sets default value.
     */


    public void setdefaultValue(Object Value) {


        defaultValue = Value;


    }


    /**
     * returns default value.
     */


    public Object getdefaultValue() {


        return defaultValue;


    }


    /**
     * sets runtime value
     */


    public void setruntimeValue(Object Value) {


        runtimeValue = Value;


    }


    /**
     * returns runtime value
     */


    public Object getruntimeValue() {


        return runtimeValue;


    }

// function to convert a String parameter to a color


    /**
     * convert a string to a color. To be used when reading report from *.rep files.Internal use.
     */


    public static java.awt.Color convertColor(String c) {


        if (c.compareTo("NULL") == 0) return null;


        if (c.compareTo("RED") == 0) return java.awt.Color.red;


        if (c.compareTo("BLACK") == 0) return java.awt.Color.black;


        if (c.compareTo("BLUE") == 0) return java.awt.Color.blue;


        if (c.compareTo("CYAN") == 0) return java.awt.Color.cyan;


        if (c.compareTo("DARKGRAY") == 0) return java.awt.Color.darkGray;


        if (c.compareTo("GRAY") == 0) return java.awt.Color.gray;


        if (c.compareTo("GREEN") == 0) return java.awt.Color.green;


        if (c.compareTo("LIGHTGRAY") == 0) return java.awt.Color.lightGray;


        if (c.compareTo("MAGENTA") == 0) return java.awt.Color.magenta;


        if (c.compareTo("ORANGE") == 0) return java.awt.Color.orange;


        if (c.compareTo("PINK") == 0) return java.awt.Color.pink;


        if (c.compareTo("WHITE") == 0) return java.awt.Color.white;


        if (c.compareTo("YELLOW") == 0) return java.awt.Color.yellow;


        try {


            return java.awt.Color.decode(c);


        } catch (Exception e) {
            return java.awt.Color.black;
        }


    }

    // convert parameter to a Font


    /**
     * convert a string to a font. To be used when reading report from *.rep files.Internal use.
     */


    public static java.awt.Font convertFont(String f) {


        String[] items = convertList(f);


        if (items == null) return null;


        if (items.length < 3) return null;


        int s = java.awt.Font.PLAIN;


        if (items[1].compareTo("BOLD") == 0) s = java.awt.Font.BOLD;


        if (items[1].compareTo("ITALIC") == 0) s = java.awt.Font.ITALIC;


        try {


            return new java.awt.Font(items[0], s, new Integer(items[2]).intValue());


        }


        catch (Exception e) {
            return null;
        }


    }


    /**
     * convert a string to a list of double. To be used when reading report from *.rep files.Internal use.
     */


    public static double[] convertDoubleList(String items) {


        String[] iStr = convertList(items);


        double[] d = new double[iStr.length];


        for (int i = 0; i < d.length; i++) {


            d[i] = new Double(iStr[i]).doubleValue();


        }


        return d;


    }


    /**
     * convert a string to a list of String. To be used when reading report from *.rep files.Internal use.
     */


    public static String[] convertList(String items) {


        String[] itema = new String[500];


        int itemCount = 0;

        // count number of items


        int p = items.indexOf("|");


        while (p >= 0) {


            itema[itemCount++] = items.substring(0, p);


            items = items.substring(p + 1, items.length());


            p = items.indexOf("|");


        }


        if (items.compareTo("") != 0) itema[itemCount++] = items;


        if (itemCount == 0) return null;


        String[] result = new String[itemCount];


        for (int i = 0; i < itemCount; i++) result[i] = itema[i];


        return result;


    }


    /**
     * read and process line. To be used when reading report from *.rep files. Internal use.
     */


    public void importLine(String key, String val) {


        if (key.compareTo("FNAME") == 0) this.fieldName = val;


        if (key.compareTo("NAME") == 0) this.name = val;


        if (key.compareTo("HEIGHT") == 0) this.height = (new Double(val).doubleValue());


        if (key.compareTo("WIDTH") == 0) this.width = (new Double(val).doubleValue());


        if (key.compareTo("X") == 0) this.x = (new Double(val).doubleValue());


        if (key.compareTo("Y") == 0) this.y = (new Double(val).doubleValue());


        if (key.compareTo("CONSTANT") == 0) this.setConstant(val.compareTo("1") == 0);


        if (key.compareTo("TOOLTIP") == 0) this.tooltip = val;


        if (key.compareTo("ACTION") == 0) this.triggerAction = (val.compareTo("1") == 0);


        if (key.compareTo("VISIBLE") == 0) this.visible = (val.compareTo("1") == 0);


        if (key.compareTo("SELECTAB") == 0) this.selectable = (val.compareTo("1") == 0);


        if (key.compareTo("HTMLLINK") == 0) this.DHTMLLink = val;


    }


    /**
     * create image file
     */


    public void createFile(Image image, String file, String format) {


        if ((format.toUpperCase().compareTo("JPG") == 0) || (format.toUpperCase().compareTo("JPEG") == 0)) {


            try {

                // open file


                java.io.File f = new java.io.File(file);


                f.delete();


                java.io.FileOutputStream of = new java.io.FileOutputStream(f);

                // encode buffered image to a jpeg


                com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(of);


                encoder.encode((java.awt.image.BufferedImage) image);


                of.close();


            }


            catch (Exception e) {
                System.out.println(e.getMessage());
            }


        } // JPG


        if (format.toUpperCase().compareTo("GIF") == 0) {


            try {

                // download and install gif encoder from this address:

                // http://www.acme.com/resources/classes/Acme.tar.gz

                // open file


                java.io.File f = new java.io.File(file);


                f.delete();


                java.io.FileOutputStream of = new java.io.FileOutputStream(f);

                // encode image to a gif


                Class enClass = Class.forName("Acme.JPM.Encoders.GifEncoder");


                Class[] constructorParams = new Class[2];


                constructorParams[0] = Class.forName("java.awt.Image");


                constructorParams[1] = Class.forName("java.io.OutputStream");


                Object[] constructorObj = new Object[2];


                constructorObj[0] = image;


                constructorObj[1] = of;


                Object encoder = enClass.getConstructor(constructorParams).newInstance(constructorObj);


                Class[] encodeParams = new Class[0];


                Object[] encodeObj = new Object[0];


                enClass.getMethod("encode", encodeParams).invoke(encoder, encodeObj);

                //Acme.JPM.Encoders.GifEncoder encoder = new Acme.JPM.Encoders.GifEncoder(image,of);

                //encoder.encode();


                of.close();


            }


            catch (Exception e) {
                System.out.println(e.getMessage());
            }


        } // GIF


        if (format.toUpperCase().compareTo("PNG") == 0) {

            // before you use the PNG encoder you must install the com.bigfoot.bugar.image.PNGEncoder encoder

            // download from http://users.boone.net/wbrameld/pngencoder/


            try {

                // open file


                java.io.File f = new java.io.File(file);


                f.delete();


                java.io.FileOutputStream of = new java.io.FileOutputStream(f);

                // encode buffered image to a png

                // download png encoder from http://users.boone.net/wbrameld/pngencoder/


                Class enClass = Class.forName("com.bigfoot.bugar.image.PNGEncoder");


                Class[] constructorParams = new Class[2];


                constructorParams[0] = Class.forName("java.awt.Image");


                constructorParams[1] = Class.forName("java.io.OutputStream");


                Object[] constructorObj = new Object[2];


                constructorObj[0] = image;


                constructorObj[1] = of;


                Object encoder = enClass.getConstructor(constructorParams).newInstance(constructorObj);


                Class[] encodeParams = new Class[0];


                Object[] encodeObj = new Object[0];


                enClass.getMethod("encodeImage", encodeParams).invoke(encoder, encodeObj);

                //com.bigfoot.bugar.image.PNGEncoder en= new com.bigfoot.bugar.image.PNGEncoder(image,of);

                //en.encodeImage();


                of.close();


            }


            catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }  // PNG


    }


    /**
     * Getter for property widthpixel.
     *
     * @return Value of property widthpixel.
   */

  public int getWidthpixel() {

      return widthpixel;

  }  



  /**
   * Setter for property widthpixel.
   *
   * @param widthpixel New value of property widthpixel.
   */

  public void setWidthpixel(int widthpixel) {

      this.widthpixel = widthpixel;

  }  



}















