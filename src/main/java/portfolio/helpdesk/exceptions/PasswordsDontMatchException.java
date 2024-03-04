package portfolio.helpdesk.exceptions;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException() {
        super("Las claves no coinciden");
    }
}
