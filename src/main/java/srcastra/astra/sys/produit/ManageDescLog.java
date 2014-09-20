/*
 * ManageDescLog.java
 *
 * Created on 2 avril 2003, 10:31
 */

package srcastra.astra.sys.produit;

/**
 *
 * @author  thomas
 */
import java.sql.*;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.sys.classetransfert.dossier.hotel.Hotel_T;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.*;
import java.rmi.*;
import java.util.ArrayList;
import srcastra.astra.sys.*;
public class ManageDescLog {
    
    /** Creates a new instance of ManageDescLog */
   produit_T prod;
   ArrayList descArray;
   Connection con;
   PreparedStatement pstmt;
    public ManageDescLog(produit_T prod,ArrayList descArray,Connection con,PreparedStatement pstmt) {
        this.prod=prod;
        this.descArray=descArray;
        this.con=con;
        this.pstmt=pstmt;
        
    }
    public void checkDescLog()throws SQLException{
        if(descArray!=null){
          for(int i=0;i<descArray.size();i++){
            DescriptionLogement_T desclog=(DescriptionLogement_T )descArray.get(i);
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[KKKKKKKKKKKKKKKK] desclog "+desclog.getDlt_cleUnik()+" desclog situation "+desclog.getDlt_situation()+"  annuler?:"+desclog.isDeleted());
             if(desclog.isDeleted())
                 annul_logement(con,pstmt,desclog);
             else if(desclog.isNewreccord())
                 insertOneDescriptionLogement(con,pstmt,desclog);
             else if(desclog.isModif())
                 modifyOnlyDescLog(con,pstmt,desclog);
          }          
        }             
    }
    public void insertAllDescLog() throws SQLException{
        if(descArray!=null){
          for(int i=0;i<descArray.size();i++){
            DescriptionLogement_T desclog=(DescriptionLogement_T )descArray.get(i);        
                 insertOneDescriptionLogement(con,pstmt,desclog);
          }          
        }          
    }
     private  void insertOneDescriptionLogement(Connection con,PreparedStatement pstmt,DescriptionLogement_T desclo)throws SQLException{
         long id=0;     
         String requete="";
         if(prod instanceof Brochure_T){ 
                 requete="INSERT INTO brochurelogement (brot_quantite,brot_xlit,brot_commodite,brot_situation,brot_vue,brot_regime,bro_cleunik,brot_datetimecrea,brot_datetimemodif,code) VALUES (?,?,?,?,?,?,?,NOW(),NOW(),?)";
                 id=((Brochure_T)prod).getBro_cleUnik();
         }
         else if(prod instanceof Hotel_T){
              requete="INSERT INTO hotellogement (hlt_quantite,hlt_xlit,hlt_commodite,hlt_situation,hlt_vue,hlt_regime,hl_cleunik,hlt_datetimecrea,hlt_datetimemodif,code) VALUES (?,?,?,?,?,?,?,NOW(),NOW(),?)";
              id=((Hotel_T)prod).getHl_cleUnik();
         }
                 pstmt=con.prepareStatement(requete);
                 pstmt.setInt(1,desclo.getDlt_quantité());
                 pstmt.setInt(2,desclo.getDlt_xlit());
                 pstmt.setInt(3,desclo.getDlt_commodite());
                 pstmt.setInt(4,desclo.getDlt_situation());
                 pstmt.setInt(5,desclo.getDlt_vue());
                 pstmt.setInt(6,desclo.getDlt_regime());
                 pstmt.setLong(7,id);
                 pstmt.setString(8,desclo.getCode());
                 pstmt.execute();
              }
     
