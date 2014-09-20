package srcastra.astra.gui.components.combobox.aCombo;


import java.beans.*;


public class AComboBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/;


    private static BeanDescriptor getBdescriptor() {


        BeanDescriptor beanDescriptor = new BeanDescriptor(ACombo.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties


    private static final int PROPERTY_registeredKeyStrokes = 0;


    private static final int PROPERTY_valid = 1;


    private static final int PROPERTY_lastFocusedComponent = 2;


    private static final int PROPERTY_y = 3;


    private static final int PROPERTY_insets = 4;


    private static final int PROPERTY_focusCycleRoot = 5;


    private static final int PROPERTY_maximumSizeSet = 6;


    private static final int PROPERTY_preferredSizeSet = 7;


    private static final int PROPERTY_UIClassID = 8;


    private static final int PROPERTY_verifyInputWhenFocusTarget = 9;


    private static final int PROPERTY_propertyChangeListeners = 10;


    private static final int PROPERTY_alignmentY = 11;


    private static final int PROPERTY_grp_Comp_nextComponent = 12;


    private static final int PROPERTY_doubleBuffered = 13;


    private static final int PROPERTY_font = 14;


    private static final int PROPERTY_focusListeners = 15;


    private static final int PROPERTY_width = 16;


    private static final int PROPERTY_mouseMotionListeners = 17;


    private static final int PROPERTY_foreground = 18;


    private static final int PROPERTY_componentListeners = 19;


    private static final int PROPERTY_maximumSize = 20;


    private static final int PROPERTY_enabled = 21;


    private static final int PROPERTY_inputVerifier = 22;


    private static final int PROPERTY_debugGraphicsOptions = 23;


    private static final int PROPERTY_containerListeners = 24;


    private static final int PROPERTY_selectionColor = 25;


    private static final int PROPERTY_focusTraversable = 26;


    private static final int PROPERTY_toolTipText = 27;


    private static final int PROPERTY_inputMethodRequests = 28;


    private static final int PROPERTY_minimumSize = 29;


    private static final int PROPERTY_text = 30;


    private static final int PROPERTY_ancestorListeners = 31;


    private static final int PROPERTY_graphicsConfiguration = 32;


    private static final int PROPERTY_parent = 33;


    private static final int PROPERTY_focusTraversalPolicySet = 34;


    private static final int PROPERTY_mouseWheelListeners = 35;


    private static final int PROPERTY_model = 36;


    private static final int PROPERTY_height = 37;


    private static final int PROPERTY_opaque = 38;


    private static final int PROPERTY_keyListeners = 39;


    private static final int PROPERTY_foregroundSet = 40;


    private static final int PROPERTY_selectedCleUnik = 41;


    private static final int PROPERTY_accessibleContext = 42;


    private static final int PROPERTY_focusTraversalPolicy = 43;


    private static final int PROPERTY_hierarchyBoundsListeners = 44;


    private static final int PROPERTY_UI = 45;


    private static final int PROPERTY_paintingTile = 46;


    private static final int PROPERTY_vetoableChangeListeners = 47;


    private static final int PROPERTY_hierarchyListeners = 48;


    private static final int PROPERTY_focusTraversalKeysEnabled = 49;


    private static final int PROPERTY_colorModel = 50;


    private static final int PROPERTY_x = 51;


    private static final int PROPERTY_requestFocusEnabled = 52;


    private static final int PROPERTY_selectedIndex = 53;


    private static final int PROPERTY_visibleRect = 54;


    private static final int PROPERTY_visible = 55;


    private static final int PROPERTY_rootPane = 56;


    private static final int PROPERTY_treeLock = 57;


    private static final int PROPERTY_selectedText = 58;


    private static final int PROPERTY_focusCycleRootAncestor = 59;


    private static final int PROPERTY_peer = 60;


    private static final int PROPERTY_dropTarget = 61;


    private static final int PROPERTY_transferHandler = 62;


    private static final int PROPERTY_selectedTextColor = 63;


    private static final int PROPERTY_locale = 64;


    private static final int PROPERTY_ignoreRepaint = 65;


    private static final int PROPERTY_cursor = 66;


    private static final int PROPERTY_alignmentX = 67;


    private static final int PROPERTY_backgroundSet = 68;


    private static final int PROPERTY_optimizedDrawingEnabled = 69;


    private static final int PROPERTY_workingIcon = 70;


    private static final int PROPERTY_actionMap = 71;


    private static final int PROPERTY_showing = 72;


    private static final int PROPERTY_toolkit = 73;


    private static final int PROPERTY_nextFocusableComponent = 74;


    private static final int PROPERTY_focusOwner = 75;


    private static final int PROPERTY_autoscrolls = 76;


    private static final int PROPERTY_bounds = 77;


    private static final int PROPERTY_inputMethodListeners = 78;


    private static final int PROPERTY_minimumSizeSet = 79;


    private static final int PROPERTY_focusable = 80;


    private static final int PROPERTY_background = 81;


    private static final int PROPERTY_cursorSet = 82;


    private static final int PROPERTY_border = 83;


    private static final int PROPERTY_layout = 84;


    private static final int PROPERTY_topLevelAncestor = 85;


    private static final int PROPERTY_preferredSize = 86;


    private static final int PROPERTY_displayable = 87;


    private static final int PROPERTY_mouseListeners = 88;


    private static final int PROPERTY_validateRoot = 89;


    private static final int PROPERTY_components = 90;


    private static final int PROPERTY_managingFocus = 91;


    private static final int PROPERTY_fontSet = 92;


    private static final int PROPERTY_componentOrientation = 93;


    private static final int PROPERTY_componentCount = 94;


    private static final int PROPERTY_lightweight = 95;


    private static final int PROPERTY_name = 96;


    private static final int PROPERTY_graphics = 97;


    private static final int PROPERTY_inputContext = 98;


    private static final int PROPERTY_locationOnScreen = 99;


    private static final int PROPERTY_verif = 100;


    private static final int PROPERTY_component = 101;


    private static final int PROPERTY_focusTraversalKeys = 102;

    // Property array

    /*lazy PropertyDescriptor*/;


    private static PropertyDescriptor[] getPdescriptor() {


        PropertyDescriptor[] properties = new PropertyDescriptor[103];


        try {


            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor("registeredKeyStrokes", ACombo.class, "getRegisteredKeyStrokes", null);


            properties[PROPERTY_valid] = new PropertyDescriptor("valid", ACombo.class, "isValid", null);


            properties[PROPERTY_lastFocusedComponent] = new PropertyDescriptor("lastFocusedComponent", ACombo.class, "isLastFocusedComponent", "setLastFocusedComponent");


            properties[PROPERTY_y] = new PropertyDescriptor("y", ACombo.class, "getY", null);


            properties[PROPERTY_insets] = new PropertyDescriptor("insets", ACombo.class, "getInsets", null);


            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor("focusCycleRoot", ACombo.class, "isFocusCycleRoot", "setFocusCycleRoot");


            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor("maximumSizeSet", ACombo.class, "isMaximumSizeSet", null);


            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor("preferredSizeSet", ACombo.class, "isPreferredSizeSet", null);


            properties[PROPERTY_UIClassID] = new PropertyDescriptor("UIClassID", ACombo.class, "getUIClassID", null);


            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor("verifyInputWhenFocusTarget", ACombo.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget");


            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor("propertyChangeListeners", ACombo.class, "getPropertyChangeListeners", null);


            properties[PROPERTY_alignmentY] = new PropertyDescriptor("alignmentY", ACombo.class, "getAlignmentY", "setAlignmentY");


            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", ACombo.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");


            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor("doubleBuffered", ACombo.class, "isDoubleBuffered", "setDoubleBuffered");


            properties[PROPERTY_font] = new PropertyDescriptor("font", ACombo.class, "getFont", "setFont");


            properties[PROPERTY_focusListeners] = new PropertyDescriptor("focusListeners", ACombo.class, "getFocusListeners", null);


            properties[PROPERTY_width] = new PropertyDescriptor("width", ACombo.class, "getWidth", null);


            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor("mouseMotionListeners", ACombo.class, "getMouseMotionListeners", null);


            properties[PROPERTY_foreground] = new PropertyDescriptor("foreground", ACombo.class, "getForeground", "setForeground");


            properties[PROPERTY_componentListeners] = new PropertyDescriptor("componentListeners", ACombo.class, "getComponentListeners", null);


            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", ACombo.class, "getMaximumSize", "setMaximumSize");


            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", ACombo.class, "isEnabled", "setEnabled");


            properties[PROPERTY_inputVerifier] = new PropertyDescriptor("inputVerifier", ACombo.class, "getInputVerifier", "setInputVerifier");


            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor("debugGraphicsOptions", ACombo.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions");


            properties[PROPERTY_containerListeners] = new PropertyDescriptor("containerListeners", ACombo.class, "getContainerListeners", null);


            properties[PROPERTY_selectionColor] = new PropertyDescriptor("selectionColor", ACombo.class, "getSelectionColor", "setSelectionColor");


            properties[PROPERTY_focusTraversable] = new PropertyDescriptor("focusTraversable", ACombo.class, "isFocusTraversable", null);


            properties[PROPERTY_toolTipText] = new PropertyDescriptor("toolTipText", ACombo.class, "getToolTipText", "setToolTipText");


            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor("inputMethodRequests", ACombo.class, "getInputMethodRequests", null);


            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", ACombo.class, "getMinimumSize", "setMinimumSize");


            properties[PROPERTY_text] = new PropertyDescriptor("text", ACombo.class, null, "setText");


            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor("ancestorListeners", ACombo.class, "getAncestorListeners", null);


            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor("graphicsConfiguration", ACombo.class, "getGraphicsConfiguration", null);


            properties[PROPERTY_parent] = new PropertyDescriptor("parent", ACombo.class, "getParent", null);


            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor("focusTraversalPolicySet", ACombo.class, "isFocusTraversalPolicySet", null);


            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor("mouseWheelListeners", ACombo.class, "getMouseWheelListeners", null);


            properties[PROPERTY_model] = new PropertyDescriptor("model", ACombo.class, "getModel", "setModel");


            properties[PROPERTY_height] = new PropertyDescriptor("height", ACombo.class, "getHeight", null);


            properties[PROPERTY_opaque] = new PropertyDescriptor("opaque", ACombo.class, "isOpaque", "setOpaque");


            properties[PROPERTY_keyListeners] = new PropertyDescriptor("keyListeners", ACombo.class, "getKeyListeners", null);


            properties[PROPERTY_foregroundSet] = new PropertyDescriptor("foregroundSet", ACombo.class, "isForegroundSet", null);


            properties[PROPERTY_selectedCleUnik] = new PropertyDescriptor("selectedCleUnik", ACombo.class, "getSelectedCleUnik", "setSelectedCleUnik");


            properties[PROPERTY_accessibleContext] = new PropertyDescriptor("accessibleContext", ACombo.class, "getAccessibleContext", null);


            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor("focusTraversalPolicy", ACombo.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");


            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor("hierarchyBoundsListeners", ACombo.class, "getHierarchyBoundsListeners", null);


            properties[PROPERTY_UI] = new PropertyDescriptor("UI", ACombo.class, "getUI", "setUI");


            properties[PROPERTY_paintingTile] = new PropertyDescriptor("paintingTile", ACombo.class, "isPaintingTile", null);


            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor("vetoableChangeListeners", ACombo.class, "getVetoableChangeListeners", null);


            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor("hierarchyListeners", ACombo.class, "getHierarchyListeners", null);


            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor("focusTraversalKeysEnabled", ACombo.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled");


            properties[PROPERTY_colorModel] = new PropertyDescriptor("colorModel", ACombo.class, "getColorModel", null);


            properties[PROPERTY_x] = new PropertyDescriptor("x", ACombo.class, "getX", null);


            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor("requestFocusEnabled", ACombo.class, "isRequestFocusEnabled", "setRequestFocusEnabled");


            properties[PROPERTY_selectedIndex] = new PropertyDescriptor("selectedIndex", ACombo.class, "getSelectedIndex", "setSelectedIndex");


            properties[PROPERTY_visibleRect] = new PropertyDescriptor("visibleRect", ACombo.class, "getVisibleRect", null);


            properties[PROPERTY_visible] = new PropertyDescriptor("visible", ACombo.class, "isVisible", "setVisible");


            properties[PROPERTY_rootPane] = new PropertyDescriptor("rootPane", ACombo.class, "getRootPane", null);


            properties[PROPERTY_treeLock] = new PropertyDescriptor("treeLock", ACombo.class, "getTreeLock", null);


            properties[PROPERTY_selectedText] = new PropertyDescriptor("selectedText", ACombo.class, "getSelectedText", null);


            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor("focusCycleRootAncestor", ACombo.class, "getFocusCycleRootAncestor", null);


            properties[PROPERTY_peer] = new PropertyDescriptor("peer", ACombo.class, "getPeer", null);


            properties[PROPERTY_dropTarget] = new PropertyDescriptor("dropTarget", ACombo.class, "getDropTarget", "setDropTarget");


            properties[PROPERTY_transferHandler] = new PropertyDescriptor("transferHandler", ACombo.class, "getTransferHandler", "setTransferHandler");


            properties[PROPERTY_selectedTextColor] = new PropertyDescriptor("selectedTextColor", ACombo.class, "getSelectedTextColor", "setSelectedTextColor");


            properties[PROPERTY_locale] = new PropertyDescriptor("locale", ACombo.class, "getLocale", "setLocale");


            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor("ignoreRepaint", ACombo.class, "getIgnoreRepaint", "setIgnoreRepaint");


            properties[PROPERTY_cursor] = new PropertyDescriptor("cursor", ACombo.class, "getCursor", "setCursor");


            properties[PROPERTY_alignmentX] = new PropertyDescriptor("alignmentX", ACombo.class, "getAlignmentX", "setAlignmentX");


            properties[PROPERTY_backgroundSet] = new PropertyDescriptor("backgroundSet", ACombo.class, "isBackgroundSet", null);


            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor("optimizedDrawingEnabled", ACombo.class, "isOptimizedDrawingEnabled", null);


            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", ACombo.class, "getWorkingIcon", "setWorkingIcon");


            properties[PROPERTY_actionMap] = new PropertyDescriptor("actionMap", ACombo.class, "getActionMap", "setActionMap");


            properties[PROPERTY_showing] = new PropertyDescriptor("showing", ACombo.class, "isShowing", null);


            properties[PROPERTY_toolkit] = new PropertyDescriptor("toolkit", ACombo.class, "getToolkit", null);


            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor("nextFocusableComponent", ACombo.class, "getNextFocusableComponent", "setNextFocusableComponent");


            properties[PROPERTY_focusOwner] = new PropertyDescriptor("focusOwner", ACombo.class, "isFocusOwner", null);


            properties[PROPERTY_autoscrolls] = new PropertyDescriptor("autoscrolls", ACombo.class, "getAutoscrolls", "setAutoscrolls");


            properties[PROPERTY_bounds] = new PropertyDescriptor("bounds", ACombo.class, "getBounds", "setBounds");


            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor("inputMethodListeners", ACombo.class, "getInputMethodListeners", null);


            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor("minimumSizeSet", ACombo.class, "isMinimumSizeSet", null);


            properties[PROPERTY_focusable] = new PropertyDescriptor("focusable", ACombo.class, "isFocusable", "setFocusable");


            properties[PROPERTY_background] = new PropertyDescriptor("background", ACombo.class, "getBackground", "setBackground");


            properties[PROPERTY_cursorSet] = new PropertyDescriptor("cursorSet", ACombo.class, "isCursorSet", null);


            properties[PROPERTY_border] = new PropertyDescriptor("border", ACombo.class, "getBorder", "setBorder");


            properties[PROPERTY_layout] = new PropertyDescriptor("layout", ACombo.class, "getLayout", "setLayout");


            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor("topLevelAncestor", ACombo.class, "getTopLevelAncestor", null);


            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", ACombo.class, "getPreferredSize", "setPreferredSize");


            properties[PROPERTY_displayable] = new PropertyDescriptor("displayable", ACombo.class, "isDisplayable", null);


            properties[PROPERTY_mouseListeners] = new PropertyDescriptor("mouseListeners", ACombo.class, "getMouseListeners", null);


            properties[PROPERTY_validateRoot] = new PropertyDescriptor("validateRoot", ACombo.class, "isValidateRoot", null);


            properties[PROPERTY_components] = new PropertyDescriptor("components", ACombo.class, "getComponents", null);


            properties[PROPERTY_managingFocus] = new PropertyDescriptor("managingFocus", ACombo.class, "isManagingFocus", null);


            properties[PROPERTY_fontSet] = new PropertyDescriptor("fontSet", ACombo.class, "isFontSet", null);


            properties[PROPERTY_componentOrientation] = new PropertyDescriptor("componentOrientation", ACombo.class, "getComponentOrientation", "setComponentOrientation");


            properties[PROPERTY_componentCount] = new PropertyDescriptor("componentCount", ACombo.class, "getComponentCount", null);


            properties[PROPERTY_lightweight] = new PropertyDescriptor("lightweight", ACombo.class, "isLightweight", null);


            properties[PROPERTY_name] = new PropertyDescriptor("name", ACombo.class, "getName", "setName");


            properties[PROPERTY_graphics] = new PropertyDescriptor("graphics", ACombo.class, "getGraphics", null);


            properties[PROPERTY_inputContext] = new PropertyDescriptor("inputContext", ACombo.class, "getInputContext", null);


            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor("locationOnScreen", ACombo.class, "getLocationOnScreen", null);


            properties[PROPERTY_verif] = new PropertyDescriptor("verif", ACombo.class, "getverif", null);


            properties[PROPERTY_component] = new IndexedPropertyDescriptor("component", ACombo.class, null, null, "getComponent", null);


            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor("focusTraversalKeys", ACombo.class, null, null, "getFocusTraversalKeys", "setFocusTraversalKeys");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.


        return properties;
    }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events


    private static final int EVENT_inputMethodListener = 0;


    private static final int EVENT_mouseMotionListener = 1;


    private static final int EVENT_hierarchyBoundsListener = 2;


    private static final int EVENT_containerListener = 3;


    private static final int EVENT_ancestorListener = 4;


    private static final int EVENT_focusListener = 5;


    private static final int EVENT_propertyChangeListener = 6;


    private static final int EVENT_vetoableChangeListener = 7;


    private static final int EVENT_keyListener = 8;


    private static final int EVENT_actionListener = 9;


    private static final int EVENT_componentListener = 10;


    private static final int EVENT_mouseListener = 11;


    private static final int EVENT_hierarchyListener = 12;


    private static final int EVENT_mouseWheelListener = 13;

    // EventSet array

    /*lazy EventSetDescriptor*/;


    private static EventSetDescriptor[] getEdescriptor() {


        EventSetDescriptor[] eventSets = new EventSetDescriptor[14];


        try {


            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[]{"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener");


            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[]{"mouseMoved", "mouseDragged"}, "addMouseMotionListener", "removeMouseMotionListener");


            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[]{"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener");


            eventSets[EVENT_containerListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "containerListener", java.awt.event.ContainerListener.class, new String[]{"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener");


            eventSets[EVENT_ancestorListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[]{"ancestorMoved", "ancestorRemoved", "ancestorAdded"}, "addAncestorListener", "removeAncestorListener");


            eventSets[EVENT_focusListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "focusListener", java.awt.event.FocusListener.class, new String[]{"focusLost", "focusGained"}, "addFocusListener", "removeFocusListener");


            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[]{"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener");


            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[]{"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener");


            eventSets[EVENT_keyListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "keyListener", java.awt.event.KeyListener.class, new String[]{"keyReleased", "keyPressed", "keyTyped"}, "addKeyListener", "removeKeyListener");


            eventSets[EVENT_actionListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "actionListener", java.awt.event.ActionListener.class, new String[]{"actionPerformed"}, "addActionListener", "removeActionListener");


            eventSets[EVENT_componentListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "componentListener", java.awt.event.ComponentListener.class, new String[]{"componentResized", "componentMoved", "componentHidden", "componentShown"}, "addComponentListener", "removeComponentListener");


            eventSets[EVENT_mouseListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "mouseListener", java.awt.event.MouseListener.class, new String[]{"mouseExited", "mouseEntered", "mouseReleased", "mouseClicked", "mousePressed"}, "addMouseListener", "removeMouseListener");


            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[]{"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener");


            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[]{"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


        return eventSets;
    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods


    private static final int METHOD_addItems0 = 0;


    private static final int METHOD_addItems1 = 1;


    private static final int METHOD_clearIcon2 = 2;


    private static final int METHOD_updateUI3 = 3;


    private static final int METHOD_isLightweightComponent4 = 4;


    private static final int METHOD_firePropertyChange5 = 5;


    private static final int METHOD_addPropertyChangeListener6 = 6;


    private static final int METHOD_grabFocus7 = 7;


    private static final int METHOD_disable8 = 8;


    private static final int METHOD_getToolTipText9 = 9;


    private static final int METHOD_firePropertyChange10 = 10;


    private static final int METHOD_registerKeyboardAction11 = 11;


    private static final int METHOD_registerKeyboardAction12 = 12;


    private static final int METHOD_paintImmediately13 = 13;


    private static final int METHOD_printAll14 = 14;


    private static final int METHOD_revalidate15 = 15;


    private static final int METHOD_createToolTip16 = 16;


    private static final int METHOD_getInputMap17 = 17;


    private static final int METHOD_getDefaultLocale18 = 18;


    private static final int METHOD_paintImmediately19 = 19;


    private static final int METHOD_getInsets20 = 20;


    private static final int METHOD_getConditionForKeyStroke21 = 21;


    private static final int METHOD_firePropertyChange22 = 22;


    private static final int METHOD_getListeners23 = 23;


    private static final int METHOD_getInputMap24 = 24;


    private static final int METHOD_getClientProperty25 = 25;


    private static final int METHOD_firePropertyChange26 = 26;


    private static final int METHOD_firePropertyChange27 = 27;


    private static final int METHOD_contains28 = 28;


    private static final int METHOD_repaint29 = 29;


    private static final int METHOD_repaint30 = 30;


    private static final int METHOD_firePropertyChange31 = 31;


    private static final int METHOD_removePropertyChangeListener32 = 32;


    private static final int METHOD_firePropertyChange33 = 33;


    private static final int METHOD_firePropertyChange34 = 34;


    private static final int METHOD_getSize35 = 35;


    private static final int METHOD_getActionForKeyStroke36 = 36;


    private static final int METHOD_removeNotify37 = 37;


    private static final int METHOD_unregisterKeyboardAction38 = 38;


    private static final int METHOD_reshape39 = 39;


    private static final int METHOD_addNotify40 = 40;


    private static final int METHOD_print41 = 41;


    private static final int METHOD_resetKeyboardActions42 = 42;


    private static final int METHOD_requestDefaultFocus43 = 43;


    private static final int METHOD_getPropertyChangeListeners44 = 44;


    private static final int METHOD_setInputMap45 = 45;


    private static final int METHOD_paint46 = 46;


    private static final int METHOD_getBounds47 = 47;


    private static final int METHOD_scrollRectToVisible48 = 48;


    private static final int METHOD_putClientProperty49 = 49;


    private static final int METHOD_update50 = 50;


    private static final int METHOD_computeVisibleRect51 = 51;


    private static final int METHOD_getToolTipLocation52 = 52;


    private static final int METHOD_setDefaultLocale53 = 53;


    private static final int METHOD_requestFocus54 = 54;


    private static final int METHOD_getLocation55 = 55;


    private static final int METHOD_requestFocusInWindow56 = 56;


    private static final int METHOD_requestFocus57 = 57;


    private static final int METHOD_enable58 = 58;


    private static final int METHOD_removeAll59 = 59;


    private static final int METHOD_insets60 = 60;


    private static final int METHOD_add61 = 61;


    private static final int METHOD_add62 = 62;


    private static final int METHOD_remove63 = 63;


    private static final int METHOD_getComponentAt64 = 64;


    private static final int METHOD_applyComponentOrientation65 = 65;


    private static final int METHOD_invalidate66 = 66;


    private static final int METHOD_transferFocusDownCycle67 = 67;


    private static final int METHOD_transferFocusBackward68 = 68;


    private static final int METHOD_minimumSize69 = 69;


    private static final int METHOD_findComponentAt70 = 70;


    private static final int METHOD_isFocusCycleRoot71 = 71;


    private static final int METHOD_add72 = 72;


    private static final int METHOD_add73 = 73;


    private static final int METHOD_list74 = 74;


    private static final int METHOD_isAncestorOf75 = 75;


    private static final int METHOD_paintComponents76 = 76;


    private static final int METHOD_getComponentAt77 = 77;


    private static final int METHOD_add78 = 78;


    private static final int METHOD_areFocusTraversalKeysSet79 = 79;


    private static final int METHOD_locate80 = 80;


    private static final int METHOD_deliverEvent81 = 81;


    private static final int METHOD_printComponents82 = 82;


    private static final int METHOD_layout83 = 83;


    private static final int METHOD_remove84 = 84;


    private static final int METHOD_preferredSize85 = 85;


    private static final int METHOD_findComponentAt86 = 86;


    private static final int METHOD_validate87 = 87;


    private static final int METHOD_doLayout88 = 88;


    private static final int METHOD_list89 = 89;


    private static final int METHOD_countComponents90 = 90;


    private static final int METHOD_inside91 = 91;


    private static final int METHOD_add92 = 92;


    private static final int METHOD_handleEvent93 = 93;


    private static final int METHOD_createImage94 = 94;


    private static final int METHOD_dispatchEvent95 = 95;


    private static final int METHOD_mouseMove96 = 96;


    private static final int METHOD_getLocation97 = 97;


    private static final int METHOD_list98 = 98;


    private static final int METHOD_transferFocusUpCycle99 = 99;


    private static final int METHOD_action100 = 100;


    private static final int METHOD_setSize101 = 101;


    private static final int METHOD_paintAll102 = 102;


    private static final int METHOD_size103 = 103;


    private static final int METHOD_postEvent104 = 104;


    private static final int METHOD_mouseEnter105 = 105;


    private static final int METHOD_hasFocus106 = 106;


    private static final int METHOD_move107 = 107;


    private static final int METHOD_location108 = 108;


    private static final int METHOD_mouseExit109 = 109;


    private static final int METHOD_transferFocus110 = 110;


    private static final int METHOD_nextFocus111 = 111;


    private static final int METHOD_getFontMetrics112 = 112;


    private static final int METHOD_remove113 = 113;


    private static final int METHOD_getSize114 = 114;


    private static final int METHOD_repaint115 = 115;


    private static final int METHOD_mouseUp116 = 116;


    private static final int METHOD_keyDown117 = 117;


    private static final int METHOD_list118 = 118;


    private static final int METHOD_lostFocus119 = 119;


    private static final int METHOD_setLocation120 = 120;


    private static final int METHOD_mouseDown121 = 121;


    private static final int METHOD_resize122 = 122;


    private static final int METHOD_imageUpdate123 = 123;


    private static final int METHOD_repaint124 = 124;


    private static final int METHOD_repaint125 = 125;


    private static final int METHOD_keyUp126 = 126;


    private static final int METHOD_show127 = 127;


    private static final int METHOD_list128 = 128;


    private static final int METHOD_checkImage129 = 129;


    private static final int METHOD_checkImage130 = 130;


    private static final int METHOD_toString131 = 131;


    private static final int METHOD_show132 = 132;


    private static final int METHOD_prepareImage133 = 133;


    private static final int METHOD_prepareImage134 = 134;


    private static final int METHOD_hide135 = 135;


    private static final int METHOD_createImage136 = 136;


    private static final int METHOD_resize137 = 137;


    private static final int METHOD_createVolatileImage138 = 138;


    private static final int METHOD_setBounds139 = 139;


    private static final int METHOD_bounds140 = 140;


    private static final int METHOD_enable141 = 141;


    private static final int METHOD_contains142 = 142;


    private static final int METHOD_mouseDrag143 = 143;


    private static final int METHOD_enableInputMethods144 = 144;


    private static final int METHOD_setLocation145 = 145;


    private static final int METHOD_setSize146 = 146;


    private static final int METHOD_createVolatileImage147 = 147;


    private static final int METHOD_gotFocus148 = 148;

    // Method array

    /*lazy MethodDescriptor*/;


    private static MethodDescriptor[] getMdescriptor() {


        MethodDescriptor[] methods = new MethodDescriptor[149];


        try {


            methods[METHOD_addItems0] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("addItems", new Class[]{Class.forName("[Ljava.lang.String;")}));


            methods[METHOD_addItems0].setDisplayName("");


            methods[METHOD_addItems1] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("addItems", new Class[]{java.lang.String.class}));


            methods[METHOD_addItems1].setDisplayName("");


            methods[METHOD_clearIcon2] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("clearIcon", new Class[]{}));


            methods[METHOD_clearIcon2].setDisplayName("");


            methods[METHOD_updateUI3] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("updateUI", new Class[]{}));


            methods[METHOD_updateUI3].setDisplayName("");


            methods[METHOD_isLightweightComponent4] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("isLightweightComponent", new Class[]{java.awt.Component.class}));


            methods[METHOD_isLightweightComponent4].setDisplayName("");


            methods[METHOD_firePropertyChange5] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Boolean.TYPE, Boolean.TYPE}));


            methods[METHOD_firePropertyChange5].setDisplayName("");


            methods[METHOD_addPropertyChangeListener6] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("addPropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));


            methods[METHOD_addPropertyChangeListener6].setDisplayName("");


            methods[METHOD_grabFocus7] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("grabFocus", new Class[]{}));


            methods[METHOD_grabFocus7].setDisplayName("");


            methods[METHOD_disable8] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("disable", new Class[]{}));


            methods[METHOD_disable8].setDisplayName("");


            methods[METHOD_getToolTipText9] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getToolTipText", new Class[]{java.awt.event.MouseEvent.class}));


            methods[METHOD_getToolTipText9].setDisplayName("");


            methods[METHOD_firePropertyChange10] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Byte.TYPE, Byte.TYPE}));


            methods[METHOD_firePropertyChange10].setDisplayName("");


            methods[METHOD_registerKeyboardAction11] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, Integer.TYPE}));


            methods[METHOD_registerKeyboardAction11].setDisplayName("");


            methods[METHOD_registerKeyboardAction12] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));


            methods[METHOD_registerKeyboardAction12].setDisplayName("");


            methods[METHOD_paintImmediately13] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("paintImmediately", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_paintImmediately13].setDisplayName("");


            methods[METHOD_printAll14] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("printAll", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_printAll14].setDisplayName("");


            methods[METHOD_revalidate15] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("revalidate", new Class[]{}));


            methods[METHOD_revalidate15].setDisplayName("");


            methods[METHOD_createToolTip16] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("createToolTip", new Class[]{}));


            methods[METHOD_createToolTip16].setDisplayName("");


            methods[METHOD_getInputMap17] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getInputMap", new Class[]{Integer.TYPE}));


            methods[METHOD_getInputMap17].setDisplayName("");


            methods[METHOD_getDefaultLocale18] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getDefaultLocale", new Class[]{}));


            methods[METHOD_getDefaultLocale18].setDisplayName("");


            methods[METHOD_paintImmediately19] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("paintImmediately", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_paintImmediately19].setDisplayName("");


            methods[METHOD_getInsets20] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getInsets", new Class[]{java.awt.Insets.class}));


            methods[METHOD_getInsets20].setDisplayName("");


            methods[METHOD_getConditionForKeyStroke21] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getConditionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_getConditionForKeyStroke21].setDisplayName("");


            methods[METHOD_firePropertyChange22] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_firePropertyChange22].setDisplayName("");


            methods[METHOD_getListeners23] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getListeners", new Class[]{java.lang.Class.class}));


            methods[METHOD_getListeners23].setDisplayName("");


            methods[METHOD_getInputMap24] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getInputMap", new Class[]{}));


            methods[METHOD_getInputMap24].setDisplayName("");


            methods[METHOD_getClientProperty25] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getClientProperty", new Class[]{java.lang.Object.class}));


            methods[METHOD_getClientProperty25].setDisplayName("");


            methods[METHOD_firePropertyChange26] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Long.TYPE, Long.TYPE}));


            methods[METHOD_firePropertyChange26].setDisplayName("");


            methods[METHOD_firePropertyChange27] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Short.TYPE, Short.TYPE}));


            methods[METHOD_firePropertyChange27].setDisplayName("");


            methods[METHOD_contains28] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("contains", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_contains28].setDisplayName("");


            methods[METHOD_repaint29] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("repaint", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_repaint29].setDisplayName("");


            methods[METHOD_repaint30] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("repaint", new Class[]{Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_repaint30].setDisplayName("");


            methods[METHOD_firePropertyChange31] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Double.TYPE, Double.TYPE}));


            methods[METHOD_firePropertyChange31].setDisplayName("");


            methods[METHOD_removePropertyChangeListener32] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("removePropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));


            methods[METHOD_removePropertyChangeListener32].setDisplayName("");


            methods[METHOD_firePropertyChange33] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Character.TYPE, Character.TYPE}));


            methods[METHOD_firePropertyChange33].setDisplayName("");


            methods[METHOD_firePropertyChange34] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Float.TYPE, Float.TYPE}));


            methods[METHOD_firePropertyChange34].setDisplayName("");


            methods[METHOD_getSize35] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getSize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_getSize35].setDisplayName("");


            methods[METHOD_getActionForKeyStroke36] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getActionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_getActionForKeyStroke36].setDisplayName("");


            methods[METHOD_removeNotify37] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("removeNotify", new Class[]{}));


            methods[METHOD_removeNotify37].setDisplayName("");


            methods[METHOD_unregisterKeyboardAction38] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("unregisterKeyboardAction", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_unregisterKeyboardAction38].setDisplayName("");


            methods[METHOD_reshape39] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("reshape", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_reshape39].setDisplayName("");


            methods[METHOD_addNotify40] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("addNotify", new Class[]{}));


            methods[METHOD_addNotify40].setDisplayName("");


            methods[METHOD_print41] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("print", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_print41].setDisplayName("");


            methods[METHOD_resetKeyboardActions42] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("resetKeyboardActions", new Class[]{}));


            methods[METHOD_resetKeyboardActions42].setDisplayName("");


            methods[METHOD_requestDefaultFocus43] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("requestDefaultFocus", new Class[]{}));


            methods[METHOD_requestDefaultFocus43].setDisplayName("");


            methods[METHOD_getPropertyChangeListeners44] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getPropertyChangeListeners", new Class[]{java.lang.String.class}));


            methods[METHOD_getPropertyChangeListeners44].setDisplayName("");


            methods[METHOD_setInputMap45] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setInputMap", new Class[]{Integer.TYPE, javax.swing.InputMap.class}));


            methods[METHOD_setInputMap45].setDisplayName("");


            methods[METHOD_paint46] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("paint", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paint46].setDisplayName("");


            methods[METHOD_getBounds47] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getBounds", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_getBounds47].setDisplayName("");


            methods[METHOD_scrollRectToVisible48] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("scrollRectToVisible", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_scrollRectToVisible48].setDisplayName("");


            methods[METHOD_putClientProperty49] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("putClientProperty", new Class[]{java.lang.Object.class, java.lang.Object.class}));


            methods[METHOD_putClientProperty49].setDisplayName("");


            methods[METHOD_update50] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("update", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_update50].setDisplayName("");


            methods[METHOD_computeVisibleRect51] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("computeVisibleRect", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_computeVisibleRect51].setDisplayName("");


            methods[METHOD_getToolTipLocation52] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getToolTipLocation", new Class[]{java.awt.event.MouseEvent.class}));


            methods[METHOD_getToolTipLocation52].setDisplayName("");


            methods[METHOD_setDefaultLocale53] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setDefaultLocale", new Class[]{java.util.Locale.class}));


            methods[METHOD_setDefaultLocale53].setDisplayName("");


            methods[METHOD_requestFocus54] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("requestFocus", new Class[]{Boolean.TYPE}));


            methods[METHOD_requestFocus54].setDisplayName("");


            methods[METHOD_getLocation55] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getLocation", new Class[]{java.awt.Point.class}));


            methods[METHOD_getLocation55].setDisplayName("");


            methods[METHOD_requestFocusInWindow56] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("requestFocusInWindow", new Class[]{}));


            methods[METHOD_requestFocusInWindow56].setDisplayName("");


            methods[METHOD_requestFocus57] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("requestFocus", new Class[]{}));


            methods[METHOD_requestFocus57].setDisplayName("");


            methods[METHOD_enable58] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("enable", new Class[]{}));


            methods[METHOD_enable58].setDisplayName("");


            methods[METHOD_removeAll59] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("removeAll", new Class[]{}));


            methods[METHOD_removeAll59].setDisplayName("");


            methods[METHOD_insets60] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("insets", new Class[]{}));


            methods[METHOD_insets60].setDisplayName("");


            methods[METHOD_add61] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.awt.Component.class, Integer.TYPE}));


            methods[METHOD_add61].setDisplayName("");


            methods[METHOD_add62] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.awt.Component.class, java.lang.Object.class, Integer.TYPE}));


            methods[METHOD_add62].setDisplayName("");


            methods[METHOD_remove63] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("remove", new Class[]{Integer.TYPE}));


            methods[METHOD_remove63].setDisplayName("");


            methods[METHOD_getComponentAt64] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_getComponentAt64].setDisplayName("");


            methods[METHOD_applyComponentOrientation65] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("applyComponentOrientation", new Class[]{java.awt.ComponentOrientation.class}));


            methods[METHOD_applyComponentOrientation65].setDisplayName("");


            methods[METHOD_invalidate66] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("invalidate", new Class[]{}));


            methods[METHOD_invalidate66].setDisplayName("");


            methods[METHOD_transferFocusDownCycle67] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("transferFocusDownCycle", new Class[]{}));


            methods[METHOD_transferFocusDownCycle67].setDisplayName("");


            methods[METHOD_transferFocusBackward68] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("transferFocusBackward", new Class[]{}));


            methods[METHOD_transferFocusBackward68].setDisplayName("");


            methods[METHOD_minimumSize69] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("minimumSize", new Class[]{}));


            methods[METHOD_minimumSize69].setDisplayName("");


            methods[METHOD_findComponentAt70] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("findComponentAt", new Class[]{java.awt.Point.class}));


            methods[METHOD_findComponentAt70].setDisplayName("");


            methods[METHOD_isFocusCycleRoot71] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("isFocusCycleRoot", new Class[]{java.awt.Container.class}));


            methods[METHOD_isFocusCycleRoot71].setDisplayName("");


            methods[METHOD_add72] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.awt.Component.class, java.lang.Object.class}));


            methods[METHOD_add72].setDisplayName("");


            methods[METHOD_add73] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.awt.Component.class}));


            methods[METHOD_add73].setDisplayName("");


            methods[METHOD_list74] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("list", new Class[]{java.io.PrintStream.class, Integer.TYPE}));


            methods[METHOD_list74].setDisplayName("");


            methods[METHOD_isAncestorOf75] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("isAncestorOf", new Class[]{java.awt.Component.class}));


            methods[METHOD_isAncestorOf75].setDisplayName("");


            methods[METHOD_paintComponents76] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("paintComponents", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paintComponents76].setDisplayName("");


            methods[METHOD_getComponentAt77] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getComponentAt", new Class[]{java.awt.Point.class}));


            methods[METHOD_getComponentAt77].setDisplayName("");


            methods[METHOD_add78] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.lang.String.class, java.awt.Component.class}));


            methods[METHOD_add78].setDisplayName("");


            methods[METHOD_areFocusTraversalKeysSet79] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("areFocusTraversalKeysSet", new Class[]{Integer.TYPE}));


            methods[METHOD_areFocusTraversalKeysSet79].setDisplayName("");


            methods[METHOD_locate80] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("locate", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_locate80].setDisplayName("");


            methods[METHOD_deliverEvent81] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("deliverEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_deliverEvent81].setDisplayName("");


            methods[METHOD_printComponents82] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("printComponents", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_printComponents82].setDisplayName("");


            methods[METHOD_layout83] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("layout", new Class[]{}));


            methods[METHOD_layout83].setDisplayName("");


            methods[METHOD_remove84] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("remove", new Class[]{java.awt.Component.class}));


            methods[METHOD_remove84].setDisplayName("");


            methods[METHOD_preferredSize85] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("preferredSize", new Class[]{}));


            methods[METHOD_preferredSize85].setDisplayName("");


            methods[METHOD_findComponentAt86] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("findComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_findComponentAt86].setDisplayName("");


            methods[METHOD_validate87] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("validate", new Class[]{}));


            methods[METHOD_validate87].setDisplayName("");


            methods[METHOD_doLayout88] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("doLayout", new Class[]{}));


            methods[METHOD_doLayout88].setDisplayName("");


            methods[METHOD_list89] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("list", new Class[]{java.io.PrintWriter.class, Integer.TYPE}));


            methods[METHOD_list89].setDisplayName("");


            methods[METHOD_countComponents90] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("countComponents", new Class[]{}));


            methods[METHOD_countComponents90].setDisplayName("");


            methods[METHOD_inside91] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("inside", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_inside91].setDisplayName("");


            methods[METHOD_add92] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("add", new Class[]{java.awt.PopupMenu.class}));


            methods[METHOD_add92].setDisplayName("");


            methods[METHOD_handleEvent93] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("handleEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_handleEvent93].setDisplayName("");


            methods[METHOD_createImage94] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("createImage", new Class[]{java.awt.image.ImageProducer.class}));


            methods[METHOD_createImage94].setDisplayName("");


            methods[METHOD_dispatchEvent95] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("dispatchEvent", new Class[]{java.awt.AWTEvent.class}));


            methods[METHOD_dispatchEvent95].setDisplayName("");


            methods[METHOD_mouseMove96] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseMove", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseMove96].setDisplayName("");


            methods[METHOD_getLocation97] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getLocation", new Class[]{}));


            methods[METHOD_getLocation97].setDisplayName("");


            methods[METHOD_list98] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("list", new Class[]{}));


            methods[METHOD_list98].setDisplayName("");


            methods[METHOD_transferFocusUpCycle99] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("transferFocusUpCycle", new Class[]{}));


            methods[METHOD_transferFocusUpCycle99].setDisplayName("");


            methods[METHOD_action100] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("action", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_action100].setDisplayName("");


            methods[METHOD_setSize101] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setSize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_setSize101].setDisplayName("");


            methods[METHOD_paintAll102] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("paintAll", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paintAll102].setDisplayName("");


            methods[METHOD_size103] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("size", new Class[]{}));


            methods[METHOD_size103].setDisplayName("");


            methods[METHOD_postEvent104] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("postEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_postEvent104].setDisplayName("");


            methods[METHOD_mouseEnter105] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseEnter", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseEnter105].setDisplayName("");


            methods[METHOD_hasFocus106] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("hasFocus", new Class[]{}));


            methods[METHOD_hasFocus106].setDisplayName("");


            methods[METHOD_move107] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("move", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_move107].setDisplayName("");


            methods[METHOD_location108] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("location", new Class[]{}));


            methods[METHOD_location108].setDisplayName("");


            methods[METHOD_mouseExit109] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseExit", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseExit109].setDisplayName("");


            methods[METHOD_transferFocus110] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("transferFocus", new Class[]{}));


            methods[METHOD_transferFocus110].setDisplayName("");


            methods[METHOD_nextFocus111] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("nextFocus", new Class[]{}));


            methods[METHOD_nextFocus111].setDisplayName("");


            methods[METHOD_getFontMetrics112] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getFontMetrics", new Class[]{java.awt.Font.class}));


            methods[METHOD_getFontMetrics112].setDisplayName("");


            methods[METHOD_remove113] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("remove", new Class[]{java.awt.MenuComponent.class}));


            methods[METHOD_remove113].setDisplayName("");


            methods[METHOD_getSize114] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("getSize", new Class[]{}));


            methods[METHOD_getSize114].setDisplayName("");


            methods[METHOD_repaint115] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("repaint", new Class[]{Long.TYPE}));


            methods[METHOD_repaint115].setDisplayName("");


            methods[METHOD_mouseUp116] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseUp", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseUp116].setDisplayName("");


            methods[METHOD_keyDown117] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("keyDown", new Class[]{java.awt.Event.class, Integer.TYPE}));


            methods[METHOD_keyDown117].setDisplayName("");


            methods[METHOD_list118] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("list", new Class[]{java.io.PrintStream.class}));


            methods[METHOD_list118].setDisplayName("");


            methods[METHOD_lostFocus119] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("lostFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_lostFocus119].setDisplayName("");


            methods[METHOD_setLocation120] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setLocation", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setLocation120].setDisplayName("");


            methods[METHOD_mouseDown121] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseDown", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseDown121].setDisplayName("");


            methods[METHOD_resize122] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("resize", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_resize122].setDisplayName("");


            methods[METHOD_imageUpdate123] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("imageUpdate", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_imageUpdate123].setDisplayName("");


            methods[METHOD_repaint124] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("repaint", new Class[]{}));


            methods[METHOD_repaint124].setDisplayName("");


            methods[METHOD_repaint125] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("repaint", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_repaint125].setDisplayName("");


            methods[METHOD_keyUp126] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("keyUp", new Class[]{java.awt.Event.class, Integer.TYPE}));


            methods[METHOD_keyUp126].setDisplayName("");


            methods[METHOD_show127] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("show", new Class[]{}));


            methods[METHOD_show127].setDisplayName("");


            methods[METHOD_list128] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("list", new Class[]{java.io.PrintWriter.class}));


            methods[METHOD_list128].setDisplayName("");


            methods[METHOD_checkImage129] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("checkImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));


            methods[METHOD_checkImage129].setDisplayName("");


            methods[METHOD_checkImage130] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("checkImage", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, java.awt.image.ImageObserver.class}));


            methods[METHOD_checkImage130].setDisplayName("");


            methods[METHOD_toString131] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("toString", new Class[]{}));


            methods[METHOD_toString131].setDisplayName("");


            methods[METHOD_show132] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("show", new Class[]{Boolean.TYPE}));


            methods[METHOD_show132].setDisplayName("");


            methods[METHOD_prepareImage133] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));


            methods[METHOD_prepareImage133].setDisplayName("");


            methods[METHOD_prepareImage134] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, java.awt.image.ImageObserver.class}));


            methods[METHOD_prepareImage134].setDisplayName("");


            methods[METHOD_hide135] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("hide", new Class[]{}));


            methods[METHOD_hide135].setDisplayName("");


            methods[METHOD_createImage136] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("createImage", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_createImage136].setDisplayName("");


            methods[METHOD_resize137] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("resize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_resize137].setDisplayName("");


            methods[METHOD_createVolatileImage138] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE, java.awt.ImageCapabilities.class}));


            methods[METHOD_createVolatileImage138].setDisplayName("");


            methods[METHOD_setBounds139] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setBounds", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setBounds139].setDisplayName("");


            methods[METHOD_bounds140] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("bounds", new Class[]{}));


            methods[METHOD_bounds140].setDisplayName("");


            methods[METHOD_enable141] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("enable", new Class[]{Boolean.TYPE}));


            methods[METHOD_enable141].setDisplayName("");


            methods[METHOD_contains142] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("contains", new Class[]{java.awt.Point.class}));


            methods[METHOD_contains142].setDisplayName("");


            methods[METHOD_mouseDrag143] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("mouseDrag", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseDrag143].setDisplayName("");


            methods[METHOD_enableInputMethods144] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("enableInputMethods", new Class[]{Boolean.TYPE}));


            methods[METHOD_enableInputMethods144].setDisplayName("");


            methods[METHOD_setLocation145] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setLocation", new Class[]{java.awt.Point.class}));


            methods[METHOD_setLocation145].setDisplayName("");


            methods[METHOD_setSize146] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("setSize", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setSize146].setDisplayName("");


            methods[METHOD_createVolatileImage147] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_createVolatileImage147].setDisplayName("");


            methods[METHOD_gotFocus148] = new MethodDescriptor(srcastra.astra.gui.components.combobox.aCombo.ACombo.class.getMethod("gotFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_gotFocus148].setDisplayName("");


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
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         properties of this bean.  May return null if the
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
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
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         properties supported by this bean.  May return null if the
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         information should be obtained by automatic analysis.
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         If a property is indexed, then its entry in the result array will
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         A client of getPropertyDescriptors can use "instanceof" to check
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
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
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         events fired by this bean.  May return null if the information
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
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
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         implemented by this bean.  May return null if the information
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         should be obtained by automatic analysis.
     */


    public MethodDescriptor[] getMethodDescriptors() {


        return getMdescriptor();


    }


    /**
     * A bean may have a "default" property that is the property that will
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * mostly commonly be initially chosen for update by human's who are
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         returned by getPropertyDescriptors.
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <P>	Returns -1 if there is no default property.
     */


    public int getDefaultPropertyIndex() {


        return defaultPropertyIndex;


    }


    /**
     * A bean may have a "default" event that is the event that will
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * mostly commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         returned by getEventSetDescriptors.
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <P>	Returns -1 if there is no default event.
     */


    public int getDefaultEventIndex() {


        return defaultEventIndex;


    }


}











