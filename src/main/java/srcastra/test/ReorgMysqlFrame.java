/*
 * ReorgMysqlFrame.java
 *
 * Created on 10 octobre 2003, 13:51
 */
package srcastra.test;
import java.util.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author  Thomas
 */
public class ReorgMysqlFrame extends javax.swing.JFrame {
    
    /** Creates new form ReorgMysqlFrame */
      File file=null;
      File file2=null;
      BufferedReader in=null; 
      BufferedReader in2=null; 
      Connection con=null;
      String centrum="installcentrum";
      String astra="install";
      //String centrum="AstraCentrum";
      //String astra="Astra";
      String ip="localhost";
    public ReorgMysqlFrame() { 
        initComponents();
        try{
//        file=new File("C:\\Documents and Settings\\administrateur\\Bureau\\delete\\table.txt");
        file2=new File("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\tabletodelete.txt");
  //      in = new BufferedReader(new FileReader(file));
        in2 = new BufferedReader(new FileReader(file2));
      
        con=connectDb("root","XkLm2000",astra,ip,3306);  
      
        } catch(SQLException e1) { 
                e1.printStackTrace(); 
           /// Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> SQLException : " + e1) ;
            /*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
            returnerreur.setErreurcode(e1.getErrorCode());*/
            } 
         catch(IOException in) { 
                in.printStackTrace();
           /// Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> SQLException : " + e1) ;
            /*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
            returnerreur.setErreurcode(e1.getErrorCode());*/
            }   
    }        
     public void deleteTable2() {
        try{
        String str="";
        String central="DELETE FROM comptecentralisateur where cour_cleunik>7";
        String journaux="DELETE FROM journcompta WHERE jota_cleunik>24";
        String compte="DELETE FROM compte where ce_cleunik>721";
        PreparedStatement pstmt=null;
        while((str=in2.readLine())!=null){
            String requete="DELETE  FROM "+str+";";        
            pstmt=con.prepareStatement(requete);
            pstmt.execute();      
        }
        pstmt=con.prepareStatement(central);
        pstmt.execute();
        pstmt=con.prepareStatement(journaux);
        pstmt.execute();
        pstmt=con.prepareStatement(compte);
        pstmt.execute();
        in2.close();
        }catch(SQLException sn){
          sn.printStackTrace();          
        }
         catch(IOException In) { 
                In.printStackTrace();               
           /// Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> SQLException : " + e1) ;            
            /*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
            returnerreur.setErreurcode(e1.getErrorCode());*/
            }   
        }
      public void deleteTable() {
        try{
        String str="";
        String central="DELETE FROM comptecentralisateur where cour_cleunik>7";
        String journaux="DELETE FROM journcompta WHERE jota_cleunik>24";
        String compte="DELETE FROM compte where ce_cleunik>721";
        PreparedStatement pstmt=null;
        while((str=in.readLine())!=null){
            String requete="DELETE  FROM "+str+";";           
            pstmt=con.prepareStatement(requete);
            pstmt.execute();      
        }
        pstmt=con.prepareStatement(central);
        pstmt.execute();
        pstmt=con.prepareStatement(journaux);
        pstmt.execute();
        pstmt=con.prepareStatement(compte);
        pstmt.execute();
        in.close();
        }catch(SQLException sn){
          sn.printStackTrace();          
        }
         catch(IOException In) { 
                In.printStackTrace();
            }   
        }
     private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try {
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)
                Class.forName(jdbcDriverClassName) ;
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");
               // tmpcon.close();
                //tmpcon = DriverManager.getConnection("jdbc:"+jdbcName+"://195.162.199.148:5000/Astratmp",user,pass);
            /*returnerreur.setErreurmessage("Connection etablie avec succès");
            returnerreur.setErreurcode(10000);*/
               // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"connecter à la bd");
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();                         
            }           
            catch(Exception e2) {
                e2.printStackTrace();            
            }
            return tmpcon;
        }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButton2 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jButton1.setText("vider table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton1);

        jTextField1.setText("testthom");
        jPanel1.add(jTextField1);

        jPanel1.add(jLayeredPane1);

        jButton2.setText("vider2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-300)/2, 400, 300);
    }//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       deleteTable2();      
       deleteCentrum();// Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
private void deleteCentrum(){
    try{
    Connection con=con=connectDb("root","XkLm2000",centrum,ip,3306);  
    String requete="DELETE FROM societe";
    PreparedStatement pstmt=con.prepareStatement(requete);
    pstmt.execute();
    String requeteUser="DELETE FROM user";
    pstmt=con.prepareStatement(requeteUser);
    pstmt.execute();
     }catch(SQLException sn){
          sn.printStackTrace();   
        
        }
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       deleteTable();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new ReorgMysqlFrame().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
