/*
 * Task_manage.java
 *
 * Created on 12 mars 2004, 11:03
 */

package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.Task_T;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import java.util.ArrayList;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.gui.modules.agenda.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class Task_manage {
    
    /** Creates a new instance of Task_manage */
    public Task_manage() {
    }
    public void insertTask(Task_T task,Poolconnection tmpool) throws SQLException{
        //                                  1           2           3               4           5           6               7          8        9           10         11           12      13          14          15      
        String insert="INSERT INTO task (dr_cleunik,task_numdos,cscleunikcont,cscleunikfact,task_object,task_echeance,task_debut,task_etat,task_rapelle,task_auto,task_envois,task_memo,task_message,urcleunik,annuler) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(insert);
        pstmt.setLong(1, task.getDr_cleunik());
        pstmt.setString(2, task.getTask_numdos());
        pstmt.setLong(3, task.getCscleunikcont());
        pstmt.setLong(4, task.getCscleunifact());
        pstmt.setString(5, task.getTask_object());
        pstmt.setString(6, task.getTask_echeance().toString());
        pstmt.setString(7, task.getTask_debut().toString());
        pstmt.setInt(8, task.getTask_etat());
        pstmt.setInt(9, task.getTask_rappelle());
        pstmt.setInt(10, task.getTask_auto());
        pstmt.setInt(11, task.getTask_envois());
        pstmt.setString(12, task.getTask_memo());
        pstmt.setString(13, task.getTask_message());
        pstmt.setInt(14, tmpool.getUrcle2());
        pstmt.setInt(15, task.getAnnuler());
        pstmt.execute();
    }
    public void modifyTask(Task_T task,Poolconnection tmpool)throws SQLException{
        String modif="UPDATE task SET dr_cleunik=?,task_numdos=?,cscleunikcont=?,cscleunikfact=?,task_object=?,task_echeance=?,task_debut=?,task_etat=?,task_rapelle=?,task_auto=?,task_envois=?,task_memo=?,task_message=?,urcleunik=?,annuler=? WHERE task_id=?";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(modif);
        pstmt.setLong(1, task.getDr_cleunik());
        pstmt.setString(2, task.getTask_numdos());
        pstmt.setLong(3, task.getCscleunikcont());
        pstmt.setLong(4, task.getCscleunifact());
        pstmt.setString(5, task.getTask_object());
        pstmt.setString(6, task.getTask_echeance().toString());
        pstmt.setString(7, task.getTask_debut().toString());
        pstmt.setInt(8, task.getTask_etat());
        pstmt.setInt(9, task.getTask_rappelle());
        pstmt.setInt(10, task.getTask_auto());
        pstmt.setInt(11, task.getTask_envois());
        pstmt.setString(12, task.getTask_memo());
        pstmt.setString(13, task.getTask_message());
        pstmt.setInt(14, task.getUrcleunik());
        pstmt.setInt(15, task.getAnnuler());
        pstmt.setLong(16, task.getTask_id());
        pstmt.execute();
    }   
    public java.util.ArrayList getList(srcastra.astra.sys.classetransfert.utils.Date date,Poolconnection tmpool)throws SQLException{
        ArrayList retval=null;
        String modif="SELECT t.dr_cleunik,t.task_numdos,t.cscleunikcont,t.cscleunikfact,t.task_object,t.task_echeance,t.task_debut,t.task_etat,t.task_rapelle,t.task_auto,t.task_envois,t.task_memo,t.task_message,t.urcleunik,t.annuler, e.eeabrev,t.task_id,c1.csnom,c2.csnom FROM task t LEFT JOIN clients c1 ON(t.cscleunikcont=c1.cscleunik) LEFT JOIN clients c2 ON(t.cscleunikfact=c2.cscleunik) ,entite e,user u WHERE ((task_debut<? AND task_etat!=3) OR (task_debut=?)) AND t.annuler=0 AND t.urcleunik=u.urcleunik AND u.eecleunik=e.eecleunik AND e.eecleunik=?";
        if(date==null || date.isOpen() || date.isUnknow())
            return null;
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(modif);
        date.setMinutes(0);
        date.setHours(0);
        date.setSeconds(0);
        pstmt.setString(1,date.toString());
        pstmt.setString(2,date.toString());
        pstmt.setInt(3,tmpool.getNumeroentite());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
            if(retval==null)
                retval=new ArrayList();
            Task_T task=new Task_T();
            task.setDr_cleunik(result.getLong(1));
            task.setTask_numdos(result.getString(2));
            task.setCscleunikcont(result.getLong(3));
            task.setCscleunifact(result.getLong(4));
            task.setTask_object(result.getString(5));
            task.setTask_echeance(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(6)));
            task.setTask_debut(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(7)));
            task.setTask_etat(result.getInt(8));
            task.setTask_rappelle(result.getInt(9));
            task.setTask_auto(result.getInt(10));
            task.setTask_envois(result.getInt(11));
            task.setTask_memo(result.getString(12));
            task.setTask_message(result.getString(13));
            task.setUrcleunik(result.getInt(14));
            task.setAnnuler(result.getInt(15));
            task.setEeabrev(result.getString(16));     
            task.setTask_id(result.getLong(17));      
            task.setCscontname(result.getString(18));
            task.setCsfactname(result.getString(19));
            retval.add(task);
        }
        return retval;
    }
    private String getMonth(int month,int year,Loginusers_T user){
        String months="";
     if ( month == 0)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("jan")+" "+year;
      else  if (month== 1)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("feb")+" "+year;
      else  if (month== 2)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("mar")+" "+year;
      else  if (month== 3)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("avri")+" "+year;
      else  if (month == 4)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("mai")+" "+year;
      else  if (month == 5)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("jui")+" "+year;
      else  if (month == 6)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("juill")+" "+year;
      else  if (month == 7)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("aou")+" "+year;
      else  if (month == 8)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("sep")+" "+year;
      else  if (month == 9)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("oct")+" "+year;
      else  if (month == 10)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("nov")+" "+year;
      else  if (month == 11)  
            months=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("dec")+" "+year;
        return months;
    }
    public Calendar_T getArray(srcastra.astra.sys.classetransfert.utils.Date date,Loginusers_T user,Poolconnection tmpool)throws SQLException{      
        ArrayList array=new ArrayList();
        java.util.Calendar c=java.util.Calendar.getInstance();
        c.set(date.getYear(), date.getMonth()-1, 1);
        int month=date.getMonth();
        srcastra.astra.sys.classetransfert.utils.Date datedeb=new srcastra.astra.sys.classetransfert.utils.Date();
        srcastra.astra.sys.classetransfert.utils.Date datefin=new srcastra.astra.sys.classetransfert.utils.Date();
        
        datedeb.setDay(1);
        datedeb.setMonth(date.getMonth());
        datedeb.setYear(date.getYear());

        datefin.setDay(c.getActualMaximum(c.DAY_OF_MONTH));
        datefin.setMonth(date.getMonth());
        datefin.setYear(date.getYear());
        
        String requete="SELECT task_debut FROM task WHERE task_debut BETWEEN  ? AND  ?";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
        pstmt.setString(1, datedeb.toString());
        pstmt.setString(2, datefin.toString());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        Hashtable hash=new Hashtable();
        while(result.next()){
          srcastra.astra.sys.classetransfert.utils.Date datetmp=new  srcastra.astra.sys.classetransfert.utils.Date(result.getString(1));
          if(datetmp!=null)
              hash.put(new Integer(datetmp.getDay()),"");         
        }
        
        for(int i=0;i<7;i++){
            Object[] tmp=new Object[7];
            if(i==0)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("lun");
            else if(i==1)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("ma");
            else if(i==2)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("mer");
            else if(i==3)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("jeu");
            else if(i==4)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("ven");
            else if(i==5)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("sam");
            else if(i==6)
                tmp[0]=java.util.ResourceBundle.getBundle("srcastra/astra/sys/ServeurBundle", user.getLangage()).getString("dim");            
            array.add(tmp);            
        }     
       
        int commence = c.get(c.DAY_OF_WEEK); //gets the day of the week 1 sunday, 2 monday, etc...
        int day = 1;
        int j = commence - 2;
        if (commence == 1)
            commence = 8; 
        for (int i=1; (day <= c.getActualMaximum(c.DAY_OF_MONTH) && i<=6); i++){
            j = commence - 2;
            while (j<=6 && day <= c.getActualMaximum(c.DAY_OF_MONTH) ){
                 Object[] tab=(Object[])array.get(j);
                 CalendarDay_T dayc=new CalendarDay_T();
                 dayc.setDay(day);
                 if(hash.get(new Integer(day))==null)
                     dayc.setTask(false);
                 else
                     dayc.setTask(true);
                     
                 tab[i]=dayc;                 
                 day++;
                 j++;
            }
            commence = 2;      
        }
        Calendar_T retval=new Calendar_T();
        retval.setMonth(getMonth(c.get(c.MONTH),c.get(c.YEAR),user));
        retval.setVector(array);
        return retval;    
    }
}
