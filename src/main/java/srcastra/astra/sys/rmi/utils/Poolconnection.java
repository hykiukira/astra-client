/*















 * Poolconnection.java















 *















 * Created on 13 mars 2002, 11:26















 */


package srcastra.astra.sys.rmi.utils;


import srcastra.astra.sys.rmi.utils.ServeurBuffer;


import srcastra.astra.sys.droits.*;


import java.util.Hashtable;


/**
 * @author thom
 */


public class Poolconnection {


    static Hashtable buffers = new Hashtable();


    /**
     * Creates new Poolconnection
     */


    public Poolconnection(int urcleunik, java.sql.Connection conuser, int numeroentite, String username, int urright, String urnumerosession) {


        Object tmp;


        this.conuser = conuser;


        this.numeroentite = numeroentite;


        this.username = username;


        this.urcleunik = urcleunik;


        this.urright = urright;


        this.urnumerosession = urnumerosession;


        tmp = buffers.get(new Integer(numeroentite));


        if (tmp == null) {


            this.buffer = new HashServeurBuffer();


            buffers.put(new Integer(numeroentite), this.buffer);


        } else


            this.buffer = (HashServeurBuffer) tmp;


        this.clienthost = null;


    }


    public Poolconnection() {


    }


    /**
     * Getter for property conuser.
     *
     * @return Value of property conuser.
     */


    public java.sql.Connection getConuser() {


        return conuser;


    }


    /**
     * Getter for property numeroentite.
     *
     * @return Value of property numeroentite.
     */


    public int getNumeroentite() {


        return numeroentite;


    }


    public java.util.Locale getLangage() {


        java.util.Locale tmplocale;


        if (lmcleunik == 1)


            tmplocale = new java.util.Locale("fr", "");


        else if (lmcleunik == 2)


            tmplocale = new java.util.Locale("nl", "");


        else


            tmplocale = new java.util.Locale("fr", "");


        return tmplocale;


    }


    /**
     * Getter for property urcleunik.
     *
     * @return Value of property urcleunik.
     */


    public int getUrcleunik() {


        return urcleunik;


    }


    public void setUrcleunik(int urcleunik) {


        this.urcleunik = urcleunik;


    }


    /**
     * Getter for property urright.
     *
     * @return Value of property urright.
     */


    public int getUrright() {


        return urright;


    }


    public ServeurBuffer getBuffer() {


        return this.buffer;


    }


    public void setBuffer(HashServeurBuffer buffer) {


        this.buffer = buffer;


    }


    /**
     * Getter for property urnumerosession.
     *
     * @return Value of property urnumerosession.
     */


    public java.lang.String getUrnumerosession() {


        return urnumerosession;


    }


    /**
     * Getter for property lmcleunik.
     *
     * @return Value of property lmcleunik.
     */


    public int getLmcleunik() {


        return lmcleunik;


    }


    /**
     * Setter for property lmcleunik.
     *
     * @param lmcleunik New value of property lmcleunik.
     */


    public void setLmcleunik(int lmcleunik) {


        this.lmcleunik = lmcleunik;


    }


    /** Setter for property urnumerosession.















     * @param urnumerosession New value of property urnumerosession.















     */


    /** Setter for property urcleunik.















     * @param urcleunik New value of property urcleunik.















     */


    /**
     * Sets the client password for later comparaison
     *
     * @param pass New value for password
     */


    public void setPass(String pass) {


        userpass = pass;


    }


    /**
     * Checks the password is the one given as argument
     *
     * @param pass password to check
     */


    public boolean isPass(String pass) {


        return pass.equals(userpass);


    }


    /**
     * Set the clients host address.
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * Several clients can only log from one host. This is the purpose of this
     *
     * @param host the client host address
     */


    public void setHost(String host) {


        clienthost = host;


    }


    /**
     * Compare the given host with the saved one
     *
     * @param host The host to check
     */


    public boolean isHost(String host) {


        if (clienthost == null)


            return true;


        return clienthost.equals(host);


    }


    /**
     * Getter for property ursociete.
     *
     * @return Value of property ursociete.
     */


    public int getUrsociete() {


        return ursociete;


    }


    /**
     * Setter for property ursociete.
     *
     * @param ursociete New value of property ursociete.
     */


    public void setUrsociete(int ursociete) {


        this.ursociete = ursociete;


    }


    public Rights getDroits() {


        return droits;


    }


    public void setDroits(Rights droits) {


        this.droits = droits;


    }


    public synchronized void reconnectIfNeeded(UsersManager manager, boolean mayReconnect) throws java.rmi.RemoteException


    {


        if (mayReconnect)


            conuser = manager.reconnectIfNeeded(username, userpass, databaseName, databaseHost, databasePort, conuser);


        else if (!manager.checkConnection(conuser))


            throw new srcastra.astra.sys.rmi.Exception.AtomicOperationFailed("Lost \"lose not allowed\" connection (Probably during LOCK)");


    }


    /**
     * Getter for property urcle2.
     *
     * @return Value of property urcle2.
     */


    public int getUrcle2() {


        return urcle2;


    }


    /**
     * Setter for property urcle2.
     *
     * @param urcle2 New value of property urcle2.
     */


    public void setUrcle2(int urcle2) {


        this.urcle2 = urcle2;


    }


    /**
     * Getter for property userSock.
     *
     * @return Value of property userSock.
     */


    public java.net.Socket getUserSock() {


        return userSock;


    }


    /**
     * Setter for property userSock.
     *
     * @param userSock New value of property userSock.
     */


    public void setUserSock(java.net.Socket userSock) {


        this.userSock = userSock;


    }


    /**
     * Getter for property username.
     *
     * @return Value of property username.
     */

    public java.lang.String getUsername() {

        return username;

    }

    public java.lang.String getDatabaseLogin() {
        return this.databaseLogin;
    }

    public java.lang.String getDatabasePassword() {
        return this.databasePassword;
    }

    public void setDatabaseLogin(String login) {
        this.databaseLogin = login;
    }

    public void setDatabasePassword(String password) {
        this.databasePassword = password;
    }


    /**
     * Setter for property username.
     *
     * @param username New value of property username.
     */

    public void setUsername(java.lang.String username) {

        this.username = username;

    }


    /**
     * Getter for property databaseName.
     *
     * @return Value of property databaseName.
     */

    public java.lang.String getDatabaseName() {

        return databaseName;

    }


    /**
     * Setter for property databaseName.
     *
     * @param databaseName New value of property databaseName.
     */

    public void setDatabaseName(java.lang.String databaseName) {

        this.databaseName = databaseName;

    }


    private java.sql.Connection conuser;


    private int numeroentite;


    private String username;


    private String userpass;


    private String clienthost;


    private int urcleunik;


    private int lmcleunik;


    private String urnumerosession;


    private int urright;


    // private ServeurBuffer buffer;


    private int ursociete;


    private HashServeurBuffer buffer;


    private Rights droits;


    private String databasePassword;
    private String databaseLogin;


    private String databaseName;


    private String databaseHost;


    private int databasePort;


    private int urcle2;


    private java.net.Socket userSock;


}















