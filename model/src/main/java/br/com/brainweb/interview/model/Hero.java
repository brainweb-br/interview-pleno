package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.enums.EnumRace;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hero {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumRace race;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(updatable = false)
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,
            name = "power_stats_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_power_stats"))
    private PowerStats powerStatsId;

    @PrePersist
    public void addingValues(){
        this.created_at = OffsetDateTime.now();
        this.updated_at = OffsetDateTime.now();
    }

    @PreUpdate
    public void updatingValues(){
        this.updated_at = OffsetDateTime.now();
    }

    public void activate(){
        this.enabled = true;
    }

    public void deactivate(){
        this.enabled = false;
    }
}
