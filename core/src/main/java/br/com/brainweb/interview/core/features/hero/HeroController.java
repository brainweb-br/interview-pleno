package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.dto.ResponseContent;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("heros")
@AllArgsConstructor
public class HeroController {

    private HeroService heroService;

    @GetMapping
    public ResponseContent getHeroes(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseContent getHeroeById(@PathVariable(value = "id") Long id) throws Throwable {
        return ResponseContent
                .builder()
                .body(heroService.getById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND)))
                .build();

    }
}
