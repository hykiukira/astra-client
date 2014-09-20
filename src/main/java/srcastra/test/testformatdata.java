/*
 * testformatdata.java
 *
 * Created on 28 octobre 2002, 12:17
 */

package srcastra.test;
import java.util.*;
import java.text.*;

/**
 *
 * @author  Thomas
 */
public class testformatdata {
    
    /** Creates a new instance of testformatdata */
    public testformatdata() {
     

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        float value=1000.20f;
     NumberFormat formatter = new DecimalFormat("####0.00");
    Locale locale = Locale.FRENCH;
  //  String string = NumberFormat.getNumberInstance(locale).format(-12341245.56);  // -1,234.56

     String s = formatter.format(100012.20);  // -001235
    // formatter = new DecimalFormat("#.000000");
     s = formatter.format(-1234.567);         // -1234.567000

     
     Number num=formatter.parse(s);
     System.out.println(s);
    // System.out.println(string);
     float value2=num.floatValue();
     //float value2=Float.parseFloat(s);
    System.out.println(value2);
    /* Calendar c=Calendar.getInstance();
     c.set(2002,10,10);
     c.add(c.DATE,25);
     System.out.println("year "+c.get(Calendar.YEAR));
     System.out.println("Month "+c.get(Calendar.MONTH));
     System.out.println("day "+c.get(Calendar.DAY_OF_MONTH));*/
    
    }
    
}
