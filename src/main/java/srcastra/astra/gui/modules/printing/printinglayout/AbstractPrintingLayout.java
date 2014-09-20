/*
 * AbstractPrintingLayout.java
 *
 * Created on 4 mars 2003, 12:36
 */

package srcastra.astra.gui.modules.printing.printinglayout;
import com.java4less.rreport.*;
/**
 *
 * @author  Thomas
 */
public abstract class AbstractPrintingLayout {
    
    /** Creates a new instance of AbstractPrintingLayout */
    public AbstractPrintingLayout(RReportJ2 report) {
        this.report=report;
    }
RReportJ2 report;    
public abstract void performConfig1(boolean visible);
public abstract void performConfig2(boolean visible);
public abstract ReturnClass performConfig3(double xvariation,double yvariation);
public abstract ReturnClass performConfig4(double x,double y);
public abstract void performCOnfigDefault1(double x,double y);
public abstract void performConfigDefautl2(double x,double y);
public abstract ReturnClass performConfig5(double xvariation, double yvariation);
public abstract ReturnClass performConfig6(double xvariation, double yvariation);
public abstract ReturnClass performConfig7(double xvariation, double yvariation);
public abstract void performCOnfigDefault3(); 
}
  