package br.com.brainweb.interview.core.features.hero.it;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.Utils;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class CreateHeroIT {

    private static final String BASE_PATH = "/heroes";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Flyway flyway;

    @After
    public void clearData(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldCreateHeroWhenSendValidHero() throws Exception {
        Hero hero = Utils.getValidHero();
        hero.setId(null);
        hero.getPowerStats().setId(null);
        hero.setName(hero.getName() + Timestamp.from(Instant.now()));
        MvcResult mvcResult = mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hero)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Hero createdHero = objectMapper.readValue(response, Hero.class);

        assertNotNull(createdHero.getId());
    }

    @Test
    public void shouldReturnBadRequestWhenNotSendValidHero() throws Exception {
        mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {"/sql/insert_hero.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldThrowExceptionWhenCreateHeroWithSameName() throws Exception {
        Hero hero = Utils.getValidHero();
        hero.setId(null);
        hero.getPowerStats().setId(null);
        hero.setName("teste");

        mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hero)));

        mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hero))
        ).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.DUPLICATED_HERO_NAME_MESSAGE));
    }


}
