package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.TicketImageDTO;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketImage;
import portfolio.helpdesk.services.ITicketService;

@Mapper(componentModel = "spring")
public abstract class TicketImageMapper {
    @Autowired
    protected ITicketService ticketService;

    public TicketImage convertToEntity(TicketImageDTO dto) {
        Ticket ticket = ticketService.getReferenceById(dto.idTicket());
        TicketImage ticketImage = new TicketImage();
        ticketImage.setUrl(dto.url());
        ticketImage.setTicket(ticket);
        return ticketImage;
    }
}
