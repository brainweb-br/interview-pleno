package br.com.brainweb.interview.model.bean;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompareHero {

	private UUID hero1;
	
	private UUID hero2;

	private int strength;

	private int agility;

	private int dexterity;

	private int intelligence;
}
