package com.java4less.rreport;

//  RReport

//  Copyright (C)

//

//  Java4Less@Confluencia.net

//  All rights reserved

//

// Adquisition , use and distribution of this code is subject to restriction:

//  - You may modify the source code in order to adapt it to your needs.

//  - You may not copy and paste prany code into external files.

//  - Redistribution of this ( or a modified version) source cPDode is prohibited. You may only redistribute compiled versions.

//  - You may redistribute the compiled version as part of your application, not a new java component with the same purpose as this one.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material


import java.awt.*;


import java.sql.*;


import java.awt.Dialog;


import java.util.*;


import java.awt.event.*;


import com.java4less.rreport.exp.Evaluator;


import srcastra.astra.sys.*;


/**
 * Report to be previewed , printed and/or exported. It contains one or more areas that can be printed one or more times. Each area will contains several RObjects.
 */


public class RReport extends Panel implements MouseMotionListener, MouseListener // AWT. Panel


{


    /**
     * expression evaluation
     */


    Evaluator evaluator = new Evaluator();


    /**
     * discard pages once they have been printed. Do not build list of pages
     */


    public boolean discardPagesInmediatelly = false;


    /**
     * specifies the number of milliseconds the tree will wait before showing a tip for the current node.
     */


    public int tipDelay = 300;


    /**
     * activate tooltips
     */


    public boolean activateTips = true;


    /**
     * activate actions
     */


    public boolean activateActions = true;


    /**
     * activate debug
     */


    public boolean debug = false;


    /**
     * this listener is called when an object is clicked
     */


    private RObjectListener objlistener = null;


    /**
     * Font used to display tips.
     */


    public Font tipFont = new java.awt.Font("Arial", java.awt.Font.PLAIN, 10);


    /**
     * color of the border of the box used to displayed tips.
     */


    public Color tipColor = java.awt.Color.black;


    /**
     * color of the border of the box used to displayed tips.
     */


    public Color tipBorder = java.awt.Color.gray;


    /**
     * background of the box used to displayed tips.
     */


    public Color tipBack = java.awt.Color.yellow;


    long showingTipTime = 0;


    long enterTime = 0;


    /**
     * current posision of cursor
     */


    int cursorX = 0;


    int cursorY = 0;


    /**
     * directory where the images that will be exported for the html report
     */


    public String exportDirectory = "";


    /**
     * the prefix is added to the image url when the DHTML is created. For example, if your image is images/a.gif and prefix is /examples/, the final url in the HTML code will be /examples/images/a.gif.
     */


    public String imagesHTMLPrefix = "";


    /**
     * format of the images that will be exported for the html report
     */


    public String exportImagesFormat = "JPG";


    /**
     * not used.
     */


    public static final int POSITION_NEXT = 0;


    /**
     * not used.
     */


    public static final int POSITION_UNDER = 1;


    /**
     * not used.
     */


    public static final int POSITION_RIGHT = 2;

/**



 * not used.



 */


    /**
     * show progress window while printing? only used by RReportJ2
     */


    public boolean showProgressWindow = true;


    /**
     * portrait layout
     */


    public static final int ORIENTATION_VERTICAL = 0;


    /**
     * landscape layout
     */


    public static final int ORIENTATION_HORIZONTAL = 1;


    /**
     * directory where ttf files are found. Default is  .\\fonts\\.
     */


    public String pdfFontsDirectory = ".\\fonts\\";


    private int dNumber = 0;


    private java.awt.Component ComponentOwner;


    protected boolean disabledPrinting = false;


    private RLineStyle frameStyle;


    private RArea mPageBackgroundArea = null;


    private java.awt.Color mPageBackground = java.awt.Color.white;


    private RPicture mPageBackgroundImage;


    private String dbUrl = "";


    private String dbUser = "";


    private String dbPsw = "";


    private String dbDriver = "";


    private boolean reportStarted = false;


    private boolean endOfReport = false;


    private String databaseProductName = "";


    private Vector databaseConnections = new Vector();


    /**
     * this cursor will be activated when the cursor is over an object that has an associated action.
     */


    public Cursor actionCursor = new Cursor(Cursor.HAND_CURSOR);


    /**
     * the cursor is over this object
     */


    public RObjectInstance selectedObject = null;


    /**
     * set it to false in order to hide the printer dialog
     */


    public boolean showPrintDialog = true;


    /**
     * set it to false in order to hide the page format dialog (API Java 2)
     */


    public boolean showPageDialog = false;


    private boolean doLayoutScroll = false;


    /**
     * parameter used to created a bufferedImage for previews. Default is java.awt.image.BufferedImage.TYPE_INT_RGB
     */


    public int bufferedImageType = -999;


    protected RPrintSetup printSetup = new RPrintSetup();


    private Connection DBCon;


    Vector areas = new Vector(8, 2);


    Vector Pages = new Vector(5, 5);


    RPreview PreviewCanvas = new RPreview();

    // AWT.


    Panel ViewArea = new Panel(null);

    //JPanel ViewArea= new JPanel(null);

    // AWT.


    ScrollPane Scroll;

    //JScrollPane Scroll = new JScrollPane();


    Graphics pg = null;


    RArea pageHeader;


    RArea pageFooter;


    RArea reportHeader;


    RArea reportFooter;


    private boolean bHTML = false;


    private boolean bDHTML = true;    // create DHTML instead of HTML?


    /**
     * are we using preview?
     */


    protected boolean bPreview;


    /**
     * this is necesary for areas that are printed in several columns. We need to know the last printed areas
     */


    private RArea lastPrintedArea = null;


    private int lastPrinterAreaColumn = 1;


    /**
     * algorithm used for the preview zoom, only jdk 1.1
     */


    public int scaleAlgorithm = Image.SCALE_DEFAULT;


    private int Orientation = ORIENTATION_VERTICAL;


    double pageFrameVerticalMargin = 0;


    double pageFrameHorizontalMargin = 0;


    int currentPageNumber = 0;


    int pageSubCounter = 0;// page counter within report


    int showPages = 1;


    RPage currentPage;


    int maxPages = 0;


    int mPageWidth = 620;


    int mPageHeight = 876;


    int mResolution = (int) (72 / 2.56);


    PrintJob pjob;


    double currentX;


    double currentY;


    double currentMaxY;


    double currentScale = 1;


    double currentSeveralPagesScale = 1;


    private String[] sHTML = new String[10000];


    private String repTitle = "";


    private boolean HTMLPages = false;


    private boolean createHTMLPage = false;

    // if false, report will be printed as the same time the RPage objects are created


    protected boolean firstCreateObjects = false;


    /**
     * true if the user cancelled the printing in the printer dialog
     */


    public boolean printingCancelled = false;


    /**
     * margin of the page in centimeters.
     */


    public double marginLeft = 2;


    /**
     * margin of the page in centimeters.
     */


    public double marginRight = 2;


    /**
     * margin of the page in centimeters. You will need to increase the bottom margin if you increase the size of the page footer.
     */


    public double marginBottom = 2;


    /**
     * margin of the page in centimeters.
     */


    public double marginTop = 2;


    /**
     * size of the page in centimeter.
     */


    public double mPageWidthCM = 21;


    /**
     * size of the page in centimeter.
     */


    public double mPageHeightCM = 29.7;


    public static double javaVersion = 1.1;


    private String imageFile = "";


    private String imageFormat = "";


    private String PDFFile = "";


    private java.io.OutputStream PDFStream = null;


    private PDFLayer pdfLayer;


    private DHTMLLayer DHtmlLayer;


    private String dateFormat = "dd MMM yyyy";


    /**
     * images for double buffering in the preview. The preview displays a maximum of 2 pages at a time
     */


    private Image pageImage1 = null; // image for page1


    private Graphics pageGraphics1 = null;


    private RPage page1 = null;  // which page is painted in this image?


    private Image pageImage2 = null; // image for page 2


    private Graphics pageGraphics2 = null;


    private RPage page2 = null;  // which page is painted in this image?

    // repetition ID of the area


    private long areaRepetitionId = 0;


    /**
     * constructor:
     * <p/>
     * <p/>
     * <p/>
     * Owner: must be a visible Frame or NULL
     */


    public RReport(Frame Owner) {


        super();


        createRReport(Owner, 0);


    }


    /**
     * constructor:<BR>
     * <p/>
     * <p/>
     * <p/>
     * - Owner: must be a visible Frame or NULL.<BR>
     * <p/>
     * <p/>
     * <p/>
     * - reso: resolution used for the preview (in CM). The default is the screen resolution.
     */


    public RReport(Frame Owner, int reso) {


        super();


        createRReport(Owner, reso);


    }


    /**
     * this listener is called when an object is clicked
     */


    public void setObjectListener(RObjectListener l) {


        objlistener = l;


    }

    // DB access fields


    /**
     * gets database url.
     */


    public String getDBUrl() {
        return dbUrl;
    }


    /**
     * gets database user.
     */


    public String getDBUser() {
        return dbUser;
    }


    /**
     * gets database password.
     */


    public String getDBPsw() {
        return dbPsw;
    }


    /**
     * gets driver.
     */


    public String getDBDriver() {
        return dbDriver;
    }


    /**
     * sets database url.
     */


    public void setDBUrl(String s) {
        dbUrl = s;
    }


    /**
     * sets database user.
     */


    public void setDBUser(String s) {
        dbUser = s;
    }


    /**
     * sets database password.
     */


    public void setDBPsw(String s) {
        dbPsw = s;
    }


    /**
     * sets driver.
     */


    public void setDBDriver(String s) {
        dbDriver = s;
    }


    /**
     * Sets the RPrintSetup object used to configurate the printer. The default is the jdk 1.1 compatible RPrintSetup.
     */


    public void setPrintSetup(RPrintSetup ps) {
        printSetup = ps;
    }


    /**
     * gets the RPrintSetup object used to configurate the printer. The default is the jdk 1.1 compatible RPrintSetup.
     */


    public RPrintSetup getPrintSetup() {
        return printSetup;
    }


    /**
     * if true, the report will not be printed. This is usefull if you just want to create HTML or PDF output.
     */


    public void disablePrinting(boolean b) {
        disabledPrinting = b;
    }


    /**
     * if true, the report will not be printed. This is usefull if you just want to create HTML or PDF output.
     */


    public boolean isPrintingDisabled() {
        return disabledPrinting;
    }

    // set PDF output file


    /**
     * gets the file name for PDF output.
     */


    public String getPDFFile() {
        return PDFFile;
    }


    /**
     * sets the file name for PDF output.
     */


    public void setPDFFile(String f) {
        PDFFile = f;
    }


