/*
 * PorduitTableModel.java
 *
 * Created on 13 octobre 2002, 12:50
 */

package srcastra.astra.gui.test;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.clients.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import java.util.*;
import java.awt.event.*;
import srcastra.astra.gui.sys.comparator.*;
import javax.swing.event.*;
import srcastra.astra.sys.rmi.*;
import java.rmi.RemoteException;
import srcastra.astra.sys.rmi.Exception.*;
import java.awt.Component;

/**
 *
 * @author  Administrateur
 */
public class ClientTableModel extends AbstractTableModel implements Comparator,InterfaceModel{
    
    /** Creates a new instance of PorduitTableModel */
    public ClientTableModel(astrainterface serveur,Loginusers_T login,Component textfield) {
     // this.parent=parent;
      //loadData();
        m_serveur=serveur;
        m_login=login;
        m_textField=textfield;
    }
    private ColumnData[] m_columns={ 
    new ColumnData("NOM",100,JLabel.LEFT),
    new ColumnData("REFERENCE",100,JLabel.LEFT),
    new ColumnData("ADRESSE",100,JLabel.LEFT),  
    new ColumnData("CP",50,JLabel.LEFT),   
    };
    private ArrayList m_vector;
    public void loadData(ArrayList tmparray,String proposedResult){
        array=null;
        m_vector=new ArrayList();
        if(tmparray!=null){ 
            array=tmparray;
        }
        else{
            try{
               array=(ArrayList)m_serveur.chargeAllclient(m_serveur.CHARGE_CLIENT_ALL_CLIENT,null,m_login.getUrcleunik(),m_login.getUrlmcleunik(),proposedResult,0);
               }catch(ServeurSqlFailure se){
                  se.printStackTrace();                 
               }catch(RemoteException re){
                   re.printStackTrace(); 
                }
        }       
        System.out.println("\n\n[*******]Ok je rentre dans le loadata ProduitTableModel");
        if(array!=null){
            System.out.println("ok l'array n'est pas vide");
            for(int i=0;i<array.size();i++)
            {
              Clients_T cli=(Clients_T)array.get(i);
              ClientAffichage cliaff=new ClientAffichage(cli,i);
              m_vector.add(cliaff);                  
            }           
        }
        else{
            System.out.println("\n\n\n[***************]ARRAY vide");
        }
   // m_vector.addElement(new ProduitAffichage("B","Jetair","Jetait prod","AV","","tout gratuit",1000,1,1,100,"OK",1000));
   // m_vector.addElement(new ProduitAffichage("-","Jetair","Jetait prod","AV","","",-1000,1,1,100,"OK",-1000));
    }
    public String getColumnName(int column)
    {
      String  str=m_columns[column].c_title;  
      if(column==m_sortCol)
          str+= m_sortAsc?" >>":" <<";
          return str;
    }
    public int getColumnCount() {
        return m_columns.length;
    }
    
    public int getRowCount() {
        return m_vector==null ? 0:m_vector.size();
    }
    
    public Object getValueAt(int param, int param1) {
        if(param<0 || param1>getColumnCount())
            return "";
        ClientAffichage row=(ClientAffichage)m_vector.get(param);
        
        switch(param1)
        {
            case 0:return row.m_nom;
            case 1:return row.m_ref;
            case 2:return row.m_adresse;
            case 3:return row.m_cp;
        }
        return "";
    }
    public ProduitAffichage getClassAffichage(int ligne){
       /*  if(m_vector==null)
             return null;
         else{
             ClientAffichage row=(ClientAffichage)m_vector.get(ligne);
             return row;
         }*/return null;
        
    }
     public boolean isCellEditable(int nrow,int ncol){
             return false;
   }
     
