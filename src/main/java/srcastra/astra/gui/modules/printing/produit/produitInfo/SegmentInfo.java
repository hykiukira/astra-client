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
public class SegmentInfo extends AbstractInfoProduit implements java.io.Serializable{
    
    /** Creates a new instance of BrochureInfo */
    int idcompagnie;
    int idarrive;
    int iddepart;
    int clesegment;
    public SegmentInfo(int urcleunik, int lmcleunik, int idcompagnie, int iddepart,int idarrive, int clesegment) {
        super(urcleunik,lmcleunik);
        this.idcompagnie=idcompagnie;
        this.iddepart=iddepart;
        this.idarrive=idarrive;
        this.clesegment=clesegment;
       
       // this.idaccomadation=idaccomadation;
       // this.emb=emb; 
       // this.deb=deb;
    }
    public void initMe(Connection con) throws SQLException{
        PreparedStatement pstmt=con.prepareStatement("SELECT coe_abrev   FROM compagnie WHERE coe_cleunik  =?");
      //  pstmt.setInt(1,lmcleunik);
        pstmt.setInt(1,idcompagnie);
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
          compagnie=result.getString(1);           
        }
        if(idarrive!=0){
            pstmt=con.prepareStatement("SELECT  tn_traduction  FROM  traduction_destination  WHERE   dn_cleunik =? AND lmcleunik=0");
            pstmt.setInt(1,idarrive);
            result=pstmt.executeQuery();
            while(result.next()){
               arrive=result.getString(1).toUpperCase();           
            }
        }
        else {
            pstmt=con.prepareStatement("SELECT  freelibelea  FROM  avion_segments  WHERE   as_cleunik=?");
            pstmt.setInt(1,clesegment);
            result=pstmt.executeQuery();
            while(result.next()){
               arrive=result.getString(1).toUpperCase();           
            }
                    
        }
        if(iddepart!=0){
         pstmt=con.prepareStatement("SELECT  tn_traduction  FROM  traduction_destination  WHERE   dn_cleunik =? AND lmcleunik=0");
        //pstmt=con.prepareStatement("SELECT  dn_abrev FROM  destination  WHERE  dn_cleunik =?");
        //pstmt.setInt(1,lmcleunik);
        pstmt.setInt(1,iddepart);
        result=pstmt.executeQuery();
        while(result.next()){
           depart=result.getString(1).toUpperCase();           
        }
        }
         else {
            pstmt=con.prepareStatement("SELECT  freelibelede  FROM  avion_segments  WHERE   as_cleunik=?");
            pstmt.setInt(1,clesegment);
            result=pstmt.executeQuery();
            while(result.next()){
               depart=result.getString(1).toUpperCase();           
            }
                    
        }
        /*
          *
        pstmt=con.prepareStatement("SELECT tremb_traduction FROM traduction_embarqdebarq WHERE lmcleunik=? AND emb_cleunik =?");
        pstmt.setInt(1,lmcleunik);
        pstmt.setInt(2,deb);
        result=pstmt.executeQuery();
        while(result.next()){
           debarquement=result.getString(1);           
        }*/
    }
    
    
   
   
    public java.lang.String getCompagnie() {
        return compagnie;
    }
    
    /** Setter for property compagnie.
     * @param compagnie New value of property compagnie.
     */
    public void setCompagnie(java.lang.String compagnie) {
        this.compagnie = compagnie;
    }
    
    /** Getter for property arrive.
     * @return Value of property arrive.
     */
    public java.lang.String getArrive() {
        return arrive;
    }
    
    /** Setter for property arrive.
     * @param arrive New value of property arrive.
     */
    public void setArrive(java.lang.String arrive) {
        this.arrive = arrive;
    }
    
    /** Getter for property depart.
     * @return Value of property depart.
     */
    public java.lang.String getDepart() {
        return depart;
    }
    
    /** Setter for property depart.
     * @param depart New value of property depart.
     */
    public void setDepart(java.lang.String depart) {
        this.depart = depart;
    }
    
    String depart;
    String arrive;
    String compagnie;
    
    
}
