package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.hero.impl.HeroRepositoryImpl;
import br.com.gubee.interview.core.features.hero.impl.HeroServiceImpl;
import br.com.gubee.interview.dto.ComparisonRequest;
import br.com.gubee.interview.dto.HeroComparison;
import br.com.gubee.interview.dto.HeroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;



public class HeroControllerTest {
    @Mock
    private HeroService heroService;

    @InjectMocks
    private HeroController heroController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateHero_ValidHero_ReturnsOkResponse() {
        HeroResponse hero = new HeroResponse("Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroService.save(hero)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity response = heroController.createHero(hero);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetHero_ValidId_ReturnsOkResponse() {
        UUID heroId = UUID.randomUUID();
        HeroResponse hero = new HeroResponse("Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroService.getById(heroId)).thenReturn(new ResponseEntity<>(hero, HttpStatus.OK));

        ResponseEntity<HeroResponse> response = heroController.getHero(heroId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(hero, response.getBody());

    }

    @Test
    public void testGetHero_InvalidId_ReturnsNotFoundResponse() {
        UUID heroId = UUID.randomUUID();

        when(heroService.getById(heroId)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<HeroResponse> response = heroController.getHero(heroId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetHeroByName_ValidName_ReturnsOkResponse() {
        String heroName = "Superman";
        HeroResponse hero = new HeroResponse( heroName, "Kryptonian", true, 100, 80, 70, 90);

        when(heroService.getByName(heroName)).thenReturn(new ResponseEntity<>(hero, HttpStatus.OK));

        ResponseEntity<HeroResponse> response = heroController.getHeroByName(heroName);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(hero, response.getBody());

    }

    @Test
    public void testGetHeroByName_InvalidName_ReturnsNotFoundResponse() {
        String heroName = "Superman";

        when(heroService.getByName(heroName)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<HeroResponse> response = heroController.getHeroByName(heroName);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testEditHero_ValidHeroAndId_ReturnsOkResponse() {
        UUID heroId = UUID.randomUUID();
        HeroResponse hero = new HeroResponse("Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroService.editHero(hero, heroId)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity response = heroController.editHero(hero, heroId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testEditHero_InvalidHeroOrId_ReturnsNotFoundResponse() {
        UUID heroId = UUID.randomUUID();
        HeroResponse hero = new HeroResponse( "Superman", "Kryptonian", true, 100, 80, 70, 90);

        when(heroService.editHero(hero, heroId)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity response = heroController.editHero(hero, heroId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test
    public void testDeleteHero_ValidId_ReturnsOkResponse() {
        UUID heroId = UUID.randomUUID();

        when(heroService.deleteById(heroId)).thenReturn(new ResponseEntity<>("HERO DELETED", HttpStatus.OK));

        ResponseEntity response = heroController.deleteHero(heroId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testDeleteHero_InvalidId_ReturnsNotFoundResponse() {
        UUID heroId = UUID.randomUUID();

        when(heroService.deleteById(heroId)).thenReturn(new ResponseEntity<>("HERO NOT FOUND", HttpStatus.NOT_FOUND));

        ResponseEntity response = heroController.deleteHero(heroId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testCompareHeroes_ValidRequest_ReturnsOkResponse() {
        UUID hero1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();
        ComparisonRequest request = new ComparisonRequest(hero1Id, hero2Id);
        HeroComparison comparison = new HeroComparison(hero1Id, hero2Id, 10, 5, 3, -2);

        when(heroService.compareHeroes(request)).thenReturn(new ResponseEntity<>(comparison, HttpStatus.OK));

        ResponseEntity<HeroComparison> response = heroController.compareHeroes(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(comparison, response.getBody());

    }

    @Test
    public void testCompareHeroes_InvalidRequest_ReturnsNotFoundResponse() {
        UUID hero1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();
        ComparisonRequest request = new ComparisonRequest(hero1Id, hero2Id);

        when(heroService.compareHeroes(request)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<HeroComparison> response = heroController.compareHeroes(request);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

}

