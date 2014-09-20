package srcastra.astra.gui.components.textfield2;


import java.beans.*;


public class ATextFieldBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/;


    private static BeanDescriptor getBdescriptor() {


        BeanDescriptor beanDescriptor = new BeanDescriptor(ATextField.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties


    private static final int PROPERTY_lastFocusedComponent = 0;


    private static final int PROPERTY_verifyInputWhenFocusTarget = 1;


    private static final int PROPERTY_propertyChangeListeners = 2;


    private static final int PROPERTY_grp_Comp_nextComponent = 3;


    private static final int PROPERTY_maximumSize = 4;


    private static final int PROPERTY_enabled = 5;


    private static final int PROPERTY_warningIcon = 6;


    private static final int PROPERTY_toolTipText = 7;


    private static final int PROPERTY_minimumSize = 8;


    private static final int PROPERTY_parent = 9;


    private static final int PROPERTY_requestFocusEnabled = 10;


    private static final int PROPERTY_preferedSize = 11;


    private static final int PROPERTY_grp_Comp_previousComponent = 12;


    private static final int PROPERTY_workingIcon = 13;


    private static final int PROPERTY_preferredSize = 14;


    private static final int PROPERTY_name = 15;


    private static final int PROPERTY_verif = 16;

    // Property array

    /*lazy PropertyDescriptor*/;


    private static PropertyDescriptor[] getPdescriptor() {


        PropertyDescriptor[] properties = new PropertyDescriptor[17];


        try {


            properties[PROPERTY_lastFocusedComponent] = new PropertyDescriptor("lastFocusedComponent", ATextField.class, "isLastFocusedComponent", "setLastFocusedComponent");


            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor("verifyInputWhenFocusTarget", ATextField.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget");


            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor("propertyChangeListeners", ATextField.class, "getPropertyChangeListeners", null);


            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", ATextField.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");


            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", ATextField.class, "getMaximumSize", "setMaximumSize");


            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", ATextField.class, "isEnabled", "setEnabled");


            properties[PROPERTY_warningIcon] = new PropertyDescriptor("warningIcon", ATextField.class, "getWarningIcon", "setWarningIcon");


            properties[PROPERTY_toolTipText] = new PropertyDescriptor("toolTipText", ATextField.class, "getToolTipText", "setToolTipText");


            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", ATextField.class, "getMinimumSize", "setMinimumSize");


            properties[PROPERTY_parent] = new PropertyDescriptor("parent", ATextField.class, "getParent", null);


            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor("requestFocusEnabled", ATextField.class, "isRequestFocusEnabled", "setRequestFocusEnabled");


            properties[PROPERTY_preferedSize] = new PropertyDescriptor("preferedSize", ATextField.class, null, "setPreferedSize");


            properties[PROPERTY_grp_Comp_previousComponent] = new PropertyDescriptor("grp_Comp_previousComponent", ATextField.class, "getGrp_Comp_previousComponent", "setGrp_Comp_previousComponent");


            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", ATextField.class, "getWorkingIcon", "setWorkingIcon");


            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", ATextField.class, "getPreferredSize", "setPreferredSize");


            properties[PROPERTY_name] = new PropertyDescriptor("name", ATextField.class, "getName", "setName");


            properties[PROPERTY_verif] = new PropertyDescriptor("verif", ATextField.class, "getverif", null);


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


            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[]{"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener");


            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[]{"mouseMoved", "mouseDragged"}, "addMouseMotionListener", "removeMouseMotionListener");


            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[]{"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener");


            eventSets[EVENT_containerListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "containerListener", java.awt.event.ContainerListener.class, new String[]{"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener");


            eventSets[EVENT_ancestorListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[]{"ancestorMoved", "ancestorRemoved", "ancestorAdded"}, "addAncestorListener", "removeAncestorListener");


            eventSets[EVENT_focusListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "focusListener", java.awt.event.FocusListener.class, new String[]{"focusLost", "focusGained"}, "addFocusListener", "removeFocusListener");


            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[]{"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener");


            eventSets[EVENT_keyListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "keyListener", java.awt.event.KeyListener.class, new String[]{"keyReleased", "keyPressed", "keyTyped"}, "addKeyListener", "removeKeyListener");


            eventSets[EVENT_componentListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "componentListener", java.awt.event.ComponentListener.class, new String[]{"componentResized", "componentMoved", "componentHidden", "componentShown"}, "addComponentListener", "removeComponentListener");


            eventSets[EVENT_mouseListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "mouseListener", java.awt.event.MouseListener.class, new String[]{"mouseExited", "mouseEntered", "mouseReleased", "mouseClicked", "mousePressed"}, "addMouseListener", "removeMouseListener");


            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[]{"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener");


            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[]{"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener");


            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor(srcastra.astra.gui.components.textFields.ATextField.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[]{"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


        return eventSets;
    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods

    // Method array

    /*lazy MethodDescriptor*/;


    private static MethodDescriptor[] getMdescriptor() {


        MethodDescriptor[] methods = new MethodDescriptor[0];//GEN-HEADEREND:Methods

        // Here you can add code for customizing the methods array.


        return methods;
    }//GEN-LAST:Methods


    private static java.awt.Image iconColor16 = null; //GEN-BEGIN:IconsDef


    private static java.awt.Image iconColor32 = null;


    private static java.awt.Image iconMono16 = null;


    private static java.awt.Image iconMono32 = null; //GEN-END:IconsDef


    private static String iconNameC16 = "/srcastra/astra/gui/components/textFields/icon/16x16_256color.gif";//GEN-BEGIN:Icons


    private static String iconNameC32 = "/srcastra/astra/gui/components/textFields/icon/32x32_256color.gif";


    private static String iconNameM16 = null;


    private static String iconNameM32 = null;//GEN-END:Icons


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


    /**
     * This method returns an image object that can be used to
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * will typically be GIFs, but may in future include other formats.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * Beans aren't required to provide icons and may return null from
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * this method.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * There are four possible flavors of icons (16x16 color,
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * support a single icon we recommend supporting 16x16 color.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * We recommend that icons have a "transparent" background
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * so they can be rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested.  This should be
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32,
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 <p/>
     *                 ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return An image object representing the requested icon.  May
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         <p/>
     *         return null if no suitable icon is available.
     */


    public java.awt.Image getIcon(int iconKind) {


        switch (iconKind) {


            case ICON_COLOR_16x16:


                if (iconNameC16 == null)


                    return null;


                else {


                    if (iconColor16 == null)


                        iconColor16 = loadImage(iconNameC16);


                    return iconColor16;


                }


            case ICON_COLOR_32x32:


                if (iconNameC32 == null)


                    return null;


                else {


                    if (iconColor32 == null)


                        iconColor32 = loadImage(iconNameC32);


                    return iconColor32;


                }


            case ICON_MONO_16x16:


                if (iconNameM16 == null)


                    return null;


                else {


                    if (iconMono16 == null)


                        iconMono16 = loadImage(iconNameM16);


                    return iconMono16;


                }


            case ICON_MONO_32x32:


                if (iconNameM32 == null)


                    return null;


                else {


                    if (iconMono32 == null)


                        iconMono32 = loadImage(iconNameM32);


                    return iconMono32;


                }


            default:
                return null;


        }


    }


}











