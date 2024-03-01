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
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idWorker;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_WORKER_USER"))
    private User user;
}
