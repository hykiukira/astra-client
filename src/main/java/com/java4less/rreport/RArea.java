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


import java.util.*;


import java.awt.*;


/**
 * Main component of a Report. A Rreport is made of Rareas. A RArea is a group of objects (RObject) that must be printed together. Typical examples of areas in a report are: <BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * - Page header<BR>
 * <p/>
 * <p/>
 * - Page footer<BR>
 * <p/>
 * <p/>
 * - Report header<BR>
 * <p/>
 * <p/>
 * - Report footer<BR>
 * <p/>
 * <p/>
 * - Detail section<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * An area can be printed 1 or more times (repetitions). Each time the area is printed the objects in the area will contain a different value. These values can be assigned:<BR>
 * <p/>
 * <p/>
 * - automatically using a RSource object.<BR>
 * <p/>
 * <p/>
 * - or programatically using area.setObjectValue(...);<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * Areas can have linked/nested areas. After each repetition of an area, all linked areas are printed. This is used to create master/detail relationships (see also RJDBCSource).<BR>
 */


public class RArea


{

    // types of areas


    public final static int AREA_PAGE_FOOTER = 1;


    public final static int AREA_PAGE_HEADER = 2;


    public final static int AREA_GROUP_FOOTER = 4;


    public final static int AREA_GROUP_HEADER = 8;


    public final static int AREA_REPORT_FOOTER = 16;


    public final static int AREA_REPORT_HEADER = 32;


    public final static int AREA_GROUP_DETAIL = 64;


    public final static int AREA_DETAIL = 128;


    public final static int AREA_BACKGROUND = 256;


    Graphics g;


    /**
     * the area has no frame.
     */


    public final static int FRAME_NONE = 0;


    /**
     * just one frame for all repetitions of this area in the page.
     */


    public final static int FRAME_PAGE = 1;


    /**
     * the area has a frame.
     */


    public final static int FRAME_AREA = 2;


    public final static int FRAME_PAGE_NO_TOP = 3;


    private int frameType = FRAME_NONE;


    private RLineStyle frameStyle = new RLineStyle(0.2f, java.awt.Color.black, RLineStyle.LINE_NORMAL);


    private RLineStyle gridStyle = new RLineStyle(0.2f, java.awt.Color.black, RLineStyle.LINE_NORMAL);
    ;


    private double[] gridXs;


    /**
     * number of repetition of the area for testing in RReport Visual Builder
     */


    public int testRepetitions = 1;


    /**
     * this area will be printed in several columns...
     */


    public int columns = 1;


    /**
     * In centimeters. If you want to leave an empty space left to the area or at the bottom of the area use these fields.
     */


    public double verticalTab = 0;


    /**
     * In centimeters. If you want to leave an empty space left to the area or at the bottom of the area use these fields.
     */


    public double horizontalTab = 0;


    /**
     * In centimeters. These are margins inside the area.
     */


    public double marginLeft;


    /**
     * In centimeters. These are margins inside the area.
     */


    public double marginRight;


    /**
     * In centimeters. These are margins inside the area.
     */


    public double marginBottom;


    /**
     * In centimeters. These are margins inside the area.
     */


    public double marginTop;


    /**
     * list of fields to group by.
     */


    protected String[] groupBy = new String[0];


    /**
     * group header, printed before first repetition of the area and before each new value of the "groupBy" fields.
     */


    protected RArea groupHeaderArea = null;


    /**
     * group footer, printed after last repetition of the area and after each new value of the "groupBy" fields.
     */


    protected RArea groupFooterArea = null;


    /**
     * internal use only, for loading the report using RReportImp
     */


    protected String groupFooterAreaName = "";


    /**
     * not used
     */


    public RArea Reference = null;


    /**
     * not used
     */


    public boolean NewReference = true; // the reference has  been printed


    /**
     * not used
     */


    public int AreaPosition = RReport.POSITION_NEXT;


    private RSource DataSource;


    private String sHTML = null;


    private boolean HorizontalGrid;


    private String name = "";


    /**
     * print this area in a new page?
     */


    public boolean newPage = false;


    /**
     * repetition of the area
     */


