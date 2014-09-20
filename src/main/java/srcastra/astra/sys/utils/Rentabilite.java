/*
 * Rentabilite.java
 *
 * Created on 24 juin 2004, 16:22
 */

package srcastra.astra.sys.utils;

import srcastra.astra.sys.rmi.utils.MY_bigDecimal;

/**
 * @author Administrateur
 */
public class Rentabilite {

    /**
     * Creates a new instance of Rentabilite
     */
    public Rentabilite() {
    }

    public static MY_bigDecimal getRentabilite(MY_bigDecimal achat, MY_bigDecimal vente) {
        MY_bigDecimal rentabilite = new MY_bigDecimal("0.00");
        if (vente != null && achat != null) {
            if (achat.doubleValue() == 0d) {
                rentabilite = new MY_bigDecimal("100.00");
            } else {
                MY_bigDecimal quotient = vente.divide(new MY_bigDecimal("100.00"));
                MY_bigDecimal diff = vente.subtract(achat);
                if (quotient.doubleValue() != 0d) {
                    rentabilite = diff.divide(quotient);
                } else {
                    rentabilite = new MY_bigDecimal("0.00");
                }
            }
        }
        return rentabilite;
    }

}
