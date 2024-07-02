package automation.de.dg.servicenow.enumaration;

/**
 * <b>Service Now/Enumeration : Incident Types</b> Incident Types
 */

public enum EmcIncidentTypes {

    INQUIRY("Inquiry / Help"),
    SOFTWARE("Application / Software"),
    HARDWARE("Hardware / System"),
    NETWORK("Network");

    public final String option;


    EmcIncidentTypes(String option) {
        this.option = option;
    }

}
