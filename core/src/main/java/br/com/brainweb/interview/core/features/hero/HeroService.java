package br.com.brainweb.interview.core.features.hero;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.TO.ComparePowersTO;
import br.com.brainweb.interview.TO.HeroPowerTO;
import br.com.brainweb.interview.model.Hero;
import javassist.NotFoundException;

@Service
public class HeroService {

	@Autowired
	private HeroRepository heroRepository;

	public Hero saveOrUpdate(Hero obj) {
		return heroRepository.save(obj);
	}

	public Optional<Hero> findById(Integer id) throws NotFoundException {
		Optional<Hero> hero = this.heroRepository.findById(id);
		if(hero.isPresent()) {
			return hero;
		}else {
			throw new NotFoundException("Hero not Found");
		}
	}

	public Optional<Hero> findByName(String name) throws NotFoundException {
		Optional<Hero> hero = heroRepository.findByName(name);
		if(hero.isPresent()) {
			return heroRepository.findByName(name);
		}else {
			throw new NotFoundException("Hero not Found");
		}
	}

	public void delete(Integer id) throws NotFoundException {
		Optional<Hero> hero = this.findById(id);
		if (hero.isPresent()) {
			heroRepository.delete(hero.get());
		}
	}

	public ComparePowersTO comparePowers(Integer idHeroOne, Integer idHeroTwo) throws NotFoundException {
		Optional<Hero> heroOne = this.findById(idHeroOne);
		Optional<Hero> heroTwo = this.findById(idHeroTwo);

		ComparePowersTO cp = new ComparePowersTO();
		if (heroOne.isPresent() && heroTwo.isPresent()) {
			HeroPowerTO hpOne = new HeroPowerTO();
			HeroPowerTO hpTwo = new HeroPowerTO();

			if (heroOne.get().getPowerStats().getStrength() > heroTwo.get().getPowerStats().getStrength()) {
				hpOne.setStrength(true);
			} else {
				hpTwo.setStrength(true);
			}

			if (heroOne.get().getPowerStats().getAgility() > heroTwo.get().getPowerStats().getAgility()) {
				hpOne.setAgility(true);
			} else {
				hpTwo.setAgility(true);
			}

			if (heroOne.get().getPowerStats().getDexterity() > heroTwo.get().getPowerStats().getDexterity()) {
				hpOne.setDexterity(true);
			} else {
				hpTwo.setDexterity(true);
			}

			if (heroOne.get().getPowerStats().getIntelligence() > heroTwo.get().getPowerStats().getIntelligence()) {
				hpOne.setIntelligence(true);
			} else {
				hpTwo.setIntelligence(true);
			}

			cp.setHeroOne(hpOne);
			cp.setHeroTwo(hpTwo);
		}
		return cp;
	}

}
