package com.java4less.rreport;


import java.util.Vector;

import java.util.Enumeration;

import java.awt.Graphics;


/**
 * This class represents a page of the report. It contains a reference to all object instances printed in that page.
 */

public class RPage {


    Vector ObjectsInstances = new Vector(100, 50);


    protected int printAtX;

    protected int printAtY;

    protected double printScale;

    protected boolean finishPage = false;


    protected double currentX = 0;

    protected double currentY = 0;

    protected double mWidth = 0;

    protected double mHeight = 0;

    private RObjectInstance itemDelayed = null;


    private int mResolution;

    private PDFLayer mPdfLayer;

    private DHTMLLayer mDHtmlLayer;


    /**
     * constructor (used by RReport)
     */

    protected RPage(int w, int h, int resolution, PDFLayer pdfLayer, DHTMLLayer DHtmlLayer) {

        mResolution = resolution;

        mPdfLayer = pdfLayer;

        mDHtmlLayer = DHtmlLayer;

        mWidth = w;

        mHeight = h;

    }


    /**
     * find object on page by position in CM (x,y). Used by the preview in order to locate the object the cursor in on.
     */

    public RObjectInstance findObjectAt(double x, double y) {


        for (int i = (ObjectsInstances.size() - 1); i >= 0; i--) {

            RObjectInstance item = (RObjectInstance) ObjectsInstances.elementAt(i);

            //System.out.println("itemX " +item.mX +" +/- "+item.mWidth +" itemY "+item.mY+" +/- "+item.mHeight);

            if ((item.getRObject().selectable) && (item.visible))

                if ((item.mX <= x) && ((item.mX + item.mWidth) >= x))

                    if ((item.mY <= y) && ((item.mY + item.mHeight) >= y))

                        return item;

        }


        return null;

    }


    /**
     * return all RObjectInstances in the page
     */

    public Vector getRObjectInstances() {
        return ObjectsInstances;
    }


    /**
     * return all RObjectInstances of an area
     */

    public Vector getRObjectInstancesByArea(long areaRepetitionId) {


        Vector v = new Vector(10, 10);


        for (int i = 0; i < ObjectsInstances.size(); i++)

            if (((RObjectInstance) ObjectsInstances.elementAt(i)).getAreaRepetitionId() == areaRepetitionId)
                v.addElement(ObjectsInstances.elementAt(i));


        return v;

    }


    /**
     * return RObjectInstance of an area by name
     */

    public RObjectInstance getRObjectInstancesByName(long areaRepetitionId, String name) {

        Vector c = getRObjectInstancesByArea(areaRepetitionId);


        for (int i = 0; i < c.size(); i++)

            if (((RObjectInstance) c.elementAt(i)).mRepObject.name.compareTo(name) == 0)
                return (RObjectInstance) c.elementAt(i);


        return null;

    }


    protected void ElementPrint(Graphics g, RObjectInstance Ele, boolean reprint) {

        Ele.print(g, reprint, mResolution, mPdfLayer, mHeight, mDHtmlLayer);

    }


    protected void printDelayedItem(Graphics g) {

        addObjectInstance(itemDelayed, false, g);

    }


    protected RObjectInstance getDelayedItem() {

        return itemDelayed;

    }


    /**
     * add robject instance to page
     */

    protected void addObjectInstance(RObjectInstance item, boolean delayed, Graphics PageGraphics) {


        if (delayed) {
            itemDelayed = item;

            currentX = item.mX + item.mWidth;

            currentY = item.mY + item.mHeight;

            return;

        }

        // print

        ObjectsInstances.addElement(item);

        finishPage = false;


        if (item.mRepObject instanceof RPageBreak)

            finishPage = true;

        else

            ElementPrint(PageGraphics, item, false); // page breaks are speciall, there is nothing to print


        currentX = item.mX + item.mWidth;

        currentY = item.mY + item.mHeight;

    }


    /**
     * prints page after preview
     */

    protected void rePrint(Graphics g) {

        for (Enumeration e = ObjectsInstances.elements(); e.hasMoreElements();)

        {

            ElementPrint(g, (RObjectInstance) e.nextElement(), true);


        }

    }


}