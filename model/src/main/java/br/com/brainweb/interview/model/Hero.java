package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.enums.Race;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private UUID power_stats_id;
    private boolean enabled;
    private Timestamp created_at;
    private Timestamp updated_at;

    public void setValues (HeroDTO heroDTO, UUID powerStatsId){
        this.setPower_stats_id(powerStatsId);
        this.setRace(Race.values()[heroDTO.getRace()].name());
        this.setCreated_at(new Timestamp(System.currentTimeMillis()));
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }
}
