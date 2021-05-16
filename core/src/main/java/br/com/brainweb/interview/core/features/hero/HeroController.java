package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.dto.CompareHeroResponse;
import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("heros")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody Hero hero) {
        Hero created = heroService.createHero(hero);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
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
    public ResponseEntity<Hero> update(@PathVariable(name = "id") String id, @RequestBody Hero hero) {
        return new ResponseEntity<Hero>(heroService.update(id, hero), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        heroService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/compare/{hero1Id}/to/{hero2Id}")
    public ResponseEntity<CompareHeroResponse> compare(@PathVariable("hero1Id") String hero1Id,
                                                       @PathVariable("hero2Id") String hero2Id) {
        return new ResponseEntity<CompareHeroResponse>(heroService.compare(hero1Id, hero2Id), HttpStatus.OK);
    }
}
