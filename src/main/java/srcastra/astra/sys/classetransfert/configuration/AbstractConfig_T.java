/*
 * Config_T.java
 *
 * Created on 5 mars 2003, 8:37
 */

package srcastra.astra.sys.classetransfert.configuration;

/**
 *
 * @author  Thomas
 */
public abstract class AbstractConfig_T implements java.io.Serializable{
    
    /** Creates a new instance of Config_T */
    public AbstractConfig_T() {
    }
    
    /** Getter for property bdc_bdc_cli_x.
     * @return Value of property bdc_bdc_cli_x.
     */
    public double getBdc_bdc_cli_x() {
        return bdc_bdc_cli_x;
    }
    
    /** Setter for property bdc_bdc_cli_x.
     * @param bdc_bdc_cli_x New value of property bdc_bdc_cli_x.
     */
    public void setBdc_bdc_cli_x(double bdc_bdc_cli_x) {
        this.bdc_bdc_cli_x = bdc_bdc_cli_x;
    }
    
    /** Getter for property bdc_bdc_cli_y.
     * @return Value of property bdc_bdc_cli_y.
     */
    public double getBdc_bdc_cli_y() {
        return bdc_bdc_cli_y;
    }
    
    /** Setter for property bdc_bdc_cli_y.
     * @param bdc_bdc_cli_y New value of property bdc_bdc_cli_y.
     */
    public void setBdc_bdc_cli_y(double bdc_bdc_cli_y) {
        this.bdc_bdc_cli_y = bdc_bdc_cli_y;
    }
    
    /** Getter for property bdc_bdc_x.
     * @return Value of property bdc_bdc_x.
     */
    public double getBdc_bdc_x() {
        return bdc_bdc_x;
    }
    
    /** Setter for property bdc_bdc_x.
     * @param bdc_bdc_x New value of property bdc_bdc_x.
     */
    public void setBdc_bdc_x(double bdc_bdc_x) {
        this.bdc_bdc_x = bdc_bdc_x;
    }
    
    /** Getter for property bdc_bdc_y.
     * @return Value of property bdc_bdc_y.
     */
    public double getBdc_bdc_y() {
        return bdc_bdc_y;
    }
    
    /** Setter for property bdc_bdc_y.
     * @param bdc_bdc_y New value of property bdc_bdc_y.
     */
    public void setBdc_bdc_y(double bdc_bdc_y) {
        this.bdc_bdc_y = bdc_bdc_y;
    }
    
    /** Getter for property bdc_bloc_d.
     * @return Value of property bdc_bloc_d.
     */
    public int getBdc_bloc_d() {
        return bdc_bloc_d;
    }
    
    /** Setter for property bdc_bloc_d.
     * @param bdc_bloc_d New value of property bdc_bloc_d.
     */
    public void setBdc_bloc_d(int bdc_bloc_d) {
        this.bdc_bloc_d = bdc_bloc_d;
    }
    
    /** Getter for property bdc_bloc_g.
     * @return Value of property bdc_bloc_g.
     */
    public int getBdc_bloc_g() {
        return bdc_bloc_g;
    }
    
    /** Setter for property bdc_bloc_g.
     * @param bdc_bloc_g New value of property bdc_bloc_g.
     */
    public void setBdc_bloc_g(int bdc_bloc_g) {
        this.bdc_bloc_g = bdc_bloc_g;
    }
    
    /** Getter for property bdc_texte.
     * @return Value of property bdc_texte.
     */
    public java.lang.String getBdc_texte() {
        return bdc_texte;
    }
    
    /** Setter for property bdc_texte.
     * @param bdc_texte New value of property bdc_texte.
     */
    public void setBdc_texte(java.lang.String bdc_texte) {
        this.bdc_texte = bdc_texte;
    }
    
    /** Getter for property concleunik.
     * @return Value of property concleunik.
     */
    public int getConfcleunik() {
        return confcleunik;
    }
    
    /** Setter for property concleunik.
     * @param concleunik New value of property concleunik.
     */
    public void setConfcleunik(int concleunik) {
        this.confcleunik = concleunik;
    }
    
    /** Getter for property eecleunik.
     * @return Value of property eecleunik.
     */
    public int getEecleunik() {
        return eecleunik;
    }
    
