package srcastra.astra.sys.classetransfert.configuration;
/**** @author  Thomas Automatique process class generation*/
 public class Caisselibelle_T implements java.io.Serializable{
     int cale_cleunik;
     String cale_lib1;
     String cale_lib2;
     int cale_categorie;
     int ce_cleunik;
     String comptetiers;
public int getCale_cleunik(){
      return this.cale_cleunik;
}

public void setCale_cleunik(int cale_cleunik){
    this.cale_cleunik=cale_cleunik;
}
public String getCale_lib1(){
      return this.cale_lib1;
}

public void setCale_lib1(String cale_lib1){
    this.cale_lib1=cale_lib1;
}
public String getCale_lib2(){
      return this.cale_lib2;
}

public void setCale_lib2(String cale_lib2){
    this.cale_lib2=cale_lib2;
}
public int getCale_categorie(){
      return this.cale_categorie;
}

public void setCale_categorie(int cale_categorie){
    this.cale_categorie=cale_categorie;
}
public int getCe_cleunik(){
      return this.ce_cleunik;
}
 
public void setCe_cleunik(int ce_cleunik){
    this.ce_cleunik=ce_cleunik;
}

/** Getter for property comptetiers.
 * @return Value of property comptetiers.
 */
public java.lang.String getComptetiers() {
    return comptetiers;
}

/** Setter for property comptetiers.
 * @param comptetiers New value of property comptetiers.
 */
public void setComptetiers(java.lang.String comptetiers) {
    this.comptetiers = comptetiers;
}

public final static int CLIENT=1;
public final static int FOURNISSEUR=2;
public final static int GENE=3;
}