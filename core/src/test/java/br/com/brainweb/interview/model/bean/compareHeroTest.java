package br.com.brainweb.interview.model.bean;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class compareHeroTest {

	@Test
	public void testCompareHero() throws Exception {

		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(UUID.randomUUID());
		compareHero.setHero2(UUID.randomUUID());
		compareHero.setStrength(1);
		compareHero.setAgility(1);
		compareHero.setDexterity(1);
		compareHero.setIntelligence(1);
		compareHero.toString();
	}
}
