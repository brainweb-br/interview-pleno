package br.com.brainweb.interview.core.features.hero.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import br.com.brainweb.interview.model.bean.CompareHero;
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
	public ResponseEntity<Object> findById(@PathVariable UUID id) {
		try {
			Optional<Hero> hero = heroService.findById(id);
			if (hero.isPresent()) {
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
			List<Hero> hero = heroService.findByName(name);
			return new ResponseEntity<Object>(hero, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao procurar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "save")
	@ApiOperation(value = "Salvar heroi")
	public ResponseEntity<Object> saveHero(@Valid @RequestBody Hero hero) {
		try {
			List<Hero> listHero = heroService.findByName(hero.getName());
			if (!listHero.isEmpty()) {
				return new ResponseEntity<Object>("Nome de heroi duplicado", HttpStatus.BAD_REQUEST);
			}
			Hero h = heroService.saveHero(hero);
			return new ResponseEntity<Object>(h, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao salvar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "edit")
	@ApiOperation(value = "Editar heroi")
	public ResponseEntity<Object> editHero(@Valid @RequestBody Hero hero) {
		try {
			Optional<Hero> existHero = heroService.findById(hero.getId());
			if (existHero.isPresent()) {
				Hero h = heroService.editHero(hero);
				return new ResponseEntity<Object>(h, HttpStatus.OK);
			}
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Ocorreu um erro ao editar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "delete")
	@ApiOperation(value = "Deletar heroi")
	public ResponseEntity<Object> deleteHero(@RequestBody Hero hero) {
		try {
			String msg = heroService.deleteHero(hero);
			return new ResponseEntity<Object>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao deletar o heroi", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "compare")
	@ApiOperation(value = "Compara dois heroi por ID")
	public ResponseEntity<Object> compareHero(@RequestBody CompareHero compareHero) {
		try {
			Optional<Hero> hero = heroService.findById(compareHero.getHero1());
			if (hero.isPresent()) {
				Optional<Hero> hero2 = heroService.findById(compareHero.getHero2());
				if (hero2.isPresent()) {
					heroService.compareHero(compareHero, hero.get(), hero2.get());
					return new ResponseEntity<Object>(compareHero, HttpStatus.OK);
				}
				return new ResponseEntity<Object>("ID2 nao encontrado", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Object>("ID nao encontrado", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>("Ocorreu um erro ao comparar os herois", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
