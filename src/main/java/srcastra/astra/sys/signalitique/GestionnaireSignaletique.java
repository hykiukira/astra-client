/*

 * GestionnaireSignaletique.java

 *

 * Created on 5 février 2003, 11:45

 */



package srcastra.astra.sys.signalitique;

import srcastra.astra.sys.rmi.utils.*;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.sys.compress.*;

import srcastra.astra.sys.Transaction;

/**

 *

 * @author  Thomas

 */

public class GestionnaireSignaletique {

    

    /** Creates a new instance of GestionnaireSignaletique */

    public GestionnaireSignaletique() {

    }

    public static CompressArray renvsignaletique(String requete,int lmcleunik,ServeurBuffer buf,int cas,java.sql.Connection usercon,String signatureSousBuf,String signatureGlobal){

        String tmplangue;

       

        CompressArray cp;
        String signature=QueryKeyGen.renvSignalitiques(lmcleunik, 0, cas, cas );

        if (buf.isValid(signatureSousBuf))
                    cp=buf.getValue(signatureSousBuf);
                else {
                   //sqlrequete="SELECT c.ce_cleunik,c.ce_num,ca.cate_intitule FROM compte c,categorie_compte ca WHERE c.cate_cleunik=ca.cate_cleunik AND ca.lmcleunik="+lmcleunik+" ORDER by c.ce_num;";
                    cp=Transaction.generecombostest3(requete,usercon);
                    buf.setValue(signatureSousBuf,cp);
                    buf.linkNewName(signature,signatureGlobal);
                    buf.setValue(signature, new Long(System.currentTimeMillis()));                   
                }  
         return cp;

    }

   

}

