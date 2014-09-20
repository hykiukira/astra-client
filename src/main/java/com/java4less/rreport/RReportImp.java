package com.java4less.rreport;


import java.awt.*;


/**
 * RReportImp is a subclass RReport that can read definition files (*.rep) . It is the class you must use if you want to import a report created with RReport Visual Designer.
 */


public class RReportImp extends RReport


{


    /**
     * Owner: must be a visible Frame or NULL
     */


    public RReportImp(Frame Owner) {


        super(Owner);


    }


    /**
     * constructor:
     * <p/>
     * <p/>
     * Owner: must be a visible Frame or NULL
     * <p/>
     * <p/>
     * reso: resolution used for the preview
     */


    public RReportImp(Frame Owner, int reso) {


        super(Owner, reso);


    }


    private boolean d2() {


        return false;


    }


    /**
     * read a *.rep file from a file
     */


    public boolean importReport(String f) {


        try {


            return importReport(new java.io.InputStreamReader(new java.io.FileInputStream(f), System.getProperty("file.encoding")));


        } catch (Exception e) {


            System.out.println("Error importing file: " + e.getMessage());


            return false;


        }


    }


    /**
     * read a *.rep file from a URL. If you want to read a rep file inside a jar file you can use:<BR>
     * <p/>
     * <p/>
     * <BR>
     * <p/>
     * <p/>
     * rep.importReport(ClassLoader.getSystemResource("file.rep"));
     */


    public boolean importReport(java.net.URL f) {


        if (f == null) return false;


        try {


            return importReport(new java.io.InputStreamReader(f.openStream()));


        } catch (Exception e) {


            System.out.println("Error importing file: " + e.getMessage());


            return false;


        }


    }


    /**
     * read a *.rep file
     */


