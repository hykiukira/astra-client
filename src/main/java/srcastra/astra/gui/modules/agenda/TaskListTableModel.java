/*
 * TaskListTableModel.java
 *
 * Created on 18 mars 2004, 11:25
 */

package srcastra.astra.gui.modules.agenda;

import srcastra.astra.sys.rmi.*;

import java.rmi.*;

import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import srcastra.astra.sys.rmi.astrainterface;


import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.ArrayList;

import srcastra.astra.Astra;

import java.rmi.RemoteException;

import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.ListSelectorMask;
import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.test.*;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.ArrayList;

import srcastra.astra.Astra;

import java.rmi.RemoteException;

import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.ListSelectorMask;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.manipuleclient.ClientConstante;
import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.classetransfert.dossier.ProduitAffichage;

import javax.swing.*;
import java.util.*;
import java.awt.Component;
import javax.swing.table.AbstractTableModel;

import srcastra.astra.gui.sys.comparator.ListeSelectorComparaTor2;
import srcastra.astra.gui.sys.tableModel.dossierTableModel.*;

/**
 * @author Administrateur
 */
public class TaskListTableModel extends javax.swing.table.AbstractTableModel {

    /**
     * Creates a new instance of TaskListTableModel
     */
    astrainterface m_serveur;
    Loginusers_T m_user;
    ArrayList m_vector = new ArrayList();
    ArrayList m_vector1;

    public TaskListTableModel(astrainterface serveur, Loginusers_T user) {
        m_serveur = serveur;
        m_user = user;
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        if (m_vector != null)
            return m_vector.size();
        else
            return 0;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Task_T task = (Task_T) m_vector.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return new srcastra.astra.gui.components.combobox.liste.RowColorListe(task.getEeabrev(), 1);
            case 1:
                return new srcastra.astra.gui.components.combobox.liste.RowColorListe(task.getTask_numdos(), 1);
            case 2:
                return new srcastra.astra.gui.components.combobox.liste.RowColorListe(task.getTask_echeance().toString2(), 1);
            case 3:
                return new srcastra.astra.gui.components.combobox.liste.RowColorListe(task.getTask_object(), 1);
            case 4:
                return task.getTask_etat() == 3 ? new Boolean(true) : new Boolean(false);
        }
        return "";
        // task.

    }

    public Task_T getTask(int row) {
        return (Task_T) m_vector.get(row);
    }

    public void refresh(srcastra.astra.sys.classetransfert.utils.Date date) {
        try {

            Comparator DossierComparator = new Comparator() {

                public int compare(Object o1, Object o2) {

                    Task_T t1 = (Task_T) o1;

                    Task_T t2 = (Task_T) o2;


                    return (t1.getTask_numdos().compareTo(t2.getTask_numdos()));
                }
            };


            m_vector1 = m_serveur.renvDossierRmiObject(m_user.getUrcleunik()).getList(date, m_user.getUrcleunik());

            Map taskMap = new HashMap();
            Map taskMap1 = new HashMap();
            m_vector = new ArrayList();

            if (m_vector1 != null) {
                //Collections.sort(m_vector1,DossierComparator);

                String lastDoss = "";

                Task_T tempo = null;


                for (int i = 0; i < m_vector1.size(); i++) {
                    Task_T task = (Task_T) m_vector1.get(i);
                    String message = "";

                    if (task.getDr_cleunik() == -1) {
                        if (taskMap.containsKey(task.getTask_numdos())) {

                            Task_T taskTempo = (Task_T) taskMap.get(task.getTask_numdos());


                            message = taskTempo.getTask_memo() + " /  " + task.getTask_memo();

                            taskTempo.setTask_memo(message);
                            taskTempo.setTask_message(message);
                            taskTempo.setTask_object(message);

                            taskMap.put(task.getTask_numdos(), taskTempo);


                        } else
                            taskMap.put(task.getTask_numdos(), task);
                    } else
                        m_vector.add(task);

                    //System.out.println()
                }

                taskMap = new TreeMap(taskMap);
                taskMap1 = new TreeMap(taskMap1);
                Set lesEntrees = taskMap.entrySet();
                Iterator it = lesEntrees.iterator();

                while (it.hasNext()) {
                    Map.Entry e = (Map.Entry) it.next();
                    m_vector.add(e.getValue());
                }


            }

            // m_vector=taskMap;

            //m_vector=m_vector1;


        } catch (ServeurSqlFailure se) {
            se.printStackTrace();

        } catch (RemoteException rn) {
            rn.printStackTrace();
        }
    }

    public String getColumnName(int column) {
        return m_columns[column].c_title;
    }

    public boolean isCellEditable(int nrow, int ncol) {
        if (ncol == 4)
            return true;
        else
            return false;
    }

    public void setValueAt(Object value, int nRow, int nCol) {
        if (nRow < 0 || nRow >= getRowCount())
            return;
        Task_T task = (Task_T) m_vector.get(nRow);
        String svalue = value.toString();
        switch (nCol) {
            case 4:
                //   if(row.getParent().getTypeDeProduitCleunik()!=produit_T.FRAIS)
                boolean booli = ((Boolean) value).booleanValue();
                if (booli)
                    task.setTask_etat(3);
                break;
        }
    }

    /**
     * Getter for property m_vector.
     *
     * @return Value of property m_vector.
     */
    public java.util.ArrayList getM_vector() {
        return m_vector;
    }

    /**
     * Setter for property m_vector.
     *
     * @param m_vector New value of property m_vector.
     */
    public void setM_vector(java.util.ArrayList m_vector) {
        this.m_vector = m_vector;
    }

    public ColumnData[] m_columns = new ColumnData[]{
            new ColumnData(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("entite"), 10, JLabel.LEFT),
            new ColumnData(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("dossier"), 30, JLabel.LEFT),
            new ColumnData(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("echeance"), 40, JLabel.LEFT),
            new ColumnData(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("obj"), 250, JLabel.LEFT),
            new ColumnData(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("etat"), 5, JLabel.LEFT)};


}
