/*
* astraimplement.java
*
* Created on 7 mars 2002, 13:44
*/
package srcastra.astra.sys.rmi;

import srcastra.astra.gui.modules.aidedesicion.AbstractBuffer;
import srcastra.astra.gui.modules.aidedesicion.DecFatory;
import srcastra.astra.gui.modules.param.ServerIp;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.btn.workflow.BtnWorkFlow;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.configuration.AbstractConfig_T;
import srcastra.astra.sys.classetransfert.configuration.Compte;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.configuration.*;
import srcastra.astra.sys.document.Genere_Doc;
import srcastra.astra.sys.export.CheckThread;
import srcastra.astra.sys.manipuleclient.chargeclient.DeleteClient;
import srcastra.astra.sys.manipuleclient.chargeclient.InsertModClient;
import srcastra.astra.sys.manipuleclient.chargeclient.mRenvClient;
import srcastra.astra.sys.manipulesignaletique.ManipSegnaletique;
import srcastra.astra.sys.rmi.Exception.AuthorizationFailedException;
import srcastra.astra.sys.rmi.Exception.ManageServSQLExcption;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface;
import srcastra.astra.sys.rmi.groupe_dec.ManageGroupeDec;
import srcastra.astra.sys.rmi.socketfactory.SSLClientSocketFactory;
import srcastra.astra.sys.rmi.socketfactory.SSLServerSocketFactory;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.signalitique.*;
import srcastra.test.ReopenYear;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

//import srcastra.astra.sys.rmi.socketfactory.*;

/**
 * Unicast remote object implementing java.rmi.Remote interface.
 *
 * @author thom
 * @version 1.1  - Buffered revision
 */
