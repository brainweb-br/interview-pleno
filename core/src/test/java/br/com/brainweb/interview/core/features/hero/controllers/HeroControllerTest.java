package br.com.brainweb.interview.core.features.hero.controllers;

import br.com.brainweb.interview.core.features.controllers.HeroController;
import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsResponseDTO;
import br.com.brainweb.interview.model.enums.Race;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HeroController.class)
public class HeroControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HeroService heroService;

    private static final String UUID_POWER_STATS = UUID.randomUUID().toString();
    private static final Integer AGILITY = generateRandomNumber(1, 10);
    private static final Integer DEXTERITY = generateRandomNumber(1, 10);
    private static final Integer INTELLIGENCE = generateRandomNumber(1, 10);
    private static final Integer STRENGTH = generateRandomNumber(1, 10);

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", BRAZIL);

    private static final LocalDateTime CREATED_AT = generateRandomDateTime();
    private static final LocalDateTime UPDATED_AT = generateRandomDateTime();

    private static final String UUID_HERO = UUID.randomUUID().toString();
    private static final String NAME = generateRandomName(10);
    private static final Race RACE = pickRandomRace();
    private static final Boolean ENABLED = generateRandomBoolean();


    private static final PowerStatsResponseDTO POWER_STATS_RESPONSE_DTO =
            new PowerStatsResponseDTO(UUID.fromString(UUID_HERO), AGILITY, DEXTERITY,
                    INTELLIGENCE, STRENGTH, CREATED_AT, UPDATED_AT);

    private static final HeroResponseDTO HERO_RESPONSE_DTO =
            new HeroResponseDTO(UUID.fromString(UUID_POWER_STATS),
                    NAME, RACE, POWER_STATS_RESPONSE_DTO, ENABLED,
                    CREATED_AT, UPDATED_AT);

    @Test
    public void shouldFetchHero() throws Exception {

        when(heroService.find(UUID_HERO)).thenReturn(HERO_RESPONSE_DTO);

        String heroJson = mvc.perform(get("/heroes/" + UUID_HERO))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("{\"id\":\"" + UUID_POWER_STATS + "\"," +
                "\"name\":\"" + NAME + "\"," +
                "\"race\":\"" + RACE + "\"," +

                "\"power_stats\":{\"id\":\"" + UUID_HERO + "\"," +
                "\"agility\":" + AGILITY + "," +
                "\"dexterity\":" + DEXTERITY + "," +
                "\"intelligence\":" + INTELLIGENCE + "," +
                "\"strength\":" + STRENGTH + "," +
                "\"created\":\"" + FORMATTER.format(CREATED_AT) + "\"," +
                "\"updated\":\"" + FORMATTER.format(UPDATED_AT) + "\"}," +
                "\"enabled\":" + ENABLED + "," +
                "\"created\":\"" + FORMATTER.format(CREATED_AT) + "\"," +
                "\"updated\":\"" + FORMATTER.format(UPDATED_AT) + "\"}", heroJson);
    }


}
