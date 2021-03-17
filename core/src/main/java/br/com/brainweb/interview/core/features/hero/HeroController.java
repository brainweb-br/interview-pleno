package br.com.brainweb.interview.core.features.hero;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HeroDto> save(@Valid @RequestBody HeroDto hero) {
		heroService.save(hero.toEntity());
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(hero);
	}
}
