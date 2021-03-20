package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.adapters.HeroAdapter;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public Optional<Iterable<HeroModel>> listAll(Optional<String> name) {
        if(name.isPresent()){
            return Optional.of(heroRepository.findByName(name.get()));
        }
        return Optional.of(heroRepository.findAll());
    }

    public Optional<HeroModel> getById(UUID id) {
        return heroRepository.findById(id);
    }

    public HeroModel save(Hero hero) {
        return heroRepository.save(HeroAdapter.heroToHeroModelWithoutId(hero));
    }

    public HeroModel update(Hero hero) {
        return heroRepository.save(HeroAdapter.heroToHeroModel(hero));
    }

    public void remove(UUID id) {
        heroRepository.deleteById(id);
    }

}
