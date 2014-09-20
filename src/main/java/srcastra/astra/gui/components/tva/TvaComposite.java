/*

 * MainTree.java

 *

 * Created on 25 avril 2003, 15:31

 */


package srcastra.astra.gui.components.tva;

import java.util.*;

import javax.swing.JTree;

import javax.swing.tree.*;

import srcastra.astra.sys.*;

import java.awt.event.*;


/**
 * @author Thomas
 */

public class TvaComposite extends TvaComponent {


    /**
     * Creates a new instance of MainTree
     */

    public TvaComposite() {

        //mainListe=new TreeSet();

    }

    public TvaComposite(JTree tree) {

        // mainListe=new TreeSet();

    }

    public TvaComposite(String type) {

        this.type = type;

        setLibelle1(type);

        //mainListe=new TreeSet();

    }

    public TvaComposite(String type, DefaultMutableTreeNode tree) {


        this.type = type;

        //mainListe=new TreeSet();

    }


    public void addComponent(TvaComponent c, boolean init) {

        int l = 0;

        // My_node p2node=null;

        if (init) {

            if (getChildren() == null)

                setChildren(new TreeMap());

            //   this.node=pnode;

            //   p2node=new DefaultMutableTreeNode(c.getLibelle1());

            // c.node=p2node;

            // this.node.add(p2node);

            getChildren().put(c.getType(), c);

        } else {

            // Logger.getDefaultLogger().log(Logger.LOG_INFOS,"this.type "+getType()+"  c.type"+c.getType());

            if (c.getType().equals(getType())) {

                this.setAbrev(c.getAbrev());

                this.setLibelle1(c.getLibelle1());

                this.setLibelle2(c.getLibelle2());

                this.setLibelle3(c.getLibelle3());

                this.setLibelleliste(c.getLibelleliste());

                this.setIdtva(c.getIdtva());

                // this.=c;

            } else if (getType().equals(c.getType().substring(0, getType().length())))

                if (getChildren() != null) {

                    java.util.TreeMap treemap = (TreeMap) getChildren();

                    if (treemap != null) {

                        java.util.Set set = treemap.keySet();

                        java.util.Iterator iterator = set.iterator();

                        while (iterator.hasNext())

                        {

                            TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                            l = child.getType().length();

                            break;

                        }

                        TvaComponent prov = (TvaComponent) getChildren().get(c.getType().substring(0, l));

                        if (prov != null) {

                            //     p2node=new DefaultMutableTreeNode(c.getLibelle1());

                            //   this.node.add(p2node);

                            // c.node=p2node;

                            prov.addComponent(c, false);

                        }

                    }

                } else

                {

                    if (getLeaf() == null)

                        setLeaf(new java.util.TreeMap());

                    getLeaf().put(c.getType(), c);


                }

        }

    }

    public void addGraphicNode(DefaultMutableTreeNode pnode) {

        if (getChildren() != null) {

            java.util.TreeMap treemap = (TreeMap) getChildren();

            if (treemap != null) {

                java.util.Set set = treemap.keySet();

                java.util.Iterator iterator = set.iterator();

                while (iterator.hasNext())

                {

                    TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                    My_node pnode2 = new My_node(child);

                    pnode.add(pnode2);

                    child.addGraphicNode(pnode2);


                }

            }

        } else if (getLeaf() != null) {

            java.util.TreeMap treemap = (TreeMap) getLeaf();

            if (treemap != null) {

                java.util.Set set = treemap.keySet();

                java.util.Iterator iterator = set.iterator();

                while (iterator.hasNext())

                {

                    TvaComponent child = (TvaComponent) treemap.get(iterator.next());

                    My_node pnode2 = new My_node(child);

                    pnode.add(pnode2);

                }

            }

        }

    }

    public void removeComponent(TvaComponent c) {

        mainListe.remove(c);

    }


}

