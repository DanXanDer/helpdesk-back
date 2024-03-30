package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.request.TicketRequestDTO;
import portfolio.helpdesk.DTO.response.TicketResponseDTO;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketStatus;
import portfolio.helpdesk.services.IClientService;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, WorkerMapper.class})
public abstract class TicketMapper {
    @Autowired
    protected IClientService clientService;

    public Ticket convertToEntity(TicketRequestDTO ticketRequest) {
        if (ticketRequest == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setClient(clientService.getReferenceById(ticketRequest.idClient()));
        ticket.setSummary(ticketRequest.summary());
        ticket.setDescription(ticketRequest.description());
        ticket.setTicketStatus(TicketStatus.OPEN);
        return ticket;
    }

    public abstract TicketResponseDTO convertToDTO(Ticket ticket);
}
