package com.java4less.rreport;

//  RReport

//

//  Java4Less@Confluencia.net


import com.lowagie.text.*;

import com.lowagie.text.pdf.*;

import com.lowagie.text.pdf.BaseFont;


/**
 * this class is used by RReport for exporting to PDF. RReport calls the print() method in order to create the PDF code for all objects in the report.<BR>
 * <p/>
 * <BR>
 * <p/>
 * This class using the PDF Library called iText. You can download it here: <A ref=http://www.lowagie.com/iText>http://www.lowagie.com/iText</A><BR><BR>
 * <p/>
 * After downloading it you must copy iText.jar to RReport's directory and make sure it is included in your classpath.
 */

public class PDFLayer {

    // directory where ttf files are found. Default is  .\\fonts\\.

    public static String fontsDirectory = ".\\fonts\\";


    Document PDFDocument;

    com.lowagie.text.pdf.PdfContentByte contentByte = null;


    /**
     * initializes layer and the pdf document with the given size.
     */

    public PDFLayer(String file, java.io.OutputStream os, int w, int h, String title) throws Exception

    {

        //System.out.println("Initializing PDFLayer");

        try {

            PDFDocument = new Document(new Rectangle(w, h), 0, 0, 0, 0);

            //PDFDocument=new Document(PageSize.A4),0,0,0,0);


            PdfWriter writer = null;


            if (os != null) writer = PdfWriter.getInstance(PDFDocument, os);

            else writer = PdfWriter.getInstance(PDFDocument, new java.io.FileOutputStream(file));


            contentByte = writer.getDirectContent();


        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());

        }
        ;


        PDFDocument.addAuthor("RReport");

        PDFDocument.addSubject(title);


