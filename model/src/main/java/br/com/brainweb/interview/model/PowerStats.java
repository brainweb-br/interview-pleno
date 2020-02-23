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

    public void setValuesDefault(){
        this.setCreated_at(new Timestamp(System.currentTimeMillis()));
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }

    public void update(PowerStats powerStats){
        this.setAgility(powerStats.getAgility());
        this.setDexterity(powerStats.getDexterity());
        this.setIntelligence(powerStats.getIntelligence());
        this.setStrength(powerStats.getStrength());
        this.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }
}
