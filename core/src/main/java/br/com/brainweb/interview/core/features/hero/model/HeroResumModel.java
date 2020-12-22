package br.com.brainweb.interview.core.features.hero.model;

import java.util.UUID;

import br.com.brainweb.interview.model.Hero;
import lombok.Data;

@Data
public class HeroResumModel {

	private UUID id;
	private String name;
	
	public HeroResumModel(Hero hero) {
		this.id = hero.getId();
		this.name = hero.getName();
	}
}
