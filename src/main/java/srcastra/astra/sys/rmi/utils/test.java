/*
 * test.java
 *
 * Created on 9 décembre 2003, 18:37
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  Thomas
 */
public class test {
    
    /** Creates a new instance of test */
    public test() {
    }
    public void execute(int x,int y,int threadNumber){
        System.out.println("tread : "+threadNumber+ " x :"+x+" y "+y);
        int z=x+y;
        System.out.println("Result : "+z);
        
    }
    
}
