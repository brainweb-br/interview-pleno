package br.com.brainweb.interview.core.features.hero.controllers;

import br.com.brainweb.interview.core.features.controllers.HeroController;
import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.response.PowerStatsDifferenceDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.brainweb.interview.core.features.hero.TestUtils.createPowerStatsDifferenceDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HeroController.class)
public class HeroControllerCompareTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HeroService heroService;

    private PowerStatsDifferenceDTO powerStatsDifferenceDTO;

    @Test
    public void shouldReturnDifference() throws Exception {


        powerStatsDifferenceDTO = createPowerStatsDifferenceDTO();

        when(heroService.calculateDifference(powerStatsDifferenceDTO.getFirstHeroUUID().toString(),
                powerStatsDifferenceDTO.getSecondHeroUUID().toString())).thenReturn(powerStatsDifferenceDTO);


        String heroJson = mvc.perform(get("/heroes/difference")
                .param("first-hero-id", powerStatsDifferenceDTO.getFirstHeroUUID().toString())
                .param("second-hero-id", powerStatsDifferenceDTO.getSecondHeroUUID().toString())
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        assertEquals("{\"first_hero_uuid\":\"" + powerStatsDifferenceDTO.getFirstHeroUUID().toString() + "\"," +
                "\"second_hero_uuid\":\"" + powerStatsDifferenceDTO.getSecondHeroUUID().toString() + "\"," +
                "\"agility\":" + powerStatsDifferenceDTO.getAgility() + "," +
                "\"dexterity\":" + powerStatsDifferenceDTO.getDexterity() + "," +
                "\"intelligence\":" + powerStatsDifferenceDTO.getIntelligence() + "," +
                "\"strength\":" + powerStatsDifferenceDTO.getStrength() + "}", heroJson);

    }

    @Test
    public void shouldReturnNotFountWithoutParameters() throws Exception {


        powerStatsDifferenceDTO = createPowerStatsDifferenceDTO();

        when(heroService.calculateDifference(powerStatsDifferenceDTO.getFirstHeroUUID().toString(),
                powerStatsDifferenceDTO.getSecondHeroUUID().toString())).thenReturn(powerStatsDifferenceDTO);


        String heroJson = mvc.perform(get("/heroes/difference")
        ).andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }
}
