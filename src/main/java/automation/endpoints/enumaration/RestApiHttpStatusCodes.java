package automation.endpoints.enumaration;

/**
 * <b>Enumeration : RestAPI</b> RestAPI HTTP Status Codes
 */

public enum RestApiHttpStatusCodes {

    /**
     * Using RestAPI HTTP Status Codes as Enum list<br>
     * Status Codes are used in request's header
     */

    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405);

    public final int option;


    RestApiHttpStatusCodes(int option) {
        this.option = option;
    }

}
