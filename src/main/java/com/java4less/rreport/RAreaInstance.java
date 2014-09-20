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


import java.util.Vector;


/**
 * this class contains a the set of RObjectIntances of an area instance
 */

public class RAreaInstance {


    Vector list = new Vector();


    /**
     * are repetition Id
     */

    public long repetitionId = 0;


    public RAreaInstance(long repId) {

        repetitionId = repId;

    }


    /**
     * add object intance
     */

    public void addObjectInstance(RObjectInstance obj) {

        list.addElement(obj);

    }


    /**
     * get Object Instance by name
     */

    public RObjectInstance getObjectInstance(String name) {


        for (int j = 0; j < list.size(); j++) {

            RObjectInstance obj = (RObjectInstance) list.elementAt(j);


            if (obj.getRObject().name.compareTo(name) == 0) return obj;

        }


        return null;

    }


}