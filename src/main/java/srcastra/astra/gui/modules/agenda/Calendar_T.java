/*
 * Calendar_T.java
 *
 * Created on 23 mars 2004, 10:41
 */

package srcastra.astra.gui.modules.agenda;

import java.util.*;

/**
 * @author Administrateur
 */
public class Calendar_T implements java.io.Serializable {

    /**
     * Creates a new instance of Calendar_T
     */
    public Calendar_T() {
    }

    /**
     * Getter for property vector.
     *
     * @return Value of property vector.
     */
    public java.util.ArrayList getVector() {
        return vector;
    }

    /**
     * Setter for property vector.
     *
     * @param vector New value of property vector.
     */
    public void setVector(java.util.ArrayList vector) {
        this.vector = vector;
    }

    /**
     * Getter for property month.
     *
     * @return Value of property month.
     */
    public java.lang.String getMonth() {
        return month;
    }

    /**
     * Setter for property month.
     *
     * @param month New value of property month.
     */
    public void setMonth(java.lang.String month) {
        this.month = month;
    }

    ArrayList vector;
    String month;

}
