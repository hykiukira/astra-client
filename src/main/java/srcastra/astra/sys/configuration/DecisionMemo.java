/*
 * Decision.java
 *
 * Created on 16 januari 2003, 16:03
 */

package srcastra.astra.sys.configuration;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import java.sql.*;
import java.util.Hashtable;
import srcastra.astra.gui.modules.aidedesicion.*;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.*;
import java.util.*;

/**
 *
 * @author  Thomas
 */
public class DecisionMemo extends AbstractDecision{
    
    /** Creates a new instance of Decision */
    public DecisionMemo(AbstractBuffer dectree) {
        block=new Hashtable();
        block.put(new Integer(produit_T.AS),new Object());
        block.put(new Integer(produit_T.AV),new Object());
        block.put(new Integer(produit_T.BA),new Object());
        block.put(new Integer(produit_T.BRO),new Object());
        block.put(new Integer(produit_T.HO),new Object());
        block.put(new Integer(produit_T.TAX),new Object());
        block.put(new Integer(produit_T.TR),new Object());
        block.put(new Integer(produit_T.VO),new Object());
        this.dectree=dectree;
            
      
        
       // pstmt=tmpool.getConuser().prepareStatement("INSERT INTO aidedecision (cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?)");
        
    }
    public Object insert(Object obj, Poolconnection tmpool) throws java.sql.SQLException{
        return null;
       
   }
    public Object delete(int id, Poolconnection tmpool,int type) throws java.sql.SQLException {
           checkDectree(tmpool);
            synchronized(dectree){
               Transaction.begin(tmpool.getConuser());
               pstmt=tmpool.getConuser().prepareStatement("DELETE FROM aidedecisionmemo  WHERE aid_cleunik =?");
               pstmt.setInt(1,id);
               pstmt.execute();
               pstmt=tmpool.getConuser().prepareStatement("DELETE FROM aidedecision_traduction_memo  WHERE aid_cleunik =?");
               pstmt.setInt(1,id);
               pstmt.execute();
               dectree.removeChildren(new Integer(id).toString(),type); 
               Transaction.commit(tmpool.getConuser());
               dectree.afficheMe();
               return new Insert_T(id,dectree.getTimestamp()); 
            }
    } 
    
    /** Creates a new instance of Decision  */
    public Object insert(Object obj, Poolconnection tmpool, java.util.ArrayList data, int id2, int my_type) throws java.sql.SQLException{
        checkDectree(tmpool);
        Transaction.begin(tmpool.getConuser());
        pstmt=tmpool.getConuser().prepareStatement("INSERT INTO aidedecisionmemo (cate_prod) VALUES (?)");
        pstmt.setInt(1,my_type);
        pstmt.execute();
        pstmt=tmpool.getConuser().prepareStatement("select LAST_INSERT_ID();");
        ResultSet result=pstmt.executeQuery();
        result.first();
        int id=result.getInt(1);
        pstmt=tmpool.getConuser().prepareStatement("INSERT INTO aidedecision_traduction_memo(aid_cleunik,cate_prod ,lmcleunik,Traduction)   VALUES(?,?,?,?)");
        Object[] first=(Object[])obj;
        for(int i=0;i<data.size();i++){
            Object[] second=(Object[])data.get(i);
            second[0]=new Integer(id);
            pstmt.setInt(1,id);
            pstmt.setInt(2,my_type);
            pstmt.setInt(3,((Integer)second[2]).intValue());
            pstmt.setString(4,second[3].toString());
            pstmt.execute();            
        }
        synchronized(dectree){
            dectree.setData(data,id,my_type);
        }
        Transaction.commit(tmpool.getConuser());
        dectree.afficheMe();
     return new Insert_T(id,dectree.getTimestamp());
    }
    
    public Object modify(Object obj, Poolconnection tmpool, ArrayList data) throws java.sql.SQLException{
        checkDectree(tmpool);
        Transaction.begin(tmpool.getConuser());
        pstmt=tmpool.getConuser().prepareStatement("UPDATE aidedecision_traduction_memo set Traduction=? WHERE aid_cleunik =? AND lmcleunik=?");
        int my_type=0;
        int id=0;
        for(int i=0;i<data.size();i++){
            Object[] first=(Object[])data.get(i);
            id=((Integer)first[0]).intValue();
            int langue=((Integer)first[2]).intValue();
            my_type=((Integer)first[1]).intValue();
            pstmt.setString(1,first[3].toString());
            pstmt.setInt(2,id);
            pstmt.setInt(3,langue);
            pstmt.execute();            
        }
        synchronized(dectree){
            dectree.setData(data,id,my_type);
        }
        Transaction.commit(tmpool.getConuser());
        dectree.afficheMe();
        return new Insert_T(id,dectree.getTimestamp());
    }
    
