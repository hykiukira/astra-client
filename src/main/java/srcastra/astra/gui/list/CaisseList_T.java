/*

 * CaisseList_T.java

 *

 * Created on 17 novembre 2003, 16:42

 */


package srcastra.astra.gui.list;

import java.util.*;

/**
 * @author Thomas
 */

public class CaisseList_T implements java.io.Serializable {


    /**
     * Creates a new instance of CaisseList_T
     */

    Hashtable solde;

    ArrayList list;

    public CaisseList_T() {

    }


    /**
     * Getter for property list.
     *
     * @return Value of property list.
     */

    public java.util.ArrayList getList() {

        return list;

    }


    /**
     * Setter for property list.
     *
     * @param list New value of property list.
     */

    public void setList(java.util.ArrayList list) {

        this.list = list;

    }


    /**
     * Getter for property solde.
     *
     * @return Value of property solde.
     */

    public java.util.Hashtable getSolde() {

        return solde;

    }


    /**
     * Setter for property solde.
     *
     * @param solde New value of property solde.
     */

    public void setSolde(java.util.Hashtable solde) {

        this.solde = solde;

    }


}

