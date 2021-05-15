package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("heros")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody Hero hero) {
        return new ResponseEntity<Hero>(heroService.createHero(hero), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getById(@PathVariable("id") String id) {
        return new ResponseEntity<Hero>(heroService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hero>> searchByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<List<Hero>>(heroService.getByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> update(@RequestParam(name = "id") String id, @RequestBody Hero hero) {
        return new ResponseEntity<Hero>(heroService.update(id, hero), HttpStatus.OK);
    }
}
