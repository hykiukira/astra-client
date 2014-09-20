/*
 * Task_T.java
 *
 * Created on 12 mars 2004, 10:23
 */

package srcastra.astra.sys.classetransfert;

/**
 *
 * @author  Administrateur
 */
public class Task_T implements java.io.Serializable{
    
    /** Creates a new instance of Task_T */
    public Task_T() {
    }
    
    /** Getter for property cscleunifact.
     * @return Value of property cscleunifact.
     *
     */
    public long getCscleunifact() {
        return cscleunifact;
    }
    
    /** Setter for property cscleunifact.
     * @param cscleunifact New value of property cscleunifact.
     *
     */
    public void setCscleunifact(long cscleunifact) {
        this.cscleunifact = cscleunifact;
    }
    
    /** Getter for property cscleunikcont.
     * @return Value of property cscleunikcont.
     *
     */
    public long getCscleunikcont() {
        return cscleunikcont;
    }
    
    /** Setter for property cscleunikcont.
     * @param cscleunikcont New value of property cscleunikcont.
     *
     */
    public void setCscleunikcont(long cscleunikcont) {
        this.cscleunikcont = cscleunikcont;
    }
    
    /** Getter for property dr_cleunik.
     * @return Value of property dr_cleunik.
     *
     */
    public long getDr_cleunik() {
        return dr_cleunik;
    }
    
    /** Setter for property dr_cleunik.
     * @param dr_cleunik New value of property dr_cleunik.
     *
     */
    public void setDr_cleunik(long dr_cleunik) {
        this.dr_cleunik = dr_cleunik;
    }
    
    /** Getter for property task_auto.
     * @return Value of property task_auto.
     *
     */
    public int getTask_auto() {
        return task_auto;
    }
    
    /** Setter for property task_auto.
     * @param task_auto New value of property task_auto.
     *
     */
    public void setTask_auto(int task_auto) {
        this.task_auto = task_auto;
    }
    
    /** Getter for property task_debut.
     * @return Value of property task_debut.
     *
     */
    public srcastra.astra.sys.classetransfert.utils.Date getTask_debut() {
        return task_debut;
    }
    
    /** Setter for property task_debut.
     * @param task_debut New value of property task_debut.
     *
     */
    public void setTask_debut(srcastra.astra.sys.classetransfert.utils.Date task_debut) {
        this.task_debut = task_debut;
    }
    
    /** Getter for property task_echeance.
     * @return Value of property task_echeance.
     *
     */
    public srcastra.astra.sys.classetransfert.utils.Date getTask_echeance() {
        return task_echeance;
    }
    
    /** Setter for property task_echeance.
     * @param task_echeance New value of property task_echeance.
     *
     */
    public void setTask_echeance(srcastra.astra.sys.classetransfert.utils.Date task_echeance) {
        this.task_echeance = task_echeance;
    }
    
    /** Getter for property task_envois.
     * @return Value of property task_envois.
     *
     */
    public int getTask_envois() {
        return task_envois;
    }
    
    /** Setter for property task_envois.
     * @param task_envois New value of property task_envois.
     *
     */
    public void setTask_envois(int task_envois) {
        this.task_envois = task_envois;
    }
    
    /** Getter for property task_etat.
     * @return Value of property task_etat.
     *
     */
    public int getTask_etat() {
        return task_etat;
    }
    
    /** Setter for property task_etat.
     * @param task_etat New value of property task_etat.
     *
     */
    public void setTask_etat(int task_etat) {
        this.task_etat = task_etat;
    }
    
    /** Getter for property task_id.
     * @return Value of property task_id.
     *
     */
    public long getTask_id() {
        return task_id;
    }
    
