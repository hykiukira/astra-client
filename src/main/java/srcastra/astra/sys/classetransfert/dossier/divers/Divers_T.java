/*
* VoitureLocation_T.java
*
* Created on 10 décembre 2002, 9:15
*/
package srcastra.astra.sys.classetransfert.dossier.divers;

import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.dossier.ProduitInfoComplementaire;
import srcastra.astra.sys.classetransfert.dossier.ProduitSynthese;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.produit.SupplementReduction;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.rmi.groupe_dec.GrpProduitGestion;
import srcastra.astra.sys.rmi.groupe_dec.GrpRetValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sébastien
 */
public class Divers_T extends srcastra.astra.sys.classetransfert.dossier.produit_T implements java.io.Serializable, Cloneable, srcastra.astra.sys.rmi.DossierSql, srcastra.astra.sys.classetransfert.dossier.InterfaceIndivProduit, ProduitSynthese {

    private long vl_cleUnik;
    private String vl_pnr;
    private Date vl_dateDepart;
    private Date vl_dateRetour;
    private float vl_montant;
    private String vl_memo;
    private Date datetimemodif;
    private Date datetimecrea;
    public long getAt_cleunik() {
        return vl_cleUnik;
    }

    public void setAt_cleunik(long atCleunik) {
        vl_cleUnik = atCleunik;
        super.setAt_cleunik(atCleunik);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Creates a new instance of VoitureLocation_T
     */
    public Divers_T() {
        setPax(1);
        setQua(1);
        setPrct(100);
        setTypeDeProduitCleunik(super.DIV);
    }

    public Divers_T(boolean sw) {
        setPax(1);
        setQua(1);
        setPrct(100);

    }

    public void annulMe(Connection con, PreparedStatement pstmt) throws SQLException {
        pstmt = con.prepareStatement("UPDATE diversprod set annuler=1 WHERE div_cleunik=?;");
        pstmt.setLong(1, this.getVl_cleUnik());
        pstmt.execute();
    }

    public void chargeMe(Loginusers_T currentuser, astrainterface serveur, Dossier_T dossier, Connection con, double cledossier, PreparedStatement pstmt) throws SQLException, java.rmi.RemoteException {
        String date = null;                   //1                2            3         4             5                   6                   7           8           9           10       11     12          13          14              15          16
        pstmt = con.prepareStatement("SELECT d.div_cleunik,d.div_objet,d.div_montant,d.div_memo,d.div_datetimemodif,d.div_datetimecrea,d.longtime,d.dr_cleunik,d.frgtcleunik,d.pourcent,d.pax,d.quantite, h.hevaleur, h.hevaleurbase, h.hevaleurtva,h.helibelle FROM  diversprod d,historique2 h WHERE h.lignecleunik=d.div_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=11 AND d.dr_cleunik=? AND d.annuler=0 AND h.hedossiercourant='O'");
        pstmt.setInt(1, dossier.getDrcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Divers_T voiture = new Divers_T();
            voiture.setVl_cleUnik(result.getLong(1));
            voiture.setVl_pnr(result.getString(2));
            voiture.setAt_val_vente(result.getDouble(3));
            voiture.setVl_memo(result.getString(4));
            voiture.setDatetimecrea(new Date(result.getString(5)));
            voiture.setDatetimemodif(new Date(result.getString(6)));
            voiture.setLongtime(result.getLong(7));
            voiture.setFrgtcleunik(result.getInt(9));
            voiture.setPrct(result.getFloat(10));
            voiture.setPax(result.getInt(11));
            voiture.setQua(result.getInt(12));
            voiture.setValeur_tot_tva_inc(-result.getDouble(13));
            voiture.setValeur_tot(-result.getDouble(14));
            voiture.setMontant_tva(-result.getDouble(15));
            voiture.setLibelleCompta(result.getString(16));
            GrpRetValue retG = GrpProduitGestion.filGrpDecToProd(serveur, voiture, con, currentuser.getUrcleunik());
            voiture.setGroupdecBase(retG.getBase());
            voiture.setGroupdec(retG.getOwn());
            ProduitInfoComplementaire.getInfo(ProduitInfoComplementaire.DIV, ProduitInfoComplementaire.DIV_FULL, produit_T.DIV, currentuser.getLangage(), voiture, con);
            voiture.setDoc(pstmt, con);
            SupplementReduction.chargeSupreduc(voiture, con, pstmt, serveur, currentuser.getUrcleunik());
            dossier.addDivers(voiture);
        }
    }

    public long insertOnlyme(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {
        String date = null;
        pstmt = con.prepareStatement("INSERT INTO diversprod (div_objet,div_montant,div_memo,div_datetimemodif,div_datetimecrea,longtime,dr_cleunik,frgtcleunik,pourcent,pax,quantite) VALUES (? ,? ,? ,NOW(), NOW(),? ,? ,? ,? ,? ,?  );");
        pstmt.setString(1, this.getVl_pnr());
        pstmt.setDouble(2, this.getAt_val_vente());
        pstmt.setString(3, this.getVl_memo());
        pstmt.setLong(4, getLongtime());
        pstmt.setLong(5, new Double(cledossier).longValue());
        pstmt.setInt(6, getFrgtcleunik());
        pstmt.setFloat(7, this.getPrct());
        pstmt.setInt(8, this.getPax());
        pstmt.setInt(9, this.getQua());
        pstmt.execute();
        this.setVl_cleUnik(getId(con));
        return this.getVl_cleUnik();
    }

    public void modifyOnlyMe(Connection con, int cledossier, PreparedStatement pstmt) throws SQLException {
        String date = null;
        pstmt = con.prepareStatement("UPDATE diversprod set div_objet=?,div_montant=?,div_memo=?,div_datetimemodif=NOW(),frgtcleunik=?,pourcent=?,pax=?,quantite=? WHERE div_cleunik=?;");
        pstmt.setString(1, this.getVl_pnr());
        pstmt.setDouble(2, this.getAt_val_vente());
        pstmt.setString(3, this.getVl_memo());
        pstmt.setInt(4, getFrgtcleunik());
        pstmt.setFloat(5, this.getPrct());
        pstmt.setInt(6, this.getPax());
        pstmt.setInt(7, this.getQua());
        pstmt.setLong(8, this.getVl_cleUnik());
        pstmt.execute();
    }

    /**
     * Getter for property datetimecrea.
     *
     * @return Value of property datetimecrea.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getDatetimecrea() {
        return datetimecrea;
    }

    /**
     * Setter for property datetimecrea.
     *
     * @param datetimecrea New value of property datetimecrea.
     */
    public void setDatetimecrea(srcastra.astra.sys.classetransfert.utils.Date datetimecrea) {
        this.datetimecrea = datetimecrea;
    }

    /**
     * Getter for property datetimemodif.
     *
     * @return Value of property datetimemodif.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getDatetimemodif() {
        return datetimemodif;
    }

    /**
     * Setter for property datetimemodif.
     *
     * @param datetimemodif New value of property datetimemodif.
     */
    public void setDatetimemodif(srcastra.astra.sys.classetransfert.utils.Date datetimemodif) {
        this.datetimemodif = datetimemodif;
    }

    /** Getter for property isattached.
     * @return Value of property isattached.
     */

    /** Getter for property longtime.
     * @return Value of property longtime.
     */
    /** Getter for property produitaffichage.
     * @return Value of property produitaffichage.
     */
/*   public srcastra.astra.sys.classetransfert.dossier.ProduitAffichage getProduitaffichage() {
return produitaffichage;
}


public void setProduitaffichage(srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaffichage) {
this.produitaffichage = produitaffichage;
}*/

    /**
     * Getter for property vl_cleUnik.
     *
     * @return Value of property vl_cleUnik.
     */
    public long getVl_cleUnik() {
        return vl_cleUnik;
    }

    /**
     * Setter for property vl_cleUnik.
     *
     * @param vl_cleUnik New value of property vl_cleUnik.
     */
    public void setVl_cleUnik(long vl_cleUnik) {
        setAt_cleunik(vl_cleUnik);
    }

    /**
     * Getter for property vl_dateDepart.
     *
     * @return Value of property vl_dateDepart.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getVl_dateDepart() {
        return vl_dateDepart;
    }

    /**
     * Setter for property vl_dateDepart.
     *
     * @param vl_dateDepart New value of property vl_dateDepart.
     */
    public void setVl_dateDepart(srcastra.astra.sys.classetransfert.utils.Date vl_dateDepart) {
        this.vl_dateDepart = vl_dateDepart;
    }

    /**
     * Getter for property vl_dateRetour.
     *
     * @return Value of property vl_dateRetour.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getVl_dateRetour() {
        return vl_dateRetour;
    }

    /**
     * Setter for property vl_dateRetour.
     *
     * @param vl_dateRetour New value of property vl_dateRetour.
     */
    public void setVl_dateRetour(srcastra.astra.sys.classetransfert.utils.Date vl_dateRetour) {
        this.vl_dateRetour = vl_dateRetour;
    }

    /**
     * Getter for property vl_memo.
     *
     * @return Value of property vl_memo.
     */
    public java.lang.String getVl_memo() {
        return vl_memo;
    }

    /**
     * Setter for property vl_memo.
     *
     * @param vl_memo New value of property vl_memo.
     */
    public void setVl_memo(java.lang.String vl_memo) {
        this.vl_memo = vl_memo;
    }

    /**
     * Getter for property vl_montant.
     *
     * @return Value of property vl_montant.
     */
    public float getVl_montant() {
        return vl_montant;
    }

    /**
     * Setter for property vl_montant.
     *
     * @param vl_montant New value of property vl_montant.
     */
    public void setVl_montant(float vl_montant) {
        this.vl_montant = vl_montant;
        setAt_val_vente(vl_montant);
    }

    /**
     * Getter for property vl_pnr.
     *
     * @return Value of property vl_pnr.
     */
    public java.lang.String getVl_pnr() {
        return vl_pnr;
    }

    /**
     * Setter for property vl_pnr.
     *
     * @param vl_pnr New value of property vl_pnr.
     */
    public void setVl_pnr(java.lang.String vl_pnr) {
        this.vl_pnr = vl_pnr;
    }

    /**
     * Getter for property vl_statut.
     *
     * @return Value of property vl_statut.
     */

    public void chargeDescriptif(Connection con, PreparedStatement pstmt, Dossier_T tmpDossier) throws SQLException {
    }

    public long insertDescriptif(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {
        return 0;
    }

    public void modifyDescriptif(Connection con, PreparedStatement pstmt) throws SQLException {
    }

    public srcastra.astra.sys.classetransfert.utils.Date getDateDep() {
        return this.vl_dateDepart;
    }

    public String getDestination() {
        return "";
    }

    public String getLogement() {
        return "";
    }

    public String getPnr() {
        return this.vl_pnr;
    }

    public java.util.ArrayList getDestinationArray() {
        return null;
    }
}
