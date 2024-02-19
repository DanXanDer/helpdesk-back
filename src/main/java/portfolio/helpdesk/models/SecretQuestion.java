package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SecretQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretQuestion;

    @Column(nullable = false, length = 100)
    private String questionName;

    @OneToMany(mappedBy = "secretQuestion", cascade = {CascadeType.ALL})
    private List<User> users;
}
