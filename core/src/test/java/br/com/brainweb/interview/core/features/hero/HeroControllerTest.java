package br.com.brainweb.interview.core.features.hero;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;

@WebMvcTest(HeroController.class)
public class HeroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private HeroService heroService;

	@Test
	void create() throws Exception {
		final String body = objectMapper.writeValueAsString(createHero());
		final ResultActions resultActions = mockMvc
				.perform(post("/heroes").contentType(APPLICATION_JSON).content(body));
		
		resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
		verify(heroService, times(1)).create(any());
	}

	private Hero createHero() {
		return Hero.builder().name("Batman").race(Race.HUMAN).powerStats(createPowerStats()).build();
	}

	private PowerStats createPowerStats() {
		return PowerStats.builder().agility(4).dexterity(8).strength(6).intelligence(10).build();
	}
}
