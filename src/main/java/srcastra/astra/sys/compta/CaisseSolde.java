/*
 * CaisseSolde.java
 *
 * Created on 9 février 2004, 09:16
 */

package srcastra.astra.sys.compta;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.classetransfert.configuration.*;
/**
 *
 * @author  Administrateur
 */
public class CaisseSolde {
    
    /** Creates a new instance of CaisseSolde */
    //static String requeteListe="SELECT sum(h.hevaleur) FROM historique2 h,periode p ,typepaiement t,exercicecomptable ex WHERE h.ce_cleunik2=? AND h.hetypepayement=t.tynt_cleunik AND t. tynt_categorie=? AND h.heperiode=p.pede_cleunik AND p.pede_numper<? AND ex.exle_courant ='O'";
    static String requeteListe="SELECT sum(h.hevaleur) FROM historique2 h,periode p ,typepaiement t WHERE h.ce_cleunik2=? AND h.hetypepayement=t.tynt_cleunik AND t. tynt_categorie=? AND h.heperiode=p.pede_cleunik AND p.pede_cleunik<?";
    static String soldeinitial="SELECT open_cash,open_cheque FROM open WHERE open_cat=? AND usen_cleunik=?";
    //static String requete2="SELECT sum(h.hevaleur) FROM historique2 h,periode p ,typepaiement t,exercicecomptable ex WHERE h.ce_cleunik2=? AND h.hetypepayement=t.tynt_cleunik AND t. tynt_categorie=? AND h.heperiode=p.pede_cleunik AND p.pede_numper<=? AND ex.exle_courant ='O'";
    static String requete2="SELECT sum(h.hevaleur) FROM historique2 h,periode p ,typepaiement t WHERE h.ce_cleunik2=? AND h.hetypepayement=t.tynt_cleunik AND t. tynt_categorie=? AND h.heperiode=p.pede_cleunik AND p.pede_cleunik<=?";
   // String requete2="SUM (hevaleur) FROM historique2 h,user u, entite e WHERE h.ce_cleunik2=? AND hetypepayement=? AND u.eecleunik=e.eecleunik AND e.eecleunik=?";
    public CaisseSolde() {
        
    }
    public static void  getSolde(int pede_cleunik, Poolconnection tmpool, CaisseData data, int categorie, int urentcleunik, boolean before, Periode_T periode) throws SQLException{
        System.out.println("\n\n\n\n********************* categorie "+categorie+" ur "+urentcleunik);
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement("SELECT ce_cleunik FROM userentitecompte WHERE ce_cleunik_cat=449 AND ureecleunik=? AND uste_cat=?");
        pstmt.setInt(1,urentcleunik);
        pstmt.setInt(2,categorie); 
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        int cle=-1;
        while(result.next()){
            cle=result.getInt(1);
        }
        pstmt=tmpool.getConuser().prepareStatement(soldeinitial);
        pstmt.setInt(1,categorie);
        pstmt.setInt(2,urentcleunik);
        double initcash=0;
        double initcheque=0;
        result=pstmt.executeQuery();
        while(result.next()){
                 initcash=result.getDouble(1);  
                 initcheque=result.getDouble(2);  
        }
         if(!before)
            pstmt=tmpool.getConuser().prepareStatement(requete2);
        else
             pstmt=tmpool.getConuser().prepareStatement(requeteListe);
        pstmt.setInt(1,cle);
        pstmt.setInt(2,TypePayement.CASH);
        pstmt.setInt(3, periode.getPede_cleunik());
       // pstmt.setInt(3, pede_cleunik);
        result=pstmt.executeQuery();
        while(result.next()){
                data.setSoldecash(result.getDouble(1)+initcash);
                data.setSoldecash(MathRound.roundThisToDouble(data.getSoldecash()));
        } 
        if(!before)
            pstmt=tmpool.getConuser().prepareStatement(requete2);
        else
             pstmt=tmpool.getConuser().prepareStatement(requeteListe);
        pstmt.setInt(1,cle);
        pstmt.setInt(2,TypePayement.CHEQUE);
        pstmt.setInt(3, periode.getPede_cleunik());
        //pstmt.setInt(3, pede_cleunik);
        result=pstmt.executeQuery();
        while(result.next()){
                data.setSoldecheque(result.getDouble(1)+initcheque);   
                data.setSoldecheque(MathRound.roundThisToDouble(data.getSoldecheque()));
        }     
    }
     
    
    
}
