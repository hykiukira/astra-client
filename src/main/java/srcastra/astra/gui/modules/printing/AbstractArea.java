/*
 * AbstractArea.java
 *
 * Created on 14 mars 2003, 11:51
 */

package srcastra.astra.gui.modules.printing;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.header.*;
/**
 *
 * @author  Thomas
 */
public abstract class AbstractArea extends RArea{
    
    /** Creates a new instance of AbstractArea */
    public AbstractArea(Object obj,Object obj2,Object obj3) {
        super();
    }
     public void addBloque(AbstractBloque bloque){
         for(int i=0;i<bloque.getFieldArray().size();i++){
           RField[] tmp=(RField[])bloque.getFieldArray().get(i);
           for(int j=0;j<tmp.length;j++){
               if(tmp[j]!=null )
                add(tmp[j]);
             /*  RLine ligne=new RLine();
               ligne.y=tmp[j].y+0.1d;
               ligne.x=tmp[j].x;
               ligne.width=tmp[j].width-0.1d;
               add(ligne);*/
           }
       }
      }

}
