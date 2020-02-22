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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
public class HeroTest {

    @Autowired
    private HeroRepository heroRepository;


    private static Hero hero;

    @BeforeAll
    public static void init() {

        // given
        PowerStats powerStats = new PowerStats();

//        UUID id = UUID.randomUUID();
////        powerStats.setId(id);

        powerStats.setStrength(1);
        powerStats.setAgility(2);
        powerStats.setDexterity(3);
        powerStats.setIntelligence(4);
        powerStats.setCreated(LocalDateTime.now());
        powerStats.setUpdated(LocalDateTime.now());

        hero = new Hero();
        hero.setName("SuperMan");
        hero.setRace(Race.ALIEN);
        hero.setPowerStats(powerStats);
        hero.setEnabled(true);
        hero.setCreated(LocalDateTime.now());
        hero.setUpdated(LocalDateTime.now());
    }


    @Test
    public void shouldStoreEmployeeAndFindById() {


        //testEntityManager.merge(hero);
        System.out.println("hhhh");
        // testEntityManager.merge(EMPLOYEE_2);

        Hero save = heroRepository.save(hero);
        // assertEquals(EMPLOYEE_2, testEntityManager.find(Employee.class, 2));
    }
}