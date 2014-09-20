package com.java4less.rreport;


import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

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
 * progress window displayed when a report is created.
 */

class progressWindow extends JDialog implements ActionListener {

    JLabel lblProgress = new JLabel();

    JProgressBar progressBar = new JProgressBar();

    JButton cmdCancel = new JButton();

    int maxPages = 0;

    boolean cancelled = false;

    String tmpText = "";


    String prepareTextTemplate = "Preparing pages ...";

    String printingTextTemplate = "Printing pages ...";


    public progressWindow() {

        try {

            jbInit();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void jbInit() throws Exception {

        lblProgress.setBorder(BorderFactory.createEtchedBorder());

        lblProgress.setText("Printing ...");

        lblProgress.setBounds(new Rectangle(7, 7, 203, 20));

        this.getContentPane().setLayout(null);

        progressBar.setBounds(new Rectangle(7, 31, 203, 22));

        cmdCancel.setBorder(BorderFactory.createEtchedBorder());

        cmdCancel.setActionCommand("CANCEL");

        cmdCancel.setText("Cancel");

        cmdCancel.setBounds(new Rectangle(70, 59, 79, 27));

        this.setResizable(false);

        this.getContentPane().add(progressBar, null);

        this.getContentPane().add(cmdCancel, null);

        this.getContentPane().add(lblProgress, null);

        this.setTitle("Progress...");


        cmdCancel.addActionListener(this);


        this.setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize().width - 220) / 2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height - 120) / 2), 220, 120);

    }


    public void setVisible(boolean b) {

        if (b) cancelled = false;

        super.setVisible(b);

    }


    /**
     * set number of pages
     */

    public void setMaxPages(int pages) {


        maxPages = pages;


        if (maxPages <= 0) maxPages = 20;


        progressBar.setMaximum(maxPages);

    }


    /**
     * display text for page p
     */

    public void setCurrentPage(int p, boolean printing) {


        try {


            String t = prepareTextTemplate;

            if (printing) t = printingTextTemplate;


            this.lblProgress.setText(t);


            progressBar.setStringPainted(true);

            if (printing) progressBar.setString("" + p + "/" + maxPages);

            else progressBar.setString("" + p);


            progressBar.setValue(p % (maxPages + 1));


            if (this.getGraphics() != null) this.paint(this.getGraphics());


        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }


    }


    /**
     * modify default text while preparing preview
     */

    public void setPrepareTextTemplate(String t) {

        prepareTextTemplate = t;

    }


    /**
     * modify default text while preparing preview
     */

    public void setPrintTextTemplate(String t) {

        printingTextTemplate = t;

    }


    /**
     * activate Cancel button?
     */

    public void allowCancel(boolean b) {

        this.cmdCancel.setEnabled(b);

    }


    /**
     * cancel button pressed?
     */

    public boolean isCancelled() {
        return cancelled;
    }


    public void actionPerformed(ActionEvent a) {


        String ac = a.getActionCommand();


        if (ac.compareTo("CANCEL") == 0) {

            cancelled = true;

            this.setVisible(false);

        }


    }


}

