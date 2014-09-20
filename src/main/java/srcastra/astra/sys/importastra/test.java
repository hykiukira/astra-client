/*

 * test.java

 *

 * Created on 17 octobre 2003, 11:00

 */



package srcastra.astra.sys.importastra;

import srcastra.test.*;

import java.rmi.*;

import srcastra.astra.sys.rmi.Exception.*;

/**

 *

 * @author  Thomas

 */

public class test {

    

    /** Creates a new instance of test */

    ServeurConnect connect;
    public test() {
    /*    connect=new ServeurConnect();
        connect.connectServeur();

        try{

            connect.serveur2.importClient(connect.tmpgestion2.getUrcleunik());

        }catch(RemoteException rn){

            rn.printStackTrace();

            

        }/*catch(ServeurSqlFailure se){

          se.printStackTrace();    

        }*/

    }

    

    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) {

        new test();

    }

    

}

