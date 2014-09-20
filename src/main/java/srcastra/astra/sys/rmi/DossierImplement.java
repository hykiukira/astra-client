/*
* DossierImplement.java
*

* Created on 27 août 2002, 14:45
*/
package srcastra.astra.sys.rmi;

import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*;

import srcastra.astra.sys.classetransfert.Task_T;

import java.sql.*;

import srcastra.astra.sys.rmi.astraimplement;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.rmi.*;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;

import java.util.ArrayList;
import java.util.Hashtable;

import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.classetransfert.clients.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.produit.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.rmi.Recherche.*;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.printing.PrintingInfo;
import srcastra.astra.gui.modules.printing.produit.produitInfo.*;
import srcastra.astra.gui.modules.compta.achat.*;
import srcastra.astra.sys.rmi.groupe_dec.*;
// ajout de Driart le 11 aout 2003
import java.io.*;

import srcastra.astra.sys.compta.checkcompte.*;
import srcastra.astra.gui.modules.agenda.Calendar_T;
import srcastra.astra.sys.rmi.socketfactory.*;

import srcastra.astra.gui.modules.compta.achat.*;


//end ajout

/**p
 * @author Thomas
 */
public class DossierImplement extends java.rmi.server.UnicastRemoteObject implements DossierRmiInterface, DossierSql, DossierSqlRecherche {


    /**
     * Creates a new instance of DossierImplement
     */


    Hashtable dossierModif;

    public DossierImplement(astraimplement serveur, int port) throws RemoteException {
        super(port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        this.serveur = serveur;
        this.lock = new Dossier_T();
        genereTabRechercheClass();
        dossierModif = new Hashtable();
    }

    public void updateCompteParDefaut(int urcleunik, int vente, int achat) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = null;
        try {
// gncomptevente,gncompteachat
            String defaut = "update groupedecisiondef set gncomptevente=?,gncompteachat=?;";
            String groupedec = "update groupedecision set gncomptevente=?,gncompteachat=?;";
            tmpool = serveur.getConnectionAndCheck(urcleunik, true);
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(defaut);
            pstmt.setInt(1, vente);
            pstmt.setInt(2, achat);
            pstmt.execute();
            pstmt = tmpool.getConuser().prepareStatement(groupedec);
            pstmt.setInt(1, vente);
            pstmt.setInt(2, achat);
            pstmt.execute();

        } catch (SQLException sn) {
            renvexception(sn, "[************DOSSIER: stacktrace: ", tmpool.getConuser());
        }
    }

    private boolean lockDossier(Object numdosv, Object urcleunik) {
        synchronized (dossierModif) {
            if (dossierModif.get(numdosv) == null) {
                dossierModif.put(numdosv, urcleunik);
                return false;
            } else {
                Object urcle = dossierModif.get(numdosv);
                if (urcle.equals(urcleunik)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    private void unLockDossier(Object numdosv) {
        synchronized (dossierModif) {
            if (dossierModif.get(numdosv) != null) {
                dossierModif.remove(numdosv);

            }
        }
    }

    /**
     * Getter for property cscleunik.
     *
     * @return Value of property cscleunik.
     */

    public AbstractInfoProduit getProduitInfo(int urcleunik, AbstractInfoProduit prod) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = null;
        try {
            tmpool = serveur.getConnectionAndCheck(urcleunik, true);
            prod.initMe(tmpool.getConuser());
            return prod;
        } catch (SQLException sn) {
            renvexception(sn, "[************DOSSIER: stacktrace: ", tmpool.getConuser());
        }
        return null;
    }

    public Clients_T getDossierClient(int csCleunik, Loginusers_T currentUser) throws RemoteException {
        Poolconnection tmpool = serveur.getConnectionAndCheck(currentUser.getUrcleunik(), true);
        return getDossierClientLocal(csCleunik, currentUser);
    }

    private void insertMemo(Dossier_T dossier, Poolconnection tmpool) throws SQLException {
        if (dossier != null) {
            if (dossier.getMemo() != null) {
                if (!dossier.getMemo().isEmpty()) {
                    PreparedStatement pstmt = tmpool.getConuser().prepareStatement("INSERT INTO `dossiermemo` (`dr_cleunik` , `domo_org` , `domo_imp`,`domo_fact`,`specialdossier` ) VALUES ( ?, ?, ?, ?, ?)");
                    pstmt.setLong(1, dossier.getDrcleunik());
                    pstmt.setString(2, dossier.getMemo().getMemoOrg());
                    pstmt.setString(3, dossier.getMemo().getMemoPrint());
                    pstmt.setString(4, dossier.getMemo().getMemoFacture());
                    pstmt.setInt(5, dossier.getMemo().getSpecialdossier());
                    pstmt.execute();
                }
            }
        }
    }

    private void dealWhithMemo(Dossier_T dossier, Poolconnection tmpool) throws SQLException {
        if (dossier != null) {
            if (dossier.isNewreccord()) {  //si le dossier est un nouveau dossier
                insertMemo(dossier, tmpool); //alors on insert le nouveau memo
            } else {
                Memo_T memo = selectMemo(dossier, tmpool);
                if (memo == null) {  //si le dossier n'est pa nouveau mais qu'il n'a pas encore de mémo
                    insertMemo(dossier, tmpool); //alors on insert le nouveau memo
                } else {
                    modifyMemo(dossier, tmpool);//sinon on modifie le mémo existant
                }
            }
        }
    }

    private void modifyMemo(Dossier_T dossier, Poolconnection tmpool) throws SQLException {
        if (dossier != null) {
            if (dossier.getMemo() != null) {
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement("UPDATE `dossiermemo` SET `domo_org`=? , `domo_imp`=?,`domo_fact`=?,`specialdossier`=? WHERE `dr_cleunik`=?;");
                pstmt.setString(1, dossier.getMemo().getMemoOrg());
                pstmt.setString(2, dossier.getMemo().getMemoPrint());
                pstmt.setString(3, dossier.getMemo().getMemoFacture());
                pstmt.setInt(4, dossier.getMemo().getSpecialdossier());
                pstmt.setLong(5, dossier.getDrcleunik());
                pstmt.execute();
            }
        }
    }

    private Memo_T selectMemo(Dossier_T dossier, Poolconnection tmpool) throws SQLException {
        Memo_T memo = null;
        if (dossier != null) {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement("SELECT `domo_org` , `domo_imp`,`domo_fact`,`specialdossier` FROM `dossiermemo` WHERE `dr_cleunik`=?;");
            pstmt.setLong(1, dossier.getDrcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                memo = new Memo_T();
                memo.setMemoOrg(result.getString(1));
                memo.setMemoPrint(result.getString(2));
                memo.setMemoFacture(result.getString(3));
                memo.setSpecialdossier(result.getInt(4));
                if (memo.getMemoOrg().equals("") && memo.getMemoPrint().equals("") && memo.getMemoFacture().equals(""))
                    memo.setEmpty(true);
            }
        }
        return memo;
    }

    private Memo_T selectMemo(int cledossier, Poolconnection tmpool) throws SQLException {
        Memo_T memo = null;

        PreparedStatement pstmt = tmpool.getConuser().prepareStatement("SELECT `domo_org` , `domo_imp`,`domo_fact`,`specialdossier` FROM `dossiermemo` WHERE `dr_cleunik`=?;");
        pstmt.setLong(1, new Integer(cledossier).longValue());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            memo = new Memo_T();
            memo.setMemoOrg(result.getString(1));
            memo.setMemoPrint(result.getString(2));
            memo.setMemoFacture(result.getString(3));
            memo.setSpecialdossier(result.getInt(4));
            if (memo.getMemoOrg().equals("") && memo.getMemoPrint().equals("") && memo.getMemoFacture().equals(""))
                memo.setEmpty(true);
        }

        return memo;
    }

    public Object getAgenceInfo(int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = null;
            Entite_T entite = null;
            String req = "SELECT * FROM entite WHERE eecleunik=?";
            pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, tmpool.getNumeroentite());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                if (entite == null)
                    entite = new Entite_T();
                entite.setEenom(result.getString(2));
                entite.setEeadresse(result.getString(3));
                entite.setEetelephonep(result.getString(4));
                entite.setEetelephones(result.getString(5));
                entite.setEefax(result.getString(6));
                entite.setEemail(result.getString(7));
                entite.setEeactif(result.getInt(8));
                entite.setDatecrea(new Date(result.getString(9)));
                entite.setDatemodif(new Date(result.getString(10)));
                entite.setLmcleunik(result.getInt(11));
                entite.setCxcleunik(result.getInt(12));
                entite.setSecleunik(result.getInt(13));
                entite.setEeabrev(result.getString(14));
                entite.setEetva(result.getString(16));
                entite.setEecomptebancaire(result.getString(17));
                entite.setLicence(result.getString(18));
            }
            return entite;
        } catch (SQLException sn) {
            renvexception(sn, "[************DOSSIER:INSERT stacktrace: ", tmpool.getConuser());
        }
        return null;
    }

    private void getUserInfo(GeneralePrinting tmp, PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, tmp.getCxusecleunik());
        pstmt.setInt(2, tmp.getClientLmcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Generiquecp infoclient = new Generiquecp();
            infoclient.setCp(result.getString(1));
            infoclient.setLocalité(result.getString(2));
            tmp.setUser(infoclient);
        }
    }
//ajout de Driart le 05/09/2003
    public ArrayList getDossier(int urcleunik, int cleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT d.dr_numdos, d.dr_date_depart, d.dr_date_retour, d.dr_nbrenuit, d.dr_typbooking, d.dr_status FROM dossier d WHERE d.dr_cleunik = ? ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[6];
                for (int j = 0; j < 6; j++)
                    if (j == 1 || j == 2) {
                        Date date = new Date(result.getString(j + 1));
                        tab[j] = date;
                    } else {
                        tab[j] = result.getObject(j + 1);
                    }
                array.add(tab);
            }
            Memo_T memo = selectMemo(cleunik, tmpool);
            if (memo != null)
                array.add(memo);
            else
                array.add("dumy");
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getClient(int urcleunik, int cleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT c.csnom, c.csreference, c.csadresse, c.cstelephonep, c.csgsm, c.csmailprincip,r.Traduction,cp.cxcode,t.txtraduction FROM clients c LEFT JOIN regtva_traduction r ON(c.cstvaregime=r.regtva_cleunik AND r.lmcleunik=?) LEFT JOIN traductioncodpostaux t ON(c.cxcleunik=t.cxcleunik AND t.lmcleunik=?), dossier d,codepostaux cp WHERE d.dr_cleunik = ? AND d.cscleunik = c.cscleunik AND c.cxcleunik=cp.cxcleunik";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(3, cleunik);
            pstmt.setInt(2, lmcleunik);
            pstmt.setInt(1, lmcleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j < 9; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
//la fonction pour trouver les produits d'un dossier
    public ArrayList getProduit(int urcleunik, int cleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT dp.cate_cleunik, dp.prod_cleunik FROM dossier d, dossierproduit dp WHERE d.dr_cleunik = ? ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[2];
                for (int j = 0; j < 2; j++)
                    tab[j] = result.getObject(j + 1); //pour le convertir
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
//la fonction pour chercher les infos d'un produits donnée
    private ResumeClasse getResumeData(String requete, int dr_cleunik, String type, int categorie, Poolconnection tmpool, ArrayList array) throws SQLException {
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, dr_cleunik);
        String requeteValue = "SELECT SUM(hevaleur) FROM historique2 WHERE drcleunik=? AND lignecleunik=? AND ctprocleunik=? AND hedossiercourant='O' and hetypeligne not like 'ACP'";
        String requeteLogBrochure = "SELECT brot_quantite,brot_xlit,brot_commodite,brot_situation,brot_vue,brot_regime,code FROM brochurelogement WHERE bro_cleunik=?";
        String requeteLogHotel = "SELECT hlt_quantite,hlt_xlit,hlt_commodite,hlt_situation,hlt_vue,hlt_regime,code FROM hotellogement WHERE hl_cleunik=?";
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        ResumeClasse resum = null;
        while (result.next()) {
            resum = new ResumeClasse();
            resum.setFournisseur(result.getString(1));
            resum.setPoPnr(result.getString(2));
            resum.setPoPnr(result.getString(2));
            resum.setMemo(result.getString(3));
            resum.setLogement(result.getString(4));
            resum.setDestination(result.getString(5));
            resum.setGroep(result.getString(6));
            resum.setId(result.getLong(7));
            resum.setType(type);
            PreparedStatement pstmt2 = tmpool.getConuser().prepareStatement(requeteValue);
            pstmt2.setLong(1, dr_cleunik);
            pstmt2.setLong(2, resum.getId());
            pstmt2.setInt(3, categorie);
            ResultSet result2 = pstmt2.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                resum.setPrix(result2.getDouble(1) * -1);
            }
            String requetelog = "";
            if (categorie == produit_T.BRO || categorie == produit_T.HO) {
                if (categorie == produit_T.BRO) {
                    requetelog = requeteLogBrochure;
                } else if (categorie == produit_T.HO) {
                    requetelog = requeteLogHotel;
                }
                PreparedStatement pstmt3 = tmpool.getConuser().prepareStatement(requetelog);
                pstmt3.setLong(1, resum.getId());
                ResultSet result3 = pstmt3.executeQuery();
                result3.beforeFirst();
                while (result3.next()) {
                    Object[] tmptab = new Object[7];
                    for (int i = 0; i < 7; i++) {
                        tmptab[i] = result3.getObject(i + 1);
                    }
                    resum.addDescriptif(tmptab);
                }
            }
            array.add(resum);
        }
        return resum;
    }

    public ArrayList getProduitData(int urcleunik, int cleunik, boolean flag) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = new ArrayList();
        ArrayList temp = new ArrayList();
        String[] req = new String[9];
        req[7] = "SELECT f.frnom1, a.ase_num_police, a.ase_memo, NULL, NULL, fg.frgtitrecatalog,a.ase_cleunik FROM assurance a, fournisseur f,  fournisseur_grproduit fg WHERE a.dr_cleunik = ? AND  a.frgt_cleunik=fg.frgtcleunik AND fg.frcleunik=f.frcleunik AND a.annuler=0";
        req[0] = "SELECT f.frnom1, b.bro_po, b.bro_memo, b.bro_logement, b.bro_lieu, fb.frgtitrecatalog,b.bro_cleunik FROM brochure b, fournisseur f,  fournisseur_grproduit fb WHERE b.dr_cleunik = ? AND  b.frgt_cleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  b.annuler=0";
        req[1] = "SELECT f.frnom1, b.bat_pnr,  b.bat_memo, NULL, NULL, fb.frgtitrecatalog,b.bat_cleunik FROM  bateau b,fournisseur f,fournisseur_grproduit fb WHERE b.dr_cleunik = ? AND  b.frgtcleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  b.annuler=0";
        req[2] = "SELECT f.frnom1, v.vl_pnr,  v.vl_memo, NULL, NULL, fb.frgtitrecatalog,v.vl_cleunik FROM car v, fournisseur f,  fournisseur_grproduit fb WHERE v.dr_cleunik = ? AND  v.frgtcleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  v.annuler=0";
        req[5] = "SELECT f.frnom1, h.hl_pnr, h.hl_memo, NULL, h.hl_localite, fb.frgtitrecatalog,h.hl_cleunik FROM hotel h, fournisseur f,  fournisseur_grproduit fb WHERE h.dr_cleunik = ?  AND  h.frgtcleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  h.annuler=0";
        req[6] = "SELECT f.frnom1, t.tax_pnr,  t.tax_memo, NULL, NULL, fb.frgtitrecatalog,t.tax_cleunik FROM taxi t, fournisseur f,  fournisseur_grproduit fb WHERE t.dr_cleunik = ? AND t.frgt_cleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  t.annuler=0";
        req[3] = "SELECT f.frnom1, a.at_pnr, a.at_memo, NULL, NULL, fb.frgtitrecatalog,a.at_cleunik FROM avion_ticket a, fournisseur f,  fournisseur_grproduit fb WHERE a.dr_cleunik = ? AND  a.frgtcleunik=fb.frgtcleunik AND fb.frcleunik=f.frcleunik AND  a.annuler=0";
        req[4] = "SELECT f.frnom1, t.trn_billet, t.trn_memo, NULL, NULL, fb.frgtitrecatalog,t.trn_cleunik FROM train t, fournisseur f, fournisseur_grproduit fb WHERE t.dr_cleunik = ? AND  t.frgtcleunik=fb.frgtcleunik AND fb.frcleunik = f.frcleunik AND  t.annuler=0";
        req[8] = "SELECT f.frnom1, d.div_objet, d.div_memo, NULL, NULL, fb.frgtitrecatalog,d.div_cleunik FROM diversprod d, fournisseur f, fournisseur_grproduit fb WHERE d.dr_cleunik = ? AND  d.frgtcleunik=fb.frgtcleunik AND fb.frcleunik = f.frcleunik AND  d.annuler=0";
        try {
            Object obj = getResumeData(req[7], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("as"), produit_T.AS, tmpool, array);

            obj = getResumeData(req[0], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("bro"), produit_T.BRO, tmpool, array);

            obj = getResumeData(req[1], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("ba"), produit_T.BA, tmpool, array);

            obj = getResumeData(req[2], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("ca"), produit_T.CAR, tmpool, array);

            obj = getResumeData(req[5], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("ho"), produit_T.HO, tmpool, array);

            obj = getResumeData(req[6], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("tax"), produit_T.TAX, tmpool, array);

            obj = getResumeData(req[3], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("av"), produit_T.AV, tmpool, array);

            obj = getResumeData(req[4], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("tr"), produit_T.TR, tmpool, array);

            obj = getResumeData(req[8], cleunik, java.util.ResourceBundle.getBundle("srcastra.astra.sys.ProduitAffichage", tmpool.getLangage()).getString("div"), produit_T.DIV, tmpool, array);

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
/*  for (int i=0; i<8; i++) {
try {
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req[i]);
pstmt.setInt(1, cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
Object[] tab = new Object[result.getMetaData().getColumnCount()+1];
//tab[0] = ;     ici pour remplir le 1er champ avec la categorie (donc type de produit)
if (i==0)
tab[0] = "BRO";
else if (i==1)
tab[0] = "BAT";
else if (i==2)
tab[0] = "CAR";
else if (i==3)
tab[0] = "AV";
else if (i==4)
tab[0] = "TR";
else if (i==5)
tab[0] = "HO";
else if (i==6)
tab[0] = "TAX";
else if (i==7)
tab[0] = "AS";
for(int j=1; j<=result.getMetaData().getColumnCount(); j++)
tab[j] = result.getObject(j); //pour le convertir
array.add(tab);
}
}catch(SQLException sn){
renvexception(sn,"erreur lors de la demande d'info sur l'historique",tmpool.getConuser());
}
}//for*/
        return array;
    }
//la fonction pour obtenir les pasagers
    public ArrayList getPassagers(Loginusers_T user, int cleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(user.getUrcleunik(), true);
        String req,req1;
        ArrayList array = new ArrayList();
        req = "SELECT concat(t.tsabrege,concat(' ',p.pr_nom)), p.pr_prénom, pr_adresse FROM dossier d, passager p, intermediairepassager ip, traductiontitrepers t WHERE d.dr_cleunik = ? AND d.dr_cleunik = ip.dr_cleunik AND ip.pr_cleunik = p.pr_cleunik and p.tscleunik=t.tscleunik and t.lmcleunik=?";
        req1 = "SELECT p.pr_nom, p.pr_prénom, pr_adresse FROM dossier d, passager p, intermediairepassager ip WHERE d.dr_cleunik = ? AND d.dr_cleunik = ip.dr_cleunik AND ip.pr_cleunik = p.pr_cleunik";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, cleunik);
            pstmt.setInt(2,user.getUrlmcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            if (!result.next())
            {
            pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, cleunik);
            //pstmt.setInt(2,user.getUrlmcleunik());
            result = pstmt.executeQuery();
            }
            
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[3];
                for (int j = 0; j < 3; j++)
                    tab[j] = result.getObject(j + 1); //pour le convertir
                array.add(tab);
            }

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
//end ajout

    public ArrayList getPassagerInfo(ArrayList tmp, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        if (tmp != null) {
            try {
                for (int i = 0; i < tmp.size(); i++) {
                    GeneralePrinting gen = (GeneralePrinting) tmp.get(i);
                    PrintingInfo.getLocalite(gen, urcleunik, tmpool, PrintingInfo.PASSAGER);
                    PrintingInfo.getTitle(gen, urcleunik, tmpool, PrintingInfo.PASSAGER);
                    PrintingInfo.getNationalite(gen, urcleunik, tmpool, PrintingInfo.PASSAGER);

                }
            } catch (SQLException sn) {
                renvexception(sn, "erreur lors de la demande d'info sur les passagers", tmpool.getConuser());

            }
        } else
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Array des passager vide");
        return tmp;
    }
    
    public ArrayList getFactureBidon(int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle, h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_dc,helibellecompta2,h.henumpiece,f.frnom1, h.sn_cleunik,h.hedatemouv,j.jota_abrev,jxcleunik, ff.cleunik  FROM historique2 h LEFT JOIN dossier d ON (h.drcleunik=d.dr_cleunik),tvatraduction2 t,journcompta j,fournisseur f, facture ff WHERE (h.hetypeligne = 'ACP' OR h.hetypeligne ='NCA' ) AND h.tva_cleunik=t.tva_cleunik AND h.intervenantcleunik=f.frcleunik AND h.jxcleunik=j.jota_cleunik AND t.lmcleunik=?   AND ff.numpiece = h.henumpiece and ff.he_cleunik=0 ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, tmpool.getLmcleunik());
           
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                AchatCp achatcp = new AchatCp();
                achatcp.setDc(result.getString(17));
                achatcp.setCe_cleunik(result.getInt(5));
                achatcp.getTva1().setTva_id(result.getInt(6));
                achatcp.getTva1().setTva_base(result.getString(3));
                achatcp.getTva1().setTva_value(result.getString(4));
                achatcp.getTva1().setPrixTot(result.getString(2));
                achatcp.getTva1().setTva_rate(result.getString(8));
                achatcp.getTva1().setTva_code(result.getString(15));
                achatcp.setBase(result.getString(3));
                achatcp.setCleprod(result.getLong(9));
                achatcp.setCatProd(result.getInt(11));
                achatcp.setCledossier(result.getLong(10));
                achatcp.setNumdos(result.getString(16));
                achatcp.setFrcleunik(result.getInt(12));
                achatcp.setCommentaire(result.getString(7));
                achatcp.setPoPnr(result.getString(18));
                achatcp.setCeText(result.getString(14));
                achatcp.setNumpiece(result.getString(19));
                achatcp.setFrnom(result.getString(20));
                achatcp.setClefacture(result.getLong(21));
                achatcp.setDateMouv(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(22)));
                achatcp.setJota_abrev(result.getString(23));
                achatcp.setJota_cleunik(result.getInt(24));
                achatcp.setCleunikFact(result.getLong(25));
                
               
// achat.add(achatcp);
                array.add(achatcp);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
    
    
    
//Driart ajout le 6 aout 2003
    public ArrayList getHistoriqueInfoSelonToutes(int urcleunik, String nomf, String piece, String date_m) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle, h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_dc,helibellecompta2,h.henumpiece,f.frnom1, h.sn_cleunik,h.hedatemouv,j.jota_abrev,jxcleunik  FROM historique2 h LEFT JOIN dossier d ON (h.drcleunik=d.dr_cleunik),tvatraduction2 t,journcompta j,fournisseur f WHERE (h.hetypeligne = 'ACP' OR h.hetypeligne ='NCA' ) AND h.tva_cleunik=t.tva_cleunik AND h.intervenantcleunik=f.frcleunik AND h.jxcleunik=j.jota_cleunik AND t.lmcleunik=?   AND f.frnom1 LIKE(CONCAT(?, '%')) AND h.henumpiece LIKE(CONCAT(?, '%')) AND h.hedatemouv LIKE(CONCAT(?, '%')) and d.dr_numdos not like 'ADIV' ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, tmpool.getLmcleunik());
            pstmt.setString(2, nomf);
            pstmt.setString(3, piece);
            pstmt.setString(4, date_m);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                AchatCp achatcp = new AchatCp();
                achatcp.setDc(result.getString(17));
                achatcp.setCe_cleunik(result.getInt(5));
                achatcp.getTva1().setTva_id(result.getInt(6));
                achatcp.getTva1().setTva_base(result.getString(3));
                achatcp.getTva1().setTva_value(result.getString(4));
                achatcp.getTva1().setPrixTot(result.getString(2));
                achatcp.getTva1().setTva_rate(result.getString(8));
                achatcp.getTva1().setTva_code(result.getString(15));
                achatcp.setBase(result.getString(3));
                achatcp.setCleprod(result.getLong(9));
                achatcp.setCatProd(result.getInt(11));
                achatcp.setCledossier(result.getLong(10));
                achatcp.setNumdos(result.getString(16));
                achatcp.setFrcleunik(result.getInt(12));
                achatcp.setCommentaire(result.getString(7));
                achatcp.setPoPnr(result.getString(18));
                achatcp.setCeText(result.getString(14));
                achatcp.setNumpiece(result.getString(19));
                achatcp.setFrnom(result.getString(20));
                achatcp.setClefacture(result.getLong(21));
                achatcp.setDateMouv(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(22)));
                achatcp.setJota_abrev(result.getString(23));
                achatcp.setJota_cleunik(result.getInt(24));
// achat.add(achatcp);
                array.add(achatcp);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueSelonProduit(int urcleunik, long cleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND h.hetypeligne = 'ACP' AND h.lignecleunik =?";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setLong(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    if (j == 2) {
                        srcastra.astra.sys.classetransfert.utils.Date date = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(j + 1));
                        tab[j] = date;
                    } else
                        tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueVenteSelonProduit(int urcleunik, long cleunik, long drcleunik, int ctprocleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND h.hetypeligne = 'D' AND h.lignecleunik =? AND h.drcleunik=? AND h.ctprocleunik=? AND h.sn_cleunik=0 AND h.hedossiercourant='O'";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setLong(1, cleunik);
            pstmt.setLong(2, drcleunik);
            pstmt.setInt(3, ctprocleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    if (j == 2) {
                        srcastra.astra.sys.classetransfert.utils.Date date = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(j + 1));
                        tab[j] = date;
                    } else
                        tab[j] = result.getObject(j + 1);
                array.add(tab);
            }

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
//Driart ajout le 4ème aout 2003
    public ArrayList getHistoriqueInfoSelonFournisseur(int urcleunik, int frcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND f.frcleunik = ? AND h.hetypeligne = 'A'  ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, frcleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    if (j == 2) {
                        srcastra.astra.sys.classetransfert.utils.Date date = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(j + 1));
                        tab[j] = date;
                    } else
                        tab[j] = result.getObject(j + 1);
                array.add(tab);
            }

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueInfoSelonDate(int urcleunik, String date) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;

        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE h.hedatemouv LIKE(CONCAT(?, '%')) AND h.hetypeligne = 'A' AND f.frcleunik = h.intervenantcleunik";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setString(1, date);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueInfoSelonPiece(int urcleunik, String piece) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;

        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE h.henumpiece LIKE(CONCAT(?, '%')) AND h.hetypeligne = 'A' AND f.frcleunik = h.intervenantcleunik";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setString(1, piece);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueInfoSelonFournisseurEtDate(int urcleunik, String date, int frcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;

        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND f.frcleunik = ? AND h.hedatemouv LIKE(CONCAT(?, '%')) AND h.hetypeligne = 'A'";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setString(2, date);
            pstmt.setInt(1, frcleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueInfoSelonFournisseurEtPiece(int urcleunik, String piece, int frcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;

        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND f.frcleunik = ? AND h.henumpiece LIKE(CONCAT(?, '%')) AND h.hetypeligne = 'A'";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setString(2, piece);
            pstmt.setInt(1, frcleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }

    public ArrayList getHistoriqueInfoSelonDateEtPiece(int urcleunik, String piece, String date) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;

        ArrayList array = new ArrayList();
        req = "SELECT h.hecleunik, h.henumpiece, h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, f.frnom1 FROM historique2 h, fournisseur f WHERE f.frcleunik = h.intervenantcleunik AND h.henumpiece LIKE(CONCAT(?, '%')) AND h.hetypeligne = 'A' AND  h.hedatemouv LIKE(CONCAT(?, '%')) ";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setString(1, piece);
            pstmt.setString(2, date);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[9];
                for (int j = 0; j <= 8; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return array;
    }
// Driart ajout le 1èr aout 2003
    public ArrayList getProduitsInfoSelonDossier(int urcleunik, String numdos) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String[] req = new String[8];
        ArrayList array = new ArrayList();

        req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = a.dr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))   ";
        req[1] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = t.dr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[2] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = t.dr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[3] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = h.dr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[4] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = car.dr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[5] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = b.dr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[6] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = b.dr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%'))";
        req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = a.dr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_numdos LIKE(CONCAT(?, '%')) ";
        try {
            for (int i = 0; i < 8; i++) {
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req[i]);
                pstmt.setString(1, numdos);
                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    Object[] tab = new Object[12];
                    for (int j = 0; j <= 10; j++) {
                        tab[j] = result.getObject(j + 1);

                    }
                    if (i == 0)
                        tab[11] = new Integer(produit_T.AS);
                    else if (i == 1)
                        tab[11] = new Integer(produit_T.TR);
                    else if (i == 2)
                        tab[11] = new Integer(produit_T.TAX);
                    else if (i == 3)
                        tab[11] = new Integer(produit_T.HO);
                    else if (i == 4)
                        tab[11] = new Integer(produit_T.CAR);
                    else if (i == 5)
                        tab[11] = new Integer(produit_T.BRO);
                    else if (i == 6)
                        tab[11] = new Integer(produit_T.BA);
                    else
                        tab[11] = new Integer(produit_T.AV);

                    array.add(tab);

                }
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur les produits", tmpool.getConuser());
        }

        return array;
    }

    public ArrayList getProduitsInfoSelonPassager(int urcleunik, String prnom) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String[] req = new String[8];
        ArrayList array = new ArrayList();


        req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[1] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[2] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[3] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[4] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[5] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[6] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND p.pr_nom LIKE(CONCAT(?, '%'))";
        try {
            for (int i = 0; i < 8; i++) {
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req[i]);
                pstmt.setString(1, prnom);
                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    Object[] tab = new Object[12];
                    for (int j = 0; j <= 10; j++)
                        tab[j] = result.getObject(j + 1);
                    if (i == 0)
                        tab[11] = new Integer(produit_T.AS);
                    else if (i == 1)
                        tab[11] = new Integer(produit_T.TR);
                    else if (i == 2)
                        tab[11] = new Integer(produit_T.TAX);
                    else if (i == 3)
                        tab[11] = new Integer(produit_T.HO);
                    else if (i == 4)
                        tab[11] = new Integer(produit_T.CAR);
                    else if (i == 5)
                        tab[11] = new Integer(produit_T.BRO);
                    else if (i == 6)
                        tab[11] = new Integer(produit_T.BA);
                    else
                        tab[11] = new Integer(produit_T.AV);

                    array.add(tab);

                }
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur les produits", tmpool.getConuser());
        }

        return array;
    }
