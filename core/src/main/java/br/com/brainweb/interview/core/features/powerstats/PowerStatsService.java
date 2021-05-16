package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    public PowerStats updatePowerAttributes(PowerStats oldPower, PowerStats power) {
        if(power != null) {
            if(power.getStrength() != null) oldPower.setStrength(power.getStrength());
            if(power.getAgility() != null) oldPower.setAgility(power.getAgility());
            if(power.getDexterity() != null) oldPower.setDexterity(power.getDexterity());
            if(power.getIntelligence() != null) oldPower.setIntelligence(power.getIntelligence());
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
