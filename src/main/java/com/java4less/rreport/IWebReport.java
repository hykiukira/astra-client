package com.java4less.rreport;

//  RReport

//  Copyright (C)

//

//  RReport@Confluencia.net

//  All rights reserved

//

// Adquisition , use and distribution of this code is subject to restriction:

//  - You may modify the source code in order to adapt it to your needs.

//  - Redistribution of this ( or a modifed version) source code is prohibited. You may only redistribute compiled versions.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material

//


import javax.servlet.*;

import javax.servlet.http.*;


/**
 * Classes loaded by RReportServlet must implement this interface.<BR>
 * <p/>
 * This interface receives the http request parameters as input and creates a report as output.<BR>
 * <p/>
 * <BR>
 * <p/>
 * See WebReportJDBC for a implementation of the interface.
 */

public interface IWebReport {


    /**
     * gets report to be run. The class can use the http request parameters to creaate the correct report.
     */

    public RReport createReport(HttpServletRequest request);


    /**
     * after getting the report, the servlet will set the PDF or HTML output and will call this method.<BR>
     * <p/>
     * This method should run the report by calling prepare() , printAreas() (if needed) and endReport(),
     */

    public boolean runReport();


}