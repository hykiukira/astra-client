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


public class LieuEmbarquementTableModel extends ListeTableModel implements ListeModelInterface {


    /**
     * Creates a new instance of TransportTableModel
     */


    public LieuEmbarquementTableModel(astrainterface serveur, Loginusers_T login) {


        super(serveur, login);


        loadata();


    }


    public ArrayList loadata() {


        if (m_vector == null) {


            m_vector = new ArrayList();


            m_vector.add(new Object[]{new Integer(1), "Bruxelles Nationnal", new Integer(1)});


            m_vector.add(new Object[]{new Integer(2), "Gare du Nord :O)", new Integer(3)});


            m_vector.add(new Object[]{new Integer(3), "Gare du Midi", new Integer(2)});


            m_vector.add(new Object[]{new Integer(4), "Place Bockstael", new Integer(4)});


        }


        return m_vector;


    }


    /**
     * Getter for property m_column.
     *
     * @return Value of property m_column.
     */


    public srcastra.astra.gui.test.ColumnData[] getM_column() {


        return this.m_columns;


    }


    /**
     * Setter for property m_column.
     *
     * @param m_column New value of property m_column.
     */


    public void setM_column(srcastra.astra.gui.test.ColumnData[] m_column) {


        this.m_columns = m_column;


    }

    /** Getter for property seveur.





     * @return Value of property seveur.





     */


    /**
     * Getter for property login.
     *
     * @return Value of property login.
     */


    public String getColumnName(int column)


    {


        return m_columns[column].c_title;


    }


    /**
     * Getter for property m_vector_by_key.
     *
     * @return Value of property m_vector_by_key.
     */


    public int getColumnCount() {


        return 1;


    }


    ColumnData[] m_columns = new ColumnData[]{


            new ColumnData("Lieu", 0, JLabel.LEFT)

            /*new ColumnData("Commune",20,JLabel.LEFT)*/};


}