package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DTO.HeroDTO;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HeroServiceTest {
    @Autowired
    private HeroService heroService;

    @MockBean
    private HeroRepository heroRepository;

    @Before
    public void setup(){
        Hero hero = this.MockgetHeroRepository();
        BDDMockito.when(heroRepository.findById(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"))).thenReturn(java.util.Optional.of(hero));
        BDDMockito.when(heroRepository.findByName("batman")).thenReturn(java.util.Optional.of(hero));
    }

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

    private Hero MockUpdateHeroRepository() {
        PowerStats powerStats = new PowerStats(UUID.fromString("fc248cc0-5a4a-489a-8987-749a14a1177f"),
                3,
                3,
                2,
                1,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));
        Hero hero = new Hero(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"),
                "superMan",
                Race.ALIEN.name(),
                true,
                Timestamp.valueOf("2020-02-22 21:29:59"),
                Timestamp.valueOf("2020-02-22 21:29:59"));

        hero.setPowerStats(powerStats);
        return hero;
    }

    private HeroDTO MockHeroDTONonExistent(){
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

    private HeroDTO MockHeroDTOExistent(){
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

    @Test
    public void getHeroByIdFound(){
        HeroDTO heroDTO = heroService.get(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"));
        assertNotNull(heroDTO);
        assertNotNull(heroDTO.getId());
        assertEquals("batman", heroDTO.getName());
    }

    @Test
    public void getHeroByIdNotFound(){
        HeroDTO heroDto = heroService.get(UUID.fromString("9f72ac14-ef20-4cd7-98ca-89c047780b4a"));
        assertNull(heroDto);
    }

    @Test
    public void getHeroByNameFound(){
        HeroDTO heroDTO = heroService.get("batman");
        assertNotNull(heroDTO);
        assertNotNull(heroDTO.getId());
        assertEquals("batman", heroDTO.getName());
    }

    @Test
    public void getHeroByNameNotFound(){
        HeroDTO heroDto = heroService.get("superMan");
        assertNull(heroDto);
    }

    @Test
    public void deleteHeroFailure(){
        boolean deleted = heroService.delete(UUID.fromString("9f72ac14-ef20-4cd7-98ca-89c047780b4a"));
        assertFalse(deleted);
    }

    @Test
    public void deleteHeroSuccess(){
        boolean deleted = heroService.delete(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"));
        assertTrue(deleted);
    }

    @Test
    public void createHeroFailure(){
        Hero hero = MockSaveHeroRepository();
        HeroDTO heroDTO = MockHeroDTOExistent();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        HeroDTO heroDTOResponse = heroService.create(heroDTO);
        assertNull(heroDTOResponse);
    }

    @Test
    public void createHeroSuccess(){
        Hero hero = MockSaveHeroRepository();
        HeroDTO heroDTO = MockHeroDTONonExistent();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        HeroDTO heroDTOResponse = heroService.create(heroDTO);
        assertNotNull(heroDTOResponse);
        assertEquals(heroDTOResponse.getName(), "superMan");
    }

    @Test
    public void updateHeroFailure(){
        Hero hero = MockSaveHeroRepository();
        HeroDTO heroDTO = MockHeroDTONonExistent();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        HeroDTO heroDTOResponse = heroService.update(UUID.fromString("751776fc-2d07-4584-9dc9-00c2b7173fca"), heroDTO);
        assertNull(heroDTOResponse);
    }

    @Test
    public void updateHeroSuccess(){
        Hero hero = MockUpdateHeroRepository();
        HeroDTO heroDTO = MockHeroDTONonExistent();
        BDDMockito.when(heroRepository.save(hero)).thenReturn(hero);
        HeroDTO heroDTOResponse = heroService.update(UUID.fromString("6dcc3998-f510-404d-aac4-2ce8caae29b9"), heroDTO);
        assertNotNull(heroDTOResponse);
        assertEquals("superMan", heroDTO.getName());
    }
}
