package portfolio.helpdesk.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException() {
        super("Modelo no encontrado");
    }
}
