/*



 * Achat_T.java



 *



 * Created on 31 juillet 2003, 11:19



 */


package srcastra.astra.gui.modules.compta.achat;


import srcastra.astra.sys.classetransfert.utils.Date;


import java.util.*;

import srcastra.astra.sys.rmi.utils.*;


/**
 * @author Thomas
 */


public class Achat_T implements java.io.Serializable {

    public Achat_T() {
        montanteuro = new MY_bigDecimal("0.00");
        tvamontant = new MY_bigDecimal("0.00");
        base = new MY_bigDecimal("0.00");
    }

    public java.util.ArrayList getAchat() {
        if (achat == null)
            achat = new ArrayList();
        return achat;
    }

    public void setAchat(java.util.ArrayList achat) {
        this.achat = achat;
    }

    public java.lang.String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(java.lang.String commentaire) {
        this.commentaire = commentaire;
    }

    public srcastra.astra.sys.classetransfert.utils.Date getDate() {
        return date;
    }

    public void setDate(srcastra.astra.sys.classetransfert.utils.Date date) {
        this.date = date;
    }

    public srcastra.astra.sys.classetransfert.utils.Date getEcheance() {
        return echeance;
    }

    public void setEcheance(srcastra.astra.sys.classetransfert.utils.Date echeance) {
        this.echeance = echeance;
    }

    public int getFraisgen() {
        return fraisgen;
    }

    public void setFraisgen(int fraisgen) {
        this.fraisgen = fraisgen;
    }

    public int getFrcleunik() {
        return frcleunik;
    }

    public void setFrcleunik(int frcleunik) {
        this.frcleunik = frcleunik;
    }

    public int getJota_cleunik() {
        return jota_cleunik;
    }

    public void setJota_cleunik(int jota_cleunik) {
        this.jota_cleunik = jota_cleunik;
    }

    public int getJota_periode() {
        return jota_periode;
    }

    public void setJota_periode(int jota_periode) {
        this.jota_periode = jota_periode;
    }

    public MY_bigDecimal getMontanteuro() {
        return montanteuro;
    }

    public void setMontanteuro(String montanteuro) {
        this.montanteuro = new MY_bigDecimal(montanteuro);
        this.montanteuro.abs();
    }

    public java.lang.String getNumpiece() {
        return numpiece;
    }

    public void setNumpiece(java.lang.String numpiece) {
        this.numpiece = numpiece;
    }

    public srcastra.astra.sys.classetransfert.utils.Date getRecept() {
        return recept;
    }

    public void setRecept(srcastra.astra.sys.classetransfert.utils.Date recept) {
        this.recept = recept;
    }

    public int getTypeprodCleunik() {
        return typeprodCleunik;
    }

    public void setTypeprodCleunik(int typeprodCleunik) {
        this.typeprodCleunik = typeprodCleunik;
    }

    public long getLignecleunik() {
        return lignecleunik;
    }

    public void setLignecleunik(long lignecleunik) {
        this.lignecleunik = lignecleunik;
    }

