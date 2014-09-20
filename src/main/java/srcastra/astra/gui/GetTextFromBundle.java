/*


 * GetTextFromBundle.java


 *


 * Created on 24 f�vrier 2003, 16:10


 */


package srcastra.astra.gui;


/**
 * @author Thomas
 */


public class GetTextFromBundle {


    /**
     * Creates a new instance of GetTextFromBundle
     */


    public GetTextFromBundle() {


    }


    public static String getText(String path, String key, java.util.Locale locale) {


        return java.util.ResourceBundle.getBundle(path, locale).getString(key);


    }


}


