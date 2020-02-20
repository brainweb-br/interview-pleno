package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.dto.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {

    @Autowired
    Facade facade;

    @PostMapping
    public void create(@RequestBody HeroDTO dto) {
        facade.save(dto);
    }

    @GetMapping(path = "/{id}")
    public HeroDTO getById(@PathVariable String id) {
        return facade.findById(id);
    }

    @GetMapping(path = "/by-name/{heroName}")
    public List<HeroDTO> getByName(@PathVariable String heroName) {

        return facade.findByName(heroName);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        facade.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<HeroDTO>> getAll() {

        return new ResponseEntity<>(facade.getAll(), HttpStatus.OK);
    }


}
