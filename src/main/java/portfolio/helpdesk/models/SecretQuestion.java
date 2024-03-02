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
public class SecretQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretQuestion;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "secretQuestion", cascade = {CascadeType.ALL})
    private List<UserData> userData;
}
