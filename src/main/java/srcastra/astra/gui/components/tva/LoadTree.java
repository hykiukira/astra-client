/*

 * LoadTree.java

 *

 * Created on 7 mai 2003, 9:35

 */


package srcastra.astra.gui.components.tva;

import srcastra.test.*;

import java.util.*;

import javax.swing.*;

import javax.swing.tree.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

import java.awt.event.*;

import srcastra.astra.sys.*;

/**
 * @author Thomas
 */

public class LoadTree {


    /**
     * Creates a new instance of LoadTree
     */

    public LoadTree() {

    }

    public void loadTree(TvaComponent node, ArrayList tvaliste) {

        for (int i = 0; i < tvaliste.size(); i++) {

            Object[] tab = (Object[]) tvaliste.get(i);

            TvaComponent tmp = new TvaComposite();

            tmp.setTaux(Float.parseFloat(tab[2].toString()));

            tmp.setIdtva(((Integer) tab[0]).intValue());

            tmp.setType(tab[3].toString());

            tmp.setLibelleliste(tab[25] != null ? tab[25].toString() : " ");

            tmp.setLibelle1(tab[26].toString());

            tmp.setLibelle2(tab[27].toString());

            tmp.setLibelle3(tab[28].toString());

            //  Logger.getDefaultLogger().log(Logger.LOG_INFOS,i+" cleunik first "+tmp.getIdtva()+"  "+tmp.getLibelle1());

            DefaultMutableTreeNode ne = new DefaultMutableTreeNode();

            //TvaComponent achat=(TvaComponent)main.getChildren().get("0");

            java.util.TreeMap treemap = (TreeMap) node.getChildren();

            if (treemap != null) {

                java.util.Set set = treemap.keySet();

                java.util.Iterator iterator = set.iterator();

                while (iterator.hasNext())

                {

                    TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                    child.addComponent(tmp, false);

                }

            }

        }

        Logger.getDefaultLogger().log(Logger.LOG_INFOS, "\n");

    }


}

