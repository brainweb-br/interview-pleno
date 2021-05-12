package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public Hero createHero(Hero hero)  {
        if(heroRepository.findByName(hero.getName()) != null) {
            throw new DuplicatedHeroNameException("Esse nome de heroi já existe.");
        }
        hero = heroRepository.save(hero);
        return hero;
    }
}