public class astraimplement extends java.rmi.server.UnicastRemoteObject
        implements srcastra.astra.sys.rmi.astrainterface,
        srcastra.astra.sys.rmi.utils.CheckInterface {
    String host;
    static int m_port;
    static ServerIp serverconfig;

    public static ServerIp getServerconfig() {
        return serverconfig;
    }

    public static void setServerconfig(ServerIp serverconfig2) {
        serverconfig = serverconfig;
    }

    /**
     * Constructs astraimplement object and exports it on default port.
     */
    static Hashtable port;
    String m_centrum;
    CheckThread checkThread;
    public String m_smtp;
    public String m_to;
    public String m_from;
    BtnWorkFlow btnWorkFlow;

    public astraimplement(String host, String centrum, String database, String starthour, String endhour, String smtp, String from, String to, String am_pm, String graphic, String ip, int firewall, String directory, String xmlschema,String login,String password) throws RemoteException {


//super(((Integer)port.get("doss")).intValue(),SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
        super(m_port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        Enumeration enume = System.getProperties().keys();
        while (enume.hasMoreElements()) {
            String key = (String) enume.nextElement();
            System.out.println(key + "  " + System.getProperties().get(key));
        }
        setHost(host);
        m_from = from;
        m_to = to;
        m_smtp = smtp;
        m_centrum = centrum;
        
        

        
// Logger.getDefaultLogger().setMinLogLevel(Logger.LOG_INFOS);
// ThreadedEchoServer th=new ThreadedEchoServer(7800,this);
// th.start();
        Gestionerreur_T returnerreur = new Gestionerreur_T();
        usercon = new ArrayList();
        managepool = new ManagePoolconnection(usercon);
        genereRequeteClient();
        this.userManager = new UsersManager(managepool);
        this.signalitiques = new SignalitiqueManager();
        decisionTable = new Hashtable();
// String jdbcN
//tmpcon = DriverManager.getConnection("jdbc:"+jdbcName+"://"+dbHost+":"+dbPort+"/"+dbName+"?autoReconnect=true",userName,password);
        signalitiques.addSignalitique('c', COMBO_CODE_POST, new SignalitiqueCodePostal());
        signalitiques.addSignalitique('p', COMBO_PAYS, new SignalitiquePays());
        signalitiques.addSignalitique('d', COMBO_DEVISE, new SignalitiqueDevise());
        signalitiques.addSignalitique('l', COMBO_LANGUE, new SignalitiqueLangue());
        signalitiques.addSignalitique('L', -1, new SignalitiqueLocalite());
        signalitiques.addSignalitique('r', COMBO_TVAREGIME, new SignalitiqueTvaRegime());
        signalitiques.addSignalitique('f', COMBO_FOURNISSEUR, new SignalitiqueFournisseur());
        signalitiques.addSignalitique('g', COMBO_FOURNPROD, new SignalitiqueFournisseurProduit());
        signalitiques.addSignalitique('t', -1, new SignalitiqueValeurTva());
        signalitiques.addSignalitique('a', COMBO_TITREPERSONNES, new SignalitiqueTitrePersonne());
        signalitiques.addSignalitique('b', COMBO_LOGEMENT, new SignalitiqueLogement());
        signalitiques.addSignalitique('e', COMBO_TRANSPORT, new SignalitiqueTransport());
        signalitiques.addSignalitique('?', COMBO_FOURNISSEURCOMP, new SignalitiqueFournisseurComplet());
        signalitiques.addSignalitique('?', COMBO_FOURNDOC, new SignalitiqueFournisseurDocument());
        signalitiques.addSignalitique('?', COMBO_FOURGRPDEC, new SignalitiqueFournisseurGroupeDecision(this));
        signalitiques.addSignalitique('?', COMBO_FOURNCONTACT, new SignalitiqueFournisseurContact());
        signalitiques.addSignalitique('?', COMBO_SIGNALITIQUE, new SignalitiqueSignalitique());
        signalitiques.addSignalitique('?', COMBO_TVA_TYPE, new SignalitiqueTvaType());
        signalitiques.addSignalitique('?', COMBO_TITREPERSONNES, new SignalitiqueTitrePersonne());
        signalitiques.addSignalitique('?', COMBO_CATEGORIE_PRODUIT, new SignaletiqueTypeProduit());
        signalitiques.addSignalitique('?', COMBO_DESTINATION, new SignaletiqueDestination());
        signalitiques.addSignalitique('?', COMBO_COMPAGNIE, new SignaletiqueCompagnie());
        signalitiques.addSignalitique('?', COMBO_COMPTE, new SignaletiqueCompte());
        signalitiques.addSignalitique('?', COMBO_EMBARQDEBARQ, new SignalitiqueEmbarqDebarq());
        signalitiques.addSignalitique('?', COMBO_PRODUITGRPDEC, new SignalitiqueProduitGroupeDecision());
        signalitiques.addSignalitique('?', COMBO_NATIONALITE, new SignalitiqueNationalite());
        signalitiques.addSignalitique('?', COMBO_SUPREDUC_GRPDEC, new SignalitiqueSupReductGroupeDecision());
        dossierRmi = new DossierImplement(this, m_port);
        fournisseurRmi = new FournisseurImplement(this, m_port);
        grprmi = new srcastra.astra.sys.rmi.groupe_dec.ManageGroupeDec("", this, m_port);
        comptarmi = new ParamComptable(this, m_port);
        configrmi = new ConfigRmiModule(this, m_port);
        userrmi = new UserRmi(this, m_port);
        entite = new EntiteRmi(this, m_port);
        compteRmi = new CompteRmi(this, m_port);
        comptecentral = new CompteCentralRmi(this, m_port);
        traductioncompte = new Traduction_compteRmi(this, m_port);
        typepaiement = new TypePayementRmi(this, m_port);
        caisseLibelle = new CaisselibelleRmi(this, m_port);
        soldeCompta = new SoldeRmi(this, m_port);
        periodeCompta = new PeriodeRmi(this, m_port);
        userentite = new UserentitecompteRmi(this, m_port);
        this.setDatabase(getHost(), 3306, centrum);
        this.setDriver("org.gjt.mm.mysql.Driver", "mysql");
        this.setUser(login,password);
        initSocBuffers();
        initTreeBuffers();
 //       if (!directory.equals("NO")) {
 //           btnWorkFlow = new BtnWorkFlow(directory, xmlschema, database, host);
 //       }

        try {
          /*  if (graphic.equals("true")) {
                String path = srcastra.astra.sys.ManageDirectory.testDirectory("config");
                File file2 = new File(path + "/config.dat");
                if (file2.exists()) {
                    System.out.println("[********]file existe");
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file2));
                    m_smtp = in.readObject().toString();
                    in.close();
                } else {
// System.out.println("file "+file .toURI().toString());
                    m_smtp = javax.swing.JOptionPane.showInputDialog("SMTP SERVER ?");
//  ip2 = javax.swing.JOptionPane.showInputDialog("IP du serveur ?");
                    file2.createNewFile();
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file2));
                    out.writeObject(m_smtp);
                    out.close();

                }
                file2 = new File(path + "/adresse.dat");
                if (file2.exists()) {
                    System.out.println("[********]file existe");
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file2));
                    m_from = in.readObject().toString();
                    in.close();
                } else {
// System.out.println("file "+file .toURI().toString());
                    m_from = javax.swing.JOptionPane.showInputDialog("EMAIL ?");
//  ip2 = javax.swing.JOptionPane.showInputDialog("IP du serveur ?");
                    file2.createNewFile();
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file2));
                    out.writeObject(m_from);
                    out.close();

                }
                new srcastra.astra.sys.rmi.servergraphic.ServerFrame().show();
//String host=super.
            }else{
                m_smtp=smtp;
                m_from=from;

            }*/
            
            if (graphic.equals("true")) {
                new srcastra.astra.sys.rmi.servergraphic.ServerFrame().show();
                
            }
            
            checkThread = new CheckThread(starthour, endhour, m_smtp, to, m_from, database, host, am_pm,login,password);
        } catch (Exception e) {
            e.printStackTrace();

        }

// new ServeurFrame().show();
    }

    private void initTreeBuffers() {
        for (int i = 0; i < userlist.length; i++) {
            Loginusers_T user = userlist[i];
            if (decisionTable.get(new Integer(user.getSociete())) == null) {
                Hashtable tmp = new Hashtable();
                tmp.put(new Integer(AbstractRequete.SUP_RECUC), new Decision(new SupreducLibelleRequete(11), AbstractRequete.SUP_RECUC));
                tmp.put(new Integer(AbstractRequete.MEMO), new Decision(new MemoRequete(11)));
                tmp.put(new Integer(AbstractRequete.TVA_TYPE), new Decision(new TypeTvaRequete(1)));
                tmp.put(new Integer(AbstractRequete.TVA_REGIME), new Decision(new RegimeTvaRequete(1)));
                tmp.put(new Integer(AbstractRequete.IMPRESSION), new Decision(new ImpressionRequete(3)));
                tmp.put(new Integer(AbstractRequete.IMPRESSION), new Decision(new ImpressionRequete(3)));
                tmp.put(new Integer(AbstractRequete.DECSRIPTIF_LOG), new Decision(new DescLogRequete(8)));
                tmp.put(new Integer(AbstractRequete.DIVERS), new Decision(new DiversRequete(2)));
                decisionTable.put(new Integer(user.getSociete()), tmp);
            }
        }
    }
    /** Construct astraimplement object and export it to specified port+ZipSocketFactory
     *
     */
/* public astraimplement( int port ) throws RemoteException {
super( port, new ZipClientSocketFactory(), new ZipServerSocketFactory() );
Gestionerreur_T returnerreur=new Gestionerreur_T();
usercon=new ArrayList();
managepool=new ManagePoolconnection(usercon);
connectdb("jerome","F",returnerreur);
init2();
}*/

    /**
     * This check that the handle passed as argument by a client is valid
     * and owned by the computer calling remote invocation. If this is the case,
     * check if the database connection is still valid and reconnect if needed.
     *
     * @param handle:          handle contains the id of user connection.
     * @param needValidSocket: if true and the connection to the database is lost
     *                         for some reason, throw an AtomicOperationFailed
     * @throw AtomicOperationFailed: If the connection to the database has been lost and
     * needed to be still alive.
     */
    public Poolconnection getConnectionAndCheck(int handle, boolean mayReconnect) throws RemoteException {
        synchronized (managepool) {
            managepool.poolgetsize();
            Poolconnection tmpool = (Poolconnection) managepool.renvObjectprurcleunik(handle);
            if (tmpool != null)
                try {
                    if (!tmpool.isHost(this.getClientHost()))
                        throw new srcastra.astra.sys.rmi.Exception.AuthorizationFailedException("Access forbidden");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new AuthorizationFailedException("Access forbidden. embedded Exception: " + e.getMessage());
                }
            return tmpool;
        }
    }

    public Poolconnection getConnection(int handle, boolean mayReconnect) throws RemoteException {
        synchronized (managepool) {
            managepool.poolgetsize();
            Poolconnection tmpool = (Poolconnection) managepool.renvObjectprurcleunik(handle);
            return tmpool;
        }
    }

    private void setDatabase(String databaseServer, int port, String database) {
        userManager.setDatabase(database, databaseServer, port);
    }

    private void setDriver(String driverClass, String driverName) {
        userManager.setDriver(driverClass, driverName);
    }

    private void setUser(String username, String pass) {
        userManager.setUser(username);
        userManager.setPassword(pass);
    }

    /**
     * Seems a useless thing :))
     */
    private void init2() {


    }


    /**
     * Register astraimplement object with the RMI registry.
     *
     * @param name   - name identifying the service in the RMI registry
     * @param create - create local registry if necessary
     * @throw RemoteException if cannot be exported or bound to RMI registry
     * @throw MalformedURLException if name cannot be used to construct a valid URL
     * @throw IllegalArgumentException if null passed as name
     */
    public static void registerToRegistry(String name, Remote obj, boolean create) throws RemoteException, MalformedURLException {
        if (name == null) throw new IllegalArgumentException("registration name can not be null");
        try {

            Naming.rebind(name, obj);
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "objet lié au registre");
        } catch (RemoteException ex) {

//Registry r=LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
            try {
//
                Registry r = LocateRegistry.createRegistry(m_port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
//Registry  r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                r.rebind(name, obj);
                Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "Nouveau registre créé, objet lié au registre");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("et oui fallait si attendre");
            }
        }
    }

    /**
     * Constructs astraimplement object and exports it on specified port.
     *
     * @param port The port for exporting
     */
    public static void main(String[] args) {
        String str = null;
        String path = null;
        port = new Hashtable();
        ServerIp server = null;
        String graphic = ""; //args[11];
        String CleAgenceServeur="";
        String host="";
        String alias="";
        String h="";
        String h1="";
        String SMTP="";
        String MAIL1="";
        String MAIL2="";
        String P1="",P2="",P3="",P4="";
        
        boolean graphicContext = false;
        try {
            String centrum = "";//args[2];
            String astra = "";//args[4];
            
            String login="";
            String password="";
            
            if(args.length==2)
            CleAgenceServeur=args[1];
            
            String url="";
            
            if (CleAgenceServeur.equals(""))
            {
                String portPath = srcastra.astra.sys.ManageDirectory.testDirectory("config");
                File file2 = new File(portPath + "/key.dat");
                
                if (file2.exists()) {
                    BufferedReader in = new BufferedReader(new FileReader(file2));
                    while ((str = in.readLine()) != null) {
                            CleAgenceServeur= str;
                        }
                        in.close();
                    
                    
                }
                else {
                
                String tmpPort = javax.swing.JOptionPane.showInputDialog("KEY AGENCE ?");
                file2.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file2));
                out.write(tmpPort.trim());
                out.close();
                CleAgenceServeur=tmpPort;  
                }
            }
            
          
            
            Class.forName("org.gjt.mm.mysql.Driver");
            
            if(args[0].equals("1"))
            url="jdbc:mysql://ooohtml.no-ip.info:3306/AstraConnect";
            else
            url="jdbc:mysql://ooohtml.no-ip.info:3306/AstraConnect";
            
            Connection con=null;
            boolean oki=false;
            try{
            
            con=DriverManager.getConnection(url,"agence","keyagence3164978520");
            oki=true;
            }catch(Exception e) { e.printStackTrace(); }
            
            finally {
                
                if(!oki)
                {
                    try{
                        url="jdbc:mysql://195.144.69.11:3306/AstraConnect";
                        con=DriverManager.getConnection(url,"agence","keyagence3164978520");
                        
                    }
                    catch (Exception e) { e.printStackTrace();
                   // JOptionPane.showMessageDialog(null,"Error param connection");
                    System.exit(0);
                    }
                }
                
            }
            System.out.println(url);
            PreparedStatement stmt=con.prepareStatement("select decode(bdoLogin,?), decode(bdoPassword,?), decode(host,?), decode(alias,?), decode(centrum,?), decode(port,?), decode(astra,?), H, H1, decode(SMTP,?), decode(MAIL1,?), decode(MAIL2,?), P1,P2,P3,P4 from ParamServeur where cleAgence like md5(Password(?))");
            stmt.setString(1, CleAgenceServeur);
            stmt.setString(2, CleAgenceServeur);
            stmt.setString(3, CleAgenceServeur);
            stmt.setString(4, CleAgenceServeur);
            stmt.setString(5, CleAgenceServeur);
            stmt.setString(6, CleAgenceServeur);
            stmt.setString(7, CleAgenceServeur);
            stmt.setString(8, CleAgenceServeur);
            stmt.setString(9, CleAgenceServeur);
            stmt.setString(10, CleAgenceServeur);
            stmt.setString(11, CleAgenceServeur);
            
            ResultSet rs=stmt.executeQuery();
            
            int cpt=0;
            
            while (rs.next())
            {   
                login=rs.getString(1);
                password=rs.getString(2);
                host=rs.getString(3);
                alias=rs.getString(4);
                centrum=rs.getString(5);
                m_port=rs.getInt(6);
                astra=rs.getString(7);
                h=rs.getString(8);
                h1=rs.getString(9);
                SMTP=rs.getString(10);
                MAIL1=rs.getString(11);
                MAIL2=rs.getString(12);
                P1=rs.getString(13);
                graphic=rs.getString(14);
                P3=rs.getString(15);
                P4=rs.getString(16);
                
                cpt++; 
           }
            
           rs.close();
            stmt.close();
            con.close();
            
            if (cpt!=1)
               System.exit(0);
            
            if (graphic.equals("true")) {
                graphicContext = true;
            }
//   if(args[0].equals("localhost")){
            new srcastra.astra.sys.init.MysqlUser(graphicContext, centrum, astra, login, password);
            
// }
  
            new ReopenYear(astra,login,password);
            
            
          /*  if (graphic.equals("false")) {
                File file = new File(args[3]);
                BufferedReader in = new BufferedReader(new FileReader(file));
                int i = 1;
                while ((str = in.readLine()) != null) {
                    m_port = new Integer(str).intValue();
                }
                in.close();
            } else {
                String portPath = srcastra.astra.sys.ManageDirectory.testDirectory("config");
                File file2 = new File(portPath + "/port.dat");
                if (file2.exists()) {
                    System.out.println("[********]file existe");
                    BufferedReader in = new BufferedReader(new FileReader(file2));
                    try {
                        while ((str = in.readLine()) != null) {
                            m_port = new Integer(str).intValue();
                        }
                        in.close();

                        if (m_port == 0) {
                            Exception e = new Exception("INCORECT PORT==NULL");
                            throw e;
                        }


                    } catch (NumberFormatException nn) {
                        Exception e = new Exception("INCORECT PORT==" + str.toString());
                        throw e;
                    }
                } else {
                    String tmpPort = javax.swing.JOptionPane.showInputDialog("SERVER PORT ?");
                    file2.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(file2));
                    out.write(tmpPort.trim());
                    out.close();
                    m_port = new Integer(tmpPort).intValue();
                }
            }*/
            astraimplement obj = new astraimplement(host,centrum, astra, h, h1, SMTP, MAIL1, MAIL2, P1, graphic, null, 0, P3, P4,login,password);
            registerToRegistry(alias, obj, true);
            InetAddress myadress = InetAddress.getLocalHost();
            System.out.println("Mon adresse est :" + myadress);

        } catch (java.net.UnknownHostException he) {
            he.printStackTrace();
            throwDGraphicException(graphicContext, he);
        } catch (RemoteException rn) {
            rn.printStackTrace();
            throwDGraphicException(graphicContext, rn);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throwDGraphicException(graphicContext, ex);
        } catch (IOException e) {
            e.printStackTrace();
            throwDGraphicException(graphicContext, e);
        } catch (SQLException sn) {
            sn.printStackTrace();
            throwDGraphicException(graphicContext, sn);
        } catch (Exception e) {
            e.printStackTrace();
            throwDGraphicException(graphicContext, e);

        }

    }

    private static void throwDGraphicException(boolean graphic, Exception e) {
        if (graphic) {
            srcastra.astra.gui.sys.ErreurScreenLibrary.displayErreur(null, srcastra.astra.gui.sys.ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
        System.exit(0);
    }

    private void initSocBuffers() {
        userlist = userManager.getUserList();
        init_buffer(null);

    }
//*******************************************************************************************
//
//Implementation des méthodes distantes
//--------------------------------------------------------------------------------------------
//
//
    public boolean deletefournisseur(int focleunik) throws RemoteException {
        boolean x = false;
        return x;
    }
//--------------------------------------------------------------------------------------------
//
//
    public Gestionerreur_T modifyFournisseurMemo(String memo, int frcleunik, int urcleunik) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "avant affichage");
// Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"pays"+fourn.getPyscleunik()+" devise"+fourn.getDecleunik()+" langue"+fourn.getLecleunik()+" code"+fourn.getCxcleunik()+" user"+urcleunik);
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        ServeurBuffer buf = tmpool.getBuffer();
//String usercon=(String)tmpool.getConuser();
        try {
            int tmpdebug = frcleunik;
// Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"cle du fournisseur "+fourn.getFrcleunik()+fourn.getFrfax());
            sqlrequete = "update  fournisseur " +
                    "set frmemo='" + memo + "', frdatetimemodif=NOW(), snumerosessionmodif='" + tmpool.getUrnumerosession() + "' " +
                    "where frcleunik='" + frcleunik + "';";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user " + tmpool.getConuser());
            synchronized (buf) {
                tmpret = Transaction.execrequeteModif(sqlrequete, tmpool.getConuser());
                buf.invalidateBuffer("fournisseurDir");
            }
//Transaction.commit(tmpool.getConuser());
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }

    public Gestionerreur_T insertfournisseur(Fournisseur_T fourn, int urcleunik) throws RemoteException {
        boolean x = false;
        String sqlrequetefourn = null;  //lors de l'insertion d'un fournisseur on insère aussi automatiquement 2 ligne de décisions(été/hiver),
// String []sqlrequetegrpfourn=new String[2];  //une table de document par défaut, et un groupe de produit par défault
        String sqlrequetedocument = null;
        String sqlrequeteGrpProd = null;
        Gestionerreur_T tmpret = null;
        Grpdecision_T grpd = null;
        Document_T doc = null;
        int aecleunik = 1;    //variable temporaire qui sert à l'insertion d'une cle analityque dans lors de l'insertion du
//  groupe de produit par défaut. Elle devra être remplacée par une variable de paramètrage system
        int saison = 16;      //variable temporaire qui sert à choisir si on donne une ligne de décision de type hiver ou bien été
//  au nouveau produit créé
        int[] tmpint = new int[2];
        int frcleunik = 0;
        int grpCleunikHiver = 0;
        int grpCleunikEte = 0;
        int frgtCleunik = 0;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "avant affichage");
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, fourn.getFrtvaregime());
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        ServeurBuffer buff = tmpool.getBuffer();
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "pays" + fourn.getPyscleunik() + " devise" + fourn.getDecleunik() + " langue" + fourn.getLecleunik() + " code" + fourn.getCxcleunik() + " user" + urcleunik);
        try {
            synchronized (buff) {
                grpd = ChargeDefaultGrpFourn(tmpool.getConuser(), 1);   //Ces 2 fonctions chargent 2 objets avec les valeurs par défault du des document et des ligne
                doc = ChargeDefaultDocumentFourn(tmpool.getConuser());  //de décisions
//
//requete pour insérer un fournisseur
                String slrequetefourn = "insert into fournisseur"
                        + "(frnom1,frnom2,frreference1,frreference2,"
                        + "fradresse,frtvanum,frtvatype,frtvaregime,"
                        + "frnumbanque1,frnumbanque2,frnumbanque3,"
                        + "frtelephone1,frfax,frmail,frmodecccf,"
                        + "frdelaipaienbrjour,frdomiciliation,frmemo,"
                        + "frdatetimecrea,frdatetimemodif,"
                        + "snumerosessioncrea,snumerosessionmodif,decleunik,"
                        + "cxcleunik,aecleunik,lecleunik,pyscleunik,"
                        + "frfournprod, frNcompte)"
                        + " values('" + fourn.getFrnom1() + "','"
                        + fourn.getFrnom2() + "','" + fourn.getFrreference1()
                        + "','" + fourn.getFrreference2() + "','"
                        + fourn.getFradresse() + "','" + fourn.getFrtvanum()
                        + "','" + fourn.getFrtvatype() + "','"
                        + fourn.getFrtvaregime() + "','"
                        + fourn.getFrnumbanque1() + "','"
                        + fourn.getFrnumbanque2() + "','"
                        + fourn.getFrnumbanque3() + "','"
                        + fourn.getFrtelephone1() + "','"
                        + fourn.getFrfax() + "','" + fourn.getFrmail() + "',"
                        + fourn.getFrmodecccf() + ","
                        + fourn.getFrdelaipaienbrjour() + ",'"
                        + fourn.getFrdomiciliation() + "','"
                        + fourn.getFrmemo() + "',NOW()"/*+fourn.getFrdatetimecrea()*/
                        + ",NOW()"/*+fourn.getFrdatetimemodif()*/ + ",'"
                        + tmpool.getUrnumerosession() + "','"
                        + tmpool.getUrnumerosession() + "',"
                        + fourn.getDecleunik() + "," + fourn.getCxcleunik() + ","
                        + fourn.getAecleunik() + "," + fourn.getLecleunik() + ","
                        + fourn.getPyscleunik() + ","
                        + fourn.getFrFournProduit() + ","
                        + fourn.getFrCompteCleunik() + ");";
                Transaction.begin(tmpool.getConuser()); //début de la transaction
                tmpret = Transaction.execrequeteinsert(sqlrequetefourn, tmpool.getConuser()); // j'insère le fournisseur et récupère son id dans tmpret.getTmpin()
                if (tmpret.getErreurcode() == 10000)// si l'insertion s'est réalisée avec succés je continue
                {
                    frcleunik = tmpret.getTmpint();
                    sqlrequetedocument = "insert into fournisseur_document"
                            + " (frcleunik,frdtdatetimecrea,"
                            + "frdtdatetimemodif,frdtnbrdocprev,"
                            + "frdtnbrconfprev,frdtnbrfactprev,"
                            + "frdtnbrncprev,frdtnbrfactsprev,"
                            + "snumerosessioncrea,snumerosessionmodif)"
                            + " values(" + frcleunik + ",NOW(),NOW(),"
                            + doc.getFrdtnbrdocprev() + ","
                            + doc.getFrdtnbrconfprev() + ","
                            + doc.getFrdtnbrfactprev() + ","
                            + doc.getFrdtnbrcprev() + ","
                            + doc.getFrdtnbrfactsprev() + ",'"
                            + tmpool.getUrnumerosession() + "','"
                            + tmpool.getUrnumerosession() + "');";
//                    Insertion de la ligne des documents par défaut
                    tmpret = Transaction.execrequeteinsert(sqlrequetedocument, tmpool.getConuser());
                    if (tmpret.getErreurcode() == 10000) //si l'insertion c'est réalisée avec succès
                    {
                        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, frcleunik);
                        String tmpnom = fourn.getFrnom1();
                        sqlrequeteGrpProd = "insert into fournisseur_grproduit"
                                + "(frcleunik,frgtitrecatalog,frgtreference1,"
                                + "frgtreference2,aecleunik,frgtdatetimecrea,"
                                + "frgtdatetimemodi,snumerosessioncrea,"
                                + "snumerosessionmodif) values("
                                + frcleunik + ",'" + tmpnom + "','" + tmpnom + "','"
                                + tmpnom + "'," + aecleunik + ",NOW(),NOW(),'"
                                + tmpool.getUrnumerosession() + "','"
                                + tmpool.getUrnumerosession() + "');";
                        tmpret = Transaction.execrequeteinsert(sqlrequeteGrpProd, tmpool.getConuser());
//insertion du groupe de produit par défaut
                        if (tmpret.getErreurcode() == 10000) {
                            int prodcleunik = tmpret.getTmpint();
                            sqlrequeteGrpProd = "insert into groupedecision("
                                    + "frcleunik,ttcleunik,itcleunik,aecleunik,"
                                    + "gndatetimecrea,gndatetimemodif,"
                                    + "gncodetvavente,gntvainclusvente,"
                                    + "gncodetvaachat,gntvainclusachat,"
                                    + "gnnbravanteche,gnacompteminpp,"
                                    + "gnprodstockiata,gnprodstockautre,"
                                    + "gnpccomvente,gncominclpvent,"
                                    + "gnpcacompte,gnpccomachat,"
                                    + "gnpcclerepvend,gnpcclerepconcept,"
                                    + "gnpcclerepmmere,snumerosessioncrea,"
                                    + "snumerosessionmodif,frgtcleunik,"
                                    + "gncomptevente,gncompteachat,"
                                    + "gnfracomptepc,gnfrnbda,gnfrsoldepc,"
                                    + "gnfrnbds,gnfranchise,caecleunik,"
                                    + "tecleunik) values(" + frcleunik + ","
                                    + grpd.getTtcleunik() + ","
                                    + grpd.getItcleunik() + ","
                                    + grpd.getAecleunik() + ",NOW(),NOW(),"
                                    + grpd.getGncodetvavente() + ","
                                    + grpd.getGntvainclusvente() + ","
                                    + grpd.getGncodetvaachat() + ","
                                    + grpd.getGntvainclusachat() + ","
                                    + grpd.getGnnbravanteche() + ","
                                    + grpd.getGnacompteminpp() + ","
                                    + grpd.getGnprodstockiata() + ","
                                    + grpd.getGnprodstockautre() + ","
                                    + grpd.getGnpccomvente() + ","
                                    + grpd.getGncominclpvent() + ","
                                    + grpd.getGnpcacompte() + ","
                                    + grpd.getGnpccomachat() + ","
                                    + grpd.getGnpcclerepvend() + ","
                                    + grpd.getGnpcclerepconcept() + ","
                                    + grpd.getGnpcclerepmmere() + ",'"
                                    + tmpool.getUrnumerosession() + "','"
                                    + tmpool.getUrnumerosession() + "',"
                                    + prodcleunik + ",'"
                                    + grpd.getGncomptevente() + "','"
                                    + grpd.getGncompteachat() + "',"
                                    + grpd.getGnfracomptepc() + ","
                                    + grpd.getGnfrnbda() + ","
                                    + grpd.getGnfrsoldepc() + ","
                                    + grpd.getGnfrnbds() + ","
                                    + grpd.getGnfranchise() + ","
                                    + grpd.getCaecleunik() + ","
                                    + grpd.getTecleunik() + ");";
                            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "requete " + sqlrequeteGrpProd);
                            tmpret = Transaction.execrequeteinsert(sqlrequeteGrpProd, tmpool.getConuser());
                            Transaction.commit(tmpool.getConuser());
                            tmpool.getBuffer().invalidateBuffer("fournisseurDir");
                        }
                    }//fin 4 ème requete
                }//fin 3 eme requete
            } // fin 2 eme requete
        } // fin try
        catch (SQLException sn) {
            sn.printStackTrace();
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + sn);
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        tmpret.setTmpint(frcleunik);
// fin synchronyze
        return tmpret;
    }
