/*







 * SupplementReduction.java







 *







 * Created on 18 octobre 2002, 9:36







 */
package srcastra.astra.sys.produit;

import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.rmi.DossierSql;

import java.sql.*;
import java.util.Enumeration;

import srcastra.astra.sys.compta.GenereLigneComptable;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.*;
import srcastra.astra.sys.rmi.groupe_dec.*;
import srcastra.astra.sys.compta.*;

/**
 * @author Thomas
 */
public class SupplementReduction implements DossierSql {
    /**
     * Creates a new instance of SupplementReduction
     */
    // static astrainterface m_serveur;
    public SupplementReduction() {
        //    m_serveur=serveur;
    }

    public static void insertSupreduc(produit_T produit, Connection con, PreparedStatement pstmt, double cledossier, long intervenantCleunik, int urcleunik, astrainterface serveur) throws SQLException {
        if (produit.getSup_reduc() != null) {
            for (Enumeration enu = produit.getSup_reduc().keys(); enu.hasMoreElements(); ) {
                Sup_reduc_T supreduc = (Sup_reduc_T) produit.getSup_reduc().get(enu.nextElement());
                insertSupreducLocale(supreduc, con, pstmt, produit, urcleunik, serveur);
                float tva = supreduc.getSoumis_tva() == 1 ? produit.getGroupdec().getValeurGenFloat1() : 0;
                int clecompte = 0;
                int categorieCompte = 0;
                if (supreduc.getSup() == 1) {
                    clecompte = produit.getGroupdec().getGncomptevente();
                    categorieCompte = produit.getGroupdec().getCategoriecomptev();


                } else if (supreduc.getReduc() == 1) {
                    clecompte = 3;
                    categorieCompte = 3;


                }
                //  GenereLigneComptable.insertLigneComptable(supreduc,con,cledossier,pstmt,tva,produit.getAt_cleunik(),supreduc.getAt_cleunik(),intervenantCleunik,clecompte,categorieCompte);
                /*     pstmt.setInt(1,supreduc.getSup());







           pstmt.setInt(2,supreduc.getReduc());







           pstmt.setInt(3,supreduc.getSoumis_tva());







           pstmt.setString(4,supreduc.getFreeLibelle());







           pstmt.setFloat(5,supreduc.getPrix());







           pstmt.setFloat(6,supreduc.prct);







           pstmt.setInt(7,supreduc.qua);







           pstmt.setInt(8,supreduc.pax);







           pstmt.setInt(9,supreduc.getParent().getTypeDeProduitCleunik());







           pstmt.setLong(10,produit.getAt_cleunik());







           pstmt.execute();     */
            }


        }


    }

