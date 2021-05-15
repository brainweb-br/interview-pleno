package br.com.brainweb.interview.core.features.hero.it;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.utils.Constants;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"/sql/insert_hero.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("it")
public class GetHeroIT {

    private final String BASE_PATH = "/heros";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    Flyway flyway;

    @After
    public void clearData(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldGetHeroById() throws Exception {
        String validId = heroRepository.findAll().get(0).getId().toString();

        mvc.perform(get(BASE_PATH + "/" + validId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.powerStats").exists());
    }

    @Test
    public void shouldReturnErrorWhenHeroIdNotFound() throws Exception {
        mvc.perform(get(BASE_PATH + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.HERO_NOT_FOUND_MESSAGE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.powerStats").doesNotExist());
    }

    @Test
    public void shouldReturnHeroWhenFilterWithExistingtName() throws Exception {
        mvc.perform(get(BASE_PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "teste")
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].powerStats").exists());
    }

    @Test
    public void shouldReturnEmptyBodyWhenFilterByNonexistentName() throws Exception {
        mvc.perform(get(BASE_PATH + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "testeDeNomeInexistente")
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());
    }

}
