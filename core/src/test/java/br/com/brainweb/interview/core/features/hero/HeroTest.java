package br.com.brainweb.interview.core.features.hero;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class HeroTest {
	
	private Validator validator;
	
	@BeforeEach
    public void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.brainweb.interview.core.features.hero");
        
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
	
	@Test
	public void deveValidarHero() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 0);
	}
	
	@Test
	public void deveValidarHeroQuandoNomeEmpty() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.setName("");
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Atributo name nao pode ser vazio", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoNomeNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.setName(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo name", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoRaceEmpty() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.setRace("");
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo race", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoRaceNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.setRace(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo race", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoAgilityNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.getPowerStats().setAgility(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo agility", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoDexterityNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.getPowerStats().setDexterity(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo dexterity", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoIntelligenceNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.getPowerStats().setIntelligence(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo intelligence", validacoes.stream().findFirst().get().getMessage());
	}
	
	@Test
	public void deveValidarHeroQuandoStrengthNulo() {
		HeroDto hero = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		hero.getPowerStats().setStrength(null);
		
		Set<ConstraintViolation<HeroDto>> validacoes = validator.validate(hero);

	    Assert.assertEquals(validacoes.size(), 1);
	    Assert.assertEquals("Informe o atributo strength", validacoes.stream().findFirst().get().getMessage());
	}
}
