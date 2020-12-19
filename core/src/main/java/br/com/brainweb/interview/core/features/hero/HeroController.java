package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptionhandler.ExceptionController;
import br.com.brainweb.interview.core.features.hero.model.HeroInput;
import br.com.brainweb.interview.model.Hero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllHeroes( @RequestParam (required = false) String name ) {
        if(!name.isBlank()) {
            return ok(heroService.findAllHeroes());
        }
        else{
            return ok().body(heroService.findHeroFilterByName(name));
        }
    }

    @GetMapping("/{idHero}")
    public ResponseEntity<?> findHeroById(@PathVariable String idHero) {
        return ok(findHero(idHero));
    }



    @PostMapping
    public ResponseEntity<?> addNewHero(@Valid @RequestBody HeroInput heroInput) {
        Hero hero = modelMapper.map(heroInput, Hero.class);
        validateHero(hero, true);
        Hero entity = heroService.saveHero(hero);
        return created(URI.create(String.format("/heroes/%s", entity
                .getId()))).body(Map.of("id", entity.getId()));
    }

    @PutMapping("/{idHero}")
    public ResponseEntity<?> alterHero(@Valid @RequestBody HeroInput heroInput, @PathVariable String idHero) {
        Hero currentHero = findHero(idHero);
        modelMapper.map(heroInput, currentHero);
        validateHero(currentHero, false);
        return ok(heroService.saveHero(currentHero));
    }

    @DeleteMapping("/{idHero}")
    public ResponseEntity<?> deleteHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.removeHero(hero);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idHero}/activation")
    public ResponseEntity<?> activeHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.active(hero);
        return null;
    }

    @DeleteMapping("/{idHero}/activation")
    public ResponseEntity<?> deactiveHero(@PathVariable String idHero) {
        Hero hero = findHero(idHero);
        heroService.deactivate(hero);
        return null;
    }

    private Hero findHero(String code) {
        return heroService
                .findHeroById(UUID.fromString(code))
                .orElseThrow(() -> notFound(String.format("Herói de identificação '%s' não encontrado.", code)));

    }


    private void validateHero(Hero hero, boolean newHero) {
        if (newHero) {
            heroService.findHeroByName(hero.getName())
                    .ifPresent(it -> {
                        throw conflict(
                                String.format("Já existe um herói cadastrado com o nome '%s'.", hero.getName()));
                    });
        } else {
            heroService.findHeroByNameAndId(hero.getId(), hero.getName()).ifPresent(it -> {
                throw conflict(
                        String.format("Já existe um herói cadastrado com o nome '%s', não é permitido dois heróis com o mesmo nome.", hero.getName()));
            });
        }
    }
}
