package portfolio.helpdesk.exceptions;

public class ParentEntityDisabledException extends RuntimeException {
    public ParentEntityDisabledException(String message) {
        super(message);
    }
}
