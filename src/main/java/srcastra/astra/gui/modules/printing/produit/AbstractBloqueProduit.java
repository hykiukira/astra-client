/*
 * AbstractBloqueProduit.java
 *
 * Created on 18 mars 2003, 16:18
 */

package srcastra.astra.gui.modules.printing.produit;
import srcastra.astra.gui.modules.printing.header.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.classetransfert.utils.CalculDate;

/**
 *
 * @author  Thomas
 */
public abstract class AbstractBloqueProduit extends AbstractBloque{
    
    /** Creates a new instance of AbstractBloqueProduit */
    public  AbstractBloqueProduit(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,java.awt.Frame frame) {
          super(obj,obj2,obj3,obj4,frame,report);      
    }
    
}
