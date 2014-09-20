/*
 * LangueSystem.java
 *
 * Created on 25 avril 2002, 10:13
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  Administrateur
 * @version 
 */
public class LangueSystem extends srcastra.astra.sys.classetransfert.Gestionerreur_T implements java.io.Serializable {

    /** Creates new LangueSystem */
    public LangueSystem() {
        super();
        reinitialise();
    }
  /*  public void selectionLangue(int urlmcleunik,java.sql.Connection usercon,int cas)
    {
        java.sql.ResultSet reccord=null;
        java.sql.Statement select=null;
        int nbrLigne=0;
        String sqlrequete=null;
        reinitialise();
        if(cas==1)
           sqlrequete ="select  lmcleunik,lmintitule from languesystem order by lmcleunik";
        else if(cas==2)
            sqlrequete="select  lmcleunik,lmintitule from languesystem order by lmcleunik FOR UPDATE";
         try{
                select=usercon.createStatement();
                reccord=select.executeQuery(sqlrequete);// Add your handling 
                reccord.last();
                nbrLigne=reccord.getRow();
                if(nbrLigne!=0)
                {
                   reccord.beforeFirst();
                   while(reccord.next())
                   {
                       thNombreLangue++;
                       Object[] tmpobj=new Object[3];
                       tmpobj[0]=(Object)reccord.getObject(1);
                       tmpobj[1]=(Object)reccord.getObject(2); 
                       tmpobj[2]=null;
                       thTraduction.add(tmpobj);
                   }
                    
                }
           }catch(java.sql.SQLException e)
                            {   
                                System.out.println(e);
                                reccord=null;
                            }
    }
    public void associeTraduction(java.util.ArrayList data){
        int tailleArray;
        for(int i=0;i<this.thTraduction.size();i++)
        {
            Object[] tmpobj;
            tmpobj=(Object[])this.thTraduction.get(i);
            tmpobj[2]=(Object)data.get(i);
            this.thTraduction.set(i, tmpobj);
        }       
    }*/
    private void reinitialise()
    {
        thTraduction=null;
        thTraduction=new java.util.ArrayList(); 
        thNombreLangue=0;
    }/*
    private void testAffichage()
    {
       int tailleArray;
       tailleArray=thTraduction.size();
       for(int i=0;i<tailleArray;i++)
       {
           Object[] tmp;
           tmp=(Object[])thTraduction.get(i);
           System.out.println(tmp[0]+"   "+tmp[1]);
       }     
    }*/
    public void setdata(java.util.ArrayList tmpdata)
    {
        this.thTraduction=tmpdata;   
    }
    public java.util.ArrayList getdata()
    {
        return this.thTraduction;
    }
    public int getNbrLangue()
    {
        int tmpsize;
        tmpsize=this.thTraduction.size();
        return tmpsize;
    }
    
    private java.util.ArrayList thTraduction;
    private int thNombreLangue;
    private int urlmcleunik;
}
