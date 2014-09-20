/*
 * ProduitInfoComplementaire.java
 *
 * Created on 19 mars 2003, 8:46
 */

package srcastra.astra.sys.classetransfert.dossier;
import java.sql.*;

/**
 *
 * @author  Thomas
 */
public class ProduitInfoComplementaire {
    
    /** Creates a new instance of ProduitInfoComplementaire */
    public ProduitInfoComplementaire() {
    }
    public static void getInfo(String key1,String key2,int typeProduit,java.util.Locale locale,produit_T prod,Connection con) throws java.sql.SQLException{
        //"SELECT fg.frgtitrecatalog,f.frcleunik,f.frnom1 from fournisseur_grproduit fg,fournisseur f WHERE fg.frcleunik=f.frcleunik AND fg.frgtcleunik=?;";
         PreparedStatement pstmt=con.prepareStatement("SELECT fg.frgtitrecatalog,f.frcleunik,f.frnom1,f.frreference1,f.fradresse,f.frmail,f.frtelephone1,f.frfax,f.cxcleunik  from fournisseur_grproduit fg,fournisseur f WHERE fg.frcleunik=f.frcleunik AND fg.frgtcleunik=?");
         pstmt.setInt(1,prod.getFrgtcleunik());
         ResultSet result2=pstmt.executeQuery();
         result2.beforeFirst();
         while(result2.next()){
             
             //inversion voulue de la réference et du nom du fournisseur
             //comme ça dans l'interface graphique
             prod.setGroupe_produit_nom(result2.getString(1));
             prod.setFrcleunik(result2.getInt(2));
             prod.setFrnom(result2.getString(3));
             prod.setFrreference(result2.getString(4));
             prod.setFradresse(result2.getString(5));
             prod.setFrrmail(result2.getString(6));
             prod.setFrtelephone(result2.getString(7));
             prod.setFrfax(result2.getString(8));
             prod.setFrcxcleunik(result2.getInt(9));
             prod.setTypeDeProduitCleunik(typeProduit);
             prod.setTypeDeProduitNom(loadNames(key1,locale));
             prod.setFullname(loadNames(key2,locale));
            }  
    }    
     private static String loadNames(String key,java.util.Locale locale) {
        String retVal = "";
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/sys/ProduitAffichage", locale).getString(key);
        }
        catch (java.util.MissingResourceException e) {
        }
        finally {
            return retVal;
        }
    }
public final static String AS="as";
public final static String AS_FULL="as_full";
public final static String AV="av";
public final static String AV_FULL="av_full";
public final static String BA="ba";
public final static String BA_FULL="ba_full";
public final static String BRO="bro";
public final static String BRO_FULL="bro_full";
public final static String HO="ho";
public final static String HO_FULL="ho_full";
public final static String TAX="tax";
public final static String TAX_FULL="tax_full";
public final static String TR="tr";
public final static String TR_FULL="tr_full";
public final static String VO="vo";
public final static String VO_FULL="vo_full";
public final static String CA="ca";
public final static String CA_FULL="ca_full";
public final static String DIV="div";
public final static String DIV_FULL="div_full";

}
