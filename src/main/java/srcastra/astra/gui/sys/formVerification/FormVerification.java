/*
 * FormVerification.java
 *
 * Created on 25 mars 2002, 15:27
 */
package srcastra.astra.gui.sys.formVerification;
/** Classe spécifique pour la vérification du contenu de champs
 * de texte et autre type de composants d'encodage.
 * Cette Classe peut également être utilisé pour faire des vérification
 * sur des valeurs.
 * @author Sébastien
 * @version Merci de mettre des variables static final "TYPE_..."
 * si on ajoute une méthode spécifique à la classe :O)
 */
public class FormVerification {
    /** Vérification de type String */    
    public static final int TYPE_STRING = 0;    
    /** Vérification de type int */    
    public static final int TYPE_INT = 1;    
    /** Vérification de type float */    
    public static final int TYPE_FLOAT = 2;
    /** Vérification de type double */    
    public static final int TYPE_DOUBLE = 3;    
    /** Vérification de type mail */    
    public static final int TYPE_MAIL = 4;   
    /** Vérification de type tva */    
    public static final int TYPE_TVA = 5;
    /** Vérification de type date */    
    public static final int TYPE_DATE = 6;
    /** Vérification de type alphanumérique */
    public static final int TYPE_ALPHANUM = 7;
    /** erreur : champs vide */    
    public static final int ERREUR_VIDE = 0;
    /** erreur : entier invalide */    
    public static final int ERREUR_INT = 1;
    /** erreur : float invalide */    
    public static final int ERREUR_FLOAT = 2;
    /** erreur : double invalide */    
    public static final int ERREUR_DOUBLE = 3;
    /** erreur : mail invalide */    
    public static final int ERREUR_MAIL = 4;
    /** erreur : tva invalide */    
    public static final int ERREUR_TVA = 5;
    /** erreur : date invalide */    
    public static final int ERREUR_DATE = 6;
    
    public static final int ERREUR_STRING = 7;
    
    /** Verifie si la chaîne n'est pas vide
     * @return true si valide
     * False si invalide
     * @param text chaine à vérifier
     */    
    public static boolean isNotEmpty(String text) {
        if (text.equals("") || text.equals(null)) {
            return false;
        }
        return true;
    }
    
    /** Vérifie si la chaîne est une valeur de type int
     * @param text chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    public static boolean isNumeric(String text) {
        try {
            int i = Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }    
    
    /** Vérifie si la chaîne est une valeur de type float
     * @param text chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    public static boolean isFloat(String text) {
        try {
            float f = Float.parseFloat(text);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }    
    
    /** Vérifie si la chaîne est une valeur de type double
     * @param text chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    public static boolean isDouble(String text) {
        try {
            double d = Double.parseDouble(text);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    /** Vérifie la longueur d'une chaîne
     * @param text chaîne à vérifier
     * @param startLimit nbre minimal de caractère
     * @param endLimit nbre maximal de caractères
     * @return true si valide
     * False si invalide
     */    
    public static boolean isValidLenght(String text, int startLimit, int endLimit) {
        int len = text.length();
        if (len < startLimit || len > endLimit) {
            return false;
        }
        return true;
    }
    
