/*
 * FieldNotFoundException.java
 *
 * Created on 5 juin 2004, 22:20
 */

package srcastra.astra.sys.btn.workflow;
import java.util.*;
import org.jdom.*;
/**
 *
 * @author  administrateur
 */
public class FieldNotFoundException extends Exception{
    
    /** Creates a new instance of FieldNotFoundException */
    ArrayList array;
    public FieldNotFoundException(ArrayList array,String file) {
        
        this.array=array;        
        }
    public void showUnfoundElement(){
        System.out.println("\n\n\n\n\n\n[*********Showin fucking elements]*********\n\n\n\n");
        if(array!=null){
          for(int i=0;i<array.size();i++)   {
              ElementNotFound element=(ElementNotFound)array.get(i);
              String parentName=element.getParent().getName();
              if(element.getParent().getAttributeValue("name")!=null){
                  parentName=parentName+" "+element.getParent().getAttributeValue("name");
              }
              String childName=element.getChild().getName();
              if(element.getChild().getAttributeValue("name")!=null){
                  childName=childName+" "+element.getChild().getAttributeValue("name");
              }
                System.out.println("\n\n[**************]Parent "+parentName+"    Enfant "+element.getChild().getName()+" Not found");
          }
        }
    }
    
    /**
     * Getter for property array.
     * @return Value of property array.
     */
    public java.util.ArrayList getArray() {
        return array;
    }    
    
    /**
     * Setter for property array.
     * @param array New value of property array.
     */
    public void setArray(java.util.ArrayList array) {
        this.array = array;
    }    
    
}
