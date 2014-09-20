/*




 * DateFormat.java




 *




 * Created on 21 janvier 2003, 10:34




 */


package srcastra.astra.gui.components.textFields.FormatedTextField.formats;


import java.text.*;


/**
 * @author thomas
 */


public class My_DateFormat extends java.text.Format {


    private SimpleDateFormat sformat;


    /**
     * Creates a new instance of DateFormat
     */


    public My_DateFormat() {


        sformat = new SimpleDateFormat("dd-MM-yy");


    }


    public StringBuffer format(Object obj, StringBuffer stringBuffer, java.text.FieldPosition fieldPosition) {


        return sformat.format(obj, stringBuffer, fieldPosition);


    }


    public Object parseObject(String str, java.text.ParsePosition parsePosition) {


        return sformat.parseObject(str, parsePosition);


    }


}



















