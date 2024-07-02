package automation.de.dg.servicenow.enumaration;

/**
 * <b>Service Now/Enumeration : Header Label Names</b> Header Label Names
 */
public enum HeaderLabelNames {

    ALL("All"),
    FAVORITES("Favorites"),
    HISTORY("History"),
    WORKSPACES("Workspaces");

    public final String option;

    HeaderLabelNames(String option) {
        this.option = option;
    }

}
