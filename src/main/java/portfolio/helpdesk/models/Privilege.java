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

public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrivilege;

    @Column(nullable = false, length = 50)
    private String icon;

    @Column(nullable = false, length = 50)
    private String authority;

    @Column(nullable = false, length = 100)
    private String url;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

}
