/*
 * ADate.java
 *
 * Created on 11 mars 2003, 14:09
 */

package srcastra.astra.gui.components.date.thedate;

import srcastra.astra.gui.components.AstraComponent;
import srcastra.astra.gui.components.textFields.ComponentInterface;
import srcastra.astra.gui.components.textFields.Mtextfield;
import srcastra.astra.gui.sys.MessageManager;
import srcastra.astra.sys.classetransfert.utils.CalculDate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * @author Thomas
 */

public class ADate extends javax.swing.JPanel implements java.io.Serializable, AstraComponent, ComponentInterface, FocusListener {
    JComponent grp_Comp_nextComponent;
    javax.swing.JLabel grp_Label_warning;
    Mtextfield grp_JText_encode;
    protected boolean lastFocusedComponent;
    protected Icon warningIcon;
    protected Icon workingIcon;
    srcastra.astra.gui.components.date.thedate.DateMask document;
    EventListenerList m_listenerliste;
    ADateEvent event;
    MessageManager m_manager;
    ArrayList actionlist;

    /**
     * Creates a new instance of ADate
     */
    public ADate() {
        actionlist = new ArrayList();
        initComponents();
        document = new srcastra.astra.gui.components.date.thedate.DateMask(grp_JText_encode, this);
        grp_JText_encode.setDocument(document);
        grp_JText_encode.addActionListener(m_actionlistener);
        m_listenerliste = new EventListenerList();
        date = new srcastra.astra.sys.classetransfert.utils.Date();
        enabled = true;
        m_manager = new MessageManager();
    }

    protected void initComponents() {
        grp_JText_encode = new Mtextfield(this);
        grp_Label_warning = new javax.swing.JLabel();
        grp_JText_encode.setFont(new Font("Tahoma", Font.PLAIN, 10));
        grp_JText_encode.setDisabledTextColor(java.awt.Color.black);
        grp_JText_encode.setVerifyInputWhenFocusTarget(false);
        grp_JText_encode.setBorder(new LineBorder(Color.black));
        grp_Label_warning.setPreferredSize(new java.awt.Dimension(18, 18));
        grp_Label_warning.setOpaque(false);
        this.setPreferredSize(new java.awt.Dimension(100, 18));
        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
        this.add(grp_JText_encode);
        this.add(grp_Label_warning);
        this.setOpaque(false);
    }

    public void addAdateListener(ADateListener listener) {
        m_listenerliste.add(ADateListener.class, listener);
    }

