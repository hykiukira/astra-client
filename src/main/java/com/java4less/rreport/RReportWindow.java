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


import java.awt.event.*;


import java.awt.Dialog;


import java.util.*;


import java.io.BufferedWriter;


import java.io.FileWriter;


import javax.swing.*;


/**
 * AWT preview window. See exampleOrder.java for an example of usage of a preview window.
 */


public class RReportWindow extends Dialog implements ActionListener, WindowListener, ComponentListener


{


    Panel StatusBar = new Panel(null);


    Panel ToolBar = new Panel(null);


    java.awt.Label txtPage = new Label();


    RButton btnOnePage = new RButton("onep.gif", "");


    RButton btnTwoPages = new RButton("twop.gif", "");

    //RButton btnFourPages= new RButton("fourp.gif","");


    RButton btnPrint = new RButton("print.gif", "");


    RButton btnHTML = new RButton("html.gif", "");


    RButton btnUp = new RButton("right.gif", "");


    RButton btnDown = new RButton("left.gif", "");


    RButton btnExit = new RButton("exit.gif", "");


    RButton btnNext = new RButton("right.gif", "");


    RButton btnPrevious = new RButton("left.gif", "");


    Label txtZoom = new Label();


    TextField txtPagesToPrint = new TextField();


    RButton btnZoomUp = new RButton("up.gif", "");


    RButton btnZoomDown = new RButton("down.gif", "");


    RReport rep;


    private java.awt.Frame DialogOwner = null;


    boolean bActivated = false;


    AstraPrint astraprint;


    public RReportWindow(RReport Rep, java.awt.Frame Owner) {


        super(Owner, Rep.getTitle(), true);


        this.setSize(this.getToolkit().getScreenSize());


        DialogOwner = Owner;


        DialogOwner = (java.awt.Frame) Owner;


        rep = Rep;


        rep.setPreview(true);


        this.setLayout(new java.awt.BorderLayout());


        StatusBar.setSize(new Dimension(100, 25));


        ToolBar.setSize(new Dimension(100, 35));


        StatusBar.setBackground(java.awt.Color.lightGray);


        ToolBar.setBackground(java.awt.Color.lightGray);

        // print


        txtPagesToPrint.setBounds(5, 7, 76, 22);


        btnPrint.setBounds(85, 2, 30, 30);


        btnPrint.setActionCommand("PRINT");


        btnPrint.addActionListener(this);

        // Zoom buttons


        txtZoom.setBackground(java.awt.Color.white);


        txtZoom.setBounds(120, 9, 40, 18);


        btnZoomDown.setBounds(167, 17, 16, 16);


        btnZoomDown.setActionCommand("ZOOMDOWN");


        btnZoomDown.addActionListener(this);


        btnZoomUp.setBounds(167, 1, 16, 16);


        btnZoomUp.setActionCommand("ZOOMUP");


        btnZoomUp.addActionListener(this);

        // one/several pages


        btnOnePage.setBounds(187, 2, 30, 30);


        btnOnePage.setActionCommand("ONEPAGE");


        btnOnePage.addActionListener(this);


        btnTwoPages.setBounds(221, 2, 30, 30);


        btnTwoPages.setActionCommand("TWOPAGES");


        btnTwoPages.addActionListener(this);

        //btnFourPages.setBounds(255,2,30,30);

        //btnFourPages.setActionCommand("FOURPAGES");

        //btnFourPages.addActionListener(this);

        // HTML


        if (rep.getHTMLActive()) {


            btnHTML.setBounds(255, 2, 30, 30);


            btnHTML.setActionCommand("HTML");


            btnHTML.addActionListener(this);

            // exit


            btnExit.setBounds(389, 2, 30, 30);


        } else btnExit.setBounds(255, 2, 30, 30);


        btnExit.setActionCommand("EXIT");


        btnExit.addActionListener(this);

        //page navigators


        txtPage.setBounds(5, 4, 50, 18);


        txtPage.setBackground(java.awt.Color.white);


        btnDown.setBounds(57, 4, 18, 18);


        btnDown.setActionCommand("DOWN");


        btnDown.addActionListener(this);


        btnUp.setBounds(75, 4, 18, 18);


        btnUp.setActionCommand("UP");


        btnUp.addActionListener(this);


        btnNext.setBounds(323, 2, 30, 30);


        btnNext.setActionCommand("NEXT");


        btnNext.addActionListener(this);


        btnPrevious.setBounds(289, 2, 30, 30);


        btnPrevious.setActionCommand("PREV");


        btnPrevious.addActionListener(this);


        ToolBar.add(txtPagesToPrint);


        ToolBar.add(btnPrint);


        ToolBar.add(txtZoom);


        ToolBar.add(btnZoomUp);


        ToolBar.add(btnZoomDown);


        ToolBar.add(btnOnePage);


        ToolBar.add(btnTwoPages);

        //omToolBar.add(btnFourPages);


        ToolBar.add(btnHTML);


        ToolBar.add(btnExit);


        ToolBar.add(btnPrevious);


        ToolBar.add(btnNext);


        StatusBar.add(txtPage);


        if (getAstraprint() != null) {


            StatusBar.add(btnDown);


            StatusBar.add(btnUp);


        }


        updateZoomText();


        this.add("Center", Rep);


        this.add("North", ToolBar);


        this.add("South", StatusBar);


    }


