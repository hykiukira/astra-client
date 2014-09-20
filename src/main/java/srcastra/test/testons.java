/*
 * testons.java
 *
 * Created on 8 août 2003, 16:04
 */

package srcastra.test;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
/**
 *
 * @author  thomas
 */
public class testons {
    
    /** Creates a new instance of testons */
    public testons() {
    Clients_T client1=new Clients_T();
    client1.setCodenom("cool man");
    Clients_T client2=client1;
    client1.setCodenom("cool man2000");
    client2.setCodenom("thomas");
    System.out.println("change client1  "+client1.getCodenom());
    }
  public static void main(String[] args){
    new testons();   
  }
}
