package br.com.brainweb.interview.core.features.hero;

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

    public Hero findById(String id) {
	return this.repository.findById(id);
    }

    public Hero findByName(String name) {
	return repository.findByName(name);
    }

    public void update(Hero hero) {
	repository.update(hero);
    }

    public void delete(String id) {
	repository.delete(id);
    }

}
