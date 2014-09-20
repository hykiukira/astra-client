/*

 * DecisionScreenManager.java

 *

 * Created on 6 mars 2003, 11:11

 */


package srcastra.astra.gui.modules.aidedesicion;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.sys.*;

import java.util.*;

import javax.swing.*;

import javax.swing.text.*;

import srcastra.astra.gui.modules.config.AbstractConfig;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.sys.configuration.*;

import srcastra.astra.gui.sys.*;

/**
 * @author Thomas
 */

public class DecisionScreenManager extends AbstractDecisionScreenManager {


    /**
     * Creates a new instance of DecisionScreenManager
     */

    AbstractConfig m_parent;

    DossierMainScreenModule parent;

    srcastra.astra.gui.modules.aidedesicion.MyTableModel model;

    JTable generiqueTable;

    ActionToolBar m_actionToolBar;

    Hashtable mainHash;

    int idEcran;

    int typDecision;

    public DecisionScreenManager(AbstractConfig parent1, DossierMainScreenModule parent, srcastra.astra.gui.modules.aidedesicion.MyTableModel model, JTable generiqueTable, ActionToolBar m_actionToolBar, Hashtable mainHash, int typDecision) {

        this.model = model;

        m_parent = parent1;

        this.parent = parent;

        this.generiqueTable = generiqueTable;

        this.m_actionToolBar = m_actionToolBar;

        this.mainHash = mainHash;

        this.idEcran = idEcran;

        this.typDecision = typDecision;


    }


    public void doPrevious() {

        try {

            if (m_parent.mode == ActionToolBar.ACT_INSERT) {

                if (!checkLangue()) {

                    new MessageManager().showMessageDialog(m_parent.getMe(), "aide_dec", "aide_dec_titre", parent.getCurrentUser());

                    ((java.awt.Component) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

                } else {

                    ArrayList tmp = genereUneEntree();

                    if (tmp == null) {

                        new MessageManager().showConfirmDialog(m_parent.getMe(), "supdef", "supdef_title", parent.getCurrentUser());

                        return;

                    }

                    Object id = parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_INSERT, tmp, 0, m_parent.typeProduit, 0l, typDecision);

                    addData(tmp, ((Insert_T) id).getM_id(), ((Insert_T) id).getM_timestamp());

                    ((DecBuffer) parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, 0l, typDecision)).generateTable(m_parent.typeProduit);

                    // m_parent.getAbstractBuffer().afficheMe();

                    // attention rajouter le reload

                    reloadTable();// attention rajouter le displayreadmode

                    invalideVector();

                    displayReadMode();

                }

            } else if (m_parent.mode == ActionToolBar.ACT_READ) {

                // attention rajouter le displayreadmode

                if (multiproduct)

                    selectComponent(true);

            } else if (m_parent.mode == ActionToolBar.ACT_MODIFY) {

                if (!checkLangue()) {

                    new MessageManager().showMessageDialog(m_parent.getMe(), "aide_dec", "aide_dec_titre", parent.getCurrentUser());

                    ((java.awt.Component) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

                } else {

                    ArrayList tmp = genereUneEntree();

                    if (tmp == null) {

                        new MessageManager().showConfirmDialog(m_parent.getMe(), "supdef", "supdef_title", parent.getCurrentUser());

                        return;

                    }

                    Object id = parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_MODIFY, tmp, 0, m_parent.typeProduit, 0l, typDecision);

                    addData(tmp, m_parent.idToModiFy, ((Insert_T) id).getM_timestamp());

                    ((DecBuffer) parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, 0l, typDecision)).generateTable(m_parent.typeProduit);

                    // attention rajouter le displayreadmode

                    reloadTable();

                    invalideVector();

                    // attention rajouter le displayreadmode

                    displayReadMode();

                }

            }

            System.out.println("wazzzzzzzaaaaaaaaaaaaa\n");

            m_parent.getAbstractBuffer().afficheMe();

