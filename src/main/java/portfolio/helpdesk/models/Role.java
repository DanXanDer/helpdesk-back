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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole" ),
            inverseJoinColumns = @JoinColumn(name = "id_privilege", referencedColumnName = "idPrivilege")
    )
    private Set<Privilege> privileges;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL})
    private Set<UserData> users;

}
