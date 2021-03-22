package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.brainweb.interview.core.features.hero.dto.HeroDto;
import br.com.brainweb.interview.core.features.hero.mapper.HeroMapper;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;

@Controller
@RequestMapping("/api/v1/heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HeroDto> save(@Valid @RequestBody HeroDto hero) {
		Hero createdHero = heroService.save(HeroMapper.toNewEntity(hero));
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(HeroMapper.toDto(createdHero));
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HeroDto> update(@Valid @RequestBody HeroDto hero, @PathVariable("id") String id) {
		Optional<Hero> optionalHero = heroService.update(HeroMapper.toUpdateEntity(hero), id);
		
		if (optionalHero.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(HeroMapper.emptyBody());
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(HeroMapper.toDto(optionalHero.get()));
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<HeroDto> find(@PathVariable("id") String id) {
		Optional<Hero> optionalHero = heroService.findById(id);
		
		if (optionalHero.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(HeroMapper.emptyBody());
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(HeroMapper.toDto(optionalHero.get()));
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<HeroDto>> findByName(@RequestParam(name = "name") String name) {
		List<Hero> heroes = heroService.findByName(name);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(HeroMapper.toDto(heroes));
	}
	
	@DeleteMapping(path = "/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable String id) {
		heroService.delete(id);
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}
