/*
 * AbstractReport.java
 *
 * Created on 14 mars 2003, 14:40
 */

package srcastra.astra.gui.modules.printing;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.produit.*;

/**
 *
 * @author  Thomas
 */
public abstract class AbstractReport extends RReportJ2{
    
    /** Creates a new instance of AbstractReport */
    public AbstractReport(java.awt.Frame frame) {
        super(frame);
    }
     public abstract void prepareArea();
protected AbstractHeader header;  
protected AbstractHeader detail1;  
protected AbstractProduitEdition detailproduit1; 
protected AbstractProduitEdition detailproduit2; 
protected AbstractProduitEdition detailproduit3;
protected AbstractHeader tvaDetail;
protected AbstractHeader footer; 
protected AbstractHeader footer2;
}
