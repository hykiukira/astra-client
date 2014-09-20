/*

 * Payement.java

 *

 * Created on 24 octobre 2003, 11:06

 */



package srcastra.astra.gui.modules.dossier.productSpecification.integration;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.sys.classetransfert.dossier.avion.*;

import java.util.*;

import srcastra.astra.sys.compta.*;

/**

 *

 * @author  Thomas

 */

public class Payement {

    

    /** Creates a new instance of Payement */

    public Payement() {

    }
    
    public static void addPaymentMCO(Avion_ticket_T avion, Dossier_T dossier,int i){
        long cle=Math.abs(i+100)*-10;
        
        double value=avion.getAt_fullfare();
        Payement_T pay=new Payement_T(cle,new srcastra.astra.sys.classetransfert.utils.Date("00-00-0000 00:00:00"),value,25);
        pay.setAvion(avion);
        pay.setNewreccord(true);
        dossier.addPayement2(pay); 
    }

    public static void addPayment(Avion_ticket_T avion, Dossier_T dossier,int i){

        long cle=Math.abs(i+100)*-1;

        double value=avion.getAt_val_vente()-avion.getAt_fullfare();
        
        double val=avion.getAt_fullfare();
        

        if(avion.getSup_reduc()!=null)

            for(Enumeration enu=avion.getSup_reduc().keys();enu.hasMoreElements();){

                Sup_reduc_T supreduc=(Sup_reduc_T)avion.getSup_reduc().get(enu.nextElement());

                if(supreduc.getAclibelle()!=-4){

                    if(supreduc.getReduc()==1)

                        value=value-supreduc.getAt_val_vente();

                    else

                        value=value+supreduc.getAt_val_vente();                    

                }

              

            }
        
            if(val==0)
            {
                if(avion.getAt_tax_compagnie()!=0)
                    value=value-avion.getAt_tax_compagnie();
            
            }
        

          value=MathRound.roundThisToDouble(value);

        Payement_T pay=new Payement_T(cle,new srcastra.astra.sys.classetransfert.utils.Date("00-00-0000 00:00:00"),value,6);

        pay.setAvion(avion);

        pay.setCccf(true);

        dossier.setDr_type_payement(6);

        pay.setNewreccord(true);

        dossier.addPayement2(pay);   

    }

}

