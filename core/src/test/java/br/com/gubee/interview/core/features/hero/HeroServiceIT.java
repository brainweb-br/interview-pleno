package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.hero.impl.HeroRepositoryImpl;
import br.com.gubee.interview.core.features.hero.impl.HeroServiceImpl;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.dto.ComparisonRequest;
import br.com.gubee.interview.dto.HeroComparison;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.dto.PowerStatsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("it")
public class HeroServiceIT {

    @Mock
    private HeroRepositoryImpl heroRepository;

    @Mock
    private PowerStatsRepository powerStatsRepository;

    @InjectMocks
    private HeroServiceImpl heroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById_WithExistingId_ReturnsOkResponse() {
        UUID existingId = UUID.randomUUID();
        HeroResponse existingHero = new HeroResponse("Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroRepository.getById(existingId)).thenReturn(existingHero);

        ResponseEntity<HeroResponse> response = heroService.getById(existingId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(existingHero, response.getBody());
    }

    @Test
    public void testGetById_WithNonExistingId_ReturnsNotFoundResponse() {
        UUID nonExistingId = UUID.randomUUID();

        when(heroRepository.getById(nonExistingId)).thenReturn(null);

        ResponseEntity<HeroResponse> response = heroService.getById(nonExistingId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetByName_WithExistingName_ReturnsOkResponse() {
        String existingName = "Superman";
        HeroResponse existingHero = new HeroResponse("Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroRepository.getByName(existingName)).thenReturn(existingHero);

        ResponseEntity<HeroResponse> response = heroService.getByName(existingName);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(existingHero, response.getBody());
    }

    @Test
    public void testGetByName_WithNonExistingName_ReturnsNotFoundResponse() {
        String nonExistingName = "Iron Man";

        when(heroRepository.getByName(nonExistingName)).thenReturn(null);

        ResponseEntity<HeroResponse> response = heroService.getByName(nonExistingName);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSave_SuccessfulSave_ReturnsOkResponse() {
        HeroResponse hero = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);
        PowerStatsResponse powerStats = new PowerStatsResponse();
        powerStats.setStrength(hero.getStrength());
        powerStats.setAgility(hero.getAgility());
        powerStats.setDexterity(hero.getDexterity());
        powerStats.setIntelligence(hero.getIntelligence());

        UUID powerStatsUuid = UUID.randomUUID();

        when(powerStatsRepository.save(powerStats)).thenReturn(powerStatsUuid);

        ResponseEntity response = heroService.save(hero);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSave_ExceptionThrown_ReturnsInternalServerErrorResponse() {
        HeroResponse hero = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);

        when(powerStatsRepository.save(any(PowerStatsResponse.class))).thenThrow(new RuntimeException());

        ResponseEntity response = heroService.save(hero);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("ERROR IN CREATE", response.getBody());

    }

    @Test
    public void testDeleteById_ExistingId_ReturnsOkResponse() {
        UUID existingId = UUID.randomUUID();

        HeroResponse existingHero = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);
        when(heroRepository.getById(existingId)).thenReturn(existingHero);

        UUID powerStatsUuid = UUID.randomUUID();
        when(heroRepository.getPowerStatsUuidById(existingId)).thenReturn(powerStatsUuid);

        ResponseEntity response = heroService.deleteById(existingId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("HERO DELETED", response.getBody());
    }

    @Test
    public void testDeleteById_NonExistingId_ReturnsNotFoundResponse() {
        UUID nonExistingId = UUID.randomUUID();

        when(heroRepository.getById(nonExistingId)).thenReturn(null);

        ResponseEntity response = heroService.deleteById(nonExistingId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("HERO NOT FOUND", response.getBody());
    }

    @Test
    public void testEditHero_ExistingId_ReturnsOkResponse() {
        UUID existingId = UUID.randomUUID();

        HeroResponse existingHero = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);
        when(heroRepository.getById(existingId)).thenReturn(existingHero);

        UUID powerStatsUuid = UUID.randomUUID();
        when(heroRepository.getPowerStatsUuidById(existingId)).thenReturn(powerStatsUuid);

        HeroResponse updatedHero = new HeroResponse("Superman", "HUMAN", true, 90, 70, 60, 80);

        ResponseEntity response = heroService.editHero(updatedHero, existingId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("HERO EDITED", response.getBody());
    }

    @Test
    public void testEditHero_NonExistingId_ReturnsNotFoundResponse() {
        UUID nonExistingId = UUID.randomUUID();

        when(heroRepository.getById(nonExistingId)).thenReturn(null);

        HeroResponse updatedHero = new HeroResponse("Superman", "HUMAN", true, 90, 70, 60, 80);

        ResponseEntity response = heroService.editHero(updatedHero, nonExistingId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("HERO NOT FOUND", response.getBody());

    }

    @Test
    public void testEditHero_ExceptionThrown_ReturnsInternalServerErrorResponse() {
        UUID existingId = UUID.randomUUID();

        HeroResponse existingHero = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);
        when(heroRepository.getById(existingId)).thenReturn(existingHero);

        when(heroRepository.getPowerStatsUuidById(existingId)).thenThrow(new RuntimeException());

        HeroResponse updatedHero = new HeroResponse("Superman", "HUMAN", true, 90, 70, 60, 80);

        ResponseEntity response = heroService.editHero(updatedHero, existingId);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("ERROR IN EDIT", response.getBody());

    }

    @Test
    public void testCompareHeroes_BothHeroesExist_ReturnsOkResponse() {
        UUID hero1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();

        HeroResponse hero1 = new HeroResponse("Superman", "HUMAN", true, 100, 80, 70, 90);
        HeroResponse hero2 = new HeroResponse("Batman", "HUMAN", true, 60, 70, 90, 100);

        ComparisonRequest request = new ComparisonRequest(hero1Id, hero2Id);

        when(heroRepository.getById(hero1Id)).thenReturn(hero1);
        when(heroRepository.getById(hero2Id)).thenReturn(hero2);

        ResponseEntity<HeroComparison> response = heroService.compareHeroes(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        HeroComparison comparison = response.getBody();
        Assertions.assertNotNull(comparison);
        Assertions.assertEquals(hero1Id, comparison.getHero1Id());
        Assertions.assertEquals(hero2Id, comparison.getHero2Id());
        Assertions.assertEquals(hero2.getStrength() - hero1.getStrength(), comparison.getStrengthDifference());
        Assertions.assertEquals(hero2.getAgility() - hero1.getAgility(), comparison.getAgilityDifference());
        Assertions.assertEquals(hero2.getDexterity() - hero1.getDexterity(), comparison.getDexterityDifference());
        Assertions.assertEquals(hero2.getIntelligence() - hero1.getIntelligence(), comparison.getIntelligenceDifference());
    }

    @Test
    public void testCompareHeroes_Hero1NotFound_ReturnsNotFoundResponse() {
        UUID hero1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();

        ComparisonRequest request = new ComparisonRequest(hero1Id, hero2Id);

        when(heroRepository.getById(hero1Id)).thenReturn(null);

        ResponseEntity<HeroComparison> response = heroService.compareHeroes(request);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCompareHeroes_Hero2NotFound_ReturnsNotFoundResponse() {
        UUID hero1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();

        ComparisonRequest request = new ComparisonRequest(hero1Id, hero2Id);

        HeroResponse hero1 = new HeroResponse( "Superman", "HUMAN", true, 100, 80, 70, 90);

        when(heroRepository.getById(hero1Id)).thenReturn(hero1);
        when(heroRepository.getById(hero2Id)).thenReturn(null);

        ResponseEntity<HeroComparison> response = heroService.compareHeroes(request);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
