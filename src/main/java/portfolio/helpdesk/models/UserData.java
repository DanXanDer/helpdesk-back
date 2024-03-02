package portfolio.helpdesk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @ManyToOne(optional = true)
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

    @Column(length = 100)
    private String secretAnswer;

    @Column
    private Boolean enabled = true;

    @Column
    private Boolean firstLogin = true;

    @OneToOne(mappedBy = "userData", cascade = {CascadeType.ALL})
    private Worker worker;

    @OneToOne(mappedBy = "userData", cascade = {CascadeType.ALL})
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    )
    private Set<Role> roles;

}
