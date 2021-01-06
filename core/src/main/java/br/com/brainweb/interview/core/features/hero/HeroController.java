package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DtoHeroResponse;
import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    public ResponseEntity<DtoHeroResponse> createHero(@RequestBody Hero newHero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.createHero(newHero));
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<DtoHeroResponse> findHeroById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.findHeroById(id));
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<DtoHeroResponse> filterHeroesByName(@RequestParam(value = "name") String heroName) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.filterHeroesByName(heroName));
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DtoHeroResponse> updateHero(@RequestBody Hero newHero, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.updateHero(newHero, id));
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteHero(@PathVariable String id) {
        return heroService.deleteHero(id);
    }
}
