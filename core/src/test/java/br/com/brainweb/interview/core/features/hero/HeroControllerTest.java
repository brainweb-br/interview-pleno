package br.com.brainweb.interview.core.features.hero;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brainweb.interview.core.features.hero.exception.GlobalHandler;
import br.com.brainweb.interview.model.Hero;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

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
	public void deveBuscarHeroById() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroService.findById(Mockito.anyString())).thenReturn(Optional.of(hero));
		
		this.mockMvc.perform(get("/api/v1/heroes/{id}", id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id").value("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"))
		.andExpect(jsonPath("name").value("Batman"));
	}
	
	@Test
	public void deveValidarQuandoHeroNaoEncontrado() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		
		Mockito.when(heroService.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get("/api/v1/heroes/{id}", id))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void v2() throws Exception {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		hero.setName("");
		
		//Mockito.when(heroService.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(post("/api/v1/heroes")
				.content(asJsonString(hero))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("messages[0]").value("Informe o atributo name"));
	}
	
	public String asJsonString(Hero hero) {
	    try {
	        return new ObjectMapper().writeValueAsString(hero);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
