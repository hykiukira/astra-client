/*
* DossierCompta.java
*
* Created on 10 février 2003, 15:15
*/
package srcastra.astra.sys.compta.command;

import srcastra.astra.gui.modules.compta.achat.AchatCp;
import srcastra.astra.gui.modules.compta.achat.Achat_T;
import srcastra.astra.gui.modules.compta.achat.Tva_abrev_Achat_T;
import srcastra.astra.sys.classetransfert.compta.JournalCompta_T;
import srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T;
import srcastra.astra.sys.classetransfert.utils.GetId;
import srcastra.astra.sys.compta.AbstractLigneCompta;
import srcastra.astra.sys.compta.Configuration;
import srcastra.astra.sys.compta.GetData;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCentral;
import srcastra.astra.sys.rmi.ParamComptable;
import srcastra.astra.sys.rmi.astraimplement;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Thomas
 */
public class AchatCompta extends AbstractLigneCompta implements Command {

    /**
     * Creates a new instance of DossierCompta
     */
    Achat_T produit;
    Configuration m_config;
    astraimplement m_serveur;
    float codetva;
    String typeprod;
    int codetvavente = 0;
    int codeCompteVente = 0;
    int codeComteCentral = 0;
    int gncleunik = 0;
    int compte;
    int centralisateur;
    String comptes;
    Check check;
    JournalCompta_T journal;

    public AchatCompta(Object obj, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {

        produit = (Achat_T) obj;
        m_config = config;
        check = new Check(config.getCle2(), config);
        check.init();

        journal = (JournalCompta_T) m_config.getM_serveur().renvParamCompta(m_config.getCle2()).getJournalBase(m_config.getCle2(), produit.getJota_cleunik(), 0);
        check.base5(CheckCompteCentral.FOURNISSEUR, journal.getJota_categorie(), journal.getJota_cleunik());
// comptes=m_config.getM_serveur().getCompte(GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),new Long(m_config.getCle2()).intValue());

    }

    private void addTva(Hashtable hash, Tva_abrev_Achat_T tva1, AchatCp achatcp) {
        Tva_abrev_Achat_T tvasom = (Tva_abrev_Achat_T) hash.get(new Integer(tva1.getTva_id()));
        if (tvasom == null) {
            System.out.println("creation " + tva1.getTva_rate() + " " + tva1.getTva_base() + " " + tva1.getTva_value());
            tvasom = new Tva_abrev_Achat_T(tva1.getTva_id(), tva1.getTva_base().toString(), tva1.getTva_value().toString(), tva1.getTva_rate().toString(), achatcp.getDc());
            System.out.println("ajout " + tvasom.getTva_rate() + " " + tvasom.getTva_base() + " " + tvasom.getTva_value());
            if (tva1.getTva_id() != 0)
                hash.put(new Integer(tva1.getTva_id()), tvasom);
        } else {
            System.out.println("ajout " + tva1.getTva_rate() + " " + tva1.getTva_base() + " " + tva1.getTva_value());
            System.out.println("ajout " + tvasom.getTva_rate() + " " + tvasom.getTva_base() + " " + tvasom.getTva_value());
            tvasom.addValue(tva1.getTva_base().toString(), tva1.getTva_value().toString(), achatcp.getDc());
        }


    }

    public void executeInsert(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        ComptaInseMod comptains = new ComptaInseMod();
        String numpiece = produit.getNumpiece();
        
        long exported = produit.getExported();
        
        
        
        if(numpiece==null)
            numpiece="";
        
        
        try {
            
            if(numpiece.length()==0 || numpiece==null)
            {    
            numpiece = ((ParamComptable) m_config.getM_serveur().renvParamCompta(m_config.getCle2())).checkNumero2(m_config.getCle2(), GetData.getCompteData("" + m_config.getM_urcleunik()).getJota_cleunik());
            m_config.setNumnc(numpiece);
            }
            
        } catch (java.rmi.RemoteException rn) {
            rn.printStackTrace();
        }
        GetData.getCompteData("" + urcleunik).setPeriode(produit.getJota_periode());
//String dc="C";
        String typeLigne = "A";
        String typeLignecp = "ACP";
        double montant = 0;
        double horstax = 0;
        double tax = 0;
        if (produit.getDc().equals("D")) {
//if(journal.getJota_categorie()==ParamComptableInterface.JOURNAL_NCACHAT){
// dc="D";
            typeLigne = "NCAB";
            typeLignecp = "NCA";
            produit.getMontanteuro().inverse();
            produit.getTvamontant().inverse();
            produit.getBase().inverse();
// produit.get
//montant=montant*-1;
//horstax=horstax*-1;
//tax=tax*-1;
        } else {
        }
        int jxcleunik = GetData.getCompteData("" + m_config.getM_urcleunik()).getJota_cleunik();
        GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), produit.getMontanteuro().doubleValue(), produit.getDc(), null));
        GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.FOURNISSEUR, produit.getFrcleunik(), produit.getMontanteuro().doubleValue(), produit.getDc(), null));
        comptains.insert(INSERT_LIGNE, m_config, 1, 0, 0, 0, 0l, Long.parseLong(numpiece), produit.getDate().toString(), centralisateur, 0, codeCompteVente, comptes, produit.getMontanteuro().doubleValue(), 0, produit.getBase().doubleValue(),
                produit.getTvamontant().doubleValue(), 0, "", 0d, produit.getCommentaire(), produit.getDrcleunik(), 0, 0, 0, 2, produit.getFrcleunik(), 1, "O", typeLigne, urcleunik, 0, "Ligne achat",
                0, 0, 0, 0, gncleunik, 1, produit.getDc(),exported);
        int id = GetId.getLastId(m_config.getCon());
        ArrayList contrepartie = produit.getAchat();
