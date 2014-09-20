/*
* UsersManager.java
*
* Created on 19 août 2002, 8:56
*/
package srcastra.astra.sys.rmi.utils;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.sql.*;

import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import srcastra.astra.sys.rmi.Exception.*;

import java.rmi.server.ServerNotActiveException;

import srcastra.astra.sys.Transaction;

/**
 * Handle a connection to a central database containing the list of users.
 * Used to get individual connections for different users. Manage session keys too.
 *
 * @author David
 */
public class UsersManager {

    private String database;
    private String host;
    private int port;
    private String username;
    private String pass;
    private String jdbcName;
    private String jdbcDriverClassName;
    private Loginusers_T[] mainUsersList = null;
    private Connection dbCon = null;
    private ManagePoolconnection managepool;
    private static int GET_USERS = 0;
    private static int GET_USERS_SOCNUMBER = 1;
    private static int GET_USERS_SOCNOM = 2;
    private static int GET_USER_DATA = 3;
    private String[] requeteSQL = {
        "SELECT u.urcleunik as urcleunik,u.urlogin as login,u.uridlogo as logo,"
            + "u.lmcleunik as lmcleunik,l.lmabrev as lmabrev, s.soccleunik as soccleunik, "
            + "s.databasename as databasename, s.databaseserver as databaseserver, "
            + "s.databaseport as databaseport, s.nom as societenom "
            + "from user u,languesystem l, societe s"
            + " where u.lmcleunik=l.lmcleunik"
            + " and s.soccleunik=u.soccleunik",
        "SELECT u.urcleunik as urcleunik,u.urlogin as login,u.uridlogo as logo,"
            + "u.lmcleunik as lmcleunik,l.lmabrev as lmabrev, s.soccleunik as soccleunik, "
            + "s.databasename as databasename, s.databaseserver as databaseserver, "
            + "s.databaseport as databaseport, s.nom as societenom "
            + "from user u,languesystem l, societe s"
            + " where u.lmcleunik=l.lmcleunik and s.soccleunik=?"
            + " and s.soccleunik=u.soccleunik",
        "SELECT u.urcleunik as urcleunik,u.urlogin as login,u.uridlogo as logo,"
            + "u.lmcleunik as lmcleunik,l.lmabrev as lmabrev, s.soccleunik as soccleunik, "
            + "s.databasename as databasename, s.databaseserver as databaseserver, "
            + "s.databaseport as databaseport, s.nom as societenom"
            + "from user u,languesystem l, societe s "
            + "where u.lmcleunik=l.lmcleunik and s.nom=? "
            + "and s.soccleunik=u.soccleunik",
        "SELECT u.eecleunik, u.urnom, u.urprenom, u.droits,u.urdroit,u.urmailbur,e.eesmtp,u.ursignature "
            + "FROM user u,entite e "
            + "WHERE u.eecleunik=e.eecleunik AND urcleunik =?"};

    /**
     * Creates a new instance of UsersManager
     */
    public UsersManager(ManagePoolconnection managepool) {
        this.managepool = managepool;
    }

    public UsersManager() {

    }

    public void setPassword(String pass) {
        this.pass = pass;
        dbCon = null;
    }

    public void setUser(String user) {
        this.username = user;
        dbCon = null;
    }

    public void setDatabase(String database, String host, int port) {
        this.database = database;
        this.host = host;
        this.port = port;
        dbCon = null;
    }

    public void setDriver(String driverClassName, String jdbcName) {
        this.jdbcDriverClassName = driverClassName;
        this.jdbcName = jdbcName;
        dbCon = null;
    }