    /** Setter for property eecleunik.
     * @param eecleunik New value of property eecleunik.
     */
    public void setEecleunik(int eecleunik) {
        this.eecleunik = eecleunik;
    }
    
    /** Getter for property nbr_lettre_liste.
     * @return Value of property nbr_lettre_liste.
     */
    public int getNbr_lettre_liste() {
        return nbr_lettre_liste;
    }
    
    /** Setter for property nbr_lettre_liste.
     * @param nbr_lettre_liste New value of property nbr_lettre_liste.
     */
    public void setNbr_lettre_liste(int nbr_lettre_liste) {
        this.nbr_lettre_liste = nbr_lettre_liste;
    }
    
    /** Getter for property fraisDossierTva.
     * @return Value of property fraisDossierTva.
     */
    public float getFraisDossierTva() {
        return fraisDossierTva;
    }
    /** Setter for property fraisDossierTva.
     * @param fraisDossierTva New value of property fraisDossierTva.
     */
    public void setFraisDossierTva(float fraisDossierTva) {
        this.fraisDossierTva = fraisDossierTva;
    }
    /** Getter for property fraisDossier.
     * @return Value of property fraisDossier.
     */
    public double getFraisDossier() {
        return fraisDossier;
    }
    
    /** Setter for property fraisDossier.
     * @param fraisDossier New value of property fraisDossier.
     */
    public void setFraisDossier(double fraisDossier) {
        this.fraisDossier = fraisDossier;
    } 
    
    /** Getter for property fraisdossiertvacle.
     * @return Value of property fraisdossiertvacle.
     */
    public int getFraisdossiertvacle() {
        return fraisdossiertvacle;
    }
    
    /** Setter for property fraisdossiertvacle.
     * @param fraisdossiertvacle New value of property fraisdossiertvacle.
     */
    public void setFraisdossiertvacle(int fraisdossiertvacle) {
        this.fraisdossiertvacle = fraisdossiertvacle;
    }
    
    /** Getter for property multibureaux.
     * @return Value of property multibureaux.
     */
    public int getMultibureaux() {
        return multibureaux;
    }
    
    /** Setter for property multibureaux.
     * @param multibureaux New value of property multibureaux.
     */
    public void setMultibureaux(int multibureaux) {
        this.multibureaux = multibureaux;
    }
    
    /** Getter for property caisseparutilisateur.
     * @return Value of property caisseparutilisateur.
     */
    public int getCaisseparutilisateur() {
        return caisseparutilisateur;
    }
    
    /** Setter for property caisseparutilisateur.
     * @param caisseparutilisateur New value of property caisseparutilisateur.
     */
    public void setCaisseparutilisateur(int caisseparutilisateur) {
        this.caisseparutilisateur = caisseparutilisateur;
    }
    
    /** Getter for property annullfees.
     * @return Value of property annullfees.
     *
     */
    public int getAnnullfees() {
        return annullfees;
    }
    
    /** Setter for property annullfees.
     * @param annullfees New value of property annullfees.
     *
     */
    public void setAnnullfees(int annullfees) {
        this.annullfees = annullfees;
    }
    
    /** Getter for property fraistvainclus.
     * @return Value of property fraistvainclus.
     *
     */
    public int getFraistvainclus() {
        return fraistvainclus;
    }    
    
    /** Setter for property fraistvainclus.
     * @param fraistvainclus New value of property fraistvainclus.
     *
     */
    public void setFraistvainclus(int fraistvainclus) {
        this.fraistvainclus = fraistvainclus;
    }
    
    /** Getter for property compteFrais.
     * @return Value of property compteFrais.
     *
     */
    public int getCompteFrais() {
        return compteFrais;
    }
    
    /** Setter for property compteFrais.
     * @param compteFrais New value of property compteFrais.
     *
     */
    public void setCompteFrais(int compteFrais) {
        this.compteFrais = compteFrais;
    }
    
    /** Getter for property compteTaxcomp.
     * @return Value of property compteTaxcomp.
     *
     */
    public int getCompteTaxcomp() {
        return compteTaxcomp;
    }
    
    /** Setter for property compteTaxcomp.
     * @param compteTaxcomp New value of property compteTaxcomp.
     *
     */
    public void setCompteTaxcomp(int compteTaxcomp) {
        this.compteTaxcomp = compteTaxcomp;
    }
    