    protected void fireStateChanged(int validate) {
        Object[] listeners = m_listenerliste.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            System.out.println("[FIRESTATECHANGED] listeners : " + listeners[i].getClass());
            if (ADateListener.class.isAssignableFrom(listeners[i].getClass())) {
                System.out.println("[FIRESTATECHANGED] passé");
                event = new ADateEvent(this, validate, "validate");
                ((ADateListener) listeners[i]).dateSmallerThenCurrenTime(event);
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        actionlist.add(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }


    public JComponent getGrp_Comp_nextComponent() {
        return grp_Comp_nextComponent;
    }

    public String getText2() {
        return "";
    }

    public boolean getverif() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLastFocusedComponent() {
        return lastFocusedComponent;
    }

    public void removeActionListener(ActionListener listener) {
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    public void requestFocus() {
        System.out.println("ok je met l'icone");
        setGoodIcon(true);
        grp_JText_encode.requestFocus();
        grp_JText_encode.setSelectionStart(0);
        grp_JText_encode.setSelectionEnd(0);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        super.setEnabled(enabled);
        grp_Label_warning.setEnabled(enabled);
        grp_JText_encode.setEnabled(enabled);
        adaptBackgroundColor(enabled);
    }

    private void adaptBackgroundColor(boolean enabled) {
        Color background = enabled ? Color.white : Color.lightGray;
        grp_JText_encode.setBackground(background);
    }

    public void setLastFocusedComponent(boolean lastFocusedComponent) {
    }

    public void setText(String text) {
    }

    public boolean getCorreInput() {
        int[] tmp = null;
        boolean valide = false;
        document.fillDate();
        if (!document.checkBeforeChange()) {
            setGoodIcon(false);
            return false;
        }
        getDate();
        System.out.println("action performed");
        if (dateBeforeComp != null) {
            dateBefore = ((ADate) dateBeforeComp).getDate();
            if (!dateBefore.isOpen() && !dateBefore.isUnknow())
                tmp = CalculDate.renvDifferenceBetweenDate(dateBefore, date);
        }
        if (checkUptodate && dateBeforeComp == null) {
            if (!date.isOpen() && !date.isUnknow()) {
                valide = CalculDate.verifyUptodateDate(date);
                if (!valide) {
                    if (user != null && form != null) {
                        m_manager.showMessageDialog(form, "date_smaller", "date_smaller_title", user);
                        fireStateChanged(ADateEvent.DATE_SMALLER_THEN_NOW);
                    }
                }

            }
            return true;
        } else {
            if (tmp != null) {
                if (!date.isOpen() && !date.isUnknow()) {
                    if (tmp[0] <= 0 && !valide && checkUptodate) {
                        if (user != null && form != null) {
                            fireStateChanged(ADateEvent.DATE_SMALLER_THEN_OTHE_AND_FROM_NOW);
                            setGoodIcon(false);
                            return false;
                        }
                    } else if (tmp[0] < 0 && !valide && !checkUptodate) {
                        if (user != null && form != null) {
                            m_manager.showMessageDialog(form, "date_smaller_than", "date_smaller_than_title", user);
                            fireStateChanged(ADateEvent.DATE_SMALLER_THEN_OTHE);
                            setGoodIcon(false);
                            return false;
                        }
                    } else if (tmp[0] >= 0) {
                        setNbrjour(tmp[1]);
                        setNbrnigth(tmp[0]);
                        fireStateChanged(ADateEvent.CALCUL_DAY);
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public boolean isOpen() {
        return false;
    }

    public void setGoodIcon(boolean sw) {
        if (sw) {
            if (workingIcon != null)
                grp_Label_warning.setIcon(workingIcon);
        } else {
            if (warningIcon != null)
                grp_Label_warning.setIcon(warningIcon);
        }
    }

    public javax.swing.Icon getWarningIcon() {
        return warningIcon;
    }

    public void setName(String name) {
        grp_JText_encode.setName(name);
    }

    public void setWarningIcon(javax.swing.Icon warningIcon) {
        this.warningIcon = warningIcon;
    }

    public javax.swing.Icon getWorkingIcon() {
        return workingIcon;
    }

    public void setWorkingIcon(javax.swing.Icon workingIcon) {
        this.workingIcon = workingIcon;
    }

    /**
     * Setter for property grp_Comp_nextComponent.
     *
     * @param grp_Comp_nextComponent New value of property grp_Comp_nextComponent.
     */
    public void setGrp_Comp_nextComponent(JComponent grp_Comp_nextComponent) {
        this.grp_Comp_nextComponent = grp_Comp_nextComponent;
        grp_JText_encode.setNext(grp_Comp_nextComponent);
    }

    public void focusGained(java.awt.event.FocusEvent focusEvent) {
        setGoodIcon(true);
    }


    public void focusLost(java.awt.event.FocusEvent focusEvent) {
        clearIcon();
    }

    public void clearIcon() {
        grp_Label_warning.setIcon(null);
    }

    ActionListener m_actionlistener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            action(evt);

        }
    };

    public void action(ActionEvent evt) {
        if (getCorreInput()) {
            passFocus();
            for (int i = 0; i < actionlist.size(); i++) {
                ((ActionListener) actionlist.get(i)).actionPerformed(evt);
            }
        }
    }

    protected void passFocus() {
        fireStateChanged(ADateEvent.GENERIQUE);
        System.out.println("action : comp" + grp_Comp_nextComponent);
        if (grp_Comp_nextComponent != null) {
            clearIcon();
            grp_Comp_nextComponent.setEnabled(true);
            grp_Comp_nextComponent.requestFocus();
        }

    }

    public void setDate(srcastra.astra.sys.classetransfert.utils.Date date) {
        if (date != null) {
            int day = date.getDay();
            int month = date.getMonth();
            int year = date.getYear();
            if (day == 1 && month == 1 && year == 1001) {
                document.setDate("OO", "OO", "OOOO");
                open = true;
                unknown = false;
            } else if (day == 1 && month == 1 && year == 1000) {
                document.setDate("??", "??", "????");
                open = false;
                unknown = true;
            } else if (day == 0 && month == 0 && year == 0) {
                document.setDate("??", "??", "????");
                open = false;
                unknown = true;
            } else {
                document.setDate("" + day, "" + month, "" + year);
            }
        }
    }

    public srcastra.astra.sys.classetransfert.utils.Date getDate() {
        int year = 1000;
        int month = 1;
        int day = 1;
        date = new srcastra.astra.sys.classetransfert.utils.Date();
        try {
            if (isOpen()) {
                year = 1001;
                month = 1;
                day = 1;
            } else if (isUnknown()) {
                year = 1000;
                month = 1;
                day = 1;
            } else {
                year = document.getYear();
                month = document.getMonth();
                day = document.getDay();
            }
        } catch (Exception ee) {
        }
        if (day < 0 || month < 0 || year < 0) {
            unknown = true;
            year = 1000;
            month = 1;
            day = 1;
            //grp_TField_jour.setText("?");
        }

        System.out.println("[GET DATE DE ADATE] year - month - day => " + year + " - " + month + "-" + day);
        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);
        date.setHours(10);
        date.setMinutes(10);
        date.setSeconds(10);
        return date;
    }

    /**
     * Setter for property open.
     *
     * @param open New value of property open.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Getter for property unknown.
     *
     * @return Value of property unknown.
     */
    public boolean isUnknown() {
        return false;
    }

    /**
     * Setter for property unknown.
     *
     * @param unknown New value of property unknown.
     */
    public void setUnknown(boolean unknown) {
        this.unknown = unknown;
    }

    /**
     * Getter for property dateBefore.
     *
     * @return Value of property dateBefore.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getDateBefore() {
        return dateBefore;
    }

    /**
     * Setter for property dateBefore.
     *
     * @param dateBefore New value of property dateBefore.
     */
    public void setDateBefore(srcastra.astra.sys.classetransfert.utils.Date dateBefore) {
        this.dateBefore = dateBefore;
    }

    /**
     * Getter for property checkUptodate.
     *
     * @return Value of property checkUptodate.
     */
    public boolean isCheckUptodate() {
        return checkUptodate;
    }

    /**
     * Setter for property checkUptodate.
     *
     * @param checkUptodate New value of property checkUptodate.
     */
    public void setCheckUptodate(boolean checkUptodate) {
        this.checkUptodate = checkUptodate;
    }

    /**
     * Getter for property dateBeforeComp.
     *
     * @return Value of property dateBeforeComp.
     */
    public java.awt.Component getDateBeforeComp() {
        return dateBeforeComp;
    }

    /**
     * Setter for property dateBeforeComp.
     *
     * @param dateBeforeComp New value of property dateBeforeComp.
     */
    public void setDateBeforeComp(JComponent dateBeforeComp) {
        this.dateBeforeComp = dateBeforeComp;
    }

    /**
     * Getter for property nbrjour.
     *
     * @return Value of property nbrjour.
     */
    public int getNbrjour() {
        return nbrjour;
    }

    /**
     * Setter for property nbrjour.
     *
     * @param nbrjour New value of property nbrjour.
     */
    public void setNbrjour(int nbrjour) {
        this.nbrjour = nbrjour;
    }

    /**
     * Getter for property nbrnigth.
     *
     * @return Value of property nbrnigth.
     */
    public int getNbrnigth() {
        return nbrnigth;
    }

    /**
     * Setter for property nbrnigth.
     *
     * @param nbrnigth New value of property nbrnigth.
     */
    public void setNbrnigth(int nbrnigth) {
        this.nbrnigth = nbrnigth;
    }

    /**
     * Getter for property form.
     *
     * @return Value of property form.
     */
    public java.awt.Component getForm() {
        return form;
    }

    /**
     * Setter for property form.
     *
     * @param form New value of property form.
     */
    public void setForm(Component form) {
        this.form = form;
    }

    /**
     * Getter for property user.
     *
     * @return Value of property user.
     */
    public srcastra.astra.sys.classetransfert.Loginusers_T getUser() {
        return user;
    }

    /**
     * Setter for property user.
     *
     * @param user New value of property user.
     */
    public void setUser(srcastra.astra.sys.classetransfert.Loginusers_T user) {
        this.user = user;
    }

    /**
     * Getter for property grp_JText_encode.
     *
     * @return Value of property grp_JText_encode.
     */
    public srcastra.astra.gui.components.textFields.Mtextfield getGrp_JText_encode() {
        return grp_JText_encode;
    }

    /**
     * Setter for property grp_JText_encode.
     *
     * @param grp_JText_encode New value of property grp_JText_encode.
     */
    public void setGrp_JText_encode(srcastra.astra.gui.components.textFields.Mtextfield grp_JText_encode) {
        this.grp_JText_encode = grp_JText_encode;
    }

    int day;
    int month;
    int year;
    boolean open;
    boolean unknown;
    srcastra.astra.sys.classetransfert.utils.Date date;
    srcastra.astra.sys.classetransfert.utils.Date dateBefore;
    private boolean checkUptodate;
    JComponent dateBeforeComp;
    boolean enabled;
    int nbrjour;
    int nbrnigth;
    Component form;
    srcastra.astra.sys.classetransfert.Loginusers_T user;
}
