package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody HeroDTO heroDTO) {
        try {
            HeroDTO heroDTOResponse = this.heroService.create(heroDTO);
            return heroDTOResponse != null ? ResponseEntity.created(getURI(heroDTOResponse.getId())).build() : ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") UUID id){
        HeroDTO heroDTO = this.heroService.getHero(id);
        return heroDTO != null ?
            ResponseEntity.ok(heroDTO) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity get(@PathVariable("name") String name){
        HeroDTO heroDTO = this.heroService.getHero(name);
        return heroDTO != null ?
                ResponseEntity.ok(heroDTO) :
                ResponseEntity.ok().build();
    }

    private URI getURI(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
