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


/**
 * This class is used to load images from files. The default package for the images is com/java4less/images.<BR>
 * <p/>
 * <p/>
 * Example of use:<BR>
 * <p/>
 * <p/>
 * <BR>
 * <p/>
 * <p/>
 * new RImageFile("file.gif");  // this will try to load the image from the package com/java4less/images/file.gif<BR>
 * <p/>
 * <p/>
 * new RImageFile("images/file.gif");  // this will try to load the image from the package images/file.gif<BR>
 * <p/>
 * <p/>
 * new RImageFile("file:/D:/file.gif");  // this will try to load the image from the url d:/file.gif<BR>
 */


public class RImageFile


{


    /**
     * Default location for images is "com/java4less/images"
     */


    public static String defaultPath = "com/java4less/images/";


    java.awt.Image image = null;


    boolean loaded = false;


    String name = "";


    String urlLocation = "";


    /**
     * create an image file. "n" contains the name of the file.
     */


    public RImageFile(String n) {


        this.setName(n);


    }


    /**
     * get url of the image
     */


    public String getUrlLocation() {


        if (name.length() == 0) return name;


        if (urlLocation.length() == 0) return defaultPath + name;


        return urlLocation;


    }


    /**
     * set url of the image
     */


    public void setUrlLocation(String u) {


        urlLocation = u;


    }


    /**
     * get file name
     */


    public String getName() {
        return name;
    }


    /**
     * returns whether the image has been loaded
     */


    public boolean getLoaded() {
        return loaded;
    }


    /**
     * set filename
     */


    public void setName(String n) {


        if (name.compareTo(n) != 0) loaded = false;


        name = n;


        int p = n.lastIndexOf("\\");


        if (p == -1) p = n.lastIndexOf("/");


        if (p >= 0) {


            name = n.substring(p + 1, n.length());


            urlLocation = n;


        } else urlLocation = defaultPath + name;


    }


    /**
     * get Image object, loading the file if not loaded yet.
     */


    public Image getImage() {


        if (!loaded) loadImage();


        return image;


    }


    /**
     * set image object and name
     */


    public void setImage(String n, Image i) {


        setName(n);


        loaded = true;


        image = i;


    }


    /**
     * set image object.
     */


    public void setImage(Image i) {
        image = i;
    }


    /**
     * load image from file
     */


    public boolean loadImage() {


        String location = urlLocation;


        if (location.length() == 0) location = defaultPath + name;


        java.awt.Image im2 = null;


        try {


            java.net.URL url = null;


            if (this.getClass().getClassLoader() != null) url = this.getClass().getClassLoader().getResource(location);


            if (url == null) url = ClassLoader.getSystemResource(location);

            // use the loacation as url


            try {


                if (url == null) url = new java.net.URL(location);


            } catch (Exception e) {


                e.printStackTrace();


            }

            //System.out.println("Image : "+ defaultPath+name);

            // System.out.println("Image url: "+ url);


            if (url == null) System.out.println("Image not found : " + location);


            else {


                im2 = Toolkit.getDefaultToolkit().getImage(url);


                int WidthIm = 0;


                if (im2 != null) WidthIm = im2.getWidth(null);


                if (im2 == null) System.out.println("Image not loaded : " + location);


            }


        } catch (Exception e2) {


            System.out.println("Error reading image " + name + ": " + e2.getMessage());


				return false;


			}








		this.image=im2;


	   loaded=true;


	   return true;


	}





}