    /** Getter for property compteTaxDest.
     * @return Value of property compteTaxDest.
     *
     */
    public int getCompteTaxDest() {
        return compteTaxDest;
    }
    
    /** Setter for property compteTaxDest.
     * @param compteTaxDest New value of property compteTaxDest.
     *
     */
    public void setCompteTaxDest(int compteTaxDest) {
        this.compteTaxDest = compteTaxDest;
    }
    
    /** Getter for property compteTaxFees.
     * @return Value of property compteTaxFees.
     *
     */
    public int getCompteTaxFees() {
        return compteTaxFees;
    }
    
    /** Setter for property compteTaxFees.
     * @param compteTaxFees New value of property compteTaxFees.
     *
     */
    public void setCompteTaxFees(int compteTaxFees) {
        this.compteTaxFees = compteTaxFees;
    }
    
    /** Getter for property compteTaxLoc.
     * @return Value of property compteTaxLoc.
     *
     */
    public int getCompteTaxLoc() {
        return compteTaxLoc;
    }
    
    /** Setter for property compteTaxLoc.
     * @param compteTaxLoc New value of property compteTaxLoc.
     *
     */
    public void setCompteTaxLoc(int compteTaxLoc) {
        this.compteTaxLoc = compteTaxLoc;
    }
    
    /** Getter for property compteTaxRemise.
     * @return Value of property compteTaxRemise.
     *
     */
    public int getCompteTaxRemise() {
        return compteTaxRemise;
    }
    
    /** Setter for property compteTaxRemise.
     * @param compteTaxRemise New value of property compteTaxRemise.
     *
     */
    public void setCompteTaxRemise(int compteTaxRemise) {
        this.compteTaxRemise = compteTaxRemise;
    }
    
    /** Getter for property nombrebloc.
     * @return Value of property nombrebloc.
     *
     */
    public int getNombrebloc() {
        return nombrebloc;
    }
    
    /** Setter for property nombrebloc.
     * @param nombrebloc New value of property nombrebloc.
     *
     */
    public void setNombrebloc(int nombrebloc) {
        this.nombrebloc = nombrebloc;
    }
    
    /** Getter for property passagerVisibleBDC.
     * @return Value of property passagerVisibleBDC.
     *
     */
    public int getPassagerVisibleBDC() {
        return passagerVisibleBDC;
    }
    
    /** Setter for property passagerVisibleBDC.
     * @param passagerVisibleBDC New value of property passagerVisibleBDC.
     *
     */
    public void setPassagerVisibleBDC(int passagerVisibleBDC) {
        this.passagerVisibleBDC = passagerVisibleBDC;
    }
    
    /** Getter for property passagerVisibleFact.
     * @return Value of property passagerVisibleFact.
     *
     */
    public int getPassagerVisibleFact() {
        return passagerVisibleFact;
    }
    
    /** Setter for property passagerVisibleFact.
     * @param passagerVisibleFact New value of property passagerVisibleFact.
     *
     */
    public void setPassagerVisibleFact(int passagerVisibleFact) {
        this.passagerVisibleFact = passagerVisibleFact;
    }
    
    /** Getter for property detailProduitVisibleFact.
     * @return Value of property detailProduitVisibleFact.
     *
     */
    public int getDetailProduitVisibleFact() {
        return detailProduitVisibleFact;
    }
    
    /** Setter for property detailProduitVisibleFact.
     * @param detailProduitVisibleFact New value of property detailProduitVisibleFact.
     *
     */
    public void setDetailProduitVisibleFact(int detailProduitVisibleFact) {
        this.detailProduitVisibleFact = detailProduitVisibleFact;
    }
    
    /** Getter for property detailProduitVisibleBDC.
     * @return Value of property detailProduitVisibleBDC.
     *
     */
    public int getDetailProduitVisibleBDC() {
        return detailProduitVisibleBDC;
    }
    
    /** Setter for property detailProduitVisibleBDC.
     * @param detailProduitVisibleBDC New value of property detailProduitVisibleBDC.
     *
     */
    public void setDetailProduitVisibleBDC(int detailProduitVisibleBDC) {
        this.detailProduitVisibleBDC = detailProduitVisibleBDC;
    }
    