//ajout de Driart le 28 aout 2003
//Les 3 fonctions pour manipuler l'agenda
//Ces fonctions servent pour obtenir, inserer et modifier respectivement l'agenda
    public ArrayList getAgendaPourDate(int urcleunik, String date) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1;
        ArrayList array1 = new ArrayList();
        req1 = "SELECT a.cle_unik, a.traite, a.cle_doss, a.cle_achat, a.remarques FROM agenda a WHERE a.date = ? AND (a.cle_user = ? OR a.cle_user = 0)";

        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setString(1, date.toString());
            pstmt.setInt(2, urcleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[5];
                for (int j = 0; j < 5; j++)
                    tab[j] = result.getObject(j + 1);
                array1.add(tab);
            }

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }

        return array1;
    }

    public void insertPersonalAgendaPourDate(int urcleunik, String date, String remarques, int cle_doss, int cle_achat) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1;
        ArrayList array1 = new ArrayList();
        req1 = "INSERT INTO 'agenda' ('cle_doss', 'cle_achat', 'date', 'remarques', 'cle_user') VALUES ('?', '?', '?', '?', '?')";

        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, cle_doss);
            pstmt.setInt(2, cle_achat);
            pstmt.setString(3, date);
            pstmt.setString(4, remarques);
            pstmt.setInt(5, urcleunik);

            pstmt.execute();

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }

    }

    public void insertPublicAgendaPourDate(int urcleunik, String date, String remarques, int cle_doss, int cle_achat) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1;
        ArrayList array1 = new ArrayList();
        req1 = "INSERT INTO 'agenda' ('cle_doss', 'cle_achat', 'date', 'remarques', 'cle_user', 'traite') VALUES ('?', '?', '?', '?', '0', '0')";
//par defaut l'agenda n'est pas traite, par consequent on met 0
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, cle_doss);
            pstmt.setInt(2, cle_achat);
            pstmt.setString(3, date);
            pstmt.setString(4, remarques);
//le cle_user pour une tache publique est 0

            pstmt.execute();

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }

    }

    public void updateAgendaPourDate(int urcleunik, String date, String remarques, int cle_doss, int cle_achat, int traite, int cle_unik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1;
        ArrayList array1 = new ArrayList();
        req1 = "UPDATE agenda set 'cle_doss'=?, 'cle_achat'=?, 'remarques'=?, 'traite'=? WHERE (cle_user = ? OR cle_user = '0') AND date = ? AND cle_unik = ?";

        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, cle_doss);
            pstmt.setInt(2, cle_achat);
            pstmt.setString(5, date);
            pstmt.setString(3, remarques);
            pstmt.setInt(4, urcleunik);
            pstmt.setInt(5, traite);

            pstmt.execute();

        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }

    }
//end ajout
//ajout de Driart le 19 aout 2003
    
    public ArrayList getListingJournalOD(int urcleunik,String dateMouvDeb,String dateMouvFin, String entite) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String reqVente;
        
        ArrayList array=new ArrayList();
        
        reqVente="Select h.henumpiece, h.hedatemouv, d.dr_numdos, d.dr_date_depart, c.csnom, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.he_exported from historique2 h, dossier d, clients c, journcompta j, entite e where h.drcleunik=d.dr_cleunik and h.intervenantcleunik=c.cscleunik and h.henumpiece >0 and h.jxcleunik = j.jota_cleunik and j.jota_categorie=? and j.eecleunik = e.eecleunik and e.eeabrev like concat(?,'%') and  (h.hedatemouv <=? and h.hedatemouv >=?) and h.ce_cleunik like '40%' order by e.eeabrev,h.henumpiece";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(reqVente);
            
            
            pstmt.setInt(1,24);
            pstmt.setString(2, entite);
            pstmt.setString(3, dateMouvFin);
            pstmt.setString(4, dateMouvDeb);
            
             ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[9];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
    
    public ArrayList getListingJournalV(int urcleunik,String dateMouvDeb,String dateMouvFin, String entite, boolean nc ) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String reqVente;
        
        ArrayList array=new ArrayList();
        
        reqVente="Select h.henumpiece, h.hedatemouv, d.dr_numdos, d.dr_date_depart, c.csnom, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.he_exported from historique2 h, dossier d, clients c, journcompta j, entite e where h.drcleunik=d.dr_cleunik and h.intervenantcleunik=c.cscleunik and h.henumpiece >0 and h.jxcleunik = j.jota_cleunik and j.jota_categorie=? and j.eecleunik = e.eecleunik and e.eeabrev like concat(?,'%') and  (h.hedatemouv <=? and h.hedatemouv >=?) and h.ce_cleunik like '40%' order by e.eeabrev,h.henumpiece";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(reqVente);
            
            if(nc) 
                pstmt.setInt(1,18);
            else
                pstmt.setInt(1,21);
            
            pstmt.setString(2, entite);
            pstmt.setString(3, dateMouvFin);
            pstmt.setString(4, dateMouvDeb);
            
             ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[9];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
    
    
     public ArrayList getListingFournisseur(int urcleunik,String de,String a, int lm) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String reqVente;
        
        ArrayList array=new ArrayList();
        
        reqVente="select frnom1,  frreference1, frreference2 , fradresse, c.cxcode, cp.txtraduction, ff.frgtitrecatalog, gd.gnnbravanteche from fournisseur f , fournisseur_grproduit ff, traductioncodpostaux cp, codepostaux c, groupedecision  gd where cp.cxcleunik=f.cxcleunik and cp.lmcleunik=? and c.cxcleunik=f.cxcleunik and f.frcleunik = ff.frcleunik and ff.frgtcleunik=gd.frgtcleunik and (frnom1 between concat(?,'%') and concat(?,'ZZZZZZZZZZ')  ) order by frnom1";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(reqVente);
            
            
            pstmt.setInt(1, lm);
            pstmt.setString(2, de);
            pstmt.setString(3, a);
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[8];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
     
     public ArrayList getListingEmbarquement(int urcleunik,String dossBase, int lm) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        
        ArrayList array=new ArrayList();
        
        req="select d.dr_numdos, p.pr_nom,  b.de_cleunik, b.bro_freeembarq, ed.tremb_traduction, bro_free_destination, td.tn_traduction from dossier d, passager p, intermediairepassager ip,  brochure b left join traduction_embarqdebarq ed on b.emb_cleunik_1 = ed.emb_cleunik  and ed.lmcleunik=? left join traduction_destination td on b.de_cleunik = td.dn_cleunik and td.lmcleunik=0 where d.dr_cleunik = ip.dr_cleunik and p.pr_cleunik = ip.pr_cleunik and d.dr_numdos like ? and d.dr_numdos not like ? and d.dr_cleunik = b.dr_cleunik order by p.pr_nom ";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            
            
            pstmt.setInt(1, lm);
            pstmt.setString(2, dossBase+"%");
            pstmt.setString(3, dossBase+"%G0");
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[7];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
    
     public ArrayList getListingDebarquement(int urcleunik,String dossBase, int lm) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req;
        
        ArrayList array=new ArrayList();
        
        req=req="select d.dr_numdos, p.pr_nom,  b.de_cleunik, b.bro_freederbaq, ed.tremb_traduction, bro_free_destination, td.tn_traduction from dossier d, passager p, intermediairepassager ip,  brochure b left join traduction_embarqdebarq ed on b.emb_cleunik_2 = ed.emb_cleunik  and ed.lmcleunik=? left join traduction_destination td on b.de_cleunik = td.dn_cleunik and td.lmcleunik=0 where d.dr_cleunik = ip.dr_cleunik and p.pr_cleunik = ip.pr_cleunik and d.dr_numdos like ? and d.dr_numdos not like ? and d.dr_cleunik = b.dr_cleunik order by p.pr_nom ";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            
            
            pstmt.setInt(1, lm);
            pstmt.setString(2, dossBase+"%");
            pstmt.setString(3, dossBase+"%G0");
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[7];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
     
     public ArrayList getListingJournalA(int urcleunik,String dateMouvDeb,String dateMouvFin, String entite, boolean nc ) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String reqVente;
        
        ArrayList array=new ArrayList();
        
        reqVente="Select h.henumpiece, h.hedatemouv, d.dr_numdos, d.dr_date_depart, c.csnom, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.he_exported from historique2 h, dossier d, clients c, journcompta j, entite e where h.drcleunik=d.dr_cleunik and d.cscleunik=c.cscleunik and h.henumpiece >0 and h.jxcleunik = j.jota_cleunik and j.jota_categorie=? and j.eecleunik = e.eecleunik and e.eeabrev like concat(?,'%') and  (h.hedatemouv <=? and h.hedatemouv >=?) order by e.eeabrev,h.henumpiece";
        
        try{
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(reqVente);
            
            if(nc) 
                pstmt.setInt(1,19);
            else
                pstmt.setInt(1,20);
            
            pstmt.setString(2, entite);
            pstmt.setString(3, dateMouvFin);
            pstmt.setString(4, dateMouvDeb);
            
            System.out.println("Je passe par ici");
            
             ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[9];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      return array;
      
        
    }
     
    public ArrayList getListingChiffreAffaireFournisseur(int urcleunik,String dateDepartDeb,String dateDepartFin,String fde,String fa,String entite) throws RemoteException, ServeurSqlFailure
    {
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
        ArrayList array=new ArrayList();
        
        String req1="SELECT fournisseur.frcleunik,fournisseur.`frnom1`,fournisseur_grproduit.frgtitrecatalog,sum(h.hevaleur*-1),fournisseur_grproduit.frgtcleunik FROM fournisseur_grproduit,dossierproduit dp,dossier d,`historique2` h,fournisseur WHERE ((d.dr_cleunik = dp.dr_cleunik) and (h.lignecleunik = dp.prod_cleunik) and  (h.ctprocleunik = dp.cate_cleunik) and  (dp.fr_cleunik = fournisseur.frcleunik) and  (dp.frgt_cleunik = fournisseur_grproduit.frgtcleunik) and (fournisseur.frreference1 >=? and fournisseur.frreference1 <=?) and (d.dr_date_depart>=? and d.dr_date_depart<=?) and d.dr_numdos like concat(?,'%') ) group by fournisseur_grproduit.frgtcleunik order by fournisseur.frnom1,fournisseur_grproduit.frgtitrecatalog,fournisseur.frcleunik";
        String req2="SELECT fournisseur.frcleunik,fournisseur.`frnom1`,fournisseur_grproduit.frgtitrecatalog,sum(h.hevaleur*-1),fournisseur_grproduit.frgtcleunik FROM fournisseur_grproduit,dossierproduit dp,dossier d,`historique2` h,fournisseur WHERE ((d.dr_cleunik = dp.dr_cleunik) and (h.lignecleunik = dp.prod_cleunik) and  (h.ctprocleunik = dp.cate_cleunik) and  (dp.fr_cleunik = fournisseur.frcleunik) and  (dp.frgt_cleunik = fournisseur_grproduit.frgtcleunik) and (d.dr_date_depart>=? and d.dr_date_depart<=?) and d.dr_numdos like concat(?,'%')) group by fournisseur_grproduit.frgtcleunik order by fournisseur.frnom1,fournisseur_grproduit.frgtitrecatalog,fournisseur.frcleunik";
        String requete="";
        
        boolean fourn=false;
        
        requete=req2;
        
        if (fde.length()>0 && fa.length()>0)
        {
            requete=req1;
            fourn=true;
        }
        
        
        
        try{
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        
            if(fourn)
            {
                pstmt.setString(1,fde);
                pstmt.setString(2,fa);
                pstmt.setString(3,dateDepartDeb);
                pstmt.setString(4,dateDepartFin);
                pstmt.setString(5,entite);
            }
            else
            {
                pstmt.setString(1,dateDepartDeb);
                pstmt.setString(2,dateDepartFin);
                pstmt.setString(3,entite);
                
            }
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            
            int fcleunik=-1;
            Object[] tab = null;
            double somme=0;
            
            while(result.next())
            {
                
                
                if(result.getInt(1) != fcleunik)
                {
                    fcleunik=result.getInt(1);
                    if(tab!=null)
                    {
                        
                        tab = new Object[8];
                        tab[0]=new String("");
                        tab[1]=new String("");
                        tab[2]=new Double(somme);
                        
                        array.add(tab);
                        
                    }
                     
                    tab=new Object[8];
                    
                    System.out.println(result.getObject(4).toString());
                    somme=result.getDouble(4);
                    tab[0]=result.getObject(2);
                    tab[1]=result.getObject(3);
                    tab[2]=result.getObject(4);
                    tab[6]=result.getObject(1);
                    tab[7]=result.getObject(5);
                    
                    array.add(tab);
                }
                
                else
                {
                    tab = new Object[8];
                    tab[0]=new String("");
                    tab[1]=result.getObject(3);
                    tab[2]=result.getObject(4);
                    tab[6]=result.getObject(1);
                    tab[7]=result.getObject(5);
                    somme=somme+result.getDouble(4);
                    
                    array.add(tab);
                }    
                  
                
                
            }
           tab = new Object[8];
                        tab[0]=new String("");
                        tab[1]=new String("");
                        tab[2]=new Double(somme);     
         array.add(tab);   
            
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        
        
        
        
        
        return array;
        
    }
    
     
    public ArrayList getListingChiffreAffaireClient(int urcleunik, String dateCreationDeb, String dateCreationFin, String bureau) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
        ArrayList array = new ArrayList();
        
        String req1 = "SELECT c.cscleunik,c.csnom,c.csadresse,d.dr_totalprix,d.dr_facture,cp.cxintitulegen,cp.cxcode,d.dr_datetimecrea FROM codepostaux cp,dossier d,clients c WHERE ((c.cscleunik = d.cscleunik)and (cp.cxcleunik=c.cxcleunik) and (d.dr_datetimecrea >=? and d.dr_datetimecrea <=?) and d.dr_numdos like concat(?,'%')) ORDER BY 2";
        
        try {
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req1);
            pstmt.setString(1,dateCreationDeb);
            pstmt.setString(2,dateCreationFin);
            pstmt.setString(3,bureau);
            
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            
            int last=-1;
            
            Object[] tab = null;
            int cpt=0;
            while(result.next())
            {
                int cliCourant = result.getInt(1);
                
                if(cliCourant != last)
                {
                    cpt++;
                    last = cliCourant;
                    
                    if(tab !=null)
                    {
                        tab[7]=new Integer(cpt);
                        cpt=0;
                        array.add(tab);
                    }
                    
                    tab = new Object[12];
                    tab[0]=result.getObject(1);
                    tab[1]=result.getObject(2);
                    tab[2]=result.getObject(3);
                    tab[3]=result.getObject(7);
                    tab[4]=result.getObject(6);
                    
                    tab[5]=new Double(result.getDouble(4));
                    if(result.getString(5).equals("1"))
                    tab[6]=new Double(result.getDouble(4));
                    else
                    tab[6]=new Double(0);
                    
                    
                }
                else
                {
                    last= cliCourant;
                    tab[5]=new Double(Double.valueOf(tab[5].toString()).doubleValue()+result.getDouble(4));
                    if(result.getString(5).equals("1"))
                        tab[6]=new Double(Double.valueOf(tab[6].toString()).doubleValue()+result.getDouble(4));
                    
                    cpt++;
                }
                
            }
            
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
         
        return array;
        
        
    }
    
    public ArrayList getListingRentabilite1(int urcleunik, String dateDep, String dateFin, String bureau, String dateCreDep, String dateCreFin) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1, req2,req3;
        ArrayList array1 = new ArrayList();
        
         req2="SELECT d.dr_numdos, d.dr_date_depart, h.henumpiece, (h.hevaleur*-1) ,h.hetypeligne,h.lignecleunik,h.henotcpt,h.ctprocleunik, d.dr_facture FROM journcompta j, compte c1, compte c2, dossier d, fournisseur f, historique2 h LEFT JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 2 AND h.intervenantcleunik = f.frcleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik and ((d.dr_date_depart >=? and d.dr_date_depart <=?) and (d.dr_datetimecrea >=? and d.dr_datetimecrea<=?)) and dr_numdos like concat(?,'%') AND (h.hetypeligne='ACP' OR h.hetypeligne='NCA') ORDER by d.dr_date_depart, d.dr_numdos";
        
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req2);
            pstmt.setString(1, dateDep);
            pstmt.setString(2, dateFin);
            pstmt.setString(5, bureau);
            pstmt.setString(3, dateCreDep);
            pstmt.setString(4, dateCreFin);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[9]; //c'était 21
                for (int j = 0; j < tab.length; j++)
                {   
                    tab[j] = result.getObject(j+1);
                    if (j==7) 
                    {
                        int typeProd=Integer.valueOf(tab[j].toString()).intValue();
                        int lignecleunik=Integer.valueOf(tab[5].toString()).intValue();
                        String requete="";
                        
                        switch (typeProd)
                        {
                                    //avion
                            case 1: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, avion_ticket a where a.at_cleunik=? and a.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 2: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, brochure b where b.bro_cleunik=? and b.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 3: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, hotel h where h.hl_cleunik=? and h.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 4: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, taxi t where t.tax_cleunik=? and t.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 5: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, bateau b where b.bat_cleunik=? and b.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 6: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, voiture v where v.vl_cleunik=? and v.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik"; 
                                    break;
                            case 7: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, train t where t.trn_cleunik=? and t.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 8: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, assurance a where a.ase_cleunik=? and a.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 9: typeProd=9;
                                    break;
                            case 10: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, car c where c.vl_cleunik=? and c.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 11: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, diversprod d where d.div_cleunik=? and d.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            default: typeProd=-1;
                                     break;
                            
                            
                        }
                        
                        if(typeProd !=-1 && typeProd !=9)
                        {
                        PreparedStatement pstmt1 = tmpool.getConuser().prepareStatement(requete);
                        System.out.println("**"+lignecleunik);
                        System.out.println("**tp"+typeProd);
                        pstmt1.setInt(1, lignecleunik);
                      //  pstmt1.setInt(2, );
                     //   pstmt1.setString(3, bureau);
                        ResultSet result1 = pstmt1.executeQuery();
                        result1.beforeFirst();
                        if(result1.next())
                        
                        tab[5]=result1.getObject(1);
                        
                        //String s=tab[5].toString();
                        tab[7]=result1.getObject(2);
                        //s=s+"|"+tab[7].toString();
                        //tab[7]=s;
                        
                        System.out.println("fourn"+tab[5].toString());
                        }
                        else if (typeProd==9)
                        {
                        tab[5] = new String("**"); 
                        tab[7] = new String("**"); 
                        }
                        else if (typeProd==-1)
                        {
                        tab[5] = new String("");    
                        tab[7] = new String("");  
                        }
                    }
                    
                }
                array1.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
         
            return array1;
        
    }
    
    private void addTask(Poolconnection tmpool,ResultSet result,String libelle) throws ServeurSqlFailure     {
        try{
                                 
                 Task_T t =new Task_T();

                    while (result.next()) {
                        //Object[] tab = new Object[9]; //c'était 21
                        //for (int j = 0; j < tab.length; j++)
                        //{   
                        //tab[j] = result.getObject(j+1);
                         
                        t.setAnnuler(0);
                        t.setDr_cleunik(-1);
                        t.setCscleunikcont(result.getLong(9));
                        t.setCscleunifact(result.getLong(10));
                        
                        t.setTask_debut(CalculDate.getTodayDate());
                        t.setTask_echeance(CalculDate.getTodayDate());
                        t.setTask_object(libelle);
                        t.setTask_memo(libelle);
                        t.setTask_numdos(result.getString(1));
                        
                        t.setTask_message(libelle);
                        
                        int save=tmpool.getUrcle2();
                        
                        
                        tmpool.setUrcle2(result.getInt(11));
                                                
                        new srcastra.astra.sys.rmi.Task_manage().insertTask(t, tmpool);
                        
                        tmpool.setUrcle2(save);
                        
                        //}
                    }
                 
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
        
        
        
        
    }
    
    public int getNumService(int urcleunik,String doss) throws RemoteException, ServeurSqlFailure
    {
        int nb=0;
        
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
        PreparedStatement pstmt;
       
        
        String requ="select count(*) from dossier where dr_numdos like concat(?,'%')";
        
        try{
        pstmt = tmpool.getConuser().prepareStatement(requ);
                     pstmt.setString(1,doss);
                     pstmt.execute();

                     ResultSet result = pstmt.executeQuery();
                     result.beforeFirst();
                     result.next();
                     nb=result.getInt(1);
                     
         } catch (SQLException sn) {
                renvexception(sn, "erreur", tmpool.getConuser());

                }
        
        
        return nb;
        
    }
    
       public void setVerifAgenda(int urcleunik) throws RemoteException, ServeurSqlFailure {
           
           boolean suite=false;
           Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
           PreparedStatement pstmt;
           
           String requeteParam = "select paramAgenda,j1,j2,j3,j4,j5,j6,j7,j8 from configuration1";
           
           String requete="delete from task where dr_cleunik=-1";
           String test="select count(*) as cpt from task where dr_cleunik=-1 and task_debut=current_date";
           
           
           //pas d'acompte 5 jours après le booking
           
           String requete1="select dr_numdos, dr_date_depart, dr_datetimecrea,  ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(dr_datetimecrea)) / 3600 / 24) as diff, dr_facture, dr_paye, dr_solde, dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(dr_datetimecrea)) / 3600 / 24)  >= ? and dr_paye = 0  and dr_solde=0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') ";
           
           //dossier non factures 14 jours avant le depart
           
           String requete2="select dr_numdos, dr_date_depart,dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, dr_facture,dr_paye,dr_solde,dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  < ? and dr_facture = 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N')";
           
           //dossier non soldes 5 jorus avant le depart
           
           String requete3="select dr_numdos, dr_date_depart, dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, dr_facture,dr_paye,dr_solde,dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  < ? and  dr_solde=0 and (dr_totalprix - dr_paye) >=1 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N')";
           
           //documents 8 jours apres le booking
           
           String requete4="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea,((UNIX_TIMESTAMP(current_date)-UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24)  >= ? and pd.frdtnbrconfprev <> 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // documents 10 jours avant le départ
           
           String requete5="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrdocprev <> 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // facture acompte x jours apres le booking
           
           String requete6="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24)  >= ? and pd.frdtnbrfactprev <> 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // note de crédit 
           
           String requete7="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrncprev <> 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           //facture solde
           
           String requete8="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrfactsprev <> 0 and dr_totalprix<>0 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N') group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           //dossier RQ
           
           String requete9="select dr_numdos, dr_date_depart, dr_datetimecrea,  ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(dr_datetimecrea)) / 3600 / 24) as diff, dr_facture, dr_paye, dr_solde, dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where dr_status=1 and (substring(dr_numdos,9,1) <> 'F' and substring(dr_numdos,9,1) <> 'N')";
           
           String message1="",message2="",message3="",message4="",message5="",message6="",message7="",message8="";
           
           
           
           double param=0;
           
           int j1=0,j2=0,j3=0,j4=0,j5=0,j6=0,j7=0,j8=0;
           
            
                try{
                 pstmt = tmpool.getConuser().prepareStatement(requeteParam);
                 
                 pstmt.execute();
                 
                 ResultSet result = pstmt.executeQuery();
                 result.beforeFirst();
                 
                 result.next();
                 
                 if(result.getDouble(1) != -1)
                 {    
                     suite=true;
                     param=result.getDouble(1);
                     j1=result.getInt(2);
                     j2=result.getInt(3);
                     j3=result.getInt(4);
                     j4=result.getInt(5);
                     j5=result.getInt(6);
                     j6=result.getInt(7);
                     j7=result.getInt(8);
                     j8=result.getInt(9);
                     
                     message1 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag1");
                     message1+=" ("+j1+") "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag1_1");
                     message2=java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag2");
                     message2+=" ("+j2+") "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag2_1");
                     message3=java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag3");
                     message3+=" ("+j3+") "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag3_1");
                     message4 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4");
                     message5 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_1");
                     message6 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_2");
                     message7 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_3");
                     message8 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_4");
                 
                 
                 }
                 if(suite)
                 {    
                    
                    pstmt = tmpool.getConuser().prepareStatement(test);
                 //pstmt.setString(1,CalculDate.getTodayDate());
                 
                    pstmt.execute();
                 
                    result = pstmt.executeQuery();
                    result.beforeFirst();
                 
                     result.next();
                 
                     if(result.getInt(1) ==0)
                     {
                         pstmt = tmpool.getConuser().prepareStatement(requete);
                         pstmt.execute();
                         suite=true;
                     }
                     else
                         suite=false;

                 }
                  
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
            
                if(suite)
                {    
                try{
                    
                    if(param%2 !=0)
                    {
                     pstmt = tmpool.getConuser().prepareStatement(requete1);
                     pstmt.setInt(1,j1);
                     pstmt.execute();

                     ResultSet result = pstmt.executeQuery();
                     result.beforeFirst();

                     this.addTask(tmpool, result, message1);   
                    }    
                    
                    
                    if(param==2 || param==3 || param==6 || param==7 || param==10 || param==11 || param==14 || param==15)
                    { 
                    pstmt = tmpool.getConuser().prepareStatement(requete2);
                    pstmt.setInt(1,j2);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message2);
                    }
                    
                    if( (param >=4 && param <=7) || (param>=12 && param<=15) )
                    {    
                    pstmt = tmpool.getConuser().prepareStatement(requete3);
                    pstmt.setInt(1,j3);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message3);        
                    }     
                    
                    if(param >=8 && param <=15)
                    {
                        
                    pstmt = tmpool.getConuser().prepareStatement(requete4);
                    pstmt.setInt(1,j4);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message4);
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete5);
                    pstmt.setInt(1,j5);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message5);
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete6);
                    pstmt.setInt(1,j6);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message6);
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete7);
                    pstmt.setInt(1,j7);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message7);
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete8);
                    pstmt.setInt(1,j8);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, message8);
                    
                    }
               
                 
                 
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
            
                }
           
         if(suite)
         {
       try{
            pstmt = tmpool.getConuser().prepareStatement(requete9);
                    //pstmt.setInt(1,j8);
                    pstmt.execute();
                    
                     ResultSet result=pstmt.executeQuery();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    this.addTask(tmpool,result, "Doss. RQ");
       
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
         }  
           
           
           
       }
       
       
       
       public String getQuantite(int urcleunik, String drnumdos) throws RemoteException, ServeurSqlFailure {
       
           Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
           
           ArrayList array=new ArrayList();
           
           String requeteTotal="select gp.frgtcleunik, gp.frgtitrecatalog, b.pax from brochure b, fournisseur_grproduit gp, dossier d where  d.dr_numdos like ? and d.dr_cleunik = b.dr_cleunik  and b.annuler=0 and b.frgt_cleunik = gp.frgtcleunik";
           String requeteDetail="select sum(b.pax), gp.frgtcleunik from brochure b, fournisseur_grproduit gp, dossier d where  d.dr_numdos not like ? and d.dr_numdos like ? and d.dr_cleunik = b.dr_cleunik  and b.annuler=0 and b.frgt_cleunik = gp.frgtcleunik group by  frgtcleunik";
           
           try{
              PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requeteTotal);
            

            pstmt.setString(1, drnumdos);
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                    Object[] tab = new Object[4];
                    //for (int j = 0; j < 3; j++)
                    //    tab[j] = result.getObject(j + 1);
                    tab[0]=new Integer(result.getInt(1));
                    tab[1]=new String(result.getString(2));
                    tab[2]=new Integer(result.getInt(3));
                    tab[3]=new Integer(0);
                    
                    array.add(tab);
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
           
            try{
               PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requeteDetail);
            

            pstmt.setString(1, drnumdos);
            pstmt.setString(2, drnumdos.substring(0,8)+"%");
            
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                
                    int cat =result.getInt(2);
                    
                    int i = 0;
                    
                    boolean trouve=false;
                    
                    while(!trouve && i<array.size())
                    {
                        Object[] tmp =(Object[]) array.get(i);
                        
                        if(Integer.valueOf(tmp[0].toString()).intValue()==cat)
                        {
                            trouve=true;
                            
                            tmp[3]=new Integer(Integer.valueOf(tmp[3].toString()).intValue()+result.getInt(1));
                            array.set(i, tmp);
                            
                        }    
                            
                        i++;
                        
                    }
                
                   
                }
      } catch (SQLException sn) {
        renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
      }
        
      String out="";
      
      for(int i=0;i<array.size();i++)
      {
          Object[] tmp =(Object[]) array.get(i);
          
          out=tmp[1].toString()+"\t"+"\t"+tmp[2].toString()+"\t"+tmp[3].toString()+"\n";
          
          
          
      }    
           
      return out;
           
           
           
              
       }
    
    public String getAnomalieDossier(int urcleunik, int drcleunik, boolean test) throws RemoteException, ServeurSqlFailure {
           
           boolean suite=false;
           Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
           PreparedStatement pstmt;
           
           String requeteParam = "select paramAgenda,j1,j2,j3,j4,j5,j6,j7,j8 from configuration1";
           
           
           //pas d'acompte 5 jours après le booking
           
           String requete1="select dr_numdos, dr_date_depart, dr_datetimecrea,  ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(dr_datetimecrea)) / 3600 / 24) as diff, dr_facture, dr_paye, dr_solde, dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(dr_datetimecrea)) / 3600 / 24)  >= ? and dr_paye = 0  and dr_solde=0 and dr_cleunik=?";
           
           //dossier non factures 14 jours avant le depart
           
           String requete2="select dr_numdos, dr_date_depart,dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, dr_facture,dr_paye,dr_solde,dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  < ? and dr_facture = 0 and dr_cleunik=?";
           
           //dossier non soldes 5 jorus avant le depart
           
           String requete3="select dr_numdos, dr_date_depart, dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, dr_facture,dr_paye,dr_solde,dr_totalprix,cscleunik,cscleunik_fact,urcleunik from dossier where ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  < ? and  dr_solde=0 and (dr_totalprix - dr_paye) >=1 and dr_cleunik=?";
           
           //documents 8 jours apres le booking
           
           String requete4="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea,((UNIX_TIMESTAMP(current_date)-UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24)  >= ? and pd.frdtnbrconfprev <> 0 and d.dr_cleunik=? group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // documents 10 jours avant le départ
           
           String requete5="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrdocprev <> 0 and d.dr_cleunik=? group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // facture acompte x jours apres le booking
           
           String requete6="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(current_date)  - UNIX_TIMESTAMP(d.dr_datetimecrea)) / 3600 / 24)  >= ? and pd.frdtnbrfactprev <> 0 and d.dr_cleunik=? group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           // note de crédit 
           
           String requete7="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrncprev <> 0 and d.dr_cleunik=? group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           //facture solde
           
           String requete8="select d.dr_numdos, d.dr_date_depart, d.dr_datetimecrea, ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24) as diff, d.dr_facture,d.dr_paye,d.dr_solde,d.dr_totalprix,d.cscleunik,d.cscleunik_fact,d.urcleunik from dossier d, historique2 h, produit_document pd where h.lignecleunik=pd.lignecleunik and pd.cate_pro = h.ctprocleunik and h.drcleunik = d.dr_cleunik and ((UNIX_TIMESTAMP(dr_date_depart)  - UNIX_TIMESTAMP(current_date)) / 3600 / 24)  <= ? and pd.frdtnbrfactsprev <> 0 and d.dr_cleunik=? group by d.dr_numdos, pd.cate_pro order by d.dr_numdos,pd.lignecleunik";
           
           String message1="",message2="",message3="",message4="",message5="",message6="",message7="",message8="";
           
           String messageAnomalie="";
           
           double param=0;
           
           int j1=0,j2=0,j3=0,j4=0,j5=0,j6=0,j7=0,j8=0;
           
            
                try{
                 pstmt = tmpool.getConuser().prepareStatement(requeteParam);
                 
                 pstmt.execute();
                 
                 ResultSet result = pstmt.executeQuery();
                 result.beforeFirst();
                 
                 result.next();
                 
                 if(result.getDouble(1) != 0)
                 {    
                     suite=true;
                     param=result.getDouble(1);
                     j1=result.getInt(2);
                     j2=result.getInt(3);
                     j3=result.getInt(4);
                     j4=result.getInt(5);
                     j5=result.getInt(6);
                     j6=result.getInt(7);
                     j7=result.getInt(8);
                     j8=result.getInt(9);
                     
                     message1 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag1");
                     message1+=" "+j1+" "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag1_1");
                     message2=java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag2");
                     message2+=" "+j2+" "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag2_1");
                     message3=java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag3");
                     message3+=" "+j3+" "+java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag3_1");
                     message4 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4");
                     message5 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_1");
                     message6 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_2");
                     message7 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_3");
                     message8 =java.util.ResourceBundle.getBundle("srcastra.astra.locale.agenda", tmpool.getLangage()).getString("ag4_4");
                 
                 
                 }  
                }
                catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }   
                    
                if(suite)
                { 
                     
                try{
                    
                    if(param%2 !=0)
                    {
                     pstmt = tmpool.getConuser().prepareStatement(requete1);
                     pstmt.setInt(1,j1);
                     pstmt.setInt(2,drcleunik);
                     pstmt.execute();

                     ResultSet result = pstmt.executeQuery();
                     result.beforeFirst();
                     if(result.next())
                         messageAnomalie=messageAnomalie+message1+"\n";
                     if(test)
                         return messageAnomalie;
                        
                    }    
                    
                    
                    if(param==2 || param==3 || param==6 || param==7 || param==10 || param==11 || param==14 || param==15)
                    { 
                    pstmt = tmpool.getConuser().prepareStatement(requete2);
                    pstmt.setInt(1,j2);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                    
                    
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message2+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    }
                    
                    if( (param >=4 && param <=7) || (param>=12 && param<=15) )
                    {    
                    pstmt = tmpool.getConuser().prepareStatement(requete3);
                    pstmt.setInt(1,j3);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message3+"\n";
                     
                    }
                    
                    if(test)
                         return messageAnomalie;
                     
                    
                    if(param >=8 && param <=15)
                    {
                        
                    pstmt = tmpool.getConuser().prepareStatement(requete4);
                    pstmt.setInt(1,j4);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    ResultSet result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message4+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete5);
                    pstmt.setInt(1,j5);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message5+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete6);
                    pstmt.setInt(1,j6);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message6+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete7);
                    pstmt.setInt(1,j7);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message7+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    
                    pstmt = tmpool.getConuser().prepareStatement(requete8);
                    pstmt.setInt(1,j8);
                    pstmt.setInt(2,drcleunik);
                    pstmt.execute();
                 
                    result=pstmt.executeQuery();
                 
                    result.beforeFirst();
                 
                    if(result.next())
                         messageAnomalie=messageAnomalie+message8+"\n";
                     
                    if(test)
                         return messageAnomalie;
                     
                    
                    }
               
                 
                 
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
            
                }
           
       
            
       

            
           return messageAnomalie;
           
           
       }   
       
    public ArrayList getListingRentabilite(int urcleunik, String dateDep, String dateFin, String bureau, String dateCreDep, String dateCreFin) throws RemoteException, ServeurSqlFailure {
    
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1, req2,req3;
        ArrayList array1 = new ArrayList();
        
        //vente
        
        
        
        req1="SELECT d.dr_numdos, d.dr_date_depart, h.henumpiece, (h.hevaleur*-1),h.hetypeligne,h.lignecleunik,h.henotcpt,h.ctprocleunik,d.dr_facture  FROM journcompta j, compte c1, compte c2, dossier d, clients c, historique2 h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 1 AND h.intervenantcleunik = c.cscleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik and ((d.dr_date_depart >=? and d.dr_date_depart <=?) and (d.dr_datetimecrea >=? and d.dr_datetimecrea<=?)) and dr_numdos like concat(?,'%') AND (h.hetypeligne='D' OR h.hetypeligne='NC' ) ORDER BY d.dr_date_depart, d.dr_numdos";
        
        //achat
        //req2="SELECT d.dr_numdos, d.dr_date_depart, h.henumpiece, (h.hevaleur*-1) ,h.hetypeligne,f.frnom1,h.henotcpt  FROM journcompta j, compte c1, compte c2, dossier d, fournisseur f, historique2 h LEFT JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 2 AND h.intervenantcleunik = f.frcleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND (d.dr_date_depart >=? and d.dr_date_depart <=?) and dr_numdos like concat(?,'%') AND (h.hetypeligne='ACP' OR h.hetypeligne='NCA') ORDER BY d.dr_numdos, h.hecleunik";
      
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setString(1, dateDep);
            pstmt.setString(2, dateFin);
            pstmt.setString(5, bureau);
            pstmt.setString(3, dateCreDep);
            pstmt.setString(4, dateCreFin);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[9]; //c'était 21
                for (int j = 0; j < tab.length; j++)
                {   
                    tab[j] = result.getObject(j+1);
                    if (j==7) 
                    {
                        int typeProd=Integer.valueOf(tab[j].toString()).intValue();
                        int lignecleunik=Integer.valueOf(tab[5].toString()).intValue();
                        String requete="";
                        
                        switch (typeProd)
                        {
                                    //avion
                            case 1: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, avion_ticket a where a.at_cleunik=? and a.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 2: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, brochure b where b.bro_cleunik=? and b.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 3: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, hotel h where h.hl_cleunik=? and h.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 4: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, taxi t where t.tax_cleunik=? and t.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 5: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, bateau b where b.bat_cleunik=? and b.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 6: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, voiture v where v.vl_cleunik=? and v.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik"; 
                                    break;
                            case 7: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, train t where t.trn_cleunik=? and t.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 8: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, assurance a where a.ase_cleunik=? and a.frgt_cleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 9: typeProd=9;
                                    break;
                            case 10: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, car c where c.vl_cleunik=? and c.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            case 11: requete="select f.frnom1, gp.frgtitrecatalog from fournisseur f, fournisseur_grproduit gp, diversprod d where d.div_cleunik=? and d.frgtcleunik=gp.frgtcleunik and f.frcleunik=gp.frcleunik";
                                    break;
                            default: typeProd=-1;
                                     break;
                            
                            
                        }
                        
                        if(typeProd !=-1 && typeProd !=9)
                        {
                        PreparedStatement pstmt1 = tmpool.getConuser().prepareStatement(requete);
                        System.out.println("**"+lignecleunik);
                        System.out.println("**tp"+typeProd);
                        pstmt1.setInt(1, lignecleunik);
                      //  pstmt1.setInt(2, );
                     //   pstmt1.setString(3, bureau);
                        ResultSet result1 = pstmt1.executeQuery();
                        result1.beforeFirst();
                        if(result1.next())
                        
                        tab[5]=result1.getObject(1);
                        
                        //String s=tab[5].toString();
                        tab[7]=result1.getObject(2);
                        //s=s+"|"+tab[7].toString();
                        //tab[7]=s;
                        
                        System.out.println("fourn"+tab[5].toString());
                        }
                        else if (typeProd==9)
                        {
                        tab[5] = new String("**"); 
                        tab[7] = new String("**"); 
                        }
                        else if (typeProd==-1)
                        {
                        tab[5] = new String("");    
                        tab[7] = new String("");  
                        }
                    }
                    
                }
                array1.add(tab);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        
        
        
        
        return array1;
                
    }
    
    public ArrayList getListingIntegrationFacture(int urcleunik, int type) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik,true);
        
        String req1="",req2="";
        ArrayList array = new ArrayList();
        
        if(type==1)
        req1="select f.libelle,f.POnum,f.datedepart,f.nompassager, f.amountachat, f.he_cleunik, d.dr_numdos, f.numpiece, f.numImport from facture f left join dossier d on  f.drcleunik = d.dr_cleunik where f.he_cleunik=1 order by he_cleunik desc, libelle asc";
        else if(type==2)
        req1="select f.libelle,f.POnum,f.datedepart,f.nompassager, f.amountachat, f.he_cleunik, d.dr_numdos, f.numpiece, f.numImport from facture f left join dossier d on  f.drcleunik = d.dr_cleunik where f.he_cleunik=1 or f.he_cleunik=0 order by he_cleunik desc, libelle asc";
        else if(type==3)
        req1="select f.libelle,f.POnum,f.datedepart,f.nompassager, f.amountachat, f.he_cleunik, d.dr_numdos, f.numpiece, f.numImport from facture f left join dossier d on  f.drcleunik = d.dr_cleunik where f.he_cleunik=0 order by he_cleunik desc, libelle asc";
        else if(type==0)
        req1="select f.libelle,f.POnum,f.datedepart,f.nompassager, f.amountachat, f.he_cleunik, d.dr_numdos, f.numpiece, f.numImport from facture f left join dossier d on  f.drcleunik = d.dr_cleunik having f.numImport = ? order by he_cleunik desc, libelle asc";
           
        req2="select max(numImport) from facture";
        
        
        long numImport=0; 
        
        if(type==0) {
                try{
                   PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req2);
                    ResultSet result = pstmt.executeQuery();
                    result.beforeFirst(); 

                    if(result.next())
                    {
                        numImport=result.getLong(1);
                    }


                } catch (SQLException sn) {
                    renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
                }
        }
        
        try {
            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req1);
            if(type==0) pstmt.setLong(1,numImport);
            
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[9]; 
                for (int j = 0; j < tab.length; j++)
                    tab[j] = result.getObject(j + 1);
                array.add(tab);
            }
            
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        
        return array; 
        
    
    }
    
    
    
    public ArrayList getListing(int urcleunik, int cledoss) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1, req2,req3,req0,req;
        ArrayList array1 = new ArrayList();
        ArrayList array2 = new ArrayList();
        
        ArrayList cles = new ArrayList();
        ArrayList clesDoss = new ArrayList();
        
        cles.add(new Integer(cledoss));
        
        req0 = "select dr_numdos from dossier where dr_cleunik=?";
        req  = "select dr_cleunik,dr_numdos from dossier where dr_numdos like ?";
        
