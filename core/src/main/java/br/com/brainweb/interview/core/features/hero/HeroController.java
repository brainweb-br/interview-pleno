package br.com.brainweb.interview.core.features.hero;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brainweb.interview.TO.ComparePowersTO;
import br.com.brainweb.interview.model.Hero;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/hero")
public class HeroController {

	@Autowired
	private HeroService heroService;

	@SuppressWarnings("rawtypes")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveOrUpdate(@RequestBody Hero object) {
		return new ResponseEntity<Hero>(this.heroService.saveOrUpdate(object), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findById(@PathVariable("id") Integer id) throws NotFoundException {
		return new ResponseEntity<Optional<Hero>>(this.heroService.findById(id), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/findByFilter")
	public ResponseEntity findByFilter(@RequestBody String name) throws NotFoundException {
		return new ResponseEntity<Optional<Hero>>(this.heroService.findByName(name), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) throws NotFoundException {
		this.heroService.findById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/findByFilter")
	public ResponseEntity comparePowers(@RequestBody Integer idHeroOne, @RequestBody Integer idHeroTwo) throws NotFoundException {
		return new ResponseEntity<ComparePowersTO>(this.heroService.comparePowers(idHeroOne, idHeroTwo), HttpStatus.OK);
	}

}
