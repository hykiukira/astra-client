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


import java.text.SimpleDateFormat;


import java.text.DecimalFormat;

import java.util.*;


/**
 * RField will paint, dates, numbers and strings. See also RObject
 * <p/>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * <p/>
 * Note: you can set myField.setConstant(true) and use following system values<BR>
 * <p/>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * <p/>
 * [Page] : for current page (only in page header and footer pages)<BR>
 * <p/>
 * <p/>
 * <p/>
 * [Pages] : for number of pages (only in page header and footer pages)<BR> *
 * <p/>
 * <p/>
 * <p/>
 * [Date] : for current Date. See also RReport.setDateFormat() to change the date format.<BR>
 */


public class RField extends RObject


{


    /**
     * font to be used
     */


    public Font FontType = new Font("SansSerif", Font.PLAIN, 10);


    /**
     * color of the font
     */


    public java.awt.Color FontColor = java.awt.Color.black;


    /**
     * the field will increase its size if the data requires it
     */


    public boolean Expand = false;


    /**
     * rotate text
     */


    public int rotation = 0;


    /**
     * evaluation expresions? expresiona must start with "=". For example =3+4.
     */


    public boolean evaluateExpression = true;


    /**
     * the field will shrink if
     */


    public boolean Compress = false;


    /**
     * Valid values are ALIGN_LEFT, ALIGN_RIGHT and ALIGN_CENTER.
     */


    public int Align = ALIGN_LEFT;


    /**
     * Format string used for dates and numbers.Examples: "dd/MM/yyyy" for a date or "#####.00" for a number. Dates a formatted using the java.text. SimpleDateFormat and numbers using the java.text.DecimalFormat
     */


    public String format = "";


    /**
     * Format string used for dates.Examples: "dd/MM/yyyy"
     */


    public String dateFormat = "";


    /**
     * if true the words the text will be printed in several lines if needed. All lines must however fit into the current page.
     */


    public boolean multiLine = true;


    /**
     * maximum number of character per line
     */


    public int maxChars = 0;


    private double layoutHeight = -1;


    /**
     * supports HTML?
     */


    public boolean canHTML() {
        return true;
    }


    /**
     * Distamce between lines
     */


    protected int lineFeedSize = 0;


    /**
     * test splitted into lines
     */


    protected String[] lines;


