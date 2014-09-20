/*

 * ClientAstra.java

 *

 * Created on 1 décembre 2003, 13:46

 */



package srcastra.astra.sys.importastra;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.rmi.utils.*;

import java.sql.*;

import srcastra.astra.sys.classetransfert.clients.*;

import java.util.*;
import srcastra.astra.sys.export.DbfManager2;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.*;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.configuration.*;
/**

 *

 * @author  Thomas

 */

public class ClientAstra {

    

    /** Creates a new instance of ClientAstra */

    astraimplement serveur;

     int col; 
     astrainterface m_serveur;
     Loginusers_T m_user;
     ArrayList m_clientVoyager;
     Poolconnection m_pool;
     Hashtable entite;
     Connection con;
    public ClientAstra() {
        
                
    }
    public ClientAstra(ArrayList clientVoyager,Poolconnection tmpool,astrainterface serveur,Loginusers_T user) {
        m_clientVoyager=clientVoyager;
        m_pool=tmpool;                
        m_serveur=serveur;        
      //  m_user=user;
    }
    public void creatTmpClient() throws ServeurSqlFailure{
        try{
            con=connectDb(m_pool.getDatabaseLogin(), m_pool.getDatabasePassword(),  "Astra",  "localhost", 3306);
            createDatabase();
            System.out.println("database v_client created");
            Transaction.begin(con);
            
            insertIntoTmpDatabase();
            checkCodePostaux();
            System.out.println("Insert into V_CLIENT terminé");
            Transaction.commit(con);
            con.close();
        }catch(SQLException sn){            
           sn.printStackTrace();
           Transaction.rollback(con);
           ServeurSqlFailure se=new ServeurSqlFailure();
           se.setSqlException(sn);
           throw se;
        }        
    }
    private void createDatabase() throws SQLException{
         //v.`C_NOCLIENT` ,v.`CDE_LOC`, v.`C_NOM1` , v.`C_NOM2` , v.`C_ADRESSE` , v.C_LOCA , v.`C_TELEB` , v.`C_TELEP` , 
  //v.`C_TELEFAX` , v.`C_TITRE` , ' ' , v.`C_DATENAI`  , v.C_REFTVA, v.`C_NOTVA` , v.`C_REGIME` , v.`C_LANG` , v.`C_CMAILING`
  //, v.`C_DTCOTIS` , v.`C_COTISAT` , v.`C_PAYS`
    String drop="DROP TABLE `v_client`";
    String requete="    CREATE TABLE `v_client` (id int(11) NOT NULL auto_increment,`CDE_LOC` varchar(2) default NULL,`C_NOCLIENT` varchar(8) default NULL,  `C_NOM1` varchar(30) default NULL,  `C_NOM2` varchar(30) default NULL,  `C_ADRESSE` varchar(30) default NULL,  `C_CPOS` varchar(8) default NULL,  `C_LOCA` varchar(20) default NULL,  `C_TELEB` varchar(16) default NULL,  `C_TELEP` varchar(16) default NULL,`C_TELEFAX` varchar(16) default NULL,`C_TITRE` char(1) default NULL,`C_DATENAI` datetime default NULL,`C_REFTVA` char(2) default NULL,`C_NOTVA` varchar(12) default NULL,`C_REGIME` char(1) default NULL,`C_LANG` char(1) default NULL,`C_CMAILING` varchar(5) default NULL,`C_DTCOTIS` datetime default NULL,`C_COTISAT` decimal(4,0) default NULL,`C_PAYS` varchar(20) default NULL, PRIMARY KEY  (id) ) TYPE=InnoDB;";
    String table="SHOW tables";
    PreparedStatement pstmt=con.prepareStatement(table);
    ResultSet result=pstmt.executeQuery();
    boolean sw=false;
    while(result.next()){
        String tableName=result.getString(1);
        if(tableName.equals("v_client")){            
         sw=true;
        }
    }
    if(sw){
        pstmt=con.prepareStatement(drop);
        pstmt.execute();
    }
    pstmt=con.prepareStatement(requete);
    pstmt.execute();
    }
    private void checkCodePostaux() throws SQLException{
        String requete="SELECT DISTINCT v.C_CPOS,v.C_LOCA,c.cxcode FROM v_client v LEFT JOIN codepostaux c ON (v.C_CPOS=c.cxcode) WHERE c.cxcode is null AND v.C_CPOS IS NOT NULL AND v.C_LOCA IS NOT NULL AND v.C_CPOS NOT LIKE('') AND v.C_LOCA NOT LIKE('')  AND v.C_CPOS NOT LIKE('0') ";
        String requetcp="insert into codepostaux(cxcode,cxintitulegen) values(?,?)";
        String requeTraduction="insert into traductioncodpostaux(cxcleunik,txtraduction,lmcleunik) values(?,?,?)";
        PreparedStatement pstmt=m_pool.getConuser().prepareStatement(requete)        ;
        PreparedStatement pstmt2=m_pool.getConuser().prepareStatement(requetcp);
        PreparedStatement pstmt3=m_pool.getConuser().prepareStatement(requeTraduction);
        ResultSet result=pstmt.executeQuery();
        ArrayList array=new ArrayList();
        Hashtable tablehash=new Hashtable();
        while(result.next()){            
            Object[] tmp=new Object[2];
            
            
            tmp[0]=result.getString(1);
            tmp[1]=result.getString(2);
             System.out.println("obj "+tmp[0]);
            Object objtmp=tablehash.get(tmp[0]);
            if(objtmp==null){
                tablehash.put(tmp[0],tmp);
                 System.out.println("put");
            }
           // System.out.println("put");
          /*  pstmt2.setString(1, tmp[0].toString());
            pstmt2.setString(2, tmp[1].toString());
            pstmt2.execute();
            int id=srcastra.astra.sys.classetransfert.utils.GetId.getLastId(m_pool.getConuser());
            pstmt3.setInt(1,id);
            pstmt3.setString(2, tmp[1].toString());
            pstmt3.setInt(3, 1);
            pstmt3.execute();
            pstmt3.setInt(3, 2);
            pstmt3.execute();             
            pstmt3.setInt(3, 3);
            pstmt3.execute();
            pstmt3.setInt(3, 4);
            pstmt3.execute();
            pstmt3.setInt(3, 5);
            pstmt3.execute();*/
            
            
        }    
        System.out.println("insertion new code postaux");
        for(Enumeration en=tablehash.keys();en.hasMoreElements();){
           
            Object[] tmpobj=(Object[])tablehash.get(en.nextElement());
             System.out.println("tmpobj "+tmpobj[0]);
            pstmt2.setString(1, tmpobj[0].toString());
            pstmt2.setString(2, tmpobj[1].toString());
            pstmt2.execute();
            int id=srcastra.astra.sys.classetransfert.utils.GetId.getLastId(m_pool.getConuser());
            pstmt3.setInt(1,id);
            pstmt3.setString(2, tmpobj[1].toString());
            pstmt3.setInt(3, 1);
            pstmt3.execute();
            pstmt3.setInt(3, 2);
            pstmt3.execute();             
            pstmt3.setInt(3, 3);
            pstmt3.execute();
            pstmt3.setInt(3, 4);
            pstmt3.execute();
            pstmt3.setInt(3, 5);
            pstmt3.execute();                       
        }
        System.out.println("Fin de l'insertion des code postauxs");
    
        
    }
    private void insertIntoTmpDatabase() throws SQLException{
        String insert="INSERT INTO `v_client` ( `CDE_LOC` , `C_NOCLIENT` , `C_NOM1` , `C_NOM2` , `C_ADRESSE` , `C_CPOS` , `C_LOCA` , `C_TELEB` , `C_TELEP` , `C_TELEFAX` , `C_TITRE` ,  `C_DATENAI` , `C_REFTVA` , `C_NOTVA` , `C_REGIME` , `C_LANG` ,`C_CMAILING` ,  `C_DTCOTIS` , `C_COTISAT` ,  `C_PAYS` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt=m_pool.getConuser().prepareStatement(insert);
        if(m_clientVoyager!=null){
            for(int i=0;i<m_clientVoyager.size();i++){
                Object[] tab=(Object[])m_clientVoyager.get(i);
                for(int j=0;j<tab.length;j++){
                    String param=" ";
                    if(tab[j]!=null)
                        param=tab[j].toString();
                    pstmt.setString(j+1,param);                    
                }
                pstmt.execute();
                
            }
            
            
        }
    
    }
    public void getEntity() throws ServeurSqlFailure{
        try{
            System.out.println("cle user "+m_pool.getUrcleunik());
            ArrayList array=m_serveur.renvEntiteRmiObject(m_pool.getUrcleunik()).getList(m_pool.getUrcleunik(), 0);
            for(int i=0;i<array.size();i++){
                if(entite==null)
                    entite=new Hashtable();
                Entite entitet=(Entite)array.get(i);
                if(entitet!=null)
                    entite.put(entitet.getEeabrev(), new Integer(entitet.getEecleunik()));                                
            }
        }
        catch(java.rmi.RemoteException rn){
            
        
        }
        
    }
    public void importFromDBF(String path){
        
    }
    private Integer checkEntity(String entity) throws srcastra.astra.sys.importastra.EntityNotFoundException
    {

     /*   if(entity.equals("ZZ"))

             return new Integer(1);

        else if(entity.equals("WA"))

              return new Integer(2);       

     /*   else if(entity.equals("ME"))

              return new Integer(7);

        else if(entity.equals("LE"))

              return new Integer(6);

        else if(entity.equals("DI"))

              return new Integer(4);

        else if(entity.equals("PU"))

              return new Integer(8);

        else if(entity.equals("TR"))

              return new Integer(5);

        else if(entity.equals("ZZ"))

              return new Integer(9);*/
        Integer cle=(Integer) entite.get(entity);
        if(cle==null)
            throw new EntityNotFoundException(entity);
        return cle;

    }
    public ArrayList importClient2(Poolconnection tmpool) throws SQLException,java.rmi.RemoteException, srcastra.astra.sys.importastra.EntityNotFoundException,ServeurSqlFailure{
        getEntity();
        //                                  0                   1           2           3               4           5       6           7               8               9        10         11              12          13          14              15              16              17              18              19          20      21     22  
        String requete="SELECT  DISTINCT v.`C_NOCLIENT` ,v.`CDE_LOC`, v.`C_NOM1` , v.`C_NOM2` , v.`C_ADRESSE` ,  2859 ,v.`C_TELEB` , v.`C_TELEP` , v.`C_TELEFAX` , v.`C_TITRE` , ' ' , v.`C_DATENAI`  , v.C_REFTVA, v.`C_NOTVA` , v.`C_REGIME` , v.`C_LANG` , v.`C_CMAILING` , v.`C_DTCOTIS` , v.`C_COTISAT` , v.`C_PAYS`, v.C_CPOS,v.C_LOCA,v.id FROM `v_client` v ";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        result.last();
        int number=result.getRow();
        result.beforeFirst();
        col=result.getMetaData().getColumnCount();    
        ArrayList arraycli=null;
        System.out.println("\n\n\nnombre de client correcte : "+number);
        while (result.next()){
              if(arraycli==null)
                arraycli=new ArrayList();
            Object[] tab=new Object[col+1];
            for(int i=0;i<col;i++){
                tab[i]=result.getObject(i+1);                                
            }        
          tab[col]=checkEntity(tab[1].toString());
          arraycli.add(setClient(tab));              

        }   
        return arraycli;
            
        }
    
