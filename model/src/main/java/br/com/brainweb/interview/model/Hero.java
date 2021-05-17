package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.enums.RaceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hero")
@Builder
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, nullable = false)
    private UUID id;

    @NotNull
    @NotNull(message = "Informe o campo 'name'")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o campo 'race'")
    private RaceType race;

    @NotNull(message = "Informe o campo 'enabled'")
    private Boolean enabled;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,
            name = "power_stats_id",
            referencedColumnName = "id")
    @JsonProperty("powerStats")
    private PowerStats powerStats;

    @PrePersist
    public void addingValues(){ this.createdAt = this.updatedAt = Timestamp.from(Instant.now()); }

    @PreUpdate
    public void updatingValues(){
        this.updatedAt = Timestamp.from(Instant.now());
    }
}