//--------------------------------------------------------------------------------------------
//
//

//-------------------------------------------------------------------------------------------
    public void remoterollback(int urcleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        Transaction.rollback(tmpool.getConuser());
        System.out.println("Rollback effectué");
    }

    public void remoteCommit(int urcleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        Transaction.commit(tmpool.getConuser());
        System.out.println("Commit effectué");
    }
//--------------------------------------------------------------------------------------------
//
//

    public Gestionerreur_T insertdocument(Document_T doc, int urcleunik) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
// ServeurBuffer buff=tmpool.getBuffer();              e
        try {
            sqlrequete = "insert into fournisseur_document (frcleunik,frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev," +
                    "frdtnbrncprev,frdtnbrfactsprev,snumerosessioncrea,snumerosessionmodif) values(" + doc.getFrcleunik() +
                    ",NOW(),NOW()," + doc.getFrdtnbrdocprev() + "," + doc.getFrdtnbrconfprev() + "," +
                    doc.getFrdtnbrfactprev() + "," + doc.getFrdtnbrcprev() + "," +
                    doc.getFrdtnbrfactsprev() + ",'" + tmpool.getUrnumerosession() + "','" + tmpool.getUrnumerosession() + "');";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user " + tmpool.getConuser());
            Transaction.begin(tmpool.getConuser());
            synchronized (tmpool.getBuffer()) {
                tmpret = Transaction.execrequeteinsert(sqlrequete, tmpool.getConuser());
                Transaction.commit(tmpool.getConuser());
                tmpool.getBuffer().invalidateBuffer("fournisseurDir");
            }
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }
//--------------------------------------------------------------------------------------------
//
//

    public Gestionerreur_T insertgroupedecision(Grpdecision_T grpd, Connection usercon, String numsess) {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        try {
            sqlrequete = "insert into groupedecision(frcleunik,ttcleunik,itcleunik,aecleunik,gndatetimecrea,gndatetimemodif,gncodetvavente," +
                    "gntvainclusvente,gncodetvaachat,gntvainclusachat,gnnbravanteche,gnacompteminpp,gnprodstockiata,gnprodstockautre,gnpccomvente,gncominclpvent,gnpcacompte,gnpccomachat,gnpcclerepvend,gnpcclerepconcept,gnpcclerepmmere,snumerosessioncrea,snumerosessionmodif,frgtcleunik,gncomptevente,gncompteachat,gnfracomptepc,gnfrnbda,gnfrsoldepc,gnfrnbds,gnfranchise,caecleunik,tecleunik) values(" + grpd.getFrcleunik() +
                    "," + grpd.getTtcleunik() + "," + grpd.getItcleunik() + "," +
                    grpd.getAecleunik() + ",NOW(),NOW()," + grpd.getGncodetvavente() + "," + grpd.getGntvainclusvente() + "," + grpd.getGncodetvaachat() + "," + grpd.getGntvainclusachat() + "," + grpd.getGnnbravanteche() + "," + grpd.getGnacompteminpp() + "," + grpd.getGnprodstockiata() + "," + grpd.getGnprodstockautre() + "," + grpd.getGnpccomvente() + "," + grpd.getGncominclpvent() + "," + grpd.getGnpcacompte() + "," + grpd.getGnpccomachat() + "," + grpd.getGnpcclerepvend() + "," + grpd.getGnpcclerepconcept() + "," + grpd.getGnpcclerepmmere() + ",'" + numsess + "','" + numsess + "'," + grpd.getFrgtcleunik() + ",'" + grpd.getGncomptevente() + "','" + grpd.getGncompteachat() + "'," + grpd.getGnfracomptepc() + "," + grpd.getGnfrnbda() + "," + grpd.getGnfrsoldepc() + "," + grpd.getGnfrnbds() + "," + grpd.getGnfranchise() + "," + grpd.getCaecleunik() + "," + grpd.getTecleunik() + ");";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user " + usercon);
            tmpret = Transaction.execrequeteinsert(sqlrequete, usercon);
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }
//--------------------------------------------------------------------------------------------
//
//
    public Gestionerreur_T insertfourncontact(FournContact_T fourcon, int urcleunik) throws RemoteException, ServeurSqlFailure {
//  String sqlrequete="INSERT INTO fournisseur_contact ( frctgrpadminist , frctnom , frctprenom , frctmail , frcttelephone , frcleunik , frctdatetimecrea , frctdatetimemodif , snumerosessioncrea,urcleunik,frctfax ) VALUES (? , ?, ?, ?, ?,  ?, NOW(),NOW() , ?, ?,? )";
        Gestionerreur_T tmpret = null;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement("INSERT INTO fournisseur_contact ( frctgrpadminist , frctnom , frctprenom , frctmail , frcttelephone , frcleunik , frctdatetimecrea , frctdatetimemodif , snumerosessioncrea,urcleunik,frctfax ) VALUES (? , ?, ?, ?, ?,  ?, NOW(),NOW() , ?, ?,? )");
            pstmt.setString(1, fourcon.getFrctgrpadminist());
            pstmt.setString(2, fourcon.getFrctnom());
            pstmt.setString(3, fourcon.getFrctprenom());
            pstmt.setString(4, fourcon.getFrctmail());
            pstmt.setString(5, fourcon.getFrcttelephone());
            pstmt.setInt(6, fourcon.getFrcleunik());
            pstmt.setString(7, tmpool.getUrnumerosession());
            pstmt.setString(9, fourcon.getFrctfax());
            pstmt.setInt(8, tmpool.getUrcle2());
            Transaction.begin(tmpool.getConuser());
            synchronized (tmpool.getBuffer()) {
                pstmt.execute();
                tmpret = Transaction.getLastInsertId(tmpool.getConuser());
                Transaction.commit(tmpool.getConuser());
                tmpool.getBuffer().invalidateBuffer("fournisseurDir");
            }
        } catch (SQLException sn) {
            ManageServSQLExcption.gestion1(sn, tmpool.getConuser());
//  Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Exception :"+i);
        }
        return tmpret;
    }
/*  private Gestionerreur_T insertfourncontact(FournContact_T fourcon,Poolconnection tmpool)
{
String sqlrequete;
Gestionerreur_T tmpret=null;
try{
sqlrequete="insert into fournisseur_contact (frctgrpadminist,frctnom,frctprenom,frctmail,frcttelephone,frcleunik,frctdatetimecrea,frctdatetimemodif,snumerosessioncrea,snumerosessionmodif) values('"+
fourcon.getFrctgrpadminist()+"','"+fourcon.getFrctnom()+"','"+fourcon.getFrctprenom()+"','"+fourcon.getFrctmail()+"','"+fourcon.getFrcttelephone()+"',"+fourcon.getFrcleunik()+",NOW(),NOW(),'"+tmpool.getUrnumerosession()+"','"+tmpool.getUrnumerosession()+"');";
//  synchronized(buff){
tmpret=Transaction.execrequeteinsert(sqlrequete,tmpool.getConuser());
//buff.invalidateBuffer("fournisseurDir");
//  }
}
catch(Exception i) {
Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Exception :"+i);
}
return tmpret;
}*/
//--------------------------------------------------------------------------------------------
//
//
    public Gestionerreur_T modifyfournisseur(Fournisseur_T fourn, int urcleunik) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "avant affichage");
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "pays" + fourn.getPyscleunik() + " devise" + fourn.getDecleunik() + " langue" + fourn.getLecleunik() + " code" + fourn.getCxcleunik() + " user" + urcleunik);
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        ServeurBuffer buf = tmpool.getBuffer();
//String usercon=(String)tmpool.getConuser();
        try {
            int tmpdebug = fourn.getFrcleunik();
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "cle du fournisseur " + fourn.getFrcleunik() + fourn.getFrfax());
            sqlrequete = "update  fournisseur " +
                    "set frnom1='" + fourn.getFrnom1() + "', frnom2='" + fourn.getFrnom2() + "', frreference1='" + fourn.getFrreference1() + "'," +
                    " frreference2='" + fourn.getFrreference2() + "', fradresse='" + fourn.getFradresse() + "', frtvanum='" + fourn.getFrtvanum() + "'," +
                    " frtvatype='" + fourn.getFrtvatype() + "', frtvaregime='" + fourn.getFrtvaregime() + "', frnumbanque1='" + fourn.getFrnumbanque1() + "'," +
                    " frnumbanque2='" + fourn.getFrnumbanque2() + "', frnumbanque3='" + fourn.getFrnumbanque3() + "', frtelephone1='" + fourn.getFrtelephone1() + "', frfax='" + fourn.getFrfax() + "'," +
                    " frmail='" + fourn.getFrmail() + "', frmodecccf=" + fourn.getFrmodecccf() + ", frdelaipaienbrjour=" + fourn.getFrdelaipaienbrjour() + ", frdomiciliation='" + fourn.getFrdomiciliation() + "'," +
                    " frmemo='" + fourn.getFrmemo() + "', frdatetimemodif=NOW(), snumerosessionmodif='" + tmpool.getUrnumerosession() + "'," +
                    " decleunik=" + fourn.getDecleunik() + ",cxcleunik=" + fourn.getCxcleunik() + ",aecleunik=" + fourn.getAecleunik() + ",lecleunik=" + fourn.getLecleunik() + ",pyscleunik=" + fourn.getPyscleunik() + "," +
                    " frfournprod=" + fourn.getFrFournProduit() + ",frNcompte=" + fourn.getFrCompteCleunik() +
                    " where frcleunik=" + fourn.getFrcleunik() + ";";

// System.out.println("pays"+fourn.getPyscleunik()+" devise"+fourn.getDecleunik()+" langue"+fourn.getLecleunik()+" code"+fourn.getCxcleunik()+" user"+urcleunik);
// managepool.poolgetsize();
//Poolconnection tmpool=(Poolconnection) managepool.renvObjectprurcleunik(urcleunik);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user " + tmpool.getConuser());
            synchronized (buf) {
                tmpret = Transaction.execrequeteModif(sqlrequete, tmpool.getConuser());
                buf.invalidateBuffer("fournisseurDir");
            }
//Transaction.commit(tmpool.getConuser());
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }
//--------------------------------------------------------------------------------------------
//
//
    public Gestionerreur_T modifyfourncontact(FournContact_T fourcon, int urcleunik) throws RemoteException, ServeurSqlFailure {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        ServeurBuffer buff = tmpool.getBuffer();
        try {
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement("UPDATE fournisseur_contact set frctgrpadminist=? , frctnom=? , frctprenom=? , frctmail=? , frcttelephone=?  , frctdatetimemodif=NOW(), snumerosessioncrea=?,urcleunikmodif=?,frctfax=? WHERE    frctcleunik  =?");
            pstmt.setString(1, fourcon.getFrctgrpadminist());
            pstmt.setString(2, fourcon.getFrctnom());
            pstmt.setString(3, fourcon.getFrctprenom());
            pstmt.setString(4, fourcon.getFrctmail());
            pstmt.setString(5, fourcon.getFrcttelephone());
            pstmt.setString(6, tmpool.getUrnumerosession());
            pstmt.setInt(7, tmpool.getUrcle2());
            pstmt.setString(8, fourcon.getFrctfax());
            pstmt.setInt(9, fourcon.getFrctcleunik());
            Transaction.begin(tmpool.getConuser());
            synchronized (tmpool.getBuffer()) {
                pstmt.execute();
//  tmpret=Transaction.getLastInsertId(tmpool.getConuser());
                Transaction.commit(tmpool.getConuser());
                tmpool.getBuffer().invalidateBuffer("fournisseurDir");
            }
        } catch (SQLException sn) {
            ManageServSQLExcption.gestion1(sn, tmpool.getConuser());
//  Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Exception :"+i);
        }
        return tmpret;
    }
