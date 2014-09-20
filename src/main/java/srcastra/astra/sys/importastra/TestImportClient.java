/*
 * TestImportClient.java
 *
 * Created on 27 juin 2004, 17:06
 */

package srcastra.astra.sys.importastra;
import xBaseJ.*;
import srcastra.astra.sys.export.*;
import java.util.*;
import srcastra.astra.gui.sys.*;
import srcastra.test.*;
import java.rmi.*;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
/**
 *
 * @author  administrateur
 */
public class TestImportClient {
    
    /** Creates a new instance of TestImportClient */
      ServeurConnect connect;
    public TestImportClient(String file) {
        try{
        connect=new ServeurConnect(); 
        connect.connectServeur("ADMIN", "GgHh459");
        ArrayList array =readClientFile(file);
        connect.serveur2.importClient(connect.tmpgestion2.getUrcleunik(), array);
        }catch(ServeurSqlFailure sn){
            sn.printStackTrace();        
        }catch(RemoteException rn){
            rn.printStackTrace();
        }
        catch(EntityNotFoundException en){
            en.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestImportClient("C:\\Documents and Settings\\Administrateur\\Mes documents\\travail\\cliwhite.dbf");
        // TODO code application logic here
    }
    private ArrayList readClientFile(String filepath){
        DbfManager2 dbfmanager=new DbfManager2(true);
        try{
        System.out.println("lecture des clients");
        ArrayList array=dbfmanager.readClientFromFile(filepath,'r');     
        System.out.println("lecture effectuée");
        /*if(array!=null){
        for(int i=0;i<array.size();i++){
            Object[] tmp=(Object[] )array.get(i);
            if(tmp!=null){
                String affiche="";
                for(int j=0;j<tmp.length;j++){
                    if(tmp[j]!=null)
                        affiche=affiche+tmp[j].toString();
                }
            System.out.println("affiche "+affiche);
            }
            
        }
        }*/
         return array;
        }catch(xBaseJException xn){
         ErreurScreenLibrary.displayErreur(null , ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG,xn,connect.tmpgestion2);
        
        
        }catch(java.io.IOException in){
         ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG,in,connect.tmpgestion2);
        
        }
        return null;
       
    }
    
}
