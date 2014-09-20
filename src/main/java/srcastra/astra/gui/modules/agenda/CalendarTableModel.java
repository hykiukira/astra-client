/*
 * CalendarTableModel.java
 *
 * Created on 22 mars 2004, 17:22
 */

package srcastra.astra.gui.modules.agenda;

import java.util.*;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.rmi.Exception.*;

import java.rmi.*;

import srcastra.astra.gui.modules.agenda.Calendar_T;

/**
 * @author Administrateur
 */
public class CalendarTableModel extends javax.swing.table.AbstractTableModel {

    /**
     * Creates a new instance of CalendarTableModel
     */
    ArrayList m_vector;
    astrainterface m_serveur;
    Loginusers_T m_user;
    public String month;

    public CalendarTableModel(astrainterface serveur, Loginusers_T user) {
        m_serveur = serveur;
        m_user = user;
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] tmp = (Object[]) m_vector.get(rowIndex);
        if (tmp[columnIndex] instanceof CalendarDay_T) {
            CalendarDay_T tmpday = (CalendarDay_T) tmp[columnIndex];
            return tmpday;
            //return  new Integer(tmpday.getDay());
        } else {
            return tmp[columnIndex];
        }
        // return "";
    }

    public Object getValue2(int rowIndex, int columnIndex) {
        Object[] tmp = (Object[]) m_vector.get(rowIndex);
        if (tmp[columnIndex] instanceof CalendarDay_T) {
            CalendarDay_T tmpday = (CalendarDay_T) tmp[columnIndex];
            return new Integer(tmpday.getDay());
        } else {
            return tmp[columnIndex];
        }
    }

    public void refresh(srcastra.astra.sys.classetransfert.utils.Date date) {
        try {
            Calendar_T calendar = m_serveur.renvDossierRmiObject(m_user.getUrcleunik()).getCalendar(date, m_user, m_user.getUrcleunik());
            m_vector = calendar.getVector();
            month = calendar.getMonth();
        } catch (ServeurSqlFailure se) {
            se.printStackTrace();

        } catch (RemoteException rn) {
            rn.printStackTrace();
        }
    }

}
