package br.com.brainweb.interview.core.features.services;

import br.com.brainweb.interview.model.entities.Hero;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static br.com.brainweb.interview.core.features.hero.TestUtils.createHero;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PowerStatsServiceTest {

    @Autowired


    @Test
    void save() {
        Hero hero = createHero();


    }
}