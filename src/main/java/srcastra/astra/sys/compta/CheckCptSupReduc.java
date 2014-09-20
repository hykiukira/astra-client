/*
 * CheckCptSupReduc.java
 *
 * Created on 6 janvier 2004, 14:45
 */

package srcastra.astra.sys.compta;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.gui.modules.aidedesicion.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.sys.configuration.*;
import srcastra.astra.gui.sys.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.*;

/**
 *
 * @author  Thomas
 */

public class CheckCptSupReduc {
final static int LOCAL=-1;
final static int DEST=-2;
final static int COMP=-3;
final static int FEES=-4;
final static int REM=-5;
final static int FRAIS=-6;
    /** Creates a new instance of CheckCptSupReduc */
    public CheckCptSupReduc() {
    }
    public static void setSupReduc(Sup_reduc_T supreduc,int type,int cle){
         if(type>=0){
            try{
                AbstractBuffer decTree=(AbstractBuffer)srcastra.astra.sys.utils.Utils.getServeur().workWithDecision(null,srcastra.astra.sys.utils.Utils.getUser().getUrcleunik(),ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.SUP_RECUC);
                ArrayList array=BufferEllement.getBufferArray(decTree, type, cle, 0); //récupère l'arraylist avec les info du sup reduc
                if(array!=null){
                   Object[] tmp=(Object[])array.get(0);  
                   int sup;
                   if(tmp[6]!=null){
                        sup=Integer.parseInt(tmp[6].toString());
                        if(sup==1){
                            supreduc.setSup(1);
                            supreduc.setReduc(0);
                        }
                        else{
                            supreduc.setSup(0);
                            supreduc.setReduc(1);
                        }
                   }
                }
            
            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){
            ErreurScreenLibrary.displayErreur(null,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se,srcastra.astra.sys.utils.Utils.getUser());
            }
            catch(java.rmi.RemoteException re){
                ErreurScreenLibrary.displayErreur(null,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re,srcastra.astra.sys.utils.Utils.getUser());
            }
         }
    }
    public static void checkGrpDec(Grpdecision_T child,int type,int cle){
        int ce_cleunik=0;
        int def=0;
        int tva_cleunik=0;
        int comptabilser=0;
        int inclus=0;
        if(type>=0){
            try{
                AbstractBuffer decTree=(AbstractBuffer)srcastra.astra.sys.utils.Utils.getServeur().workWithDecision(null,srcastra.astra.sys.utils.Utils.getUser().getUrcleunik(),ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.SUP_RECUC);
                ArrayList array=BufferEllement.getBufferArray(decTree, type, cle, 0); //récupère l'arraylist avec les info du sup reduc
                if(array!=null){
                   Object[] tmp=(Object[])array.get(0);   
                   if(tmp!=null){
                        if(tmp[7]!=null){ //def : si oui ou non le supreduc prend son propre compte par défault ou utilise celui de la ligne de base
                            def=Integer.parseInt(tmp[7].toString());
                            if(def==1){
                                if(tmp[5]!=null  && tmp[8]!=null){ //tmp5 l'id du comtpe
                                    ce_cleunik=Integer.parseInt(tmp[5].toString());
                                    tva_cleunik=Integer.parseInt(tmp[8].toString());   
                                    comptabilser=Integer.parseInt(tmp[9].toString());   
                                    inclus=Integer.parseInt(tmp[10].toString());  
                                    
                                    //child.setGncomptevente(ce_cleunik);
                                    child.setGntvainclusvente(inclus);
                                    child.setGnfrtvaComptabiliserVente(comptabilser);
                                    child.setGncodetvavente(tva_cleunik);
                                    child.setGncomptevente(ce_cleunik);
                                    child.setValeurGenFloat1(srcastra.astra.sys.compta.TvaVente.getTvaPrct(tva_cleunik));
                                    child.setModifyreccord(true);                          
                                }
                            }
                        }
                   }
                }
            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){
            ErreurScreenLibrary.displayErreur(null,ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se,srcastra.astra.sys.utils.Utils.getUser());
            }
            catch(java.rmi.RemoteException re){
                ErreurScreenLibrary.displayErreur(null,ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re,srcastra.astra.sys.utils.Utils.getUser());
            }
        }
        else{       
            if(type==LOCAL){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxLoc();               
            }
            else if(type==DEST){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxDest();
            }
            else if(type==COMP){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxcomp();
            }
            else if(type==FEES){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxFees();
            }
            else if(type==REM){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteTaxRemise();
            }
            else if(type==FRAIS){
                ce_cleunik=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCompteFrais();
            }
            if(child.getGncomptevente()!=ce_cleunik && ce_cleunik!=0){
                child.setGncomptevente(ce_cleunik);
                child.setModifyreccord(true);
            }
        } 
    }
     public static void checkGrpDecServeur(Grpdecision_T child,int type,astraimplement serveur){
        int ce_cleunik=0;
        if(type==0){
            
        }
        else{       
            if(type==LOCAL){
                ce_cleunik=serveur.getConfig().getCompteTaxLoc();               
            }
            else if(type==DEST){
                ce_cleunik=serveur.getConfig().getCompteTaxDest();
            }
            else if(type==COMP){
                ce_cleunik=serveur.getConfig().getCompteTaxcomp();
            }
            else if(type==FEES){
                ce_cleunik=serveur.getConfig().getCompteTaxFees();
            }
            else if(type==REM){
                ce_cleunik=serveur.getConfig().getCompteTaxRemise();
            }
            else if(type==FRAIS){
                ce_cleunik=serveur.getConfig().getCompteFrais();
            } 
            if(child.getGncomptevente()!=ce_cleunik){
                child.setGncomptevente(ce_cleunik);
                child.setModifyreccord(true);
            }
        } 
    }
    
}
