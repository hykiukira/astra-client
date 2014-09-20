/*
 * JournalCompta.java
 *
 * Created on 21 mai 2003, 15:01
 */

package srcastra.astra.sys.classetransfert.compta;

/**
 *
 * @author  thomas
 */
public class JournalCompta_T implements java.io.Serializable{
    
    /** Creates a new instance of JournalCompta */
  public JournalCompta_T() {
    }
  
  /** Getter for property jota_abrev.
   * @return Value of property jota_abrev.
   */
  public java.lang.String getJota_abrev() {
      return jota_abrev;
  }
  
  /** Setter for property jota_abrev.
   * @param jota_abrev New value of property jota_abrev.
   */
  public void setJota_abrev(java.lang.String jota_abrev) {
      this.jota_abrev = jota_abrev;
  }
  
  /** Getter for property jota_abrev2.
   * @return Value of property jota_abrev2.
   */
  public java.lang.String getJota_abrev2() {
      return jota_abrev2;
  }
  
  /** Setter for property jota_abrev2.
   * @param jota_abrev2 New value of property jota_abrev2.
   */
  public void setJota_abrev2(java.lang.String jota_abrev2) {
      this.jota_abrev2 = jota_abrev2;
  }
  
  /** Getter for property jota_beg.
   * @return Value of property jota_beg.
   */
  public srcastra.astra.sys.classetransfert.utils.Date getJota_beg() {
      return jota_beg;
  }
  
  /** Setter for property jota_beg.
   * @param jota_beg New value of property jota_beg.
   */
  public void setJota_beg(srcastra.astra.sys.classetransfert.utils.Date jota_beg) {  
      this.jota_beg = jota_beg;
  }
  
  /** Getter for property jota_cleunik.
   * @return Value of property jota_cleunik.
   */
  public int getJota_cleunik() {
      return jota_cleunik;
  }
  
  /** Setter for property jota_cleunik.
   * @param jota_cleunik New value of property jota_cleunik.
   */
  public void setJota_cleunik(int jota_cleunik) {
      this.jota_cleunik = jota_cleunik;
  }
  
  /** Getter for property jota_com.
   * @return Value of property jota_com.
   */
  public java.lang.String getJota_com() {
      return jota_com;
  }
  
  /** Setter for property jota_com.
   * @param jota_com New value of property jota_com.
   */
  public void setJota_com(java.lang.String jota_com) {
      this.jota_com = jota_com;
  } 
  
  /** Getter for property jota_end.
   * @return Value of property jota_end.
   */
  public srcastra.astra.sys.classetransfert.utils.Date getJota_end() {
      return jota_end;
  }
  
  /** Setter for property jota_end.
   * @param jota_end New value of property jota_end.
   */
  public void setJota_end(srcastra.astra.sys.classetransfert.utils.Date jota_end) {
      this.jota_end = jota_end;
  }
  
  /** Getter for property jota_ext.
   * @return Value of property jota_ext.
   */
  public String getJota_ext() {
      return jota_ext;
  }
  
  /** Setter for property jota_ext.
   * @param jota_ext New value of property jota_ext.
   */
  public void setJota_ext(String jota_ext) {
      this.jota_ext = jota_ext;
  }
  
  /** Getter for property jota_libelle.
   * @return Value of property jota_libelle.
   */
  public java.lang.String getJota_libelle() {
      return jota_libelle;
  }
  
  /** Setter for property jota_libelle.
   * @param jota_libelle New value of property jota_libelle.
   */
  public void setJota_libelle(java.lang.String jota_libelle) {
      this.jota_libelle = jota_libelle;
  }
  
  /** Getter for property jota_libelle2.
   * @return Value of property jota_libelle2.
   */
  public java.lang.String getJota_libelle2() {
      return jota_libelle2;
  }
  
  /** Setter for property jota_libelle2.
   * @param jota_libelle2 New value of property jota_libelle2.
   */
  public void setJota_libelle2(java.lang.String jota_libelle2) {
      this.jota_libelle2 = jota_libelle2;
  }
  
  /** Getter for property jota_lock.
   * @return Value of property jota_lock.
   */
  public int getJota_lock() {
      return jota_lock;
  }
  
  /** Setter for property jota_lock.
   * @param jota_lock New value of property jota_lock.
   */
  public void setJota_lock(int jota_lock) {
      this.jota_lock = jota_lock;
  }
  
  /** Getter for property numdoc.
   * @return Value of property numdoc.
   */
  public java.lang.String getNumdoc() {
      return numdoc;
  }
  
  /** Setter for property numdoc.
   * @param numdoc New value of property numdoc.
   */
  public void setNumdoc(java.lang.String numdoc) {
      this.numdoc = numdoc;
  }
  
  /** Getter for property exle_cleunik.
   * @return Value of property exle_cleunik.
   */
  public int getExle_cleunik() {
      return exle_cleunik;
  }
  
  /** Setter for property exle_cleunik.
   * @param exle_cleunik New value of property exle_cleunik.
   */
  public void setExle_cleunik(int exle_cleunik) {
      this.exle_cleunik = exle_cleunik;
  }
  
  /** Getter for property jota_categorie.
   * @return Value of property jota_categorie.
   */
  public int getJota_categorie() {
      return jota_categorie;
  }  
  /** Setter for property jota_categorie.
   * @param jota_categorie New value of property jota_categorie.
   */
  public void setJota_categorie(int jota_categorie) {
      this.jota_categorie = jota_categorie;
  } 
  /** Getter for property ce_cleunik.
   * @return Value of property ce_cleunik.
   */
  public int getCour_cleunik() {
      return cour_cleunik;
  }  
  /** Setter for property ce_cleunik.
   * @param ce_cleunik New value of property ce_cleunik.
   */
  public void setCour_cleunik(int cour_cleunik) {
      this.cour_cleunik = cour_cleunik;
  } 
  
  /** Getter for property urcleunik.
   * @return Value of property urcleunik.
   */
  public int getUrcleunik() {
      return urcleunik;
  }
  
  /** Setter for property urcleunik.
   * @param urcleunik New value of property urcleunik.
   */
  public void setUrcleunik(int urcleunik) {
      this.urcleunik = urcleunik;
  }
  
  /** Getter for property eecleunik.
   * @return Value of property eecleunik.
   */
  public int getEecleunik() {
      return eecleunik;
  }
  
  /** Setter for property eecleunik.
   * @param eecleunik New value of property eecleunik.
   */
  public void setEecleunik(int eecleunik) {
      this.eecleunik = eecleunik;
  }
  
   int jota_cleunik; 
   String jota_abrev;  
   String jota_libelle;
   String jota_abrev2 ;
   String jota_libelle2 ;
   String jota_ext  ;
   srcastra.astra.sys.classetransfert.utils.Date jota_beg ;
   srcastra.astra.sys.classetransfert.utils.Date jota_end ;
   int jota_lock ;
   String jota_com;
   String numdoc;
   int exle_cleunik;
   int jota_categorie;
   int urcleunik;
   int eecleunik;
   int cour_cleunik;    
}
