package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_secret_question", foreignKey = @ForeignKey(name = "FK_USER_SECRET_QUESTION"))
    private SecretQuestion secretQuestion;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(nullable = false, length = 100)
    private String secretAnswer;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean firstLogin = true;
}