    private void updatePageNumber() {


        txtPage.setText(rep.getPageNumber() + "/" + rep.getMaxPages());


    }


    /**
     * shows preview window.
     */


    public void show() {


        this.addWindowListener(this);


        refreshPageNumber();


        super.show();


    }


    private void refreshPageNumber() {


        txtPagesToPrint.setText("1-" + rep.getMaxPages());


        updatePageNumber();


    }


    /**
     * Action listener for buttons
     */


    public void actionPerformed(ActionEvent a) {


        String action = a.getActionCommand();


        if (action.compareTo("UP") == 0) {


            if (rep.getPageNumber() < rep.getMaxPages()) {


                rep.setPageNumber(rep.getPageNumber() + 1);


                updatePageNumber();


            }


        }


        if (action.compareTo("DOWN") == 0) {


            if (rep.getPageNumber() > 1) {


                rep.setPageNumber(rep.getPageNumber() - 1);


                updatePageNumber();


            }


        }


        if (action.compareTo("ONEPAGE") == 0) {


            rep.setShowPages(1);


            updateZoomText();


        }


        if (action.compareTo("TWOPAGES") == 0) {


            rep.setShowPages(2);


            updateZoomText();


        }


        if (action.compareTo("NEXT") == 0) {

            if (astraprint != null)

                astraprint.next();


        }


        if (action.compareTo("PREV") == 0) {

            if (astraprint != null)

                astraprint.previous();


        }


        if (action.compareTo("FOURPAGES") == 0) {


            rep.setShowPages(4);


            updateZoomText();


        }


        if (action.compareTo("ZOOMUP") == 0) {


            increaseZoom();


        }


        if (action.compareTo("ZOOMDOWN") == 0) {


            decreaseZoom();


        }


        if (action.compareTo("HTML") == 0) {


            String s = rep.getHTML();


            FileDialog FD = new FileDialog(DialogOwner);


            FD.setFile("Report.htm");


            FD.show();


            if (FD.getFile() != null) {


                try {

                    // write only a space


                    BufferedWriter out1 = new BufferedWriter(new FileWriter(FD.getDirectory() + FD.getFile(), true));


                    out1.write(" ", 0, 1);


                    out1.close();


                    java.io.OutputStreamWriter out2 = new java.io.OutputStreamWriter(new java.io.FileOutputStream(FD.getDirectory() + FD.getFile()), System.getProperty("file.encoding"));


                    out2.write(s, 0, s.length());


                    out2.close();


                } catch (Exception e) {
                }


            }


        }


        if (action.compareTo("EXIT") == 0) {
            this.setVisible(false);
            this.dispose();
        }


        if (action.compareTo("PRINT") == 0) {


            printPreview();


        }


    }


