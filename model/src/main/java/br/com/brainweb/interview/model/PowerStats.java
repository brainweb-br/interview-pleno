package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.DTO.HeroDTO;
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
@Entity(name = "power_stats")
public class PowerStats {
    public PowerStats(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    private Timestamp created_at;
    private Timestamp updated_at;

    public void setValuesDefault(HeroDTO heroDTO){
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
    }

    public void update(PowerStats powerStats){
        this.setAgility(powerStats.getAgility());
        this.setDexterity(powerStats.getDexterity());
        this.setIntelligence(powerStats.getIntelligence());
        this.setStrength(powerStats.getStrength());
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }

    public PowerStats( UUID id,
             int strength,
             int agility,
             int dexterity,
             int intelligence,
             Timestamp created_at,
             Timestamp updated_at){

        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }
}
