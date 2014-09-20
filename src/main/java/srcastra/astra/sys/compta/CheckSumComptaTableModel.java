/*
 * DossierIndexTableModel.java
 *
 * Created on 30 août 2002, 9:49
 */

package srcastra.astra.sys.compta;

import srcastra.astra.gui.sys.tableModel.AbstractVectorTableModel;
import srcastra.astra.gui.sys.tableModel.ColumnData;
import javax.swing.JLabel;
import java.util.Vector;

import java.util.ArrayList;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;

/**
 *
 * @author  Sébastien
 */
public class CheckSumComptaTableModel extends javax.swing.table.AbstractTableModel   {
    
    /** Creates a new instance of DossierIndexTableModel */
    public CheckSumComptaTableModel() {
        super();
        loadColumnNames();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Méthodes Surchargées
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    
    public ColumnData[] loadColumnNames() {
        
 
            col =new ColumnData[] {  new ColumnData("Compte" ,60, JLabel.RIGHT), 
                                       new ColumnData("Débit", 40, JLabel.LEFT), 
                                       new ColumnData("Crédit",40, JLabel.RIGHT),
                                       new ColumnData("Type",60, JLabel.RIGHT),
                                      
            };    
            return col;
    }
    
    /*
    public  ArrayList loadData() {
      ArrayList data = new ArrayList();
      
      }
        return data;
    }*/
    
  
    
    public void setData(java.util.ArrayList data) {
    }
    
    public boolean retrieveData(ArrayList vector) {
        m_vector=vector;
        return true;
       
      
    }
    public Object getValueAt(int param, int param1) {
        if(param<0 || param1>getColumnCount())
            return "";
        Object[] row=(Object[])m_vector.get(param);
        switch(param1)
        {             
            case 0:return row[0];
            case 1:return row[1];
            case 2:return row[2];
            case 3:return row[3];
        }
        return "";
    } 
    
    public int getColumnCount() {
        return col.length;
    }
    public String getColumnName(int column){
     return ((ColumnData)col[column]).getM_title();   
    }
    public int getRowCount() {
        return m_vector.size();
    }
    
   ArrayList m_vector;
   public ColumnData[] col;
}
