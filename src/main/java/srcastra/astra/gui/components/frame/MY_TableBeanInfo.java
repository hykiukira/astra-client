package srcastra.astra.gui.components.frame;


import java.beans.*;


public class MY_TableBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/

    private static BeanDescriptor getBdescriptor() {

        BeanDescriptor beanDescriptor = new BeanDescriptor(MY_Table.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties

    private static final int PROPERTY_accessibleContext = 0;

    private static final int PROPERTY_actionMap = 1;

    private static final int PROPERTY_alignmentX = 2;

    private static final int PROPERTY_alignmentY = 3;

    private static final int PROPERTY_ancestorListeners = 4;

    private static final int PROPERTY_autoCreateColumnsFromModel = 5;

    private static final int PROPERTY_autoResizeMode = 6;

    private static final int PROPERTY_autoscrolls = 7;

    private static final int PROPERTY_background = 8;

    private static final int PROPERTY_backgroundSet = 9;

    private static final int PROPERTY_border = 10;

    private static final int PROPERTY_bounds = 11;

    private static final int PROPERTY_cellEditor = 12;

    private static final int PROPERTY_cellSelectionEnabled = 13;

    private static final int PROPERTY_colorModel = 14;

    private static final int PROPERTY_columnClass = 15;

    private static final int PROPERTY_columnCount = 16;

    private static final int PROPERTY_columnModel = 17;

    private static final int PROPERTY_columnName = 18;

    private static final int PROPERTY_columnSelectionAllowed = 19;

    private static final int PROPERTY_columnSelectionInterval = 20;

    private static final int PROPERTY_component = 21;

    private static final int PROPERTY_componentCount = 22;

    private static final int PROPERTY_componentListeners = 23;

    private static final int PROPERTY_componentOrientation = 24;

    private static final int PROPERTY_components = 25;

    private static final int PROPERTY_containerListeners = 26;

    private static final int PROPERTY_cursor = 27;

    private static final int PROPERTY_cursorSet = 28;

    private static final int PROPERTY_debugGraphicsOptions = 29;

    private static final int PROPERTY_displayable = 30;

    private static final int PROPERTY_doubleBuffered = 31;

    private static final int PROPERTY_dragEnabled = 32;

    private static final int PROPERTY_dropTarget = 33;

    private static final int PROPERTY_editing = 34;

    private static final int PROPERTY_editingColumn = 35;

    private static final int PROPERTY_editingRow = 36;

    private static final int PROPERTY_editorComponent = 37;

    private static final int PROPERTY_enabled = 38;

    private static final int PROPERTY_focusable = 39;

    private static final int PROPERTY_focusCycleRoot = 40;

    private static final int PROPERTY_focusCycleRootAncestor = 41;

    private static final int PROPERTY_focusListeners = 42;

    private static final int PROPERTY_focusOwner = 43;

    private static final int PROPERTY_focusTraversable = 44;

    private static final int PROPERTY_focusTraversalKeys = 45;

    private static final int PROPERTY_focusTraversalKeysEnabled = 46;

    private static final int PROPERTY_focusTraversalPolicy = 47;

    private static final int PROPERTY_focusTraversalPolicySet = 48;

    private static final int PROPERTY_font = 49;

    private static final int PROPERTY_fontSet = 50;

    private static final int PROPERTY_foreground = 51;

    private static final int PROPERTY_foregroundSet = 52;

    private static final int PROPERTY_graphics = 53;

    private static final int PROPERTY_graphicsConfiguration = 54;

    private static final int PROPERTY_gridColor = 55;

    private static final int PROPERTY_height = 56;

    private static final int PROPERTY_hierarchyBoundsListeners = 57;

    private static final int PROPERTY_hierarchyListeners = 58;

    private static final int PROPERTY_ignoreRepaint = 59;

    private static final int PROPERTY_inputContext = 60;

    private static final int PROPERTY_inputMethodListeners = 61;

    private static final int PROPERTY_inputMethodRequests = 62;

    private static final int PROPERTY_inputVerifier = 63;

    private static final int PROPERTY_insets = 64;

    private static final int PROPERTY_intercellSpacing = 65;

    private static final int PROPERTY_keyListeners = 66;

    private static final int PROPERTY_layout = 67;

    private static final int PROPERTY_lightweight = 68;

    private static final int PROPERTY_locale = 69;

    private static final int PROPERTY_locationOnScreen = 70;

    private static final int PROPERTY_managingFocus = 71;

    private static final int PROPERTY_maximumSize = 72;

    private static final int PROPERTY_maximumSizeSet = 73;

    private static final int PROPERTY_minimumSize = 74;

    private static final int PROPERTY_minimumSizeSet = 75;

    private static final int PROPERTY_model = 76;

    private static final int PROPERTY_mouseListeners = 77;

    private static final int PROPERTY_mouseMotionListeners = 78;

    private static final int PROPERTY_mouseWheelListeners = 79;

    private static final int PROPERTY_name = 80;

    private static final int PROPERTY_nextFocusableComponent = 81;

    private static final int PROPERTY_opaque = 82;

    private static final int PROPERTY_optimizedDrawingEnabled = 83;

    private static final int PROPERTY_paintingTile = 84;

    private static final int PROPERTY_parent = 85;

    private static final int PROPERTY_peer = 86;

    private static final int PROPERTY_preferredScrollableViewportSize = 87;

    private static final int PROPERTY_preferredSize = 88;

    private static final int PROPERTY_preferredSizeSet = 89;

    private static final int PROPERTY_propertyChangeListeners = 90;

    private static final int PROPERTY_registeredKeyStrokes = 91;

    private static final int PROPERTY_requestFocusEnabled = 92;

    private static final int PROPERTY_rootPane = 93;

    private static final int PROPERTY_rowCount = 94;

    private static final int PROPERTY_rowMargin = 95;

    private static final int PROPERTY_rowSelectionAllowed = 96;

    private static final int PROPERTY_rowSelectionInterval = 97;

    private static final int PROPERTY_scrollableTracksViewportHeight = 98;

    private static final int PROPERTY_scrollableTracksViewportWidth = 99;

    private static final int PROPERTY_selectedColumn = 100;

    private static final int PROPERTY_selectedColumnCount = 101;

    private static final int PROPERTY_selectedColumns = 102;

    private static final int PROPERTY_selectedRow = 103;

    private static final int PROPERTY_selectedRowCount = 104;

    private static final int PROPERTY_selectedRows = 105;

    private static final int PROPERTY_selectionBackground = 106;

    private static final int PROPERTY_selectionForeground = 107;

    private static final int PROPERTY_selectionMode = 108;

    private static final int PROPERTY_selectionModel = 109;

    private static final int PROPERTY_showGrid = 110;

    private static final int PROPERTY_showHorizontalLines = 111;

    private static final int PROPERTY_showing = 112;

    private static final int PROPERTY_showVerticalLines = 113;

    private static final int PROPERTY_surrendersFocusOnKeystroke = 114;

    private static final int PROPERTY_tableHeader = 115;

    private static final int PROPERTY_toolkit = 116;

    private static final int PROPERTY_toolTipText = 117;

    private static final int PROPERTY_topLevelAncestor = 118;

    private static final int PROPERTY_transferHandler = 119;

    private static final int PROPERTY_treeLock = 120;

    private static final int PROPERTY_UI = 121;

    private static final int PROPERTY_UIClassID = 122;

    private static final int PROPERTY_valid = 123;

    private static final int PROPERTY_validateRoot = 124;

    private static final int PROPERTY_verifyInputWhenFocusTarget = 125;

    private static final int PROPERTY_vetoableChangeListeners = 126;

    private static final int PROPERTY_visible = 127;

    private static final int PROPERTY_visibleRect = 128;

    private static final int PROPERTY_width = 129;

    private static final int PROPERTY_x = 130;

    private static final int PROPERTY_y = 131;

    // Property array

    /*lazy PropertyDescriptor*/

    private static PropertyDescriptor[] getPdescriptor() {

        PropertyDescriptor[] properties = new PropertyDescriptor[132];


        try {

            properties[PROPERTY_accessibleContext] = new PropertyDescriptor("accessibleContext", MY_Table.class, "getAccessibleContext", null);

            properties[PROPERTY_actionMap] = new PropertyDescriptor("actionMap", MY_Table.class, "getActionMap", "setActionMap");

            properties[PROPERTY_alignmentX] = new PropertyDescriptor("alignmentX", MY_Table.class, "getAlignmentX", "setAlignmentX");

            properties[PROPERTY_alignmentY] = new PropertyDescriptor("alignmentY", MY_Table.class, "getAlignmentY", "setAlignmentY");

            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor("ancestorListeners", MY_Table.class, "getAncestorListeners", null);

            properties[PROPERTY_autoCreateColumnsFromModel] = new PropertyDescriptor("autoCreateColumnsFromModel", MY_Table.class, "getAutoCreateColumnsFromModel", "setAutoCreateColumnsFromModel");

            properties[PROPERTY_autoResizeMode] = new PropertyDescriptor("autoResizeMode", MY_Table.class, "getAutoResizeMode", "setAutoResizeMode");

            properties[PROPERTY_autoscrolls] = new PropertyDescriptor("autoscrolls", MY_Table.class, "getAutoscrolls", "setAutoscrolls");

            properties[PROPERTY_background] = new PropertyDescriptor("background", MY_Table.class, "getBackground", "setBackground");

            properties[PROPERTY_backgroundSet] = new PropertyDescriptor("backgroundSet", MY_Table.class, "isBackgroundSet", null);

            properties[PROPERTY_border] = new PropertyDescriptor("border", MY_Table.class, "getBorder", "setBorder");

            properties[PROPERTY_bounds] = new PropertyDescriptor("bounds", MY_Table.class, "getBounds", "setBounds");

            properties[PROPERTY_cellEditor] = new PropertyDescriptor("cellEditor", MY_Table.class, "getCellEditor", "setCellEditor");

            properties[PROPERTY_cellSelectionEnabled] = new PropertyDescriptor("cellSelectionEnabled", MY_Table.class, "getCellSelectionEnabled", "setCellSelectionEnabled");

            properties[PROPERTY_colorModel] = new PropertyDescriptor("colorModel", MY_Table.class, "getColorModel", null);

            properties[PROPERTY_columnClass] = new IndexedPropertyDescriptor("columnClass", MY_Table.class, null, null, "getColumnClass", null);

            properties[PROPERTY_columnCount] = new PropertyDescriptor("columnCount", MY_Table.class, "getColumnCount", null);

            properties[PROPERTY_columnModel] = new PropertyDescriptor("columnModel", MY_Table.class, "getColumnModel", "setColumnModel");

            properties[PROPERTY_columnName] = new IndexedPropertyDescriptor("columnName", MY_Table.class, null, null, "getColumnName", null);

            properties[PROPERTY_columnSelectionAllowed] = new PropertyDescriptor("columnSelectionAllowed", MY_Table.class, "getColumnSelectionAllowed", "setColumnSelectionAllowed");

            properties[PROPERTY_columnSelectionInterval] = new IndexedPropertyDescriptor("columnSelectionInterval", MY_Table.class, null, null, null, "setColumnSelectionInterval");

            properties[PROPERTY_component] = new IndexedPropertyDescriptor("component", MY_Table.class, null, null, "getComponent", null);

            properties[PROPERTY_componentCount] = new PropertyDescriptor("componentCount", MY_Table.class, "getComponentCount", null);

            properties[PROPERTY_componentListeners] = new PropertyDescriptor("componentListeners", MY_Table.class, "getComponentListeners", null);

            properties[PROPERTY_componentOrientation] = new PropertyDescriptor("componentOrientation", MY_Table.class, "getComponentOrientation", "setComponentOrientation");

            properties[PROPERTY_components] = new PropertyDescriptor("components", MY_Table.class, "getComponents", null);

            properties[PROPERTY_containerListeners] = new PropertyDescriptor("containerListeners", MY_Table.class, "getContainerListeners", null);

            properties[PROPERTY_cursor] = new PropertyDescriptor("cursor", MY_Table.class, "getCursor", "setCursor");

            properties[PROPERTY_cursorSet] = new PropertyDescriptor("cursorSet", MY_Table.class, "isCursorSet", null);

            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor("debugGraphicsOptions", MY_Table.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions");

            properties[PROPERTY_displayable] = new PropertyDescriptor("displayable", MY_Table.class, "isDisplayable", null);

            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor("doubleBuffered", MY_Table.class, "isDoubleBuffered", "setDoubleBuffered");

            properties[PROPERTY_dragEnabled] = new PropertyDescriptor("dragEnabled", MY_Table.class, "getDragEnabled", "setDragEnabled");

            properties[PROPERTY_dropTarget] = new PropertyDescriptor("dropTarget", MY_Table.class, "getDropTarget", "setDropTarget");

            properties[PROPERTY_editing] = new PropertyDescriptor("editing", MY_Table.class, "isEditing", null);

            properties[PROPERTY_editingColumn] = new PropertyDescriptor("editingColumn", MY_Table.class, "getEditingColumn", "setEditingColumn");

            properties[PROPERTY_editingRow] = new PropertyDescriptor("editingRow", MY_Table.class, "getEditingRow", "setEditingRow");

            properties[PROPERTY_editorComponent] = new PropertyDescriptor("editorComponent", MY_Table.class, "getEditorComponent", null);

            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", MY_Table.class, "isEnabled", "setEnabled");

            properties[PROPERTY_focusable] = new PropertyDescriptor("focusable", MY_Table.class, "isFocusable", "setFocusable");

            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor("focusCycleRoot", MY_Table.class, "isFocusCycleRoot", "setFocusCycleRoot");

            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor("focusCycleRootAncestor", MY_Table.class, "getFocusCycleRootAncestor", null);

            properties[PROPERTY_focusListeners] = new PropertyDescriptor("focusListeners", MY_Table.class, "getFocusListeners", null);

            properties[PROPERTY_focusOwner] = new PropertyDescriptor("focusOwner", MY_Table.class, "isFocusOwner", null);

            properties[PROPERTY_focusTraversable] = new PropertyDescriptor("focusTraversable", MY_Table.class, "isFocusTraversable", null);

            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor("focusTraversalKeys", MY_Table.class, null, null, "getFocusTraversalKeys", "setFocusTraversalKeys");

            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor("focusTraversalKeysEnabled", MY_Table.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled");

            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor("focusTraversalPolicy", MY_Table.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");

            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor("focusTraversalPolicySet", MY_Table.class, "isFocusTraversalPolicySet", null);

            properties[PROPERTY_font] = new PropertyDescriptor("font", MY_Table.class, "getFont", "setFont");

            properties[PROPERTY_fontSet] = new PropertyDescriptor("fontSet", MY_Table.class, "isFontSet", null);

            properties[PROPERTY_foreground] = new PropertyDescriptor("foreground", MY_Table.class, "getForeground", "setForeground");

            properties[PROPERTY_foregroundSet] = new PropertyDescriptor("foregroundSet", MY_Table.class, "isForegroundSet", null);

            properties[PROPERTY_graphics] = new PropertyDescriptor("graphics", MY_Table.class, "getGraphics", null);

            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor("graphicsConfiguration", MY_Table.class, "getGraphicsConfiguration", null);

            properties[PROPERTY_gridColor] = new PropertyDescriptor("gridColor", MY_Table.class, "getGridColor", "setGridColor");

            properties[PROPERTY_height] = new PropertyDescriptor("height", MY_Table.class, "getHeight", null);

            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor("hierarchyBoundsListeners", MY_Table.class, "getHierarchyBoundsListeners", null);

            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor("hierarchyListeners", MY_Table.class, "getHierarchyListeners", null);

            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor("ignoreRepaint", MY_Table.class, "getIgnoreRepaint", "setIgnoreRepaint");

            properties[PROPERTY_inputContext] = new PropertyDescriptor("inputContext", MY_Table.class, "getInputContext", null);

            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor("inputMethodListeners", MY_Table.class, "getInputMethodListeners", null);

            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor("inputMethodRequests", MY_Table.class, "getInputMethodRequests", null);

            properties[PROPERTY_inputVerifier] = new PropertyDescriptor("inputVerifier", MY_Table.class, "getInputVerifier", "setInputVerifier");

            properties[PROPERTY_insets] = new PropertyDescriptor("insets", MY_Table.class, "getInsets", null);

            properties[PROPERTY_intercellSpacing] = new PropertyDescriptor("intercellSpacing", MY_Table.class, "getIntercellSpacing", "setIntercellSpacing");

            properties[PROPERTY_keyListeners] = new PropertyDescriptor("keyListeners", MY_Table.class, "getKeyListeners", null);

            properties[PROPERTY_layout] = new PropertyDescriptor("layout", MY_Table.class, "getLayout", "setLayout");

            properties[PROPERTY_lightweight] = new PropertyDescriptor("lightweight", MY_Table.class, "isLightweight", null);

            properties[PROPERTY_locale] = new PropertyDescriptor("locale", MY_Table.class, "getLocale", "setLocale");

            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor("locationOnScreen", MY_Table.class, "getLocationOnScreen", null);

            properties[PROPERTY_managingFocus] = new PropertyDescriptor("managingFocus", MY_Table.class, "isManagingFocus", null);

            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", MY_Table.class, "getMaximumSize", "setMaximumSize");

            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor("maximumSizeSet", MY_Table.class, "isMaximumSizeSet", null);

            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", MY_Table.class, "getMinimumSize", "setMinimumSize");

            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor("minimumSizeSet", MY_Table.class, "isMinimumSizeSet", null);

            properties[PROPERTY_model] = new PropertyDescriptor("model", MY_Table.class, "getModel", "setModel");

            properties[PROPERTY_mouseListeners] = new PropertyDescriptor("mouseListeners", MY_Table.class, "getMouseListeners", null);

            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor("mouseMotionListeners", MY_Table.class, "getMouseMotionListeners", null);

            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor("mouseWheelListeners", MY_Table.class, "getMouseWheelListeners", null);

            properties[PROPERTY_name] = new PropertyDescriptor("name", MY_Table.class, "getName", "setName");

            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor("nextFocusableComponent", MY_Table.class, "getNextFocusableComponent", "setNextFocusableComponent");

            properties[PROPERTY_opaque] = new PropertyDescriptor("opaque", MY_Table.class, "isOpaque", "setOpaque");

            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor("optimizedDrawingEnabled", MY_Table.class, "isOptimizedDrawingEnabled", null);

            properties[PROPERTY_paintingTile] = new PropertyDescriptor("paintingTile", MY_Table.class, "isPaintingTile", null);

            properties[PROPERTY_parent] = new PropertyDescriptor("parent", MY_Table.class, "getParent", null);

            properties[PROPERTY_peer] = new PropertyDescriptor("peer", MY_Table.class, "getPeer", null);

            properties[PROPERTY_preferredScrollableViewportSize] = new PropertyDescriptor("preferredScrollableViewportSize", MY_Table.class, "getPreferredScrollableViewportSize", "setPreferredScrollableViewportSize");

            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", MY_Table.class, "getPreferredSize", "setPreferredSize");

            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor("preferredSizeSet", MY_Table.class, "isPreferredSizeSet", null);

            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor("propertyChangeListeners", MY_Table.class, "getPropertyChangeListeners", null);

            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor("registeredKeyStrokes", MY_Table.class, "getRegisteredKeyStrokes", null);

            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor("requestFocusEnabled", MY_Table.class, "isRequestFocusEnabled", "setRequestFocusEnabled");

            properties[PROPERTY_rootPane] = new PropertyDescriptor("rootPane", MY_Table.class, "getRootPane", null);

            properties[PROPERTY_rowCount] = new PropertyDescriptor("rowCount", MY_Table.class, "getRowCount", null);

            properties[PROPERTY_rowMargin] = new PropertyDescriptor("rowMargin", MY_Table.class, "getRowMargin", "setRowMargin");

            properties[PROPERTY_rowSelectionAllowed] = new PropertyDescriptor("rowSelectionAllowed", MY_Table.class, "getRowSelectionAllowed", "setRowSelectionAllowed");

            properties[PROPERTY_rowSelectionInterval] = new IndexedPropertyDescriptor("rowSelectionInterval", MY_Table.class, null, null, null, "setRowSelectionInterval");

            properties[PROPERTY_scrollableTracksViewportHeight] = new PropertyDescriptor("scrollableTracksViewportHeight", MY_Table.class, "getScrollableTracksViewportHeight", null);

            properties[PROPERTY_scrollableTracksViewportWidth] = new PropertyDescriptor("scrollableTracksViewportWidth", MY_Table.class, "getScrollableTracksViewportWidth", null);

            properties[PROPERTY_selectedColumn] = new PropertyDescriptor("selectedColumn", MY_Table.class, "getSelectedColumn", null);

            properties[PROPERTY_selectedColumnCount] = new PropertyDescriptor("selectedColumnCount", MY_Table.class, "getSelectedColumnCount", null);

            properties[PROPERTY_selectedColumns] = new PropertyDescriptor("selectedColumns", MY_Table.class, "getSelectedColumns", null);

            properties[PROPERTY_selectedRow] = new PropertyDescriptor("selectedRow", MY_Table.class, "getSelectedRow", null);

            properties[PROPERTY_selectedRowCount] = new PropertyDescriptor("selectedRowCount", MY_Table.class, "getSelectedRowCount", null);

            properties[PROPERTY_selectedRows] = new PropertyDescriptor("selectedRows", MY_Table.class, "getSelectedRows", null);

            properties[PROPERTY_selectionBackground] = new PropertyDescriptor("selectionBackground", MY_Table.class, "getSelectionBackground", "setSelectionBackground");

            properties[PROPERTY_selectionForeground] = new PropertyDescriptor("selectionForeground", MY_Table.class, "getSelectionForeground", "setSelectionForeground");

            properties[PROPERTY_selectionMode] = new PropertyDescriptor("selectionMode", MY_Table.class, null, "setSelectionMode");

            properties[PROPERTY_selectionModel] = new PropertyDescriptor("selectionModel", MY_Table.class, "getSelectionModel", "setSelectionModel");

            properties[PROPERTY_showGrid] = new PropertyDescriptor("showGrid", MY_Table.class, null, "setShowGrid");

            properties[PROPERTY_showHorizontalLines] = new PropertyDescriptor("showHorizontalLines", MY_Table.class, "getShowHorizontalLines", "setShowHorizontalLines");

            properties[PROPERTY_showing] = new PropertyDescriptor("showing", MY_Table.class, "isShowing", null);

            properties[PROPERTY_showVerticalLines] = new PropertyDescriptor("showVerticalLines", MY_Table.class, "getShowVerticalLines", "setShowVerticalLines");

            properties[PROPERTY_surrendersFocusOnKeystroke] = new PropertyDescriptor("surrendersFocusOnKeystroke", MY_Table.class, "getSurrendersFocusOnKeystroke", "setSurrendersFocusOnKeystroke");

            properties[PROPERTY_tableHeader] = new PropertyDescriptor("tableHeader", MY_Table.class, "getTableHeader", "setTableHeader");

            properties[PROPERTY_toolkit] = new PropertyDescriptor("toolkit", MY_Table.class, "getToolkit", null);

            properties[PROPERTY_toolTipText] = new PropertyDescriptor("toolTipText", MY_Table.class, "getToolTipText", "setToolTipText");

            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor("topLevelAncestor", MY_Table.class, "getTopLevelAncestor", null);

            properties[PROPERTY_transferHandler] = new PropertyDescriptor("transferHandler", MY_Table.class, "getTransferHandler", "setTransferHandler");

            properties[PROPERTY_treeLock] = new PropertyDescriptor("treeLock", MY_Table.class, "getTreeLock", null);

            properties[PROPERTY_UI] = new PropertyDescriptor("UI", MY_Table.class, "getUI", "setUI");

            properties[PROPERTY_UIClassID] = new PropertyDescriptor("UIClassID", MY_Table.class, "getUIClassID", null);

            properties[PROPERTY_valid] = new PropertyDescriptor("valid", MY_Table.class, "isValid", null);

            properties[PROPERTY_validateRoot] = new PropertyDescriptor("validateRoot", MY_Table.class, "isValidateRoot", null);

            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor("verifyInputWhenFocusTarget", MY_Table.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget");

            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor("vetoableChangeListeners", MY_Table.class, "getVetoableChangeListeners", null);

            properties[PROPERTY_visible] = new PropertyDescriptor("visible", MY_Table.class, "isVisible", "setVisible");

            properties[PROPERTY_visibleRect] = new PropertyDescriptor("visibleRect", MY_Table.class, "getVisibleRect", null);

            properties[PROPERTY_width] = new PropertyDescriptor("width", MY_Table.class, "getWidth", null);

            properties[PROPERTY_x] = new PropertyDescriptor("x", MY_Table.class, "getX", null);

            properties[PROPERTY_y] = new PropertyDescriptor("y", MY_Table.class, "getY", null);

        }

        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.


        return properties;
    }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    private static final int EVENT_ancestorListener = 0;

    private static final int EVENT_componentListener = 1;

    private static final int EVENT_containerListener = 2;

    private static final int EVENT_focusListener = 3;

    private static final int EVENT_hierarchyBoundsListener = 4;

    private static final int EVENT_hierarchyListener = 5;

    private static final int EVENT_inputMethodListener = 6;

    private static final int EVENT_keyListener = 7;

    private static final int EVENT_mouseListener = 8;

    private static final int EVENT_mouseMotionListener = 9;

    private static final int EVENT_mouseWheelListener = 10;

    private static final int EVENT_propertyChangeListener = 11;

    private static final int EVENT_vetoableChangeListener = 12;

    // EventSet array

    /*lazy EventSetDescriptor*/

    private static EventSetDescriptor[] getEdescriptor() {

        EventSetDescriptor[] eventSets = new EventSetDescriptor[13];


        try {

            eventSets[EVENT_ancestorListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[]{"ancestorAdded", "ancestorMoved", "ancestorRemoved"}, "addAncestorListener", "removeAncestorListener");

            eventSets[EVENT_componentListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "componentListener", java.awt.event.ComponentListener.class, new String[]{"componentHidden", "componentMoved", "componentResized", "componentShown"}, "addComponentListener", "removeComponentListener");

            eventSets[EVENT_containerListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "containerListener", java.awt.event.ContainerListener.class, new String[]{"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener");

            eventSets[EVENT_focusListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "focusListener", java.awt.event.FocusListener.class, new String[]{"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener");

            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[]{"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener");

            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[]{"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener");

            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[]{"caretPositionChanged", "inputMethodTextChanged"}, "addInputMethodListener", "removeInputMethodListener");

            eventSets[EVENT_keyListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "keyListener", java.awt.event.KeyListener.class, new String[]{"keyPressed", "keyReleased", "keyTyped"}, "addKeyListener", "removeKeyListener");

            eventSets[EVENT_mouseListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "mouseListener", java.awt.event.MouseListener.class, new String[]{"mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased"}, "addMouseListener", "removeMouseListener");

            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[]{"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener");

            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[]{"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener");

            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[]{"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener");

            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.frame.MY_Table.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[]{"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener");

        }

        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


        return eventSets;
    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods

    private static final int METHOD_action0 = 0;

    private static final int METHOD_add1 = 1;

    private static final int METHOD_addColumn2 = 2;

    private static final int METHOD_addColumnSelectionInterval3 = 3;

    private static final int METHOD_addNotify4 = 4;

    private static final int METHOD_addPropertyChangeListener5 = 5;

    private static final int METHOD_addRowSelectionInterval6 = 6;

    private static final int METHOD_applyComponentOrientation7 = 7;

    private static final int METHOD_areFocusTraversalKeysSet8 = 8;

    private static final int METHOD_bounds9 = 9;

    private static final int METHOD_changeSelection10 = 10;

    private static final int METHOD_checkImage11 = 11;

    private static final int METHOD_clearSelection12 = 12;

    private static final int METHOD_columnAdded13 = 13;

    private static final int METHOD_columnAtPoint14 = 14;

    private static final int METHOD_columnMarginChanged15 = 15;

    private static final int METHOD_columnMoved16 = 16;

    private static final int METHOD_columnRemoved17 = 17;

    private static final int METHOD_columnSelectionChanged18 = 18;

    private static final int METHOD_computeVisibleRect19 = 19;

    private static final int METHOD_contains20 = 20;

    private static final int METHOD_convertColumnIndexToModel21 = 21;

    private static final int METHOD_convertColumnIndexToView22 = 22;

    private static final int METHOD_countComponents23 = 23;

    private static final int METHOD_createDefaultColumnsFromModel24 = 24;

    private static final int METHOD_createImage25 = 25;

    private static final int METHOD_createScrollPaneForTable26 = 26;

    private static final int METHOD_createToolTip27 = 27;

    private static final int METHOD_createVolatileImage28 = 28;

    private static final int METHOD_deliverEvent29 = 29;

    private static final int METHOD_disable30 = 30;

    private static final int METHOD_dispatchEvent31 = 31;

    private static final int METHOD_doLayout32 = 32;

    private static final int METHOD_editCellAt33 = 33;

    private static final int METHOD_editingCanceled34 = 34;

    private static final int METHOD_editingStopped35 = 35;

    private static final int METHOD_enable36 = 36;

    private static final int METHOD_enableInputMethods37 = 37;

    private static final int METHOD_findComponentAt38 = 38;

    private static final int METHOD_firePropertyChange39 = 39;

    private static final int METHOD_getActionForKeyStroke40 = 40;

    private static final int METHOD_getBounds41 = 41;

    private static final int METHOD_getCellEditor42 = 42;

    private static final int METHOD_getCellRect43 = 43;

    private static final int METHOD_getCellRenderer44 = 44;

    private static final int METHOD_getClientProperty45 = 45;

    private static final int METHOD_getColumn46 = 46;

    private static final int METHOD_getComponentAt47 = 47;

    private static final int METHOD_getConditionForKeyStroke48 = 48;

    private static final int METHOD_getDefaultEditor49 = 49;

    private static final int METHOD_getDefaultLocale50 = 50;

    private static final int METHOD_getDefaultRenderer51 = 51;

    private static final int METHOD_getFontMetrics52 = 52;

    private static final int METHOD_getInputMap53 = 53;

    private static final int METHOD_getInsets54 = 54;

    private static final int METHOD_getListeners55 = 55;

    private static final int METHOD_getLocation56 = 56;

    private static final int METHOD_getPropertyChangeListeners57 = 57;

    private static final int METHOD_getRowHeight58 = 58;

    private static final int METHOD_getScrollableBlockIncrement59 = 59;

    private static final int METHOD_getScrollableUnitIncrement60 = 60;

    private static final int METHOD_getSize61 = 61;

    private static final int METHOD_getToolTipLocation62 = 62;

    private static final int METHOD_getToolTipText63 = 63;

    private static final int METHOD_getValueAt64 = 64;

    private static final int METHOD_gotFocus65 = 65;

    private static final int METHOD_grabFocus66 = 66;

    private static final int METHOD_handleEvent67 = 67;

    private static final int METHOD_hasFocus68 = 68;

    private static final int METHOD_hide69 = 69;

    private static final int METHOD_imageUpdate70 = 70;

    private static final int METHOD_insets71 = 71;

    private static final int METHOD_inside72 = 72;

    private static final int METHOD_invalidate73 = 73;

    private static final int METHOD_isAncestorOf74 = 74;

    private static final int METHOD_isCellEditable75 = 75;

    private static final int METHOD_isCellSelected76 = 76;

    private static final int METHOD_isColumnSelected77 = 77;

    private static final int METHOD_isFocusCycleRoot78 = 78;

    private static final int METHOD_isLightweightComponent79 = 79;

    private static final int METHOD_isRowSelected80 = 80;

    private static final int METHOD_keyDown81 = 81;

    private static final int METHOD_keyUp82 = 82;

    private static final int METHOD_layout83 = 83;

    private static final int METHOD_list84 = 84;

    private static final int METHOD_locate85 = 85;

    private static final int METHOD_location86 = 86;

    private static final int METHOD_lostFocus87 = 87;

    private static final int METHOD_minimumSize88 = 88;

    private static final int METHOD_mouseDown89 = 89;

    private static final int METHOD_mouseDrag90 = 90;

    private static final int METHOD_mouseEnter91 = 91;

    private static final int METHOD_mouseExit92 = 92;

    private static final int METHOD_mouseMove93 = 93;

    private static final int METHOD_mouseUp94 = 94;

    private static final int METHOD_move95 = 95;

    private static final int METHOD_moveColumn96 = 96;

    private static final int METHOD_nextFocus97 = 97;

    private static final int METHOD_paint98 = 98;

    private static final int METHOD_paintAll99 = 99;

    private static final int METHOD_paintComponents100 = 100;

    private static final int METHOD_paintImmediately101 = 101;

    private static final int METHOD_postEvent102 = 102;

    private static final int METHOD_preferredSize103 = 103;

    private static final int METHOD_prepareEditor104 = 104;

    private static final int METHOD_prepareImage105 = 105;

    private static final int METHOD_prepareRenderer106 = 106;

    private static final int METHOD_print107 = 107;

    private static final int METHOD_printAll108 = 108;

    private static final int METHOD_printComponents109 = 109;

    private static final int METHOD_putClientProperty110 = 110;

    private static final int METHOD_registerKeyboardAction111 = 111;

    private static final int METHOD_remove112 = 112;

    private static final int METHOD_removeAll113 = 113;

    private static final int METHOD_removeColumn114 = 114;

    private static final int METHOD_removeColumnSelectionInterval115 = 115;

    private static final int METHOD_removeEditor116 = 116;

    private static final int METHOD_removeNotify117 = 117;

    private static final int METHOD_removePropertyChangeListener118 = 118;

    private static final int METHOD_removeRowSelectionInterval119 = 119;

    private static final int METHOD_repaint120 = 120;

    private static final int METHOD_requestDefaultFocus121 = 121;

    private static final int METHOD_requestFocus122 = 122;

    private static final int METHOD_requestFocusInWindow123 = 123;

    private static final int METHOD_resetKeyboardActions124 = 124;

    private static final int METHOD_reshape125 = 125;

    private static final int METHOD_resize126 = 126;

    private static final int METHOD_revalidate127 = 127;

    private static final int METHOD_rowAtPoint128 = 128;

    private static final int METHOD_scrollRectToVisible129 = 129;

    private static final int METHOD_selectAll130 = 130;

    private static final int METHOD_setBounds131 = 131;

    private static final int METHOD_setDefaultEditor132 = 132;

    private static final int METHOD_setDefaultLocale133 = 133;

    private static final int METHOD_setDefaultRenderer134 = 134;

    private static final int METHOD_setInputMap135 = 135;

    private static final int METHOD_setLocation136 = 136;

    private static final int METHOD_setRowHeight137 = 137;

    private static final int METHOD_setSize138 = 138;

    private static final int METHOD_setValueAt139 = 139;

    private static final int METHOD_show140 = 140;

    private static final int METHOD_size141 = 141;

    private static final int METHOD_sizeColumnsToFit142 = 142;

    private static final int METHOD_tableChanged143 = 143;

    private static final int METHOD_toString144 = 144;

    private static final int METHOD_transferFocus145 = 145;

    private static final int METHOD_transferFocusBackward146 = 146;

    private static final int METHOD_transferFocusDownCycle147 = 147;

    private static final int METHOD_transferFocusUpCycle148 = 148;

    private static final int METHOD_unregisterKeyboardAction149 = 149;

    private static final int METHOD_update150 = 150;

    private static final int METHOD_updateUI151 = 151;

    private static final int METHOD_validate152 = 152;

    private static final int METHOD_valueChanged153 = 153;

    // Method array

    /*lazy MethodDescriptor*/

    private static MethodDescriptor[] getMdescriptor() {

        MethodDescriptor[] methods = new MethodDescriptor[154];


        try {

            methods[METHOD_action0] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("action", new Class[]{java.awt.Event.class, java.lang.Object.class}));

            methods[METHOD_action0].setDisplayName("");

            methods[METHOD_add1] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("add", new Class[]{java.awt.Component.class}));

            methods[METHOD_add1].setDisplayName("");

            methods[METHOD_addColumn2] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("addColumn", new Class[]{javax.swing.table.TableColumn.class}));

            methods[METHOD_addColumn2].setDisplayName("");

            methods[METHOD_addColumnSelectionInterval3] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("addColumnSelectionInterval", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_addColumnSelectionInterval3].setDisplayName("");

            methods[METHOD_addNotify4] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("addNotify", new Class[]{}));

            methods[METHOD_addNotify4].setDisplayName("");

            methods[METHOD_addPropertyChangeListener5] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("addPropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));

            methods[METHOD_addPropertyChangeListener5].setDisplayName("");

            methods[METHOD_addRowSelectionInterval6] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("addRowSelectionInterval", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_addRowSelectionInterval6].setDisplayName("");

            methods[METHOD_applyComponentOrientation7] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("applyComponentOrientation", new Class[]{java.awt.ComponentOrientation.class}));

            methods[METHOD_applyComponentOrientation7].setDisplayName("");

            methods[METHOD_areFocusTraversalKeysSet8] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("areFocusTraversalKeysSet", new Class[]{Integer.TYPE}));

            methods[METHOD_areFocusTraversalKeysSet8].setDisplayName("");

            methods[METHOD_bounds9] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("bounds", new Class[]{}));

            methods[METHOD_bounds9].setDisplayName("");

            methods[METHOD_changeSelection10] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("changeSelection", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE}));

            methods[METHOD_changeSelection10].setDisplayName("");

            methods[METHOD_checkImage11] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("checkImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));

            methods[METHOD_checkImage11].setDisplayName("");

            methods[METHOD_clearSelection12] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("clearSelection", new Class[]{}));

            methods[METHOD_clearSelection12].setDisplayName("");

            methods[METHOD_columnAdded13] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnAdded", new Class[]{javax.swing.event.TableColumnModelEvent.class}));

            methods[METHOD_columnAdded13].setDisplayName("");

            methods[METHOD_columnAtPoint14] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnAtPoint", new Class[]{java.awt.Point.class}));

            methods[METHOD_columnAtPoint14].setDisplayName("");

            methods[METHOD_columnMarginChanged15] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnMarginChanged", new Class[]{javax.swing.event.ChangeEvent.class}));

            methods[METHOD_columnMarginChanged15].setDisplayName("");

            methods[METHOD_columnMoved16] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnMoved", new Class[]{javax.swing.event.TableColumnModelEvent.class}));

            methods[METHOD_columnMoved16].setDisplayName("");

            methods[METHOD_columnRemoved17] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnRemoved", new Class[]{javax.swing.event.TableColumnModelEvent.class}));

            methods[METHOD_columnRemoved17].setDisplayName("");

            methods[METHOD_columnSelectionChanged18] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("columnSelectionChanged", new Class[]{javax.swing.event.ListSelectionEvent.class}));

            methods[METHOD_columnSelectionChanged18].setDisplayName("");

            methods[METHOD_computeVisibleRect19] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("computeVisibleRect", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_computeVisibleRect19].setDisplayName("");

            methods[METHOD_contains20] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("contains", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_contains20].setDisplayName("");

            methods[METHOD_convertColumnIndexToModel21] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("convertColumnIndexToModel", new Class[]{Integer.TYPE}));

            methods[METHOD_convertColumnIndexToModel21].setDisplayName("");

            methods[METHOD_convertColumnIndexToView22] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("convertColumnIndexToView", new Class[]{Integer.TYPE}));

            methods[METHOD_convertColumnIndexToView22].setDisplayName("");

            methods[METHOD_countComponents23] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("countComponents", new Class[]{}));

            methods[METHOD_countComponents23].setDisplayName("");

            methods[METHOD_createDefaultColumnsFromModel24] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("createDefaultColumnsFromModel", new Class[]{}));

            methods[METHOD_createDefaultColumnsFromModel24].setDisplayName("");

            methods[METHOD_createImage25] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("createImage", new Class[]{java.awt.image.ImageProducer.class}));

            methods[METHOD_createImage25].setDisplayName("");

            methods[METHOD_createScrollPaneForTable26] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("createScrollPaneForTable", new Class[]{javax.swing.JTable.class}));

            methods[METHOD_createScrollPaneForTable26].setDisplayName("");

            methods[METHOD_createToolTip27] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("createToolTip", new Class[]{}));

            methods[METHOD_createToolTip27].setDisplayName("");

            methods[METHOD_createVolatileImage28] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_createVolatileImage28].setDisplayName("");

            methods[METHOD_deliverEvent29] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("deliverEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_deliverEvent29].setDisplayName("");

            methods[METHOD_disable30] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("disable", new Class[]{}));

            methods[METHOD_disable30].setDisplayName("");

            methods[METHOD_dispatchEvent31] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("dispatchEvent", new Class[]{java.awt.AWTEvent.class}));

            methods[METHOD_dispatchEvent31].setDisplayName("");

            methods[METHOD_doLayout32] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("doLayout", new Class[]{}));

            methods[METHOD_doLayout32].setDisplayName("");

            methods[METHOD_editCellAt33] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("editCellAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_editCellAt33].setDisplayName("");

            methods[METHOD_editingCanceled34] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("editingCanceled", new Class[]{javax.swing.event.ChangeEvent.class}));

            methods[METHOD_editingCanceled34].setDisplayName("");

            methods[METHOD_editingStopped35] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("editingStopped", new Class[]{javax.swing.event.ChangeEvent.class}));

            methods[METHOD_editingStopped35].setDisplayName("");

            methods[METHOD_enable36] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("enable", new Class[]{}));

            methods[METHOD_enable36].setDisplayName("");

            methods[METHOD_enableInputMethods37] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("enableInputMethods", new Class[]{Boolean.TYPE}));

            methods[METHOD_enableInputMethods37].setDisplayName("");

            methods[METHOD_findComponentAt38] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("findComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_findComponentAt38].setDisplayName("");

            methods[METHOD_firePropertyChange39] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Byte.TYPE, Byte.TYPE}));

            methods[METHOD_firePropertyChange39].setDisplayName("");

            methods[METHOD_getActionForKeyStroke40] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getActionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_getActionForKeyStroke40].setDisplayName("");

            methods[METHOD_getBounds41] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getBounds", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_getBounds41].setDisplayName("");

            methods[METHOD_getCellEditor42] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getCellEditor", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getCellEditor42].setDisplayName("");

            methods[METHOD_getCellRect43] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getCellRect", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE}));

            methods[METHOD_getCellRect43].setDisplayName("");

            methods[METHOD_getCellRenderer44] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getCellRenderer", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getCellRenderer44].setDisplayName("");

            methods[METHOD_getClientProperty45] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getClientProperty", new Class[]{java.lang.Object.class}));

            methods[METHOD_getClientProperty45].setDisplayName("");

            methods[METHOD_getColumn46] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getColumn", new Class[]{java.lang.Object.class}));

            methods[METHOD_getColumn46].setDisplayName("");

            methods[METHOD_getComponentAt47] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getComponentAt47].setDisplayName("");

            methods[METHOD_getConditionForKeyStroke48] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getConditionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_getConditionForKeyStroke48].setDisplayName("");

            methods[METHOD_getDefaultEditor49] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getDefaultEditor", new Class[]{java.lang.Class.class}));

            methods[METHOD_getDefaultEditor49].setDisplayName("");

            methods[METHOD_getDefaultLocale50] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getDefaultLocale", new Class[]{}));

            methods[METHOD_getDefaultLocale50].setDisplayName("");

            methods[METHOD_getDefaultRenderer51] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getDefaultRenderer", new Class[]{java.lang.Class.class}));

            methods[METHOD_getDefaultRenderer51].setDisplayName("");

            methods[METHOD_getFontMetrics52] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getFontMetrics", new Class[]{java.awt.Font.class}));

            methods[METHOD_getFontMetrics52].setDisplayName("");

            methods[METHOD_getInputMap53] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getInputMap", new Class[]{Integer.TYPE}));

            methods[METHOD_getInputMap53].setDisplayName("");

            methods[METHOD_getInsets54] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getInsets", new Class[]{java.awt.Insets.class}));

            methods[METHOD_getInsets54].setDisplayName("");

            methods[METHOD_getListeners55] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getListeners", new Class[]{java.lang.Class.class}));

            methods[METHOD_getListeners55].setDisplayName("");

            methods[METHOD_getLocation56] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getLocation", new Class[]{java.awt.Point.class}));

            methods[METHOD_getLocation56].setDisplayName("");

            methods[METHOD_getPropertyChangeListeners57] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getPropertyChangeListeners", new Class[]{java.lang.String.class}));

            methods[METHOD_getPropertyChangeListeners57].setDisplayName("");

            methods[METHOD_getRowHeight58] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getRowHeight", new Class[]{}));

            methods[METHOD_getRowHeight58].setDisplayName("");

            methods[METHOD_getScrollableBlockIncrement59] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getScrollableBlockIncrement", new Class[]{java.awt.Rectangle.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getScrollableBlockIncrement59].setDisplayName("");

            methods[METHOD_getScrollableUnitIncrement60] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getScrollableUnitIncrement", new Class[]{java.awt.Rectangle.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getScrollableUnitIncrement60].setDisplayName("");

            methods[METHOD_getSize61] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getSize", new Class[]{java.awt.Dimension.class}));

            methods[METHOD_getSize61].setDisplayName("");

            methods[METHOD_getToolTipLocation62] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getToolTipLocation", new Class[]{java.awt.event.MouseEvent.class}));

            methods[METHOD_getToolTipLocation62].setDisplayName("");

            methods[METHOD_getToolTipText63] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getToolTipText", new Class[]{java.awt.event.MouseEvent.class}));

            methods[METHOD_getToolTipText63].setDisplayName("");

            methods[METHOD_getValueAt64] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("getValueAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getValueAt64].setDisplayName("");

            methods[METHOD_gotFocus65] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("gotFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));

            methods[METHOD_gotFocus65].setDisplayName("");

            methods[METHOD_grabFocus66] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("grabFocus", new Class[]{}));

            methods[METHOD_grabFocus66].setDisplayName("");

            methods[METHOD_handleEvent67] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("handleEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_handleEvent67].setDisplayName("");

            methods[METHOD_hasFocus68] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("hasFocus", new Class[]{}));

            methods[METHOD_hasFocus68].setDisplayName("");

            methods[METHOD_hide69] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("hide", new Class[]{}));

            methods[METHOD_hide69].setDisplayName("");

            methods[METHOD_imageUpdate70] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("imageUpdate", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_imageUpdate70].setDisplayName("");

            methods[METHOD_insets71] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("insets", new Class[]{}));

            methods[METHOD_insets71].setDisplayName("");

            methods[METHOD_inside72] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("inside", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_inside72].setDisplayName("");

            methods[METHOD_invalidate73] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("invalidate", new Class[]{}));

            methods[METHOD_invalidate73].setDisplayName("");

            methods[METHOD_isAncestorOf74] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isAncestorOf", new Class[]{java.awt.Component.class}));

            methods[METHOD_isAncestorOf74].setDisplayName("");

            methods[METHOD_isCellEditable75] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isCellEditable", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_isCellEditable75].setDisplayName("");

            methods[METHOD_isCellSelected76] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isCellSelected", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_isCellSelected76].setDisplayName("");

            methods[METHOD_isColumnSelected77] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isColumnSelected", new Class[]{Integer.TYPE}));

            methods[METHOD_isColumnSelected77].setDisplayName("");

            methods[METHOD_isFocusCycleRoot78] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isFocusCycleRoot", new Class[]{java.awt.Container.class}));

            methods[METHOD_isFocusCycleRoot78].setDisplayName("");

            methods[METHOD_isLightweightComponent79] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isLightweightComponent", new Class[]{java.awt.Component.class}));

            methods[METHOD_isLightweightComponent79].setDisplayName("");

            methods[METHOD_isRowSelected80] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("isRowSelected", new Class[]{Integer.TYPE}));

            methods[METHOD_isRowSelected80].setDisplayName("");

            methods[METHOD_keyDown81] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("keyDown", new Class[]{java.awt.Event.class, Integer.TYPE}));

            methods[METHOD_keyDown81].setDisplayName("");

            methods[METHOD_keyUp82] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("keyUp", new Class[]{java.awt.Event.class, Integer.TYPE}));

            methods[METHOD_keyUp82].setDisplayName("");

            methods[METHOD_layout83] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("layout", new Class[]{}));

            methods[METHOD_layout83].setDisplayName("");

            methods[METHOD_list84] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("list", new Class[]{java.io.PrintStream.class, Integer.TYPE}));

            methods[METHOD_list84].setDisplayName("");

            methods[METHOD_locate85] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("locate", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_locate85].setDisplayName("");

            methods[METHOD_location86] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("location", new Class[]{}));

            methods[METHOD_location86].setDisplayName("");

            methods[METHOD_lostFocus87] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("lostFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));

            methods[METHOD_lostFocus87].setDisplayName("");

            methods[METHOD_minimumSize88] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("minimumSize", new Class[]{}));

            methods[METHOD_minimumSize88].setDisplayName("");

            methods[METHOD_mouseDown89] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseDown", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseDown89].setDisplayName("");

            methods[METHOD_mouseDrag90] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseDrag", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseDrag90].setDisplayName("");

            methods[METHOD_mouseEnter91] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseEnter", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseEnter91].setDisplayName("");

            methods[METHOD_mouseExit92] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseExit", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseExit92].setDisplayName("");

            methods[METHOD_mouseMove93] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseMove", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseMove93].setDisplayName("");

            methods[METHOD_mouseUp94] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("mouseUp", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseUp94].setDisplayName("");

            methods[METHOD_move95] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("move", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_move95].setDisplayName("");

            methods[METHOD_moveColumn96] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("moveColumn", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_moveColumn96].setDisplayName("");

            methods[METHOD_nextFocus97] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("nextFocus", new Class[]{}));

            methods[METHOD_nextFocus97].setDisplayName("");

            methods[METHOD_paint98] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("paint", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paint98].setDisplayName("");

            methods[METHOD_paintAll99] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("paintAll", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paintAll99].setDisplayName("");

            methods[METHOD_paintComponents100] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("paintComponents", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paintComponents100].setDisplayName("");

            methods[METHOD_paintImmediately101] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("paintImmediately", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_paintImmediately101].setDisplayName("");

            methods[METHOD_postEvent102] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("postEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_postEvent102].setDisplayName("");

            methods[METHOD_preferredSize103] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("preferredSize", new Class[]{}));

            methods[METHOD_preferredSize103].setDisplayName("");

            methods[METHOD_prepareEditor104] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("prepareEditor", new Class[]{javax.swing.table.TableCellEditor.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_prepareEditor104].setDisplayName("");

            methods[METHOD_prepareImage105] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));

            methods[METHOD_prepareImage105].setDisplayName("");

            methods[METHOD_prepareRenderer106] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("prepareRenderer", new Class[]{javax.swing.table.TableCellRenderer.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_prepareRenderer106].setDisplayName("");

            methods[METHOD_print107] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("print", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_print107].setDisplayName("");

            methods[METHOD_printAll108] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("printAll", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_printAll108].setDisplayName("");

            methods[METHOD_printComponents109] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("printComponents", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_printComponents109].setDisplayName("");

            methods[METHOD_putClientProperty110] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("putClientProperty", new Class[]{java.lang.Object.class, java.lang.Object.class}));

            methods[METHOD_putClientProperty110].setDisplayName("");

            methods[METHOD_registerKeyboardAction111] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));

            methods[METHOD_registerKeyboardAction111].setDisplayName("");

            methods[METHOD_remove112] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("remove", new Class[]{Integer.TYPE}));

            methods[METHOD_remove112].setDisplayName("");

            methods[METHOD_removeAll113] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeAll", new Class[]{}));

            methods[METHOD_removeAll113].setDisplayName("");

            methods[METHOD_removeColumn114] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeColumn", new Class[]{javax.swing.table.TableColumn.class}));

            methods[METHOD_removeColumn114].setDisplayName("");

            methods[METHOD_removeColumnSelectionInterval115] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeColumnSelectionInterval", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_removeColumnSelectionInterval115].setDisplayName("");

            methods[METHOD_removeEditor116] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeEditor", new Class[]{}));

            methods[METHOD_removeEditor116].setDisplayName("");

            methods[METHOD_removeNotify117] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeNotify", new Class[]{}));

            methods[METHOD_removeNotify117].setDisplayName("");

            methods[METHOD_removePropertyChangeListener118] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removePropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));

            methods[METHOD_removePropertyChangeListener118].setDisplayName("");

            methods[METHOD_removeRowSelectionInterval119] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("removeRowSelectionInterval", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_removeRowSelectionInterval119].setDisplayName("");

            methods[METHOD_repaint120] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("repaint", new Class[]{Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_repaint120].setDisplayName("");

            methods[METHOD_requestDefaultFocus121] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("requestDefaultFocus", new Class[]{}));

            methods[METHOD_requestDefaultFocus121].setDisplayName("");

            methods[METHOD_requestFocus122] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("requestFocus", new Class[]{}));

            methods[METHOD_requestFocus122].setDisplayName("");

            methods[METHOD_requestFocusInWindow123] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("requestFocusInWindow", new Class[]{}));

            methods[METHOD_requestFocusInWindow123].setDisplayName("");

            methods[METHOD_resetKeyboardActions124] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("resetKeyboardActions", new Class[]{}));

            methods[METHOD_resetKeyboardActions124].setDisplayName("");

            methods[METHOD_reshape125] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("reshape", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_reshape125].setDisplayName("");

            methods[METHOD_resize126] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("resize", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_resize126].setDisplayName("");

            methods[METHOD_revalidate127] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("revalidate", new Class[]{}));

            methods[METHOD_revalidate127].setDisplayName("");

            methods[METHOD_rowAtPoint128] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("rowAtPoint", new Class[]{java.awt.Point.class}));

            methods[METHOD_rowAtPoint128].setDisplayName("");

            methods[METHOD_scrollRectToVisible129] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("scrollRectToVisible", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_scrollRectToVisible129].setDisplayName("");

            methods[METHOD_selectAll130] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("selectAll", new Class[]{}));

            methods[METHOD_selectAll130].setDisplayName("");

            methods[METHOD_setBounds131] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setBounds", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setBounds131].setDisplayName("");

            methods[METHOD_setDefaultEditor132] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setDefaultEditor", new Class[]{java.lang.Class.class, javax.swing.table.TableCellEditor.class}));

            methods[METHOD_setDefaultEditor132].setDisplayName("");

            methods[METHOD_setDefaultLocale133] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setDefaultLocale", new Class[]{java.util.Locale.class}));

            methods[METHOD_setDefaultLocale133].setDisplayName("");

            methods[METHOD_setDefaultRenderer134] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setDefaultRenderer", new Class[]{java.lang.Class.class, javax.swing.table.TableCellRenderer.class}));

            methods[METHOD_setDefaultRenderer134].setDisplayName("");

            methods[METHOD_setInputMap135] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setInputMap", new Class[]{Integer.TYPE, javax.swing.InputMap.class}));

            methods[METHOD_setInputMap135].setDisplayName("");

            methods[METHOD_setLocation136] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setLocation", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setLocation136].setDisplayName("");

            methods[METHOD_setRowHeight137] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setRowHeight", new Class[]{Integer.TYPE}));

            methods[METHOD_setRowHeight137].setDisplayName("");

            methods[METHOD_setSize138] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setSize", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setSize138].setDisplayName("");

            methods[METHOD_setValueAt139] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("setValueAt", new Class[]{java.lang.Object.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setValueAt139].setDisplayName("");

            methods[METHOD_show140] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("show", new Class[]{}));

            methods[METHOD_show140].setDisplayName("");

            methods[METHOD_size141] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("size", new Class[]{}));

            methods[METHOD_size141].setDisplayName("");

            methods[METHOD_sizeColumnsToFit142] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("sizeColumnsToFit", new Class[]{Boolean.TYPE}));

            methods[METHOD_sizeColumnsToFit142].setDisplayName("");

            methods[METHOD_tableChanged143] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("tableChanged", new Class[]{javax.swing.event.TableModelEvent.class}));

            methods[METHOD_tableChanged143].setDisplayName("");

            methods[METHOD_toString144] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("toString", new Class[]{}));

            methods[METHOD_toString144].setDisplayName("");

            methods[METHOD_transferFocus145] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("transferFocus", new Class[]{}));

            methods[METHOD_transferFocus145].setDisplayName("");

            methods[METHOD_transferFocusBackward146] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("transferFocusBackward", new Class[]{}));

            methods[METHOD_transferFocusBackward146].setDisplayName("");

            methods[METHOD_transferFocusDownCycle147] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("transferFocusDownCycle", new Class[]{}));

            methods[METHOD_transferFocusDownCycle147].setDisplayName("");

            methods[METHOD_transferFocusUpCycle148] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("transferFocusUpCycle", new Class[]{}));

            methods[METHOD_transferFocusUpCycle148].setDisplayName("");

            methods[METHOD_unregisterKeyboardAction149] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("unregisterKeyboardAction", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_unregisterKeyboardAction149].setDisplayName("");

            methods[METHOD_update150] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("update", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_update150].setDisplayName("");

            methods[METHOD_updateUI151] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("updateUI", new Class[]{}));

            methods[METHOD_updateUI151].setDisplayName("");

            methods[METHOD_validate152] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("validate", new Class[]{}));

            methods[METHOD_validate152].setDisplayName("");

            methods[METHOD_valueChanged153] = new MethodDescriptor(srcastra.astra.gui.components.frame.MY_Table.class.getMethod("valueChanged", new Class[]{javax.swing.event.ListSelectionEvent.class}));

            methods[METHOD_valueChanged153].setDisplayName("");

        }

        catch (Exception e) {
        }//GEN-HEADEREND:Methods

        // Here you can add code for customizing the methods array.


        return methods;
    }//GEN-LAST:Methods


    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx

    private static final int defaultEventIndex = -1;//GEN-END:Idx

    //GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

    //GEN-LAST:Superclass


    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable
     *         <p/>
     *         properties of this bean.  May return null if the
     *         <p/>
     *         information should be obtained by automatic analysis.
     */

    public BeanDescriptor getBeanDescriptor() {

        return getBdescriptor();

    }


    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     *         <p/>
     *         properties supported by this bean.  May return null if the
     *         <p/>
     *         information should be obtained by automatic analysis.
     *         <p/>
     *         <p/>
     *         <p/>
     *         If a property is indexed, then its entry in the result array will
     *         <p/>
     *         belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     *         <p/>
     *         A client of getPropertyDescriptors can use "instanceof" to check
     *         <p/>
     *         if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */

    public PropertyDescriptor[] getPropertyDescriptors() {

        return getPdescriptor();

    }


    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of
     *         <p/>
     *         events fired by this bean.  May return null if the information
     *         <p/>
     *         should be obtained by automatic analysis.
     */

    public EventSetDescriptor[] getEventSetDescriptors() {

        return getEdescriptor();

    }


    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods
     *         <p/>
     *         implemented by this bean.  May return null if the information
     *         <p/>
     *         should be obtained by automatic analysis.
     */

    public MethodDescriptor[] getMethodDescriptors() {

        return getMdescriptor();

    }


    /**
     * A bean may have a "default" property that is the property that will
     * <p/>
     * mostly commonly be initially chosen for update by human's who are
     * <p/>
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     *         <p/>
     *         returned by getPropertyDescriptors.
     *         <p/>
     *         <P>	Returns -1 if there is no default property.
     */

    public int getDefaultPropertyIndex() {

        return defaultPropertyIndex;

    }


    /**
     * A bean may have a "default" event that is the event that will
     * <p/>
     * mostly commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array
     *         <p/>
     *         returned by getEventSetDescriptors.
     *         <p/>
     *         <P>	Returns -1 if there is no default event.
     */

    public int getDefaultEventIndex() {

        return defaultEventIndex;

    }

}



