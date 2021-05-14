package br.com.brainweb.interview.core.features.hero.it;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.Utils;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ActiveProfiles("it") tive problemas ao executar os testes utilizando esse profile
public class GetHeroIT {

    private final String BASE_URI = "/heros";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetHeroById() throws Exception {
        mvc.perform(get(BASE_URI + "/" + Utils.getValidHero().getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.powerStats", hasSize(1)));

        verify(heroRepository, times(1)).findById(any());
    }

    @Test
    void shouldReturnErrorWhenHeroIdNotFound() throws Exception {
        mvc.perform(get(BASE_URI + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", hasSize(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.powerStats", hasSize(0)));

        verify(heroRepository, times(1)).findById(any());
    }

}
