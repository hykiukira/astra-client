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


import javax.swing.*;


import java.awt.*;


/**
 * Creates two areas used to print the header and content of a JTable.
 */


public class RJTable


{


    RArea ColumnHeader = new RArea();


    RArea Columns = new RArea();


    JTable Table;


    String colName;


    double colWidth;


    double totalWidth = 0;


    double tableWidth;


    double tableY;


    double tableX;


    double tableHDRHeight;


    double tableLINHeight;


    RLineStyle HDRFrameStyle;


    RLineStyle LINFrameStyle;


    RLineStyle LINGridStyle;


    public Font headerFont = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);


    public Font detailFont = new java.awt.Font("Arial", java.awt.Font.PLAIN, 12);


    int ColumnCount;


    /**
     * Creates a RJTable. The paramaters mean:
     * <p/>
     * <p/>
     * T: Jtable to be printed
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * X: left margin.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * Y: top margin.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * Width: width of the table.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * HDRHeight: Height of the header area.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * LINHeight: Height of the detail area.
     */


    public RJTable(JTable T, double x, double y, double Width, double HDRHeight, double LINHeight) {


        Table = T;


        tableX = x;


        tableY = y;


        tableWidth = Width;


        tableHDRHeight = HDRHeight;


        tableLINHeight = LINHeight;


    }


    /**
     * style of the line used for the frame of the header.
     */


    public void setHDRFrameStyle(RLineStyle s) {


        HDRFrameStyle = s;


    }


    /**
     * style of the line used for the frame of the detail area.
     */


    public void setLINFrameStyle(RLineStyle s) {


        LINFrameStyle = s;


    }


    /**
     * style of the line used for the grid of the table.
     */


    public void setLINGridStyle(RLineStyle s) {


        LINGridStyle = s;


    }


    /**
     * Creates the header and detail areas.
     */


    public void createAreas() {


        double currentX;


        RField lbl = null;


        RField fi = null;


        RCheck chk;


        RPicture im;


        double currentWidth;


        Object o;


        Class c;

        // this will make that after a page break the header is printed again


        Columns.setHeaderArea(this.ColumnHeader);


        ColumnCount = Table.getColumnCount();


        int r = Table.getRowCount();


        double[] grid = new double[ColumnCount - 1];


        if (HDRFrameStyle != null) {


            ColumnHeader.setFrameStyle(HDRFrameStyle);


            ColumnHeader.setFrameType(RArea.FRAME_AREA);


        }


        if (LINFrameStyle != null) {


            Columns.setFrameStyle(LINFrameStyle);


            Columns.setFrameType(RArea.FRAME_PAGE);


            if (HDRFrameStyle != null) Columns.setFrameType(RArea.FRAME_PAGE_NO_TOP);


        }

        // calculate table width


        for (int i = 0; i < ColumnCount; i++) {


            totalWidth = totalWidth + Table.getColumnModel().getColumn(i).getWidth();


        }


        currentX = 0;


        for (int i = 0; i < ColumnCount; i++) {


            o = "";


            if (r > 0) o = Table.getValueAt(0, i);


            currentWidth = (Table.getColumnModel().getColumn(i).getWidth() / totalWidth) * tableWidth;


            if (i < (ColumnCount - 1)) grid[i] = (currentX + currentWidth);

            // Create labels for header


            lbl = new RField();


            lbl.setdefaultValue(Table.getColumnName(i));


            lbl.FontType = headerFont;


            lbl.setConstant(true);


            lbl.x = currentX;


            lbl.y = 0;


            lbl.width = currentWidth;


            lbl.height = tableHDRHeight;


            lbl.name = "lbl_" + Table.getColumnName(i);


            ColumnHeader.add(lbl);

            // create image object


            if (o instanceof Boolean) {


                chk = new RCheck();


                chk.name = Table.getColumnName(i);


                chk.x = currentX;


                chk.y = 0;


                chk.width = currentWidth;


                chk.height = tableLINHeight;


                chk.setConstant(false);


                Columns.add(chk);


            }

            // create image object


            if (o instanceof Image) {


                im = new RPicture();


                im.setConstant(false);


                im.name = Table.getColumnName(i);


                im.x = currentX;


                im.y = 0;


                im.width = currentWidth;


                im.height = tableLINHeight;


                Columns.add(im);


            }

            // other, as string


            if (!((o instanceof Image) || (o instanceof Boolean))) {


                fi = new RField();


                fi.name = Table.getColumnName(i);


                fi.FontType = detailFont;


                fi.setConstant(false);


                fi.x = currentX;


                fi.y = 0;


                fi.width = currentWidth;


                fi.height = tableLINHeight;


                Columns.add(fi);


            }


            currentX = currentX + currentWidth;


        }


        if (LINGridStyle != null) {


            Columns.setGridStyle(LINGridStyle);


            Columns.setGrid(grid);


            Columns.setHorizontalGrid(true);


        }


        ColumnHeader.marginLeft = tableX;


        ColumnHeader.marginTop = tableY;


        ColumnHeader.width = tableWidth;


        ColumnHeader.height = tableHDRHeight;


        Columns.marginLeft = tableX;


        Columns.width = tableWidth;


        Columns.height = tableLINHeight;


        Columns.setDataSource(new RJTableSource(Table.getModel()));


    }


    /**
         * Returns the created header area for the JTable.
         */


	public RArea getHeaderArea() {return ColumnHeader;}





        /**
         * Returns the created detail area for the JTable.
         */


	public RArea getDetailArea() {return Columns;}








}


