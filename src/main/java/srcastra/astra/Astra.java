/*


 * Astra.java


 * Created on 6 mars 2002, 9:53


 */

package srcastra.astra;

import srcastra.astra.sys.rmi.*;

import javax.swing.JOptionPane;

import srcastra.astra.gui.modules.mainScreenModule.*;

import java.io.*;
import java.sql.*;
import java.security.*;
import javax.crypto.*;


/**
 * @author S�bastien
 */


public class Astra {

    public static final int DEBUG = srcastra.astra.gui.sys.ErreurScreenLibrary.DEBUG_MODE_ON;
    protected srcastra.astra.sys.rmi.astrainterface serveur;
    private java.util.Locale locale;
    DossierMainScreenModule parent;
    String serveurname;
    String m_port;
    String version;
    String local;
    String keyAg = "";


    static String serverIp;
    private String localDb;

    public static String getIp() {
        return serverIp;
    }

    /**
     * Creates new Astra
     */

    public Astra(String[] args) {
        System.out.println(this.getClass().getName());
        //Chargement de L'UIManager
        version = "1.5";
        m_port = "8000";
        setUpProgramParams(args);
        this.serveurname = "homeinstall";
        srcastra.astra.gui.sys.Plaf.setPaf(true);
        locale = new java.util.Locale("en", "");
        String path = srcastra.astra.sys.ManageDirectory.testIpFile("config");
        File file2;
        file2 = new File(path + File.separator + "newPathAstra");
        if (!file2.exists()) {
            File file = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
            System.out.println(file);
            String[] children = file.list();
            for (int i = 0; i < children.length; i++) {
                if (new File(file + "\\" + children[i]).isFile() && children[i].endsWith(".lnk")) {
                    String link = children[i];
                    link = link.replaceAll(".lnk", "");
                    link = link.toLowerCase();

                    if (link.startsWith("astra")) {
                        File f = new File(file + "\\" + children[i]);
                        f.delete();
                        System.out.println(link);
                    }

                }
                //System.out.println(children[i]);

            }

            try {

                file2.createNewFile();
            } catch (Exception e) {
            }
            file = file.getParentFile();
            String rep1 = "\\http";
            String rep2 = "\\https";
            String rep3 = "\\indirect";
            String path1 = "\\Application Data\\Sun\\Java\\Deployment\\cache\\javaws";
            String path2 = "\\Application Data\\Sun\\Java\\Deployment\\javaws\\cache";
            System.out.println(file.toString() + "\\Application Data\\Sun\\Java\\Deployment\\cache\\javaws");

            File files = new File(srcastra.astra.sys.ManageDirectory.testIpFile("config") + File.separator + "version");

            files.delete();


            File del = new File(file.toString() + path1 + rep1);
            System.out.println(del.toString());

            this.deleteDir(del);

            del = new File(file.toString() + path1 + rep2);
            this.deleteDir(del);

            del = new File(file.toString() + path2 + rep1);
            this.deleteDir(del);

            del = new File(file.toString() + path2 + rep2);
            this.deleteDir(del);

            del = new File(file.toString() + path2 + rep3);
            this.deleteDir(del);

            try {

                Runtime.getRuntime().exec("javaws http://www.perforinco.com/Download/release/install/astra2.jnlp");
                //del.delete();
            } catch (Exception e) {
            }

            System.exit(0);

        }

        //if (verifVersion())
        verifConfig();
        /*else
        {
         JOptionPane.showMessageDialog(null, "The version "+version+" of astra is available");
         try{
           //String url="http://www.google.be";
         //Runtime.getRuntime().exec("cmd.exe /c start " + url);
         }catch(Exception e){
             
             
         }
         System.exit(0); 
        }*/

        //  connectServeur();
        //  proceedLogin();
    }