//ArrayList tvaliste=produit.getTva();
        String comptes = "";
        int comptecp = 0;
        int comptecentral = 0;
// dc="D";
//if(journal.getJota_categorie()==ParamComptableInterface.JOURNAL_NCACHAT)
//  dc="C";
        Hashtable tvaHash = new Hashtable();
        if (contrepartie != null) {
            for (int i = 0; i < contrepartie.size(); i++) {
                AchatCp achatcp = (AchatCp) contrepartie.get(i);
                if (achatcp != null) {
                    if (achatcp.getTva1() != null) {
                        System.out.println("DC " + achatcp.getDc() + " BASE " + achatcp.getBase().toString() + " TVA BASE " + achatcp.getTva1().getTva_base().toString() + " TVA VALUE" + achatcp.getTva1().getTva_value().toString());

                    }
                }
            }


            for (int i = 0; i < contrepartie.size(); i++) {
                AchatCp achatcp = (AchatCp) contrepartie.get(i);
                if (achatcp != null) {
                    if (achatcp.getTva1() != null) {
                        addTva(tvaHash, achatcp.getTva1(), achatcp);
                        if (achatcp.getBase().doubleValue() != 0d) {
                            if (achatcp.getDc().equals("D")) {
                                achatcp.getBase().abs();
                                achatcp.getTva1().getTva_base().abs();
                                achatcp.getTva1().getTva_value().abs();
                                achatcp.getTva1().getPrixTot().abs();
                                achatcp.getBase().inverse();
                                achatcp.getTva1().getTva_base().inverse();
                                achatcp.getTva1().getTva_value().inverse();
                                achatcp.getTva1().getPrixTot().inverse();
                            }
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n[*******************]addTva");

// Object[] tmp=(Object[])contrepartie.get(i);
//dc=tmp[3].toString();
// if(tmp[0]==null || tmp[1]==null || tmp[2]==null);
//else{
// if(tmp!=null){
                            check.commun(achatcp.getCe_cleunik(), GetData.getCompteData("" + m_config.getM_urcleunik()).getTypecentral());
                            String commentaire = "";
                            if (achatcp.getCommentaire() != null)
                                commentaire = achatcp.getCommentaire();
                            int tvacle = achatcp.getTva1().getTva_id();
//if(tmp[7]!=null)
//  tvacle=((Integer)tmp[7]).intValue();
                            double valeurtotal = achatcp.getTva1().getPrixTot().doubleValue();
// if(tmp[9]!=null)
//   valeurtotal=((Double)tmp[9]).doubleValue();
                            float tvarate = new Double(achatcp.getTva1().getTva_rate().doubleValue()).floatValue();
//if(tmp[10]!=null)
//  tvarate=((Float)tmp[10]).floatValue();
                            double base = achatcp.getBase().doubleValue();
//if(tmp[2]!=null)
//    base=((Double)tmp[2]).doubleValue();
                            double tvavalue = achatcp.getTva1().getTva_value().doubleValue();
//if(tmp[8]!=null)
//    tvavalue=((Double)tmp[8]).doubleValue();
                            long cledos = achatcp.getCledossier();
//if(tmp[12]!=null)
//  cledos=((Long)tmp[12]).longValue();
                            long cleprod = achatcp.getCleprod();
//if(tmp[11]!=null)
//  cleprod=((Long)tmp[11]).longValue();
                            int cateprod = achatcp.getCatProd();
//   if(tmp[13]!=null)
//     cateprod=((Integer)tmp[13]).intValue();
                            int cleintervenant = achatcp.getFrcleunik();
                            String po = "";
                            if (achatcp.getPoPnr() != null) {
                                po = achatcp.getPoPnr();
                            }
//if(journal.getJota_categorie()==ParamComptableInterface.JOURNAL_ACHAT){
//valeurtotal=valeurtotal*-1;
//base=base*-1;
//tvavalue=tvavalue*-1;
//}
//     if(tmp[14]!=null)
//       cleintervenant=((Integer)tmp[14]).intValue();
                            GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), Math.abs(base), achatcp.getDc(), null));
                            comptains.insert(INSERT_LIGNE, m_config, 1, 0, 0, 0, 0l, Long.parseLong(numpiece), produit.getDate().toString(), comptecentral, tvacle, comptecp, comptes, valeurtotal, tvarate, base,
                                    tvavalue, 0, "", 0d, commentaire, cledos, cleprod, id, cateprod, 2, cleintervenant, 1, "O", typeLignecp, urcleunik, 0, po,
                                    0, 0, 0, 0, gncleunik, 1, achatcp.getDc(),exported);
                        }
                    }
                }
            }
        }
        if (tvaHash != null) {
            for (Enumeration enu = tvaHash.keys(); enu.hasMoreElements();) {
                Tva_abrev_Achat_T tvaAchat = (Tva_abrev_Achat_T) tvaHash.get(enu.nextElement());
                if (tvaAchat.getDc().equals("D")) {
                    tvaAchat.getTva_base().inverse();
                    tvaAchat.getTva_value().inverse();
                }
                check.base3(CheckCompteCentral.TVA_RECUP, journal.getJota_categorie());
                GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), tvaAchat.getTva_value().doubleValue(), tvaAchat.getDc(), null));
                comptains.insert(INSERT_LIGNE, m_config, 1, 0, 0, 0, 0l, Long.parseLong(numpiece), "0000-00-00 00:00:00", comptecentral, tvaAchat.getTva_id(), comptecp, comptes, tvaAchat.getTva_base().doubleValue(), new Double(tvaAchat.getTva_rate().doubleValue()).floatValue(), tvaAchat.getTva_value().doubleValue(),
                        tvaAchat.getTva_value().doubleValue(), 0, "", 0d, "", 0, 0, id, 0, 2, produit.getFrcleunik(), 1, "O", "TVA", urcleunik, 0, "Ligne TVA achat",
                        0, 0, 0, 0, gncleunik, 1, "C",exported);

            }
        }
    }