    public int compare(Object obj, Object obj1) {
        System.out.println("[COMPARE] column = " + m_sortCol2);
        ClientAffichage cli = (ClientAffichage) obj;
        Object[] tempObj2 = (Object[]) obj1;
        Object comp1=tempObj2[1];
        int compare;
        String cp1=null;
        String cp2=null;
        switch(m_sortCol2)
        {
            case 0:
                cp1=cli.m_nom.m_data.toString();
                cp2=comp1.toString();
                break;
            case 1:
                cp1=cli.m_ref.m_data.toString();
                cp2=comp1.toString();     
                break;
        }        
        int comparaison = (cp1.toUpperCase()).compareTo(cp2.toUpperCase());
        System.out.println("[COMPARE] Objet comparé 1 = " + cp1 + " Objet comparé 2 = " + cp2 + " Nbre de comparaison retourné = " + comparaison);
        return comparaison;
    }
     public int searchAWord(Object word, int column) {
        int position = -1;
        m_sortCol2 = column;
        Object[] array = this.m_vector.toArray();
        Object[] array2 = new Object[getColumnCount()+1];
        for (int i=0; i < array2.length; i++) {
          array2[i] = word;
        }
        position = Arrays.binarySearch(array, array2,this);
       // position = Arrays.binarySearch(array, array2, this);
       // position = Arrays.binarySearch(array, word);
        System.out.println("[SEARCH A WORD] position after binarySearch : " + position);
        if (position < 0) return (position +1) * -1;
        return position;
    }
    public class ColumnListener extends MouseAdapter{
        
        
       protected JTable m_table;
       public ColumnListener(JTable table){
          m_table=table; 
       }
       public void mouseClicked(MouseEvent e){
           TableColumnModel colModel=m_table.getColumnModel();
           int columnIndex=colModel.getColumnIndexAtX(e.getX());
           int modelIndex=colModel.getColumn(columnIndex).getModelIndex();
           if(modelIndex<0)
               return;
           if(m_sortCol==modelIndex)
               m_sortAsc=!m_sortAsc;
           else
               m_sortCol=modelIndex;
           for(int i=0;i<m_columns.length;i++){
               TableColumn column=colModel.getColumn(i);
               column.setHeaderValue(getColumnName(column.getModelIndex()));               
           }
           m_table.getTableHeader().repaint();
           Collections.sort(m_vector,new ClientComparator(modelIndex,m_sortAsc));
           m_table.tableChanged(new TableModelEvent(ClientTableModel.this));
           m_table.repaint();   
            if(m_table.getRowCount()>0)
               m_table.changeSelection(0,0,false,false);
           m_textField.requestFocus();
          
       }
    }
    public void  sortByColonne(int col,JTable m_table){
           TableColumnModel colModel=m_table.getColumnModel();           
           int columnIndex=0;
           int modelIndex=colModel.getColumn(columnIndex).getModelIndex();
           if(modelIndex<0)
               return;
           if(m_sortCol==modelIndex)
               m_sortAsc=true;
           else
               m_sortCol=modelIndex;
           for(int i=0;i<m_columns.length;i++){
               TableColumn column=colModel.getColumn(i);
               column.setHeaderValue(getColumnName(column.getModelIndex()));               
           }
           m_table.getTableHeader().repaint();
           Collections.sort(m_vector,new ClientComparator(modelIndex,m_sortAsc));
           m_table.tableChanged(new TableModelEvent(ClientTableModel.this));
           m_table.repaint(); 
             if(m_table.getRowCount()>0)
               m_table.changeSelection(0,0,false,false);
           m_textField.requestFocus();
         
    }
    
    /** Getter for property array.
     * @return Value of property array.
     */
    public java.util.ArrayList getArray() {
        return array;
    }
    
    /** Getter for property m_sortAsc.
     * @return Value of property m_sortAsc.
     */
    public boolean isM_sortAsc() {
        return m_sortAsc;
    }
    
    /** Setter for property m_sortAsc.
     * @param m_sortAsc New value of property m_sortAsc.
     */
    public void setM_sortAsc(boolean m_sortAsc) {
        this.m_sortAsc = m_sortAsc;
    }
    
    /** Getter for property m_vector.
     * @return Value of property m_vector.
     */
    public java.util.ArrayList getM_vector() {
        return m_vector;
    }
    
    /** Setter for property m_vector.
     * @param m_vector New value of property m_vector.
     */
    public void setM_vector(java.util.ArrayList m_vector) {
        this.m_vector = m_vector;
    }
    
    /** Getter for property m_serveur.
     * @return Value of property m_serveur.
     */
    public srcastra.astra.sys.rmi.astrainterface getM_serveur() {
        return m_serveur;
    }
    
    /** Setter for property m_serveur.
     * @param m_serveur New value of property m_serveur.
     */
    public void setM_serveur(srcastra.astra.sys.rmi.astrainterface m_serveur) {
        this.m_serveur = m_serveur;
    }
    
    /** Getter for property m_login.
     * @return Value of property m_login.
     */
    public srcastra.astra.sys.classetransfert.Loginusers_T getM_login() {
        return m_login;
    }
    
    /** Setter for property m_login.
     * @param m_login New value of property m_login.
     */
    public void setM_login(srcastra.astra.sys.classetransfert.Loginusers_T m_login) {
        this.m_login = m_login;
    }
    
    /** Getter for property m_textField.
     * @return Value of property m_textField.
     */
    public java.awt.Component getM_textField() {
        return m_textField;
    }
    
    /** Setter for property m_textField.
     * @param m_textField New value of property m_textField.
     */
    public void setM_textField(java.awt.Component m_textField) {
        this.m_textField = m_textField;
    }
    
    /** Getter for property m_columns.
     * @return Value of property m_columns.
     */
    public srcastra.astra.gui.test.ColumnData[] getM_columns() {
        return this.m_columns;
    }
    
    /** Setter for property m_columns.
     * @param m_columns New value of property m_columns.
     */
    public void setM_columns(srcastra.astra.gui.test.ColumnData[] m_columns) {
        this.m_columns = m_columns;
    }
    
    public ListeObjectAffichage getClassAffichage2(int ligne) {
         if(m_vector==null)
             return null;
         else{
             ClientAffichage row=(ClientAffichage)m_vector.get(ligne);
             return row;
        
    }
    }
    
    public ArrayList loadData() {
        return null;
    }
    
   // public ClientTableModel.ColumnListener
    
    /** Setter for property array.
     * @param array New value of property array. 
     */
    
protected int m_sortCol=0;
private boolean m_sortAsc=true;
protected int m_sortCol2=0;
DossierMainScreenModule parent;    
astrainterface m_serveur;
Loginusers_T m_login;
private ArrayList array;
Component m_textField;
}
