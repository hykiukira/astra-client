/*

 * getBufferData.java

 *

 * Created on 21 mars 2003, 14:32

 */


package srcastra.astra.gui.modules.aidedesicion;

import java.util.*;

/**
 * @author thomas
 */

public class getBufferData {


    /**
     * Creates a new instance of getBufferData
     */

    public getBufferData() {

    }

    public static Object getElement(AbstractBuffer mainbuf, int type, int id, int lmcleunik) {

        DecBuffer buff = new DecBuffer();

        buff = (DecBuffer) mainbuf.getData3(type, id, buff);

        if (buff != null) {

            ArrayList data = buff.getData();

            if (data != null) {

                for (int i = 0; i < data.size(); i++) {

                    Object[] tmp = (Object[]) data.get(i);

                    if (lmcleunik == ((Integer) tmp[2]).intValue())

                        return tmp[3];

                }

            }

        }

        return null;

    }

}