/*    if(tvaliste!=null){
/* if(produit.getComtpetva()!=null){
Object[] tab=(Object[])produit.getComtpetva().get(new Long(1));
comptecentral=((Integer)tab[0]).intValue();
comptecp=((Integer)tab[1]).intValue();
comptes=m_config.getM_serveur().getCompte(comptecp,new Long(m_config.getCle2()).intValue());

}
check.base3(CheckCompteCentral.TVA_RECUP,journal.getJota_categorie());
// comptes=m_config.getM_serveur().getCompte(GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),new Long(m_config.getCle2()).intValue());

for(int i=0;i<tvaliste.size();i++){
Tva_abrev_T tvaa=(Tva_abrev_T)tvaliste.get(i);
double base=tvaa.getTva_base();
double value=tvaa.getTva_value();
if(journal.getJota_categorie()==ParamComptableInterface.JOURNAL_ACHAT){
base=base*-1;
value=value*-1;
}
GetData.getCompteData(""+m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE,GetData.getCompteData(""+m_config.getM_urcleunik()).getCe_cleunik(),Math.abs(tvaa.getTva_value()),dc,null));
comptains.insert(INSERT_LIGNE,m_config,1,0,0,0,0l,Long.parseLong(numpiece),"0000-00-00 00:00:00",comptecentral,tvaa.getTva_id(),comptecp,comptes,base,tvaa.getTva_rate(),value,
value,0,"",0d,"",0,0,id,0,2,produit.getFrcleunik(),1,"O","TVA",urcleunik,0,"Ligne TVA achat",
0,0,0,0,gncleunik,1,"C");
}


}
//}
}*/
    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {
/*  modify(String requete,Object obj,Configuration m_config,int ce_cleunik_cent,int tva_cleunik,int ce_cleunik2,String ce_cleunik ,double hevaleur ,double hecodetva
,double hevaleurbase ,double hevaleurtva ,int decleunik ,String hedatedevise , double hevaleurdevise ,String helibelle
,int intervenantcleunik ,int cate_cleunik ,int urcleunik
,int hetypepayement,String helibellecompta2 , int pax , int quantite , float pourcent , double hevaleuru , int gn_cleunik) throws SQLException{      */
/*    if(produit.isDeleted()){
new ComptaInseMod().delete(m_config,m_config.getCledossier(),produit.getAt_cleunik(),0,produit.getTypeDeProduitCleunik());
}else{
if(!produit.isIsnewreccord()){
/* new ComptaInseMod().modify(m_config.getCledossier(),produit.getAt_cleunik(),0,UPDATE_LIGNE,produit, m_config,0,codetvavente,codeCompteVente,compte ,-produit.getValeur_tot_tva_inc(),codetva
,-produit.getValeur_tot(),-produit.getMontant_tva() ,0 ,"" , 0 ,produit.getLibelleCompta()
,m_config.getCleintervenant() ,0 , urcleunik
,0,"" , produit.getPax() , produit.getQua() , produit.getPrct() , produit.getAt_val_vente() ,gncleunik) ; */
/*     new ComptaInseMod().modify(m_config.getCledossier(),produit.getAt_cleunik(),0,UPDATE_LIGNE,produit, m_config,codeComteCentral,produit.getGroupdec().getGncodetvavente(),codeCompteVente,compte ,-produit.getValeur_tot_tva_inc(),codetva
,-produit.getValeur_tot(),-produit.getMontant_tva() ,0 ,"" , 0 ,produit.getLibelleCompta()
,m_config.getCleintervenant() ,0 , urcleunik
,0,"" , produit.getPax() , produit.getQua() , produit.getPrct() , produit.getAt_val_vente() ,produit.getGroupdec().getGncleunik()) ;

}
else{
new ComptaInseMod().insert(INSERT_LIGNE,m_config,0,0,0,0,0l,0l,"0000-00-00 00:00:00",codeComteCentral,codetvavente,codeCompteVente,compte,-produit.getValeur_tot_tva_inc(),codetva,-produit.getValeur_tot(),
-produit.getMontant_tva(),0,"",0d,produit.getLibelleCompta(),m_config.getCledossier(),produit.getAt_cleunik(),0,produit.getTypeDeProduitCleunik(),1,m_config.getCleintervenant(),1,"O","D",urcleunik,0,"Ligne de globalisation du dossier",
produit.getPax(),produit.getQua(),produit.getPrct(),produit.getAt_val_vente(),gncleunik,1);

}
}
}*/
    }

 public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 }   
     
     
     
 }
