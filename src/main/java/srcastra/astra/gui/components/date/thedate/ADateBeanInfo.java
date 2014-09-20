package srcastra.astra.gui.components.date.thedate;


import java.beans.*;


public class ADateBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/

    private static BeanDescriptor getBdescriptor() {

        BeanDescriptor beanDescriptor = new BeanDescriptor(ADate.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties

    private static final int PROPERTY_accessibleContext = 0;

    private static final int PROPERTY_actionMap = 1;

    private static final int PROPERTY_alignmentX = 2;

    private static final int PROPERTY_alignmentY = 3;

    private static final int PROPERTY_ancestorListeners = 4;

    private static final int PROPERTY_autoscrolls = 5;

    private static final int PROPERTY_background = 6;

    private static final int PROPERTY_backgroundSet = 7;

    private static final int PROPERTY_border = 8;

    private static final int PROPERTY_bounds = 9;

    private static final int PROPERTY_checkUptodate = 10;

    private static final int PROPERTY_colorModel = 11;

    private static final int PROPERTY_component = 12;

    private static final int PROPERTY_componentCount = 13;

    private static final int PROPERTY_componentListeners = 14;

    private static final int PROPERTY_componentOrientation = 15;

    private static final int PROPERTY_components = 16;

    private static final int PROPERTY_containerListeners = 17;

    private static final int PROPERTY_correInput = 18;

    private static final int PROPERTY_cursor = 19;

    private static final int PROPERTY_cursorSet = 20;

    private static final int PROPERTY_date = 21;

    private static final int PROPERTY_dateBefore = 22;

    private static final int PROPERTY_dateBeforeComp = 23;

    private static final int PROPERTY_debugGraphicsOptions = 24;

    private static final int PROPERTY_displayable = 25;

    private static final int PROPERTY_doubleBuffered = 26;

    private static final int PROPERTY_dropTarget = 27;

    private static final int PROPERTY_enabled = 28;

    private static final int PROPERTY_focusable = 29;

    private static final int PROPERTY_focusCycleRootAncestor = 30;

    private static final int PROPERTY_focusListeners = 31;

    private static final int PROPERTY_focusOwner = 32;

    private static final int PROPERTY_focusTraversable = 33;

    private static final int PROPERTY_focusTraversalKeys = 34;

    private static final int PROPERTY_focusTraversalKeysEnabled = 35;

    private static final int PROPERTY_focusTraversalPolicy = 36;

    private static final int PROPERTY_focusTraversalPolicySet = 37;

    private static final int PROPERTY_font = 38;

    private static final int PROPERTY_fontSet = 39;

    private static final int PROPERTY_foreground = 40;

    private static final int PROPERTY_foregroundSet = 41;

    private static final int PROPERTY_form = 42;

    private static final int PROPERTY_goodIcon = 43;

    private static final int PROPERTY_graphics = 44;

    private static final int PROPERTY_graphicsConfiguration = 45;

    private static final int PROPERTY_grp_Comp_nextComponent = 46;

    private static final int PROPERTY_height = 47;

    private static final int PROPERTY_hierarchyBoundsListeners = 48;

    private static final int PROPERTY_hierarchyListeners = 49;

    private static final int PROPERTY_ignoreRepaint = 50;

    private static final int PROPERTY_inputContext = 51;

    private static final int PROPERTY_inputMethodListeners = 52;

    private static final int PROPERTY_inputMethodRequests = 53;

    private static final int PROPERTY_inputVerifier = 54;

    private static final int PROPERTY_insets = 55;

    private static final int PROPERTY_keyListeners = 56;

    private static final int PROPERTY_lastFocusedComponent = 57;

    private static final int PROPERTY_layout = 58;

    private static final int PROPERTY_lightweight = 59;

    private static final int PROPERTY_locale = 60;

    private static final int PROPERTY_locationOnScreen = 61;

    private static final int PROPERTY_managingFocus = 62;

    private static final int PROPERTY_maximumSize = 63;

    private static final int PROPERTY_maximumSizeSet = 64;

    private static final int PROPERTY_minimumSize = 65;

    private static final int PROPERTY_minimumSizeSet = 66;

    private static final int PROPERTY_mouseListeners = 67;

    private static final int PROPERTY_mouseMotionListeners = 68;

    private static final int PROPERTY_mouseWheelListeners = 69;

    private static final int PROPERTY_name = 70;

    private static final int PROPERTY_nextFocusableComponent = 71;

    private static final int PROPERTY_opaque = 72;

    private static final int PROPERTY_open = 73;

    private static final int PROPERTY_optimizedDrawingEnabled = 74;

    private static final int PROPERTY_paintingTile = 75;

    private static final int PROPERTY_parent = 76;

    private static final int PROPERTY_peer = 77;

    private static final int PROPERTY_preferredSize = 78;

    private static final int PROPERTY_preferredSizeSet = 79;

    private static final int PROPERTY_propertyChangeListeners = 80;

    private static final int PROPERTY_registeredKeyStrokes = 81;

    private static final int PROPERTY_requestFocusEnabled = 82;

    private static final int PROPERTY_rootPane = 83;

    private static final int PROPERTY_showing = 84;

    private static final int PROPERTY_text = 85;

    private static final int PROPERTY_text2 = 86;

    private static final int PROPERTY_toolkit = 87;

    private static final int PROPERTY_toolTipText = 88;

    private static final int PROPERTY_topLevelAncestor = 89;

    private static final int PROPERTY_transferHandler = 90;

    private static final int PROPERTY_treeLock = 91;

    private static final int PROPERTY_UI = 92;

    private static final int PROPERTY_UIClassID = 93;

    private static final int PROPERTY_unknown = 94;

    private static final int PROPERTY_user = 95;

    private static final int PROPERTY_validateRoot = 96;

    private static final int PROPERTY_verif = 97;

    private static final int PROPERTY_verifyInputWhenFocusTarget = 98;

    private static final int PROPERTY_vetoableChangeListeners = 99;

    private static final int PROPERTY_visible = 100;

    private static final int PROPERTY_visibleRect = 101;

    private static final int PROPERTY_warningIcon = 102;

    private static final int PROPERTY_width = 103;

    private static final int PROPERTY_workingIcon = 104;

    private static final int PROPERTY_x = 105;

    private static final int PROPERTY_y = 106;

    // Property array

    /*lazy PropertyDescriptor*/

    private static PropertyDescriptor[] getPdescriptor() {

        PropertyDescriptor[] properties = new PropertyDescriptor[107];


        try {

            properties[PROPERTY_accessibleContext] = new PropertyDescriptor("accessibleContext", ADate.class, "getAccessibleContext", null);

            properties[PROPERTY_actionMap] = new PropertyDescriptor("actionMap", ADate.class, "getActionMap", "setActionMap");

            properties[PROPERTY_alignmentX] = new PropertyDescriptor("alignmentX", ADate.class, "getAlignmentX", "setAlignmentX");

            properties[PROPERTY_alignmentY] = new PropertyDescriptor("alignmentY", ADate.class, "getAlignmentY", "setAlignmentY");

            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor("ancestorListeners", ADate.class, "getAncestorListeners", null);

            properties[PROPERTY_autoscrolls] = new PropertyDescriptor("autoscrolls", ADate.class, "getAutoscrolls", "setAutoscrolls");

            properties[PROPERTY_background] = new PropertyDescriptor("background", ADate.class, "getBackground", "setBackground");

            properties[PROPERTY_backgroundSet] = new PropertyDescriptor("backgroundSet", ADate.class, "isBackgroundSet", null);

            properties[PROPERTY_border] = new PropertyDescriptor("border", ADate.class, "getBorder", "setBorder");

            properties[PROPERTY_bounds] = new PropertyDescriptor("bounds", ADate.class, "getBounds", "setBounds");

            properties[PROPERTY_checkUptodate] = new PropertyDescriptor("checkUptodate", ADate.class, "isCheckUptodate", "setCheckUptodate");

            properties[PROPERTY_colorModel] = new PropertyDescriptor("colorModel", ADate.class, "getColorModel", null);

            properties[PROPERTY_component] = new IndexedPropertyDescriptor("component", ADate.class, null, null, "getComponent", null);

            properties[PROPERTY_componentCount] = new PropertyDescriptor("componentCount", ADate.class, "getComponentCount", null);

            properties[PROPERTY_componentListeners] = new PropertyDescriptor("componentListeners", ADate.class, "getComponentListeners", null);

            properties[PROPERTY_componentOrientation] = new PropertyDescriptor("componentOrientation", ADate.class, "getComponentOrientation", "setComponentOrientation");

            properties[PROPERTY_components] = new PropertyDescriptor("components", ADate.class, "getComponents", null);

            properties[PROPERTY_containerListeners] = new PropertyDescriptor("containerListeners", ADate.class, "getContainerListeners", null);

            properties[PROPERTY_correInput] = new PropertyDescriptor("correInput", ADate.class, "getCorreInput", null);

            properties[PROPERTY_cursor] = new PropertyDescriptor("cursor", ADate.class, "getCursor", "setCursor");

            properties[PROPERTY_cursorSet] = new PropertyDescriptor("cursorSet", ADate.class, "isCursorSet", null);

            properties[PROPERTY_date] = new PropertyDescriptor("date", ADate.class, "getDate", "setDate");

            properties[PROPERTY_dateBefore] = new PropertyDescriptor("dateBefore", ADate.class, "getDateBefore", "setDateBefore");

            properties[PROPERTY_dateBeforeComp] = new PropertyDescriptor("dateBeforeComp", ADate.class, "getDateBeforeComp", "setDateBeforeComp");

            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor("debugGraphicsOptions", ADate.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions");

            properties[PROPERTY_displayable] = new PropertyDescriptor("displayable", ADate.class, "isDisplayable", null);

            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor("doubleBuffered", ADate.class, "isDoubleBuffered", "setDoubleBuffered");

            properties[PROPERTY_dropTarget] = new PropertyDescriptor("dropTarget", ADate.class, "getDropTarget", "setDropTarget");

            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", ADate.class, "isEnabled", "setEnabled");

            properties[PROPERTY_focusable] = new PropertyDescriptor("focusable", ADate.class, "isFocusable", "setFocusable");

            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor("focusCycleRootAncestor", ADate.class, "getFocusCycleRootAncestor", null);

            properties[PROPERTY_focusListeners] = new PropertyDescriptor("focusListeners", ADate.class, "getFocusListeners", null);

            properties[PROPERTY_focusOwner] = new PropertyDescriptor("focusOwner", ADate.class, "isFocusOwner", null);

            properties[PROPERTY_focusTraversable] = new PropertyDescriptor("focusTraversable", ADate.class, "isFocusTraversable", null);

            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor("focusTraversalKeys", ADate.class, null, null, "getFocusTraversalKeys", "setFocusTraversalKeys");

            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor("focusTraversalKeysEnabled", ADate.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled");

            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor("focusTraversalPolicy", ADate.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");

            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor("focusTraversalPolicySet", ADate.class, "isFocusTraversalPolicySet", null);

            properties[PROPERTY_font] = new PropertyDescriptor("font", ADate.class, "getFont", "setFont");

            properties[PROPERTY_fontSet] = new PropertyDescriptor("fontSet", ADate.class, "isFontSet", null);

            properties[PROPERTY_foreground] = new PropertyDescriptor("foreground", ADate.class, "getForeground", "setForeground");

            properties[PROPERTY_foregroundSet] = new PropertyDescriptor("foregroundSet", ADate.class, "isForegroundSet", null);

            properties[PROPERTY_form] = new PropertyDescriptor("form", ADate.class, "getForm", "setForm");

            properties[PROPERTY_goodIcon] = new PropertyDescriptor("goodIcon", ADate.class, null, "setGoodIcon");

            properties[PROPERTY_graphics] = new PropertyDescriptor("graphics", ADate.class, "getGraphics", null);

            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor("graphicsConfiguration", ADate.class, "getGraphicsConfiguration", null);

            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", ADate.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");

            properties[PROPERTY_height] = new PropertyDescriptor("height", ADate.class, "getHeight", null);

            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor("hierarchyBoundsListeners", ADate.class, "getHierarchyBoundsListeners", null);

            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor("hierarchyListeners", ADate.class, "getHierarchyListeners", null);

            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor("ignoreRepaint", ADate.class, "getIgnoreRepaint", "setIgnoreRepaint");

            properties[PROPERTY_inputContext] = new PropertyDescriptor("inputContext", ADate.class, "getInputContext", null);

            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor("inputMethodListeners", ADate.class, "getInputMethodListeners", null);

            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor("inputMethodRequests", ADate.class, "getInputMethodRequests", null);

            properties[PROPERTY_inputVerifier] = new PropertyDescriptor("inputVerifier", ADate.class, "getInputVerifier", "setInputVerifier");

            properties[PROPERTY_insets] = new PropertyDescriptor("insets", ADate.class, "getInsets", null);

            properties[PROPERTY_keyListeners] = new PropertyDescriptor("keyListeners", ADate.class, "getKeyListeners", null);

            properties[PROPERTY_lastFocusedComponent] = new PropertyDescriptor("lastFocusedComponent", ADate.class, "isLastFocusedComponent", "setLastFocusedComponent");

            properties[PROPERTY_layout] = new PropertyDescriptor("layout", ADate.class, "getLayout", "setLayout");

            properties[PROPERTY_lightweight] = new PropertyDescriptor("lightweight", ADate.class, "isLightweight", null);

            properties[PROPERTY_locale] = new PropertyDescriptor("locale", ADate.class, "getLocale", "setLocale");

            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor("locationOnScreen", ADate.class, "getLocationOnScreen", null);

            properties[PROPERTY_managingFocus] = new PropertyDescriptor("managingFocus", ADate.class, "isManagingFocus", null);

            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", ADate.class, "getMaximumSize", "setMaximumSize");

            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor("maximumSizeSet", ADate.class, "isMaximumSizeSet", null);

            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", ADate.class, "getMinimumSize", "setMinimumSize");

            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor("minimumSizeSet", ADate.class, "isMinimumSizeSet", null);

            properties[PROPERTY_mouseListeners] = new PropertyDescriptor("mouseListeners", ADate.class, "getMouseListeners", null);

            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor("mouseMotionListeners", ADate.class, "getMouseMotionListeners", null);

            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor("mouseWheelListeners", ADate.class, "getMouseWheelListeners", null);

            properties[PROPERTY_name] = new PropertyDescriptor("name", ADate.class, "getName", "setName");

            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor("nextFocusableComponent", ADate.class, "getNextFocusableComponent", "setNextFocusableComponent");

            properties[PROPERTY_opaque] = new PropertyDescriptor("opaque", ADate.class, "isOpaque", "setOpaque");

            properties[PROPERTY_open] = new PropertyDescriptor("open", ADate.class, "isOpen", "setOpen");

            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor("optimizedDrawingEnabled", ADate.class, "isOptimizedDrawingEnabled", null);

            properties[PROPERTY_paintingTile] = new PropertyDescriptor("paintingTile", ADate.class, "isPaintingTile", null);

            properties[PROPERTY_parent] = new PropertyDescriptor("parent", ADate.class, "getParent", null);

            properties[PROPERTY_peer] = new PropertyDescriptor("peer", ADate.class, "getPeer", null);

            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", ADate.class, "getPreferredSize", "setPreferredSize");

            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor("preferredSizeSet", ADate.class, "isPreferredSizeSet", null);

            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor("propertyChangeListeners", ADate.class, "getPropertyChangeListeners", null);

            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor("registeredKeyStrokes", ADate.class, "getRegisteredKeyStrokes", null);

            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor("requestFocusEnabled", ADate.class, "isRequestFocusEnabled", "setRequestFocusEnabled");

            properties[PROPERTY_rootPane] = new PropertyDescriptor("rootPane", ADate.class, "getRootPane", null);

            properties[PROPERTY_showing] = new PropertyDescriptor("showing", ADate.class, "isShowing", null);

            properties[PROPERTY_text] = new PropertyDescriptor("text", ADate.class, null, "setText");

            properties[PROPERTY_text2] = new PropertyDescriptor("text2", ADate.class, "getText2", null);

            properties[PROPERTY_toolkit] = new PropertyDescriptor("toolkit", ADate.class, "getToolkit", null);

            properties[PROPERTY_toolTipText] = new PropertyDescriptor("toolTipText", ADate.class, "getToolTipText", "setToolTipText");

            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor("topLevelAncestor", ADate.class, "getTopLevelAncestor", null);

            properties[PROPERTY_transferHandler] = new PropertyDescriptor("transferHandler", ADate.class, "getTransferHandler", "setTransferHandler");

            properties[PROPERTY_treeLock] = new PropertyDescriptor("treeLock", ADate.class, "getTreeLock", null);

            properties[PROPERTY_UI] = new PropertyDescriptor("UI", ADate.class, "getUI", "setUI");

            properties[PROPERTY_UIClassID] = new PropertyDescriptor("UIClassID", ADate.class, "getUIClassID", null);

            properties[PROPERTY_unknown] = new PropertyDescriptor("unknown", ADate.class, "isUnknown", "setUnknown");

            properties[PROPERTY_user] = new PropertyDescriptor("user", ADate.class, "getUser", "setUser");

            properties[PROPERTY_validateRoot] = new PropertyDescriptor("validateRoot", ADate.class, "isValidateRoot", null);

            properties[PROPERTY_verif] = new PropertyDescriptor("verif", ADate.class, "getverif", null);

            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor("verifyInputWhenFocusTarget", ADate.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget");

            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor("vetoableChangeListeners", ADate.class, "getVetoableChangeListeners", null);

            properties[PROPERTY_visible] = new PropertyDescriptor("visible", ADate.class, "isVisible", "setVisible");

            properties[PROPERTY_visibleRect] = new PropertyDescriptor("visibleRect", ADate.class, "getVisibleRect", null);

            properties[PROPERTY_warningIcon] = new PropertyDescriptor("warningIcon", ADate.class, "getWarningIcon", "setWarningIcon");

            properties[PROPERTY_width] = new PropertyDescriptor("width", ADate.class, "getWidth", null);

            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", ADate.class, "getWorkingIcon", "setWorkingIcon");

            properties[PROPERTY_x] = new PropertyDescriptor("x", ADate.class, "getX", null);

            properties[PROPERTY_y] = new PropertyDescriptor("y", ADate.class, "getY", null);

        }

        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.


        return properties;
    }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    private static final int EVENT_actionListener = 0;

    private static final int EVENT_ancestorListener = 1;

    private static final int EVENT_componentListener = 2;

    private static final int EVENT_containerListener = 3;

    private static final int EVENT_focusListener = 4;

    private static final int EVENT_hierarchyBoundsListener = 5;

    private static final int EVENT_hierarchyListener = 6;

    private static final int EVENT_inputMethodListener = 7;

    private static final int EVENT_keyListener = 8;

    private static final int EVENT_mouseListener = 9;

    private static final int EVENT_mouseMotionListener = 10;

    private static final int EVENT_mouseWheelListener = 11;

    private static final int EVENT_propertyChangeListener = 12;

    private static final int EVENT_vetoableChangeListener = 13;

    // EventSet array

    /*lazy EventSetDescriptor*/

    private static EventSetDescriptor[] getEdescriptor() {

        EventSetDescriptor[] eventSets = new EventSetDescriptor[14];


        try {

            eventSets[EVENT_actionListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "actionListener", java.awt.event.ActionListener.class, new String[]{"actionPerformed"}, "addActionListener", "removeActionListener");

            eventSets[EVENT_ancestorListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[]{"ancestorAdded", "ancestorMoved", "ancestorRemoved"}, "addAncestorListener", "removeAncestorListener");

            eventSets[EVENT_componentListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "componentListener", java.awt.event.ComponentListener.class, new String[]{"componentHidden", "componentMoved", "componentResized", "componentShown"}, "addComponentListener", "removeComponentListener");

            eventSets[EVENT_containerListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "containerListener", java.awt.event.ContainerListener.class, new String[]{"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener");

            eventSets[EVENT_focusListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "focusListener", java.awt.event.FocusListener.class, new String[]{"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener");

            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[]{"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener");

            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[]{"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener");

            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[]{"caretPositionChanged", "inputMethodTextChanged"}, "addInputMethodListener", "removeInputMethodListener");

            eventSets[EVENT_keyListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "keyListener", java.awt.event.KeyListener.class, new String[]{"keyPressed", "keyReleased", "keyTyped"}, "addKeyListener", "removeKeyListener");

            eventSets[EVENT_mouseListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "mouseListener", java.awt.event.MouseListener.class, new String[]{"mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased"}, "addMouseListener", "removeMouseListener");

            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[]{"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener");

            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[]{"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener");

            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[]{"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener");

            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[]{"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener");

        }

        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


        return eventSets;
    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods

    private static final int METHOD_action0 = 0;

    private static final int METHOD_add1 = 1;

    private static final int METHOD_addAdateListener2 = 2;

    private static final int METHOD_addNotify3 = 3;

    private static final int METHOD_addPropertyChangeListener4 = 4;

    private static final int METHOD_applyComponentOrientation5 = 5;

    private static final int METHOD_areFocusTraversalKeysSet6 = 6;

    private static final int METHOD_bounds7 = 7;

    private static final int METHOD_checkImage8 = 8;

    private static final int METHOD_clearIcon9 = 9;

    private static final int METHOD_computeVisibleRect10 = 10;

    private static final int METHOD_contains11 = 11;

    private static final int METHOD_countComponents12 = 12;

    private static final int METHOD_createImage13 = 13;

    private static final int METHOD_createToolTip14 = 14;

    private static final int METHOD_createVolatileImage15 = 15;

    private static final int METHOD_deliverEvent16 = 16;

    private static final int METHOD_disable17 = 17;

    private static final int METHOD_dispatchEvent18 = 18;

    private static final int METHOD_doLayout19 = 19;

    private static final int METHOD_enable20 = 20;

    private static final int METHOD_enableInputMethods21 = 21;

    private static final int METHOD_findComponentAt22 = 22;

    private static final int METHOD_firePropertyChange23 = 23;

    private static final int METHOD_focusGained24 = 24;

    private static final int METHOD_focusLost25 = 25;

    private static final int METHOD_getActionForKeyStroke26 = 26;

    private static final int METHOD_getBounds27 = 27;

    private static final int METHOD_getClientProperty28 = 28;

    private static final int METHOD_getComponentAt29 = 29;

    private static final int METHOD_getConditionForKeyStroke30 = 30;

    private static final int METHOD_getDefaultLocale31 = 31;

    private static final int METHOD_getFontMetrics32 = 32;

    private static final int METHOD_getInputMap33 = 33;

    private static final int METHOD_getInsets34 = 34;

    private static final int METHOD_getListeners35 = 35;

    private static final int METHOD_getLocation36 = 36;

    private static final int METHOD_getPropertyChangeListeners37 = 37;

    private static final int METHOD_getSize38 = 38;

    private static final int METHOD_getToolTipLocation39 = 39;

    private static final int METHOD_getToolTipText40 = 40;

    private static final int METHOD_gotFocus41 = 41;

    private static final int METHOD_grabFocus42 = 42;

    private static final int METHOD_handleEvent43 = 43;

    private static final int METHOD_hasFocus44 = 44;

    private static final int METHOD_hide45 = 45;

    private static final int METHOD_imageUpdate46 = 46;

    private static final int METHOD_insets47 = 47;

    private static final int METHOD_inside48 = 48;

    private static final int METHOD_invalidate49 = 49;

    private static final int METHOD_isAncestorOf50 = 50;

    private static final int METHOD_isFocusCycleRoot51 = 51;

    private static final int METHOD_isLightweightComponent52 = 52;

    private static final int METHOD_keyDown53 = 53;

    private static final int METHOD_keyUp54 = 54;

    private static final int METHOD_layout55 = 55;

    private static final int METHOD_list56 = 56;

    private static final int METHOD_locate57 = 57;

    private static final int METHOD_location58 = 58;

    private static final int METHOD_lostFocus59 = 59;

    private static final int METHOD_minimumSize60 = 60;

    private static final int METHOD_mouseDown61 = 61;

    private static final int METHOD_mouseDrag62 = 62;

    private static final int METHOD_mouseEnter63 = 63;

    private static final int METHOD_mouseExit64 = 64;

    private static final int METHOD_mouseMove65 = 65;

    private static final int METHOD_mouseUp66 = 66;

    private static final int METHOD_move67 = 67;

    private static final int METHOD_nextFocus68 = 68;

    private static final int METHOD_paint69 = 69;

    private static final int METHOD_paintAll70 = 70;

    private static final int METHOD_paintComponents71 = 71;

    private static final int METHOD_paintImmediately72 = 72;

    private static final int METHOD_postEvent73 = 73;

    private static final int METHOD_preferredSize74 = 74;

    private static final int METHOD_prepareImage75 = 75;

    private static final int METHOD_print76 = 76;

    private static final int METHOD_printAll77 = 77;

    private static final int METHOD_printComponents78 = 78;

    private static final int METHOD_putClientProperty79 = 79;

    private static final int METHOD_registerKeyboardAction80 = 80;

    private static final int METHOD_remove81 = 81;

    private static final int METHOD_removeAll82 = 82;

    private static final int METHOD_removeNotify83 = 83;

    private static final int METHOD_removePropertyChangeListener84 = 84;

    private static final int METHOD_repaint85 = 85;

    private static final int METHOD_requestDefaultFocus86 = 86;

    private static final int METHOD_requestFocus87 = 87;

    private static final int METHOD_requestFocusInWindow88 = 88;

    private static final int METHOD_resetKeyboardActions89 = 89;

    private static final int METHOD_reshape90 = 90;

    private static final int METHOD_resize91 = 91;

    private static final int METHOD_revalidate92 = 92;

    private static final int METHOD_scrollRectToVisible93 = 93;

    private static final int METHOD_setBounds94 = 94;

    private static final int METHOD_setDefaultLocale95 = 95;

    private static final int METHOD_setInputMap96 = 96;

    private static final int METHOD_setLocation97 = 97;

    private static final int METHOD_setSize98 = 98;

    private static final int METHOD_show99 = 99;

    private static final int METHOD_size100 = 100;

    private static final int METHOD_toString101 = 101;

    private static final int METHOD_transferFocus102 = 102;

    private static final int METHOD_transferFocusBackward103 = 103;

    private static final int METHOD_transferFocusDownCycle104 = 104;

    private static final int METHOD_transferFocusUpCycle105 = 105;

    private static final int METHOD_unregisterKeyboardAction106 = 106;

    private static final int METHOD_update107 = 107;

    private static final int METHOD_updateUI108 = 108;

    private static final int METHOD_validate109 = 109;

    // Method array

    /*lazy MethodDescriptor*/

    private static MethodDescriptor[] getMdescriptor() {

        MethodDescriptor[] methods = new MethodDescriptor[110];


        try {

            methods[METHOD_action0] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("action", new Class[]{java.awt.event.ActionEvent.class}));

            methods[METHOD_action0].setDisplayName("");

            methods[METHOD_add1] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("add", new Class[]{java.awt.Component.class}));

            methods[METHOD_add1].setDisplayName("");

            methods[METHOD_addAdateListener2] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("addAdateListener", new Class[]{srcastra.astra.gui.components.date.thedate.ADateListener.class}));

            methods[METHOD_addAdateListener2].setDisplayName("");

            methods[METHOD_addNotify3] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("addNotify", new Class[]{}));

            methods[METHOD_addNotify3].setDisplayName("");

            methods[METHOD_addPropertyChangeListener4] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("addPropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));

            methods[METHOD_addPropertyChangeListener4].setDisplayName("");

            methods[METHOD_applyComponentOrientation5] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("applyComponentOrientation", new Class[]{java.awt.ComponentOrientation.class}));

            methods[METHOD_applyComponentOrientation5].setDisplayName("");

            methods[METHOD_areFocusTraversalKeysSet6] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("areFocusTraversalKeysSet", new Class[]{Integer.TYPE}));

            methods[METHOD_areFocusTraversalKeysSet6].setDisplayName("");

            methods[METHOD_bounds7] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("bounds", new Class[]{}));

            methods[METHOD_bounds7].setDisplayName("");

            methods[METHOD_checkImage8] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("checkImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));

            methods[METHOD_checkImage8].setDisplayName("");

            methods[METHOD_clearIcon9] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("clearIcon", new Class[]{}));

            methods[METHOD_clearIcon9].setDisplayName("");

            methods[METHOD_computeVisibleRect10] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("computeVisibleRect", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_computeVisibleRect10].setDisplayName("");

            methods[METHOD_contains11] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("contains", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_contains11].setDisplayName("");

            methods[METHOD_countComponents12] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("countComponents", new Class[]{}));

            methods[METHOD_countComponents12].setDisplayName("");

            methods[METHOD_createImage13] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("createImage", new Class[]{java.awt.image.ImageProducer.class}));

            methods[METHOD_createImage13].setDisplayName("");

            methods[METHOD_createToolTip14] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("createToolTip", new Class[]{}));

            methods[METHOD_createToolTip14].setDisplayName("");

            methods[METHOD_createVolatileImage15] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_createVolatileImage15].setDisplayName("");

            methods[METHOD_deliverEvent16] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("deliverEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_deliverEvent16].setDisplayName("");

            methods[METHOD_disable17] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("disable", new Class[]{}));

            methods[METHOD_disable17].setDisplayName("");

            methods[METHOD_dispatchEvent18] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("dispatchEvent", new Class[]{java.awt.AWTEvent.class}));

            methods[METHOD_dispatchEvent18].setDisplayName("");

            methods[METHOD_doLayout19] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("doLayout", new Class[]{}));

            methods[METHOD_doLayout19].setDisplayName("");

            methods[METHOD_enable20] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("enable", new Class[]{}));

            methods[METHOD_enable20].setDisplayName("");

            methods[METHOD_enableInputMethods21] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("enableInputMethods", new Class[]{Boolean.TYPE}));

            methods[METHOD_enableInputMethods21].setDisplayName("");

            methods[METHOD_findComponentAt22] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("findComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_findComponentAt22].setDisplayName("");

            methods[METHOD_firePropertyChange23] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Byte.TYPE, Byte.TYPE}));

            methods[METHOD_firePropertyChange23].setDisplayName("");

            methods[METHOD_focusGained24] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("focusGained", new Class[]{java.awt.event.FocusEvent.class}));

            methods[METHOD_focusGained24].setDisplayName("");

            methods[METHOD_focusLost25] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("focusLost", new Class[]{java.awt.event.FocusEvent.class}));

            methods[METHOD_focusLost25].setDisplayName("");

            methods[METHOD_getActionForKeyStroke26] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getActionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_getActionForKeyStroke26].setDisplayName("");

            methods[METHOD_getBounds27] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getBounds", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_getBounds27].setDisplayName("");

            methods[METHOD_getClientProperty28] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getClientProperty", new Class[]{java.lang.Object.class}));

            methods[METHOD_getClientProperty28].setDisplayName("");

            methods[METHOD_getComponentAt29] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_getComponentAt29].setDisplayName("");

            methods[METHOD_getConditionForKeyStroke30] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getConditionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_getConditionForKeyStroke30].setDisplayName("");

            methods[METHOD_getDefaultLocale31] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getDefaultLocale", new Class[]{}));

            methods[METHOD_getDefaultLocale31].setDisplayName("");

            methods[METHOD_getFontMetrics32] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getFontMetrics", new Class[]{java.awt.Font.class}));

            methods[METHOD_getFontMetrics32].setDisplayName("");

            methods[METHOD_getInputMap33] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getInputMap", new Class[]{Integer.TYPE}));

            methods[METHOD_getInputMap33].setDisplayName("");

            methods[METHOD_getInsets34] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getInsets", new Class[]{java.awt.Insets.class}));

            methods[METHOD_getInsets34].setDisplayName("");

            methods[METHOD_getListeners35] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getListeners", new Class[]{java.lang.Class.class}));

            methods[METHOD_getListeners35].setDisplayName("");

            methods[METHOD_getLocation36] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getLocation", new Class[]{java.awt.Point.class}));

            methods[METHOD_getLocation36].setDisplayName("");

            methods[METHOD_getPropertyChangeListeners37] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getPropertyChangeListeners", new Class[]{java.lang.String.class}));

            methods[METHOD_getPropertyChangeListeners37].setDisplayName("");

            methods[METHOD_getSize38] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getSize", new Class[]{java.awt.Dimension.class}));

            methods[METHOD_getSize38].setDisplayName("");

            methods[METHOD_getToolTipLocation39] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getToolTipLocation", new Class[]{java.awt.event.MouseEvent.class}));

            methods[METHOD_getToolTipLocation39].setDisplayName("");

            methods[METHOD_getToolTipText40] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("getToolTipText", new Class[]{java.awt.event.MouseEvent.class}));

            methods[METHOD_getToolTipText40].setDisplayName("");

            methods[METHOD_gotFocus41] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("gotFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));

            methods[METHOD_gotFocus41].setDisplayName("");

            methods[METHOD_grabFocus42] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("grabFocus", new Class[]{}));

            methods[METHOD_grabFocus42].setDisplayName("");

            methods[METHOD_handleEvent43] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("handleEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_handleEvent43].setDisplayName("");

            methods[METHOD_hasFocus44] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("hasFocus", new Class[]{}));

            methods[METHOD_hasFocus44].setDisplayName("");

            methods[METHOD_hide45] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("hide", new Class[]{}));

            methods[METHOD_hide45].setDisplayName("");

            methods[METHOD_imageUpdate46] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("imageUpdate", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_imageUpdate46].setDisplayName("");

            methods[METHOD_insets47] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("insets", new Class[]{}));

            methods[METHOD_insets47].setDisplayName("");

            methods[METHOD_inside48] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("inside", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_inside48].setDisplayName("");

            methods[METHOD_invalidate49] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("invalidate", new Class[]{}));

            methods[METHOD_invalidate49].setDisplayName("");

            methods[METHOD_isAncestorOf50] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("isAncestorOf", new Class[]{java.awt.Component.class}));

            methods[METHOD_isAncestorOf50].setDisplayName("");

            methods[METHOD_isFocusCycleRoot51] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("isFocusCycleRoot", new Class[]{java.awt.Container.class}));

            methods[METHOD_isFocusCycleRoot51].setDisplayName("");

            methods[METHOD_isLightweightComponent52] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("isLightweightComponent", new Class[]{java.awt.Component.class}));

            methods[METHOD_isLightweightComponent52].setDisplayName("");

            methods[METHOD_keyDown53] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("keyDown", new Class[]{java.awt.Event.class, Integer.TYPE}));

            methods[METHOD_keyDown53].setDisplayName("");

            methods[METHOD_keyUp54] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("keyUp", new Class[]{java.awt.Event.class, Integer.TYPE}));

            methods[METHOD_keyUp54].setDisplayName("");

            methods[METHOD_layout55] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("layout", new Class[]{}));

            methods[METHOD_layout55].setDisplayName("");

            methods[METHOD_list56] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("list", new Class[]{java.io.PrintStream.class, Integer.TYPE}));

            methods[METHOD_list56].setDisplayName("");

            methods[METHOD_locate57] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("locate", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_locate57].setDisplayName("");

            methods[METHOD_location58] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("location", new Class[]{}));

            methods[METHOD_location58].setDisplayName("");

            methods[METHOD_lostFocus59] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("lostFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));

            methods[METHOD_lostFocus59].setDisplayName("");

            methods[METHOD_minimumSize60] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("minimumSize", new Class[]{}));

            methods[METHOD_minimumSize60].setDisplayName("");

            methods[METHOD_mouseDown61] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseDown", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseDown61].setDisplayName("");

            methods[METHOD_mouseDrag62] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseDrag", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseDrag62].setDisplayName("");

            methods[METHOD_mouseEnter63] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseEnter", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseEnter63].setDisplayName("");

            methods[METHOD_mouseExit64] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseExit", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseExit64].setDisplayName("");

            methods[METHOD_mouseMove65] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseMove", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseMove65].setDisplayName("");

            methods[METHOD_mouseUp66] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("mouseUp", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_mouseUp66].setDisplayName("");

            methods[METHOD_move67] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("move", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_move67].setDisplayName("");

            methods[METHOD_nextFocus68] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("nextFocus", new Class[]{}));

            methods[METHOD_nextFocus68].setDisplayName("");

            methods[METHOD_paint69] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("paint", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paint69].setDisplayName("");

            methods[METHOD_paintAll70] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("paintAll", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paintAll70].setDisplayName("");

            methods[METHOD_paintComponents71] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("paintComponents", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_paintComponents71].setDisplayName("");

            methods[METHOD_paintImmediately72] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("paintImmediately", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_paintImmediately72].setDisplayName("");

            methods[METHOD_postEvent73] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("postEvent", new Class[]{java.awt.Event.class}));

            methods[METHOD_postEvent73].setDisplayName("");

            methods[METHOD_preferredSize74] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("preferredSize", new Class[]{}));

            methods[METHOD_preferredSize74].setDisplayName("");

            methods[METHOD_prepareImage75] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));

            methods[METHOD_prepareImage75].setDisplayName("");

            methods[METHOD_print76] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("print", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_print76].setDisplayName("");

            methods[METHOD_printAll77] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("printAll", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_printAll77].setDisplayName("");

            methods[METHOD_printComponents78] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("printComponents", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_printComponents78].setDisplayName("");

            methods[METHOD_putClientProperty79] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("putClientProperty", new Class[]{java.lang.Object.class, java.lang.Object.class}));

            methods[METHOD_putClientProperty79].setDisplayName("");

            methods[METHOD_registerKeyboardAction80] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));

            methods[METHOD_registerKeyboardAction80].setDisplayName("");

            methods[METHOD_remove81] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("remove", new Class[]{Integer.TYPE}));

            methods[METHOD_remove81].setDisplayName("");

            methods[METHOD_removeAll82] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("removeAll", new Class[]{}));

            methods[METHOD_removeAll82].setDisplayName("");

            methods[METHOD_removeNotify83] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("removeNotify", new Class[]{}));

            methods[METHOD_removeNotify83].setDisplayName("");

            methods[METHOD_removePropertyChangeListener84] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("removePropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));

            methods[METHOD_removePropertyChangeListener84].setDisplayName("");

            methods[METHOD_repaint85] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("repaint", new Class[]{Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_repaint85].setDisplayName("");

            methods[METHOD_requestDefaultFocus86] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("requestDefaultFocus", new Class[]{}));

            methods[METHOD_requestDefaultFocus86].setDisplayName("");

            methods[METHOD_requestFocus87] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("requestFocus", new Class[]{}));

            methods[METHOD_requestFocus87].setDisplayName("");

            methods[METHOD_requestFocusInWindow88] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("requestFocusInWindow", new Class[]{}));

            methods[METHOD_requestFocusInWindow88].setDisplayName("");

            methods[METHOD_resetKeyboardActions89] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("resetKeyboardActions", new Class[]{}));

            methods[METHOD_resetKeyboardActions89].setDisplayName("");

            methods[METHOD_reshape90] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("reshape", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_reshape90].setDisplayName("");

            methods[METHOD_resize91] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("resize", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_resize91].setDisplayName("");

            methods[METHOD_revalidate92] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("revalidate", new Class[]{}));

            methods[METHOD_revalidate92].setDisplayName("");

            methods[METHOD_scrollRectToVisible93] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("scrollRectToVisible", new Class[]{java.awt.Rectangle.class}));

            methods[METHOD_scrollRectToVisible93].setDisplayName("");

            methods[METHOD_setBounds94] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("setBounds", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setBounds94].setDisplayName("");

            methods[METHOD_setDefaultLocale95] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("setDefaultLocale", new Class[]{java.util.Locale.class}));

            methods[METHOD_setDefaultLocale95].setDisplayName("");

            methods[METHOD_setInputMap96] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("setInputMap", new Class[]{Integer.TYPE, javax.swing.InputMap.class}));

            methods[METHOD_setInputMap96].setDisplayName("");

            methods[METHOD_setLocation97] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("setLocation", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setLocation97].setDisplayName("");

            methods[METHOD_setSize98] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("setSize", new Class[]{Integer.TYPE, Integer.TYPE}));

            methods[METHOD_setSize98].setDisplayName("");

            methods[METHOD_show99] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("show", new Class[]{}));

            methods[METHOD_show99].setDisplayName("");

            methods[METHOD_size100] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("size", new Class[]{}));

            methods[METHOD_size100].setDisplayName("");

            methods[METHOD_toString101] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("toString", new Class[]{}));

            methods[METHOD_toString101].setDisplayName("");

            methods[METHOD_transferFocus102] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("transferFocus", new Class[]{}));

            methods[METHOD_transferFocus102].setDisplayName("");

            methods[METHOD_transferFocusBackward103] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("transferFocusBackward", new Class[]{}));

            methods[METHOD_transferFocusBackward103].setDisplayName("");

            methods[METHOD_transferFocusDownCycle104] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("transferFocusDownCycle", new Class[]{}));

            methods[METHOD_transferFocusDownCycle104].setDisplayName("");

            methods[METHOD_transferFocusUpCycle105] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("transferFocusUpCycle", new Class[]{}));

            methods[METHOD_transferFocusUpCycle105].setDisplayName("");

            methods[METHOD_unregisterKeyboardAction106] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("unregisterKeyboardAction", new Class[]{javax.swing.KeyStroke.class}));

            methods[METHOD_unregisterKeyboardAction106].setDisplayName("");

            methods[METHOD_update107] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("update", new Class[]{java.awt.Graphics.class}));

            methods[METHOD_update107].setDisplayName("");

            methods[METHOD_updateUI108] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("updateUI", new Class[]{}));

            methods[METHOD_updateUI108].setDisplayName("");

            methods[METHOD_validate109] = new MethodDescriptor(srcastra.astra.gui.components.date.thedate.ADate.class.getMethod("validate", new Class[]{}));

            methods[METHOD_validate109].setDisplayName("");

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



