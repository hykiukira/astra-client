package srcastra.astra.gui.components.textfield2;


import java.beans.*;


public class ATextFieldWithButtonBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/;


    private static BeanDescriptor getBdescriptor() {


        BeanDescriptor beanDescriptor = new BeanDescriptor(ATextFieldWithButton.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties


    private static final int PROPERTY_dialogDimension = 0;


    private static final int PROPERTY_lastFocusedComponent = 1;


    private static final int PROPERTY_y = 2;


    private static final int PROPERTY_frame = 3;


    private static final int PROPERTY_propertyChangeListeners = 4;


    private static final int PROPERTY_grp_Comp_nextComponent = 5;


    private static final int PROPERTY_enabled = 6;


    private static final int PROPERTY_maximumSize = 7;


    private static final int PROPERTY_warningIcon = 8;


    private static final int PROPERTY_minimumSize = 9;


    private static final int PROPERTY_text = 10;


    private static final int PROPERTY_parent = 11;


    private static final int PROPERTY_grp_Pane_InternScreenModule = 12;


    private static final int PROPERTY_x = 13;


    private static final int PROPERTY_requestFocusEnabled = 14;


    private static final int PROPERTY_preferedSize = 15;


    private static final int PROPERTY_grp_Comp_previousComponent = 16;


    private static final int PROPERTY_workingIcon = 17;


    private static final int PROPERTY_frameModal = 18;


    private static final int PROPERTY_nextFocusableComponent = 19;


    private static final int PROPERTY_cleUnik = 20;


    private static final int PROPERTY_preferredSize = 21;


    private static final int PROPERTY_name = 22;


    private static final int PROPERTY_verif = 23;

    // Property array

    /*lazy PropertyDescriptor*/;


    private static PropertyDescriptor[] getPdescriptor() {


        PropertyDescriptor[] properties = new PropertyDescriptor[24];


        try {


            properties[PROPERTY_dialogDimension] = new PropertyDescriptor("dialogDimension", ATextFieldWithButton.class, "getDialogDimension", "setDialogDimension");


            properties[PROPERTY_lastFocusedComponent] = new PropertyDescriptor("lastFocusedComponent", ATextFieldWithButton.class, "isLastFocusedComponent", "setLastFocusedComponent");


            properties[PROPERTY_y] = new PropertyDescriptor("y", ATextFieldWithButton.class, "getY", null);


            properties[PROPERTY_frame] = new PropertyDescriptor("frame", ATextFieldWithButton.class, "getFrame", "setFrame");


            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor("propertyChangeListeners", ATextFieldWithButton.class, "getPropertyChangeListeners", null);


            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", ATextFieldWithButton.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");


            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", ATextFieldWithButton.class, "isEnabled", "setEnabled");


            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", ATextFieldWithButton.class, "getMaximumSize", "setMaximumSize");


            properties[PROPERTY_warningIcon] = new PropertyDescriptor("warningIcon", ATextFieldWithButton.class, "getWarningIcon", "setWarningIcon");


            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", ATextFieldWithButton.class, "getMinimumSize", "setMinimumSize");


            properties[PROPERTY_text] = new PropertyDescriptor("text", ATextFieldWithButton.class, null, "setText");


            properties[PROPERTY_parent] = new PropertyDescriptor("parent", ATextFieldWithButton.class, "getParent", null);


            properties[PROPERTY_grp_Pane_InternScreenModule] = new PropertyDescriptor("grp_Pane_InternScreenModule", ATextFieldWithButton.class, "getGrp_Pane_InternScreenModule", "setGrp_Pane_InternScreenModule");


            properties[PROPERTY_x] = new PropertyDescriptor("x", ATextFieldWithButton.class, "getX", null);


            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor("requestFocusEnabled", ATextFieldWithButton.class, "isRequestFocusEnabled", "setRequestFocusEnabled");


            properties[PROPERTY_preferedSize] = new PropertyDescriptor("preferedSize", ATextFieldWithButton.class, null, "setPreferedSize");


            properties[PROPERTY_grp_Comp_previousComponent] = new PropertyDescriptor("grp_Comp_previousComponent", ATextFieldWithButton.class, "getGrp_Comp_previousComponent", "setGrp_Comp_previousComponent");


            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", ATextFieldWithButton.class, "getWorkingIcon", "setWorkingIcon");


            properties[PROPERTY_frameModal] = new PropertyDescriptor("frameModal", ATextFieldWithButton.class, "isFrameModal", "setFrameModal");


            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor("nextFocusableComponent", ATextFieldWithButton.class, "getNextFocusableComponent", "setNextFocusableComponent");


            properties[PROPERTY_cleUnik] = new PropertyDescriptor("cleUnik", ATextFieldWithButton.class, "getCleUnik", null);


            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", ATextFieldWithButton.class, "getPreferredSize", "setPreferredSize");


            properties[PROPERTY_name] = new PropertyDescriptor("name", ATextFieldWithButton.class, "getName", "setName");


            properties[PROPERTY_verif] = new PropertyDescriptor("verif", ATextFieldWithButton.class, "getverif", null);


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


    private static final int EVENT_vetoableChangeListener = 6;


    private static final int EVENT_keyListener = 7;


    private static final int EVENT_componentListener = 8;


    private static final int EVENT_mouseListener = 9;


    private static final int EVENT_propertyChangeListener = 10;


    private static final int EVENT_hierarchyListener = 11;


    private static final int EVENT_mouseWheelListener = 12;

    // EventSet array

    /*lazy EventSetDescriptor*/;


    private static EventSetDescriptor[] getEdescriptor() {


        EventSetDescriptor[] eventSets = new EventSetDescriptor[13];


        try {


            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[]{"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener");


            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[]{"mouseMoved", "mouseDragged"}, "addMouseMotionListener", "removeMouseMotionListener");


            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[]{"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener");


            eventSets[EVENT_containerListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "containerListener", java.awt.event.ContainerListener.class, new String[]{"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener");


            eventSets[EVENT_ancestorListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[]{"ancestorMoved", "ancestorRemoved", "ancestorAdded"}, "addAncestorListener", "removeAncestorListener");


            eventSets[EVENT_focusListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "focusListener", java.awt.event.FocusListener.class, new String[]{"focusLost", "focusGained"}, "addFocusListener", "removeFocusListener");


            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[]{"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener");


            eventSets[EVENT_keyListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "keyListener", java.awt.event.KeyListener.class, new String[]{"keyReleased", "keyPressed", "keyTyped"}, "addKeyListener", "removeKeyListener");


            eventSets[EVENT_componentListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "componentListener", java.awt.event.ComponentListener.class, new String[]{"componentResized", "componentMoved", "componentHidden", "componentShown"}, "addComponentListener", "removeComponentListener");


            eventSets[EVENT_mouseListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "mouseListener", java.awt.event.MouseListener.class, new String[]{"mouseExited", "mouseEntered", "mouseReleased", "mouseClicked", "mousePressed"}, "addMouseListener", "removeMouseListener");


            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[]{"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener");


            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[]{"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener");


            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[]{"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


        return eventSets;
    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods


    private static final int METHOD_loadContentString0 = 0;


    private static final int METHOD_getVerif1 = 1;


    private static final int METHOD_requestFocus2 = 2;


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


    private static final int METHOD_enable57 = 57;


    private static final int METHOD_removeAll58 = 58;


    private static final int METHOD_insets59 = 59;


    private static final int METHOD_add60 = 60;


    private static final int METHOD_add61 = 61;


    private static final int METHOD_remove62 = 62;


    private static final int METHOD_getComponentAt63 = 63;


    private static final int METHOD_applyComponentOrientation64 = 64;


    private static final int METHOD_invalidate65 = 65;


    private static final int METHOD_transferFocusDownCycle66 = 66;


    private static final int METHOD_transferFocusBackward67 = 67;


    private static final int METHOD_minimumSize68 = 68;


    private static final int METHOD_findComponentAt69 = 69;


    private static final int METHOD_isFocusCycleRoot70 = 70;


    private static final int METHOD_add71 = 71;


    private static final int METHOD_add72 = 72;


    private static final int METHOD_list73 = 73;


    private static final int METHOD_isAncestorOf74 = 74;


    private static final int METHOD_paintComponents75 = 75;


    private static final int METHOD_getComponentAt76 = 76;


    private static final int METHOD_add77 = 77;


    private static final int METHOD_areFocusTraversalKeysSet78 = 78;


    private static final int METHOD_locate79 = 79;


    private static final int METHOD_deliverEvent80 = 80;


    private static final int METHOD_printComponents81 = 81;


    private static final int METHOD_layout82 = 82;


    private static final int METHOD_remove83 = 83;


    private static final int METHOD_preferredSize84 = 84;


    private static final int METHOD_findComponentAt85 = 85;


    private static final int METHOD_validate86 = 86;


    private static final int METHOD_doLayout87 = 87;


    private static final int METHOD_list88 = 88;


    private static final int METHOD_countComponents89 = 89;


    private static final int METHOD_inside90 = 90;


    private static final int METHOD_add91 = 91;


    private static final int METHOD_handleEvent92 = 92;


    private static final int METHOD_createImage93 = 93;


    private static final int METHOD_dispatchEvent94 = 94;


    private static final int METHOD_mouseMove95 = 95;


    private static final int METHOD_getLocation96 = 96;


    private static final int METHOD_list97 = 97;


    private static final int METHOD_transferFocusUpCycle98 = 98;


    private static final int METHOD_action99 = 99;


    private static final int METHOD_setSize100 = 100;


    private static final int METHOD_paintAll101 = 101;


    private static final int METHOD_size102 = 102;


    private static final int METHOD_postEvent103 = 103;


    private static final int METHOD_mouseEnter104 = 104;


    private static final int METHOD_hasFocus105 = 105;


    private static final int METHOD_move106 = 106;


    private static final int METHOD_location107 = 107;


    private static final int METHOD_mouseExit108 = 108;


    private static final int METHOD_transferFocus109 = 109;


    private static final int METHOD_nextFocus110 = 110;


    private static final int METHOD_getFontMetrics111 = 111;


    private static final int METHOD_remove112 = 112;


    private static final int METHOD_getSize113 = 113;


    private static final int METHOD_repaint114 = 114;


    private static final int METHOD_mouseUp115 = 115;


    private static final int METHOD_keyDown116 = 116;


    private static final int METHOD_list117 = 117;


    private static final int METHOD_lostFocus118 = 118;


    private static final int METHOD_setLocation119 = 119;


    private static final int METHOD_mouseDown120 = 120;


    private static final int METHOD_resize121 = 121;


    private static final int METHOD_imageUpdate122 = 122;


    private static final int METHOD_repaint123 = 123;


    private static final int METHOD_repaint124 = 124;


    private static final int METHOD_keyUp125 = 125;


    private static final int METHOD_show126 = 126;


    private static final int METHOD_list127 = 127;


    private static final int METHOD_checkImage128 = 128;


    private static final int METHOD_checkImage129 = 129;


    private static final int METHOD_toString130 = 130;


    private static final int METHOD_show131 = 131;


    private static final int METHOD_prepareImage132 = 132;


    private static final int METHOD_prepareImage133 = 133;


    private static final int METHOD_hide134 = 134;


    private static final int METHOD_createImage135 = 135;


    private static final int METHOD_resize136 = 136;


    private static final int METHOD_createVolatileImage137 = 137;


    private static final int METHOD_setBounds138 = 138;


    private static final int METHOD_bounds139 = 139;


    private static final int METHOD_enable140 = 140;


    private static final int METHOD_contains141 = 141;


    private static final int METHOD_mouseDrag142 = 142;


    private static final int METHOD_enableInputMethods143 = 143;


    private static final int METHOD_setLocation144 = 144;


    private static final int METHOD_setSize145 = 145;


    private static final int METHOD_createVolatileImage146 = 146;


    private static final int METHOD_gotFocus147 = 147;

    // Method array

    /*lazy MethodDescriptor*/;


    private static MethodDescriptor[] getMdescriptor() {


        MethodDescriptor[] methods = new MethodDescriptor[148];


        try {


            methods[METHOD_loadContentString0] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("loadContentString", new Class[]{Integer.TYPE}));


            methods[METHOD_loadContentString0].setDisplayName("");


            methods[METHOD_getVerif1] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getVerif", new Class[]{}));


            methods[METHOD_getVerif1].setDisplayName("");


            methods[METHOD_requestFocus2] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("requestFocus", new Class[]{}));


            methods[METHOD_requestFocus2].setDisplayName("");


            methods[METHOD_updateUI3] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("updateUI", new Class[]{}));


            methods[METHOD_updateUI3].setDisplayName("");


            methods[METHOD_isLightweightComponent4] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("isLightweightComponent", new Class[]{java.awt.Component.class}));


            methods[METHOD_isLightweightComponent4].setDisplayName("");


            methods[METHOD_firePropertyChange5] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Boolean.TYPE, Boolean.TYPE}));


            methods[METHOD_firePropertyChange5].setDisplayName("");


            methods[METHOD_addPropertyChangeListener6] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("addPropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));


            methods[METHOD_addPropertyChangeListener6].setDisplayName("");


            methods[METHOD_grabFocus7] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("grabFocus", new Class[]{}));


            methods[METHOD_grabFocus7].setDisplayName("");


            methods[METHOD_disable8] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("disable", new Class[]{}));


            methods[METHOD_disable8].setDisplayName("");


            methods[METHOD_getToolTipText9] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getToolTipText", new Class[]{java.awt.event.MouseEvent.class}));


            methods[METHOD_getToolTipText9].setDisplayName("");


            methods[METHOD_firePropertyChange10] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Byte.TYPE, Byte.TYPE}));


            methods[METHOD_firePropertyChange10].setDisplayName("");


            methods[METHOD_registerKeyboardAction11] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, Integer.TYPE}));


            methods[METHOD_registerKeyboardAction11].setDisplayName("");


            methods[METHOD_registerKeyboardAction12] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("registerKeyboardAction", new Class[]{java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));


            methods[METHOD_registerKeyboardAction12].setDisplayName("");


            methods[METHOD_paintImmediately13] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("paintImmediately", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_paintImmediately13].setDisplayName("");


            methods[METHOD_printAll14] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("printAll", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_printAll14].setDisplayName("");


            methods[METHOD_revalidate15] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("revalidate", new Class[]{}));


            methods[METHOD_revalidate15].setDisplayName("");


            methods[METHOD_createToolTip16] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("createToolTip", new Class[]{}));


            methods[METHOD_createToolTip16].setDisplayName("");


            methods[METHOD_getInputMap17] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getInputMap", new Class[]{Integer.TYPE}));


            methods[METHOD_getInputMap17].setDisplayName("");


            methods[METHOD_getDefaultLocale18] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getDefaultLocale", new Class[]{}));


            methods[METHOD_getDefaultLocale18].setDisplayName("");


            methods[METHOD_paintImmediately19] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("paintImmediately", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_paintImmediately19].setDisplayName("");


            methods[METHOD_getInsets20] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getInsets", new Class[]{java.awt.Insets.class}));


            methods[METHOD_getInsets20].setDisplayName("");


            methods[METHOD_getConditionForKeyStroke21] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getConditionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_getConditionForKeyStroke21].setDisplayName("");


            methods[METHOD_firePropertyChange22] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_firePropertyChange22].setDisplayName("");


            methods[METHOD_getListeners23] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getListeners", new Class[]{java.lang.Class.class}));


            methods[METHOD_getListeners23].setDisplayName("");


            methods[METHOD_getInputMap24] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getInputMap", new Class[]{}));


            methods[METHOD_getInputMap24].setDisplayName("");


            methods[METHOD_getClientProperty25] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getClientProperty", new Class[]{java.lang.Object.class}));


            methods[METHOD_getClientProperty25].setDisplayName("");


            methods[METHOD_firePropertyChange26] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Long.TYPE, Long.TYPE}));


            methods[METHOD_firePropertyChange26].setDisplayName("");


            methods[METHOD_firePropertyChange27] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Short.TYPE, Short.TYPE}));


            methods[METHOD_firePropertyChange27].setDisplayName("");


            methods[METHOD_contains28] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("contains", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_contains28].setDisplayName("");


            methods[METHOD_repaint29] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("repaint", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_repaint29].setDisplayName("");


            methods[METHOD_repaint30] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("repaint", new Class[]{Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_repaint30].setDisplayName("");


            methods[METHOD_firePropertyChange31] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Double.TYPE, Double.TYPE}));


            methods[METHOD_firePropertyChange31].setDisplayName("");


            methods[METHOD_removePropertyChangeListener32] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("removePropertyChangeListener", new Class[]{java.lang.String.class, java.beans.PropertyChangeListener.class}));


            methods[METHOD_removePropertyChangeListener32].setDisplayName("");


            methods[METHOD_firePropertyChange33] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Character.TYPE, Character.TYPE}));


            methods[METHOD_firePropertyChange33].setDisplayName("");


            methods[METHOD_firePropertyChange34] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("firePropertyChange", new Class[]{java.lang.String.class, Float.TYPE, Float.TYPE}));


            methods[METHOD_firePropertyChange34].setDisplayName("");


            methods[METHOD_getSize35] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getSize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_getSize35].setDisplayName("");


            methods[METHOD_getActionForKeyStroke36] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getActionForKeyStroke", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_getActionForKeyStroke36].setDisplayName("");


            methods[METHOD_removeNotify37] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("removeNotify", new Class[]{}));


            methods[METHOD_removeNotify37].setDisplayName("");


            methods[METHOD_unregisterKeyboardAction38] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("unregisterKeyboardAction", new Class[]{javax.swing.KeyStroke.class}));


            methods[METHOD_unregisterKeyboardAction38].setDisplayName("");


            methods[METHOD_reshape39] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("reshape", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_reshape39].setDisplayName("");


            methods[METHOD_addNotify40] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("addNotify", new Class[]{}));


            methods[METHOD_addNotify40].setDisplayName("");


            methods[METHOD_print41] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("print", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_print41].setDisplayName("");


            methods[METHOD_resetKeyboardActions42] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("resetKeyboardActions", new Class[]{}));


            methods[METHOD_resetKeyboardActions42].setDisplayName("");


            methods[METHOD_requestDefaultFocus43] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("requestDefaultFocus", new Class[]{}));


            methods[METHOD_requestDefaultFocus43].setDisplayName("");


            methods[METHOD_getPropertyChangeListeners44] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getPropertyChangeListeners", new Class[]{java.lang.String.class}));


            methods[METHOD_getPropertyChangeListeners44].setDisplayName("");


            methods[METHOD_setInputMap45] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setInputMap", new Class[]{Integer.TYPE, javax.swing.InputMap.class}));


            methods[METHOD_setInputMap45].setDisplayName("");


            methods[METHOD_paint46] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("paint", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paint46].setDisplayName("");


            methods[METHOD_getBounds47] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getBounds", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_getBounds47].setDisplayName("");


            methods[METHOD_scrollRectToVisible48] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("scrollRectToVisible", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_scrollRectToVisible48].setDisplayName("");


            methods[METHOD_putClientProperty49] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("putClientProperty", new Class[]{java.lang.Object.class, java.lang.Object.class}));


            methods[METHOD_putClientProperty49].setDisplayName("");


            methods[METHOD_update50] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("update", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_update50].setDisplayName("");


            methods[METHOD_computeVisibleRect51] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("computeVisibleRect", new Class[]{java.awt.Rectangle.class}));


            methods[METHOD_computeVisibleRect51].setDisplayName("");


            methods[METHOD_getToolTipLocation52] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getToolTipLocation", new Class[]{java.awt.event.MouseEvent.class}));


            methods[METHOD_getToolTipLocation52].setDisplayName("");


            methods[METHOD_setDefaultLocale53] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setDefaultLocale", new Class[]{java.util.Locale.class}));


            methods[METHOD_setDefaultLocale53].setDisplayName("");


            methods[METHOD_requestFocus54] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("requestFocus", new Class[]{Boolean.TYPE}));


            methods[METHOD_requestFocus54].setDisplayName("");


            methods[METHOD_getLocation55] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getLocation", new Class[]{java.awt.Point.class}));


            methods[METHOD_getLocation55].setDisplayName("");


            methods[METHOD_requestFocusInWindow56] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("requestFocusInWindow", new Class[]{}));


            methods[METHOD_requestFocusInWindow56].setDisplayName("");


            methods[METHOD_enable57] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("enable", new Class[]{}));


            methods[METHOD_enable57].setDisplayName("");


            methods[METHOD_removeAll58] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("removeAll", new Class[]{}));


            methods[METHOD_removeAll58].setDisplayName("");


            methods[METHOD_insets59] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("insets", new Class[]{}));


            methods[METHOD_insets59].setDisplayName("");


            methods[METHOD_add60] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.awt.Component.class, Integer.TYPE}));


            methods[METHOD_add60].setDisplayName("");


            methods[METHOD_add61] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.awt.Component.class, java.lang.Object.class, Integer.TYPE}));


            methods[METHOD_add61].setDisplayName("");


            methods[METHOD_remove62] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("remove", new Class[]{Integer.TYPE}));


            methods[METHOD_remove62].setDisplayName("");


            methods[METHOD_getComponentAt63] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_getComponentAt63].setDisplayName("");


            methods[METHOD_applyComponentOrientation64] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("applyComponentOrientation", new Class[]{java.awt.ComponentOrientation.class}));


            methods[METHOD_applyComponentOrientation64].setDisplayName("");


            methods[METHOD_invalidate65] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("invalidate", new Class[]{}));


            methods[METHOD_invalidate65].setDisplayName("");


            methods[METHOD_transferFocusDownCycle66] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("transferFocusDownCycle", new Class[]{}));


            methods[METHOD_transferFocusDownCycle66].setDisplayName("");


            methods[METHOD_transferFocusBackward67] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("transferFocusBackward", new Class[]{}));


            methods[METHOD_transferFocusBackward67].setDisplayName("");


            methods[METHOD_minimumSize68] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("minimumSize", new Class[]{}));


            methods[METHOD_minimumSize68].setDisplayName("");


            methods[METHOD_findComponentAt69] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("findComponentAt", new Class[]{java.awt.Point.class}));


            methods[METHOD_findComponentAt69].setDisplayName("");


            methods[METHOD_isFocusCycleRoot70] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("isFocusCycleRoot", new Class[]{java.awt.Container.class}));


            methods[METHOD_isFocusCycleRoot70].setDisplayName("");


            methods[METHOD_add71] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.awt.Component.class, java.lang.Object.class}));


            methods[METHOD_add71].setDisplayName("");


            methods[METHOD_add72] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.awt.Component.class}));


            methods[METHOD_add72].setDisplayName("");


            methods[METHOD_list73] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("list", new Class[]{java.io.PrintStream.class, Integer.TYPE}));


            methods[METHOD_list73].setDisplayName("");


            methods[METHOD_isAncestorOf74] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("isAncestorOf", new Class[]{java.awt.Component.class}));


            methods[METHOD_isAncestorOf74].setDisplayName("");


            methods[METHOD_paintComponents75] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("paintComponents", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paintComponents75].setDisplayName("");


            methods[METHOD_getComponentAt76] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getComponentAt", new Class[]{java.awt.Point.class}));


            methods[METHOD_getComponentAt76].setDisplayName("");


            methods[METHOD_add77] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.lang.String.class, java.awt.Component.class}));


            methods[METHOD_add77].setDisplayName("");


            methods[METHOD_areFocusTraversalKeysSet78] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("areFocusTraversalKeysSet", new Class[]{Integer.TYPE}));


            methods[METHOD_areFocusTraversalKeysSet78].setDisplayName("");


            methods[METHOD_locate79] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("locate", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_locate79].setDisplayName("");


            methods[METHOD_deliverEvent80] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("deliverEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_deliverEvent80].setDisplayName("");


            methods[METHOD_printComponents81] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("printComponents", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_printComponents81].setDisplayName("");


            methods[METHOD_layout82] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("layout", new Class[]{}));


            methods[METHOD_layout82].setDisplayName("");


            methods[METHOD_remove83] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("remove", new Class[]{java.awt.Component.class}));


            methods[METHOD_remove83].setDisplayName("");


            methods[METHOD_preferredSize84] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("preferredSize", new Class[]{}));


            methods[METHOD_preferredSize84].setDisplayName("");


            methods[METHOD_findComponentAt85] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("findComponentAt", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_findComponentAt85].setDisplayName("");


            methods[METHOD_validate86] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("validate", new Class[]{}));


            methods[METHOD_validate86].setDisplayName("");


            methods[METHOD_doLayout87] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("doLayout", new Class[]{}));


            methods[METHOD_doLayout87].setDisplayName("");


            methods[METHOD_list88] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("list", new Class[]{java.io.PrintWriter.class, Integer.TYPE}));


            methods[METHOD_list88].setDisplayName("");


            methods[METHOD_countComponents89] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("countComponents", new Class[]{}));


            methods[METHOD_countComponents89].setDisplayName("");


            methods[METHOD_inside90] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("inside", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_inside90].setDisplayName("");


            methods[METHOD_add91] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("add", new Class[]{java.awt.PopupMenu.class}));


            methods[METHOD_add91].setDisplayName("");


            methods[METHOD_handleEvent92] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("handleEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_handleEvent92].setDisplayName("");


            methods[METHOD_createImage93] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("createImage", new Class[]{java.awt.image.ImageProducer.class}));


            methods[METHOD_createImage93].setDisplayName("");


            methods[METHOD_dispatchEvent94] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("dispatchEvent", new Class[]{java.awt.AWTEvent.class}));


            methods[METHOD_dispatchEvent94].setDisplayName("");


            methods[METHOD_mouseMove95] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseMove", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseMove95].setDisplayName("");


            methods[METHOD_getLocation96] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getLocation", new Class[]{}));


            methods[METHOD_getLocation96].setDisplayName("");


            methods[METHOD_list97] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("list", new Class[]{}));


            methods[METHOD_list97].setDisplayName("");


            methods[METHOD_transferFocusUpCycle98] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("transferFocusUpCycle", new Class[]{}));


            methods[METHOD_transferFocusUpCycle98].setDisplayName("");


            methods[METHOD_action99] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("action", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_action99].setDisplayName("");


            methods[METHOD_setSize100] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setSize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_setSize100].setDisplayName("");


            methods[METHOD_paintAll101] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("paintAll", new Class[]{java.awt.Graphics.class}));


            methods[METHOD_paintAll101].setDisplayName("");


            methods[METHOD_size102] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("size", new Class[]{}));


            methods[METHOD_size102].setDisplayName("");


            methods[METHOD_postEvent103] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("postEvent", new Class[]{java.awt.Event.class}));


            methods[METHOD_postEvent103].setDisplayName("");


            methods[METHOD_mouseEnter104] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseEnter", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseEnter104].setDisplayName("");


            methods[METHOD_hasFocus105] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("hasFocus", new Class[]{}));


            methods[METHOD_hasFocus105].setDisplayName("");


            methods[METHOD_move106] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("move", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_move106].setDisplayName("");


            methods[METHOD_location107] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("location", new Class[]{}));


            methods[METHOD_location107].setDisplayName("");


            methods[METHOD_mouseExit108] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseExit", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseExit108].setDisplayName("");


            methods[METHOD_transferFocus109] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("transferFocus", new Class[]{}));


            methods[METHOD_transferFocus109].setDisplayName("");


            methods[METHOD_nextFocus110] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("nextFocus", new Class[]{}));


            methods[METHOD_nextFocus110].setDisplayName("");


            methods[METHOD_getFontMetrics111] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getFontMetrics", new Class[]{java.awt.Font.class}));


            methods[METHOD_getFontMetrics111].setDisplayName("");


            methods[METHOD_remove112] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("remove", new Class[]{java.awt.MenuComponent.class}));


            methods[METHOD_remove112].setDisplayName("");


            methods[METHOD_getSize113] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("getSize", new Class[]{}));


            methods[METHOD_getSize113].setDisplayName("");


            methods[METHOD_repaint114] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("repaint", new Class[]{Long.TYPE}));


            methods[METHOD_repaint114].setDisplayName("");


            methods[METHOD_mouseUp115] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseUp", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseUp115].setDisplayName("");


            methods[METHOD_keyDown116] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("keyDown", new Class[]{java.awt.Event.class, Integer.TYPE}));


            methods[METHOD_keyDown116].setDisplayName("");


            methods[METHOD_list117] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("list", new Class[]{java.io.PrintStream.class}));


            methods[METHOD_list117].setDisplayName("");


            methods[METHOD_lostFocus118] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("lostFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_lostFocus118].setDisplayName("");


            methods[METHOD_setLocation119] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setLocation", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setLocation119].setDisplayName("");


            methods[METHOD_mouseDown120] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseDown", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseDown120].setDisplayName("");


            methods[METHOD_resize121] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("resize", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_resize121].setDisplayName("");


            methods[METHOD_imageUpdate122] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("imageUpdate", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_imageUpdate122].setDisplayName("");


            methods[METHOD_repaint123] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("repaint", new Class[]{}));


            methods[METHOD_repaint123].setDisplayName("");


            methods[METHOD_repaint124] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("repaint", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_repaint124].setDisplayName("");


            methods[METHOD_keyUp125] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("keyUp", new Class[]{java.awt.Event.class, Integer.TYPE}));


            methods[METHOD_keyUp125].setDisplayName("");


            methods[METHOD_show126] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("show", new Class[]{}));


            methods[METHOD_show126].setDisplayName("");


            methods[METHOD_list127] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("list", new Class[]{java.io.PrintWriter.class}));


            methods[METHOD_list127].setDisplayName("");


            methods[METHOD_checkImage128] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("checkImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));


            methods[METHOD_checkImage128].setDisplayName("");


            methods[METHOD_checkImage129] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("checkImage", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, java.awt.image.ImageObserver.class}));


            methods[METHOD_checkImage129].setDisplayName("");


            methods[METHOD_toString130] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("toString", new Class[]{}));


            methods[METHOD_toString130].setDisplayName("");


            methods[METHOD_show131] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("show", new Class[]{Boolean.TYPE}));


            methods[METHOD_show131].setDisplayName("");


            methods[METHOD_prepareImage132] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, java.awt.image.ImageObserver.class}));


            methods[METHOD_prepareImage132].setDisplayName("");


            methods[METHOD_prepareImage133] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("prepareImage", new Class[]{java.awt.Image.class, Integer.TYPE, Integer.TYPE, java.awt.image.ImageObserver.class}));


            methods[METHOD_prepareImage133].setDisplayName("");


            methods[METHOD_hide134] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("hide", new Class[]{}));


            methods[METHOD_hide134].setDisplayName("");


            methods[METHOD_createImage135] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("createImage", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_createImage135].setDisplayName("");


            methods[METHOD_resize136] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("resize", new Class[]{java.awt.Dimension.class}));


            methods[METHOD_resize136].setDisplayName("");


            methods[METHOD_createVolatileImage137] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE, java.awt.ImageCapabilities.class}));


            methods[METHOD_createVolatileImage137].setDisplayName("");


            methods[METHOD_setBounds138] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setBounds", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setBounds138].setDisplayName("");


            methods[METHOD_bounds139] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("bounds", new Class[]{}));


            methods[METHOD_bounds139].setDisplayName("");


            methods[METHOD_enable140] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("enable", new Class[]{Boolean.TYPE}));


            methods[METHOD_enable140].setDisplayName("");


            methods[METHOD_contains141] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("contains", new Class[]{java.awt.Point.class}));


            methods[METHOD_contains141].setDisplayName("");


            methods[METHOD_mouseDrag142] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("mouseDrag", new Class[]{java.awt.Event.class, Integer.TYPE, Integer.TYPE}));


            methods[METHOD_mouseDrag142].setDisplayName("");


            methods[METHOD_enableInputMethods143] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("enableInputMethods", new Class[]{Boolean.TYPE}));


            methods[METHOD_enableInputMethods143].setDisplayName("");


            methods[METHOD_setLocation144] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setLocation", new Class[]{java.awt.Point.class}));


            methods[METHOD_setLocation144].setDisplayName("");


            methods[METHOD_setSize145] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("setSize", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_setSize145].setDisplayName("");


            methods[METHOD_createVolatileImage146] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("createVolatileImage", new Class[]{Integer.TYPE, Integer.TYPE}));


            methods[METHOD_createVolatileImage146].setDisplayName("");


            methods[METHOD_gotFocus147] = new MethodDescriptor(srcastra.astra.gui.components.textFields.ATextFieldWithButton.class.getMethod("gotFocus", new Class[]{java.awt.Event.class, java.lang.Object.class}));


            methods[METHOD_gotFocus147].setDisplayName("");


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











