package portfolio.helpdesk.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReport;

    @ManyToOne
    @JoinColumn(
            name = "id_client",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_REPORT_CLIENT")
    )
    private Client client;

    @Column(nullable = false, length = 100)
    private String resume;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime creationDate;
}
