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


import java.util.*;


import java.sql.*;


import java.math.*;


/**
 * Uses a database table (or a SQL query) as data source for an area.
 */


public class RJDBCSource implements RSource


{


    int[] ColumnTypes;


    String[] ColumnNames;


    Object[] Values;


    int Cols;


    Statement st;


    ResultSet rec;


    String msSQL;


    private RSource linkFrom;


    private String mFilterSQL = "";


    private String[] fromFields;


    private String[] toFields;


    private Vector subTables = new Vector();


    private String[] params = new String[10];


    private String[] paramsV = new String[10];


    private int paramCount = 0;


    /**
     * to be used by RReport Visual Builder. Required if promptForParameters=true.
     */


    public Frame parentFrame = null;


    /**
     * to be used by RReport Visual Builder. If true, you will be prompted for the missing parameters.
     */


    public boolean promptForParameters = false;


    /**
     * If true, the [%] parameters in the SQL statement will be replaced with the values provided (see setParameter()). The default is true.
     */


    public boolean allowParameters = true;


    /**
     * sets a value for a parameter. SQL stattments may ccontain parameters in the form of [%paramname]. For example: "Select * from table where id=[%parameter1]". Use myRJDBCSource.setParameter("Parameter1" ,value) before you run the report.
     */


    public void setParameter(String param, String value) {


        for (int i = 0; i < paramCount; i++)


            if (params[i].compareTo(param) == 0) {


                paramsV[i] = value;


                return;


            }

        // new entry


        params[paramCount] = param;


        paramsV[paramCount] = value;


        paramCount++;


    }


    private String getParameterValue(String param) {


        for (int i = 0; i < paramCount; i++)


            if (params[i].compareTo(param) == 0) return paramsV[i];


        return null;


    }


    /**
     * changes the sql of this data source
     */


    public void setSQL(String s) {
        msSQL = s;
    }


    /**
     * The fields' names in the select command (psSQL) must match the elements' names in the area.
     */


    public RJDBCSource(java.sql.Statement pst, String psSQL) {


        st = pst;


        msSQL = psSQL;


    }

    // replace parameters names with values


    private String repParameters(String s) {


        int p1 = s.indexOf("[%");


        int p2 = s.indexOf("]");


        String value;


        while ((p1 >= 0) && (p2 >= 0) && (p2 > p1)) {


            String param = s.substring(p1 + 2, p2);


            value = getParameterValue(param);


            if (value == null)


                if (promptForParameters) {

                    // get parameter from user


                    ParamInput pi = new ParamInput(parentFrame, param);


                    pi.show();


                    if (!pi.cancelled) {


                        value = pi.result;


                    }


                    pi = null;


                }

            // replace param


            if (value != null) s = s.substring(0, p1) + value + s.substring(p2 + 1, s.length());


            p1 = s.indexOf("[%", p2 + 1);


            p2 = s.indexOf("]", p2 + 1);


        }


        return s;


    }


    /**
     * gets or sets the Statement object used for executing the SQL query.
     */


    public Statement getStatement() {


        return st;


    }


    /**
     * gets or sets the Statement object used for executing the SQL query.
     */


    public void setStatement(java.sql.Statement pst) {


        st = pst;
    }

    // RJDBCSource (table) this one depends from


    /**
     * linked RJDBCSource. Please see: Using a database.
     */


    public RSource getLinkSource() {


        return linkFrom;


    }


    /**
     * linked RJDBCSource. Please see: Using a database.
     */


    public void setLinkSource(RJDBCSource l) {


        linkFrom = l;


    }


    /**
     * linked RJDBCSource. Please see: Using a database.
     */


    public void setLinkSource(RSource l) {


        linkFrom = l;


    }

    // fields used to link tables


    /**
     * field names in the linked RJDBCSource used for the relationship (this is normally the primary key).
     */


    public String[] getFromFields() {


        return fromFields;


    }


    /**
     * field names in this RDBCSource used for the relationship (this is normally a foreign key).
     */


    public String[] getToFields() {


        return toFields;


    }


    /**
     * field names in the linked RJDBCSource used for the relationship (this is normally the primary key).
     */


    public void setFromFields(String[] s) {


        fromFields = s;


    }


    /**
     * field names in this RDBCSource used for the relationship (this is normally a foreign key).
     */


    public void setToFields(String[] s) {


        toFields = s;


    }

    // additional SQL filter


    /**
     * current filter that will be used to contruct the SQL statement. For example "date < '01/01/2001' ". This will be added to the psSQL parameter in the constructor.
     */


    public String getFilter() {


        return mFilterSQL;


    }


    /**
     * current filter that will be used to contruct the SQL statement. For example "date < '01/01/2001' ". This will be added to the psSQL parameter in the constructor.
     */


    public void setFilter(String s) {


        mFilterSQL = s;


    }


    /**
     * get value for field "Fieldname".
     */


    public Object rsource_getData(String FieldName) {


        Object result;


        String FieldName2 = FieldName;


        int p = FieldName.indexOf(".");

        //System.out.println(FieldName);


        if (p > 0) FieldName2 = FieldName.substring(p + 1, FieldName.length());


        for (int i = 1; i <= Cols; i++) {


            if (ColumnNames[i].equalsIgnoreCase(FieldName))

                // do not read twice the same


                return Values[i];


            if (ColumnNames[i].equalsIgnoreCase(FieldName2))

                // do not read twice the same


                return Values[i];


        }

//        System.out.println("Field not found");


        return null;


    }


    /**
     * get next record in table
     */


