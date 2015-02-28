/*

 * JGoodiesFactory.java

 *

 * Created on 6 novembre 2003, 9:43

 */


package srcastra.astra.gui.list;

import com.jgoodies.forms.layout.*;

import com.jgoodies.forms.builder.*;

import javax.swing.*;

import javafx.scene.layout.HBox;
import srcastra.astra.gui.components.date.thedate.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.components.checkbox.*;

import javax.swing.JComboBox.*;


import srcastra.astra.gui.components.combobox.liste.*;

import java.awt.event.*;

import java.util.*;

import javax.swing.border.*;

import java.awt.*;

import srcastra.astra.sys.classetransfert.*;

/**
 * @author Thomas
 */


public class JGoodiesFactory {

    public static final int DOSSIER_CHOICE = 1;

    public static final int DOSSIER = 2;

    public static final int MAINPANEL = 3;

    public static final int CAISSE = 4;

    public static final int CONTROLE = 5;

    public static final int FOURN = 6;

    public static final int JOURN = 7;

    public static final int LFOURN = 8;

    public static final int ACHAT = 9;

    public static final int CHA = 10;

    public static final int CHAF = 11;

    public static final int DEBEMB = 12;


    ArrayList panel;

    ListingFrame frame;

    Loginusers_T currentUser;


    public static final String ROW = "left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu," +


            "left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu,left:10dlu,left:2dlu";

    /**
     * Creates a new instance of JGoodiesFactory
     */

    JPanel layeredpane;

    JComboBox liste;


    JCheckBox dossier;

    JCheckBox controle;

    JCheckBox caisse;

    JCheckBox fournisseur;

    ButtonGroup group;

    boolean first = true;