/*  req1 = "SELECT h.hecleunik, h.heperiode, h.henotcpt, h.hetransact, j.jota_abrev , h.henumpiece, h.hedatecreation, h.hedatemouv, c1.ce_num, c2.ce_num, t.Tva_rate, h.ce_cleunik2, h.ce_cleunik, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, d.dr_numdos, c.csnom, h.hetypepayement FROM journcompta j, compte c1, compte c2, dossier d, clients c, historique2 h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 1 AND h.intervenantcleunik = c.cscleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.drcleunik = ? ORDER BY h.hecleunik";
req2 = "SELECT h.hecleunik, h.heperiode, h.henotcpt, h.hetransact, j.jota_abrev , h.henumpiece, h.hedatecreation, h.hedatemouv, c1.ce_num, c2.ce_num, t.Tva_rate, h.ce_cleunik2, h.ce_cleunik, h.hevaleur, h.hecodetva, h.hevaleurbase, h.hevaleurtva, h.helibelle, d.dr_numdos, f.frnom1,h.hetypepayement FROM journcompta j, compte c1, compte c2, dossier d, fournisseur f, historique2 h LEFT JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 2 AND h.intervenantcleunik = f.frcleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.drcleunik = ? ORDER BY h.hecleunik";
*/
        req1 = "SELECT h.henotcpt, j.jota_abrev , h.henumpiece, '', h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurtva, h.helibelle,h.hetypeligne FROM journcompta j, compte c1, compte c2, dossier d, clients c, historique2 h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 1 AND h.intervenantcleunik = c.cscleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.drcleunik = ? AND (h.hetypeligne='B' OR h.hetypeligne='BP' OR h.hetypeligne='NCB' OR h.hetypeligne='OCCC' OR h.hetypeligne='NCOB') ORDER BY h.hecleunik";
        req2 = "SELECT h.henotcpt, j.jota_abrev , h.henumpiece, f.frnom2, h.hedatemouv, (h.hevaleur*-1), h.hecodetva, h.hevaleurtva, h.helibelle,h.hetypeligne FROM journcompta j, compte c1, compte c2, dossier d, fournisseur f, historique2 h LEFT JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 2 AND h.intervenantcleunik = f.frcleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.drcleunik = ? AND (h.hetypeligne='ACP' OR h.hetypeligne='NCA') ORDER BY h.hecleunik";
        req3 = "SELECT h.henotcpt, j.jota_abrev , h.henumpiece, '', h.hedatemouv, h.hevaleur, h.hecodetva, h.hevaleurtva, h.helibelle,h.hetypeligne FROM journcompta j, compte c1, compte c2, dossier d, clients c, historiquerecup h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik WHERE h.typeintervenantcleunik = 1 AND h.intervenantcleunik = c.cscleunik AND h.drcleunik = d.dr_cleunik AND c1.ce_cleunik = h.ce_cleunik_cent AND c2.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.drcleunik = ? AND (h.hetypeligne='B' OR h.hetypeligne='BP' OR h.hetypeligne='NCB' OR h.hetypeligne='OCCC' OR h.hetypeligne='NCOB') ORDER BY h.hecleunik";
//pour la 1ère requète
//lignes des ventes

        String drparent="";
        
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req0);
            pstmt.setInt(1,cledoss);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            result.next();
            
            drparent=result.getString(1);
            clesDoss.add(drparent);
            pstmt=tmpool.getConuser().prepareStatement(req);
            pstmt.setString(1,drparent+"%");
            result = pstmt.executeQuery();
            result.beforeFirst();
             
            while(result.next())
            {
               // System.out.println(result.getInt(1));
                
                cles.add(result.getObject(1));
                clesDoss.add(result.getString(2));
                
            }
            
            if(cles.size()>1)
            {
                cles.remove(0);
                clesDoss.remove(0);
            }
            
            for(int i=0; i<cles.size();i++)
            {    
            
            
            pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, Integer.valueOf(cles.get(i).toString()).intValue());
            result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[10]; //c'était 21
                for (int j = 0; j < tab.length; j++)
                    tab[j] = result.getObject(j + 1);
                
                String tmp=clesDoss.get(i).toString();
                
                boolean cont=true;
                
                if(tmp.length()>8)
                {
                    if(tmp.charAt(8)=='N')
                    {
                        if(tab[2].toString().equals("0"))
                            cont=false;
                    }
                    
                    
                }
                
                if(cont)
                array1.add(tab);
                
             }
            
            }
            
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
         try {
             
            for(int i=0; i<cles.size();i++)
            {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req3);
            pstmt.setInt(1, Integer.valueOf(cles.get(i).toString()).intValue());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();

            while (result.next()) {
                Object[] tab = new Object[10]; //c'était 21
                for (int j = 0; j < tab.length; j++)
                    tab[j] = result.getObject(j + 1);
                array1.add(tab);
            }
            
               }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }

//pour la 2eme requete
//lignes des achats aux fournisseurs
        
        //achat fourn
        
        if (tmpool.getUrright()==1 || tmpool.getUrright()==2 || tmpool.getUrright()==3) {
            try {
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req2);
                pstmt.setInt(1, cledoss);
                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    Object[] tab = new Object[10];
                    for (int j = 0; j < tab.length; j++)
                        tab[j] = result.getObject(j + 1);
                    array1.add(tab);
                }
            } catch (SQLException sn) {
                renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
            }
        }
// le tri
// for (int i=0; i<array1.size(); i++)
//      for (int j=0; j<array2.size(); j++)
//          {
//      }



        return array1; //juste comme ça
    }
//end ajout
    public ArrayList getProduitsInfoSelonDate(int urcleunik, String dr_date_depart) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String[] req = new String[8];
        ArrayList array = new ArrayList();


/*   req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[1] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[2] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[3] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[4] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[5] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))";
req[6] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) ";
req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[1] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[2] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[3] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[4] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[5] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[6] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart=?";
*/
        req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        req[1] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        req[2] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        req[3] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        req[4] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        req[5] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))";
        req[6] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) ";
        req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))   ";
        try {
            for (int i = 0; i < 8; i++) {
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req[i]);
                pstmt.setString(1, dr_date_depart);
                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    Object[] tab = new Object[12];
                    for (int j = 0; j <= 10; j++)
                        tab[j] = result.getObject(j + 1);
                    if (i == 0)
                        tab[11] = new Integer(produit_T.AS);
                    else if (i == 1)
                        tab[11] = new Integer(produit_T.TR);
                    else if (i == 2)
                        tab[11] = new Integer(produit_T.TAX);
                    else if (i == 3)
                        tab[11] = new Integer(produit_T.HO);
                    else if (i == 4)
                        tab[11] = new Integer(produit_T.CAR);
                    else if (i == 5)
                        tab[11] = new Integer(produit_T.BRO);
                    else if (i == 6)
                        tab[11] = new Integer(produit_T.BA);
                    else
                        tab[11] = new Integer(produit_T.AV);

                    array.add(tab);

                }
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur les produits", tmpool.getConuser());
        }

        return array;
    }
/* CETTE FONCTION N'EST PAS NECESSAIRE PUISQUE IL Y A L'AUTRE QUI FAIT DES RECHERCHES COMBINEES
public ArrayList getProduitsInfoSelonDateetPassager(int urcleunik,String dr_date_depart, String prnom) throws RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
String[] req = new String[8];
ArrayList array=new ArrayList();

req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[1] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[2] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[3] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[4] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[5] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[6] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%'))";
req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%'))";
try {
for (int i=0; i<8; i++) {
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req[i]);
pstmt.setString(2, prnom);
pstmt.setString(1, dr_date_depart);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
Object[] tab = new Object[12];
for(int j=0; j<=10; j++)
tab[j] = result.getObject(j+1);
if (i==0)
tab[11] = new Integer(produit_T.AS);
else if (i==1)
tab[11] = new Integer(produit_T.TR);
else if (i==2)
tab[11] = new Integer(produit_T.TAX);
else if (i==3)
tab[11] = new Integer(produit_T.HO);
else if (i==4)
tab[11] = new Integer(produit_T.CAR);
else if (i==5)
tab[11] = new Integer(produit_T.BRO);
else if (i==6)
tab[11] = new Integer(produit_T.BA);
else tab[11] = new Integer(produit_T.AV);
array.add(tab);

}
}
}catch(SQLException sn){
renvexception(sn,"erreur lors de la demande d'info sur les produits",tmpool.getConuser());
}

return array;
}    */
//ajout de Driart le 06 aout 2003 pour faire des recherches combines des dossiers
    public ArrayList getProduitsInfoSelonToutes(int urcleunik, String dr_date_depart, String dossier, String type, String passager) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String[] req = new String[10];
        ArrayList array = new ArrayList();
