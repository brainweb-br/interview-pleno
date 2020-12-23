package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.EnumRace;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("it")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/heroes";

    mockData();
    }

    @Test
    public void given_createHero_when_withAllArguments_success() {
        RestAssured.given()
                .body("{\n" +
                        "    \"name\":\" Super-man\",\n" +
                        "    \"race\":\"ALIEN\",\n" +
                        "    \"enabled\":true,\n" +
                        "    \"power_stats_id\": {\n" +
                        "    \"strength\":10,\n" +
                        "    \"agility\":10,\n" +
                        "    \"dexterity\":6,\n" +
                        "    \"intelligence\":2\n" +
                        "    }\n" +
                        "}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());    }

    @Test
    public void given_createHero_when_withoutAnyArguments_failare() {
        RestAssured.given()
                .body("{\n" +
                        "    \"name\":\" Super-man\",\n" +
                        "    \"power_stats_id\": {\n" +
                        "    \"strength\":10,\n" +
                        "    \"agility\":10,\n" +
                        "    \"dexterity\":6,\n" +
                        "    \"intelligence\":2\n" +
                        "    }\n" +
                        "}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());    }

    @Test
    public void given_alterHero_when_withAllArgumentsButPowerStatsWrong_failare() {
        RestAssured.given()
                .body("{\n" +
                        "    \"name\":\" Super-man\",\n" +
                        "    \"race\":\"ALIEN\",\n" +
                        "    \"enabled\":true,\n" +
                        "    \"power_stats_id\": {\n" +
                        "    \"strength\":10,\n" +
                        "    \"agility\":102,\n" +
                        "    \"dexterity\":126,\n" +
                        "    \"intelligence\":2\n" +
                        "    }\n" +
                        "}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());    }

    @Test
    public void given_alterHero_when_withoutAnyArguments_failare() {
        RestAssured.given()
                .body("{\n" +
                        "    \"name\":\" Super-man\",\n" +
                        "    \"power_stats_id\": {\n" +
                        "    \"strength\":10,\n" +
                        "    \"agility\":10,\n" +
                        "    \"dexterity\":6,\n" +
                        "    \"intelligence\":2\n" +
                        "    }\n" +
                        "}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());    }

    @Test
    public void given_findAllHeros_must_return200() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    private void mockData() {
        var powerStats = new PowerStats();
        powerStats.setAgility((short) 10);
        powerStats.setStrength((short) 8);
        powerStats.setDexterity((short) 4);
        powerStats.setIntelligence((short) 1);

        var hero = new Hero();
        hero.setName("Mutano");
        hero.setRace(EnumRace.ALIEN);
        hero.setEnabled(true);
        hero.setPowerStatsId(powerStats);
        heroService.saveHero(hero);

        Hero hero2 = new Hero();

        hero.setName("Batman");
        hero.setRace(EnumRace.HUMAN);
        hero.setEnabled(true);
        powerStats.setAgility((short) 8);
        powerStats.setStrength((short) 6);
        powerStats.setDexterity((short) 10);
        powerStats.setIntelligence((short) 10);
        heroService.saveHero(hero2);

    }
}