    /** Getter for property detailPrixVisibleFact.
     * @return Value of property detailPrixVisibleFact.
     *
     */
    public int getDetailPrixVisibleFact() {
        return detailPrixVisibleFact;
    }
    
    /** Setter for property detailPrixVisibleFact.
     * @param detailPrixVisibleFact New value of property detailPrixVisibleFact.
     *
     */
    public void setDetailPrixVisibleFact(int detailPrixVisibleFact) {
        this.detailPrixVisibleFact = detailPrixVisibleFact;
    }
    
    /** Getter for property detailPrixVisibleBDC.
     * @return Value of property detailPrixVisibleBDC.
     *
     */
    public int getDetailPrixVisibleBDC() {
        return detailPrixVisibleBDC;
    }
    
    /** Setter for property detailPrixVisibleBDC.
     * @param detailPrixVisibleBDC New value of property detailPrixVisibleBDC.
     *
     */
    public void setDetailPrixVisibleBDC(int detailPrixVisibleBDC) {
        this.detailPrixVisibleBDC = detailPrixVisibleBDC;
    }
    
    /** Getter for property factureauto.
     * @return Value of property factureauto.
     *
     */
    public int getFactureauto() {
        return factureauto;
    }
    
    /** Setter for property factureauto.
     * @param factureauto New value of property factureauto.
     *
     */
    public void setFactureauto(int factureauto) {
        this.factureauto = factureauto;
    }
    
    /** Getter for property facturenumber.
     * @return Value of property facturenumber.
     *
     */
    public int getFacturenumber() {
        return facturenumber;
    }
    
    /** Setter for property facturenumber.
     * @param facturenumber New value of property facturenumber.
     *
     */
    public void setFacturenumber(int facturenumber) {
        this.facturenumber = facturenumber;
    }
    
    /** Getter for property bdcauto.
     * @return Value of property bdcauto.
     *
     */
    public int getBdcauto() {
        return bdcauto;
    }
    
    /** Setter for property bdcauto.
     * @param bdcauto New value of property bdcauto.
     *
     */
    public void setBdcauto(int bdcauto) {
        this.bdcauto = bdcauto;
    }
    
    /** Getter for property bdcnumber.
     * @return Value of property bdcnumber.
     *
     */
    public int getBdcnumber() {
        return bdcnumber;
    }
    
    /** Setter for property bdcnumber.
     * @param bdcnumber New value of property bdcnumber.
     *
     */
    public void setBdcnumber(int bdcnumber) {
        this.bdcnumber = bdcnumber;
    }
    
    /** Getter for property ncauto.
     * @return Value of property ncauto.
     *
     */
    public int getNcauto() {
        return ncauto;
    }
    
    /** Setter for property ncauto.
     * @param ncauto New value of property ncauto.
     *
     */
    public void setNcauto(int ncauto) {
        this.ncauto = ncauto;
    }
    
    /** Getter for property nccnumber.
     * @return Value of property nccnumber.
     *
     */
    public int getNccnumber() {
        return nccnumber;
    }
    
    /** Setter for property nccnumber.
     * @param nccnumber New value of property nccnumber.
     *
     */
    public void setNccnumber(int nccnumber) {
        this.nccnumber = nccnumber;
    }
    
    /** Getter for property factureresum.
     * @return Value of property factureresum.
     *
     */
    public int getFactureresum() {
        return factureresum;
    }
    
    /** Setter for property factureresum.
     * @param factureresum New value of property factureresum.
     *
     */
    public void setFactureresum(int factureresum) {
        this.factureresum = factureresum;
    }
    
    /** Getter for property affichebloclegalfacture.
     * @return Value of property affichebloclegalfacture.
     *
     */
    public int getAffichebloclegalfacture() {
        return affichebloclegalfacture;
    }
    
    /** Setter for property affichebloclegalfacture.
     * @param affichebloclegalfacture New value of property affichebloclegalfacture.
     *
     */
    public void setAffichebloclegalfacture(int affichebloclegalfacture) {
        this.affichebloclegalfacture = affichebloclegalfacture;
    }
    
    /** Getter for property bloquegupx.
     * @return Value of property bloquegupx.
     *
     */
    public double getBloquegupx() {
        return bloquegupx;
    }
    
