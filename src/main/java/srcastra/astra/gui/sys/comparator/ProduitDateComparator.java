/*
 * ProduitDateComparator.java
 *
 * Created on 26 mars 2003, 9:18
 */

package srcastra.astra.gui.sys.comparator;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.utils.*;
/**
 *
 * @author  Thomas
 */
public class ProduitDateComparator extends ProduitComparator{
    
    /** Creates a new instance of ProduitDateComparator */
    public ProduitDateComparator(int sortCol, boolean sortAsc) {
        super(sortCol,sortAsc);
    }
     public int compare(Object obj, Object obj1) {
     //   if(!(obj instanceof ProduitAffichage)||!(obj1 instanceof ProduitAffichage))
       //     return 0;
        
        ProduitSynthese c1=(ProduitSynthese)obj;
        ProduitSynthese c2=(ProduitSynthese)obj1;
        Date date1=c1.getDateDep();
        Date date2=c2.getDateDep();
        if(date1== null)
            return 1;
        if(date2==null)
            return -1;
        if((date1.isOpen() || date1.isUnknow()) && (!date2.isOpen() && !date2.isUnknow()))
          return 1;
        if((date1.isOpen() || date1.isUnknow()) && (date2.isOpen() || date2.isUnknow()))
          return 0;
         if((date2.isOpen() || date2.isUnknow()) && (!date1.isOpen() && !date1.isUnknow()))
          return -1;
       // Long l1;
        //Long l2;
        String str1=null;
        String str2=null;
        int result=0;
        switch(m_sortCol){
            case 0:
                result=CalculDate.renvBiggerDate(date1,date2);
                break;
        }
        if(!m_sortAsc)
            result=-result;
        return result;
    }

 
}
