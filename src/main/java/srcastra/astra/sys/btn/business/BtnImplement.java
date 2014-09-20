/*
 * BtnImplement.java
 *
 * Created on 13 septembre 2004, 21:38
 */

package srcastra.astra.sys.btn.business;
import srcastra.astra.sys.btn.model.*;
import java.rmi.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.socketfactory.*;
import java.util.*;
import srcastra.astra.sys.rmi.utils.*;
import java.sql.*;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.*;
/**
 *
 * @author  Administrateur
 */
public class BtnImplement extends java.rmi.server.UnicastRemoteObject implements Btn {
    
    /** Creates a new instance of BtnImplement */
    astraimplement serveur;

    public BtnImplement(astraimplement serveur,int port)   throws RemoteException {  
        super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
        this.serveur=serveur;
    }
    public ArrayList getReservations(int entity, int urcleunik) throws RemoteException,ServeurSqlFailure{
        Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
        try{
        String requete="SELECT t.id,t.tocode,t.tobrochcode,t.boekingsnr,t.statusboek,t.taalklant,t.aantaldeelnemers,t.leadnaam,t.reservatiedatum,t.prijsboek,t.contact,t.changed,t.wijzigingsdatum,f.frnom1,fp.frgtitrecatalog FROM t_reservering t LEFT JOIN fournisseur f ON(t.tocode=f.frcleunik )  LEFT JOIN fournisseur_grproduit fp ON(t.tobrochcode=fp.frgtcleunik ) WHERE integrated=0 ";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        ArrayList array=null;
        while(result.next()){
            if(array==null){
                array=new ArrayList();
            }
            Reservering reservering=new Reservering();
            reservering.setId(result.getInt(1));
            reservering.setTocode(result.getInt(2));
            reservering.setTobrochure(result.getInt(3));
            reservering.setBoekingnumber(result.getString(4));
            reservering.setStatus(result.getString(5));
            reservering.setLanguage(result.getString(6));
            reservering.setPassengerNumber(new Integer(result.getString(7)).intValue());
            reservering.setClientName(result.getString(8));
            reservering.setTripDate(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9),true));
            reservering.setPrice(result.getString(10));
            reservering.setContact(result.getString(11));
            reservering.setChanged(result.getString(12));
            reservering.setTocodeS(result.getString(14));
            reservering.setTobrochureS(result.getString(15));
            ArrayList passenger=getPassenger(reservering.getId(), tmpool);
            ArrayList serviceTransport=getServiceTransport(reservering.getId(), tmpool);
            ArrayList service=getService(reservering.getId(), tmpool);
            if(passenger!=null)
                reservering.setPassenger(passenger);
            if(serviceTransport!=null)
                reservering.setServiceTransport(serviceTransport);
            if(service!=null)
                reservering.setService(service);
            array.add(reservering);
        }
        return array;
        }catch(SQLException sn){
            renvexception(sn, "nothing", tmpool.getConuser());
        }
        return null;
    }
     private ServeurSqlFailure renvexception(SQLException se,String message,Connection con)throws ServeurSqlFailure{
         System.out.println(message);
         se.printStackTrace();
         Transaction.rollback(con);
         ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
         sqe.setSqlException(se);
         sqe.setErrorcode(se.getErrorCode());
         throw sqe;       
   }
    private ArrayList getPassenger(long id_parent,Poolconnection tmpool) throws SQLException{
        String requete="SELECT r.id,r.id_parent,r.reizigervolgnr,r.titelcode,r.familienaam,r.leeftijd,t.tsintitule FROM t_reizigerinfo r LEFT JOIN traductiontitrepers t ON (r.titelcode=t.tscleunik AND lmcleunik=?) WHERE id_parent=?";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1,tmpool.getLmcleunik());
        pstmt.setLong(2,id_parent);
        ResultSet result=pstmt.executeQuery();
        ArrayList array=null;
        while(result.next()){
            if(array==null)
                array=new ArrayList();
            Passenger passenger=new Passenger();
            passenger.setId(result.getLong(1));
            passenger.setId_parent(result.getLong(2));
            passenger.setSequences(result.getInt(3));
            passenger.setTitelcode(result.getInt(4));
            passenger.setFirstName(result.getString(5));
            passenger.setAge(result.getInt(6));
            passenger.setTitelcodeString(result.getString(7));
            array.add(passenger);
        }
        return array;
    }
    private ArrayList getServiceTransport(long id_parent,Poolconnection tmpool) throws SQLException{
    /*long id;
    long parent_id;
    int order;
    String serviceTypeString;
    String serviceType;
    Date startDate;
    Date endDate;
    String statusString;
    String status;
    int destination;
    int transportType;
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
    String boardCode;
    int nombre;// antaal stuk*/
        //                    1         2           3       4               5               6           7               8               9               10              11          12              13          14          15          16      17          18          19      
    String requete="SELECT d.id,d.id_parent,d.dienstnr,d.soortdienstcode,d.begindatum,d.einddatum,d.statusdienst,d.richtingcode,tr.trtcleunik,d.vertrekplcode,d.aankomstplcode,d.uurvertrek,d.uuraankomst,d.trklassecd,d.carriercode,d.seqnr,d.lijnnr,t.tn_traduction,tt.trattraduction FROM traductiontransport tt, t_dienst d LEFT JOIN traduction_destination t ON (d.aankomstplcode =t.dn_cleunik AND t.lmcleunik=?) LEFT JOIN transport tr ON(d.typevervoercode=tr.btncode ) where d.soortdienstcode='T'  AND tr.trtcleunik=tt.trtcleunik and tt.lmcleunik=? and id_parent=?";
    PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, 0);
        pstmt.setInt(2, tmpool.getLmcleunik());
        pstmt.setLong(3,id_parent);
        ResultSet result=pstmt.executeQuery();
        ArrayList array=null;
        while(result.next()){
            if(array==null)
                array=new ArrayList();
            Service service=new Service();
            service.setId(result.getInt(1));
            service.setParent_id(result.getInt(2));
            service.setOrder(result.getInt(3));
            service.setServiceType(result.getString(4));
            service.setStartDate(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(5),true));
            service.setEndDate(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(6),true));
            service.setStatus(result.getString(7));
            service.setDestination(result.getInt(8));
            service.setTransportType(result.getInt(9));
            service.setStartPlace(result.getInt(10));
            service.setArrivePlace(result.getInt(11));
            service.setStartHour(result.getString(12));
            service.setArriveHour(result.getString(13));
            service.setClasse(result.getString(14));
            service.setSequence(result.getString(16));
            service.setLigne(result.getString(17));
            service.setDestinationS(result.getString(18));
            service.setTransportTypeS(result.getString(19));
            array.add(service);
            
        }
    return array;
    }
    private ArrayList getService(long id_parent,Poolconnection tmpool) throws SQLException{
        String requete="SELECT d.id,d.id_parent,d.dienstnr,d.soortdienstcode,d.begindatum,d.einddatum,d.statusdienst,d.accommodatiecode,d.accommodatienaam,d.kamertype,d.aantalkamers,d.extDienstcode,d.extDienstcat,d.aantstuks,d.kinderbed,b.tradfr,b.tradnl,b.id FROM t_dienst d  LEFT JOIN board b ON (d.boardcode=b.code) where soortdienstcode!='T' and id_parent=?";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        pstmt.setLong(1,id_parent);
        ResultSet result=pstmt.executeQuery();
        ArrayList array=null;
        while(result.next()){
            if(array==null)
                array=new ArrayList();
                Service service=new Service();
                service.setId(result.getInt(1));
                service.setParent_id(result.getInt(2));
                service.setOrder(result.getInt(3));
                service.setServiceType(result.getString(4));
                service.setStartDate(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(5),true));
                service.setEndDate(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(6),true));
                service.setStatus(result.getString(7));
                service.setRoomCode(result.getString(8));
                service.setHotelName(result.getString(9));
                service.setRoomType(result.getString(10));
                service.setNumberOfRooms(result.getInt(11));
                service.setBoardCodeFr(result.getString(16));
                service.setBoardCodeNl(result.getString(17));
                service.setBoardCode(result.getInt(18));
                array.add(service);
        }
        return array;
    }
}
