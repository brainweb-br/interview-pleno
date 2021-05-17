package br.com.brainweb.interview.core.features.hero.it;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.Utils;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.RaceType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("it")
@Sql(scripts = {"/sql/insert_hero.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UpdateHeroIT {

    private static final String BASE_PATH = "/heroes/{id}";

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
    public void shouldUpdateHero() throws Exception {
        Hero oldHero = heroRepository.findAll().get(0);
        Hero newValues = Hero.builder().name("Nome editado").
                race(RaceType.CYBORG)
                .powerStats(PowerStats.builder().strength(45).build())
                .build();

        MvcResult mvcResult = mvc.perform(put(BASE_PATH, oldHero.getId().toString())
                .content(objectMapper.writeValueAsString(newValues))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Hero updatedHero = objectMapper.readValue(response, Hero.class);

        assertEquals(newValues.getName(), updatedHero.getName());
        assertEquals(newValues.getRace(), updatedHero.getRace());
        assertEquals(newValues.getPowerStats().getStrength(), updatedHero.getPowerStats().getStrength());

        // should not update null values
        assertNotEquals(newValues.getEnabled(), updatedHero.getEnabled());
        assertNotEquals(newValues.getPowerStats().getAgility(), updatedHero.getPowerStats().getAgility());
    }

    @Test
    public void shouldThrowExceptionWhenHeroNotFound() throws Exception {
        Hero hero = Utils.getValidHero();

        Hero newValues = Hero.builder().name("Nome editado").
                race(RaceType.CYBORG)
                .powerStats(PowerStats.builder().strength(45).build())
                .build();


        mvc.perform(put(BASE_PATH, hero.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newValues)))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status_code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.HERO_NOT_FOUND_MESSAGE));
    }


}
