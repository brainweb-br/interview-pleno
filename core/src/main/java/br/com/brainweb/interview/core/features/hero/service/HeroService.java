package br.com.brainweb.interview.core.features.hero.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.brainweb.interview.core.repository.HeroRepository;
import br.com.brainweb.interview.core.repository.PowerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.CompareHero;

@Service
@Component
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerRepository;


    public Hero saveHero(Hero hero){
        powerRepository.save(hero.getPower());
        heroRepository.save(hero);
        return hero;
    }

    public Optional<Hero> findById(UUID id) {
        return heroRepository.findById(id);
    }

    public List<Hero> findByName(String name) {
        return heroRepository.findByNameIgnoreCase(name);
    }

    public Hero editHero(Hero hero) {
        hero.getPower().setUpdatedAt(new Date());
        hero.setUpdatedAt(new Date());
        hero.setPower(powerRepository.save(hero.getPower()));
        return heroRepository.save(hero);
    }

    public CompareHero compareHeroes(UUID idHeroA, UUID idHeroB)  {
        Hero heroA = heroRepository.findById(idHeroA).get();
        Hero heroB = heroRepository.findById(idHeroB).get();
        if (heroA !=null && heroB!=null) {
            return compareHeroes(heroA,heroB);
        }
        return null;

    }

    private CompareHero compareHeroes(Hero a, Hero b) {

        Integer somatorio = a.getPower().getAgility()+a.getPower().getDexterity()+a.getPower().getIntelligence()+a.getPower().getStrength();
        Integer somatorioB = b.getPower().getAgility()+b.getPower().getDexterity()+b.getPower().getIntelligence()+b.getPower().getStrength();
        if (somatorio > somatorioB){
            return compare(a,false);
        }else if (somatorioB > somatorio){
            return compare(b,false);
        }
        return compare(a,true);
    }

    private CompareHero compare(Hero strong,Boolean sameForce) {
        CompareHero compareHero = new CompareHero();
        compareHero.setAgility(strong.getPower().getAgility());
        compareHero.setDexterity(strong.getPower().getDexterity());
        compareHero.setIntelligence(strong.getPower().getIntelligence());
        compareHero.setStrength(strong.getPower().getStrength());
        compareHero.setIds(strong.getId().toString());
        if (sameForce == false) {
            compareHero.setName(strong.getName());
        }
        else {
            compareHero.setName("SAME POWER");
        }
        compareHero.setSamePower(sameForce);
        return compareHero;
    }

    public void deleteHero(Hero hero) {
        heroRepository.delete(hero);
        powerRepository.delete(hero.getPower());
    }

    public List<Hero> findByIds(List<UUID> heros) {
       return heroRepository.findByIds(heros);
    }
}
