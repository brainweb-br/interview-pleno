package br.com.brainweb.interview.core.features.hero.controller;

import br.com.brainweb.interview.core.Application;
import br.com.brainweb.interview.core.features.hero.dto.HeroDTO;
import br.com.brainweb.interview.core.features.hero.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroControllerIT {

    @Autowired
    private MockMvc mvc;

    private String getObjectJson(HeroDTO heroDTO) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(heroDTO);
    }

    @Test
    public void test_create_hero() throws Exception {
        HeroDTO heroDto = Utils.getHeroDTOWithoutId();
        heroDto.setName("Homem-de-Ferro");
        String json = getObjectJson(heroDto);
        mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void test_get_by_id() throws Exception {
        HeroDTO heroDto = Utils.getHeroDTOWithId();
        String json = getObjectJson(heroDto);
        MvcResult mvcResult = mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue())).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HeroDTO heroDTO = mapper.readValue(contentAsString, HeroDTO.class);

        mvc.perform(get("/api/v1/heros/" + heroDTO.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name", is(heroDto.getName())))
                .andExpect(jsonPath("$.race", is(heroDto.getRace().toString())))
                .andExpect(jsonPath("$.id", is(heroDTO.getId().toString())));
    }

    @Test
    public void test_should_return_404_if_not_found() throws Exception {

        mvc.perform(get("/api/v1/heros/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_should_filter_by_hero_name() throws Exception {
        HeroDTO heroDTO = Utils.getHeroDTOWithId();
        heroDTO.setName("Birdman");
        String json = getObjectJson(heroDTO);
        mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue()));
        mvc.perform(get("/api/v1/hero?name=bird"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    public void test_should_bring_empty_hero_list() throws Exception {
        HeroDTO fullHeroDto = Utils.getHeroDTOWithId();
        fullHeroDto.setName("Birdwoman");
        String json = getObjectJson(fullHeroDto);
        mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue()));
        mvc.perform(get("/api/v1/heros"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
        mvc.perform(get("/api/v1/heros?name=crazyname"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    public void test_should_update() throws Exception {
        HeroDTO fullHeroDto = Utils.getHeroDTOWithId();
        fullHeroDto.setName("manbat");
        String json = getObjectJson(fullHeroDto);
        MvcResult mvcResult = mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue())).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        HeroDTO heroDTO = mapper.readValue(contentAsString, HeroDTO.class);
        heroDTO.setName("Batman");
        json = getObjectJson(heroDTO);

        mvc.perform(put("/api/v1/heros/" + heroDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name", is("Batman")))
                .andExpect(jsonPath("$.race", is(fullHeroDto.getRace().toString())))
                .andExpect(jsonPath("$.id", is(heroDTO.getId().toString())));
    }

    @Test
    public void test_should_return_404_when_not_find_on_update() throws Exception {
        HeroDTO fullHeroDto = Utils.getHeroDTOWithId();
        fullHeroDto.setName("manbat2");
        String json = getObjectJson(fullHeroDto);
        MvcResult mvcResult = mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue())).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        HeroDTO heroDTO = mapper.readValue(contentAsString, HeroDTO.class);
        heroDTO.setName("Batman");
        json = getObjectJson(heroDTO);

        mvc.perform(put("/api/v1/heros/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void test_should_delete() throws Exception {
        HeroDTO fullHeroDto = Utils.getHeroDTOWithId();
        String json = getObjectJson(fullHeroDto);
        MvcResult mvcResult = mvc.perform(post("/api/v1/heros")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", notNullValue())).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HeroDTO heroDTO = mapper.readValue(contentAsString, HeroDTO.class);

        mvc.perform(get("/api/v1/heros/" + heroDTO.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name", is(fullHeroDto.getName())))
                .andExpect(jsonPath("$.race", is(fullHeroDto.getRace().toString())))
                .andExpect(jsonPath("$.id", is(heroDTO.getId().toString())));

        mvc.perform(delete("/api/v1/heros/" + heroDTO.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(get("/api/v1/heros/" + heroDTO.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_should_return_404_if_not_found_when_delete() throws Exception {
        mvc.perform(delete("/api/v1/heros/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}