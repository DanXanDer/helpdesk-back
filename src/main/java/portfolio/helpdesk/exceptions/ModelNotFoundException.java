package portfolio.helpdesk.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(Object id) {
        super("Model not found with id: " + id);
    }
}
