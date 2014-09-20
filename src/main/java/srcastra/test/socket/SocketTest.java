/*
 * SocketTest.java
 *
 * Created on 19 octobre 2004, 20:44
 */

package srcastra.test.socket;
import java.net.*;
import java.io.*;
/**
 *
 * @author  Administrateur
 */
public class SocketTest {
    
    /** Creates a new instance of SocketTest */
    public SocketTest() {
        try{
        Socket sock=new Socket("10.0.25.5",8000);
        System.out.println("ok connected");
        sock.close();
        }catch(UnknownHostException un){
            un.printStackTrace();
        }
        catch(IOException in){
            in.printStackTrace();
        }
    }
    public static void  main(String[] args){
        new SocketTest();
    }
    
}
