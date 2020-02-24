package br.com.brainweb.interview.core.features.hero.entities;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.brainweb.interview.core.features.hero.TestUtils.createHero;
import static br.com.brainweb.interview.core.features.hero.TestUtils.createPowerStats;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
public class HeroTest {

    @Autowired
    private HeroRepository heroRepository;


    @BeforeAll
    public static void init() {

    }


    @Test
    public void shouldStoreEmployeeAndFindById() {

        Hero hero = createHero();
        PowerStats powerStats = hero.getPowerStats();
        powerStats.setCreated(LocalDateTime.now());
        powerStats.setUpdated(LocalDateTime.now());
        //testEntityManager.merge(hero);
        System.out.println("hhhh");
        // testEntityManager.merge(EMPLOYEE_2);

        Hero save = heroRepository.save(hero);
        // assertEquals(EMPLOYEE_2, testEntityManager.find(Employee.class, 2));
    }
}