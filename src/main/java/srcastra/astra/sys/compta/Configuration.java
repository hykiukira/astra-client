/*



 * Configuration.java



 *



 * Created on 7 février 2003, 9:50



 */







package srcastra.astra.sys.compta;



import java.sql.*;



import srcastra.astra.sys.rmi.*;



import srcastra.astra.sys.classetransfert.dossier.Dossier_T;



/**



 *



 * @author  Thomas



 */



public class Configuration {



    



    /** Creates a new instance of Configuration */



    public Configuration( Connection con,



        PreparedStatement pstmt,



        long cledossier,



        float tva,



        long cleproduit,



        long cleintervenant,       



        long cleCompte,



        int categorieCompte,



        astraimplement serveur,



        int urcleunik,



        int facture) {



            this.con=con;



            this.pstmt=pstmt;



            this.cledossier=cledossier;



            this.cleintervenant=cleintervenant;



            this.cleCompte=cleCompte;



            this.categorieCompte=categorieCompte;



            m_serveur=serveur;



            m_urcleunik=urcleunik;



            dossierfacture=facture;



    }

    public Configuration(){

        

    }



    



    /** Getter for property categorieCompte.



     * @return Value of property categorieCompte.



     */



    public int getCategorieCompte() {



        return categorieCompte;



    }



    



    /** Setter for property categorieCompte.



     * @param categorieCompte New value of property categorieCompte.



     */



  



    



    /** Getter for property cleCompte.



     * @return Value of property cleCompte.



     */



    public long getCleCompte() {



        return cleCompte;



    }



    



    /** Setter for property cleCompte.



     * @param cleCompte New value of property cleCompte.



     */







    public long getCledossier() {



        return cledossier;



    }



    



  



    



    /** Getter for property cleintervenant.



     * @return Value of property cleintervenant.



     */



    public long getCleintervenant() {



        return cleintervenant;



    }



    



    



    



    /** Getter for property cleproduit.



     * @return Value of property cleproduit.



     */



    public long getCleproduit() {



        return cleproduit;



    }



    



    /** Setter for property cleproduit.



     * @param cleproduit New value of property cleproduit.



     */



 



    public java.sql.Connection getCon() {



        return con;



    }



    



    /** Setter for property con.



     * @param con New value of property con.



     */







    public java.sql.PreparedStatement getPstmt() {



        return pstmt;



    }



    



    /** Setter for property pstmt.



     * @param pstmt New value of property pstmt.



     */



 



    public float getTva() {



        return tva;



    }



    



    /** Getter for property m_urcleunik.



     * @return Value of property m_urcleunik.



     */



    public int getM_urcleunik() {



        return m_urcleunik;



    }



    



    /** Getter for property dossierfacture.



     * @return Value of property dossierfacture.



     */



    public int getDossierfacture() {



        return dossierfacture;



    }



    



    /** Getter for property m_serveur.



     * @return Value of property m_serveur.



     */



    public srcastra.astra.sys.rmi.astraimplement getM_serveur() {



        return m_serveur;



    }    



  



    /** Setter for property m_serveur.



     * @param m_serveur New value of property m_serveur.



     */



    public void setM_serveur(srcastra.astra.sys.rmi.astraimplement m_serveur) {



        this.m_serveur = m_serveur;



    }    



  



    /** Setter for property cleproduit.



     * @param cleproduit New value of property cleproduit.



     */



    public void setCleproduit(long cleproduit) {



        this.cleproduit = cleproduit;



    }    



    



    /** Getter for property dossier.



     * @return Value of property dossier.



     */



    public Dossier_T getDossier() {



        return dossier;



    }



    



    /** Setter for property dossier.



     * @param dossier New value of property dossier.



     */



    public void setDossier(Dossier_T dossier) {



        this.dossier = dossier;



    }



    /** Getter for property info.

     * @return Value of property info.

     */

    public srcastra.astra.sys.compta.InfoCompta getInfo() {

        return info;

    }    

    

    /** Setter for property info.

     * @param info New value of property info.

     */

    public void setInfo(srcastra.astra.sys.compta.InfoCompta info) {

        this.info = info;

    }    



    /** Setter for property m_urcleunik.

     * @param m_urcleunik New value of property m_urcleunik.

     */

    public void setM_urcleunik(int m_urcleunik) {

        this.m_urcleunik = m_urcleunik;

    }

    

    /** Getter for property cle2.

     * @return Value of property cle2.

     */

    public int getCle2() {

        return cle2;

    }

    

    /** Setter for property cle2.

     * @param cle2 New value of property cle2.

     */

    public void setCle2(int cle2) {

        this.cle2 = cle2;

    }

    

    /** Getter for property eecleunik.

     * @return Value of property eecleunik.

     */

    public int getEecleunik() {

        return eecleunik;

    }

    

    /** Setter for property eecleunik.

     * @param eecleunik New value of property eecleunik.

     */

    public void setEecleunik(int eecleunik) {

        this.eecleunik = eecleunik;

    }

    

    /** Getter for property numnc.

     * @return Value of property numnc.

     */

    public java.lang.String getNumnc() {

        return numnc;

    }

    

    /** Setter for property numnc.

     * @param numnc New value of property numnc.

     */

    public void setNumnc(java.lang.String numnc) {

        this.numnc = numnc;

    }

    

    /** Getter for property typeProduit.

     * @return Value of property typeProduit.

     */

    public int getTypeProduit() {

        return typeProduit;

    }

    

    /** Setter for property typeProduit.

     * @param typeProduit New value of property typeProduit.

     */

    public void setTypeProduit(int typeProduit) {

        this.typeProduit = typeProduit;

    }

    /** Setter for property cleintervenant.
     * @param cleintervenant New value of property cleintervenant.
     *
     */
    public void setCleintervenant(long cleintervenant) {
        this.cleintervenant = cleintervenant;
    }    
    
    /** Getter for property typeintervenant.
     * @return Value of property typeintervenant.
     *
     */
    public int getTypeintervenant() {
        return typeintervenant;
    }    

    /** Setter for property typeintervenant.
     * @param typeintervenant New value of property typeintervenant.
     *
     */
    public void setTypeintervenant(int typeintervenant) {
        this.typeintervenant = typeintervenant;
    }
    
    /** Setter for property m_urcleunik.



     * @param m_urcleunik New value of property m_urcleunik.



     */



  



   



        Connection con;
        PreparedStatement pstmt;
        long cledossier;
        int cle2;
        float tva;
        long cleproduit;
        long cleintervenant;       
        long cleCompte;
        int categorieCompte;
        astraimplement m_serveur;
        int m_urcleunik;
        int dossierfacture;
        int eecleunik;
        Dossier_T dossier;
        InfoCompta info;
        String numnc;
        int typeProduit;
        int typeintervenant=1;



    



}



