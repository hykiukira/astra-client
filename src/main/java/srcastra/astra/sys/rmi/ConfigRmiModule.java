/*

 * ConfigRmiModule.java

 *

 * Created on 5 mars 2003, 8:56

 */



package srcastra.astra.sys.rmi;

import srcastra.astra.sys.rmi.Exception.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.sys.rmi.utils.*;

import java.sql.*;

import srcastra.astra.sys.Transaction;

import srcastra.astra.sys.rmi.socketfactory.*; 



/**

 *

 * @author  Thomas

 */

public class ConfigRmiModule  extends java.rmi.server.UnicastRemoteObject implements ConfigRmiInterface{

    

    /** Creates a new instance of ConfigRmiModule */

    astraimplement m_serveur;

    public ConfigRmiModule(astraimplement serveur,int port) throws java.rmi.RemoteException{ 

      //  super(port);

        super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());

        m_serveur=serveur;

    }

    

    public int insertConfig(AbstractConfig_T user, int urcleunik) throws ServeurSqlFailure, java.rmi.RemoteException {

        return 1;

    }

    

    public int modifyConfig(AbstractConfig_T config, int urcleunik) throws ServeurSqlFailure, java.rmi.RemoteException {

        Poolconnection tmpool=m_serveur.getConnectionAndCheck(urcleunik,true);

        try{       

            PreparedStatement pstmt=tmpool.getConuser().prepareStatement("UPDATE configuration set bdc_bdc_x=? ,bdc_bdc_y=? ,bdc_bdc_cli_x=? ,bdc_bdc_cli_y=? ,bdc_bloc_g=? , bdc_bloc_d=? ,nb_lette_liste=? ,bdc_texte=?,cn_frdos_tva =?,cn_frdos=?,cn_mulituser=?,cn_multibur=?,cn_comptefrais=?,cn_cpttaxloc=?,cn_cpttaxdest=?,cn_cpttaxcomp=?,cn_cptfees=?,cn_cptrem=?,cn_fraisdossiertva=?,factureauto=?,facturenumber=?,bdcauto=?,bdcnumber=?,ncauto=?,nccnumber=?,nombrebloc=?,factureresum=?,affichebloclegalfacture=?,passagerVisibleBDC=?,detailProduitVisibleBDC=?,detailPrixVisibleBDC=?,passagerVisibleFact=?,detailProduitVisibleFact=?,detailPrixVisibleFact=?,bloquegupx=?,bloquegupy=?,bloquedupx=?,bloquedupy=?,corpx=?,corpy=?,topmargin=?,bottomargin=?,cn_frinclus=?  WHERE eecleunik=?"); 

            pstmt.setDouble(1,config.getBdc_bdc_x());

            pstmt.setDouble(2,config.getBdc_bdc_y());

            pstmt.setDouble(3,config.getBdc_bdc_cli_x());

            pstmt.setDouble(4,config.getBdc_bdc_cli_y());

            pstmt.setInt(5,config.getBdc_bloc_g());

            pstmt.setInt(6,config.getBdc_bloc_d());

            pstmt.setInt(7,config.getNbr_lettre_liste());

            pstmt.setString(8,config.getBdc_texte());

            pstmt.setInt(9,config.getFraisdossiertvacle());

            pstmt.setDouble(10,config.getFraisDossier());           

            pstmt.setInt(11,config.getCaisseparutilisateur());

            pstmt.setInt(12,config.getMultibureaux());

            pstmt.setInt(13,config.getCompteFrais());

            pstmt.setInt(14,config.getCompteTaxLoc());

            pstmt.setInt(15,config.getCompteTaxDest());

            pstmt.setInt(16,config.getCompteTaxcomp());

            pstmt.setInt(17,config.getCompteTaxFees());

            pstmt.setInt(18,config.getCompteTaxRemise());

            pstmt.setInt(19,config.getFraisdossiertvacle());

            pstmt.setInt(20,config.getFactureauto());

            pstmt.setInt(21,config.getFacturenumber());

            pstmt.setInt(22,config.getBdcauto());

            pstmt.setInt(23,config.getBdcnumber());

            pstmt.setInt(24,config.getNcauto());

            pstmt.setInt(25,config.getNccnumber());

            pstmt.setInt(26,config.getNombrebloc());

            pstmt.setInt(27,config.getFactureresum());

            pstmt.setInt(28,config.getAffichebloclegalfacture());

            pstmt.setInt(29,config.getPassagerVisibleBDC());

            pstmt.setInt(30,config.getDetailProduitVisibleBDC());

            pstmt.setInt(31,config.getDetailPrixVisibleBDC());

            pstmt.setInt(32,config.getPassagerVisibleFact());

            pstmt.setInt(33,config.getDetailProduitVisibleFact());

            pstmt.setInt(34,config.getDetailPrixVisibleFact());

            pstmt.setDouble(35,config.getBloquegupx());

            pstmt.setDouble(36,config.getBloquegupy());

            pstmt.setDouble(37,config.getBloquedupx());

            pstmt.setDouble(38,config.getBloquedupy());

            pstmt.setDouble(39,config.getCorpx());

            pstmt.setDouble(40,config.getCorpy());

            pstmt.setDouble(41,config.getTopmargin());

            pstmt.setDouble(42,config.getBottomargin());

            //mt.setInt(24,config.getDetailProduitVisibleBDC());

            //tmt.setInt(25,config.getDetailPrixVisibleBDC());

            pstmt.setInt(43,1);
            
            pstmt.setInt(44,config.getFraistvainclus());
            

            pstmt.execute();

            m_serveur.setConfig(config);

            return 0;

        }catch(SQLException sn){

             renvexception(sn,"erreur dans la selection de configuration",tmpool.getConuser());

        }

        return 0;

    }

    

    public AbstractConfig_T selectConfig(int urcleunik) throws ServeurSqlFailure, java.rmi.RemoteException {

        Poolconnection tmpool=m_serveur.getConnectionAndCheck(urcleunik,true);

        System.out.println("\n\n\n[*****************]numeroentite "+tmpool.getNumeroentite());

        try{

            AbstractConfig_T config=null;

            PreparedStatement pstmt=tmpool.getConuser().prepareStatement("SELECT `conf_cleunik`,`eecleunik` , `bdc_bdc_x` , `bdc_bdc_y` , `bdc_bdc_cli_x` , `bdc_bdc_cli_y` , `bdc_bloc_g` , `bdc_bloc_d` , `nb_lette_liste` , `bdc_texte` , `cn_frdos_tva` , `cn_frdos` , `cn_mulituser` , `cn_multibur` , `cn_fraisdossiertva` , `cn_comptefrais` , `cn_cpttaxloc` , `cn_cpttaxdest` , `cn_cpttaxcomp` , `cn_cptfees` , `cn_cptrem`,`cn_frinclus`,factureauto,facturenumber,bdcauto,bdcnumber,ncauto,nccnumber,nombrebloc,factureresum,affichebloclegalfacture,passagerVisibleBDC,detailProduitVisibleBDC,detailPrixVisibleBDC,passagerVisibleFact,detailProduitVisibleFact,detailPrixVisibleFact,bloquegupx,bloquegupy,bloquedupx,bloquedupy,corpx,corpy,topmargin,bottomargin,factfourn FROM configuration WHERE eecleunik=?");

            pstmt.setInt(1,1);

            ResultSet result=pstmt.executeQuery();

            result.beforeFirst();

            while(result.next()){

                System.out.println("ok resutl non null;");

                if(config==null)

                    config=new Config2_T();

                    config.setConfcleunik(result.getInt(1));

                    config.setEecleunik(result.getInt(2));

                    config.setBdc_bdc_x(result.getDouble(3));

                    config.setBdc_bdc_y(result.getDouble(4));

                    config.setBdc_bdc_cli_x(result.getDouble(5));

                    config.setBdc_bdc_cli_y(result.getDouble(6));

                    config.setBdc_bloc_g(result.getInt(7));

                    config.setBdc_bloc_d(result.getInt(8));

                    config.setNbr_lettre_liste(result.getInt(9));

                    config.setBdc_texte(result.getString(10));  

                    config.setFraisdossiertvacle(result.getInt(11));

                    config.setFraisDossier(result.getDouble(12));

                    config.setCaisseparutilisateur(result.getInt(13));

                    config.setMultibureaux(result.getInt(14));           

                    config.setFraistvainclus(result.getInt(15));

                    config.setCompteFrais(result.getInt(16));

                    config.setCompteTaxLoc(result.getInt(17));

                    config.setCompteTaxDest(result.getInt(18));

                    config.setCompteTaxcomp(result.getInt(19));

                    config.setCompteTaxFees(result.getInt(20));

                    config.setCompteTaxRemise(result.getInt(21));

                    config.setFraistvainclus(result.getInt(22));

                    config.setFactureauto(result.getInt(23));

                    config.setFacturenumber(result.getInt(24));

                    config.setBdcauto(result.getInt(25));

                    config.setBdcnumber(result.getInt(26));

                    config.setNcauto(result.getInt(27));

                    config.setNccnumber(result.getInt(28));

                    config.setNombrebloc(result.getInt(29));

                    config.setFactureresum(result.getInt(30));

                    config.setAffichebloclegalfacture(result.getInt(31));

                    config.setPassagerVisibleBDC(result.getInt(32));

                    config.setDetailProduitVisibleBDC(result.getInt(33));

                    config.setDetailPrixVisibleBDC(result.getInt(34));

                    config.setPassagerVisibleFact(result.getInt(35));

                    config.setDetailProduitVisibleFact(result.getInt(36));

                    config.setDetailPrixVisibleFact(result.getInt(37));

                    config.setBloquegupx(result.getDouble(38));

                    config.setBloquegupy(result.getDouble(39));

                    config.setBloquedupx(result.getDouble(40));

                    config.setBloquedupy(result.getDouble(41));

                    config.setCorpx(result.getDouble(42));

                    config.setCorpy(result.getDouble(43));

                    config.setTopmargin(result.getDouble(44));

                    config.setBottomargin(result.getDouble(45));

                    config.setFactfourn(result.getInt(46));                   

            }

            pstmt=tmpool.getConuser().prepareStatement("SELECT Tva_rate FROM tva WHERE tva_cleunik=?");

            if(config==null)

                return null;

            pstmt.setInt(1,config.getFraisdossiertvacle());

            result=pstmt.executeQuery();

            result.first();

            config.setFraisDossierTva(result.getFloat(1)); 

          //  synchronized(m_serveur.getConfig()){

             if(m_serveur.getConfig()==null)

                  m_serveur.setConfig(config);

                

           // }

            return config;

        }catch(SQLException sn){

             renvexception(sn,"erreur dans la selection de configuration",tmpool.getConuser());

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

}

 