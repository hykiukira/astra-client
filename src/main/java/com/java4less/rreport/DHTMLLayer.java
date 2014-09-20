package com.java4less.rreport;

//  RReport

//  Copyright (C)

//

//  Java4Less@Confluencia.net

//  All rights reserved

//

// Adquisition , use and distribution of this codle is subject to restriction:

//  - You may modify the source code in order to adapt it to your needs.

//  - You may not copy and paste any code into external files.

//  - Redistribution of this ( or a modified version) source code is prohibited. You may only redistribute compiled versions.

//  - You may redistribute the compiled version as part of your application, not a new java component with the same purpose as this one.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material


/**
 * this class is used by RReport for exporting to DHTML. RReport calls the print() method in order to create the DHTML code for all objects in the report.
 */

public class DHTMLLayer {

    // html output

    String output = "";

    private String crlf = "" + (char) 13 + (char) 10;

    private int pageOffset = 0;


    protected String imagesDirectory = "";

    protected String imagesHTMLPrefix = "";

    protected String imagesFormat = "JPG";


    int elementCounter = 0;

    int pageHeight = 0;


    /**
     * creates a DHTML layer. W and H are the page size in pixels.
     */

    public DHTMLLayer(int w, int h) {

        pageHeight = h;

        pageOffset = 0;

    }


    /**
     * initializes a new page
     */

    public void newPage() {

        output = "";

    }


    public void newPage(boolean multipleFiles) {

        if (!multipleFiles)
            pageOffset = pageOffset + pageHeight; // we use only 1 file, add new page at the bottom by increasing the page offset

        output = "";

        //System.out.println("A: "+pageOffset);

    }


    /**
     * get DHTML code for current page
     */

    public String getOutput() {
        return output;
    }


    /**
     * add DHTML code for the RObject "o" at posisiotn x and y.
     */

    public void print(RObject o, double x, double y, Object Value) {


        elementCounter++;


        if (o instanceof RLine) {

            this.printLine((RLine) o, x, y, Value);

            return;

        }


        if (o instanceof RRectangle) {

            this.printRectangle((RRectangle) o, x, y, Value);

            return;

        }


        if (o instanceof RField) {

            this.printField((RField) o, x, y, Value);

            return;

        }


        if (o instanceof RCombo) {

            this.printCombo((RCombo) o, x, y, Value);

            return;

        }

        // if (o instanceof RBarcoding) {

        //   this.printImage(((RBarcoding) o).getImage(),o,x,y,o.heightpixel);

        // return;

        //}

        //  if (o instanceof RGraph) {

        //    this.printImage(((RGraph) o).getImage(),o,x,y,o.heightpixel);

        //  return;

        //}


        if (o instanceof RUserComponent) {

            this.printImage(((RUserComponent) o).getImage(), o, x, y, o.heightpixel);

            return;

        }


        if (o instanceof RCheck) {

            this.printCheck((RCheck) o, x, y, Value);

            return;

        }


        if (o instanceof RPicture) {

            this.printPicture((RPicture) o, x, y, Value);

            return;

        }


    }


    /**
     * print DHTML output for Checkbox
     */

    protected void printCheck(RCheck RCh, double px, double py, Object Value) {


        if (Value == null) Value = new Boolean(true);


        if (Value instanceof Boolean) {

            Boolean b = (Boolean) Value;


            output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RCh.widthpixel + "px; height:" + RCh.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:#000000; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000; \">" + crlf;


            if (RCh.DHTMLLink.length() > 0) output = output + "<a href=\"" + RCh.DHTMLLink + "\">";


            if ((b.booleanValue()) && (RCh.imageHTMLTrue.length() > 0))
                output = output + "<IMG src=\"" + getImageHTMLUrl(imagesHTMLPrefix, RCh.imageHTMLTrue) + "\" border=0></IMG>" + crlf;

            if ((!b.booleanValue()) && (RCh.imageHTMLFalse.length() > 0))
                output = output + "<IMG src=\"" + getImageHTMLUrl(imagesHTMLPrefix, RCh.imageHTMLFalse) + "\" border=0></IMG>" + crlf;


            if (RCh.DHTMLLink.length() > 0) output = output + "</a>";


            output = output + "</DIV>" + crlf;


        }

    }


    /**
     * convert RRectangle to DHTML
     */

    protected void printRectangle(RRectangle RRec, double px, double py, Object Value) {

        String back = "";

        if (RRec.fill)

            back = "background-color:" + RRec.fontColortoHTML(RRec.fillColor) + ";";

//   System.out.println(" p "+pageOffset+" "+ pageHeight +" "+ py    );


        output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RRec.widthpixel + "px; height:" + RRec.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:" + RRec.fontColortoHTML(RRec.color) + "; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:1px 1px 1px 1px; border-color:" + RRec.fontColortoHTML(RRec.color) + ";" + back + " \">" + crlf;

        output = output + "</DIV>" + crlf;

    }


