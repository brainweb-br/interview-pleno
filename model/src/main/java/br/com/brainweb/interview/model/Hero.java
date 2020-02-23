package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.enums.Race;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@Entity(name = "hero")
public class Hero {
    public Hero (){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String race;
    private boolean enabled;
    private Timestamp created_at;
    private Timestamp updated_at;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private PowerStats powerStats;

    public void setValuesDefault(HeroDTO heroDTO, PowerStats powerStats){
        this.setPowerStats(powerStats);
        this.setRace(heroDTO.getRace().name());

        if (heroDTO.getCreated_at() != null) {
            this.setCreated_at(heroDTO.getCreated_at());
        } else {
            this.setCreated_at(new Timestamp(System.currentTimeMillis()));
        }

        if (heroDTO.getUpdated_at() != null) {
            this.setUpdated_at(heroDTO.getUpdated_at());
        } else {
            this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        }

        this.setEnabled(true);
    }

    public void update(Hero hero, HeroDTO heroDTO){
        this.setName(hero.getName());
        this.setRace(heroDTO.getRace().name());
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }

    public Hero(UUID id, String name, String race, boolean enabled, Timestamp created_at, Timestamp updated_at){
        this.id = id;
        this.name = name;
        this.race = race;
        this.enabled = enabled;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
