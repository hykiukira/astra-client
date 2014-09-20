/*
 * Service.java
 *
 * Created on 13 septembre 2004, 21:21
 */

package srcastra.astra.sys.btn.model;
import srcastra.astra.sys.classetransfert.utils.Date;
/**
 *
 * @author  Administrateur
 */
public class Service implements java.io.Serializable{
    
    /** Creates a new instance of Service */
    public Service() {
    }
    
    /**
     * Getter for property arriveHour.
     * @return Value of property arriveHour.
     */
    public java.lang.String getArriveHour() {
        return arriveHour;
    }
    
    /**
     * Setter for property arriveHour.
     * @param arriveHour New value of property arriveHour.
     */
    public void setArriveHour(java.lang.String arriveHour) {
        this.arriveHour = arriveHour;
    }
    
    /**
     * Getter for property arrivePlace.
     * @return Value of property arrivePlace.
     */
    public int getArrivePlace() {
        return arrivePlace;
    }
    
    /**
     * Setter for property arrivePlace.
     * @param arrivePlace New value of property arrivePlace.
     */
    public void setArrivePlace(int arrivePlace) {
        this.arrivePlace = arrivePlace;
    }
    
    /**
     * Getter for property boardCode.
     * @return Value of property boardCode.
     */
    public int getBoardCode() {
        return boardCode;
    }
    
    /**
     * Setter for property boardCode.
     * @param boardCode New value of property boardCode.
     */
    public void setBoardCode(int boardCode) {
        this.boardCode = boardCode;
    }
    
    /**
     * Getter for property carrier.
     * @return Value of property carrier.
     */
    public int getCarrier() {
        return carrier;
    }
    
    /**
     * Setter for property carrier.
     * @param carrier New value of property carrier.
     */
    public void setCarrier(int carrier) {
        this.carrier = carrier;
    }
    
    /**
     * Getter for property classe.
     * @return Value of property classe.
     */
    public java.lang.String getClasse() {
        return classe;
    }
    
    /**
     * Setter for property classe.
     * @param classe New value of property classe.
     */
    public void setClasse(java.lang.String classe) {
        this.classe = classe;
    }
    
    /**
     * Getter for property destination.
     * @return Value of property destination.
     */
    public int getDestination() {
        return destination;
    }
    
