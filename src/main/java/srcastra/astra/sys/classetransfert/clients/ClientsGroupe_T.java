/*
 * ClientsGroupe_T.java
 *
 * Created on 6 juin 2002, 11:37
 */

package srcastra.astra.sys.classetransfert.clients;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class ClientsGroupe_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable, srcastra.astra.sys.classetransfert.RmiObjectOperations {

    private int csgecleunik;
    private String csgenom;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
    private java.sql.Date csgedatetimecrea;
    private java.sql.Date csgedatetimemodif;
    private String urnomcrea;
    private String urnommodif;
    
    
    /** Creates new ClientsGroupe_T */
    public ClientsGroupe_T() {
    }

    /** Creates new ClientsGroupe_T */
    public ClientsGroupe_T(int csgecleunik,
                           String csgenom,
                           String snumerosessioncrea,
                           String snumerosessionmodif) {
           this.csgecleunik = csgecleunik;
           this.csgenom = csgenom;
           this.snumerosessioncrea = snumerosessioncrea;
           this.snumerosessionmodif = snumerosessionmodif;
    }
    
    /** Getter for property csgecleunik.
     * @return Value of property csgecleunik.
     */
    public int getCsgecleunik() {
        return csgecleunik;
    }
    
    /** Setter for property csgecleunik.
     * @param csgecleunik New value of property csgecleunik.
     */
    public void setCsgecleunik(int csgecleunik) {
        this.csgecleunik = csgecleunik;
    }
    
    /** Getter for property csgenom.
     * @return Value of property csgenom.
     */
    public java.lang.String getCsgenom() {
        return csgenom;
    }
    
    /** Setter for property csgenom.
     * @param csgenom New value of property csgenom.
     */
    public void setCsgenom(java.lang.String csgenom) {
        this.csgenom = csgenom;
    }
    
    /** Getter for property snumerosessioncrea.
     * @return Value of property snumerosessioncrea.
     */
    public java.lang.String getSnumerosessioncrea() {
        return snumerosessioncrea;
    }
    
    /** Setter for property snumerosessioncrea.
     * @param snumerosessioncrea New value of property snumerosessioncrea.
     */
    public void setSnumerosessioncrea(java.lang.String snumerosessioncrea) {
        this.snumerosessioncrea = snumerosessioncrea;
    }
    
    /** Getter for property snumerosessionmodif.
     * @return Value of property snumerosessionmodif.
     */
    public java.lang.String getSnumerosessionmodif() {
        return snumerosessionmodif;
    }
    
    /** Setter for property snumerosessionmodif.
     * @param snumerosessionmodif New value of property snumerosessionmodif.
     */
    public void setSnumerosessionmodif(java.lang.String snumerosessionmodif) {
        this.snumerosessionmodif = snumerosessionmodif;
    }
    
    public int getObjectCleUnik() {
        return csgecleunik;
    }
    
    public void setObjectCleUnik(int cleUnik) {
        this.csgecleunik = cleUnik;
    }
    
    /** select an object for update from an ObjectInputStream  */
    public Object selectObjectForUpdate(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        int[] param=new int[1];
        param[0]=cleUnik;
        return serveur.ChargeClient(serveur.CHARGE_GROUPEMENT_FOR_UPDATE,param,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
         // return Serialver.selectObject(cleUnik, Serialver.CLIENT_GROUPE);
    }
    
    /** delete an object to ObjectOutPutStream  */
    public void deleteObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        //Serialver.deleteObject(this.csgecleunik, Serialver.CLIENT_GROUPE);
    }
    
    /** Add an object to an ObjectOutputStream  */
    public void insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        int tmpint=serveur.insertClient(serveur.INSERT_GROUPEMENT,currentUser.getUrcleunik(),null,this);
        this.setCsgecleunik(tmpint);
    }
    
    /** get a object list from an ObjectInputStream  */
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return serveur.renvClient(serveur.RENV_GROUPEMENT,null,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
        /*java.util.ArrayList data = Serialver.selectObject(Serialver.CLIENT_GROUPE);
        java.util.ArrayList newData = new java.util.ArrayList(0);
        ClientsGroupe_T sct = null;
        for (int i=0; i < data.size(); i++) {
            sct = (ClientsGroupe_T)data.get(i);
            if (sct != null) {
                Object[] obj = new Object[2];
                obj[0] = new Integer(sct.getCsgecleunik());
                obj[1] = sct.getCsgenom();
                newData.add(obj);
            }
        }
        return newData;*/
    }
    
    /** update an object to an ObjectOutputStream  */
    public void updateObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
       serveur.ModifyClient(serveur.UPDATE_GROUPEMENT,currentUser.getUrcleunik(),null,this);
    }
    
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        return null;//Serialver.selectObject(cleUnik, Serialver.CLIENT_GROUPE);
    }
    
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return null;//Serialver.selectObject(this.csgecleunik, Serialver.CLIENT_GROUPE);
    }
    
    /** get a object list from an ObjectInputStream  */
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int[] param, int cas) throws java.rmi.RemoteException, Exception, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        return new java.util.ArrayList();
    }
    
    /** Getter for property csgedatetimecrea.
     * @return Value of property csgedatetimecrea.
     */
    public java.sql.Date getCsgedatetimecrea() {
        return csgedatetimecrea;
    }
    
    /** Setter for property csgedatetimecrea.
     * @param csgedatetimecrea New value of property csgedatetimecrea.
     */
    public void setCsgedatetimecrea(java.sql.Date csgedatetimecrea) {
        this.csgedatetimecrea = csgedatetimecrea;
    }
    
    /** Getter for property csgedatetimemodif.
     * @return Value of property csgedatetimemodif.
     */
    public java.sql.Date getCsgedatetimemodif() {
        return csgedatetimemodif;
    }
    
    /** Setter for property csgedatetimemodif.
     * @param csgedatetimemodif New value of property csgedatetimemodif.
     */
    public void setCsgedatetimemodif(java.sql.Date csgedatetimemodif) {
        this.csgedatetimemodif = csgedatetimemodif;
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
    
}
