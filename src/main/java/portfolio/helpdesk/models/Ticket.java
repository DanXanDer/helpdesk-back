package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTicket;
    @ManyToOne
    @JoinColumn(
            name = "id_client",
            foreignKey = @ForeignKey(name = "fk_ticket_client")
    )
    private Client client;
    @ManyToOne
    @JoinColumn(
            name = "id_worker",
            foreignKey = @ForeignKey(name = "fk_ticket_worker")
    )
    private Worker worker;

    @Column(nullable = false, length = 100)
    private String summary;

    @Column(nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    TicketStatus ticketStatus;

    @Column(nullable = false, length = 100)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(nullable = false, length = 100)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<TicketMessage> messages;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<TicketImage> images;
}
