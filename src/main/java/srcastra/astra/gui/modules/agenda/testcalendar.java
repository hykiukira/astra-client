/*


 * testcalendar.java


 *


 * Created on 28 août 2003, 9:35


 */


package srcastra.astra.gui.modules.agenda;


import java.util.*;


/**
 * @author x
 */


public class testcalendar {


    /**
     * Creates a new instance of testcalendar
     */


    public testcalendar() {


        Calendar c = Calendar.getInstance();


        c.set(c.get(c.YEAR), c.get(c.MONTH), 1);

        //System.out.println("year "+c.get(c.YEAR)+" month "+c.get(c.MONTH)+" day"+c.get(c.DAY_OF_WEEK));


        System.out.println("Maximum: " + c.getActualMaximum(c.DAY_OF_MONTH));


    }


    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {


        new testcalendar();


    }


}


