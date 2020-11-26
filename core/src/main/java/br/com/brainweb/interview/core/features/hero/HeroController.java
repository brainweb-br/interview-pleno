package br.com.brainweb.interview.core.features.hero;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brainweb.interview.model.Hero;

@RestController
@RequestMapping(value = "/heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@PostMapping
	public ResponseEntity<Void> create(
			@Valid @RequestBody Hero hero) {
		final UUID id = heroService.create(hero).getId();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<Hero> findByID(
			@RequestParam(value = "id", defaultValue = "", required = true) UUID id) {
		try {
			Hero hero = heroService.findById(id);
			return ResponseEntity.ok().body(hero);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping(value = "/filter")
	public ResponseEntity<List<Hero>> findByName(
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		List<Hero> heroes = heroService.findByName(name);
		
		if (heroes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return ResponseEntity.ok().body(heroes);
		}
	}
	
	@PutMapping
	public ResponseEntity<Hero> update(
			@RequestParam(value = "id", defaultValue = "", required = true) UUID id) {
		try {
			Hero hero = heroService.findById(id);
			return ResponseEntity.ok().body(heroService.update(hero));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(
			@RequestParam(value = "id", defaultValue = "", required = true) UUID id) {
		Hero hero = heroService.findById(id);
		if (hero == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			heroService.delete(hero);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}		
	}
}
