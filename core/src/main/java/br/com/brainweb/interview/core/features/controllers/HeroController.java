package br.com.brainweb.interview.core.features.controllers;

import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsDifferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody HeroRequestDTO heroRequestDTO) {
        heroService.save(heroRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/{uuid}")
    public ResponseEntity<Void> update(@PathVariable String uuid, @RequestBody HeroRequestDTO heroRequestDTO) {
        heroService.update(uuid, heroRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {

        heroService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(path = "/{uuid:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<HeroResponseDTO> find(@PathVariable String uuid) {

        HeroResponseDTO heroResponseDTO = heroService.find(uuid);
        if (heroResponseDTO != null) {
            return new ResponseEntity<>(heroResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/names/{name}")
    public ResponseEntity<List<HeroResponseDTO>> findByName(@PathVariable Optional<String> name) {

        heroService.find(name);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/difference/firstHeroUUID/{firstHeroUUID}/secondHeroUUID/{secondHeroUUID}")
    public ResponseEntity<PowerStatsDifferenceDTO> compare(@RequestParam final String firstHeroUUID,
                                                           @RequestParam final String secondHeroUUID) {
        PowerStatsDifferenceDTO powerStatsDifferenceDTO = heroService.calculateDifference(firstHeroUUID, secondHeroUUID);

        return new ResponseEntity<>(powerStatsDifferenceDTO, HttpStatus.OK);
    }
}


