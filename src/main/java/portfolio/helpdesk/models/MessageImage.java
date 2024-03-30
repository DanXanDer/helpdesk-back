package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MessageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImageMessage;

    @Column(nullable = false, length = 100)
    private String url;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_ticket_message",
            foreignKey = @ForeignKey(name = "fk_image_message_ticket_message")
    )
    private TicketMessage ticketMessage;
}
