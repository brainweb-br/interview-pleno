package br.com.brainweb.interview.model;

import br.com.brainweb.interview.enums.EnumRace;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Builder
public class Hero {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumRace race;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(updatable = false)
    private Timestamp created_at;
    @Column(insertable = false)
    private Timestamp updated_at;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,
            name = "power_stats_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_power_stats"))
    private PowerStats powerStatsId;

    @PrePersist
    public void addingValues(){
        this.id = UUID.randomUUID();
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void updatingValues(){
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void activate(){
        this.enabled = true;
    }

    public void deactivate(){
        this.enabled = false;
    }

}
