/*

 * Component.java

 *

 * Created on 25 avril 2003, 15:06

 */


package srcastra.astra.gui.components.tva;

import java.util.*;

import javax.swing.JTree;

import javax.swing.tree.*;

import srcastra.astra.sys.*;

/**
 * @author Thomas
 */

public abstract class TvaComponent implements java.io.Serializable, National, Intra_com, Extra_com, NatVente, Vente_intra, Vente_extra {


    /**
     * Creates a new instance of Component
     */

    public TvaComponent() {

    }

    public abstract void addComponent(TvaComponent c, boolean init);

    public abstract void removeComponent(TvaComponent c);

    public abstract void addGraphicNode(DefaultMutableTreeNode pnode);

    public java.lang.String getType() {

        return type;

    }


    /**
     * Setter for property type.
     *
     * @param type New value of property type.
     */

    public void setType(java.lang.String type) {

        this.type = type;

    }


    /**
     * Getter for property mainListe.
     *
     * @return Value of property mainListe.
     */

    public java.util.TreeMap getChildren() {

        return mainListe;

    }


    /**
     * Setter for property mainListe.
     *
     * @param mainListe New value of property mainListe.
     */

    public void setChildren(java.util.TreeMap mainListe) {

        this.mainListe = mainListe;

    }


    /**
     * Getter for property abrev.
     *
     * @return Value of property abrev.
     */

    public java.lang.String getAbrev() {

        return abrev;

    }


    /**
     * Setter for property abrev.
     *
     * @param abrev New value of property abrev.
     */

    public void setAbrev(java.lang.String abrev) {

        this.abrev = abrev;

    }


    /**
     * Getter for property libelle1.
     *
     * @return Value of property libelle1.
     */

    public java.lang.String getLibelle1() {

        return libelle1;

    }


    /**
     * Setter for property libelle1.
     *
     * @param libelle1 New value of property libelle1.
     */

    public void setLibelle1(java.lang.String libelle1) {

        this.libelle1 = libelle1;

    }


    /**
     * Getter for property lebelle2.
     *
     * @return Value of property lebelle2.
     */

    public java.lang.String getLibelle2() {

        return libelle2;

    }


    /**
     * Setter for property lebelle2.
     *
     * @param lebelle2 New value of property lebelle2.
     */

    public void setLibelle2(java.lang.String libelle2) {

        this.libelle2 = libelle2;

    }


    /**
     * Getter for property leaf.
     *
     * @return Value of property leaf.
     */

    public java.util.TreeMap getLeaf() {

        return leaf;

    }


    /**
     * Setter for property leaf.
     *
     * @param leaf New value of property leaf.
     */

    public void setLeaf(java.util.TreeMap leaf) {

        this.leaf = leaf;

    }

    public void afficheMe(String tmp) {

        Logger.getDefaultLogger().log(Logger.LOG_INFOS, tmp + "  " + getType() + "   " + getLibelle1() + "   " + getLibelle2());

        if (getChildren() != null) {

            java.util.TreeMap treemap = (TreeMap) getChildren();

            if (treemap != null) {

                java.util.Set set = treemap.keySet();

                java.util.Iterator iterator = set.iterator();

                tmp = tmp + "-----";

                while (iterator.hasNext())

                {

                    TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                    child.afficheMe(tmp);

                }

            }

        } else

        {

            if (getLeaf() != null)

            {

                java.util.TreeMap treemap = (TreeMap) getLeaf();

                if (treemap != null) {

                    java.util.Set set = treemap.keySet();

                    java.util.Iterator iterator = set.iterator();

                    tmp = tmp + "-----";

                    while (iterator.hasNext())

                    {

                        TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                        Logger.getDefaultLogger().log(Logger.LOG_INFOS, tmp + "  " + child.getType() + "   " + child.getLibelle1() + "   " + child.getLibelle2());

                    }

                }

            }

        }

    }


    /**
     * Getter for property libelle3.
     *
     * @return Value of property libelle3.
     */

    public java.lang.String getLibelle3() {

        return libelle3;

    }


    /**
     * Setter for property libelle3.
     *
     * @param libelle3 New value of property libelle3.
     */

    public void setLibelle3(java.lang.String libelle3) {

        this.libelle3 = libelle3;

    }


    /**
     * Getter for property idtva.
     *
     * @return Value of property idtva.
     */

    public int getIdtva() {

        return idtva;

    }


    /**
     * Setter for property idtva.
     *
     * @param idtva New value of property idtva.
     */

    public void setIdtva(int idtva) {

        this.idtva = idtva;

    }


    /**
     * Getter for property libelleliste.
     *
     * @return Value of property libelleliste.
     */

    public java.lang.String getLibelleliste() {

        return libelleliste;

    }


    /**
     * Setter for property libelleliste.
     *
     * @param libelleliste New value of property libelleliste.
     */

    public void setLibelleliste(java.lang.String libelleliste) {

        this.libelleliste = libelleliste;

    }


    /**
     * Getter for property taux.
     *
     * @return Value of property taux.
     */

    public float getTaux() {

        return taux;

    }


    /**
     * Setter for property taux.
     *
     * @param taux New value of property taux.
     */

    public void setTaux(float taux) {

        this.taux = taux;

    }


    /**
     * Getter for property tree.
     *
     * @return Value of property tree.
     */


    TreeMap mainListe;

    TreeMap leaf;

    String type;

    protected DefaultMutableTreeNode node;

    String abrev;

    String libelle1;

    String libelle2;

    String libelle3;

    String libelleliste;

    int idtva;

    float taux;

}

