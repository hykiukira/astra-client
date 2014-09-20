/*
 * ClientsMultiAdresses_T.java
 *
 * Created on 6 juin 2002, 11:38
 */

package srcastra.astra.sys.classetransfert.clients;
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class ClientsMultiAdresses_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable, srcastra.astra.sys.classetransfert.RmiObjectOperations {

    private int csmscleunik;
    private int cscleunik;
    private String csmstype;
    private String csmsnom;
    private String csmsadresse;
    private int cxcleunik;
    private String csmslocalite;
    private int pyscleunik;
    private String csmstelephone;
    private String csmsfax;
    private String csmsgsm;
    private String csmsmail;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
    private String codenom;
    private String pysnom;
    private java.sql.Date csmsdatetimecrea;
    private java.sql.Date csmsdatetimodif;
    private String urnomcrea;
    private String urnommodif;
       
    
    /** Creates new ClientsMultiAdresses_T */
    public ClientsMultiAdresses_T() {
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
    
    /** Getter for property csmsadresse.
     * @return Value of property csmsadresse.
     */
    public java.lang.String getCsmsadresse() {
        return csmsadresse;
    }
    
    /** Setter for property csmsadresse.
     * @param csmsadresse New value of property csmsadresse.
     */
    public void setCsmsadresse(java.lang.String csmsadresse) {
        this.csmsadresse = csmsadresse;
    }
    
    /** Getter for property csmscleunik.
     * @return Value of property csmscleunik.
     */
    public int getCsmscleunik() {
        return csmscleunik;
    }
    
    /** Setter for property csmscleunik.
     * @param csmscleunik New value of property csmscleunik.
     */
    public void setCsmscleunik(int csmscleunik) {
        this.csmscleunik = csmscleunik;
    }
    
    /** Getter for property csmsfax.
     * @return Value of property csmsfax.
     */
    public java.lang.String getCsmsfax() {
        return csmsfax;
    }
    
    /** Setter for property csmsfax.
     * @param csmsfax New value of property csmsfax.
     */
    public void setCsmsfax(java.lang.String csmsfax) {
        this.csmsfax = csmsfax;
    }
    
    /** Getter for property csmsgsm.
     * @return Value of property csmsgsm.
     */
    public java.lang.String getCsmsgsm() {
        return csmsgsm;
    }
    
    /** Setter for property csmsgsm.
     * @param csmsgsm New value of property csmsgsm.
     */
    public void setCsmsgsm(java.lang.String csmsgsm) {
        this.csmsgsm = csmsgsm;
    }
    
    /** Getter for property csmsmail.
     * @return Value of property csmsmail.
     */
    public java.lang.String getCsmsmail() {
        return csmsmail;
    }
    
    /** Setter for property csmsmail.
     * @param csmsmail New value of property csmsmail.
     */
    public void setCsmsmail(java.lang.String csmsmail) {
        this.csmsmail = csmsmail;
    }
    
    /** Getter for property csmsnom.
     * @return Value of property csmsnom.
     */
    public java.lang.String getCsmsnom() {
        return csmsnom;
    }
    
    /** Setter for property csmsnom.
     * @param csmsnom New value of property csmsnom.
     */
    public void setCsmsnom(java.lang.String csmsnom) {
        this.csmsnom = csmsnom;
    }
    
    /** Getter for property csmstelephone.
     * @return Value of property csmstelephone.
     */
    public java.lang.String getCsmstelephone() {
        return csmstelephone;
    }
    
    /** Setter for property csmstelephone.
     * @param csmstelephone New value of property csmstelephone.
     */
    public void setCsmstelephone(java.lang.String csmstelephone) {
        this.csmstelephone = csmstelephone;
    }
    
    /** Getter for property csmstype.
     * @return Value of property csmstype.
     */
    public java.lang.String getCsmstype() {
        return csmstype;
    }
    
    /** Setter for property csmstype.
     * @param csmstype New value of property csmstype.
     */
    public void setCsmstype(java.lang.String csmstype) {
        this.csmstype = csmstype;
    }
    
    /** Getter for property cxcleunik.
     * @return Value of property cxcleunik.
     */
    public int getCxcleunik() {
        return cxcleunik;
    }
    
    /** Setter for property cxcleunik.
     * @param cxcleunik New value of property cxcleunik.
     */
    public void setCxcleunik(int cxcleunik) {
        this.cxcleunik = cxcleunik;
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
        return csmscleunik;
    }
    
    public void setObjectCleUnik(int cleUnik) {
        this.csmscleunik = cleUnik;
    }
    
    
    /** Getter for property pyscleunik.
     * @return Value of property pyscleunik.
     */
    public int getPyscleunik() {
        return pyscleunik;
    }
    
    /** Setter for property pyscleunik.
     * @param pyscleunik New value of property pyscleunik.
     */
    public void setPyscleunik(int pyscleunik) {
        this.pyscleunik = pyscleunik;
    }
    
    /** update an object to an ObjectOutputStream  */
    public void updateObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
      serveur.ModifyClient(serveur.UPDATE_CLIENT_ADR,currentUser.getUrcleunik(),null,this);
       // Serialver.updateObject(this, this.cscleunik, Serialver.CLIENT_MULTADRESSE);
    }
    
    /** Add an object to an ObjectOutputStream  */
    public void insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception,ServeurSqlFailure {
        serveur.insertClient(serveur.INSERT_CLIENT_MULT_ADR,currentUser.getUrcleunik(),null,this);
       // this.csmscleunik = Serialver.insertObject(this, Serialver.CLIENT_MULTADRESSE);
    }
     public int  insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser,int i) throws java.rmi.RemoteException, Exception,ServeurSqlFailure {
        return serveur.insertClient(serveur.INSERT_CLIENT_MULT_ADR,currentUser.getUrcleunik(),null,this);
       // this.csmscleunik = Serialver.insertObject(this, Serialver.CLIENT_MULTADRESSE);
    } 
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        return null;//return Serialver.selectObject(this.cscleunik, Serialver.CLIENT_MULTADRESSE);
    }
    
    /** get a object list from an ObjectInputStream  */
   /* public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception,ServeurSqlFailure {
       
        /*java.util.ArrayList data = Serialver.selectObject(Serialver.CLIENT_MULTADRESSE);
        java.util.ArrayList newData = new java.util.ArrayList(0);
        ClientsMultiAdresses_T sct = null;
        for (int i=0; i < data.size(); i++) {
            sct = (ClientsMultiAdresses_T)data.get(i);
            if (sct != null) {
                Object[] obj = new Object[4];
                obj[0] = new Integer(sct.getCsmscleunik());
                obj[1] = sct.getCsmstype();
                obj[2] = sct.getCsmsnom();
                obj[3] = sct.getCsmslocalite();
                newData.add(obj);
            }
        }
        return newData;
    }*/
    
    /** get a object list from an ObjectInputStream  */
  /*  public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, int extCleUnik) throws java.rmi.RemoteException, Exception {
        java.util.ArrayList data = Serialver.selectObject(Serialver.CLIENT_MULTADRESSE);
        java.util.ArrayList newData = new java.util.ArrayList(0);
        ClientsMultiAdresses_T sct = null;
        for (int i=0; i < data.size(); i++) {
            sct = (ClientsMultiAdresses_T)data.get(i);
            if (sct != null && sct.getCscleunik() == extCleUnik) {
                Object[] obj = new Object[4];
                obj[0] = new Integer(sct.getCsmscleunik());
                obj[1] = sct.getCsmstype();
                obj[2] = sct.getCsmsnom();
                obj[3] = sct.getCsmslocalite();
                newData.add(obj);
            }
        }
        return newData;
    }*/
    
    /** delete an object to ObjectOutPutStream  */
    public void deleteObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception {
        int[] param=new int[1];
        param[0]=this.getCsmscleunik();
        serveur.deleteClient(serveur.DELETE_CLIENT_MULTI_AD,currentUser.getUrcleunik(),param);//Serialver.deleteObject(this.cscleunik, Serialver.CLIENT_MULTADRESSE);
    }
    
    /** select an object from an ObjectInputStream  */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception {
        int[] tabint=new int[1];
        tabint[0]=cleUnik;
        return serveur.ChargeClient(serveur.CHARGE_CLIENT_MULT_ADR,tabint,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
    }    
    
    /** Getter for property csmslocalite.
     * @return Value of property csmslocalite.
     */
    public java.lang.String getCsmslocalite() {
        return csmslocalite;
    }
    
    /** Setter for property csmslocalite.
     * @param csmslocalite New value of property csmslocalite.
     */
    public void setCsmslocalite(java.lang.String csmslocalite) {
        this.csmslocalite = csmslocalite;
    }
    
    /** select an object for update from an ObjectInputStream  */
    public Object selectObjectForUpdate(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception,ServeurSqlFailure {
        int[] tabint=new int[1];
        tabint[0]=cleUnik;
        return serveur.ChargeClient(serveur.CHARGE_CLIENT_MULT_ADR_FOR_UPDATE,tabint,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
    }
    
    /** get a object list from an ObjectInputStream  */
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int[] param, int cas) throws java.rmi.RemoteException, Exception, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure {
        return serveur.renvClient(cas,param,currentUser.getUrcleunik(),currentUser.getUrlmcleunik());
    }    
     
    /** Getter for property codenom.
     * @return Value of property codenom.
     */
    public java.lang.String getCodenom() {
        return codenom;
    }
    
    /** Setter for property codenom.
     * @param codenom New value of property codenom.
     */
    public void setCodenom(java.lang.String codenom) {
        this.codenom = codenom;
    }
    
    /** Getter for property pysnom.
     * @return Value of property pysnom.
     */
    public java.lang.String getPysnom() {
        return pysnom;
    }
    
    /** Setter for property pysnom.
     * @param pysnom New value of property pysnom.
     */
    public void setPysnom(java.lang.String pysnom) {
        this.pysnom = pysnom;
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
    
    /** Getter for property csmsdatetimecrea.
     * @return Value of property csmsdatetimecrea.
     */
    public java.sql.Date getCsmsdatetimecrea() {
        return csmsdatetimecrea;
    }
    
    /** Setter for property csmsdatetimecrea.
     * @param csmsdatetimecrea New value of property csmsdatetimecrea.
     */
    public void setCsmsdatetimecrea(java.sql.Date csmsdatetimecrea) {
        this.csmsdatetimecrea = csmsdatetimecrea;
    }
    
    /** Getter for property csmsdatetimodif.
     * @return Value of property csmsdatetimodif.
     */
    public java.sql.Date getCsmsdatetimodif() {
        return csmsdatetimodif;
    }
    
    /** Setter for property csmsdatetimodif.
     * @param csmsdatetimodif New value of property csmsdatetimodif.
     */
    public void setCsmsdatetimodif(java.sql.Date csmsdatetimodif) {
        this.csmsdatetimodif = csmsdatetimodif;
    }
    
    /** Creates new ClientsMultiAdresses_T */
    public ClientsMultiAdresses_T(int csmscleunik,
                                  int cscleunik,
                                  String csmstype,
                                  String csmsnom,
                                  String csmsadresse,
                                  int cxcleunik,
                                  String csmslocalite,
                                  int pyscleunik,
                                  String csmstelephone,
                                  String csmsfax,
                                  String csmsgsm,
                                  String csmsmail,
                                  String snumerosessioncrea,
                                  String snumerosessionmodif) {
                                      
             this.csmscleunik = csmscleunik;
             this.cscleunik = cscleunik;
             this.csmstype = csmstype;
             this.csmsnom = csmsnom;
             this.csmsadresse = csmsadresse;
             this.cxcleunik = cxcleunik;
             this.csmslocalite = csmslocalite;
             this.pyscleunik = pyscleunik;
             this.csmstelephone = csmstelephone;
             this.csmsfax = csmsfax;
             this.csmsgsm = csmsgsm;
             this.csmsmail = csmsmail;
             this.snumerosessioncrea = snumerosessioncrea;
             this.snumerosessionmodif = snumerosessionmodif;    
    }
}
