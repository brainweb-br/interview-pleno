package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static br.com.brainweb.interview.core.features.hero.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("it")
public class PersistenceLayerTestHero {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HeroRepository heroRepository;


    @Test
    public void whenFindByName_thenReturnHero() {
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

        Hero superMan = new Hero();
        superMan.setName(generateRandomName(10));
        superMan.setRace(Race.ALIEN);
        superMan.setPowerStats(powerStats);
        superMan.setEnabled(generateRandomBoolean());
        superMan.setCreated(LocalDateTime.now());
        superMan.setUpdated(LocalDateTime.now());

        // when
        Hero heroFoundByName = heroRepository.findByName(superMan.getName()).get();

        // then
        assertThat(heroFoundByName.getName())
                .isEqualTo(superMan.getName());
    }

}
