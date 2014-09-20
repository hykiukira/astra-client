package com.java4less.rreport;

// user objects must be in this package


import java.awt.*;


/**
 * example or the use of the RUserObject class.
 */

public class RExampleUserObject extends RObject {


    public RExampleUserObject() {

    }

    // print object here.

    public void print(Graphics g, double px, double py, Object Value) {

        // coordinates px,y

        // size: this.widthpixel, this.heightpixel

        // Value:  is the runtime value

        // you can also access any RObject properties. See "Creating your RObject" in documentation RReport_Help.html


        g.setColor(java.awt.Color.black);

        g.setFont(new Font("Arial", Font.PLAIN, 12));


        if (Value != null)

            if (Value instanceof String) {

                g.drawString((String) Value, (int) px, (int) py);

                return;

            }


        g.drawString("UserObject test", (int) px, (int) py);

    }


}