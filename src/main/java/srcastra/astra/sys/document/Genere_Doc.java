/*
 * Genere_Doc.java
 *
 * Created on 9 juillet 2002, 14:24
 */

package srcastra.astra.sys.document;
import java.sql.*;
//import javax.xml.parsers.DocumentBuilder; 
//import javax.xml.parsers.DocumentBuilderFactory;  
//import javax.xml.parsers.FactoryConfigurationError;  
//import javax.xml.parsers.ParserConfigurationException;
import srcastra.astra.sys.rmi.utils.*;
//import org.w3c.dom.*;
import java.util.*;
import java.io.*;
import java.util.zip.*;
/**
 *
 * @author  Administrateur
 * @version 
 */
public class Genere_Doc extends java.lang.Object implements java.io.Externalizable,Genere_Doc_Int{

    /** Creates new Genere_Doc */
    public Genere_Doc(int key,Poolconnection userPool,int objCleunik) {
        super();
        this.objCleunik=objCleunik;
        this.userPool=userPool;
        this.typeRequete=key;
       // Connection con=connectdb2();
        RequestTable=new ArrayList();
        String[] balise=new String[2];
        String[] requeteTab=new String[1];
        balise[0]="allFournisseur";
        balise[1]="fournisseur";
        requeteTab[0]="SELECT f.frreference1,f.frreference2,f.frnom1,f.frnom2,f.fradresse,"
                                        +"f.frtvanum,f.frtvatype,f.frtvaregime,f.frnumbanque1,f.frnumbanque2,"
                                        +"f.frnumbanque3,f.frtelephone1,f.frfax,f.frmail,c.cxcode,ct.txtraduction,"
                                        +"p.pysabrev,pt.pystraduction from fournisseur f,codepostaux c,traductioncodpostaux ct,"
                                        +"pays p,traductionpays pt where f.cxcleunik=c.cxcleunik and c.cxcleunik=ct.cxcleunik and "
                                        +"f.pyscleunik=p.pyscleunik and p.pyscleunik=pt.pyscleunik and pt.lmcleunik=? and ct.lmcleunik=?;";                                     
        RequestTable.add(0,new Requete(this.PRINT_ALL_FOURNISSEUR,balise,requeteTab,1,2));
        String[] balise1=new String[2];
        String[] requeteTab1=new String[1];
        balise1[0]="allCodePostaux";
        balise1[1]="infocodepostaux";
        requeteTab1[0]="select c.cxcode,tx.txtraduction"
                         +" from codepostaux c,traductioncodpostaux tx"
                         +" where c.cxcleunik=tx.cxcleunik"
                         +" and tx.lmcleunik=?"
                         +" order by cxcode;";
        RequestTable.add(1,new Requete(this.PRINT_CODE_POSTAUX,balise1,requeteTab1,1,1));
        String[] balise2=new String[5];
        String[] requeteTab2=new String[3];
        balise2[0]="oneFournisseur";
        balise2[1]="fournisseur";
        balise2[2]="infogenerale";
        balise2[3]="document";
        balise2[4]="produit";
        requeteTab2[0]=" SELECT f.frreference1,f.frreference2,f.frnom1,f.frnom2,f.fradresse,"
                                        +"f.frtvanum,f.frtvatype,f.frtvaregime,f.frnumbanque1,f.frnumbanque2,"
                                        +"f.frnumbanque3,f.frtelephone1,f.frfax,f.frmail,f.frmodecccf,f.frdelaipaienbrjour,f.frdomiciliation,f.frmemo,"
                                        +"f.frdatetimecrea,c.cxcode,ct.txtraduction,"
                                        +"p.pysabrev,pt.pystraduction from fournisseur f,codepostaux c,traductioncodpostaux ct,"
                                        +"pays p,traductionpays pt where f.cxcleunik=c.cxcleunik and c.cxcleunik=ct.cxcleunik and "
                                        +"f.pyscleunik=p.pyscleunik and p.pyscleunik=pt.pyscleunik and pt.lmcleunik=? and ct.lmcleunik=? and f.frcleunik=?;";
       requeteTab2[1]="SELECT frdtnbrdocprev,frdtnbrconfprev,"
                                        +"frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev from "
                                        +" fournisseur_document where frcleunik=?";
       requeteTab2[2]="SELECT frgtitrecatalog,frgtreference1,frgtreference2 "
                                        +"from fournisseur_grproduit where frgtcleunik=?;";   
       RequestTable.add(2,new Requete(this.PRINT_ONE_FOURNISSEUR,balise2,requeteTab2,3,1));
       CompressDoc(makeDocument(key));    
    }
    public Genere_Doc() {
        super();
    }
        private String  makeDocument(int key)
        {          
            Requete tmpreq=(Requete)this.RequestTable.get(key);
            ResultSet tmpResult;
            String tmp="<"+tmpreq.getBalise(0)+">";//+"<"+tmpreq.getBalise(1)+">";
            int nbrligne;
            try{
                 this.docNotEmpty=false;
                for(int i=0;i<tmpreq.getNbrRequete();i++)
                {
                      tmpResult=appel_requete(this.userPool,key,tmpreq.getNbrArgument(),tmpreq.getLesRequetes(i));
                      ResultSetMetaData ResutlMeta=tmpResult.getMetaData();
                      int colcount=ResutlMeta.getColumnCount();
                      tmpResult.beforeFirst();   
                      if(tmpreq.getNbrRequete()>1)
                      tmp=tmp+"<"+tmpreq.getBalise(i+2)+">";
                      while (tmpResult.next())
                      {   tmp= tmp+"<"+tmpreq.getBalise(1)+">";
                          this.docNotEmpty=true;
                          for(int j=1;j<=colcount;j++)
                          {
                              tmp=tmp+"<"+ResutlMeta.getColumnName(j)+">";
                              tmp=tmp+tmpResult.getObject(j).toString();
                              tmp=tmp+"</"+ResutlMeta.getColumnName(j)+">";
                          }
                          tmp= tmp+"</"+tmpreq.getBalise(1)+">";
                      }
                      if(tmpreq.getNbrRequete()>1)
                      tmp=tmp+"</"+tmpreq.getBalise(i+2)+">";
                }   
                  tmp=tmp+"</"+tmpreq.getBalise(1)+">";//+"</"+tmpreq.getBalise(0)+">";    
            }catch(SQLException e)
            {
               e.printStackTrace(); 
            }
            return tmp;
        }
        private ResultSet appel_requete(Poolconnection userpool,int key,int nbrargument,String req)
        {
            ResultSet Result=null;
            try{
               PreparedStatement pstmt = userpool.getConuser().prepareStatement(req);
               for(int i=0;i<nbrargument;i++)
               {
               pstmt.setInt((i+1), this.objCleunik);
               }
                Result=pstmt.executeQuery();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
            return Result;
 
        }
        private void affiche_requete(ResultSet Result)
        {
             int nbrligne;
             try{
                    Result.last();
                    nbrligne=Result.getRow();
                    if(nbrligne!=0)
                       {   
                        Result.beforeFirst();
                        while(Result.next())
                        {
                        System.out.println(Result.getString(1)); 
                       }     
                    }        
                }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
       }
       private Object CompressDoc(String doc)
       {
        Object tmpObj=null;
        try {
              ByteArrayOutputStream ByteOut=new ByteArrayOutputStream();
              DeflaterOutputStream defOut=new DeflaterOutputStream(ByteOut);
              ObjectOutputStream ObjOut=new ObjectOutputStream(defOut);
              if(this.docNotEmpty==true)
              {
                  System.out.println(doc);
                  tmpObj=doc;
                  ObjOut.writeObject(tmpObj);
                  ObjOut.flush();
                  ObjOut.close();
                  this.TabByte=ByteOut.toByteArray();
                  this.sizeOfTabByte=this.TabByte.length;
              }
              else
              {
                 ObjOut.close(); 
              }
             
        } 
         catch(IOException e1)
         {
             e1.printStackTrace();
         }
          return document;
       }
       public Object getDocument()
       {
           return this.document;
       }
       private void decompressDoc(byte[] tabin)
       {
          try{
              ByteArrayInputStream ByteIn=new  ByteArrayInputStream(tabin);
              InflaterInputStream defIn=new InflaterInputStream(ByteIn);
              ObjectInputStream ObjIn=new ObjectInputStream(defIn); 
              this.document=ObjIn.readObject();
              ObjIn.close();
          }
          catch(IOException e)
          {
             e.printStackTrace(); 
          }
          catch(ClassNotFoundException e1)
          {
             e1.printStackTrace(); 
          }
       }
       
       public void readExternal(java.io.ObjectInput objectInput) throws java.io.IOException, java.lang.ClassNotFoundException {
            byte[] tmpByte;
            this.docNotEmpty=objectInput.readBoolean();
            if(this.docNotEmpty==true)
            {   
                this.sizeOfTabByte=objectInput.readInt();
                tmpByte=new byte[this.sizeOfTabByte];
                objectInput.readFully(tmpByte);
                decompressDoc(tmpByte);
            }
       }
       
       public void writeExternal(java.io.ObjectOutput objectOutput) throws java.io.IOException {   
           if(docNotEmpty=true)
           {
               objectOutput.writeBoolean(this.docNotEmpty); 
               objectOutput.writeInt(this.sizeOfTabByte);
               objectOutput.write(this.TabByte);
           }
           else
           {
              objectOutput.writeBoolean(this.docNotEmpty);  
           }
       }
       public void affiche_doc()
       {
           System.out.println(this.document.toString());
       }
       public boolean getDocumentValide()
       {
           return this.docNotEmpty;
       }
       //classe contenant les requetes et information sur les requetes
               public class Requete
               {
                   public Requete(int TypeRequete,String[] balise,String[] lesRequetes,int nbrRequete,int nbrArgument)
                   {
                       this.TypeRequete=TypeRequete;
                       this.balise=balise;
                       this.lesRequetes=lesRequetes;
                       this.nbrRequete=nbrRequete;
                       this.nbrArgument=nbrArgument;
                   }

                   public java.lang.String getBalise(int index) {
                       return balise[index];
                   }
                   public java.lang.String[] getBalise() {
                       return balise;
                   }
                   public void setBalise(int index, java.lang.String balise) {
                       this.balise[index] = balise;
                   }
                   public void setBalise(java.lang.String[] balise) {
                       this.balise = balise;
                   }
                   public java.lang.String getLesRequetes(int index) {
                       return lesRequetes[index];
                   }
                   public java.lang.String[] getLesRequetes() {
                       return lesRequetes;
                   }
                   public int getNbrRequete() {
                       return nbrRequete;
                   }

                   public int getRights() {
                       return rights;
                   }
                   public int getTypeRequete() {
                       return TypeRequete;
                   }  
                   public int getNbrArgument()
                   {
                      return this.nbrArgument; 
                   }
                   private String[] balise;
                   private int TypeRequete;
                   private String[] lesRequetes;
                   private int rights;
                   private int nbrRequete;
                   private int nbrArgument;
               }
//static Hashtable RequestTable;
static ArrayList RequestTable;
private boolean docNotEmpty;
private Object document;
private byte[] TabByte;
private int sizeOfTabByte;
private Poolconnection userPool;
private int objCleunik;
int typeRequete;

    
    
}
