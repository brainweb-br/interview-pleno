package br.com.brainweb.interview.core.features.hero.controllers;

import br.com.brainweb.interview.core.features.controllers.HeroController;
import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
public class HeroControllerFindByIdTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HeroService heroService;

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", BRAZIL);

    private HeroResponseDTO heroResponseDTO = createHeroResponseDTO();
    private PowerStatsResponseDTO powerStatsResponseDTO = heroResponseDTO.getPowerStats();

    @Test
    public void shouldFetchHero() throws Exception {

        when(heroService.find(heroResponseDTO.getId().toString())).thenReturn(heroResponseDTO);

        String heroJson = mvc.perform(get("/heroes/" + heroResponseDTO.getId().toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("{\"id\":\"" + heroResponseDTO.getId() + "\"," +
                "\"name\":\"" + heroResponseDTO.getName() + "\"," +
                "\"race\":\"" + heroResponseDTO.getRace() + "\"," +

                "\"power_stats\":{\"id\":\"" + powerStatsResponseDTO.getId() + "\"," +
                "\"agility\":" + powerStatsResponseDTO.getAgility() + "," +
                "\"dexterity\":" + powerStatsResponseDTO.getDexterity() + "," +
                "\"intelligence\":" + powerStatsResponseDTO.getIntelligence() + "," +
                "\"strength\":" + powerStatsResponseDTO.getStrength() + "," +
                "\"created\":\"" + FORMATTER.format(powerStatsResponseDTO.getCreated()) + "\"," +
                "\"updated\":\"" + FORMATTER.format(powerStatsResponseDTO.getUpdated()) + "\"}," +
                "\"enabled\":" + heroResponseDTO.getEnabled() + "," +

                "\"created\":\"" + FORMATTER.format(heroResponseDTO.getCreated()) + "\"," +
                "\"updated\":\"" + FORMATTER.format(heroResponseDTO.getUpdated()) + "\"}", heroJson);
    }

    @Test
    public void shouldReturnNotFoundWhenHeroDoesNotExist() throws Exception {
        UUID idDoesNotExist = UUID.randomUUID();
        when(heroService.find(heroResponseDTO.getId().toString())).thenReturn(heroResponseDTO);

        mvc.perform(get("/heroes/" + idDoesNotExist))
                .andExpect(status().isNotFound());
    }
}
