/*





 * lol.java





 *





 * Created on 30 mars 2002, 10:54





 */


package srcastra.astra.gui.components.InsertCombo;


import java.util.ArrayList;


/**
 * @author Sébastien
 */


public class ManipuleTable {


    private InsertCombo parent;


    private String tmpText;


    private char comboFirstChar = 'p';


    private int index = -1;


    /**
     * Creates new lol
     */


    public ManipuleTable(InsertCombo insertCombo) {


        this.parent = insertCombo;


        this.parent.position = new ArrayList();


        this.parent.position.add(new Integer(0));


    }


    /**
     * Dicotomie pour la recherche des mots dans l'Array List
     */


    private int searchCurrentWord() {


        int tmpsize2;


        boolean sw2 = false;


        boolean retour = false;


        boolean sw = false;


        int sub_end = parent.getTextGrp_TField_affiche().length();


        int size = parent.data.size();


        int tmpsize = parent.position.size();


        int i = 0;


        String TfieldParse;


        int test = 0;


        String text = parent.getTextGrp_TField_affiche();


        System.out.println("size de parent.position :" + tmpsize);


        String str;


        String tmpstring;


        Object[] obj;


        Object tmpobj;


        String transMaj1 = null;


        String transMaj2 = null;


        tmpobj = parent.position.get(tmpsize - 1);


        tmpstring = tmpobj.toString();


        i = Integer.parseInt(tmpstring);


        retour = verifBorne();


        if (retour == true) {


            do {


                str = getObjectInArray(i, 1, parent.data).toString();

                // obj = (Object[]) parent.data.get(i);

                //str = (String) obj[1];


                tmpsize2 = str.length();


                if (tmpsize2 < sub_end) {


                    TfieldParse = str;


                    i++;


                    sw = false;


                } else


                {


                    TfieldParse = str.substring(0, sub_end);


                }


                transMaj1 = TfieldParse.toUpperCase();


                transMaj2 = text.toUpperCase();


                test = transMaj2.compareTo(transMaj1);


                if (sw == true && test != 0) {


                    i++;


                }


                sw = true;


            }


            while (i < size && test > 0);


            if (transMaj2.compareTo(transMaj1) == 0) {


                if (i != 0)


                    parent.position.add(new Integer(i));


                parent.setRowSelectionIntervalGrp_Table_Affiche(i, i);


                parent.scrollRectToVisibleGrp_table_Affiche(new java.awt.Rectangle(0, parent.getGrp_Table_Affiche().getRowHeight() * i, 20, parent.getGrp_Table_Affiche().getRowHeight()));


            } else {


                parent.setTextGrp_TField_affiche(this.tmpText);


            }


        }


        return (i - 1);


    }


    private boolean verifBorne() {


        boolean returnvalue;


        int sub_end = parent.getTextGrp_TField_affiche().length();


        int size = parent.data.size();


        Object[] obj1 = (Object[]) parent.data.get(0);


        Object[] obj2 = (Object[]) parent.data.get(size - 1);


        String str1 = (String) obj1[1];


        String str2 = (String) obj2[1];


        System.out.println("Compare 1 = " + parent.getTextGrp_TField_affiche().compareTo(str1));


        System.out.println("Compare 2 = " + parent.getTextGrp_TField_affiche().compareTo(str2));


        System.out.println("Compare 3 = " + parent.getTextGrp_TField_affiche());


        System.out.println("Compare Str1 = " + str1);


        System.out.println("Compare Str2 = " + str2);


        str1 = str1.toUpperCase();


        str2 = str2.toUpperCase();


        String tmpStr = parent.getTextGrp_TField_affiche().toUpperCase();

        // if(grp_TField_Encode.getText().compareTo(str1.substring(0,sub_end))>=0 && grp_TField_Encode.getText().compareTo(str2.substring(0,sub_end))<=0)


        if (tmpStr.compareTo(str1) >= 0 && tmpStr.compareTo(str2) <= 0) {


            returnvalue = true;


        } else {


            returnvalue = false;


        }


        System.out.println("je sors de verifborne");


        return returnvalue;


    }


    private Object getObjectInArray(int row, int col, ArrayList data) {


        Object[] obj;


        obj = (Object[]) data.get(row);


        Object returnValue = obj[col];


        return returnValue;


    }


    public void combo(int keyCode) {


        int debugi;


        char debugc;


        if (!checKeycode(keyCode))


        {


            char[] chT = parent.getTextGrp_TField_affiche().toCharArray();


            System.out.println("longueur du tableau :" + chT.length);


            if (chT.length > 0)


                debugc = chT[0];


            debugi = chT.length;


            int current_word = searchCurrentWord();


        }


    }


    /**
     * Setter for property comboFirstChar.
     *
     * @param comboFirstChar New value of property comboFirstChar.
     */


    public void setComboFirstChar(char comboFirstChar) {


        this.comboFirstChar = comboFirstChar;

        // chargeparent.data();


    }


    public int checkKeyCode(java.awt.event.KeyEvent evt) {


        int returnValue = 0;


        int keyCode = evt.getKeyCode();


        if (keyCode == java.awt.event.KeyEvent.VK_ENTER


                || keyCode == java.awt.event.KeyEvent.VK_UP


                || keyCode == java.awt.event.KeyEvent.VK_DOWN


                || keyCode == java.awt.event.KeyEvent.VK_LEFT


                || keyCode == java.awt.event.KeyEvent.VK_RIGHT


                || keyCode == java.awt.event.KeyEvent.VK_PAGE_UP


                || keyCode == java.awt.event.KeyEvent.VK_PAGE_DOWN)


            returnValue = 1;


        return returnValue;


    }


    /**
     * Getter for property comboFirstChar.
     *
     * @return Value of property comboFirstChar.
     */


    public char getComboFirstChar() {


        return comboFirstChar;


    }


    public boolean checKeycode(int keyCode)


    {


        Object tmpobj = null;


        String tmpstring = null;


        int tmpsize = 0;


        boolean returnValue = false;


        int newposition = 0;


        if (keyCode == 8)


        {


            index = -1;


            tmpsize = parent.position.size();


            if (tmpsize > 1)


            {


                parent.position.remove(tmpsize - 1);


                tmpobj = parent.position.get(tmpsize - 2);


                tmpstring = tmpobj.toString();


                newposition = Integer.parseInt(tmpstring);


                parent.setRowSelectionIntervalGrp_Table_Affiche(newposition, newposition);


                parent.scrollRectToVisibleGrp_table_Affiche(new java.awt.Rectangle(0, parent.getGrp_Table_Affiche().getRowHeight() * newposition, 20, parent.getGrp_Table_Affiche().getRowHeight()));


            } else if (tmpsize == 1)


            {


                tmpobj = parent.position.get(0);


                tmpstring = tmpobj.toString();


                newposition = Integer.parseInt(tmpstring);


                parent.setRowSelectionIntervalGrp_Table_Affiche(newposition, newposition);


                parent.scrollRectToVisibleGrp_table_Affiche(new java.awt.Rectangle(0, parent.getGrp_Table_Affiche().getRowHeight() * newposition, 20, parent.getGrp_Table_Affiche().getRowHeight()));

                //   grp_Pop_Menu.setVisible(false);


            }


            returnValue = true;


        } else


        {


            returnValue = false;


        }


        return returnValue;


    }


}