    protected int repetition = 0;


    private RArea linkedArea = null;


     private RArea headerArea = null;


    private java.awt.Color mBackground = java.awt.Color.white;


    private RPicture mBackgroundImage;


    /**
     * resolution used when exporting to HTML. This resolution is used to convert CM to pixels.
     */


    public double HTMLResolution;


    /**
     * positiom X at runtime. Used by RReport.
     */


    public double x;


    /**
     * positiom Y at runtime. Used by RReport.
     */


    public double y;


    /**
     * size at runtime. Used by RReport.
     */


    public double width = 0;


    /**
     * size at runtime. Used by RReport.
     */


    public double height = 0;


    /**
     * defined size of the area. Not modified at runtime.
     */


    public double layoutHeight = -1;


    private boolean frame = false;

    // collection of object in area


    /**
     * vector that contains the Robjects in the area.
     */


    public Vector items = new Vector(10, 5);


    /**
     * is this area a group header o, group footer ...
     */


    protected int areaType = AREA_DETAIL;


    /**
     * is this area a group header o, group footer ...
     */


    public int getAreaType() {
        return areaType;
    }


    /**
     * is this area a group header o, group footer ...
     */


    public void setAreaType(int b) {
        areaType = b;
    }


    /**
     * last instance of the area
     */


    private RAreaInstance currentInstance = null;


    /**
     * instances if this is the detail area of a group.
     */


    private Vector groupInstances = new Vector();


    /**
     * last instance of the area
     */


    public RAreaInstance getInstance() {


        return currentInstance;


    }


    /**
     * add RArea Instance
     */


    public void addInstance(RAreaInstance i) {


        currentInstance = i;


        if (this.areaType == RArea.AREA_GROUP_DETAIL)


            groupInstances.addElement(i);


    }


    /**
     * instance of the detail area of a group. This is used by functions like SUM, AVG ....
     */


    public Vector getGroupOfInstances() {


        return groupInstances;


    }


    /**
     * reset group of instances
     */


    public void resetInstances() {


        groupInstances.removeAllElements();


    }


    private RAreaListener listener;


    /**
     * set area listener
     */


    public void setListener(RAreaListener l) {


        this.listener = l;


    }


    /**
     * get area listener
     */


    public RAreaListener getListener() {


        return listener;


    }


    public RArea() {


    }


    /**
     * gets/sets area name.
     */


    public void setName(String n) {
        name = n;
    }


    /**
     * gets/sets area name.
     */


    public String getName() {
        return name;
    }


    /**
     * gets/sets list of fields for grouping. When the value of these fields changes in the data source, the group header and group footer will be printed
     */


    public void setGroupByFields(String[] n) {
        groupBy = n;
    }


    /**
     * gets/sets list of fields for grouping. When the value of these fields changes in the data source, the group header and group footer will be printed
     */


    public String[] getGroupByFields() {
        return groupBy;
    }


    /**
     * gets or sets the superarea (where this area is nested).
     */


    public void setLinkedArea(RArea a) {


        this.linkedArea = a;


    }


    /**
     * gets or sets the superarea (where this area is nested).
     */


    public RArea getLinkedArea() {


        return this.linkedArea;


    }


    /**
     * gets or sets the header area for this a area. If the current repetition of the area cannot be printed in the current page, a new page is created. The header area will be printed in the new page before the current area
     */


    public void setHeaderArea(RArea a) {


        this.headerArea = a;


    }


    /**
     * gets or sets the header area for this a area. If the current repetition of the area cannot be printed in the current page, a new page is created. The header area will be printed in the new page before the current area
     */


    public RArea getHeaderArea() {


        return this.headerArea;


    }


    /**
     * gets or sets the group header area for this a area.
     */


    public void setGroupHeaderArea(RArea a) {


        this.groupHeaderArea = a;


    }


    /**
     * gets or sets the group header area for this a area.
     */


    public RArea getGroupHeaderArea() {


        return this.groupHeaderArea;


    }


    /**
     * gets or sets the group header area for this a area.
     */


    public void setGroupFooterArea(RArea a) {


        this.groupFooterArea = a;


    }


