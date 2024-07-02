package automation.de.dg.servicenow.enumaration;

/**
 * <b>Service Now/Enumeration : Incident States</b> Incident States
 */
public enum IncidentStates {

    NEW("New"),
    IN_PROGRESS("In Progress"),
    ON_HOLD("On Hold"),
    RESOLVED("Resolved"),
    CANCELED("Canceled");

    public final String option;


    IncidentStates(String option) {
        this.option = option;
    }

}
