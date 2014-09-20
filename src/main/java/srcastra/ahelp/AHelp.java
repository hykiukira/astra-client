/*


 * AHelp.java


 *


 * Created on 10 janvier 2003, 9:32


 */


package srcastra.ahelp;


import java.net.*;


import javax.help.*;


import java.awt.event.*;


/**
 * @author Sébastien
 */


public class AHelp {


    private HelpSet m_helpSet;


    private HelpBroker m_hBroker;


    /**
     * Creates a new instance of AHelp
     */


    public AHelp() {


        try {


            URL hsUrl = getClass().getResource("/srcastra/ahelp/astra.hs");


            m_helpSet = new HelpSet(null, hsUrl);


            m_hBroker = m_helpSet.createHelpBroker();


        }


        catch (Exception e) {


            e.printStackTrace();


        }


    }


    public void displayHelpView() {


        m_hBroker.setViewDisplayed(true);


    }


    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {


        AHelp ah = new AHelp();


        ah.displayHelpView();


    }


}


