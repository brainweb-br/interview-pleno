package br.com.brainweb.interview.core.features.hero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/hero/")
@Api(value = "API para realizar a manutenção dos herois")
public class HeroController {

	@Autowired
	private HeroService heroService;

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Recupera heroi por ID")
	public ResponseEntity<Object> findById(@PathVariable("id") int id) {
		try {
			Hero hero = heroService.findById(id);
			if (hero != null) {
				return new ResponseEntity<Object>(hero, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao procurar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "Recupera heroi por nome")
	public ResponseEntity<Object> findByName(@PathVariable("name") String name) {
		try {
			Hero hero = heroService.findByName(name);
			return new ResponseEntity<Object>(hero, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao procurar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "save")
	@ApiOperation(value = "Salvar heroi")
	public ResponseEntity<Object> saveHero(@Valid @RequestBody Hero hero) {
		try {
			Hero h = heroService.saveHero(hero);
			return new ResponseEntity<Object>(h, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao salvar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
