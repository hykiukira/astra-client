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

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.Mailing.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.sys.rmi.*;

/**
 * Swing preview window. See exampleOrder.java for an example of usage of a preview window.
 */

public class RReportJWindow extends JDialog implements ActionListener, WindowListener, ComponentListener, srcastra.astra.gui.modules.Mailing.MailInterface {


    boolean bActivated = false;


    JPanel root = new JPanel();

    JPanel StatusBar = new JPanel();

    //JPanel rep = new JPanel();

    BorderLayout borderLayout2 = new BorderLayout();

    BorderLayout borderLayout3 = new BorderLayout();

    JButton btnMail = new JButton();

    JButton btnDown = new JButton();

    JButton btnUp = new JButton();

    JTextField txtPage = new JTextField();

    JPanel ToolBar = new JPanel();

    JButton btnOnePage = new JButton();

    JTextField txtZoom = new JTextField();

    JButton btnZoomUp = new JButton();

    JButton btnZoomDown = new JButton();

    JButton btnTwoPages = new JButton();

    JButton btnHTML = new JButton();

    JButton btnExit = new JButton();

    JButton btnPrint = new JButton();

    private java.awt.Frame DialogOwner = null;

    RReport rep;

    JButton btnFirst = new JButton();

    JButton btnLast = new JButton();

    java.awt.Frame Owner = null;

    String pathForMail;
    String mailEntite;
    String[] emailAdresse;
    Loginusers_T m_user;
    String path;
    String from;


    /**
     * creates a preview window for the report.
     */

    public RReportJWindow(RReport r, java.awt.Frame Owner, Loginusers_T m_user, String path, String from) {


        super(Owner);

        this.Owner = Owner;
        this.m_user = m_user;

        this.path = path;


        this.rep = r;

        this.from = from;

        // set icon

        //try {

        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/java4less/images/Preview.gif")));

        //}

        //catch(Exception e) {

        //  e.printStackTrace();

        //}


        DialogOwner = (java.awt.Frame) Owner;


        try {

            jbInit();

        }

        catch (Exception e) {

            e.printStackTrace();

        }


        userInit();


    }


    public void updateNavigationButtons() {


        btnFirst.setEnabled(true);

        btnLast.setEnabled(true);

        btnUp.setEnabled(true);

        btnDown.setEnabled(true);


        if (rep.getPageNumber() <= 1) {

            btnDown.setEnabled(false);

            btnFirst.setEnabled(false);

        }


        if (rep.getPageNumber() >= rep.getMaxPages()) {

            btnUp.setEnabled(false);

            btnLast.setEnabled(false);

        }


    }