    /** Setter for property bloquegupx.
     * @param bloquegupx New value of property bloquegupx.
     *
     */
    public void setBloquegupx(double bloquegupx) {
        this.bloquegupx = bloquegupx;
    }
    
    /** Getter for property bloquegupy.
     * @return Value of property bloquegupy.
     *
     */
    public double getBloquegupy() {
        return bloquegupy;
    }
    
    /** Setter for property bloquegupy.
     * @param bloquegupy New value of property bloquegupy.
     *
     */
    public void setBloquegupy(double bloquegupy) {
        this.bloquegupy = bloquegupy;
    }
    
    /** Getter for property bloquedupx.
     * @return Value of property bloquedupx.
     *
     */
    public double getBloquedupx() {
        return bloquedupx;
    }
    
    /** Setter for property bloquedupx.
     * @param bloquedupx New value of property bloquedupx.
     *
     */
    public void setBloquedupx(double bloquedupx) {
        this.bloquedupx = bloquedupx;
    }
    
    /** Getter for property bloquedupy.
     * @return Value of property bloquedupy.
     *
     */
    public double getBloquedupy() {
        return bloquedupy;
    }
    
    /** Setter for property bloquedupy.
     * @param bloquedupy New value of property bloquedupy.
     *
     */
    public void setBloquedupy(double bloquedupy) {
        this.bloquedupy = bloquedupy;
    }
    
    /** Getter for property corpx.
     * @return Value of property corpx.
     *
     */
    public double getCorpx() {
        return corpx;
    }
    
    /** Setter for property corpx.
     * @param corpx New value of property corpx.
     *
     */
    public void setCorpx(double corpx) {
        this.corpx = corpx;
    }
    
    /** Getter for property corpy.
     * @return Value of property corpy.
     *
     */
    public double getCorpy() {
        return corpy;
    }
    
    /** Setter for property corpy.
     * @param corpy New value of property corpy.
     *
     */
    public void setCorpy(double corpy) {
        this.corpy = corpy;
    }
    
    /** Getter for property topmargin.
     * @return Value of property topmargin.
     *
     */
    public double getTopmargin() {
        return topmargin;
    }
    
    /** Setter for property topmargin.
     * @param topmargin New value of property topmargin.
     *
     */
    public void setTopmargin(double topmargin) {
        this.topmargin = topmargin;
    }
    
    /** Getter for property bottomargin.
     * @return Value of property bottomargin.
     *
     */
    public double getBottomargin() {
        return bottomargin;
    }
    
    /** Setter for property bottomargin.
     * @param bottomargin New value of property bottomargin.
     *
     */
    public void setBottomargin(double bottomargin) {
        this.bottomargin = bottomargin;
    }
    
    /** Getter for property factfourn.
     * @return Value of property factfourn.
     *
     */
    public int getFactfourn() {
        return factfourn;
    }
    
    /** Setter for property factfourn.
     * @param factfourn New value of property factfourn.
     *
     */
    public void setFactfourn(int factfourn) {
        this.factfourn = factfourn;
    }
    
    int confcleunik;
    int eecleunik;
    double bdc_bdc_x;
    double bdc_bdc_y;
    double bdc_bdc_cli_x;
    double bdc_bdc_cli_y;
    int bdc_bloc_g;
    int bdc_bloc_d;
    int nbr_lettre_liste;
    String bdc_texte;
    float fraisDossierTva;
    double fraisDossier;
    int fraisdossiertvacle;
    int multibureaux=0;
    int caisseparutilisateur=0;
    int annullfees;
    int fraistvainclus;
    int compteFrais;
    int compteTaxLoc;
    int compteTaxDest;
    int compteTaxcomp;
    int compteTaxFees;
    int compteTaxRemise;
    int factureauto;
    int facturenumber;
    int bdcauto;
    int bdcnumber;
    int ncauto;
    int nccnumber;
    int nombrebloc;
    int factureresum;
    int affichebloclegalfacture;
    int passagerVisibleBDC;
    int detailProduitVisibleBDC;
    int detailPrixVisibleBDC;
    int passagerVisibleFact;
    int detailProduitVisibleFact;
    int detailPrixVisibleFact;
    double bloquegupx;
    double bloquegupy;
    double bloquedupx;
    double bloquedupy;
    double corpx;
    double corpy;
    double topmargin;
    double bottomargin;
    int factfourn;
}