//--------------------------------------------------------------------------------------------
//
//
    public Gestionerreur_T modifydocument(Document_T doc, int urcleunik) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        ServeurBuffer buff = tmpool.getBuffer();
        try {
            sqlrequete = "update fournisseur_document set frdtdatetimemodif=NOW(),frdtnbrdocprev=" + doc.getFrdtnbrdocprev() + ",frdtnbrconfprev=" + doc.getFrdtnbrconfprev() + ",frdtnbrfactprev=" + doc.getFrdtnbrfactprev() + ",frdtnbrncprev=" + doc.getFrdtnbrcprev() + ",frdtnbrfactsprev=" + doc.getFrdtnbrfactsprev() + ",snumerosessionmodif='" + tmpool.getUrnumerosession() + "'  where frdtcleunik=" + doc.getFrdtcleunik() + ";";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "requete" + sqlrequete);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user " + tmpool.getConuser());
            synchronized (buff) {
                tmpret = Transaction.execrequeteModif(sqlrequete, tmpool.getConuser());
                buff.invalidateBuffer("fournisseurDir");
            }
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Gestionerreur_T modifyGrpDecision(Grpdecision_T grpd, int urcleunik) throws RemoteException {
        String sqlrequete;
        Gestionerreur_T tmpret = null;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        ServeurBuffer buff = tmpool.getBuffer();
        try {
            sqlrequete = "update groupedecision set ttcleunik=" + grpd.getTtcleunik() + ",itcleunik=" + grpd.getItcleunik() + ",aecleunik=" + grpd.getAecleunik() + ",gndatetimemodif=NOW(),gncodetvavente=" + grpd.getGncodetvavente() + "," +
                    "gntvainclusvente=" + grpd.getGntvainclusvente() + ",gncodetvaachat=" + grpd.getGncodetvaachat() + ",gntvainclusachat=" + grpd.getGntvainclusachat() + ",gnnbravanteche=" + grpd.getGnnbravanteche() + ",gnacompteminpp=" + grpd.getGnacompteminpp() + ",gnprodstockiata=" + grpd.getGnprodstockiata() + ",gnprodstockautre=" + grpd.getGnprodstockautre() + ",gnpccomvente=" + grpd.getGnpccomvente() + ",gncominclpvent=" + grpd.getGncominclpvent() + ",gnpcacompte=" + grpd.getGnpcacompte() + ",gnpccomachat=" + grpd.getGnpccomachat() + ",gnpcclerepvend=" + grpd.getGnpcclerepvend() + ",gnpcclerepconcept=" + grpd.getGnpcclerepconcept() + ",gnpcclerepmmere=" + grpd.getGnpcclerepmmere() + ",snumerosessionmodif='" + tmpool.getUrnumerosession() + "',gncomptevente='" + grpd.getGncomptevente() + "',gncompteachat='" + grpd.getGncompteachat() + "',gnfracomptepc=" + grpd.getGnfracomptepc() + ",gnfrnbda=" + grpd.getGnfrnbda() + ",gnfrnbds=" + grpd.getGnfrnbds() + ",gnfranchise=" + grpd.getGnfranchise() + " where gncleunik=" + grpd.getGncleunik() + ";";
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Connexion du user" + tmpool.getConuser());
            synchronized (buff) {
                tmpret = Transaction.execrequeteModif(sqlrequete, tmpool.getConuser());
                buff.invalidateBuffer("fournisseurDir");
            }
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Exception :" + i);
        }
        return tmpret;
    }

    public Loginusers_T userautorisation(Loginusers_T user, Userinfo_T infodistante) throws RemoteException {
        Loginusers_T returnvalue = null;
        try {
            int cle2 = user.getUrcleunik();
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "login: " + user.getUrlogin() + "\npass: " + "'<Do not show>'");
            returnvalue = userManager.connectUser(user);
            returnvalue.setErreurcode(10000);
            this.managepool.renvObjectprurcleunik(user.getUrcleunik(), returnvalue.getUrlmcleunik(), returnvalue.getCle2()).setBuffer((HashServeurBuffer) socBuffer.get(new Integer(returnvalue.getSociete())));
//checkEntityBuffer(returnvalue.getUrsecleunik(),this.managepool.renvObjectprurcleunik(user.getUrcleunik(),returnvalue.getUrlmcleunik(),cle2).getBuffer());
            if (compteTree == null) {
                compteTree = new DecFatory().getBuffer2();
                Poolconnection tmpool = getConnectionAndCheck(returnvalue.getUrcleunik(), true);
                String sqlrequete = "select c.ce_cleunik,c.ce_num,tx.trate_traduction "
                        + " from compte c,traduction_compte tx"
                        + " where c.ce_cleunik=tx.ce_cleunik"
                        + " and tx.lmcleunik=1"
                        + " order by c.ce_num;";
                PreparedStatement pstmt = tmpool.getConuser().prepareStatement(sqlrequete);
                pstmt.execute();
                ResultSet result = pstmt.executeQuery();
                Hashtable tmp = null;
                while (result.next()) {
                    if (tmp == null)
                        tmp = new Hashtable();
                    Object[] tab = new Object[2];
                    tab[0] = new Long(result.getLong(2));
                    tmp.put(new Integer(result.getInt(1)), tab);
                }
                compteTree.setData(tmp, 1, 1);
            }
//  if(decision==null){
//    Poolconnection tmpool=getConnectionAndCheck(returnvalue.getUrcleunik(),true);
//  decision=new Decision(dectree);
//decision.initDecisionTree(tmpool,dectree);
//  }
// init_buffer(this.managepool.renvObjectprurcleunik(user.getUrcleunik(),returnvalue.getUrlmcleunik(),cle2).getBuffer());
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "erreur dans userautorisation : " + i);
            i.printStackTrace();
            throw (RemoteException) (new AuthorizationFailedException("Loggin failed: " + i.getMessage(), i));
        }
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, returnvalue.getErreurmessage());
        return returnvalue;
    }
//--------------------------------------------------------------------------------------------
//
//
/* private void checkEntityBuffer(int cleSociete,ServeurBuffer buffer){
synchronized(buffer){
if(entityBuffer!=null){
if(entityBuffer.get(new Integer(cleSociete))!=null){
buffer=(ServeurBuffer)entityBuffer.get(new Integer(cleSociete));
System.out.println("[*************]RECUPERATION D UN BUFFER EXISTANT");
}
}
else {
init_buffer(buffer);
addBufferToEntityBuffer(cleSociete,buffer);
System.out.println("[*************]INITIATION DES BUFFERS");
}
}
}*/
    public boolean userlogof(int urcleunik) throws RemoteException {
        int i;
        boolean returnvalue = false;
        Poolconnection tmp;
        String sqlRequete;
        synchronized (managepool) {
            i = managepool.renvindexprurcleunik(urcleunik);
            tmp = (Poolconnection) managepool.poolreturnobject(i);
            managepool.poolless(i);
        }
        sqlRequete = "update session set ssdateof= NOW() where snumerosession='" + tmp.getUrnumerosession() + "';";
        Transaction.execrequete(sqlRequete, tmp.getConuser());
        Transaction.rollback(tmp.getConuser());
        try {
            tmp.getConuser().close();
        } catch (SQLException e) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "erreur lors de la fermeture de la con dans userlogof " + e.getMessage() + " code " + e.getErrorCode());
        }
        return returnvalue;
    }

    public Loginusers_T[] returnusers(int numentite) throws RemoteException {
//userManager.getUserList(numentite);
        userlist = userManager.getUserList();
        return userlist;
    }

//--------------------------------------------------------------------------------------------
//
//
    public java.util.ArrayList renvcombofourncontact(int urcleunik, char plettre, int cas, int frcleunik) throws RemoteException {
        String sqlrequete = null;
        ArrayList tmpfourncon;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        switch (cas) {
            case 1:
// sqlrequete="select  frctcleunik,frctnom,frctprenom,frctgrpadminist,frctmail,frcttelephone from fournisseur_contact where frcleunik="+frcleunik+" order by frctnom;";
                sqlrequete = "select  frctcleunik,frctnom,frctprenom,frctgrpadminist from fournisseur_contact where frcleunik=" + frcleunik + " order by frctnom;";
                break;
            case 2:
// sqlrequete="select  frctcleunik,frctnom,frctprenom,frctgrpadminist,frctmail,frcttelephone from fournisseur_contact order by frctnom;";
                sqlrequete = "select  frctcleunik,frctprenom,frctnom,frctgrpadminist from fournisseur_contact where frcleunik=" + frcleunik + " order by frctnom;";
                break;
        }
        tmpfourncon = generecombostest(sqlrequete, tmpool.getConuser());
        return tmpfourncon;
    }
//--------------------------------------------------------------------------------------------
//
//
    private void test(ArrayList pays) {
        int i = pays.size();
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "taille du tableau" + i);
        for (int j = 0; j < i; j++) {
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "liste des pays" + pays.get(j));
        }
    }
//--------------------------------------------------------------------------------------------
//
//
    public ArrayList renvLangueSystem(int urcleunik, int urlmcleunik, int tmpint) throws RemoteException {
        ArrayList tmp = null;
        String sqlrequete = "select  lmcleunik,lmintitule from languesystem order by lmcleunik";
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        tmp = generecombostest(sqlrequete, tmpool.getConuser());
        return tmp;
    }

//*******************************************************************************************
// ! ! ! ! !
// Cette fonction est define dans l'interface et ne semble plus être utilisée
// Il pourrait être judicieux de la supprimer de celle ci.
// De plus, elle a du être modifiée pour supprimer les champs globaux de l'objet!!!
//
    public ArrayList renvComboTest(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas) throws RemoteException {
        return null;
    }

//*******************************************************************************************
//
//
    public String[] renvNomUserPourSession(Connection con, String numSessionCrea, String numSessionModif) {
        String sqlrequete = null;
        ResultSet tmpResult = null;
        ResultSet tmpResult2 = null;
        String[] returnValue = new String[2];
        int tmpNbrLigne = 0;
        int tmpNbrLigne2 = 0;
        sqlrequete = "Select u1.urnom,s1.snumerosession from user u1, session s1 where s1.urcleunik=u1.urcleunik and s1.snumerosession='" + numSessionCrea + "';";
        tmpResult = Transaction.execrequete(sqlrequete, con);
        sqlrequete = "Select u1.urnom,s1.snumerosession from user u1, session s1 where s1.urcleunik=u1.urcleunik and s1.snumerosession='" + numSessionModif + "';";
        tmpResult2 = Transaction.execrequete(sqlrequete, con);
        try {
            tmpResult.last();
            tmpResult2.last();
            tmpNbrLigne = tmpResult.getRow();
            tmpNbrLigne2 = tmpResult2.getRow();
            if (tmpNbrLigne != 0 && tmpNbrLigne2 != 0) {
                tmpResult.first();
                tmpResult2.first();
                returnValue[0] = tmpResult.getString(1);
                returnValue[0] = tmpResult2.getString(1);
            } else {
                returnValue[0] = "Aucun";
                returnValue[1] = "Aucun";
            }
        } catch (SQLException e) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "Erreur dans renvNomUserPourSession: " + e);
        }
        return returnValue;
    }

//________________________________________________________________________________________________________________________________________________________
    private void checkIfExistBeforeModify(Connection usercon, ArrayList traduction, int cas, int objectcleunik, Object[] defaultValue) {
        Gestionerreur_T tmperreur = null;
        String requete1;
        String requete2;
        String requete3;
        Object[] tmpobj;
        int index = 0;
        String requeteModify = null;
        String requeteInsert = null;
        boolean sw = false;
        switch (cas) {
            case COMBO_CODE_POST:
                requete1 = "select lmcleunik from traductioncodpostaux where cxcleunik=" + objectcleunik + " order by lmcleunik;";
                ResultSet traductionExistante = Transaction.execrequete3(requete1, usercon, tmperreur);
                try {
                    traductionExistante.beforeFirst();
                    while (traductionExistante.next()) {
                        for (int i = 0; i < traduction.size(); i++) {
                            tmpobj = (Object[]) traduction.get(i);
                            index = Integer.parseInt(tmpobj[0].toString());
                            if (traductionExistante.getInt(1) == index) {
                                requeteModify = "update traductioncodpostaux set txtraduction='" + tmpobj[1].toString() + "' where lmcleunik=" + index + " and cxcleunik=" + objectcleunik + ";";
                                Transaction.execrequete3(requeteModify, usercon, tmperreur);
                                sw = true;
                            }
                        }
                        if (!sw) {
                            requeteInsert = "insert into traductioncodpostaux values(" + objectcleunik + ",'" + defaultValue[0].toString() + "'," + index + ");";
                            Transaction.execrequete3(requeteInsert, usercon, tmperreur);
                            sw = false;
                        }
                    }
                } catch (SQLException e) {
                    Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Erreur dans checkIfExistBeforeModify code postaux" + e.getErrorCode() + "   " + e.getMessage());
                } catch (Exception e2) {
                    Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Erreur dans checkIfExistBeforeModify code postaux" + e2.getMessage());
                }
                break;
        }
    }

    private int genereId(String sqlrequete, Connection usercon) {
        Statement select = null;
        ResultSet tmpresult = null;
        int id = 0;
        try {
            select = usercon.createStatement();
//select.execute("BEGIN;");
            select.execute(sqlrequete);// Add your handling
            tmpresult = select.executeQuery("select LAST_INSERT_ID();");
            tmpresult.first();
            id = tmpresult.getInt(1);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "numero du fournisseur " + id);
            tmpresult.close();
        } catch (SQLException e) {
            try {
                select.execute("ROLLBACK;");
            } catch (SQLException e4) {
                Logger.getDefaultLogger().log(Logger.LOG_WARNING, "impossible d'effectuer le Rollback dans execrequeteinsert");
            }

        }
        return id;
    }

    private MessagTypeExchange connectdbcombo(String user, String pass) {
        MessagTypeExchange returnvalue = new MessagTypeExchange();
        String message;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            returnvalue.setCon(DriverManager.getConnection("jdbc:mysql://localhost:5000/Astratmp", user, pass));
            returnvalue.setErreurmessage("Connection etablie avec succès");
            returnvalue.setErreurcode(10000);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "connecter à la bd");
        } catch (ClassNotFoundException e0) {
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY, "--> ClassNotFoundException : " + e0);
            returnvalue.setErreurmessage("ClassNotFoundException :" + e0.getMessage());
            returnvalue.setErreurcode(0);
        } catch (SQLException e1) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "--> SQLException : " + e1);
            returnvalue.setErreurmessage("SQLException :" + e1.getMessage());
            returnvalue.setErreurcode(e1.getErrorCode());
        } catch (Exception e2) {
            Logger.getDefaultLogger().log(Logger.LOG_SECURITY, "--> Exception : " + e2);
            returnvalue.setErreurmessage("Exception :" + e2.getMessage());
            returnvalue.setErreurcode(0);
        }       // Add your handling code here:
        return returnvalue;
    }

    /**
     * renvoie une chaine avec la liste des users connectes
     * l'objet manage pool doit être synchronized
     */
    private String givemeusers() {
        String tmp2 = null;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "taille de la pool:" + managepool.poolgetsize());
        tmp2 = managepool.alreadylogusers();//genere une chaine sql qui reprend les id des user connecter
