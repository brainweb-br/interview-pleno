package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.core.features.util.CompareHero;
import br.com.brainweb.interview.model.Hero;

@Service("heroService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class HeroService {
	
	private static final Log log = LogFactory.getLog(HeroService.class);
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Transactional
	public void saveHero(Hero hero) {
		heroRepository.saveAndFlush(hero);
	}
	
	public Hero getHeroById(String id) throws Exception {
		UUID idUUID = null;
		try {
			idUUID = UUID.fromString(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
        return heroRepository.getOne(idUUID);
    }
	
    public List<Hero> getHeroesByName(String nome) {
        return heroRepository.findByNameIgnoreCaseContaining(nome);
    }
    
    @Transactional
    public void deleteHero(Hero hero) {
    	heroRepository.delete(hero);
    }
    
    @Given("Compara dois super herois")
	public CompareHero compareHeroes(String idHeroA, String idHeroB) throws Exception {
		CompareHero compareHero = null;
		
    	Hero heroA = this.getHeroById(idHeroA);
    	if (heroA != null) {
    		Hero heroB = this.getHeroById(idHeroB);
    		if (heroB != null) {
    			compareHero = compareHeroes(heroA, heroB);
    		}
    	}
    	
    	return compareHero;
    }

    @When("Faz as comparacoes de todos os atributos da classe PowerStats")
	private CompareHero compareHeroes(Hero heroA, Hero heroB) {

		CompareHero compareHero = new CompareHero(); 
		compareHero.setIds(heroA.getId() + " , " + heroB.getId());
		compareHero.setStrength(compare(heroA.getPowerStats().getStrength(), 
				heroB.getPowerStats().getStrength()));
		compareHero.setAgility(compare(heroA.getPowerStats().getAgility(), 
				heroB.getPowerStats().getAgility()));
		compareHero.setDexterity(compare(heroA.getPowerStats().getDexterity(), 
				heroB.getPowerStats().getDexterity()));
		compareHero.setIntelligence(compare(heroA.getPowerStats().getIntelligence(), 
				heroB.getPowerStats().getIntelligence()));
		
		return compareHero;
	}
	
    @Then("Mostra o resultado final, mostrando de quem o atributo esta maior, menor ou igual")
	private String compare(Short a, Short b) {
		if (a == b) {
			return ("= , =");
		} else if (a > b) {
			return ("+ , -");
		} else {
			return ("- , +");
		}
	}
	
}