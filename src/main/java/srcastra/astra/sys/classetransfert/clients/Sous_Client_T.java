/*
 * Sous_Client_T.java
 *
 * Created on 12 juin 2002, 15:22
 */

package srcastra.astra.sys.classetransfert.clients;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class Sous_Client_T extends srcastra.astra.sys.classetransfert.Gestionerreur_T implements java.io.Serializable, srcastra.astra.sys.classetransfert.RmiObjectOperations {
    
    private int cscleunik;
    private java.sql.Date csdatetimecrea;
    private java.sql.Date csdatetimemodif;
    private String csmailprinc;
    private String csfax;
    private String cstelephonep;
    private String csnom;
    private String csreference;
    private String cslocalite;
    private String userModifName;
    private String userCreaName;
        
    /** Creates new Sous_Client_T */
    public Sous_Client_T() {
    }
    
    /** Creates new Sous_Client_T */
    public Sous_Client_T(int cscleunik,
                         java.sql.Date csdatetimecrea,
                         java.sql.Date csdatetimemodif,
                         String csmailprinc,
                         String csfax,
                         String cstelephonep,
                         String csnom,
                         String csreference,
                         String cslocalite) {
            
            this.cscleunik = cscleunik;
            this.csdatetimecrea = csdatetimecrea;
            this.csdatetimemodif = csdatetimemodif;
            this.csmailprinc = csmailprinc;
            this.csfax = csfax;
            this.cstelephonep = cstelephonep;
            this.csnom = csnom;
            this.csreference = csreference;
            this.cslocalite = cslocalite;
    }

    /** Getter for property cscleunik.
     * @return Value of property cscleunik.
     */
    public int getCscleunik() {
        return cscleunik;
    }
    
    /** Setter for property cscleunik.
     * @param cscleunik New value of property cscleunik.
     */
    public void setCscleunik(int cscleunik) {
        this.cscleunik = cscleunik;
    }
    
    /** Getter for property csdatetimecrea.
     * @return Value of property csdatetimecrea.
     */
    public java.sql.Date getCsdatetimecrea() {
        return csdatetimecrea;
    }
    
    /** Setter for property csdatetimecrea.
     * @param csdatetimecrea New value of property csdatetimecrea.
     */
    public void setCsdatetimecrea(java.sql.Date csdatetimecrea) {
        this.csdatetimecrea = csdatetimecrea;
    }
    
    /** Getter for property csdatetimemodif.
     * @return Value of property csdatetimemodif.
     */
    public java.sql.Date getCsdatetimemodif() {
        return csdatetimemodif;
    }
    
    /** Setter for property csdatetimemodif.
     * @param csdatetimemodif New value of property csdatetimemodif.
     */
    public void setCsdatetimemodif(java.sql.Date csdatetimemodif) {
        this.csdatetimemodif = csdatetimemodif;
    }
    
    /** Getter for property csfax.
     * @return Value of property csfax.
     */
    public java.lang.String getCsfax() {
        return csfax;
    }
    
    /** Setter for property csfax.
     * @param csfax New value of property csfax.
     */
    public void setCsfax(java.lang.String csfax) {
        this.csfax = csfax;
    }
    
    /** Getter for property csmailprinc.
     * @return Value of property csmailprinc.
     */
    public java.lang.String getCsmailprinc() {
        return csmailprinc;
    }
    
    /** Setter for property csmailprinc.
     * @param csmailprinc New value of property csmailprinc.
     */
    public void setCsmailprinc(java.lang.String csmailprinc) {
        this.csmailprinc = csmailprinc;
    }
    
    /** Getter for property csnom.
     * @return Value of property csnom.
     */
    public java.lang.String getCsnom() {
        return csnom;
    }
    
    /** Setter for property csnom.
     * @param csnom New value of property csnom.
     */
    public void setCsnom(java.lang.String csnom) {
        this.csnom = csnom;
    }
    
    /** Getter for property csreference.
     * @return Value of property csreference.
     */
    public java.lang.String getCsreference() {
        return csreference;
    }
    
    /** Setter for property csreference.
     * @param csreference New value of property csreference.
     */
    public void setCsreference(java.lang.String csreference) {
        this.csreference = csreference;
    }
    
    /** Getter for property cstelephonep.
     * @return Value of property cstelephonep.
     */
    public java.lang.String getCstelephonep() {
        return cstelephonep;
    }
    
    /** Setter for property cstelephonep.
     * @param cstelephonep New value of property cstelephonep.
     */
    public void setCstelephonep(java.lang.String cstelephonep) {
        this.cstelephonep = cstelephonep;
    }
    
    public static java.util.ArrayList getAllSous_Client_T(int cas) {
        return new java.util.ArrayList();
    }
    
    public int getObjectCleUnik() {
        return this.cscleunik;
    }
    
    public void setObjectCleUnik(int cleUnik) {
        this.cscleunik = cleUnik;
    }
    
    /** update an object to an ObjectOutputStream  */
    public void updateObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        // Serialver.updateObject(this, this.cscleunik, Serialver.CLIENT_SOUS);
    }
    
    /** Add an object to an ObjectOutputStream  */
    public void insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        // this.cscleunik = Serialver.insertObject(this, Serialver.CLIENT_SOUS);
    }
    
        
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return null;
    }
    
    /** get a object list from an ObjectInputStream  */
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int[] param, int cas) throws java.rmi.RemoteException, Exception,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{
        java.util.ArrayList newData;
        newData= serveur.renvClient(cas,param,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
        return newData;
    }
    
    /** delete an object to ObjectOutPutStream  */
    public void deleteObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        int[] param=new int[1];
        param[0]=this.getCscleunik();
        serveur.deleteClient(serveur.DELETE_CLIENT,currentUser.getUrcleunik(),param);
    }    
    
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        int[] tabInt=new int[1];
        tabInt[0]=cleUnik;
        Sous_Client_T tmp=(Sous_Client_T)serveur.ChargeClient(serveur.CHARGE_SOUS_CLIENT,tabInt,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
        return tmp;
    }
    
    /** Getter for property cslocalite.
     * @return Value of property cslocalite.
     */
    public java.lang.String getCslocalite() {
        return cslocalite;
    }
    
    /** Setter for property cslocalite.
     * @param cslocalite New value of property cslocalite.
     */
    public void setCslocalite(java.lang.String cslocalite) {
        this.cslocalite = cslocalite;
    }
    
    /** select an object for update from an ObjectInputStream  */
    public Object selectObjectForUpdate(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        return null;
    }
    
    /** Getter for property userCreaName.
     * @return Value of property userCreaName.
     */
    public java.lang.String getUserCreaName() {
        return userCreaName;
    }    
       
    /** Setter for property userCreaName.
     * @param userCreaName New value of property userCreaName.
     */
    public void setUserCreaName(java.lang.String userCreaName) {
        this.userCreaName = userCreaName;
    }
    
    /** Getter for property userModifName.
     * @return Value of property userModifName.
     */
    public java.lang.String getUserModifName() {
        return userModifName;
    }
    
    /** Setter for property userModifName.
     * @param userModifName New value of property userModifName.
     */
    public void setUserModifName(java.lang.String userModifName) {
        this.userModifName = userModifName;
    }
    
}