    /**
     * sets the stream for PDF output.
     */


    public void setPDFStream(java.io.OutputStream f) {
        PDFStream = f;
        PDFFile = "outputstream";
    }


    /**
     * sets the file name for Image output.
     */


    public void setImageFile(String f, String imageF) {


        this.imageFile = f;


        this.imageFormat = imageF;


    }


    /**
     * page frame top/bottom margin (in CM)
     * <p/>
     * <p/>
     * <p/>
     * if 0 it will be calculated as topMargin/3 and bottomMargin/3
     */


    public double getPageFrameVerticalMargin() {


        return pageFrameVerticalMargin;


    }


    /**
     * page frame top/bottom margin (in CM)
     * <p/>
     * <p/>
     * <p/>
     * if 0 it will be calculated as topMargin/3 and bottomMargin/3
     */


    public void setPageFrameVerticalMargin(double d) {


        pageFrameVerticalMargin = d;


    }


    /**
     * page frame left/right margin (in CM)
     * <p/>
     * <p/>
     * <p/>
     * if 0 it will be calculated as leftMargin/3 and rightMargin/3
     */


    public double getPageFrameHorizontalMargin() {


        return pageFrameHorizontalMargin;


    }


    /**
     * page frame left/right margin (in CM)
     * <p/>
     * <p/>
     * <p/>
     * if 0 it will be calculated as leftMargin/3 and rightMargin/3
     */


    public void setPageFrameHorizontalMargin(double d) {


        pageFrameHorizontalMargin = d;


    }


    protected boolean d() {


        return false;


    }

    // managing areas in the report


    /**
     * remove areas from report
     */


    public void removeAllAreas() {


        this.areas.removeAllElements();


    }


    /**
     * remove one area from report
     */


    public void removeAreaAt(int i) {


        areas.removeElementAt(i);


    }


    /**
     * gets date format for [Date] system variable. The default is "dd MMM yyyy". See documentation of SimpleDateFormat if you need to change the format.
     */


    public String getDateFormat() {


        return dateFormat;


    }


    /**
     * sets date format for [Date] system variable. The default is "dd MMM yyyy". See documentation of SimpleDateFormat if you need to change the format.
     */


    public void setDateFormat(String s) {


        dateFormat = s;


    }


    /**
     * gets detail are of a group, given the header or footer
     */


    public RArea getGroupDetail(RArea footerOrHeader) {


        for (int i = 0; i < areas.size(); i++) {


            RAreaProp a = (RAreaProp) areas.elementAt(i);


            if (a.area.getGroupFooterArea() == footerOrHeader) return a.area;


            if (a.area.getGroupHeaderArea() == footerOrHeader) return a.area;


        }


        return null;


    }


    /**
     * gets area by name
     */


    public RArea getAreaByName(String name) {


        for (int i = 0; i < areas.size(); i++) {


            RAreaProp a = (RAreaProp) areas.elementAt(i);


            if (a.area.getName().compareTo(name) == 0) return a.area;


        }

        // is this one of the special areas


        if (this.pageFooter != null)


            if (pageFooter.getName().compareTo(name) == 0) return pageFooter;


        if (this.reportFooter != null)


            if (reportFooter.getName().compareTo(name) == 0) return reportFooter;


        if (this.pageHeader != null)


            if (pageHeader.getName().compareTo(name) == 0) return pageHeader;


        if (this.reportHeader != null)


            if (reportHeader.getName().compareTo(name) == 0) return reportHeader;


        if (this.mPageBackgroundArea != null)


            if (mPageBackgroundArea.getName().compareTo(name) == 0) return mPageBackgroundArea;


        return null;


    }


    /**
     * page width, pixels
     */


    public int getPageWidthPixels() {
        return mPageWidth;
    }


    /**
     * gets area by position
     */


    public RArea getAreaAt(int i) {


        return ((RAreaProp) areas.elementAt(i)).area;


    }


    /**
     * get number of area in report
     */


    public int getAreaCount() {


        return areas.size();


    }


    /**
     * get current connection to database. Will be automatically set if DBActive=true in *.rep file.
     */


    public Connection getDBCon() {
        return DBCon;
    }


    /**
     * this function returns a connection. It creates a new connection for SQL Server.
     */


