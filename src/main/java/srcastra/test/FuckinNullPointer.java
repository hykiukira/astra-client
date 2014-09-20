package srcastra.test;

import srcastra.astra.sys.compta.command.NcCompta;

import java.util.logging.Logger;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 24-nov.-2004
 * Time: 20:41:03
 *
 * @author
 * @version $revision : $
 *          To change this template use File | Settings | File Templates.
 */
public class FuckinNullPointer {
    private static Logger logger = Logger.getLogger(FuckinNullPointer.class.getName());


    public FuckinNullPointer() {
        Connection con=null;
        try {
            con=connectDb("root","XkLm2000","oranje","195.144.69.10",3306);
            logger.info("Connected");
            PreparedStatement pstmt=null;
         //pstmt=m_config.getCon().prepareStatement("SELECT  *  FROM historique2 WHERE hetypeligne!='P' AND hetypeligne!='CP' AND  hetypeligne!='BP' AND hetypeligne!='CPC' AND hetypeligne!='A' AND hetypeligne!='ACP' AND hetypeligne!='NCA' AND hetypeligne!='NCAB' AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt=con.prepareStatement("SELECT  *  FROM historique2 WHERE (hetypeligne='B' OR hetypeligne='D' OR  hetypeligne='TVAV') AND hedossiercourant='O' AND drcleunik=? ORDER BY hecleunik");
         pstmt.setLong(1,5767);
         ResultSet result=pstmt.executeQuery();
         ResultSetMetaData meta=result.getMetaData();
         int col=meta.getColumnCount();
         while (result.next()){
             String affiche="";
             for(int i=0;i<col;i++){
                 Object tmp=result.getObject(i+1);
                 String tmps=result.getString(i+1);

               /*  if(tmp==null){
                     tmps="empty";
                 }
                 else{
                     tmps=tmp.toString();
                 }*/
                 affiche=affiche+" "+meta.getColumnName(i+1)+" field "+tmps;
             }
             logger.info(affiche);
           I_ligneComptable tmp= new I_ligneComptable(
                                                                    result.getObject(2),result.getObject(3),result.getObject(4),result.getObject(5),
                                                                    result.getObject(6),result.getObject(7),result.getObject(8),
                                                                    result.getObject(9),result.getObject(10),result.getObject(11),
                                                                    result.getObject(12),result.getObject(13),result.getObject(14),result.getObject(15),result.getObject(16),
                                                                    result.getObject(17),result.getObject(18),result.getObject(19),result.getObject(20),result.getObject(21),
                                                                    result.getObject(22),result.getObject(23),result.getObject(24),result.getObject(25),result.getObject(26),
                                                                    result.getObject(27),result.getObject(28),result.getObject(29),result.getObject(30),result.getObject(31),
                                                                    result.getObject(32),result.getObject(33),result.getObject(34),result.getObject(35),result.getObject(36),
                                                                    result.getObject(37),result.getObject(38),result.getObject(39),result.getObject(40),result.getObject(41),
                                                                    result.getObject(42),result.getObject(45));

        //  insertLigneNoteCrédit(tmp,con,pstmt);
         }
        } catch (SQLException e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
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
    public static void main(String[] args) {
        new FuckinNullPointer();
    }
    public class I_ligneComptable{


                             //      1              2               3                 4                     5                   6                  7                    8                   9                  11
 public I_ligneComptable(Object heperiode,Object henotcpt,Object heclottva,Object heclotperiode,Object heclotexercice,Object hetransact, Object jxcleunik, Object henumpiece, Object hedatecreation,Object hedatemouv ,Object ce_cleunik_cent,Object tva_cleunik,Object ce_cleunik2,Object ce_cleunik,Object hevaleur,Object hecodetva,Object heValeurBase,Object heValeurTva,Object decleunik,Object hedatedevise,Object hevaleurdevise,Object helibele,Object drCleunik,

                                Object lecleunik,Object sn_cleunik,Object ct_cleunik,Object typeIntervenant,Object intervenantCleunik,

                                Object cate_cleunik,Object hedossiercourant,Object he_dossier_lignetype,Object urcleunik,Object  hetypePayement,Object helibellecompta,Object pax,Object quantite,Object pourcent,Object hevaleuru,Object gn_cleunik, Object typegrpdec,Object exle_cleunik,Object debcre){

           m_ce_cleunik_cent=(Integer)ce_cleunik_cent;

           m_tva_cleunik=(Integer)tva_cleunik;

           m_ce_cleunik2=(Integer)ce_cleunik2;

           m_heperiode=heperiode.toString();

           m_heclottva=(Integer)heclottva;

           m_heclotperiode=(Integer)heclotperiode;

           m_heclotexercice=(Integer)heclotexercice;

           m_hetransact=(Integer)hetransact;

           m_jxcleunik=(Integer)jxcleunik;

           m_henumpiece=(Long)henumpiece;

           m_hedatecreation=hedatecreation.toString();

           m_hedatemouv= hedatemouv.toString() ;

           m_henotcpt=(Integer)henotcpt;

           m_hevaleur=new Double(-((Double)hevaleur).doubleValue());

           m_hecodetva=(Float)hecodetva;

           m_heValeurBase=new Double(-((Double)heValeurBase).doubleValue());

           m_heValeurTva=new Double(-((Double)heValeurTva).doubleValue());

           m_decleunik=(Integer)decleunik;
           //m_hedatedevise
           if(hedatedevise!=null){
            m_hedatedevise=hedatedevise.toString();
           }
            else{
               m_hedatedevise="";
           }

           m_hevaleurdevise=(Double)hevaleurdevise;

           if(helibele!=null)

             m_helibelle=helibele.toString();

           else m_helibelle="";

           m_drCleunik=(Long)drCleunik;

           m_lecleunik=(Long)lecleunik;

           m_sn_cleunik=(Long)sn_cleunik;

           m_ct_cleunik=(Integer)ct_cleunik;

           m_typeIntervenant=(Integer)typeIntervenant;

           m_intervenantCleunik=(Integer)intervenantCleunik;

           m_ce_cleunik=ce_cleunik.toString();

           m_cate_cleunik=(Integer)cate_cleunik;

           m_he_dossier_lignetype=he_dossier_lignetype.toString();

           m_he_dossier_courant=hedossiercourant.toString();

           m_urcleunik=(Integer)urcleunik;

           m_hetypePayement=(Integer)hetypePayement;

           m_helibellecompta=helibellecompta.toString();

           m_pax=(Integer)pax;

           m_quantite=(Integer)quantite;

           m_pourcent=(Float)pourcent;

           m_hevaleuru=(Double)hevaleuru;

           m_gn_cleunik=(Integer)gn_cleunik;

           m_typegrpdec=(Integer)typegrpdec;

           m_exle_cleunik=(Integer)exle_cleunik;
           m_debcre=debcre.toString().equals("D")?"C":"D";

         }

        public Integer m_ce_cleunik_cent;

        public Integer m_tva_cleunik;

        public Integer m_ce_cleunik2;

        public String m_heperiode;

        public Integer m_henotcpt;

        public Integer m_heclottva;

        public Integer m_heclotperiode;

        public Integer m_heclotexercice;

        public Integer  m_hetransact;

        public Integer m_jxcleunik;

        public Long m_henumpiece;

        public String m_hedatecreation;

        public String m_hedatemouv;

        public Double m_hevaleur;

        public Float m_hecodetva;

        public Double m_heValeurBase;

        public Double m_heValeurTva;

        public Integer m_decleunik;

        public String m_hedatedevise;

        public Double m_hevaleurdevise;

        public String m_helibelle;

        public Long m_drCleunik;

        public Long m_lecleunik;

        public Long m_sn_cleunik;

        public Integer m_ct_cleunik;

        public Integer m_typeIntervenant;

        public Integer m_intervenantCleunik;

        public String m_ce_cleunik;

        public Integer m_cate_cleunik;

        public String  m_he_dossier_courant;

        public String m_he_dossier_lignetype;

        public Integer m_urcleunik;

        public Integer m_hetypePayement;

        public String m_helibellecompta;

        public Integer m_pax;

        public Integer m_quantite;

        public Float m_pourcent;

        public Double m_hevaleuru;

        public Integer m_gn_cleunik;

        public Integer m_typegrpdec;

        public Integer m_exle_cleunik;
        public String m_debcre;



     }
}
