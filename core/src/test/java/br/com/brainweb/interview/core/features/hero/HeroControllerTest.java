package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.exception.NotFoundEntity;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    Hero updatedHero = new Hero();
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
        setFindByValidId();

        logger.info("Save Built");
        setSave();


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

    public void setFindByValidId() {
        hero.setId(UUID.fromString(heroId));
        hero.getPowerStats().setId(UUID.fromString(powerStatsId));
        when(heroRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(hero));

    }

    public void setFindByInvalidId() {
        when(heroRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    }

    public void setFindByName() {
        when(heroRepository.findByName(any(String.class))).thenReturn(Optional.ofNullable(hero));
    }

    public void setFindByNameAndBreed() {
        when(heroRepository.findByNameAndBreed(any(String.class), any(String.class))).thenReturn(Optional.ofNullable(hero));
    }

    public void setUpdateHero() {
        updatedHero = new Hero();
        updatedHero.setName("thor");
        updatedHero.setBreed("DIVINE");
        updatedHero.setPowerStats(hero.getPowerStats());
        updatedHero.setIsEnabled(hero.getIsEnabled());
        updatedHero.setCreationDate(hero.getCreationDate());
        updatedHero.setUpdateDate(hero.getUpdateDate());
        updatedHero.setId(UUID.fromString(heroId));
        updatedHero.getPowerStats().setId(UUID.fromString(powerStatsId));
        when(heroRepository.save(any(Hero.class))).thenReturn(updatedHero);
    }

    public void setDeleteInvalidIdHero() {
        when(heroService.deleteHero(heroId)).thenThrow(new NotFoundEntity("Herói com id " + heroId + " não encontrado"));
    }

    @Test
    public void shouldReturnHeroObjectWhenCreateNewHeroTest() {
        logger.info("Iniciando teste da classe service (createHero)");

        DtoHeroResponse dtoHeroResponse = heroService.createHero(hero);

        assertThat(dtoHeroResponse.getName()).isEqualTo(hero.getName());
        assertThat(dtoHeroResponse.getPowerStats().getAgility()).isEqualTo(hero.getPowerStats().getAgility());
    }

    @Test
    public void shouldThrowExceptionWhenCreateNewHeroThatAlreadyExistsTest() {
        logger.info("Iniciando teste da classe service (createHero)");

        setFindByNameAndBreed();
        Exception exception = assertThrows(RuntimeException.class, () -> heroService.createHero(hero));

        String expectedMessage = "Hero already exists";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void shouldReturnHeroObjectWhenFindHeroByIdTest() {
        logger.info("Iniciando teste da classe service (findHeroById)");

        DtoHeroResponse dtoHeroResponse = heroService.findHeroById(heroId);
        DtoHeroResponse objectToBeCompared = new DtoHeroResponse(hero);

        assertThat(dtoHeroResponse).isEqualTo(objectToBeCompared);
    }

    @Test
    public void shouldThrowExceptionWhenFindHeroByInvalidIdTest() {
        logger.info("Iniciando teste da classe service (findHeroById)");

        heroId = UUID.randomUUID().toString();
        setFindByInvalidId();
        Exception exception = assertThrows(RuntimeException.class, () -> heroService.findHeroById(heroId));

        String expectedMessage = "Herói nao encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void shouldReturnAHeroObjectWhenFilterHeroesByNameTest() {
        logger.info("FindByName Built");
        setFindByName();
        logger.info("Iniciando teste da classe service (filterHeroesByName");

        DtoHeroResponse dtoHeroResponse = heroService.filterHeroesByName("superman");
        DtoHeroResponse objectToBeCompared = new DtoHeroResponse(hero);

        assertThat(dtoHeroResponse).isEqualTo(objectToBeCompared);
    }

    @Test
    public void shouldThrowExceptionWhenFilterHeroesByInvalidTest() {
        logger.info("Iniciando teste da classe service (filterHeroesByName");

        Exception exception = assertThrows(RuntimeException.class, () -> heroService.filterHeroesByName("supermann"));

        String expectedMessage = "Heroi com o nome: supermann nao encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void shouldThrowExceptionWhenWhenUpdateHeroWithInvalidIdTest() {
        logger.info("Iniciando teste da classe service (updateHero");

        setUpdateHero();
        DtoHeroResponse dtoHeroResponse = heroService.updateHero(updatedHero, heroId);
        DtoHeroResponse objectToBeCompared = new DtoHeroResponse(updatedHero);

        assertThat(dtoHeroResponse.getId()).isEqualTo(objectToBeCompared.getId());
        assertThat(dtoHeroResponse.getName()).isEqualTo(objectToBeCompared.getName());
        assertThat(dtoHeroResponse.getBreed()).isEqualTo(objectToBeCompared.getBreed());
        assertThat(dtoHeroResponse.getPowerStats()).isEqualTo(objectToBeCompared.getPowerStats());
        assertThat(dtoHeroResponse.getIsEnabled()).isEqualTo(objectToBeCompared.getIsEnabled());
        assertThat(dtoHeroResponse.getCreationDate()).isEqualTo(objectToBeCompared.getCreationDate());
    }

    @Test
    public void shouldReturnHeroObjectWhenUpdateHeroTest() {
        logger.info("Iniciando teste da classe service (updateHero");

        heroId = UUID.randomUUID().toString();
        setFindByInvalidId();
        Exception exception = assertThrows(RuntimeException.class, () -> heroService.updateHero(updatedHero, heroId));

        String expectedMessage = "Herói nao encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void shouldReturnOkWhenDeleteHeroWithValidIdTest() {
        logger.info("Iniciando teste da classe service (deleteHero");

        ResponseEntity<Object> response = heroService.deleteHero(heroId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkWhenDeleteHeroWithInvalidIdTest() {
        logger.info("Iniciando teste da classe service (deleteHero");

        setFindByInvalidId();
        Exception exception = assertThrows(NotFoundEntity.class, () -> heroService.deleteHero(heroId));

        String expectedMessage = "Herói com id " + heroId + " não encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

}