    public boolean importReport(java.io.InputStreamReader ir) {


        String l = "";


        String k = "";


        String v = "";


        RArea area = null;


        RObject obj = null;


        if (super.d() != this.d2()) return false;


        try {


            java.io.BufferedReader i = new java.io.BufferedReader(ir);


            while (l != null) {


                l = i.readLine();


                k = l;


                if (k.length() > 8) k = k.substring(0, 8).trim();


                if (l.length() > 8) v = l.substring(9, l.length()).trim();


                this.importLine(k, v);


                if ((k.compareTo("DBSOURCE") == 0) && (v.compareTo("1") == 0)) {

                    // connect to DB


                    this.openDB();


                }

                // end of report general data


                if (l.indexOf("---") == 0) break;


            }


            boolean inArea = false;


            boolean inObject = false;


            l = i.readLine();


            while (l != null) {


                k = l;


                if (k.length() > 8) k = k.substring(0, 8).trim();


                if (l.length() > 8) v = l.substring(9, l.length()).trim();


                if (k.compareTo("AREA") == 0) {

                    // new area


                    area = new RArea();


                    boolean hdr_footer = false;


                    if (v.indexOf("HEADER") >= 0) {


                        if (v.indexOf("VISIBLE") >= 0) this.setReportHeader(area);


                        hdr_footer = true;


                    }


                    if (v.indexOf("HPAGE") >= 0) {


                        if (v.indexOf("VISIBLE") >= 0) this.setPageHeader(area);


                        hdr_footer = true;


                    }


                    if (v.indexOf("FOOTER") >= 0) {


                        if (v.indexOf("VISIBLE") >= 0) this.setReportFooter(area);


                        hdr_footer = true;


                    }


                    if (v.indexOf("FPAGE") >= 0) {


                        if (v.indexOf("VISIBLE") >= 0) this.setPageFooter(area);


                        else this.setPageFooter(null);


                        hdr_footer = true;


                    }


                    if (v.indexOf("BACK") >= 0) {


                        if (v.indexOf("VISIBLE") >= 0) this.setPageBackgroundArea(area);


                        hdr_footer = true;


                    }


                    if (!hdr_footer) this.addArea(area);


                    inArea = true;


                }


                if (k.compareTo("ROBJECT") == 0) {


                    obj = (RObject) Class.forName(v).newInstance();


                    inObject = true;


                    inArea = false;


                }


                if (l.compareTo("--- END AREA ---") == 0) inArea = false;


                if (l.compareTo("--- END OBJECT ---") == 0) {

                    // add object to area


                    if (obj != null) {


                        if (obj instanceof RPageBreak) area.add(obj);


                        else area.add(obj, false);


                    }


                    inObject = false;


                }


                if (inArea) {


                    area.importLine(k, v);


                    if ((k.compareTo("TABLE") == 0) && (v.length() > 0) && (this.getDBCon() != null)) {

                        // create JDBCSource object


                        try {


                            area.setDataSource(new RJDBCSource(this.getDBConForSelect().createStatement(), v));


                        } catch (Exception e) {


                            System.out.println("Statement Error: " + e.getMessage());


                        }


                    }


                    if ((k.compareTo("LINKAREA") == 0) && (v.length() > 0)) {

                        // find linked are


                        RArea larea = this.getAreaByName(v);

                        // is the linked area the report Header?


                        if ((larea == null) && (this.reportHeader != null))


                            if (reportHeader.getName().compareTo(v) == 0) larea = reportHeader;


                        if (larea != null) {


                            if (area.getDataSource() != null)


                                area.getDataSource().setLinkSource(larea.getDataSource());


                            area.setLinkedArea(larea);


                        }


                    }


                    if ((k.compareTo("RSOURCEN") == 0) && (v.length() > 0)) {

                        /**


                         * set user defined RSource class


                         */


                        try {


                            Object source = Class.forName(v).newInstance();


                            if (source instanceof RSource) area.setDataSource((RSource) source);


                        } catch (Exception e) {


                            System.out.println("Error loading user defined RSource class: " + e.getMessage());


                        }


                    }


                    if ((k.compareTo("HDRAREA") == 0) && (v.length() > 0)) {

                        // find linked are


                        RArea harea = this.getAreaByName(v);


                        if ((harea != null) && (harea != this.reportHeader)) area.setHeaderArea(harea);


                    }

                    // find group header


                    if ((k.compareTo("GROUPHDR") == 0) && (v.length() > 0)) {


                        RArea harea = this.getAreaByName(v);


                        if ((harea != null) && (harea != this.reportHeader)) area.setGroupHeaderArea(harea);


                    }

                    // remember group footer, the area is not loaded yet


                    if ((k.compareTo("GROUPFOO") == 0) && (v.length() > 0)) area.groupFooterAreaName = v;

                    // this is the group footer, find detail area


                    if ((k.compareTo("GROUPF") == 0) && (v.compareTo("1") == 0)) {


                        RArea dArea = null;


                        for (int j = 0; j < areas.size(); j++) {


                            RAreaProp a = (RAreaProp) areas.elementAt(j);


                            if (a.area.groupFooterAreaName.compareTo(area.getName()) == 0) {


                                dArea = a.area;


                                break;


                            }


                        }

                        // detail area found


                        if (dArea != null) dArea.setGroupFooterArea(area);


                    }


                    if ((k.compareTo("GROUPBY") == 0) && (v.length() > 0)) {

                        // list of fields for link


                        area.setGroupByFields(RObject.convertList(v));


                    }


                    if ((k.compareTo("LINKFROM") == 0) && (v.length() > 0) && (area.getDataSource() != null)) {

                        // list of fields for link


                        (area.getDataSource()).setFromFields(RObject.convertList(v));


                    }


                    if ((k.compareTo("LINKTO") == 0) && (v.length() > 0) && (area.getDataSource() != null)) {

                        // list of fields for link


                        (area.getDataSource()).setToFields(RObject.convertList(v));


                    }


                }


                if ((inObject) && (obj != null)) {


                    obj.importLine(k, v);

                    // replace RUserObject with the class requested by the user


                    if ((k.compareTo("CLASS") == 0) && (obj instanceof RUserObject)) {


                        obj = ((RUserObject) obj).getUserObject();


                    }


                }


                l = i.readLine();


            }


            i.close();


        } catch (Exception e) {


            System.out.println("Error importing file: " + e.getMessage());


            return false;


	  }





	return true;





  }





}


