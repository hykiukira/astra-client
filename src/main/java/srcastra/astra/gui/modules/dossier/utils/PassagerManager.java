/*
 * PassagerManager.java
 *
 * Created on 5 février 2003, 8:54
 */

package srcastra.astra.gui.modules.dossier.utils;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;

/**
 *
 * @author  Thomas
 */
public class PassagerManager {
    
    /** Creates a new instance of PassagerManager */
    public PassagerManager() {
    }
    
    /** Getter for property isListCorrect.
     * @return Value of property isListCorrect.
     */
    public boolean isIsListCorrect() {
        return isListCorrect;
    }
    
    /** Setter for property isListCorrect.
     * @param isListCorrect New value of property isListCorrect.
     */
    public void setIsListCorrect(boolean isListCorrect) {
        this.isListCorrect = isListCorrect;
    }
    
    /** Getter for property isClientIn.
     * @return Value of property isClientIn.
     */
    public boolean isIsClientIn() {
        return isClientIn;
    }
    
    /** Setter for property isClientIn.
     * @param isClientIn New value of property isClientIn.
     */
    public void setIsClientIn(boolean isClientIn) {
        this.isClientIn = isClientIn;
    }
    
    /** Getter for property askForList.
     * @return Value of property askForList.
     */
    public boolean isAskForList() {
        return askForList;
    }
    
    /** Setter for property askForList.
     * @param askForList New value of property askForList.
     */
    public void setAskForList(boolean askForList) {
        this.askForList = askForList;
    }
    
    /** Getter for property askForClient.
     * @return Value of property askForClient.
     */
    public boolean isAskForClient() {
        return askForClient;
    }
    
    /** Setter for property askForClient.
     * @param askForClient New value of property askForClient.
     */
    public void setAskForClient(boolean askForClient) {
        this.askForClient = askForClient;
    }
    public Passager_T insertPassager(int client){
        Passager_T tmp=new Passager_T();
        if(askForClient && !isIsClientIn()){
            tmp.setCscleunik2(client); 
            isClientIn=true;
        }
        return tmp;            
    }
   
    boolean isListCorrect;
    boolean isClientIn;
    boolean askForList;
    boolean askForClient;
    
}