    /**
     * load properties from definition file. Used by RReportImp.
     */


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("FCOLOR") == 0) FontColor = this.convertColor(val);


        if (key.compareTo("FONT") == 0) FontType = this.convertFont(val);


        if (key.compareTo("VALUE") == 0) this.setdefaultValue(val);


        if (key.compareTo("FORMAT") == 0) format = val;


        if (key.compareTo("EVAL") == 0) evaluateExpression = ((val.compareTo("1") == 0));


        if (key.compareTo("ROTATE") == 0) rotation = (new Integer(val).intValue());


        if (key.compareTo("ALIGN") == 0) Align = (new Integer(val).intValue());


        if (key.compareTo("MULTILIN") == 0) multiLine = ((val.compareTo("1") == 0));


        if (key.compareTo("EXPAND") == 0) Expand = ((val.compareTo("1") == 0));


        if (key.compareTo("COMPRESS") == 0) Compress = ((val.compareTo("1") == 0));


        if (key.compareTo("MAXCHAR") == 0) this.maxChars = (new Integer(val).intValue());


    }


    /**
     * convert value to HTML
     */


    public String toHTML(Object Value) {


        String s;


        Value = toString(Value);


        if (Value == null) return "> </td>";


        if (!(Value instanceof String)) return "> </td>";


        s = "";


        if (Align == ALIGN_RIGHT) s = s + " ALIGN=\"RIGHT\"";


        if (Align == ALIGN_CENTER) s = s + " ALIGN=\"CENTER\"";


        if (Align == ALIGN_LEFT) s = s + " ALIGN=\"LEFT\"";


        s = s + ">";


        s = s + "<font SIZE=\"" + super.fontSizetoHTML(FontType) + "\" FACE=\"" + FontType.getName() + "\" COLOR=\"" + super.fontColortoHTML(FontColor) + "\">" + super.fontValuetoHTML(FontType, (String) Value) + "</font></td>";


        return s;


    }


    protected Object toString(Object Value) {


        if (Value == null) Value = defaultValue;


        if (Value == null) return "";

// convert to string


        if (Value instanceof Integer) {


            if (format.compareTo("") == 0) Value = ((Integer) (Value)).toString();


            else {


                DecimalFormat df = new DecimalFormat(format);


                Value = df.format(((Integer) Value).intValue());


            }


        }


        if (Value instanceof Long) {


            if (format.compareTo("") == 0) Value = ((Long) (Value)).toString();


            else {


                DecimalFormat df = new DecimalFormat(format);


                Value = df.format(((Long) Value).longValue());


            }


        }


        if (Value instanceof java.util.Date) {


            if (dateFormat.length() == 0) dateFormat = format;


            if (dateFormat.compareTo("") == 0) dateFormat = "dd/MM/yy";


            try {


                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


                Value = formatter.format((java.util.Date) Value);


            } catch (Exception e1) {
                return null;
            }


        }


        if (Value instanceof java.math.BigDecimal) {


            Value = ((java.math.BigDecimal) (Value)).toString();


        }


        if (Value instanceof java.math.BigInteger) {


            Value = ((java.math.BigInteger) (Value)).toString();


        }


        if (Value instanceof Double) {


            if (format.compareTo("") == 0) Value = ((Double) (Value)).toString();


            else {


                DecimalFormat df = new DecimalFormat(format);


                Value = df.format(((Double) Value).doubleValue());


            }


        }


        if (Value instanceof Float) {


            if (format.compareTo("") == 0)


                Value = ((Float) (Value)).toString();


            else {


                DecimalFormat df = new DecimalFormat(format);


                Value = df.format(((Float) Value).floatValue());


            }


        }


        if (Value instanceof Boolean) {


            Value = ((Boolean) (Value)).toString();


        }

        // try just TOSTRING


        if (!(Value instanceof String)) {


            try {


                Value = Value.toString();


            }


            catch (Exception e) {
                return null;
            }


        }


        return (String) Value;


    }


    public int countLin;

    private int getCharsByLine(Graphics g, java.awt.Font font, int resolution) {

        int pixelWith = (int) Math.floor(width * resolution);

        int pixelheigthChar = g.getFontMetrics(font).getHeight();

        int pixelheigth = 0;

        int charsPerLine = 0;

        if (this.multiLine == true) {

            if (getruntimeValue() != null) {

                String text = getruntimeValue().toString();

                int lettersWidth = g.getFontMetrics().stringWidth("X");

                if (lettersWidth != 0) {

                    charsPerLine = (int) (pixelWith / lettersWidth);

                }

            }

        }

        return charsPerLine;

    }

    /*   private void calculHeigth(Graphics g,Object value){

        int charsPerLine=getCharsByLine(g);

                if(charsPerLine!=0){

                    int numberLine=countLine(getruntimeValue(), charsPerLine);

                    this.height=heightpixel*numberLine;

                }

    }*/

    private int countLine(String value, int linechars) {

        StringTokenizer token = new StringTokenizer(value, "\n");

        ArrayList array = new ArrayList();

        int countLine = 0;

        while (token.hasMoreElements()) {

            array.add(token.nextElement());

        }

        if (array.size() == 1) {

            countLine++;

            float number = value.length() / new Integer(linechars).floatValue();

            if (number > 1) {

                number = number + 0.5f;

                int ligeninc = Math.round(number);

                countLine = countLine + ligeninc;

            }

        } else
            countLine = array.size();

        /*    else{

            if(array!=null){

              for(int i=0;i<array.size();i++){

                  Object lineo=array.get(i);

                  if(lineo!=null){

                    //countLine++;

                    String line=lineo.toString();



                    float number=line.length()/new Integer(linechars).floatValue();

                    if(number>1){

                        //number

                        number=number+0.5f;

                        int ligeninc=Math.round(number);

                        countLine=countLine+ligeninc;

                    }

                    else

                        countLine++;

                  }

              }

            }

        }*/

        return countLine;

        //return countLine;

        //   if((countLine/2)<2)
        //       return (countLine/2)+2;
        //   else
        //   return (countLine/2)+5;

    }

    public void calculHeigth(Graphics g, int resolution, Font font) {

        int charsPerLine = getCharsByLine(g, font, resolution);

        int pixelheigthChar = g.getFontMetrics(font).getHeight();

        double pixelheigth = 0;

        if (charsPerLine != 0) {

            if (getruntimeValue() != null) {

                int numberLine = countLine(getruntimeValue().toString(), charsPerLine);

                pixelheigth = pixelheigthChar * numberLine;

                this.height = (double) (pixelheigth / resolution);

                // this.height=pixelheigth;
                //his.height=heightpixel*numberLine;

            }

        }

    }

    public void print(Graphics g, double px, double py, Object Value) {


        int fontHeight;


        int fontWidth;


        int marginx = 0;


        int marginy = 0;


        String lin;


        String rest;


        countLin = 0;


        int charsPerLine = 0;


        java.util.Vector linesVector = new java.util.Vector(5, 5);


        this.lines = null;


        String jversion = java.lang.System.getProperty("java.version");


        Value = toString(Value);


        if (Value instanceof String) {


            lin = (String) Value;


            if (FontType != null) g.setFont(FontType);


            g.setColor(FontColor);


            lin = "";


            rest = (String) Value;


            fontHeight = g.getFontMetrics().getHeight();


            this.lineFeedSize = fontHeight;


            charsPerLine = (int) (widthpixel / g.getFontMetrics().stringWidth("X"));


            fontWidth = g.getFontMetrics().stringWidth(rest);

            // fits in one line


            if ((widthpixel) >= fontWidth) charsPerLine = 0;


            boolean lineFeedFound = false;


            int charsInThisLine = charsPerLine;

            // look for "\n"


            if (rest.indexOf("\n") >= 0)


                if ((rest.indexOf("\n") <= charsPerLine) || (charsPerLine == 0)) {

                    lineFeedFound = true;

                    charsInThisLine = rest.indexOf("\n") + 1;

                }

            // do not use multiline


            if (!this.multiLine) charsPerLine = rest.length() + 1;

            // print text in several lines


            while (true) {

                //System.out.println(rest);


                if (((rest.length() > charsPerLine) && (charsPerLine > 0)) || (lineFeedFound)) {


                    lin = rest.substring(0, charsInThisLine);

                    // find space in lin.


                    if (lineFeedFound) {


                        rest = rest.substring(lin.length(), rest.length());


                        if (lin.length() > 0) lin = lin.substring(0, lin.length() - 1);


                    } else {


                        if (lin.lastIndexOf(" ") > 0) {


                            lin = lin.substring(0, lin.lastIndexOf(" ") + 1);


                        }


                        rest = rest.substring(lin.length(), rest.length());


                    }


                    lineFeedFound = false;


                    charsInThisLine = charsPerLine;

                    // look for "\n"


                    if (rest.indexOf("\n") >= 0)


                        if ((rest.indexOf("\n") <= charsPerLine) || (charsPerLine == 0)) {


                            charsInThisLine = rest.indexOf("\n") + 1;


                            lineFeedFound = true;


                        }


                } else


                {


                    lin = rest;


                    rest = "";


                }


                fontWidth = g.getFontMetrics().stringWidth(lin);


                if ((widthpixel) >= fontWidth) {


                    marginx = (int) ((widthpixel - fontWidth) / 2);


                    if (Align == ALIGN_RIGHT) marginx = (int) ((widthpixel - fontWidth) - 4);


                    if (Align == ALIGN_LEFT) marginx = (int) 4;


                } else {
                    marginx = (int) 4;
                }


                countLin++;


                linesVector.addElement(lin);

                // System.out.println("font: "+fontHeight+" y:"+py);

                // System.out.println(lin+ " "+(px+marginx)+ " "+(py+(fontHeight*countLin)));


                if ((rotation == 0) || (jversion.indexOf("1.1") == 0))
                    g.drawString(lin, (int) px + marginx, (int) (py + (fontHeight * countLin)));


                else rotateText(g, lin, (int) px + marginx, (int) (py + (fontHeight * countLin)), fontHeight);

                // no more lines, exit


                if (rest.compareTo("") == 0) break;

                // exit if there is no more place and we dont want to expand


                if ((!Expand) && ((heightpixel) < (fontHeight * (countLin + 1)))) break;

                // update height


                if ((heightpixel) < (fontHeight * (countLin + 1))) heightpixel = (fontHeight * (countLin + 1));


            }


            if ((Compress) && ((heightpixel) > (fontHeight * (countLin + 1))))
                heightpixel = (fontHeight * (countLin + 1));


            this.lines = new String[linesVector.size()];


            for (int k = 0; k < linesVector.size(); k++) lines[k] = (String) linesVector.elementAt(k);


        } // if string

        //  calculHeigth(g,Value);

    } // print


    /**
     * paints rotated text
     */


    private void rotateText(Graphics g, String lin, int x, int y, int fontHeight) {


        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;


        java.awt.geom.AffineTransform at = new java.awt.geom.AffineTransform();


        int rotationPoint = widthpixel;


        if (rotationPoint < heightpixel) rotationPoint = heightpixel;

        // rotate, anchor point is center


        if (rotation == 90) at.rotate(Math.toRadians(1 * rotation), (int) x + fontHeight, (int) (y - fontHeight));


        if (rotation == 180)
            at.rotate(Math.toRadians(1 * rotation), (int) x + (this.widthpixel / 2), (int) (y - fontHeight) + (this.heightpixel / 2));


        if (rotation == 270)
            at.rotate(Math.toRadians(1 * rotation), (int) x + (this.widthpixel / 2), (int) (y - fontHeight) + (this.widthpixel / 2));

        // get current transform


        java.awt.geom.AffineTransform tmpAff = g2.getTransform();

        // set rotation


        g2.transform(at);


        g2.drawString(lin, x, y);

        // restore old transform


        g2.setTransform(tmpAff);


    }







}



