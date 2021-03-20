package br.com.brainweb.interview.core.features.hero;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.core.features.util.CompareHero;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@RestController
public class HeroController {
	
	private static final Log log = LogFactory.getLog(HeroController.class);
	
	private HeroService heroService;
	
	private PowerStatsService powerStatsService;

	@Autowired
	public HeroController(HeroService heroService, PowerStatsService powerStatsService) {
		this.heroService = heroService;
		this.powerStatsService = powerStatsService;
	}

	@PostMapping(path ="saveHero")
	@Transactional
	public ResponseEntity<?> saveHero(@Valid @RequestBody Hero hero) {
		
		try {		
			PowerStats powerStats = powerStatsService.savePowerStats(hero.getPowerStats());
			hero.setPowerStats(powerStats);
			heroService.saveHero(hero);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(" Erro ao Salvar o Heroi" + hero.getName() + " , ERRO: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "getHeroById/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable(value = "id") String id) throws Exception {
		try {
			Hero hero = heroService.getHeroById(id);
			if (hero != null) {
				return new ResponseEntity<Hero>(hero, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
	
	@GetMapping(path = "getHeroesByName/{name}")
    public ResponseEntity<List<Hero>> getHeroesByName(@PathVariable(value = "name") String name) {
        List<Hero> heroes = heroService.getHeroesByName(name);
        if (heroes != null && !heroes.isEmpty()) {
        	return new ResponseEntity<List<Hero>>(heroes, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
    }
	
	@PutMapping(path = "updateHero/{id}")
	@Transactional
    public ResponseEntity<Hero> updateHero(@PathVariable(value = "id") String id, 
    		@Valid @RequestBody Hero atuzalicaoHero) throws Exception {
		
		try {
			Hero oldHero = heroService.getHeroById(id);
			if (oldHero != null) {
				heroService.saveHero(atuzalicaoHero);
				powerStatsService.savePowerStats(atuzalicaoHero.getPowerStats());
				return new ResponseEntity<Hero>(atuzalicaoHero, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
	
	@DeleteMapping(path ="deleteHero/{id}")
	@Transactional
    public ResponseEntity<Hero> deleteHero(@PathVariable(value = "id") String id) throws Exception {
		try {
			Hero hero = heroService.getHeroById(id);
			if (hero != null) {
				powerStatsService.deletePowerStats(hero.getPowerStats());
				heroService.deleteHero(hero);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
	
	@GetMapping(path = "compareHeroes/{idA}/{idB}")
    public ResponseEntity<CompareHero> compareHeroes(@PathVariable(value = "idA") String idA, 
    		@PathVariable(value = "idB") String idB) throws Exception {
		
		try {
			CompareHero compareHero = heroService.compareHeroes(idA, idB);
			if (compareHero != null) {
				return new ResponseEntity<CompareHero>(compareHero, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
