package br.com.brainweb.interview.core.features.hero.controller;

import br.com.brainweb.interview.core.features.hero.dto.HeroDTO;
import br.com.brainweb.interview.core.features.hero.mapper.HeroDTOMapper;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/heros", produces="application/json")
public class HeroController {

    private final HeroService heroService;
    private final HeroDTOMapper mapper;

    @PostMapping
    public ResponseEntity<HeroDTO> saveHero(@Valid @RequestBody final HeroDTO hero) {
        return ResponseEntity.ok().body(mapper.toDto(heroService.saveHero(mapper.toModel(hero))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable(value = "id") final UUID heroId) {
        return ResponseEntity.ok().body(mapper.toDto(heroService.findById(heroId)));
    }

    @GetMapping
    public ResponseEntity<HeroDTO> getHeroByParam(@RequestParam(value = "name") final String heroName) {
        return ResponseEntity.ok().body(
                heroService.findHeroByName(heroName)
                        .map(mapper::toDto)
                        .orElse(null)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroDTO> updateHero(@PathVariable(value = "id") final UUID heroId,
                                              @Valid @RequestBody final HeroDTO heroDto) {
        if (heroDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hero id Null");
        }
        if (!Objects.equals(heroId, heroDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hero id Invalid");
        }
        return ResponseEntity.ok().body(mapper.toDto(heroService.updateHero(mapper.toModel(heroDto))));
    }
}