    /**
     * Setter for property destination.
     * @param destination New value of property destination.
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }
    
    /**
     * Getter for property endDate.
     * @return Value of property endDate.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getEndDate() {
        return endDate;
    }
    
    /**
     * Setter for property endDate.
     * @param endDate New value of property endDate.
     */
    public void setEndDate(srcastra.astra.sys.classetransfert.utils.Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Getter for property hotelName.
     * @return Value of property hotelName.
     */
    public java.lang.String getHotelName() {
        return hotelName;
    }
    
    /**
     * Setter for property hotelName.
     * @param hotelName New value of property hotelName.
     */
    public void setHotelName(java.lang.String hotelName) {
        this.hotelName = hotelName;
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
     * Getter for property ligne.
     * @return Value of property ligne.
     */
    public java.lang.String getLigne() {
        return ligne;
    }
    
    /**
     * Setter for property ligne.
     * @param ligne New value of property ligne.
     */
    public void setLigne(java.lang.String ligne) {
        this.ligne = ligne;
    }
    
    /**
     * Getter for property nombre.
     * @return Value of property nombre.
     */
    public int getNombre() {
        return nombre;
    }
    
    /**
     * Setter for property nombre.
     * @param nombre New value of property nombre.
     */
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter for property numberOfRooms.
     * @return Value of property numberOfRooms.
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    
    /**
     * Setter for property numberOfRooms.
     * @param numberOfRooms New value of property numberOfRooms.
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    
    /**
     * Getter for property order.
     * @return Value of property order.
     */
    public int getOrder() {
        return order;
    }
    
    /**
     * Setter for property order.
     * @param order New value of property order.
     */
    public void setOrder(int order) {
        this.order = order;
    }
    
    /**
     * Getter for property parent_id.
     * @return Value of property parent_id.
     */
    public long getParent_id() {
        return parent_id;
    }
    
    /**
     * Setter for property parent_id.
     * @param parent_id New value of property parent_id.
     */
    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }
    
    /**
     * Getter for property roomCode.
     * @return Value of property roomCode.
     */
    public java.lang.String getRoomCode() {
        return roomCode;
    }
    
    /**
     * Setter for property roomCode.
     * @param roomCode New value of property roomCode.
     */
    public void setRoomCode(java.lang.String roomCode) {
        this.roomCode = roomCode;
    }
    
    /**
     * Getter for property roomType.
     * @return Value of property roomType.
     */
    public java.lang.String getRoomType() {
        return roomType;
    }
    
    /**
     * Setter for property roomType.
     * @param roomType New value of property roomType.
     */
    public void setRoomType(java.lang.String roomType) {
        this.roomType = roomType;
    }
    
    /**
     * Getter for property sequence.
     * @return Value of property sequence.
     */
    public java.lang.String getSequence() {
        return sequence;
    }
    
    /**
     * Setter for property sequence.
     * @param sequence New value of property sequence.
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }
    
    /**
     * Getter for property serviceType.
     * @return Value of property serviceType.
     */
    public java.lang.String getServiceType() {
        return serviceType;
    }
    
    /**
     * Setter for property serviceType.
     * @param serviceType New value of property serviceType.
     */
    public void setServiceType(java.lang.String serviceType) {
        this.serviceType = serviceType;
    }
    
    /**
     * Getter for property serviceTypeString.
     * @return Value of property serviceTypeString.
     */
    public java.lang.String getServiceTypeString() {
        return serviceTypeString;
    }
    
    /**
     * Setter for property serviceTypeString.
     * @param serviceTypeString New value of property serviceTypeString.
     */
    public void setServiceTypeString(java.lang.String serviceTypeString) {
        this.serviceTypeString = serviceTypeString;
    }
    
    /**
     * Getter for property startDate.
     * @return Value of property startDate.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getStartDate() {
        return startDate;
    }
    
    /**
     * Setter for property startDate.
     * @param startDate New value of property startDate.
     */
    public void setStartDate(srcastra.astra.sys.classetransfert.utils.Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Getter for property startHour.
     * @return Value of property startHour.
     */
    public java.lang.String getStartHour() {
        return startHour;
    }
    
    /**
     * Setter for property startHour.
     * @param startHour New value of property startHour.
     */
    public void setStartHour(java.lang.String startHour) {
        this.startHour = startHour;
    }
    
    /**
     * Getter for property startPlace.
     * @return Value of property startPlace.
     */
    public int getStartPlace() {
        return startPlace;
    }
    
    /**
     * Setter for property startPlace.
     * @param startPlace New value of property startPlace.
     */
    public void setStartPlace(int startPlace) {
        this.startPlace = startPlace;
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
     * Getter for property statusString.
     * @return Value of property statusString.
     */
    public java.lang.String getStatusString() {
        return statusString;
    }
    
    /**
     * Setter for property statusString.
     * @param statusString New value of property statusString.
     */
    public void setStatusString(java.lang.String statusString) {
        this.statusString = statusString;
    }
    
    /**
     * Getter for property transportType.
     * @return Value of property transportType.
     */
    public int getTransportType() {
        return transportType;
    }
    
    /**
     * Setter for property transportType.
     * @param transportType New value of property transportType.
     */
    public void setTransportType(int transportType) {
        this.transportType = transportType;
    }
    
    /**
     * Getter for property transportTypeS.
     * @return Value of property transportTypeS.
     */
    public java.lang.String getTransportTypeS() {
        return transportTypeS;
    }
    
    /**
     * Setter for property transportTypeS.
     * @param transportTypeS New value of property transportTypeS.
     */
    public void setTransportTypeS(java.lang.String transportTypeS) {
        this.transportTypeS = transportTypeS;
    }
    
    /**
     * Getter for property destinationS.
     * @return Value of property destinationS.
     */
    public java.lang.String getDestinationS() {
        return destinationS;
    }
    
    /**
     * Setter for property destinationS.
     * @param destinationS New value of property destinationS.
     */
    public void setDestinationS(java.lang.String destinationS) {
        this.destinationS = destinationS;
    }
    
    /**
     * Getter for property boardCodeFr.
     * @return Value of property boardCodeFr.
     */
    public java.lang.String getBoardCodeFr() {
        return boardCodeFr;
    }
    
    /**
     * Setter for property boardCodeFr.
     * @param boardCodeFr New value of property boardCodeFr.
     */
    public void setBoardCodeFr(java.lang.String boardCodeFr) {
        this.boardCodeFr = boardCodeFr;
    }
    
    /**
     * Getter for property boardCodeNl.
     * @return Value of property boardCodeNl.
     */
    public java.lang.String getBoardCodeNl() {
        return boardCodeNl;
    }
    
    /**
     * Setter for property boardCodeNl.
     * @param boardCodeNl New value of property boardCodeNl.
     */
    public void setBoardCodeNl(java.lang.String boardCodeNl) {
        this.boardCodeNl = boardCodeNl;
    }
    
    long id;
    long parent_id;
    int order;
    String serviceTypeString;
    String serviceType;
    Date startDate;
    Date endDate;
    String statusString;
    String status;
    int destination;
    String destinationS;
    int transportType;
    String transportTypeS;
    int startPlace;
    int arrivePlace;
    String startHour;
    String arriveHour;
    String classe;
    int carrier;
    String sequence;
    String ligne;
    String roomCode;
    String hotelName;
    String roomType;
    int numberOfRooms;
    int boardCode;
    String boardCodeFr;
    String boardCodeNl;
    int nombre;// antaal stuk
    
}