    /**
     * convert RLine to DHTML
     */

    protected void printLine(RLine Line, double px, double py, Object Value) {


        if (Line.lineDirection == Line.HORIZONTAL)

            output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + Line.widthpixel + "px; height:1px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:" + Line.fontColortoHTML(Line.color) + "; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:1px 0px 0px 0px; border-color:" + Line.fontColortoHTML(Line.color) + "; \"></DIV>" + crlf;

        else

            output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:1px; height:" + Line.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:" + Line.fontColortoHTML(Line.color) + "; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:0px 1px 0px 0px; border-color:" + Line.fontColortoHTML(Line.color) + "; \"></DIV>" + crlf;


    }


    /**
     * print DHTML output for Field
     */

    protected void printField(RField RFi, double px, double py, Object Value) {


        if (RFi.lines == null) return;


        for (int i = 0; i < RFi.lines.length; i++) {

            // String lin=(String) Value;

            String lin = RFi.lines[i];


            String alignment = "RIGHT";

            if (RFi.Align == RField.ALIGN_CENTER) alignment = "CENTER";

            if (RFi.Align == RField.ALIGN_LEFT) alignment = "LEFT";


            String fWeight = "normal";

            if (RFi.FontType.isBold()) fWeight = "bold";

            String fStyle = "normal";

            if (RFi.FontType.isItalic()) fStyle = "italic";

            output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RFi.widthpixel + "px; height:" + RFi.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - (py - (i * RFi.lineFeedSize)))) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:" + RFi.fontColortoHTML(RFi.FontColor) + "; font-family:" + RFi.FontType.getFamily() + "; font-size:" + RFi.FontType.getSize() + "px; font-weight:" + fWeight + "; font-style:" + fStyle + "; text-align:" + alignment + "; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000;\">";


            if (RFi.DHTMLLink.length() > 0) output = output + "<a href=\"" + RFi.DHTMLLink + "\">";

            output = output + lin;

            if (RFi.DHTMLLink.length() > 0) output = output + "</a>";


