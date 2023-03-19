package br.com.brainweb.interview.core.features.powerstats.model;

import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class PowerStatsInput {

    @Max(10)
    @NotNull
    private int strength;
    @Max(10)
    @NotNull
    private int agility;
    @Max(10)
    @NotNull
    private int dexterity;
    @Max(10)
    @NotNull
    private int intelligence;

    public PowerStatsInput(){}

     PowerStatsInput(PowerStatsBuilder powerStatsBuilder){
        this.strength = powerStatsBuilder.strength;
        this.agility = powerStatsBuilder.agility;
        this.dexterity = powerStatsBuilder.dexterity;
        this.intelligence = powerStatsBuilder.intelligence;
    }

    public static class PowerStatsBuilder{
        private int strength;
        private int agility;
        private int dexterity;
        private int intelligence;

        public PowerStatsBuilder strength(int value){
            strength = value;
            return this;
        }
        public PowerStatsBuilder agility(int value){
            agility = value;
            return this;
        }
        public PowerStatsBuilder dexterity(int value){
            dexterity = value;
            return this;
        }
        public PowerStatsBuilder intelligence(int value){
            intelligence = value;
            return this;
        }

        public PowerStatsInput build(){
            return new PowerStatsInput(this);
        }
    }
}