    /**
     * gets or sets the group header area for this a area.
     */


    public RArea getGroupFooterArea() {


        return this.groupFooterArea;


    }


    /**
     * to be used by RReportImp.java
     */


    public void importLine(String key, String val) {


        if (key.compareTo("NAME") == 0) this.setName(val);


        if (key.compareTo("HEIGHT") == 0) this.height = (new Double(val).doubleValue());


        if (key.compareTo("WIDTH") == 0) this.width = (new Double(val).doubleValue());


        if (key.compareTo("NEWPAGE") == 0) this.newPage = (val.compareTo("0") != 0);


        if (key.compareTo("TESTING") == 0) this.testRepetitions = new Integer(val).intValue();


        if (key.compareTo("COLUMNS") == 0) this.columns = new Integer(val).intValue();


        if (key.compareTo("FRTYPE") == 0) this.setFrameType(new Integer(val).intValue());


        if (key.compareTo("FRCOLOR") == 0) this.getFrameStyle().setColor(RObject.convertColor(val));


        if (key.compareTo("FRSTYLE") == 0) getFrameStyle().setType(new Integer(val).intValue());


        if (key.compareTo("FRWIDTH") == 0) getFrameStyle().setWidth((float) new Double(val).doubleValue());


        if ((key.compareTo("HGRID") == 0) && (val.compareTo("1") == 0)) this.setHorizontalGrid(true);


        if ((key.compareTo("HGRID") == 0) && (val.compareTo("0") == 0)) this.setHorizontalGrid(false);


        if (key.compareTo("ATYPE") == 0) this.areaType = new Integer(val).intValue();


        if ((key.compareTo("GRID") == 0) && (val.trim().length() > 0)) this.setGrid(RObject.convertDoubleList(val));


        if ((key.compareTo("GRID") == 0) && (val.trim().length() == 0)) this.setGrid(null);


        if (key.compareTo("GRCOLOR") == 0) this.getGridStyle().setColor(RObject.convertColor(val));


        if (key.compareTo("GRSTYLE") == 0) getGridStyle().setType(new Integer(val).intValue());


        if (key.compareTo("GRWIDTH") == 0) getGridStyle().setWidth((float) new Double(val).doubleValue());


        if (key.compareTo("BCOLOR") == 0) this.setBackground(RObject.convertColor(val));

        //if (key.compareTo("BIMAGE")==0)  {

        //	this.setBackgroundImage(new RPicture());

        //	this.getBackgroundImage().currentImage.setName(val);

        //	this.getBackgroundImage().setdefaultValue(this.getBackgroundImage().currentImage.getImage());

        //}


    }


    /**
     * background color
     */


    public java.awt.Color getBackground() {


        return mBackground;


    }


    /**
     * background image
     */


    public RPicture getBackgroundImage() {


        return mBackgroundImage;


    }


    /**
     * background color
     */


    public void setBackground(java.awt.Color c) {


        mBackground = c;


    }


    /**
     * background image
     */


    public void setBackgroundImage(RPicture i) {


        mBackgroundImage = i;


    }


    /**
     * set frame type for the area. Default is NONE
     */


    public void setFrame(boolean b) {


        frame = b;


        if (b) {


            if (this.frameType == FRAME_NONE) this.frameType = FRAME_AREA;


        }


    }


    /**
     * Sets the Rsource used to assign values to the elements in the area
     */


    public void setDataSource(RSource s) {


        DataSource = s;


    }


    /**
     * gets the Rsource used to assign values to the elements in the area
     */


    public RSource getDataSource() {


        return DataSource;


    }


    private String newLine() {


        char lf = 10;


        char cr = 13;


        return "" + cr + lf;
    }


    /**
     * gets the HTML version of the area.
     */


    public String getHTML() {


        if (sHTML == null) sHTML = toHTML();


        return sHTML;


    }


    /**
     * sets the HTML version of the area.
     */


    public void setHTML(String h) {


        sHTML = h;


    }


    /**
     * specifies that the grid must use horizontal separators also.
     */


    public boolean getHorizontalGrid() {


        return HorizontalGrid;


    }