    private static void insertSupreducLocale(srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T supreduc, Connection con, PreparedStatement pstmt, produit_T produit, int urcleunik, astrainterface m_serveur) throws SQLException {
        if (!supreduc.isDeleted()) {
            pstmt = con.prepareStatement("INSERT INTO suplement_reduction (sn_supplement,sn_reduction,sn_soumistva,sn_libele,sn_prix,sn_prct,sn_qua,sn_pax,ctpro_cleunik,at_cleunik,snlib_cleunik,longvalue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, supreduc.getSup());
            pstmt.setInt(2, supreduc.getReduc());
            pstmt.setInt(3, supreduc.getSoumis_tva());
            pstmt.setString(4, supreduc.getFreeLibelle());
            pstmt.setDouble(5, supreduc.getAt_val_vente());
            pstmt.setFloat(6, supreduc.prct);
            pstmt.setInt(7, supreduc.qua);
            pstmt.setInt(8, supreduc.pax);
            pstmt.setInt(9, produit.getTypeDeProduitCleunik());
            pstmt.setLong(10, produit.getAt_cleunik());
            pstmt.setInt(11, supreduc.getAclibelle());
            pstmt.setLong(12, supreduc.getLonvalue());
            pstmt.execute();
            supreduc.setSup_reduc_cleunik(getId(con));
            GrpProduitGestion.grpdecinsertSup(supreduc, con, urcleunik, m_serveur);

        }

    }

    private static void insertGroupeDec(long lignecleunik, int categoriecleunik, Grpdecision_T grpd, Connection con) {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        try {
            sqlrequete = "insert into groupedecisionsupreduc(frcleunik,ttcleunik,itcleunik,aecleunik,gndatetimecrea,gndatetimemodif,gncodetvavente," + "gntvainclusvente,gncodetvaachat,gntvainclusachat,gnnbravanteche,gnacompteminpp,gnprodstockiata,gnprodstockautre,gnpccomvente,gncominclpvent,gnpcacompte,gnpccomachat,gnpcclerepvend,gnpcclerepconcept,gnpcclerepmmere,snumerosessioncrea,snumerosessionmodif,frgtcleunik,gncomptevente,gncompteachat,gnfracomptepc,gnfrnbda,gnfrsoldepc,gnfrnbds,gnfranchise,caecleunik,tecleunik,lignecleunik,ctpro_cleunik ) values(" + 0 + "," + grpd.getTtcleunik() + "," + grpd.getItcleunik() + "," + grpd.getAecleunik() + ",NOW(),NOW()," + grpd.getGncodetvavente() + "," + grpd.getGntvainclusvente() + "," + grpd.getGncodetvaachat() + "," + grpd.getGntvainclusachat() + "," + grpd.getGnnbravanteche() + "," + grpd.getGnacompteminpp() + "," + grpd.getGnprodstockiata() + "," + grpd.getGnprodstockautre() + "," + grpd.getGnpccomvente() + "," + grpd.getGncominclpvent() + "," + grpd.getGnpcacompte() + "," + grpd.getGnpccomachat() + "," + grpd.getGnpcclerepvend() + "," + grpd.getGnpcclerepconcept() + "," + grpd.getGnpcclerepmmere() + ",' ',' '," + grpd.getFrgtcleunik() + ",'" + grpd.getGncomptevente() + "','" + grpd.getGncompteachat() + "'," + grpd.getGnfracomptepc() + "," + grpd.getGnfrnbda() + "," + grpd.getGnfrsoldepc() + "," + grpd.getGnfrnbds() + "," + grpd.getGnfranchise() + "," + grpd.getCaecleunik() + "," + grpd.getTecleunik() + "," + lignecleunik + "," + categoriecleunik + ");";
            tmpret = Transaction.execrequeteinsert(sqlrequete, con);


        } catch (Exception i) {
        }


    }

    private static void modifyGroupeDec(long lignecleunik, int categoriecleunik, Grpdecision_T grpd, Connection con) {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        try {
            sqlrequete = "update groupedecisionproduit set ttcleunik=" + grpd.getTtcleunik() + ",itcleunik=" + grpd.getItcleunik() + ",aecleunik=" + grpd.getAecleunik() + ",gndatetimemodif=NOW(),gncodetvavente=" + grpd.getGncodetvavente() + "," + "gntvainclusvente=" + grpd.getGntvainclusvente() + ",gncodetvaachat=" + grpd.getGncodetvaachat() + ",gntvainclusachat=" + grpd.getGntvainclusachat() + ",gnnbravanteche=" + grpd.getGnnbravanteche() + ",gnacompteminpp=" + grpd.getGnacompteminpp() + ",gnprodstockiata=" + grpd.getGnprodstockiata() + ",gnprodstockautre=" + grpd.getGnprodstockautre() + ",gnpccomvente=" + grpd.getGnpccomvente() + ",gncominclpvent=" + grpd.getGncominclpvent() + ",gnpcacompte=" + grpd.getGnpcacompte() + ",gnpccomachat=" + grpd.getGnpccomachat() + ",gnpcclerepvend=" + grpd.getGnpcclerepvend() + ",gnpcclerepconcept=" + grpd.getGnpcclerepconcept() + ",gnpcclerepmmere=" + grpd.getGnpcclerepmmere() + ",gncomptevente='" + grpd.getGncomptevente() + "',gncompteachat='" + grpd.getGncompteachat() + "',gnfracomptepc=" + grpd.getGnfracomptepc() + ",gnfrnbda=" + grpd.getGnfrnbda() + ",gnfrnbds=" + grpd.getGnfrnbds() + ",gnfranchise=" + grpd.getGnfranchise() + " where gncleunik=" + grpd.getGncleunik() + ";";
            // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Connexion du user"+connect.getConuser());
            tmpret = Transaction.execrequeteinsert(sqlrequete, con);


        } catch (Exception i) {
        }


    }

    public static void chargeSupreduc(produit_T produit, Connection con, PreparedStatement pstmt, astrainterface serveur, int urcleunik) throws SQLException {
        String requete = "SELECT  s.sn_cleunik,s.sn_supplement,s.sn_reduction,s.sn_soumistva,s.sn_libele,s.sn_prix,s.sn_prct,s.sn_qua,s.sn_pax,h.hevaleur,h.hevaleurbase,h.hevaleurtva,s.snlib_cleunik,s.longvalue  FROM suplement_reduction s,historique2 h WHERE s.annuler=0 AND h.lignecleunik=s.at_cleunik AND s.at_cleunik=? AND h.sn_cleunik=s.sn_cleunik AND h.ctprocleunik=s.ctpro_cleunik AND s.ctpro_cleunik=? AND h.hedossiercourant='O' AND h.hetypeligne='D'";
        //pstmt=con.prepareStatement("SELECT  s.sn_cleunik,s.sn_supplement,s.sn_reduction,s.sn_soumistva,s.sn_libele,s.sn_prix,s.sn_prct,s.sn_qua,s.sn_pax,h.hevaleur,h.hevaleurbase,h.hevaleurtva,s.snlib_cleunik,s.longvalue  FROM suplement_reduction s,historique2 h WHERE s.annuler=0 AND h.lignecleunik=? AND h.sn_cleunik=s.sn_cleunik AND h.ctprocleunik=? AND h.hedossiercourant='O'");
        pstmt = con.prepareStatement(requete);
        pstmt.setLong(1, produit.getAt_cleunik());
        pstmt.setInt(2, produit.getTypeDeProduitCleunik());
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            Sup_reduc_T supreduc = new Sup_reduc_T();
            supreduc.setSup_reduc_cleunik(result.getLong(1));
            supreduc.setSup(result.getInt(2));
            supreduc.setReduc(result.getInt(3));
            supreduc.setSoumis_tva(result.getInt(4));
            supreduc.setFreeLibelle(result.getString(5));
            supreduc.setPrix(result.getFloat(6));
            supreduc.setAt_val_vente(result.getDouble(6));
            supreduc.prct = result.getFloat(7);
            supreduc.qua = result.getInt(8);
            supreduc.pax = result.getInt(9);
            supreduc.setValeur_tot_tva_inc(-result.getDouble(10));
            supreduc.setValeur_tot(-result.getDouble(11));
            supreduc.setMontant_tva(-result.getDouble(12));
            supreduc.setAclibelle(result.getInt(13));
            supreduc.setLonvalue(result.getLong(14));
            supreduc.setParent(produit);
            GrpProduitGestion.filGrpDecToSupReduc(serveur, supreduc, produit, con, urcleunik);
            pstmt = con.prepareStatement(CHARGE_GRP_PROD);
            produit.addSup_Reduc(supreduc);

        }

    }