    public boolean rsource_nextRecord() {


        Object result;


        boolean more;

        //System.out.println("Next record "+this.msSQL);

        //if (rec==null) System.out.println("record is IS NULL");


        try {


            more = rec.next();


        } catch (Exception e1) {


            e1.printStackTrace();


            more = false;
        }


        if (!more) return false;


        else {

            // System.out.println("Have record");

            // read all values


            for (int i = 1; i <= Cols; i++) {


                result = null;


                try {


                    if (ColumnTypes[i] == java.sql.Types.BIT) {


                        boolean b = rec.getBoolean(i);


                        if (!rec.wasNull()) result = new Boolean(b);


                    }


                    if ((ColumnTypes[i] == java.sql.Types.SMALLINT) || (ColumnTypes[i] == java.sql.Types.INTEGER)) {


                        int j = rec.getInt(i);


                        if (!rec.wasNull()) result = new Integer(j);


                    }


                    if ((ColumnTypes[i] == java.sql.Types.CHAR) || (ColumnTypes[i] == java.sql.Types.VARCHAR)) {


                        result = rec.getString(i);


                    }


                    if ((ColumnTypes[i] == java.sql.Types.DECIMAL) || (ColumnTypes[i] == java.sql.Types.BIGINT) || (ColumnTypes[i] == java.sql.Types.DOUBLE) || (ColumnTypes[i] == java.sql.Types.REAL) || (ColumnTypes[i] == java.sql.Types.NUMERIC)) {


                        double d = rec.getDouble(i);


                        if (!rec.wasNull()) result = new Double(d);


                    }


                    if (ColumnTypes[i] == java.sql.Types.DATE) {


                        result = rec.getDate(i);


                        if (rec.wasNull()) result = null;


                    }


                    if (ColumnTypes[i] == java.sql.Types.TIME) {


                        result = rec.getTime(i);


                        if (rec.wasNull()) result = null;


                    }


                    if (ColumnTypes[i] == java.sql.Types.TIMESTAMP) {


                        result = rec.getTimestamp(i);


                        if (rec.wasNull()) result = null;


                    }


                    if (ColumnTypes[i] == java.sql.Types.FLOAT) {


                        float f = rec.getFloat(i);


                        if (!rec.wasNull()) result = new Float(f);


                    }

                    // read eveythingelse as string (for example nvarchar)


                    if (result == null) result = rec.getString(i);


                } catch (Exception e2) {
                    result = null;
                }


                Values[i] = result;


            } // for


            return true;


        } // else


    } // sub

// add a filter to a SQL


    private String addFilter(String pSQL, String filter) {


        String by = "";


        String SQL = pSQL;


        int p1 = SQL.toUpperCase().indexOf("ORDER BY");


        int p2 = SQL.toUpperCase().indexOf("GROUP BY");


        int p = p1;


        if (p1 <= 0) p = p2;


        if ((p2 > 0) && (p2 < p1)) p = p2;


        if (p > 0) {


            by = SQL.substring(p, SQL.length());


            SQL = SQL.substring(0, p);


        }


        if (SQL.toUpperCase().indexOf("WHERE") > 0) return SQL + " AND " + filter + " " + by;


        else return SQL + " WHERE " + filter + " " + by;


    }

    // get linked fiels values


    private String getLinkSQL() {


        if (this.fromFields == null) return "";


        if (this.toFields == null) return "";


        if (this.linkFrom == null) return "";


        String sql = "";


        for (int i = 0; i < this.fromFields.length; i++) {


            if (this.toFields.length <= i) break;


            if (i > 0) sql = sql + " AND ";


            sql = sql + toFields[i] + "=";


            Object value = this.linkFrom.rsource_getData(this.fromFields[i]);


            if (value instanceof String) sql = sql + "'" + ((String) value) + "'";


            if (value instanceof Double) sql = sql + ((Double) value).doubleValue();


            if (value instanceof Float) sql = sql + ((Float) value).floatValue();


            if (value instanceof Integer) sql = sql + ((Integer) value).intValue();


            if (value instanceof Boolean) {


                if (((Boolean) value).booleanValue()) sql = sql + "-1";


                else sql = sql + "0";


            }


        }


        return sql;


    }


    /**
     * opens database table
     */


    public boolean rsource_start() {


        String errDesc;


        java.sql.ResultSetMetaData Meta;


        try {

            // convert to a select SQL if it is only a table name


            if (msSQL.toUpperCase().trim().indexOf("SELECT ") != 0) msSQL = "SELECT * FROM " + msSQL;


            if (allowParameters) msSQL = this.repParameters(msSQL);


            String tmpSQL = msSQL;


            if (mFilterSQL.trim().length() > 0) tmpSQL = addFilter(tmpSQL, mFilterSQL);


            String linkSQL = getLinkSQL();


            if (linkSQL.trim().length() > 0) tmpSQL = addFilter(tmpSQL, linkSQL);

            //  System.out.println("Executing: "+tmpSQL);


            rec = st.executeQuery(tmpSQL);


            Meta = rec.getMetaData();


            Cols = Meta.getColumnCount();


            ColumnNames = new String[Cols + 1];


            Values = new Object[Cols + 1];


            ColumnTypes = new int[Cols + 1];

            for (int i = 1; i <= Cols; i++) {


                ColumnTypes[i] = Meta.getColumnType(i);


                ColumnNames[i] = Meta.getColumnName(i);


            }


        } catch (Exception e1) {


            errDesc = e1.getMessage();


            System.out.println("Error: " + errDesc);


            return false;
        }


        return true;


    }


    /**
     * closes table
         */



	public void rsource_end() {







        try {



		rec.close();



        } catch (Exception e1) {}







		rec=null;



	}







}