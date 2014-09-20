/*

 * BaseChain.java

 *

 * Created on 7 février 2003, 9:17

 */



package srcastra.astra.sys.compta;

import srcastra.astra.sys.compta.command.*;

import srcastra.astra.sys.classetransfert.dossier.*;

/**

 *

 * @author  Thomas

 */

public class FraisChaine extends AbstractLigneCompta implements Chain{

    

    /** Creates a new instance of BaseChain */

    private Chain nextChain;

    private final static String ACTION="base";

    public FraisChaine() {

    }

     public FraisChaine(Configuration config) {

         m_config=config;

    }

    public void addChain(Chain nextChain) {

        this.nextChain=nextChain; 

    }

    

    public Chain getChain() {

        return nextChain;

    }

    

    public void sendToChain(String action, Object obj2) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

        if(obj2 instanceof produit_T){

            Command cmd=new BaseCompta(obj2,getM_config()); 

            cmd.executeInsert(getM_config().getM_urcleunik()); 

        }else

            nextChain.sendToChain(action,obj2);

    }    

    

    public Object getConfig() {

        return m_config;

    }

    

}