    public void resetCodePostaux(Poolconnection tmpool) throws SQLException{
      //  checkCodePostaux();
        boolean sw;
       // getEntity();
        ArrayList arraycli=null;
        Connection tmpconnect=null;
        if(tmpool==null){
            tmpconnect=connectDb("ADMIN", "GgHh459", "Astra2", "localhost", 3306);
        }else{
            tmpconnect=tmpool.getConuser();
        }
        System.out.println("debut du reset de cp");
        String requeteClient="SELECT id,C_CPOS,C_LOCA FROM v_client";
        String requete="SELECT  DISTINCT   c.`cxcleunik` FROM `v_client` v ,codepostaux c, traductioncodpostaux t WHERE c.cxcode=? AND LEFT(t.txtraduction,3)=LEFT(?,3)  AND t.lmcleunik=2 AND t.cxcleunik=c.cxcleunik order by c_nom1";
        String requete2="SELECT   DISTINCT c.`cxcleunik` FROM  codepostaux c WHERE c.cxcode=?";
        String updateClient="UPDATE clients   set cxcleunik=? where cscleunik=?";
        PreparedStatement pstmt=tmpconnect.prepareStatement(requeteClient);
        PreparedStatement pstmt2=null;
        ResultSet result=pstmt.executeQuery();
        ResultSet result2=null;
        result.last();
        int numberRow=result.getRow();
        System.out.println("nombre de ligne "+numberRow);
        int cxcleunik=0;
        result.beforeFirst();
        int k=1;
        while(result.next()){
            cxcleunik=2859;
            int id=result.getInt(1);
            String code=result.getString(2);
            System.out.println(k+" code "+code);
            String loca=result.getString(3);
            pstmt2=tmpconnect.prepareStatement(requete);
            pstmt2.setString(1,code);
            pstmt2.setString(2,loca);
            result2=pstmt2.executeQuery();
            while(result2.next()){
                cxcleunik=result2.getInt(1);                
              //  break;                
            }
            if(cxcleunik==2859){
                pstmt2=tmpconnect.prepareStatement(requete2);
                pstmt2.setString(1,code);                
                result2=pstmt2.executeQuery();
                while(result2.next()){
                    cxcleunik=result2.getInt(1);       
                //    break;
                }
                
            }
             if(cxcleunik!=2859){
                 pstmt2=tmpconnect.prepareStatement(updateClient);
                 pstmt2.setInt(1, cxcleunik);
                 pstmt2.setInt(2, id);
                 pstmt2.execute();
             }   
            k++;
        }
    }