    public static void modifySupreduc(produit_T produit, Connection con, PreparedStatement pstmt, int cledossier, long intervenantCleunik, int urcleunik, astrainterface serveur) throws SQLException {
        if (produit.getSup_reduc() != null) {
            pstmt = con.prepareStatement(INSERT_SUP_REDUC);
            for (Enumeration enu = produit.getSup_reduc().keys(); enu.hasMoreElements(); ) {
                Sup_reduc_T supreduc = (Sup_reduc_T) produit.getSup_reduc().get(enu.nextElement());
                float tva = supreduc.getSoumis_tva() == 1 ? produit.getGroupdec().getValeurGenFloat1() : 0;
                int clecompte = 0;
                int categorieCompte = 0;
                if (supreduc.getSup() == 1) {
                    clecompte = produit.getGroupdec().getGncomptevente();
                    categorieCompte = produit.getGroupdec().getCategoriecomptev();

                } else if (supreduc.getReduc() == 1) {
                    clecompte = 3;
                    categorieCompte = 3;

                }
                if (!supreduc.isDeleted()) {
                    if (supreduc.isNewreccord()) {
                        insertSupreducLocale(supreduc, con, pstmt, produit, urcleunik, serveur);

                    } else if (supreduc.isModifyreccord()) {
                        modifySupreducLocale(supreduc, con, pstmt, produit, urcleunik, serveur);

                    }

                } else {
                    annulSupreducLocale(supreduc, con, pstmt, null);

                }

            }

        }

    }

