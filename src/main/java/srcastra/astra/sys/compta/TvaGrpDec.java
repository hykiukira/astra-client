/* * TvaGrpDec.java * * Created on 26 f�vrier 2003, 14:32 */package srcastra.astra.sys.compta;import srcastra.astra.sys.classetransfert.dossier.*;/** * * @author  Thomas */public class TvaGrpDec {        /** Creates a new instance of TvaGrpDec */    public TvaGrpDec() {    }    public static double calcul(InterfaceProduit prod){      if(prod.getGroupdec().getGnfrtvaComptabiliserVente()==0){       // prod.getGroupdec().setValeurGenFloat3(prod.getGroupdec().getValeurGenFloat1());       // prod.getGroupdec().setValeurGenFloat1(0f);               srcastra.astra.sys.compta.TvaReturnValue tmp= srcastra.astra.sys.compta.tvaCalcul.TvaReturnValueNoTva(prod);        prod.setValeur_tot(tmp.montantHtva);        prod.setMontant_tva(tmp.montant_Tva);        prod.setValeur_tot_tva_inc(tmp.montant_TvaCompris);        return tmp.montant_TvaCompris;                }      else{          if(prod.getGroupdec().getGntvainclusvente()==0)          {             srcastra.astra.sys.compta.TvaReturnValue tmp= srcastra.astra.sys.compta.tvaCalcul.TvaReturnValue(prod);             prod.setValeur_tot(tmp.montantHtva);             prod.setMontant_tva(tmp.montant_Tva);             prod.setValeur_tot_tva_inc(tmp.montant_TvaCompris);             return tmp.montant_TvaCompris;          }          else           {            srcastra.astra.sys.compta.TvaReturnValue tmp= srcastra.astra.sys.compta.tvaCalcul.TvaReturnValueLess(prod);            prod.setMontant_tva(tmp.montant_Tva);            prod.setValeur_tot_tva_inc(tmp.montant_TvaCompris);            prod.setValeur_tot(tmp.montantHtva);            return prod.getValeur_tot_tva_inc() ;          }      }    }}