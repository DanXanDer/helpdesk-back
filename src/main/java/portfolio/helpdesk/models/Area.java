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
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArea;

    @ManyToOne
    @JoinColumn(name = "id_campus", foreignKey = @ForeignKey(name = "FK_AREA_CAMPUS"))
    private Campus campus;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 100)
    private String name;
}
