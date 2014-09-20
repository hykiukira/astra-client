/*
 * CalculTva.java
 *
 * Created on 30 december 2002, 10:47
 */

package srcastra.astra.sys.compta;

/**
 *
 * @author  Thomas
 */
public class CalculTva implements srcastra.astra.sys.classetransfert.dossier.InterfaceProduitConstante{
    
    /** Creates a new instance of CalculTva */
    public CalculTva() {
    }
    public double montantTvaComprise(double prix,int typeProduit){
       switch(typeProduit){
           case HO:
               return procedeCalcule(prix,HOTEL);
           case BRO:
               break;       
       }
        return 0d;        
    }
    private double procedeCalcule(double prix,double split){        
           double split_tva=prix*split;
           double montant_tva=split_tva*TVA;
           double prixTvac=prix+montant_tva;
           long tmp=Math.round(prixTvac*100);
           return (tmp/100);           
       } 
    private static final double HOTEL=0.08d;
    private static final double TVA =0.21d;
    
}
