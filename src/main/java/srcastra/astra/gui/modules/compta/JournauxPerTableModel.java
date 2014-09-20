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

public class JournauxPerTableModel extends javax.swing.table.AbstractTableModel {

    ArrayList data;

    astrainterface m_serveur;

    Loginusers_T m_user;


    /**
     * Creates a new instance of JournauxTableModel
     */

    public JournauxPerTableModel(astrainterface serveur, Loginusers_T user) {

        m_serveur = serveur;

        m_user = user;


    }

    public ArrayList loadData() throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {

        data = m_serveur.renvParamCompta(m_user.getUrcleunik()).getJournauxPeriode(m_user.getUrcleunik());

        return data;


    }

    public void addTableModelListener(javax.swing.event.TableModelListener tableModelListener) {

    }


    public int getColumnCount() {

        return 3;

    }


    public String getColumnName(int param) {

        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", m_user.getLangage()).getString("col" + (param + 4));

    }


    public int getRowCount() {

        if (data != null)

            return data.size();

        else

            return 0;

    }

    public int getId(int param) {

        if (data != null) {

            if (param < 0 || param > data.size())

                return 0;

            Object[] tmp = (Object[]) data.get(param);

            return ((Integer) tmp[2]).intValue();

        } else return 0;


    }

    public boolean alreadyClosed(int param) {

        if (data != null) {

            if (param < 0 || param > data.size())

                return false;

            Object[] tmp = (Object[]) data.get(param);

            if (((Integer) tmp[7]).intValue() == 0)

                return false;

            else return true;

        } else return false;


    }

    public Object getValueAt(int param, int param1) {

        if (param < 0 || param > data.size())

            return "";

        Object[] tmp = (Object[]) data.get(param);

        switch (param1) {

            case 0:

            {
                if (m_user.getUrlmcleunik() == 1)

                    return tmp[0];

                else

                    return tmp[1];

            }

            case 1:

                return tmp[4];

            case 2: {

                if (((Integer) tmp[7]).intValue() == 1)

                    return new Boolean(true);

                else

                    return new Boolean(false);

            }

        }

        return "";

    }

    public boolean isCellEditable(int param, int param1) {

        if (param1 == 2)

            return true;

        else

            return false;

    }


    public void removeTableModelListener(javax.swing.event.TableModelListener tableModelListener) {

    }


    public void setValueAt(Object obj, int param, int param2) {

    }


}

