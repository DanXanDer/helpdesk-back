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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_CLIENT_USER"))
    private UserData userData;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_area",
            foreignKey = @ForeignKey(name = "FK_CLIENT_AREA"))
    private Area area;

    @Column(nullable = false, length = 9)
    private String anydesk;

    @Column(nullable = false, length = 9)
    private String teamviewer;
}
