package br.com.brainweb.interview.model;

import br.com.brainweb.interview.model.Hero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CompareHero {

	private String ids;

	private String name;

	private int strength;

	private int agility;

	private int dexterity;

	private int intelligence;

	private Boolean samePower;
	
}
