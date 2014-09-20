/* * Document_T.java * * Created on 27 mars 2002, 15:28 */
package srcastra.astra.sys.classetransfert.clients;


/** * * @author  thomas * @version  */
public class ClientsMemo_T extends srcastra.astra.sys.classetransfert.Gestionerreur_T implements java.io.Serializable, srcastra.astra.sys.classetransfert.RmiObjectOperations {
    public ClientsMemo_T() {    }   
    public ClientsMemo_T(int cscleunik,String memo,java.sql.Date csdatetimecrea,java.sql.Date csdatetimemodif,String snumerosessioncrea,String snumerosessionmodif ) {                
        this.cscleunik = cscleunik;        
        this.memo = memo;        
        this.csdatetimecrea = csdatetimecrea;        
        this.csdatetimemodif = csdatetimemodif;        
        this.snumerosessioncrea = snumerosessioncrea;        
        this.snumerosessionmodif = snumerosessionmodif;    
    }    /** select an object for update from an ObjectInputStream  */    
    public Object selectObjectForUpdate(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        int[] param=new int[1];
        param[0]=cleUnik;
        ClientsMemo_T clientMemo=(ClientsMemo_T)serveur.ChargeClient(serveur.CHARGE_CLIENT_MEMO_FOR_UPDATE,param,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
        return clientMemo;  
    }            /** delete an object to ObjectOutPutStream  */   
    public void deleteObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {    }
    /** Add an object to an ObjectOutputStream  */    
    public void insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {    }
    /** get a object list from an ObjectInputStream  */    
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return new java.util.ArrayList(0);    
    }        
    public int getObjectCleUnik() {        
        return this.cscleunik;    
    }        /** update an object to an ObjectOutputStream  */   
    public void updateObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        serveur.ModifyClient(serveur.UPDATE_CLIENT_MEMO,currentUser.getUrcleunik(),null,this);
        //Clients_T clt = (Clients_T) new Clients_T().selectObjectForUpdate(serveur, null, this.cscleunik);
        //clt.setCscleunik(this.cscleunik);
        //clt.setCsmemo(this.memo);
        //clt.updateObject(serveur, null);    
    }        
    public void setObjectCleUnik(int cleUnik) {       
        this.cscleunik = cleUnik;  
    }        /** select an object from an ObjectInputStream  */    
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception { 
        int[] param=new int[1];
        param[0]=cleUnik;
        ClientsMemo_T clientMemo=(ClientsMemo_T)serveur.ChargeClient(serveur.CHARGE_CLIENT_MEMO,param,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
        return clientMemo;
    }        /** select an object from an ObjectInputStream  */   
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return new Object();   
    }        /** Getter for property cscleunik.     * @return Value of property cscleunik.     */   
    public int getCscleunik() {        
        return cscleunik;   
    }        /** Setter for property cscleunik.     * @param cscleunik New value of property cscleunik.     */    
    public void setCscleunik(int cscleunik) {       
        this.cscleunik = cscleunik;   
    }        /** Getter for property csdatetimecrea.     * @return Value of property csdatetimecrea.     */   
    public java.sql.Date getCsdatetimecrea() {       
        return csdatetimecrea; 
    }        /** Setter for property csdatetimecrea.     * @param csdatetimecrea New value of property csdatetimecrea.     */   
    public void setCsdatetimecrea(java.sql.Date csdatetimecrea) {       
        this.csdatetimecrea = csdatetimecrea;   
    }        /** Getter for property csdatetimemodif.     * @return Value of property csdatetimemodif.     */   
    public java.sql.Date getCsdatetimemodif() {        
        return csdatetimemodif;  
    }        /** Setter for property csdatetimemodif.     * @param csdatetimemodif New value of property csdatetimemodif.     */   
    public void setCsdatetimemodif(java.sql.Date csdatetimemodif) {  
        this.csdatetimemodif = csdatetimemodif;   
    }        /** Getter for property memo.     * @return Value of property memo.     */   
    public java.lang.String getMemo() {
        return memo;    
    }        /** Setter for property memo.     * @param memo New value of property memo.     */   
    public void setMemo(java.lang.String memo) {     
        this.memo = memo; 
    }        /** Getter for property snumerosessioncrea.     * @return Value of property snumerosessioncrea.     */   
    public java.lang.String getSnumerosessioncrea() { 
        return snumerosessioncrea;   
    }        /** Setter for property snumerosessioncrea.     * @param snumerosessioncrea New value of property snumerosessioncrea.     */ 
    public void setSnumerosessioncrea(java.lang.String snumerosessioncrea) {       
        this.snumerosessioncrea = snumerosessioncrea;   
    }        /** Getter for property snumerosessionmodif.     * @return Value of property snumerosessionmodif.     */   
    public java.lang.String getSnumerosessionmodif() {       
        return snumerosessionmodif;   
    }        /** Setter for property snumerosessionmodif.     * @param snumerosessionmodif New value of property snumerosessionmodif.     
     */  
    public void setSnumerosessionmodif(java.lang.String snumerosessionmodif) {
        this.snumerosessionmodif = snumerosessionmodif;   
    }        /** get a object list from an ObjectInputStream  */   
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int[] param, int cas) throws java.rmi.RemoteException, Exception, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure { 
        return new java.util.ArrayList();   
    }      
    
    /** Getter for property urnomcrea.
     * @return Value of property urnomcrea.
     */
    public java.lang.String getUrnomcrea() {
        return urnomcrea;
    }
    
    /** Setter for property urnomcrea.
     * @param urnomcrea New value of property urnomcrea.
     */
    public void setUrnomcrea(java.lang.String urnomcrea) {
        this.urnomcrea = urnomcrea;
    }
    
    /** Getter for property urnommodif.
     * @return Value of property urnommodif.
     */
    public java.lang.String getUrnommodif() {
        return urnommodif;
    }
    
    /** Setter for property urnommodif.
     * @param urnommodif New value of property urnommodif.
     */
    public void setUrnommodif(java.lang.String urnommodif) {
        this.urnommodif = urnommodif;
    }
    
    private int cscleunik;   
    private String memo;  
    private java.sql.Date csdatetimecrea;  
    private java.sql.Date csdatetimemodif;  
    private String snumerosessioncrea;   
    private String snumerosessionmodif;
    private String urnomcrea;
    private String urnommodif;
}
