/*

 * JournauxTableModel.java

 *

 * Created on 26 mai 2003, 14:31

 */


package srcastra.astra.gui.modules.compta;

import java.util.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.sys.classetransfert.Loginusers_T;

/**
 * @author Thomas
 */

public class JournauxTableModel extends javax.swing.table.AbstractTableModel {

    ArrayList data;

    astrainterface m_serveur;

    Loginusers_T m_user;


    /**
     * Creates a new instance of JournauxTableModel
     */

    public JournauxTableModel(astrainterface serveur, Loginusers_T user) {

        m_serveur = serveur;

        m_user = user;


    }

    public ArrayList loadData() throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {

        data = m_serveur.renvParamCompta(m_user.getUrcleunik()).getJournalCompta(m_user.getUrcleunik());

        return data;


    }

    public void addTableModelListener(javax.swing.event.TableModelListener tableModelListener) {

    }


    public int getColumnCount() {

        return 3;

    }


    public String getColumnName(int param) {

        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", m_user.getLangage()).getString("col" + (param + 1));

    }


    public int getRowCount() {

        if (data != null)

            return data.size();

        else

            return 0;

    }

    public Object getObject(int row) {

        return data.get(row);

    }

    public Object getValueAt(int param, int param1) {

        if (param < 0 || param > data.size())

            return "";

        Object[] tmp = (Object[]) data.get(param);

        switch (param1) {

            case 0:

            {
                if (m_user.getUrlmcleunik() == 1)

                    return tmp[1];

                else

                    return tmp[3];

            }

            case 1:

            {

                if (m_user.getUrlmcleunik() == 1)

                    return tmp[2];

                else

                    return tmp[4];

            }

            case 2:
                return tmp[11];

        }

        return "";

    }


    public boolean isCellEditable(int param, int param1) {


        return false;

    }


    public void removeTableModelListener(javax.swing.event.TableModelListener tableModelListener) {

    }


    public void setValueAt(Object obj, int param, int param2) {

    }


}

