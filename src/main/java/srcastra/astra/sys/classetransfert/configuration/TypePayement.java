package srcastra.astra.sys.classetransfert.configuration;

/**** @author  Thomas Automatique process class generation*/

 public class TypePayement implements java.io.Serializable{

     int tynt_cleunik;

     String tynt_libelle1;

     String tynt_libelle2;

     int tynt_categorie;

     int ce_cleunik;

     int old_ce_cleunik;

     String compteIntitule;

public int getTynt_cleunik(){

      return this.tynt_cleunik;

}



public void setTynt_cleunik(int tynt_cleunik){

    this.tynt_cleunik=tynt_cleunik;

}

public String getTynt_libelle1(){

      return this.tynt_libelle1;

}



public void setTynt_libelle1(String tynt_libelle1){

    this.tynt_libelle1=tynt_libelle1;

}

public String getTynt_libelle2(){

      return this.tynt_libelle2;

}



public void setTynt_libelle2(String tynt_libelle2){

    this.tynt_libelle2=tynt_libelle2;

}

public int getTynt_categorie(){

      return this.tynt_categorie;

}



public void setTynt_categorie(int tynt_categorie){

    this.tynt_categorie=tynt_categorie;

}

public int getCe_cleunik(){

      return this.ce_cleunik;

}



public void setCe_cleunik(int ce_cleunik){

    this.ce_cleunik=ce_cleunik;

}



/** Getter for property compteIntitule.

 * @return Value of property compteIntitule.

 */

public java.lang.String getCompteIntitule() {

    return compteIntitule;

}



/** Setter for property compteIntitule.

 * @param compteIntitule New value of property compteIntitule.

 */

public void setCompteIntitule(java.lang.String compteIntitule) {

    this.compteIntitule = compteIntitule;

}



/** Getter for property old_ce_cleunik.

 * @return Value of property old_ce_cleunik.

 */

public int getOld_ce_cleunik() {

    return old_ce_cleunik;

}



/** Setter for property old_ce_cleunik.

 * @param old_ce_cleunik New value of property old_ce_cleunik.

 */

public void setOld_ce_cleunik(int old_ce_cleunik) {

    this.old_ce_cleunik = old_ce_cleunik;

}



public final static int CASH=1;

public final static int CHEQUE=2;

public final static int CARD=3;

public final static int CCCF=4;

public final static int MCO=25;


}