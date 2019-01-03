package ro.rosmof.exception;

/**
 * Used for passing strong-typed attributes to "/errors/validationexception.jsp"
 * general exception page.
 */
public enum ExceptionModelAttribute {
    REQUEST_URI("requestUri"),
    SESSION_ID("sessionId"),
    EXCEPTION("exception"),
    EXCEPTION_STACK("exceptionStack"),
    STATUS_VALUE("statusValue"),
    STATUS_REASON("statusReason");

    private String value;

    ExceptionModelAttribute(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }
}
