package br.com.brainweb.interview.core.features.hero.model.input;

import br.com.brainweb.interview.core.features.powerstats.model.PowerStatsInput;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.enums.EnumRace;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HeroInput {

    @NotBlank
    private String name;
    @NotNull
    private EnumRace race;
    @NotNull
    private Boolean enabled;
    @Valid
    @NotNull
    private PowerStatsInput powerStatsId;

    public HeroInput(){}

    private HeroInput(HeroInputBuilder heroBuilder){
        this.name = heroBuilder.name;
        this.race = heroBuilder.race;
        this.enabled = heroBuilder.enabled;
        this.powerStatsId = heroBuilder.powerStatsId;
    }

    public static class HeroInputBuilder{
        private String name;
        private EnumRace race;
        private Boolean enabled;
        private PowerStatsInput powerStatsId;

        public HeroInputBuilder name(String value){
            name = value;
            return this;
        }
        public HeroInputBuilder race(EnumRace value){
            race = value;
            return this;
        }
        public HeroInputBuilder enabled(Boolean value){
            enabled = value;
            return this;
        }
        public HeroInputBuilder powerStatsId(PowerStatsInput value){
            powerStatsId = value;
            return this;
        }
        public HeroInput build(){
            return new HeroInput(this);
        }
    }
}
