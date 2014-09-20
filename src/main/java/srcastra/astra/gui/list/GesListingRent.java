/*
 * GesListingRent.java
 *
 * Created on 1 juillet 2005, 15:23
 */

package srcastra.astra.gui.list;

import java.util.*;


/**
 * @author Administrateur
 */
public class GesListingRent {

    ArrayList d;

    /**
     * Creates a new instance of GesListingRent
     */
    public GesListingRent() {

    }

    public void add(Object[] obj) {
        boolean trouve = false;
        int i = 0;

        while (!trouve && i < d.size()) {
            DossierListing doss = (DossierListing) d.get(i);

            if (doss.getNumDos().equals(obj[1]))
                trouve = true;
            else
                i++;
        }

        if (trouve) {
            Vector v = new Vector();


        }

        //d.contains()


    }

}
