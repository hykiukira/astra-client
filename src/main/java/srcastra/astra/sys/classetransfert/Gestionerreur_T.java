/* * gestionerreur.java * * Created on 5 mars 2002, 15:22 */package srcastra.astra.sys.classetransfert;public class Gestionerreur_T implements  java.io.Serializable{    /** Creates new gestionerreur */    public Gestionerreur_T() {    }        /** Getter for property erreurcode.     * @return Value of property erreurcode.     */    public int getErreurcode() {        return erreurcode;    }    /** Setter for property erreurcode.     * @param erreurcode New value of property erreurcode.     */    public void setErreurcode(int erreurcode) {        this.erreurcode = erreurcode;    }        /** Getter for property erreurmessage.     * @return Value of property erreurmessage.     */    public java.lang.String getErreurmessage() {        return erreurmessage;    }        /** Setter for property erreurmessage.     * @param erreurmessage New value of property erreurmessage.     */    public void setErreurmessage(java.lang.String erreurmessage) {        this.erreurmessage = erreurmessage;    }        /** Getter for property tmpint.     * @return Value of property tmpint.     */    public int getTmpint() {        return tmpint;    }        /** Setter for property tmpint.     * @param tmpint New value of property tmpint.     */    public void setTmpint(int tmpint) {        this.tmpint = tmpint;    }        /** Getter for property tmpString.     * @return Value of property tmpString.     */    public java.lang.String getTmpString() {        return tmpString;    }        /** Setter for property tmpString.     * @param tmpString New value of property tmpString.     */    public void setTmpString(java.lang.String tmpString) {        this.tmpString = tmpString;    }        public void from (Gestionerreur_T source)    {        erreurmessage=source.erreurmessage;        erreurcode=source.erreurcode;        tmpint=source.tmpint;        tmpString=source.tmpString;    }        private int erreurcode;    private String erreurmessage;    private int tmpint;    private String tmpString;    }