//System.out.println("taille de la pool:"+managepool.poolgetsize());
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "chaine:  " + tmp2);
        return tmp2;
    }

    private CompressArray generecombostest2(String Sqlrequete, Connection usercon) {
        ArrayList optionarray = null;
        int tmpcolcount;
        int tmpligncount;
        Object[] tmp;
        boolean check;
        CompressArray myArray;
        ResultSetMetaData tmpresultmeta;
        ResultSet tmpresult = Transaction.execrequete(Sqlrequete, usercon);
        myArray = new CompressArray();
        myArray.Compress_from_resulset(tmpresult);
        return myArray;

    }

    private CompressArray generecombostest(String Sqlrequete, Connection usercon) {
        ArrayList optionarray = null;
        int tmpcolcount;
        int tmpligncount;
        Object[] tmp;
        boolean check;
        CompressArray myArray;
        ResultSet tmpresult = Transaction.execrequete(Sqlrequete, usercon);
        myArray = new CompressArray();
        myArray.Compress_from_resulset(tmpresult);
        return myArray;

    }


    private CompressArray generecombos2(String Sqlrequete, int urcleunik) throws RemoteException//,ArrayList optionarray)
    {
        ArrayList optionarray = null;
        CompressArray myArray;
        int tmpcolcount;
        int tmpligncount;
        Object[] tmp;
        boolean check;
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        try {
            ResultSetMetaData tmpresultmeta;
//MessagTypeExchange tmp=new MessagTypeExchange();
//tmp=connectdbcombo("jerome","junior85");
//ResultSet tmpresult=Transaction.execrequete("select frreference1,frnom1,frtelephone,frfax,frmail from fournisseur",tmp.getCon());
            ResultSet tmpresult = Transaction.execrequete(Sqlrequete, tmpool.getConuser());
            myArray = new CompressArray();
            myArray.Compress_from_resulset(tmpresult);
            return myArray;
        } catch (Exception i) {
            Logger.getDefaultLogger().log(Logger.LOG_WARNING, "exception " + i);
        }
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "marre");
/*return optionarray;*/
        return null;

    }

