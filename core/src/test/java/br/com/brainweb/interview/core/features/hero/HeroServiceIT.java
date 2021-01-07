package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.Application;
import br.com.brainweb.interview.model.ComparativeResponse;
import br.com.brainweb.interview.model.DtoHeroResponse;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@ActiveProfiles("it")
@TestPropertySource(locations = "classpath:application.properties")
public class HeroServiceIT {

    final String BASE_PATH = "http://localhost:8080";
    private RestTemplate restTemplate;

    @Before
    public void  setUp() {
        restTemplate = new RestTemplate();
    }

    public Hero createHero() {
        PowerStats powerStats = new PowerStats();
        powerStats.setAgility(10);
        powerStats.setStrength(11);
        powerStats.setDexterity(12);
        powerStats.setIntelligence(13);

        Hero hero = new Hero();
        hero.setBreed("HUMAN");
        hero.setPowerStats(powerStats);
        hero.setIsEnabled(true);

        return hero;
    }

    @Test
    public void createHeroIT() {
        Hero hero = createHero();

        hero.setName("flash");
        DtoHeroResponse response = restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);
        assertEquals(response.getName(), hero.getName());

        hero.setName("thor");
        hero.setBreed("DIVINE");
        response = restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);
        assertEquals(response.getName(), hero.getName());
        assertEquals(response.getBreed(), hero.getBreed());

    }

    @Test
    public void findHeroByIdIT() {
        Hero hero = createHero();
        hero.setName("batman");
        DtoHeroResponse responseSaveHero = restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);

        DtoHeroResponse response = restTemplate.getForObject(BASE_PATH + "/heroes/" + responseSaveHero.getId().toString(), DtoHeroResponse.class);

        assertNotNull(response);

        assertEquals(response.getName(), hero.getName());
        assertEquals(response.getBreed(), hero.getBreed());
    }

    @Test
    public void filterHeroByName() {
        Hero hero = createHero();
        hero.setName("superman");
        restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);

        DtoHeroResponse response = restTemplate.getForObject(BASE_PATH + "/heroes?name=superman", DtoHeroResponse.class);
        assertNotNull(response);

        assertEquals(response.getName(), hero.getName());
        assertEquals(response.getBreed(), hero.getBreed());
    }

    @Test
    public void updateHeroIT() {
        Hero hero = createHero();
        hero.setName("wonder woman");
        DtoHeroResponse heroCreated = restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        hero.setName("iron man");

        HttpEntity<Hero> entity = new HttpEntity<Hero>(hero, headers);

        ResponseEntity<DtoHeroResponse> response = restTemplate.exchange(BASE_PATH + "/heroes/{id}", HttpMethod.PUT, entity, DtoHeroResponse.class, heroCreated.getId());


        assertNotNull(response);

        assertEquals(response.getBody().getName(), hero.getName());
        assertEquals(response.getBody().getBreed(), hero.getBreed());
    }

    @Test
    public void deleteHeroIT() {
        Hero hero = createHero();
        hero.setName("hulk");
        DtoHeroResponse heroCreated = restTemplate.postForObject(BASE_PATH + "/heroes", hero, DtoHeroResponse.class);

        ResponseEntity<Object> response = restTemplate.exchange(BASE_PATH + "/heroes/{id}", HttpMethod.DELETE, new HttpEntity<>(heroCreated), Object.class, heroCreated.getId().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void compareHeroesIT() {
        Hero heroOne = createHero();
        Hero heroTwo = createHero();

        PowerStats heroOnePowerStats = new PowerStats();
        heroOnePowerStats.setAgility(10);
        heroOnePowerStats.setStrength(11);
        heroOnePowerStats.setDexterity(12);
        heroOnePowerStats.setIntelligence(13);

        heroOne.setName("lanterna verde");
        heroOne.setPowerStats(heroOnePowerStats);

        restTemplate.postForObject(BASE_PATH + "/heroes", heroOne, DtoHeroResponse.class);

        PowerStats heroTwoPowerStats = new PowerStats();
        heroTwoPowerStats.setAgility(5);
        heroTwoPowerStats.setStrength(4);
        heroTwoPowerStats.setDexterity(6);
        heroTwoPowerStats.setIntelligence(7);

        heroTwo.setPowerStats(heroTwoPowerStats);
        heroTwo.setName("homem aranha");
        restTemplate.postForObject(BASE_PATH + "/heroes", heroTwo, DtoHeroResponse.class);

        ComparativeResponse response = restTemplate.getForObject(BASE_PATH + "/heroes/compare?names=lanterna verde, homem aranha", ComparativeResponse.class);

        assertThat(response.getAgilityHeroOne()).isEqualTo(5);
        assertThat(response.getAgilityHeroTwo()).isEqualTo(-5);

    }

}