    /**
     * specifies that the grid must use horizontal separators also.
     */


    public void setHorizontalGrid(boolean b) {


        HorizontalGrid = b;


    }


    /**
     * sets the frame type.
     */


    public void setFrameType(int fType) {


        frameType = fType;


    }


    /**
     * sets the frame style. The frame will be displayed at the position marginLeft/3 , marginTop/3 ...
     */


    public void setFrameStyle(RLineStyle fStyle) {


        frameStyle = fStyle;


    }


    /**
     * styled used by the grid. See setGrid().
     */


    public void setGridStyle(RLineStyle gStyle) {


        gridStyle = gStyle;


    }


    /**
     * Sets the positions of the columns (lines) of the grid.
     */


    public void setGrid(double[] colSeps) {


        gridXs = colSeps;


    }


    /**
     * frame type: NONE,PAGE...
     */


    public int getFrameType() {


        return frameType;


    }


    /**
     * gets the frame style. The frame will be displayed at the position marginLeft/3 , marginTop/3 ...
     */


    public RLineStyle getFrameStyle() {


        return frameStyle;


    }


    /**
     * line style of the frame.
     */


    public RLineStyle getGridStyle() {


        return gridStyle;


    }


    /**
     * gets the positions of the columns (lines) of the grid.
     */


    public double[] getGrid() {


        return gridXs;


    }


    private String toHTML() {


        RObject Min = null;


        RObject NextInRow;


        RObject Obj;


        RObject Obj2;


        String HTML = "";


        String currentRow = "";


        double currentRowHeight;


        double currentRowWidth;


        RObject nextObj;


        Enumeration enu;


        int count = 0;


        Vector SortedItems;


        Vector copyItems = new Vector();


        Vector tmp = (Vector) items.clone();

        // copyItems should be sorted by x


        int tmpCount = tmp.size();


        for (int i = 0; i < tmpCount; i++) {


            double minX = 999;


            for (int j = 0; j < tmp.size(); j++) {


                RObject ro = (RObject) tmp.elementAt(j);


                if (ro.x < minX) {


                    Min = ro;


                    minX = ro.x;


                }


            }


            tmp.removeElement(Min);


            copyItems.addElement(Min);


        }


        HTML = "";

        // line break for top margin


        for (double i = 0; i < marginBottom; i = i + 0.5) HTML = HTML + "<BR>";

        // remove not HTML elements


        for (Enumeration e = copyItems.elements(); e.hasMoreElements();) {


            Obj = (RObject) e.nextElement();


            if ((!Obj.canHTML()) || (Obj.name.compareTo("") == 0))


            {


                if (copyItems.size() == 1) copyItems.removeAllElements();


                else copyItems.removeElement(Obj);


                e = copyItems.elements();


            }


        }


        Min = null;


        while (copyItems.size() > 0) {


            SortedItems = new Vector(items.capacity(), 5);


            Min = null;


            Obj = null;

            // create row in table, look for first element


            for (Enumeration e = copyItems.elements(); e.hasMoreElements();) {


                Obj = (RObject) e.nextElement();

                // look for top left


                if (Min == null) Min = Obj;


                else {


                    if (Obj.y < Min.y) Min = Obj;


                    else if ((Obj.x < Min.x) && (Obj.y == Min.y)) Min = Obj;


                }


            }

            // remove element


            if (copyItems.size() == 1) copyItems.removeAllElements();


            else copyItems.removeElement(Min);


            SortedItems.addElement(Min);


            currentRowHeight = Min.height + Min.y;


            currentRowWidth = Min.x + Min.width;

            // find all objects in the same row


            while (true) {

                //


                Vector candidates = new Vector();

                // look for all elemens in its row


                for (Enumeration e = copyItems.elements(); e.hasMoreElements();) {


                    Obj = (RObject) e.nextElement();


                    if (Obj.x >= currentRowWidth)


                        if (Obj.y <= currentRowHeight) candidates.addElement(Obj);


                    Obj = null;


                }


                NextInRow = null;

                // candidates are sorted by x

                // get first candidate that


                for (int h = 0; h < candidates.size(); h++) {


                    NextInRow = (RObject) candidates.elementAt(h);

                    // does it overlap with an height object?


                    for (int g = h + 1; g < candidates.size(); g++) {


                        RObject o = (RObject) candidates.elementAt(g);


                        if (NextInRow.y > o.y) { // it is higher!


                            if (o.x < (NextInRow.x + NextInRow.width)) {


                                NextInRow = null;


                                break; // this candidate was not good, proceed


                            }


                        }


                    }


                    if (NextInRow != null) break;


                }


                if (NextInRow == null) break;


                else {


                    if (copyItems.size() == 1) copyItems.removeAllElements();


                    else copyItems.removeElement(NextInRow);


                    SortedItems.addElement(NextInRow);


                    if ((NextInRow.y + NextInRow.height) > currentRowHeight)
                        currentRowHeight = (NextInRow.y + NextInRow.height);


                    if ((NextInRow.x + NextInRow.width) > currentRowWidth)
                        currentRowWidth = (NextInRow.x + NextInRow.width);

                    //if ((NextInRow.y+NextInRow.height)<currentRowHeight) currentRowHeight=(NextInRow.y+NextInRow.height);


                }


            }

            // create row


            currentRow = "<tr HEIGHT=" + (new Integer((int) ((currentRowHeight - Min.y) * HTMLResolution)).toString()) + ">" + newLine();


            if (Min.x > 0)
                currentRow = currentRow + "<td WIDTH=" + (new Integer((int) (Min.x * HTMLResolution)).toString()) + "></td>" + newLine();


            enu = SortedItems.elements();


            Obj = (RObject) enu.nextElement();


            for (; Obj != null;) {


                if (enu.hasMoreElements()) nextObj = (RObject) enu.nextElement();


                else nextObj = null;


                double w;


                if (nextObj == null) w = Obj.width;


                else w = nextObj.x - Obj.x;


                if (w < Obj.width) w = Obj.width;


                count++;


                currentRow = currentRow + "<td WIDTH=" + (new Integer((int) (w * HTMLResolution)).toString()) + "<%%" + Obj.name + "%%>" + newLine();


                Obj = nextObj;


            }


            currentRow = currentRow + "</tr>" + newLine();


            currentRow = "<table BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\">" + newLine() + currentRow;


            currentRow = currentRow + newLine() + "</table>";


            HTML = HTML + currentRow;


        } // Size >0

        // line break for top margin


        for (double i = 0; i < marginBottom; i = i + 0.5) HTML = HTML + "<BR>";


        if (count == 0) return "";


        return HTML;


    }


