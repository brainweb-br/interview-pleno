package br.com.brainweb.interview.core.features.hero;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HeroControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private HeroRepository heroRepository;
	
	@MockBean
	private HeroService heroService;
	
	@MockBean
	private PowerStatsRepository powerStatsRepository;
	
	@MockBean
	private HeroController heroController;
	
	private Hero hero;
	
	@BeforeEach
	public void setup() {
		hero = criacaoHero();
		BDDMockito.when(heroRepository.getOne(hero.getId())).thenReturn(hero);
	}

	@Test
	public void saveHero() {
		BDDMockito.when(powerStatsRepository.save(hero.getPowerStats())).thenReturn(hero.getPowerStats()); 
		BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
		
		ResponseEntity<Hero> response = restTemplate.postForEntity("/saveHero", hero, Hero.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
    public void getHeroByIdStatusCode200() throws Exception {
		ResponseEntity<Hero> response = restTemplate.getForEntity("/getHeroById/{id}", 
				Hero.class, hero.getId().toString());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void getHeroesByNameStatusCode200() {
    	ResponseEntity<String> response = restTemplate.getForEntity("/getHeroesByName/{name}", 
    			String.class, "Monster");
    	Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }	
	
	@Test
    public void updateHeroStatusCode200() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange("/updateHero/{id}", HttpMethod.PUT, new HttpEntity<Hero>(hero),
				String.class, hero.getId().toString());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void deleteHeroStatusCode200() throws Exception {
		BDDMockito.doNothing().when(heroRepository).delete(hero);
		ResponseEntity<String> response = restTemplate.exchange("/deleteHero/{id}", HttpMethod.DELETE, HttpEntity.EMPTY, 
				String.class, hero.getId().toString());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void compareHeroes() throws Exception {
		
		Hero heroB = criacaoHeroB();
		BDDMockito.when(heroRepository.getOne(heroB.getId())).thenReturn(heroB);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/compareHeroes/{idA}/{idB}", 
				String.class, hero.getId().toString(), String.class, heroB.getId().toString());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	private Hero criacaoHero() {
		
		PowerStats powerStats = new PowerStats();
		UUID idUUID = UUID.fromString("c323e5dd-35ea-4780-97e6-2c5f832ae163");
		powerStats.setId(idUUID);
		powerStats.setDexterity((short) 10);
		powerStats.setAgility((short) 8);
		powerStats.setCreatedAt(LocalDateTime.now());
		powerStats.setIntelligence((short) 5);
		powerStats.setStrength((short) 3);
		powerStats.setUpdatedAt(LocalDateTime.now());
		
		Hero hero = new Hero();
		UUID idUUIDHero = UUID.fromString("416fd8f3-7915-4be5-91c4-dece191c464c");
		hero.setId(idUUIDHero);
		hero.setName("Flash");
		hero.setRace(Race.ALIEN);
		hero.setEnabled(true);
		hero.setCreatedAt(LocalDateTime.now());
		hero.setUpdatedAt(LocalDateTime.now());
		
		hero.setPowerStats(powerStats);
		
		return hero;
	}
	
	private Hero criacaoHeroB() {
		
		PowerStats powerStats = new PowerStats();
		powerStats.setId(UUID.randomUUID());
		powerStats.setDexterity((short) 3);
		powerStats.setAgility((short) 5);
		powerStats.setCreatedAt(LocalDateTime.now());
		powerStats.setIntelligence((short) 1);
		powerStats.setStrength((short) 9);
		powerStats.setUpdatedAt(LocalDateTime.now());
		
		Hero hero = new Hero();
		hero.setId(UUID.randomUUID());
		hero.setName("Mutant");
		hero.setRace(Race.CYBORG);
		hero.setEnabled(true);
		hero.setCreatedAt(LocalDateTime.now());
		hero.setUpdatedAt(LocalDateTime.now());
		
		hero.setPowerStats(powerStats);
		
		return hero;
	}
	
}
