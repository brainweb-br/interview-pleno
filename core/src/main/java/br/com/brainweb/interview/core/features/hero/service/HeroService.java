package br.com.brainweb.interview.core.features.hero.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.core.features.hero.exception.BusinessException;
import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.util.StringHelper;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
	
	@Transactional
	public Hero save(Hero hero) {
		log.info("Salvando hero.");
		
		hero.setId(StringHelper.createUUID());
		hero.atualizarPowerStatsId(StringHelper.createUUID());
		hero.atualizarDataCriacaoAtualizacao(LocalDateTime.now());
		
		PowerStats powerStats = hero.getPowerStats();
		
		powerStatsRepository.save(powerStats);
		heroRepository.save(hero);
		
		log.info("Hero salvo com sucesso.");
		return hero;
	}
	
	@Transactional
	public Optional<Hero> update(Hero heroRequest, String id) {
		UUID idAtual = StringHelper.createUUID(id);
		
		if (!idAtual.equals(heroRequest.getId())) {
			log.info("Recursos com identificadores diferentes.");
			
			throw BusinessException.create(HttpStatus.UNPROCESSABLE_ENTITY, "Os identicadores do recurso sao diferentes.");
		}
		
		Optional<Hero> optionalHero = findById(idAtual.toString());
		
		if (optionalHero.isEmpty()) {
			log.info("Hero com o id: %s nao encontrado", id);
			return optionalHero;
		}
		
		Hero heroAtual = optionalHero.get();
		heroAtual.setEnable(heroRequest.isEnable());
		heroAtual.setName(heroRequest.getName());
		heroAtual.setRace(heroRequest.getRace());
		heroAtual.setUpdated_at(LocalDateTime.now());
		
		PowerStats powerAtual = heroAtual.getPowerStats();
		PowerStats powerRequest = heroRequest.getPowerStats();
		powerAtual.setAgility(powerRequest.getAgility());
		powerAtual.setDexterity(powerRequest.getDexterity());
		powerAtual.setIntelligence(powerRequest.getIntelligence());
		powerAtual.setStrength(powerRequest.getStrength());
		powerAtual.setUpdated_at(LocalDateTime.now());
		
		powerStatsRepository.update(powerAtual);
		heroRepository.update(heroAtual);
		
		log.info("Hero atualizado com sucesso.");
		return Optional.of(heroAtual);
	}
	
	public Optional<Hero> findById(String id) {
		log.info("Consultando hero com id: %s", id);
		
		return heroRepository.findById(StringHelper.createUUID(id));
	}
	
	public Optional<Hero> findByName(String name) {
		log.info("Consultando hero com o nome: %s", name);
		return heroRepository.findByName(name);
	}
	
	@Transactional
	public void delete(String id) {
		log.info("Deletando hero com id: %s", id);
		
		UUID heroId = StringHelper.createUUID(id);
		
		Optional<Hero> optionalHero = heroRepository.findById(heroId);
		
		if (optionalHero.isEmpty()) {
			log.info("Hero nao encontrado com id: [%s", id);
			
			throw BusinessException.create(HttpStatus.NOT_FOUND, "Hero nao encontrado");
		}
		
		Hero hero = optionalHero.get();
		UUID powerStatsId = hero.getPowerStatsId();
		
		heroRepository.delete(heroId);
		powerStatsRepository.delete(powerStatsId);
		
		log.info("Hero deletado com sucesso.");
	}
}