// String requete="SELECT DISTINCT  d.dr_numdos, d.dr_date_depart, d.po, d.destination, d.hotel, d.dr_status, c1.csnom, d.fournisseur, d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d, user u, dossierproduit dp, clients c1, clients c2,historique2 h WHERE d.urcleunik = u.urcleunik AND u.eecleunik = ? AND dr_annull = 0 AND d.dr_cleunik = dp.dr_cleunik AND d.cscleunik = c1.cscleunik AND d.cscleunik_fact = c2.cscleunik AND dr_numdos LIKE (CONCAT( ?, '%' ) ) AND dp.doit_po LIKE (CONCAT( ?, '%' ) ) AND c1.csnom  LIKE (CONCAT( ?, '%' ) ) AND c2.csnom  LIKE (CONCAT( ?, '%' ) ) AND dp.doit_type LIKE (CONCAT(?, '%' ) ) AND d.dr_datetimecrea >= ? AND d.dr_date_depart >= ? AND d.dr_cleunik=h.drcleunik AND h.henumpiece  LIKE (CONCAT( ?, '%' ) )  AND helibelle LIKE (CONCAT( ?, '%' )) AND henotcpt LIKE (CONCAT( ?, '%' )) ORDER BY "+orderS;
        req[0] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.AV + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, avion_ticket a LEFT JOIN historique2 h on(h.lignecleunik=a.at_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.AV + " AND a.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik  AND c.cscleunik = d.cscleunik AND a.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND a.at_num_billet LIKE(CONCAT(?, '%'))";
        req[1] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.BA + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, bateau b LEFT JOIN historique2 h on(h.lignecleunik=b.bat_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.BA + " AND b.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND b.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND b.bat_pnr LIKE(CONCAT(?, '%'))";
        req[2] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.BRO + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, brochure b LEFT JOIN historique2 h on(h.lignecleunik=b.bro_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.BRO + " AND b.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = b.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND b.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND b.bro_po LIKE(CONCAT(?, '%'))";
        req[3] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.CAR + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, car LEFT JOIN historique2 h on(h.lignecleunik=car.vl_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.CAR + " AND car.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = car.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND car.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND car.vl_pnr LIKE(CONCAT(?, '%'))";
        req[4] = "SELECT DISTINCT hi.hecleunik,d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.HO + ",d.dr_datetimecrea,hi.hevaleur*-1 FROM dossier d, hotel h LEFT JOIN historique2 hi on(hi.lignecleunik=h.hl_cleunik AND hi.sn_cleunik=0 AND hi.ctprocleunik=" + produit_T.HO + " AND h.dr_cleunik=hi.drcleunik AND hi.hetypeligne='D' AND hi.hedossiercourant='O'),  fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = h.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND h.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%'))  AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND h.hl_pnr LIKE(CONCAT(?, '%'))";
        req[5] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.TAX + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, taxi t LEFT JOIN historique2 h on(h.lignecleunik=t.tax_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.TAX + " AND t.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND t.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND t.tax_pnr LIKE(CONCAT(?, '%'))";
        req[6] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.TR + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, train t LEFT JOIN historique2 h on(h.lignecleunik=t.trn_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.TR + " AND t.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = t.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND t.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND t.trn_billet LIKE(CONCAT(?, '%'))";
        req[7] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.AS + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d, assurance a LEFT JOIN historique2 h on(h.lignecleunik= a.ase_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.AS + " AND a.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND a.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND a.ase_num_police LIKE(CONCAT(?, '%'))";
// req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik,"+produit_T.AS+" FROM dossier d, assurance a LEFT JOIN historique2 h on(h.lignecleunik=v.vl_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=6 AND v.dr_cleunik=h.drcleunik), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = a.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND a.ase_num_police LIKE(CONCAT(?, '%'))";

        req[8] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, v.vl_cleunik, v.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.VO + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d , voiture v LEFT JOIN historique2 h on(h.lignecleunik=v.vl_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.VO + " AND v.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = v.dr_cleunik AND d.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = v.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND v.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND v.vl_pnr LIKE(CONCAT(?, '%'))";
        req[9] = "SELECT DISTINCT h.hecleunik,d.dr_numdos, d.dr_date_depart, dv.div_cleunik, dv.div_objet,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik," + produit_T.DIV + ",d.dr_datetimecrea,h.hevaleur*-1 FROM dossier d , diversprod dv LEFT JOIN historique2 h on(h.lignecleunik= dv.div_cleunik AND h.sn_cleunik=0 AND h.ctprocleunik=" + produit_T.DIV + " AND dv.dr_cleunik=h.drcleunik AND h.hetypeligne='D' AND h.hedossiercourant='O'), fournisseur f, fournisseur_grproduit ft, clients c, passager p, intermediairepassager ip WHERE d.dr_cleunik = dv.dr_cleunik AND dv.dr_cleunik = ip.dr_cleunik AND p.pr_cleunik = ip.pr_cleunik AND ft.frgtcleunik = dv.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND dv.annuler=0 AND d.dr_date_depart LIKE(CONCAT(?, '%')) AND d.dr_numdos LIKE(CONCAT(?, '%')) AND p.pr_nom LIKE(CONCAT(?, '%')) AND dv.div_objet LIKE(CONCAT(?, '%'))";

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("i " + i);
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req[i]);
                pstmt.setString(1, dr_date_depart);
                pstmt.setString(2, dossier);
                pstmt.setString(3, passager);
                pstmt.setString(4, type);
                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    Object[] tab = new Object[14];
                    for (int j = 0; j < tab.length; j++) {
                        if (j == 1 || j == 12) {
                            tab[j] = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(j + 2));
                        } else {
                            tab[j] = result.getObject(j + 2);
                        }
                    }
/*    if (i==0)
tab[11] = new Integer(produit_T.AS);
else if (i==1)
tab[11] = new Integer(produit_T.TR);
else if (i==2)
tab[11] = new Integer(produit_T.TAX);
else if (i==3)
tab[11] = new Integer(produit_T.HO);
else if (i==4)
tab[11] = new Integer(produit_T.CAR);
else if (i==5)
tab[11] = new Integer(produit_T.BRO);
else if (i==6)
tab[11] = new Integer(produit_T.BA);
else tab[11] = new Integer(produit_T.AV);*/
                    array.add(tab);

                }
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur les produits", tmpool.getConuser());
        }

        return array;
    }
//ajout de Driart le 7 aout 2003
    public srcastra.astra.gui.modules.compta.achat.VenteRentabilite getPreviousFacture(long cledossier, int urcleunik, int lmcleunik, long cleproduit, int ctprocleunik, boolean exclude) throws RemoteException, ServeurSqlFailure {
// 'B', 'BP','P', 'CP','CPC','A','OBCC','OCCC','NCB','NCAB','NCOB' )";
//'D','ACP','TVA','TVAV','NC','NCT','NCA','NCO','NCOT') ";

        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req2 = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle, h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_dc,helibellecompta2,h.henumpiece,f.frnom1, h.sn_cleunik,h.hedatemouv,j.jota_abrev,jxcleunik  FROM historique2 h LEFT JOIN dossier d ON (h.drcleunik=d.dr_cleunik),tvatraduction2 t,journcompta j,fournisseur f WHERE (h.hetypeligne = 'ACP' OR h.hetypeligne ='NCA' ) AND h.tva_cleunik=t.tva_cleunik AND h.intervenantcleunik=f.frcleunik AND h.jxcleunik=j.jota_cleunik AND t.lmcleunik=? AND h.drcleunik=?";
        String sumVenteDossier = "SELECT SUM(hevaleur) FROM historique2 WHERE (hetypeligne='B' OR hetypeligne='NCB' OR hetypeligne='NCOB') AND drcleunik=?";
        String sumVenteProduit = "SELECT (SUM(hevaleur)*-1) FROM historique2 WHERE (hetypeligne='D' OR hetypeligne='NCO' OR hetypeligne='NC') AND lignecleunik=? AND ctprocleunik=?";
        srcastra.astra.gui.modules.compta.achat.VenteRentabilite retval = new srcastra.astra.gui.modules.compta.achat.VenteRentabilite();
        if (exclude) {
            req2 = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle, h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_dc,helibellecompta2,h.henumpiece,f.frnom1, h.sn_cleunik,h.hedatemouv,j.jota_abrev,jxcleunik  FROM historique2 h LEFT JOIN dossier d ON (h.drcleunik=d.dr_cleunik),tvatraduction2 t,journcompta j,fournisseur f WHERE (h.hetypeligne = 'ACP' OR h.hetypeligne ='NCA' ) AND h.tva_cleunik=t.tva_cleunik AND h.intervenantcleunik=f.frcleunik AND h.lignecleunik NOT LIKE (?) AND h.ctprocleunik NOT LIKE(?)AND  h.jxcleunik=j.jota_cleunik AND t.lmcleunik=? AND h.drcleunik=?";
        }
        ArrayList achat = null;
        try {       //              1                   2               3           4               5           6               7
//req2 = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle,
//      8           9               10          11              12                      13          14          15          16          17      18                  19          20          21
//h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_cd,helibellecompta2  ,h.he_numpiece,f.frnom1, h.sn_cleunik
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(sumVenteDossier);
            pstmt.setLong(1, cledossier);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                String value = result.getString(1);
                if (value == null)
                    value = "0.00";
                retval.setVenteTotal(value);
            }
            pstmt = tmpool.getConuser().prepareStatement(sumVenteProduit);
            pstmt.setLong(1, cleproduit);
            pstmt.setLong(2, ctprocleunik);
            result = pstmt.executeQuery();
            while (result.next()) {
                String value = result.getString(1);
                if (value == null)
                    value = "0.00";
                retval.setVenteProduit(value);
            }
            pstmt = tmpool.getConuser().prepareStatement(req2);
            if (!exclude) {
                pstmt.setInt(1, tmpool.getLmcleunik());
                pstmt.setLong(2, cledossier);
            } else {
                pstmt.setLong(1, cleproduit);
                pstmt.setLong(2, ctprocleunik);
                pstmt.setInt(3, tmpool.getLmcleunik());
                pstmt.setLong(4, cledossier);
            }
            result = pstmt.executeQuery();
            result.beforeFirst();
            achat = new ArrayList();
            while (result.next()) {
                AchatCp achatcp = new AchatCp();
                achatcp.setDc(result.getString(17));
                achatcp.setCe_cleunik(result.getInt(5));
                achatcp.getTva1().setTva_id(result.getInt(6));
                achatcp.getTva1().setTva_base(result.getString(3));
                achatcp.getTva1().setTva_value(result.getString(4));
                achatcp.getTva1().setPrixTot(result.getString(2));
                achatcp.getTva1().setTva_rate(result.getString(8));
                achatcp.getTva1().setTva_code(result.getString(15));
                achatcp.setBase(result.getString(3));
                achatcp.setCleprod(result.getLong(9));
                achatcp.setCatProd(result.getInt(11));
                achatcp.setCledossier(result.getLong(10));
                achatcp.setNumdos(result.getString(16));
                achatcp.setFrcleunik(result.getInt(12));
                achatcp.setCommentaire(result.getString(7));
                achatcp.setPoPnr(result.getString(18));
                achatcp.setCeText(result.getString(14));
                achatcp.setNumpiece(result.getString(19));
                achatcp.setFrnom(result.getString(20));
                achatcp.setClefacture(result.getLong(21));
                achatcp.setDateMouv(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(22)));
                achatcp.setJota_abrev(result.getString(23));
                achatcp.setJota_cleunik(result.getInt(24));
                achat.add(achatcp);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        retval.setAchat(achat);
        return retval;
    }

    public Achat_T getFactureInfoFromHistorique(int urcleunik, long cleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req1, req2;
        Object[] tab = new Object[13];
        ArrayList array = new ArrayList();
//                1                 2       3               4           5               6               7                   8               9           10              11              12              13          14              15              16
        req1 = "SELECT h.jxcleunik, h.heperiode, h.henumpiece, h.hedatemouv, h.he_reception, h.he_echeance, h.intervenantcleunik, h.helibelle, h.hevaleur, h.hevaleurbase, h.hevaleurtva,  h.lignecleunik, h.drcleunik, h.ctprocleunik,h.hetypeligne,h.he_exported,h.hetransact FROM historique2 h WHERE (h.hetypeligne = 'A' OR h.hetypeligne='NCAB') AND h.hecleunik = ?";
        req2 = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle, h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_dc,helibellecompta2  FROM historique2 h LEFT JOIN dossier d ON (h.drcleunik=d.dr_cleunik),tvatraduction2 t WHERE (h.hetypeligne = 'ACP' OR h.hetypeligne ='NCA' ) AND h.tva_cleunik=t.tva_cleunik AND t.lmcleunik=? AND h.sn_cleunik=?";
        Achat_T facture = new Achat_T();
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setLong(1, cleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                facture.setJota_cleunik(result.getInt(1));
                facture.setJota_periode(result.getInt(2));
                facture.setNumpiece(result.getString(3));
                facture.setDate(new Date(result.getString(4)));
                facture.setRecept(new Date(result.getString(5)));
                facture.setEcheance(new Date(result.getString(6)));
                facture.setFrcleunik(result.getInt(7));
                facture.setCommentaire(result.getString(8));
                facture.setLignecleunik(result.getLong(12));
                facture.setDrcleunik(result.getInt(13));
                facture.setTypeprodCleunik(result.getInt(14));
                facture.setFraisgen(0);
                facture.setMontanteuro(result.getString(9));
                if (result.getString(15).equals("A")) {
                    facture.setDc("C");
                } else if (result.getString(15).equals("NCAP")) {
                    facture.setDc("D");
                }
                facture.setExported(result.getLong(16));
                facture.setHeTransact(result.getLong(17));
                
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        ArrayList achat = new ArrayList();
        ArrayList tva = new ArrayList();
        try {       //              1                   2               3           4               5           6               7
//req2 = "SELECT h.intervenantcleunik, h.hevaleur, h.hevaleurbase, h.hevaleurtva, h.ce_cleunik2, h.tva_cleunik, h.helibelle,
//      8           9               10          11              12                      13          14          15          16          17      18
//h.hecodetva, h.lignecleunik, h.drcleunik, h.ctprocleunik, h.intervenantcleunik, h.hetypeligne,h.ce_cleunik,t.genField1,d.dr_numdos,he_cd,helibellecompta2
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req2);
            pstmt.setLong(2, cleunik);
            pstmt.setInt(1, tmpool.getLmcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                AchatCp achatcp = new AchatCp();
                achatcp.setDc(result.getString(17));
                achatcp.setCe_cleunik(result.getInt(5));
                achatcp.getTva1().setTva_id(result.getInt(6));
                achatcp.getTva1().setTva_base(result.getString(3));
                achatcp.getTva1().setTva_value(result.getString(4));
                achatcp.getTva1().setPrixTot(result.getString(2));
                achatcp.getTva1().setTva_rate(result.getString(8));
                achatcp.getTva1().setTva_code(result.getString(15));
                achatcp.setBase(result.getString(3));
                achatcp.setCleprod(result.getLong(9));
                achatcp.setCatProd(result.getInt(11));
                achatcp.setCledossier(result.getLong(10));
                achatcp.setNumdos(result.getString(16));
                achatcp.setFrcleunik(result.getInt(12));
                achatcp.setCommentaire(result.getString(7));
                achatcp.setPoPnr(result.getString(18));
                achatcp.setCeText(result.getString(14));
                if (achatcp.getCleprod() != 0) {
                    srcastra.astra.gui.modules.compta.achat.VenteRentabilite venteRenta = getPreviousFacture(achatcp.getCledossier(), urcleunik, tmpool.getLmcleunik(), achatcp.getCleprod(), achatcp.getCatProd(), true);
                    achatcp.setVenteRent(venteRenta);
                }
                achat.add(achatcp);
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        facture.setAchat(achat);
        return facture;
    }
// end ajout
// ajout de Driart le 4 aout 2003
/*
CETTE FONCTION N'EST PAS NECESSAIRE PUISQUE IL Y A L'AUTRE QUI FAIT DES RECHERCHES COMBINEES


public ArrayList getProduitsInfoSelonTypeDeProduit(int urcleunik, String champ) throws RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
String[] req = new String[8];
ArrayList array=new ArrayList();



req[0] = "SELECT d.dr_numdos, d.dr_date_depart, a.ase_cleunik, a.ase_num_police, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, assurance a, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = a.dr_cleunik AND ft.frgtcleunik = a.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND a.ase_num_police LIKE(CONCAT(?, '%'))";
req[1] = "SELECT d.dr_numdos, d.dr_date_depart, t.trn_cleunik, t.trn_billet, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, train t, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = t.dr_cleunik AND ft.frgtcleunik = t.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND t.trn_billet LIKE(CONCAT(?, '%'))";
req[2] = "SELECT d.dr_numdos, d.dr_date_depart, t.tax_cleunik, t.tax_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog,c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, taxi t, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = t.dr_cleunik AND ft.frgtcleunik = t.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND t.tax_pnr LIKE(CONCAT(?, '%'))";
req[3] = "SELECT d.dr_numdos, d.dr_date_depart, h.hl_cleunik, h.hl_pnr, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, hotel h, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = h.dr_cleunik AND ft.frgtcleunik = h.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND h.hl_pnr LIKE(CONCAT(?, '%'))";
req[4] = "SELECT d.dr_numdos, d.dr_date_depart, car.vl_cleunik, car.vl_pnr,f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, car, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = car.dr_cleunik AND ft.frgtcleunik = car.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND car.vl_pnr LIKE(CONCAT(?, '%'))";
req[5] = "SELECT d.dr_numdos, d.dr_date_depart, b.bro_cleunik, b.bro_po, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, brochure b, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = b.dr_cleunik AND ft.frgtcleunik = b.frgt_cleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND b.bro_po LIKE(CONCAT(?, '%'))";
req[6] = "SELECT d.dr_numdos, d.dr_date_depart, b.bat_cleunik, b.bat_pnr, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, bateau b, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = b.dr_cleunik AND ft.frgtcleunik = b.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND b.bat_pnr LIKE(CONCAT(?, '%'))";
req[7] = "SELECT d.dr_numdos, d.dr_date_depart, a.at_cleunik, a.at_num_billet, f.frcleunik, f.frnom1, ft.frgtcleunik, ft.frgtitrecatalog, c.cscleunik, c.csnom, d.dr_cleunik FROM dossier d, avion_ticket a, fournisseur f, fournisseur_grproduit ft, clients c WHERE d.dr_cleunik = a.dr_cleunik AND ft.frgtcleunik = a.frgtcleunik AND ft.frcleunik = f.frcleunik AND c.cscleunik = d.cscleunik AND a.at_num_billet LIKE(CONCAT(?, '%')) ";
try {
for (int i=0; i<8; i++) {
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req[i]);
pstmt.setString(1, champ);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
Object[] tab = new Object[12];
for(int j=0; j<=10;j++)  {
tab[j] = result.getObject(j+1);

}
if (i==0)
tab[11] = new Integer(produit_T.AS);
else if (i==1)
tab[11] = new Integer(produit_T.TR);
else if (i==2)
tab[11] = new Integer(produit_T.TAX);
else if (i==3)
tab[11] = new Integer(produit_T.HO);
else if (i==4)
tab[11] = new Integer(produit_T.CAR);
else if (i==5)
tab[11] = new Integer(produit_T.BRO);
else if (i==6)
tab[11] = new Integer(produit_T.BA);
else tab[11] = new Integer(produit_T.AV);

array.add(tab);

}
}

}catch(SQLException sn){
renvexception(sn,"erreur lors de la demande d'info sur les produits",tmpool.getConuser());
}

return array;
}
*/
// end ajout
    public ArrayList getFournisseurrInfo(ArrayList tmp, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        if (tmp != null) {
            try {
                for (int i = 0; i < tmp.size(); i++) {
                    GeneralePrinting gen = (GeneralePrinting) tmp.get(i);
                    PrintingInfo.getLocalite(gen, urcleunik, tmpool, PrintingInfo.FOUNISSEUR);
//  PrintingInfo.getTitle(gen,urcleunik,tmpool,PrintingInfo.PASSAGER);
//  PrintingInfo.getNationalite(gen,urcleunik,tmpool,PrintingInfo.PASSAGER);

                }
            } catch (SQLException sn) {
                renvexception(sn, "erreur lors de la demande d'info sur les passagers", tmpool.getConuser());

            }
        } else
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Array des passager vide");
        return tmp;
    }
    
    public String modifyAchat(Achat_T achat,int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
         try{   
         
         PreparedStatement pstmt;
         String requete="delete from historique2 where hetransact=?";
         
         pstmt = tmpool.getConuser().prepareStatement(requete);

         pstmt.setLong(1, achat.getHeTransact());
        //pstmt.setDouble(2, prcleunik);
        pstmt.execute();
         
            if(achat.getCleunikFact()!=0)
            {
            String requete1 = "update facture set he_cleunik=1, drcleunik=? where cleunik=?";
            
                try{
                 pstmt = tmpool.getConuser().prepareStatement(requete1);
                 pstmt.setInt(1, achat.getDrcleunik());
                 pstmt.setLong(2, achat.getCleunikFact());
                //pstmt.setDouble(2, prcleunik);
                pstmt.execute();
                
                 
                  
                } catch (SQLException sn) {
                renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

                }
            
            
            }
         
          achat.setDrcleunik(0);
        return insertAchat(achat,urcleunik);
        
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());
                
        }
        return "";
        
    }

    public String insertAchat(Achat_T achat, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String retval = "";
        try {
            synchronized (serveur.getNumdosSyn()) {
                Transaction.begin(tmpool.getConuser());
                PreparedStatement pstmt = null;
                Configuration config = new Configuration(tmpool.getConuser(), pstmt, achat.getDrcleunik(), 0, 0, achat.getFrcleunik(), 0, 0, serveur, tmpool.getUrcle2(), 0);
                config.setCle2(urcleunik);
                Check.getTransaction(tmpool, serveur.getTransactSyn(), serveur);
                retval = new MangageChaineComptable(config, serveur, urcleunik).insertAchat(achat);
                serveur.renvSoldeRmiObject(urcleunik).insert(urcleunik);
            }
            Transaction.commit(tmpool.getConuser());
            return retval;

        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return "";
    }

    public String insertPaymentTransfert(Payement_T pay, int urcleunik, int cleintervenant) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String retval = "";
        try {
            synchronized (serveur.getNumdosSyn()) {
                Transaction.begin(tmpool.getConuser());
                PreparedStatement pstmt = null;
                Configuration config = new Configuration(tmpool.getConuser(), pstmt, 0, 0, 0, cleintervenant, 0, 0, serveur, tmpool.getUrcle2(), 0);
                config.setCle2(urcleunik);
                config.setEecleunik(tmpool.getNumeroentite());
                Check.getTransaction(tmpool, serveur.getTransactSyn(), serveur);
                retval = new MangageChaineComptable(config, serveur, urcleunik).insertPayement(pay);
                serveur.renvSoldeRmiObject(urcleunik).insert(urcleunik);
            }
            Transaction.commit(tmpool.getConuser());
            return retval;

        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return "";
    }
//ajout de Driart le 13 aout 2003
    private void createDossierDivers(int urcleunik) throws RemoteException, ServeurSqlFailure {
        
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
        
        String req1 = new String();
        
        
        
        req1 = "select count(*) from dossier where dr_numdos=?";
        
        PreparedStatement pstmt1;
        PreparedStatement pstmt2;
        
        
        try {
            
              pstmt1 = tmpool.getConuser().prepareStatement(req1);
              
              pstmt1.setString(1,"ADIV");
              
              ResultSet result1 = pstmt1.executeQuery();
              
              result1.beforeFirst();
              
              if(result1.next())
              {
                  if(result1.getInt(1)==0)
                  {
                  
                      String requ3="insert into dossier(dr_numdos) values(?)";
                      try{
                      pstmt2 = tmpool.getConuser().prepareStatement(requ3);
                      
                      pstmt2.setString(1,"ADIV");
                      
                      pstmt2.execute();
                      } catch (SQLException sn) {
                        renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

                        }
                      
                  
                  
                  }
                  
                  
              }
             
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }
     
        
      
        
        
    }
    
    private ArrayList paramAchat(int urcleunik) throws RemoteException, ServeurSqlFailure {
    
        ArrayList data=new ArrayList();
     
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik, true);
        
        String req1 = new String();
        
        PreparedStatement pstmt1;
        
        req1 = "select typeIntegrationAchat,journalAchat,journalNCAchat,journalNCAchatBidon,journalAchatBidon from configuration1";
        
        try {
            
              pstmt1 = tmpool.getConuser().prepareStatement(req1);
                           
              ResultSet result1 = pstmt1.executeQuery();
              
              result1.beforeFirst();
              
              if(result1.next())
              {
                    for(int i=1;i<6;i++)
                    {
                        System.out.println(i+" "+result1.getObject(i));
                        
                        data.add(result1.getObject(i));
                    
                    }
                  
                    return data;
              }
              
              
              
          } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }
        
        return data;
    }
    
    
    
    
    
    public void insertFactureIntoDatabase(FactureDeFloppy f, int urcleunik, long longtime) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req = new String();
        req = "INSERT INTO `facture` (`he_cleunik` , `numfourn` , `clinum` , `libelle` , `dateecheance` ,"
                + "`POnum` , `datedepart` , `nompassager` , `valeurcommiss` , `POtax` , `domiciliation` ,"
                + "`annulation` , `rmarquecomm`, `MontTotal`, `solde`, `dateReception`, `Credit`, `amountAchat`, `numImport`, `drcleunik`, `numpiece` )VALUES (?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        
        String req1 = new String();
        String req2 = new String();
        String req3 = new String();
        String req4 = new String();
        String req5 = new String();
        
        int typeIntegration;
        
        
        ArrayList param=paramAchat(urcleunik);
        
        typeIntegration=Integer.valueOf(param.get(0).toString()).intValue();
        
        
        f.NumPO="0"+f.NumPO;
        
        
        

        //req1 = "select dr_cleunik from dossier where po = ?";
        
        req1 = "select dp.dr_cleunik,dp.prod_cleunik from dossierproduit dp, brochure b where b.bro_po=? and b.bro_cleunik=dp.prod_cleunik and dp.cate_cleunik=2";
        
        req2 = "select j.jota_cleunik from journcompta j, dossier d, user u where d.dr_cleunik=? and d.urcleunik=u.urcleunik and j.jota_categorie=? and (j.eecleunik=u.eecleunik or j.eecleunik=0)";
        
        req3 = "select pede_cleunik from periode where  ? >= pede_de and ? <= pede_a";
        
        req4 = "select drcleunik,numpiece from facture where libelle = ? order by numImport";
        
        req5 = "select dr_cleunik from dossier where dr_numdos ='ADIV'";
        
        createDossierDivers(urcleunik);
        
       
        boolean trouve = false;
        
        boolean tr=false;
        
        boolean exist = false;
        
        int numDossier=-1;
        
        int dossierExist=-1;
        String numpieceExist="";
        
        
        
        String numpiece="";
        
        int prodNum=-1;
        
        PreparedStatement pstmt;
        
        try {
            
            pstmt = tmpool.getConuser().prepareStatement(req4);
            
            pstmt.setString(1,f.Libelle);
            
            
            ResultSet result = pstmt.executeQuery();
            
            result.beforeFirst();
            if(result.next())
            {
                
               dossierExist=Integer.valueOf(result.getString(1)).intValue();
               numpieceExist=result.getString(2);
               
               exist=true;
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }
        
 if(!exist)
 {    
        try {
            
            pstmt = tmpool.getConuser().prepareStatement(req1);
            
            pstmt.setString(1,f.NumPO);
            
            ResultSet result = pstmt.executeQuery();
            
            result.beforeFirst();
            if(result.next())
            {
                
               numDossier=Integer.valueOf(result.getString(1)).intValue();
               prodNum=Integer.valueOf(result.getString(2)).intValue();
               
               trouve=true;
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }

        
        
        int journ=-1;
        int journal=ParamComptable.JOURNAL_ACHAT;
        
        
        if(f.isCredit())
            journal=ParamComptable.JOURNAL_NCACHAT;
        
        
        
        
        if(!trouve)
        {
            try {
            
            pstmt = tmpool.getConuser().prepareStatement(req5);
            
            ResultSet result = pstmt.executeQuery();
            
            result.beforeFirst();
            if(result.next())
            {
                
               
            numDossier=Integer.valueOf(result.getString(1)).intValue();
            prodNum=0;
            trouve=true;
            tr=true;
            
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }
            
           
            
            
        }    
        
        
        
        
        if(trouve)
        {
           if(typeIntegration == 1)
           {
                try {

                pstmt = tmpool.getConuser().prepareStatement(req2);

                pstmt.setInt(1,numDossier);
                pstmt.setInt(2,journal);


                ResultSet result = pstmt.executeQuery();

                result.beforeFirst();
                if(result.next())
                {

                    journ=result.getInt(1);


                }
            } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

            }
           }
           else
           {
              if(!tr)
              {  
                if(f.isCredit())
                journ=Integer.valueOf(param.get(2).toString()).intValue();
                else
                journ=Integer.valueOf(param.get(1).toString()).intValue();
              }
              else
              {
                if(f.isCredit())
                journ=Integer.valueOf(param.get(3).toString()).intValue();
                else
                journ=Integer.valueOf(param.get(4).toString()).intValue(); 
                  
                  
              }   
                
           }
            
           
            Achat_T achat=new Achat_T();
            
            //achat.setDrcleunik(numDossier);
            achat.setMontanteuro(f.ValeurBase);
            achat.setBase(f.ValeurBase);
            achat.setJota_cleunik(journ);
            
            achat.setJota_periode(0);
            
            
            try {
            
            pstmt = tmpool.getConuser().prepareStatement(req3);
            
            pstmt.setString(1,f.DateCreation);
            pstmt.setString(2,f.DateCreation);
            ResultSet result = pstmt.executeQuery();
            
            result.beforeFirst();
            if(result.next())
            {
                
            achat.setJota_periode(result.getInt(1));
            
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la requete", tmpool.getConuser());

        }
            
            //achat.setJota_periode(30);
            
            achat.setFrcleunik(459);
            achat.setJota_categorie(journal);
            achat.setDc("C");
            
            if(f.isCredit())
            achat.setDc("D");
            
            achat.setFraisgen(0);
            achat.setTva("0");
            achat.setTvamontant("0");
            achat.setTypeprodCleunik(2);
            achat.setCommentaire(f.Libelle);
            
            Date d1=new Date();
            
            d1.setYear(Integer.valueOf(f.DateCreation.substring(0,4)).intValue());
            d1.setMonth(Integer.valueOf(f.DateCreation.substring(4,6)).intValue());
            d1.setDay(Integer.valueOf(f.DateCreation.substring(6,8)).intValue());
            
            achat.setDate(d1);
            
            Date d2=new Date();
          
            d2.setYear(Integer.valueOf(f.DateEcheance.substring(0,4)).intValue());
            d2.setMonth(Integer.valueOf(f.DateEcheance.substring(4,6)).intValue());
            d2.setDay(Integer.valueOf(f.DateEcheance.substring(6,8)).intValue());
            
            achat.setEcheance(d1);
            
            
            
            //achat.setEcheance(f.DateEcheance);
            //achat.setDate(f.DateReception);
            
            
           //ParamComptable.JOURNAL_ACHAT
            
            //f.DateReception
            
            
            
            
            AchatCp cp=new AchatCp();

            cp.setDc("D");
            if(f.isCredit())
                cp.setDc("C");
            
            
            cp.setFrcleunik(achat.getFrcleunik());
            
            
            cp.setCatProd(2);
            cp.setCledossier(numDossier);
            cp.setCe_cleunik(458);
            cp.setPoPnr(f.NumPO);
            cp.setCleprod(prodNum);
            
            
            //tva
            
            cp.getTva1().setTva_id(4);
            cp.getTva1().setTva_code("MD0");
            cp.setBase(f.ValeurBase);
            
            cp.getTva1().setTva_base(f.ValeurBase);
            cp.getTva1().setTva_value("0");
            cp.getTva1().setPrixTot(f.ValeurBase);
            
            cp.getTva1().setTva_rate("0");
            cp.setDateMouv(d1);
           
            
            achat.getAchat().add(cp);
            
            
            
            
            //chat.setAchat(achat)
            
            
           
         
            numpiece=insertAchat(achat, urcleunik);
            System.out.println(cp.getBase());
          System.out.println("Achat");
          
          
            
        //}
            
            
           /* achat.setJota_cleunik(result.getInt(1));
                facture.setJota_periode(result.getInt(2));
                facture.setNumpiece(result.getString(3));
                facture.setDate(new Date(result.getString(4)));
                facture.setRecept(new Date(result.getString(5)));
                facture.setEcheance(new Date(result.getString(6)));
                facture.setFrcleunik(result.getInt(7));
                facture.setCommentaire(result.getString(8));
                facture.setLignecleunik(result.getLong(12));
                facture.setDrcleunik(result.getInt(13));
                facture.setTypeprodCleunik(result.getInt(14));
                facture.setFraisgen(0);
                facture.setMontanteuro(result.getString(9));
                if (result.getString(15).equals("A")) {
                    facture.setDc("C");
                } else if (result.getString(15).equals("NCAP")) {
                    facture.setDc("D");
                }
                facture.setExported(result.getLong(16));
            }*/
        
        }  
 }      
        try {
            
            pstmt = tmpool.getConuser().prepareStatement(req);
            
            if(!exist)
            {
                if(trouve && !tr)
                {
                pstmt.setLong(20,numDossier);
                pstmt.setInt(1, 1); //le hecleunik; le cleunik est genere automatiquement
                }
                else if(trouve && tr)
                {
                pstmt.setInt(1,0);
                pstmt.setLong(20,numDossier);
                }
            }
            else
            {
                pstmt.setInt(1,2);
                pstmt.setLong(20,dossierExist);
            }
            pstmt.setString(2, f.NumFournisseur);
            pstmt.setString(3, f.NumClient);
            pstmt.setString(4, f.Libelle);
            pstmt.setString(5, f.DateEcheance);
            pstmt.setString(6, f.NumPO);
            pstmt.setString(7, f.DateDepart);
            pstmt.setString(8, f.NomPassager);
            //pstmt.setString(9, f.CommisionTotalePO);
            pstmt.setDouble(9,Double.valueOf(f.CommisionTotalePO).doubleValue());
            //pstmt.setString(10, f.ValeurTotaleTaxePO);
            pstmt.setDouble(10,Double.valueOf(f.ValeurTotaleTaxePO).doubleValue());
            //pstmt.setDouble(14,Double.valueOf(f.MontantTotal).doubleValue());
            pstmt.setString(11, f.Domiciliation);
            pstmt.setString(12, f.AnnulationFacture);
            pstmt.setString(13, f.RemarqueCommission);
            
            //System.out.println(f.MontantTotal);
            
            
            if(f.MontantTotal.length()!=0)
            pstmt.setDouble(14,Double.valueOf(f.MontantTotal).doubleValue());
            else
             pstmt.setDouble(14,0);   
            
            
            
            //pstmt.setFloat(15,f.ValeurBase);
            
            if (f.isAcompte())
                pstmt.setString(15, "N");
            else
                pstmt.setString(15, "Y");

            if (f.isCredit())
                pstmt.setString(17, "Y");
            else
                pstmt.setString(17, "N");

            pstmt.setString(16, CalculDate.getTodayDate().toString());
            
            pstmt.setDouble(18,Double.valueOf(f.ValeurBase).doubleValue());
            pstmt.setLong(19,longtime);
           // pstmt.setLong(20,numDossier);
            
            if(!exist)
            pstmt.setString(21,numpiece);
            else
            pstmt.setString(21,numpieceExist);
            
            
           
            if (pstmt != null) {

                System.out.println(" LE STATEMENT EST: " + pstmt);
                pstmt.execute();
            } else {
                System.out.println(" LE STATEMENT N'EST PAS CORRECTE");

            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de l'insertion de la facture", tmpool.getConuser());

        }
        
        

    }

//end ajout
    public GeneralePrinting getLocaliteInfo(GeneralePrinting tmp, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String req = "SELECT c.cxcode,t.txtraduction FROM traductioncodpostaux t,codepostaux c WHERE c.cxcleunik=? AND c.cxcleunik=t.cxcleunik AND t.lmcleunik=?";
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            getUserInfo(tmp, pstmt);
            pstmt.setInt(1, tmp.getCxagencecleunik());
            pstmt.setInt(2, tmp.getClientLmcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Generiquecp infoagence = new Generiquecp();
                infoagence.setCp(result.getString(1));
                infoagence.setLocalité(result.getString(2));
                tmp.setAgence(infoagence);
            }
            pstmt.setInt(1, tmp.getClientCxCleunik());
            pstmt.setInt(2, tmp.getClientLmcleunik());
            result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Generiquecp client = new Generiquecp();
                client.setCp(result.getString(1));
                client.setLocalité(result.getString(2));
                tmp.setClient(client);
            }
            return tmp;
        } catch (SQLException sn) {
            renvexception(sn, "Erreur lors de la demande des informations clientes", tmpool.getConuser());
        }
        return null;
    }

    private Clients_T getDossierClientLocal(int csCleunik, Loginusers_T currentUser) {
        Clients_T Client = null;
        try {
            Client = (Clients_T) new Clients_T().selectObject(this.serveur, currentUser, csCleunik);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Client;
    }

    private String generenumdossier(Connection con, int urcleunik) throws SQLException {
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("SELECT u.urcleunik,e.eeabrev,e.eecompteur,e.eecleunik FROM entite e,user u WHERE u.eecleunik=e.eecleunik AND u.urcleunik=?");
//"SELECT u.urcleunik,e.eeabrev,e.eecompteur,e.eecleunik FROM entite e,user u WHERE u.eecleunik=e.eecleunik AND u.urcleunik=?";
        pstmt.setInt(1, urcleunik);
        ResultSet result = pstmt.executeQuery();
        result.first();
        String entiteabrev = result.getString(2);
        String année = "" + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String abrevannée = année.substring(3, 4);
        int numcompteur = result.getInt(3);
        int entite = result.getInt(4);
        String numero = "";
        if (numcompteur < 10)
            numero = "0000" + numcompteur;
        else if (numcompteur < 100)
            numero = "000" + numcompteur;
        else if (numcompteur < 1000)
            numero = "00" + numcompteur;
        else if (numcompteur < 10000)
            numero = "0" + numcompteur;
        else if (numcompteur < 100000)
            numero = "" + numcompteur;
        else if (numcompteur == 100000) {
            numcompteur = 1;
            numero = "00000" + 1;
        }
        pstmt = con.prepareStatement("UPDATE entite SET eecompteur=? WHERE eecleunik=?");
        pstmt.setInt(1, (numcompteur + 1));
        pstmt.setInt(2, entite);
        pstmt.execute();
        String numeroDossier = entiteabrev + abrevannée + numero;
        System.out.println("[*************numero du dossier" + numeroDossier);
        return numeroDossier;
    }

    private ServeurSqlFailure renvexception(SQLException se, String message, Connection con) throws ServeurSqlFailure {
        System.out.println(message);
        se.printStackTrace();
        Transaction.rollback(con);
        ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
        sqe.setSqlException(se);
        sqe.setErrorcode(se.getErrorCode());
        throw sqe;
    }

    public void setDossierBdc(long cledossier, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            String requete = "UPDATE dossier SET dr_bdc =1 WHERE dr_cleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setLong(1, cledossier);
            pstmt.execute();
        } catch (SQLException sn) {
            renvexception(sn, "Erreur lors de la demande des informations clientes", tmpool.getConuser());

        }
    }

    private void checkDossierSolde(Dossier_T dossier) {
        double sommepayement = 0;
        if (dossier.getPayement() != null) {
            java.util.TreeMap treemap = dossier.getPayement();
            java.util.Set set = treemap.keySet();
            java.util.Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
                Payement_T lpayement = (Payement_T) treemap.get((Long) iterator.next());
                sommepayement = sommepayement + lpayement.getPrix();
            }
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomme payement " + sommepayement + "  montant dossier " + dossier.getDrtotalprix());
        if (sommepayement == dossier.getDrtotalprix())
            dossier.setDr_solde(1); //le dossier est soldé
        else
            dossier.setDr_solde(0);
        if (dossier.getPassager() != null)
            dossier.setDr_numpass(dossier.getPassager().size()); //mise à jour du nombre de passager dans le dossier
    }
    
    public void setDateNCService(int urCleunik,srcastra.astra.sys.classetransfert.utils.Date d,int drcleunik,String numpiece,int entite,boolean facture) throws RemoteException, ServeurSqlFailure {
        
       // java.util.Calendar dd=null;
        
       //dd.set(d.getYear(),d.getMonth(),d.getDay());
        
       // java.util.Date day=dd.getTime();
        //dd.setsetDay(d.getDay());
        //dd.setMonth(d.getMonth());
        //dd.setYear(d.getYear());
        
        
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urCleunik, true);
        
        String requete="update historique2 set hedatemouv=?, heperiode=? where drcleunik=? and henumpiece=? and jxcleunik=?";
        
        String periode="SELECT p.pede_cleunik,p.pede_numper,p.pede_de,p.pede_a,p.pede_locked,p.exle_cleunik,p.pede_actif FROM periode p,exercicecomptable e  WHERE ? BETWEEN pede_de  AND pede_a AND p.exle_cleunik=e.exle_cleunik AND e.exle_courant='O'";
        
        String journal="";
        
        if(facture)
        journal="SELECT * FROM journcompta WHERE jota_categorie =21 AND eecleunik=?";
        else
        journal="SELECT * FROM journcompta WHERE jota_categorie =18 AND eecleunik=?";
        
        String dd=d.toString();
        
        srcastra.astra.sys.classetransfert.utils.Date cp=new srcastra.astra.sys.classetransfert.utils.Date(d.getYear(),d.getMonth(),d.getDay());
        
        System.out.println(cp.toString());
        
        
        
        
        int per=0;
        int jour=21;
        
        try{
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(periode);
        pstmt.setString(1,cp.toString());
        //pstmt.setInt(2,drcleunik);
        ResultSet r=pstmt.executeQuery();
       
        if(r.next())
            per=r.getInt(1);
        
        pstmt=tmpool.getConuser().prepareStatement(journal);
        pstmt.setInt(1, entite);
        r=pstmt.executeQuery();
        
        if(r.next())
            jour=r.getInt(1);
        
        
        pstmt=tmpool.getConuser().prepareStatement(requete);
        pstmt.setString(1,d.toString());
        pstmt.setInt(2,per);
        pstmt.setInt(3,drcleunik);
        pstmt.setString(4,numpiece);
        pstmt.setInt(5,jour);
        
        pstmt.execute();
        
        
        
         } catch (SQLException se) {
            se.printStackTrace();
        }
        
        
    }
    
    
    public Hashtable insertDossierLocale(int urCleunik, Dossier_T dossier, Poolconnection tmpool) throws RemoteException, ServeurSqlFailure, SQLException {

        System.out.println("[*************user]" + tmpool.getUrsociete());
        String date;
        Hashtable returnvalue = null;
        double t1 = System.currentTimeMillis();
        if (!dossier.isRattrap() && !dossier.isNcFactService() && !dossier.isGroupe()) {    //si le dossier n'est pas de rattrapage alors on génère le numéro de dossier
            dossier.setDrnumdos(generenumdossier(tmpool.getConuser(), tmpool.getUrcle2()));
        }
        checkDossierSolde(dossier); //vérifie si le dossier est soldé
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement("INSERT INTO dossier (dr_numdos,dr_typbooking,dr_reference,dr_status,dr_datetimecrea,cscleunik,urcleunik,dr_prix,dr_taxe,dr_tva,dr_totalprix,cscleunik_fact,dr_date_depart,dr_date_retour,dr_nbrenuit,dr_nbj_av_eche,dr_type_payement,dr_date_echeance,client,fournisseur,hotel,destination,po,dr_facture,dr_paye,dr_asannul,dr_asbagage,dr_ascomp,dr_bdc,dr_nbrpass,dr_solde,dr_nbjour,dr_visa,dr_identchild,dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc) VALUES (?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, dossier.getDrnumdos());
        pstmt.setInt(2, dossier.getDr_type_booking());
        pstmt.setString(3, dossier.getDrreference());
        pstmt.setInt(4, dossier.getDrstatus());
        pstmt.setInt(5, dossier.getCscleunik());
        pstmt.setInt(6, tmpool.getUrcle2());
        pstmt.setDouble(7, dossier.getDrprix());
        pstmt.setDouble(8, dossier.getDrtaxe());
        pstmt.setDouble(9, dossier.getDrtva());
        pstmt.setDouble(10, dossier.getDrtotalprix());
        pstmt.setInt(11, dossier.getCscleunik_fact());
        if (dossier.getDr_date_depart() == null) date = "0000-00-00 00:00:00"; else date = dossier.getDr_date_depart().toString();
        pstmt.setString(12, date);
        if (dossier.getDr_date_retour() == null) date = "0000-00-00 00:00:00"; else date = dossier.getDr_date_retour().toString();
        pstmt.setString(13, date);
        pstmt.setInt(14, dossier.getDr_nbrenuit());
        pstmt.setInt(15, dossier.getDr_nbj_av_eche());
        pstmt.setInt(16, dossier.getDr_type_payement());
        if (dossier.getDr_date_echeance() == null) date = "0000-00-00 00:00:00"; else date = dossier.getDr_date_echeance().toString();
        pstmt.setString(17, date);
        pstmt.setString(18, dossier.getClientContractuel().getCsnom());
        pstmt.setString(19, dossier.getFournIntitule());
        pstmt.setString(20, dossier.getHotelIntitule());
        pstmt.setString(21, dossier.getDestIntitule());
        pstmt.setString(22, dossier.getPoBilletIntitule());
        pstmt.setInt(23, dossier.getDr_facture());
        pstmt.setDouble(24, dossier.getDr_Paye());
        pstmt.setInt(25, dossier.getDr_asannul());
        pstmt.setInt(26, dossier.getDr_asbagage());
        pstmt.setInt(27, dossier.getDr_ascomp());
        pstmt.setInt(28, dossier.getDr_bcd());
        pstmt.setInt(29, dossier.getDr_numpass());
        pstmt.setInt(30, dossier.getDr_solde());
        pstmt.setInt(31, dossier.getDr_nbrjour());
        pstmt.setInt(32, dossier.getDr_visa());
        pstmt.setInt(33, dossier.getDr_identchild());
//dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc
        pstmt.setInt(34, dossier.getDr_voy());
        pstmt.setInt(35, dossier.getDr_annul_lim());
        pstmt.setInt(36, dossier.getDr_pass_ob());
        pstmt.setInt(37, dossier.getDr_ident());
        pstmt.setInt(38, dossier.getDr_cee());
        pstmt.setInt(39, dossier.getDr_vacc());
        pstmt.execute();
        double t2 = System.currentTimeMillis();
        double t3 = (t2 - t1) / 1000;
        t1 = System.currentTimeMillis();
        System.out.println("\n\n[**************]temps pour insertion dossier :" + t3);
        double cle = getId(tmpool.getConuser());   //récupére la clé unique du dossier générée par la base de donnée

        returnvalue = new Hashtable();   //la valeur de retour de cette fonction est une hashtable 2 éléments, l'un est la clé unique du dossier, l'autre est le numéro de dossier généré
        returnvalue.put("id", new Integer(new Double(cle).intValue()));
        returnvalue.put("numdos", dossier.getDrnumdos());
        dossier.setDrcleunik(getId(tmpool.getConuser())); //mise à jour du dossier avec sa propre clé
        dealWhithMemo(dossier, tmpool);  //gestion des mémos du dossiers
        System.out.println("ok dossier insérer, dossier numero:" + dossier.getDrnumdos());
        insertPassager(dossier, tmpool.getConuser(), cle); //insertion des passagers du mémo
        Produit_gestion.InsertProduit(dossier, tmpool.getConuser(), cle, pstmt, tmpool, serveur); //classe qui gére l'insertion, la modification et l'annulation des produit
        dossier.setNewreccord(true);
        t2 = System.currentTimeMillis();
        t3 = (t2 - t1) / 1000;
        System.out.println("\n\n[**************]temps pour des produit et passager :" + t3);
        t1 = System.currentTimeMillis();
        Configuration config = new Configuration(tmpool.getConuser(), pstmt, dossier.getDrcleunik(), 0, 0, dossier.getClientContractuel().getCscleunik(), 0, 0, serveur, tmpool.getUrcle2(), dossier.getDr_facture());
        config.setDossier(dossier);
        config.setCle2(urCleunik);
        config.setEecleunik(tmpool.getNumeroentite());
        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[ppppppppppppp]DossierImplement Serveur: " + serveur);
        srcastra.astra.sys.compta.checkcompte.Check.getTransaction(tmpool, serveur.getTransactSyn(), serveur);
        Object check = new MangageChaineComptable(config, serveur, urCleunik).genereOperationComptable(dossier);
        t2 = System.currentTimeMillis();
        t3 = (t2 - t1) / 1000;
        System.out.println("\n\n[**************]temps pour l'insertion comptable :" + t3);

        returnvalue.put("c", check);
        this.dossier = dossier;
        return returnvalue;
    }

    public Hashtable insertDossier(int urCleunik, Dossier_T dossier) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urCleunik, true);
        Hashtable returnvalue = null;
        try {
            synchronized (serveur.getNumdosSyn()) {
                Transaction.begin(tmpool.getConuser());
                returnvalue = insertDossierLocale(urCleunik, dossier, tmpool);
                Transaction.commit(tmpool.getConuser());
            }
        } catch (SQLException se) {
            renvexception(se, "[************DOSSIER:INSERT stacktrace: ", tmpool.getConuser());
        } catch (Exception e) {
            e.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
        }
        return returnvalue;
    }

    public void insertDossierFrais(Dossier_T dossier) {
    }

    public void insertPassager(Dossier_T dossier, Connection con, double cledossier) throws SQLException {
        String date;
        if (dossier.getPassager() == null)
            System.out.println("[***********DOSSIER_ATTENTION]:aucun passagers");
        else
            System.out.println("[**********DOSSIER: nombre de passager ==" + dossier.getPassager().size());
        if (dossier.getPassager() != null) {
            ArrayList passager = dossier.getPassager();
            for (int i = 0; i < passager.size(); i++) {
                Passager_T tmpPassager = (Passager_T) passager.get(i);
                if (!tmpPassager.isDeleted())
                    insertPassagerLocale(con, tmpPassager, cledossier);
            }
        }
    }

    private void insertPassagerLocale(Connection con, Passager_T tmpPassager, double cledossier) throws SQLException {
        int val;
        String date;
        tmpPassager.setDr_cleunik(cledossier);
        double cle = 0;
        PreparedStatement pstmt = null;
        if (tmpPassager.alreadyInBD == false) {
            System.out.println("passager already in bd");
            pstmt = con.prepareStatement(INSERT_PASSAGER);
            pstmt.setString(1, tmpPassager.getPr_nom());
            pstmt.setString(2, tmpPassager.getPr_prénom());
            pstmt.setString(3, tmpPassager.getPr_tel());
            pstmt.setString(4, tmpPassager.getPr_fax());
            pstmt.setString(5, tmpPassager.getPr_gsm());
            pstmt.setString(6, tmpPassager.getPr_email());
            val = tmpPassager.getLecleunik() == -1 ? 0 : tmpPassager.getLecleunik();
            pstmt.setInt(7, val);
            if (tmpPassager.getPr_datenaissance() == null) date = "0000-00-00 00:00:00"; else date = tmpPassager.getPr_datenaissance().toString();
            pstmt.setString(8, date);
            val = tmpPassager.getPr_nat() == -1 ? 0 : tmpPassager.getPr_nat();
            pstmt.setInt(9, val);
            pstmt.setInt(10, tmpPassager.getTscleunik());
            val = tmpPassager.getCscleunik() == -1 ? 0 : tmpPassager.getCscleunik();
            pstmt.setInt(11, val);
            val = tmpPassager.getPyscleunik() == -1 ? 0 : tmpPassager.getPyscleunik();
            pstmt.setInt(12, val);
            pstmt.setString(13, tmpPassager.getPr_code_mailing());
            pstmt.setString(14, tmpPassager.getPr_bookingclass());
            pstmt.setInt(15, tmpPassager.getPr_windowseat());
            pstmt.setInt(16, tmpPassager.getPr_smoking());
            pstmt.setString(17, tmpPassager.getPr_adrese());
            pstmt.setInt(18, tmpPassager.getMainPassager());
            pstmt.execute();
            tmpPassager.setPr_cleunik((double) getId(con));
        }
        pstmt = con.prepareStatement("INSERT INTO intermediairepassager (dr_cleunik,pr_cleunik,cscleunik ) VALUES (?,?,?)");
        pstmt.setDouble(1, tmpPassager.getDr_cleunik());
        pstmt.setDouble(2, tmpPassager.getPr_cleunik());
        pstmt.setLong(3, tmpPassager.getCscleunik2());
        pstmt.execute();
    }

    private void deletePassager(Connection con, int drcleunik, double prcleunik) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DELETE_PASSAGER);
        pstmt.setInt(1, drcleunik);
        pstmt.setDouble(2, prcleunik);
        pstmt.execute();
    }

    public void dossierNoteCredit(int urCleunik, Dossier_T dossier) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urCleunik, true);
