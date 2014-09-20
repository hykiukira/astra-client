/*





 * TransportTableModel.java





 *





 * Created on 20 novembre 2002, 14:22





 */


package srcastra.astra.gui.components.combobox.liste;


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


/**
 * @author Thomas
 */


public class NationaliteListeTableModel extends ListeTableModel implements ListeModelInterface {


    /**
     * Creates a new instance of TransportTableModel
     */


    public NationaliteListeTableModel(astrainterface serveur, Loginusers_T login) {


        super(serveur, login);


        loadata();


    }


    public ArrayList loadata() {


        if (m_vector == null) {


            m_vector = new ArrayList();


            try {


                m_vector = m_serveur.renvSignalitiques(m_login.getUrlmcleunik(), m_login.getUrcleunik(), 1, astrainterface.COMBO_NATIONALITE, true);


            }


            catch (RemoteException e) {


                ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, Astra.DEBUG, e, m_login);


            }


            catch (Exception e) {


                ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, Astra.DEBUG, e, m_login);


            }


        }


        return m_vector;


    }


    public srcastra.astra.gui.test.ColumnData[] getM_column() {


        return this.m_columns;


    }


    public void setM_column(srcastra.astra.gui.test.ColumnData[] m_column) {


        this.m_columns = m_column;


    }


    public String getColumnName(int column)


    {


        return m_columns[column].c_title;


    }


    /**
     * Getter for property m_vector_by_key.
     *
     * @return Value of property m_vector_by_key.
     */


    ColumnData[] m_columns = new ColumnData[]{


            new ColumnData("Nationalite", 40, JLabel.LEFT),


            new ColumnData("Abréviation", 20, JLabel.LEFT)};


}