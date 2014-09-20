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


/**
 * Data source for an area.
 * <p/>
 * <p/>
 * Areas are programatically printed by:
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * 1. Assigning a value to the elements in the area. The following command assigns the value "Article1" to the element "TextDescription".<BR> <BR>
 * <p/>
 * <p/>
 * DETAIL_PurchaseOrder.getItemByName("TextDescription").setruntimeValue("Article1");<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * and Calling the report's printArea() method: <BR><BR>
 * <p/>
 * <p/>
 * rep.printArea(DETAIL_PurchaseOrder);<BR><BR>
 * <p/>
 * <p/>
 * 2. or if they are linked to a superarea, after the superarea is printed.<BR><BR>
 * <p/>
 * <p/>
 * However you can also use a Rsource object. In this case the report will automatically assing the values to the fields and print the area as many times as need. There are 3 implemented Rsource classes, but you can implement your own class.<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * The following example uses the class RArraySource to print the lines of a Purchase Order:<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * private static void Example2() {<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * // example: array as data source<BR>
 * <p/>
 * <p/>
 * Win= new RReportWindow(rep,MainWindow);<BR>
 * <p/>
 * <p/>
 * DETAIL_PurchaseOrder.setDataSource(new RArraySource(columnsNames,columnsData));<BR>
 * <p/>
 * <p/>
 * rep.prepare();<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * //print here<BR>
 * <p/>
 * <p/>
 * rep.printArea(DETAIL_PurchaseOrder);<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * rep.endReport();<BR>
 * <p/>
 * <p/>
 * Win.show();<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * }<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * The array columnsNames contains the names of the fields and the array columnsData contanins the values. See Examples or exampleOrder.java.<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * Note: you can use a RAreaListener in order to execute your own java code before each repetition of the area is printed.<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * Note: objects defined as constant (see RObject.setConstant()) will not be modified by the data source (RSource).<BR>
 */


public interface RSource


{


    /**
     * used to retrieve an element's value.
     */


    public Object rsource_getData(String FieldName);


    /**
     * used to require a new data record. Must return false if there are no more records to be printed
     */


    public boolean rsource_nextRecord();


    /**
     * called before printing the first record. Can be used, for example to connect to a database. Must return true if the RSource is ready.
     */


    public boolean rsource_start();


    /**
     * called after printing all records. Can be used for example to close a connection to a database
     */


    public void rsource_end();


    /**
     * linked RSource (if the RSource supports nested RSources).
     */


    public RSource getLinkSource();


    /**
     * linked RSource (if the RSource supports nested RSources).
     */


    public void setLinkSource(RSource l);


    /**
     * field names in the linked RSource used for the relationship (this is normally the primary key in a table or record).
     */


    public String[] getFromFields();


    /**
     * field names in this RSource used for the relationship (this is normally a foreign key).
     */


    public String[] getToFields();


    /**
     * field names in the linked RSource used for the relationship (this is normally the primary key).
     */


    public void setFromFields(String[] s);


    /**
     * field names in this RSource used for the relationship (this is normally a foreign key).
     */


    public void setToFields(String[] s);


    /**
     * sets a value for a parameter. Some RSource can accept parameters, for example the RJDBCSource can use parameters to create a database SQl query.
    */


    public void setParameter(String param,String value);





}


