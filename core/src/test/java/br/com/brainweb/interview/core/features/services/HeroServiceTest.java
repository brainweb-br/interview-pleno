package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.core.features.repositories.PowerStatsRepository;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.exeptions.HeroNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
class HeroServiceTest {
    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    private static HeroRequestDTO heroRequestDTO;


    @BeforeAll
    public static void setUp() {
        heroRequestDTO = createHeroRequestDTO();
    }

    @Test
    @Commit
    void save() {
        // when hero saved
        heroRequestDTO = createHeroRequestDTO();
        UUID idOfSaved = heroService.save(heroRequestDTO);
        // then get by id
        String name = heroService.find(idOfSaved.toString()).getName();
        assertEquals(heroRequestDTO.getName(), name);
        assertTrue(heroService.find(Optional.of(name)).stream()
                .anyMatch(h -> h.getName().equals(heroRequestDTO.getName())));
    }

    @Test
    @Commit
    void update() {

        // save hero
        UUID idOfSaved = heroService.save(heroRequestDTO);
        //find hero
        HeroResponseDTO heroResponseDTO = heroService.find(idOfSaved.toString());

        // update hero's name
        String randomName = generateRandomName(10);

        heroRequestDTO.setName(randomName);

        heroService.update(idOfSaved.toString(), heroRequestDTO);


        assertEquals(randomName, heroService.find(idOfSaved.toString()).getName());

    }

    @Test
    @Commit
    void delete() {
        // when hero saved
        UUID idOfSaved = heroService.save(heroRequestDTO);
        // deleted
        heroService.delete(idOfSaved.toString());
        // then
        assertThrows(HeroNotFoundException.class, () -> heroService.find(idOfSaved.toString()));

    }

    @Test
    @Commit
    void deleteCascade() {
        // when hero saved
        Hero hero = createHero();
        Hero save = heroRepository.save(hero);
        UUID powerStatsId = hero.getPowerStats().getId();
        // deleted
        heroService.delete(save.getId().toString());
        // then
        Optional<PowerStats> powerStatsDeleted = powerStatsRepository.findById(powerStatsId);

        assertTrue(powerStatsDeleted.isEmpty());

    }

}