package br.com.brainweb.interview.core.features.controllers;

import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsDifferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody HeroRequestDTO heroRequestDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/heroes/" + heroService.save(heroRequestDTO).toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{uuid}", method = PATCH, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> update(@PathVariable String uuid, @RequestBody HeroRequestDTO heroRequestDTO) {
        heroService.update(uuid, heroRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{uuid}")
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

    @GetMapping(value = {"/names/{name}", "/names"})
    public ResponseEntity<List<HeroResponseDTO>> findByName(@PathVariable Optional<String> name) {
        return new ResponseEntity<>(heroService.find(name), HttpStatus.OK);
    }

    @GetMapping(path = "/difference")
    public ResponseEntity<PowerStatsDifferenceDTO> compare(@RequestParam("first-hero-id") final String firstHeroUUID,
                                                           @RequestParam("second-hero-id") final String secondHeroUUID) {
        PowerStatsDifferenceDTO powerStatsDifferenceDTO = heroService.calculateDifference(firstHeroUUID, secondHeroUUID);

        return new ResponseEntity<>(powerStatsDifferenceDTO, HttpStatus.OK);
    }
}


