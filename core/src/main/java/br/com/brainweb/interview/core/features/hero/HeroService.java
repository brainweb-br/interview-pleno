package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.exceptions.HeroNotFoundException;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public Hero createHero(Hero hero)  {
        if(heroRepository.findByName(hero.getName()) != null) {
            throw new DuplicatedHeroNameException(Constants.DUPLICATED_HERO_NAME_MESSAGE);
        }
        hero = heroRepository.save(hero);
        return hero;
    }

    public Hero getHeroById(String id) {
        Optional<Hero> hero = heroRepository.findById(UUID.fromString(id));
        if(hero.isEmpty()) {
            throw new HeroNotFoundException(Constants.HERO_NOT_FOUND_MESSAGE);
        }
        return hero.get();
    }

}
