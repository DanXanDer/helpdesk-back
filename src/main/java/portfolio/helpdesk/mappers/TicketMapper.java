package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.TicketImageDTO;
import portfolio.helpdesk.DTO.request.TicketMessageRequestDTO;
import portfolio.helpdesk.DTO.request.TicketRequestDTO;
import portfolio.helpdesk.DTO.response.TicketMessageResponseDTO;
import portfolio.helpdesk.DTO.response.TicketResponseDTO;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketImage;
import portfolio.helpdesk.models.TicketMessage;
import portfolio.helpdesk.models.TicketStatus;
import portfolio.helpdesk.services.IClientService;
import portfolio.helpdesk.services.ITicketService;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, WorkerMapper.class})
public abstract class TicketMapper {
    @Autowired
    protected IClientService clientService;
    @Autowired
    protected ITicketService ticketService;

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

    @Mapping(target = "id", source = "idTicket")
    @Mapping(target = "status", source = "ticketStatus.status")
    @Mapping(target = "color", source = "ticketStatus.color")
    public abstract TicketResponseDTO convertToDTO(Ticket ticket);

    public TicketImage convertToEntity(TicketImageDTO dto) {
        Ticket ticket = ticketService.getReferenceById(dto.idTicket());
        TicketImage ticketImage = new TicketImage();
        ticketImage.setUrl(dto.url());
        ticketImage.setTicket(ticket);
        return ticketImage;
    }

    public TicketMessage convertToEntity(TicketMessageRequestDTO dto) {
        TicketMessage ticketMessage = new TicketMessage();
        ticketMessage.setMessage(dto.message());
        ticketMessage.setSender(dto.sender());
        ticketMessage.setReceiver(dto.receiver());
        ticketMessage.setTicket(ticketService.getReferenceById(dto.idTicket()));
        return ticketMessage;
    }


    @Mapping(target = "idTicket", source = "ticket.idTicket")
    @Mapping(target = "images", source = "messageImages")
    public abstract TicketMessageResponseDTO convertToDTO(TicketMessage ticketMessage);

}
