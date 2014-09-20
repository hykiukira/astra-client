package srcastra.astra.gui.modules.btn;

import javax.swing.*;
import java.util.logging.Logger;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 29-nov.-2004
 * Time: 21:39:41
 *
 * @author
 * @version $revision : $
 *          To change this template use File | Settings | File Templates.
 */
public class BtnPanel2 extends JPanel {
    private static Logger logger = Logger.getLogger(BtnPanel2.class.getName());

    public BtnPanel2() {
        setLayout(new GridBagLayout());
        setPreferredSize(new java.awt.Dimension(710, 480));
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();


        grp_table_brochure = new JTable();
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBorder(new javax.swing.border.TitledBorder("List"));
        jScrollPane1.setOpaque(false);


        jScrollPane1.setViewportView(grp_table_brochure);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 150));


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        // gridBagConstraints.weighty=0.5;
        //gridBagConstraints.weightx=0.5;
        // gridBagConstraints.ipadx=250;
        // gridBagConstraints.ipady=70;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jScrollPane1, gridBagConstraints);
        // jpanel1.setLayout(new GridBagLayout());
        // jpanel1.add(jScrollPane1,gridBagConstraints);
        grp_Table_Transport = new JTable();
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setBorder(new javax.swing.border.TitledBorder("Transport"));
        jScrollPane2.setOpaque(false);
        jScrollPane2.setViewportView(grp_Table_Transport);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        // gridBagConstraints.weighty=0.5;
        //gridBagConstraints.weightx=0.5;
        //  gridBagConstraints.ipadx=250;
        // gridBagConstraints.ipady=70;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jScrollPane2, gridBagConstraints);


        grp_Table_service = new JTable();
        jScrollPane3 = new JScrollPane();
        jScrollPane3.setBorder(new javax.swing.border.TitledBorder("Service"));
        jScrollPane3.setOpaque(false);
        jScrollPane3.setViewportView(grp_Table_service);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(350, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        // gridBagConstraints.weighty=0.5;
        //gridBagConstraints.weightx=0.5;
        //  gridBagConstraints.ipadx=250;
        // gridBagConstraints.ipady=70;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jScrollPane3, gridBagConstraints);

        grp_Table_passenger = new JTable();
        jscrollpane = new JScrollPane();
        jscrollpane.setBorder(new javax.swing.border.TitledBorder("Passager"));
        jscrollpane.setOpaque(false);
        jscrollpane.setViewportView(grp_Table_passenger);
        jscrollpane.setPreferredSize(new java.awt.Dimension(350, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        // gridBagConstraints.weighty=0.5;
        //gridBagConstraints.weightx=0.5;
        //  gridBagConstraints.ipadx=250;
        // gridBagConstraints.ipady=70;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jscrollpane, gridBagConstraints);

        jPanel4 = new JPanel();
        g_pa_destination = new JPanel();
        g_pa_destination.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        g_pa_destination = new JPanel();
        g_pa_destination.setOpaque(false);

        g_la_destination = new JLabel();
        g_la_destination.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("destination"));
        jPanel4.add(g_la_destination);

        grp_LSelect_Destination = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Destination.setPreferredSize(new java.awt.Dimension(85, 18));
        jPanel4.add(grp_LSelect_Destination);

        g_la_transport = new JLabel();
        g_la_transport.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("transport"));
        jPanel4.add(g_la_transport);

        grp_LSelect_Transport = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Transport.setPreferredSize(new java.awt.Dimension(85, 18));
        jPanel4.add(grp_LSelect_Transport);

        g_la_depart = new JLabel();
        g_la_depart.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("dep"));
        jPanel4.add(g_la_depart);

        grp_LSelect_Depart = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Depart.setPreferredSize(new java.awt.Dimension(85, 18));
        jPanel4.add(grp_LSelect_Depart);

        g_la_arrive = new JLabel();
        g_la_arrive.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("arriv"));
        jPanel4.add(g_la_arrive);

        grp_LSelect_Arrive = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Arrive.setPreferredSize(new java.awt.Dimension(85, 18));
        jPanel4.add(grp_LSelect_Arrive);

        g_la_carrier = new JLabel();
        g_la_carrier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("carrier"));
        jPanel4.add(g_la_carrier);

        grp_LSelect_Carrier = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_Carrier.setPreferredSize(new java.awt.Dimension(85, 18));
        jPanel4.add(grp_LSelect_Carrier);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel4, gridBagConstraints);
    }

    public static void main(String[] args) {
        BtnPanel2 panel = new BtnPanel2();
        JFrame frame = new JFrame();
        //  frame.setBounds(0,0,710,490);
        frame.getContentPane().add(panel);
        frame.show();
        frame.pack();
    }


    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jscrollpane;

    private JTable grp_Table_Transport;
    private JTable grp_Table_passenger;
    private JTable grp_Table_service;
    private JTable grp_table_brochure;

    private javax.swing.JLabel g_la_destination;
    private javax.swing.JLabel g_la_transport;
    private javax.swing.JLabel g_la_arrive;
    private javax.swing.JLabel g_la_depart;
    private javax.swing.JLabel g_la_carrier;

    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;

    private javax.swing.JPanel g_pa_destination;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;

    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Destination;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Fournisseur;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Arrive;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Transport;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Carrier;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Depart;


}
