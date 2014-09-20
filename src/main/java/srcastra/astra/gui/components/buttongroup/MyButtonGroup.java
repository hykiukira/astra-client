/*





 * MyButtonGroup.java





 *





 * Created on 10 december 2002, 9:29





 */


package srcastra.astra.gui.components.buttongroup;


import javax.swing.*;


import java.util.*;


import java.awt.Component;


/**
 * @author Thomas
 */


public class MyButtonGroup extends ButtonGroup {


    /**
     * Creates a new instance of MyButtonGroup
     */


    public MyButtonGroup() {


        super();


        hashComp = new Hashtable();


    }


    public void add(AbstractButton b, Component comp)


    {


        super.add(b);

        // JRadioButton tmpbutton=(JRadioButton)b;


        String s = "";


        if (b instanceof JRadioButton) {


            JRadioButton tmpbutton = (JRadioButton) b;


            s = tmpbutton.getName();


        } else if (b instanceof JCheckBox) {


            JCheckBox tmpbutton2 = (JCheckBox) b;


            s = tmpbutton2.getName();


        }


        System.out.println("nom:" + s);


        hashComp.put(s, comp);


    }


    public void setSelected(ButtonModel m, boolean b) {


        super.setSelected(m, b);


        for (Enumeration e = this.getElements(); e.hasMoreElements();) {

            // JRadioButton b2 = (JRadioButton)e.nextElement();


            JToggleButton b2 = (JToggleButton) e.nextElement();


            Component tmpcom = (Component) hashComp.get(b2.getName());


            if (b2.getModel() == this.getSelection()) {


                tmpcom.setEnabled(true);


                tmpcom.requestFocus();


            } else


                tmpcom.setEnabled(false);


        }

        //return null;


    }


    public void setNextElementSelected(ButtonModel m, boolean b) {

        //super.setSelected(m,b);


        try {


            for (Enumeration e = this.getElements(); e.hasMoreElements();) {

                //  JRadioButton b2 = (JRadioButton)e.nextElement();


                JToggleButton b2 = (JToggleButton) e.nextElement();


                Component tmpcom = (Component) hashComp.get(b2.getName());


                if (b2.getModel() == this.getSelection()) {


                    tmpcom.setEnabled(true);


                    tmpcom.requestFocus();

                    // JRadioButton b3 =(JRadioButton)e.nextElement();


                    JToggleButton b3 = (JToggleButton) e.nextElement();


                    super.setSelected(b3.getModel(), b);


                }


            }


        } catch (NoSuchElementException ne) {


            Enumeration e = this.getElements();


            JRadioButton b4 = (JRadioButton) e.nextElement();


            super.setSelected(b4.getModel(), b);


        }

        //return null;


    }

    // public static JRadioButton getSelection(ButtonGroup group) {


    public static JToggleButton getSelection(ButtonGroup group) {


        for (Enumeration e = group.getElements(); e.hasMoreElements();) {

            //  JRadioButton b = (JRadioButton)e.nextElement();


            JToggleButton b = (JToggleButton) e.nextElement();


            if (b.getModel() == group.getSelection()) {


                return b;


            }


        }


        return null;


    }


    public boolean isSelected(JToggleButton btn) {


        if (btn.getModel() == this.getSelection()) {


            return true;


        } else return false;


    }


    Hashtable hashComp;


}