    private void printPreview() {

        // build list of pages to print


        int[] Pages = new int[rep.getMaxPages()];


        int PagesCount = 0;


        int coma = 0;


        int hyphen = 0;


        int Page1;


        int Page2;


        String strPage1;


        String strPage2;


        String item;


        String list = txtPagesToPrint.getText();

        // Parse txtPagesToPrintField

        // look for a ,


        list = txtPagesToPrint.getText();


        while (list.compareTo("") != 0) {


            coma = list.indexOf(",");


            if (coma >= 0) {


                item = list.substring(0, coma);


                list = list.substring(coma + 1, list.length());


            } else {
                item = list;
                list = "";
            }

            // look for a -


            hyphen = item.indexOf("-");


            if (hyphen >= 0) {


                strPage1 = item.substring(0, hyphen).trim();


                strPage2 = item.substring(hyphen + 1, item.length()).trim();


            } else


            {


                strPage1 = item.trim();


                strPage2 = item.trim();


            }

            // convert to int


            try {


                Page1 = new Integer(strPage1).intValue();


            } catch (Exception e1) {
                Page1 = 0;
            }


            try {


                Page2 = new Integer(strPage2).intValue();


            } catch (Exception e1) {
                Page2 = 0;
            }


            if ((Page2 >= Page1) && (Page1 > 0))


                for (int i = Page1; (i <= Page2) && (i <= rep.getMaxPages()); i++) {
                    Pages[PagesCount] = i;
                    PagesCount++;
                }


        }


        if (PagesCount > 0) rep.printPreview(Pages, PagesCount);


    }


    private void updateZoomText() {


        if (rep.getShowPages() == 1)


            txtZoom.setText((int) (rep.getScale() * 100) + " %");


        else


            txtZoom.setText((int) (rep.getScalePages() * 100) + " %");


    }


    private void increaseZoom() {


        if (rep.getScale() < 2) {


            rep.setScale(rep.getScale() + 0.25);


            updateZoomText();


        }


    }


    private void decreaseZoom() {


        if (rep.getScale() > 0.5) {


            rep.setScale(rep.getScale() - 0.25);


            updateZoomText();


        }


    }


    /**
     * empty
     */


    public void componentShown(ComponentEvent e) {
    }


    /**
     * empty
     */


    public void componentHidden(ComponentEvent e) {
    }


    /**
     * empty
     */


    public void componentResized(ComponentEvent e) {
    }


    /**
     * empty
     */


    public void componentMoved(ComponentEvent e) {
    }


    /**
     * empty
     */


    public void windowClosed(WindowEvent e) {


    }


    /**
     * close window
     */


    public void windowClosing(WindowEvent e) {


        this.setVisible(false);
        this.dispose();


    }


    /**
     * empty
     */


    public void windowOpened(WindowEvent e) {
    }


    /**
     * empty
     */


    public void windowOpenning(WindowEvent e) {
    }


    /**
     * empty
     */


    public void windowIconified(WindowEvent e) {
    }


    /**
     * empty
     */


    public void windowDeiconified(WindowEvent e) {
    }


    /**
     * empty
     */


    public void windowDeactivated(WindowEvent e) {
    }


    /**
     * empty
     */


    public void windowActivated(WindowEvent e) {
    }


    public void processWindowEvent(WindowEvent e) {


        super.processWindowEvent(e);


        if ((e.getID() == e.WINDOW_ACTIVATED) && (!bActivated)) {


            rep.setShowPages(1);


            updateZoomText();


            bActivated = true;


        }


    }


    /**
     * empty
     */


    public void processComponentEvent(ComponentEvent e) {


    }


    /**
     * Getter for property astraprint.
     *
     * @return Value of property astraprint.
     */


    public com.java4less.rreport.AstraPrint getAstraprint() {


        return astraprint;


    }


    /**
     * Setter for property astraprint.
     *
     * @param astraprint New value of property astraprint.
     */


    public void setAstraprint(com.java4less.rreport.AstraPrint astraprint) {


        this.astraprint = astraprint;


    }


}



