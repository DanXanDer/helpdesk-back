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
public class Client {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserData userData;

    @ManyToOne
    @JoinColumn(
            name = "id_area",
            foreignKey = @ForeignKey(name = "FK_CLIENT_AREA"))
    private Area area;

    @Column(nullable = false, length = 9)
    private String anydesk;

    @Column(nullable = false, length = 9)
    private String teamviewer;

    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Report> report;
}
