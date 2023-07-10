package br.com.gubee.interview.core.features.hero.impl;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.impl.PowerStatsServiceImpl;
import br.com.gubee.interview.dto.ComparisonRequest;
import br.com.gubee.interview.dto.HeroComparison;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.dto.PowerStatsResponse;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepositoryImpl heroRepository;

    @Autowired
    PowerStatsRepository powerStatsRepository;

    @Override
    public ResponseEntity<HeroResponse> getById(UUID id) {
        HeroResponse hero = heroRepository.getById(id);
        return isNull(hero) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HeroResponse> getByName(String name) {
        HeroResponse hero = heroRepository.getByName(name);
        return isNull(hero) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @Override
    public ResponseEntity save(HeroResponse hero) {
        try {
            PowerStatsResponse powerStats = new PowerStatsResponse();
            powerStats.setStrength(hero.getStrength());
            powerStats.setAgility(hero.getAgility());
            powerStats.setDexterity(hero.getDexterity());
            powerStats.setIntelligence(hero.getIntelligence());
            UUID uuidPowerStats = powerStatsRepository.save(powerStats);
            heroRepository.save(hero, uuidPowerStats);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR IN CREATE", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(UUID id) {
        HeroResponse hero = heroRepository.getById(id);
        if(isNull(hero)){
            return new ResponseEntity<>("HERO NOT FOUND", HttpStatus.NOT_FOUND);
        }

        try {
            UUID uuidPowerStats = heroRepository.getPowerStatsUuidById(id);
            heroRepository.deleteById(id);
            powerStatsRepository.deleteById(uuidPowerStats);
            return new ResponseEntity<>("HERO DELETED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR IN DELETE", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity editHero(HeroResponse hero, UUID id) {
        HeroResponse heroLoaded = heroRepository.getById(id);
        if(isNull(heroLoaded)){
            return new ResponseEntity<>("HERO NOT FOUND", HttpStatus.NOT_FOUND);
        }

        try {
            heroRepository.editHero(hero, id);
            UUID uuidPowerStats = heroRepository.getPowerStatsUuidById(id);
            powerStatsRepository.editHero(hero, uuidPowerStats);
            return new ResponseEntity<>("HERO EDITED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR IN EDIT", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HeroComparison> compareHeroes(ComparisonRequest request) {
        HeroResponse hero1 = heroRepository.getById(request.getHero1());
        if(isNull(hero1)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HeroResponse hero2 = heroRepository.getById(request.getHero2());
        if(isNull(hero2)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int strengthDifference = hero2.getStrength() - hero1.getStrength();
        int agilityDifference = hero2.getAgility() - hero1.getAgility();
        int dexterityDifference = hero2.getDexterity() - hero1.getDexterity();
        int intelligenceDifference = hero2.getIntelligence() - hero1.getIntelligence();

        HeroComparison comparison = new HeroComparison(request.getHero1(), request.getHero2(),
                strengthDifference, agilityDifference, dexterityDifference, intelligenceDifference);

        return ResponseEntity.ok(comparison);
    }
}
