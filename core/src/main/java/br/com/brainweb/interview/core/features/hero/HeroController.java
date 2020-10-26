package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.dtos.HeroDto;
import br.com.brainweb.interview.core.dtos.HeroDto.HeroOut;
import br.com.brainweb.interview.core.dtos.HeroDto.HeroStronger;
import br.com.brainweb.interview.model.Hero;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hero")
public class HeroController {

    @Autowired
    private HeroService service;

    @PostMapping
    public ResponseEntity<HeroOut> create(@RequestBody @Valid  final Hero hero) {
        return ResponseEntity.ok(service.create(hero));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HeroOut> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid final Hero input) {
        return ResponseEntity.ok(service.update(id, input));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HeroOut> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HeroOut>> findByName(@RequestBody final String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping(value = "/compare-stronger")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HeroStronger>> compareStronger(final @Valid HeroDto.HeroFilter name) {
        return ResponseEntity.ok(service.comparetorHero(name.getName()));
    }
}