    public MY_bigDecimal getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = new MY_bigDecimal(base);
        this.base.abs();
    }

    public void setTva(String tva) {
        this.tvamontant = new MY_bigDecimal(tva);
        this.tvamontant.abs();
    }

    public int getDrcleunik() {
        return drcleunik;
    }

    public long getHeTransact() {
        return this.hetransact;
    }

    public void setHeTransact(long hetransact) {
        this.hetransact = hetransact;
    }

    public void setDrcleunik(int drcleunik) {

        this.drcleunik = drcleunik;
    }

    public MY_bigDecimal getTvamontant() {
        return tvamontant;
    }

    public void setTvamontant(String tvamontant) {
        this.tvamontant = new MY_bigDecimal(tvamontant);
        this.tvamontant.abs();
    }

    public java.util.Hashtable getComtpe() {
        return comtpe;
    }

    public void setComtpe(java.util.Hashtable comtpe) {
        this.comtpe = comtpe;
    }

    public java.util.Hashtable getComtpecp() {
        return comtpecp;
    }

    public void setComtpecp(java.util.Hashtable comtpecp) {
        this.comtpecp = comtpecp;
    }

    public java.util.Hashtable getComtpetva() {
        return comtpetva;
    }

    public void setComtpetva(java.util.Hashtable comtpetva) {
        this.comtpetva = comtpetva;
    }

    public int getJota_categorie() {
        return jota_categorie;
    }

    public void setJota_categorie(int jota_categorie) {
        this.jota_categorie = jota_categorie;
    }

    public java.util.ArrayList getResume() {
        return resume;
    }

    public void setResume(java.util.ArrayList resume) {
        this.resume = resume;

    }

    public MY_bigDecimal CalculSoldeComptable(String montantfacture, String debitCredit) throws AchatSoldeException {
        MY_bigDecimal basecpDebit;
        MY_bigDecimal basetvaDebit;
        MY_bigDecimal tvavalueDebit;
        MY_bigDecimal basecpCredit;
        MY_bigDecimal basetvaCredit;
        MY_bigDecimal tvavalueCredit;
        basecpDebit = new MY_bigDecimal("0.00");
        basetvaDebit = new MY_bigDecimal("0.00");
        tvavalueDebit = new MY_bigDecimal("0.00");
        basecpCredit = new MY_bigDecimal("0.00");
        basetvaCredit = new MY_bigDecimal("0.00");
        tvavalueCredit = new MY_bigDecimal("0.00");
        MY_bigDecimal retval = new MY_bigDecimal("0.00");
        montanteuro = new MY_bigDecimal(montantfacture.trim());
        dc = debitCredit;
        if (achat != null) {
            for (int i = 0; i < achat.size(); i++) {
                AchatCp achatcp = (AchatCp) achat.get(i);
                if (achatcp.getDc() != null) {
                    if (achatcp.getDc().equals("D")) {
                        basecpDebit = basecpDebit.add(achatcp.getBase());
                        Tva_abrev_Achat_T tva1 = achatcp.getTva1();
                        Tva_abrev_Achat_T tva2 = achatcp.getTva2();
                        basetvaDebit = basetvaDebit.add(tva1.getTva_base());
                        basetvaDebit = basetvaDebit.add(tva2.getTva_base());
                        tvavalueDebit = tvavalueDebit.add(tva1.getTva_value());
                        tvavalueDebit = tvavalueDebit.add(tva2.getTva_value());
                    } else if (achatcp.getDc().equals("C")) {
                        basecpCredit = basecpCredit.add(achatcp.getBase());
                        Tva_abrev_Achat_T tva1 = achatcp.getTva1();
                        Tva_abrev_Achat_T tva2 = achatcp.getTva2();
                        basetvaCredit = basetvaCredit.add(tva1.getTva_base());
                        basetvaCredit = basetvaCredit.add(tva2.getTva_base());
                        tvavalueCredit = tvavalueCredit.add(tva1.getTva_value());
                        tvavalueCredit = tvavalueCredit.add(tva2.getTva_value());
                    }
                }
            }
        }
        if (basecpCredit.doubleValue() != basetvaCredit.doubleValue()) {
            // throw new AchatSoldeException(basecpDebit,basecpCredit,basetvaCredit,basetvaDebit);

        }
        if (basecpDebit.doubleValue() != basetvaDebit.doubleValue()) {
            //throw new AchatSoldeException(basecpDebit,basecpCredit,basetvaCredit,basetvaDebit);
        }
        MY_bigDecimal totalDebit = basecpDebit.add(tvavalueDebit);
        MY_bigDecimal totalCredit = basecpCredit.add(tvavalueCredit);
        MY_bigDecimal totalTva = tvavalueDebit.subtract(tvavalueCredit);
        MY_bigDecimal total = totalDebit.subtract(totalCredit);
        setBase(total.toString());
        setTvamontant(totalTva.toString());
        if (dc.equals("C")) {
            if (total.doubleValue() >= 0d) {
                total.setDc("D");
            } else {
                total.setDc("C");
            }
            total.abs();
            if (total.getDc().equals("C")) {
                retval = montanteuro.add(total);
                retval.setDc("D");
            } else {

                MY_bigDecimal tmpTot = montanteuro.subtract(total);
                if (tmpTot.doubleValue() >= 0d) {
                    retval = new MY_bigDecimal(tmpTot.toString());
                    retval.setDc("D");
                } else {
                    tmpTot.abs();
                    retval = new MY_bigDecimal(tmpTot.toString());
                    retval.setDc("C");
                }

            }
        } else if (dc.equals("D")) {
            if (total.doubleValue() > 0d) {
                total.setDc("D");
            } else {
                total.setDc("C");
            }
            total.abs();
            if (total.getDc().equals("D")) {
                retval = montanteuro.add(total);
                retval.setDc("C");
            } else {
                MY_bigDecimal tmpTot = montanteuro.subtract(total);
                if (tmpTot.doubleValue() >= 0d) {
                    retval = new MY_bigDecimal(tmpTot.toString());
                    retval.setDc("C");
                } else {
                    tmpTot.abs();
                    retval = new MY_bigDecimal(tmpTot.toString());
                    retval.setDc("D");
                }

            }
        }
        System.out.println("[********************] CACUL SOLDE ACHAT\n\n");
        System.out.println("     base cp debit " + basecpDebit.toString());
        System.out.println("     base tva debit " + basetvaDebit.toString());
        System.out.println("     Tva debit " + tvavalueDebit.toString());
        System.out.println("     base cp credit " + basecpCredit.toString());
        System.out.println("     base tva credit " + basetvaCredit.toString());
        System.out.println("     Tva credit " + tvavalueCredit.toString());
        return retval;
    }

    /**
     * Getter for property dc.
     *
     * @return Value of property dc.
     */
    public java.lang.String getDc() {
        return dc;
    }

    /**
     * Setter for property dc.
     *
     * @param dc New value of property dc.
     */
    public void setDc(java.lang.String dc) {
        this.dc = dc;
    }

    /**
     * Getter for property exported.
     *
     * @return Value of property exported.
     */
    public long getExported() {
        return exported;
    }

    /**
     * Setter for property exported.
     *
     * @param exported New value of property exported.
     */
    public void setExported(long exported) {
        this.exported = exported;
    }


    public long getCleunikFact() {
        return this.cleunikFact;
    }

    public void setCleunikFact(long cleunikFact) {
        this.cleunikFact = cleunikFact;
    }

    /**
     * Getter for property montantFactPre.
     *
     * @return Value of property montantFactPre.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getMontantFactPre() {
        return montantFactPre;
    }

    /**
     * Setter for property montantFactPre.
     *
     * @param montantFactPre New value of property montantFactPre.
     */
    public void setMontantFactPre(srcastra.astra.sys.rmi.utils.MY_bigDecimal montantFactPre) {
        this.montantFactPre = montantFactPre;
    }

    int jota_cleunik;
    int jota_periode;
    String numpiece;
    Date date;
    Date recept;
    Date echeance;
    int frcleunik;
    MY_bigDecimal montanteuro;
    MY_bigDecimal montantFactPre;
    int fraisgen;
    String commentaire;
    ArrayList achat;
    //ArrayList tva;
    int typeprodCleunik;
    long lignecleunik;
    MY_bigDecimal base;
    MY_bigDecimal tvamontant;
    int drcleunik;
    Hashtable comtpe;
    Hashtable comtpecp;
    Hashtable comtpetva;
    int jota_categorie;
    ArrayList resume;
    String dc;
    long exported;
    long hetransact;
    long cleunikFact;


}