    private static void modifySupreducLocale(Sup_reduc_T supreduc, Connection con, PreparedStatement pstmt, produit_T produit, int urcleunik, astrainterface m_serveur) throws SQLException {
        pstmt = con.prepareStatement("UPDATE suplement_reduction set sn_supplement=?,sn_reduction=?,sn_soumistva=?,sn_libele=?,sn_prix=?,sn_prct=?,sn_qua=?,sn_pax=?,snlib_cleunik=? WHERE sn_cleunik=?;");
        pstmt.setInt(1, supreduc.getSup());
        pstmt.setInt(2, supreduc.getReduc());
        pstmt.setInt(3, supreduc.getSoumis_tva());
        pstmt.setString(4, supreduc.getFreeLibelle());
        pstmt.setDouble(5, supreduc.getAt_val_vente());
        pstmt.setFloat(6, supreduc.prct);
        pstmt.setInt(7, supreduc.qua);
        pstmt.setInt(8, supreduc.pax);
        pstmt.setInt(9, supreduc.getAclibelle());
        pstmt.setLong(10, supreduc.getSup_reduc_cleunik());
        pstmt.execute();
        GrpProduitGestion.grpdecinsertSup(supreduc, con, urcleunik, m_serveur);
        GrpProduitGestion.grpdecModifySup(supreduc, con, urcleunik, m_serveur);

    }

    public static void annulSupreducLocale(Sup_reduc_T supreduc, Connection con, PreparedStatement pstmt, produit_T produit) throws SQLException {
        pstmt = con.prepareStatement("UPDATE suplement_reduction set annuler=1  WHERE sn_cleunik=?;");
        pstmt.setLong(1, supreduc.getSup_reduc_cleunik());
        pstmt.execute();
        //  GrpProduitGestion.grpdecinsertSup(supreduc,con);
        // GrpProduitGestion.grpdecModifySup(supreduc,con);
    }

    private static int getId(Connection con) {
        int id = 0;
        try {
            PreparedStatement pstmt = con.prepareStatement("select LAST_INSERT_ID()");
            ResultSet tmpresult = pstmt.executeQuery();
            tmpresult.first();
            id = tmpresult.getInt(1);
            // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"numero du fournisseur "+id);
            tmpresult.close();


        } catch (SQLException se) {
            se.printStackTrace();


        }
        return id;


    }

