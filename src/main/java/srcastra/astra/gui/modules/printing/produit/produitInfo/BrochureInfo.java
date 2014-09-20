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
public class BrochureInfo  extends AbstractInfoProduit implements java.io.Serializable{
    
    /** Creates a new instance of BrochureInfo */
  
    int idtransport;
    int iddest;
    int emb;  
    int deb;
    int log;
    long bro_cleunik;
    public BrochureInfo(int urcleunik,int lmcleunik,int idtransport,int iddest,int emb,int deb,int logement,long bro_cleunik) {
        super(urcleunik,lmcleunik);
        this.idtransport=idtransport;
        this.iddest=iddest;
        this.emb=emb; 
        this.deb=deb;
        this.log=logement;
        this.bro_cleunik=bro_cleunik;
    }
    public void initMe(Connection con) throws SQLException{
        PreparedStatement pstmt=con.prepareStatement("SELECT tn_traduction FROM traduction_destination WHERE lmcleunik=? AND dn_cleunik=?");
        pstmt.setInt(1,0);
        pstmt.setInt(2,iddest);
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
           destination=FormatTexte.format(result.getString(1));           
        }
        pstmt=con.prepareStatement("SELECT  trattraduction FROM traductiontransport  WHERE lmcleunik=? AND trtcleunik=?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,idtransport);
        result=pstmt.executeQuery();
        while(result.next()){
           transport=FormatTexte.format(result.getString(1));             
        }
        if(emb!=0){
            pstmt=con.prepareStatement("SELECT tremb_traduction   FROM traduction_embarqdebarq WHERE lmcleunik=? AND emb_cleunik =?");
            pstmt.setInt(1,lmcleunik);
            pstmt.setInt(2,emb);
            result=pstmt.executeQuery();
            while(result.next()){
               embarquement=FormatTexte.format(result.getString(1));   
            }
        }
        else{
           pstmt=con.prepareStatement("SELECT  bro_freeembarq  FROM  brochure  WHERE  bro_cleunik=?");
           pstmt.setLong(1,bro_cleunik);
           result=pstmt.executeQuery();
           while(result.next()){
               embarquement=result.getString(1).toUpperCase();           
            } 
            
        }
        if(deb!=0){
                pstmt=con.prepareStatement("SELECT tremb_traduction   FROM traduction_embarqdebarq WHERE lmcleunik=? AND emb_cleunik =?");
                pstmt.setInt(1,lmcleunik);
                pstmt.setInt(2,deb);
                result=pstmt.executeQuery();
                while(result.next()){
                   debarquement=FormatTexte.format(result.getString(1));           
                }
        }else{
           pstmt=con.prepareStatement("SELECT  bro_freederbaq  FROM  brochure  WHERE  bro_cleunik=?");
           pstmt.setLong(1,bro_cleunik);
           result=pstmt.executeQuery();
           while(result.next()){
               debarquement=result.getString(1).toUpperCase();           
            } 
            
        }
        pstmt=con.prepareStatement("SELECT  ltintitule   FROM  traductionlogement WHERE lmcleunik=? AND  ltcleunik  =?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,log);
        result=pstmt.executeQuery();
        while(result.next()){
           this.logement=FormatTexte.format(result.getString(1));           
        }
    }
    
    /** Getter for property debarquement.
     * @return Value of property debarquement.
     */
    public java.lang.String getDebarquement() {
        return debarquement;
    }
    
    /** Setter for property debarquement.
     * @param debarquement New value of property debarquement.
     */
    public void setDebarquement(java.lang.String debarquement) {
        this.debarquement = debarquement;
    }
    
    /** Getter for property embarquement.
     * @return Value of property embarquement.
     */
    public java.lang.String getEmbarquement() {
        return embarquement;
    }
    
    /** Setter for property embarquement.
     * @param embarquement New value of property embarquement.
     */
    public void setEmbarquement(java.lang.String embarquement) {
        this.embarquement = embarquement;
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
    
    /** Getter for property logement.
     * @return Value of property logement.
     */
    public java.lang.String getLogement() {
        return logement;
    }
    
    /** Setter for property logement.
     * @param logement New value of property logement.
     */
    public void setLogement(java.lang.String logement) {
        this.logement = logement;
    }  
    String destination;
    String transport;
    String embarquement;
    String debarquement;
    String logement;
    
}
