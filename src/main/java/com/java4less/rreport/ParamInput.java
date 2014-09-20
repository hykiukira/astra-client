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


/**
 * used by RJDBCSource to ask for parameters when they have not been provided by the java program. This is actually only used by RReport Visual Builder.
 */


public class ParamInput extends Dialog {


    Label label1 = new Label();


    TextField textField1 = new TextField();


    Button button1 = new Button();


    Button button2 = new Button();


    boolean cancelled = true;


    String result = "";


    /**
     * Constructor, parent frame and name of the parameter to ask for.
     */


    public ParamInput(Frame f, String paramName) {


        super(f);


        this.setModal(true);


        this.setBounds(100, 100, 330, 135);


        try {


            jbInit();


            label1.setText("Please enter a value for parameter " + paramName + ":");


        }


        catch (Exception e) {


            e.printStackTrace();


        }


    }


    private void jbInit() throws Exception {


        label1.setText("Please enter a value for parameter");


        label1.setBounds(new Rectangle(5, 34, 312, 17));


        this.setTitle("Input dialog");


        this.setLayout(null);


        textField1.setBounds(new Rectangle(5, 59, 311, 21));


        button1.setLabel("OK");


        button1.setBounds(new Rectangle(70, 92, 75, 27));


        button1.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(ActionEvent e) {


                button1_actionPerformed(e);


            }


        });


        button2.setLabel("Skip");


        button2.setBounds(new Rectangle(161, 92, 75, 27));


        button2.addActionListener(new java.awt.event.ActionListener() {


            public void actionPerformed(ActionEvent e) {


                button2_actionPerformed(e);


            }


        });


        this.add(label1, null);


        this.add(textField1, null);


        this.add(button2, null);


        this.add(button1, null);


    }


    void button1_actionPerformed(ActionEvent e) {


        cancelled = false;


        result = textField1.getText();


        this.setVisible(false);


    }


    void button2_actionPerformed(ActionEvent e) {


        cancelled = true;


        this.setVisible(false);


    }


}