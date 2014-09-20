/*
 * AchatCp.java
 *
 * Created on 20 juin 2004, 19:31
 */

package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.sys.rmi.utils.MY_bigDecimal;
import srcastra.astra.sys.compta.*;

/**
 * @author administrateur
 */
public class AchatCp implements java.io.Serializable {

    /**
     * Creates a new instance of AchatCp
     */
    public AchatCp() {
        base = new MY_bigDecimal("0.00");
    }

    /**
     * Getter for property base.
     *
     * @return Value of property base.
     */
    public MY_bigDecimal getBase() {
        return base;
    }

    /**
     * Setter for property base.
     *
     * @param base New value of property base.
     */
    public void setBase(String base) {
        this.base = new MY_bigDecimal(base);
        this.base.abs();
    }

    /**
     * Getter for property catProd.
     *
     * @return Value of property catProd.
     */
    public int getCatProd() {
        return catProd;
    }

    /**
     * Setter for property catProd.
     *
     * @param catProd New value of property catProd.
     */
    public void setCatProd(int catProd) {
        this.catProd = catProd;
    }

    /**
     * Getter for property ce_cleunik.
     *
     * @return Value of property ce_cleunik.
     */
    public int getCe_cleunik() {
        return ce_cleunik;
    }

    /**
     * Setter for property ce_cleunik.
     *
     * @param ce_cleunik New value of property ce_cleunik.
     */
    public void setCe_cleunik(int ce_cleunik) {
        this.ce_cleunik = ce_cleunik;
    }

    /**
     * Getter for property ceText.
     *
     * @return Value of property ceText.
     */
    public java.lang.String getCeText() {
        return ceText;
    }

    /**
     * Setter for property ceText.
     *
     * @param ceText New value of property ceText.
     */
    public void setCeText(java.lang.String ceText) {
        this.ceText = ceText;
    }

    /**
     * Getter for property cledossier.
     *
     * @return Value of property cledossier.
     */
    public long getCledossier() {
        return cledossier;
    }

    /**
     * Setter for property cledossier.
     *
     * @param cledossier New value of property cledossier.
     */
    public void setCledossier(long cledossier) {
        this.cledossier = cledossier;
    }

    /**
     * Getter for property cleprod.
     *
     * @return Value of property cleprod.
     */
    public long getCleprod() {
        return cleprod;
    }

    /**
     * Setter for property cleprod.
     *
     * @param cleprod New value of property cleprod.
     */
    public void setCleprod(long cleprod) {
        this.cleprod = cleprod;
    }

    /**
     * Getter for property commentaire.
     *
     * @return Value of property commentaire.
     */
    public java.lang.String getCommentaire() {
        return commentaire;
    }