            m_parent.getMe().requestFocus();

        }

        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            se.printStackTrace();

        }

        catch (java.rmi.RemoteException re) {

            re.printStackTrace();

        }


    }

    private void invalideVector() {

        // if(idEcran==0)

        //      parent.getMainframe().getSupReducVector().put(new Integer(m_parent.typeProduit),"vide");

        // else if(idEcran==0)

        //      parent.getMainframe().getMemoVector().put(new Integer(m_parent.typeProduit),"vide");

        //  mainHash.put(new Integer(m_parent.typeProduit),"vide");


    }

    public ArrayList genereUneEntree() {

        Object[] tmp = null;

        String tmps = "";

        AstraComponent tmpText = (AstraComponent) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()));


        ArrayList data = new ArrayList();

        if (tmpText.getText2() == null || tmpText.getText2() == "")

            return null;

        else

            tmps = tmpText.getText2();

        if (typDecision == AbstractRequete.SUP_RECUC) {

            Integer compte = new Integer(((srcastra.astra.gui.components.combobox.liste.Liste) m_parent.getHashCompLangue().get(new Integer(6))).getCleUnik());

            Integer sup = ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getHashCompLangue().get(new Integer(7))).isSelected() ? new Integer(1) : new Integer(0);

            Integer def = ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getHashCompLangue().get(new Integer(8))).isSelected() ? new Integer(1) : new Integer(0);

            Integer tva = new Integer(((srcastra.astra.gui.components.combobox.liste.Liste) m_parent.getHashCompLangue().get(new Integer(9))).getCleUnik());

            Integer compta = ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getHashCompLangue().get(new Integer(10))).isSelected() ? new Integer(1) : new Integer(0);

            Integer compris = ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getHashCompLangue().get(new Integer(11))).isSelected() ? new Integer(1) : new Integer(0);

            if (def.intValue() == 1 && (compte.intValue() == 0 || tva.intValue() == 0))

                return null;

            for (int i = 0; i < m_parent.getHashCompLangue2().size(); i++)

            {

                tmp = new Object[11];

                Object[] tmpligne = (Object[]) m_parent.getHashCompLangue2().get(i);

                AstraComponent jr = (AstraComponent) tmpligne[0];

                if (m_parent.mode == ActionToolBar.ACT_INSERT)

                    tmp[0] = new Integer(m_parent.tmpId);

                else if (m_parent.mode == ActionToolBar.ACT_MODIFY)

                    tmp[0] = new Integer(m_parent.idToModiFy);

                tmp[1] = new Integer(m_parent.typeProduit);

                tmp[2] = (Integer) tmpligne[1];

                String s = jr.getText2();

                if (s == null || s.equals(""))

                    s = tmps;

                tmp[3] = s;

                tmp[4] = new Integer(0);

                tmp[5] = compte;

                tmp[6] = sup;

                tmp[7] = def;

                tmp[8] = tva;

                tmp[9] = compta;

                tmp[10] = compris;

                data.add(tmp);

            }


        } else {

            for (int i = 0; i < m_parent.getHashCompLangue2().size(); i++)

            {

                tmp = new Object[5];

                Object[] tmpligne = (Object[]) m_parent.getHashCompLangue2().get(i);

                AstraComponent jr = (AstraComponent) tmpligne[0];

                if (m_parent.mode == ActionToolBar.ACT_INSERT)

                    tmp[0] = new Integer(m_parent.tmpId);

                else if (m_parent.mode == ActionToolBar.ACT_MODIFY)

                    tmp[0] = new Integer(m_parent.idToModiFy);

                tmp[1] = new Integer(m_parent.typeProduit);

                tmp[2] = (Integer) tmpligne[1];

                String s = jr.getText2();

                if (s == null || s.equals(""))

                    s = tmps;

                tmp[3] = s;

                tmp[4] = new Integer(0);

                data.add(tmp);

            }

        }

        return data;

    }

    public void addData(ArrayList entree) {

        if (m_parent.mode == ActionToolBar.ACT_INSERT) {

            m_parent.getAbstractBuffer().setData(entree, m_parent.tmpId, m_parent.typeProduit);

            m_parent.tmpId = m_parent.tmpId - 1;

        } else

            m_parent.getAbstractBuffer().setData(entree, m_parent.idToModiFy, m_parent.typeProduit);

    }


    public void addData(ArrayList entree, int id, long timestamp) {

        if (m_parent.mode == ActionToolBar.ACT_INSERT) {

            m_parent.getAbstractBuffer().setData(entree, id, m_parent.typeProduit, timestamp);

            m_parent.tmpId = m_parent.tmpId - 1;

        } else

            m_parent.getAbstractBuffer().setData(entree, m_parent.idToModiFy, m_parent.typeProduit);

    }

    public boolean checkLangue() {

        AstraComponent tmpText = (AstraComponent) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()));

        if (tmpText.getText2() != null && !tmpText.getText2().equals(""))

            return true;

        else return false;

    }

    //a arranger

    public void displayInsertMode() {

        for (int i = 0; i < m_parent.getComponantToVerif().length; i++)

        {

            if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.combobox.liste.Liste) {

                ((srcastra.astra.gui.components.combobox.liste.Liste) m_parent.getComponantToVerif()[i]).setCleUnik(0);

                m_parent.getComponantToVerif()[i].setEnabled(true);

            } else if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.checkbox.ACheckBox) {

                ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getComponantToVerif()[i]).setSelected(false);

                m_parent.getComponantToVerif()[i].setEnabled(true);

            } else {

                m_parent.getComponantToVerif()[i].setText("");

                m_parent.getComponantToVerif()[i].setEnabled(true);

            }

        }

        m_parent.mode = ActionToolBar.ACT_INSERT;

        getDefaultActionToolBarMask();

        m_parent.enableComponent(false);

        ((java.awt.Component) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();


    }

    public void displayReadMode() {

        if (generiqueTable.getRowCount() > 0) {

            Object[] tmp = model.getObject(generiqueTable.getSelectedRow());

            int id = ((Integer) tmp[5]).intValue();

            affiche(id, false);

        } else {

            for (int i = 0; i < m_parent.getComponantToVerif().length; i++)

            {

                m_parent.getComponantToVerif()[i].setEnabled(false);

            }

        }

        m_parent.mode = ActionToolBar.ACT_READ;

        m_parent.enableComponent(true);

        getDefaultActionToolBarMask();

        m_parent.getMe().requestFocus();

        /*for(int i=0;i<m_parent.getComponantToVerif().length;i++)

        {

         m_parent.getComponantToVerif()[i].setEnabled(false);

        }



        getDefaultActionToolBarMask();

        m_parent.enableComponent(true);

        if(generiqueTable.getRowCount()>0){

        Object[] tmp=model.getObject(generiqueTable.getSelectedRow());

            for(int i=0;i<m_parent.getComponantToVerif().length;i++)

             {

                    m_parent.getComponantToVerif()[i].setText(tmp[i].toString());

             }

        }

        m_parent.getMe().requestFocus();*/

    }

    public void doCancel() {

        if (m_parent.mode == ActionToolBar.ACT_INSERT || m_parent.mode == ActionToolBar.ACT_MODIFY) {

            // attention rajouter le displayreadmode

            displayReadMode();

            return;

        }

    }

    public void doClose() {

        System.out.println("close operation");

        parent.cancelModule();


    }

    public void doCreate() {

        displayInsertMode();


    }

    public void doDelete() {

        if (typDecision != AbstractRequete.IMPRESSION) {

            Object[] tmp = model.getObject(generiqueTable.getSelectedRow());

            m_parent.idToDelete = ((Integer) tmp[5]).intValue();

            try {

                Object id = parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.DO_DELETE, null, m_parent.idToDelete, m_parent.typeProduit, 0, typDecision);

                if (id instanceof Boolean)

                {

                    new MessageManager().showMessageDialog(m_parent.getMe(), "decision_texte", "decision_titre", parent.getCurrentUser());

                } else {

                    //  m_parent.getAbstractBuffer().afficheMe();

                    m_parent.getAbstractBuffer().removeChildren(new Integer(((Insert_T) id).getM_id()).toString(), m_parent.typeProduit);

                    m_parent.getAbstractBuffer().setTimestamp(((Insert_T) id).getM_timestamp());

                    ((DecBuffer) parent.getServeur().workWithDecision(null, parent.getCurrentUser().getUrcleunik(), ActionToolBar.ACT_READ, null, 0, 0, 0l, typDecision)).generateTable(m_parent.typeProduit);

                    reloadTable();

                    displayReadMode();

                }

            }

            catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

                se.printStackTrace();

            }

            catch (java.rmi.RemoteException re) {

                re.printStackTrace();

            }

        }

    }

    public void reloadTable() {

        model.generiqueInt = m_parent.typeProduit;

        model.loadData();

        generiqueTable.tableChanged(new javax.swing.event.TableModelEvent(model));

        if (generiqueTable.getRowCount() > 0)

            generiqueTable.changeSelection(0, 0, false, false);


    }

    public int[] getDefaultActionToolBarMask() {

        int[] tmp = null;

        if (m_parent.mode == ActionToolBar.ACT_READ) {

            if (generiqueTable.getRowCount() != 0) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };

                m_actionToolBar.setActionEnabled(tmp);

            } else {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_CLOSE,

                };

                m_actionToolBar.setActionEnabled(tmp);

            }

        } else if (m_parent.mode == ActionToolBar.ACT_INSERT) {

            tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_CANCEL,

            };

            m_actionToolBar.setActionEnabled(tmp);

        } else if (m_parent.mode == ActionToolBar.ACT_MODIFY) {

            tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_CANCEL,

            };

            m_actionToolBar.setActionEnabled(tmp);

        }

        m_parent.getMe().requestFocus();

        return tmp;

    }

    public JRadioButton getSelectComponent() {


        for (Enumeration e = m_parent.getButtonGroup().getElements(); e.hasMoreElements();) {

            JRadioButton b = (JRadioButton) e.nextElement();

            if (b.getModel() == m_parent.getButtonGroup().getSelection()) {

                return b;

            }

        }

        return null;

    }

    public void selectComponent(boolean after) {

        JRadioButton b = getSelectComponent();

        if (after) {

            Object[] tmp = (Object[]) m_parent.getCompAfter().get(b.getName());

            ((JRadioButton) tmp[0]).setSelected(true);

            m_parent.typeProduit = ((Integer) tmp[1]).intValue();

        } else {

            Object[] tmp = (Object[]) m_parent.getCompBefore().get(b.getName());

            ((JRadioButton) tmp[0]).setSelected(true);

            m_parent.typeProduit = ((Integer) tmp[1]).intValue();
        }

        reloadTable();

        getDefaultActionToolBarMask();

        m_parent.getMe().requestFocus();

    }


    /**
     * Getter for property multiproduct.
     *
     * @return Value of property multiproduct.
     */

    public boolean isMultiproduct() {

        return multiproduct;

    }


    /**
     * Setter for property multiproduct.
     *
     * @param multiproduct New value of property multiproduct.
     */

    public void setMultiproduct(boolean multiproduct) {

        this.multiproduct = multiproduct;

    }

    public void affiche(int id, boolean open) {

        Object[] filltab = new Object[12];

        int k = 0;

        ArrayList tmp = BufferEllement.getBufferArray(m_parent.getAbstractBuffer(), m_parent.typeProduit, id, parent.getCurrentUser().getUrlmcleunik());

        if (tmp != null) {

            for (int i = 0; i < tmp.size(); i++) {

                Object[] tab = (Object[]) tmp.get(i);

                if (tab != null) {

                    for (int j = 0; j < tab.length; j++) {

                        if (tab[j] != null) {

                            if (j == 3) {

                                filltab[k] = tab[j];

                            }

                            if (j == 5) {

                                filltab[5] = tab[j];

                            }

                            if (j == 6) {

                                if (Integer.parseInt(tab[j].toString()) == 1) {

                                    filltab[6] = new Integer(1);

                                    filltab[7] = new Integer(0);

                                } else

                                {

                                    filltab[6] = new Integer(0);

                                    filltab[7] = new Integer(1);

                                }

                            }

                            if (j == 7) {

                                filltab[8] = tab[j];

                            }

                            if (j == 8) {

                                filltab[9] = tab[j];

                            }

                            if (j == 9) {

                                filltab[10] = tab[j];

                            }

                            if (j == 10) {

                                filltab[11] = tab[j];

                            }

                        } else System.out.println("tab[j] null");

                    }

                } else System.out.println("tab null");

                k++;

            }

        } else System.out.println("array null");

        for (int i = 0; i < m_parent.getComponantToVerif().length; i++)

        {

            if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.textFields.ATextField || m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.sys.utils.JTextAreaAdapter) {

                if (filltab[i] != null) {

                    m_parent.getComponantToVerif()[i].setText(filltab[i].toString());

                } else {

                    m_parent.getComponantToVerif()[i].setText(" ");

                }

                m_parent.getComponantToVerif()[i].setEnabled(open);

            } else
            if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.combobox.liste.Liste) {

                ((srcastra.astra.gui.components.combobox.liste.Liste) m_parent.getComponantToVerif()[i]).setCleUnik(Integer.parseInt(filltab[i].toString()));

                m_parent.getComponantToVerif()[i].setEnabled(open);

            } else if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.checkbox.ACheckBox) {

                ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getComponantToVerif()[i]).setSelected(Integer.parseInt(filltab[i].toString()) == 1 ? true : false);

                m_parent.getComponantToVerif()[i].setEnabled(open);

            } else if (m_parent.getComponantToVerif()[i] instanceof srcastra.astra.gui.components.checkbox.ACheckBox) {

                ((srcastra.astra.gui.components.checkbox.ACheckBox) m_parent.getComponantToVerif()[i]).setSelected(Integer.parseInt(filltab[i].toString()) == 1 ? true : false);

                m_parent.getComponantToVerif()[i].setEnabled(open);

            }

        }

    }

    public void displayUpdateModeSupReduc(int id) {

        System.out.println("id " + m_parent.idToModiFy);

        affiche(id, true);

        m_parent.mode = ActionToolBar.ACT_MODIFY;

        getDefaultActionToolBarMask();

        m_parent.enableComponent(false);

        ((java.awt.Component) m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

    }

    public void displayUpdateMode() {

        //  if(typDecision==AbstractRequete.SUP_RECUC){

        Object[] tmp = model.getObject(generiqueTable.getSelectedRow());

        m_parent.idToModiFy = ((Integer) tmp[5]).intValue();

        displayUpdateModeSupReduc(m_parent.idToModiFy);

        /*}else{

          Object[] tmp=model.getObject(generiqueTable.getSelectedRow());

          for(int i=0;i<m_parent.getComponantToVerif().length;i++)

          {

              m_parent.getComponantToVerif()[i].setText(tmp[i].toString());

              m_parent.getComponantToVerif()[i].setEnabled(true);

          }

          m_parent.idToModiFy=((Integer)tmp[5]).intValue();

          m_parent.mode=ActionToolBar.ACT_MODIFY;

          getDefaultActionToolBarMask();

          m_parent.enableComponent(false);

         ((java.awt.Component)m_parent.getHashCompLangue().get(new Integer(parent.getCurrentUser().getUrlmcleunik()))).requestFocus();

        }*/


    }

    boolean multiproduct;

}