// try{
        PreparedStatement pstmt = null;
// new GenereLigneComptable().noteDeCrédit(dossier,tmpool.getConuser(),pstmt);
//  }catch(SQLException sn){
// new javax.swing.JOptionPane().showMessageDialog(null,sn.printStackTrace());
//    sn.printStackTrace();
//renvexception(sn,"[************DOSSIER:INSERT stacktrace: ",tmpool.getConuser());

// }

    }
/*  public void insertAvionticket(Dossier_T dossier,Connection con,double cledossier)throws SQLException{
String date;
String hour;
System.out.println("\n\n\n[***********AVIONTICKET:je suis dans la fonction");
if(dossier.getAvionTicket()!=null)
{
System.out.println("[************AVIONTICKET:ok arraylist non null");
ArrayList avionTicket=dossier.getAvionTicket();
PreparedStatement pstmt=con.prepareStatement(INSERT_TICKET);
for(int i=0;i<avionTicket.size();i++)
{
ticket= (Avion_ticket_T)avionTicket.get(i);

//Transaction.begin(tmpool.getConuser());
pstmt.setString(1,ticket.getAt_pnr());
pstmt.setInt(2,ticket.getFrgtcleunik());
pstmt.setInt(3,ticket.getAt_type_billet());
pstmt.setInt(4,ticket.getAt_vendu());
pstmt.setString(5,ticket.getAt_num_billet());
if(ticket.getAt_date_emission()==null) date="0000-00-00 00:00:00"; else date=ticket.getAt_date_emission().toString();
pstmt.setString(6,date);
if(ticket.getAt_date_depart()==null) date="0000-00-00 00:00:00"; else date=ticket.getAt_date_depart().toString();
pstmt.setString(7,date);
pstmt.setInt(8,ticket.getAt_annul_type());
if(ticket.getAt_annul_date()==null) date="0000-00-00 00:00:00"; else date=ticket.getAt_annul_date().toString();
pstmt.setString(9,date);
pstmt.setFloat(10,ticket.getAt_annul_tot());
pstmt.setString(11,ticket.getAt_numrefund());
if(ticket.getAt_cloture_date()==null) date="0000-00-00 00:00:00"; else date=ticket.getAt_cloture_date().toString();
pstmt.setString(12,date);
pstmt.setDouble(13,cledossier);
pstmt.setInt(14,ticket.getAt_mode_paiement());
pstmt.setString(15,ticket.getAt_num_cccf());
pstmt.setFloat(16,ticket.getAt_val_facial());
pstmt.setFloat(17,ticket.getAt_val_vente());
pstmt.setFloat(18,ticket.getAt_val_nette());
pstmt.setFloat(19,ticket.getAt_fullfare());
pstmt.setInt(20,ticket.getCoe_cleunik());
pstmt.setInt(21,ticket.getAt_etat());
pstmt.setFloat(22,ticket.getAt_val_com());
pstmt.setString(23,ticket.getAt_memo());
pstmt.execute();
long cle2=getId(con);
if(ticket.getConjonctionList()!=null)
{
ArrayList conjonctionArray=ticket.getConjonctionList();
for(int j=0;j<conjonctionArray.size();j++)
{
Conjonction_T conjonction=(Conjonction_T)conjonctionArray.get(j);
pstmt=con.prepareStatement(INSERT_CONJONCTION);
pstmt.setString(1,conjonction.getNumbillet());
pstmt.setLong(2,cle2);
pstmt.execute();
cle2=getId(con);
if(conjonction.getSegmentList()!=null)
{
for(int k=0;k<conjonction.getSegmentList().size();k++){
Avion_segment_T  segment=(Avion_segment_T)conjonction.getSegmentList().get(k);
pstmt=con.prepareStatement(INSERT_SEGMENT);
pstmt.setLong(1,cle2);
pstmt.setInt(2,segment.getAs_position());
pstmt.setInt(3,segment.getAs_routing_de());
pstmt.setInt(4,segment.getAs_routing_a());
pstmt.setInt(5,segment.getCoe_cleunik());
pstmt.setString(6,segment.getAs_numero_vol());
if(segment.getAs_date_départ()==null) date="0000-00-00 00:00:00"; else date=segment.getAs_date_départ().toString();
pstmt.setString(7,date);
pstmt.setString(8,"00:00:00");
pstmt.setString(9,"00:00:00");
pstmt.setString(10,segment.getAs_farebasis());
pstmt.setInt(11,segment.getAs_classe());
pstmt.setInt(12,segment.getCoe_cleunik());
pstmt.setInt(13,segment.getVoidSegment());
pstmt.setInt(14,segment.getNonEditableSegment());
pstmt.execute();
}
}
}
}
}
}
}*/
    public void chargeFraisDossier(Dossier_T dossier, Connection con, PreparedStatement pstmt, Loginusers_T user2) throws SQLException {
        String requete = "SELECT f.fdr_cleunik,f.fdr_libellé,f.longtime,h.hevaleur,h.hevaleurbase,h.hevaleurtva  FROM  fraisdossier f,historique2 h WHERE h.lignecleunik=f.fdr_cleunik  AND h.hetypeligne='F' AND h.ctprocleunik=" + produit_T.FRAIS + " AND dr_cleunik=? AND f.annuler=0 AND h.hedossiercourant='O'";
        pstmt = con.prepareStatement(requete);
        pstmt.setLong(1, dossier.getDrcleunik());
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            Frais_T frais = new Frais_T();
            frais.setCleunik(result.getLong(1));
            frais.setLibelleCompta(result.getString(2));
            frais.setLongtime(result.getLong(3));
            frais.setValeur_tot_tva_inc(result.getDouble(4));
            frais.setValeur_tot(result.getDouble(5));
            frais.setMontant_tva(result.getDouble(6));
            dossier.addFraisDossier(frais);
        }
    }

    public Object modifyDossier(int urCleunik, Dossier_T dossier, Loginusers_T currentuser) throws RemoteException, ServeurSqlFailure {

        Poolconnection tmpool = serveur.getConnectionAndCheck(urCleunik, true);
        Object check = null;
        try {
            synchronized (serveur.getNumdosSyn()) {
                checkDossierSolde(dossier);
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement("UPDATE  dossier set dr_numdos=?,dr_typbooking=?,dr_reference=?,dr_status=?,cscleunik=?,dr_prix=?,dr_taxe=?,dr_tva=?,dr_totalprix=?,cscleunik_fact=?,dr_date_depart=?,dr_date_retour=?,dr_nbrenuit=?,dr_facture=?, dr_paye=?,dr_asannul=?,dr_asbagage=?,dr_ascomp=?,dr_bdc=?,dr_nbrpass=?,dr_solde=?,dr_nbjour=?,dr_visa=?,dr_identchild=?,dr_voy=?,dr_annul_lim=?,dr_pass_ob=?,dr_ident=?,dr_cee=?,dr_vacc=?,dr_nbj_av_eche=?,dr_date_echeance=?  WHERE dr_cleunik=?");
// pstmt.setInt(1,dossier.getDrcleunik());
                pstmt.setString(1, dossier.getDrnumdos());
                pstmt.setInt(2, dossier.getDr_type_booking());
                pstmt.setString(3, dossier.getDrreference());
                pstmt.setInt(4, dossier.getDrstatus());
                pstmt.setInt(5, dossier.getCscleunik());
                pstmt.setDouble(6, dossier.getDrprix());
                pstmt.setDouble(7, dossier.getDrtaxe());
                pstmt.setDouble(8, dossier.getDrtva());
                pstmt.setDouble(9, dossier.getDrtotalprix());
                pstmt.setInt(10, dossier.getCscleunik_fact());
                pstmt.setString(11, dossier.getDr_date_depart().toString());
                pstmt.setString(12, dossier.getDr_date_retour().toString());
                pstmt.setInt(13, dossier.getDr_nbrenuit());
                pstmt.setInt(14, dossier.getDr_facture());
                pstmt.setDouble(15, dossier.getDr_Paye());
                pstmt.setInt(16, dossier.getDr_asannul());
                pstmt.setInt(17, dossier.getDr_asbagage());
                pstmt.setInt(18, dossier.getDr_ascomp());
                pstmt.setInt(19, dossier.getDr_bcd());
                pstmt.setInt(20, dossier.getDr_numpass());
                pstmt.setInt(21, dossier.getDr_solde());
                pstmt.setInt(22, dossier.getDr_nbrjour());
                pstmt.setInt(23, dossier.getDr_visa());
                pstmt.setInt(24, dossier.getDr_identchild());
                pstmt.setInt(25, dossier.getDr_voy());
                pstmt.setInt(26, dossier.getDr_annul_lim());
                pstmt.setInt(27, dossier.getDr_pass_ob());
                pstmt.setInt(28, dossier.getDr_ident());
                pstmt.setInt(29, dossier.getDr_cee());
                pstmt.setInt(30, dossier.getDr_vacc());
                
                pstmt.setInt(31, dossier.getDr_nbj_av_eche());
                pstmt.setString(32, dossier.getDr_date_echeance().toString());
                
                
                pstmt.setInt(33, dossier.getDrcleunik());
//  if(dossier.getDr_facture()==1 && dossier.isComptaModify()); //je ne sais pas pourquoi
//else
                Transaction.begin(tmpool.getConuser());
                pstmt.execute();
                dealWhithMemo(dossier, tmpool);
                pstmt = tmpool.getConuser().prepareStatement(this.MODIF_DOSSIER2);
                pstmt.setInt(1, dossier.getDrcleunik());
                pstmt.setInt(2, tmpool.getUrcle2());
                pstmt.execute();
                modifPassager(urCleunik, dossier, tmpool.getConuser());
                Produit_gestion.modifyproduct(dossier, tmpool.getConuser(), pstmt, this.serveur, currentuser, tmpool);
                Configuration config = new Configuration(tmpool.getConuser(), pstmt, dossier.getDrcleunik(), 0, 0, dossier.getClientContractuel().getCscleunik(), 0, 0, serveur, tmpool.getUrcle2(), dossier.getDr_facture());
                config.setDossier(dossier);
                config.setCle2(urCleunik);
                config.setEecleunik(tmpool.getNumeroentite());
                Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[ppppppppppppp]DossierImplement Serveur: " + serveur);
                srcastra.astra.sys.compta.checkcompte.Check.getTransaction(tmpool, serveur.getTransactSyn(), serveur);
                MangageChaineComptable chaine = new MangageChaineComptable(config, serveur, urCleunik);
                chaine.setServeur(this.serveur);
                check = chaine.genereOperationComptable(dossier);
                Transaction.commit(tmpool.getConuser());
                this.dossier = dossier;
            }
            return check;
        } catch (SQLException se) {
            renvexception(se, "[************DOSSIER:MODIFY stacktrace: ", tmpool.getConuser());
        } catch (Exception e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
/*System.out.println("[************DOSSIER: stacktrace: ");
se.printStackTrace();
Transaction.rollback(tmpool.getConuser());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setSqlException(se);
sqe.setErrorcode(se.getErrorCode());
throw sqe;    */
        }
        return null;
    }

    private void modifyDossierLocal(int urCleunik, Dossier_T dossier, Loginusers_T currentuser, Poolconnection tmpool) throws RemoteException, ServeurSqlFailure {
        Object check = null;
        try {
            synchronized (serveur.getNumdosSyn()) {
                checkDossierSolde(dossier);
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement("UPDATE  dossier set dr_numdos=?,dr_typbooking=?,dr_reference=?,dr_status=?,cscleunik=?,dr_prix=?,dr_taxe=?,dr_tva=?,dr_totalprix=?,cscleunik_fact=?,dr_date_depart=?,dr_date_retour=?,dr_nbrenuit=?,dr_facture=?, dr_paye=?,dr_asannul=?,dr_asbagage=?,dr_ascomp=?,dr_bdc=?,dr_nbrpass=?,dr_solde=?,dr_nbjour=?,dr_visa=?,dr_identchild=?,dr_voy=?,dr_annul_lim=?,dr_pass_ob=?,dr_ident=?,dr_cee=?,dr_vacc=?  WHERE dr_cleunik=?");
// pstmt.setInt(1,dossier.getDrcleunik());
                pstmt.setString(1, dossier.getDrnumdos());
                pstmt.setInt(2, dossier.getDr_type_booking());
                pstmt.setString(3, dossier.getDrreference());
                pstmt.setInt(4, dossier.getDrstatus());
                pstmt.setInt(5, dossier.getCscleunik());
                pstmt.setDouble(6, dossier.getDrprix());
                pstmt.setDouble(7, dossier.getDrtaxe());
                pstmt.setDouble(8, dossier.getDrtva());
                pstmt.setDouble(9, dossier.getDrtotalprix());
                pstmt.setInt(10, dossier.getCscleunik_fact());
                pstmt.setString(11, dossier.getDr_date_depart().toString());
                pstmt.setString(12, dossier.getDr_date_retour().toString());
                pstmt.setInt(13, dossier.getDr_nbrenuit());
                pstmt.setInt(14, dossier.getDr_facture());
                pstmt.setDouble(15, dossier.getDr_Paye());
                pstmt.setInt(16, dossier.getDr_asannul());
                pstmt.setInt(17, dossier.getDr_asbagage());
                pstmt.setInt(18, dossier.getDr_ascomp());
                pstmt.setInt(19, dossier.getDr_bcd());
                pstmt.setInt(20, dossier.getDr_numpass());
                pstmt.setInt(21, dossier.getDr_solde());
                pstmt.setInt(22, dossier.getDr_nbrjour());
                pstmt.setInt(23, dossier.getDr_visa());
                pstmt.setInt(24, dossier.getDr_identchild());
                pstmt.setInt(25, dossier.getDr_voy());
                pstmt.setInt(26, dossier.getDr_annul_lim());
                pstmt.setInt(27, dossier.getDr_pass_ob());
                pstmt.setInt(28, dossier.getDr_ident());
                pstmt.setInt(29, dossier.getDr_cee());
                pstmt.setInt(30, dossier.getDr_vacc());
                pstmt.setInt(31, dossier.getDrcleunik());
//  if(dossier.getDr_facture()==1 && dossier.isComptaModify());
//else
//Transaction.begin(tmpool.getConuser());
                pstmt.execute();
                dealWhithMemo(dossier, tmpool);
                pstmt = tmpool.getConuser().prepareStatement(this.MODIF_DOSSIER2);
                pstmt.setInt(1, dossier.getDrcleunik());
                pstmt.setInt(2, tmpool.getUrcle2());
                pstmt.execute();
                modifPassager(urCleunik, dossier, tmpool.getConuser());
                Produit_gestion.modifyproduct(dossier, tmpool.getConuser(), pstmt, this.serveur, currentuser, tmpool);
                Configuration config = new Configuration(tmpool.getConuser(), pstmt, dossier.getDrcleunik(), 0, 0, dossier.getClientContractuel().getCscleunik(), 0, 0, serveur, tmpool.getUrcle2(), dossier.getDr_facture());
                config.setDossier(dossier);
                config.setCle2(urCleunik);
                config.setEecleunik(tmpool.getNumeroentite());
                Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[ppppppppppppp]DossierImplement Serveur: " + serveur);
                srcastra.astra.sys.compta.checkcompte.Check.getTransaction(tmpool, serveur.getTransactSyn(), serveur);
                MangageChaineComptable chaine = new MangageChaineComptable(config, serveur, urCleunik);
                chaine.setServeur(this.serveur);
                check = chaine.genereOperationComptable(dossier);
                this.dossier = dossier;
            }
// return check;
        } catch (SQLException se) {
            renvexception(se, "[************DOSSIER:MODIFY stacktrace: ", tmpool.getConuser());
        } catch (Exception e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
/*System.out.println("[************DOSSIER: stacktrace: ");
se.printStackTrace();
Transaction.rollback(tmpool.getConuser());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setSqlException(se);
sqe.setErrorcode(se.getErrorCode());
throw sqe;    */
        }
//       return null;
    }

    public void modifPassager(int urCleunik, Dossier_T dossier, Connection con) throws RemoteException, SQLException {
        if (dossier.getPassager() != null) {
            ArrayList passager = dossier.getPassager();
            for (int i = 0; i < passager.size(); i++) {
                Passager_T tmpPassager = (Passager_T) passager.get(i);
                if (tmpPassager.isModify() == true) {
                    PreparedStatement pstmt = con.prepareStatement(this.MODIF_PASSAGER);
                    pstmt.setString(1, tmpPassager.getPr_nom());
                    pstmt.setString(2, tmpPassager.getPr_prénom());
                    pstmt.setString(3, tmpPassager.getPr_tel());
                    pstmt.setString(4, tmpPassager.getPr_fax());
                    pstmt.setString(5, tmpPassager.getPr_gsm());
                    pstmt.setString(6, tmpPassager.getPr_email());
                    pstmt.setInt(7, tmpPassager.getLecleunik());
                    pstmt.setString(8, tmpPassager.getPr_datenaissance().toString());
                    pstmt.setInt(9, tmpPassager.getPr_nat());
                    pstmt.setInt(10, tmpPassager.getTscleunik());
                    pstmt.setInt(11, tmpPassager.getCscleunik());
                    pstmt.setInt(12, tmpPassager.getPyscleunik());
                    pstmt.setString(13, tmpPassager.getPr_code_mailing());
                    pstmt.setString(14, tmpPassager.getPr_bookingclass());
                    pstmt.setInt(15, tmpPassager.getPr_windowseat());
                    pstmt.setInt(16, tmpPassager.getPr_smoking());
                    pstmt.setString(17, tmpPassager.getPr_adrese());
                    pstmt.setInt(18, tmpPassager.getMainPassager());
                    pstmt.setDouble(19, tmpPassager.getPr_cleunik());
                    pstmt.execute();
                } else if (tmpPassager.isNewReccord() == true) {
                    insertPassagerLocale(con, tmpPassager, dossier.getDrcleunik());
                } else if (tmpPassager.isDeleted() == true) {
                    deletePassager(con, dossier.getDrcleunik(), tmpPassager.getPr_cleunik());
                }
            }
        }
    }

    public void remoteUnlock(int dossierCle) throws RemoteException, ServeurSqlFailure {
        unLockDossier(new Integer(dossierCle));
    }

    private void checkDate(Dossier_T dossier, Poolconnection tmpool) throws SQLException {
        Date creation = null;
        Date facturation = null;
        if (dossier.getDr_facture() == 1) {
            String requete = "SELECT hedatecreation,hedatemouv FROM historique2 WHERE hedossiercourant='O' AND hetypeligne='B' AND henotcpt=1 AND drcleunik=?";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setInt(1, dossier.getDrcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            int i = 0;
            while (result.next()) {
                creation = new Date(result.getString(1));
                facturation = new Date(result.getString(2));
                i++;
//    }
                System.out.println(i + " creation " + creation.toString() + "  facturation" + facturation.toString());
            }
            if (facturation != null) {
                if (facturation.toString().equals("0-00-00 00:00:00")) {
                    System.out.println("badfacuration");
                    dossier.setDr_date_facturation(creation);
                } else {
                    dossier.setDr_date_facturation(facturation);
                }
            } else {
//attention ceci est peut etre foireux
                dossier.setDr_date_facturation(CalculDate.getTodayDate());
            }
        }
    }
    
    
        public ArrayList chargeNCService(Loginusers_T currentUser, int dossierCleunik, boolean lock) throws RemoteException, ServeurSqlFailure {

        Poolconnection tmpool = serveur.getConnectionAndCheck(currentUser.getUrcleunik(), true);
        Dossier_T dossier;
        String typerequete;
        System.out.println("[WAAAAAAAAAAAAAAAAAAAAAAZZZZZAAAAAAAAAAAAAAAAAA**************]je rentre dans chargedossier");
        ArrayList returnvalue = new ArrayList();
        boolean lock2;

        if (dossierCleunik == 0)
            typerequete = "SELECT d.dr_cleunik,d.dr_numdos,d.dr_typbooking,d.dr_reference,d.dr_status,d.dr_datetimecrea,d.cscleunik,d.urcleunik,d.dr_prix,d.dr_taxe,d.dr_tva,d.dr_totalprix,d.cscleunik_fact,d.dr_date_depart,d.dr_date_retour,d.dr_nbrenuit,d.dr_nbj_av_eche,d.dr_type_payement,d.dr_date_echeance, d.dr_facture, d.dr_paye , u.eecleunik,u.urnom,dr_asannul,dr_asbagage,dr_ascomp,dr_bdc,dr_nbjour,dr_visa,dr_identchild,dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc  FROM dossier d,user u WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0 ORDER BY d.dr_numdos";
        else
            typerequete = "SELECT d.dr_cleunik,d.dr_numdos,d.dr_typbooking,d.dr_reference,d.dr_status,d.dr_datetimecrea,d.cscleunik,d.urcleunik,d.dr_prix,d.dr_taxe,d.dr_tva,d.dr_totalprix,d.cscleunik_fact,d.dr_date_depart,d.dr_date_retour,d.dr_nbrenuit,d.dr_nbj_av_eche,d.dr_type_payement,d.dr_date_echeance, d.dr_facture, d.dr_paye ,u.eecleunik,u.urnom,dr_asannul,dr_asbagage,dr_ascomp,dr_bdc,h.henumpiece,dr_nbjour,dr_visa,dr_identchild,dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc   FROM dossier d,user u,historique2 h WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0  AND d.dr_cleunik=? AND h.drcleunik= d.dr_cleunik ";
        try {
            PreparedStatement pstmt = null;
 /*           if (dossierCleunik != 0) {
                if (lock) {
                    lock2 = lockDossier(new Integer(dossierCleunik), new Integer(currentUser.getCle2()));
                    if (lock2) {
                        ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
                        int errorcode = -100;
                        sqe.setErrorcode(errorcode);
                        throw sqe;
                    }
                }
//                if(lock){
//                    Transaction.begin(tmpool.getConuser());
//                    String requete="SELECT d.dr_cleunik FROM dossier d WHERE d.dr_cleunik=? FOR UPDATE";
//                    pstmt=tmpool.getConuser().prepareStatement(requete);
//                    pstmt.setInt(1,dossierCleunik);
//                    pstmt.execute();
//                }
//
            }*/
            pstmt = tmpool.getConuser().prepareStatement(typerequete);
            pstmt.setInt(1, currentUser.getUreecleunik());
            if (dossierCleunik != 0)
                pstmt.setInt(2, dossierCleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                dossier = new Dossier_T();
                dossier.setDrcleunik(result.getInt(1));
                dossier.setDrnumdos(result.getString(2));
                dossier.setDr_type_booking(result.getInt(3));
                dossier.setDrreference(result.getString(4));
                dossier.setDrstatus(result.getInt(5));
                dossier.setDr_datetimecrea(new Date(result.getString(6)));
                dossier.setCscleunik(result.getInt(7));
                dossier.setUrcleunikcrea(result.getInt(8));
                dossier.setDrprix(result.getFloat(9));
                dossier.setDrtaxe(result.getFloat(10));
                dossier.setDrtva(result.getFloat(11));
                dossier.setDrtotalprix(result.getFloat(12));
                dossier.setCscleunik_fact(result.getInt(13));
                dossier.setDr_date_depart(new Date(result.getString(14)));
                dossier.setDr_date_retour(new Date(result.getString(15)));
                dossier.setDr_nbrenuit(result.getInt(16));
                
                dossier.setDr_nbj_av_eche(result.getInt(17));
                dossier.setDr_date_echeance(new Date(result.getString(19)));
                
            
                
                dossier.setDr_facture(result.getInt(20));
                dossier.setDr_Paye(result.getDouble(21));
                dossier.setDr_asannul(result.getInt(24));
                dossier.setDr_asbagage(result.getInt(25));
                dossier.setDr_ascomp(result.getInt(26));
                dossier.setDr_bcd(result.getInt(27));
                dossier.setNumfact(result.getObject(28).toString());
                dossier.setNumnc(result.getObject(28).toString());
                dossier.setDr_nbrjour(result.getInt(29));
                dossier.setDr_visa(result.getInt(30));
                dossier.setDr_identchild(result.getInt(31));

//dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc
                dossier.setDr_voy(result.getInt(32));
                dossier.setDr_annul_lim(result.getInt(33));
                dossier.setDr_pass_ob(result.getInt(34));
                dossier.setDr_ident(result.getInt(35));
                dossier.setDr_cee(result.getInt(36));
                dossier.setDr_vacc(result.getInt(37));

                checkDate(dossier, tmpool);
                if (!lock)
                    dossier.setReadMode(true);
                Memo_T memo = selectMemo(dossier, tmpool);
                if (memo != null)
                    dossier.setMemo(memo);
                chargeDossierUser(dossier, tmpool.getConuser(), pstmt, currentUser);
                chargeDossierUserModif(dossier, tmpool.getConuser(), pstmt, currentUser);
                chargePayement(dossier, pstmt, tmpool.getConuser());
                chargeFraisDossier(dossier, tmpool.getConuser(), pstmt, currentUser);
                ArrayList tmpPassager = chargePassager(dossier, tmpool.getConuser(), tmpool.getLmcleunik());
                if (tmpPassager.size() != 0)
                    dossier.setPassager(tmpPassager);
                dossier.setClientContractuel(getDossierClientLocal(dossier.getCscleunik(), currentUser));
                dossier.setClientFacturable(getDossierClientLocal(dossier.getCscleunik_fact(), currentUser));
//  Avion_gestion2.chargeAvionticket(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
                Produit_gestion.chargeProduit(currentUser, serveur, dossier, tmpool.getConuser(), 0, pstmt);
//  Brochure_Gestion.chargeBrochure(serveur,currentUser,dossier,tmpool.getConuser(),0,pstmt);
                returnvalue.add(dossier);
            }
        } catch (SQLException e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(e);
            int errorcode = 0;
            if (e.getErrorCode() == 1205) {
                errorcode = -100;
            } else
                errorcode = e.getErrorCode();
            sqe.setErrorcode(errorcode);
            throw sqe;
        }
        return returnvalue;
    }

    public ArrayList chargeDossier(Loginusers_T currentUser, int dossierCleunik, boolean lock) throws RemoteException, ServeurSqlFailure {

        Poolconnection tmpool = serveur.getConnectionAndCheck(currentUser.getUrcleunik(), true);
        Dossier_T dossier;
        String typerequete;
        System.out.println("[WAAAAAAAAAAAAAAAAAAAAAAZZZZZAAAAAAAAAAAAAAAAAA**************]je rentre dans chargedossier");
        ArrayList returnvalue = new ArrayList();
        boolean lock2;

        if (dossierCleunik == 0)
            typerequete = "SELECT d.dr_cleunik,d.dr_numdos,d.dr_typbooking,d.dr_reference,d.dr_status,d.dr_datetimecrea,d.cscleunik,d.urcleunik,d.dr_prix,d.dr_taxe,d.dr_tva,d.dr_totalprix,d.cscleunik_fact,d.dr_date_depart,d.dr_date_retour,d.dr_nbrenuit,d.dr_nbj_av_eche,d.dr_type_payement,d.dr_date_echeance, d.dr_facture, d.dr_paye , u.eecleunik,u.urnom,dr_asannul,dr_asbagage,dr_ascomp,dr_bdc,dr_nbjour,dr_visa,dr_identchild,dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc  FROM dossier d,user u WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0 ORDER BY d.dr_numdos";
        else
            typerequete = "SELECT d.dr_cleunik,d.dr_numdos,d.dr_typbooking,d.dr_reference,d.dr_status,d.dr_datetimecrea,d.cscleunik,d.urcleunik,d.dr_prix,d.dr_taxe,d.dr_tva,d.dr_totalprix,d.cscleunik_fact,d.dr_date_depart,d.dr_date_retour,d.dr_nbrenuit,d.dr_nbj_av_eche,d.dr_type_payement,d.dr_date_echeance, d.dr_facture, d.dr_paye ,u.eecleunik,u.urnom,dr_asannul,dr_asbagage,dr_ascomp,dr_bdc,h.henumpiece,dr_nbjour,dr_visa,dr_identchild,dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc   FROM dossier d,user u,historique2 h WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0  AND d.dr_cleunik=? AND h.hetypeligne='B' AND h.drcleunik= d.dr_cleunik AND h.hedossiercourant='O' ";
        try {
            PreparedStatement pstmt = null;
            if (dossierCleunik != 0) {
                if (lock) {
                    lock2 = lockDossier(new Integer(dossierCleunik), new Integer(currentUser.getCle2()));
                    if (lock2) {
                        ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
                        int errorcode = -100;
                        sqe.setErrorcode(errorcode);
                        throw sqe;
                    }
                }
//                if(lock){
//                    Transaction.begin(tmpool.getConuser());
//                    String requete="SELECT d.dr_cleunik FROM dossier d WHERE d.dr_cleunik=? FOR UPDATE";
//                    pstmt=tmpool.getConuser().prepareStatement(requete);
//                    pstmt.setInt(1,dossierCleunik);
//                    pstmt.execute();
//                }
//
            }
            pstmt = tmpool.getConuser().prepareStatement(typerequete);
            pstmt.setInt(1, currentUser.getUreecleunik());
            if (dossierCleunik != 0)
                pstmt.setInt(2, dossierCleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                dossier = new Dossier_T();
                dossier.setDrcleunik(result.getInt(1));
                dossier.setDrnumdos(result.getString(2));
                dossier.setDr_type_booking(result.getInt(3));
                dossier.setDrreference(result.getString(4));
                dossier.setDrstatus(result.getInt(5));
                dossier.setDr_datetimecrea(new Date(result.getString(6)));
                dossier.setCscleunik(result.getInt(7));
                dossier.setUrcleunikcrea(result.getInt(8));
                dossier.setDrprix(result.getFloat(9));
                dossier.setDrtaxe(result.getFloat(10));
                dossier.setDrtva(result.getFloat(11));
                dossier.setDrtotalprix(result.getFloat(12));
                dossier.setCscleunik_fact(result.getInt(13));
                dossier.setDr_date_depart(new Date(result.getString(14)));
                dossier.setDr_date_retour(new Date(result.getString(15)));
                dossier.setDr_nbrenuit(result.getInt(16));
                
                dossier.setDr_nbj_av_eche(result.getInt(17));
                dossier.setDr_date_echeance(new Date(result.getString(19)));
                
            
                
                dossier.setDr_facture(result.getInt(20));
                dossier.setDr_Paye(result.getDouble(21));
                dossier.setDr_asannul(result.getInt(24));
                dossier.setDr_asbagage(result.getInt(25));
                dossier.setDr_ascomp(result.getInt(26));
                dossier.setDr_bcd(result.getInt(27));
                dossier.setNumfact(result.getObject(28).toString());
                dossier.setDr_nbrjour(result.getInt(29));
                dossier.setDr_visa(result.getInt(30));
                dossier.setDr_identchild(result.getInt(31));

//dr_voy,dr_annul_lim,dr_pass_ob,dr_ident,dr_cee,dr_vacc
                dossier.setDr_voy(result.getInt(32));
                dossier.setDr_annul_lim(result.getInt(33));
                dossier.setDr_pass_ob(result.getInt(34));
                dossier.setDr_ident(result.getInt(35));
                dossier.setDr_cee(result.getInt(36));
                dossier.setDr_vacc(result.getInt(37));

                checkDate(dossier, tmpool);
                if (!lock)
                    dossier.setReadMode(true);
                Memo_T memo = selectMemo(dossier, tmpool);
                if (memo != null)
                    dossier.setMemo(memo);
                chargeDossierUser(dossier, tmpool.getConuser(), pstmt, currentUser);
                chargeDossierUserModif(dossier, tmpool.getConuser(), pstmt, currentUser);
                chargePayement(dossier, pstmt, tmpool.getConuser());
                chargeFraisDossier(dossier, tmpool.getConuser(), pstmt, currentUser);
                ArrayList tmpPassager = chargePassager(dossier, tmpool.getConuser(), tmpool.getLmcleunik());
                if (tmpPassager.size() != 0)
                    dossier.setPassager(tmpPassager);
                dossier.setClientContractuel(getDossierClientLocal(dossier.getCscleunik(), currentUser));
                dossier.setClientFacturable(getDossierClientLocal(dossier.getCscleunik_fact(), currentUser));
//  Avion_gestion2.chargeAvionticket(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
                Produit_gestion.chargeProduit(currentUser, serveur, dossier, tmpool.getConuser(), 0, pstmt);
//  Brochure_Gestion.chargeBrochure(serveur,currentUser,dossier,tmpool.getConuser(),0,pstmt);
                returnvalue.add(dossier);
            }
        } catch (SQLException e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(e);
            int errorcode = 0;
            if (e.getErrorCode() == 1205) {
                errorcode = -100;
            } else
                errorcode = e.getErrorCode();
            sqe.setErrorcode(errorcode);
            throw sqe;
        }
        return returnvalue;
    }

    private void chargeDossierUser(Dossier_T dossier, Connection con, PreparedStatement pstmt, Loginusers_T user2) throws SQLException {
        String requete = "SELECT u.urnom,u.urprenom,u.droits,u.uradresse,u.urmailbur,u.urmailprive,u.urtelephonebd,u.urtelephoneprive,u.eecleunik,u.urgsm,c.cxcode,t.txtraduction,e.eenom  FROM user u,codepostaux  c,traductioncodpostaux t,entite e WHERE u.urcleunik=? AND u.cxcleunik=c.cxcleunik AND u.cxcleunik=t.cxcleunik AND t.lmcleunik=? AND u.eecleunik=e.eecleunik";
        System.out.println("dossier.getUrcleunikcrea()"+dossier.getUrcleunikcrea());
        System.out.println("user2.getUrlmcleunik()"+user2.getUrlmcleunik());
        pstmt = con.prepareStatement(requete);
        pstmt.setInt(1, dossier.getUrcleunikcrea());
        pstmt.setInt(2, user2.getUrlmcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            User_T user = new User_T(dossier.getUrcleunikcrea());
            user.setUrnom(result.getString(1));
            user.setUrprenom(result.getString(2));
            user.setUrdroit(result.getInt(3));
            user.setUradresse(result.getString(4));
            user.setUrmailbur(result.getString(5));
            user.setUrmailprive(result.getString(6));
            user.setUrtelephonebd(result.getString(7));
            user.setUrtelephonepriv(result.getString(8));
            user.setEelcleunik(result.getInt(9));
            user.setUrgsm(result.getString(10));
            user.setCxcode(result.getString(11));
            user.setUrlocalite(result.getString(12));
            user.setEntitenom(result.getString(13));
            dossier.setCreator(user);
        }
    }

    private void chargeDossierUserModif(Dossier_T dossier, Connection con, PreparedStatement pstmt, Loginusers_T user2) throws SQLException {
        String requete = "SELECT  m.urcleunik,u.urnom,u.urprenom,u.droits,u.uradresse,u.urmailbur,u.urmailprive,u.urtelephonebd,u.urtelephoneprive,u.eecleunik,u.urgsm,c.cxcode,t.txtraduction,e.eenom  FROM user u,codepostaux  c,traductioncodpostaux t,entite e,dossier_modification m WHERE m.urcleunik=u.urcleunik AND m.dr_cleunik=? AND u.cxcleunik=c.cxcleunik AND u.cxcleunik=t.cxcleunik AND t.lmcleunik=? AND u.eecleunik=e.eecleunik";
        pstmt = con.prepareStatement(requete);
        pstmt.setLong(1, dossier.getDrcleunik());
        pstmt.setInt(2, user2.getUrlmcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            User_T user = new User_T(result.getInt(1));
            user.setUrnom(result.getString(2));
            user.setUrprenom(result.getString(3));
            user.setUrdroit(result.getInt(4));
            user.setUradresse(result.getString(5));
            user.setUrmailbur(result.getString(6));
            user.setUrmailprive(result.getString(7));
            user.setUrtelephonebd(result.getString(8));
            user.setUrtelephonepriv(result.getString(9));
            user.setEelcleunik(result.getInt(10));
            user.setUrgsm(result.getString(11));
            user.setCxcode(result.getString(12));
            user.setUrlocalite(result.getString(13));
            user.setEntitenom(result.getString(14));
            dossier.addUser(user);
        }
    }

    public CompressArray chargeDossierArray(Loginusers_T currentUser, int dossierCleunik) throws RemoteException {
/*  Poolconnection tmpool=serveur.getConnectionAndCheck(currentUser.getUrcleunik(),true);
Dossier_T dossier;
String typerequete;
System.out.println("[WAAAAAAAAAAAAAAAAAAAAAAZZZZZAAAAAAAAAAAAAAAAAA**************]je rentre dans chargedossier");
//ArrayList returnvalue=new ArrayList();
CompressArray myArray=null;
try{
PreparedStatement pstmt=tmpool.getConuser().prepareStatement("SELECT d.dr_cleunik,d.dr_numdos,d.dr_date_depart,d.po,d.destination,d.hotel,d.dr_status,d.client,d.fournisseur,d.dr_totalprix, d.dr_facture, d.dr_paye FROM dossier d,user u WHERE  d.urcleunik=u.urcleunik AND  u.eecleunik=? AND dr_annull=0 ORDER BY d.dr_numdos"  );
pstmt.setInt(1,currentUser.getUreecleunik());
ResultSet result=pstmt.executeQuery();
myArray=new CompressArray();
myArray.Compress_from_resulset(result);
/* while(result.next()){
dossier=new Dossier_T();
dossier.setDrcleunik(result.getInt(1));
dossier.setDrnumdos(result.getString(2));
dossier.setDr_type_booking(result.getString(3));
dossier.setDrreference(result.getString(4));
dossier.setDrstatus(result.getInt(5));
dossier.setDr_datetimecrea(new Date(result.getString(6)));
dossier.setCscleunik(result.getInt(7));
dossier.setUrcleunikcrea(result.getInt(8));
dossier.setDrprix(result.getFloat(9));
dossier.setDrtaxe(result.getFloat(10));
dossier.setDrtva(result.getFloat(11));
dossier.setDrtotalprix(result.getFloat(12));
dossier.setCscleunik_fact(result.getInt(13));
dossier.setDr_date_depart(new Date(result.getString(14)));
dossier.setDr_date_retour(new Date(result.getString(15)));
dossier.setDr_nbrenuit(result.getInt(16));
dossier.setUrnom(result.getString(18));
chargePayement(dossier,pstmt,tmpool.getConuser());
ArrayList tmpPassager=chargePassager(dossier,tmpool.getConuser(),tmpool.getLmcleunik());
if(tmpPassager.size()!=0)
dossier.setPassager(tmpPassager);
dossier.setClientContractuel(getDossierClientLocal(dossier.getCscleunik(),currentUser));
dossier.setClientFacturable(getDossierClientLocal(dossier.getCscleunik_fact(),currentUser));
Avion_gestion2.chargeAvionticket(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
Produit_gestion.chargeProduit(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
Brochure_Gestion.chargeBrochure(serveur,currentUser,dossier,tmpool.getConuser(),0,pstmt);
returnvalue.add(dossier);
}
}catch(SQLException se){
se.printStackTrace();
Transaction.rollback(tmpool.getConuser());
}
return myArray;
}*/
/*public ArrayList chargeDossierRecherche(Loginusers_T currentUser, int cleRecherche) throws RemoteException{
Poolconnection tmpool=serveur.getConnectionAndCheck(currentUser.getUrcleunik(),true);
Dossier_T dossier;
String typerequete;
System.out.println("[WAAAAAAAAAAAAAAAAAAAAAAZZZZZAAAAAAAAAAAAAAAAAA**************]je rentre dans chargedossier");
ArrayList returnvalue=new ArrayList();
if(dossierCleunik==0)
typerequete=CHARGE_DOSSIER;
else
typerequete=CHARGE_UN_DOSSIER;
try{
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(typerequete);
pstmt.setInt(1,currentUser.getUreecleunik());
if(typerequete==CHARGE_UN_DOSSIER)
pstmt.setInt(2,dossierCleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
dossier=new Dossier_T();
dossier.setDrcleunik(result.getInt(1));
dossier.setDrnumdos(result.getString(2));
dossier.setDr_type_booking(result.getString(3));
dossier.setDrreference(result.getString(4));
dossier.setDrstatus(result.getInt(5));
dossier.setDr_datetimecrea(new Date(result.getString(6)));
dossier.setCscleunik(result.getInt(7));
dossier.setUrcleunikrea(result.getInt(8));
dossier.setDrprix(result.getFloat(9));
dossier.setDrtaxe(result.getFloat(10));
dossier.setDrtva(result.getFloat(11));
dossier.setDrtotalprix(result.getFloat(12));
dossier.setCscleunik_fact(result.getInt(13));
dossier.setDr_date_depart(new Date(result.getString(14)));
dossier.setDr_date_retour(new Date(result.getString(15)));
dossier.setDr_nbrenuit(result.getInt(16));
dossier.setUrnom(result.getString(18));
chargePayement(dossier,pstmt,tmpool.getConuser());
ArrayList tmpPassager=chargePassager(dossier,tmpool.getConuser(),tmpool.getLmcleunik());
if(tmpPassager.size()!=0)
dossier.setPassager(tmpPassager);
dossier.setClientContractuel(getDossierClientLocal(dossier.getCscleunik(),currentUser));
dossier.setClientFacturable(getDossierClientLocal(dossier.getCscleunik_fact(),currentUser));
Avion_gestion2.chargeAvionticket(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
Produit_gestion.chargeProduit(currentUser,serveur,dossier,tmpool.getConuser(),0,pstmt);
Brochure_Gestion.chargeBrochure(serveur,currentUser,dossier,tmpool.getConuser(),0,pstmt);
returnvalue.add(dossier);
}
}catch(SQLException se){
se.printStackTrace();
Transaction.rollback(tmpool.getConuser());
}
return returnvalue;*/
        return null;
    }

    private void chargePayement(Dossier_T dossier, PreparedStatement pstmt, Connection con) throws SQLException {
        pstmt = con.prepareStatement("SELECT hecleunik,hedatecreation,hevaleur,hetypepayement,urcleunik,henumpiece,hedatemouv,helibelle  FROM historique2 WHERE drcleunik=?  AND hedossiercourant='O' AND (hetypeligne='BP' OR hetypeligne='OCCC') ORDER BY hedatecreation;");
        pstmt.setInt(1, dossier.getDrcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Date tmpdate = new Date(result.getString(7));
            if (tmpdate != null && !tmpdate.isOpen() && !tmpdate.isUnknow())
                ;
            else
                tmpdate = new Date(result.getString(2));
            Payement_T pay = new Payement_T(result.getLong(1), tmpdate, result.getDouble(3), result.getInt(4));
            pay.setCreator(result.getInt(5));
            pay.setHenumpiece(new Long(result.getLong(6)).toString());
            pay.setLibelle(result.getString(8));
            dossier.addPayement2(pay);
        }
        pstmt = con.prepareStatement("SELECT hecleunik,hedatecreation,hevaleur,hetypepayement,urcleunik,henumpiece,hedatemouv,helibelle  FROM historiquerecup WHERE drcleunik=?  AND hedossiercourant='O' AND (hetypeligne='BP' OR hetypeligne='OCCC') ORDER BY hedatecreation;");
        pstmt.setInt(1, dossier.getDrcleunik());
        result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Date tmpdate = new Date(result.getString(7));
            if (tmpdate != null && !tmpdate.isOpen() && !tmpdate.isUnknow())
                ;
            else
                tmpdate = new Date(result.getString(2));
            Payement_T pay = new Payement_T(result.getLong(1), tmpdate, result.getDouble(3), result.getInt(4));
            pay.setCreator(result.getInt(5));
            pay.setHenumpiece(new Long(result.getLong(6)).toString());
            pay.setLibelle(result.getString(8));

            dossier.addPayement2(pay);
        }
    }
