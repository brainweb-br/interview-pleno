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
    //private UUID power_stats_id;
    private boolean enabled;
    private Timestamp created_at;
    private Timestamp updated_at;

    @OneToOne
    @JoinColumn
    private PowerStats powerStats;

    public void setValuesDefault(HeroDTO heroDTO, PowerStats powerStats){
        this.setPowerStats(powerStats);
        this.setRace(Race.values()[heroDTO.getRaceValue()].name());
        this.setCreated_at(new Timestamp(System.currentTimeMillis()));
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        this.setEnabled(true);
    }
}
