package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.CompareHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.UUID;

import static br.com.brainweb.interview.core.constants.ApiConstants.*;

@RestController
@RequestMapping("/")
public class HeroController {

    @Autowired
    HeroService heroService;

    @PostMapping(value = "save")
    @ApiOperation(value = "Salvar heroi")
    public ResponseEntity<Object> createHero(@Valid @RequestBody Hero hero) {
        if (!heroService.findByName(hero.getName()).isEmpty()) {
            return new ResponseEntity<>(HERO_NAME_EXISTS, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(heroService.saveHero(hero),HttpStatus.OK);
    }

    @RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Recupera heroi por ID")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        if (heroService.findById(id).isPresent()) {
            return new ResponseEntity<>(heroService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HERO_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "Recupera heroi por nome")
    public ResponseEntity<Object> findByName(@PathVariable("name") String name) {

        return new ResponseEntity<>(heroService.findByName(name), HttpStatus.OK);

    }

    @PostMapping(value = "edit")
    @ApiOperation(value = "Editar heroi")
    public ResponseEntity<Object> editHero(@Valid @RequestBody Hero hero) {

        if (heroService.findById(hero.getId()).isPresent()) {
            heroService.editHero(hero);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HERO_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "compareHeroes/{idA}/{idB}")
    public ResponseEntity<CompareHero> compareHeroes(@PathVariable(value = "idA") UUID idA,
                                                     @PathVariable(value = "idB") UUID idB) {
            CompareHero compareHero = heroService.compareHeroes(idA, idB);
            if (compareHero != null) {
                return new ResponseEntity<>(compareHero, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "Deletar heroi")
    public ResponseEntity<Object> deleteHero(@RequestBody UUID hero) {
        if (heroService.findById(hero).isPresent()) {
            heroService.deleteHero(heroService.findById(hero).get());
            return new ResponseEntity<>(HERO_DELETE, HttpStatus.OK);
        }
        return new ResponseEntity<>(HERO_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
