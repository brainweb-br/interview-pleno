package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HeroService {


    @Autowired
    private HeroRepository heroRepository;


    public List<Hero> getAll() {
        return heroRepository.findAll();
    }

    public Hero findById(UUID id) {
        return heroRepository.findById(id).get();
    }

    public List<Hero> findByName(String name) {
        return heroRepository.findByName(name);
    }

    public void save(Hero hero) {
        heroRepository.saveAndUpdate(hero);
    }

    public void delete(UUID id) {
    }
}