    private void setUpProgramParams(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("Invalid param numbers");
        this.local = args[0];
        if ("1".equals(local) && args.length < 3) {
            throw new IllegalArgumentException("When local is set to 1, third argument needs to " +
                    "be setup with user db ip");
        }
        if (args.length > 1){
            keyAg = args[1];
        }
        if (args.length > 2)
            this.localDb = args[2];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        srcastra.astra.sys.Configuration.initConfig();

        // args=new String[]{"serveur","8000"};

        new Astra(args);
    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }


    private boolean verifVersion() {

        boolean retour = true;

        try {
            String jdbcDriverClassName = "org.gjt.mm.mysql.Driver";
            if (jdbcDriverClassName != null)
                Class.forName(jdbcDriverClassName);
            String url = "";
            if (local.equals("1"))
                url = "jdbc:mysql://" + this.localDb + ":3306/AstraConnect";
            else
                url = "jdbc:mysql://ooohtml.no-ip.info:3306/AstraConnect";

            String user = "agence";
            String password = "keyagence3164978520";
            String actif = "";
            Connection con = null;
            boolean oki = false;
            try {
                con = DriverManager.getConnection(url, user, password);
                oki = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!oki) {
                    //url="jdbc:mysql://192.168.1.63:3306/AstraConnect";
                    System.out.println("je passe par ici");
                    try {
                        url = "jdbc:mysql://ooohtml.dyndns.org:3306/astraconnect";


                        con = DriverManager.getConnection(url, user, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error param connection");
                        System.exit(0);
                    }
                }
            }

            System.out.println(url);

            String queryString = "select verClient from ParamVersion";
            PreparedStatement stmt = con.prepareStatement(queryString);

            ResultSet rs = stmt.executeQuery();

            rs.next();
            String tmp = rs.getString(1);
            if (!(tmp.equals(version)))
                retour = false;

            version = tmp;
        } catch (Exception e) {


            e.printStackTrace();
        }

        return retour;
    }

