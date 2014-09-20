/*
 * AchatSoldeException.java
 *
 * Created on 22 juin 2004, 09:46
 */

package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.sys.rmi.utils.*;

/**
 * @author Administrateur
 */
public class AchatSoldeException extends Exception {

    /**
     * Creates a new instance of AchatSoldeException
     */
    MY_bigDecimal baseDebit;
    MY_bigDecimal baseCredit;
    MY_bigDecimal tvaBaseCredit;
    MY_bigDecimal tvaBaseDebit;

    public AchatSoldeException(MY_bigDecimal baseDebit, MY_bigDecimal baseCredit, MY_bigDecimal tvaBaseCredit, MY_bigDecimal tvaBaseDebit) {
        this.baseDebit = baseDebit;
        this.baseCredit = baseCredit;
        this.tvaBaseCredit = tvaBaseCredit;
        this.tvaBaseDebit = tvaBaseDebit;

    }

    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("[********************] CACUL SOLDE ACHAT\n\n");
        System.out.println("     base cp debit " + baseDebit.toString() + "  Tva debit " + tvaBaseDebit.toString());
        System.out.println("     base tva debit " + baseCredit.toString() + " Tva credit " + tvaBaseCredit.toString());
    }

}
