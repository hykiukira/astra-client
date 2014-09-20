/*

 * TvaMain.java

 *

 * Created on 28 avril 2003, 9:12

 */


package srcastra.astra.gui.components.tva;

/**

 *

 * @author thomas

 */

import srcastra.test.*;

import java.util.*;

import javax.swing.*;

import javax.swing.tree.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

import java.awt.event.*;

import srcastra.astra.sys.*;

import srcastra.astra.gui.modules.*;

public class TvaMain {


    /**
     * Creates a new instance of TvaMain
     */

    DefaultMutableTreeNode troot;

    JTree tree;

    javax.swing.JDialog parent2;

    ArrayList tvaliste;

    int i = 0;

    public TvaMain(JTree tree, javax.swing.JDialog parent2, astrainterface serveur, Loginusers_T user, int type, MainScreenModule parent) {

        this.tree = tree;

        this.parent2 = parent2;

        LoadTvaFrame load = new LoadTvaFrame(type, parent);

        if (type == 1) {

            if (user.getUrlmcleunik() == 1) {

                mainEAchat = (TvaComponent) load.tryReadObject(mainEAchat, "astra/tree", load.AEFR);

                mainNAchat = (TvaComponent) load.tryReadObject(mainNAchat, "astra/tree", load.ANFR);

                mainIAchat = (TvaComponent) load.tryReadObject(mainIAchat, "astra/tree", load.AIFR);


            } else if (user.getUrlmcleunik() == 2) {

                mainEAchat = (TvaComponent) load.tryReadObject(mainEAchat, "astra/tree", load.AENL);

                mainNAchat = (TvaComponent) load.tryReadObject(mainNAchat, "astra/tree", load.ANNL);

                mainIAchat = (TvaComponent) load.tryReadObject(mainIAchat, "astra/tree", load.AINL);


            }


        } else {

            if (user.getUrlmcleunik() == 1) {

                mainEAchat = (TvaComponent) load.tryReadObject(mainEAchat, "astra/tree", load.VEFR);

                mainNAchat = (TvaComponent) load.tryReadObject(mainNAchat, "astra/tree", load.VNFR);

                mainIAchat = (TvaComponent) load.tryReadObject(mainIAchat, "astra/tree", load.VIFR);


            } else if (user.getUrlmcleunik() == 2) {

                mainEAchat = (TvaComponent) load.tryReadObject(mainEAchat, "astra/tree", load.VENL);

                mainNAchat = (TvaComponent) load.tryReadObject(mainNAchat, "astra/tree", load.VNNL);

                mainIAchat = (TvaComponent) load.tryReadObject(mainIAchat, "astra/tree", load.VINL);

            }

        }

        if (mainEAchat == null || mainNAchat == null || mainIAchat == null) {

            load.loadTva(serveur, user);

            /* tvaliste=null;

          try{

              tvaliste=serveur.getTvaListe(user.getUrcleunik());

          } catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



          } catch(java.rmi.RemoteException re){



          }



          //System.out.println("time"+System.currentTimeMillis());

          mainNAchat=new  TvaFactory().getTreeAchatNat(tree,parent);

          mainIAchat=new TvaFactory().getTreeAchatIntra();

          mainEAchat=new TvaFactory().getTreeAchatExtra();*

          loadTree(mainNAchat);

          loadTree(mainIAchat);

          loadTree(mainEAchat);

          load.tryWriteObject(mainEAchat,"tree","mainEachat");

          load.tryWriteObject(mainNAchat,"tree","mainNAchat");

          load.tryWriteObject(mainIAchat,"tree", "mainIAchat");

          }

       //   mainNAchat.afficheMe("---");

         // mainIAchat.afficheMe("***");

           long t1=System.currentTimeMillis();*/

            if (type == 1) {

                if (user.getUrlmcleunik() == 1) {

                    mainNAchat = load.getMainNfr();

                    mainIAchat = load.getMainIfr();

                    mainEAchat = load.getMainEfr();

                } else if (user.getUrlmcleunik() == 2) {

                    mainNAchat = load.getMainNnl();

                    mainIAchat = load.getMainInl();

                    mainEAchat = load.getMainEnl();

                }


            } else if (type == 2) {

                if (user.getUrlmcleunik() == 1) {

                    mainNAchat = load.getMainVNfr();

                    mainIAchat = load.getMainVIfr();

                    mainEAchat = load.getMainVEfr();

                } else if (user.getUrlmcleunik() == 2) {

                    mainNAchat = load.getMainVNnl();

                    mainIAchat = load.getMainVInl();

                    mainEAchat = load.getMainVEnl();

                }

            }

        }

        loadGraphicTree();

        //long t2=System.currentTimeMillis();

        //long t3=(t2-t1)/1000;

        // System.out.println("temps = "+t3);

    }

