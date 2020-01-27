package br.com.brainweb.interview.core.features.hero;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@WebAppConfiguration
@SpringBootTest(classes = { HeroController.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HeroServiceIT {

	@InjectMocks
	private HeroController heroController;

	private MockMvc	mockMvc;

	@Test
	public void shouldFindHeroByName() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heros?name=hero")).andExpect(
				MockMvcResultMatchers.status().isOk());
	}
}
