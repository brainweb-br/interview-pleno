package br.com.brainweb.interview.core.features.hero;

import java.util.ArrayList;
import java.util.List;

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

    public Hero create(Hero hero){
	LOGGER.info("Invoking create(hero), values({})", StringUtils.join(new Object[] { hero }, ", "));
	try {
	    return service.createHero(hero);
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    }
    
    public Hero findById(String id) {
	 LOGGER.info("Invoking findById(id), values({})", StringUtils.join(new Object[] { id }, ", "));
	 Hero hero = null;
	 try {
	    hero = service.findById(id);
	 } catch (Exception e) {
	     LOGGER.error("{}", e.getMessage(), e);
	     throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 if(hero == null) throw new HeroNotFoundException();
	 return hero;
	
    }
    
    public List<Hero> findByName(String name){
	 LOGGER.info("Invoking findByName(name), values({})", StringUtils.join(new Object[] { name }, ", "));
	 try {
	     List<Hero> heroes = service.findByName(name);
	     if(heroes == null) return new ArrayList<Hero>();
	     return heroes;
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
}

class HeroNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
   
}
