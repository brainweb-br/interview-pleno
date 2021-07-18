package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroCommand;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroQuery;
import br.com.brainweb.interview.core.features.hero.adapter.dto.PowerStatsCommand;
import br.com.brainweb.interview.model.Race;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroControllerIT {

    @LocalServerPort
    private int port;

    private final String base_url = "http://localhost:";

    @Autowired
    private HeroRepository heroRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setup() {
        heroRepository.deleteAll();
    }

    @AfterEach
    void after() {
        heroRepository.deleteAll();
    }

    @Test
    public void shouldCreateAHeroAndReturn200WithHeroUUID() {
        var url = base_url + port + "/heroes";
        var command = heroCommand();
        var result = restTemplate.postForEntity(url, command, String.class);
        UUID.fromString(Objects.requireNonNull(result.getBody()));
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    public void shouldCreateAHeroAndGetHeroById() {
        var url = base_url + port + "/heroes";
        var command = heroCommand();
        var result = restTemplate.postForEntity(url, command, String.class);
        var heroId = result.getBody();
        var getResult = restTemplate.getForEntity(url + "/"+ heroId, HeroQuery.class);
        assertEquals(200, getResult.getStatusCode().value());
        assertEquals(heroId, getResult.getBody().getId().toString());
    }

    @Test
    public void shouldUpdateAHeroAndGetHeroById() {
        var url = base_url + port + "/heroes";
        var command = heroCommand();
        var result = restTemplate.postForEntity(url, command, String.class);
        var heroId = result.getBody();
        command.setName("Clark Kent");
        command.setRace(Race.HUMAN);
        restTemplate.put(url + "/"+ heroId, command);
        var getResult = restTemplate.getForEntity(url + "/"+ heroId, HeroQuery.class);
        assertEquals(200, getResult.getStatusCode().value());
        assertEquals("Clark Kent", getResult.getBody().getName());
        assertEquals(Race.HUMAN, getResult.getBody().getRace());
    }

    private HeroCommand heroCommand() {
        var statsCommand = new PowerStatsCommand(10, 10, 9, 10);
        var heroCommand = new HeroCommand("Superman", Race.DIVINE, statsCommand);
        heroCommand.setEnabled(true);
        return heroCommand;
    }

}
