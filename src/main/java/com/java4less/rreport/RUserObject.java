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


import java.io.*;


/**
 * This object can be used to load a class dinamically at runtime. The class must be a subclass of RObject.
 */


public class RUserObject extends RObject


{


    private String userClassName = "";


    private RObject userObject = null;


    public void importLine(String key, String val) {


        super.importLine(key, val);


        if (key.compareTo("CLASS") == 0) this.userClassName = val;


    }


    /**
     * class to be loaded. Must be located in com/java4less/rreport
     */


    public void setUserClassName(String n) {


        userClassName = n;


    }


    /**
     * return class to be loaded
     */


    public String getUserClassName() {


        return userClassName;


    }


    /**
     * load class
     */


    public RObject getUserObject() {


        if (userObject == null)


            if (userClassName.compareTo("") != 0) {

                // load new class


                try {


                    userObject = (RObject) Class.forName("com.java4less.rreport." + userClassName).newInstance();


                    userObject.x = this.x;


                    userObject.y = this.y;


                    userObject.height = this.height;


                    userObject.width = this.width;


                } catch (Exception e) {


                    System.out.println("Error importing user class: " + e.getMessage());


                }


            }


        return userObject;


    }


    public void setUserObject(RObject uo) {
        userObject = uo;
    }


    public void print(Graphics g, double px, double py,Object Value) {











	}





}


