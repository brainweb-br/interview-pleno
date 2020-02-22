package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {
    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PowerStatsRepository powerStatsRepository;


    @PostMapping
    public void save() {

        Hero hero;
        // given
        PowerStats powerStats = new PowerStats();

        powerStats.setStrength(1);
        powerStats.setAgility(2);
        powerStats.setDexterity(3);
        powerStats.setIntelligence(4);
        powerStatsRepository.save(powerStats);

        hero = new Hero();
        hero.setName(UUID.randomUUID().toString());
        hero.setRace(Race.ALIEN);
        hero.setPowerStats(powerStats);
        hero.setEnabled(true);

        heroRepository.save(hero);
    }


}
