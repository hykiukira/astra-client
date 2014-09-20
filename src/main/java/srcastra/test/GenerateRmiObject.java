/*
 * GenerateRmiObject.java
 *
 * Created on 4 juillet 2003, 9:08
 */

package srcastra.test;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author  thomas
 */
public class GenerateRmiObject {
    
    /** Creates a new instance of GenerateRmiObject */
    Connection con;
    String[] args;
    public GenerateRmiObject(String[] args) {
        this.args=args;
        try{ 
            File rmiObject=new File(args[3]);
            BufferedWriter out = new BufferedWriter(new FileWriter(rmiObject));
           // BufferedWriter out2 = new BufferedWriter(new FileWriter(rmiInterface));
            //String sinterf=genereRmiInterface(obj
            con=connectDb("THOM","vidaloca","Astrainvalide","195.144.70.246",3306);
            getTableDesc(args[5]);     
            generateSqlrequete(args[5]);
           // genereRmiInterface(args);
            String tmp=getEnteteClass(args);          
            tmp=genereInsert(args,tmp);
            tmp=genereModify(args,tmp);
            tmp=genereGet(args,tmp);
            tmp=genereDelete(args,tmp);
            tmp=genereGetAll(args,tmp);
            tmp=genereGetAll2(args,tmp);
            tmp=tmp+"\n}";
            System.out.println("classe "+tmp+" \nmodif"+reqmodif);
            out.write(tmp);
            out.close();
           // createSerialisedClass("dossier",args);
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new  GenerateRmiObject(args) ;
    }
    private String genereRmiInterface(String[] args) throws SQLException,IOException{
         File rmiinter=new File(args[0]);
         BufferedWriter out = new BufferedWriter(new FileWriter(rmiinter));
         String rmiintstring= "package srcastra.astra.sys.rmi;"
                              +"\nimport srcastra.astra.sys.classetransfert.compta.*;"
                              +"\nimport java.sql.*;"
                              +"\nimport srcastra.astra.sys.rmi.utils.*;"
                              +"\nimport srcastra.astra.sys.rmi.Exception.*;"
                              +"\nimport java.util.*;"
                              +"\nimport srcastra.astra.sys.classetransfert.configuration.*;"
                              +"\nimport srcastra.astra.sys.compta.*;\n/**** @author  thomas*/"
                              +"\npublic interface GlobalRmiInterface extends java.rmi.Remote{\n\n"
                              +"Object get(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"\nvoid modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"\nvoid insert(int urcleunik,Object obj11) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"\nvoid delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"\njava.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"\njava.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;\n"
                              +"}";
            out.write(rmiintstring);
            out.close();
         return rmiintstring;
         //File rmiclass=new File(args[5]);
         //BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
    } 
     private String generateRmiObject(Object obj,String string,String[] args,String[] element) throws SQLException,IOException{
         return null;
        
        
    } 
   private String getEnteteClass(String[] args){
    String entete=  "package srcastra.astra.sys.rmi;\n"+
                    "import srcastra.astra.sys.Transaction;\n"+
                    "import srcastra.astra.sys.rmi.astraimplement;\n"+
                    "import java.sql.*;\n"+
                    "import srcastra.astra.sys.rmi.utils.Poolconnection;\n"+
                    "import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;\n"+
                    "import java.rmi.*;\n"+
                    "import srcastra.astra.sys.rmi.Exception.ManageServSQLExcption;\n"+
                    "import java.util.*;\n"+
                    "import srcastra.astra.sys.compress.*;\n"+
                    "import srcastra.astra.sys.classetransfert.Fournisseur_T;\n"+
                    "import srcastra.astra.sys.rmi.groupe_dec.*;\n"+
                    "import srcastra.astra.sys.classetransfert.configuration.*;\n"+
                    "import srcastra.astra.sys.compress.*;\n"+
                    "/**\n"+
                    "*\n"+
                    "* @author  Thomas\n"+
                    "*/\n"+
                    "public class "+args[4]+" extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{\n"+
                        "astraimplement serveur;\n"+
                     "public "+args[4]+"(astraimplement serveur) throws RemoteException {      \n"+
                        "this.serveur=serveur;\n"+       
                    "}\n"; 
    return entete;
   }
   private String genereInsert(String[] args,String tmp){
            tmp=tmp+generateMethodeInsert(args);
            tmp=tmp+check(0);
            tmp=tmp+generepstmtInsert();
            return tmp;     
   }
   private String genereModify(String[] args,String tmp){
            tmp=tmp+generateMethodeModify(args);
            tmp=tmp+check(1);
            tmp=tmp+generepstmtModify();
            return tmp;     
   }
    private String genereGet(String[] args,String tmp){
            tmp=tmp+generateMethodeGet(args);
            tmp=tmp+check(2);
            tmp=tmp+generepstmtGet();
            return tmp;     
   }
   private String genereGetAll(String[] args,String tmp){
            tmp=tmp+generateMethodeGetAll(args);
            tmp=tmp+check(4);
            tmp=tmp+generepstmtGetAll();
            return tmp;     
   }
    private String genereGetAll2(String[] args,String tmp){
            tmp=tmp+generateMethodeGetAll1(args);
            check(5);
            tmp=tmp+getAll2()+"\n}";
        //    tmp=tmp+generepstmtGetAll2();
            return tmp;     
   }
   private String genereDelete(String[] args,String tmp){
        tmp=tmp+generateMethodeDelete(args);
        tmp=tmp+check(3);
        tmp=tmp+generepstmtDelete();
        return tmp;     
   }
   private String generateMethodeInsert(String[] args){
       return "\npublic void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{\n"+args[2]+" obj=("+args[2]+")obj1;\n";
       
   }
   private String generateMethodeDelete(String[] args){
       return "\npublic void delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{\n";
       
   }
   private String generateMethodeGetAll(String[] args){
       return "\npublic java.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{\n";
       
   }
   private String generateMethodeGetAll1(String[] args){
       return "\npublic java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{\n";   
   }
    private String generateMethodeModify(String[] args){
       return "\npublic void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{\n"+args[2]+" obj=("+args[2]+")obj1;\n";  
   }
     private String generateMethodeGet(String[] args){
       return "\npublic Object get(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure{\n"+args[2]+" obj=new "+args[2]+"();\n";
   }
   private String check(int type){
       String req=null;
       if(type==0)
           req=reqinsert;
       else if(type==1)
           req=reqmodif;
       else if(type==2)
           req=reqget;
       else if(type==3)
            req=reqdelete;
       else if(type==4)
            req=reqgetAll;
       else if(type==5)
            req=reqgetAll2;       
       String check= "Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);\n"+
         "try{\n"+
         "Transaction.begin(tmpool.getConuser());\n"+
         "String requete=\""+req+"\";\n"+
         "PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);\n";
         return check;
   }
   private String getAll2(){
       String tmp="\n   CompressArray cp;\n     Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);\n      "+
       "return cp=Transaction.generecombostest3(\""+reqgetAll2+"\",tmpool.getConuser());";
       return tmp;
   }
  private String generepstmtModify(){
      String test="";
      
      int size=desctable.size();
      int i;
      for(i=1;i<size;i++){
        String[] obj=(String[])desctable.get(i);  
        test=test+genereAttribut(obj[1],obj[0],i)+"\n";
     }
      String[] obj=(String[])desctable.get(0);
      test=test+genereAttribut(obj[1],obj[0],i)+"\n";
      test=test+"\npstmt.execute();\nTransaction.commit(tmpool.getConuser());\n"+fin+"\n}"; 
      return  test;
  }
   private String generepstmtGet(){
      String test="";
      int size=desctable.size();
      int i;
      String[] obj=(String[])desctable.get(0);
      test=test+genereAttribut3(obj[1],this.args[2],1)+"\nResultSet result=pstmt.executeQuery();\nresult.beforeFirst();\nwhile (result.next()){\n ";
      for(i=1;i<=size;i++){
         obj=(String[])desctable.get(i-1);  
        test=test+genereAttribut2(obj[1],obj[0],i)+"\n";
     }
      test=test+"\n}\nTransaction.commit(tmpool.getConuser());\n\n return obj;"+fin+"\n return null;\n}";
      return  test;
  }
   private String generepstmtGetAll(){
      String test="";
      int size=desctable.size();
      int i;
      String[] obj=(String[])desctable.get(0);
      test="\nArrayList array=new ArrayList();\nResultSet result=pstmt.executeQuery();\nresult.beforeFirst();\nwhile (result.next()){\n"+args[2] +" obj=new "+args[2]+"();\n ";
      for(i=1;i<=size;i++){
          
        obj=(String[])desctable.get(i-1);  
        test=test+genereAttribut2(obj[1],obj[0],i)+"\n";
     }
      test=test+"\narray.add(obj);}\nTransaction.commit(tmpool.getConuser());\n\n return array;"+fin+"\n return null;\n}";
      return  test;
  }
   private String generepstmtGetAll2(){
      String test="";
      int size=desctable.size();
      int i;
      String[] obj=(String[])desctable.get(0);
      test=test+genereAttribut3(obj[1],this.args[2],1)+
      "\nArrayList array=new ArrayList();\nResultSet result=pstmt.executeQuery();\nresult.beforeFirst();\n"+
      "while (result.next()){\nObject obj=new Object[2];\nobj[0]=result.getObject(1);\nobj[1]=result.getObject(2);\n ";
      test=test+"\narray.add(obj);\n}\nTransaction.commit(tmpool.getConuser());\n\n return array;"+fin+"\n return null;\n}";
      return  test;
  }
   private String generepstmtDelete(){
      String test="";
      int size=desctable.size();
      int i;
      String[] obj=(String[])desctable.get(0);
      test=test+genereAttribut3(obj[1],this.args[2],1)+";\n";
      test=test+"\npstmt.execute();\nTransaction.commit(tmpool.getConuser());\n"+fin+"\n}";
      return test;
  }
   private String generepstmtInsert(){
      String test="";
      
      int size=desctable.size();
      for(int i=1;i<size;i++){
        String[] obj=(String[])desctable.get(i);  
        test=test+genereAttribut(obj[1],obj[0],i)+"\n";
     }
      test=test+"\npstmt.execute();\nTransaction.commit(tmpool.getConuser());\n"+fin+"\n}";
    
      return  test;
  }
 private String genereSqlinsert(){
     return null;
     
     
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
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();
            }
            catch(Exception e2) {
                e2.printStackTrace();
            }       // Add your handling code here:
            return tmpcon;
        }
 private void generateSqlrequete(String table){
     int size=desctable.size();
     reqinsert="INSERT into "+table+"(";
     reqmodif="UPDATE "+table+" SET ";
     String tmp=" VALUES(";
     for(int i=1;i<size;i++){
        String[] obj=(String[])desctable.get(i);  
        if(i==size-1){
            reqinsert=reqinsert+obj[0]+")";
            reqmodif=reqmodif+obj[0]+"=? WHERE ";
            tmp=tmp+"?)";
        }else
        {
           reqinsert=reqinsert+obj[0]+",";
           reqmodif=reqmodif+obj[0]+"=? ,";
           tmp=tmp+"? ,";  
        }
     }
     reqinsert=reqinsert+tmp;
     reqmodif=reqmodif+((Object[])desctable.get(0))[0].toString()+"=?";
     reqget="SELECT * FROM "+table+" WHERE "+((Object[])desctable.get(0))[0].toString()+"=?";
     reqdelete="UPDATE "+table+" SET annuler=1 WHERE "+((Object[])desctable.get(0))[0].toString()+"=?";
     reqgetAll="SELECT * FROM "+table+" ORDER BY "+args[8];
     reqgetAll2="SELECT "+args[7]+","+args[8]+","+args[9]+" FROM "+table+" ORDER BY "+args[8];
     System.out.println("insert "+ reqinsert);
     
 }
  private String genereAttribut(Object obj,String string,int x){
        String type=obj.toString();
        if(type.substring(0,3).equals("tin") || type.substring(0,3).equals("int") || type.substring(0,3).equals("med"))
            return "pstmt.setInt("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"());";
        if(type.substring(0,3).equals("big"))
            return "pstmt.setLong("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"());";
        if(type.substring(0,3).equals("dou"))
             return "pstmt.setDouble("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"());";
        if(type.substring(0,3).equals("flo"))
             return "pstmt.setFloat("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"());";
        if(type.substring(0,3).equals("dat"))
            return "pstmt.setString("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"().toString());";
        if(type.substring(0,3).equals("cha") || type.substring(0,3).equals("var") || type.substring(0,3).equals("tex"))
            return "pstmt.setString("+x+",obj.get"+string.substring(0,1).toUpperCase()+string.substring(1)+"());";
        return string;
    }
  private String genereAttribut3(Object obj,String string,int x){
        String type=obj.toString();
        if(type.substring(0,3).equals("tin") || type.substring(0,3).equals("int") || type.substring(0,3).equals("med"))
            return "pstmt.setInt("+x+",cleunik);";
        if(type.substring(0,3).equals("big"))
              return "pstmt.setLong("+x+",cleunik);";
        if(type.substring(0,3).equals("dou"))
               return "pstmt.setDouble("+x+",cleunik);";
        if(type.substring(0,3).equals("flo"))
               return "pstmt.setFloat("+x+",cleunik);";
        if(type.substring(0,3).equals("dat"))
              return "pstmt.setString("+x+",cleunik);";
        if(type.substring(0,3).equals("cha") || type.substring(0,3).equals("var") || type.substring(0,3).equals("tex"))
              return "pstmt.setString("+x+",cleunik);";
        return string;
    }
   private String genereAttribut2(Object obj,String string,int x){ 
        String type=obj.toString();
        if(type.substring(0,3).equals("tin") || type.substring(0,3).equals("int") || type.substring(0,3).equals("med"))
            return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(result.getInt("+x+"));";
        if(type.substring(0,3).equals("big"))
            return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(result.getLong("+x+"));";
        if(type.substring(0,3).equals("dou"))
              return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(result.getDouble("+x+"));";
        if(type.substring(0,3).equals("flo"))
             return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(result.getFloat("+x+"));";
        if(type.substring(0,3).equals("dat"))
            return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(new srcastra.astra.sys.classetransfert.utils.Date(result.getString("+x+")));";
        if(type.substring(0,3).equals("cha") || type.substring(0,3).equals("var") || type.substring(0,3).equals("tex"))
             return "obj.set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(result.getString("+x+"));";
        return string;
    }
 private void getTableDesc(String table) throws SQLException{
       PreparedStatement prep=con.prepareStatement("DESC "+table+";");
       ResultSet result=prep.executeQuery();
       result.beforeFirst();
       String text="null";
       desctable=new ArrayList();
       while(result.next()){
           String[] obj=new String[2];
           obj[0]=result.getObject(1).toString();
           obj[1]=result.getObject(2).toString();
           desctable.add(obj); 
       }   
 }
 String reqinsert;
 String reqmodif;
 String reqdelete;
 String reqget;
 String reqgetAll;
 String reqgetAll2;
 ArrayList desctable;
 String fin="} \ncatch(SQLException sn){\n"+
            "Transaction.rollback(tmpool.getConuser());\n"+
            "String[] message=new String[1];\n"+
           " message[0]=java.util.ResourceBundle.getBundle(\"srcastra.astra.locale.alertmessage\", tmpool.getLangage()).getString(\"fr_doublon\");\n"+           
           " ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());\n"+          
        "}\n";
}