/* ------ Gestion des signalitiques --------*/
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int TypObject, int cas) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.insertObjectPopup(objdp, urcleunik, TypObject, cas, tmpool);
    }

    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, int caecleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.renvIntitule(urlmcleunik, urcleunik, cas, typedesignalitique, caecleunik, tmpool);
    }

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.renvSignalitiques(urlmcleunik, urcleunik, cas, typedesignalitique, tmpool);
    }

    public ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        ServeurBuffer buf = tmpool.getBuffer();
        return signalitiques.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas, tmpool);
    }

    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int TypObject, int cas) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        return signalitiques.modifyObjectPopup(objdp, urcleunik, TypObject, cas, tmpool);
    }

    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, int comboConstante) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.chargeObjetCombo(objectCleunik, urcleunik, urlmcleunik, comboConstante, tmpool);
    }

    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, int comboConstante) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.ChargeObjectPopup(urlmcleunik, urcleunik, objcleunik, cas, comboConstante, tmpool);
    }

    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, int comboConstante) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        return signalitiques.ChargeObject(urlmcleunik, urcleunik, objcleunik, cas, comboConstante, tmpool);
    }

    private Document_T ChargeDefaultDocumentFourn(Connection usercon) {
        String sqlrequet = null;
        Gestionerreur_T tmperreur = new Gestionerreur_T();
        String sqlrequete = "SELECT frdtcleunik,frcleunik,frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev,snumerosessioncrea,snumerosessionmodif from fournisseur_documentdef;";
        ResultSet tmpresult = null;
        int tmpNbrLigne;
        tmpresult = Transaction.execrequete3(sqlrequete, usercon, tmperreur);
        Document_T tmpdoc = null;
        if (tmperreur.getErreurcode() == 10000) {
            try {
                tmpresult.last();
                tmpNbrLigne = tmpresult.getRow();
                if (tmpNbrLigne != 0) {
                    tmpresult.first();
                    tmpdoc = new Document_T(tmpresult.getInt(1), tmpresult.getInt(2), tmpresult.getDate(3), tmpresult.getDate(4), tmpresult.getInt(5), tmpresult.getInt(6), tmpresult.getInt(7), tmpresult.getInt(8), tmpresult.getInt(9), tmpresult.getString(10), tmpresult.getString(11), "", "");
                    tmpdoc.setErreurcode(tmperreur.getErreurcode());
                    tmpdoc.setErreurmessage(tmperreur.getErreurmessage());
                }
                tmpresult.close();
            } catch (SQLException e) {
                Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Erreur dans  ChargeCodePostaux: " + e);
            } catch (Exception e1) {
                Logger.getDefaultLogger().log(Logger.LOG_WARNING, "erreur dans chargecodepostaux :" + e1);
            }
        }
        return tmpdoc;
    }

    private Grpdecision_T ChargeDefaultGrpFourn(Connection usercon, int tecleunik) throws SQLException {
        return ((ManageGroupeDec) grprmi).selectDef(1, usercon, 0);
/*      String sqlrequet=null;
Gestionerreur_T tmperreur=new Gestionerreur_T();
String sqlrequete="SELECT * from groupedecisiondef where tecleunik="+tecleunik+";";
ResultSet tmpresult=null;
int tmpNbrLigne;
Grpdecision_T  tmpGrpDec=new Grpdecision_T();
tmpresult=Transaction.execrequete3(sqlrequete,usercon,tmperreur);
if(tmperreur.getErreurcode()==10000) {
try{
tmpresult.last();
tmpNbrLigne=tmpresult.getRow();
if(tmpNbrLigne!=0) {
tmpresult.first();

tmpGrpDec=new Grpdecision_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getInt(3),tmpresult.getInt(4),tmpresult.getInt(5), tmpresult.getDate(6),tmpresult.getDate(7),tmpresult.getInt(8),tmpresult.getInt(9),tmpresult.getInt(10),tmpresult.getInt(11),tmpresult.getInt(12),tmpresult.getFloat(13),tmpresult.getInt(14),tmpresult.getInt(15),tmpresult.getFloat(16),tmpresult.getInt(17),tmpresult.getFloat(18),tmpresult.getFloat(19),tmpresult.getFloat(20),tmpresult.getFloat(21),tmpresult.getFloat(22),tmpresult.getString(23),tmpresult.getString(24),tmpresult.getInt(25),tmpresult.getInt(26),tmpresult.getInt(27),tmpresult.getFloat(28),tmpresult.getInt(29),tmpresult.getInt(30),tmpresult.getInt(31),tmpresult.getInt(32),tmpresult.getInt(33),tmpresult.getInt(34),"","", 0, 0);
}
tmpresult.close();
}
catch(SQLException e){
Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Erreur dans  ChargeCodePostaux: "+e);
}
catch(Exception e1){
Logger.getDefaultLogger().log(Logger.LOG_WARNING,"erreur dans chargecodepostaux :"+e1);
}
}
return tmpGrpDec;
*/
    }

    public boolean isBufferOk(String reference, long timestamp, long currenttime, int urcleunik) throws RemoteException {
        long local = System.currentTimeMillis();
        Object tmpObj = null;
        Object dummyObject = new String("Dummy variable");
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        HashServeurBuffer buf = (HashServeurBuffer) tmpool.getBuffer();
        if (buf.isValid(reference)) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nreference ok :" + reference);
            tmpObj = buf.getValue(reference);
        } else
            return false;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, buf.toString());
        synchronized (tmpool.getBuffer()) {
            System.out.println("false");
            if (tmpObj == null) {
                System.out.println("[***************]BUFFER VIDE NULL]");
                return false;
            } else {
                if (tmpObj.equals(dummyObject)) {
                    System.out.println("[***************]BUFFER VIDE SIGNATURE OK]");
                    return false;
                } else {
                    CompressArray array = (CompressArray) tmpObj;
                    System.out.println("[++++++++++++++++]timestamps : serveur :" + array.getSignature() + "client " + timestamp);
                    if (timestamp == array.getSignature()) {
                        System.out.println("[***************]SIGNATURE OK]");
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nreference signatureok :" + reference);
                        return true;
                    } else {
                        System.out.println("[***************]SIGNATURE INCORRECT");
                        return false;
                    }
                }
            }
        }
    }

    private void intialiseHastableNombuffer() {
        Hashtable hash = new Hashtable();
        hash.put("1", "signCodePostalCas1");
        hash.put("1", "signCodePostalCas2");
        hash.put("1", "signDeviseCas1");
        hash.put("1", "signDeviseCas2");
        hash.put("1", "signPaysCas1");
        hash.put("1", "signPaysCas2");
        hash.put("1", "signLangueCas1");
        hash.put("1", "signLangueCas2");
        hash.put("1", "signLocaliteCas1");
        hash.put("1", "signLocaliteCas2");
        hash.put("1", "signTvaTypeCas1");
        hash.put("1", "signTvaTypeCas2");
        hash.put("1", "signTransportCas1");
        hash.put("1", "signTransportCas2");
        hash.put("1", "signLogementCas1");
        hash.put("1", "signLogementCas2");
        hash.put("1", "signTitrePersCas1");
        hash.put("1", "signTitrePersCas2");
        hash.put("1", "signTvaRegimeCas1");
        hash.put("1", "signTvaRegimeCas2");
        hash.put("1", "signDestinationCas1");
        hash.put("1", "signDestinationCas2");
        hash.put("1", "signCompteCas1");
        hash.put("1", "signCompteCas2");
        hash.put("1", "signCompagnieCas1");
        hash.put("1", "signCompagnieCas2");
        hash.put("1", "signFournisseurCas1");
        hash.put("1", "signFournisseurCas2");
        hash.put("1", "clientOrdByRef");
        hash.put("1", "clientOrdByName");
        hash.put("1", "clientOrdByNameWithAdress");
        hash.put("1", "signTypeProduitCas1");
        hash.put("1", "signTypeProduitCas2");
    }

    private void init_buffer(ServeurBuffer buf) {
        socBuffer = new Hashtable();
        for (int i = 0; i < userlist.length; i++) {
            Loginusers_T user = userlist[i];
            thebuffer = new HashServeurBuffer();
            socBuffer.put(new Integer(user.getSociete()), thebuffer);
            ServeurBuffer tmp;
            ServeurBuffer buffy; /*Contre les vampires*/
            tmp = new HashServeurBuffer();
            buffy = new HashServeurBuffer();
            tmp.setValue("codePostal", null);
            tmp.setValue("signCodePostalCas1", null);
            tmp.setValue("signCodePostalCas2", null);
            tmp.setValue("signCodePostalCas3", null);
            tmp.setValue("signCodePostalCas4", null);
            tmp.setValue("signCodePostalCas5", null);
            buffy.importDirectory("codePostalDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("devise", null);
            tmp.setValue("signDeviseCas1", null);
            tmp.setValue("signDeviseCas2", null);
            tmp.setValue("signDeviseCas3", null);
            tmp.setValue("signDeviseCas4", null);
            tmp.setValue("signDeviseCas5", null);
            buffy.importDirectory("deviseDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("pays", null);
            tmp.setValue("signPaysCas1", null);
            tmp.setValue("signPaysCas2", null);
            tmp.setValue("signPaysCas3", null);
            tmp.setValue("signPaysCas4", null);
            tmp.setValue("signPaysCas5", null);
            buffy.importDirectory("paysDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("langue", null);
            tmp.setValue("signLangueCas1", null);
            tmp.setValue("signLangueCas2", null);
            tmp.setValue("signLangueCas3", null);
            tmp.setValue("signLangueCas4", null);
            tmp.setValue("signLangueCas5", null);
            buffy.importDirectory("langueDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("localite", null);
            tmp.setValue("signLocaliteCas1", null);
            tmp.setValue("signLocaliteCas2", null);
            buffy.importDirectory("localiteDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("tvaType", null);
            tmp.setValue("signTvaTypeCas1", null);
            tmp.setValue("signTvaTypeCas2", null);
            buffy.importDirectory("tvaTypeDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("transport", null);
            tmp.setValue("signTransportCas1", null);
            tmp.setValue("signTransportCas2", null);
            tmp.setValue("signTransportCas3", null);
            tmp.setValue("signTransportCas4", null);
            tmp.setValue("signTransportCas5", null);
            buffy.importDirectory("transportDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("logement", null);
            tmp.setValue("signLogementCas1", null);
            tmp.setValue("signLogementCas2", null);
            tmp.setValue("signLogementCas3", null);
            tmp.setValue("signLogementCas4", null);
            tmp.setValue("signLogementCas5", null);
            buffy.importDirectory("logementDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("titrePers", null);
            tmp.setValue("signTitrePersCas1", null);
            tmp.setValue("signTitrePersCas2", null);
            tmp.setValue("signTitrePersCas3", null);
            tmp.setValue("signTitrePersCas4", null);
            tmp.setValue("signTitrePersCas5", null);
            buffy.importDirectory("titrePersDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("tvaRegime", null);
            tmp.setValue("signTvaRegimeCas1", null);
            tmp.setValue("signTvaRegimeCas2", null);
            tmp.setValue("signTvaRegimeCas3", null);
            tmp.setValue("signTvaRegimeCas4", null);
            tmp.setValue("signTvaRegimeCas5", null);
            buffy.importDirectory("tvaRegimeDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("destination", null);
            tmp.setValue("signDestinationCas0", null);
            tmp.setValue("signDestinationCas2", null);
            tmp.setValue("signDestinationCas3", null);
            tmp.setValue("signDestinationCas4", null);
            tmp.setValue("signDestinationCas5", null);
            buffy.importDirectory("destinationDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("compte", null);
            tmp.setValue("signCompteCas1", null);
            tmp.setValue("signCompteCas2", null);
            tmp.setValue("signCompteCas3", null);
            tmp.setValue("signCompteCas4", null);
            tmp.setValue("signCompteCas5", null);
            buffy.importDirectory("compteDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("compagnie", null);
            tmp.setValue("signCompagnieCas1", null);
            tmp.setValue("signCompagnieCas2", null);
            buffy.importDirectory("compagnieDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("nationalite", null);
            tmp.setValue("natCas1", null);
            buffy.importDirectory("natDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("embardebarq", null);
            tmp.setValue("signEmbarDebarqCas1", null);
            tmp.setValue("signEmbarDebarqCas2", null);
            tmp.setValue("signEmbarDebarqCas3", null);
            tmp.setValue("signEmbarDebarqCas4", null);
            tmp.setValue("signEmbarDebarqCas5", null);
            buffy.importDirectory("embarqDebarqDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("fournisseur", null);
            tmp.setValue("signFournisseurCas1", null);
            tmp.setValue("signFournisseurCas2", null);
            buffy.importDirectory("fournisseurDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("client", null);
            tmp.setValue("clientOrdByRef", null);
            tmp.setValue("clientOrdByName", null);
            tmp.setValue("clientOrdByNameWithAdress", null);
            tmp.setValue("clientOrdByRefEntWithAdress", null);
            tmp.setValue("clientOrdByNameEntWithAdress", null);
            thebuffer.importDirectory("clientDir", tmp);
            tmp = new HashServeurBuffer();
            tmp.setValue("typeProduit", null);
            tmp.setValue("signTypeProduitCas1", null);
            tmp.setValue("signTypeProduitCas2", null);
            buffy.importDirectory("ProduitDir", tmp);
            thebuffer.importDirectory("signaletiqueDir", buffy);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG, thebuffer.toString());
        }
    }

    public CompressArray renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
//srcastra.astra.sys.manipuleclient.chargeclient
        mRenvClient manipCli = new mRenvClient(cas, listeParam, urcleunik, lmcleunik, this.requeteClient, tmpool, null);
        return manipCli.renvclient();
    }

    public CompressArray renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik, String name) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
//srcastra.astra.sys.manipuleclient.chargeclient
        mRenvClient manipCli = new mRenvClient(cas, listeParam, urcleunik, lmcleunik, this.requeteClient, tmpool, name);
        return manipCli.renvclient();
    }

    public Object ChargeClient(int cas, int[] listeParam, int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
//srcastra.astra.sys.manipuleclient.chargeclient
        mRenvClient manipCli = new mRenvClient(cas, listeParam, urcleunik, lmcleunik, this.requeteClient, tmpool, null);
        return manipCli.chargeClient(this);
    }

    public int insertClient(int cas, int urcleunik, int[] param, Object objcli) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        InsertModClient manipclient = new InsertModClient(tmpool, this.getRequeteClient(), cas, this, false);
        return manipclient.InsertObject(objcli);
    }

    public void ModifyClient(int cas, int urcleunik, int[] param, Object objcli) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, false);
        InsertModClient manipclient = new InsertModClient(tmpool, this.getRequeteClient(), cas, this, false);
        manipclient.modifyObject(objcli);
    }

    public void deleteClient(int cas, int urcleunik, int[] param) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        DeleteClient delcli = new DeleteClient(tmpool, this.getRequeteClient(), cas);
        delcli.delete(param);
    }

    public void deleteSignaletique(String table, String nomcleunik, int urcleunik, int checkCode, int cleunik, int typeObjet) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
//Transaction.begin(tmpool.getConuser());
        if (checkCode == CHECK_FOR_TRANS_REMISE || checkCode == CHECK_FOR_LOGEMENT_REMISE) {
            ManipSegnaletique.renvServeurFailureLogementTransport(checkCode, cleunik, tmpool.getConuser());
            signalitiques.deleteSignaletique(cleunik, typeObjet, tmpool);
//ManipSegnaletique.deleteTransLog(cleunik,tmpool.getConuser(),tmpool.getBuffer(),checkCode);
        } else {
            if (typeObjet == astrainterface.COMBO_COMPAGNIE || typeObjet == astrainterface.COMBO_DESTINATION || typeObjet == astrainterface.COMBO_EMBARQDEBARQ && typeObjet == astrainterface.COMBO_FOURNPROD)
                signalitiques.deleteSignaletique(cleunik, typeObjet, tmpool);
            else {
                ManipSegnaletique.renvServeurFailuerException(checkCode, cleunik, nomcleunik, tmpool.getConuser());
                signalitiques.deleteSignaletique(cleunik, typeObjet, tmpool);
            }
//ManipSegnaletique.delete(table,nomcleunik,cleunik,tmpool.getConuser(),tmpool.getBuffer());
        }

    }

    public String[] getRequeteClient() {
        return this.requeteClient;
    }

    public Genere_Doc edition(int key, int objcleunik, int urcleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        Genere_Doc doc = new Genere_Doc(key, tmpool, objcleunik);
        return doc;
    }

    public srcastra.astra.sys.classetransfert.FournMemo_T renvFournisseurMemo(int frcleunik, int urcleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);

        String sqlrequet = null;
        Gestionerreur_T tmperreur = new Gestionerreur_T();
        String sqlrequete = "SELECT frcleunik, frmemo, snumerosessionmodif from fournisseur where frcleunik=" + frcleunik + ";";
        ResultSet tmpresult = null;
        int tmpNbrLigne;
        FournMemo_T tmpFournMemo = new FournMemo_T();

        tmpresult = Transaction.execrequete3(sqlrequete, tmpool.getConuser(), tmperreur);
        if (tmperreur.getErreurcode() == 10000) {
            try {
                tmpresult.last();
                tmpNbrLigne = tmpresult.getRow();
                if (tmpNbrLigne != 0) {
                    tmpresult.first();
                    tmpFournMemo = new FournMemo_T(tmpresult.getInt(1), tmpresult.getString(2), tmpresult.getString(3));
                }
                tmpresult.close();
            } catch (SQLException e) {
                Logger.getDefaultLogger().log(Logger.LOG_WARNING, "Erreur dans  ChargeCodePostaux: " + e);
            } catch (Exception e1) {
                Logger.getDefaultLogger().log(Logger.LOG_WARNING, "erreur dans chargecodepostaux :" + e1);
            }
        }

        return tmpFournMemo;

    }

    public FournisseurRmiInterface renvFournisseurRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        FournisseurRmiInterface fourn = null;
        if (fournisseurRmi == null) {
            fournisseurRmi = new FournisseurImplement(this, m_port);
//fourn=(FournisseurRmiInterface)FournisseurImplement.exportObject(fournisseurRmi);
        }
// fournisseurRmi.setTmpool(tmpool);
        return fournisseurRmi;
    }

    public DossierRmiInterface renvDossierRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (dossierRmi == null) {
            dossierRmi = new DossierImplement(this, m_port);
        }
        return dossierRmi;
    }

    public ListRmiInterface renvListRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (listrmi == null) {
            listrmi = new ListRmi(this, m_port);
        }
        return listrmi;
    }

    public GlobalRmiInterface renvCompteCentralRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (comptecentral == null) {
            comptecentral = new CompteCentralRmi(this, m_port);
        }
        return comptecentral;
    }

    public CompteRmiInterface renvCompte2RmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (compteRmi2 == null) {
            compteRmi2 = (CompteRmiInterface) renvCompteRmiObject(urCleunik);
        }
        return compteRmi2;
    }

    public GlobalRmiInterface renvPeriodeRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (periodeCompta == null) {
            periodeCompta = new PeriodeRmi(this, m_port);
        }
        return periodeCompta;
    }

    public UserEntiteRmiInterface renvUserEntiteRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (userentite == null) {
            userentite = new UserentitecompteRmi(this, m_port);
        }
        return userentite;
    }

    public SoldeRmiInterface renvSoldeRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (soldeCompta == null) {
            soldeCompta = new SoldeRmi(this, m_port);
        }
        return soldeCompta;
    }

    public GlobalRmiInterface renvTypePayementRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (typepaiement == null) {
            typepaiement = new TypePayementRmi(this, m_port);
        }
        return typepaiement;
    }

    public GlobalRmiInterface renvTraductionCompteRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (traductioncompte == null) {
            traductioncompte = new Traduction_compteRmi(this, m_port);
        }
        return traductioncompte;
    }

    public GlobalRmiInterface renvCaisseLibelleRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (caisseLibelle == null) {
            caisseLibelle = new CaisselibelleRmi(this, m_port);
        }
        return caisseLibelle;
    }

    public GlobalRmiInterface renvUserRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (userrmi == null) {
            userrmi = new UserRmi(this, m_port);
        }
        return userrmi;
    }

    public GlobalRmiInterface renvCompteRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (compteRmi == null) {
            compteRmi = new CompteRmi(this, m_port);
        }
        return compteRmi;
    }

    public GlobalRmiInterface renvEntiteRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (entite == null) {
            entite = new EntiteRmi(this, m_port);
        }
        return entite;
    }

    public ParamComptableInterface renvParamCompta(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (comptarmi == null) {
            comptarmi = new ParamComptable(this, m_port);
        }
        return comptarmi;
    }

    public ConfigRmiInterface renvConfigRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (configrmi == null) {
            configrmi = new ConfigRmiModule(this, m_port);
        }
        return configrmi;
    }

    public srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface renvGrpDecRmiObject(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        return grprmi;
    }

    private void createGrpRmiObject() throws RemoteException {
        grprmi = new srcastra.astra.sys.rmi.groupe_dec.ManageGroupeDec("", this, m_port);
    }

    private void genereRequeteClient() {
        requeteClient = new String[42];
        requeteClient[RENV_ALL_CLIENTS_ORD_BY_REF] =
                "SELECT c.cscleunik,c.csreference,c.csnom,tcx.txtraduction FROM "
                + "clients c,traductioncodpostaux tcx WHERE c.cxcleunik=tcx.cxcleunik "
                + "and tcx.lmcleunik=? ORDER BY c.csreference";
        requeteClient[RENV_ALL_CLIENTS_ORD_BY_NAME] =
                "SELECT c.cscleunik,c.csreference,c.csnom,tcx.txtraduction FROM "
                + "clients c,traductioncodpostaux tcx WHERE c.cxcleunik=tcx.cxcleunik "
                + "and tcx.lmcleunik=? ORDER BY c.csnom";
        requeteClient[RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS] =
                "SELECT c.cscleunik, c.csnom,c.csadresse FROM "
                + "clients c ORDER BY c.csnom";
        requeteClient[RENV_ENT_CLIENTS_ORD_BY_REF] =
                "SELECT c.cscleunik,c.csdatetimecrea,c.csdatetimemodi,c.csmailprincip,c.csfax,c.cstelephonep ,c.csnom,c.csreference,c.csadresse,c.cxcleunik,c.csadresse,t.txtraduction,co.cxcode  FROM clients c ,traductioncodpostaux t,codepostaux co WHERE c.cxcleunik=t.cxcleunik AND co.cxcleunik=c.cxcleunik AND t.lmcleunik= ? AND  c.eecleunik=? AND c.csnom LIKE(CONCAT(?,'%')) ORDER BY c.csreference;";
/*  "SELECT c.cscleunik,c.csreference,c.csnom,tcx.txtraduction FROM "
+"clients c,traductioncodpostaux tcx WHERE c.cxcleunik=tcx.cxcleunik "
+"and tcx.lmcleunik=? AND c.eecleunik=? ORDER BY c.csnom";*/
        requeteClient[RENV_ENT_CLIENTS_ORD_BY_NAME] =
                "SELECT c.cscleunik,c.csdatetimecrea,c.csdatetimemodi,c.csmailprincip,c.csfax,c.cstelephonep ,c.csnom,c.csreference,c.csadresse,c.cxcleunik,c.csadresse,t.txtraduction,co.cxcode,c.annuler  FROM clients c ,traductioncodpostaux t,codepostaux co WHERE c.cxcleunik=t.cxcleunik AND co.cxcleunik=c.cxcleunik AND t.lmcleunik= ? AND c.eecleunik=? AND c.csnom LIKE(CONCAT(?,'%')) ORDER BY c.csnom;";
/*"SELECT c.cscleunik,c.csreference,c.csnom,tcx.txtraduction FROM "
FournisseurRmiInterface
+"clients c,traductioncodpostaux tcx WHERE c.cxcleunik=tcx.cxcleunik "
+"and tcx.lmcleunik=? AND c.eecleunik=? ORDER BY c.csnom";*/
        requeteClient[CHARGE_SOUS_CLIENT] = "SELECT c.cscleunik,c.csdatetimecrea,c.csdatetimemodi,c.csmailprincip,c.csfax"
                + ",c.cstelephonep,c.csnom,c.csreference,c.cslocalite,c.snumerosessioncrea,"
                + "c.snumerosessionmodif FROM clients c"
                + " WHERE c.cscleunik=?";
        requeteClient[CHARGE_ALL_SOUS_CLIENT] = "SELECT c.cscleunik,c.csdatetimecrea,c.csdatetimemodi,c.csmailprincip,c.csfax,c.cstelephonep ,c.csnom,c.csreference,c.csadresse,c.cxcleunik,c.csadresse,t.txtraduction,co.cxcode  FROM clients c ,traductioncodpostaux t,codepostaux co WHERE c.cxcleunik=t.cxcleunik AND co.cxcleunik=c.cxcleunik AND t.lmcleunik= ? AND c.csnom LIKE(CONCAT(?,'%')) ORDER BY c.csnom;";
        requeteClient[INSERT_CLIENT] = "INSERT INTO clients(csgecleunik,eecleunik,csdatetimecrea,csdatetimemodi,"
                + "csreference,tscleunik,csnom,csadresse,cxcleunik,pyscleunik,csnom2,cstelephonep,"
                + "cstelephones,csfax,csgsm,csmailprincip,csmailsecond,cstvatype,cstvanum,cstvaregime,"
                + "csdatenaiss,cscodemailing,lecleunik,csbanque,cscartecredit,csanalytique,"
                + "cscodecotisateur,csdatecotisation,csmontcotisation,csbloque,csdelaipaiem,"
                + "snumerosessioncrea,snumerosessionmodif, cstvatype2,ce_cleunik2  ) values(?,?,NOW(),NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?"
                + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        requeteClient[CHARGE_CLIENT] =
//                1               2           3           4               5
                "SELECT c.cscleunik,c.csgecleunik,c.eecleunik,c.csdatetimecrea,c.csdatetimemodi,"
//              6               7       8       9           10              11      12
                + "c.csreference,c.tscleunik,c.csnom,c.csadresse,c.cxcleunik,c.pyscleunik,c.csnom2,"
//              12              13          14      15          16              17
                + "c.cstelephonep,c.cstelephones,c.csfax,c.csgsm,c.csmailprincip,c.csmailsecond,"
//              18          19          20              21          22              23
                + "c.cstvatype,c.cstvanum,c.cstvaregime,c.csdatenaiss,c.cscodemailing,c.lecleunik,"
//              24              25          26              27                  28
                + "c.csbanque,c.cscartecredit,c.csanalytique,c.cscodecotisateur,c.csdatecotisation,"
//          29
                + "c.csmontcotisation,c.csbloque,c.csdelaipaiem,c.snumerosessioncrea,c.snumerosessionmodif,"
                + "tp.pystraduction,tc.txtraduction,cp.cxcode,tri.tetraduction,trl.leabreviation,ti.tsintitule,"
                + "clients_groupe.csgenom,c.csdatecotisation,c.cstvatype2,c.ce_cleunik2     FROM clients c left join  clients_groupe on c.csgecleunik=clients_groupe.csgecleunik,"
                + "traductioncodpostaux tc,traductionpays tp,codepostaux cp,traductionintitule tri,langue trl, traductionlangues trl2"
                + ",traductiontitrepers ti WHERE tc.cxcleunik=c.cxcleunik AND c.cxcleunik=cp.cxcleunik AND tp.pyscleunik=c.pyscleunik "
                + "AND trl.lecleunik=c.lecleunik AND trl2.lmcleunik=? AND tri.tecleunik=c.cstvaregime AND tri.caecleunik=1 "
                + "AND tri.lmcleunik=? AND tc.lmcleunik=? AND tp.lmcleunik=? AND c.tscleunik=ti.tscleunik AND ti.lmcleunik=? "
                + "AND c.cscleunik=?";
        requeteClient[CHARGE_CLIENT_ALL_CLIENT] = "SELECT * FROM clients WHERE UPPER(csnom) LIKE(UPPER(?%)) ORDER BY cscleunik";
        requeteClient[INSERT_CLIENT_MULT_ADR] =
                "INSERT INTO clients_multadres(cscleunik,csmstype,csmsnom,csmsadresse,cxcleunik,csmslocalite,pyscleunik,"
                + "csmstelephone,csmsfax,csmsgsm,csmsmail,snumerosessioncrea,snumerosessionmodif,csmsdatetimecrea,csmsdatetimemodif)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
        requeteClient[CHARGE_CLIENT_MULT_ADR] =
                "SELECT ca.csmscleunik,ca.cscleunik,ca.csmstype,ca.csmsnom,ca.csmsadresse,ca.cxcleunik,ca.pyscleunik,"
                + "ca.csmstelephone,ca.csmsfax,ca.csmsgsm,ca.csmsmail,ca.snumerosessioncrea,ca.snumerosessionmodif,"
                + "ca.csmsdatetimecrea,ca.csmsdatetimemodif,tp.pystraduction,tc.txtraduction,cp.cxcode FROM clients_multadres ca,"
                + "codepostaux cp, traductioncodpostaux tc,traductionpays tp WHERE  ca.cxcleunik=cp.cxcleunik AND ca.cxcleunik=tc.cxcleunik "
                + "AND tc.lmcleunik=? AND tp.pyscleunik=ca.pyscleunik AND tp.lmcleunik=? AND ca.csmscleunik=?";
        requeteClient[RENV_CLIENT_MULT_ADR_ORD_BY_TYPE] =
                "SELECT ca.csmscleunik,ca.csmstype,ca.csmsnom,tc.txtraduction FROM clients_multadres ca,traductioncodpostaux tc "
                + "WHERE  ca.cxcleunik=tc.cxcleunik AND tc.lmcleunik=? AND ca.cscleunik=? ORDER BY ca.csmstype";
        requeteClient[RENV_CLIENT_MULT_ADR_ORD_BY_NAME] =
                "SELECT ca.csmscleunik,ca.csmsnom,ca.csmstype,tc.txtraduction FROM clients_multadres ca,traductioncodpostaux tc "
                + "WHERE  ca.cxcleunik=tc.cxcleunik AND tc.lmcleunik=? AND ca.cscleunik=? ORDER BY ca.csmsnom";
        requeteClient[INSERT_CLIENT_FEES] =
                "INSERT INTO clients_remises (csrspcdossier,csrspcannuel,csrsfees,cscleunik,ltcleunik,ttcleunik,frcleunik,frgtcleunik2 ,"
                + "snumerosessioncrea,snumerosessionmodif,csrsdatetimecrea,csrsdatetimemodif,csrscategorie ) VALUES(?,?,?,?,?,?,?,?,?,?,NOW(),NOW(),?)";
        requeteClient[RENV_CLIENT_FEES_ORDBY_REMISE] =
                "SELECT r.csrscleunik,r.csrspcannuel,r.csrsfees FROM clients_remises r WHERE r.cscleunik=? ORDER BY "
                + "r.csrspcannuel";
        requeteClient[RENV_CLIENT_FEES_ORDBY_FEES] =
                "SELECT r.csrscleunik,r.csrsfees,r.csrspcannuel FROM clients_remises r WHERE r.cscleunik=? ORDER BY r.csrsfees";
        requeteClient[CHARGE_CLIENT_FEES] =
                "SELECT r.csrscleunik,r.csrspcdossier,r.csrspcannuel,r.csrsfees,r.cscleunik,r.ltcleunik,r.ttcleunik,r.frcleunik,r.snumerosessioncrea,r.snumerosessionmodif,tl.ltintitule,tt.trattraduction,f.frnom1 FROM clients_remises r,traductiontransport tt,traductionlogement tl,fournisseur f WHERE r.csrscleunik=? AND r.ltcleunik=tl.ltcleunik AND tl.lmcleunik=? AND r.ttcleunik=tt.trtcleunik AND tt.lmcleunik=? AND r.frcleunik=f.frcleunik ORDER BY r.csrspcannuel";
        requeteClient[CHARGE_CLIENT_FOR_UPDATE] =
                "SELECT c.cscleunik,c.csgecleunik,c.eecleunik,c.csdatetimecrea,c.csdatetimemodi,"
                + "c.csreference,c.tscleunik,c.csnom,c.csadresse,c.cxcleunik,c.pyscleunik,c.csnom2,"
                + "c.cstelephonep,c.cstelephones,c.csfax,c.csgsm,c.csmailprincip,c.csmailsecond,"
                + "c.cstvatype,c.cstvanum,c.cstvaregime,c.csdatenaiss,c.cscodemailing,c.lecleunik,"
                + "c.csbanque,c.cscartecredit,c.csanalytique,c.cscodecotisateur,c.csdatecotisation,"
                + "c.csmontcotisation,c.csbloque,c.csdelaipaiem,c.snumerosessioncrea,c.snumerosessionmodif,"
                + "tp.pystraduction,tc.txtraduction,cp.cxcode,tri.tetraduction,trl.leabreviation,ti.tsintitule,"
                + "clients_groupe.csgenom,c.csdatecotisation,c.cstvatype2,c.ce_cleunik2     FROM clients c left join  clients_groupe on c.csgecleunik=clients_groupe.csgecleunik,"
                + "traductioncodpostaux tc,traductionpays tp,codepostaux cp,traductionintitule tri,langue trl, traductionlangues trl2"
                + ",traductiontitrepers ti WHERE tc.cxcleunik=c.cxcleunik AND c.cxcleunik=cp.cxcleunik AND tp.pyscleunik=c.pyscleunik "
                + "AND trl.lecleunik=c.lecleunik AND trl2.lmcleunik=? AND tri.tecleunik=c.cstvaregime AND tri.caecleunik=1 "
                + "AND tri.lmcleunik=? AND tc.lmcleunik=? AND tp.lmcleunik=? AND c.tscleunik=ti.tscleunik AND ti.lmcleunik=? "
                + "AND c.cscleunik=? FOR UPDATE";
/* "SELECT c.cscleunik,c.csgecleunik,c.eecleunik,c.csdatetimecrea,c.csdatetimemodi,"
+"c.csreference,c.tscleunik,c.csnom,c.csadresse,c.cxcleunik,c.pyscleunik,c.csnom2,"
+"c.cstelephonep,c.cstelephones,c.csfax,c.csgsm,c.csmailprincip,c.csmailsecond,"
+"c.cstvatype,c.cstvanum,c.cstvaregime,c.csdatenaiss,c.cscodemailing,c.lecleunik,"
+"c.csbanque,c.cscartecredit,c.csanalytique,c.cscodecotisateur,c.csdatecotisation,"
+"c.csmontcotisation,c.csbloque,c.csdelaipaiem,c.snumerosessioncrea,c.snumerosessionmodif,"
+"tp.pystraduction,tc.txtraduction,cp.cxcode,tri.tetraduction,trl.letraduction,ti.tsintitule,"
+"clients_groupe.csgenom   FROM clients c left join  clients_groupe on c.csgecleunik=clients_groupe.csgecleunik,"
+"traductioncodpostaux tc,traductionpays tp,codepostaux cp,traductionintitule tri,traductionlangues trl"
+",traductiontitrepers ti WHERE tc.cxcleunik=c.cxcleunik AND c.cxcleunik=cp.cxcleunik AND tp.pyscleunik=c.pyscleunik "
+"AND trl.lecleunik=c.lecleunik AND trl.lmcleunik=? AND tri.tecleunik=c.cstvaregime AND tri.caecleunik=1 "
+"AND tri.lmcleunik=? AND tc.lmcleunik=? AND tp.lmcleunik=? AND c.tscleunik=ti.tscleunik AND ti.lmcleunik=? "
+"AND c.cscleunik=? ";*/
        requeteClient[UPDATE_CLIENT_ADR] =
                "UPDATE clients_multadres set csmstype=?,csmsnom=?,csmsadresse=?,cxcleunik=?,csmslocalite=?"
                + ",pyscleunik=?,csmstelephone=?,"
                + "csmsfax=?,csmsgsm=?,csmsmail=?,snumerosessionmodif=?,csmsdatetimemodif=NOW() WHERE csmscleunik =?";
        requeteClient[CHARGE_CLIENT_MULT_ADR_FOR_UPDATE] =
                "SELECT ca.csmscleunik,ca.cscleunik,ca.csmstype,ca.csmsnom,ca.csmsadresse,ca.cxcleunik,ca.pyscleunik,"
                + "ca.csmstelephone,ca.csmsfax,ca.csmsgsm,ca.csmsmail,ca.snumerosessioncrea,ca.snumerosessionmodif,"
                + "ca.csmsdatetimecrea,ca.csmsdatetimemodif,tp.pystraduction,tc.txtraduction,cp.cxcode FROM "
                + "clients_multadres ca,codepostaux cp, traductioncodpostaux tc,traductionpays tp WHERE  ca.cxcleunik=cp.cxcleunik "
                + "AND ca.cxcleunik=tc.cxcleunik AND tc.lmcleunik=? AND tp.pyscleunik=ca.pyscleunik AND tp.lmcleunik=? AND "
                + "ca.csmscleunik=? FOR UPDATE";
        requeteClient[UPDATE_CLIENT] = "UPDATE clients set eecleunik=?,csdatetimemodi=NOW(),"
                + "csreference=?,tscleunik=?,csnom=?,csadresse=?,cxcleunik=?,pyscleunik=?,csnom2=?,cstelephonep=?,"
                + "cstelephones=?,csfax=?,csgsm=?,csmailprincip=?,csmailsecond=?,cstvatype=?,cstvanum=?,cstvaregime=?,"
                + "csdatenaiss=?,cscodemailing=?,lecleunik=?,csbanque=?,cscartecredit=?,csanalytique=?,"
                + "cscodecotisateur=?,csdatecotisation=?,csmontcotisation=?,csbloque=?,csdelaipaiem=?,"
                + "snumerosessionmodif=?,csgecleunik=?, cstvatype2=?,ce_cleunik2 =?  WHERE cscleunik=?";
        requeteClient[CHARGE_CLIENT_FEES_FOR_UPDATE] =
                "SELECT r.csrscleunik,r.csrspcdossier,r.csrspcannuel,r.csrsfees,r.cscleunik,r.ltcleunik,r.ttcleunik,r.frcleunik,"
                + "r.snumerosessioncrea,r.snumerosessionmodif,tl.ltintitule,tt.trattraduction,f.frnom1 FROM clients_remises r,"
                + "traductiontransport tt,traductionlogement tl,fournisseur f WHERE r.csrscleunik=? AND r.ltcleunik=tl.ltcleunik "
                + "AND tl.lmcleunik=? AND r.ttcleunik=tt.trtcleunik AND tt.lmcleunik=? AND r.frcleunik=f.frcleunik ORDER BY "
                + "r.csrspcannuel FOR UPDATE";
        requeteClient[UPDATE_CLIENT_FEES] =
                "UPDATE clients_remises set csrspcdossier=?,csrspcannuel=?,csrsfees=?,cscleunik=?,ltcleunik=?,ttcleunik=?,frcleunik=?, frgtcleunik2=?"
                + ",snumerosessionmodif=?,csrsdatetimemodif=NOW() WHERE csrscleunik=?";
        requeteClient[CHARGE_CLIENT_MEMO] =
                "SELECT cscleunik,csmemo,csdatetimecrea,csdatetimemodi,snumerosessioncrea,snumerosessionmodif FROM clients WHERE "
                + "cscleunik=?";
        requeteClient[CHARGE_CLIENT_MEMO_FOR_UPDATE] =
                "SELECT cscleunik,csmemo,csdatetimecrea,csdatetimemodi,snumerosessioncrea,snumerosessionmodif FROM clients WHERE "
                + "cscleunik=? FOR UPDATE";
        requeteClient[UPDATE_CLIENT_MEMO] =
                "UPDATE clients SET csmemo=? WHERE cscleunik=?";
        requeteClient[INSERT_GROUPEMENT] =
                "INSERT INTO clients_groupe(csgenom,snumerosessioncrea,snumerosessionmodif,csgedatetimecrea,csgedatetimemodif) "
                + "VALUES(?,?,?,NOW(),NOW())";
        requeteClient[RENV_GROUPEMENT] =
                "SELECT * FROM clients_groupe";
        requeteClient[ADD_GROUPEMENT_TO_CLIENT] =
                "UPDATE clients set csgecleunik=? WHERE cscleunik=?";
        requeteClient[UPDATE_GROUPEMENT] =
                "UPDATE clients_groupe set csgenom=?,snumerosessionmodif=?,csgedatetimemodif=NOW() WHERE csgecleunik=?";
        requeteClient[CHARGE_GROUPEMENT_FOR_UPDATE] =
                "SELECT * FROM clients_groupe WHERE csgecleunik=? FOR UPDATE";
        requeteClient[DELETE_CLIENT] =
                "DELETE FROM clients WHERE cscleunik=?";
        requeteClient[DELETE_CLIENT_FEES] =
                "DELETE FROM clients_remises  WHERE csrscleunik=?";
        requeteClient[DELETE_CLIENT_MULTI_AD] =
                "DELETE FROM clients_multadres WHERE csmscleunik=?";
        requeteClient[DELETE_GROUPEMENT] =
                "DELETE FROM clients_groupe WHERE csgecleunik=?";
        requeteClient[SELECT_COMPTE] =
                "SELECT ce_cleunik,ce_num,ce_cat FROM compte";


    }

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, boolean x) throws RemoteException {
        return null;
    }

    public java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, boolean x) throws RemoteException {
        return null;
    }

    public Object chargeAllclient(int cas, int[] listeParam, int urcleunik, int lmcleunik, String param, int param2) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
