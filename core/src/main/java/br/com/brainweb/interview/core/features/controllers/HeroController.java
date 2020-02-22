package br.com.brainweb.interview.core.features.controllers;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.core.features.repositories.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController implements IController {
    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PowerStatsRepository powerStatsRepository;



    @Override
    public ResponseEntity save(Object dto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity update(Object dto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Object dto) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws Exception {
        return null;
    }


    @Override
    public ResponseEntity find(Long id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Page> findAll(Pageable pageable) throws Exception {
        return null;
    }



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


