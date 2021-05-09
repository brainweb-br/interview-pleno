package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hero")
public class Hero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;
    @Enumerated(EnumType.STRING)
    private RaceType race;
    private Boolean enabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToOne
    @JoinColumn(name = "power_stats_id", nullable = false)
    private PowerStats powerStats;

    public enum RaceType {
        HUMAN, ALIEN, DIVINE, CYBORG
    }
}


