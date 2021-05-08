package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public Hero createHero(Hero hero) throws ResponseStatusException {
        try {
            hero = heroRepository.save(hero);
        } catch(IllegalArgumentException ex) {
            // todo https://www.baeldung.com/spring-exceptions-json
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return hero;
    }
}