    /**
     * Adds an element to the area.
     */


    public void add(RObject obj) {


        add(obj, true);


    }


    /**
     * Adds an element to the area. If end=true this will be the last element in the list (only for use of the Visual Builder).
     */


    public void add(RObject obj, boolean end) {


        if ((obj.x + obj.width) > width) width = (obj.x + obj.width);


        if ((obj.y + obj.height) > height) height = (obj.y + obj.height);


        if (end) items.addElement(obj);


        else items.insertElementAt(obj, 0);


    }


    /**
     * Returns the Robject whose name matches the parameter.
     */


    public RObject getItemByName(String name) {


        RObject r;


        for (Enumeration e = items.elements(); e.hasMoreElements();) {


            r = (RObject) e.nextElement();

            // is this the element?


            if (r.name.compareTo(name) == 0) {


                return r;
            }


        }


        return null;


    }


    /**
     * Sets the runtime value of an element in the area.
     */


    public boolean setObjectValue(String name, Object Value) {


        RObject r;


        for (Enumeration e = items.elements(); e.hasMoreElements();) {


            r = (RObject) e.nextElement();

            // is this the element?


            if (r.name.compareTo(name) == 0) {


                r.setruntimeValue(Value);


                return true;}


		}





		return false;





	}





        /**
         * Getter for property g.
         *
         * @return Value of property g.
         */


        public java.awt.Graphics getG() {


            return g;


        }        





        /**
         * Setter for property g.
         *
         * @param g New value of property g.
         */


        public void setG(java.awt.Graphics g) {


            this.g = g;


        }


        


}