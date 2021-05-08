package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;
    @Enumerated(EnumType.STRING)
    private RaceType race;
    private Boolean enable;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private PowerStats powerStats;

    public enum RaceType {
        HUMAN, ALIEN, DIVINE, CYBORG
    }
}


