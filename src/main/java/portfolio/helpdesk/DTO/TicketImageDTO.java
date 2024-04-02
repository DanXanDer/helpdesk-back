package portfolio.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TicketImageDTO(
        String url,
        Integer idTicket
) {

}
