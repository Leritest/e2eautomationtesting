package automation.endpoints.enumaration;

/**
 * <b>Enumeration : RestAPI</b> RestAPI Authorization
 */

public enum RestApiAuth {

    /**
     * Using RestAPI Authorization as Enum list<br>
     * Authorization is used in request's header
     */

    NONE("None"),
    HEADER("Header-Parameter"),
    BASIC("Basic-Auth"),
    TOKEN("Token-Auth");

    public final String option;


    RestApiAuth(String option) {
        this.option = option;
    }

}
