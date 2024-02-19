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

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String type;
    private String secretAnswer;
    private boolean enabled;
    private boolean firstLogin;
}
