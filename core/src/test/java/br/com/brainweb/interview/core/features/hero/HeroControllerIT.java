package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HeroControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private HeroRepository heroRepository;

    @Autowired
    private MockMvc mockMvc;

    private Hero MockgetHeroRepository() {
        PowerStats powerStats = new PowerStats(UUID.fromString("fc248cc0-5a4a-489a-8987-749a14a1177f"),
                1,
                1,
                1,
                1,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));
        Hero hero = new Hero(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"),
                "batman",
                Race.HUMAN.name(),
                true,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));

        hero.setPowerStats(powerStats);
        return hero;
    }

    private Hero MockSaveHeroRepository() {
        PowerStats powerStats = new PowerStats(null,
                3,
                3,
                2,
                1,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));
        Hero hero = new Hero(null,
                "superMan",
                Race.ALIEN.name(),
                true,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));

        hero.setPowerStats(powerStats);
        return hero;
    }

    private HeroDTO MockHeroDTOSucess(){
        return HeroDTO.builder()
                .name("superMan")
                .agility(3)
                .dexterity(2)
                .strength(3)
                .intelligence(1)
                .race(Race.ALIEN)
                .created_at(Timestamp.valueOf("2020-02-22 21:29:59"))
                .updated_at(Timestamp.valueOf("2020-02-22 21:29:59"))
                .build();
    }

    private HeroDTO MockHeroDTOFailure(){
        return HeroDTO.builder()
                .name("batman")
                .agility(3)
                .dexterity(2)
                .strength(3)
                .intelligence(1)
                .race(Race.ALIEN)
                .created_at(Timestamp.valueOf("2020-02-22 21:29:59"))
                .updated_at(Timestamp.valueOf("2020-02-22 21:29:59"))
                .build();
    }

    @Before
    public void setup(){
        Hero hero = this.MockgetHeroRepository();
        BDDMockito.when(heroRepository.findById(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"))).thenReturn(java.util.Optional.of(hero));
        BDDMockito.when(heroRepository.findByName("batman")).thenReturn(java.util.Optional.of(hero));
    }


    @Test
    public void getHeroByIdFailure() throws Exception {
//        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/heroes/751776fc-2d07-4584-9dc9-00c2b7173fca", String.class);
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/heroes/{id}", UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getHeroByIdSuccess() throws Exception {
//        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/heroes/6dcc3998-f510-404d-aac4-2ce8caae29b9", String.class);
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/heroes/{id}", UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9")))
                .andExpect(status().isOk());
    }

    @Test
    public void getHeroByNameFailure() throws Exception {
//        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/heroes/name/thor", String.class);
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/heroes/name/{name}", "thor"))
                .andExpect(status().isOk());
    }

    @Test
    public void getHeroByNameSuccess() throws Exception {
//        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/api/v1/heroes/name/batman", String.class);
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/api/v1/heroes/name/{name}", "batman"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteHeroSuccess() throws Exception{
        Hero hero = this.MockgetHeroRepository();
        BDDMockito.doNothing().when(heroRepository).delete(hero);
//        ResponseEntity<String> exchange = testRestTemplate.exchange("/api/v1/heroes/{id}", HttpMethod.DELETE, null, String.class, UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"));
//        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
        mockMvc.perform(MockMvcRequestBuilders.
                delete("/api/v1/heroes/{id}", UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteHeroFailure() throws Exception{
        Hero hero = this.MockgetHeroRepository();
        BDDMockito.doNothing().when(heroRepository).delete(hero);
//        ResponseEntity<String> exchange = testRestTemplate.exchange("/api/v1/heroes/{id}", HttpMethod.DELETE, null, String.class, UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"));
//        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
        mockMvc.perform(MockMvcRequestBuilders.
                delete("/api/v1/heroes/{id}", UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createHeroFailure() throws Exception{
        Hero hero = MockSaveHeroRepository();
        HeroDTO heroDTO = MockHeroDTOFailure();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/v1/heroes", heroDTO, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void createHeroSuccess() throws Exception{
        Hero hero = MockSaveHeroRepository();
        HeroDTO heroDTO = MockHeroDTOSucess();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/v1/heroes", heroDTO, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

}
