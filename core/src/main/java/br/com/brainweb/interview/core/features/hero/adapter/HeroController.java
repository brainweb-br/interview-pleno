package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.HeroService;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroCommand;
import br.com.brainweb.interview.core.features.hero.adapter.dto.CompareHeroQuery;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @GetMapping("/{id}")
    public ResponseEntity<HeroQuery> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(HeroDTOAssembler.toHeroQuery(heroService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<HeroQuery>> search(@RequestParam("name") String name) {
        return ResponseEntity.ok(
                heroService.search(name)
                        .stream()
                        .map(HeroDTOAssembler::toHeroQuery)
                        .collect(Collectors.toList()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(@RequestBody HeroCommand heroCommand) {
        return ResponseEntity.ok(heroService.create(HeroDTOAssembler.toHero(heroCommand)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") UUID id,
                                       @RequestBody HeroCommand heroCommand) {
        heroService.update(id, HeroDTOAssembler.toHero(heroCommand));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        heroService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/power-stats/comparisons")
    public ResponseEntity<CompareHeroQuery> compare(@RequestParam("heroId1") UUID heroId1,
                                                    @RequestParam("heroId2") UUID heroId2) {
        return ResponseEntity.ok(HeroDTOAssembler.toCompareHeroQuery(heroService.compare(heroId1, heroId2)));
    }

}
