package br.com.brainweb.interview.core.features.hero.it;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.Utils;
import br.com.brainweb.interview.core.features.hero.dto.CompareHeroResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class CompareHeroIT {
    private static final String BASE_PATH = "/heroes/compare/{idHeroOne}/to/{idHeroTwo}";

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
    @Sql(scripts = {"/sql/insert_hero.sql", "/sql/insert_another_hero.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldCompareTwoExistingHeroes() throws Exception {
        List<Hero> heroes = heroRepository.findAll();
        Hero heroOne = heroes.get(0);
        Hero heroTwo = heroes.get(1);

        MvcResult mvcResult = mvc.perform(get(BASE_PATH, heroOne.getId().toString(), heroTwo.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hero_id").value(heroOne.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hero_compared_id").value(heroTwo.getId().toString()))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        CompareHeroResponse result = objectMapper.readValue(response, CompareHeroResponse.class);

        assertEquals(heroOne.getPowerStats().getStrength() - heroTwo.getPowerStats().getStrength(),
                result.getStrength().intValue());
        assertEquals(heroOne.getPowerStats().getAgility() - heroTwo.getPowerStats().getAgility(),
                result.getAgility().intValue());
        assertEquals(heroOne.getPowerStats().getDexterity() - heroTwo.getPowerStats().getDexterity(),
            result.getDexterity().intValue());
        assertEquals(heroOne.getPowerStats().getIntelligence() - heroTwo.getPowerStats().getIntelligence(),
                result.getIntelligence().intValue());
    }

    @Test
    public void shouldThrowExceptionWhenHeroNotFound() throws Exception {
        Hero heroOne = Utils.getValidHero();
        Hero heroTwo = Utils.getValidHero();

        mvc.perform(get(BASE_PATH, heroOne.getId().toString(), heroTwo.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status_code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.HERO_NOT_FOUND_MESSAGE));
    }
}
