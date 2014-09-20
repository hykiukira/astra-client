/*
 * PayementClient.java
 *
 * Created on 8 septembre 2003, 17:38
 */

package srcastra.astra.sys.compta;

import srcastra.astra.sys.classetransfert.compta.JournalCompta_T;
import srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T;
import srcastra.astra.sys.classetransfert.configuration.TypePayement;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCentral;
import srcastra.astra.sys.compta.command.ComptaInseMod;
import srcastra.astra.sys.rmi.ParamComptableInterface;

/**
 * @author Thomas
 */
public class Payement {

    /**
     * Creates a new instance of PayementClient
     */

    public Payement(Check check, Payement_T payement, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        this.payement = payement;
        m_config = config;
        this.urcleunik = m_config.getCle2();
        if (!payement.isCccf())
            check.base(CheckCompteCentral.CAISSE, ParamComptableInterface.JOURNAL_CAISSE);
        else
            check.base4(CheckCompteCentral.CCCF, ParamComptableInterface.JOURNAL_OCCCF);
        this.check = check;

        numpiece = "0";

        if (m_config.getDossier() != null) {
            if (!m_config.getDossier().isRattrap()) {
                if (!payement.isCccf()) {
                    numpiece = m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(), ParamComptableInterface.JOURNAL_CAISSE, m_config.getM_urcleunik(), m_config.getEecleunik());
                } else {
                    numpiece = m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(), ParamComptableInterface.JOURNAL_OCCCF, 0, m_config.getEecleunik());
                }
            }
        } else if (!payement.isCccf()) {
            numpiece = m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(), ParamComptableInterface.JOURNAL_CAISSE, m_config.getM_urcleunik(), m_config.getEecleunik());
        } else {
            numpiece = m_config.getM_serveur().renvParamCompta(urcleunik).checkNumero(m_config.getCle2(), ParamComptableInterface.JOURNAL_OCCCF, 0, m_config.getEecleunik());
        }
    }

    protected void insertPayement(String p, String sign, String decre) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        double prix = payement.getPrix();
        datepayement = payement.getDatePayement().toString();
        if (datepayement == null)
            datepayement = "0000-00-00 00:00:00";
        String debcre = "";
        if (sign.equals("-")) {
            prix = prix * -1;
        }
        if (m_config.getDossier() != null) {
            if (!m_config.getDossier().isRattrap()) {
                new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE, m_config, 1, 0, 0, 0, 0, Long.parseLong(numpiece), datepayement, 0, 0, 0, "", prix, 0, 0,
                        0, 0, "", 0, payement.getLibelle(), m_config.getCledossier(), payement.getAvion() == null ? -1 : payement.getAvion().getAt_cleunik(), -1, 0, m_config.getTypeintervenant(), m_config.getCleintervenant(), 1, "O", p, m_config.getM_urcleunik(), payement.getM_typepayement(), "Ligne de globalisation du dossier",
                        0, 0, 0, 0, 0, 0, decre,0);
            } else {
                new ComptaInseMod().insertRattrap(AbstractLigneCompta.INSERT_LIGNE, m_config, 1, 0, 0, 0, 0, Long.parseLong(numpiece), datepayement, 0, 0, 0, "", prix, 0, 0,
                        0, 0, "", 0, payement.getLibelle(), m_config.getCledossier(), payement.getAvion() == null ? -1 : payement.getAvion().getAt_cleunik(), -1, 0, m_config.getTypeintervenant(), m_config.getCleintervenant(), 1, "O", p, m_config.getM_urcleunik(), payement.getM_typepayement(), "Ligne de globalisation du dossier",
                        0, 0, 0, 0, 0, 0, decre);

            }
        } else {
            new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE, m_config, 1, 0, 0, 0, 0, Long.parseLong(numpiece), datepayement, 0, 0, 0, "", prix, 0, 0,
                    0, 0, "", 0, payement.getLibelle(), m_config.getCledossier(), payement.getAvion() == null ? -1 : payement.getAvion().getAt_cleunik(), -1, 0, m_config.getTypeintervenant(), m_config.getCleintervenant(), 1, "O", p, m_config.getM_urcleunik(), payement.getM_typepayement(), "Ligne de globalisation du dossier",
                    0, 0, 0, 0, 0, 0, decre,0);
        }
    }

    protected Payement_T payement;
    protected JournalCompta_T obj;
    protected TypePayement typepay;
    protected Caisselibelle_T caisse;
    protected String datepayement;
    protected String numpiece;
    protected Configuration m_config;
    protected int urcleunik;
    protected Check check;

}
