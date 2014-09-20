/*
 * Car_T.java
 *
 * Created on 12 mai 2003, 14:43
 */

package srcastra.astra.sys.classetransfert.dossier.car;
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
import srcastra.astra.sys.rmi.groupe_dec.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.produit.*;
/**
 *
 * @author  Thomas
 */
public class Car_T extends srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T{
    
    /** Creates a new instance of Car_T */
    public Car_T() {
        super(true);
         setTypeDeProduitCleunik(super.CAR);
    }
    
    /** Getter for property vl_statut.
     *
     *
     *
     * @return Value of property vl_statut.
     *
     *
     *
     */
    public void chargeDescriptif(Connection con, PreparedStatement pstmt, Dossier_T tmpDossier) throws SQLException {
        
        
        
    }
    
    public void chargeMe(Loginusers_T currentuser, astrainterface serveur, Dossier_T dossier, Connection con, double cledossier, PreparedStatement pstmt) throws SQLException, java.rmi.RemoteException {        
        String date=null;        
        pstmt=con.prepareStatement("SELECT v.vl_cleunik,v.vl_pnr,v.vl_date_depart,v.vl_date_retour,v.vl_statut,v.vl_montant,v.vl_memo,v.vl_datetimemodif,v.vl_datetimecrea,v.longtime,v.dr_cleunik,v.frgtcleunik,v.pourcent,v.pax,v.quantite, h.hevaleur, h.hevaleurbase, h.hevaleurtva,h.helibelle FROM  car v,historique2 h WHERE h.lignecleunik=v.vl_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=10 AND v.dr_cleunik=? AND v.annuler=0 AND h.hedossiercourant='O'");
        pstmt.setInt(1,dossier.getDrcleunik());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
            Car_T voiture=new Car_T();
            voiture.setVl_cleUnik(result.getLong(1));
            voiture.setVl_pnr(result.getString(2));
            voiture.setVl_dateDepart(new Date(result.getString(3)));
            voiture.setVl_dateRetour(new Date(result.getString(4)));
            voiture.setStatut(result.getInt(5));
            voiture.setAt_val_vente(result.getFloat(6));
            voiture.setVl_memo(result.getString(7));
            voiture.setDatetimecrea(new Date(result.getString(8)));
            voiture.setDatetimemodif(new Date(result.getString(9)));
            voiture.setLongtime(result.getLong(10));
            voiture.setFrgtcleunik(result.getInt(12));
            voiture.setPrct(result.getFloat(13));
            voiture.setPax(result.getInt(14));
            voiture.setQua(result.getInt(15));
            voiture.setValeur_tot_tva_inc(-result.getDouble(16));
            voiture.setValeur_tot(-result.getDouble(17));
            voiture.setMontant_tva(-result.getDouble(18));
            voiture.setLibelleCompta(result.getString(19));
            GrpRetValue retG=GrpProduitGestion.filGrpDecToProd(serveur,voiture,con,currentuser.getUrcleunik());
            voiture.setGroupdecBase(retG.getBase());
            voiture.setGroupdec(retG.getOwn());
            ProduitInfoComplementaire.getInfo(ProduitInfoComplementaire.CA,ProduitInfoComplementaire.CA_FULL,produit_T.CAR,currentuser.getLangage(),voiture,con);
            voiture.setDoc(pstmt,con);
            SupplementReduction.chargeSupreduc(voiture,con,pstmt,serveur,currentuser.getUrcleunik());
            dossier.addCar(voiture);
        }
    }
    
    public void annulMe(Connection con, PreparedStatement pstmt) throws SQLException {
        pstmt=con.prepareStatement("UPDATE car set annuler=1 WHERE vl_cleunik=?;");
        pstmt.setLong(1,this.getVl_cleUnik()); 
        pstmt.execute();
        
        
    }
    
    public long insertOnlyme(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {
        String date=null;
        pstmt=con.prepareStatement("INSERT INTO car (vl_pnr,vl_date_depart,vl_date_retour,vl_statut,vl_montant,vl_memo,vl_datetimemodif,vl_datetimecrea,longtime,dr_cleunik,frgtcleunik,pourcent,pax,quantite) VALUES (? ,? ,? ,? ,? ,? ,NOW(), NOW(),? ,? ,? ,? ,? ,?  );");
        pstmt.setString(1,this.getVl_pnr());
        if(getVl_dateDepart()==null) date="0000-00-00 00:00:00"; else date=getVl_dateDepart().toString();
        pstmt.setString(2,date);
        if(getVl_dateRetour()==null) date="0000-00-00 00:00:00"; else date=getVl_dateRetour().toString();
        pstmt.setString(3,date);
        pstmt.setInt(4,this.getStatut());
        pstmt.setDouble(5,this.getAt_val_vente());
        pstmt.setString(6,this.getVl_memo());
        pstmt.setLong(7,getLongtime());
        pstmt.setLong(8,new Double(cledossier).longValue());
        pstmt.setInt(9,getFrgtcleunik());
        pstmt.setFloat(10,this.getPrct());
        pstmt.setInt(11,this.getPax());
        pstmt.setInt(12,this.getQua());
        pstmt.execute();
        this.setVl_cleUnik(getId(con));
        return this.getVl_cleUnik();
    }
    
    public long insertDescriptif(Connection con, double cledossier, PreparedStatement pstmt) throws SQLException {      
        return 0;
    }
    
    public void modifyDescriptif(Connection con, PreparedStatement pstmt) throws SQLException {
        
        
        
    }
    
    public void modifyOnlyMe(Connection con, int cledossier, PreparedStatement pstmt) throws SQLException {
        String date=null;
        pstmt=con.prepareStatement("UPDATE car set vl_pnr=?,vl_date_depart=?,vl_date_retour=?,vl_statut=?,vl_montant=?,vl_memo=?,vl_datetimemodif=NOW(),frgtcleunik=?,pourcent=?,pax=?,quantite=? WHERE vl_cleunik=?;");
        pstmt.setString(1,this.getVl_pnr());
        if(getVl_dateDepart()==null) date="0000-00-00 00:00:00"; else date=getVl_dateDepart().toString();
        pstmt.setString(2,date);
        if(getVl_dateRetour()==null) date="0000-00-00 00:00:00"; else date=getVl_dateRetour().toString();
        pstmt.setString(3,date);
        pstmt.setInt(4,this.getStatut());
        pstmt.setDouble(5,this.getAt_val_vente());
        pstmt.setString(6,this.getVl_memo());
        pstmt.setInt(7,getFrgtcleunik());
        pstmt.setFloat(8,this.getPrct());
        pstmt.setInt(9,this.getPax());
        pstmt.setInt(10,this.getQua());
        pstmt.setLong(11,this.getVl_cleUnik());
        pstmt.execute();        
    }
}
