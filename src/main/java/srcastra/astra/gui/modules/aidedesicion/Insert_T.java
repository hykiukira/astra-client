/*



 * Insert_T.java



 *



 * Created on 21 janvier 2003, 16:16



 */


package srcastra.astra.gui.modules.aidedesicion;


/**
 * @author Thomas
 */


public class Insert_T implements java.io.Serializable {


    /**
     * Creates a new instance of Insert_T
     */


    int m_id;


    long m_timestamp;


    public Insert_T(int id, long timestamp) {


        m_id = id;


        m_timestamp = timestamp;


    }


    /**
     * Getter for property m_id.
     *
     * @return Value of property m_id.
     */


    public int getM_id() {


        return m_id;


    }


    public long getM_timestamp() {


        return m_timestamp;


    }


}