    private static Object chargeSuprecucGrpDec(PreparedStatement pstmt, Connection con, long objcleunik) throws SQLException {
        String sqlrequete = null;
        Object returnValue = null;
        Gestionerreur_T tmperreur = new Gestionerreur_T();
        Gestionerreur_T tmperreur2 = new Gestionerreur_T();
        sqlrequete = "SELECT g.gncleunik,g.frcleunik,g.ttcleunik,g.itcleunik,g.aecleunik,g.gndatetimecrea,g.gndatetimemodif,g.gncodetvavente,g.gntvainclusvente,g.gncodetvaachat,g.gntvainclusachat,g.gnnbravanteche,g.gnacompteminpp,g.gnprodstockiata,g.gnprodstockautre,g.gnpccomvente,g.gncominclpvent,g.gnpcacompte,g.gnpccomachat,g.gnpcclerepvend,g.gnpcclerepconcept,g.gnpcclerepmmere,g.snumerosessioncrea,g.snumerosessionmodif,g.frgtcleunik,g.gncomptevente,g.gncompteachat,g.gnfracomptepc,g.gnfrnbda,g.gnfrsoldepc,g.gnfrnbds,g.gnfranchise,g.caecleunik,g.tecleunik,v.vavaleurtva,v1.vavaleurtva FROM groupedecisionsupreduc  g,valeurtva v,valeurtva v1 where  lignecleunik=?  and g.gncodetvavente=v.vacleunik and g.gncodetvaachat=v1.vacleunik;";
        pstmt = con.prepareStatement(sqlrequete);
        pstmt.setLong(1, objcleunik);
        ResultSet tmpresult = pstmt.executeQuery();
        while (tmpresult.next()) {
            String[] numSession = Transaction.renvNomUserPourSession(con, tmpresult.getString(22), tmpresult.getString(23));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   /* les 2 nveaux checkBox */
            Grpdecision_T tmpGrpDec = new Grpdecision_T(tmpresult.getInt(1), tmpresult.getInt(2), tmpresult.getInt(3), tmpresult.getInt(4), tmpresult.getInt(5), tmpresult.getDate(6), tmpresult.getDate(7), tmpresult.getInt(8), tmpresult.getInt(9), tmpresult.getInt(10), tmpresult.getInt(11), tmpresult.getInt(12), tmpresult.getFloat(13), tmpresult.getInt(14), tmpresult.getInt(15), tmpresult.getFloat(16), tmpresult.getInt(17), tmpresult.getFloat(18), tmpresult.getFloat(19), tmpresult.getFloat(20), tmpresult.getFloat(21), tmpresult.getFloat(22), tmpresult.getString(23), tmpresult.getString(24), tmpresult.getInt(25), tmpresult.getInt(26), tmpresult.getInt(27), tmpresult.getFloat(28), tmpresult.getInt(29), tmpresult.getInt(30), tmpresult.getInt(31), tmpresult.getInt(32), tmpresult.getInt(33), tmpresult.getInt(34), numSession[0], numSession[1], 0, 0);
            tmpGrpDec.setValeurGenFloat1(tmpresult.getFloat(35));
            tmpGrpDec.setValeurGenFloat2(tmpresult.getFloat(36));
            tmpGrpDec.setErreurcode(tmperreur.getErreurcode());
            tmpGrpDec.setErreurmessage(tmperreur.getErreurmessage());
            String req = "SELECT g.gncomptevente,c.ce_num,c.cate_cleunik FROM groupedecisionsupreduc g,compte c WHERE g.gncomptevente=c.ce_cleunik AND g.lignecleunik=?";
            pstmt = con.prepareStatement(req);
            pstmt.setLong(1, objcleunik);
            ResultSet tmp2 = pstmt.executeQuery();
            while (tmp2.next()) {
                tmpGrpDec.setIntitulecomptev(tmp2.getInt(2));
                tmpGrpDec.setCategoriecomptev(tmp2.getInt(3));


            }
            req = "SELECT g.gncompteachat,c.ce_num,c.cate_cleunik FROM groupedecisionsupreduc g,compte c WHERE g.gncompteachat=c.ce_cleunik AND g.lignecleunik=?";
            pstmt = con.prepareStatement(req);
            pstmt.setLong(1, objcleunik);
            tmp2 = pstmt.executeQuery();
            while (tmp2.next()) {
                tmpGrpDec.setIntitulecomptea(tmp2.getInt(2));
                tmpGrpDec.setCategoriecomptea(tmp2.getInt(3));


            }
            returnValue = (Grpdecision_T) tmpGrpDec;


        }
        return returnValue;


    }


}







