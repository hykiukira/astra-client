/*
 * DossierIndexTableModel.java
 *
 * Created on 30 août 2002, 9:49
 */

package srcastra.astra.gui.sys.tableModel.dossierTableModel;

import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import java.util.ArrayList;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;
import srcastra.astra.sys.rmi.DossierRmiInterface;

/**
 *
 * @author  Sébastien
 */
public class DossierSupReducTableModel extends AbstractAstraTableModel implements DossierTableModelInterface {
    
    /** Creates a new instance of DossierIndexTableModel */
    public DossierSupReducTableModel(astrainterface serveur, Loginusers_T currentUser,DossierMainScreenModule parent) {
        super(serveur, currentUser);
        this.parent=parent;
    }
     public DossierSupReducTableModel() {
         super(null,null);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Méthodes Surchargées
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
    protected String[] loadColumnNames() {
        /* try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/DossierIndexTableTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2"), rb.getString("3"), rb.getString("4"), rb.getString("5"), rb.getString("6"), rb.getString("7"), rb.getString("8"), rb.getString("9") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
         */
        return new String[] {"Type","Libélé","Prix" };
    }
    
    protected ArrayList loadData() {
        ArrayList data = new ArrayList(0);
        // data.add(new Object[] { "1", "+", "lkjkljlj","400" });
        try {
        if(parent.getTicket()!=null)
        {
            //tmpArray=parent.getTicket().getSup_reduc();
            if(tmpArray!=null)
            {
                for(int i=0;i<tmpArray.size();i++)
                {
                    Sup_reduc_T tmpSupReduc=(Sup_reduc_T)tmpArray.get(i);
                    if(tmpSupReduc.getReduc()==1)
                        data.add(new Object[] { "2","-", tmpSupReduc.getFreeLibelle(), new Float(tmpSupReduc.getPrix()) }); 
                    else
                        data.add(new Object[] { "3","+", tmpSupReduc.getFreeLibelle(), new Float(tmpSupReduc.getPrix()) }); 
                }               
            }           
        }
        }catch (Exception e) { e.printStackTrace(); }
        return data;
    }
    
    /** Getter for property data.
     * @return Value of property data.
     */
    public java.util.ArrayList getData() {
        return this.tmpArray;
    }
    
    /** Setter for property data.
     * @param data New value of property data.
     */
    public void setData(java.util.ArrayList data) {
        this.tmpArray = tmpArray;
    }
    
//private ArrayList data;
private ArrayList tmpArray;
private DossierMainScreenModule parent;    
private DossierRmiInterface serveurDossier;
}
