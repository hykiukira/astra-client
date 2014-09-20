/*
 * Reservering.java
 *
 * Created on 13 septembre 2004, 21:19
 */

package srcastra.astra.sys.btn.model;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.rmi.utils.*;
/**
 *
 * @author  Administrateur
 */
public class Reservering implements java.io.Serializable{
    
    /** Creates a new instance of Reservering */
   
    public Reservering() {
    }
    
    /**
     * Getter for property boekingnumber.
     * @return Value of property boekingnumber.
     */
    public java.lang.String getBoekingnumber() {
        return boekingnumber;
    }
    
    /**
     * Setter for property boekingnumber.
     * @param boekingnumber New value of property boekingnumber.
     */
    public void setBoekingnumber(java.lang.String boekingnumber) {
        this.boekingnumber = boekingnumber;
    }
    
    /**
     * Getter for property changed.
     * @return Value of property changed.
     */
    public java.lang.String getChanged() {
        return changed;
    }
    
    /**
     * Setter for property changed.
     * @param changed New value of property changed.
     */
    public void setChanged(java.lang.String changed) {
        this.changed = changed;
    }
    
    /**
     * Getter for property clientName.
     * @return Value of property clientName.
     */
    public java.lang.String getClientName() {
        return clientName;
    }
    
    /**
     * Setter for property clientName.
     * @param clientName New value of property clientName.
     */
    public void setClientName(java.lang.String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * Getter for property contact.
     * @return Value of property contact.
     */
    public java.lang.String getContact() {
        return contact;
    }
    
    /**
     * Setter for property contact.
     * @param contact New value of property contact.
     */
    public void setContact(java.lang.String contact) {
        this.contact = contact;
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
     * Getter for property language.
     * @return Value of property language.
     */
    public java.lang.String getLanguage() {
        return language;
    }
    
    /**
     * Setter for property language.
     * @param language New value of property language.
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }
    
    /**
     * Getter for property passenger.
     * @return Value of property passenger.
     */
    public java.util.ArrayList getPassenger() {
        return passenger;
    }
    
    /**
     * Setter for property passenger.
     * @param passenger New value of property passenger.
     */
    public void setPassenger(java.util.ArrayList passenger) {
        this.passenger = passenger;
    }
    
    /**
     * Getter for property passengerNumber.
     * @return Value of property passengerNumber.
     */
    public int getPassengerNumber() {
        return passengerNumber;
    }
    
    /**
     * Setter for property passengerNumber.
     * @param passengerNumber New value of property passengerNumber.
     */
    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }
    
    /**
     * Getter for property price.
     * @return Value of property price.
     */
    public srcastra.astra.sys.rmi.utils.MY_bigDecimal getPrice() {
        return price;
    }
    
    /**
     * Setter for property price.
     * @param prices New value of property price.
     */
    public void setPrice(String prices) {
        this.price =new  MY_bigDecimal(prices);
    }
    
    /**
     * Getter for property reservationDate.
     * @return Value of property reservationDate.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getReservationDate() {
        return reservationDate;
    }
    
    /**
     * Setter for property reservationDate.
     * @param reservationDate New value of property reservationDate.
     */
    public void setReservationDate(srcastra.astra.sys.classetransfert.utils.Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    
    /**
     * Getter for property status.
     * @return Value of property status.
     */
    public java.lang.String getStatus() {
        return status;
    }
    
    /**
     * Setter for property status.
     * @param status New value of property status.
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }
    
    /**
     * Getter for property tobrochure.
     * @return Value of property tobrochure.
     */
    public int getTobrochure() {
        return tobrochure;
    }
    
    /**
     * Setter for property tobrochure.
     * @param tobrochure New value of property tobrochure.
     */
    public void setTobrochure(int tobrochure) {
        this.tobrochure = tobrochure;
    }
    
    /**
     * Getter for property tocode.
     * @return Value of property tocode.
     */
    public int getTocode() {
        return tocode;
    }
    
    /**
     * Setter for property tocode.
     * @param tocode New value of property tocode.
     */
    public void setTocode(int tocode) {
        this.tocode = tocode;
    }
    
    /**
     * Getter for property tripDate.
     * @return Value of property tripDate.
     */
    public Date getTripDate() {
        return tripDate;
    }
    
    /**
     * Setter for property tripDate.
     * @param tripDate New value of property tripDate.
     */
    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }
    
    /**
     * Getter for property tobrochureS.
     * @return Value of property tobrochureS.
     */
    public java.lang.String getTobrochureS() {
        return tobrochureS;
    }
    
    /**
     * Setter for property tobrochureS.
     * @param tobrochureS New value of property tobrochureS.
     */
    public void setTobrochureS(java.lang.String tobrochureS) {
        this.tobrochureS = tobrochureS;
    }
    
    /**
     * Getter for property tocodeS.
     * @return Value of property tocodeS.
     */
    public java.lang.String getTocodeS() {
        return tocodeS;
    }
    
    /**
     * Setter for property tocodeS.
     * @param tocodeS New value of property tocodeS.
     */
    public void setTocodeS(java.lang.String tocodeS) {
        this.tocodeS = tocodeS;
    }
    
    /**
     * Getter for property service.
     * @return Value of property service.
     */
    public java.util.ArrayList getService() {
        return service;
    }
    
    /**
     * Setter for property service.
     * @param service New value of property service.
     */
    public void setService(java.util.ArrayList service) {
        this.service = service;
    }
    
    /**
     * Getter for property serviceTransport.
     * @return Value of property serviceTransport.
     */
    public java.util.ArrayList getServiceTransport() {
        return serviceTransport;
    }
    
    /**
     * Setter for property serviceTransport.
     * @param serviceTransport New value of property serviceTransport.
     */
    public void setServiceTransport(java.util.ArrayList serviceTransport) {
        this.serviceTransport = serviceTransport;
    }
    
    long id;
    int tocode;
    String tocodeS;    
    int tobrochure;
    String tobrochureS;
    String boekingnumber;
    String status;
    String language;
    int passengerNumber;
    String clientName;
    Date reservationDate;
    String contact;
    String changed;
    MY_bigDecimal price;
    Date tripDate;
    java.util.ArrayList passenger;
    java.util.ArrayList serviceTransport;
    java.util.ArrayList service;
}