 private  Clients_T setClient(Object[] tab){

    //                          0               1       2           3           4               5               6               7   

   //SELECT  DISTINCT v.`C_NOCLIENT` ,v.`CDE_LOC`, v.`C_NOM1` , v.`C_NOM2` , v.`C_ADRESSE` ,  c.`cxcleunik` , v.`C_TELEB` , v.`C_TELEP` , 

   //           8               9           10              11            12              13       14              15                16         17

     //v.`C_TELEFAX` , v.`C_TITRE` , v.`C_COMMENT` , v.`C_DATENAI`  , v.C_REFTVA, v.`C_NOTVA` , v.`C_REGIME` , v.`C_LANG` , v.`C_CMAILING` , v.`C_DTCOTIS` 

  //            18          19   

     //, v.`C_COTISAT` , v.`C_PAYS` FROM `voyagercli` v ,codepostaux c, traductioncodpostaux t WHERE v.C_CPOS=c.cxcode AND v.C_LOCA=t.txtraduction 

     //AND t.lmcleunik=2 AND t.cxcleunik=c.cxcleunik";

      Clients_T cli=null;

      String data="";

     if(tab!=null){

         cli=new Clients_T();
         cli.setEecleunik(Integer.parseInt(tab[col].toString()));
         cli.setCsreference(checkNull(tab[0]));
         cli.setCsnom(checkNull(tab[2]));
         System.out.println("cli nom"+cli.getCsnom());
         cli.setCsnom2(checkNull(tab[3]));
         cli.setCsadresse(checkNull(tab[4]));  
         if(tab[5]==null){
             cli.setCxcleunik(2859); 
         }
         else{ 
            cli.setCxcleunik(Integer.parseInt(tab[5].toString()));
         }
         cli.setCstelephones(checkNull(tab[6]));        
         cli.setCstelephonep(checkNull(tab[7]));
         cli.setCsfax(checkNull(tab[8]));
         cli.setTscleunik(checkTitre(tab[9]));
         //cli.setCsdatenaiss(tab[11].toString());
         String typeTva="BE";
         if(tab[12]!=null)
             typeTva=tab[12].toString();
         cli.setCstvatype(typeTva);
         cli.setCstvanum(checkNull(tab[13]));
         cli.setCstvaregime(checkTvaRegime(tab[14]));  
         cli.setLecleunik(checkLangue(tab[15]));
         cli.setCscodemailing(checkNull(tab[16]));
         //cli.setCsdatecotisation(tab[18].toString());
         cli.setCscodecotisateur(checkNull(tab[18]));
         cli.setPyscleunik(20);
         cli.setTvatype(1);
         cli.setCe_cleunik(635);
         cli.setCsmemo(tab[20]+"   "+tab[20]+"  "+tab[21]);
         cli.setCscleunik(Integer.parseInt(tab[22].toString()));
     }
      return cli;

 }

