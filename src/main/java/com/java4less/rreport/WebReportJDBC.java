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

//  - This notice disclaim all warranties of eall material


import javax.servlet.*;

import javax.servlet.http.*;


/**
 * implementation of the IWebRepot interface. This class is used to run reports that use JDBC/ODBC as data source.<BR>
 * <p/>
 * The input parameters are REPORTFILE (*.rep file) and any other parameter the RJDBCSource of the areas defined in the rep file might need.<BR>
 * <p/>
 * Parameters for the RJDBCSources of the report must start with "PAR_".
 */

public class WebReportJDBC implements IWebReport {

    // loaded report

    private RReportJ2 rep;

    public boolean debug = false;


    public WebReportJDBC() {

    }


    /**
     * create report loading the template file (*.rep). The request parameter "REPORTFILE" must contain the name of the report template file, as relative or absolute url.<BR>
     * <p/>
     * For example:<BR><BR>
     * <p/>
     * http://localhost:8080/rreport/servlet/RReportServlet?REPORTFILE=file://employee.rep&FORMAT=PDF
     */

    public RReport createReport(HttpServletRequest request) {


        rep = new RReportJ2(null);

        String reportFile = request.getParameter("REPORTFILE");

        if (reportFile == null) reportFile = "";


        if (reportFile.length() == 0) {

            System.out.println("Report file is missing");

            return null;

        }


        if (debug) System.out.println("Report file is " + reportFile);


        if (reportFile.startsWith("file://")) {

            // load as file

            if (!rep.importReport(reportFile.substring(7, reportFile.length()))) return null;

        } else {

            // load as resource

            java.net.URL fileURL = ClassLoader.getSystemResource(reportFile);

            if ((debug) && (fileURL == null))

                System.out.println("File could not be loaded as resource");


            if (fileURL == null) return null;


            if (debug) System.out.println("File URL is " + fileURL.toString());


            if (!rep.importReport(fileURL)) return null;

        }

        // prepare report for exporting

        rep.disablePrinting(true);

        rep.showProgressWindow = false;

        rep.showPageDialog = false;

        rep.showPrintDialog = false;

        // set JDBC parameters

        // get all areas from report and set parameters of the RJDBC Source

        setParamArea(rep.getReportHeader(), request);

        setParamArea(rep.getReportFooter(), request);

        setParamArea(rep.getPageFooter(), request);

        setParamArea(rep.getPageHeader(), request);

        // for all detail areas, set JDBC parameters

        for (int i = 0; i < rep.getAreaCount(); i++) setParamArea((RArea) rep.getAreaAt(i), request);


        return rep;

    }


    /**
     * run report, call prepare() and endReport().<BR>
     * <p/>
     * Note: all detail areas must be linked to the report header directly or indirectly since the prepare() method prints only the header and all linked areas.
     */

    public boolean runReport() {


        rep.prepare();

        rep.endReport();


        return true;

    }


    /**
     * set parameters for the areas
     */

    private void setParamArea(RArea area, HttpServletRequest request) {

        // check if this area has an associate table

        if (area == null) return;

        if (area.getDataSource() == null) return;

        //if (!(area.getDataSource() instanceof RSource )) return;


        if (debug) System.out.println("Setting parameters for " + area.getName());


        RSource source = (RSource) area.getDataSource();

        // activate use of paramaters

        if (source instanceof RJDBCSource) ((RJDBCSource) source).allowParameters = true;


        java.util.Enumeration ps = request.getParameterNames();

        while (ps.hasMoreElements()) {

            String name = (String) ps.nextElement();

            // we have a JDBC parameter

            if (name.toUpperCase().startsWith("PAR_")) {

                source.setParameter(name.substring(4, name.length()), request.getParameter(name));

                if (debug)
                    System.out.println("Parameter " + name.substring(4, name.length()) + "=" + request.getParameter(name));

            }


        }


    }


}