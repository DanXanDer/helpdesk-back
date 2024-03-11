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
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_secret_question", foreignKey = @ForeignKey(name = "FK_USER_SECRET_QUESTION"))
    private SecretQuestion secretQuestion;

    @Column
    private Boolean firstLogin = true;

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

    @Column(length = 100)
    private String secretAnswer;

    @Column
    private Boolean enabled = true;


    @OneToOne(mappedBy = "userData")
    private Worker worker;

    @OneToOne(mappedBy = "userData")
    private Client client;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_role",
            foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
    private Role role;

}
