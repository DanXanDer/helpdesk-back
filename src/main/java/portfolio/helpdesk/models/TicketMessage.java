package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TicketMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicketMessage;
    @Column(nullable = false, length = 500)
    private String message;
    @Column(nullable = false, length = 100)
    private String sender;
    @Column(nullable = false, length = 100)
    private String receiver;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_ticket",
            foreignKey = @ForeignKey(name = "fk_ticket_message")
    )
    private Ticket ticket;
    @OneToMany(mappedBy = "ticketMessage", fetch = FetchType.LAZY)
    private List<MessageImage> messageImages;
}