    public Connection getDBConForSelect() {


        if (databaseProductName.indexOf("SQL SERVER") >= 0) {


            try {

                // create new connection


                Connection c = DriverManager.getConnection(dbUrl, dbUser, dbPsw);

                // add to list of connections


                this.databaseConnections.addElement(c);


                return c;


            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        return DBCon;


    }


    /**
     * sets current connection to database.
     */


    public void setDBCon(Connection c) {


        DBCon = c;


    }


    /**
     * closes database connections
     */


    public void closeDB() {


        try {


            if (DBCon != null) DBCon.close();

            // close all other connections created for SQL Server


            for (int i = 0; i < this.databaseConnections.size(); i++) {


                Connection c = (Connection) this.databaseConnections.elementAt(i);


                c.close();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * connect to database. Will be automatically executed if DBActive=true in *.rep file.
     */


    public boolean openDB() {


        try {

            // load driver


            if (this.dbDriver.trim().length() == 0) {

                // ODBC


                String v = System.getProperty("java.vendor");


                if (v.indexOf("Microsoft") >= 0) {


                    Class.forName("com.ms.jdbc.odbc.JdbcOdbcDriver");


                } else


                {

                    // if we use Sun the we load


                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");


                }


            } else Class.forName(this.dbDriver.trim());


            if (dbUrl.toUpperCase().indexOf(":") < 0) dbUrl = "jdbc:odbc:" + dbUrl;


            DBCon = DriverManager.getConnection(dbUrl, dbUser, dbPsw);


            databaseProductName = DBCon.getMetaData().getDatabaseProductName().toUpperCase();


            return true;


        } catch (Exception e) {


            System.out.println("Open DB: " + e.getMessage());


            return false;


        }


    }


    /**
     * Import *.rep file. Only implemented in RReportImp and RReportJ2.
     */


    public boolean importReport(String f) {


        return false;


    }


    private void createRReport(Frame Owner, int reso) {


        ComponentOwner = Owner;

        // get jdk version


        String v = java.lang.System.getProperty("java.version");


        javaVersion = 1.2;


        if (v.indexOf("1.1") == 0) javaVersion = 1.1;


        if (v.indexOf("1.3") == 0) javaVersion = 1.3;


        if (v.indexOf("1.4") == 0) javaVersion = 1.4;


        if (javaVersion > 1.2) printSetup = new RPrintSetupJDK13();


        this.setLayout(new java.awt.BorderLayout());


        PreviewCanvas.setBackground(ViewArea.getBackground());


        if ((activateTips) || (activateActions)) PreviewCanvas.addMouseMotionListener(this);


        mResolution = reso;


        if (mResolution <= 0) mResolution = (int) (72 / (2.56)); // resolution in centimeters

        // System.out.println("" +this.getToolkit().getScreenResolution());


        setDefaultPageSize(21, 29.7);


        boolean isHeadless = false;


        try {


            String headlessStr = java.lang.System.getProperty("java.awt.headless");


            if (headlessStr != null) {


                System.out.println("Headless is " + headlessStr);


                if (headlessStr.toUpperCase().compareTo("TRUE") == 0) isHeadless = true;


            }


        } catch (Exception e) {
        }


        if (!isHeadless) {


            ViewArea.setBackground(java.awt.Color.lightGray);


            Scroll = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);


            ViewArea.add(PreviewCanvas);


            Scroll.setBackground(java.awt.Color.lightGray);


            Scroll.add(ViewArea);


            this.add("Center", Scroll);


        }

        // create default page footer


        RArea PFOOTER = new RArea();


        PFOOTER.width = 16.429;


        PFOOTER.height = 0.767;


        RField TextPageNUmber = new RField();


        TextPageNUmber.name = "TextPageNumber";


        TextPageNUmber.setConstant(false);


        TextPageNUmber.x = 0.238;


        TextPageNUmber.y = 0.185;


        TextPageNUmber.width = 3;


        TextPageNUmber.height = 0.423;


        TextPageNUmber.Align = RField.ALIGN_LEFT;


        TextPageNUmber.FontColor = java.awt.Color.black;


        TextPageNUmber.FontType = new java.awt.Font("Arial", java.awt.Font.PLAIN, 8);


        PFOOTER.add(TextPageNUmber);


        TextPageNUmber.setdefaultValue("[Page]");


        this.setPageFooter(PFOOTER);


    }


    /**
     * internal use. Process line read from *.rep file.
     */


    public void importLine(String key, String val) {


        if (key.compareTo("NAME") == 0) this.setTitle(val);


        if (key.compareTo("PDFFILE") == 0) this.setPDFFile(val);


        if (key.compareTo("DB") == 0) this.setDBUrl(val);


        if (key.compareTo("DBUSER") == 0) this.setDBUser(val);


        if (key.compareTo("DBPSW") == 0) this.setDBPsw(val);


        if (key.compareTo("DBDRIVER") == 0) this.setDBDriver(val);


        if (key.compareTo("IMFORMAT") == 0) this.exportImagesFormat = val;


        if (key.compareTo("EXPORTDI") == 0) this.exportDirectory = val;


        if (key.compareTo("RESOLUTI") == 0) {


            this.mResolution = (new Integer(val)).intValue();


            if (getPrintSetup() instanceof RPrintSetupJDK13)
                ((RPrintSetupJDK13) getPrintSetup()).printerResolution = (int) (mResolution * 2.56);


        }


        if (key.compareTo("PRDIALOG") == 0) {


            this.showPrintDialog = (val.compareTo("1") == 0);


            if (getPrintSetup() instanceof RPrintSetupJDK13)
                ((RPrintSetupJDK13) getPrintSetup()).showDialog = showPrintDialog;


        }


        if (key.compareTo("PADIALOG") == 0) this.showPageDialog = (val.compareTo("1") == 0);


        if (key.compareTo("PROGRESS") == 0) this.showProgressWindow = (val.compareTo("1") == 0);


        if (key.compareTo("TTFFONTS") == 0) this.pdfFontsDirectory = val;


        if (key.compareTo("HTML") == 0) this.setHTMLActive(val.compareTo("1") == 0);


        if (key.compareTo("DHTML") == 0) this.setDHTMLActive(val.compareTo("1") == 0);


        if (key.compareTo("DISABLE") == 0) this.disablePrinting(val.compareTo("1") == 0);


        if (key.compareTo("HEIGHT") == 0) this.setDefaultPageSize(this.mPageWidthCM, new Double(val).doubleValue());


        if (key.compareTo("LEFT") == 0) this.marginLeft = (new Double(val).doubleValue());


        if (key.compareTo("RIGHT") == 0) this.marginRight = (new Double(val).doubleValue());


        if (key.compareTo("BOTTOM") == 0) this.marginBottom = (new Double(val).doubleValue());


        if (key.compareTo("FRAMEMAR") == 0) this.setPageFrameVerticalMargin(new Double(val).doubleValue());


        if (key.compareTo("FRAMEHMA") == 0) this.setPageFrameHorizontalMargin(new Double(val).doubleValue());


        if (key.compareTo("DATEF") == 0) this.dateFormat = val;


        if (key.compareTo("TOP") == 0) this.marginTop = (new Double(val).doubleValue());


        if (key.compareTo("WIDTH") == 0) this.setDefaultPageSize(new Double(val).doubleValue(), this.mPageHeightCM);


        if ((key.compareTo("ORIENTAT") == 0) && (val.compareTo("HORIZONTAL") == 0))
            this.Orientation = RReport.ORIENTATION_HORIZONTAL;


        if ((key.compareTo("FRAME") == 0) && (val.compareTo("1") == 0))
            this.setPageFrameStyle(new RLineStyle(0.2f, Color.black, RLineStyle.LINE_NORMAL));


        if ((key.compareTo("FRCOLOR") == 0) && (getPageFrameStyle() != null))
            getPageFrameStyle().setColor(RObject.convertColor(val));


        if ((key.compareTo("FRSTYLE") == 0) && (getPageFrameStyle() != null))
            getPageFrameStyle().setType(new Integer(val).intValue());


        if ((key.compareTo("FRWIDTH") == 0) && (getPageFrameStyle() != null))
            getPageFrameStyle().setWidth((float) new Double(val).doubleValue());


        if (key.compareTo("BCOLOR") == 0) this.mPageBackground = (RObject.convertColor(val));

        // background image

        //if (key.compareTo("BIMAGE")==0)  {

        //	this.setPageBackgroundImage(new RPicture());

        //	this.getPageBackgroundImage().currentImage.setName(val);

        //	this.getPageBackgroundImage().setdefaultValue(this.getPageBackgroundImage().currentImage.getImage());

        //}


    }


    private boolean saveToImageFile(String fi, Image pageImage) {


        if (this.imageFormat.toUpperCase().compareTo("GIF") == 0) return saveToGIF(fi, pageImage);


        if (this.imageFormat.toUpperCase().compareTo("JPG") == 0) return saveToJPEG(fi, pageImage);


        if (this.imageFormat.toUpperCase().compareTo("PNG") == 0) return saveToPNG(fi, pageImage);


        return false;


    }


    /**
     * saves current page image to a png file<BR>
     * <p/>
     * <p/>
     * <p/>
     * <BR>
     * <p/>
     * <p/>
     * <p/>
     * download png encoder from http://users.boone.net/wbrameld/pngencoder/ <BR>
     */


    private boolean saveToPNG(String fi, Image pageImage) {


        try {

            // open file


            java.io.File f = new java.io.File(fi);


            f.delete();


            java.io.FileOutputStream of = new java.io.FileOutputStream(f);


            Class enClass = Class.forName("com.bigfoot.bugar.image.PNGEncoder");


            Class[] constructorParams = new Class[2];


            constructorParams[0] = Class.forName("java.awt.Image");


            constructorParams[1] = Class.forName("java.io.OutputStream");


            Object[] constructorObj = new Object[2];


            constructorObj[0] = pageImage;


            constructorObj[1] = of;


            Object encoder = enClass.getConstructor(constructorParams).newInstance(constructorObj);


            Class[] encodeParams = new Class[0];


            Object[] encodeObj = new Object[0];


            enClass.getMethod("encodeImage", encodeParams).invoke(encoder, encodeObj);


            of.close();


        }


        catch (Exception e) {


            System.out.println(e.getMessage());


            return false;
        }


        return true;


    }


    /**
     * saves current page image to a jpg file
     */


    private boolean saveToJPEG(String fi, Image pageImage) {


        try {

            // open file


            java.io.File f = new java.io.File(fi);


            f.delete();


            java.io.FileOutputStream of = new java.io.FileOutputStream(f);

            // encode buffered image to a jpeg


            com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(of);


            encoder.encode((java.awt.image.BufferedImage) pageImage);


            of.close();


        }


        catch (Exception e) {


            System.out.println(e.getMessage());


            return false;
        }


        return true;


    }


    /**
     * saves current page image to a gif file <BR>
     * <p/>
     * <p/>
     * <p/>
     * <BR>
     * <p/>
     * <p/>
     * <p/>
     * download and install gif encoder from this address:<BR>
     * <p/>
     * <p/>
     * <p/>
     * http://www.acme.com/resources/classes/Acme.tar.gz   <BR>
     */


    private boolean saveToGIF(String fi, Image pageImage) {


        try {

            // open file


            java.io.File f = new java.io.File(fi);


            f.delete();


            java.io.FileOutputStream of = new java.io.FileOutputStream(f);


            Class enClass = Class.forName("Acme.JPM.Encoders.GifEncoder");


            Class[] constructorParams = new Class[2];


            constructorParams[0] = Class.forName("java.awt.Image");


            constructorParams[1] = Class.forName("java.io.OutputStream");


            Object[] constructorObj = new Object[2];


            constructorObj[0] = pageImage;


            constructorObj[1] = of;


            Object encoder = enClass.getConstructor(constructorParams).newInstance(constructorObj);


            Class[] encodeParams = new Class[0];


            Object[] encodeObj = new Object[0];


            enClass.getMethod("encode", encodeParams).invoke(encoder, encodeObj);


            of.close();


        }


        catch (Exception e) {


            System.out.println(e.getMessage());


            return false;
        }


        return true;


    }


    /**
     * This image will be printed in the background of each page
     */


    public void setPageBackgroundImage(RPicture p) {


        mPageBackgroundImage = p;


    }


    /**
     * This image will be printed in the background of each page
     */


    public RPicture getPageBackgroundImage() {


        return mPageBackgroundImage;


    }


    /**
     * background color of the page.
     */


    public void setPageBackground(java.awt.Color c) {


        mPageBackground = c;


    }


    /**
     * background color of the page.
     */


    public Color getPageBackground() {


        return mPageBackground;


    }


    /**
     * This area will be printed in the background of each page
     */


    public void setPageBackgroundArea(RArea a) {


        mPageBackgroundArea = a;


        if (a != null) mPageBackgroundArea.setAreaType(RArea.AREA_BACKGROUND);


    }


    /**
     * This area will be printed in the background of each page
     */


    public RArea getPageBackgroundArea() {


        return mPageBackgroundArea;


    }


    /**
     * sets the frame style for the pages of the report.
     */


    public void setPageFrameStyle(RLineStyle fStyle) {


        frameStyle = fStyle;


    }


    /**
     * sets the frame style for the pages of the report.
     */


    public RLineStyle getPageFrameStyle() {


        return frameStyle;


    }


    /**
     * Specifies that the HTML version should be created.
     */


    public void setHTMLActive(boolean activate)


    {


        bHTML = activate;


    }


    /**
     * Specifies that the HTML version should be created.
     */


    public boolean getHTMLActive()


    {


        return bHTML;


    }


    /**
     * Specifies that the HTML version should be created.
     */


    public void setDHTMLActive(boolean activate)


    {


        bDHTML = activate;


    }


    /**
     * Specifies that the HTML version should be created.
     */


    public boolean getDHTMLActive()


    {


        return bDHTML;


    }


    /**
     * Title of the report.
     */


    public void setTitle(String t) {
        repTitle = t;
    }


    /**
     * Title of the report.
     */


    public String getTitle() {
        return repTitle;
    }


    /**
     * returns scale of the pages being display in the preview window. (1 is 100%)
     */


    public double getScalePages() {
        return currentSeveralPagesScale;
    }


    /**
     * sets date format for [Date] system variable. The default is "dd MMM yyyy". See documentation of SimpleDateFormat if you need to change the format.
     */


    public void setDefaultPageSize(double w, double h) {


        mPageWidthCM = w;


        mPageHeightCM = h;


        mPageWidth = (int) (mPageWidthCM * mResolution);


        mPageHeight = (int) (mPageHeightCM * mResolution);


    }


    /**
     * resolution of the report (pixels/CM). This should be the resolution used by the printer. Default is 29 (72 dpi)
     */


    public int getResolution() {


        return this.mResolution;


    }


    /**
     * resolution of the report (pixels/CM). This should be the resolution used by the printer. Default is 29 (72 dpi)
     */


    public void setResolution(int r) {


        this.mResolution = r;


        setDefaultPageSize(mPageWidthCM, mPageHeightCM);


    }


    /**
     * sets report orientation (RReport.ORIENTATION_HORIZONTAL or RReport.ORIENTATION_VERTICAL)
     */


    public int getOrientation() {


        return Orientation;


    }


    /**
     * sets report orientation (RReport.ORIENTATION_HORIZONTAL or RReport.ORIENTATION_VERTICAL)
     */


    public void setOrientation(int o) {

        // it is the same, do now swap sizes


        if (o == Orientation) return;


        Orientation = o;


        int h1 = mPageWidth;


        mPageWidth = mPageHeight;


        mPageHeight = h1;


        double h2 = mPageWidthCM;


        mPageWidthCM = mPageHeightCM;


        mPageHeightCM = h2;


    }


    /**
     * returns current page number during preview.
     */


    public int getPageNumber() {
        return currentPageNumber;
    }


    /**
     * returns number of pages of the report.
     */


    public int getMaxPages() {
        return maxPages;
    }


    /**
     * set page to show in the preview
     */


    public boolean setPageNumber(int p) {


        if ((p >= 1) && (p <= maxPages)) {


            currentPageNumber = p;


            updatePage();


            updateCanvas();


            return true;


        }


        return false;


    }


    /**
     * Add an area to the report. The area will be added after the last one.
     */


    public void addArea(RArea a) {


        areas.addElement(new RAreaProp(a));


    }


    /**
     * sets the number of pages to be displayed in the preview.  To be used by the preview window.
     */


    public void setShowPages(int p) {


        if (showPages != p) doLayoutScroll = true;


        showPages = p;


        updateCanvas();
    }


    /**
     * returns the number of pages to be displayed in the preview.  To be used by the preview window.
     */


    public int getShowPages() {
        return showPages;
    }


    private void drawAreaBackground(RArea a) {


        java.awt.Color AreaBack = a.getBackground();


        RPicture AreaIm = a.getBackgroundImage();


        if (!AreaBack.equals(mPageBackground)) {


            RRectangle rec = new RRectangle();


            rec.x = a.x + a.marginLeft / 3;


            rec.y = a.y + a.marginTop / 3;


            rec.width = a.width - (a.marginLeft / 3) - (a.marginRight / 3);


            rec.height = a.height - (a.marginTop / 3) - (a.marginBottom / 3);


            rec.Style = null;


            rec.fillColor = AreaBack;


            rec.fill = true;


            rec.selectable = false;


            RObjectInstance ObjInstance = new RObjectInstance(rec.x, rec.y, rec, null, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


        }


        if (AreaIm != null) {


            RObjectInstance ObjInstance = new RObjectInstance(currentX + AreaIm.x + a.marginLeft, currentY + AreaIm.y + a.marginTop, AreaIm, null, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


        }


    }


    private void drawGridInternal(RAreaProp p) { //,double x,double topY,double bottomY) {


        if (p.topY != 0) {


            if (p.area.getGrid() != null) {

                // draw vertial lines


                double[] g = p.area.getGrid();


                for (int i = 0; i < g.length; i++) {


                    RLine lin = new RLine();


                    lin.selectable = false;


                    lin.lineDirection = lin.VERTICAL;


                    lin.x = g[i] + p.area.marginLeft;


                    lin.y = p.area.marginTop;


                    lin.width = 0;


                    lin.height = p.bottomY - p.topY - p.area.marginTop;


                    lin.Style = p.area.getGridStyle();


                    RObjectInstance ObjInstance = new RObjectInstance(lin.x + p.x, lin.y + p.topY, lin, null, areaRepetitionId);


                    currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


                }


            }


            if (p.area.getFrameType() == RArea.FRAME_PAGE) {

                // draw frame


                RRectangle rec = new RRectangle();


                rec.x = (p.area.marginLeft / 3);


                rec.y = (p.area.marginTop / 3);


                rec.selectable = false;


                rec.width = p.area.width - (p.area.marginLeft / 3) - (p.area.marginRight / 3);


                rec.height = p.bottomY - p.topY - (p.area.marginTop / 3) - (p.area.marginBottom / 3);


                rec.Style = p.area.getFrameStyle();


                RObjectInstance ObjInstance = new RObjectInstance(rec.x + p.x, rec.y + p.topY, rec, null, areaRepetitionId);


                currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


            }


            if (p.area.getFrameType() == RArea.FRAME_PAGE_NO_TOP) {

                // draw vertical lines


                RLine lin = new RLine();


                lin.x = (p.area.marginLeft / 3);


                lin.y = (p.area.marginTop / 3);


                lin.selectable = false;


                lin.width = 0;


                lin.height = p.bottomY - p.topY - (p.area.marginTop / 3) - (p.area.marginBottom / 3);
                ;


                lin.Style = p.area.getFrameStyle();


                RObjectInstance ObjInstance = new RObjectInstance(lin.x + p.x, lin.y + p.topY, lin, null, areaRepetitionId);


                currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


                RLine lin2 = new RLine();


                lin2.x = (p.area.marginLeft / 3) + p.area.width - (p.area.marginLeft / 3) - (p.area.marginRight / 3);


                lin2.y = (p.area.marginTop / 3);


                lin2.width = 0;


                lin2.selectable = false;


                lin2.height = p.bottomY - p.topY - (p.area.marginTop / 3) - (p.area.marginBottom / 3);


                lin2.Style = p.area.getFrameStyle();


                RObjectInstance ObjInstance2 = new RObjectInstance(lin2.x + p.x, lin2.y + p.topY, lin2, null, areaRepetitionId);


                currentPage.addObjectInstance(ObjInstance2, false, pageGraphics1);


                RLine lin3 = new RLine();


                lin3.x = (p.area.marginLeft / 3);


                lin3.selectable = false;


                lin3.y = p.bottomY - p.topY + (p.area.marginTop / 3) - (p.area.marginTop / 3) - (p.area.marginBottom / 3);


                lin3.width = p.area.width - (p.area.marginLeft / 3) - (p.area.marginRight / 3);


                lin3.height = 0;


                lin3.Style = p.area.getFrameStyle();


                RObjectInstance ObjInstance3 = new RObjectInstance(lin3.x + p.x, lin3.y + p.topY, lin3, null, areaRepetitionId);


                currentPage.addObjectInstance(ObjInstance3, false, pageGraphics1);


            }


        }


    }


    private void drawGrid() {


        for (Enumeration e = areas.elements(); e.hasMoreElements();) {


            RAreaProp p = (RAreaProp) e.nextElement();


            drawGridInternal(p);//,p.x,p.topY,p.bottomY);


        }


    }


    private void drawBackground() {


        if (marginTop <= 0) return;


        if (marginBottom <= 0) return;


        if (marginLeft <= 0) return;


        if (marginRight <= 0) return;


        if (!mPageBackground.equals(java.awt.Color.white)) {


            RRectangle rec = new RRectangle();


            rec.x = marginLeft / 3;


            rec.y = marginTop / 3;


            rec.selectable = false;


            rec.width = mPageWidthCM - ((marginLeft / 3) + (marginRight / 3));


            rec.height = mPageHeightCM - ((marginTop / 3) + (marginBottom / 3));


            rec.Style = null;


            rec.fillColor = mPageBackground;


            rec.fill = true;


            RObjectInstance ObjInstance = new RObjectInstance(rec.x, rec.y, rec, null, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


        }


        if (mPageBackgroundArea != null) {


            currentX = marginLeft;


            currentY = marginTop;


            currentMaxY = marginTop;


            mPageBackgroundArea.x = marginLeft / 3;


            mPageBackgroundArea.y = marginTop / 3;


            mPageBackgroundArea.width = mPageWidthCM - ((marginLeft / 3) + (marginRight / 3));


            mPageBackgroundArea.height = mPageHeightCM - ((marginTop / 3) + (marginBottom / 3));


            if (debug) System.out.println("Printing background : " + mPageBackgroundArea.getName());


            printAreaInternal(mPageBackgroundArea);


            currentX = marginLeft;


            currentY = marginTop;


            currentMaxY = marginTop;


        }


        if (mPageBackgroundImage != null) {


            RObjectInstance ObjInstance2 = new RObjectInstance(mPageBackgroundImage.x, mPageBackgroundImage.y, mPageBackgroundImage, null, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance2, false, pageGraphics1);


        }


    }


    private void drawPageFrame() {


        if (marginTop <= 0) return;


        if (marginBottom <= 0) return;


        if (marginLeft <= 0) return;


        if (marginRight <= 0) return;


        if (frameStyle == null) return;


        RRectangle rec = new RRectangle();


        rec.selectable = false;


        rec.x = marginLeft / 3;


        if (pageFrameHorizontalMargin != 0) rec.x = pageFrameHorizontalMargin;


        rec.y = marginTop / 3;


        if (pageFrameVerticalMargin != 0) rec.y = pageFrameVerticalMargin;


        rec.width = mPageWidthCM - ((marginLeft / 3) + (marginRight / 3));


        if (pageFrameHorizontalMargin != 0) rec.width = mPageWidthCM - (pageFrameHorizontalMargin * 2);


        rec.height = mPageHeightCM - ((marginTop / 3) + (marginBottom / 3));


        if (pageFrameVerticalMargin != 0) rec.height = mPageHeightCM - (pageFrameVerticalMargin * 2);


        rec.Style = frameStyle;


        RObjectInstance ObjInstance = new RObjectInstance(rec.x, rec.y, rec, null, areaRepetitionId);


        currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


    }


    private void pageBreak(boolean reportBreak) {


        if ((!reportBreak) && (pageFooter != null)) {


            currentX = marginLeft;


            currentMaxY = mPageHeightCM - marginBottom - pageFooter.height; // print the footer before the bottom margin.


            if (debug) System.out.println("Page break, page footer : " + pageFooter.getName());


            printArea(pageFooter); // print footer


        }


        drawGrid();


        drawPageFrame();

        // PDF


        try {


            if (pdfLayer != null) pdfLayer.newPage();


        } catch (Exception e) {
            System.out.println("Error creating new PDF page: " + e.getMessage());
        }

        // image output


        if (this.imageFile != null)


            if (this.imageFile.length() > 0)
                this.saveToImageFile(this.imageFile + this.currentPageNumber + "." + this.imageFormat, this.pageImage1);

        // Dhtml


        if (DHtmlLayer != null) {


            if (HTMLPages) sHTML[currentPageNumber] = sHTML[currentPageNumber] + DHtmlLayer.getOutput();


            else sHTML[1] = sHTML[1] + DHtmlLayer.getOutput();

//            System.out.println("**************************");

//            System.out.println(DHtmlLayer.getOutput());


            DHtmlLayer.newPage(HTMLPages);


        }


        if (HTMLPages) sHTML[currentPageNumber] = sHTML[currentPageNumber] + endHTMLPage();


        if (reportBreak) this.pageSubCounter = 0;


        currentPage = newPage(bPreview, false);


        page1 = currentPage;


        drawBackground();


        currentX = marginLeft;


        currentY = marginTop;


        currentMaxY = marginTop;

        // initialize area prop


        for (Enumeration e = areas.elements(); e.hasMoreElements();) {


            RAreaProp p = (RAreaProp) e.nextElement();


            p.bottomY = 0;


            p.topY = 0;


        }


        sHTML[currentPageNumber] = startHTMLPage();


        if (!reportBreak)


            if (pageHeader != null) printArea(pageHeader); // Print header


        if (debug) System.out.println("Page break, header: " + pageHeader.getName());


    }


    /**
     * print group header or footer. Uses same data source as the group detail area
     */


    private void getRSourceData(RArea a, RSource source) {


        RArea dArea = null;

        // set fields of the area using data source


        if (source != null) {

            // for all items in area


            for (Enumeration e = a.items.elements(); e.hasMoreElements();) {


                RObject Obj = (RObject) e.nextElement();


                String tmpFieldName = Obj.fieldName;


                if (tmpFieldName.length() == 0) tmpFieldName = Obj.name;


                if (!Obj.isConstant()) Obj.setruntimeValue(source.rsource_getData(tmpFieldName));


            }            // for


        } // source!=null


    }


    private void printAreaSource(RArea a) {


        RObject Obj;


        boolean printGroupHeader = true;


        boolean firstRepetition = true;


        Object[] groupByValues = new Object[a.getGroupByFields().length];

//        boolean useGroups=(a.getGroupByFields().length>0);


        RSource src = a.getDataSource();


        if (src == null) return;


        else {


            if (src.rsource_start()) {


                if (debug) System.out.println("Starting data source for : " + a.getName());

                // for all records


                while (src.rsource_nextRecord()) {


                    if (debug) System.out.println("Got record : " + a.getName());

                    // for all items in area


                    getRSourceData(a, src);

                    // load "group by" values and compare with previous values


                    for (int j = 0; j < a.getGroupByFields().length; j++) {


                        Object val = src.rsource_getData(a.getGroupByFields()[j]);

                        // something changed


                        if (val != null) {


                            if (!val.equals(groupByValues[j])) printGroupHeader = true;


                        }


                        if ((val == null) && (groupByValues[j] != null)) printGroupHeader = true;


                        groupByValues[j] = val;


                    }


                    if (printGroupHeader) {


                        if ((!firstRepetition) && (a.getGroupFooterArea() != null))

                            // print first footer


                            printArea(a.getGroupFooterArea());

                        // reset instances of the group


                        a.resetInstances();


                        if (a.getGroupHeaderArea() != null) {


                            getRSourceData(a.getGroupHeaderArea(), src);


                            printArea(a.getGroupHeaderArea());


                        }


                    }


                    if (debug) System.out.println("Printing : " + a.getName());


                    printAreaInternal(a);


                    firstRepetition = false;


                    printGroupHeader = false;

                    // set values for group footer before the new data is read


                    if (a.getGroupFooterArea() != null) getRSourceData(a.getGroupFooterArea(), src);


                } // while

                // print group footer only if something has been printed


                if ((!firstRepetition) && (a.getGroupFooterArea() != null))


                    printArea(a.getGroupFooterArea());


                src.rsource_end();


            } // if start


        }


    }


    /**
     * prints an area. Before you print an area you must either provide a data souce (RSource) or set the values of the fields yourself (Rarea.setObjectValue()).
     */


    public void printArea(RArea a) {


        if (debug) System.out.println("printArea method : " + a.getName());


        if (printingCancelled) return;


        if (a.getDataSource() != null) printAreaSource(a);


        else printAreaInternal(a);


    }


    /**
     * Returns the HTML version of the report if HTMLPages is deactivated.
     */


    public String getHTML() {
        return sHTML[1];
    }


    /**
     * if this is false, the HTML report will be printed in just one file (page). If not, the HTML version will consist of many pages (files). Depending of this value you must use getHTML() or getHTMLPage(int page).
     */


    public void setHTMLPages(boolean b) {
        HTMLPages = b;
    }


    /**
     * returns a page of the HTML version of the report.  Only is setHTMLPages() was set to True.
     */


    public String getHTMLPage(int p) {


        if ((p <= maxPages) && (p > 0)) return sHTML[p];


        else return "";


    }


    private void printAreaInternal(RArea a) {

        // new page? only if this is not the first repetition


        if (a.newPage)


            if (a.repetition > 0) {


                if ((a != this.pageHeader) && (a != this.pageFooter) && (a != this.reportHeader) && (a != this.reportFooter)) {


                    if (debug) System.out.println("Page break");


                    this.pageBreak(false);


                }


            }


        if (debug) System.out.println("Printing area : " + a.getName());


        double columnOffset = 0;


        double lastPrintedAreaY = -1;


        if (a != this.lastPrintedArea) this.lastPrinterAreaColumn = 1;


        else {


            this.lastPrinterAreaColumn++;


            if (this.lastPrinterAreaColumn > a.columns) this.lastPrinterAreaColumn = 1;


            else lastPrintedAreaY = a.y; // same Y as last repetition.

            //System.out.println("Column "+lastPrinterAreaColumn);

            // calculate where the column must start


            columnOffset = (this.mPageWidthCM - (this.marginLeft + this.marginRight)) / a.columns;


            columnOffset = columnOffset * (this.lastPrinterAreaColumn - 1);

            // System.out.println("Offset "+columnOffset);

            // System.out.println("Y "+lastPrintedAreaY);

            // System.out.println("");


        }


        a.repetition++;


        areaRepetitionId++;


        a.addInstance(new RAreaInstance(areaRepetitionId));

        // remember orignal height


        if (a.layoutHeight == -1) a.layoutHeight = a.height;


        else a.height = a.layoutHeight;

        // are we printing the report header?


        if (a == reportHeader) {

            // we are printing a new report


            if (reportStarted) {

                // reset repetitions


                for (int i = 0; i < areas.size(); i++) ((RAreaProp) areas.elementAt(i)).area.repetition = 0;


                if (debug) System.out.println("End report, page break");


                endReportInternal();


                endOfReport = false;


                pageBreak(true);


            }


            this.reportStarted = true;


        }

        // call user exit


        if (a.getListener() != null) a.getListener().beforePrintingArea(a);


        int pos = a.AreaPosition;


        if ((a != this.reportFooter) && (a != this.pageFooter))


            if ((d()) && (dNumber > (120 / 2))) return;


        dNumber++;


        if ((a.Reference != null) && (pos == RReport.POSITION_UNDER) && (!a.NewReference)) {

            // under my previous instance


            currentY = a.y + a.height;


            currentX = a.x;


        }


        if ((a.Reference != null) && (pos == RReport.POSITION_UNDER) && (a.NewReference)) {

            // under another area


            currentY = a.Reference.y + a.Reference.height + a.verticalTab;


            currentX = a.Reference.x + a.horizontalTab;


            a.NewReference = false;


        }


        if ((a.Reference == null) || (pos == RReport.POSITION_NEXT)) {


            currentY = currentMaxY + a.verticalTab;


            currentX = marginLeft + a.horizontalTab;


        }


        if ((a.Reference != null) && (pos == RReport.POSITION_RIGHT)) {

            // right of another area


            currentX = a.Reference.x + a.Reference.width + a.horizontalTab;


            currentY = a.Reference.y + a.verticalTab;


        }

        // change current position if we print column>1


        currentX = currentX + columnOffset;

        // same Y as last repeptition if we are printing in several columns


        if (lastPrintedAreaY > -1) {


            currentY = lastPrintedAreaY;


        }

        //check here if need a new page


        double footerHeight = 0;


        if (pageFooter != null) footerHeight = pageFooter.height;


        if ((a != pageFooter) && (a != this.mPageBackgroundArea) && (currentY + a.height >= ((mPageHeight / mResolution) - marginBottom - footerHeight))) {

            //if ((a!= pageFooter) && (a!=this.mPageBackgroundArea) && (currentY+a.height>=((mPageHeight/mResolution)-marginBottom))) {


            pageBreak(false);


            currentY = currentMaxY + a.verticalTab;


            createHTMLPage = true;

            // print header of this area


            if (a.getHeaderArea() != null) {


                if (debug)
                    System.out.println("Print header area for : " + a.getName() + " " + a.getHeaderArea().getName());


                this.printArea(a.getHeaderArea());


                currentY = currentMaxY + a.verticalTab;


                currentX = marginLeft + a.horizontalTab;


            }


        }

        // remember where this area was printed


        a.x = currentX;


        a.y = currentY;


        drawAreaBackground(a);


        printAreaContents(a);


        if ((bHTML) && (a != mPageBackgroundArea) && (!bDHTML)) printAreaContentsHTML(a);


        if (currentMaxY < a.y + a.height) currentMaxY = a.y + a.height;

        // draw frame


        if (a.getFrameType() == RArea.FRAME_AREA) {


            RRectangle rec = new RRectangle();


            rec.selectable = false;


            rec.x = a.x + a.marginLeft / 3;


            rec.y = a.y + a.marginTop / 3;


            rec.width = a.width - (a.marginLeft / 3) - (a.marginRight / 3);

            //rec.height=(((a.y+a.height) * this.getResolution()) - (a.height* this.getResolution()))/this.getResolution();


            rec.height = a.height - (a.marginTop / 3) - (a.marginBottom / 3);

            //System.out.println("Y " +(rec.y)+ " y2 "+(rec.height+rec.y));

            //System.out.println("Y " +(rec.y)+ " y2 "+(rec.height+rec.y));


            rec.Style = a.getFrameStyle();


            RObjectInstance ObjInstance = new RObjectInstance(rec.x, rec.y, rec, null, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


        }

        // update area props


        for (Enumeration e = areas.elements(); e.hasMoreElements();) {


            RAreaProp p = (RAreaProp) e.nextElement();

            // reset areas that reference me


            if (p.area != null) {


                if (p.area.Reference == a) {


                    p.area.NewReference = true;


                }


                if (p.area == a) {


                    if (p.topY == 0) {
                        p.x = a.x;
                        p.topY = a.y;
                    } else {

                        // print horizontal line


                        if (p.area.getHorizontalGrid()) {


                            RLine lin = new RLine();


                            lin.selectable = false;


                            lin.x = (p.area.marginLeft / 3);


                            lin.y = 0;


                            lin.width = a.width - (p.area.marginLeft / 3) - (a.marginRight / 3);


                            lin.height = 0;


                            lin.Style = p.area.getGridStyle();


                            RObjectInstance ObjInstance = new RObjectInstance(lin.x + a.x, lin.y + a.y, lin, null, areaRepetitionId);


                            currentPage.addObjectInstance(ObjInstance, false, pageGraphics1);


                        }


                    }


                    p.bottomY = a.y + a.height;


                }


            }


        }

        // we just printed the report's header, print header of page


        if (a == reportHeader) {

            // print 1st page header


            if (pageHeader != null) {


                if (debug) System.out.println("Print header area after report header: " + pageHeader.getName());


                printArea(pageHeader);


            }


        }

        // there was a page break in this area


        if ((currentPage.finishPage) && (a != pageFooter)) pageBreak(false);

        // print dependent areas

        // footer and header cannot depend on other areas. They will not be in the areas() vector


        for (int i = 0; i < areas.size(); i++) {


            RAreaProp depArea = (RAreaProp) areas.elementAt(i);


            if (depArea.area != null) {


                if (depArea.area.getLinkedArea() == a)


                {


                    if (debug) System.out.println("Printing nested area: " + depArea.area.getName());


                    printArea(depArea.area);


                }


            }


        }


        lastPrintedArea = a;


    }


    private String newLine() {


        char lf = 10;


        char cr = 13;


        return "" + cr + lf;


    }


    private void endReportInternal() {

        // print report footer


        if (reportFooter != null) printArea(reportFooter);


        endOfReport = true;

        // print last page footer


        if (pageFooter != null) {


            currentX = marginLeft;


            currentMaxY = mPageHeightCM - marginBottom - pageFooter.height;


            printArea(pageFooter);


        }


        drawGrid();


        drawPageFrame();


    }


    /**
     * Run this method to end your report. It performs additional tasks like printing the report footer etc...
     */


    public void endReport() {


        if (printingCancelled) return;


        endReportInternal();


        printDelayedItems();


        if (bPreview) {


            currentPageNumber = 1;


            updatePage();


        } else {


            if (!this.disabledPrinting) this.endPrintJob();


        }


        if (pdfLayer != null) pdfLayer.close();

        // image output


        if (this.imageFile != null)


            if (this.imageFile.length() > 0)
                this.saveToImageFile(this.imageFile + this.currentPageNumber + "." + this.imageFormat, pageImage1);

        // html


        String DHTMLOutput = "";


        if (DHtmlLayer != null) DHTMLOutput = DHtmlLayer.getOutput();


        if (HTMLPages) sHTML[maxPages] = sHTML[maxPages] + DHTMLOutput + endHTMLPage();


        else sHTML[1] = sHTML[1] + DHTMLOutput + endHTMLPage();


    }


    private String replaceStr(String S, String a, String b) {


        String tmp;


        int p;


        int l;


        tmp = S;


        p = tmp.indexOf(a);


        while (p >= 0) {


            l = tmp.length();


            tmp = tmp.substring(0, p) + b + tmp.substring(p + a.length(), l);


            p = tmp.indexOf(a);


        }


        return tmp;


    }


    private void printAreaContentsHTML(RArea a) {


        RObject Obj;


        RObjectInstance ObjInstance;


        Object runtimeValue;


        String sAreaHTML = "";


        String sElementHTML = "";

        // in only one page, print header just once


        if ((!HTMLPages) && (this.pageSubCounter != 1))


            if (a == pageHeader) return;

        // no page footer, only at the end, last page


        if (a == pageFooter)


            if ((!HTMLPages) && (a == pageFooter) && (!endOfReport)) return;


        a.HTMLResolution = mResolution;


        sAreaHTML = a.getHTML();


        if (a == pageFooter) {


            sAreaHTML = "<BR><BR><BR>" + sAreaHTML;


        }


        for (Enumeration e = a.items.elements(); e.hasMoreElements();) {


            Obj = (RObject) e.nextElement();

            // add to the page


            runtimeValue = Obj.getruntimeValue();


            if (runtimeValue == null) runtimeValue = Obj.getdefaultValue();

            // date variable, only if it is a constant field!


            if (Obj.isConstant())


                if (runtimeValue != null)


                    if (dateFormat != null)


                        if (dateFormat.length() > 0)


                            if (runtimeValue instanceof String) {


                                String dateStr = "";


                                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(dateFormat);


                                dateStr = formatter.format(java.util.Calendar.getInstance().getTime());


                                String runtimeString = (String) runtimeValue;


                                if (this.HTMLPages) runtimeString = replaceStr(runtimeString, "[Date]", dateStr);


                                runtimeValue = runtimeString;


                            }


            if ((a == pageHeader) || (a == pageFooter))


                if (runtimeValue != null)


                    if (runtimeValue instanceof String) {


                        String runtimeString = (String) runtimeValue;


                        if (this.HTMLPages) runtimeString = replaceStr(runtimeString, "[Page]", "" + pageSubCounter);


                        else runtimeString = replaceStr(runtimeString, "[Page]", "1");

                        //runtimeString=replaceStr(runtimeString,"[Pages]",""+maxPages);


                        runtimeValue = runtimeString;


                    }

            // not visible


            if (!Obj.visible) sElementHTML = "";


            else if (Obj.canHTML()) sElementHTML = Obj.toHTML(runtimeValue);


            sAreaHTML = replaceStr(sAreaHTML, "<%%" + Obj.name + "%%>", sElementHTML);


        }


        if (HTMLPages) sHTML[currentPageNumber] = sHTML[currentPageNumber] + sAreaHTML;


        else sHTML[1] = sHTML[1] + sAreaHTML;


    }


    private void printAreaContents(RArea a) {


        RObject Obj;


        RObjectInstance ObjInstance;


        double maxWidth = currentX;


        double maxHeight = currentY;


        Object runtimeValue;


        boolean delayed = false;

        // currentX and currentY are now the left upper corner of the area


        for (Enumeration e = a.items.elements(); e.hasMoreElements();) {


            Obj = (RObject) e.nextElement();


            Obj.area = a;

            // add to the page


            delayed = false;


            runtimeValue = Obj.getruntimeValue();


            if (runtimeValue == null) runtimeValue = Obj.getdefaultValue();

            // date variable, only if it is a constant field!


            if (Obj.isConstant())


                if (runtimeValue != null)


                    if (dateFormat != null)


                        if (dateFormat.length() > 0)


                            if (runtimeValue instanceof String) {


                                String dateStr = "";


                                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(dateFormat);


                                dateStr = formatter.format(java.util.Calendar.getInstance().getTime());


                                String runtimeString = (String) runtimeValue;


                                runtimeString = replaceStr(runtimeString, "[Date]", dateStr);


                                runtimeValue = runtimeString;


                            }

            // page number, header or footer only


            if ((a == pageHeader) || (a == pageFooter))


                if (runtimeValue != null)


                    if (runtimeValue instanceof String) {


                        String runtimeString = (String) runtimeValue;


                        runtimeString = replaceStr(runtimeString, "[Page]", "" + this.pageSubCounter);


                        if (runtimeString.indexOf("[Pages]") > 0) delayed = true;


                        runtimeValue = runtimeString;


                    }

            // evaluate expression


            if (Obj instanceof RField) {


                RField field = (RField) Obj;


                if (field.evaluateExpression)


                    if (runtimeValue != null)


                        if (runtimeValue instanceof String)


                            if (((String) runtimeValue).indexOf("=") == 0) {


                                runtimeValue = this.evaluator.evaluate(((String) runtimeValue), this, a);

//                               System.out.println("Result of expression: -"+runtimeValue+"-");


                            }


            }

//           if (Obj.visible)     {


            ObjInstance = new RObjectInstance(currentX + Obj.x + a.marginLeft, currentY + Obj.y + a.marginTop, Obj, runtimeValue, areaRepetitionId);


            currentPage.addObjectInstance(ObjInstance, delayed, pageGraphics1);


            a.getInstance().addObjectInstance(ObjInstance);

//           }

/*



            if ((currentPage.finishPage) && (a!=pageFooter)) pageBreak(false);







            if (Obj instanceof RPageBreak) {



                            int i=0;



                          a.y=currentY;



                          maxHeight=a.y;



            }



*/


            if (currentPage.currentX > maxWidth) maxWidth = currentPage.currentX;


            if (currentPage.currentY > maxHeight) maxHeight = currentPage.currentY;


        }

        // calculate area height and width


        if (a.width < ((maxWidth - currentX) + a.marginRight)) a.width = (maxWidth - currentX) + a.marginRight;


        if (a.height < ((maxHeight - currentY) + a.marginBottom)) a.height = (maxHeight - currentY) + a.marginBottom;


    }


    private void updateCanvasSize() {


        int scaledWidth = (int) (mPageWidth * currentScale);


        int scaledHeight = (int) (mPageHeight * currentScale);


        int CanvasX;


        int CanvasY;

        // AWT.

        //int ViewAreaHeight=Scroll.getSize().height-Scroll.getHScrollbarHeight()-10;


        int ViewAreaHeight = this.getSize().height - 10;


        if (Scroll != null) ViewAreaHeight = this.getSize().height - Scroll.getHScrollbarHeight() - 10;

        //int ViewAreaHeight=Scroll.getSize().height-Scroll.getHorizontalScrollBar().getHeight()-10;

        // AWT.

        //int ViewAreaWidth=Scroll.getSize().width-Scroll.getVScrollbarWidth()-10;


        int ViewAreaWidth = this.getSize().width - 10;


        if (Scroll != null) ViewAreaWidth = this.getSize().width - Scroll.getVScrollbarWidth() - 10;

        //int ViewAreaWidth=Scroll.getSize().width-Scroll.getVerticalScrollBar().getWidth()-10;


        if (scaledHeight < ViewAreaHeight) {


            CanvasY = (int) ((this.getSize().height - scaledHeight) / 2);


        } else


        {


            CanvasY = 0;


            ViewAreaHeight = scaledHeight;


        }


        if (scaledWidth < ViewAreaWidth) {


            CanvasX = (int) ((this.getSize().width - scaledWidth) / 2);


        } else


        {


            CanvasX = 0;


            ViewAreaWidth = scaledWidth;


        }


        ViewArea.setSize(ViewAreaWidth, ViewAreaHeight);


        PreviewCanvas.setBounds(CanvasX, CanvasY, scaledWidth + 4, scaledHeight + 4);


    }


    private void updateCanvas() {


        double scaleX;


        double scaleY;


        double scale;


        int miniPageWidth;


        int miniPageHeight;


        int topMargin;


        int leftMargin;


        updateCanvasSize();

        //Scroll.doLayout();

        // if show more than one page, reset to max size


        if (showPages != 1) {

            // AWT.


            ViewArea.setSize(Scroll.getSize().width - Scroll.getVScrollbarWidth() - 10, Scroll.getSize().height - Scroll.getHScrollbarHeight() - 10);

            //ViewArea.setSize(Scroll.getSize().width-Scroll.getVerticalScrollBar().getWidth()-10,Scroll.getSize().height-Scroll.getHorizontalScrollBar().getHeight()-10);


            PreviewCanvas.setBounds(0, 0, Scroll.getSize().width, Scroll.getSize().height);

            //PreviewCanvas.setBackground(java.awt.Color.lightGray);


        }

        //else PreviewCanvas.setBackground(java.awt.Color.white);


        PreviewCanvas.pages = showPages;


        if (showPages == 1) {


            page1.printAtX = 0;


            page1.printAtY = 0;


        }

        // show 2 pages


        if (showPages == 2) {


            topMargin = 0;


            leftMargin = (int) (Scroll.getSize().width * 0.05);


            miniPageWidth = (int) ((Scroll.getSize().width / 2) - (1.5 * leftMargin));


            miniPageHeight = (int) ((Scroll.getSize().height) - (topMargin));


            scaleX = (double) (miniPageWidth) / mPageWidth;


            scaleY = (double) (miniPageHeight) / mPageHeight;


            if (scaleX < scaleY) scale = scaleX;


            else scale = scaleY;


            miniPageHeight = (int) (mPageHeight * scale);


            miniPageWidth = (int) (mPageWidth * scale);


            leftMargin = (int) ((Scroll.getSize().width - (2 * miniPageWidth)) / 3);


            topMargin = (int) ((Scroll.getSize().height - miniPageHeight) / 2);


            currentSeveralPagesScale = scale;

            //PreviewCanvas.getGraphics().setColor(java.awt.Color.white);

            //PreviewCanvas.getGraphics().fillRect(leftMargin,topMargin,miniPageWidth,miniPageHeight);


            page1.printAtX = leftMargin;


            page1.printAtY = topMargin;


            page1.printScale = scale;


            if ((currentPageNumber + 1) <= maxPages) {


                PreviewCanvas.pages = 2;


                page2 = (RPage) Pages.elementAt(currentPageNumber);


                PreviewCanvas.getGraphics().setColor(java.awt.Color.white);


                page2.printAtX = leftMargin + leftMargin + miniPageWidth;


                page2.printAtY = topMargin;

                //PreviewCanvas.getGraphics().fillRect(page2.printAtX,page2.printAtY,miniPageWidth,miniPageHeight);


                page2.printScale = scale;


            } else page2 = null;


        }


        PreviewCanvas.refreshImages();


        createInternalImages();


        if (doLayoutScroll) {

            //System.out.println("doscroll");


            Scroll.doLayout();


            doLayoutScroll = false;

            //Scroll.paintAll(Scroll.getGraphics());


        } else //this.ViewArea.paint(ViewArea.getGraphics());


            PreviewCanvas.paint(PreviewCanvas.getGraphics());

        //Scroll.paintAll(Scroll.getGraphics());


    }


    /**
     * set scale during preview. A value of 0.5 means zoom 50%.
     */


    public boolean setScale(double s) {


        if ((s >= 0.25) && (s <= 2)) {


            if (currentScale != s) this.doLayoutScroll = true;


            currentScale = s;


            updateCanvas();


            return true;


        }


        return false;


    }


    /**
     * returns current zoom value.
     */


    public double getScale() {


        return currentScale;


    }

    ;


    /**
     * Call this method if you want to make a preview. In this case you must add the RReport (RReport is a panel) to your preview window.
     */


    public boolean printPreview(int[] PagesToPrint, int Count) {


        Graphics g;


        RPage p;

        System.out.println("je rentre dedans");

        for (int i = 0; i < Count; i++) {


            if ((PagesToPrint[i] > 0) && (PagesToPrint[i] <= maxPages)) {


                g = newPrinterPage();


                if (g == null) return false;


                p = (RPage) Pages.elementAt(PagesToPrint[i] - 1);


                if (g != null) p.rePrint(g);


            }


        }


        this.endPrintJob();


        return true;


    }


    private void printDelayedItems() {


        RPage p;


        String s;


        for (int i = 1; i <= maxPages; i++) {


            if (Pages.size() >= i) {


                p = (RPage) Pages.elementAt(i - 1);


                if (p.getDelayedItem() != null) {


                    s = (String) p.getDelayedItem().mValue;


                    p.getDelayedItem().mValue = replaceStr(s, "[Pages]", "" + maxPages);


                    p.printDelayedItem(pageGraphics1);


                }


                p = null;


            }

            // change HTML version


            if (bHTML) sHTML[i] = replaceStr(sHTML[i], "[Pages]", "" + maxPages);


        }


    }


    public Graphics newPrinterPage() {


        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[*********************]ok je suis dans la fonction newPrinterPage");


        Frame dummyFrame = null;

        // retrieve dimension of the page


        if (pjob == null) {


            if (ComponentOwner instanceof Frame) dummyFrame = (Frame) ComponentOwner;


            pjob = printSetup.getPrintJob(dummyFrame);


            if (pjob == null) {


                Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[*********************]j'annule");


                return null;


            }


            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[*********************]je valide");


            mPageWidth = pjob.getPageDimension().width;


            mPageHeight = pjob.getPageDimension().height;


            mResolution = (int) (pjob.getPageResolution() / (2.56));


        }


        pg = pjob.getGraphics();


        return pg;


    }


    protected void endPrintJob() {


        if (pg != null) pg.dispose();


        if (pjob != null) pjob.end();


        pjob = null;


    }


    private void createInternalImages() {


        this.PreviewCanvas.lastPage1 = null; // force new painting


        this.PreviewCanvas.lastPage2 = null;


        pageImage1 = null;


        pageImage2 = null;


        if (ComponentOwner != null) {


            pageImage1 = ComponentOwner.createImage((int) (mPageWidth), (int) (mPageHeight));

            //pageImage1=ComponentOwner.createImage((int) (mPageWidth*this.currentSeveralPagesScale),(int) (mPageHeight*this.currentSeveralPagesScale));

            // create second page if we make preview


            if (showPages > 1)  //pageImage2=ComponentOwner.createImage((int) (mPageWidth*this.currentSeveralPagesScale),(int) (mPageHeight*this.currentSeveralPagesScale));


                pageImage2 = ComponentOwner.createImage((int) (mPageWidth), (int) (mPageHeight));


        } else {

            // this will only work with jdk 1.2


            if (bufferedImageType == -999) bufferedImageType = java.awt.image.BufferedImage.TYPE_INT_RGB;


            pageImage1 = new java.awt.image.BufferedImage((int) (mPageWidth), (int) (mPageHeight), bufferedImageType);

            //pageImage1=new java.awt.image.BufferedImage((int) (mPageWidth*this.currentSeveralPagesScale),(int)  (mPageHeight*this.currentSeveralPagesScale),bufferedImageType);

            // create second page if we make preview


            if (showPages > 1)  //pageImage2=new java.awt.image.BufferedImage((int) (mPageWidth*this.currentSeveralPagesScale),(int)  (mPageHeight*this.currentSeveralPagesScale),bufferedImageType);


                pageImage2 = new java.awt.image.BufferedImage((int) (mPageWidth), (int) (mPageHeight), bufferedImageType);


        }


        pageGraphics1 = pageImage1.getGraphics();


        if (pageImage2 != null) pageGraphics2 = pageImage2.getGraphics();


    }


    protected RPage newPage(boolean preview, boolean firstPage) {


        currentPageNumber++;


        pageSubCounter++;


        maxPages++;


        RPage r;


        if ((!preview) && (!disabledPrinting) && (!firstCreateObjects)) {// direct printing, create new printer page


            Graphics g = newPrinterPage();


            if (g == null) return null;


            pageGraphics1 = g;


            r = new RPage(mPageWidth, mPageHeight, mResolution, pdfLayer, DHtmlLayer);


            if (!discardPagesInmediatelly) Pages.addElement((Object) r);


        } else // preview, use canvas graphics


        {

            // create only one image

            //if ((firstPage) || (preview) || (firstCreateObjects)) {


            Image img = null;


            if (pageImage1 == null) {


                if (ComponentOwner != null) {


                    pageImage1 = ComponentOwner.createImage(mPageWidth, mPageHeight);


                } else {

                    // this will only work with jdk 1.2


                    if (bufferedImageType == -999) bufferedImageType = java.awt.image.BufferedImage.TYPE_INT_RGB;


                    pageImage1 = new java.awt.image.BufferedImage(mPageWidth, mPageHeight, bufferedImageType);


                }


                pageGraphics1 = pageImage1.getGraphics();


            }


            r = new RPage(mPageWidth, mPageHeight, mResolution, pdfLayer, DHtmlLayer);


            pageGraphics1.setColor(java.awt.Color.white);


            pageGraphics1.fillRect(0, 0, mPageWidth, mPageHeight);

            // }

            // else  {

            //     r=currentPage;

            // }


            pageGraphics1.setColor(java.awt.Color.white);


            pageGraphics1.fillRect(0, 0, mPageWidth, mPageHeight);

            //if ((firstPage) || (preview))


            if (!discardPagesInmediatelly) Pages.addElement((Object) r);


        }


        return r;


    }


    private void updatePage() {


        if (currentPageNumber > 0) {


            page1 = (RPage) Pages.elementAt(currentPageNumber - 1);


        }


    }


    /**
     * used by the Preview Window to tell RReport it is being previewed.
     */


    public void setPreview(boolean b) {


        bPreview = b;


    }


    private String startHTMLPage() {


        String s;


        s = "<html>" + newLine();


        s = s + "<head>" + newLine();


        s = s + "<meta name=\"GENERATOR\" Content=\"RReport\">" + newLine();


        s = s + "<title>" + repTitle + "</title>" + newLine();


        s = s + "</head>" + newLine();


        s = s + "<BODY topmargin=0 leftmargin=0 BGCOLOR=\"#ffffff\">" + newLine();


        return s;


    }


    private String endHTMLPage() {


        String s;


        s = "</body>" + newLine();


        s = s + "</html>" + newLine();


        return s;


    }


    /**
     * starts a report. Perform tasks like print the report header and all its related areas. Call this method before you call any printArea().
     */


    public boolean prepare() {


        areaRepetitionId = 0;


        Pages.removeAllElements();


        this.reportStarted = false;


        this.endOfReport = false;


        currentPageNumber = 0;


        pageSubCounter = 0;


        currentScale = 1;


        this.currentSeveralPagesScale = 1;


        maxPages = 0;


        String[] RegMsg = {"You are running an evaluation version of RReport.", "", "Please, send an e-mail to RReport@confluencia.net", "for registration."};

        // soy demo window


        if (this.bPreview)


            if (d()) {


                dNumber = 0;


                if (ComponentOwner instanceof Frame) new RMsg((Frame) ComponentOwner, "Registration", RegMsg).show();


                else new RMsg(new Frame(), "Registration", RegMsg).show();


            }


        sHTML[1] = startHTMLPage();

        // create dhtml layer if necessary


        DHtmlLayer = null;


        if ((bHTML) && (bDHTML)) {


            DHtmlLayer = new DHTMLLayer(mPageWidth, mPageHeight);


            DHtmlLayer.imagesDirectory = this.exportDirectory;


            DHtmlLayer.imagesFormat = this.exportImagesFormat;


            DHtmlLayer.imagesHTMLPrefix = imagesHTMLPrefix;


        }


        pdfLayer = null;

        // create PDFDocument if necessary


        if (PDFFile != null)


            if (PDFFile.length() > 0) {


                try {


                    pdfLayer = new PDFLayer(PDFFile, PDFStream, mPageWidth, mPageHeight, this.getTitle());


                    pdfLayer.fontsDirectory = pdfFontsDirectory;


                }


                catch (Exception e) {


                    pdfLayer = null;


                    System.out.println("Error creating PDF Document: " + e.getMessage());


                }


            }


        updateCanvasSize();


        currentPage = newPage(bPreview, true);


        page1 = currentPage;


        if (currentPage == null) {


            printingCancelled = true;


            return false;


        }


        drawBackground();


        currentMaxY = marginTop;


        currentX = marginLeft;


        currentY = marginTop;

        // print report header


        if (reportHeader != null) {


            printArea(reportHeader);


        } else {

            // print 1st page header


            if (pageHeader != null) {


                printArea(pageHeader);


            }


        }


        return true;


    }


    /**
     * This area will be printed at the top of each page.
     */


    public void setPageHeader(RArea area) {


        pageHeader = area;


        if (area != null) area.setAreaType(area.AREA_PAGE_HEADER);


    }


    /**
     * This area will be printed at the bottom of each page.
     */


    public void setPageFooter(RArea area) {


        pageFooter = area;


        if (area != null) area.setAreaType(area.AREA_PAGE_FOOTER);


    }


    /**
     * This area will be printed when you call prepare().
     */


    public void setReportHeader(RArea area) {


        reportHeader = area;


        if (area != null) area.setAreaType(area.AREA_REPORT_HEADER);


    }


    /**
     * This area will be printed when you call endReport().
     */


    public void setReportFooter(RArea area) {


        reportFooter = area;


        if (area != null) area.setAreaType(area.AREA_REPORT_FOOTER);


    }


    /**
     * This area will be printed at the top of each page.
     */


    public RArea getPageHeader() {


        return pageHeader;


    }


    /**
     * This area will be printed at the bottom of each page.
     */


    public RArea getPageFooter() {


        return pageFooter;


    }


    /**
     * This area will be printed when you call prepare().
     */


    public RArea getReportHeader() {


        return reportHeader;


    }


    /**
     * This area will be printed when you call endReport().
     */


    public RArea getReportFooter() {


        return reportFooter;


    }


    public void mouseExited(MouseEvent e) {
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseClicked(MouseEvent e) {
    }

    // user clicked on a object


    public void mousePressed(MouseEvent e) {


        if (objlistener != null)


            if (selectedObject != null)


                if (selectedObject.mRepObject.getActivateAction())
                    this.objlistener.objectClicked(currentPage, selectedObject);


    }

    // mouse listener for the preview


    public void mouseMoved(MouseEvent e) {


        boolean redraw = false;


        cursorX = e.getX();


        cursorY = e.getY();


        RObjectInstance item = currentPage.findObjectAt(cursorX / mResolution, cursorY / mResolution);

        // set default cursor


        if ((this.selectedObject != null) && (item == null)) {


            this.setCursor(Cursor.getDefaultCursor());


            if (showingTipTime >= 0) redraw = true;


            showingTipTime = 0;


        }


        if ((this.selectedObject != item) && (item != null)) {


            showingTipTime = 0;


            enterTime = System.currentTimeMillis();


        }

        // set action cursor


        if ((this.selectedObject == null) && (item != null) && (item.mRepObject.getActivateAction()))
            this.setCursor(actionCursor);


        this.selectedObject = item;

        // 3/4 second, show tip


        if (((System.currentTimeMillis() - enterTime) > tipDelay) && (showingTipTime == 0) && (selectedObject != null)) {


            showingTipTime = System.currentTimeMillis();

//               System.out.println("show tip");


            redraw = true;


        }


        if (redraw) PreviewCanvas.paint(PreviewCanvas.getGraphics());


    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseDragged(MouseEvent e) {
    }


    /**
     * canvas where previewed pages will be painted
     */


    class RPreview extends Panel // AWT. Canvas


    {


        /**
         * first page *
         */


        public RPage lastPage1;


        /**
         * second page *
         */


        public RPage lastPage2;


        /**
         * how many pages are we going to show in the preview?*
         */


        public int pages = 1;


        /**
         * current tooltip
         */


        public String[] tooltip;


        private Image tmpImage = null;


        public void refreshImages() {


            lastPage1 = null;


            lastPage2 = null;


        }


        public void paint(Graphics g) {


            boolean createImage = false;

            // use tmpImage for double buffering


            if (tmpImage == null) createImage = true;


            else {


                if (tmpImage.getWidth(null) != this.getSize().width) createImage = true;


                if (tmpImage.getHeight(null) != this.getSize().height) createImage = true;


            }


            if (createImage) {


                tmpImage = this.createImage(this.getSize().width, this.getSize().height);


            }


            Graphics tmpG = tmpImage.getGraphics();


            tmpG.setColor(java.awt.Color.lightGray);


            tmpG.fillRect(0, 0, this.getSize().width, this.getSize().height);


            double sc = currentSeveralPagesScale;


            if (showPages == 1) sc = currentScale;


            if (page1 != lastPage1) {


                pageGraphics1.setColor(java.awt.Color.white);


                pageGraphics1.fillRect(0, 0, pageImage1.getWidth(null) - 1, pageImage1.getHeight(null) - 1);

                //((Graphics2D) pageGraphics1).scale(mPageWidth,mPageHeight);


                page1.rePrint(pageGraphics1);


                lastPage1 = page1;


            }

            //g.drawImage(pageImage1,page1.printAtX,page1.printAtY,null);

            // System.out.println(page1.printAtX);


            tmpG.setColor(java.awt.Color.darkGray);


            tmpG.fillRect(page1.printAtX + 4, page1.printAtY + 4, (int) (pageImage1.getWidth(null) * sc), (int) (pageImage1.getHeight(null) * sc));


            tmpG.setColor(java.awt.Color.black);


            tmpG.fillRect(page1.printAtX, page1.printAtY, (int) (pageImage1.getWidth(null) * sc) + 2, (int) (pageImage1.getHeight(null) * sc) + 2);


            if (javaVersion >= 1.2)
                tmpG.drawImage(pageImage1, page1.printAtX + 1, page1.printAtY + 1, (int) (pageImage1.getWidth(null) * sc), (int) (pageImage1.getHeight(null) * sc), null);


            else {


                java.awt.Image Scaled = pageImage1.getScaledInstance((int) (pageImage1.getWidth(null) * sc), (int) (pageImage1.getHeight(null) * sc), scaleAlgorithm);


                tmpG.drawImage(Scaled, page1.printAtX + 1, page1.printAtY + 1, null);


            }


            if ((pages > 1) && (page2 != null)) {


                if (page2 != lastPage2) {


                    pageGraphics2.setColor(java.awt.Color.white);


                    pageGraphics2.fillRect(0, 0, pageImage2.getWidth(null), pageImage2.getHeight(null));


                    page2.rePrint(pageGraphics2);


                    lastPage2 = page2;


                }


                tmpG.setColor(java.awt.Color.darkGray);


                tmpG.fillRect(page2.printAtX + 4, page2.printAtY + 4, (int) (pageImage2.getWidth(null) * sc), (int) (pageImage2.getHeight(null) * sc));


                tmpG.setColor(java.awt.Color.black);


                tmpG.fillRect(page2.printAtX, page2.printAtY, (int) (pageImage2.getWidth(null) * sc) + 2, (int) (pageImage2.getHeight(null) * sc) + 2);

                //g.drawImage(pageImage2,page2.printAtX,page2.printAtY,null);


                if (javaVersion >= 1.2)
                    tmpG.drawImage(pageImage2, page2.printAtX + 1, page2.printAtY + 1, (int) (pageImage2.getWidth(null) * sc), (int) (pageImage2.getHeight(null) * sc), null);


                else {


                    java.awt.Image Scaled = pageImage2.getScaledInstance((int) (pageImage2.getWidth(null) * sc), (int) (pageImage2.getHeight(null) * sc), scaleAlgorithm);


                    tmpG.drawImage(Scaled, page2.printAtX + 1, page2.printAtY + 1, null);


                }


            }


            g.drawImage(tmpImage, 0, 0, null);

            // paint tooltip


            if ((showingTipTime != 0) && (selectedObject != null))


                if (selectedObject.tooltip != null)


                    if (selectedObject.tooltip.length() > 0) {

                        // break tip into lines


                        String tip = selectedObject.tooltip;


                        String[] tipL = new String[10];


                        int tipLCount = 0;


                        int p = tip.indexOf("\\n");


                        while (p >= 0) {


                            tipL[tipLCount++] = tip.substring(0, p);


                            tip = tip.substring(p + 2, tip.length());


                            p = tip.indexOf("\\n");


                        }


                        if (tip.length() > 0) tipL[tipLCount++] = tip;


                        g.setFont(tipFont);


                        int tipW = 0;


                        for (int i = 0; i < tipLCount; i++)
                            if (g.getFontMetrics().stringWidth(tipL[i]) > tipW)
                                tipW = g.getFontMetrics().stringWidth(tipL[i]);


                        int tipH = g.getFontMetrics().getHeight();


                        g.setColor(tipBack);  // tip background


                        g.fillRect(cursorX, cursorY + 20, tipW + 6, (tipH * tipLCount) + 4);


                        g.setColor(tipBorder); // tip border


                        g.drawRect(cursorX, cursorY + 20, tipW + 6, (tipH * tipLCount) + 4);


                        g.setColor(tipColor); // tip text


                        for (int i = 0; i < tipLCount; i++)
                            g.drawString(tipL[i], cursorX + 3, cursorY + 20 + ((i + 1) * tipH));


                    }

            //if ((pages>2) && (Page3!=null)) Page3.rePaint(g,Page3.printAtX,Page3.printAtY,Page3.printScale);

            //if ((pages>3)&& (Page4!=null))  Page4.rePaint(g,Page4.printAtX,Page4.printAtY,Page4.printScale);


        }


    }


    class RAreaProp


    {


        public double topY;


        public double bottomY;


        public double x;


        public RArea area;


        public RAreaProp(RArea a) {
            area = a;
        }


    }


    class RMsg extends Dialog implements java.awt.event.ActionListener


    {


        Panel P1 = new Panel();


        Panel P2 = new Panel();


        Panel P3 = new Panel();


        TextArea L = new TextArea(6, 40);


        Button B = new Button("OK");


        public RMsg(Frame f, String title, String[] text) {


            super(f, title, true);


            char lf = 10;


            this.setLayout(new java.awt.BorderLayout());


            L.setText("");


            for (int i=0;i<text.length;i++) {



			  L.append(text[i]+lf);



		 }







		 B.addActionListener(this);



		 B.setActionCommand("OK");



		 P3.add(B);



		 P2.add(L);







		 this.add("North",P1);



		 this.add("Center",P2);



		 this.add("South",P3);







		 this.setBounds(100,100,300,170);







		 this.setBackground(Color.lightGray);







	}



   public void actionPerformed(ActionEvent a) {



		String action=a.getActionCommand();







		if (action.compareTo("OK")==0) this.hide();



   }







}











}































