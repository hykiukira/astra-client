/*
 * Passenger.java
 *
 * Created on 13 septembre 2004, 21:20
 */

package srcastra.astra.sys.btn.model;

/**
 *
 * @author  Administrateur
 */
public class Passenger implements java.io.Serializable{
    
    /** Creates a new instance of Passenger */
    public Passenger() {
    }
    
    /**
     * Getter for property age.
     * @return Value of property age.
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Setter for property age.
     * @param age New value of property age.
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Getter for property firstName.
     * @return Value of property firstName.
     */
    public java.lang.String getFirstName() {
        return firstName;
    }
    
    /**
     * Setter for property firstName.
     * @param firstName New value of property firstName.
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public long getId() {
        return id;
    }
    
    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Getter for property id_parent.
     * @return Value of property id_parent.
     */
    public long getId_parent() {
        return id_parent;
    }
    
    /**
     * Setter for property id_parent.
     * @param id_parent New value of property id_parent.
     */
    public void setId_parent(long id_parent) {
        this.id_parent = id_parent;
    }
    
    /**
     * Getter for property sequences.
     * @return Value of property sequences.
     */
    public int getSequences() {
        return sequences;
    }
    
    /**
     * Setter for property sequences.
     * @param sequences New value of property sequences.
     */
    public void setSequences(int sequences) {
        this.sequences = sequences;
    }
    
    /**
     * Getter for property titelcode.
     * @return Value of property titelcode.
     */
    public int getTitelcode() {
        return titelcode;
    }
    
    /**
     * Setter for property titelcode.
     * @param titelcode New value of property titelcode.
     */
    public void setTitelcode(int titelcode) {
        this.titelcode = titelcode;
    }
    
    /**
     * Getter for property titelcodeString.
     * @return Value of property titelcodeString.
     */
    public java.lang.String getTitelcodeString() {
        return titelcodeString;
    }
    
    /**
     * Setter for property titelcodeString.
     * @param titelcodeString New value of property titelcodeString.
     */
    public void setTitelcodeString(java.lang.String titelcodeString) {
        this.titelcodeString = titelcodeString;
    }
    
    long id;
    long id_parent;
    int sequences;
    int titelcode;
    String titelcodeString;
    String firstName;
    int age;
    
    
}