    ActionListener lst = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {


            System.out.println(liste.getSelectedIndex());
            int id = liste.getSelectedIndex();
            id++;
            CardLayout cardLayout= (CardLayout) layeredpane.getLayout();

            switch (id) {

                case (1):
                  /*  ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(true);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    // layeredpane.moveToBack(layeredpane.getComponent(1));

                    cardLayout= (CardLayout) layeredpane.getLayout();
                    cardLayout.show(layeredpane, "1");
                    frame.setPanelSelected(2);
                    break;
                case (2):

                   /* ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(true);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/


                    cardLayout.show(layeredpane, "2");
                    frame.setPanelSelected(3);
                    break;
                case (3):
/*
                    ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(true);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    cardLayout.show(layeredpane, "3");
                    frame.setPanelSelected(4);

                    break;

                case (4):

                   /* ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(true);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    cardLayout.show(layeredpane, "4");
                    frame.setPanelSelected(5);
                    break;


                case (5):

                 /*   ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(true);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    cardLayout.show(layeredpane, "5");
                    frame.setPanelSelected(6);
                    break;

                case (6):

                 /*   ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(true);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);
*/
                    cardLayout.show(layeredpane, "6");
                    frame.setPanelSelected(7);
                    break;

                case (7):

                 /*   ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(true);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    cardLayout.show(layeredpane, "7");
                    frame.setPanelSelected(8);
                    break;

                case (8):

                 /*   ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(true);

                    ((JPanel) panel.get(9)).setVisible(false);*/

                    cardLayout.show(layeredpane, "8");
                    frame.setPanelSelected(9);
                    break;

                case (9):

                /*    ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    ((JPanel) panel.get(4)).setVisible(false);

                    ((JPanel) panel.get(5)).setVisible(false);
                    ((JPanel) panel.get(6)).setVisible(false);

                    ((JPanel) panel.get(7)).setVisible(false);

                    ((JPanel) panel.get(8)).setVisible(false);

                    ((JPanel) panel.get(9)).setVisible(true);
*/
                    cardLayout.show(layeredpane, "9");
                    frame.setPanelSelected(10);
                    break;

                case (-1):
                    first = false;
                    break;

            }


            System.out.println(liste.getSelectedIndex());


        }


    };

   /* KeyAdapter my_key = new KeyAdapter() {

        public void keyPressed(KeyEvent evt) {

            if (evt.getKeyCode() == evt.VK_SPACE) {

                CardLayout cardLayout= (CardLayout) layeredpane.getLayout();
                if (evt.getSource() == dossier) {

                    //  layeredpane.moveToBack(layeredpane.getComponent(2));

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(0)).setVisible(true);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);
                    cardLayout.show(layeredpane, "0");

                } else if (evt.getSource() == caisse) {

                    ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(true);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(false);

                    // layeredpane.moveToBack(layeredpane.getComponent(1));

                    cardLayout.show(layeredpane,"1");

                } else if (evt.getSource() == controle) {

                    ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(true);

                    ((JPanel) panel.get(3)).setVisible(false);


                    cardLayout.show(layeredpane, "2");


                } else if (evt.getSource() == fournisseur) {

                    ((JPanel) panel.get(0)).setVisible(false);

                    ((JPanel) panel.get(1)).setVisible(false);

                    ((JPanel) panel.get(2)).setVisible(false);

                    ((JPanel) panel.get(3)).setVisible(true);

                    cardLayout.show(layeredpane,"3");


                }


            }

        }

    };*/

   /* MouseAdapter my_mouse = new MouseAdapter() {

        public void mouseClicked(MouseEvent evt) {


            if (evt.getSource() == dossier) {

                System.out.println("dossier");

                //   layeredpane.moveToBack(layeredpane.getComponent(2));

                ((JPanel) panel.get(1)).setVisible(false);

                ((JPanel) panel.get(0)).setVisible(true);

                ((JPanel) panel.get(2)).setVisible(false);

                ((JPanel) panel.get(3)).setVisible(false);

                layeredpane.moveToFront((JPanel) panel.get(0));

                frame.setPanelSelected(1);

            } else if (evt.getSource() == caisse) {

                System.out.println("caisse");

                ((JPanel) panel.get(0)).setVisible(false);

                ((JPanel) panel.get(1)).setVisible(true);

                ((JPanel) panel.get(2)).setVisible(false);

                ((JPanel) panel.get(3)).setVisible(false);

                //   layeredpane.moveToBack(layeredpane.getComponent(1));

                layeredpane.moveToFront((JPanel) panel.get(1));

                frame.setPanelSelected(2);

            } else if (evt.getSource() == controle) {

                System.out.println("controle");

                ((JPanel) panel.get(0)).setVisible(false);

                ((JPanel) panel.get(1)).setVisible(false);

                ((JPanel) panel.get(2)).setVisible(true);

                ((JPanel) panel.get(3)).setVisible(false);

                //   layeredpane.moveToBack(layeredpane.getComponent(1));

                layeredpane.moveToFront((JPanel) panel.get(2));

                frame.setPanelSelected(3);

            } else if (evt.getSource() == fournisseur) {

                System.out.println("fourn");

                ((JPanel) panel.get(0)).setVisible(false);

                ((JPanel) panel.get(1)).setVisible(false);

                ((JPanel) panel.get(2)).setVisible(false);

                ((JPanel) panel.get(3)).setVisible(true);

                //   layeredpane.moveToBack(layeredpane.getComponent(1));

                layeredpane.moveToFront((JPanel) panel.get(2));

                frame.setPanelSelected(4);

            }


        }


    };*/

    public JGoodiesFactory(JPanel layeredpane, ListingFrame frame, Loginusers_T currentUser) {

        this.layeredpane = layeredpane;

        this.currentUser = currentUser;

        liste = new JComboBox();

        liste.addActionListener(lst);


        panel = new ArrayList();

        this.frame = frame;

        group = new ButtonGroup();

    }

    public JPanel builPanel(int type, JGoodiesConfig config) {

        switch (type) {

            case DOSSIER_CHOICE: {


                JPanel panel = new JPanel();

                CellConstraints cc = new CellConstraints();

                panel.add(liste);



                panel.setBounds(10, 20, 90, 50);


                liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_tile")));

                liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("cai")));


                if (currentUser.getUrrights() <= 2) {
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("controle")));

                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("fourn")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("journ")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("lfourn")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("ach")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("cha")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("cha1")));
                    liste.addItem(new String(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("embdeb")));
                }

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("choix"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                panel.setBorder(border);

                // panel.add(group);

                return panel;

            }

            case DOSSIER: {                             //   1       2              3       4       5       6    7   8       9           10      11          12

                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();

            }

            case CAISSE: {                             //   1       2              3       4       5       6    7   8       9           10      11          12

                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 130);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();

            }

            case CONTROLE: {
                System.out.println("controleList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case FOURN: {
                System.out.println("fournList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case JOURN: {
                System.out.println("jouurnList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case LFOURN: {
                System.out.println("fournList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case ACHAT: {
                System.out.println("fournList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case CHA: {
                System.out.println("fournList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case CHAF: {
                System.out.println("fournList");
                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case DEBEMB: {

                FormLayout layout = new FormLayout("left:pref,left:2dlu,pref,left:2dlu,left:pref,left:2dlu,pref,min",

                        "p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu,p,1dlu");

                //layout.setRowGroups(new int[][]{{1,3}});

                //JPanel panel=new JPanel(layout);

                PanelBuilder builder = new PanelBuilder(layout);

                builder.setDefaultDialogBorder();

                CellConstraints cc = new CellConstraints();

                generateComponent(builder, config, cc);

                //initLabelDossier();


                builder.getPanel().setBounds(110, 20, 510, 320);

                panel.add(builder.getPanel());

                TitledBorder border = new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 1), java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param"));

                border.setTitleFont(new Font("Tahoma", Font.BOLD, 10));

                builder.getPanel().setBorder(border);

                return builder.getPanel();


            }

            case MAINPANEL: {

                FormLayout layout = new FormLayout("left:500dlu",

                        "500dlu");

                JPanel retval = new JPanel(new java.awt.BorderLayout());

                //  JPanel panel=new JPanel(layout);

                //  PanelBuilder builder=new PanelBuilder(new java.awt.BorderLayout());

                //  builder.setDefaultDialogBorder();

                // CellConstraints cc=new CellConstraints();

                Object[][] tabcomp = config.getComponent();

                JPanel[] panel = (JPanel[]) tabcomp[0][0];

//                layeredpane.add(panel[0], ""+0);

                layeredpane.add(panel[1], ""+1);

                layeredpane.add(panel[2], ""+2);

                layeredpane.add(panel[3], ""+3);

                layeredpane.add(panel[4], ""+4);

                layeredpane.add(panel[5], ""+5);

                layeredpane.add(panel[6], ""+6);

                layeredpane.add(panel[7], ""+7);

                layeredpane.add(panel[8], ""+8);

                layeredpane.add(panel[9], ""+9);

                layeredpane.add(panel[10], ""+10);

                layeredpane.setBackground(java.awt.Color.BLUE);

                CardLayout cardLayout= (CardLayout) layeredpane.getLayout();
                cardLayout.show(layeredpane,"1");
                JPanel hbox=new JPanel();
                hbox.setLayout(new BoxLayout(hbox, BoxLayout.X_AXIS));
                hbox.add(panel[0]);
                hbox.add(layeredpane);
                retval.add(hbox);

                return retval;

            }

        }

        return null;

    }

    private void generateComponent(PanelBuilder builder, JGoodiesConfig config, CellConstraints cc) {

        String[] separator = config.getSeparator();

        Object[][] component = config.getComponent();

        int ligne = 1;

        for (int i = 0; i < separator.length; i++) {

            if (separator[i] != null)

                builder.addSeparator(separator[i], cc.xywh(1, ligne, 8, 1));

            //ligne=ligne+2;

            Object[] tabtabcomp = (Object[]) component[i];

            for (int j = 0; j < tabtabcomp.length; j++) {

                int k = 1;

                System.out.println("k" + k);

                JComponent[] tabcomp = (JComponent[]) tabtabcomp[j];

                if (tabcomp != null) {

                    ligne = ligne + 2;

                    for (int x = 0; x < tabcomp.length; x++) {

                        JComponent comp = (JComponent) tabcomp[x];

                        if (comp != null)

                            builder.add(comp, cc.xy(k, ligne));

                        k = k + 2;

                    }

                }

            }

            ligne = ligne + 2;

        }

    }

    private void initLabelDossier() {

        de = new JLabel("De");

        a = new JLabel("A");

        de1 = new JLabel("Création");

        a1 = new JLabel("Départ");

        cli = new JLabel("Client");

        clicon = new JLabel("Client Cont.");

        fourn = new JLabel("Fournisseur");

        dest = new JLabel("Destination");

        pass = new JLabel("Passager");

        po = new JLabel("Po/Pnr");


        numdos = new JLabel("Num. Dos");

        typeprod = new JLabel("Produit");


        numdos.setFont(new java.awt.Font("Tahoma", 0, 10));

        typeprod.setFont(new java.awt.Font("Tahoma", 0, 10));


        cli.setFont(new java.awt.Font("Tahoma", 0, 10));

        clicon.setFont(new java.awt.Font("Tahoma", 0, 10));

        fourn.setFont(new java.awt.Font("Tahoma", 0, 10));

        dest.setFont(new java.awt.Font("Tahoma", 0, 10));

        pass.setFont(new java.awt.Font("Tahoma", 0, 10));

        po.setFont(new java.awt.Font("Tahoma", 0, 10));


        client = new Liste();

        clientcon = new Liste();

        passa = new Liste();

        destin = new Liste();

        fournl = new Liste();

        pol = new Liste();


        numdosl = new Liste();

        typeprodl = new Liste();

        periode = new JCheckBox("Période");

        date_dossier = new JCheckBox("Date dossier");

        solde = new JCheckBox("Soldé");

        facture = new JCheckBox("Facturé");

        numfacl = new JLabel("Num. Fac");

        numncl = new JLabel("Num. NC");

        numfacl.setFont(new java.awt.Font("Tahoma", 0, 10));

        numncl.setFont(new java.awt.Font("Tahoma", 0, 10));

        date_dossier.setFont(new java.awt.Font("Tahoma", 0, 10));

        periode.setFont(new java.awt.Font("Tahoma", 0, 10));

        de.setFont(new java.awt.Font("Tahoma", 0, 10));

        a.setFont(new java.awt.Font("Tahoma", 0, 10));

        de1.setFont(new java.awt.Font("Tahoma", 0, 10));

        a1.setFont(new java.awt.Font("Tahoma", 0, 10));

        solde.setFont(new java.awt.Font("Tahoma", 0, 10));

        facture.setFont(new java.awt.Font("Tahoma", 0, 10));

        numfac = new ATextField();

        numnc = new ATextField();

    }

    JLabel de;

    JLabel a;

    JLabel de1;

    JLabel a1;

    JLabel numfacl;

    JLabel numncl;

    JLabel cli;

    JLabel clicon;

    JLabel fourn;

    JLabel dest;

    JLabel pass;

    JLabel po;

    JLabel numdos;

    JLabel typeprod;

    JCheckBox periode;

    JCheckBox date_dossier;

    JCheckBox solde;

    JCheckBox facture;

    ATextField numfac;

    ATextField numnc;

    Liste client;

    Liste clientcon;

    Liste passa;

    Liste destin;

    Liste fournl;

    Liste pol;

    Liste numdosl;

    Liste typeprodl;


}