    private void verifConfig() {
        String ip = "", port = "", alias = "";
        int cpt = 0;

        try {
            Object key = null;
            //    File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
            //   file=file.getParentFile();
            String path = srcastra.astra.sys.ManageDirectory.testIpFile("config");
            String files = path + File.separator + "astra";
            System.out.println("[********]files " + files);
            File file2 = new File(files);
            Object key2 = null;
            if (file2.exists()) {
                System.out.println("[********]file existe");
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file2));
                key2 = in.readObject();
                in.close();
            } else {
                System.out.println("[********]file dont existe");

                //System.out.println("file "+file .toURI().toString());
                key2 = javax.swing.JOptionPane.showInputDialog("Cl� Agence ?");
                //  ip2 = javax.swing.JOptionPane.showInputDialog("IP du serveur ?");
                file2.createNewFile();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file2));
                out.writeObject(key2);
                out.close();

            }

            String version;
            String detailVersion;


            String jdbcDriverClassName = "org.gjt.mm.mysql.Driver";

            if (jdbcDriverClassName != null)

                Class.forName(jdbcDriverClassName);


            String url = "";

            if (local.equals("1"))
                url = "jdbc:mysql://"+this.localDb+":3306/AstraConnect";
            else
                url = "jdbc:mysql://ns3093828.ovh.net:3306/AstraConnect";

            // fin de modif
            String user = "agence";
            String password = "keyagence3164978520";
            String actif = "";
            Connection con = null;
            boolean oki = false;
            try {
                con = DriverManager.getConnection(url, user, password);
                oki = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!oki) {
                    //url="jdbc:mysql://192.168.1.63:3306/AstraConnect";
                    System.out.println("je passe par ici");
                    try {

                        url = "jdbc:mysql://ooohtml.dyndns.org:3306/astraconnect";

                        con = DriverManager.getConnection(url, user, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error param connection");
                        System.exit(0);
                    }
                }
            }

            System.out.println(url);
            String queryString = "select decode(Ip,?), decode(Port,?), decode(alias,?), actif from ParamConnection where cleAgence like md5(old_password(?))";
            PreparedStatement stmt = con.prepareStatement(queryString);
            if (keyAg.equals("")) {
                stmt.setString(1, (String) key2);
                stmt.setString(2, (String) key2);
                stmt.setString(3, (String) key2);
                stmt.setString(4, (String) key2);
            } else {
                stmt.setString(1, keyAg);
                stmt.setString(2, keyAg);
                stmt.setString(3, keyAg);
                stmt.setString(4, keyAg);
            }

            ResultSet rs = stmt.executeQuery();


            System.out.println(rs.getFetchSize());

            while (rs.next()) {
                cpt++;
                ip = rs.getString(1);
                port = rs.getString(2);
                alias = rs.getString(3);
                actif = rs.getString(4);
            }

            stmt = con.prepareStatement("select * from ParamVersion");

            rs = stmt.executeQuery();

            rs.next();

            version = rs.getString(1);
            detailVersion = "Francais" + "\n\n" + rs.getString(3) + "\n\n" + "Nederlands\n\n" + rs.getString(4);

            rs.close(); // Fermeture
            stmt.close();
            con.close();

            path = srcastra.astra.sys.ManageDirectory.testIpFile("config");
            files = path + File.separator + "version";

            file2 = new File(files);
            Object vers = "";
            if (file2.exists()) {
                System.out.println("[********]file existe");
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file2));
                vers = in.readObject();
                in.close();
            }

            if (!vers.equals(version)) {

                srcastra.astra.gui.MAJ fen = new srcastra.astra.gui.MAJ(new java.awt.Frame(), true, detailVersion, version, file2);

                fen.show();

            }

            /*   file2.createNewFile();
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file2));
            out.writeObject(version);
            
            out.close();*/


            if (actif.equals("0")) {
                //JOptionPane.showMessageDialog(null, "Astra is bloqued : contact perforinco");
                System.exit(0);
            }


            if (cpt == 1) {
                connectServeur(ip, alias, port);
                proceedLogin(version);
            } else {
                JOptionPane.showMessageDialog(null, "Error Key Agence");
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private void connectServeur(String ip, String alias, String port) {
        try {
            //Object ip = null;
            //    File file=new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString());
            //   file=file.getParentFile();
            //String path=srcastra.astra.sys.ManageDirectory.testIpFile("config");
            //String files=path+File.separator+"astra";
            //System.out.println("[********]files "+files);
            //File file2=new File(files);
            //Object ip2=null;
            /*if(file2.exists()){
              System.out.println("[********]file existe");
              ObjectInputStream in=new ObjectInputStream(new FileInputStream(file2));
              ip2=in.readObject();
              in.close();
            }
            else{
                System.out.println("[********]file dont existe");

                //System.out.println("file "+file .toURI().toString());
                ip2 = javax.swing.JOptionPane.showInputDialog( "IP du serveur ?");
              //  ip2 = javax.swing.JOptionPane.showInputDialog("IP du serveur ?");
                file2.createNewFile();
                ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file2));
                out.writeObject(ip2);
                out.close();

            }*/

            //   if (ip == null || ip.toString().equals("")) {
            //     ip = "192.168.1.53";
            // }
            this.serverIp = ip;
            m_port = port;
            serveurname = alias;

            //System.out.println("[*********]IP2   "+ip2);
            RmiConnection rc = new RmiConnection(ip, m_port);
            rc.setVersion("1.6");
            rc.setRmiObjectName(serveurname);
            if (!rc.connect()) {
                JOptionPane.showMessageDialog(null, "Impossible de se connecter au serveur");
                System.exit(0);
            }
            this.serveur = new BufferedClient(rc.getServeur());
            System.out.println("r�ception du serveur ok");

        } catch (Exception e) {
            e.printStackTrace();

        }
        //this.serveur = rc.getServeur();
    }

    private void proceedLogin(String version) {
        new srcastra.astra.gui.LoginFrame(new javax.swing.JFrame(), true, serveur, locale, true, null, version,this.local,this.localDb).show();
    }
}