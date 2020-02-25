package br.com.brainweb.interview.core.features.hero.controllers;

import br.com.brainweb.interview.core.features.controllers.HeroController;
import br.com.brainweb.interview.core.features.services.HeroService;
import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.exeptions.HeroNotFoundException;
import br.com.brainweb.interview.model.exeptions.NameAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static br.com.brainweb.interview.core.features.TestUtils.createHeroRequestDTO;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HeroController.class)
public class HeroControllerSaveUpdateDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroService heroService;

    @Autowired
    private ObjectMapper mapper;


    private HeroRequestDTO heroRequestDTO = createHeroRequestDTO();

    @Test
    public void saveEndpointSuccess() throws Exception {


        when(heroService.save(heroRequestDTO)).thenReturn(UUID.randomUUID());

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotSaveWithNameThatExists() throws Exception {

        doThrow(new NameAlreadyExistsException(heroRequestDTO.getName()))
                .when(heroService).save(heroRequestDTO);

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }


    @Test
    public void updateEndpointSuccess() throws Exception {

        String uuid = UUID.randomUUID().toString();
        when(heroService.update(uuid, heroRequestDTO)).thenReturn(UUID.fromString(uuid));

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(patch("/heroes/" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldNotUpdateWithIdThatDoesNotExists() throws Exception {

        String uuid = UUID.randomUUID().toString();
        doThrow(new NameAlreadyExistsException(heroRequestDTO.getName()))
                .when(heroService).update(uuid, heroRequestDTO);

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(patch("/heroes" + uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteEndpointSuccess() throws Exception {

        String heroId = UUID.randomUUID().toString();
        doNothing().when(heroService).delete(heroId);

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(delete("/heroes/" + heroId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteWithIdThatDoesNotExist() throws Exception {

        String heroId = UUID.randomUUID().toString();
        doThrow(new HeroNotFoundException(heroId))
                .when(heroService).delete(heroId);

        String json = mapper.writeValueAsString(heroRequestDTO);
        mockMvc.perform(delete("/heroes/" + heroId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
