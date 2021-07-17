package br.com.brainweb.interview.core.features.hero;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @GetMapping
    public String hello(){
        System.out.println("Hello, heroes.");
        return "Hello, heroes.";
    }

}
