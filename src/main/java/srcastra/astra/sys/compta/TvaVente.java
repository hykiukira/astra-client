/*
 * TvaVente.java
 *
 * Created on 9 janvier 2004, 13:23
 */

package srcastra.astra.sys.compta;
import srcastra.astra.gui.components.tva.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.gui.modules.*;
/**
 *
 * @author  Thomas
 */
public class TvaVente {
    
    /** Creates a new instance of TvaVente */
   /* public TvaVente() {
    }*/
  public static void setTvaFrame(astrainterface serveur,Loginusers_T user,MainScreenModule parent){
    tva=new TvaFrame(null, true, serveur, user, 2, parent);
  }
   public static  TvaFrame getTvaFrame(){
        return tva;
  }
  public static  float getTvaPrct(int cleunik){
       Object[] tmpachat=(Object[])tva.getAchathash().get(new Integer(cleunik));
       float tvap=0.0f;
       try{
          tvap=Float.parseFloat(tmpachat[5].toString());  
          //Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[OOOOOOOOOOOOOO] valeur tva "+tva+"   Object "+tmpachat[5].toString());
       }
       catch(NumberFormatException nn){
           nn.printStackTrace();
       } 
       return tvap;
  }
static TvaFrame tva; 
}
