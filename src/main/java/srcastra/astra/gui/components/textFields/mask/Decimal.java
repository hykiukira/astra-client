/*

 * Decimal.java

 *

 * Created on 17 juillet 2003, 15:29

 */


package srcastra.astra.gui.components.textFields.mask;

import java.util.Locale;

import javax.swing.text.*;

import srcastra.astra.gui.components.date.aDate.ADate;

import javax.swing.JTextField;

import java.util.Calendar;

import javax.swing.event.*;

import javax.swing.JTextField;


/**
 * @author thomas
 */

public class Decimal extends PlainDocument {


    /**
     * Creates a new instance of Decimal
     */

    int pointOffset;

    JTextField parent;

    boolean init;

    boolean one;

    public Decimal(JTextField parent) {

        super();

        this.parent = parent;

        // this.parent.addCaretListener(mycaret);

        try {

            init = true;

            initString(0, "          ", null);

        } catch (BadLocationException bn) {

            bn.printStackTrace();

        }

    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        System.out.println("longueur" + super.getLength());

        String total = super.getText(0, super.getLength());

        pointOffset = total.indexOf('.');

        System.out.println("total" + total);

        int valide = checkValidity(str, offs);

        // boolean valide2=checkValidity2(str,offs);

        // boolean valide2=true;

        if (valide == 1) {

            super.insertString(offs, str + " ", a);

            String point = super.getText(offs + 1, 1);

            System.out.println("point" + point);

            if (!point.equals("."))

                super.remove(offs + 1, 1);

        } else if (valide == 2) {

            super.insertString(offs, str, a);

        } else {

            super.insertString(offs, "0", a);

        }

        // super.remove(offs,1);

        /* if(offs==2  || offs==5){

            // if(super.getText(offs-1,1).equals(" ")){

            //  super.insertString(offs-1,str,null);

            //}

            System.out.println("c vide");

            super.insertString(offs,"/",null);

            super.remove(offs+1,1);

            offs=offs+1;

        }



        if(offs!=1 && offs!=4) super.remove(offs+1,1);

        if(offs==1){

            super.remove(2,1);

            parent.moveCaretPosition(3);

            one=true;

            parent.setSelectionStart(3);

            parent.setSelectionEnd(3);

        }

        if(offs==4){

            super.remove(5,1);

            // super.remove(5,1);

            parent.moveCaretPosition(6);

            one=true;

            parent.setSelectionStart(6);

            parent.setSelectionEnd(6);

        }*/

        /*if(offs==2 || offs==5){

            if(str.equals("-"))

               super.insertString(offs,str,a);

            else

                super.insertString(offs+1,str,a);

        }

        else{

            if(!init){

              if(!insertBeforeTild( offs,  str, a))

               super.insertString(offs,str,a);

            }

        }*/

    }

    private int checkValidity(String str, int offs) {

        if (offs < 0 || offs > 9) return 0;

        //  if(offs==2 || offs==5) return false;


        char[] tmp = str.toCharArray();


        if (pointOffset != -1) {

            if (offs > (pointOffset + 2)) {

                return 0;

            }


        }

        if (tmp.length == 1) {

            try {


                int value = Integer.parseInt(str);

                return 1;

            } catch (NumberFormatException nn) {

                if (tmp[0] == '-')

                    return 1;

                if (tmp[0] == '.' && offs > 1)

                    return 1;

                return 2;

            }

        } else {

            try {

                double value = Double.parseDouble(str);

                return 2;

            } catch (NumberFormatException nn) {

                return 0;

            }


        }

        //System.out.println("taille du tableau "+str.length());


    }

    public void initString(int offs, String str, AttributeSet a) throws BadLocationException {

        super.insertString(offs, str, a);

        init = false;

    }

    /*  public void remove(int offs,int length)throws BadLocationException {

         super.insertString(offs-1," ",null);

    }*/


}

