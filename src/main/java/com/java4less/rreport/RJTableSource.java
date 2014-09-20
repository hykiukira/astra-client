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


import javax.swing.table.*;


import javax.swing.*;


import java.awt.*;


/**
 * Uses a JTable as data source for an area.
 */


public class RJTableSource implements RSource


{


    TableModel TModel;


    int Rows;


    int Cols;


    String[] ColumnNames;


    int currentRow = -1;


    /**
     * constructs the class using the tablemodel. Name of column will be used to identify fields.
     */


    public RJTableSource(TableModel TableM) {


        Cols = TableM.getColumnCount();


        Rows = TableM.getRowCount();


        ColumnNames = new String[Cols + 1];


        TModel = TableM;


        for (int i = 0; i < Cols; i++) ColumnNames[i] = TableM.getColumnName(i);


    }


    /**
     * get value for field. It must must a name of column.
     */


    public Object rsource_getData(String FieldName) {


        for (int i = 0; i < Cols; i++) {


            if (ColumnNames[i].compareTo(FieldName) == 0) {


                return TModel.getValueAt(currentRow, i);


            }


        }


        return null;


    }


    /**
     * get next row in JTable
     */


    public boolean rsource_nextRecord() {


        if ((currentRow < (Rows - 1))) {


            currentRow++;


            return true;


        } else return false;


    }


    /**
     * reset data source to first row in table
     */


    public boolean rsource_start() {


        currentRow = -1;


        return true;


    }


    /**
     * close JTable. Empty method.
     */


    public void rsource_end() {


    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public RSource getLinkSource() {


        return null;


    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public void setLinkSource(RSource l) {
    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public String[] getFromFields() {


        return new String[0];


    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public String[] getToFields() {


        return new String[0];


    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public void setFromFields(String[] s) {
    }


    /**
     * linked RSources are not implemented in RJTableSource. This is an empty method.
     */


    public void setToFields(String[] s) {
    }


    /**
     * parameters are not implemented in RJTableSource. This is an empty method.
     */


    public void setParameter(String param,String value) {}





}