/* public Dossier_T chargeDossierLocal(ResultSet result,Connection con,lmcleunik)
{
ArrayList dossiers=new ArrayList();
try{
result.beforeFirst();
while(result.next())
{
Dossier_T dossier=new Dossier_T();
dossier.setDrcleunik(result.getInt(1));
dossier.setDrnumdos(result.getString(2));
dossier.setDr_type_booking(result.getString(3));
dossier.setDrreference(result.getString(4));
dossier.setDrstatus(result.getInt(5));
dossier.setDr_datetimecrea(new Date(result.getString(6)));
dossier.setCscleunik(result.getInt(7));
dossier.setUrcleunikcrea(result.getInt(8));
dossier.setDrprix(result.getFloat(9));
dossier.setDrtaxe(result.getFloat(10));
dossier.setDrtva(result.getFloat(11));
dossier.setDrtotalprix(result.getFloat(12));
dossier.setCscleunik_fact(result.getInt(13));
dossier.setDr_date_depart(new Date(result.getString(14)));
dossier.setDr_date_retour(new Date(result.getString(15)));
dossier.setDr_nbrenuit(result.getInt(16));
ArrayList tmpPassager=chargePassager(dossier,con,lmcleunik);
if(tmpPassager.size()!=0)
dossier.setPassager(tmpPassager);
dossiers.add(dossier);
}
}catch(SQLException se){
se.printStackTrace();
Transaction.rollback(con);
}
return dossier;
}*/
    public void annulDossier(int urCleunik, int dossiercleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urCleunik, true);
        try {
            Transaction.begin(tmpool.getConuser());
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(this.DELETE_DOSSIER);
            pstmt.setInt(1, dossiercleunik);
            pstmt.execute();
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException se) {
            renvexception(se, "[************DOSSIER:MODIFY stacktrace: ", tmpool.getConuser());
        }
    }


    public ArrayList chargePassager(Dossier_T dossier, Connection con, int lmcleunik) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("SELECT i.dr_cleunik,p.pr_cleunik,p.pr_nom,p.pr_prénom,p.pr_tel,p.pr_fax,p.pr_gsm,p.pr_email,p.lecleunik,p.pr_datenaissance,p.pr_nat,p.tscleunik,p.cxcleunik,p.pyscleunik,p.pr_codemailing,p.pr_bookingclass,p.pr_windowseat,p.pr_smoking,p.pr_adresse,t.tsintitule,pr_princ   FROM intermediairepassager i,passager p LEFT JOIN traductiontitrepers t ON  (p.tscleunik=t.tscleunik AND t.lmcleunik=?)  WHERE i.pr_cleunik=p.pr_cleunik   AND i.dr_cleunik=?");
        pstmt.setInt(1, lmcleunik);
        pstmt.setInt(2, dossier.getDrcleunik());
