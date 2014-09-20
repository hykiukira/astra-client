/*
 * BspObject.java
 *
 * Created on 7 juin 2004, 14:17
 */

package srcastra.astra.gui.modules.bsp;

import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;

/**
 * @author Administrateur
 */
public class BspObject {

    /**
     * Creates a new instance of BspObject
     */
    //              0           1           2               3                   4               5               6
    //SELECT a.at_cleunik,c.coe_abrev,a.at_num_billet,a.at_date_emission,a.at_val_facial,a.at_mode_paiement,a.at_num_cccf,
    //      7           8           9
    //s.sn_prix,a.at_val_com,a.at_annull_etat 
    //FROM avion_ticket a LEFT JOIN suplement_reduction s ON (a.at_cleunik =s.at_cleunik AND s.ctpro_cleunik=1 AND s.snlib_cleunik=-1) LEFT JOIN compagnie c  ON(a.coe_cleunik=c.coe_cleunik) WHERE a.at_type_billet=1 AND a.at_annull_etat!="+Avion_ticket_T.CANCEL+" AND (at_date_emission>=  ? AND at_date_emission<=?)";
    public BspObject(Object[] tab) {
        at_cleunik = Integer.parseInt(tab[0].toString());
        compagnie = "";
        if (tab[1] != null)
            compagnie = tab[1].toString();
        numbillet = tab[2].toString();
        emission = (Date) tab[3];
        facial = new MY_bigDecimal(tab[4].toString());
        facial.abs();
        modepay = Integer.parseInt(tab[5].toString());
        cccf = tab[6].toString();
        if (tab[7] != null) {
            taxelocal = new MY_bigDecimal(tab[7].toString());
        } else {
            taxelocal = new MY_bigDecimal("0.00");

        }
        taxelocal.abs();
        com = new MY_bigDecimal(tab[8].toString());
        annulEtat = Integer.parseInt(tab[9].toString());
        if (modepay == 7 && !cccf.equals("")) {
            cccfb = true;

        }
        commission = MY_bigDecimal.calculCommission(com.getBigdec(), facial.getBigdec());
        commission.abs();
        if (tab[11] != null)
            cancelFee = new MY_bigDecimal(tab[11].toString());
        else
            cancelFee = new MY_bigDecimal("0.00");
        cancelFee.abs();
        if (cccfb) {
            netToPay = commission.multiply(new MY_bigDecimal("-1.00"));
            cancelFee = new MY_bigDecimal("0.00");
        } else {
            netToPay = facial.add(taxelocal);
            netToPay = netToPay.subtract(commission);
            netToPay = netToPay.subtract(cancelFee);
        }
        numdos = "";
        if (tab[10] != null)
            numdos = tab[10].toString();

    }

    public Object[] getString() {
        String facecash = "";
        String facecredit = "";
        String remark = " ";
        if (cccf != null)
            remark = cccf;

        if (modepay == 7 && !cccf.equals("")) {
            facecredit = facial.toString();
        } else {
            facecash = facial.toString();
        }

        return new String[]{compagnie, numbillet, emission.toString2(), facecash, facecredit, taxelocal.toString(), com.toString(), commission.toString(), " ", cancelFee.toString(), netToPay.toString(), remark, numdos};

    }

    public MY_bigDecimal addFacialCash(MY_bigDecimal cash) {
        if (!cccfb) {
            cash = cash.add(facial);
        }
        return cash;
    }

    public MY_bigDecimal addTaxeCash(MY_bigDecimal taxecash) {
        if (!cccfb) {
            taxecash = taxecash.add(taxelocal);
        }
        return taxecash;
    }

    public MY_bigDecimal addTaxeCredit(MY_bigDecimal taxecredit) {
        if (cccfb) {
            taxecredit = taxecredit.add(taxelocal);
        }
        return taxecredit;
    }

    public MY_bigDecimal addFacialCredit(MY_bigDecimal credit) {
        if (cccfb) {
            credit = credit.add(facial);
        }
        return credit;
    }

    /**
     * Getter for property taxelocal.
     *
     * @return Value of property taxelocal.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getTaxelocal() {
        return taxelocal;
    }

    /**
     * Setter for property taxelocal.
     *
     * @param taxelocal New value of property taxelocal.
     */
    public void setTaxelocal(srcastra.astra.sys.rmi.utils.MY_bigDecimal taxelocal) {
        this.taxelocal = taxelocal;
    }

    /**
     * Getter for property commission.
     *
     * @return Value of property commission.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getCommission() {
        return commission;
    }

    /**
     * Setter for property commission.
     *
     * @param commission New value of property commission.
     */
    public void setCommission(srcastra.astra.sys.rmi.utils.MY_bigDecimal commission) {
        this.commission = commission;
    }

    /**
     * Getter for property netToPay.
     *
     * @return Value of property netToPay.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getNetToPay() {
        return netToPay;
    }

    /**
     * Setter for property netToPay.
     *
     * @param netToPay New value of property netToPay.
     */
    public void setNetToPay(srcastra.astra.sys.rmi.utils.MY_bigDecimal netToPay) {
        this.netToPay = netToPay;
    }

    /**
     * Getter for property cancelFee.
     *
     * @return Value of property cancelFee.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getCancelFee() {
        return cancelFee;
    }

    /**
     * Setter for property cancelFee.
     *
     * @param cancelFee New value of property cancelFee.
     */
    public void setCancelFee(srcastra.astra.sys.rmi.utils.MY_bigDecimal cancelFee) {
        this.cancelFee = cancelFee;
    }

    int at_cleunik;
    String numbillet;
    String compagnie;
    Date emission;
    MY_bigDecimal facial;
    String numdos;
    int modepay;
    String cccf;
    MY_bigDecimal taxelocal;
    MY_bigDecimal cancelFee;
    MY_bigDecimal com;
    MY_bigDecimal commission;
    MY_bigDecimal netToPay;
    int annulEtat;
    boolean cccfb;
}
