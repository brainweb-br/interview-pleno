package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("heros")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody Hero hero) {
        System.out.println(hero.getPowerStats());
        return new ResponseEntity<Hero>(heroService.createHero(hero), HttpStatus.CREATED);
    }
}
