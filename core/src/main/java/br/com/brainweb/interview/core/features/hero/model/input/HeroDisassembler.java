package br.com.brainweb.interview.core.features.hero.model.input;

import br.com.brainweb.interview.model.Hero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeroDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Hero toDomainObject(HeroInput heroInput) {
		return modelMapper.map(heroInput, Hero.class);
	}

	public void copyToDomainObject(HeroInput heroInput, Hero hero) {
		modelMapper.map(heroInput, hero);
	}
}
