package br.com.brainweb.interview.core.features.controllers;

import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody HeroRequestDTO heroRequestDTO) {
        heroService.save(heroRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity update(Object dto) throws Exception {
        return null;
    }

    @DeleteMapping(path = "{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {

        heroService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long id) throws Exception {
        return null;
    }

    @GetMapping(path = "/{uuid:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<HeroResponseDTO> find(@PathVariable String uuid) {

        HeroResponseDTO heroResponseDTO = heroService.find(uuid);
        if (heroResponseDTO != null) {
            return new ResponseEntity<>(heroResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Page> findAll(Pageable pageable) throws Exception {
        return null;
    }


    //@PostMapping
    public void save1() {

        Hero hero;
        // given
        PowerStats powerStats = new PowerStats();

        powerStats.setStrength(1);
        powerStats.setAgility(2);
        powerStats.setDexterity(3);
        powerStats.setIntelligence(4);
        // powerStatsRepository.save(powerStats);

        hero = new Hero();
        hero.setName(UUID.randomUUID().toString());
        hero.setRace(Race.ALIEN);
        hero.setPowerStats(powerStats);
        hero.setEnabled(true);

        // heroRepository.save(hero);
    }
}


