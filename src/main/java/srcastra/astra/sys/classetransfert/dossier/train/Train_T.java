/*







 * Train_T.java







 *







 * Created on 9 décembre 2002, 12:57







 */















package srcastra.astra.sys.classetransfert.dossier.train;







import srcastra.astra.sys.classetransfert.utils.*;







import java.util.Hashtable;







import java.util.ArrayList;







import srcastra.astra.sys.classetransfert.Grpdecision_T;







import srcastra.astra.gui.test.*;







import srcastra.astra.sys.classetransfert.dossier.brochure.DescriptionLogement_T;







import java.sql.Connection;







import java.sql.ResultSet;







import java.sql.PreparedStatement;







import java.sql.SQLException;







import srcastra.astra.sys .classetransfert.Loginusers_T;







import srcastra.astra.sys.classetransfert.dossier.Dossier_T;







import srcastra.astra.sys.rmi.astrainterface;







import srcastra.astra.sys.produit.SupplementReduction;



import srcastra.astra.sys.rmi.groupe_dec.*;

import srcastra.astra.sys.classetransfert.dossier.*;















/**







 *







 * @author  Sébastien







 */







public class Train_T extends srcastra.astra.sys.classetransfert.dossier.produit_T implements java.io.Serializable,







                                    Cloneable,srcastra.astra.sys.rmi.DossierSql,srcastra.astra.sys.classetransfert.dossier.InterfaceIndivProduit,ProduitSynthese {







    







    















    private long trn_cleUnik;







    private String trn_nBillet;







    private Date trn_dateEmission;







    private Date trn_dateDepart;







    private Date trn_dateRetour;







    private String trn_memo;







    private float trn_montant;







    















  //  public transient srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaffichage;







    private Date datetimemodif;







    private Date datetimecrea;







 







                                        







                                        







 /*   public void prepareAffichage() {







        String tmp=getGroupdec().getGntvainclusvente()==1?"INC":"N.INC";







        produitaffichage=new srcastra.astra.sys.classetransfert.dossier.ProduitAffichage(this, "B", this.getFrnom(), this.getGroupe_produit_nom(), "","",this.getTrn_memo(), 







                                                                                         new Float(trn_montant).floatValue(),getQua(),getPax(),getPrct(),"ok",this.getValeur_tot(),







                                                                                         this.getAt_cleunik(),this.getTypeDeProduitCleunik(), 







                                                                                         getGroupdec().getValeurGenFloat1(),tmp,getValeur_tot_tva_inc());   







    }*/







    







    public long getAt_cleunik() {







        return trn_cleUnik;







    }







    







    public void setAt_cleunik(long atCleunik) {







        trn_cleUnik = atCleunik;







        super.setAt_cleunik(atCleunik);







    }







    







    public Object clone()throws CloneNotSupportedException{







       return super.clone(); 







    }







                                        







    /** Creates a new instance of Train_T */







    public Train_T() {







        setPax(1);







        setQua(1);







        setPrct(100);







        setTypeDeProduitCleunik(super.TR);







    }







    







    public void annulMe(Connection con, PreparedStatement pstmt) throws SQLException{

         pstmt=con.prepareStatement("UPDATE train set annuler=1  WHERE trn_cleunik=?");

         pstmt.setLong(1,getTrn_cleUnik());

         pstmt.execute();

    }







    







    public void chargeMe(Loginusers_T currentuser, astrainterface serveur, Dossier_T dossier, Connection con, double cledossier, PreparedStatement pstmt) throws SQLException, java.rmi.RemoteException {
            // "SELECT t.trn_cleunik, t.trn_billet , t.trn_date_emission , t.trn_date_depart , t.trn_date_retour ,
            //t.trn_statut , t.trn_memo , t.trn_montant , t.trn_datetimemodif , t.trn_datetimecrea , t.longtime ,
            //t.dr_cleunik , t.frgtcleunik ,  t.pourcent , t.quantite , t.pax, h.hevaleur, h.hevaleurbase, h.hevaleurtva FROM 
            //train t,historique2 h WHERE h.lignecleunik=t.trn_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=5 AND t.dr_cleunik=? AND t.annuler=0 AND h.hedossiercourant='O'"
                pstmt=con.prepareStatement("SELECT t.trn_cleunik, t.trn_billet , t.trn_date_emission , t.trn_date_depart , t.trn_date_retour , t.trn_statut , t.trn_memo , t.trn_montant , t.trn_datetimemodif , t.trn_datetimecrea , t.longtime , t.dr_cleunik , t.frgtcleunik ,  t.pourcent , t.quantite , t.pax, h.hevaleur, h.hevaleurbase, h.hevaleurtva,h.helibelle FROM train t,historique2 h WHERE h.lignecleunik=t.trn_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=7 AND t.dr_cleunik=? AND t.annuler=0 AND h.hedossiercourant='O'"); 
                pstmt.setInt(1,dossier.getDrcleunik());
                ResultSet result=pstmt.executeQuery();
                result.beforeFirst();
                while(result.next()){ 
                Train_T train=new Train_T();
                train.setTrn_cleUnik(result.getLong(1));
                train.setTrn_nBillet(result.getString(2));
                train.setTrn_dateEmission(new Date(result.getString(3)));
                train.setTrn_dateDepart(new Date(result.getString(4)));
                train.setTrn_dateRetour(new Date(result.getString(5)));
                train.setStatut(result.getInt(6));
                train.setTrn_memo(result.getString(7));
                train.setAt_val_vente(result.getDouble(8));
                train.setDatetimemodif(new Date(result.getString(9)));
                train.setDatetimecrea(new Date(result.getString(10)));
                train.setLongtime(result.getLong(11));
                train.setFrgtcleunik(result.getInt(13));
                train.setPrct(result.getFloat(14));
                train.setPax(result.getInt(16));
                train.setQua(result.getInt(15));
                train.setValeur_tot_tva_inc(-result.getDouble(17));                
                train.setValeur_tot(-result.getDouble(18));
                train.setMontant_tva(-result.getDouble(19));    
                train.setLibelleCompta(result.getString(20));
                GrpRetValue retG=GrpProduitGestion.filGrpDecToProd(serveur,train,con,currentuser.getUrcleunik());
                train.setGroupdecBase(retG.getBase());
                train.setGroupdec(retG.getOwn());
                pstmt=con.prepareStatement(CHARGE_GRP_PROD);            
                ProduitInfoComplementaire.getInfo(ProduitInfoComplementaire.TR,ProduitInfoComplementaire.TR_FULL,produit_T.TR,currentuser.getLangage(),train,con);
                train.setDoc(pstmt,con);
                SupplementReduction.chargeSupreduc(train,con,pstmt,serveur,currentuser.getUrcleunik()); 
                dossier.addTrain(train);
                }
    }







    







    public long insertOnlyme(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {







        //"INSERT INTO train (trn_billet , trn_date_emission , trn_date_depart , trn_date_retour , trn_statut , 







        //trn_memo , trn_montant , trn_datetimemodif , trn_datetimecrea , longtime , dr_cleunik , frgtcleunik ,  pourcent , 







        //quantite , pax ) VALUES (? ,? ,? , ?, ? , ? , ? ,NOW(),NOW(),? ,? ,? ,? ,? ,? );"; );







          String date;







          pstmt=con.prepareStatement("INSERT INTO train (trn_billet , trn_date_emission , trn_date_depart , trn_date_retour , trn_statut , trn_memo , trn_montant , trn_datetimemodif , trn_datetimecrea , longtime , dr_cleunik , frgtcleunik ,  pourcent , quantite , pax ) VALUES (? ,? ,? , ?, ? , ? , ? ,NOW(),NOW(),? ,? ,? ,? ,? ,? );" );







          pstmt.setString(1,this.getTrn_nBillet());







          if(this.getTrn_dateEmission()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateEmission().toString();







            pstmt.setString(2,date);







          if(this.getTrn_dateDepart()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateDepart().toString();  







            pstmt.setString(3,date);







          if(this.getTrn_dateRetour()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateRetour().toString();  







            pstmt.setString(4,date);          







          pstmt.setInt(5,getStatut());







          pstmt.setString(6,getTrn_memo());







          pstmt.setDouble(7,getAt_val_vente());







          pstmt.setLong(8,getLongtime());







          pstmt.setLong(9,new Double(cledossier).longValue());







          pstmt.setInt(10,getFrgtcleunik());







          pstmt.setFloat(11,getPrct());







          pstmt.setInt(12,getQua());







          pstmt.setInt(13,getPax());







          pstmt.execute();







          this.setTrn_cleUnik(getId(con));   







          return this.getTrn_cleUnik();







    }







    







    public void modifyOnlyMe(Connection con, int cledossier, PreparedStatement pstmt) throws SQLException {







          String date;







          pstmt=con.prepareStatement("UPDATE train set trn_billet=? , trn_date_emission=? , trn_date_depart=? , trn_date_retour=? , trn_statut=? , trn_memo=? , trn_montant=? , trn_datetimemodif=NOW() , frgtcleunik=? ,  pourcent=? , quantite=? , pax=? WHERE trn_cleunik=?");







          pstmt.setString(1,this.getTrn_nBillet());







          if(this.getTrn_dateEmission()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateEmission().toString();







            pstmt.setString(2,date);







          if(this.getTrn_dateDepart()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateDepart().toString();  







            pstmt.setString(3,date);







          if(this.getTrn_dateRetour()==null) date="0000-00-00 00:00:00"; else date=this.getTrn_dateRetour().toString();  







            pstmt.setString(4,date);          







          pstmt.setInt(5,getStatut());







          pstmt.setString(6,getTrn_memo());







          pstmt.setDouble(7,getAt_val_vente());







          pstmt.setInt(8,getFrgtcleunik());







          pstmt.setFloat(9,getPrct());







          pstmt.setInt(10,getQua());







          pstmt.setInt(11,getPax());







          pstmt.setLong(12,getTrn_cleUnik());







          pstmt.execute();







    }







    







    /** Getter for property datetimecrea.







     * @return Value of property datetimecrea.







     */







    public srcastra.astra.sys.classetransfert.utils.Date getDatetimecrea() {







        return datetimecrea;







    }







    







    /** Setter for property datetimecrea.







     * @param datetimecrea New value of property datetimecrea.







     */







    public void setDatetimecrea(srcastra.astra.sys.classetransfert.utils.Date datetimecrea) {







        this.datetimecrea = datetimecrea;







    }







    







    /** Getter for property datetimemodif.







     * @return Value of property datetimemodif.







     */







    public srcastra.astra.sys.classetransfert.utils.Date getDatetimemodif() {







        return datetimemodif;







    }







    







    /** Setter for property datetimemodif.







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







   /* public srcastra.astra.sys.classetransfert.dossier.ProduitAffichage getProduitaffichage() {







        return produitaffichage;







    }







    







    public void setProduitaffichage(srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaffichage) {







        this.produitaffichage = produitaffichage;







    }*/







    







    /** Getter for property trn_cleUnik.







     * @return Value of property trn_cleUnik.







     */







    public long getTrn_cleUnik() {







        return trn_cleUnik;







    }







    







    /** Setter for property trn_cleUnik.







     * @param trn_cleUnik New value of property trn_cleUnik.







     */







    public void setTrn_cleUnik(long trn_cleUnik) {







       setAt_cleunik(trn_cleUnik);







    }







    







    /** Getter for property trn_dateDepart.







     * @return Value of property trn_dateDepart.







     */







    public srcastra.astra.sys.classetransfert.utils.Date getTrn_dateDepart() {







        return trn_dateDepart;







    }







    







    /** Setter for property trn_dateDepart.







     * @param trn_dateDepart New value of property trn_dateDepart.







     */







    public void setTrn_dateDepart(srcastra.astra.sys.classetransfert.utils.Date trn_dateDepart) {







        this.trn_dateDepart = trn_dateDepart;







    }







    







    /** Getter for property trn_dateEmission.







     * @return Value of property trn_dateEmission.







     */







    public srcastra.astra.sys.classetransfert.utils.Date getTrn_dateEmission() {







        return trn_dateEmission;







    }







    







    /** Setter for property trn_dateEmission.







     * @param trn_dateEmission New value of property trn_dateEmission.







     */







    public void setTrn_dateEmission(srcastra.astra.sys.classetransfert.utils.Date trn_dateEmission) {







        this.trn_dateEmission = trn_dateEmission;







    }







    







    /** Getter for property trn_dateRetour.







     * @return Value of property trn_dateRetour.







     */







    public srcastra.astra.sys.classetransfert.utils.Date getTrn_dateRetour() {







        return trn_dateRetour;







    }







    







    /** Setter for property trn_dateRetour.







     * @param trn_dateRetour New value of property trn_dateRetour.







     */







    public void setTrn_dateRetour(srcastra.astra.sys.classetransfert.utils.Date trn_dateRetour) {







        this.trn_dateRetour = trn_dateRetour;







    }







    







    /** Getter for property trn_memo.







     * @return Value of property trn_memo.







     */







    public java.lang.String getTrn_memo() {







        return trn_memo;







    }







    







    /** Setter for property trn_memo.







     * @param trn_memo New value of property trn_memo.







     */







    public void setTrn_memo(java.lang.String trn_memo) {







        this.trn_memo = trn_memo;







    }







    







    /** Getter for property trn_nBillet.







     * @return Value of property trn_nBillet.







     */







    public java.lang.String getTrn_nBillet() {







        return trn_nBillet;







    }







    







    /** Setter for property trn_nBillet.







     * @param trn_nBillet New value of property trn_nBillet.







     */







    public void setTrn_nBillet(java.lang.String trn_nBillet) {







        this.trn_nBillet = trn_nBillet;







    }







    







    /** Getter for property trn_statut.







     * @return Value of property trn_statut.







     */







    







    /** Getter for property trn_montant.







     * @return Value of property trn_montant.







     */







    public float getTrn_montant() {







        return trn_montant;







    }







    







    /** Setter for property trn_montant.







     * @param trn_montant New value of property trn_montant.







     */







    public void setTrn_montant(float trn_montant) {







        this.trn_montant = trn_montant;







        setAt_val_vente(trn_montant);







    }







    







    public void chargeDescriptif(Connection con, PreparedStatement pstmt, Dossier_T tmpDossier) throws SQLException {







    }







    







    public long insertDescriptif(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {







        return 0;







    }







    







    public void modifyDescriptif(Connection con, PreparedStatement pstmt) throws SQLException {







    }



    public srcastra.astra.sys.classetransfert.utils.Date getDateDep() {

        return this.trn_dateDepart;

    }    



    public String getDestination() {

        return "";

    }    



    public String getLogement() {

        return "";

    }    

    

    public String getPnr() {

        return this.trn_nBillet;

    }    

    public java.util.ArrayList getDestinationArray() {
        return null;
    }    






}







