package com.java4less.rreport;


/**
 * this listener receives an event when a object is clicked in the report preview
 */

public interface RObjectListener {


    /**
     * user clicked on a object. This methid will be called be RReport.
     */

    public void objectClicked(RPage page, RObjectInstance oInstance);


}