        PDFDocument.open();


    }


    /**
     * forces a new page in the PDF document
     */

    public void newPage() throws Exception {

        PDFDocument.newPage();

    }


    /**
     * close Document
     */

    public void close() {

        try {

            PDFDocument.close();

        } catch (Exception e) {

            System.out.println("Error Closing PDF " + e.getMessage());

        }

    }


    /**
     * add object to the PDF Document at the given position.
     */

    public void print(RObject o, double x, double y, Object Value) {


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

        if (o instanceof RCheck) {

            this.printCheck((RCheck) o, x, y, Value);

            return;

        }

        if (o instanceof RCombo) {

            this.printCombo((RCombo) o, x, y, Value);

            return;

        }

        // if (o instanceof RBarcoding) {

        //   this.printImage(((RBarcoding) o).getImage(),x,y,o.heightpixel);

        // return;

        //}


        if (o instanceof RUserComponent) {

            this.printImage(((RUserComponent) o).getImage(), x, y, o.heightpixel);

            return;

        }

        // if (o instanceof RGraph) {

        //   this.printImage(((RGraph) o).getImage(),x,y,o.heightpixel);

        // return;

        //}


        if (o instanceof RPicture) {

            this.printPicture((RPicture) o, x, y, Value);

            return;

        }


    }


    /**
     * produce PDF output for Rectangle
     */

    protected void printRectangle(RRectangle RRec, double px, double py, Object Value) {


        contentByte.setLineWidth(1f);


        if (RRec.fillColor != null)

            if (!RRec.fillColor.equals(java.awt.Color.white)) {

                contentByte.setColorFill(RRec.fillColor);

                this.contentByte.moveTo((int) px, (int) py);

                contentByte.lineTo((int) px, (int) py - RRec.heightpixel);

                contentByte.lineTo((int) px + RRec.widthpixel, (int) py - RRec.heightpixel);

                contentByte.lineTo((int) px + RRec.widthpixel, (int) py);

                contentByte.lineTo((int) px, (int) py);

                contentByte.fillStroke();

            }


        contentByte.setLineDash(0);

        if (RRec.Style != null) {

            if (RRec.Style.getType() == RLineStyle.LINE_DASHED) contentByte.setLineDash(4, 4, 0);

            if (RRec.Style.getType() == RLineStyle.LINE_DOTS) contentByte.setLineDash(2, 2, 0);

        }


        if (RRec.Style != null) contentByte.setColorStroke(RRec.Style.Color);

        else contentByte.setColorStroke(java.awt.Color.black);


        this.contentByte.moveTo((int) px, (int) py);

        contentByte.lineTo((int) px, (int) py - RRec.heightpixel);

        contentByte.lineTo((int) px + RRec.widthpixel, (int) py - RRec.heightpixel);

        // System.out.println("y: "+py+ " h: "+ RRec.heightpixel+ " = "+(py+RRec.heightpixel));

        contentByte.lineTo((int) px + RRec.widthpixel, (int) py);

        contentByte.lineTo((int) px, (int) py);

        contentByte.stroke();


    }


    /**
     * PDF output image
     */

    protected void printImage(java.awt.Image image, double px, double py, int height) {


        com.lowagie.text.Image pfgIm = null;


        try {


            pfgIm = Image.getInstance(image, null);


            if (pfgIm != null) {

                pfgIm.setAbsolutePosition((int) px, (int) py - height);

                ((com.lowagie.text.Document) PDFDocument).add(pfgIm);

            }


        } catch (Exception e) {

            System.out.println("Error adding image: " + e.getMessage());

        }
        ;

    }


    /**
     * PDF output for Picture
     */

    protected void printPicture(RPicture RPic, double px, double py, Object Value) {

//       System.out.println("image pdf: " + py +" "+py);

/*

       String imageStr=RPic.ImageHTMLAddress;



        if (Value!=null )

                  if (Value instanceof RImageFile) {

                    imageStr=((RImageFile) Value).getUrlLocation();

        }





         if (imageStr==null) return;

         if (imageStr.length()==0) return;

*/

        com.lowagie.text.Image pfgIm = null;


        try {


            java.awt.Image image = null;


            if (Value instanceof RImageFile) image = ((RImageFile) Value).getImage();

            if (Value instanceof java.awt.Image) image = (java.awt.Image) Value;


            if (image == null) System.out.println("Image could not be loaded.");

            // scale

            //String v=java.lang.System.getProperty("java.version");

            //if ((v.indexOf("1.2")>=0) || (v.indexOf("1.3")>=0)  || (v.indexOf("1.4")>=0))

            //       if (RPic.scale) image=image.getScaledInstance(widthpixel,heightpixel,Image.SCALE_DEFAULT);


            pfgIm = Image.getInstance(image, null);


            if (pfgIm != null) {

                if (RPic.scale) pfgIm.scaleToFit(RPic.widthpixel, RPic.heightpixel);

                pfgIm.setAbsolutePosition((int) px, (int) py - RPic.heightpixel);

                ((com.lowagie.text.Document) PDFDocument).add(pfgIm);

            }


        } catch (Exception e) {

            System.out.println("Error adding PDF Picture: " + e.getMessage());

        }
        ;

    }


    /**
     * produce PDF output for Line
     */

    protected void printLine(RLine RLi, double px, double py, Object Value) {


        int tmpH = RLi.heightpixel;

        int tmpW = RLi.widthpixel;


        if (RLi.lineDirection == RLine.HORIZONTAL) tmpH = 0;

        if (RLi.lineDirection == RLine.VERTICAL) tmpW = 0;


        contentByte.setLineDash(0);

        if (RLi.Style != null) {

            if (RLi.Style.getType() == RLineStyle.LINE_DASHED) contentByte.setLineDash(4, 4, 0);

            if (RLi.Style.getType() == RLineStyle.LINE_DOTS) contentByte.setLineDash(2, 2, 0);

        }


        if (RLi.Style != null) contentByte.setColorStroke(RLi.Style.Color);

        else contentByte.setColorStroke(java.awt.Color.black);

        contentByte.setLineWidth(1f);

        this.contentByte.moveTo((int) px, (int) py);

        contentByte.lineTo((int) (px + tmpW), (int) (py - tmpH));

        contentByte.stroke();


    }


    /**
     * print PDF output for Field
     */

    protected void printField(RField RFi, double px, double py, Object Value) {


        try {


            if (RFi.lines == null) return;


            for (int i = 0; i < RFi.lines.length; i++) {

                //String lin=(String) Value;

                String lin = RFi.lines[i];


                com.lowagie.text.pdf.BaseFont bf = this.getFont(RFi.FontType);

                //py=py-RFi.heightpixel;


                py = py - RFi.lineFeedSize;

                if (RFi.rotation > 0) {

                    // we create a PdfTemplate for text rotation

                    com.lowagie.text.pdf.PdfTemplate template = contentByte.createTemplate(RFi.widthpixel, RFi.heightpixel);

                    template.beginText();

                    template.setColorStroke(RFi.FontColor);

                    template.setColorFill(RFi.FontColor);

                    template.setFontAndSize(bf, RFi.FontType.getSize());


                    if (RFi.Align == RField.ALIGN_RIGHT)

                        template.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT, lin, (int) RFi.widthpixel, (int) 0, 0);

                    if (RFi.Align == RField.ALIGN_CENTER)

                        template.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER, lin, (int) (RFi.widthpixel / 2), (int) 0, 0);

                    if (RFi.Align == RField.ALIGN_LEFT)

                        template.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT, lin, (int) 0, (int) 0, 0);


                    template.endText();

                    //contentByte.addTemplate(template,(int) px,(int) py);

                    // rotate the template 90 degrees

                    //System.out.println("size "+RFi.widthpixel +" "+ RFi.heightpixel);

                    //System.out.println("pos "+px +" "+ py);

                    //System.out.println("rot "+RFi.rotation);

                    if (RFi.rotation == 270)
                        contentByte.addTemplate(template, 0, 1, -1, 0, (int) px + RFi.heightpixel, (int) py);

                    // rotate the template 180 degrees

                    if (RFi.rotation == 180)
                        contentByte.addTemplate(template, -1, 0, 0, -1, (int) px + RFi.widthpixel, (int) py);

                    // rotate the template 270 degrees

                    if (RFi.rotation == 90)
                        contentByte.addTemplate(template, 0, -1, 1, 0, (int) px + RFi.heightpixel, (int) py);


                } else {


                    contentByte.beginText();

                    contentByte.setColorStroke(RFi.FontColor);

                    contentByte.setColorFill(RFi.FontColor);

                    contentByte.setFontAndSize(bf, RFi.FontType.getSize());


                    if (RFi.Align == RField.ALIGN_RIGHT)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT, lin, (int) px + RFi.widthpixel, (int) py, 0);

                    if (RFi.Align == RField.ALIGN_CENTER)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER, lin, (int) px + (RFi.widthpixel / 2), (int) py, 0);

                    if (RFi.Align == RField.ALIGN_LEFT)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT, lin, (int) px, (int) py, 0);


                    contentByte.endText();

                }


            }


        } catch (Exception e) {

            System.out.println("Error adding PDF Field " + e.getMessage());

        }
        ;


    }


    /**
     * print PDF output for Checkbox
     */

    protected void printCheck(RCheck RCh, double px, double py, Object Value) {


        if (Value == null) Value = new Boolean(true);


        if (Value instanceof Boolean) {


            Boolean b = (Boolean) Value;


            com.lowagie.text.Image pfgIm = null;


            try {

                // TRUE

                if ((b.booleanValue()) && (RCh.getTrueImage() != null))

                    if (RCh.getTrueImage().getLoaded())

                        pfgIm = Image.getInstance(RCh.getTrueImage().getImage(), null);

                // FALSE

                if ((!b.booleanValue()) && (RCh.getFalseImage() != null))

                    if (RCh.getFalseImage().getLoaded())

                        pfgIm = Image.getInstance(RCh.getFalseImage().getImage(), null);


                if (pfgIm != null) {

                    pfgIm.setAbsolutePosition((int) px, (int) py - RCh.heightpixel);

                    ((com.lowagie.text.Document) PDFDocument).add(pfgIm);

                }

            } catch (Exception e) {

                System.out.println("Error adding PDF check: " + e.getMessage());

            }
            ;


        }

    }


    /**
     * print PDF output for Combo
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


                String s = (String) RCo.Values[i];


                com.lowagie.text.pdf.BaseFont tbf = this.getFont(RCo.FontType);

                int sWidth = (int) (tbf.getWidth(s) / 1);


                if (RCo.printKey) {

                    com.lowagie.text.pdf.BaseFont bf = this.getFont(RCo.KeyFontType);

                    keyWidth = (int) (bf.getWidth(k) / 1);


                    contentByte.beginText();

                    contentByte.setColorStroke(RCo.KeyFontColor);

                    contentByte.setColorFill(RCo.KeyFontColor);

                    contentByte.setFontAndSize(bf, RCo.KeyFontType.getSize());


                    if (RCo.Align == RField.ALIGN_RIGHT)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT, k, (int) px + RCo.widthpixel - sWidth, (int) py - RCo.heightpixel, 0);

//                 if (RCo.Align == RField.ALIGN_CENTER)

//                      contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER,  lin,(int) px+(RCo.widthPixel/2)-(sWdith/2), (int) py-RCo.heightpixel, 0);

                    if (RCo.Align == RField.ALIGN_LEFT)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT, k, (int) px, (int) py - RCo.heightpixel, 0);


                    contentByte.endText();


                }


                contentByte.beginText();

                contentByte.setColorStroke(RCo.FontColor);

                contentByte.setColorFill(RCo.FontColor);

                contentByte.setFontAndSize(tbf, RCo.FontType.getSize());


                if (RCo.Align == RField.ALIGN_RIGHT)

                    contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT, s, (int) px + RCo.widthpixel, (int) py - RCo.heightpixel, 0);

                if (RCo.Align == RField.ALIGN_CENTER)

                    contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER, s, (int) px + (RCo.widthpixel / 2), (int) py - RCo.heightpixel, 0);

                if (RCo.Align == RField.ALIGN_LEFT)

                    contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT, s, (int) px + keyWidth, (int) py - RCo.heightpixel, 0);


                contentByte.endText();


            }

            // elements of the combo are images

            if ((RCo.Values[i] instanceof RImageFile) || (RCo.Values[i] instanceof java.awt.Image)) {


                java.awt.Image image = null;


                if (RCo.Values[i] instanceof RImageFile) image = ((RImageFile) RCo.Values[i]).getImage();

                if (RCo.Values[i] instanceof java.awt.Image) image = (java.awt.Image) RCo.Values[i];


                if (RCo.printKey) {


                    com.lowagie.text.pdf.BaseFont bf = this.getFont(RCo.KeyFontType);


                    keyWidth = (int) (bf.getWidth(k) / 1);


                    contentByte.beginText();

                    contentByte.setColorStroke(RCo.KeyFontColor);

                    contentByte.setColorFill(RCo.KeyFontColor);

                    contentByte.setFontAndSize(bf, RCo.KeyFontType.getSize());


                    if (RCo.Align == RField.ALIGN_LEFT)

                        contentByte.showTextAligned(com.lowagie.text.pdf.PdfContentByte.ALIGN_LEFT, k, (int) px, (int) py - RCo.heightpixel, 0);


                    contentByte.endText();


                }

                // image

                com.lowagie.text.Image pfgIm = Image.getInstance(image, null);


                if (pfgIm != null) {

                    pfgIm.setAbsolutePosition((int) px + keyWidth, (int) py);

                    ((com.lowagie.text.Document) PDFDocument).add(pfgIm);

                }

            }


        } catch (Exception e) {

            System.out.println("Error adding PDF Combo " + e.getMessage());

        }
        ;


    }


    private com.lowagie.text.pdf.BaseFont getFont(java.awt.Font f) {


        String fontNameId = com.lowagie.text.pdf.BaseFont.COURIER;

        int size = 10;

        int style = com.lowagie.text.Font.NORMAL;

        boolean isType1 = false;


        if (f != null) {

            //System.out.println("Font Family: "+f.getFamily()+" Font Name: "+f.getName()+" Size: "+f.getSize());

            size = f.getSize();

            String fontName = f.getName();


            if (fontName.toLowerCase().indexOf("courier") >= 0) {

                fontNameId = com.lowagie.text.pdf.BaseFont.COURIER;

                if (f.isBold()) fontNameId = com.lowagie.text.pdf.BaseFont.COURIER_BOLD;

                if (f.isItalic()) fontNameId = com.lowagie.text.pdf.BaseFont.COURIER_OBLIQUE;

                if ((f.isBold()) && (f.isItalic())) fontNameId = com.lowagie.text.pdf.BaseFont.COURIER_BOLDOBLIQUE;

                //System.out.println("COURIER");

                isType1 = true;

            }

            if (fontName.toLowerCase().indexOf("helvetica") >= 0) {

                fontNameId = com.lowagie.text.pdf.BaseFont.HELVETICA;

                if (f.isBold()) fontNameId = com.lowagie.text.pdf.BaseFont.HELVETICA_BOLD;

                if (f.isItalic()) fontNameId = com.lowagie.text.pdf.BaseFont.HELVETICA_OBLIQUE;

                if ((f.isBold()) && (f.isItalic())) fontNameId = com.lowagie.text.pdf.BaseFont.HELVETICA_BOLDOBLIQUE;

                //System.out.println("HELVETICA");

                isType1 = true;

            }

            if (fontName.toLowerCase().indexOf("roman") >= 0) {

                fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ROMAN;

                if (f.isBold()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLD;

                if (f.isItalic()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ITALIC;

                if ((f.isBold()) && (f.isItalic())) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLDITALIC;

                //System.out.println("ROMAN");

                isType1 = true;

            }

            if (fontName.toLowerCase().indexOf("symbol") >= 0) {

                fontNameId = com.lowagie.text.pdf.BaseFont.SYMBOL;

                isType1 = true;

            }

            if (fontName.toLowerCase().indexOf("zapfdingbats") >= 0) {

                fontNameId = com.lowagie.text.pdf.BaseFont.ZAPFDINGBATS;

                isType1 = true;

            }


            if (isType1) {

                //return com.lowagie.text.FontFactory.getFont(fontNameId,size,style,c);

                try {

                    return com.lowagie.text.pdf.BaseFont.createFont(fontNameId, com.lowagie.text.pdf.BaseFont.CP1252, com.lowagie.text.pdf.BaseFont.NOT_EMBEDDED);

                } catch (Exception e) {

                    System.err.println("Error loading font: " + e.getMessage());

                    return null;

                }

            } else {

                // create other fonts

                // supported encoding BaseFont.CP1252, BaseFont.WINANSI and BaseFont.MACROMAN

                BaseFont newFont = null;

                if ((fontName.equalsIgnoreCase("arial")) || (fontName.equalsIgnoreCase("century")) ||

                        (fontName.equalsIgnoreCase("garamond")) || (fontName.equalsIgnoreCase("impact")) ||

                        (fontName.equalsIgnoreCase("tahoma")) || (fontName.equalsIgnoreCase("verdana"))) {

                    try {

                        return newFont = BaseFont.createFont(fontsDirectory + fontName + ".ttf", BaseFont.CP1252, BaseFont.EMBEDDED);

                    } catch (Exception e) {

                        System.err.print("Error loading TTF Font: " + e.getMessage());

                        System.err.println(" Loading Times by default.");

                        fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ROMAN;

                        if (f.isBold()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLD;

                        if (f.isItalic()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ITALIC;

                        if ((f.isBold()) && (f.isItalic())) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLDITALIC;

                        try {

                            return com.lowagie.text.pdf.BaseFont.createFont(fontNameId, com.lowagie.text.pdf.BaseFont.CP1252, com.lowagie.text.pdf.BaseFont.NOT_EMBEDDED);

                        } catch (Exception e2) {

                            System.err.println("Error loading Default Times: " + e2.getMessage());

                            return null;

                        } // Second catch

                    } // First catch

                } else {

                    // System.err.println("Font not defined, loading Times by default.");

                    fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ROMAN;

                    if (f.isBold()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLD;

                    if (f.isItalic()) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_ITALIC;

                    if ((f.isBold()) && (f.isItalic())) fontNameId = com.lowagie.text.pdf.BaseFont.TIMES_BOLDITALIC;

                    try {

                        return com.lowagie.text.pdf.BaseFont.createFont(fontNameId, com.lowagie.text.pdf.BaseFont.CP1252, com.lowagie.text.pdf.BaseFont.NOT_EMBEDDED);

                    } catch (Exception e) {

                        System.err.println("Error loading Default Times: " + e.getMessage());

                        return null;

                    }

                }

                //BaseFont bfJapanese = BaseFont.createFont("HeiseKakugo-W5","UniJIS-UCS2-H",false);

                //Font FontJap=new Font(bfJapanese,12,Font.NORMAL);


            }


        } else

            return null;

    }


}



