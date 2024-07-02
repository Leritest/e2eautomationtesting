package automation.de.dg.servicenow.enumaration;

/**
 * <b>Service Now/Enumeration : List Labels</b> List Labels
 */
public enum ListLabels {

    // Mutual values
    ASSIGNED_TO_YOU("Assigned to you"),
    ALL("All"),
    // Interactions
    INTERACTIONS("Interactions"),
    ACTIVE("Active"),
    // Requests
    REQUESTS("Requests"),
    OPEN_REQUESTS("Open requests"),
    OPEN_ITEMS("Open items"),
    // Catalog tasks
    CATALOG_TASKS("Catalog tasks"),
    // Incidents
    INCIDENTS("Incidents"),
    UNASSIGNED("Unassigned"),
    OPEN("Open"),
    RESOLVED("Resolved"),
    // Tasks
    TASKS("Tasks")
    ;

    public final String option;


    ListLabels(String option) {
        this.option = option;
    }

}
