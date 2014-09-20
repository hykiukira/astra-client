/*
 * ProduitSynthese.java
 *
 * Created on 26 mars 2003, 9:34
 */

package srcastra.astra.sys.classetransfert.dossier;

/**
 *
 * @author  Thomas
 */
public interface ProduitSynthese {
  public String getLogement();
  public String getPnr();
  public String getDestination();
  public srcastra.astra.sys.classetransfert.utils.Date getDateDep();
}
