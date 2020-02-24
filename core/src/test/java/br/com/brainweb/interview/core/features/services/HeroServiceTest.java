package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.request.PowerStatsRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.enums.Race;
import br.com.brainweb.interview.model.exeptions.HeroNotFoundException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.TestUtils.*;
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

    private static HeroRequestDTO heroRequestDTO;


    @BeforeAll
    public static void setUp() {
        heroRequestDTO = createHeroRequestDTO();
    }

    @Test
    @Commit
    void save() {
        // when hero saved
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


}