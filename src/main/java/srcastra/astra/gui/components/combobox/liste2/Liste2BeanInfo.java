package srcastra.astra.gui.components.combobox.liste2;


import java.beans.*;


public class Liste2BeanInfo extends SimpleBeanInfo {

    // Bean descriptor //GEN-FIRST:BeanDescriptor

    /*lazy BeanDescriptor*/;


    private static BeanDescriptor getBdescriptor() {


        BeanDescriptor beanDescriptor = new BeanDescriptor(Liste2.class, null);//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.


        return beanDescriptor;
    }//GEN-LAST:BeanDescriptor

    // Property identifiers //GEN-FIRST:Properties


    private static final int PROPERTY_grp_Comp_nextComponent = 0;


    private static final int PROPERTY_maximumSize = 1;


    private static final int PROPERTY_enabled = 2;


    private static final int PROPERTY_warningIcon = 3;


    private static final int PROPERTY_toolTipText = 4;


    private static final int PROPERTY_minimumSize = 5;


    private static final int PROPERTY_readModePanel = 6;


    private static final int PROPERTY_model = 7;


    private static final int PROPERTY_clesignalitique = 8;


    private static final int PROPERTY_filNexComponent = 9;


    private static final int PROPERTY_isLastComponent = 10;


    private static final int PROPERTY_affiche_panel = 11;


    private static final int PROPERTY_nbr_lettre_avant_affichage = 12;


    private static final int PROPERTY_visible = 13;


    private static final int PROPERTY_workingIcon = 14;


    private static final int PROPERTY_preferredSize = 15;


    private static final int PROPERTY_canbenull = 16;

    // Property array

    /*lazy PropertyDescriptor*/;


    private static PropertyDescriptor[] getPdescriptor() {


        PropertyDescriptor[] properties = new PropertyDescriptor[17];


        try {


            properties[PROPERTY_grp_Comp_nextComponent] = new PropertyDescriptor("grp_Comp_nextComponent", Liste2.class, "getGrp_Comp_nextComponent", "setGrp_Comp_nextComponent");


            properties[PROPERTY_maximumSize] = new PropertyDescriptor("maximumSize", Liste2.class, "getMaximumSize", "setMaximumSize");


            properties[PROPERTY_enabled] = new PropertyDescriptor("enabled", Liste2.class, "isEnabled", "setEnabled");


            properties[PROPERTY_warningIcon] = new PropertyDescriptor("warningIcon", Liste2.class, "getWarningIcon", "setWarningIcon");


            properties[PROPERTY_toolTipText] = new PropertyDescriptor("toolTipText", Liste2.class, "getToolTipText", "setToolTipText");


            properties[PROPERTY_minimumSize] = new PropertyDescriptor("minimumSize", Liste2.class, "getMinimumSize", "setMinimumSize");


            properties[PROPERTY_readModePanel] = new PropertyDescriptor("readModePanel", Liste2.class, "isReadModePanel", "setReadModePanel");


            properties[PROPERTY_model] = new PropertyDescriptor("model", Liste2.class, "getModel", "setModel");


            properties[PROPERTY_clesignalitique] = new PropertyDescriptor("clesignalitique", Liste2.class, "getClesignalitique", "setClesignalitique");


            properties[PROPERTY_filNexComponent] = new PropertyDescriptor("filNexComponent", Liste2.class, "isFilNexComponent", "setFilNexComponent");


            properties[PROPERTY_isLastComponent] = new PropertyDescriptor("isLastComponent", Liste2.class, "isIsLastComponent", "setIsLastComponent");


            properties[PROPERTY_affiche_panel] = new PropertyDescriptor("affiche_panel", Liste2.class, "isAffiche_panel", "setAffiche_panel");


            properties[PROPERTY_nbr_lettre_avant_affichage] = new PropertyDescriptor("nbr_lettre_avant_affichage", Liste2.class, "getNbr_lettre_avant_affichage", "setNbr_lettre_avant_affichage");


            properties[PROPERTY_visible] = new PropertyDescriptor("visible", Liste2.class, "isVisible", "setVisible");


            properties[PROPERTY_workingIcon] = new PropertyDescriptor("workingIcon", Liste2.class, "getWorkingIcon", "setWorkingIcon");


            properties[PROPERTY_preferredSize] = new PropertyDescriptor("preferredSize", Liste2.class, "getPreferredSize", "setPreferredSize");


            properties[PROPERTY_canbenull] = new PropertyDescriptor("canbenull", Liste2.class, "isCanbenull", "setCanbenull");


        }


        catch (IntrospectionException e) {
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.


        return properties;
    }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array

    /*lazy EventSetDescriptor*/;


    private static EventSetDescriptor[] getEdescriptor() {


        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events

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











