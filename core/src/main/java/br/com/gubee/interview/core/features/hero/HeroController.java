package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.hero.impl.HeroServiceImpl;
import br.com.gubee.interview.dto.ComparisonRequest;
import br.com.gubee.interview.dto.HeroComparison;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api")
public class HeroController {

    @Autowired
    HeroService heroService;

    @Autowired
    HeroServiceImpl heroServiceImpl;

    @PostMapping("/createHero")
    public ResponseEntity createHero(@RequestBody HeroResponse hero){
        return heroService.save(hero);
    }

    @GetMapping("/getHero/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<HeroResponse> getHero(@PathVariable("id") UUID id){
        return heroService.getById(id);
    }

    @GetMapping("/getHeroByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<HeroResponse>  getHeroByName(@PathVariable("name") String name){
        return heroService.getByName(name);
    }

    @PatchMapping("/editHero/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity editHero(@RequestBody HeroResponse hero, @PathVariable("id") UUID id){
        return heroService.editHero(hero, id);
    }

    @DeleteMapping("/deleteHero/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity deleteHero(@PathVariable("id") UUID id){
        return heroService.deleteById(id);
    }

    @PostMapping("/compareHeroes")
    public ResponseEntity<HeroComparison> compareHeroes(@RequestBody ComparisonRequest request) {
        return heroService.compareHeroes(request);
    }
}
