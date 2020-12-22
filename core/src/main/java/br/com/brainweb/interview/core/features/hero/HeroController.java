package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.model.input.HeroDisassembler;
import br.com.brainweb.interview.core.features.hero.model.input.HeroInput;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import static br.com.brainweb.interview.core.exceptionhandler.ExceptionController.conflict;
import static br.com.brainweb.interview.core.exceptionhandler.ExceptionController.notFound;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @Autowired
    private PowerStatsService powerStatsService;

    @Autowired
    private HeroDisassembler heroDisassembler;

    @GetMapping
    public ResponseEntity<?> findAllHeroes( @RequestParam (required = false) String name ) {
        if(name == null || name.isBlank()) {
            return ok(heroService.findAllHeroes());
        }
        return ok().body(heroService.findHeroFilterByName(name));

    }

    @GetMapping("/{idHero}")
    public ResponseEntity<?> findHeroById(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        return ok(hero);
    }

    @GetMapping("/{first}/compareto/{second}")
    public ResponseEntity<?> compareHeros(@PathVariable String first, @PathVariable String second) {
        Hero firstHero = findHero(first);
        Hero secondHero = findHero(second);
        return ok(powerStatsService.compareTo(firstHero, secondHero));
    }


    @PostMapping
    public ResponseEntity<?> addNewHero(@Valid @RequestBody HeroInput heroInput) {
        Hero hero = heroDisassembler.toDomainObject(heroInput);
        validHeroByName(hero);
        Hero entity = heroService.saveHero(hero);

        return created(URI.create(String.format("/heroes/%s", entity
                .getId()))).body(Map.of("id", entity.getId()));
    }

    @PutMapping("/{idHero}")
    public ResponseEntity<?> alterHero(@Valid @RequestBody HeroInput heroInput, @PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroDisassembler.copyToDomainObject(heroInput, hero);
        validHeroByNameAndId(hero);
        return ok(heroService.saveHero(hero));
    }

    @DeleteMapping("/{idHero}")
    public ResponseEntity<?> deleteHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.removeHero(hero);
        return noContent().build();
    }

    @PutMapping("/{idHero}/activation")
    public ResponseEntity<?> activeHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.active(hero);
        return noContent().build();
    }

    @DeleteMapping("/{idHero}/activation")
    public ResponseEntity<?> deactiveHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.deactivate(hero);
        return noContent().build();
    }

    private Hero findHero(String code) {
        var idHero = UUID.fromString(code);
        return heroService.findHeroById(idHero)
                .orElseThrow(() -> notFound(String.format("Herói de identificação '%s' não encontrado.", code)));
    }

    private void validHeroByName(Hero hero) {
        heroService.findHeroByName(hero.getName()).ifPresent(it -> {
            throw conflict(
                    String.format("Já existe um herói cadastrado com o nome '%s'.", hero.getName()));
        });

    }
    private void validHeroByNameAndId(Hero hero) {
        heroService.findHeroByNameAndId(hero).ifPresent(it -> {
            throw conflict(
                    String.format("Já existe um herói cadastrado com o nome '%s'.", hero.getName()));
        });
    }
}
