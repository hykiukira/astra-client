package srcastra.test;

import srcastra.astra.sys.export.DivideVente;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 18-déc.-2004
 * Time: 15:51:15
 *
 * @author
 * @version $revision : $
 *          To change this template use File | Settings | File Templates.
 */
public class CorrectVente {

    private static Logger logger = Logger.getLogger(CorrectVente.class.getName());
    String ventes = "SELECT d.dr_numdos, c.csnom, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` , h.`heclottva` , h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` , h.`hedatecreation` , h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , cp.`ce_num` , h.`hevaleur` , t.tvic_cubic , h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` , h.`helibelle` , h.`drcleunik` , h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` , h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` , h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper,j.jota_abrev,c.csreference FROM  `historique2` h LEFT  JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN clients c ON h.intervenantcleunik = c.cscleunik LEFT JOIN tvacubic t ON (h.tva_cleunik =t.tvic_cleunik), entite e, journcompta j, user u,periode p,compte cp  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik AND h.jxcleunik = j.jota_cleunik AND (h.hetypeligne =  'B' OR h.hetypeligne =  'D' OR h.hetypeligne =  'TVAV' OR h.hetypeligne =  'NC' OR h.hetypeligne =  'NCB' OR h.hetypeligne =  'NCT' OR h.hetypeligne =  'NCO' OR h.hetypeligne =  'NCOT' OR h.hetypeligne =  'NCOB')" +
              "AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1 AND t.lmcleunik=? AND he_exported =0 AND h.drcleunik=? AND h.jxcleunik=? AND h.henumpiece=? AND cp.ce_cleunik=h.ce_cleunik2 ORDER BY h.hecleunik FOR UPDATE";
    String dossier = "SELECT DISTINCT drcleunik,henumpiece,jxcleunik FROM historique2 WHERE henotcpt=1 AND (hetypeligne='B' OR hetypeligne='NCB' OR hetypeligne='NCOB')  AND he_exported =0 order by henumpiece";
    Hashtable dossierTMP;
    ArrayList divArray;
     public void execute() throws SQLException {

        Connection con=null;
        System.out.println("Importation des ventes");
        PreparedStatement pstmt = con.prepareStatement(dossier);
        // pstmt.setInt(1,pedecleunik);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        int lmcleunik=0;
        while (result.next()) {
            if (dossierTMP == null)
                dossierTMP = new Hashtable();
            if (divArray == null) {
                divArray = new ArrayList();
            }
            DivideVente div = new DivideVente();
            div.setCledossier(result.getLong(1));
            div.setHenumpiece(result.getLong(2));
            div.setJxcleunik(result.getInt(3));
            divArray.add(div);
            String keys = "" + div.getJxcleunik() + div.getHenumpiece();
            dossierTMP.put(keys, div);
        }
        //pstmt=con.prepareStatement("UNLOCK TABLES");
        //pstmt.execute();
        if (dossierTMP == null)
         //   return null;
        if (divArray != null) {
            for (int i = 0; i < divArray.size(); i++) {

                getComptaDossier(lmcleunik, (DivideVente) divArray.get(i),con);

            }
        }
     }
     private void getComptaDossier(int lmcleunik, DivideVente div,Connection con) throws SQLException {

        // String historiquekey="";
        ArrayList dossierA = null;
        PreparedStatement pstmt = con.prepareStatement(ventes);
        pstmt.setInt(1, lmcleunik);
        pstmt.setLong(2, div.getCledossier());
        pstmt.setLong(3, div.getJxcleunik());
        pstmt.setLong(4, div.getHenumpiece());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            if (dossierA == null)
                dossierA = new ArrayList();
            Object[] tab = new Object[56];
            for (int i = 0; i < tab.length; i++) {
                if (i == 12 || i == 13) {
                    tab[i] = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i + 1)).toString3();
                } else {
                    tab[i] = result.getObject(i + 1);
                }
            }
            dossierA.add(tab);
        }
         String affichage="";
         for(int i=0;i<dossierA.size();i++){
             Object[] tmp=(Object[])dossierA.get(i);
             for (int j = 0; j < tmp.length; j++) {
                 Object o = tmp[j];
                 if(o!=null){
                     affichage="  "+o.toString();
                 }

             }
             affichage+="\n";
         }
     }
}
