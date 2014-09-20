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

public class AchatChain extends AbstractLigneCompta implements Chain{

    

    /** Creates a new instance of BaseChain */

    private Chain nextChain;

    private final static String ACTION="achat";

    public AchatChain() {

    }

     public AchatChain(Configuration config) {

         m_config=config;

    }

    public void addChain(Chain nextChain) {

        this.nextChain=nextChain; 

    }

    

    public Chain getChain() {

        return nextChain;

    }

    

    public void sendToChain(String action, Object obj2) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{

        if(obj2 instanceof AchatCompta){

            Command cmd=(Command)obj2;
            if(action.equals("insert")){
                cmd.executeInsert(getM_config().getM_urcleunik()); 
            }
            else if(action.equals("modify")){ 
              cmd.executeModif(getM_config().getM_urcleunik(),0);   
            }
        }else 
            if(nextChain!=null)
            nextChain.sendToChain(action,obj2);
    }    

    

    public Object getConfig() {

        return m_config;

    }

    

}