            output = output + "</DIV>" + crlf;

        }

    }


    /**
     * print DHTML output for Combo
     */

    protected void printCombo(RCombo RCo, double px, double py, Object Value) {


        int i;

        int keyWidth = 0;

        int keyHeight = 0;

        String k;

        int marginx = 0;

        int marginy = 0;


        if (Value == null) Value = RCo.getdefaultValue();


        if (Value == null) return;


        if (Value instanceof Integer) Value = ((Integer) Value).toString();


        if (Value instanceof Long) Value = ((Long) Value).toString();


        if (Value instanceof Double) Value = ((Double) Value).toString();


        if (!(Value instanceof String)) return;


        k = (String) Value;


        keyWidth = 0;

        keyHeight = 0;

        // look for the key

        for (i = 0; i < RCo.Keys.length; i++) {

            if (k.compareTo(RCo.Keys[i]) == 0) break;

        }


        if (i >= RCo.Keys.length) return; // key not found


        if (i >= RCo.Values.length) return; // check errors


        if (RCo.Values[i] == null) return;

        try {

            // elements of the combo are strings

            if (RCo.Values[i] instanceof String) {


                String lin = (String) RCo.Values[i];

                /*    if (RCo.printKey) {

                      com.lowagie.text.pdf.BaseFont bf= this.getFont(RCo.KeyFontType);

                      keyWidth=(int) (bf.getWidth(k)/1);



                      contentByte.beginText();

                      contentByte.setColorStroke(RCo.KeyFontColor);

                      contentByte.setColorFill(RCo.KeyFontColor);

                      contentByte.setFontAndSize(bf, RCo.KeyFontType.getSize());



                       if (RCo.Align == RField.ALIGN_RIGHT)

                            contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT,  k,(int) px + RCo.widthpixel-sWidth , (int) py-RCo.heightpixel, 0);

                       if (RCo.Align == RField.ALIGN_LEFT)

                            contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT,  k,(int) px, (int) py-RCo.heightpixel, 0);



                      contentByte.endText();



                }*/


                String alignment = "RIGHT";

                if (RCo.Align == RCombo.ALIGN_CENTER) alignment = "CENTER";

                if (RCo.Align == RCombo.ALIGN_LEFT) alignment = "LEFT";


                String fWeight = "normal";

                if (RCo.FontType.isBold()) fWeight = "bold";

                String fStyle = "normal";

                if (RCo.FontType.isItalic()) fStyle = "italic";

                output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RCo.widthpixel + "px; height:" + RCo.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:" + RCo.fontColortoHTML(RCo.FontColor) + "; font-family:" + RCo.FontType.getFamily() + "; font-size:" + RCo.FontType.getSize() + "px; font-weight:" + fWeight + "; font-style:" + fStyle + "; text-align:" + alignment + "; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000;\">";


                if (RCo.DHTMLLink.length() > 0) output = output + "<a href=\"" + RCo.DHTMLLink + "\">";

                output = output + lin;

                if (RCo.DHTMLLink.length() > 0) output = output + "</a>";

                output = output + "</DIV>" + crlf;


            }

            // elements of the combo are images

            if (RCo.Values[i] instanceof RImageFile) {


                RImageFile I = ((RImageFile) RCo.Values[i]);

/*

            if (RCo.printKey) {



                  com.lowagie.text.pdf.BaseFont bf= this.getFont(RCo.KeyFontType);



                  keyWidth=(int) (bf.getWidth(k)/1);



                  contentByte.beginText();

                  contentByte.setColorStroke(RCo.KeyFontColor);

                  contentByte.setColorFill(RCo.KeyFontColor);

                  contentByte.setFontAndSize(bf, RCo.KeyFontType.getSize());



                 if (RCo.Align == RField.ALIGN_LEFT)

                         contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT,  k,(int) px, (int) py-RCo.heightpixel, 0);



                  contentByte.endText();



            }

*/


                output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RCo.widthpixel + "px; height:" + RCo.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:#000000; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000; \">" + crlf;


                if (RCo.DHTMLLink.length() > 0) output = output + "<a href=\"" + RCo.DHTMLLink + "\">";

                output = output + "<IMG src=\"" + I.getUrlLocation() + "\" border=0></IMG>" + crlf;

                if (RCo.DHTMLLink.length() > 0) output = output + "</a>";


                output = output + "</DIV>" + crlf;


            }


        } catch (Exception e) {

            System.out.println("Error adding HTML Combo " + e.getMessage());

        }
        ;

    }


    /**
     * DHTML output for Picture
     */

    protected void printPicture(RPicture RPic, double px, double py, Object Value) {


        output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + RPic.widthpixel + "px; height:" + RPic.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:#000000; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000; \">" + crlf;

        if (RPic.DHTMLLink.length() > 0) output = output + "<a href=\"" + RPic.DHTMLLink + "\">";

        String size = "";

        if (RPic.scale) size = " WIDTH= " + RPic.widthpixel + " HEIGHT=" + RPic.heightpixel + " ";

        output = output + "<IMG src=\"" + getImageHTMLUrl(imagesHTMLPrefix, RPic.ImageHTMLAddress) + "\" " + size + "border=0 alt=\"" + RPic.tooltip + "\"></IMG>" + crlf;

        if (RPic.DHTMLLink.length() > 0) output = output + "</a>";

        output = output + "</DIV>" + crlf;


    }


    /**
     * DHTML output image
     */

    private void printImage(java.awt.Image image, RObject obj, double px, double py, int height) {


        String file = obj.name + elementCounter + "." + imagesFormat;


        obj.createFile(image, imagesDirectory + file, imagesFormat);


        output = output + "<DIV ID=\"Element" + elementCounter + "\" STYLE=\"position:absolute; width:" + obj.widthpixel + "px; height:" + obj.heightpixel + "px; left:" + px + "px; top:" + (pageOffset + ((int) pageHeight - py)) + "px; margin:0px 0px 0px 0px; padding:0px 0px 0px 0px; color:#000000; font-family:Dialog; font-size:1px; font-weight:bold; font-style:normal; text-align:CENTER; vertical-align:TOP; border-style:solid; border-width:0px 0px 0px 0px; border-color:#000000; \">" + crlf;

        if (obj.DHTMLLink.length() > 0) output = output + "<a href=\"" + obj.DHTMLLink + "\">";

        output = output + "<IMG src=\"" + getImageHTMLUrl(imagesHTMLPrefix, file) + "\" border=0 height=" + image.getHeight(null) + " width=" + image.getWidth(null) + " alt=\"" + obj.tooltip + "\" ></IMG>" + crlf;

        if (obj.DHTMLLink.length() > 0) output = output + "</a>";

        output = output + "</DIV>" + crlf;


    }


    private String getImageHTMLUrl(String prefix, String file) {

        // if this is a absolute file address, do not add prefix

        if (file.toUpperCase().startsWith("HTTP://")) return file;

        if (file.toUpperCase().startsWith("/")) return file;

        if (file.toUpperCase().startsWith("\\")) return file;


        return prefix + file;


    }


}