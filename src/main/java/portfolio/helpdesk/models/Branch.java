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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBranch;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_company",
            foreignKey = @ForeignKey(name = "FK_CAMPUS_COMPANY"))
    private Company company;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "branch",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Area> areas;
}