// pstmt.setLong(3,dossier.getClientContractuel().getCscleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        ArrayList tmpPassager;
        tmpPassager = new ArrayList();
        while (result.next()) {
            Passager_T tmpPass = new Passager_T();
            tmpPass.setDr_cleunik(result.getInt(1));
            tmpPass.setPr_cleunik(result.getInt(2));
            tmpPass.setPr_nom(result.getString(3));
            tmpPass.setPr_prénom(result.getString(4));
            tmpPass.setPr_tel(result.getString(5));
            tmpPass.setPr_fax(result.getString(6));
            tmpPass.setPr_gsm(result.getString(7));
            tmpPass.setPr_email(result.getString(8));
            tmpPass.setLecleunik(result.getInt(9));
            tmpPass.setPr_datenaissance(new Date(result.getString(10)));
            tmpPass.setPr_nat(result.getInt(11));
            tmpPass.setTscleunik(result.getInt(12));
            tmpPass.setCscleunik(result.getInt(13));
            tmpPass.setPyscleunik(result.getInt(14));
            tmpPass.setPr_code_mailing(result.getString(15));
            tmpPass.setPr_bookingclass(result.getString(16));
            tmpPass.setPr_windowseat(result.getInt(17));
            tmpPass.setPr_smoking(result.getInt(18));
            tmpPass.setPr_adrese(result.getString(19));
            tmpPass.setTitrepassager(result.getString(20));
            tmpPass.setMainPassager(result.getInt(21));
            pstmt = con.prepareStatement(CHARGE_PASSAGER_CODE);
            pstmt.setInt(1, tmpPass.getCscleunik());
            ResultSet result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setCodenom(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_LANGUE);
            pstmt.setInt(1, tmpPass.getLecleunik());
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setLanguePassager(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
            pstmt.setInt(1, tmpPass.getPyscleunik());
            pstmt.setInt(2, lmcleunik);
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setPaysnom(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
            pstmt.setInt(1, tmpPass.getPyscleunik());
            pstmt.setInt(2, lmcleunik);
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setNatnom(result2.getString(1));
            }
// tmpPass.setLanguePassager(result.getString(20));
// tmpPass.setTitrepassager(result.getString(21));
// tmpPass.setCodenom(result.getString(22));
// tmpPass.setPaysnom(result.getString(23));
// tmpPass.setNatnom(result.getString(24));
            tmpPassager.add(tmpPass);
        }
        return tmpPassager;
    }

    public ArrayList chargeRemotePassager(Loginusers_T currentUser, int lmcleunik, int cli_cleunik, int passagers, boolean askForClient) throws RemoteException {
        Poolconnection tmpool = serveur.getConnectionAndCheck(currentUser.getUrcleunik(), true);
        Connection con = tmpool.getConuser();
        String requete = null;
        int numdossier = 0;
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement("SELECT dr_cleunik FROM dossier WHERE cscleunik=? ORDER BY dr_cleunik");
            pstmt.setInt(1, cli_cleunik);
            ResultSet result = pstmt.executeQuery();
            result.last();
            int longueur = result.getRow();
            if (longueur != 0) {
                result.last();
                numdossier = result.getInt(1);

                if (!askForClient) {
                    requete = "SELECT DISTINCT i.pr_cleunik,p.pr_nom,i.dr_cleunik,p.pr_cleunik,p.pr_prénom,p.pr_tel,p.pr_fax,p.pr_gsm,p.pr_email,p.lecleunik,p.pr_datenaissance,p.pr_nat,p.tscleunik,p.cxcleunik,p.pyscleunik,p.pr_codemailing,p.pr_bookingclass,p.pr_windowseat,p.pr_smoking,p.pr_adresse,t.tsintitule  FROM intermediairepassager i,passager p,traductiontitrepers t WHERE i.pr_cleunik=p.pr_cleunik AND p.tscleunik=t.tscleunik AND t.lmcleunik=? AND i.dr_cleunik=? ORDER BY p.pr_nom";
                    pstmt = tmpool.getConuser().prepareStatement(requete);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, numdossier);
                } else {
                    requete = "SELECT DISTINCT i.pr_cleunik,p.pr_nom,i.dr_cleunik,p.pr_cleunik,p.pr_prénom,p.pr_tel,p.pr_fax,p.pr_gsm,p.pr_email,p.lecleunik,p.pr_datenaissance,p.pr_nat,p.tscleunik,p.cxcleunik,p.pyscleunik,p.pr_codemailing,p.pr_bookingclass,p.pr_windowseat,p.pr_smoking,p.pr_adresse,t.tsintitule  FROM intermediairepassager i,passager p,traductiontitrepers t WHERE i.pr_cleunik=p.pr_cleunik AND p.tscleunik=t.tscleunik AND t.lmcleunik=? AND i.dr_cleunik=? AND i.cscleunik!=? ORDER BY p.pr_nom";
                    pstmt = tmpool.getConuser().prepareStatement(requete);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, numdossier);
                    pstmt.setLong(3, cli_cleunik);
                }
                result = pstmt.executeQuery();
                result.beforeFirst();
                ArrayList tmpPassager;
                tmpPassager = new ArrayList();
                while (result.next()) {
                    Passager_T tmpPass = new Passager_T();
                    tmpPass.setDr_cleunik(result.getInt(3));
                    tmpPass.setPr_cleunik(result.getInt(4));
                    tmpPass.setPr_nom(result.getString(2));
                    tmpPass.setPr_prénom(result.getString(5));
                    tmpPass.setPr_tel(result.getString(6));
                    tmpPass.setPr_fax(result.getString(7));
                    tmpPass.setPr_gsm(result.getString(8));
                    tmpPass.setPr_email(result.getString(9));
                    tmpPass.setLecleunik(result.getInt(10));
                    tmpPass.setPr_datenaissance(new Date(result.getString(11)));
                    tmpPass.setPr_nat(result.getInt(12));
                    tmpPass.setTscleunik(result.getInt(13));
                    tmpPass.setCscleunik(result.getInt(14));
                    tmpPass.setPyscleunik(result.getInt(15));
                    tmpPass.setPr_code_mailing(result.getString(16));
                    tmpPass.setPr_bookingclass(result.getString(17));
                    tmpPass.setPr_windowseat(result.getInt(18));
                    tmpPass.setPr_smoking(result.getInt(19));
                    tmpPass.setPr_adrese(result.getString(20));
                    tmpPass.setTitrepassager(result.getString(21));
                    tmpPass.alreadyInBD = true;
                    tmpPass.setNewReccord(true);
                    pstmt = con.prepareStatement(CHARGE_PASSAGER_CODE);
                    pstmt.setInt(1, tmpPass.getCscleunik());
                    ResultSet result2 = pstmt.executeQuery();
                    result2.beforeFirst();
                    while (result2.next()) {
                        tmpPass.setCodenom(result2.getString(1));
                    }
                    pstmt = con.prepareStatement(CHARGE_PASSAGER_LANGUE);
                    pstmt.setInt(1, tmpPass.getLecleunik());
                    result2 = pstmt.executeQuery();
                    result2.beforeFirst();
                    while (result2.next()) {
                        tmpPass.setLanguePassager(result2.getString(1));
                    }
                    pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
                    pstmt.setInt(1, tmpPass.getPyscleunik());
                    pstmt.setInt(2, lmcleunik);
                    result2 = pstmt.executeQuery();
                    result2.beforeFirst();
                    while (result2.next()) {
                        tmpPass.setPaysnom(result2.getString(1));
                    }
                    pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
                    pstmt.setInt(1, tmpPass.getPyscleunik());
                    pstmt.setInt(2, lmcleunik);
                    result2 = pstmt.executeQuery();
                    result2.beforeFirst();
                    while (result2.next()) {
                        tmpPass.setNatnom(result2.getString(1));
                    }
// tmpPass.setLanguePassager(result.getString(20));
// tmpPass.setTitrepassager(result.getString(21));
// tmpPass.setCodenom(result.getString(22));
// tmpPass.setPaysnom(result.getString(23));
// tmpPass.setNatnom(result.getString(24));
                    tmpPassager.add(tmpPass);
                }
                return tmpPassager;
            } else
                return null;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
