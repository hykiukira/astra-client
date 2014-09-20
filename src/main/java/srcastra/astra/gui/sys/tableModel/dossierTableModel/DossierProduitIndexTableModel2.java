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
import srcastra.astra.sys.classetransfert.dossier.avion.*;
import srcastra.astra.sys.classetransfert.dossier.*;


/**
 *
 * @author  Sébastien
 */
public class DossierProduitIndexTableModel2 extends AbstractAstraTableModel implements DossierTableModelInterface {
    
    /** Creates a new instance of DossierIndexTableModel */
    public DossierProduitIndexTableModel2(astrainterface serveur, Loginusers_T currentUser, DossierMainScreenModule parent) {
        super(serveur, currentUser);
        this.parent=parent;
        this.etat=new ArrayList();
        this.etat.add("OK");
        this.etat.add("RQ");
        this.etat.add("NS");
        this.etat.add("SA");
        this.etat.add("WL"); 
        data=new ArrayList();
        data.add("AV"); 
        data.add("BRO");
        data.add("HO");
        data.add("TAX");
        data.add("BA");
        data.add("VO");
        data.add("TR");
        data.add( "AS");
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Méthodes Surchargées
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public int getColumnCount(){
         dataNeeded();
        int colNbr = 0;
        if (dataContainer.size() > 0) {
            Object[] row = (Object[]) dataContainer.get(0);
            colNbr = row.length-3;
        }
        return colNbr;        
    }
    public Object getValueAt(int row, int column) {
        //Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Getint data at ["+row+":"+column+"]");
        //Thread.currentThread().dumpStack();
        dataNeeded();
        Object[] line = (Object[]) dataContainer.get(row);
        return line[column+3];
    }
    protected int[] getColumnMask() {
        return new int[0];
    }
     public int getRowCount() {
        dataNeeded();
        int rowNbr = 0;
        if (dataContainer.size() > 0) rowNbr = dataContainer.size();
        return rowNbr;
    }
    
    protected String[] loadColumnNames() {
        try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/DossierProduitIndexTableTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2"), rb.getString("3"), rb.getString("4"), rb.getString("5"), rb.getString("6"), rb.getString("7"), rb.getString("8"), rb.getString("9"), rb.getString("10"), rb.getString("11") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
        return new String[] { "test","test","TP", "Fournisseur", "Grp Produits", "TR", "AC", "Libellé", "Prix Unitaire", "QUANT", "Nbre Pax", "CAL. %", "Statut"  };
    }
    
    protected ArrayList loadData() {       
        ArrayList data = new ArrayList();
        // data.add(new Object[] { new Integer(1),"t","t", "B", "Sunsnacks", "Charter", "AV", "HO", "Forfait", "1200,75 €", "1", "1", "100%", "ok" } );
        System.out.println("\n\n\n\nok on est dedans la!bordle de merde");
        //data.add(new Object[] { new Integer(1), "B", "Sunsnacks", "Charter", "AV", "HO", "Forfait", "1200,75 €", "1", "1", "100%", "ok" } );
        String tmp;
        if(parent.getTicket()!=null)
        {
            Avion_ticket_T tmpTicket=parent.getTicket();
            if(parent.getDossier().getAvionTicket()!=null)           
                tmpTicket.setAt_cleunik(parent.getDossier().getAvionTicket().size());
            else 
                tmpTicket.setAt_cleunik(0); 
            data.add(new Object[]{
            new Long(tmpTicket.getAt_cleunik()),
            new Integer(tmpTicket.getTypeDeProduitCleunik()),
            "-1",
            "B",
            tmpTicket.getFrnom(),
            tmpTicket.getGroupe_produit_nom(),
            tmpTicket.getTypeDeProduitNom(),
            "",
            tmpTicket.getAt_memo(),
            new Float(tmpTicket.getAt_val_vente()),
            "1",
            "1",
            "100",
            this.etat.get(tmpTicket.getAt_etat())});
           if(tmpTicket.getSup_reduc()!=null)
            {
                for(int j=0;j<tmpTicket.getSup_reduc().size();j++)
                {
                    Sup_reduc_T tmpsupreduc=(Sup_reduc_T)tmpTicket.getSup_reduc().get("ok");
                    if(tmpsupreduc.getReduc()==1)
                       tmp="-";
                    else 
                       tmp="+";
                    data.add(new Object[]{
                    new Long(tmpTicket.getAt_cleunik()),
                    new Integer(tmpTicket.getTypeDeProduitCleunik()),
                    (tmpsupreduc.getSup_reduc_cleunik()==-1) ? new Long(j): new Long(tmpsupreduc.getSup_reduc_cleunik()),
                    tmp,
                    tmpTicket.getFrnom(),
                    tmpTicket.getGroupe_produit_nom(),
                     tmpTicket.getTypeDeProduitNom(),
                    " ",
                    (tmpsupreduc.getAclibelle()==1)?tmpsupreduc.getFreeLibelle():tmpsupreduc.getFreeLibelle(),
                    new Float(tmpsupreduc.getPrix()),
                    "1",
                    "1",
                    "100",
                    " "});                   
                }
                
                
            }
            //data.add(new Object[] { new Integer(1), "B", "Sunsnacks", "Charter", "AV", "HO", "Forfait", "1200,75 €", "1", "1", "100%", "ok" } );
            //data.add(new Object[] { new Integer(1), "-", "Sunsnacks", "Charter", "AV", "HO", "Vol de Nuit", "50 €", "1", "1", "100%", "ok" } );
            //data.add(new Object[] { new Integer(1), "+", "Sunsnacks", "Charter", "AV", "HO", "Pension complète", "75 €", "4", "1", "100%", "ok" } );
         
        }
        else{
            System.out.println("\n\n\n\npas de ticket dans ce foutu dossier");
        }
        System.out.println("[************Ok] je renvoid data");
       // data.add(new Object[] { new Integer(1),"t","t", "B", "Sunsnacks", "Charter", "AV", "HO", "Forfait", "1200,75 €", "1", "1", "100%", "ok" } );
        //data.remove(0);
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
    DossierMainScreenModule parent;
    ArrayList tmpArray;
    ArrayList etat;
    ArrayList data;
}
