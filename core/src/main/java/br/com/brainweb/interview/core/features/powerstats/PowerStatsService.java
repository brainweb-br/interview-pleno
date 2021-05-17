package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    public PowerStats updatePowerAttributes(PowerStats oldPower, PowerStats newValues) {
        if(newValues != null) {
            if(newValues.getStrength() != null) oldPower.setStrength(newValues.getStrength());
            if(newValues.getAgility() != null) oldPower.setAgility(newValues.getAgility());
            if(newValues.getDexterity() != null) oldPower.setDexterity(newValues.getDexterity());
            if(newValues.getIntelligence() != null) oldPower.setIntelligence(newValues.getIntelligence());
        }
        return  oldPower;
    }

    public PowerStats compare(PowerStats powerStats1, PowerStats powerStats2) {
        return PowerStats.builder()
                .strength(powerStats1.getStrength() - powerStats2.getStrength())
                .agility(powerStats1.getAgility() - powerStats2.getAgility())
                .dexterity(powerStats1.getDexterity() - powerStats2.getDexterity())
                .intelligence(powerStats1.getIntelligence() - powerStats2.getIntelligence())
                .build();
    }

}