    /** Setter for property task_id.
     * @param task_id New value of property task_id.
     *
     */
    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }
    
    /** Getter for property task_memo.
     * @return Value of property task_memo.
     *
     */
    public java.lang.String getTask_memo() {
        return task_memo;
    }
    
    /** Setter for property task_memo.
     * @param task_memo New value of property task_memo.
     *
     */
    public void setTask_memo(java.lang.String task_memo) {
        this.task_memo = task_memo;
    }
    
    /** Getter for property task_message.
     * @return Value of property task_message.
     *
     */
    public java.lang.String getTask_message() {
        return task_message;
    }
    
    /** Setter for property task_message.
     * @param task_message New value of property task_message.
     *
     */
    public void setTask_message(java.lang.String task_message) {
        this.task_message = task_message;
    }
    
    /** Getter for property task_numdos.
     * @return Value of property task_numdos.
     *
     */
    public java.lang.String getTask_numdos() {
        return task_numdos;
    }
    
    /** Setter for property task_numdos.
     * @param task_numdos New value of property task_numdos.
     *
     */
    public void setTask_numdos(java.lang.String task_numdos) {
        this.task_numdos = task_numdos;
    }
    
    /** Getter for property task_object.
     * @return Value of property task_object.
     *
     */
    public java.lang.String getTask_object() {
        return task_object;
    }
    
    /** Setter for property task_object.
     * @param task_object New value of property task_object.
     *
     */
    public void setTask_object(java.lang.String task_object) {
        this.task_object = task_object;
    }
    
    /** Getter for property task_rappelle.
     * @return Value of property task_rappelle.
     *
     */
    public int getTask_rappelle() {
        return task_rappelle;
    }
    
    /** Setter for property task_rappelle.
     * @param task_rappelle New value of property task_rappelle.
     *
     */
    public void setTask_rappelle(int task_rappelle) {
        this.task_rappelle = task_rappelle;
    }
    
    /** Getter for property urcleunik.
     * @return Value of property urcleunik.
     *
     */
    public int getUrcleunik() {
        return urcleunik;
    }
    
    /** Setter for property urcleunik.
     * @param urcleunik New value of property urcleunik.
     *
     */
    public void setUrcleunik(int urcleunik) {
        this.urcleunik = urcleunik;
    }
    
    /** Getter for property annuler.
     * @return Value of property annuler.
     *
     */
    public int getAnnuler() {
        return annuler;
    }
    
    /** Setter for property annuler.
     * @param annuler New value of property annuler.
     *
     */
    public void setAnnuler(int annuler) {
        this.annuler = annuler;
    }
    
    /** Getter for property eeabrev.
     * @return Value of property eeabrev.
     *
     */
    public java.lang.String getEeabrev() {
        return eeabrev;
    }
    
    /** Setter for property eeabrev.
     * @param eeabrev New value of property eeabrev.
     *
     */
    public void setEeabrev(java.lang.String eeabrev) {
        this.eeabrev = eeabrev;
    }
    
    /** Getter for property cscontname.
     * @return Value of property cscontname.
     *
     */
    public java.lang.String getCscontname() {
        return cscontname;
    }
    
    /** Setter for property cscontname.
     * @param cscontname New value of property cscontname.
     *
     */
    public void setCscontname(java.lang.String cscontname) {
        this.cscontname = cscontname;
    }
    
    /** Getter for property csfactname.
     * @return Value of property csfactname.
     *
     */
    public java.lang.String getCsfactname() {
        return csfactname;
    }
    
    /** Setter for property csfactname.
     * @param csfactname New value of property csfactname.
     *
     */
    public void setCsfactname(java.lang.String csfactname) {
        this.csfactname = csfactname;
    }
    
    long task_id;
    long dr_cleunik;
    String task_numdos;
    long cscleunikcont;
    long cscleunifact;
    String task_object;
    srcastra.astra.sys.classetransfert.utils.Date  task_echeance;
    srcastra.astra.sys.classetransfert.utils.Date  task_debut;
    int task_etat;
    int task_rappelle;
    int task_auto;
    int task_envois;
    String task_memo;
    String task_message;
    int urcleunik;
    int annuler;
    String eeabrev;
    String cscontname;
    String csfactname;
    
    
}
