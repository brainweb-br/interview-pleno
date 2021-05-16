package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.exceptions.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.dto.CompareHeroResponse;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;

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
        Hero old = this.findById(id);
        Hero updatedHero = updateHeroAttributes(old, hero);
        return heroRepository.save(updatedHero);
    }

    public void delete(String id) {
        Hero hero = this.findById(id);
        heroRepository.delete(hero);
    }

    private Hero updateHeroAttributes(Hero oldHero, Hero hero) {
        if(hero.getName() != null) oldHero.setName(hero.getName());
        if(hero.getRace() != null) oldHero.setRace(hero.getRace());
        if(hero.getEnabled() != null) oldHero.setEnabled(hero.getEnabled());

        oldHero.setPowerStats(powerStatsService.updatePowerAttributes(oldHero.getPowerStats(), hero.getPowerStats()));

        return oldHero;
    }

    public CompareHeroResponse compare(String hero1Id, String hero2Id) {
        Hero hero1 = this.findById(hero1Id);
        Hero hero2 = this.findById(hero2Id);
        PowerStats result = powerStatsService.compare(hero1.getPowerStats(), hero2.getPowerStats());
       return  new CompareHeroResponse(hero1.getId(), hero2.getId(), result.getStrength(), result.getAgility(), result.getDexterity(), result.getIntelligence());
    }
}
