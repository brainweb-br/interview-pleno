package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.compareHeroes.CompareHeroesService;
import br.com.brainweb.interview.model.DTO.ComparisonHeroesDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CompareHeroesServiceTest {
    @MockBean
    private HeroRepository heroRepository;

    @Autowired
    private CompareHeroesService compareHeroesService;

    @Autowired
    private MockMvc mockMvc;

    private Hero MockgetHero1Repository() {
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

    private Hero MockgetHero2Repository() {
        PowerStats powerStats = new PowerStats(UUID.fromString("11ff9b51-6795-4be8-8f4c-e683a1492a6a"),
                2,
                3,
                4,
                1,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));
        Hero hero = new Hero(UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"),
                "superMan",
                Race.HUMAN.name(),
                true,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));

        hero.setPowerStats(powerStats);
        return hero;
    }

    @Before
    public void setup(){
        Hero hero1 = this.MockgetHero1Repository();
        BDDMockito.when(heroRepository.findById(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"))).thenReturn(java.util.Optional.of(hero1));
        Hero hero2 = this.MockgetHero2Repository();
        BDDMockito.when(heroRepository.findById(UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"))).thenReturn(java.util.Optional.of(hero2));
    }

    @Test
    public void getComparasionHeroesSuccess() throws Exception {
        ComparisonHeroesDTO comparisonHeroesDTO = compareHeroesService.getComparison(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"), UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"));
        assertNotNull(comparisonHeroesDTO);
    }

    @Test
    public void getComparasionHeroesFailure() throws Exception {
        ComparisonHeroesDTO comparisonHeroesDTO = compareHeroesService.getComparison(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"), UUID.fromString("9f72ac14-ef20-4cd7-98ca-89c047780b4a"));
        assertNull(comparisonHeroesDTO);
    }
}
