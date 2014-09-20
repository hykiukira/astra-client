/*
 * DossierPassagerTableModel.java
 *
 * Created on 30 août 2002, 14:21
 */

package srcastra.astra.gui.sys.tableModel.dossierTableModel;

import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import java.util.ArrayList;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.gui.test.*;


/**
 *
 * @author  Sébastien
 */
public class DossierPassagerTableModel extends AbstractAstraTableModel implements DossierTableModelInterface {
    
    /** Creates a new instance of DossierPassagerTableModel */
    public DossierPassagerTableModel(astrainterface serveur, Loginusers_T currentUser,DossierMainScreenModule parent) {
        super(serveur, currentUser);
        this.parent=parent;
    }
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
    protected String[] loadColumnNames() {
      try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/DossierPassagerTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2"), rb.getString("3"), rb.getString("4"), rb.getString("5"), rb.getString("6") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
        return new String[] { "Nom", "Prénom", "Téléphone", "Email", "Langue", "Date de Naissance" };
    }
    
    protected ArrayList loadData() {
        ArrayList data = new ArrayList(0);
        Dossier_T dossier=parent.getDossier();
        Passager_T tmpPassager;
        Passager_T tmpPassager2;
        if(dossier!=null)
        {
           ArrayList passager=dossier.getPassager();
           if(passager!=null)
           { int k=passager.size()-1; 
             int l=passager.size();
             for(int m=0;m<l;m++)
             {
                 for(int j=0;j<k;j++)
                 {
                    tmpPassager=(Passager_T)passager.get(j);
                    tmpPassager2=(Passager_T)passager.get(j+1);
                    if(tmpPassager.getPr_nom().compareTo(tmpPassager2.getPr_nom())>0)
                        {
                            Passager_T tmpPassager3=tmpPassager;
                            passager.set(j,tmpPassager2);
                            passager.set(j+1,tmpPassager);                    
                        }                
                 }
                 l--;
             }
             for(int i=0;i<passager.size();i++)
                {
                    tmpPassager=(Passager_T)passager.get(i);  
                    if(!tmpPassager.isDeleted())
                        data.add(new Object[] {""+tmpPassager.getPr_cleunik(), tmpPassager.getPr_nom(),tmpPassager.getPr_prénom(),tmpPassager.getPr_tel(),tmpPassager.getPr_email(),tmpPassager.getLanguePassager(),tmpPassager.getPr_datenaissance().toString()} );
                }
           }
          // else{
            //   data.add(new Object[] {new Integer(1), "Dussart","Thomas","02/02.02.02","hykiukira@hotmail.com","FR","02/02/2002"} );  
           //}
        }
        
       // else{
            
         //  data.add(new Object[] {new Integer(1), "Dussart","Thomas","02/02.02.02","hykiukira@hotmail.com","FR","02/02/2002"} );   
        //}
        return data;
    }
    
    public java.util.ArrayList getData() {
        return parent.getDossier().getPassager();
    }
    
    public void setData(java.util.ArrayList data) {
    }
    
private DossierMainScreenModule parent;  

}
