/*
 * VenteRentabilite.java
 *
 * Created on 24 juin 2004, 13:25
 */

package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.sys.rmi.utils.*;

/**
 * @author Administrateur
 */
public class VenteRentabilite implements java.io.Serializable {

    /**
     * Creates a new instance of VenteRentabilite
     */
    public VenteRentabilite() {
        venteTotal = new MY_bigDecimal("0.00");
        venteProduit = new MY_bigDecimal("0.00");
    }

    /**
     * Getter for property venteTotal.
     *
     * @return Value of property venteTotal.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getVenteTotal() {
        return venteTotal;
    }

    /**
     * Setter for property venteTotal.
     *
     * @param venteTotal New value of property venteTotal.
     */
    public void setVenteTotal(String venteTotals) {
        this.venteTotal = new MY_bigDecimal(venteTotals);
    }

    /**
     * Getter for property venteProduit.
     *
     * @return Value of property venteProduit.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getVenteProduit() {
        return venteProduit;
    }

    /**
     * Setter for property venteProduit.
     *
     * @param venteProduit New value of property venteProduit.
     */
    public void setVenteProduit(String venteProduits) {
        this.venteProduit = new MY_bigDecimal(venteProduits);
    }

    /**
     * Getter for property achat.
     *
     * @return Value of property achat.
     */
    public java.util.ArrayList getAchat() {
        return achat;
    }

    /**
     * Setter for property achat.
     *
     * @param achat New value of property achat.
     */
    public void setAchat(java.util.ArrayList achat) {
        this.achat = achat;
    }

    MY_bigDecimal venteTotal;
    MY_bigDecimal venteProduit;
    java.util.ArrayList achat;
}