      private  void modifyOnlyDescLog(Connection con,PreparedStatement pstmt,DescriptionLogement_T desclo)throws SQLException{
         String requete="";
         if(prod instanceof Brochure_T){ 
                 requete="UPDATE brochurelogement set brot_quantite=?,brot_xlit=?,brot_commodite=?,brot_situation=?,brot_vue=?,brot_regime=?,brot_datetimemodif=NOW(),code=? WHERE  brot_cleunik=?";
         }
         else if(prod instanceof Hotel_T){
                requete="UPDATE hotellogement set hlt_quantite=?,hlt_xlit=?,hlt_commodite=?,hlt_situation=?,hlt_vue=?,hlt_regime=?,hlt_datetimemodif=NOW(),code=? WHERE  hl_cleunik=?";
         }
         pstmt=con.prepareStatement(requete);
         pstmt.setInt(1,desclo.getDlt_quantité());
         pstmt.setInt(2,desclo.getDlt_xlit());
         pstmt.setInt(3,desclo.getDlt_commodite());
         pstmt.setInt(4,desclo.getDlt_situation());
         pstmt.setInt(5,desclo.getDlt_vue());
         pstmt.setInt(6,desclo.getDlt_regime());
         pstmt.setString(7,desclo.getCode());
         pstmt.setLong(8,desclo.getDlt_cleUnik());
         pstmt.execute();
     }
      public  void chargeBrochure_logement( )throws SQLException{
                 long id=0;     
                String requete="";
                 if(prod instanceof Brochure_T){ 
                    requete="SELECT brot_cleunik,brot_quantite,brot_xlit,brot_commodite,brot_situation,brot_vue,brot_regime,bro_cleunik,brot_datetimecrea,brot_datetimemodif,code FROM brochurelogement WHERE annuler=0 AND bro_cleunik=?";
                    id=((Brochure_T)prod).getBro_cleUnik();
                }
                else if(prod instanceof Hotel_T){
                    requete="SELECT hlt_cleunik,hlt_quantite,hlt_xlit,hlt_commodite,hlt_situation,hlt_vue,hlt_regime,hl_cleunik,hlt_datetimecrea,hlt_datetimemodif,code FROM hotellogement WHERE annuler=0 AND hl_cleunik=?";
                    id=((Hotel_T)prod).getHl_cleUnik();
                }
                pstmt=con.prepareStatement(requete);
                pstmt.setLong(1,id);
                ResultSet result=pstmt.executeQuery();
                result.beforeFirst();
                while(result.next()){
                 DescriptionLogement_T brolog=new DescriptionLogement_T();
                 brolog.setDlt_cleUnik(result.getInt(1));
                 brolog.setDlt_quantité(result.getInt(2));
                 brolog.setDlt_xlit(result.getInt(3));
                 brolog.setDlt_commodite(result.getInt(4));
                 brolog.setDlt_situation(result.getInt(5));
                 brolog.setDlt_vue(result.getInt(6));
                 brolog.setDlt_regime(result.getInt(7));
               //  brolog.setDlt_cleUnik(result.getInt(8));
                 brolog.setDlt_crea(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
                 brolog.setDlt_modif(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(10)));
                 brolog.setCode(result.getString(11));
                 if(prod instanceof Brochure_T){ 
                   ((Brochure_T)prod).addDescriptionLogement(brolog);
                     for(int i=0;i<((Brochure_T)prod).getDescriptionLogement().size();i++){
                         DescriptionLogement_T desclog=(DescriptionLogement_T )((Brochure_T)prod).getDescriptionLogement().get(i);
                        Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[KKKKKKKKKKKKKKKK] desclog "+desclog.getDlt_cleUnik()+" desclog situation "+desclog.getDlt_situation()+"  annuler?:"+desclog.isDeleted());
                     }
                }
                else if(prod instanceof Hotel_T){
                     ((Hotel_T)prod).addDescriptionLogement(brolog);
                }
                }
            
    }
       public void annul_logement(Connection con,PreparedStatement pstmt,DescriptionLogement_T desclo )throws SQLException{
                long id=0;     
                String requete="";
                 if(prod instanceof Brochure_T){ 
                    requete="UPDATE brochurelogement SET annuler=1 WHERE brot_cleunik=?";                   
                }
                else if(prod instanceof Hotel_T){
                    requete="UPDATE hotellogement SET annuler=1 WHERE hl_cleunik=?";                  
                }
                  pstmt=con.prepareStatement(requete);
                  pstmt.setLong(1,desclo.getDlt_cleUnik());
                  pstmt.execute();
                }
    
}
