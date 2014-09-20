package com.java4less.rreport.exp;

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
 * You can implement this interface if you need to execute your own functions within expressions.
 */

public interface IUserFunctions {


    /**
     * execute user defined functions.
     */

    public Object executeFunction(String functionName, Object[] parameters);


}