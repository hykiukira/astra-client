package com.java4less.rreport;


import java.awt.*;


/**
 * Object printed in a page. It contains the object to be printed (RObject) and the runtime value. This class is instanciated at runtime by RReport only.
 */

public class RObjectInstance {

    /**
     * runtime position and size of the object instance in the page *
     */

    protected double mX;

    protected double mY;

    protected double mWidth;

    protected double mHeight;

    protected RObject mRepObject;

    protected Object mValue;

    /**
     * this allow different instance of objects to have different colors and fonts *
     */

    protected Font FontType;

    protected Color FontColor;

    protected Color FillColor;

    protected String tooltip = "";

    private long areaId;

    public boolean visible = true;


    /**
     * constructor used by rreport. Creates a RObject instance for object RepObject , value and position x,y
     */

    protected RObjectInstance(double x, double y, RObject RepObject, Object Value, long area) {

        area = areaId;

        mX = x;

        mY = y;

        mRepObject = RepObject;

        mValue = Value;

        tooltip = RepObject.tooltip;

        visible = RepObject.visible;


        if (RepObject instanceof RCombo) {

            FontType = ((RCombo) RepObject).FontType;

            FontColor = ((RCombo) RepObject).FontColor;

        }


        if (RepObject instanceof RField) {

            FontType = ((RField) RepObject).FontType;

            FontColor = ((RField) RepObject).FontColor;

        }


        if (RepObject instanceof RRectangle) {

            FillColor = ((RRectangle) RepObject).fillColor;

        }


    }


    /**
     * printed oject instance now
     */

    protected void print(Graphics g, boolean reprint, int resolution, PDFLayer pdfLayer, double pageHeight, DHTMLLayer DHtmlLayer) {


        if (!visible) return;


        double tmpHeight = 0;


        mRepObject.tooltip = tooltip;


        if (mRepObject instanceof RCombo) {

            ((RCombo) mRepObject).FontType = FontType;

            ((RCombo) mRepObject).FontColor = FontColor;

        }


        if (mRepObject instanceof RField) {

            ((RField) mRepObject).FontType = FontType;

            ((RField) mRepObject).FontColor = FontColor;

        }


        if (mRepObject instanceof RRectangle) {

            ((RRectangle) mRepObject).fillColor = FillColor;

        }


        tmpHeight = mRepObject.height;

        mRepObject.convertToPixels(resolution);

        mRepObject.print(g, mX * resolution, mY * resolution, mValue);

        // print also PDF here

        if (!reprint) {

            if (pdfLayer != null)
                pdfLayer.print(mRepObject, Math.floor(mX * resolution), pageHeight - Math.floor(mY * resolution), mValue);

            if (DHtmlLayer != null)
                DHtmlLayer.print(mRepObject, (Math.floor(mX * resolution)), pageHeight - Math.floor(mY * resolution), mValue);

        }


        mRepObject.convertToCM(resolution);

        mWidth = mRepObject.width;

        mHeight = mRepObject.height;


        mRepObject.height = tmpHeight;


    }


    /**
     * get value
     */

    public Object getValue() {
        return this.mValue;
    }


    /**
     * get RObject
     */

    public RObject getRObject() {
        return this.mRepObject;
    }


    /**
     * get repetition id of the area where this instance is printed
     */

    public long getAreaRepetitionId() {
        return areaId;
    }


    /**
     * get RArea where the object is located
     */

    public RArea getArea() {
        return mRepObject.area;
    }


}

