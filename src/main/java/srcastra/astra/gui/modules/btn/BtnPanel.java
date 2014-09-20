/*
 * BtnPanel.java
 *
 * Created on 16 septembre 2004, 21:52
 */

package srcastra.astra.gui.modules.btn;

import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.btn.model.Reservering;
import srcastra.astra.sys.btn.model.Service;
import srcastra.astra.sys.btn.model.Passenger;
import srcastra.astra.sys.rmi.astrainterface;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Administrateur
 */
public class BtnPanel extends javax.swing.JPanel {

    /**
     * Creates new form BtnPanel
     */
    BTNFrame parent;
    astrainterface serveur;
    BtnTableModel table_model;
    TransportTableModel transportModel;
    ServiceTableModel serviceModel;
    PassengerTableModel passengerTableModel;
    srcastra.astra.sys.btn.business.Btn btnServeur;
    ArrayList brochure;
    srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel fournisseurModel;
    srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel tbMod_grpDeProd;
    Reservering reservering;

    public BtnPanel(BTNFrame parent, astrainterface serveur) {
        try {
            initComponents();
            this.parent = parent;
            this.serveur = serveur;
            initTable();
            initListe();
            initTableTransport();
            initTableService();
            initTablePassenger();
            grp_Table_Transport.addMouseListener(mlist);
            btnServeur = serveur.getBtnServeur(parent.getCurrentUser().getUrcleunik());
            brochure = getResevations(0, parent.getCurrentUser().getUrcleunik());
            table_model.setData(brochure);
            grp_LSelect_status.setBorder(BorderFactory.createLineBorder(Color.RED));
            grp_LSelect_transport.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

            grp_LSelect_depart.setBorder(BorderFactory.createLineBorder(Color.RED));
            grp_LSelect_arrive.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            grp_LSelect_carrier.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            grp_LSelect_TitrePers.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            refreshTable(0);
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, parent.getCurrentUser());
        } catch (java.rmi.RemoteException rn) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn, parent.getCurrentUser());
        }
    }

    private void refreshGrpProd() {
        tbMod_grpDeProd.setM_frcleunik(grp_LSelect_Fournisseur.getCleUnik());
        tbMod_grpDeProd.loadata();

    }

    private void initListe() {

        grp_LSelect_Fournisseur.setServeur(parent.getServeur());
        grp_LSelect_Fournisseur.setLogin(parent.getCurrentUser());
        this.fournisseurModel = new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_LSelect_Fournisseur.setModel(this.fournisseurModel);
        grp_LSelect_Fournisseur.init2();


        grp_LSelect_GrpProduits.setServeur(parent.getServeur());
        grp_LSelect_GrpProduits.setLogin(parent.getCurrentUser());
        this.tbMod_grpDeProd = new srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_LSelect_GrpProduits.setModel(this.tbMod_grpDeProd);
        grp_LSelect_GrpProduits.init2();

        grp_LSelect_Destination.setServeur(parent.getServeur());
        grp_LSelect_Destination.setLogin(parent.getCurrentUser());
        grp_LSelect_Destination.setModel(new srcastra.astra.gui.components.combobox.liste.DestinationtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_Destination.init2();
        grp_LSelect_Destination.setFreeModeAllow(true);

        grp_LSelect_depart.setServeur(parent.getServeur());
        grp_LSelect_depart.setLogin(parent.getCurrentUser());
        grp_LSelect_depart.setModel(new srcastra.astra.gui.components.combobox.liste.DestinationtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_depart.init2();
        grp_LSelect_depart.setFreeModeAllow(true);

        grp_LSelect_arrive.setServeur(parent.getServeur());
        grp_LSelect_arrive.setLogin(parent.getCurrentUser());
        grp_LSelect_arrive.setModel(new srcastra.astra.gui.components.combobox.liste.DestinationtListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_arrive.init2();
        grp_LSelect_arrive.setFreeModeAllow(true);

        grp_LSelect_TitrePers.setServeur(parent.getServeur());
        grp_LSelect_TitrePers.setLogin(parent.getCurrentUser());
        grp_LSelect_TitrePers.setModel(new srcastra.astra.gui.components.combobox.liste.TitrePersonneListeTableModel(parent.getServeur(), parent.getCurrentUser()));
        grp_LSelect_TitrePers.init2();
        grp_LSelect_TitrePers.setLastFocusedComponent(true);


    }

    private void refreshTable(int row) {
        grp_table_brochure.tableChanged(new TableModelEvent(table_model));
        if (grp_table_brochure.getRowCount() > 0) {
            grp_table_brochure.changeSelection(row, 0, false, false);
            reservering = (Reservering) brochure.get(row);
            if (reservering != null) {
                System.out.println("fournisseur code " + reservering.getTocode());
                grp_LSelect_Fournisseur.setCleUnik(reservering.getTocode());
                refreshGrpProd();
                grp_LSelect_GrpProduits.setCleUnik(reservering.getTobrochure());
                ArrayList transport = reservering.getServiceTransport();
                transportModel.setData(transport);
                grp_Table_Transport.tableChanged(new TableModelEvent(transportModel));
                refreshTransport(transport, 0);
                ArrayList service = reservering.getService();
                serviceModel.setData(service);
                grp_Table_service.tableChanged(new TableModelEvent(serviceModel));
                if (grp_Table_service.getRowCount() > 0) {
                    grp_Table_service.changeSelection(0, 0, false, false);
                }
                ArrayList passenger = reservering.getPassenger();
                passengerTableModel.setData(passenger);
                grp_Table_passenger.tableChanged(new TableModelEvent(passengerTableModel));
                if (grp_Table_passenger.getRowCount() > 0) {
                    grp_Table_passenger.changeSelection(0, 0, false, false);
                }
            }
        }
    }

    private void refreshTranportOnclick() {
        if (reservering != null) {
            ArrayList transport = reservering.getServiceTransport();
            int row = grp_Table_Transport.getSelectedRow();
            refreshTransport(transport, row);
        }

    }

    private void refreshTitreOnclick() {
        if (reservering != null) {
            ArrayList passagerList = reservering.getPassenger();
            int row = grp_Table_passenger.getSelectedRow();
            refreshPassenger(passagerList, row);
        }

    }

    private void refreshPassenger(ArrayList passengerList, int index) {
        if (passengerList != null) {
            Passenger passenger = (Passenger) passengerList.get(index);
            grp_LSelect_TitrePers.setCleUnik(new Long(passenger.getId()).intValue());
        } else {
            grp_LSelect_TitrePers.setCleUnik(0);
        }
        if (grp_Table_passenger.getRowCount() > 0) {
            grp_Table_passenger.changeSelection(index, index, false, false);
        }
    }

    private void refreshTransport(ArrayList transport, int index) {
        if (transport != null) {
            Service transports = (Service) transport.get(index);
            grp_LSelect_Destination.setCleUnik(transports.getDestination());
            grp_LSelect_arrive.setCleUnik(transports.getArrivePlace());
            grp_LSelect_depart.setCleUnik(transports.getStartPlace());
        } else {
            grp_LSelect_Destination.setCleUnik(0);
            grp_LSelect_arrive.setCleUnik(0);
            grp_LSelect_depart.setCleUnik(0);
        }
        if (grp_Table_Transport.getRowCount() > 0) {
            grp_Table_Transport.changeSelection(index, index, false, false);
        }
    }

    MouseAdapter mlist = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            refreshTranportOnclick();
        }
    };

    private ArrayList getResevations(int entity, int urcluenik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        if (btnServeur != null) {
            return btnServeur.getReservations(0, parent.getCurrentUser().getUrcleunik());
        } else {
            System.out.println("btnserveur==null");
        }
        return null;

    }

    private void initTable() {
        grp_table_brochure.setSelectionBackground(new java.awt.Color(221, 221, 255));
        table_model = new BtnTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_table_brochure.setAutoCreateColumnsFromModel(false);
        grp_table_brochure.setModel(table_model);
        grp_table_brochure.getTableHeader().setReorderingAllowed(false);
        grp_table_brochure.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < table_model.getColumnCount(); i++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(table_model.columData[i].getM_alignment());
            // TableCellEditor editor = new DefaultCellEditor(new JTextField());
            grp_table_brochure.addColumn(new TableColumn(i, table_model.columData[i].getM_width(), renderer, null));

        }
        JTableHeader header = grp_table_brochure.getTableHeader();
        header.setUpdateTableInRealTime(false);
    }

    private void initTableTransport() {
        grp_Table_Transport.setSelectionBackground(new java.awt.Color(221, 221, 255));
        transportModel = new TransportTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_Table_Transport.setAutoCreateColumnsFromModel(false);
        grp_Table_Transport.setModel(transportModel);
        grp_Table_Transport.getTableHeader().setReorderingAllowed(false);
        grp_Table_Transport.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < transportModel.getColumnCount(); i++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(transportModel.columData[i].getM_alignment());
            // TableCellEditor editor = new DefaultCellEditor(new JTextField());
            grp_Table_Transport.addColumn(new TableColumn(i, transportModel.columData[i].getM_width(), renderer, null));

        }
        JTableHeader header = grp_Table_Transport.getTableHeader();
        header.setUpdateTableInRealTime(false);
    }

    private void initTableService() {
        grp_Table_service.setSelectionBackground(new java.awt.Color(221, 221, 255));
        serviceModel = new ServiceTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_Table_service.setAutoCreateColumnsFromModel(false);
        grp_Table_service.setModel(serviceModel);
        grp_Table_service.getTableHeader().setReorderingAllowed(false);
        grp_Table_service.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < serviceModel.getColumnCount(); i++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(serviceModel.columData[i].getM_alignment());
            // TableCellEditor editor = new DefaultCellEditor(new JTextField());
            grp_Table_service.addColumn(new TableColumn(i, serviceModel.columData[i].getM_width(), renderer, null));

        }
        JTableHeader header = grp_Table_service.getTableHeader();
        header.setUpdateTableInRealTime(false);
    }

    private void initTablePassenger() {
        grp_Table_passenger.setSelectionBackground(new java.awt.Color(221, 221, 255));
        passengerTableModel = new PassengerTableModel(parent.getServeur(), parent.getCurrentUser());
        grp_Table_passenger.setAutoCreateColumnsFromModel(false);
        grp_Table_passenger.setModel(passengerTableModel);
        grp_Table_passenger.getTableHeader().setReorderingAllowed(false);
        grp_Table_passenger.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < passengerTableModel.getColumnCount(); i++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(passengerTableModel.columData[i].getM_alignment());
            // TableCellEditor editor = new DefaultCellEditor(new JTextField());
            grp_Table_passenger.addColumn(new TableColumn(i, passengerTableModel.columData[i].getM_width(), renderer, null));

        }
        JTableHeader header = grp_Table_passenger.getTableHeader();
        header.setUpdateTableInRealTime(false);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_table_brochure = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_Table_passenger = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        grp_Table_Transport = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grp_LSelect_GrpProduits = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel3 = new javax.swing.JLabel();
        grp_LSelect_Fournisseur = new srcastra.astra.gui.components.combobox.liste.Liste();
        grp_LSelect_status = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        aTextField1 = new srcastra.astra.gui.components.textFields.ATextField();
        jscrollpane = new javax.swing.JScrollPane();
        grp_Table_service = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        grp_LSelect_Destination = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel6 = new javax.swing.JLabel();
        grp_LSelect_transport = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel7 = new javax.swing.JLabel();
        grp_LSelect_depart = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel8 = new javax.swing.JLabel();
        grp_LSelect_arrive = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel9 = new javax.swing.JLabel();
        grp_LSelect_carrier = new srcastra.astra.gui.components.combobox.liste.Liste();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        grp_LSelect_TitrePers = new srcastra.astra.gui.components.combobox.liste.Liste();

        setLayout(new java.awt.BorderLayout());

        setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(710, 490));
        jScrollPane1.setBorder(new javax.swing.border.TitledBorder("List"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(560, 120));
        jScrollPane1.setOpaque(false);
        grp_table_brochure.setModel(new javax.swing.table.DefaultTableModel());
        grp_table_brochure.setSelectionForeground(new java.awt.Color(204, 0, 0));
        grp_table_brochure.setOpaque(false);
        grp_table_brochure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grp_table_brochureMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(grp_table_brochure);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setBorder(new javax.swing.border.TitledBorder("Passenger"));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 100));
        jScrollPane2.setOpaque(false);
        grp_Table_passenger.setModel(new javax.swing.table.DefaultTableModel());
        grp_Table_passenger.setSelectionForeground(new java.awt.Color(204, 0, 0));
        grp_Table_passenger.setOpaque(false);
        jScrollPane2.setViewportView(grp_Table_passenger);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setBorder(new javax.swing.border.TitledBorder("Transport"));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 100));
        jScrollPane3.setOpaque(false);
        grp_Table_Transport.setModel(new javax.swing.table.DefaultTableModel());
        grp_Table_Transport.setSelectionForeground(new java.awt.Color(204, 0, 0));
        grp_Table_Transport.setOpaque(false);
        jScrollPane3.setViewportView(grp_Table_Transport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jScrollPane3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Reservation"));
        jPanel2.setOpaque(false);
        jLabel1.setText("To");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("ToBroch");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel2.add(grp_LSelect_GrpProduits, gridBagConstraints);

        jLabel3.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel2.add(grp_LSelect_Fournisseur, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanel2.add(grp_LSelect_status, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Search"));
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 45));
        jPanel3.setOpaque(false);
        jLabel4.setText("Boeking Number");
        jPanel3.add(jLabel4);

        jPanel3.add(aTextField1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel3, gridBagConstraints);

        jscrollpane.setBorder(new javax.swing.border.TitledBorder("Service"));
        jscrollpane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscrollpane.setPreferredSize(new java.awt.Dimension(470, 75));
        grp_Table_service.setModel(new javax.swing.table.DefaultTableModel());
        grp_Table_service.setSelectionForeground(new java.awt.Color(204, 0, 0));
        jscrollpane.setViewportView(grp_Table_service);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jscrollpane, gridBagConstraints);

        jPanel4.setLayout(new GridBagLayout());

        jPanel4.setOpaque(false);
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("destination"));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel5, gridBagConstraints);

        grp_LSelect_Destination.setPreferredSize(new java.awt.Dimension(85, 18));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_LSelect_Destination, gridBagConstraints);
        //jPanel4.add(grp_LSelect_Destination);

        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("transport"));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel6, gridBagConstraints);


        grp_LSelect_transport.setPreferredSize(new java.awt.Dimension(85, 18));
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_LSelect_transport, gridBagConstraints);


        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("dep"));
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel7, gridBagConstraints);
        //jPanel4.add(jLabel7);

        grp_LSelect_depart.setPreferredSize(new java.awt.Dimension(85, 18));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_LSelect_depart, gridBagConstraints);
        //jPanel4.add(grp_LSelect_depart);

        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("arriv"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel8, gridBagConstraints);
        //jPanel4.add(jLabel8);

        grp_LSelect_arrive.setPreferredSize(new java.awt.Dimension(85, 18));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_LSelect_arrive, gridBagConstraints);
        //jPanel4.add(grp_LSelect_arrive);

        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("carrier"));
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel9, gridBagConstraints);
        //jPanel4.add(jLabel9);

        grp_LSelect_carrier.setPreferredSize(new java.awt.Dimension(85, 18));
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_LSelect_carrier, gridBagConstraints);
        //jPanel4.add(grp_LSelect_carrier);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setOpaque(false);
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/Btn").getString("titre"));
        jPanel5.add(jLabel10);

        jPanel5.add(grp_LSelect_TitrePers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel5, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void grp_table_brochureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grp_table_brochureMouseClicked
        refreshTable(grp_table_brochure.getSelectedRow());
    }//GEN-LAST:event_grp_table_brochureMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.textFields.ATextField aTextField1;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Destination;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_Fournisseur;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_GrpProduits;
    private javax.swing.JTable grp_Table_Transport;
    private javax.swing.JTable grp_Table_passenger;
    private javax.swing.JTable grp_Table_service;
    private javax.swing.JTable grp_table_brochure;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jscrollpane;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_status;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_transport;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_depart;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_arrive;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_carrier;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_LSelect_TitrePers;
    // End of variables declaration//GEN-END:variables

}
