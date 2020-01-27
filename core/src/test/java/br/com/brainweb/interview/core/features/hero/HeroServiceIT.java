package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.exception.NotFoundException;
import br.com.brainweb.interview.dto.HeroDTO;
import br.com.brainweb.interview.dto.PowerStatsDTO;
import br.com.brainweb.interview.enums.Race;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("it")
public class HeroServiceIT {


    @MockBean
    private HeroController heroController;

    private MockMvc mockMvc;

    @Before
    public void sehtUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(heroController).build();
    }

    @Test
    public void shouldFindHeroByName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heros?name=hero")).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldFindHeroByID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heros/6f21bead-b2fe-4033-9ed1-78d21938a9b7")).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldRemoveHero() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/heros/6f21bead-b2fe-4033-9ed1-78d21938a9b7")).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldCompareHero() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heros/6f21bead-b2fe-4033-9ed1-78d21938a9b7/compare/13ed2a41-665e-463f-9420-666f7c1749a5")).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldCreateHero() throws Exception {
        HeroDTO heroDTO = new HeroDTO();
        heroDTO.setName("Hero Test Integration");
        heroDTO.setEnabled(true);
        heroDTO.setRace(Race.DIVINE.getDescription());
        heroDTO.setPowerStats(new PowerStatsDTO(6, 6, 6, 6));
        ObjectMapper ob = new ObjectMapper();
        String heroJSON = ob.writeValueAsString(heroDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/heros/create").content(heroJSON)).andExpect(
                MockMvcResultMatchers.status().isOk());
    }
}
