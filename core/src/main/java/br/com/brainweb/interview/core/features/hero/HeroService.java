package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.exceptions.HeroNotFoundException;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public Hero createHero(Hero hero)  {
        if(heroRepository.findByName(hero.getName()).size() > 0) {
            throw new DuplicatedHeroNameException(Constants.DUPLICATED_HERO_NAME_MESSAGE);
        }
        hero = heroRepository.save(hero);
        return hero;
    }

    public Hero findById(String id) {
        Optional<Hero> hero = heroRepository.findById(UUID.fromString(id));
        if(hero.isEmpty()) {
            throw new HeroNotFoundException(Constants.HERO_NOT_FOUND_MESSAGE);
        }
        return hero.get();
    }

    public List<Hero> getByName(String name) {
        return heroRepository.findByName(name);
    }

    public Hero update(String id, Hero hero) {
        Optional<Hero> old = heroRepository.findById(UUID.fromString(id));
        if(old.isEmpty()) {
            throw new HeroNotFoundException(Constants.HERO_NOT_FOUND_MESSAGE);
        }
        Hero updatedHero = mergeHeroAttributes(old.get(), hero);
        return heroRepository.save(updatedHero);
    }

    private Hero mergeHeroAttributes(Hero oldHero, Hero hero) {
        PowerStats oldPower = oldHero.getPowerStats();
        PowerStats power = hero.getPowerStats();

        PowerStats powerStats = PowerStats.builder().id(oldPower.getId())
                .strength(power.getStrength() == null ? oldPower.getStrength() : power.getStrength())
                .agility(power.getAgility() == null ? oldPower.getAgility() : power.getAgility())
                .dexterity(power.getDexterity() == null ? oldPower.getDexterity() : power.getDexterity())
                .intelligence(power.getIntelligence() == null ? oldPower.getIntelligence() : power.getIntelligence())
                .build();

        return Hero.builder()
                .id(oldHero.getId())
                .name(hero.getName() == null ? oldHero.getName() : hero.getName())
                .race( hero.getRace() == null ? oldHero.getRace() : hero.getRace())
                .enabled( hero.getEnabled() == null ? oldHero.getEnabled() : hero.getEnabled())
                .powerStats(powerStats)
                .build();
    }

}
