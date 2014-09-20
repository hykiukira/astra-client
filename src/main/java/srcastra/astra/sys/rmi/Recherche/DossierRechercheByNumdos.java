/*
* DossierRechercheByNumdos.java
*
* Created on 2 december 2002, 14:58
*/
package srcastra.astra.sys.rmi.Recherche;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.rmi.astraimplement;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Thomas
 */
public class DossierRechercheByNumdos extends DossierRecherche implements InterfaceRecherche {
    /**
     * Creates a new instance of DossierRechercheByNumdos
     */
    public DossierRechercheByNumdos(astraimplement serveur2) {
        super(serveur2);
    }

    public ArrayList chargeDossierRecherche(Loginusers_T currentUser, int cleRecherche, String numdos, Poolconnection tmpool, int cas, String numfact, String numnc, String numfactfourn, int nonfacture, ArrayList order) throws SQLException, RemoteException {
        return null;
    }

    public String checkOrder(ArrayList checkOrder) {
        String order = "";
        boolean sw = false;
        if (checkOrder != null) {
            if (checkOrder.size() > 0) {
                for (int i = 0; i < checkOrder.size(); i++) {
                    if (checkOrder.get(i).toString().equals("1")) {
                        sw = true;
                        order = manageCheck(order, "d.dr_numdos");
                    } else if (checkOrder.get(i).toString().equals("2"))
                        order = manageCheck(order, "c1.csnom");
                    else if (checkOrder.get(i).toString().equals("3"))
                        order = manageCheck(order, "d.dr_date_depart");
                    else if (checkOrder.get(i).toString().equals("4"))
                        order = manageCheck(order, "d.po");
                    else if (checkOrder.get(i).toString().equals("5"))
                        order = manageCheck(order, "c2.csnom");
                    else if (checkOrder.get(i).toString().equals("6"))
                        order = manageCheck(order, "d.dr_datetimecrea");
                    else if (checkOrder.get(i).toString().equals("7"))
                        order = manageCheck(order, "dp.doit_type");
                    else if (checkOrder.get(i).toString().equals("8"))
                        order = manageCheck(order, "h.henumpiece");
                    else if (checkOrder.get(i).toString().equals("9"))
                        order = manageCheck(order, "h.henumpiece");
                    else if (checkOrder.get(i).toString().equals("10"))
                        order = manageCheck(order, "helibelle");
                    else if (checkOrder.get(i).toString().equals("11")) {
                        if (!sw)
                            order = manageCheck(order, "d.dr_numdos");
                    }
                }
            }
        }
        System.out.println("\n\n[++++++++++++++++++]chaine :" + order);
        return order;
    }

    private String manageCheck(String order, String lookafter) {
        if (!order.equals("")) {
            order = order.substring(0, (order.length() - 1));
            order = order + "," + lookafter + ";";
        } else {
            order = lookafter + ";";
        }
        return order;


    }

