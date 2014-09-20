/*

 * PrintingMediator.java

 *

 * Created on 4 mars 2003, 12:21

 */


package srcastra.astra.gui.modules.config;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.printinglayout.*;

import java.awt.event.*;

import com.java4less.rreport.*;


/**
 * @author Thomas
 */


public class ConfigMediator {

    PrintingPreview2 printf;

    PrintingConfig printc;

    AbstractPrintingLayout printLayout;

    double x, y;

    srcastra.astra.gui.MainFrame m_parent;

    RReportJ2 report;

    /**
     * Creates a new instance of PrintingMediator
     */

    public ConfigMediator(srcastra.astra.gui.MainFrame parent) {

        m_parent = parent;


    }

    public void registerPrintingFrame(PrintingPreview2 printf) {

        this.printf = printf;

    }

    public void registerPrintingConfig(PrintingConfig printc) {

        this.printc = printc;

        printc.getGrp_CheckBox_enteteg().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_CheckBox_enteted().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_downBDC().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_leftBDC().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_upBDC().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_rightBDC().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_downCLI().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_leftCLI().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_upCLI().setEnabled(isPrintingBdcommandeOpened());

        printc.getGrp_JBut_rigthCLI().setEnabled(isPrintingBdcommandeOpened());

        if (isPrintingBdcommandeOpened()) {

            printc.getGrp_CheckBox_enteteg().addActionListener(listener);

            printc.getGrp_CheckBox_enteted().addActionListener(listener);

            printc.getGrp_JBut_downBDC().addActionListener(listener);

            printc.getGrp_JBut_leftBDC().addActionListener(listener);

            printc.getGrp_JBut_upBDC().addActionListener(listener);

            printc.getGrp_JBut_rightBDC().addActionListener(listener);

            printc.getGrp_JBut_downCLI().addActionListener(listener);

            printc.getGrp_JBut_leftCLI().addActionListener(listener);

            printc.getGrp_JBut_upCLI().addActionListener(listener);

            printc.getGrp_JBut_rigthCLI().addActionListener(listener);

            RArea area = printf.getUi().getPageHeader();

            RObject bondc = area.getItemByName("lib_bdc");

            RObject cli = area.getItemByName("clienttitre");

            printc.getGrp_Label_xBDC().setText("" + bondc.x);

            printc.getGrp_Label_yBDC().setText("" + bondc.y);

            printc.getGrp_Label_xCLI().setText("" + cli.x);

            printc.getGrp_Label_yCLI().setText("" + cli.y);

            printc.updateUI();

        }

    }


    /**
     * Getter for property configOpened.
     *
     * @return Value of property configOpened.
     */

    public boolean isConfigOpened() {

        return configOpened;

    }


    /**
     * Setter for property configOpened.
     *
     * @param configOpened New value of property configOpened.
     */

    public void setConfigOpened(boolean configOpened) {

        this.configOpened = configOpened;

        System.out.println("configopende? " + configOpened);

    }


    /**
     * Getter for property printingBdcommandeOpened.
     *
     * @return Value of property printingBdcommandeOpened.
     */

    public boolean isPrintingBdcommandeOpened() {

        return printingBdcommandeOpened;

    }


    /**
     * Setter for property printingBdcommandeOpened.
     *
     * @param printingBdcommandeOpened New value of property printingBdcommandeOpened.
     */

