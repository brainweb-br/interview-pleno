package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.adapters.HeroAdapter;
import br.com.brainweb.interview.core.features.dto.ResponseContent;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("heros")
@AllArgsConstructor
public class HeroController {

    private static final Logger LOG = LoggerFactory.getLogger(HeroController.class);
    private HeroService heroService;

    @GetMapping
    public ResponseContent getHeroes(@RequestParam(name = "name") Optional<String> name){
        LOG.info("Get all heroes");
        return ResponseContent
                .builder()
                .body(heroService.listAll(name).orElse(new ArrayList<>()))
                .build();

    }

    @GetMapping("/{id}")
    public ResponseContent getHeroeById(@PathVariable(value = "id") UUID id) throws Throwable {
        LOG.info("Get heroe by id: {}", id);
        return ResponseContent
                .builder()
                .body(heroService.getById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND)))
                .build();

    }

    @PostMapping
    public ResponseContent save(@RequestBody Hero hero){
        LOG.info("Save new hero: {}", hero);
        return ResponseContent
                .builder()
                .body(HeroAdapter.heroModelToHeroWithoutId(heroService.save(hero)))
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseContent update(@RequestBody Hero hero, @PathVariable(value = "id") UUID id){
        LOG.info("Update hero: {}", hero);
        return ResponseContent
                .builder()
                .body(HeroAdapter.heroModelToHeroWithoutId(heroService.update(hero)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseContent delete(@PathVariable(value = "id") UUID id){
        LOG.info("Remove hero with id: {}", id);
        try {
            heroService.remove(id);
            return ResponseContent
                    .builder()
                    .message("Success")
                    .build();
        }catch (Exception e){
            LOG.error("Fail when delete hero: ", id);
            return ResponseContent
                    .builder()
                    .message("Fail")
                    .build();
        }
    }
}
