/*
 * Rentabilite.java
 *
 * Created on 24 juin 2004, 16:22
 */

package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.sys.rmi.utils.*;

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
        MY_bigDecimal quotient = vente.divide(new MY_bigDecimal("100.00"));
        MY_bigDecimal diff = vente.subtract(achat);
        if (quotient.doubleValue() != 0d)
            return diff.divide(quotient);
        else
            return new MY_bigDecimal("0.00");
    }

}
