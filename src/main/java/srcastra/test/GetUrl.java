/*
 * GetUrl.java
 *
 * Created on 22 juin 2004, 10:46
 */

package srcastra.test;
import java.net.*;
/**
 *
 * @author  Administrateur
 */
public class GetUrl {
    
    /** Creates a new instance of GetUrl */
    public GetUrl() {
        try{
        URL  adress=new URL("http://astramyhome.no-ip.info");
         System.out.println("adress "+adress.getHost());
        // adress.get
         InetAddress adres=InetAddress.getByName("perforinco.no-ip.info");
         System.out.println("adresse"+adres.getHostAddress());
          InetAddress adres2=InetAddress.getByName("astramyhome.no-ip.info");
         System.out.println("adresse"+adres2.getHostAddress());
         InetAddress localaddr = InetAddress.getLocalHost();
         System.out.println( "main Local IP Address : " + localaddr.getHostAddress() );
         System.out.println( "main Local hostname   : " + localaddr.getHostName() );
         System.out.println();

         InetAddress[] localaddrs = InetAddress.getAllByName ( "localhost" );
         for ( int i=0; i<localaddrs.length; i++ )
            {
            if ( ! localaddrs[i].equals( localaddr ) )
               {
               System.out.println( "alt  Local IP Address : " + localaddrs[i].getHostAddress() );
               System.out.println( "alt  Local hostname   : " + localaddrs[i].getHostName() );
               System.out.println();
               }
            }
       
       
        }catch(MalformedURLException mn){
            mn.printStackTrace();
        
        }catch(java.net.UnknownHostException un){
            un.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GetUrl();
        // TODO code application logic here
    }
    
}
