package portfolio.helpdesk.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatus {
    OPEN("Abierto", "green"),
    IN_PROGRESS("En progreso", "yellow"),
    RESOLVED("Resuelto", "blue"),
    CLOSED("Cerrado", "gray");

    private final String status;
    private final String color;
}
