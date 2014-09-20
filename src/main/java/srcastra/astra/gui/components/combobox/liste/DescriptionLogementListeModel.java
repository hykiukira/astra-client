/*

 * DescriptionLogementListeModel.java

 *

 * Created on 20 mars 2003, 16:10

 */


package srcastra.astra.gui.components.combobox.liste;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.MainFrame;

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

import srcastra.astra.gui.modules.aidedesicion.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.sys.comparator.*;

import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

/**
 * @author Thomas
 */

public class DescriptionLogementListeModel extends SupReducListeTableModel {


    /**
     * Creates a new instance of DescriptionLogementListeModel
     */

    boolean sw;

    public DescriptionLogementListeModel(astrainterface serveur, Loginusers_T login, int typeProduit, MainScreenModule parent, int typeDecision, Hashtable table) {

        super(serveur, login, typeProduit, parent, typeDecision, table, ((DossierMainScreenModule) parent).getDossier().getClientContractuel().getLecleunik());


    }

    public ArrayList loadata() {

        try {

            //Object tmpHash=table.get(new Integer(m_typeProduit));

            //if(tmpHash==null || tmpHash.equals("vide")){

            //  m_vector=(ArrayList)m_parent.getMainframe().getSupReducVector().get(new Integer(m_typeProduit));

            //  if(m_vector==null){

            m_vector = new ArrayList();

            DecBuffer dectree = (DecBuffer) m_serveur.workWithDecision(null, m_login.getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, 0l, typeDecision);

            m_vector = (ArrayList) dectree.getArrays(((DossierMainScreenModule) m_parent).getDossier().getClientContractuel().getLecleunik()).get(new Integer(m_typeProduit));

            if (m_vector != null) {

                Object[] tmp = (Object[]) m_vector.get(0);

                if (!tmp[3].toString().equals("***"))

                    m_vector.add(new Object[]{new Integer(-1), new Integer(1), new Integer(1), "***", new Integer(0)});

                sw = true;

                Collections.sort(m_vector, new ListeSelectorComparaTor(3, true));

            }

            return m_vector;

            /*      m_vector=dectree.fillVectorListe(m_vector,m_typeProduit,m_login.getUrlmcleunik());

                    if(m_vector!=null){

                        m_vector.add(new Object[]{new Integer(-1),new Integer(1),new Integer(1),"***",new Integer(0)});

                        Collections.sort(m_vector,new ListeSelectorComparaTor(3,true));

                        table.put(new Integer(m_typeProduit),m_vector);

                    }else

                        table.put(new Integer(m_typeProduit),"vide");

              }

              else{

                m_vector=(ArrayList)table.get(new Integer(m_typeProduit));

              }

                return m_vector;

            */
        }

        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            se.printStackTrace();

        }

        catch (java.rmi.RemoteException re) {

            re.printStackTrace();

        }

        return null;

    }


}

