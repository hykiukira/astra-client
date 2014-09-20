/*

 * manageDoc.java

 *

 * Created on 23 janvier 2003, 15:03

 */



package srcastra.astra.sys.classetransfert.utils;

import java.sql.*;

import srcastra.astra.sys.classetransfert.dossier.*;

/**

 *

 * @author  Thomas

 */

public class manageDoc {

    

    /** Creates a new instance of manageDoc */

    public manageDoc() {

    }

    public static void gereDocument(produit_T produit,Connection con,PreparedStatement pstmt) throws SQLException{
         if(produit.getDoc()!=null)
         {
             srcastra.astra.sys.classetransfert.Document_T doc=produit.getDoc();
             if(doc.isModify() && !doc.isFromProduct()){
                pstmt=con.prepareStatement( " INSERT INTO produit_document (cate_pro,lignecleunik,frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev) VALUES ( ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?)");

                pstmt.setInt(1,produit.getTypeDeProduitCleunik());

                pstmt.setLong(2,produit.getAt_cleunik());

                pstmt.setInt(3,doc.getFrdtnbrdocprev());

                pstmt.setInt(4,doc.getFrdtnbrconfprev());

                pstmt.setInt(5,doc.getFrdtnbrfactprev());

                pstmt.setInt(6,doc.getFrdtnbrcprev());

                pstmt.setInt(7,doc.getFrdtnbrfactsprev());  

                pstmt.execute();

             }

             else if(doc.isModify() && doc.isFromProduct()){

                pstmt=con.prepareStatement( " UPDATE produit_document set frdtdatetimemodif=NOW(),frdtnbrdocprev=?,frdtnbrconfprev=?,frdtnbrfactprev=?,frdtnbrncprev=?,frdtnbrfactsprev=? WHERE prdt_cleunik =?");

                pstmt.setInt(1,doc.getFrdtnbrdocprev());

                pstmt.setInt(2,doc.getFrdtnbrconfprev());

                pstmt.setInt(3,doc.getFrdtnbrfactprev());

                pstmt.setInt(4,doc.getFrdtnbrcprev());

                pstmt.setInt(5,doc.getFrdtnbrfactsprev()); 

                pstmt.setLong(6,doc.getFrdtcleunik()); 

                pstmt.execute();

             }            

         }        

     }

    

}

