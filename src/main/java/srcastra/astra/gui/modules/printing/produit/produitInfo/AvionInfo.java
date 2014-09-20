/*
 * BrochureInfo.java
 *
 * Created on 21 mars 2003, 10:43
 */

package srcastra.astra.gui.modules.printing.produit.produitInfo;
import java.sql.*;
/**
 *
 * @author  thomas
 */
public class AvionInfo extends AbstractInfoProduit implements java.io.Serializable{
    
    /** Creates a new instance of BrochureInfo */
    int iddest;
    int idcompagnie;
    public AvionInfo(int urcleunik, int lmcleunik,int idcompagnie,  int iddest) {
        super(urcleunik,lmcleunik);
        this.iddest=iddest;
        this.idcompagnie=idcompagnie;
       // this.idaccomadation=idaccomadation;
       // this.emb=emb; 
       // this.deb=deb;
    }
    public void initMe(Connection con) throws SQLException{
        PreparedStatement pstmt=con.prepareStatement("SELECT coe_nom  FROM compagnie WHERE coe_cleunik  =?");
      //  pstmt.setInt(1,lmcleunik);
        pstmt.setInt(1,idcompagnie);
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
          compagnie=result.getString(1);           
        }
    /*    pstmt=con.prepareStatement("SELECT  trattraduction FROM traductiontransport  WHERE lmcleunik=? AND trtcleunik=?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,idtransport);
        result=pstmt.executeQuery();
        while(result.next()){
           transport=result.getString(1);           
        ¨*/
       /* pstmt=con.prepareStatement("SELECT ltintitule FROM traductionlogement  WHERE lmcleunik=? AND  ltcleunik =?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,idaccomadation);
        result=pstmt.executeQuery();
        while(result.next()){
           accomodation=result.getString(1);           
        }
        pstmt=con.prepareStatement("SELECT tremb_traduction FROM traduction_embarqdebarq WHERE lmcleunik=? AND emb_cleunik =?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,deb);
        result=pstmt.executeQuery();
        while(result.next()){
           debarquement=result.getString(1);           
        }*/
    }
    
    
    /** Getter for property destination.
     * @return Value of property destination.
     */
    public java.lang.String getDestination() {
        return destination;
    }
    
    /** Setter for property destination.
     * @param destination New value of property destination.
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }
    
    /** Getter for property transport.
     * @return Value of property transport.
     */
    public java.lang.String getTransport() {
        return transport;
    }
    
    /** Setter for property transport.
     * @param transport New value of property transport.
     */
    public void setTransport(java.lang.String transport) {
        this.transport = transport;
    }
    
    /** Getter for property accomodation.
     * @return Value of property accomodation.
     */
    public java.lang.String getAccomodation() {
        return accomodation;
    }
    
    /** Setter for property accomodation.
     * @param accomodation New value of property accomodation.
     */
    public void setAccomodation(java.lang.String accomodation) {
        this.accomodation = accomodation;
    }
    
    /** Getter for property compagnie.
     * @return Value of property compagnie.
     */
    public java.lang.String getCompagnie() {
        return compagnie;
    }
    
    /** Setter for property compagnie.
     * @param compagnie New value of property compagnie.
     */
    public void setCompagnie(java.lang.String compagnie) {
        this.compagnie = compagnie;
    }
    
    String accomodation;
    String transport;
    String destination;
    String compagnie;
    
    
}
