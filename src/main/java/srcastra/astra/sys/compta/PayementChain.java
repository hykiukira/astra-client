/*

 * PayementChain.java

 *

 * Created on 7 février 2003, 9:14

 */



package srcastra.astra.sys.compta;

import srcastra.astra.sys.compta.command.*;

import srcastra.astra.sys.classetransfert.dossier.*;

/**

 *

 * @author  Thomas

 */

public class PayementChain extends  AbstractLigneCompta implements Chain{

    

    /** Creates a new instance of PayementChain */

     /** Creates a new instance of NoteCredit */

    private Chain nextChain;

    private final static String ACTION="pay";

    public PayementChain(Configuration config) {

         m_config=config;

    }

    public PayementChain() {

        

    }

    public void addChain(Chain nextChain) {

        this.nextChain=nextChain; 

    }

    

    public Chain getChain() {

        return nextChain;

    }

    

    public void sendToChain(String action, Object obj2)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{
      if(obj2 instanceof Payement_T){          
            Command cmd=new PayementCompta(obj2,getM_config()); 
            cmd.executeInsert(getM_config().getM_urcleunik());

        }else
            nextChain.sendToChain(action,obj2);
    } 
    public Object getConfig() {
        return m_config;

    }

    

}

