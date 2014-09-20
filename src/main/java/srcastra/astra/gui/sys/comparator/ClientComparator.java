/*
 * ClientComparator.java
 *
 * Created on 8 novembre 2002, 8:37
 */

package srcastra.astra.gui.sys.comparator;
import java.util.Comparator;
import srcastra.astra.sys.classetransfert.clients.ClientAffichage;
/**
 *
 * @author  Thomas
 */
public class ClientComparator implements Comparator{
    
    /** Creates a new instance of ClientComparator */
    public ClientComparator(int sortCol,boolean sortAsc) {
        m_sortCol=sortCol;
        m_sortAsc=sortAsc;
    }
    
    public int compare(Object obj, Object obj1) {
        if(!(obj instanceof ClientAffichage)||!(obj1 instanceof ClientAffichage))
            return 0;
        ClientAffichage c1=(ClientAffichage)obj;
        ClientAffichage c2=(ClientAffichage)obj1;
        int result=0; 
        String str1=null;
        String str2=null;
        switch(m_sortCol){
            case 0:
                str1=(String)c1.m_nom.m_data;
                str2=(String)c2.m_nom.m_data;
                result=(str1.toUpperCase()).compareTo(str2.toUpperCase());
                break;
             case 1:
                str1=(String)c1.m_ref.m_data;
                str2=(String)c2.m_ref.m_data;
                result=(str1.toUpperCase()).compareTo(str2.toUpperCase());
                break;          
        }
        if(!m_sortAsc)
            result=-result;
        return result;
    }
    public boolean equals(Object obj){
        if(obj instanceof ClientComparator){
          ClientComparator obj2= (ClientComparator)obj;
          return (obj2.m_sortCol==m_sortCol) && (obj2.m_sortAsc=m_sortAsc);            
        }
        return false;
        
    }
protected int m_sortCol;
protected boolean m_sortAsc;
}
