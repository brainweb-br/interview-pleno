package br.com.brainweb.interview.core.features.hero;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.model.Hero;

@Service
public class HeroService {
    
    @Autowired
    private HeroRepository repository;
    
    public void create(Hero hero) {
	this.repository.create(hero);
    }

    public Hero findById(UUID id) {
	// TODO Auto-generated method stub
	return null;
    }

    public Hero findByName(String name) {
	return repository.findByName(name);
    }

    public Hero update(Hero hero) {
	// TODO Auto-generated method stub
	return null;
    }

    public void delete(UUID id) {
	// TODO Auto-generated method stub
	
    }

}
