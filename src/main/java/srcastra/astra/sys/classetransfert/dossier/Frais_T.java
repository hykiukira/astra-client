/* * Frais_T.java * * Created on 13 novembre 2002, 16:32 */package srcastra.astra.sys.classetransfert.dossier;import srcastra.astra.sys.classetransfert.utils.*;import java.util.Hashtable;import java.util.ArrayList;import srcastra.astra.sys.classetransfert.Grpdecision_T;import srcastra.astra.gui.test.*;import srcastra.astra.sys.classetransfert.dossier.brochure.DescriptionLogement_T;import java.sql.Connection;import java.sql.ResultSet;import java.sql.PreparedStatement;import java.sql.SQLException;import srcastra.astra.sys .classetransfert.Loginusers_T;import srcastra.astra.sys.classetransfert.dossier.Dossier_T;import srcastra.astra.sys.rmi.astrainterface;import srcastra.astra.sys.produit.SupplementReduction;import srcastra.astra.sys.rmi.groupe_dec.*;/** * * @author  Thomas */public class Frais_T extends srcastra.astra.sys.classetransfert.dossier.produit_T implements java.io.Serializable,Cloneable,InterfaceProduit,InterfaceIndivProduit{    /** Creates a new instance of Frais_T */    public Frais_T() {        super.setTypeDeProduitCleunik(super.FRAIS);        setPax(1);        setQua(1);        setPrct(100f);        }    /** Getter for property date_annul.     * @return Value of property date_annul.     */    public srcastra.astra.sys.classetransfert.utils.Date getDate_annul() {        return date_annul;    }    /** Setter for property date_annul.     * @param date_annul New value of property date_annul.     */    public void setDate_annul(srcastra.astra.sys.classetransfert.utils.Date date_annul) {        this.date_annul = date_annul;    }    /** Getter for property facture.     * @return Value of property facture.     */    public int getFacture() {        return facture;    }    /** Setter for property facture.     * @param facture New value of property facture.     */    public void setFacture(int facture) {        this.facture = facture;    }    /** Getter for property frais_annulation.     * @return Value of property frais_annulation.     */    public double getFrais_annulation() {        return frais_annulation;    }    /** Setter for property frais_annulation.     * @param frais_annulation New value of property frais_annulation.     */    public void setFrais_annulation(double frais_annulation) {     // super.setValeur_tot(frais_annulation);       // super.setValeur_tot_tva_inc(frais_annulation);        setAt_val_vente(frais_annulation);    }    /** Getter for property libell�.     * @return Value of property libell�.     */    public java.lang.String getLibelle() {        return libelle;    }    /** Setter for property libell�.     * @param libell� New value of property libell�.     */    public void setLibelle(java.lang.String libelle) {        this.libelle = libelle;    }    /** Getter for property montant_annul.     * @return Value of property montant_annul.     */    public double getMontant_annul() {        return montant_annul;    }    /** Setter for property montant_annul.     * @param montant_annul New value of property montant_annul.     */    public void setMontant_annul(double montant_annul) {        this.montant_annul = montant_annul;    }    /** Getter for property note_cr�dit.     * @return Value of property note_cr�dit.     */    public int getNote_cr�dit() {        return note_cr�dit;    }    /** Setter for property note_cr�dit.     * @param note_cr�dit New value of property note_cr�dit.     */    public void setNote_cr�dit(int note_cr�dit) {        this.note_cr�dit = note_cr�dit;    }    /** Getter for property percent.     * @return Value of property percent.     */    public float getPercent() {        return percent;    }    /** Setter for property percent.     * @param percent New value of property percent.     */    public void setPercent(float percent) {        this.percent = percent;    }           /** Getter for property cleunik.     * @return Value of property cleunik.     */    public long getCleunik() {        return cleunik;    }        /** Setter for property cleunik.     * @param cleunik New value of property cleunik.     */    public void setCleunik(long cleunik) {        this.cleunik = cleunik;        setAt_cleunik(cleunik);    }   // public void prepareProduitAffichage(){     // this.produitaff=new  srcastra.astra.sys.classetransfert.dossier.ProduitAffichage(this,"B","","","","",this.getLibelleCompta(),this.getValeur_tot(),1,1,100, "ok",this.getValeur_tot(),10,10,getSup_tva(),"",this.getValeur_tot_tva_inc());    // }        /** Getter for property produitaff.     * @return Value of property produitaff.     */    public srcastra.astra.sys.classetransfert.dossier.ProduitAffichage getProduitaff() {        return produitaffichage;    }        /** Setter for property produitaff.     * @param produitaff New value of property produitaff.     */    public void setProduitaff(srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff) {        this.produitaff = produitaff;    }        public void annulMe(Connection con, PreparedStatement pstmt) {    }        public void chargeDescriptif(Connection con, PreparedStatement pstmt, Dossier_T tmpDossier) throws SQLException {    }    public void chargeMe(Loginusers_T currentuser, astrainterface serveur, Dossier_T dossier, Connection con, double cledossier, PreparedStatement pstmt) throws SQLException, java.rmi.RemoteException {       // SELECT f.frd_cleunik,fdr_dateannul, f.fdr_montantannul, f.fdr_pourcent, f.fdr_nc, f.fdr_facture, f.fdr_libell�        //, f.longtime,h.hevaleur, h.hevaleurbase, h.hevaleurtva FROM fraisdossier f,historique2 h WHERE h.lignecleunik=f.frd_cleunik         //AND h.sn_cleunik=0 AND h.ctprocleunik=20 AND f.dr_cleunik=? AND f.annuler=0 AND h.hedossiercourant='O'");                pstmt=con.prepareStatement("SELECT f.fdr_cleunik,fdr_dateannul, f.fdr_montantannul, f.fdr_pourcent, f.fdr_nc, f.fdr_facture, f.fdr_libell�, f.longtime,h.hevaleur, h.hevaleurbase, h.hevaleurtva,h.hecodetva ,f.fraisdossier,f.tva_cleunik  FROM fraisdossier f,historique2 h WHERE h.lignecleunik=f.fdr_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=9 AND f.dr_cleunik=? AND f.annuler=0 AND h.hedossiercourant='O'");                 pstmt.setInt(1,dossier.getDrcleunik());                ResultSet result=pstmt.executeQuery();                result.beforeFirst();                while(result.next()){                 Frais_T frais=new Frais_T();                frais.setCleunik(result.getLong(1));                frais.setAt_cleunik(result.getLong(1));                frais.setDate_annul(new Date(result.getString(2)));                frais.setMontant_annul(result.getDouble(3));                frais.setPercent(result.getFloat(4));                frais.setNote_cr�dit(result.getInt(5));                frais.setFacture(result.getInt(6));                frais.setLibelleCompta(result.getString(7));                frais.setLongtime(result.getLong(8));                frais.setValeur_tot_tva_inc(-result.getDouble(9));                frais.setFrais_annulation(-result.getDouble(10));                frais.setValeur_tot(-result.getDouble(10));                frais.setMontant_tva(-result.getDouble(11));                   frais.setSup_tva(result.getFloat(12));                frais.setTva_cleunik(result.getInt(13));                     frais.setTypeDeProduitCleunik(produit_T.FRAIS);                Grpdecision_T own;                ManageGroupeDec  tmp=(ManageGroupeDec)serveur.renvGrpDecRmiObject(currentuser.getUrcleunik());//new ManageGroupeDec("",null);                own=tmp.selectProd(frais.getAt_cleunik(),frais.getTypeDeProduitCleunik(),con,0);                frais.setGroupdec(own);                int j=result.getInt(13);              //  if(j==0)                    frais.setFraisDossier(j);               // else               //     frais.setFraisDossier(true);                dossier.addFraisDossier(frais);                }    }        public long insertDescriptif(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {        return 0;    }        public long insertOnlyme(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {      String requeteSql=" INSERT INTO fraisdossier (fdr_dateannul, fdr_montantannul, fdr_pourcent, fdr_nc, fdr_facture, fdr_libell�, dr_cleunik, longtime,fraisdossier,tva_cleunik) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";      String date;      pstmt=con.prepareStatement(requeteSql);      if(this.getDate_annul()==null) date="0000-00-00 00:00:00"; else date=this.getDate_annul().toString();          pstmt.setString(1,date);        pstmt.setDouble(2,this.getMontant_annul());        pstmt.setFloat(3,this.getPercent());        pstmt.setInt(4,this.getNote_cr�dit());        pstmt.setInt(5,this.getFacture());        pstmt.setString(6,this.getLibelleCompta());        pstmt.setLong(7,new Double(cledossier).longValue());        pstmt.setLong(8,this.getLongtime());        //if(this.isFraisDossier())            pstmt.setLong(9,this.isFraisDossier());        //else        //    pstmt.setLong(9,0);        pstmt.setInt(10,this.getTva_cleunik());        pstmt.execute();        this.setCleunik(getId(con));           return this.getCleunik();    }        public void modifyDescriptif(Connection con, PreparedStatement pstmt) throws SQLException {    } /*public void prepareAffichage() {       // String tmp=getGroupdec().getGntvainclusvente()==1?"INC":"N.INC";        produitaffichage=new srcastra.astra.sys.classetransfert.dossier.ProduitAffichage(this, "B", this.getFrnom(), this.getGroupe_produit_nom(), "","",this.getLibelleCompta(),                                                                                         this.getValeur_tot(),this.getQua(),this.getPax(),this.getPrct(),"ok",this.getValeur_tot(),                                                                                         this.getAt_cleunik(),this.getTypeDeProduitCleunik(),                                                                                          getSup_tva(),"",getValeur_tot_tva_inc());       }*/        public void modifyOnlyMe(Connection con, int cledossier, PreparedStatement pstmt) throws SQLException {    }    /** Getter for property fraisDossier.     * @return Value of property fraisDossier.     */    public int isFraisDossier() {        return fraisDossier;    }        /** Setter for property fraisDossier.     * @param fraisDossier New value of property fraisDossier.     */    public void setFraisDossier(int fraisDossier) {        this.fraisDossier = fraisDossier;    }            /** Getter for property tva_cleunik.     * @return Value of property tva_cleunik.     */    public int getTva_cleunik() {        return tva_cleunik;    }        /** Setter for property tva_cleunik.     * @param tva_cleunik New value of property tva_cleunik.     */    public void setTva_cleunik(int tva_cleunik) {        this.tva_cleunik = tva_cleunik;    }        public String getPnr() {        return "";    }           public java.util.ArrayList getDestinationArray() {        return null;    }        long cleunik;srcastra.astra.sys.classetransfert.utils.Date date_annul;   double montant_annul;float percent;double frais_annulation;int note_cr�dit;int facture;String libelle;int fraisDossier;transient srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaff;int tva_cleunik;}