    public ArrayList chargeDossierRecherche(Loginusers_T currentUser, int cleRecherche, String numdos, String po, String ref1, String ref2, String typeprod, Date crea, Date depart, Poolconnection tmpool, int cas, String numfact, String numnc, String numfactfourn, int nonfacture, ArrayList order) throws SQLException, RemoteException {
//  Poolconnection tmpool=serveur2.getConnectionAndCheck(currentUser.getUrcleunik(),true);
        Dossier_T dossier;
        System.out.println("[WAAAAAAAAAAAAAAAAAAAAAAZZZZZAAAAAAAAAAAAAAAAAA**************]je rentre dans chargedossier");
        String plus;
        ArrayList returnvalue = new ArrayList();
        String orderS = checkOrder(order);
        if (orderS.equals(""))
            orderS = "d.dr_numdos;";
//String requete="SELECT DISTINCT d.dr_cleunik, d.dr_numdos, d.dr_date_depart, d.po, d.destination, d.hotel, d.dr_status, c1.csnom, d.fournisseur, d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d, user u, dossierproduit dp, clients c1, clients c2 WHERE d.urcleunik = u.urcleunik AND u.eecleunik = ? AND dr_annull = 0 AND d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND d.cscleunik_fact = c2.cscleunik AND dr_numdos LIKE (CONCAT( ?, '%' ) ) AND dp.doit_po LIKE (CONCAT( ?, '%' ) ) AND c1.csnom  LIKE (CONCAT( ?, '%' ) ) AND c2.csnom  LIKE (CONCAT( ?, '%' ) ) AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND d.dr_datetimecrea >= ? AND d.dr_date_depart >= ? ORDER BY d.dr_numdos";
        
        if(numfact.length()==0)
           plus=" AND h.hedossiercourant='O' ";   
        else
           plus=" ";
        
        
        
        String requete = "SELECT DISTINCT d.dr_cleunik, d.dr_numdos, d.dr_date_depart, d.po, d.destination, h.henumpiece, d.dr_status, c1.csnom, d.fournisseur, d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d , user u, dossierproduit dp, clients c1, clients c2,historique2 h WHERE d.urcleunik = u.urcleunik AND u.eecleunik = ? AND dr_annull = 0 AND d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND d.cscleunik_fact = c2.cscleunik AND dr_numdos LIKE (CONCAT( ?, '%' ) ) AND dp.doit_po LIKE (CONCAT( ?, '%' ) ) AND c1.csnom  LIKE (CONCAT( ?, '%' ) ) AND c2.csnom  LIKE (CONCAT( ?, '%' ) ) AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND d.dr_datetimecrea >= ? AND d.dr_date_depart >= ? AND d.dr_cleunik=h.drcleunik AND h.henumpiece  LIKE (CONCAT( ?, '%' ) )  AND h.helibelle LIKE (CONCAT( ?, '%' )) AND h.henotcpt LIKE (CONCAT( ?, '%' )) AND h.hetypeligne='B' AND d.dr_facture LIKE (CONCAT( ?, '%' ))"+plus+"ORDER BY " + orderS;

        String requete1= "SELECT DISTINCT d.dr_cleunik, d.dr_numdos, d.dr_date_depart, d.po, d.destination, h.henumpiece, d.dr_status, c1.csnom, d.fournisseur, d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d , user u, dossierproduit dp, clients c1, clients c2,historique2 h WHERE d.urcleunik = u.urcleunik AND u.eecleunik = ? AND dr_annull = 0 AND d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND d.cscleunik_fact = c2.cscleunik AND dr_numdos LIKE (CONCAT( ?, '%' ) ) AND dp.doit_po LIKE (CONCAT( ?, '%' ) ) AND c1.csnom  LIKE (CONCAT( ?, '%' ) ) AND c2.csnom  LIKE (CONCAT( ?, '%' ) ) AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND d.dr_datetimecrea >= ? AND d.dr_date_depart >= ? AND d.dr_cleunik=h.drcleunik AND h.henumpiece  LIKE (CONCAT( ?, '%' ) )  AND h.helibelle LIKE (CONCAT( ?, '%' )) AND h.henotcpt LIKE (CONCAT( ?, '%' )) AND (h.hetypeligne='NCB' OR h.hetypeligne='NCOB') AND d.dr_facture LIKE (CONCAT( ?, '%' )) ORDER BY " + orderS;
        
        String requete2= "SELECT DISTINCT d.dr_cleunik, d.dr_numdos, d.dr_date_depart, d.po, d.destination, h.henumpiece, d.dr_status, c1.csnom, d.fournisseur, d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d , user u, dossierproduit dp, clients c1, clients c2,historique2 h WHERE d.urcleunik = u.urcleunik AND u.eecleunik = ? AND dr_annull = 0 AND d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND d.cscleunik_fact = c2.cscleunik AND dr_numdos LIKE (CONCAT( ?, '%' ) ) AND dp.doit_po LIKE (CONCAT( ?, '%' ) ) AND c1.csnom  LIKE (CONCAT( ?, '%' ) ) AND c2.csnom  LIKE (CONCAT( ?, '%' ) ) AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND d.dr_datetimecrea >= ? AND d.dr_date_depart >= ? AND d.dr_cleunik=h.drcleunik AND h.henumpiece  LIKE (CONCAT( ?, '%' ) )  AND h.helibelle LIKE (CONCAT( ?, '%' )) AND h.henotcpt LIKE (CONCAT( ?, '%' )) AND (h.hetypeligne='ACP') AND d.dr_facture LIKE (CONCAT( ?, '%' )) ORDER BY " + orderS;
        //String requete="SELECT d.dr_cleunik,d.dr_numdos,d.dr_date_depart,d.po,d.destination,d.hotel,d.dr_status,d.client,d.fournisseur,d.dr_totalprix,d.dr_facture, d.dr_paye FROM dossier d,user u WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0 AND dr_numdos LIKE(CONCAT(?,'%')) ORDER BY d.dr_numdos;";
        System.out.println("numdos" + numdos);
        CompressArray myArray = null;
        try {
            PreparedStatement pstmt;
            if(numfact.length()==0 && numnc.length()!=0)   //note de credit
            {
            pstmt = tmpool.getConuser().prepareStatement(requete1);

            pstmt.setString(9, numnc);
            }
            else if (numfact.length()==0 && numnc.length()==0 && numfactfourn.length()!=0) 
            {
                
            pstmt = tmpool.getConuser().prepareStatement(requete2);

            pstmt.setString(9, numfactfourn);    
                
            }
            
            else
            {
            pstmt = tmpool.getConuser().prepareStatement(requete);
            String numfacttmp = numfact;
            
            if (numfact.equals(""))
                numfacttmp = numnc;
            pstmt.setString(9, numfacttmp);
            }
            
            pstmt.setInt(1, currentUser.getUreecleunik());
            pstmt.setString(2, numdos);
            pstmt.setString(3, po);
            pstmt.setString(4, ref1);
            pstmt.setString(5, ref2);
            pstmt.setString(6, typeprod);
            System.out.println(crea.toStringRequete() + "  " + depart.toStringRequete());
            pstmt.setString(7, crea.toStringRequete());
            pstmt.setString(8, depart.toStringRequete());
            
           
            pstmt.setString(10, "");

           
            
            String nonfacturetmp = "";
            if (nonfacture == 1) 
                nonfacturetmp = "0";
            pstmt.setString(12, nonfacturetmp);
            
            pstmt.setString(11, "");
            
             System.out.println(pstmt.toString());
            
            ResultSet result = pstmt.executeQuery();
            myArray = new CompressArray();
            myArray.Compress_from_resulset2(result);
            
        } catch (SQLException se) {
            se.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
        }
        return myArray;
    }

}