//srcastra.astra.sys.manipuleclient.chargeclient
        mRenvClient manipCli = new mRenvClient(cas, listeParam, urcleunik, lmcleunik, this.requeteClient, tmpool, null);
        return manipCli.chargeAllclient(cas, this, param, param2);

    }
/* private void addBufferToEntityBuffer(int cleEntite,ServeurBuffer buff){
if(entityBuffer==null)
entityBuffer=new Hashtable();
entityBuffer.put(new Integer(cleEntite),buff);*/

//  }

    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, String serveurSigne, boolean correctBuffer) throws RemoteException {
        return null;
    }

    public java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, String serveurSigne, boolean check) throws RemoteException {
        return null;
    }

    public ArrayList getTransacState(int societe) throws RemoteException, ServeurSqlFailure {
        ArrayList array = new ArrayList();
        try {
            for (int i = 0; i < managepool.getPool().size(); i++) {
                Poolconnection tmpool = (Poolconnection) managepool.getPool().get(i);
                if (tmpool.getUrsociete() == societe) {
                    Object[] tmp = new Object[2];
                    tmp[0] = tmpool.getUsername();
                    tmp[1] = new Integer(tmpool.getConuser().getTransactionIsolation());
                    System.out.println("user login " + tmpool.getUsername() + " transaction level " + tmpool.getConuser().getTransactionIsolation());
                    array.add(tmp);
                }
            }
            return array;
        } catch (SQLException sn) {
// Transaction.rollback(tmpool.getConuser());
            sn.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(sn);
            sqe.setErrorcode(sn.getErrorCode());
            throw sqe;
        }
    }

    public Object workWithDecision(Object obj, int urcleunik, int typeAction, java.util.ArrayList data, int id2, int my_type, long timestamp, int typeDecision) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        Hashtable table = (Hashtable) decisionTable.get(new Integer(tmpool.getUrsociete()));
        AbstractDecision decision = (AbstractDecision) table.get(new Integer(typeDecision));
        try {
//  if(decision==null)
//    decision=new srcastra.astra.sys.configuration.Decision(dectree);
            if (typeAction == srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar.ACT_INSERT) {
                return decision.insert(null, tmpool, data, id2, my_type);
            } else if (typeAction == srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar.ACT_READ) {
                return decision.select(tmpool, timestamp);
            } else if (typeAction == srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar.ACT_MODIFY) {
                return decision.modify(null, tmpool, data);
            } else if (typeAction == srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar.DO_DELETE) {
                return decision.delete(id2, tmpool, my_type);
            }
            return null;
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            sn.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(sn);
            sqe.setErrorcode(sn.getErrorCode());
            throw sqe;
        }
    }


    /**
     * Getter for property compteTree.
     *
     * @return Value of property compteTree.
     */
    public srcastra.astra.gui.modules.aidedesicion.AbstractBuffer getCompteTree() {
        return compteTree;
    }

    public String getCompte(int idcompte, int urcleunik) {

        try {
            Compte compte = (Compte) renvCompteRmiObject(urcleunik).get(urcleunik, idcompte, 0);
            return new Long(compte.getCe_num()).toString();
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            se.printStackTrace();
        } catch (java.rmi.RemoteException rn) {
            rn.printStackTrace();
        }
        return null;
    }

    public java.util.ArrayList getTvaListe(int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        try {
            return TvaTree.tvaTab(tmpool, lmcleunik);
        } catch (SQLException sn) {
            sn.printStackTrace();
            return null;
        }
    }

    /**
     * Getter for property config.
     *
     * @return Value of property config.
     */
    public srcastra.astra.sys.classetransfert.configuration.AbstractConfig_T getConfig() {
        return config;
    }

    /**
     * Setter for property config.
     *
     * @param config New value of property config.
     */
    public void setConfig(srcastra.astra.sys.classetransfert.configuration.AbstractConfig_T config) {
        this.config = config;
    }

    /**
     * Getter for property PayementSyn.
     *
     * @return Value of property PayementSyn.
     */
    public java.lang.Object getPayementSyn() {
        if (PayementSyn == null)
            PayementSyn = new Object();
        return PayementSyn;
    }

    /**
     * Setter for property PayementSyn.
     *
     * @param PayementSyn New value of property PayementSyn.
     */
    public void setPayementSyn(java.lang.Object PayementSyn) {
        this.PayementSyn = PayementSyn;
    }

    /**
     * Getter for property SoldeSyn.
     *
     * @return Value of property SoldeSyn.
     */
    public java.lang.Object getSoldeSyn() {
        if (SoldeSyn == null)
            SoldeSyn = new Object();
        return SoldeSyn;
    }

    /**
     * Setter for property SoldeSyn.
     *
     * @param SoldeSyn New value of property SoldeSyn.
     */
    public void setSoldeSyn(java.lang.Object SoldeSyn) {
        this.SoldeSyn = SoldeSyn;
    }

    /**
     * Getter for property TransactSyn.
     *
     * @return Value of property TransactSyn.
     */
    public java.lang.Object getTransactSyn() {
        if (TransactSyn == null)
            TransactSyn = new Object();
        return TransactSyn;
    }

    /**
     * Setter for property TransactSyn.
     *
     * @param TransactSyn New value of property TransactSyn.
     */
    public void setTransactSyn(java.lang.Object TransactSyn) {
        this.TransactSyn = TransactSyn;
    }

    /**
     * Getter for property configrmi.
     *
     * @return Value of property configrmi.
     */
    public srcastra.astra.sys.rmi.ConfigRmiInterface getConfigrmi() {
        return configrmi;
    }

    /**
     * Setter for property configrmi.
     *
     * @param configrmi New value of property configrmi.
     */
    public void setConfigrmi(srcastra.astra.sys.rmi.ConfigRmiInterface configrmi) {
        this.configrmi = configrmi;
    }

    /**
     * Getter for property compteRmi2.
     *
     * @return Value of property compteRmi2.
     */
    public srcastra.astra.sys.rmi.CompteRmiInterface getCompteRmi2() {
        return compteRmi2;
    }

    /**
     * Setter for property compteRmi2.
     *
     * @param compteRmi2 New value of property compteRmi2.
     */
    public void setCompteRmi2(srcastra.astra.sys.rmi.CompteRmiInterface compteRmi2) {
        this.compteRmi2 = compteRmi2;
    }

    /**
     * Getter for property comptecentral.
     *
     * @return Value of property comptecentral.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getComptecentral() {
        return comptecentral;
    }

    /**
     * Setter for property comptecentral.
     *
     * @param comptecentral New value of property comptecentral.
     */
    public void setComptecentral(srcastra.astra.sys.rmi.GlobalRmiInterface comptecentral) {
        this.comptecentral = comptecentral;
    }

    /**
     * Getter for property userrmi.
     *
     * @return Value of property userrmi.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getUserrmi() {
        return userrmi;
    }

    /**
     * Setter for property userrmi.
     *
     * @param userrmi New value of property userrmi.
     */
    public void setUserrmi(srcastra.astra.sys.rmi.GlobalRmiInterface userrmi) {
        this.userrmi = userrmi;
    }

    /**
     * Getter for property soldeCompta.
     *
     * @return Value of property soldeCompta.
     */
    public srcastra.astra.sys.rmi.SoldeRmiInterface getSoldeCompta() {
        return soldeCompta;
    }

    /**
     * Setter for property soldeCompta.
     *
     * @param soldeCompta New value of property soldeCompta.
     */
    public void setSoldeCompta(srcastra.astra.sys.rmi.SoldeRmiInterface soldeCompta) {
        this.soldeCompta = soldeCompta;
    }

    /**
     * Getter for property grprmi.
     *
     * @return Value of property grprmi.
     */
    public srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface getGrprmi() {
        return grprmi;
    }

    /**
     * Setter for property grprmi.
     *
     * @param grprmi New value of property grprmi.
     */
    public void setGrprmi(srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface grprmi) {
        this.grprmi = grprmi;
    }

    /**
     * Getter for property traductioncompte.
     *
     * @return Value of property traductioncompte.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getTraductioncompte() {
        return traductioncompte;
    }

    /**
     * Setter for property traductioncompte.
     *
     * @param traductioncompte New value of property traductioncompte.
     */
    public void setTraductioncompte(srcastra.astra.sys.rmi.GlobalRmiInterface traductioncompte) {
        this.traductioncompte = traductioncompte;
    }

    /**
     * Getter for property compteRmi.
     *
     * @return Value of property compteRmi.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getCompteRmi() {
        return compteRmi;
    }

    /**
     * Setter for property compteRmi.
     *
     * @param compteRmi New value of property compteRmi.
     */
    public void setCompteRmi(srcastra.astra.sys.rmi.GlobalRmiInterface compteRmi) {
        this.compteRmi = compteRmi;
    }

    /**
     * Getter for property entite.
     *
     * @return Value of property entite.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getEntite() {
        return entite;
    }

    /**
     * Setter for property entite.
     *
     * @param entite New value of property entite.
     */
    public void setEntite(srcastra.astra.sys.rmi.GlobalRmiInterface entite) {
        this.entite = entite;
    }

    /**
     * Getter for property dossierRmi.
     *
     * @return Value of property dossierRmi.
     */
    public srcastra.astra.sys.rmi.DossierImplement getDossierRmi() {
        return dossierRmi;
    }

    /**
     * Setter for property dossierRmi.
     *
     * @param dossierRmi New value of property dossierRmi.
     */
    public void setDossierRmi(srcastra.astra.sys.rmi.DossierImplement dossierRmi) {
        this.dossierRmi = dossierRmi;
    }

    /**
     * Getter for property periodeCompta.
     *
     * @return Value of property periodeCompta.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getPeriodeCompta() {
        return periodeCompta;
    }

    /**
     * Setter for property periodeCompta.
     *
     * @param periodeCompta New value of property periodeCompta.
     */
    public void setPeriodeCompta(srcastra.astra.sys.rmi.GlobalRmiInterface periodeCompta) {
        this.periodeCompta = periodeCompta;
    }

    /**
     * Getter for property comptarmi.
     *
     * @return Value of property comptarmi.
     */
    public srcastra.astra.sys.rmi.ParamComptableInterface getComptarmi() {
        return comptarmi;
    }

    /**
     * Setter for property comptarmi.
     *
     * @param comptarmi New value of property comptarmi.
     */
    public void setComptarmi(srcastra.astra.sys.rmi.ParamComptableInterface comptarmi) {
        this.comptarmi = comptarmi;
    }

    /**
     * Getter for property userentite.
     *
     * @return Value of property userentite.
     */
    public srcastra.astra.sys.rmi.UserEntiteRmiInterface getUserentite() {
        return userentite;
    }

    /**
     * Setter for property userentite.
     *
     * @param userentite New value of property userentite.
     */
    public void setUserentite(srcastra.astra.sys.rmi.UserEntiteRmiInterface userentite) {
        this.userentite = userentite;
    }

    /**
     * Getter for property typepaiement.
     *
     * @return Value of property typepaiement.
     */
    public srcastra.astra.sys.rmi.GlobalRmiInterface getTypepaiement() {
        return typepaiement;
    }

    /**
     * Setter for property typepaiement.
     *
     * @param typepaiement New value of property typepaiement.
     */
    public void setTypepaiement(srcastra.astra.sys.rmi.GlobalRmiInterface typepaiement) {
        this.typepaiement = typepaiement;
    }

    /**
     * Getter for property fournisseurRmi.
     *
     * @return Value of property fournisseurRmi.
     */
    public srcastra.astra.sys.rmi.FournisseurImplement getFournisseurRmi() {
        return fournisseurRmi;
    }

    /**
     * Setter for property fournisseurRmi.
     *
     * @param fournisseurRmi New value of property fournisseurRmi.
     */
    public void setFournisseurRmi(srcastra.astra.sys.rmi.FournisseurImplement fournisseurRmi) {

        this.fournisseurRmi = fournisseurRmi;
    }

    public void importClient(int urcleunik, ArrayList array) throws RemoteException, ServeurSqlFailure, srcastra.astra.sys.importastra.EntityNotFoundException {
        Poolconnection tmpool = getConnectionAndCheck(urcleunik, true);
        try {
            srcastra.astra.sys.importastra.ClientAstra clientAstra = new srcastra.astra.sys.importastra.ClientAstra(array, tmpool, this, null);
            clientAstra.creatTmpClient();
            ArrayList arraycli = clientAstra.importClient2(tmpool);
            Transaction.begin(tmpool.getConuser());
            System.out.println("debut de l'insertion des clients");
            new InsertModClient(tmpool, this.requeteClient, INSERT_CLIENT, this, true).insertCLientArray(arraycli);
            clientAstra.resetCodePostaux(tmpool);
            Transaction.commit(tmpool.getConuser());
        } catch (SQLException sn) {
            Transaction.rollback(tmpool.getConuser());
            sn.printStackTrace();
            ServeurSqlFailure se = new ServeurSqlFailure();
            se.setSqlException(sn);
            throw se;
        }
    }

    /**
     * Getter for property clientSyn.
     *
     * @return Value of property clientSyn.
     */
    public java.lang.Object getClientSyn() {
        return clientSyn;
    }

    /**
     * Setter for property clientSyn.
     *
     * @param clientSyn New value of property clientSyn.
     */
    public void setClientSyn(java.lang.Object clientSyn) {
        this.clientSyn = clientSyn;
    }

    /**
     * Getter for property numdosSyn.
     *
     * @return Value of property numdosSyn.
     */
    public java.lang.Object getNumdosSyn() {
        return numdosSyn;
    }

    /**
     * Setter for property numdosSyn.
     *
     * @param numdosSyn New va ue of property numdosSyn.
     */
    public void setNumdosSyn(java.lang.Object numdosSyn) {
        this.numdosSyn = numdosSyn;
    }

    /**
     * Getter for property host.
     *
     * @return Value of property host.
     */
    public java.lang.String getHost() {
        return host;
    }

    /**
     * Setter for property host.
     *
     * @param host New value of property host.
     */
    public void setHost(java.lang.String host) {
        this.host = host;
    }

    /**
     * Getter for property m_centrum.
     *
     * @return Value of property m_centrum.
     */
    public java.lang.String getM_centrum() {
        return m_centrum;
    }

    /**
     * Setter for property m_centrum.
     *
     * @param m_centrum New value of property m_centrum.
     */
    public void setM_centrum(java.lang.String m_centrum) {
        this.m_centrum = m_centrum;
    }

    public String getVersion() throws RemoteException {
        return "1.13";
    }

    public srcastra.astra.sys.btn.business.Btn getBtnServeur(int urCleunik) throws RemoteException {
        Poolconnection tmpool = getConnectionAndCheck(urCleunik, true);
        if (btnServeur == null) {
            btnServeur = new srcastra.astra.sys.btn.business.BtnImplement(this, m_port);
        }
        return btnServeur;
    }


    /**
     * Setter for property compteTree.
     *
     * @param compteTree New value of property compteTree.
     */


