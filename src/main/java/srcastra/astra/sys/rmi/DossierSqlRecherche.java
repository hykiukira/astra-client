/*
 * DossierSqlRecherche.java
 *
 * Created on 2 december 2002, 14:02
 */

package srcastra.astra.sys.rmi;

/**
 *
 * @author  Thomas
 */
public interface DossierSqlRecherche {
static final int CHARGE_DOSSIER_BY_REF=0;
static final int CHARGE_DOSSIER_BY_CLICONTRACT_REF=1;
static final int CHARGE_DOSSIER_BY_CLIFACT_REF=2;
static final int CHARGE_DOSSIER_BY_PO=3;
static final int CHARGE_DOSSIER_BY_DATECREA=4;
static final int CHARGE_DOSSIER_BY_DATEDEPART=5;
static final int CHARGE_DOSSIER_BY_TYPEPRODUIT=6;
}
