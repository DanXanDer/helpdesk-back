package portfolio.helpdesk.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException() {
        super("Model not found");
    }
}