    public void setDefautltPrintingConfig() {

        printLayout = new PrintRepHeadAbstractLayout(this.report, "Header");

        srcastra.astra.sys.classetransfert.configuration.AbstractConfig_T defaultConfig = srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig();

        if (defaultConfig.getBdc_bloc_g() == 1)

            printLayout.performConfig1(true);

        else if (defaultConfig.getBdc_bloc_g() == 0)

            printLayout.performConfig1(false);

        if (defaultConfig.getBdc_bloc_d() == 1)

            printLayout.performConfig2(true);

        else if (defaultConfig.getBdc_bloc_d() == 0)

            printLayout.performConfig2(false);

        printLayout.performCOnfigDefault1(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bdc_x(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bdc_y());

        printLayout.performConfigDefautl2(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bdc_cli_x(), srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBdc_bdc_cli_y());

        printLayout.performCOnfigDefault3();

        // printf.finaliser();

        // printf.updateUI();

        //  printLayout.performCOnfigDefault1(defaultConfig.getBdc_bdc_x(),defaultConfig.getBdc_bdc_y());

        // printLayout.performConfigDefautl2(defaultConfig.getBdc_bdc_cli_x(),defaultConfig.getBdc_bdc_cli_y());

        //printf.finaliser();

        //    printf.updateUI();


    }

    public void setPrintingBdcommandeOpened(boolean printingBdcommandeOpened) {

        this.printingBdcommandeOpened = printingBdcommandeOpened;

        if (printingBdcommandeOpened) {

            if (configOpened)

                registerPrintingConfig(printc);

            setDefautltPrintingConfig();


        }

        if (!printingBdcommandeOpened)

            if (configOpened)

                registerPrintingConfig(printc);

        System.out.println("printingOpended ? " + printingBdcommandeOpened);

    }

    ActionListener listener = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

            if (evt.getSource().equals(printc.getGrp_CheckBox_enteteg()))

            {

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                printLayout.performConfig1(printc.getGrp_CheckBox_enteteg().isSelected());

                printf.finaliser(false);

                printf.updateUI();

            }

            if (evt.getSource().equals(printc.getGrp_CheckBox_enteted()))

            {

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                printLayout.performConfig2(printc.getGrp_CheckBox_enteted().isSelected());

                printf.finaliser(false);

                printf.updateUI();

            }

            if (evt.getSource().equals(printc.getGrp_JBut_downBDC()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(0, printLayout.performConfig3(0.0d, 0.4d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_upBDC()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(0, printLayout.performConfig3(0.0d, -0.4d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_rightBDC()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(0, printLayout.performConfig3(0.4d, 0.0d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_leftBDC()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(0, printLayout.performConfig3(-0.4d, 0.0d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_downCLI()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(1, printLayout.performConfig4(0.0d, 0.4d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_upCLI()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(1, printLayout.performConfig4(0.0d, -0.4d));

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_rigthCLI()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(1, printLayout.performConfig4(0.4d, 0.0d));
                ;

                printf.finaliser(false);

                printf.updateUI();


            }

            if (evt.getSource().equals(printc.getGrp_JBut_leftCLI()))

            {

                System.out.println("je suis danc action down");

                printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

                setXYvalue(1, printLayout.performConfig4(-0.4d, 0.0d));

                printf.finaliser(false);

                printf.updateUI();


            }

        }

    };

    private void setXYvalue(int i, ReturnClass xy) {

        if (i == 0) {

            printc.getGrp_Label_xBDC().setText("" + xy.getX());

            printc.getGrp_Label_yBDC().setText("" + xy.getY());

        } else if (i == 1) {

            printc.getGrp_Label_xCLI().setText("" + xy.getX());

            printc.getGrp_Label_yCLI().setText("" + xy.getY());

        }


    }


    /**
     * Getter for property report.
     *
     * @return Value of property report.
     */

    public void hideLinks() {


    }

    public void hideRight() {


    }

    public void moveBon(String direction) {

        this.report.setPrint(false);

        if (direction.equals("UP")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig3(0.0d, -0.4d);

            // setXYvalue(0,printLayout.performConfig3(0.0d,-0.4d));

            printf.finaliser(false);

            printf.updateUI();

        } else if (direction.equals("DOWN")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig3(0.0d, 0.4d);

            // setXYvalue(0,printLayout.performConfig3(0.0d,0.4d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("LEFT")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig3(-0.4d, 0.0d);

            // setXYvalue(0,printLayout.performConfig3(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("RIGTH")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig3(0.4d, 0.0d);

            // setXYvalue(0,printLayout.performConfig3(0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        }

        this.report.setPrint(true);

    }

    public void moveClient(String direction) {

        this.report.setPrint(false);

        if (direction.equals("UP")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig4(0.0d, -0.4d);

            printf.finaliser(false);

            printf.updateUI();

        } else if (direction.equals("DOWN")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig4(0.0d, 0.4d);

            //setXYvalue(1,printLayout.performConfig4(0.0d,0.4d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("LEFT")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig4(-0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("RIGTH")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig4(0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        }

        this.report.setPrint(true);

    }

    public void moveBloqueDroit(String direction) {

        this.report.setPrint(false);

        if (direction.equals("UP")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig6(0.0d, -0.4d);

            printf.finaliser(false);

            printf.updateUI();

        } else if (direction.equals("DOWN")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig6(0.0d, 0.4d);

            //setXYvalue(1,printLayout.performConfig4(0.0d,0.4d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("LEFT")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig6(-0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("RIGTH")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig6(0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        }

        this.report.setPrint(true);

    }

    public void moveBlocGauche(String direction) {

        this.report.setPrint(false);

        if (direction.equals("UP")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig5(0.0d, -0.4d);

            printf.finaliser(false);

            printf.updateUI();

        } else if (direction.equals("DOWN")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig5(0.0d, 0.4d);

            //setXYvalue(1,printLayout.performConfig4(0.0d,0.4d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("LEFT")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig5(-0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        } else if (direction.equals("RIGTH")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig5(0.4d, 0.0d);

            //setXYvalue(1,printLayout.performConfig4(-0.4d,0.0d));

            printf.finaliser(false);

            printf.updateUI();


        }

        this.report.setPrint(true);

    }

    public void moveCorp(String direction) {

        this.report.setPrint(false);

        if (direction.equals("UP")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig7(0.0d, -0.4d);

            printf.finaliser(false);

            printf.updateUI();

        } else if (direction.equals("DOWN")) {

            System.out.println("je suis danc action down");

            printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

            printLayout.performConfig7(0.0d, 0.4d);

            //setXYvalue(1,printLayout.performConfig4(0.0d,0.4d));

            printf.finaliser(false);

            printf.updateUI();


        }

        this.report.setPrint(true);

    }

    public void setLeftBlocVisible(boolean sw) {

        this.report.setPrint(false);

        printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

        //   printLayout.performConfig1(true);

        //printLayout.performConfig1(true);

        //    printf.finaliser(true);


        printLayout.performConfig1(sw);

        printf.finaliser(false);

        printf.updateUI();


        this.report.setPrint(true);

    }

    public void setLeftBlocVisible1(boolean sw) {

        this.report.setPrint(false);

        printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

        printLayout.performConfig1(true);

        //printLayout.performConfig1(true);

        printf.finaliser(true);

        //printLayout.performConfig2(sw);

        // printf.finaliser(false);

        // printf.updateUI();

        // this.report.setPrint(true);
        // printf.updateUI();


    }

    public void setRigthBlocVisible(boolean sw) {

        this.report.setPrint(false);

        printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

        //printLayout.performConfig2(true);

        //printLayout.performConfig1(true);

        //rintf.finaliser(true);


        printLayout.performConfig2(sw);

        printf.finaliser(false);

        printf.updateUI();

        this.report.setPrint(true);


    }

    public void setRigthBlocVisible1(boolean sw) {

        this.report.setPrint(false);

        printLayout = new PrintRepHeadAbstractLayout(printf.getUi(), "Header");

        printLayout.performConfig2(true);

        //printLayout.performConfig1(true);

        printf.finaliser(true);

        //printLayout.performConfig2(sw);

        // printf.finaliser(false);

        // printf.updateUI();

        // this.report.setPrint(true);
        // printf.updateUI();


    }

    public com.java4less.rreport.RReportJ2 getReport() {

        return report;

    }


    /**
     * Setter for property report.
     *
     * @param report New value of property report.
     */

    public void setReport(com.java4less.rreport.RReportJ2 report) {

        this.report = report;

    }


    /**
     * Getter for property report.
     *
     * @return Value of property report.
     */


    boolean configOpened;

    boolean printingBdcommandeOpened;

}

