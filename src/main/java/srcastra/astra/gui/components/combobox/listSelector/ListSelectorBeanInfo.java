package srcastra.astra.gui.components.combobox.listSelector;


import java.beans.*;


public class ListSelectorBeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor


    private static BeanDescriptor beanDescriptor = new BeanDescriptor(ListSelector.class, null);


    private static BeanDescriptor getBdescriptor() {


        return beanDescriptor;


    }


    static {//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties


    private static final int PROPERTY_lastFocusedComponent = 0;


    private static final int PROPERTY_requiredInput = 1;


    private static final int PROPERTY_grp_Comp_nextComponent = 2;


    private static final int PROPERTY_nextFieldToFill = 3;


    private static final int PROPERTY_maximumSize = 4;


    private static final int PROPERTY_enabled = 5;


    private static final int PROPERTY_warningIcon = 6;


    private static final int PROPERTY_minimumSize = 7;


    private static final int PROPERTY_columnNumberForNextFieldToField = 8;


    private static final int PROPERTY_listSelectorTableModel = 9;


    private static final int PROPERTY_locale = 10;


    private static final int PROPERTY_grp_Comp_previousComponent = 11;


    private static final int PROPERTY_workingIcon = 12;


    private static final int PROPERTY_typeTextValue = 13;


    private static final int PROPERTY_preferredSize = 14;


    private static final int PROPERTY_name = 15;

    // Property array


    private static PropertyDescriptor[] properties = new PropertyDescriptor[16];


    private static PropertyDescriptor[] getPdescriptor() {


        return properties;


    }


    static {


        try {


            properties[PROPERTY_lastFocusedComponent] = new PropertyDescriptor("lastFocusedComponent", ListSelector.class, "isLastFocusedComponent", "setLastFocusedComponent");


            properties[PROPERTY_requiredInput] = new PropertyDescriptor("requiredInput", ListSelector.class, "isRequiredInput", "setRequiredInput");


            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", ListSelector.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");


            properties[PROPERTY_nextFieldToFill] = new PropertyDescriptor("nextFieldToFill", ListSelector.class, "getNextFieldToFill", "setNextFieldToFill");


            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", ListSelector.class, "getMaximumSize", "setMaximumSize");


            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", ListSelector.class, "isEnabled", "setEnabled");


            properties[PROPERTY_warningIcon] = new PropertyDescriptor("warningIcon", ListSelector.class, "getWarningIcon", "setWarningIcon");


            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", ListSelector.class, "getMinimumSize", "setMinimumSize");


            properties[PROPERTY_columnNumberForNextFieldToField] = new PropertyDescriptor("columnNumberForNextFieldToField", ListSelector.class, "getColumnNumberForNextFieldToField", "setColumnNumberForNextFieldToField");


            properties[PROPERTY_listSelectorTableModel] = new PropertyDescriptor("listSelectorTableModel", ListSelector.class, "getListSelectorTableModel", "setListSelectorTableModel");


            properties[PROPERTY_locale] = new PropertyDescriptor("locale", ListSelector.class, "getLocale", "setLocale");


            properties[PROPERTY_grp_Comp_previousComponent] = new PropertyDescriptor("grp_Comp_previousComponent", ListSelector.class, "getGrp_Comp_previousComponent", "setGrp_Comp_previousComponent");


            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", ListSelector.class, "getWorkingIcon", "setWorkingIcon");


            properties[PROPERTY_typeTextValue] = new PropertyDescriptor("typeTextValue", ListSelector.class, "getTypeTextValue", "setTypeTextValue");


            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", ListSelector.class, "getPreferredSize", "setPreferredSize");


            properties[PROPERTY_name] = new PropertyDescriptor("name", ListSelector.class, "getName", "setName");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.


    }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events


    private static final int EVENT_actionListener = 0;

    // EventSet array


    private static EventSetDescriptor[] eventSets = new EventSetDescriptor[1];


    private static EventSetDescriptor[] getEdescriptor() {


        return eventSets;


    }


    static {


        try {


            eventSets[EVENT_actionListener] = new EventSetDescriptor(srcastra.astra.gui.components.combobox.listSelector.ListSelector.class, "actionListener", java.awt.event.ActionListener.class, new String[]{"actionPerformed"}, "addActionListener", "removeActionListener");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.


    }//GEN-LAST:Events

    // Method identifiers //GEN-FIRST:Methods

    // Method array


    private static MethodDescriptor[] methods = new MethodDescriptor[0];


    private static MethodDescriptor[] getMdescriptor() {


        return methods;


    }

//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.

    //GEN-LAST:Methods


    private static java.awt.Image iconColor16 = null; //GEN-BEGIN:IconsDef


    private static java.awt.Image iconColor32 = null;


    private static java.awt.Image iconMono16 = null;


    private static java.awt.Image iconMono32 = null; //GEN-END:IconsDef


    private static String iconNameC16 = "/srcastra/astra/gui/components/combobox/listSelector/icon/16x16.gif";//GEN-BEGIN:Icons


    private static String iconNameC32 = "/srcastra/astra/gui/components/combobox/listSelector/icon/32X32.gif";


    private static String iconNameM16 = null;


    private static String iconNameM32 = null;//GEN-END:Icons


    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx


    private static final int defaultEventIndex = -1;//GEN-END:Idx


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


        return beanDescriptor;


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


        return properties;


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


        return eventSets;


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


        return methods;


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











