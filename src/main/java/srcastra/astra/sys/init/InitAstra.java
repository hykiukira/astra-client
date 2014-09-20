/*
 * InitAstra.java
 *
 * Created on 16 octobre 2004, 16:49
 */

package srcastra.astra.sys.init;
import java.sql.*;
/**
 *
 * @author  Administrateur
 */
public class InitAstra {
    
    /** Creates a new instance of InitAstra */
    public InitAstra(Connection con) throws SQLException{
        if(!checkConfig(con)){
            System.out.println("No Astra checkConfig checkConfig");
            insertEntite(con);
            newCOnfig(con);
            newUser(con);
        }
        else{
            System.out.println("Astra config ok");
        }
        
    }
     public void insertEntite(Connection con) throws SQLException{
       
        String requete="INSERT into entite(eenom,eeadresse,eetelephonep,eetelephones,eefax,eemail,eeactif,eedatetimecrea,eedatetimemodi,lmcleunik,cxcleunik,secleunik,eeabrev,eecompteur,eetva,eecomptebancaire,eecleunik ) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?)";
        PreparedStatement pstmt=con.prepareStatement(requete);
        pstmt.setString(1,"New_entite");
        pstmt.setString(2,"");
        pstmt.setString(3,"");
        pstmt.setString(4,"");
        pstmt.setString(5,"");
        pstmt.setString(6,"");
        pstmt.setInt(7,1);
        pstmt.setString(8,"NOW()");
        pstmt.setString(9,"NOW()");
        pstmt.setInt(10,1);
        pstmt.setInt(11,1);
        pstmt.setInt(12,1);
        pstmt.setString(13,"NE");
        pstmt.setLong(14,1);
        pstmt.setString(15,"");
        pstmt.setString(16,"");
        pstmt.setInt(17,1);
        pstmt.execute();
       
}
 private boolean checkConfig(Connection con) throws SQLException{
    String check="SELECT * FROM configuration";
    PreparedStatement pstmt=con.prepareStatement(check);
    ResultSet result=pstmt.executeQuery();
    return result.next();
    
}
public void newCOnfig(Connection con)throws SQLException{   
 
 
 //                                         1               2               3           4                   5               6               7               8               9               10              11              12              13              14              15                      16              17                  18              19              20              21              22              23              24              25          26          27          28              29          30                  31                          32                      33                          34                      35                      36                              37                      38          39              40              41          42          43      44              45              46               1  2   3               4           5           6       7 8  9  10   11  12  13   14   15  16   17    18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39  40   41   42   43   44    45   46   
 String requete="INSERT INTO `configuration` ( `conf_cleunik` , `eecleunik` , `bdc_bdc_x` , `bdc_bdc_y` , `bdc_bdc_cli_x` , `bdc_bdc_cli_y` , `bdc_bloc_g` , `bdc_bloc_d` , `nb_lette_liste` , `bdc_texte` , `cn_frdos_tva` , `cn_frdos` , `cn_mulituser` , `cn_multibur` , `cn_fraisdossiertva` , `cn_comptefrais` , `cn_cpttaxdest` , `cn_cpttaxcomp` , `cn_cptfees` , `cn_cptrem` , `cn_cpttaxloc` , `cn_frinclus` , `factureauto` , `facturenumber` , `bdcauto` , `bdcnumber` , `ncauto` , `nccnumber` , `nombrebloc` , `factureresum` , `affichebloclegalfacture` , `passagerVisibleBDC` , `detailProduitVisibleBDC` , `detailPrixVisibleBDC` , `passagerVisibleFact` , `detailProduitVisibleFact` , `detailPrixVisibleFact` , `bloquegupx` , `bloquegupy` , `bloquedupx` , `bloquedupy` , `corpx` , `corpy` , `topmargin` , `bottomargin` , `factfourn` ) VALUES (1, 1, 1.64285714, 4.43571429, 9.52857143, 4.17857143, 1, 1, 1, '', 288, 0, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                                                    
//INSERT INTO `configuration` ( `conf_cleunik` , `eecleunik` , `bdc_bdc_x` , `bdc_bdc_y` , `bdc_bdc_cli_x` , `bdc_bdc_cli_y` , `bdc_bloc_g` , `bdc_bloc_d` , `nb_lette_liste` , `bdc_texte` , `cn_frdos_tva` , `cn_frdos` , `cn_mulituser` , `cn_multibur` , `cn_fraisdossiertva` , `cn_comptefrais` , `cn_cpttaxdest` , `cn_cpttaxcomp` , `cn_cptfees` , `cn_cptrem` , `cn_cpttaxloc` , `cn_frinclus` , `factureauto` , `facturenumber` , `bdcauto` , `bdcnumber` , `ncauto` , `nccnumber` , `nombrebloc` , `factureresum` , `affichebloclegalfacture` , `passagerVisibleBDC` , `detailProduitVisibleBDC` , `detailPrixVisibleBDC` , `passagerVisibleFact` , `detailProduitVisibleFact` , `detailPrixVisibleFact` , `bloquegupx` , `bloquegupy` , `bloquedupx` , `bloquedupy` , `corpx` , `corpy` , `topmargin` , `bottomargin` , `factfourn` ) VALUES ((1, 1, 1.64285714, 4.43571429, 9.52857143, 4.17857143, 1, 1, 1, '', 288, 0, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

 
 PreparedStatement pstmt=con.prepareStatement(requete);
 pstmt.execute();
}
private boolean checkUser(Connection con) throws SQLException{
    String check="SELECT * FROM user WHERE urnom='ADMIN' AND urprenom='ADMIN'";
    PreparedStatement pstmt=con.prepareStatement(check);
    ResultSet result=pstmt.executeQuery();
    return result.next();
    
}
private void newUser(Connection con) throws SQLException{      
    
    
    String requete="INSERT INTO `user` ( `urnom` ,`urprenom` , `droits` , `urlogin` , `urcode` , `uradresse` , `cxcleunik`  , `urmailbur` , `urmailprive` , `uridentificationsecu` , `urtelephonebd` , `urtelephoneprive` , `uranalytique` , `uridlogo` , `urdatetimecrea` , `urdatetimemodif` , `snumerosessioncrea` , `snumerosessionmodif` , `eecleunik` , `lmcleunik` , `urgsm` , `annuler` , `urdroit`,`urcleunik` ) "
    +"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?);";
    PreparedStatement pstmt=con.prepareStatement(requete);
    pstmt.setString(1,"ADMIN");
    pstmt.setString(2,"ADMIN");
    pstmt.setString(3,"0101");
    pstmt.setString(4,"ADMIN");
    pstmt.setString(5,"AD");
    pstmt.setString(6,"");
    pstmt.setInt(7,1);
    pstmt.setString(8,"");
    pstmt.setString(9,"");
    pstmt.setString(10,"");
    pstmt.setString(11,"");
    pstmt.setString(12,"");
    pstmt.setInt(13,1);
    pstmt.setInt(14,1);
    pstmt.setString(15,"NOW()");
    pstmt.setString(16,"NOW()");
    pstmt.setInt(17,1);
    pstmt.setInt(18,1);
    pstmt.setInt(19,1);
    pstmt.setInt(20,1);
    pstmt.setString(21,"");
    pstmt.setInt(22,0);
    pstmt.setInt(23,1);
    pstmt.setInt(24,1);
    pstmt.execute();
}
    
}
