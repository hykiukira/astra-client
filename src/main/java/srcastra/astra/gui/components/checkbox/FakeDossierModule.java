package srcastra.astra.gui.components.checkbox;

import srcastra.FakeDossier;
import srcastra.FakeUser;
import srcastra.astra.gui.MainFrame;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.sys.MessageManager;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.dossier.assurance.Assurance_T;
import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;
import srcastra.astra.sys.classetransfert.dossier.bateau.Bateau_T;
import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;
import srcastra.astra.sys.classetransfert.dossier.car.Car_T;
import srcastra.astra.sys.classetransfert.dossier.divers.Divers_T;
import srcastra.astra.sys.classetransfert.dossier.hotel.Hotel_T;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.dossier.taxi.Taxi_T;
import srcastra.astra.sys.classetransfert.dossier.train.Train_T;
import srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.sys.rmi.astrainterface;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 10/10/11
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class FakeDossierModule implements DossierMainScreenModule{
    @Override
    public DossierRmiInterface getServeurDossier() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Frame getOwner() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void registerTable(JTable generique_table) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nextScreen(int currentScreen, int insert) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Dossier_T getDossier() {
        return new FakeDossier();//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDossier(Dossier_T dossier) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContextCleUnik(int ContextCleUnik) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nextScreen(int currentScreen) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nextScreen(int currentScreen, boolean affich) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayCreateSequence() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayReadSequence(int cleUnik) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayDeleteSequence() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void closeModule() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cancelModule() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void enabledTabbedPane(boolean enabled) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void chargeStatusPanel(String[] statuts) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCurrentPanel(ToolBarComposer currentPanel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCurrentActionEnabled(int[] actionEnabled) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public astrainterface getServeur() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Loginusers_T getCurrentUser() {
        return new FakeUser();
    }

    @Override
    public void reloadToolBarInfo() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeCursor(int changeLocation, Cursor cursor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Frame getSuperOwner() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getNestedSignaletique() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setNestedSignaletique(boolean netstedSignletique) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void chargeStatusPanel() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object renvObjectSelectedInTable(JTable table) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object renvObjectSelectedInTable2(JTable table, ArrayList array) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MessageManager getMessageManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MainFrame getMainframe() {
        return new MainFrame();
    }

    @Override
    public void setMenuProduit(boolean sw) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Avion_ticket_T getTicket() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTicket(Avion_ticket_T ticket) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAfterCreation(boolean afterCreation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAfterCreation() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getTicketcompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeTicketcompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getSupReducCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementSupReducCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTotalLabelText(String txt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTotalLabelText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTotal_tvacLabelText(String txt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTotal_tvacLabelText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMontantTvaLabelText(String txt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMontantTvaLabelText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void calculTotalUnProduit(produit_T produit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCreatingSequence() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void calculTotal() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String formatNumber(float data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insertDossier() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateDossier() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Avion_ticket_T getTmpticket() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpticket(Avion_ticket_T tmpticket) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long decrementePaymentCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Assurance_T getAssurance() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAssurance(Assurance_T assu) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getAssuranceCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeAssuranceCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpAssurance(Assurance_T tmpAssu) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Assurance_T getTmpAssurance() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Brochure_T getBrochure() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBrochure(Brochure_T broch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getBrochureCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeBrochureCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpBrochure(Brochure_T tmpBroch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Brochure_T getTmpBrochure() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Hotel_T getHotel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setHotel(Hotel_T hot) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getHotelCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeHotelCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpHotel(Hotel_T tmpHot) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Hotel_T getTmpHotel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bateau_T getBateau() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBateau(Bateau_T bat) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getBateauCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeBateauCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpBateau(Bateau_T tmpBat) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bateau_T getTmpBateau() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Train_T getTrain() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTrain(Train_T train) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getTrainCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeTrainCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpTrain(Train_T tmpTrain) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Train_T getTmpTrain() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public VoitureLocation_T getVoitureLocation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setVoitureLocation(VoitureLocation_T voitureLocation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getVoitureLocationCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeVoitureLocationCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpVoitureLocation(VoitureLocation_T tmpVoit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public VoitureLocation_T getTmpVoitureLocation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Taxi_T getTaxi() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTaxi(Taxi_T taxi) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getTaxiCompteur() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decrementeTaxiCompteur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTmpTaxi(Taxi_T tmpTaxi) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Taxi_T getTmpTaxi() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nextSpecificScreen(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void getPopupVector(Vector popupVector, int screen) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void requestOwnFocus() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showBonDeCommande(Dossier_T object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ActionToolBar getToolBar() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void getTheLostFocus() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Car_T getCar() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCar(Car_T car) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Car_T getCarTmp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCarTmp(Car_T carTmp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void lockedToolBar(boolean locked) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Divers_T getDiverst() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDiverst(Divers_T diverst) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
