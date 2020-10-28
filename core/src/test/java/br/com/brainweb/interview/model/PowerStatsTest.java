package br.com.brainweb.interview.model;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PowerStatsTest {

	@Test
	public void testPower() throws Exception {
		PowerStats power = new PowerStats();

		power.setId(UUID.randomUUID());
		power.setStrength(1);
		power.setAgility(1);
		power.setDexterity(1);
		power.setIntelligence(1);
		power.prePersist();
		power.setCreatedAt(new Date());
		power.setUpdatedAt(new Date());
		power.toString();
	}

}