    /**
     * Setter for property commentaire.
     *
     * @param commentaire New value of property commentaire.
     */
    public void setCommentaire(java.lang.String commentaire) {
        this.commentaire = commentaire;
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
     * Getter for property hecleunik.
     *
     * @return Value of property hecleunik.
     */
    public int getHecleunik() {
        return hecleunik;
    }

    /**
     * Setter for property hecleunik.
     *
     * @param hecleunik New value of property hecleunik.
     */
    public void setHecleunik(int hecleunik) {
        this.hecleunik = hecleunik;
    }

    /**
     * Getter for property numdos.
     *
     * @return Value of property numdos.
     */
    public java.lang.String getNumdos() {
        return numdos;
    }

    /**
     * Setter for property numdos.
     *
     * @param numdos New value of property numdos.
     */
    public void setNumdos(java.lang.String numdos) {
        this.numdos = numdos;
    }

    /**
     * Getter for property PoPnr.
     *
     * @return Value of property PoPnr.
     */
    public java.lang.String getPoPnr() {
        return PoPnr;
    }

    /**
     * Setter for property PoPnr.
     *
     * @param PoPnr New value of property PoPnr.
     */
    public void setPoPnr(java.lang.String PoPnr) {
        this.PoPnr = PoPnr;
    }

    /**
     * Getter for property tva1.
     *
     * @return Value of property tva1.
     */
    public Tva_abrev_Achat_T getTva1() {
        if (tva1 == null)
            tva1 = new Tva_abrev_Achat_T(this);
        return tva1;
    }

    /**
     * Setter for property tva1.
     *
     * @param tva1 New value of property tva1.
     */
    public void setTva1(Tva_abrev_Achat_T tva1) {

        this.tva1 = tva1;
    }

    /**
     * Getter for property tva2.
     *
     * @return Value of property tva2.
     */
    public Tva_abrev_Achat_T getTva2() {
        if (tva2 == null)
            tva2 = new Tva_abrev_Achat_T(this);
        return tva2;
    }

    /**
     * Setter for property tva2.
     *
     * @param tva2 New value of property tva2.
     */
    public void setTva2(Tva_abrev_Achat_T tva2) {
        this.tva2 = tva2;
    }

    public void resetTva() {
        tva1 = new Tva_abrev_Achat_T();
        tva2 = new Tva_abrev_Achat_T();
    }

    /**
     * Getter for property frcleunik.
     *
     * @return Value of property frcleunik.
     */
    public int getFrcleunik() {
        return frcleunik;
    }

    /**
     * Setter for property frcleunik.
     *
     * @param frcleunik New value of property frcleunik.
     */
    public void setFrcleunik(int frcleunik) {
        this.frcleunik = frcleunik;
    }

    /**
     * Getter for property clefacture.
     *
     * @return Value of property clefacture.
     */
    public long getClefacture() {
        return clefacture;
    }

    /**
     * Setter for property clefacture.
     *
     * @param clefacture New value of property clefacture.
     */
    public void setClefacture(long clefacture) {
        this.clefacture = clefacture;
    }

    /**
     * Getter for property frnom.
     *
     * @return Value of property frnom.
     */
    public java.lang.String getFrnom() {
        return frnom;
    }

    /**
     * Setter for property frnom.
     *
     * @param frnom New value of property frnom.
     */
    public void setFrnom(java.lang.String frnom) {
        this.frnom = frnom;
    }

    /**
     * Getter for property numpiece.
     *
     * @return Value of property numpiece.
     */
    public java.lang.String getNumpiece() {
        return numpiece;
    }

    /**
     * Setter for property numpiece.
     *
     * @param numpiece New value of property numpiece.
     */
    public void setNumpiece(java.lang.String numpiece) {
        this.numpiece = numpiece;
    }

    public long getCleunikFact() {
        return this.cleunikfact;
    }

    public void setCleunikFact(long cleunikFact) {

        this.cleunikfact = cleunikFact;

    }

    /**
     * Getter for property dateMouv.
     *
     * @return Value of property dateMouv.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getDateMouv() {
        return dateMouv;
    }

    /**
     * Setter for property dateMouv.
     *
     * @param dateMouv New value of property dateMouv.
     */
    public void setDateMouv(srcastra.astra.sys.classetransfert.utils.Date dateMouv) {
        this.dateMouv = dateMouv;
    }

    /**
     * Getter for property jota_abrev.
     *
     * @return Value of property jota_abrev.
     */
    public java.lang.String getJota_abrev() {
        return jota_abrev;
    }

    /**
     * Setter for property jota_abrev.
     *
     * @param jota_abrev New value of property jota_abrev.
     */
    public void setJota_abrev(java.lang.String jota_abrev) {
        this.jota_abrev = jota_abrev;
    }

    /**
     * Getter for property jota_cleunik.
     *
     * @return Value of property jota_cleunik.
     */
    public int getJota_cleunik() {
        return jota_cleunik;
    }

    /**
     * Setter for property jota_cleunik.
     *
     * @param jota_cleunik New value of property jota_cleunik.
     */
    public void setJota_cleunik(int jota_cleunik) {
        this.jota_cleunik = jota_cleunik;
    }

    /**
     * Getter for property venteRent.
     *
     * @return Value of property venteRent.
     */
    public srcastra.astra.gui.modules.compta.achat.VenteRentabilite getVenteRent() {
        return venteRent;
    }

    /**
     * Setter for property venteRent.
     *
     * @param venteRent New value of property venteRent.
     */
    public void setVenteRent(srcastra.astra.gui.modules.compta.achat.VenteRentabilite venteRent) {
        this.venteRent = venteRent;
    }

    int ce_cleunik;
    String ceText;
    MY_bigDecimal base;
    String dc;
    String commentaire;
    String PoPnr;
    int catProd;
    int hecleunik;
    long cleprod;
    long cledossier;
    String numdos;
    int frcleunik;
    Tva_abrev_Achat_T tva1;
    Tva_abrev_Achat_T tva2;
    long clefacture;
    String frnom;
    String numpiece;
    srcastra.astra.sys.classetransfert.utils.Date dateMouv;
    String jota_abrev;
    int jota_cleunik;
    VenteRentabilite venteRent;
    long cleunikfact;

}
