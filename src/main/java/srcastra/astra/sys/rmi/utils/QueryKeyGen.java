/*
 * QueryKeyGen.java
 *
 * Created on 12 juillet 2002, 11:06
 */

package srcastra.astra.sys.rmi.utils;

/** QueryGen is a class you can't instance.
 * All the public functions of this class generete signature
 * from the parameters the receive. These signatures are aimed
 * to be used with buffering. For details of each functions,
 * please refer to the function having the same name in 
 * AstraImplement.
 * @author  David
 * @version 0.2 Kobaïa-shimaru / Saikou revision
 */
public class QueryKeyGen {

    /** Creates new QueryKeyGen */
    private QueryKeyGen() {
    }

    public static String renvcombo(char typedecombo,int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas) {
        switch(typedecombo) {
                    case 'p':
                    case 'd':
                    case 'l':
                    case 'c':
                    case 'r':
                    case 't':
                    case 'a':
                    case 'b':
                    case 'e':                        
                        return "renvcombo."
                                +" urlmcleunik: "+urlmcleunik
                                +" type: "+typedecombo
                                +" plettre: "+plettre
                                +" cas: "+cas;
                    case 'L':                        
                        return "renvcombo."
                                +" urlmcleunik: "+urlmcleunik
                                +" type: "+typedecombo
                                +" plettre: "+plettre
                                +" cxcode: "+cxcode
                                +" cas: "+cas;
                    case 'f':                        
                        return "renvcombo."
                                +" urlmcleunik: "+urlmcleunik
                                +" type: "+typedecombo
                                +" cas: "+cas;
                    case 'g':                        
                        return "renvcombo."
                                +" urlmcleunik: "+urlmcleunik
                                +" type: "+typedecombo
                                +" cxcode: "+cxcode
                                +" cas: "+cas;
                    default:
                        return "renvcombo."
                                +" urlmcleunik: "+urlmcleunik
                                +" type: "+typedecombo
                                +" plettre: "+plettre
                                +" cxcode: "+cxcode
                                +" cas: "+cas;
        }
    }
    
    public static String renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique)
    {
        return "renvSignalitique."
                +" urlmcleunik: "+urlmcleunik
                +" type: "+typedesignalitique
                +" cas: "+cas;
    }
    public static String ChargeObjectPopup(int urlmcleunik,int urcleunik,int objcleunik,int cas,int comboConstante)
    {
        return "ChargeObjectPopup."
                +" urlmcleunik: "+urlmcleunik
//                +" objcleunik: "+objcleunik
                +" cas: "+cas
                +" comboConstante: "+comboConstante;
    }    
    public static String  renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik)
    {
        switch(cas)
        {
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_ALL_CLIENTS_ORD_BY_REF:
                return "renvClient. RENV_ALL_CLIENTS_ORD_BY_REF lmcleunik: "+lmcleunik;
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_ALL_CLIENTS_ORD_BY_NAME:  
                return "renvClient. RENV_ALL_CLIENTS_ORD_BY_NAME lmcleunik: "+lmcleunik;
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_ENT_CLIENTS_ORD_BY_REF:  
                return "renvClient. RENV_ENT_CLIENTS_ORD_BY_REF lmcleunik: "+lmcleunik+" entity: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_ENT_CLIENTS_ORD_BY_NAME:  
                return "renvClient. RENV_ENT_CLIENTS_ORD_BY_NAME lmcleunik: "+lmcleunik+" entity: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_CLIENT_MULT_ADR_ORD_BY_TYPE:
                return "renvClient. RENV_CLIENT_MULT_ADR_ORD_BY_TYPE lmcleunik: "+lmcleunik+" client: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_CLIENT_MULT_ADR_ORD_BY_NAME:
                return "renvClient. RENV_CLIENT_MULT_ADR_ORD_BY_NAME lmcleunik: "+lmcleunik+" client: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_CLIENT_FEES_ORDBY_REMISE:
                return "renvClient. RENV_CLIENT_FEES_ORDBY_REMISE client: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_CLIENT_FEES_ORDBY_FEES:
                return "renvClient. RENV_CLIENT_FEES_ORDBY_FEES client: "+listeParam[0];
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS:
                return "renvClient. RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS client: "+lmcleunik;
            case srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient.RENV_GROUPEMENT:
                //return "renvCLient RENV_GROUPEMENT"+listeParam[0];
                return "renvClient RENV_GROUPEMENT";
            default:
                srcastra.astra.sys.Logger.getDefaultLogger().log(srcastra.astra.sys.Logger.LOG_EMERGENCY,"Ne peux générer de signature pour renvClient avec cas= "+cas);
                return null;
        }
    }
    
}
