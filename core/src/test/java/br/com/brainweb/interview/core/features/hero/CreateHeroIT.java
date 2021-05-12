package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.RaceType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ActiveProfiles("it")
public class CreateHeroIT {

    private final String BASE_URI = "/heros";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Flyway flyway;

    @AfterAll
    public void init(){
        flyway.clean();
        flyway.migrate();
    }


    @Test
    public void shouldReturnBadRequestWhenNotSendValidHero() throws Exception {
        MvcResult mvcResult = mvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldCreateHeroWhenSendValidHero() throws Exception {
        Hero hero = getValidHero();
        hero.setId(null);
        hero.getPowerStats().setId(null);
        hero.setName(hero.getName() + Timestamp.from(Instant.now()));
        mvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hero)))
                .andExpect(status().isCreated());

        Hero createdHero = heroRepository.findByName(hero.getName());
        assertNotNull(createdHero.getId());
    }

    @Test
    public void shouldNotCreateHeroWithSameName() throws Exception {
        Hero hero = getValidHero();
        hero.setId(null);
        hero.getPowerStats().setId(null);

        mvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hero)));
        MvcResult mvcResult = mvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hero)))
                .andExpect(status().isBadRequest()).andReturn();

        DuplicatedHeroNameException exception = (DuplicatedHeroNameException) mvcResult.getResolvedException();
        assertNotNull(exception);

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    private Hero getValidHero() {
        PowerStats power = new PowerStats(UUID.randomUUID(), 1, 1, 1, 1,
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        Hero hero = new Hero(UUID.randomUUID(), "teste",
                RaceType.HUMAN, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), power);
        return hero;
    }

}