    private void userInit() {

        //this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());

        //Dimension d=new Dimension(800,600);

        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();


        d.setSize(d.getWidth(), d.getHeight() - 100);


        this.setSize(d);


        rep.setPreview(true);


        btnFirst.addActionListener(this);

        btnLast.addActionListener(this);

        btnDown.addActionListener(this);

        btnUp.addActionListener(this);

        btnZoomDown.addActionListener(this);

        btnZoomUp.addActionListener(this);

        btnPrint.addActionListener(this);

        btnTwoPages.addActionListener(this);

        btnOnePage.addActionListener(this);

        btnHTML.addActionListener(this);

        btnExit.addActionListener(this);

        btnMail.addActionListener(this);

        /* btnFirst.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/first.gif")));

       btnLast.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/last.gif")));

       btnDown.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/left.gif")));

       btnUp.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/right.gif")));

       btnZoomDown.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/down.gif")));

       btnZoomUp.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/up.gif")));

       btnPrint.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/print.gif")));

       btnTwoPages.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/twop.gif")));

       btnOnePage.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/onep.gif")));

       btnHTML.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/html.gif")));

       btnExit.setIcon(new javax.swing.ImageIcon(ClassLoader.getSystemResource("com/java4less/images/exit.gif")));*/

        //  new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/button/nextToLastArrow.gif")

        System.out.println("" + getClass().getResource("/com/java4less/images/last.gif"));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/last.gif")));

        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/left.gif")));

        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/right.gif")));

        btnZoomDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/down.gif")));

        btnZoomUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/up.gif")));

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/print.gif")));

        btnTwoPages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/twop.gif")));

        btnOnePage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/onep.gif")));

        btnHTML.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/html.gif")));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/exit.gif")));

        btnMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java4less/images/pdf.gif")));

        this.setTitle(rep.getTitle());

    }


    private void jbInit() throws Exception {

        //rep.setLayout(borderLayout2);

        StatusBar.setLayout(null);

        root.setLayout(borderLayout3);

        StatusBar.setBorder(BorderFactory.createRaisedBevelBorder());

        StatusBar.setMinimumSize(new Dimension(30, 30));

        StatusBar.setPreferredSize(new Dimension(30, 30));

        btnDown.setBorder(BorderFactory.createEtchedBorder());

        btnDown.setMaximumSize(new Dimension(18, 18));

        btnDown.setMinimumSize(new Dimension(18, 18));

        btnDown.setPreferredSize(new Dimension(18, 18));

        btnDown.setActionCommand("DOWN");

        btnDown.setMnemonic('0');

        btnDown.setBounds(new Rectangle(73, 6, 19, 19));

        btnUp.setBounds(new Rectangle(93, 6, 19, 19));

        btnUp.setMnemonic('0');

        btnUp.setPreferredSize(new Dimension(18, 18));

        btnUp.setMinimumSize(new Dimension(18, 18));

        btnUp.setBorder(BorderFactory.createEtchedBorder());

        btnUp.setMaximumSize(new Dimension(18, 18));

        btnUp.setActionCommand("UP");

        txtPage.setBackground(Color.white);

        txtPage.setEnabled(false);

        txtPage.setBorder(BorderFactory.createEtchedBorder());

        txtPage.setMinimumSize(new Dimension(4, 18));

        txtPage.setText("1/1");

        txtPage.setBounds(new Rectangle(7, 5, 46, 20));

        ToolBar.setBorder(BorderFactory.createRaisedBevelBorder());

        ToolBar.setMinimumSize(new Dimension(30, 30));

        ToolBar.setPreferredSize(new Dimension(30, 38));

        ToolBar.setLayout(null);

        btnOnePage.setBorder(BorderFactory.createEtchedBorder());

        btnOnePage.setMaximumSize(new Dimension(30, 30));

        btnOnePage.setMinimumSize(new Dimension(30, 30));

        btnOnePage.setPreferredSize(new Dimension(30, 30));

        btnOnePage.setActionCommand("ONEPAGE");

        btnOnePage.setMnemonic('0');

        btnOnePage.setBounds(new Rectangle(111, 4, 30, 30));

        txtZoom.setBackground(Color.white);

        txtZoom.setEnabled(false);

        txtZoom.setBorder(BorderFactory.createEtchedBorder());

        txtZoom.setText("100%");

        txtZoom.setBounds(new Rectangle(44, 8, 39, 21));

        btnZoomUp.setBounds(new Rectangle(86, 3, 17, 15));

        btnZoomUp.setMnemonic('0');

        btnZoomUp.setActionCommand("ZOOMUP");

        btnZoomUp.setPreferredSize(new Dimension(16, 16));

        btnZoomUp.setMinimumSize(new Dimension(18, 18));

        btnZoomUp.setBorder(BorderFactory.createEtchedBorder());

        btnZoomUp.setMaximumSize(new Dimension(18, 18));

        btnZoomDown.setActionCommand("ZOOMDOWN");

        btnZoomDown.setBorder(BorderFactory.createEtchedBorder());

        btnZoomDown.setMaximumSize(new Dimension(18, 18));

        btnZoomDown.setMinimumSize(new Dimension(18, 18));

        btnZoomDown.setPreferredSize(new Dimension(16, 16));

        btnZoomDown.setMnemonic('0');

        btnZoomDown.setBounds(new Rectangle(86, 18, 17, 15));

        btnTwoPages.setBounds(new Rectangle(145, 4, 30, 30));

        btnTwoPages.setMnemonic('0');

        btnTwoPages.setPreferredSize(new Dimension(30, 30));

        btnTwoPages.setActionCommand("TWOPAGES");

        btnTwoPages.setMinimumSize(new Dimension(30, 30));

        btnTwoPages.setBorder(BorderFactory.createEtchedBorder());

        btnTwoPages.setMaximumSize(new Dimension(30, 30));

        btnHTML.setBounds(new Rectangle(179, 4, 30, 30));

        btnHTML.setMnemonic('0');

        btnHTML.setPreferredSize(new Dimension(30, 30));

        btnHTML.setActionCommand("HTML");

        btnHTML.setMinimumSize(new Dimension(30, 30));

        btnHTML.setBorder(BorderFactory.createEtchedBorder());

        btnHTML.setMaximumSize(new Dimension(30, 30));

        btnExit.setBounds(new Rectangle(213, 4, 30, 30));

        btnExit.setMnemonic('0');

        btnExit.setPreferredSize(new Dimension(30, 30));

        btnExit.setActionCommand("EXIT");

        btnExit.setMinimumSize(new Dimension(30, 30));

        btnExit.setBorder(BorderFactory.createEtchedBorder());

        btnExit.setMaximumSize(new Dimension(30, 30));

        btnMail.setBounds(new Rectangle(180, 4, 30, 30));

        btnMail.setMnemonic('0');

        btnMail.setPreferredSize(new Dimension(30, 30));

        btnMail.setActionCommand("MAIL");

        btnMail.setMinimumSize(new Dimension(30, 30));

        btnMail.setBorder(BorderFactory.createEtchedBorder());

        btnMail.setMaximumSize(new Dimension(30, 30));

        btnPrint.setBorder(BorderFactory.createEtchedBorder());

        btnPrint.setMaximumSize(new Dimension(30, 30));

        btnPrint.setMinimumSize(new Dimension(30, 30));

        btnPrint.setPreferredSize(new Dimension(30, 30));

        btnPrint.setActionCommand("PRINT");

        btnPrint.setMnemonic('0');

        btnPrint.setBounds(new Rectangle(7, 3, 30, 30));

        //this.setResizable(false);

        //this.setModal(true);

        btnFirst.setBounds(new Rectangle(53, 6, 19, 19));

        btnFirst.setMnemonic('0');

        btnFirst.setActionCommand("FIRST");

        btnFirst.setPreferredSize(new Dimension(18, 18));

        btnFirst.setMinimumSize(new Dimension(18, 18));

        btnFirst.setMaximumSize(new Dimension(18, 18));

        btnFirst.setBorder(BorderFactory.createEtchedBorder());

        btnLast.setBounds(new Rectangle(113, 6, 19, 19));

        btnLast.setMnemonic('0');

        btnLast.setActionCommand("LAST");

        btnLast.setPreferredSize(new Dimension(18, 18));

        btnLast.setMinimumSize(new Dimension(18, 18));

        btnLast.setMaximumSize(new Dimension(18, 18));

        btnLast.setBorder(BorderFactory.createEtchedBorder());

        root.add(rep, BorderLayout.CENTER);

        root.add(ToolBar, BorderLayout.NORTH);


        ToolBar.add(btnPrint, null);

        ToolBar.add(txtZoom, null);

        ToolBar.add(btnZoomUp, null);

        ToolBar.add(btnZoomDown, null);

        ToolBar.add(btnOnePage, null);

        ToolBar.add(btnTwoPages, null);

        if (rep.getHTMLActive()) ToolBar.add(btnHTML, null);


        ToolBar.add(btnExit, null);

        ToolBar.add(btnMail, null);


        root.add(StatusBar, BorderLayout.SOUTH);

        StatusBar.add(txtPage, null);

        StatusBar.add(btnFirst, null);

        StatusBar.add(btnLast, null);

        StatusBar.add(btnDown, null);

        StatusBar.add(btnUp, null);


        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(root, BorderLayout.CENTER);

    }


    private void updatePageNumber() {


        txtPage.setText(rep.getPageNumber() + "/" + rep.getMaxPages());


    }


    /**
     * shows preview window. Note that this is not a modal window.
     */


    public void show() {


        this.addWindowListener(this);

        refreshPageNumber();

        super.show();

    }


    private void refreshPageNumber() {

        updatePageNumber();

        updateNavigationButtons();

    }


    /**
     * Action listener for buttons
     */

    public void actionPerformed(ActionEvent a) {

        String action = a.getActionCommand();


        if (action.compareTo("UP") == 0) {

            if (rep.getPageNumber() < rep.getMaxPages()) {

                rep.setPageNumber(rep.getPageNumber() + 1);

                refreshPageNumber();

            }


        }

        if (action.compareTo("DOWN") == 0) {

            if (rep.getPageNumber() > 1) {

                rep.setPageNumber(rep.getPageNumber() - 1);

                refreshPageNumber();

            }


        }


        if (action.compareTo("FIRST") == 0) {


            rep.setPageNumber(1);

            refreshPageNumber();


        }

        if (action.compareTo("LAST") == 0) {

            rep.setPageNumber(rep.getMaxPages());

            refreshPageNumber();


        }


        if (action.compareTo("ONEPAGE") == 0) {

            rep.setShowPages(1);

            updateZoomText();

        }


        if (action.compareTo("TWOPAGES") == 0) {

            rep.setShowPages(2);

            updateZoomText();

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

        if (action.compareTo("MAIL") == 0) {
            //astrainterface m_serveur;

            //m_serveur.renvEntiteRmiObject(m_user.getUrcleunik()).getList(m_user.getUrcleunik(), m_user.getUreecleunik());

            this.mailEntite = from;
            emailAdresse = new String[]{"null"};


            MaiLing m = new MaiLing(Owner, true, this, srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig(), path);


            m.show();


        }

        //   try{

        // ArrayList data=m_serveur.renvEntiteRmiObject(m_user.getUrcleunik()).getList(m_user.getUrcleunik(), m_user.getUreecleunik());

        // Entite e=(Entite)data.get(0);

        //   mailEntite=e.getEemail();

        // srcastra.astra.gui.modules.Mailing.MaiLing();

        //new srcastra.astra.gui.modules.Mailing.MaiLing().show();
        //new srcastra.astra.gui.modules.Mailing.MaiLing(this.fr,true,this,srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig(),"").show();

        // }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

        //  ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        //  }

        //   catch (java.rmi.RemoteException re) {

        //  ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        // }

        // }


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

        int PagesCount = rep.getMaxPages();


        for (int i = 0; i < rep.getMaxPages(); i++) Pages[i] = 1;


        rep.printPreview(Pages, PagesCount);


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

        /*     try{

        ((Report1)rep).getServeur().remoterollback(((Report1)rep).getUser().getUrcleunik());

        }

        catch(java.rmi.RemoteException re){

            srcastra.astra.gui.sys.ErreurScreenLibrary.displayErreur(this, srcastra.astra.gui.sys.ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }*/

    }

    /**
     * close window if user clicks on X
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

    public java.lang.String getPathForMail() {
        return pathForMail;
    }

    public java.lang.String getFormEntite() {

        return mailEntite;
    }

    /**
     * Setter for property pathForMail.
     *
     * @param pathForMail New value of property pathForMail.
     */
    public void setPathForMail(java.lang.String pathForMail) {
        this.pathForMail = pathForMail;
    }

    /**
     * Getter for property emailAdresse.
     *
     * @return Value of property emailAdresse.
     */
    public java.lang.String[] getEmailAdresse() {
        return this.emailAdresse;
    }

    /**
     * Setter for property emailAdresse.
     *
     * @param emailAdresse New value of property emailAdresse.
     */
    public void setEmailAdresse(java.lang.String[] emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public String[] getEmailAdres() {
        return emailAdresse;
    }

    public Loginusers_T getUser() {
        return m_user;
    }

    public String getFormEntiteMail() {
        return mailEntite;
    }


}