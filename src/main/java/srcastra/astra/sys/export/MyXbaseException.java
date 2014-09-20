/*
 * MyXbaseException.java
 *
 * Created on 19 octobre 2004, 00:03
 */

package srcastra.astra.sys.export;
import xBaseJ.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class MyXbaseException extends Exception{
    
    /** Creates a new instance of MyXbaseException */
    ArrayList array;
    xBaseJException parent;
    public MyXbaseException(ArrayList array) {
        this.array=array;
    }
    public String toString(){
        String tmp="";
        if(array!=null){
            for(int i=0;i<array.size();i++){
                tmp=tmp+(String)array.get(i);            
            }
        }else{
              tmp="Erreur array null";
        }
        return tmp;
    }
   
    /**
     * Getter for property parent.
     * @return Value of property parent.
     */
    public xBaseJ.xBaseJException getParent() {
        return parent;
    }    
    
    /**
     * Setter for property parent.
     * @param parent New value of property parent.
     */
    public void setParent(xBaseJ.xBaseJException parent) {
        this.parent = parent;
    }
    
}
