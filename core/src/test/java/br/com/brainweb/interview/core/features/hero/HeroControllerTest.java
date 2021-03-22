package br.com.brainweb.interview.core.features.hero;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brainweb.interview.core.features.hero.dto.HeroDto;
import br.com.brainweb.interview.core.features.hero.exception.GlobalHandler;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ActiveProfiles("it")
public class HeroControllerTest {
	
	@InjectMocks
	private HeroController heroController;
	
	@Mock
	private HeroService heroService;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.heroController)
        		 .setControllerAdvice(new GlobalHandler())
                .build();
        
        FixtureFactoryLoader.loadTemplates("br.com.brainweb.interview.core.features.hero");
    }
	
	@Test
	public void deveCriarHero() throws Exception {
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		HeroDto heroDto = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		
		Mockito.when(heroService.save(Mockito.any(Hero.class))).thenReturn(hero);
		
		this.mockMvc.perform(post("/api/v1/heroes")
				.content(asJsonString(heroDto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("name").value("Batman"))
		.andExpect(jsonPath("race").value("HUMAN"))
		.andExpect(jsonPath("enable").value(true))
		.andExpect(jsonPath("powerStats.agility").value(5))
		.andExpect(jsonPath("powerStats.dexterity").value(5))
		.andExpect(jsonPath("powerStats.intelligence").value(5));
	}
	
	@Test
	public void deveAtualizarHero() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		HeroDto heroDto = Fixture.from(HeroDto.class).gimme("valid-hero-dto");
		
		Mockito.when(heroService.update(Mockito.any(Hero.class), Mockito.anyString())).thenReturn(Optional.of(hero));
		
		this.mockMvc.perform(put("/api/v1/heroes/{id}", id)
				.content(asJsonString(heroDto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("name").value("Batman"))
		.andExpect(jsonPath("race").value("HUMAN"))
		.andExpect(jsonPath("enable").value(true))
		.andExpect(jsonPath("powerStats.agility").value(5))
		.andExpect(jsonPath("powerStats.dexterity").value(5))
		.andExpect(jsonPath("powerStats.intelligence").value(5));
	}
	
	@Test
	public void deveBuscarHeroById() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroService.findById(Mockito.anyString())).thenReturn(Optional.of(hero));
		
		this.mockMvc.perform(get("/api/v1/heroes/{id}", id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id").value("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"))
		.andExpect(jsonPath("name").value("Batman"))
		.andExpect(jsonPath("race").value("HUMAN"))
		.andExpect(jsonPath("enable").value(true))
		.andExpect(jsonPath("powerStats.agility").value(5))
		.andExpect(jsonPath("powerStats.dexterity").value(5))
		.andExpect(jsonPath("powerStats.intelligence").value(5));
	}
	
	@Test
	public void deveValidarQuandoHeroNaoEncontrado() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		
		Mockito.when(heroService.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get("/api/v1/heroes/{id}", id))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void deveBuscarHeroByName() throws Exception {
		String name = "Batman";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroService.findByName(Mockito.anyString())).thenReturn(Optional.of(hero));
		
		this.mockMvc.perform(get("/api/v1/heroes?name={name}", name))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id").value("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"))
		.andExpect(jsonPath("name").value("Batman"))
		.andExpect(jsonPath("race").value("HUMAN"))
		.andExpect(jsonPath("enable").value(true))
		.andExpect(jsonPath("powerStats.agility").value(5))
		.andExpect(jsonPath("powerStats.dexterity").value(5))
		.andExpect(jsonPath("powerStats.intelligence").value(5));
	}
	
	@Test
	public void deveValidarQuandoNaoRecuperarHeroByName() throws Exception {
		String name = "Batman";
		
		Mockito.when(heroService.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get("/api/v1/heroes?name={name}", name))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirHero() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		
		this.mockMvc.perform(delete("/api/v1/heroes/{id}", id))
		.andExpect(status().isNoContent());
	}
	
	public String asJsonString(HeroDto hero) {
	    try {
	        return new ObjectMapper().writeValueAsString(hero);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
