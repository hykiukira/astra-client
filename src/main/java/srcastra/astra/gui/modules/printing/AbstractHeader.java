/*
 * AbstractHeader.java
 *
 * Created on 14 mars 2003, 13:04
 */

package srcastra.astra.gui.modules.printing;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
/**
 *
 * @author  Thomas
 */
public abstract class AbstractHeader extends AbstractArea{
    
    /** Creates a new instance of AbstractHeader */
    public AbstractHeader(Object obj,Object obj2,Object obj3,Object obj4) {
          super(obj,obj2,obj3);  
    }
     public abstract void initBloqueGauche(Object obj,Object obj2,Object obj3,Object obj4) ;
      public void addBloque(AbstractBloque bloque){
        if(bloque!=null){
            if(bloque.getFieldArray()!=null){
         for(int i=0;i<bloque.getFieldArray().size();i++){
           RField[] tmp=(RField[])bloque.getFieldArray().get(i);
           for(int j=0;j<tmp.length;j++){
               add(tmp[j]);
           }
         }
       }
      }
      }
public double x1,x2,y1,y2;
}