    public Object select(Poolconnection tmpool,long timestamp) throws java.sql.SQLException{
       checkDectree(tmpool);
        if(dectree!=null){
             synchronized(dectree){
                 if(timestamp==dectree.getTimestamp()){
                     System.out.println("ok same timestamp");
                    //return new Integer(0);
                     dectree.afficheMe();
                     return dectree;
                 }
                 else{
                     System.out.println("dectree back");
                     dectree.afficheMe();
                     return dectree;
                 }
             }
        }
        else 
        {
            System.out.println("tree == null");
            return null;
        }
    }
    private void renvArraylist(ResultSet result){
        if(result!=null);
           
        
        
    }
    public void synchonizeUsers() {
    }
    
    public void initDecisionTree(Poolconnection tmpool, AbstractBuffer dectree) throws SQLException{
         ArrayList array=null;
         for(int i=1;i<9;i++){
            array=new ArrayList();
            pstmt=tmpool.getConuser().prepareStatement("SELECT * FROM aidedecision_traduction_memo  WHERE cate_prod =? ORDER BY aid_cleunik,lmcleunik");
            pstmt.setInt(1,i);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            int q=1;
            while (result.next()){
             Object[] tmp=new Object[5];
             tmp[0]=result.getObject(1);
             tmp[1]=result.getObject(2);
             tmp[2]=result.getObject(3);
             tmp[3]=result.getObject(4);  
             tmp[4]=new Integer(q);
             q++;
             //tmp[4]=result.getObject(5);  
             array.add(tmp);             
            } 
            for(int k=0;k<array.size();k++)
            {
              Object[] test=(Object[])array.get(k);
              System.out.println("array "+" "+test[0]+"  "+test[1]+" "+test[2]+" "+test[3]);
            }
            getTheArrays2(array,i);
            
        }
         dectree.afficheMe();
    }
    private ArrayList getTheArrays(ArrayList main,int type_produit){
        Object first=null;
        ArrayList tmp=null;
        int j=main.size();  
         if(main!=null && main.size()>0){
            for(int i=0;i<main.size();i++){
                tmp=new ArrayList();
                first=(((Object[])main.get(i))[0]);
                tmp.add((Object[])main.get(i));
                i++;
                if(i<j){
                    while(first.equals(((Object[])main.get(i))[0])){
                        tmp.add((Object[])main.get(i)); 
                        i++;
                        if(i==j)
                            break;
                    }
                }
                dectree.setData(tmp,((Integer)first).intValue(),type_produit);                        
         }   
         }       
         return null;
        }
    private ArrayList getTheArrays2(ArrayList main,int type_produit){
        Object[] first=null;
        Object[] second=null;
        ArrayList tmp=null;
        int x1;
        int x2;
        int j=main.size();  
        
         if(main!=null && main.size()>0){
            for(int i=0;i<main.size();i++){
                 tmp=new ArrayList();
                 first=(Object[])main.get(i);
                 x1=((Integer)first[0]).intValue();
                 tmp.add(first);
                do{
                    second=null;
                    i++;
                    second=(Object[])main.get(i);
                    x2=((Integer)second[0]).intValue();
                    if(x1==x2)
                        tmp.add(second);
                    else{ 
                        i=i-1;
                        break;   
                    }
                }while(x1==x2 && i!=j-1);
                 dectree.setData(tmp,x1,type_produit);      
                 if(i==j-1)
                     break;                      
         }   
         }       
         return null;
        }
         
private void checkDectree(Poolconnection tmpool) throws SQLException {
 synchronized(new Object()){
 if(dectree==null){
    dectree=new DecFatory().getBuffer();
    initDecisionTree(tmpool,dectree);    
 }
 }
 
}
            
           
            
            
        
    
    
Poolconnection m_pool;  
PreparedStatement pstmt;
Hashtable block;
AbstractBuffer dectree;
}
