package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DtoHeroResponse;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HeroControllerTest {

    @MockBean
    private HeroRepository heroRepository;
    private HeroService heroService;
    PowerStats powerStats = new PowerStats();
    Hero hero = new Hero();
    String heroId = "";
    String powerStatsId = "";
    private static final Logger logger = LoggerFactory.getLogger(HeroControllerTest.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        heroService = new HeroService(heroRepository);
        heroId = UUID.randomUUID().toString();
        powerStatsId = UUID.randomUUID().toString();

        logger.info("Creating Hero");
        createHero();

        logger.info("FindById Built");
        setFindById();

        logger.info("Save Built");
        setSave();

        logger.info("FindByName Built");
        setFindByName();
    }

    public void createHero() {
        powerStats = new PowerStats();
        powerStats.setAgility(10);
        powerStats.setStrength(11);
        powerStats.setDexterity(12);
        powerStats.setIntelligence(13);
        powerStats.setCreationDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        powerStats.setUpdateDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));

        hero = new Hero();
        hero.setName("superman");
        hero.setBreed("HUMAN");
        hero.setPowerStats(powerStats);
        hero.setIsEnabled(true);
        hero.setCreationDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
        hero.setUpdateDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    public void setSave() {
        when(heroRepository.save(any(Hero.class))).then(returnsFirstArg());
    }

    public void setFindById() {
        hero.setId(UUID.fromString(heroId));
        hero.getPowerStats().setId(UUID.fromString(powerStatsId));
        when(heroRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(hero));

    }

    public void setFindByName() {
        when(heroRepository.findByName(any(String.class))).then(returnsFirstArg());
    }

    @Test
    public void shouldReturnHeroObjectWhenCreateNewHero() {
        logger.info("Iniciando teste da classe service (createHero)");

        DtoHeroResponse dtoHeroResponse = heroService.createHero(hero);

        assertThat(dtoHeroResponse.getName()).isEqualTo(hero.getName());
        assertThat(dtoHeroResponse.getPowerStats().getAgility()).isEqualTo(hero.getPowerStats().getAgility());
    }

    @Test
    public void shouldReturnHeroObjectWhenFindHeroById() {
        logger.info("Iniciando teste da classe service (findHeroById)");

        DtoHeroResponse dtoHeroResponse = heroService.findHeroById(heroId);
        DtoHeroResponse objectToBeCompared = new DtoHeroResponse(hero);

        assertThat(dtoHeroResponse).isEqualTo(objectToBeCompared);
    }

    @Test
    public void shouldReturnAHeroListwhenFilterHeroes() {
        logger.info("Iniciando teste da classe service (filterHeroesByName");

//        List<DtoHeroResponse> dtoHeroResponseList =
    }

}
