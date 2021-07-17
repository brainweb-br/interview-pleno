package br.com.brainweb.interview.core.features.hero.adapter;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/heroes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroController {

    @GetMapping
    public String hello() {
        System.out.println("Hello, heroes.");
        return "Hello, heroes.";
    }

}
