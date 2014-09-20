/*
 * DossierListing.java
 *
 * Created on 1 juillet 2005, 15:13
 */

package srcastra.astra.gui.list;

import java.util.*;

import srcastra.astra.gui.list.FournisseurListing;


/**
 * @author Administrateur
 */
public class DossierListing {

    private String numDos;
    private String DateDep;

    ArrayList fourn;


    /**
     * Creates a new instance of DossierListing
     */
    public DossierListing() {
        numDos = "";
        DateDep = "";
    }

    public void setNumDos(String numDos) {
        this.numDos = numDos;
    }

    public void setDateDep(String DateDep) {
        this.DateDep = DateDep;
    }

    public String getNumDos() {
        return numDos;
    }

    public String getDateDep() {

        return DateDep;
    }

    public void addFournisseur(FournisseurListing f) {
        fourn.add(f);
    }

}