/*public void modifyAvionTicket(int urCleunik,Dossier_T dossier,Poolconnection tmpool2)throws RemoteException,SQLException{
if(dossier.getAvionTicket()!=null)
{
ArrayList avionTicket=dossier.getAvionTicket();
Poolconnection tmpool=serveur.getConnectionAndCheck(urCleunik,true);
for(int i=0;i<avionTicket.size();i++)
{
ticket= (Avion_ticket_T)avionTicket.get(i);
//Transaction.begin(tmpool.getConuser());
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(this.INSERT_TICKET);
pstmt.setString(1,ticket.getAt_pnr());
pstmt.setInt(2,ticket.getFrgtcleunik());
pstmt.setInt(3,ticket.getAt_type_billet());
pstmt.setInt(4,ticket.getAt_vendu());
pstmt.setString(5,ticket.getAt_num_billet());
pstmt.setInt(6,ticket.getAt_conjonction());
pstmt.setString(7,ticket.getAt_date_emission().toString());
pstmt.setString(8,ticket.getAt_date_depart().toString());
pstmt.setInt(9,ticket.getAt_annul_type());
pstmt.setString(10,ticket.getAt_annul_date().toString());
pstmt.setFloat(11,ticket.getAt_annul_tot());
pstmt.setString(12,ticket.getAt_numrefund());
pstmt.setString(13,ticket.getAt_cloture_date().toString());
pstmt.setFloat(14,ticket.getDrcleunik());
pstmt.setInt(15,ticket.getAt_mode_paiement());
pstmt.setString(16,ticket.getAt_num_cccf());
pstmt.setFloat(17,ticket.getAt_val_facial());
pstmt.setFloat(18,ticket.getAt_val_vente());
pstmt.setFloat(19,ticket.getAt_val_nette());
pstmt.setFloat(20,ticket.getAt_tax_compagnie());
pstmt.setFloat(21,ticket.getAt_tax_locale());
pstmt.setFloat(22,ticket.getAt_tax_destination());
pstmt.setFloat(23,ticket.getAt_fullfare());
pstmt.setInt(24,ticket.getCoe_cleunik());
pstmt.setInt(25,ticket.getAt_etat());
pstmt.setFloat(26,ticket.getAt_val_com());
pstmt.setFloat(27,ticket.getAt_val_remise());
pstmt.setString(28,ticket.getAt_memo());
/*  if(ticket.getSegmentList()!=null)
{
ArrayList segmentArray=ticket.getSegmentList();
for(int j=0;j<segmentArray.size();j++)
{
Avion_segment_T segment=(Avion_segment_T)segmentArray.get(j);
pstmt=tmpool.getConuser().prepareStatement(this.INSERT_SEGMENT);
pstmt.setInt(1,segment.getAs_position());
pstmt.setInt(2,segment.getAs_routing_de());
pstmt.setInt(3,segment.getAs_routing_a());
pstmt.setInt(4,segment.getCoe_cleunik());
pstmt.setString(5,segment.getAs_numero_vol());
pstmt.setString(6,segment.getAs_date_départ().toString());
pstmt.setString(7,segment.getAs_heure_départ().toString());
pstmt.setString(8,segment.getAs_heure_arrive().toString());
pstmt.setInt(9,segment.getAs_farebasis());
pstmt.setInt(10,segment.getAs_classe());
pstmt.setInt(11,segment.getAs_status());
pstmt.setInt(12,segment.getAs_seating());
pstmt.setInt(13,segment.getAs_gmt());
}

}
}
}
} */
/*public ArrayList chargeTicket(int urCleunik,int dossierId)throws RemoteException{
Poolconnection tmpool=serveur.getConnectionAndCheck(urCleunik,true);
PreparedStatement pstmt;
ArrayList segmentList=new ArrayList();
try{
pstmt=tmpool.getConuser().prepareStatement(CHARGE_AVION_TICKET);
pstmt.setInt(1,dossierId);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
ticket=new Avion_ticket_T();
ticket.setAt_cleunik(result.getLong(1));
ticket.setAt_pnr(result.getString(2));
ticket.setFrgtcleunik(result.getInt(3));
ticket.setAt_type_billet(result.getInt(4));
ticket.setAt_vendu(result.getInt(5));
ticket.setAt_num_billet(result.getString(6));
ticket.setAt_conjonction(result.getInt(7));
ticket.setAt_date_emission(new Date(result.getString(8)));
ticket.setAt_date_depart(new Date(result.getString(9)));
ticket.setAt_annul_type(result.getInt(10));
ticket.setAt_annul_date(new Date(result.getString(11)));
ticket.setAt_annul_tot(result.getFloat(12));
ticket.setAt_numrefund(result.getString(13));
ticket.setAt_cloture_date(new Date(result.getString(14)));
ticket.setDrcleunik(result.getInt(15));
ticket.setAt_mode_paiement(result.getInt(16));
ticket.setAt_num_cccf(result.getString(17));
ticket.setAt_val_facial(result.getFloat(18));
ticket.setAt_val_vente(result.getFloat(19));
ticket.setAt_val_nette(result.getFloat(20));
ticket.setAt_tax_compagnie(result.getFloat(20));
ticket.setAt_tax_locale(result.getFloat(21));
ticket.setAt_tax_destination(result.getFloat(22));
ticket.setAt_fullfare(result.getFloat(23));
ticket.setCoe_cleunik(result.getInt(24));
ticket.setAt_etat(result.getInt(25));
ticket.setAt_val_com(result.getFloat(26));
ticket.setAt_val_remise(result.getFloat(27));
ticket.setCompagnie_nom(result.getString(28));
pstmt=tmpool.getConuser().prepareStatement(INSERT_SEGMENT);
ResultSet resultSegment=pstmt.executeQuery();
/*  while(result.next())
{
Avion_segment_T segmentT=new Avion_segment_T();
segmentT.setAs_cleunik(resultSegment.getDouble(1));
segmentT.setAt_cleunik(resultSegment.getDouble(2));
segmentT.setAs_position(resultSegment.getInt(3));
segmentT.setAs_routing_de(resultSegment.getInt(4));
segmentT.setAs_routing_a(resultSegment.getInt(5));
segmentT.setAs_numero_vol(resultSegment.getString(6));
segmentT.setAs_date_départ(new Date(resultSegment.getString(7)));
segmentT.setAs_heure_départ(new Date(resultSegment.getString(8)));
segmentT.setAs_heure_arrive(new Date(resultSegment.getString(9)));
segmentT.setAs_farebasis(resultSegment.getInt(10));
segmentT.setAs_classe(resultSegment.getInt(11));
segmentT.setAs_status(resultSegment.getInt(12));
segmentT.setAs_seating(resultSegment.getInt(13));
segmentT.setAs_gmt(resultSegment.getInt(14));
segmentT.setCoe_cleunik(resultSegment.getInt(15));
segmentT.setAs_routing_de_intitule(resultSegment.getString(16));
segmentT.setAs_routing_a_intitule(resultSegment.getString(17));
segmentT.setAs_compagnie_intitule(resultSegment.getString(18));
ticket.getSegmentList().add(segmentT);
}
segmentList.add(ticket);
}
} catch(SQLException se){
se.printStackTrace();
Transaction.rollback(tmpool.getConuser());
}
return segmentList;
}*/
    private int getId(Connection con) {
        int id = 0;
        try {
            PreparedStatement pstmt = con.prepareStatement("select LAST_INSERT_ID()");
            ResultSet tmpresult = pstmt.executeQuery();
            tmpresult.first();
            id = tmpresult.getInt(1);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "numero du fournisseur " + id);
            tmpresult.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return id;
    }

    public ArrayList getPoListe(int urcleunik, String po) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        ArrayList retval = new ArrayList();
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String requete = "SELECT b.bro_cleunik,b.bro_po, fg.frgtitrecatalog, f.frnom1, b.bro_valeur FROM brochure b, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND b.frgt_cleunik = fg.frgtcleunik AND b.bro_po LIKE(CONCAT(?,'%'))";
        try {
            Integer compteur = new Integer(0);
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.BRO), compteur);
            requete = "SELECT a.ase_cleunik,a.ase_num_police, fg.frgtitrecatalog, f.frnom1,a.ase_montant  FROM assurance a, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND a.frgt_cleunik = fg.frgtcleunik AND a.ase_num_police LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.AS), compteur);
            requete = "SELECT a.at_cleunik,a.at_pnr, fg.frgtitrecatalog, f.frnom1, a.at_val_vente FROM avion_ticket a, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND a.frgtcleunik = fg.frgtcleunik AND a.at_pnr LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.AV), compteur);
            requete = "SELECT c.vl_cleunik,c.vl_pnr, fg.frgtitrecatalog, f.frnom1, vl_montant FROM car c, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND c.frgtcleunik = fg.frgtcleunik AND c.vl_pnr LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.CAR), compteur);
            requete = "SELECT h.hl_cleunik,h.hl_pnr, fg.frgtitrecatalog, f.frnom1, h.hl_valeur FROM hotel h, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND h.frgtcleunik = fg.frgtcleunik AND h.hl_pnr LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.HO), compteur);
            requete = "SELECT t.trn_cleunik,t.trn_billet, fg.frgtitrecatalog, f.frnom1, t.trn_montant FROM train t, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND t.frgtcleunik = fg.frgtcleunik AND t.trn_billet LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.TR), compteur);
            requete = "SELECT v.vl_cleunik,v.vl_pnr, fg.frgtitrecatalog, f.frnom1, v.vl_montant FROM voiture v, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND v.frgtcleunik = fg.frgtcleunik AND v.vl_pnr LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.VO), compteur);
            requete = "SELECT b.bat_cleunik,b.bat_pnr, fg.frgtitrecatalog, f.frnom1, b.bat_montant FROM bateau b, fournisseur f, fournisseur_grproduit fg WHERE f.frcleunik = fg.frcleunik AND b.frgtcleunik = fg.frgtcleunik AND b.bat_pnr LIKE(CONCAT(?,'%'))";
            compteur = fillPo(retval, requete, po, tmpool.getConuser(), new Integer(produit_T.BA), compteur);
            return retval;
        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

    public Grpdecision_T getGroupDec(long cleunik, int typeprod, int frgtcleunik, int urcleunik) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            return GrpProduitGestion.getGrpDecForProd(cleunik, typeprod, frgtcleunik, tmpool.getConuser(), urcleunik, serveur);
        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

    public ArrayList getPassagerListe(int urcleunik, String name) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {

            PreparedStatement pstmt = tmpool.getConuser().prepareStatement("SELECT p.pr_cleunik,p.pr_nom,p.pr_prénom,p.pr_tel,p.pr_fax,p.pr_gsm,p.pr_email,p.lecleunik,p.pr_datenaissance,p.pr_nat,p.tscleunik,p.cxcleunik,p.pyscleunik,p.pr_codemailing,p.pr_bookingclass,p.pr_windowseat,p.pr_smoking,p.pr_adresse,t.tsintitule  FROM intermediairepassager i,passager p,traductiontitrepers t WHERE i.pr_cleunik=p.pr_cleunik AND p.tscleunik=t.tscleunik AND t.lmcleunik=? AND p.pr_nom LIKE(CONCAT(?,'%'))");
            pstmt.setInt(1, tmpool.getLmcleunik());
            pstmt.setString(2, name);
            ResultSet result = pstmt.executeQuery();
            CompressArray cp = new CompressArray();
            cp.Compress_from_resulset2(result);
            return cp;
        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return null;
    }

    private Integer fillPo(ArrayList array, String requete, String po, Connection con, Integer typeProduit, Integer number) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = con.prepareStatement(requete);
        pstmt.setString(1, po);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            number = new Integer(number.intValue() + 1);
            Object[] tmp = new Object[]{result.getObject(1), result.getObject(2), result.getObject(3), result.getObject(4), result.getObject(5), typeProduit, number};
            array.add(tmp);
        }
        return number;
    }

    public ArrayList chargeDossierRecherche(Loginusers_T currentUser, int cleRecherche, String numdos, String po, String ref1, String ref2, String typeprod, srcastra.astra.sys.classetransfert.utils.Date crea, srcastra.astra.sys.classetransfert.utils.Date depart, int cas, String numfact, String numnc, String numfactfourn, int nonfacture, ArrayList order) throws RemoteException {
        Poolconnection tmpool = serveur.getConnectionAndCheck(currentUser.getUrcleunik(), true);
        ArrayList returnValue = null;
        try {
            DossierRechercheByNumdos recherche = new DossierRechercheByNumdos(serveur);
            return recherche.chargeDossierRecherche(currentUser, cleRecherche, numdos, po, ref1, ref2, typeprod, crea, depart, tmpool, cas, numfact, numnc, numfactfourn, nonfacture, order);
//  InterfaceRecherche recherche=(InterfaceRecherche)requeteRecherche.get(new Integer(cleRecherche));
// returnValue=recherche.chargeDossierRecherche(currentUser,0,numdos,tmpool,cas);
        } catch (SQLException se) {
            se.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
        }
        return returnValue;
    }


    public String NC(long cledossier, int urcleunik, Dossier_T newdossier, Loginusers_T currentuser) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = null;
        try {
            synchronized (serveur.getNumdosSyn()) {
                tmpool = serveur.getConnectionAndCheck(urcleunik, true);
                Transaction.begin(tmpool.getConuser());
                Configuration config = new Configuration(tmpool.getConuser(), null, 0, 0, 0, 0, 0, 0, serveur, tmpool.getUrcle2(), 0);
// config.setDossier(dossier);
                config.setCle2(urcleunik);
                config.setEecleunik(tmpool.getNumeroentite());
                
                System.out.println("****Module général note de crédit");
                
// srcastra.astra.sys.compta.checkcompte.Check.getTransaction(tmpool,serveur.getTransactSyn());
                
                System.out.println("****Appel managechainecomptable clé dossier:"+cledossier);
                
                String numc = new MangageChaineComptable(config, serveur, urcleunik).NC(cledossier);
                
                System.out.println("****Numéro de piece note de crédit renvoyé par managechainecomptable:["+numc+"]");
                
                serveur.renvSoldeRmiObject(urcleunik).insert(urcleunik);
                modifyDossierLocal(urcleunik, newdossier, currentuser, tmpool);
                Transaction.commit(tmpool.getConuser());
                return numc;
            }
        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return "0000";

    }
    
    public String NCService(long cledossier, int urcleunik, Dossier_T newdossier, Loginusers_T currentuser) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = null;
        try {
            synchronized (serveur.getNumdosSyn()) {
                tmpool = serveur.getConnectionAndCheck(urcleunik, true);
                Transaction.begin(tmpool.getConuser());
                Configuration config = new Configuration(tmpool.getConuser(), null, 0, 0, 0, 0, 0, 0, serveur, tmpool.getUrcle2(), 0);
// config.setDossier(dossier);
                config.setCle2(urcleunik);
                config.setEecleunik(tmpool.getNumeroentite());
// srcastra.astra.sys.compta.checkcompte.Check.getTransaction(tmpool,serveur.getTransactSyn());
                String numc = new MangageChaineComptable(config, serveur, urcleunik).NCService(cledossier);
                serveur.renvSoldeRmiObject(urcleunik).insert(urcleunik);
                modifyDossierLocal(urcleunik, newdossier, currentuser, tmpool);
                Transaction.commit(tmpool.getConuser());
                return numc;
            }
        } catch (SQLException sn) {
            sn.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
            String[] message = new String[1];
            message[0] = java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
            ManageServSQLExcption.gestion2(sn, message, tmpool.getConuser());
        }
        return "0000";

    }

    public String setDossierFacture(int urcleunik, long cledossier, long numpiece, double valeurdossier) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        String requete = "UPDATE dossier SET dr_facture=1 WHERE dr_cleunik=?";
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String numdossier = "";

        try {
            synchronized (serveur.getNumdosSyn()) {
                Transaction.begin(tmpool.getConuser());
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
                pstmt.setLong(1, cledossier);
                pstmt.execute();
                numdossier = serveur.renvParamCompta(urcleunik).setHistoriqueCpt(urcleunik, cledossier, numpiece, valeurdossier);
        //Temporairement enleve   //     new srcastra.astra.sys.compta.command.NcCompta(tmpool.getUrcle2(), null).setSolde(tmpool.getConuser(), cledossier);
        //    serveur.renvSoldeRmiObject(urcleunik).insert(urcleunik);
                Transaction.commit(tmpool.getConuser());
            }
            return numdossier;
        } catch (SQLException se) {
            se.printStackTrace();
            Transaction.rollback(tmpool.getConuser());
        }
        return numdossier;
    }

    private void genereTabRechercheClass() {
        if (this.requeteRecherche == null)
            this.requeteRecherche = new Hashtable();
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_REF), new DossierRechercheByNumdos(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_CLICONTRACT_REF), new DossierRechercheByRefClient(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_CLIFACT_REF), new DossierRechercheByRefClientFact(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_PO), new DossierRechercheByPo(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_DATECREA), new DossierRechercheByRefDateCreation(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_DATEDEPART), new DossierRechercheByRefDateDepart(this.serveur));
        this.requeteRecherche.put(new Integer(CHARGE_DOSSIER_BY_TYPEPRODUIT), new DossierRechercheByTypeProduit(this.serveur));
    }

    public ArrayList getIntegrationListe(int urcleunik, String po) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        //String requete = "SELECT tion_cleunik,v_po_pnr,num_billet,passager,date_dep,val_facial,cnj_billet,val_vente,val_net,tax_locale,tax_compag,tax_destin,val_commis,val_remise,num_cccf,num_client FROM ticketintegration WHERE tion_integrated = 0 AND v_po_pnr LIKE(CONCAT(?,'%')) ORDER BY tion_cleunik";
        String requete = "SELECT tion_cleunik,v_po_pnr,num_billet,passager,date_dep,val_facial,cnj_billet,val_vente,val_net,tax_locale,tax_compag,tax_destin,val_commis,val_remise,num_cccf, c.cscleunik, c.csnom,v_fullfare FROM ticketintegration left outer join clients c on num_client=c.csreference WHERE tion_integrated = 0 AND v_po_pnr LIKE(CONCAT(?,'%')) ORDER BY tion_cleunik";
        
        
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array1 = new ArrayList();
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
            pstmt.setString(1, po);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[22]; //c'était 21
                tab[0] = result.getObject(1);  //tion cleunik
                tab[1] = new Boolean(false);
                tab[2] = result.getObject(2); //pnr
                tab[3] = result.getObject(3);   //numbille
                tab[4] = result.getObject(4);  //passager
                tab[5] = result.getObject(5);  //datadep
                tab[6] = result.getObject(6);  //val_fac
                tab[7] = new Integer(0);        //clientcleunik
                tab[8] = result.getString(16)+"|"+result.getString(17);//"";                //clientstring
                
                System.out.println(tab[8]);
                
                tab[9] = new Integer(0);    //grpdeccleunik
                tab[10] = result.getObject(8); //val_vente
                tab[11] = "";               //grpdecstring
                tab[12] = result.getObject(9); //valnet
                tab[13] = result.getObject(10);  //taxloc
                 tab[14] = result.getObject(11); //taxcompag
                
             
                
                
                tab[15] = result.getObject(12);  //taxdest
                tab[16] = result.getObject(13); //commision
                tab[17] = result.getObject(14); //remise
                tab[18] = new Integer(0); //cle fees
                tab[19] = new Double(0); //val fees
             
                
                tab[20] = result.getString(15); //cccf
                
                tab[21]=result.getString(18);
                
               System.out.println(tab[21]);
                
                array1.add(tab);
                int conjonction = Integer.parseInt(result.getString(7));
                if (conjonction > 1) {
                    for (int j = 1; j < conjonction; j++) {
                        result.next();
                    }
                }
            }
            return array1;
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return null;
    }

    public Dossier_T getProductToIntegrate(int urcleunik, String po, Hashtable hash, Loginusers_T user, int facture) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            ArrayList array1 = getTicketToIntegrate(hash, tmpool);
            Dossier_T dossier = checkConjonction(array1, tmpool, hash, user);
            return dossier;
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        }
        return null;
    }

    public void voidTicket(int urcleunik, Hashtable table, Loginusers_T user) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        String checkDossier = "SELECT * FROM dossier WHERE dr_cleunik=-1";
        String insertDossier = "INSERT INTO dossier (dr_cleunik) values(-1) ";
        Poolconnection tmpool = null;
        PreparedStatement pstmt = null;
        boolean sw = false;
        try {

            tmpool = serveur.getConnectionAndCheck(urcleunik, true);
            Transaction.begin(tmpool.getConuser());
            pstmt = tmpool.getConuser().prepareStatement(checkDossier);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                sw = true;
            }
            if (!sw) {
                pstmt.executeQuery(insertDossier);
            }
            ArrayList ticket = getTicketToIntegrate(table, tmpool);
            generateTicketToVoid(ticket, user, table, tmpool, pstmt);
            setTicketIntegrated(table, tmpool, 1);
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        } catch (IOException in) {
            in.printStackTrace();
        }
    }

    private void generateTicketToVoid(ArrayList array, Loginusers_T user, Hashtable hash, Poolconnection tmpool, PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < array.size(); i++) {
            Avion_ticket_T avion = new Avion_ticket_T();
            avion.setTypeDeProduitCleunik(produit_T.AV);
            avion.setTypeDeProduitNom("AV");
            avion.setIsnewreccord(true);
            avion.setLibelleCompta(java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("Titre_Pane_aviation"));
            Object[] tab = (Object[]) array.get(i);
            Object[] param = (Object[]) hash.get(new Long(tab[0].toString()));
            int frgtcleunik = ((Integer) param[2]).intValue();
            int frcleunik = ((Integer) param[4]).intValue();
            double valeurvente = ((Double) param[3]).doubleValue();
            int conjonction = Integer.parseInt(tab[3].toString());
            generateTicket(tab, frgtcleunik, frcleunik, tmpool, avion, param);
            ProduitInfoComplementaire.getInfo(ProduitInfoComplementaire.AV, ProduitInfoComplementaire.AV_FULL, produit_T.AV, user.getLangage(), avion, tmpool.getConuser());
            long tion_cleunik = Long.parseLong(tab[0].toString());
            avion.setAt_val_vente(valeurvente);
            generatePassager(tab, dossier, avion, -i);
            generateConjonction(tab, avion, tmpool, dossier);
            if (conjonction > 1) {
                Hashtable tmphash = new Hashtable();
                for (int j = 1; j < conjonction; j++) {
                    tmphash.put(new Long(tion_cleunik + 1), "");
                }
                ArrayList conjonctionList = getTicketToIntegrate(tmphash, tmpool);
                if (conjonctionList != null) {
                    for (int j = 0; j < conjonctionList.size(); j++) {
                        Object[] tmptab = (Object[]) conjonctionList.get(j);
                        generateConjonction(tmptab, avion, tmpool, dossier);
                    }
                }
            }
/*  ArrayList tmpconj=avion.getConjonctionList();
Conjonction_T lasconjonction=(Conjonction_T)tmpconj.get(tmpconj.size()-1);
ArrayList tmpsegmentA=lasconjonction.getSegmentList();
Segment*/
//  if(prod
// avion.calculMontantTotal();
            avion.setEtatAnnulation(avion.VOID);
            avion.setDrcleunik(-1);
            avion.insertOnlyme(tmpool.getConuser(), -1, pstmt);
        }
    }

    public Hashtable mainIntegration(int urcleunik, String po, Hashtable hash, Loginusers_T user, int facture) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Poolconnection tmpool = null;
        Hashtable retval = null;
        int idDos = 0;
        String numdos = "";
        try {
            synchronized (serveur.getNumdosSyn()) {
                tmpool = serveur.getConnectionAndCheck(urcleunik, true);
                Transaction.begin(tmpool.getConuser());
                ArrayList array1 = getTicketToIntegrate(hash, tmpool);
                
                for (int i=0;i<array1.size();i++)
                    System.out.println(array1.get(i).toString());
                   
                
                
                Dossier_T dossier = checkConjonction(array1, tmpool, hash, user);
                if (dossier != null) {
                    retval = insertDossierLocale(urcleunik, dossier, tmpool);
                    setTicketIntegrated(hash, tmpool, 1);
                    if (retval != null) {
                        idDos = ((Integer) retval.get("id")).intValue();
                        numdos = retval.get("numdos").toString();
                        if (facture == 1)
                            setDossierFacture(urcleunik, idDos, 0, dossier.getDrtotalprix());
                    }
                }
                Transaction.commit(tmpool.getConuser());
            }
        } catch (SQLException sn) {
            renvexception(sn, "erreur lors de la demande d'info sur l'historique", tmpool.getConuser());
        } catch (IOException in) {
            in.printStackTrace();
        }
        return retval;
    }

    public void setTicketIntegrated(Hashtable hash, Poolconnection tmpool, int integrated) throws SQLException {
        if (hash == null)
            return;
        String value = "";
        for (java.util.Enumeration enu = hash.keys(); enu.hasMoreElements();) {
            value = value + enu.nextElement().toString() + ",";
        }
        value = value.substring(0, value.length() - 1);
        value = " IN(" + value + ") ";
        String requete = "UPDATE ticketintegration SET  tion_integrated = ? WHERE  tion_cleunik" + value;
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, integrated);
        pstmt.execute();
    }

    public ArrayList getTicketToIntegrate(Hashtable hash, Poolconnection tmpool) throws SQLException {
        if (hash == null)
            return null;
        String value = "";
        for (java.util.Enumeration enu = hash.keys(); enu.hasMoreElements();) {
            value = value + enu.nextElement().toString() + ",";
        }
        value = value.substring(0, value.length() - 1);
        value = " IN(" + value + ") ";
//                          0               1               2           3               4           5           6           7               8           9           10          11              12      13              14          15              16          17          18          19          20          21
        String requete = "SELECT t.tion_cleunik, t.typ_billet, t.num_billet, t.cnj_billet, t.date_emiss, t.date_dep, t.num_refund, t.passager, t.mode_paiem, t.num_cccf, t.val_facial, t.val_vente, t.val_net, t.tax_locale, t.tax_compag, t.tax_destin, c.coe_cleunik, t.etat, t.val_commis, t.val_remise, t.remark, t.v_po_pnr, t.v_fullfare FROM ticketintegration t LEFT JOIN compagnie c ON t.compagnie = c.coe_abrev WHERE  t.tion_cleunik" + value + " AND t.tion_integrated = 0";
        System.out.println("requete " + requete);
        ArrayList array1 = new ArrayList();
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        ResultSet result = pstmt.executeQuery();
        int colcount = result.getMetaData().getColumnCount();
        result.beforeFirst();
        while (result.next()) {
            Object[] tab = new Object[colcount]; //c'était 21
            for (int i = 0; i < tab.length; i++) {
                if (i == 4 || i == 5) {
                    String date = result.getDate(i + 1).toString();
                    System.out.println("date " + date);
                    tab[i] = date + " 00:00:00";
                } else {
                    tab[i] = result.getObject(i + 1);
                }
            }
            array1.add(tab);
        }
        return array1;
    }

    private void generatePassager(Object[] tab, Dossier_T dossier, Avion_ticket_T ticket, int cle) {
        Passager_T passager = new Passager_T();
        passager.setPr_nom(tab[7].toString());
        passager.setPr_prénom("  ");
        passager.setPr_adrese(" ");
        passager.setNewReccord(true);
        passager.setPr_datenaissance(new srcastra.astra.sys.classetransfert.utils.Date("1001-01-01 10:10:10"));
        passager.setPr_cleunik(cle);
        ticket.setPassager(cle);
        if (dossier != null)
            dossier.addPassager(passager);
        ticket.setPassagert(passager);
    }

    private Dossier_T checkConjonction(ArrayList array, Poolconnection tmpool, Hashtable hash, Loginusers_T user) throws SQLException, RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        Dossier_T dossier = null;
        if (array != null) {
            dossier = new Dossier_T();
            dossier.setNewreccord(true);
            dossier.setUrcleunikcrea(tmpool.getUrcle2());
            boolean sw = false;
            for (int i = 0; i < array.size(); i++) {
                Avion_ticket_T avion = new Avion_ticket_T();
                avion.setTypeDeProduitCleunik(produit_T.AV);
                avion.setTypeDeProduitNom("AV");
                avion.setIsnewreccord(true);
                avion.setLibelleCompta(java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("Titre_Pane_aviation"));
                Object[] tab = (Object[]) array.get(i);
                Object[] param = (Object[]) hash.get(new Integer(tab[0].toString()));
                avion.setAt_fullfare(((Double)tab[22]).doubleValue());
                
                
                if (param[10].toString().equals("0"))
                    avion.setAt_cleunik(i);
                else
                    avion.setAt_cleunik(Long.parseLong(param[10].toString()));
                if (!sw) {
                    dossier.setCscleunik(((Integer) param[1]).intValue());
                    dossier.setCscleunik_fact(((Integer) param[1]).intValue());
                    dossier.setClientContractuel(getDossierClientLocal(dossier.getCscleunik(), user));
                    dossier.setDr_date_depart(new srcastra.astra.sys.classetransfert.utils.Date(tab[5].toString()));
                    dossier.setDr_date_retour(new srcastra.astra.sys.classetransfert.utils.Date("1001-01-01 10:10:10"));
                    sw = true;
                }
                int frgtcleunik = ((Integer) param[2]).intValue();
                int frcleunik = ((Integer) param[4]).intValue();
                double valeurvente = ((Double) param[3]).doubleValue();
                int conjonction = Integer.parseInt(tab[3].toString());
                generateTicket(tab, frgtcleunik, frcleunik, tmpool, avion, param);
                ProduitInfoComplementaire.getInfo(ProduitInfoComplementaire.AV, ProduitInfoComplementaire.AV_FULL, produit_T.AV, user.getLangage(), avion, tmpool.getConuser());
                long tion_cleunik = Long.parseLong(tab[0].toString());
                avion.setAt_val_vente(valeurvente);
                generatePassager(tab, dossier, avion, -i);
                generateConjonction(tab, avion, tmpool, dossier);
                if (conjonction > 1) {
                    Hashtable tmphash = new Hashtable();
                    for (int j = 1; j < conjonction; j++) {
                        tmphash.put(new Long(tion_cleunik + 1), "");
                    }
                    ArrayList conjonctionList = getTicketToIntegrate(tmphash, tmpool);
                    if (conjonctionList != null) {
                        for (int j = 0; j < conjonctionList.size(); j++) {
                            Object[] tmptab = (Object[]) conjonctionList.get(j);
                            generateConjonction(tmptab, avion, tmpool, dossier);
                        }
                    }
                }
/*  ArrayList tmpconj=avion.getConjonctionList();
Conjonction_T lasconjonction=(Conjonction_T)tmpconj.get(tmpconj.size()-1);
ArrayList tmpsegmentA=lasconjonction.getSegmentList();
Segment*/
                avion.calculMontantTotal();
                dossier.addTicket(avion);
                
                System.out.println(tab[8]+" "+tab[22]);
                
                double val = Double.valueOf(tab[22].toString()).doubleValue();
                
                if (val>0) {
                    
                    srcastra.astra.gui.modules.dossier.productSpecification.integration.Payement.addPaymentMCO((Avion_ticket_T) dossier.getAvionTicket().get(new Long(avion.getAt_cleunik())), dossier, i);
                    
                }
                
                if (tab[8].equals("CCCF")) {
                    srcastra.astra.gui.modules.dossier.productSpecification.integration.Payement.addPayment((Avion_ticket_T) dossier.getAvionTicket().get(new Long(avion.getAt_cleunik())), dossier, i);
                }
                
                
                
                srcastra.astra.gui.modules.dossier.utils.FillDossierInfo.validateProductAvion(dossier, avion);
            }
            dossier.calculTotalProduit();
            dossier.calculTotalPayement();
            dossier.calculEcheance();
        }
        return dossier;
    }

    private void chargeSegments(long tioncleunik, Conjonction_T conj, Poolconnection tmpool, Dossier_T dossier) throws SQLException {
        
        //add by me
        String requete1 ="SELECT c.coe_cleunik from compagnie c where c.coe_abrev like ?";
        //add by me
        String requInsertC ="INSERT INTO `compagnie` (`coe_abrev`,`coe_nom`) values (?,?)";
        //add by me
        String requInsertD ="INSERT INTO `destination` (`dn_abrev`) values (?)";
        //add by me
        String requete2 ="SELECT d.dn_cleunik from destination d where d.dn_abrev like ?";
    
        String requete = "SELECT s.position, d.dn_cleunik, d2.dn_cleunik, c.coe_cleunik, s.numero_vol, s.date_depar, s.heure_depa, s.heure_arri, s.farecode, s.classe, s.status, s.seating,s.routing_de,s.routing_a,s.compagnie FROM  segmentintegration s LEFT JOIN compagnie c ON s.compagnie = c.coe_abrev LEFT JOIN destination d ON s.routing_de = d.dn_abrev LEFT JOIN destination d2 ON s.routing_a = d2.dn_abrev WHERE s.tion_cleunik=?";
        
        //String requeteV = "SELECT  d.dn_cleunik, d2.dn_cleunik, c.coe_cleunik, s.numero_vol, s.date_depar, s.heure_depa, s.heure_arri, s.farecode, s.classe, s.status, s.seating,s.routing_de,s.routing_a,s.compagnie FROM  segmentintegration s LEFT JOIN compagnie c ON s.compagnie = c.coe_abrev LEFT JOIN destination d ON s.routing_de = d.dn_abrev LEFT JOIN destination d2 ON s.routing_a = d2.dn_abrev WHERE s.tion_cleunik=?";
        
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setLong(1, tioncleunik);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        
        while(result.next()) {
          PreparedStatement pstmt1= tmpool.getConuser().prepareStatement(requete1);
          pstmt1.setString(1,result.getString(15));
          ResultSet result1=pstmt1.executeQuery();
          result1.beforeFirst();
         
          
          
          if (!result1.next()) 
            { 
              
                pstmt1= tmpool.getConuser().prepareStatement(requInsertC);
                pstmt1.setString(1,result.getString(15));
                pstmt1.setString(2,result.getString(15));
                pstmt1.execute();
            }
          
          pstmt1= tmpool.getConuser().prepareStatement(requete2);
          pstmt1.setString(1,result.getString(14));
          result1=pstmt1.executeQuery();
           result1.beforeFirst();
          
           if (!result1.next()) 
            { 
              
            pstmt1= tmpool.getConuser().prepareStatement(requInsertD);
            pstmt1.setString(1,result.getString(14));
            pstmt1.execute();
            }
          
          pstmt1= tmpool.getConuser().prepareStatement(requete2);
          pstmt1.setString(1,result.getString(13));
          result1=pstmt1.executeQuery();
          
          result1.beforeFirst();
           
          if (!result1.next()) 
            { 
            pstmt1= tmpool.getConuser().prepareStatement(requInsertD);
            pstmt1.setString(1,result.getString(13));
            pstmt1.execute();
            }
            
        }
        
       pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setLong(1, tioncleunik);
        result = pstmt.executeQuery();
        result.beforeFirst(); 
        
        while (result.next()) {
            Avion_segment_T segment = new Avion_segment_T();
            segment.setIsnewreccord(true);
            segment.setAs_position(result.getInt(1));
            segment.setAs_routing_de(result.getInt(2));
            segment.setAs_routing_a(result.getInt(3));
            segment.setCoe_cleunik(result.getInt(4));
            segment.setAs_numero_vol(result.getString(5));
            segment.setAs_date_départ(new srcastra.astra.sys.classetransfert.utils.Date(result.getDate(6).toString() + " 00:00:00"));
            String heure = result.getTime(7).toString();
            heure = heure.substring(2, heure.length());
            String heurefin = result.getTime(8).toString();
            heurefin = heurefin.substring(2, heurefin.length());
            segment.setAs_heure_arrive(heurefin);
            segment.setAs_heure_départ(heure);
            segment.setAs_farebasis(result.getString(9));
            segment.setAs_classe(result.getString(10));
            segment.setFree_routing_de(result.getString(13));
            segment.setAs_routing_de_intitule(result.getString(13));
            segment.setFree_routing_a(result.getString(14));
            segment.setAs_routing_a_intitule(result.getString(14));
              
            if (dossier != null)
                dossier.setDr_date_retour(segment.getAs_date_départ());
            conj.addSegment(segment);
        }
        if (dossier != null) {
            int[] tmp = CalculDate.renvDifferenceBetweenDate(dossier.getDr_date_depart(), dossier.getDr_date_retour());
            dossier.setDr_nbrjour(tmp[1]);
            dossier.setDr_nbrenuit(tmp[0]);
        }
    }

    private void generateConjonction(Object[] tab, Avion_ticket_T avion, Poolconnection tmpool, Dossier_T dossier) throws SQLException {
        Conjonction_T tmpConj = new Conjonction_T();
        tmpConj.setNewreccord(true);
        tmpConj.setNewreccord(true);
        tmpConj.setNumbillet(tab[2].toString());
        chargeSegments(Long.parseLong(tab[0].toString()), tmpConj, tmpool, dossier);
        avion.addConjonction(tmpConj);
    }

    private void generateTicket(Object[] tab, int frgtcleunik, int frcleunik, Poolconnection tmpool, Avion_ticket_T avion, Object[] param) {
        
        
        srcastra.astra.gui.modules.dossier.utils.Makedefinition.makeProduitDefinition((astrainterface) serveur, avion, frgtcleunik, frcleunik, tmpool.getUrcleunik());
        avion.setTion_cleunik(Long.parseLong(tab[0].toString()));
        if (avion.getGroupdec() != null)
            avion.setFrgtcleunik(avion.getGroupdec().getGncleunik());
        avion.setAt_type_billet(1);
        avion.setAt_num_billet(tab[2].toString());
        avion.setAt_date_emission(new srcastra.astra.sys.classetransfert.utils.Date(tab[4].toString()));
        avion.setAt_date_depart(new srcastra.astra.sys.classetransfert.utils.Date(tab[5].toString()));
        avion.setAt_date_stock(new srcastra.astra.sys.classetransfert.utils.Date("1001-01-01 10:10:10"));
        avion.setAt_annul_date(new srcastra.astra.sys.classetransfert.utils.Date("1001-01-01 10:10:10"));
        avion.setAt_cloture_date(new srcastra.astra.sys.classetransfert.utils.Date("1001-01-01 10:10:10"));
        avion.setAt_numrefund(tab[6].toString());
        avion.setAt_mode_paiement(1);
        avion.setAt_num_cccf(tab[9].toString());
        if (tab[8].equals("CASH"))
            avion.setAt_mode_paiement(2);
        else
            avion.setAt_mode_paiement(7);
        double fees = ((Double) param[9]).doubleValue();
        double taxloc = ((Double) param[5]).doubleValue();
        double taxcomp = ((Double) param[6]).doubleValue();
        double taxdest = ((Double) param[7]).doubleValue();
        double remise = ((Double) param[8]).doubleValue();
        double valvente = ((Double) param[3]).doubleValue();
        avion.setAt_val_facial(Double.parseDouble(tab[10].toString()));
        avion.setAt_val_vente(valvente);
        avion.setAt_val_nette(Double.parseDouble(tab[12].toString()));
        avion.setAt_tax_locale(taxloc);
        supReduc(avion, avion.getAt_tax_locale(), 0, java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", tmpool.getLangage()).getString("tax_loc"), -1);
        avion.setAt_tax_compagnie(taxcomp);
        supReduc(avion, avion.getAt_tax_compagnie(), 0, java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", tmpool.getLangage()).getString("tax_comp"), -3);
        avion.setAt_tax_destination(taxdest);
        supReduc(avion, avion.getAt_tax_destination(), 0, java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", tmpool.getLangage()).getString("tax_des"), -2);
        int coe_cleunik = 0;
        if (tab[16] != null)
            coe_cleunik = Integer.parseInt(tab[16].toString());
        avion.setCoe_cleunik(coe_cleunik);
        avion.setAt_etat(Integer.parseInt(tab[17].toString()));
        avion.setAt_val_com(Double.parseDouble(tab[18].toString()));
        avion.setAt_val_remise(remise);
        supReduc(avion, avion.getAt_val_remise(), 1, java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", tmpool.getLangage()).getString("tax_rem"), -5);
        avion.setAt_fees(fees);
        supReduc(avion, avion.getAt_fees(), 0, java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", tmpool.getLangage()).getString("tax_fees"), -4);
        avion.setAt_memo(tab[20].toString());
        avion.setAt_pnr(tab[21].toString());
        avion.setLongtime();
        double tmpvalue = avion.getAt_val_com();
        double tmpvalue2 = avion.getAt_val_facial();
        double result = tmpvalue2 - ((tmpvalue2 / 100) * tmpvalue);
        result = MathRound.roundThisToDouble(result);
        avion.setAt_val_nette(result);
        avion.setIsnewreccord(true);
    }

    private void supReduc(Avion_ticket_T avion, double prix, int type, String libelle, int aclibelle) {
        if (prix > 0) {
            Sup_reduc_T supReduc = new Sup_reduc_T();
            supReduc.setAt_cleunik(aclibelle * -1);
            if (type == 1) {
                supReduc.setReduc(1);
                supReduc.setAt_val_vente(-prix);
            } else {
                supReduc.setAt_val_vente(prix);
                supReduc.setSup(1);
            }
            if (avion.getGroupdecBase() != null) {
                try {
                    supReduc.setGroupdec((srcastra.astra.sys.classetransfert.Grpdecision_T) avion.getGroupdecBase().clone());
                    CheckCptSupReduc.checkGrpDecServeur(supReduc.getGroupdec(), aclibelle, serveur);
                    if (aclibelle == -4) {
                        supReduc.getGroupdec().setModifyreccord(true);
                        supReduc.getGroupdec().setGnfrtvaComptabiliserVente(1);
                        supReduc.getGroupdec().setGntvainclusvente(0);
                        supReduc.getGroupdec().setGncodetvavente(TVA21);
                        supReduc.getGroupdec().setValeurGenFloat1(21f);
                        supReduc.setSoumis_tva(0);
                    }
                } catch (CloneNotSupportedException cn) {
                    cn.printStackTrace();
                }
            }
            supReduc.setParent(avion);
            supReduc.setSoumis_tva(0);
            supReduc.setAclibelle(aclibelle);
            supReduc.setFreeLibelle(libelle);
            supReduc.setNewreccord(true);
            supReduc.calculMontantTotal();
//   supReduc.calculMontantTotal();
            avion.addSup_Reduc(supReduc);

        }
    }

    public ArrayList getClientFees(int cs_cleunik, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        String requete = "";
        if (tmpool.getLmcleunik() == 1)
            requete = "SELECT clse_cleunik,clse_fees,clse_libelle1 FROM clients_remise2 WHERE cs_cleunik=" + cs_cleunik + " ORDER BY clse_fees";
        else
            requete = "SELECT clse_cleunik,clse_fees,clse_libelle2 FROM clients_remise2 WHERE cs_cleunik=" + cs_cleunik + " ORDER BY clse_fees";
        return Transaction.generecombostest3(requete, tmpool.getConuser());
    }


    public double checkSoldeComptaBle(int urcleunik) throws RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        double base = 0;
        double cp = 0;
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            String requetebase = "SELECT SUM( hevaleur )  FROM historique2 WHERE hetypeligne IN ( 'B', 'BP','P', 'CP','CPC','A','OBCC','OCCC','NCB','NCAB','NCOB' )";
            String requecp = "SELECT SUM( hevaleurbase ) FROM historique2 WHERE hetypeligne  IN ('D','ACP','TVA','TVAV','NC','NCT','NCA','NCO','NCOT') ";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requetebase);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                base = result.getDouble(1);
            }
            pstmt = tmpool.getConuser().prepareStatement(requecp);
            result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                cp = result.getDouble(1);
            }
            double solde = Math.abs(MathRound.roundThisToDouble(base)) - Math.abs(MathRound.roundThisToDouble(cp));
//System.out.println("base "+base+" cp "+cp);
            System.out.println("\n\n\n\n\n\n\n\n\n\nsolde comptable =" + solde + " \n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("base " + base + " cp " + cp);
            return solde;
        } catch (SQLException sn) {
            sn.printStackTrace();
        }
        return -15;
    }

    public void modifyTask(Task_T task, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            new Task_manage().modifyTask(task, tmpool);
        } catch (SQLException sn) {
            sn.printStackTrace();
            renvexception(sn, "Erreur lors de la modification d'une nouvelle tâche", tmpool.getConuser());
        }
    }

    public void insertTask(Task_T task, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            new Task_manage().insertTask(task, tmpool);
        } catch (SQLException sn) {
            sn.printStackTrace();
            renvexception(sn, "Erreur lors de l'insertion d'une nouvelle tâche", tmpool.getConuser());
        }
    }

    public java.util.ArrayList getList(srcastra.astra.sys.classetransfert.utils.Date date, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            return new Task_manage().getList(date, tmpool);
        } catch (SQLException sn) {
            sn.printStackTrace();
            renvexception(sn, "Erreur lors de la selection des tâches", tmpool.getConuser());
        }
        return null;
    }

    public Calendar_T getCalendar(srcastra.astra.sys.classetransfert.utils.Date date, Loginusers_T user, int urcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = serveur.getConnectionAndCheck(urcleunik, true);
        try {
            return new Task_manage().getArray(date, user, tmpool);
        } catch (SQLException sn) {
            sn.printStackTrace();
            renvexception(sn, "Erreur lors de la selection des tâches", tmpool.getConuser());
        }
        return null;
    }

    private Hashtable requeteRecherche;
    private Dossier_T dossier;
    private Avion_ticket_T ticket;
    private astraimplement serveur;
    private boolean isValid;
    private Dossier_T lock;
    private Hashtable renvValueDossier;
    public final static int TVA21 = 288;
}