//**********************************************************************************************
//
//Champs de la classe
    private ArrayList usercon;
    private ManagePoolconnection managepool;
    private String[] requeteClient;
    private UsersManager userManager;
    private SignalitiqueManager signalitiques;
    private FournisseurImplement fournisseurRmi;
    private DossierImplement dossierRmi;
    private Hashtable socBuffer;
    private Hashtable socket;
    private AbstractBuffer dectree;
    private AbstractBuffer dectreeMemo;
    private AbstractBuffer compteTree;
    private GrpGroupDecRmiInterface grprmi;
    private ParamComptableInterface comptarmi;
    private HashServeurBuffer thebuffer;
    private ConfigRmiInterface configrmi;
    private GlobalRmiInterface userrmi;
    private GlobalRmiInterface entite;
    private Hashtable decisionTable;
    private AbstractConfig_T config;
    private GlobalRmiInterface compteRmi;
    private GlobalRmiInterface comptecentral;
    private GlobalRmiInterface traductioncompte;
    private GlobalRmiInterface typepaiement;
    private GlobalRmiInterface caisseLibelle;
    private GlobalRmiInterface periodeCompta;
    private SoldeRmiInterface soldeCompta;
    private Object PayementSyn;
    private Object SoldeSyn;
    private Object TransactSyn;
    private UserEntiteRmiInterface userentite;
    private CompteRmiInterface compteRmi2;
    private ListRmiInterface listrmi;
    private Object clientSyn = new Object();
    private Object numdosSyn = new Object();
    Loginusers_T[] userlist;
    srcastra.astra.sys.btn.business.Btn btnServeur;
}

