package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.HeroService;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroCommand;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/heroes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @GetMapping
    public String hello() {
        System.out.println("Hello, heroes.");
        return "Hello, heroes.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroQuery> findById(@PathVariable String id) {
        return ResponseEntity.ok(HeroDTOAssembler.toHeroQuery(heroService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<HeroQuery>> search(@RequestParam("name") String name) {
        return null;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody HeroCommand heroCommand) {
        return ResponseEntity.ok(heroService.create(HeroDTOAssembler.toHero(heroCommand)));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody HeroCommand heroCommand) {
        heroService.update(HeroDTOAssembler.toHero(heroCommand));
        return ResponseEntity.ok().build();
    }

}
