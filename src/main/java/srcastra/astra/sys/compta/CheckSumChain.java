/*

 * BaseChain.java

 *

 * Created on 7 f�vrier 2003, 9:17

 */



package srcastra.astra.sys.compta;

import srcastra.astra.sys.compta.command.*;

import srcastra.astra.sys.classetransfert.dossier.*;

/**

 *

 * @author  Thomas

 */

public class CheckSumChain extends AbstractLigneCompta implements Chain{

    

    /** Creates a new instance of BaseChain */

    private Chain nextChain;

    private final static String ACTION="base";

    public CheckSumChain() {

    }

     public CheckSumChain(Configuration config) {

         m_config=config;

    }

    public void addChain(Chain nextChain) {

        this.nextChain=nextChain; 

    }

    

    public Chain getChain() {

        return nextChain;

    }

    

    public void sendToChain(String action, Object obj2)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

        if(obj2 instanceof CheskSumCompta){

            Command cmd=(Command)obj2;

            if(action.equals("insert"))

                cmd.executeInsert(getM_config().getM_urcleunik()); 

            else if(action.equals("modif"))

             cmd.executeModif(getM_config().getM_urcleunik(),0); 

        }else

            nextChain.sendToChain(action,obj2);

    }    

    

    public Object getConfig() {

        return m_config;

    }

    

}

