package srcastra.test;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-déc.-2004
 * Time: 17:31:29
 *
 * @author
 * @version $revision : $
 *          To change this template use File | Settings | File Templates.
 */
public class rectificationDsoleil {
    private static Logger logger = Logger.getLogger(rectificationDsoleil.class.getName());

    public rectificationDsoleil() {
        String update="update historique2  set historique2.jxcleunik= 25, historique2.ce_cleunik2=725 , historique2.ce_cleunik=700000001 where urcleunik IN(6,7,8,22) and ce_cleunik_cent=635 and jxcleunik=21";

    }
}
