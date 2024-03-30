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
public class TicketImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImageTicket;
    @Column(nullable = false, length = 500)
    private String url;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_ticket",
            foreignKey = @ForeignKey(name = "fk_image_ticket_ticket")
    )
    private Ticket ticket;
}
