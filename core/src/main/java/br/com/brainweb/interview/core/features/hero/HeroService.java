package br.com.brainweb.interview.core.features.hero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.model.Hero;

@Service
public class HeroService {
    
    @Autowired
    private HeroRepository repository;
    
    public Hero createHero(Hero hero) {
	
	return null;
    }

    public Hero findById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    public List<Hero> findByName(String name) {
	// TODO Auto-generated method stub
	return null;
    }

}