 private String checkNull(Object obj){

    if(obj!=null)return obj.toString();

    return "";

 }

 private int checkTitre(Object obj){
    int retval=25;
    if(obj!=null){
    try{
    if(Integer.parseInt(obj.toString())==1)

         retval=4;

    else if(Integer.parseInt(obj.toString())==2)

         retval=21;

    else if(Integer.parseInt(obj.toString())==3)

         retval=6;

    else if(Integer.parseInt(obj.toString())==4)

         retval=16;

    else if(Integer.parseInt(obj.toString())==5)

         retval=25;

     else if(Integer.parseInt(obj.toString())==6)

         retval=23;
    }catch(NumberFormatException nn){
        retval=25;
        
    }

    }

    return retval;

 }

 private int checkTvaRegime(Object obj){

    if(obj!=null){

    if(obj.toString().equals("N"))

         return 3;

    else if(obj.toString().equals("A"))

         return 1;

    else if(obj.toString().equals("E"))

         return 2;

    else if(obj.toString().equals("F"))

         return 16;

    return 3;

    }

    return 3; 

 }

 private int checkLangue(Object obj){

   if(obj!=null){

       if(obj.toString().equals("N"))

           return 2;

       else  if(obj.toString().equals("F"))

           return 1;

   return 1;

   }

   return 1;

 }
 public static void main(String args[]){
     try{
     new ClientAstra().resetCodePostaux(null);
     }catch(SQLException sn){
         sn.printStackTrace();
     }
     
 }
  private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try {
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)
                Class.forName(jdbcDriverClassName) ;
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();           
            }
           
            catch(Exception e2) {
                e2.printStackTrace();              
            }       // Add your handling code here:
            return tmpcon;
        }

}

