package automation.endpoints.enumaration;

/**
 * <b>RestAPI/Enumeration : RestAPI Method types</b> RestAPI Method types Enumeration
 */

public enum RestApiMethodTypes {

    /**
     * Using RestAPI Method types as Enum list<br>
     * Authorization is used in request's header
     */

    GET("Get"),
    POST("Post"),
    PUT("Put"),
    PATCH("Patch"),
    DELETE("Delete");

    public final String option;


    RestApiMethodTypes(String option) {
        this.option = option;
    }

}
