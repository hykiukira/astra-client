/*





 * DecimalFilter.java





 *





 * Created on 26 novembre 2002, 15:24





 */


package srcastra.astra.gui.filter;


import javax.swing.text.*;


/**
 * @author Thomas
 */


public class DecimalFilter extends DocumentFilter {


    /**
     * Creates a new instance of DecimalFilter
     */


    public DecimalFilter() {


    }


    int maxSize;


    public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr) {// throws BadLocationException {


        try {


            int length = str.length();


            System.out.println("Chaine " + str);


            fb.insertString(offset, str, null);


        }


        catch (BadLocationException bn) {


            bn.printStackTrace();


        }

        // fb.replace(offset, length, str, null);


    }


    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,


                        String str, AttributeSet attrs) throws BadLocationException {


        int newLength = fb.getDocument().getLength() - length + str.length();


        if (newLength <= maxSize) {


            fb.replace(offset, length, str, attrs);


        } else {


            throw new BadLocationException("New characters exceeds max size of document", offset);


        }


    }


}





