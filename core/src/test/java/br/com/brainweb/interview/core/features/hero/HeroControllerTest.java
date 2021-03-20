package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class HeroControllerTest {

    @InjectMocks
    private HeroController heroController;

    @Mock
    private HeroService heroService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(heroController).build();
    }

    @Test
    public void getHeroSuccessTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/heros"))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getHeroByNameSuccessTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/heros?name=superTester"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getHeroByIdSuccessTest() throws Exception {

        when(heroService.getById(any())).thenReturn(Optional.of(new HeroModel()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/heros/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getHeroByIdFailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/heros/10"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void saveHeroSuccessTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/heros/10"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateHeroSucessTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/heros/10"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void removeHeroSucessTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/heros/10"))
                .andExpect(status().is2xxSuccessful());
    }
}
