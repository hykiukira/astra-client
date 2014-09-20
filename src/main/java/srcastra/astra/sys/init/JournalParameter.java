/*
 * JournalParameter.java
 *
 * Created on 19 octobre 2004, 23:32
 */

package srcastra.astra.sys.init;
import java.sql.*;
import srcastra.astra.sys.rmi.*;
import java.rmi.*;
import srcastra.astra.sys.rmi.Exception.*;
/**
 *
 * @author  Administrateur
 */
public class JournalParameter {
    astraimplement m_serveur;
    /** Creates a new instance of JournalParameter */
    public JournalParameter(astraimplement m_serveur) {
        this.m_serveur=m_serveur;
    }
    private int getExercice(Connection con) throws SQLException{
        String requete="SELECT exle_cleunik FROM exercicecomptable WHERE exle_courant='O'";
        PreparedStatement pstmt=con.prepareStatement(requete);
        int exerciceCleunik=0;
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
            exerciceCleunik=result.getInt(1);
        }
        return exerciceCleunik;
        
    }
    public void updateJournalTable(int urcleunik,Connection con)throws SQLException{
        try{
        int exercice=getExercice(con);
        
        String requete="UPDATE journcompta SET exle_cleunik=?,jota_numdoc='100000'";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setInt(1,exercice);
        pstmt.execute();
        String monomono="UPDATE journcompta SET eecleunik=1 WHERE jota_cleunik!=22";
        String monomono2="UPDATE journcompta SET eecleunik=0 WHERE jota_cleunik=22";
        String monomulti="UPDATE journcompta  SET eecleunik=1";
        String multimulti_mono="UPDATE journcompta SET eecleunik=0";
        srcastra.astra.sys.classetransfert.configuration.AbstractConfig_T config=m_serveur.renvConfigRmiObject(urcleunik).selectConfig(urcleunik);
        String toexecute="";
        if(config.getMultibureaux()==1){
            execStatement(multimulti_mono,con);
        }
        else{
            if(config.getCaisseparutilisateur()==1){
                execStatement(monomulti,con);
            }
            else{
                execStatement(monomono,con);
                execStatement(monomono2,con);
            }
        }
        }catch(ServeurSqlFailure se){
            
        
        }catch(RemoteException rn){
            
        }
    }
    private void execStatement(String requete,Connection con) throws SQLException{
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.execute();
    }
    
    
}
