/*
 * ManipSegnaletiqueInterface.java
 *
 * Created on 2 août 2002, 10:27
 */

package srcastra.astra.sys.manipulesignaletique;

/**
 *
 * @author  thomas
 */
public interface ManipSegnaletiqueInterface extends srcastra.astra.sys.rmi.utils.CheckInterface {
     public static final String TABLE_CP="codepostaux";
     public static final String CLE_CP="cxcleunik";
     public static final String TABLE_DEVISE="devise";
     public static final String CLE_DEVISE="decleunik";
     public static final String TABLE_LANGUE="langue";
     public static final String CLE_LANGUE="lecleunik";
     public static final String TABLE_PAYS="pays";
     public static final String CLE_PAYS="pyscleunik";
     public static final String TABLE_TITRE_PERS="titrepers";
     public static final String CLE_TITRE_PERS="tscleunik";
     public static final String TABLE_TRANSPORT="transport";
     public static final String CLE_TRANSPORT="trtcleunik";
     public static final String TABLE_VALEUR_TVA="valeurtva";
     public static final String CLE_VALEUR_TVA="vacleunik";  
     public static final String TABLE_CLIENT="clients";
     public static final String TABLE_FOURNISSEUR="fournisseur";
     public static final String TABLE_COMPAGNIE="compagnie";
     public static final String CLE_COMPAGNIE="coe_cleunik";
}
