/* * ErreurScreenLibrary.java * * Created on 2 avril 2002, 12:20 */package srcastra.astra.gui.sys;import java.io.*;import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;import srcastra.astra.sys.classetransfert.Loginusers_T;/** * * @author  Sébastien * @version 1.0 */public class ErreurScreenLibrary implements srcastra.astra.sys.rmi.utils.CheckInterface {    public static final int DEBUG_MODE_ON = 1;    public static final int DEBUG_MODE_OFF = 0;    public static final String REMOTE_EXCEPTION = "000";    public static final String MISSING_RESSOURCE_EXCEPTION = "001";    public static final String NULL_POINTER_EXCEPTION = "002";    public static final String EXCEPTION = "003";    public static final String SERVEUR_SQL_FAILURE = "004";    public ErreurScreenLibrary() {    }        public static int displayErreur(java.awt.Component parent, String ErrorType, int DebugMode, Exception exception,Loginusers_T currentUser) {        // Normal Mode        if (DebugMode == DEBUG_MODE_OFF) {        }        // Debug Mode        if (DebugMode == DEBUG_MODE_ON) {            return displayErreurInDebugMode(parent, ErrorType, exception,currentUser);        }return -1;    }       public static void displayErreur(java.awt.Component parent, String ErrorType, int DebugMode, Exception exception) {        // Normal Mode        if (DebugMode == DEBUG_MODE_OFF) {        }        // Debug Mode        if (DebugMode == DEBUG_MODE_ON) {            if(exception instanceof ServeurSqlFailure)                {                  if(((ServeurSqlFailure)exception).sqd!=null)  {                        displayErreurInDebugMode(parent, ErrorType, ((ServeurSqlFailure)exception).sqd);                }                else{                    displayErreurInDebugMode(parent, ErrorType, exception);                }         }         else{                    displayErreurInDebugMode(parent, ErrorType, exception);                }        }    }         private static int displayErreurInDebugMode(java.awt.Component parent, String ErrorType, Exception exception,Loginusers_T currentUser) {        ServeurSqlFailure seq=null;        String[] tabrep;        if(ErrorType.equals("004"))        {              seq=(ServeurSqlFailure)exception;        }        if(exception instanceof ServeurSqlFailure){            if(((ServeurSqlFailure)exception).errorcode==5200){ //numero provenant des la classe srcastra.astra.sys.compta.SOMME                new MessageManager().showMessageDialog(parent,"caisse_transfert","caisse_transfert_title",currentUser);                                return 0;                }            else  if(((ServeurSqlFailure)exception).errorcode==5201){                new MessageManager().showMessageDialog(parent,"Modif_compte","Modif_compte_titel",currentUser);                                return 0;                }                  else  if(((ServeurSqlFailure)exception).errorcode==5202){                new MessageManager().showMessageDialog(parent,"new_compte","new_compte_titel",currentUser);                                return 0;                }               }             if(seq!=null && seq.errorcode==1205){            javax.swing.JOptionPane.showMessageDialog(parent,java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("en_modif"));        }else if(seq!=null && seq.errorcode==-100){            int rep=javax.swing.JOptionPane.showConfirmDialog(parent,java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("dos_modif"),java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("dos_modif_titre"),javax.swing.JOptionPane.YES_NO_OPTION);            return rep;        }else if(seq!=null && seq.errorcode==CHECK_WRONG){            String[] ret=seq.getMessagePerso();            tabrep=new String[2];            tabrep[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString(ret[0])+"\n";            tabrep[1]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("not_allow");            javax.swing.JOptionPane.showMessageDialog(parent,tabrep[0]+ret[1]+tabrep[1]);                   }        else if(seq!=null && (seq.errorcode==CHECK_FOR_TRANS_REMISE||seq.errorcode==CHECK_FOR_LOGEMENT_REMISE)){            String[] ret=seq.getMessagePerso();            tabrep=new String[2];            tabrep[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString(ret[0])+"\n";            tabrep[1]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("not_allow");            javax.swing.JOptionPane.showMessageDialog(parent,tabrep[0]+ret[1]+tabrep[1]);                   }        else if(seq!=null && seq.errorcode==CHECK_WRONG_BOTH_CLI_FOURN){            String[] ret=seq.getMessagePerso();            tabrep=new String[4];            tabrep[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString(ret[0])+"\n";            tabrep[1]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString(ret[3])+"\n";            tabrep[2]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString(ret[1])+"\n";            tabrep[3]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("not_allow");            javax.swing.JOptionPane.showMessageDialog(parent,tabrep[0]+tabrep[1]+ret[2]+tabrep[2]+ret[4]+tabrep[3]);                   }        else if(seq!=null && seq.errorcode==120){            String[] ret=seq.getMessagePerso();            tabrep=new String[2];            tabrep[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("ele_dossier")+"\n";            tabrep[1]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", currentUser.getLangage()).getString("not_allow");            javax.swing.JOptionPane.showMessageDialog(parent,tabrep[0]+tabrep[1]);                   }        else if(seq!=null && seq.errorcode==1062){            String[] tmp=seq.getMessagePerso();            String affiche="";            for(int i=0;i<tmp.length;i++){                affiche=affiche+tmp[i]+"\n";            }             javax.swing.JOptionPane.showMessageDialog(parent,affiche);          }        else if(seq!=null)        {            if(seq.sqd!=null)                displayErreurInDebugMode(parent, ErrorType, seq.sqd);             else                displayErreurInDebugMode(parent, ErrorType, exception);                         }        else{         displayErreurInDebugMode(parent, ErrorType, exception);         }        return -1;     }    private static void displayErreurInDebugMode(java.awt.Component parent, String ErrorType, Exception exception) {        // textare               javax.swing.JTextArea ta = new javax.swing.JTextArea();        String t="";        try        {                StringWriter sw = new StringWriter();                        PrintWriter printw = new PrintWriter(sw);            exception.printStackTrace(printw);            printw.close();            t=sw.toString();                   }        catch (Exception e)        {            t="De plus l'Exception suivante s'est produite lors de la tentative de gestion de l'erreur:\n";            t=t+e+"\n";        }        ta.setEditable(false);        //if(exception.toString()!=null)        ta.setText(exception.toString()+"\n"+t);        ta.setForeground(java.awt.Color.green);        ta.setBackground(java.awt.Color.black);                // Recherche du titre au sein du bunddle        String titre;        try {            titre = java.util.ResourceBundle.getBundle("srcastra/astra/locale/ErreurScreenLibrary").getString(REMOTE_EXCEPTION);        }        catch (java.util.MissingResourceException mre) {            titre = "Error !";            ta.setText(ta.getText() + "Attention : " + mre.toString());        }                javax.swing.JScrollPane jp = new javax.swing.JScrollPane();        jp.setViewportView(ta);        jp.setPreferredSize(new java.awt.Dimension(500,400));                javax.swing.JOptionPane.showMessageDialog(parent, jp, titre, javax.swing.JOptionPane.OK_OPTION);        String path=srcastra.astra.sys.ManageDirectory.testDirectory("log");            try {                String file=path+"\\log.txt";               // java.io.File file=new java.io.File();                BufferedWriter out = new BufferedWriter(new FileWriter(file, true));                out.write(t);                out.close();            } catch (IOException e) {                e.printStackTrace();            }        //System.exit(0);    }        public class ErrorPane extends javax.swing.JPanel {            }        /** Getter for property currentUser.     * @return Value of property currentUser.     */        /** Getter for property userLocal.     * @return Value of property userLocal.     */       }