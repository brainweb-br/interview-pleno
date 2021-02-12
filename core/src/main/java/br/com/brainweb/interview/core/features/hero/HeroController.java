package br.com.brainweb.interview.core.features.hero;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import br.com.brainweb.interview.model.Hero;

@Controller
public class HeroController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroController.class);
    
    @Autowired
    private HeroService service;
    
    public HeroController(HeroService service) {
	this.service = service;
    }

    public Hero create(Hero hero){
	LOGGER.info("Invoking create(hero), values({})", StringUtils.join(new Object[] { hero }, ", "));
	if(hero == null) throw new IllegalArgumentException("Hero cannot be null.");
	if(StringUtils.isEmpty(hero.getName())) throw new IllegalArgumentException("Name is required.");
	
	Hero other = this.findByName(hero.getName());
	if(other != null) throw new IllegalArgumentException("A hero with name " + hero.getName() + " already exists.");
	if(StringUtils.isEmpty(hero.getRace())) throw new IllegalArgumentException("Race is required.");
	if(hero.getPowerStatsId() == null) throw new IllegalArgumentException("PowerStatsId is required.");
	
	try {
	    return service.createHero(hero);
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    }
    
    public Hero findById(String id) {
	 LOGGER.info("Invoking findById(id), values({})", StringUtils.join(new Object[] { id }, ", "));
	 if(StringUtils.isEmpty(id)) throw new IllegalArgumentException("Id is required");
	 Hero hero = null;
	 try {
	    hero = service.findById(UUID.fromString(id));
	 } catch (Exception e) {
	     LOGGER.error("{}", e.getMessage(), e);
	     throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 if(hero == null) throw new HeroNotFoundException();
	 return hero;
	
    }
    
    public Hero findByName(String name){
	 LOGGER.info("Invoking findByName(name), values({})", StringUtils.join(new Object[] { name }, ", "));
	 if(StringUtils.isEmpty(name)) throw new IllegalArgumentException("Name is required.");
	 try {
	     return service.findByName(name);
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    public Hero update(Hero hero) {
	if(hero == null) throw new IllegalArgumentException("Hero cannot be null.");
	if(hero.getId() == null) throw new IllegalArgumentException("Id cannot be null.");
	Hero heroFound = this.findById(hero.getIdString());
	if(heroFound == null) throw new HeroNotFoundException();
	if(StringUtils.isEmpty(hero.getName())) throw new IllegalArgumentException("Name is required.");
	Hero other = this.findByName(hero.getName());
	if(other != null && (other.getId() != hero.getId())) throw new IllegalArgumentException("A hero with name " + hero.getName() + " already exists.");
	if(StringUtils.isEmpty(hero.getRace())) throw new IllegalArgumentException("Race is required.");
	if(hero.getPowerStatsId() == null) throw new IllegalArgumentException("PowerStatsId is required.");
	
	try {
	    return service.update(hero);
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    public void delete(String id) {
	if(id == null) throw new IllegalArgumentException("Id cannot be null.");
	Hero heroFound = this.findById(id);
	if(heroFound == null) throw new HeroNotFoundException();

	try {
	    service.delete(UUID.fromString(id));
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
}

class HeroNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
   
}
