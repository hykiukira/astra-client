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
 * Uses an array as data source for an area. See also RSource.<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * exampleOrder.java. Uses the report definition file Order.rep. It uses an array as data source for the report. runExampleOrder.bat <BR>
 */


public class RArraySource implements RSource


{


    String[] ColumnNames;


    Object[][] Values;


    int Rows;


    int Cols;


    int currentRow = -1;


    /**
     * linked sources
     */


    private RSource linkFrom;


    private String[] fromFields;


    private String[] toFields;


    /**
     * The first array contains the names of the fields, the second the values.
     */


    public RArraySource(String[] names, Object[][] val) {


        setData(names, val);


    }


    /**
     * set new array for the source
     */


    public void setData(String[] names, Object[][] val) {


        ColumnNames = names;


        Cols = names.length;


        Values = val;


        Rows = val.length;


    }


    /**
     * get value for field.
     */


    public Object rsource_getData(String FieldName) {


        for (int i = 0; i < Cols; i++) {


            if (ColumnNames[i].compareTo(FieldName) == 0) {


                return Values[currentRow][i];


            }


        }


        return null;


    }


    /**
     * get next row in array.
     */


    public boolean rsource_nextRecord() {

        // get next row until we find one which matces the current row in the link area


        while ((currentRow < (Rows - 1))) {


            currentRow++;


            if (checkForeignKey()) return true;


        }


        return false;


    }


    /**
     * does this row belong to the current row in the linked source?
     */


    private boolean checkForeignKey() {

        // if there is no linked source, accept row.


        if (this.fromFields == null) return true;


        if (this.toFields == null) return true;


        if (this.linkFrom == null) return true;


        for (int i = 0; i < this.fromFields.length; i++) {


            if (this.toFields.length <= i) break;


            Object value1 = this.linkFrom.rsource_getData(this.fromFields[i]);


            Object value2 = this.rsource_getData(this.toFields[i]);

            // does field in linked source match field in this source?


            if (!value1.equals(value2)) return false;


        }


        return true;


    }


    /**
     * set array to read first row.
     */


    public boolean rsource_start() {


        currentRow = -1;


        return true;


    }


    /**
     * finalizes data source. Empty method.
     */


    public void rsource_end() {


    }


    /**
     * linked RSource (if the RSource supports nested RSources).
     */


    public RSource getLinkSource() {


        return linkFrom;


    }


    /**
     * linked RSource (if the RSource supports nested RSources).
     */


    public void setLinkSource(RSource l) {
        this.linkFrom = l;
    }


    /**
     * field names in the linked RSource used for the relationship (this is normally the primary key in a table or record).
     */


    public String[] getFromFields() {


        return fromFields;


    }


    /**
     * field names in this RSource used for the relationship (this is normally a foreign key).
     */


    public String[] getToFields() {


        return toFields;


    }


    /**
     * field names in the linked RSource used for the relationship (this is normally the primary key).
     */


    public void setFromFields(String[] s) {
        this.fromFields = s;
    }


    /**
     * field names in this RSource used for the relationship (this is normally a foreign key).
     */


    public void setToFields(String[] s) {


        this.toFields = s;


    }


    /**
     * parameters are not implemented in RArraySource. This is an empty method.
     */


    public void setParameter(String param,String value) {}





}