    public void loadTree(TvaComponent node) {

        for (int i = 0; i < tvaliste.size(); i++) {

            Object[] tab = (Object[]) tvaliste.get(i);

            TvaComponent tmp = new TvaComposite();

            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "Valeur de la tva " + tab[2].toString());

            tmp.setTaux(((Float) tab[2]).floatValue());

            tmp.setIdtva(((Integer) tab[0]).intValue());

            tmp.setType(tab[3].toString());

            tmp.setLibelleliste(tab[25] != null ? tab[25].toString() : " ");

            tmp.setLibelle1(tab[26].toString());

            tmp.setLibelle2(tab[27].toString());

            tmp.setLibelle3(tab[28].toString());

            Logger.getDefaultLogger().log(Logger.LOG_INFOS, i + " cleunik first " + tmp.getIdtva() + "  " + tmp.getLibelle1());

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

    public void loadGraphicTree() {

        My_node troot;

        My_node troot2;

        My_node troot3;

        troot = new My_node(mainNAchat);

        troot2 = new My_node(mainIAchat);

        troot3 = new My_node(mainEAchat);

        //  troot3=new My_node(mainEAchat);

        ((TvaFrame) parent2).setNachat(new DefaultTreeModel(troot));

        ((TvaFrame) parent2).setIachat(new DefaultTreeModel(troot2));

        ((TvaFrame) parent2).setEachat(new DefaultTreeModel(troot3));

        //tree=new JTree(model);

        parent2.remove(tree);

        tree.addTreeSelectionListener((javax.swing.event.TreeSelectionListener) parent2);

        tree.addMouseListener(((TvaFrame) parent2).getListener());

        ((TvaFrame) parent2).getJScrollPane2().getViewport().add(tree);

        mainNAchat.addGraphicNode(troot);

        mainIAchat.addGraphicNode(troot2);

        mainEAchat.addGraphicNode(troot3);

        parent2.repaint();

        genereArray(troot, ((TvaFrame) parent2).getAchat(), 0);

        genereArray(troot2, ((TvaFrame) parent2).getAchat(), 1);

        genereArray(troot3, ((TvaFrame) parent2).getAchat(), 2);

    }

    public void genereArray(My_node node, ArrayList array, int i) {

        if (!node.isLeaf()) {

            for (Enumeration e = node.children(); e.hasMoreElements();) {

                My_node n = (My_node) e.nextElement();

                genereArray(n, array, i);

            }

        } else {

            Object test = node.getComponent().getLibelleliste();

            if (test == null)

                test = "xerreur";

            Object[] obj = new Object[]{new Integer(node.getComponent().getIdtva()), test, node.getComponent().getLibelle1(), node.getPath(), new Integer(i), new Float(node.getComponent().getTaux()), new Integer(i)};

            // Logger.getDefaultLogger().log(Logger.LOG_INFOS,"cleunik "+node.getComponent().getIdtva()+"  "+test+"  "+node.getComponent().getLibelle1()+"  "+new TreePath(node.getPath()));

            array.add(obj);

            ((TvaFrame) parent2).getAchathash().put(new Integer(node.getComponent().getIdtva()), obj);

        }

        // Logger.getDefaultLogger().log(Logger.LOG_INFOS,"\n");

    }

    public static void main(String[] args) {

        //new TvaMain(null,null);

    }

/* public void loadTree(TvaComponent top) {

        troot = new DefaultMutableTreeNode(main.getChildren().get("01"));

        tree = new JTree(troot);

        tree.addTreeSelectionListener((javax.swing.event.TreeSelectionListener)parent);

      //  sp.getViewport().add(tree);

        addNodes(troot, topDog);

        tree.expandRow(0);

        parentt.repaint();

    }*/

    /*private void addNodes(DefaultMutableTreeNode pnode, TvaComponent emp) {

        DefaultMutableTreeNode node;

        java.util.TreeMap treemap=emp.getChildren();

        if(treemap!=null){

            java.util.Set set=treemap.keySet();

            java.util.Iterator iterator=set.iterator();

           while(iterator.hasNext())

            {   

                TvaComponent tvacomp = (TvaComponent )iterator.next();

                node = new DefaultMutableTreeNode(newEmp.getName());

                pnode.add(node);

                addNodes(node, newEmp);

            }

        }

    }*/

    /**
     * Getter for property main.
     *
     * @return Value of property main.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMain() {

        return mainNAchat;

    }


    /**
     * Setter for property main.
     *
     * @param main New value of property main.
     */

    public void setMain(srcastra.astra.gui.components.tva.TvaComponent mainNAchat) {

        this.mainNAchat = mainNAchat;

    }

    public class MyMouseListener extends MouseAdapter {

        public void mouseClicked(MouseEvent evt) {

            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "mouse clicked");

            if (evt.getClickCount() == 2) {

                // triple-click


            }

        }

    }

    TvaComponent mainNAchat;

    TvaComponent mainIAchat;

    TvaComponent mainEAchat;

}