    /** Vérifie si la chaîne est une adresse email valide
     * @param text chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    public static boolean isMailAdress(String text) {
        char[] ch = text.toCharArray();
        int at = -1;
        int p = -1;
        
        for (int i=0; i < ch.length; i++) {
            if (ch[i] == '@') {
                at = i;
            }
            else if (ch[i] == '.') {
                p = i;
            }
        }
        
        if (at > 0 && p > 0) {
            return true;
        }
        return false;
    }
    
    /** Vérifie si la chaîne est un numéro de tva correct
     * @param text chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    public static boolean isTVA(String text) {
        try {
            char[] ch = text.toCharArray();
            String correctTxt = "";
            for (int i=0; i < ch.length; i++) {
                if (Character.isDigit(ch[i])) {
                    correctTxt += String.valueOf(ch[i]);
                }
            }
            
            boolean bol = isBelgianTva(correctTxt);
            return bol;
        }
        catch (Exception e) {
        }
        return false; 
    }
    
    public static boolean isDate(String text) {
        // à implémenter
        return true;
    }
    
    /** méthode utilisé pour facilité l'appel
     *  de méthode selon le type donné 
     *  @param type type de vérification
     *  @param text texte à vérifier
     *  @return int[] numéro d'erreur (-1 -> pas d'erreur) */
    public static int[] checkField(int type, String text) {
        java.util.ArrayList verif = new java.util.ArrayList();
        int[] erreur;
        
        // vérification
        if (!isNotEmpty(text)) {
            verif.add(new Integer(ERREUR_VIDE));
        }
        else if (type == TYPE_INT) {
            if (!isNumeric(text)) {
                verif.add(new Integer(ERREUR_INT));
            }
        }
        else if (type == TYPE_FLOAT) {
            if (!isFloat(text)) {
                verif.add(new Integer(ERREUR_FLOAT));
            }
        }
        else if (type == TYPE_DOUBLE) {
            if (!isDouble(text)) {
                verif.add(new Integer(ERREUR_DOUBLE));
            }
        }
        else if (type == TYPE_MAIL) {
            if (!isMailAdress(text)) {
                verif.add(new Integer(ERREUR_MAIL));
            }
        }
        else if (type == TYPE_TVA) {
            if (!isTVA(text)) {
                verif.add(new Integer(ERREUR_TVA));
            }
        }
        else if (type == TYPE_DATE) {
            if (!isDate(text)) {
                verif.add(new Integer(ERREUR_DATE));
            }
        }
                
        // formatage du return
        if (verif.size() != 0) {
            erreur = new int[verif.size()];
            for (int i=0 ; i < verif.size(); i++) {
                Integer intg = (Integer) verif.get(i); 
                erreur[i] = intg.intValue();
            }
            return erreur;
        }
        else {
            erreur = new int[] { -1 };
            return erreur;
        }
        
    }
     /** Renvoi la valeur des erreurs selon leur code
      * depuis le bunddle "ErreurFormVerification".
      * @return String[] la liste des erreurs
      * @param erreurCode liste des codes erreur
      * @throws NullPointerException null pointer possible
      */
    public static String[] getErreurListe(int[] erreurCode) throws NullPointerException {
        String[] erreurListe = new String[erreurCode.length];
        for (int i=0; i < erreurCode.length; i++) {
            try {
                erreurListe[i] = java.util.ResourceBundle.getBundle("srcastra/astra/locale/ErreurFormVerification").getString(Integer.toString(erreurCode[i]));
            }
            catch (java.util.MissingResourceException mre) {
                srcastra.astra.gui.sys.ErreurScreenLibrary.displayErreur(null, srcastra.astra.gui.sys.ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);
           }
        }
        return erreurListe;
    }
    
    /** Vérifie si la chaîne est un numéro de tva Belge
     * @param tva chaîne à vérifier
     * @return true si valide
     * False si invalide
     */    
    private static boolean isBelgianTva(String tva) {
        int tvaNum = Integer.parseInt(tva.substring(0,7));
        int tvaInd = Integer.parseInt(tva.substring(7));
        int tvaResult = 0;
        
        if (tva.length() == 9) {
            tvaResult = tvaNum / 97;
            tvaResult = tvaResult * 97;
            tvaResult = tvaNum - tvaResult;
            if ((97 - tvaResult) == tvaInd) {
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// instancie un nouvel objet du type document, selon un type défini    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static DefaultMask getAnInstanceOfADocumentMask(int type) {
        switch (type) {
            case FormVerification.TYPE_ALPHANUM :
                return new DefaultMask(0, 50, null, DefaultMask.CASE_UNSENSITIVE);
                               
            case FormVerification.TYPE_STRING : 
                return new StringMask(0, 50, null, DefaultMask.CASE_UNSENSITIVE);
                               
            case FormVerification.TYPE_INT :
                return new IntegerMask(0, 10, null);
                               
            case FormVerification.TYPE_DOUBLE :
                return new DecimalMask(2, 2, null);
                               
            // case FormVerification.TYPE_MAIL :
            // case FormVerification.TYPE_TVA :
              
            case FormVerification.TYPE_DATE :
                return new DateMask(null, 0, null);
                            
            default :
                return new DefaultMask(0, 50, null, DefaultMask.CASE_UNSENSITIVE);
                
        }
    }
}
