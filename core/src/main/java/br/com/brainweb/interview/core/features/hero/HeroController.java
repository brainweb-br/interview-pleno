package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
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
        try{
            HeroDTO heroDTO = this.heroService.get(id);
            return heroDTO != null ?
                ResponseEntity.ok(heroDTO) :
                ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity get(@PathVariable("name") String name){
        try{
            HeroDTO heroDTO = this.heroService.get(name);
            return heroDTO != null ?
                    ResponseEntity.ok(heroDTO) :
                    ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody HeroDTO heroDTO){
        try{
            HeroDTO heroDTOResponse = this.heroService.update(id, heroDTO);
            return heroDTOResponse != null ?
                    ResponseEntity.ok(heroDTOResponse) :
                    ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") UUID id){
        try{
            boolean deleted = this.heroService.delete(id);
            return deleted ?
                    ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    private URI getURI(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
