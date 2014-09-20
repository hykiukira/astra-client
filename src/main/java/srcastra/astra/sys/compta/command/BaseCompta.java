/*
* DossierCompta.java
*
* Created on 10 février 2003, 15:15
*/
package srcastra.astra.sys.compta.command;

import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.compta.AbstractLigneCompta;
import srcastra.astra.sys.compta.Configuration;
import srcastra.astra.sys.compta.GetData;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.rmi.astraimplement;

/**
 * @author Thomas
 */
public class BaseCompta extends AbstractLigneCompta implements Command {

    /**
     * Creates a new instance of DossierCompta
     */
    produit_T produit;
    Configuration m_config;
    astraimplement m_serveur;
    float codetva;
    String compte;
    String typeprod;
    int codetvavente = 0;
    int codeCompteVente = 0;
    int codeComteCentral = 0;
    int gncleunik = 0;
    Check check;

    public BaseCompta(Object obj, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {

        produit = (produit_T) obj;
        m_config = config;
        check = new Check(config.getCle2(), config);
        check.init();
        check.commun(produit.getGroupdec().getGncomptevente(), GetData.getCompteData("" + m_config.getM_urcleunik()).getTypecentral());
        compte = m_config.getM_serveur().getCompte(GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), new Long(m_config.getCle2()).intValue());
        codetva = produit.getGroupdec().getValeurGenFloat1();
        if (compte == null)
            compte = "erreur";
        if (produit.getTypeDeProduitCleunik() == produit_T.FRAIS)
            typeprod = "F"; //hetypeligne`
        else
            typeprod = "D"; //hetypeligne`
        if (produit.getGroupdec() != null) {
            codetvavente = produit.getGroupdec().getGncodetvavente();
            gncleunik = produit.getGroupdec().getGncleunik();
        }
    }

    public void executeInsert(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        if (!produit.isDeleted()) {
            new ComptaInseMod().insert(INSERT_LIGNE, m_config, 0, 0, 0, 0, 0l, 0l, "0000-00-00 00:00:00", codeComteCentral, codetvavente, codeCompteVente, compte, -produit.getValeur_tot_tva_inc(), codetva, -produit.getValeur_tot(),
                    -produit.getMontant_tva(), 0, "", 0d, produit.getLibelleCompta(), m_config.getCledossier(), produit.getAt_cleunik(), 0, produit.getTypeDeProduitCleunik(), 1, m_config.getCleintervenant(), 1, "O", "D", urcleunik, 0, "Ligne de globalisation du dossier",
                    produit.getPax(), produit.getQua(), produit.getPrct(), produit.getAt_val_vente(), gncleunik, 1, "C",0);
        }
    }

    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {
        if (produit.isDeleted()) {
            new ComptaInseMod().delete(m_config, m_config.getCledossier(), produit.getAt_cleunik(), 0, produit.getTypeDeProduitCleunik());
        } else {
            if (!produit.isIsnewreccord()) {
                new ComptaInseMod().modify(m_config.getCledossier(), produit.getAt_cleunik(), 0, UPDATE_LIGNE, produit, m_config, codeComteCentral, produit.getGroupdec().getGncodetvavente(), codeCompteVente, compte, -produit.getValeur_tot_tva_inc(), codetva
                        , -produit.getValeur_tot(), -produit.getMontant_tva(), 0, "", 0, produit.getLibelleCompta()
                        , m_config.getCleintervenant(), 0, urcleunik
                        , 0, "", produit.getPax(), produit.getQua(), produit.getPrct(), produit.getAt_val_vente(), produit.getGroupdec().getGncleunik(), produit.getTypeDeProduitCleunik());

            } else {
                new ComptaInseMod().insert(INSERT_LIGNE, m_config, 0, 0, 0, 0, 0l, 0l, "0000-00-00 00:00:00", codeComteCentral, codetvavente, codeCompteVente, compte, -produit.getValeur_tot_tva_inc(), codetva, -produit.getValeur_tot(),
                        -produit.getMontant_tva(), 0, "", 0d, produit.getLibelleCompta(), m_config.getCledossier(), produit.getAt_cleunik(), 0, produit.getTypeDeProduitCleunik(), 1, m_config.getCleintervenant(), 1, "O", "D", urcleunik, 0, "Ligne de globalisation du dossier",
                        produit.getPax(), produit.getQua(), produit.getPrct(), produit.getAt_val_vente(), gncleunik, 1, "C",0);

            }
        }
    }
    
     public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 
}
