/*
 * Language.java
 *
 * Created on 21 mars 2002, 12:00
 */

package srcastra.astra.gui.sys;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class Language {

    public static final String[] AVAILABLE_LANGUAGE;
    static {
        AVAILABLE_LANGUAGE = new String[] { "fr" };
    }
    
    /** Creates new Language */
    public Language() {
    }

    public static java.util.Locale getSystemLanguage() {
        java.util.Locale locale = java.util.Locale.getDefault();
        for (int i=0; i < AVAILABLE_LANGUAGE.length; i++) {
            if (locale.getLanguage().equals("fr")) {
                System.out.println(locale.getLanguage());
                return locale;
            }
        }
        
        locale.setDefault(new java.util.Locale("en",""));
        System.out.println(locale.getLanguage());
        return locale;
    }
    
}