    synchronized public Loginusers_T[] getUserList() {
        if ((dbCon == null) || !checkConnection(dbCon))
            dbCon = connectDb(this.username, this.pass, this.database, this.host, this.port);
//dbCon=connectDb("THOM", "vidaloca","AstraI, this.host, this.port);
//if (mainUsersList!=null)
//  return mainUsersList;
        Loginusers_T[] loguser_tab = null;
        ResultSet record;
        int i;
        try {
            Statement select = dbCon.createStatement();
            record = select.executeQuery(requeteSQL[GET_USERS]);
            record.last();
            i = record.getRow();
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "nbr lignes " + i);
            if (i == 0) {
                loguser_tab = new Loginusers_T[1];
                loguser_tab[0] = new Loginusers_T();
                loguser_tab[0].setUrlogin("SORRY,NO USER IN DB.");
            } else {
                loguser_tab = new Loginusers_T[i];
// Loginusers_T[] loguser_tab=new  Loginusers_T[1];
                record.beforeFirst();
                Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "num2" + record.getRow());
                for (int j = 0; record.next(); j++) {
                    loguser_tab[j] = new Loginusers_T();
//Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"j vaut "+j);
                    loguser_tab[j].setUrcleunik(record.getInt("urcleunik"));
                    loguser_tab[j].setCle2(record.getInt("urcleunik"));
//  loguser_tab[j].set(record.getInt("urcleunik"));
                    loguser_tab[j].setUrlogin(record.getString("login"));
                    loguser_tab[j].setUridlogo(record.getInt("logo"));
                    loguser_tab[j].setUrlmcleunik(record.getInt("lmcleunik"));
                    loguser_tab[j].setUrlmabrev(record.getString("lmabrev"));
                    loguser_tab[j].setDatabase(record.getString("databaseserver"), record.getString("databasename"), record.getInt("databaseport"));
                    loguser_tab[j].setSociete(record.getInt("soccleunik"));
// loguser_tab[j].setUrrights(record.getInt("udroits"));
                    Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "element " + loguser_tab[j].getUrlogin());
                }

            }
        } catch (SQLException e) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, e);
        } catch (Exception e1) {
            Logger.getDefaultLogger().printStackTrace(Logger.LOG_EMERGENCY, e1);
        }
        mainUsersList = loguser_tab;
        return loguser_tab;
        /**Code a mettre*/
    }

    public Loginusers_T[] getUserList(String societe) {
        if ((dbCon == null) || !checkConnection(dbCon))
            dbCon = connectDb(this.username, this.pass, this.database, this.host, this.port);
        int i;
        Loginusers_T[] loguser_tab = null;
        ResultSet record;
        try {
            PreparedStatement select = dbCon.prepareStatement(requeteSQL[GET_USERS_SOCNOM]);
            select.setString(1, societe);
            record = select.executeQuery();
            record.last();
            i = record.getRow();
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "nbr lignes " + i);
            if (i == 0) {
                loguser_tab = new Loginusers_T[1];
                loguser_tab[0] = new Loginusers_T();
                loguser_tab[0].setUrlogin("SORRY,NO USER IN DB.");
            } else {
                loguser_tab = new Loginusers_T[i];
// Loginusers_T[] loguser_tab=new  Loginusers_T[1];
                record.beforeFirst();
                Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "num2" + record.getRow());
                for (int j = 0; record.next(); j++) {
                    loguser_tab[j] = new Loginusers_T();
                    loguser_tab[j].setUrcleunik(record.getInt("urcleunik"));
                    loguser_tab[j].setUrlogin(record.getString("login"));
                    loguser_tab[j].setUridlogo(record.getInt("logo"));
                    loguser_tab[j].setUrlmcleunik(record.getInt("lmcleunik"));
                    loguser_tab[j].setUrlmabrev(record.getString("lmabrev"));
                    loguser_tab[j].setDatabase(record.getString("databaseserver"), record.getString("databasename"), record.getInt("databaseport"));
                    loguser_tab[j].setSociete(record.getInt("soccleunik"));
//loguser_tab[j].setUrrights(record.getInt("udroits"));
                    Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "element " + loguser_tab[j].getUrlogin());
                    j++;
                }
            }
        } catch (SQLException e) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, e);
            Logger.getDefaultLogger().printStackTrace(Logger.LOG_EMERGENCY, e);
        } catch (Exception e1) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, "erreur" + e1);
        }
        return loguser_tab;
    }

    public Loginusers_T[] getUserList(int societe) {
        if ((dbCon == null) || !checkConnection(dbCon))
            dbCon = connectDb(this.username, this.pass, this.database, this.host, this.port);
        int i;
        Loginusers_T[] loguser_tab = null;
        ResultSet record;
        try {
            PreparedStatement select = dbCon.prepareStatement(requeteSQL[GET_USERS_SOCNUMBER]);
            select.setInt(1, societe);
            record = select.executeQuery();
            record.last();
            i = record.getRow();
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "nbr lignes " + i);
            if (i == 0) {
                loguser_tab = new Loginusers_T[1];
                loguser_tab[0] = new Loginusers_T();
                loguser_tab[0].setUrlogin("SORRY,NO USER IN DB.");
            } else {
                loguser_tab = new Loginusers_T[i];
// Loginusers_T[] loguser_tab=new  Loginusers_T[1];
                record.beforeFirst();
                Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "num2" + record.getRow());
                for (int j = 0; record.next(); j++) {
                    loguser_tab[j] = new Loginusers_T();
                    loguser_tab[j].setUrcleunik(record.getInt("urcleunik"));
                    loguser_tab[j].setUrlogin(record.getString("login"));
                    loguser_tab[j].setUridlogo(record.getInt("logo"));
                    loguser_tab[j].setUrlmcleunik(record.getInt("lmcleunik"));
                    loguser_tab[j].setUrlmabrev(record.getString("lmabrev"));
                    loguser_tab[j].setDatabase(record.getString("databaseserver"), record.getString("databasename"), record.getInt("databaseport"));
                    loguser_tab[j].setSociete(record.getInt("soccleunik"));
//loguser_tab[j].setUrrights(record.getInt("udroits"));
                    Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "element " + loguser_tab[j].getUrlogin());
                }
            }
        } catch (SQLException e) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, e);
        } catch (Exception e1) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, "erreur" + e1);
        }
        return loguser_tab;
    }

    public Loginusers_T connectUser(Loginusers_T user) throws srcastra.astra.sys.rmi.Exception.AuthorizationFailedException {
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, user.toString());
        if (mainUsersList == null)
            getUserList();
        for (int i = 0; i < mainUsersList.length; i++) {
            if ((mainUsersList[i].getUrcleunik() == user.getUrcleunik())
                    && (mainUsersList[i].getSociete() == user.getSociete())) {
               //Connection tmp = connectDb(mainUsersList[i].getUrlogin(),
               //        user.getUrpassword(),
                Connection tmp = connectDb(this.username, this.pass,
                        mainUsersList[i].getDatabaseName(),
                        mainUsersList[i].getDatabaseServer(),
                        mainUsersList[i].getDatabasePort());
                
          
                
                
                if (tmp == null)
                    throw new srcastra.astra.sys.rmi.Exception.AuthorizationFailedException("Could not log in database");
                
                  try{
                  Connection con=connectDb(this.username,this.pass,this.database, "", 3306);
                  PreparedStatement ps = con.prepareStatement("select count(*) as cpt from user where urlogin=? and urpassword=Password(?)" );
                  ps.setString(1, user.getUrlogin());
                  ps.setString(2,user.getUrpassword());
                  ResultSet rs = ps.executeQuery();
                  
                  rs.next();
                  
                  System.out.println(rs.getString(1));
                  if(rs.getString(1).equals("0"))
                    return null;
                  
                  }catch(Exception e)
                  {
                      
                      
                  }
                
                user.setDatabase(mainUsersList[i].getDatabaseServer(), mainUsersList[i].getDatabaseName(), mainUsersList[i].getDatabasePort());
                user.setUrrights(mainUsersList[i].getUrrights());
                user.setDatabaseLogin(this.username);
                user.setDatabasePassword(this.pass);
//user.setUrlmcleunik(mainUsersList[i].getUrlmcleunik());
                getMoreUserInfos(tmp, user);
                userLogged(user, tmp);
                return user;
            }
        }
        return null;
    }

    public Connection reconnectIfNeeded(String user, String pass, String database, String server, int port, Connection actual) throws srcastra.astra.sys.rmi.Exception.AuthorizationFailedException {
        if (checkConnection(actual))
            return actual;
        Connection tmp = connectDb(user, pass, database, server, port);
        if (tmp == null)
            throw new srcastra.astra.sys.rmi.Exception.AuthorizationFailedException("Could not log in database");
        return tmp;
    }

    public boolean checkConnection(Connection con) {
        try {
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Testing server connection");
            con.createStatement().executeQuery("select * from 1");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private void getMoreUserInfos(Connection con, Loginusers_T user) {
        try {
            PreparedStatement ps = con.prepareStatement(requeteSQL[GET_USER_DATA]);
            ps.setInt(1, user.getUrcleunik());
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData metadata=rs.getMetaData();
            int col=metadata.getColumnCount();


            while(rs.next()){
                String aff="";
                 for(int i=0;i<col;i++){
                     Object tmp=rs.getObject(i+1);
                     if(tmp!=null){
                     aff=aff+" "+tmp;
                     }
                 }
                System.out.println("Users "+aff);

            }
           
            rs.beforeFirst();
            rs.next();//SELECT u.eecleunik, u.urnom, u.urprenom, u.droits,u.urdroit,u.urmailbur,e.eesmtp,u.ursignature
            user.setDroits(new srcastra.astra.sys.droits.Rights(rs.getString("u.droits")));
            user.setUreecleunik(rs.getInt("u.eecleunik"));
            user.setUserName(rs.getString("u.urnom"), rs.getString("u.urprenom"));
            user.setCle2(user.getUrcleunik());
            user.setUrrights(rs.getInt("u.urdroit"));
            user.setUrMailBur(rs.getString("u.urmailbur"));
            user.setEeSmtp(rs.getString("e.eesmtp"));
            user.setSignature(rs.getString("u.ursignature"));
// ps=con.prepareStatement("SELECT
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void userLogged(Loginusers_T user, Connection connect) {
        String message;
        String sqlRequete;
        try {
            int loginId = managepool.requireId();
            String sessionString = managepool.requireSessionHash();

            Poolconnection tmppc;
            synchronized (managepool) {
                tmppc = new Poolconnection(loginId, connect, user.getUreecleunik(), user.getName(), user.getUrrights(), sessionString);
                tmppc.setDroits(user.getDroits());
                tmppc.setPass(user.getUrpassword());
                tmppc.setUrcle2(user.getCle2());
                tmppc.setLmcleunik(user.getUrlmcleunik());
                tmppc.setUrsociete(user.getSociete());
                tmppc.setDatabaseName(user.getDatabaseName());
                
                tmppc.setDatabaseLogin(user.getDatabaseLogin());
                tmppc.setDatabasePassword(user.getDatabasePassword());
                
                //tmppc.getNumeroentite()
                user.setUrcleunik(loginId);
                try {
                    tmppc.setHost(java.rmi.server.RemoteServer.getClientHost());
                    Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Host set to " + java.rmi.server.RemoteServer.getClientHost());
                } catch (ServerNotActiveException e) {
                    Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, "Arg, couldn't get clienthost");
                    e.printStackTrace();
                }
                managepool.pooladd(tmppc);
            }
            sqlRequete = "insert into session values('" + sessionString + "'," + user.getUrcleunik() + ",NOW(),NOW());";
            Transaction.execrequete(sqlRequete, connect);
            user.setDroits(tmppc.getDroits());
        } catch (Exception e2) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "--> Exception : " + e2);
            user.setErreurmessage("Exception :" + e2.getMessage());
            user.setErreurcode(0);
            user.setTmpString(null);
        }       // Add your handling code here:
    }

    private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) {
        String message;
        Connection tmpcon = null;
        try {
            if (jdbcDriverClassName != null)
                Class.forName(jdbcDriverClassName);
            tmpcon = DriverManager.getConnection("jdbc:" + jdbcName + "://" + dbHost + ":" + dbPort + "/" + dbName + "?autoReconnect=true", userName, password);
//tmpcon = DriverManager.getConnection("jdbc:"+jdbcName+"://195.162.199.148:5000/Astratmp",user,pass);
/*returnerreur.setErreurmessage("Connection etablie avec succès");
returnerreur.setErreurcode(10000);*/
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "connecter à la bd");
        } catch (ClassNotFoundException e0) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, "--> ClassNotFoundException : " + e0);
/*returnerreur.setErreurmessage("ClassNotFoundException :"+e0.getMessage());
returnerreur.setErreurcode(0);*/
        } catch (SQLException e1) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "--> SQLException : " + e1);
/*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
returnerreur.setErreurcode(e1.getErrorCode());*/
        } catch (Exception e2) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "--> Exception : " + e2);
/*returnerreur.setErreurmessage("Exception :"+e2.getMessage());
returnerreur.setErreurcode(0);*/
        }       // Add your handling code here:
        return tmpcon;
    }


}
