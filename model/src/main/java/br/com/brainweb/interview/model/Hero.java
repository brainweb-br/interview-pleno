package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;
    @Enumerated(EnumType.STRING)
    private RaceType race; //todo validation
    private Boolean enable;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private PowerStats powerStats;

    public enum RaceType {
        HUMAN, ALIEN, DIVINE, CYBORG
    }
}


