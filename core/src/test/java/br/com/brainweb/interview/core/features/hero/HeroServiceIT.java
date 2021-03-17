package br.com.brainweb.interview.core.features.hero;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.zaxxer.hikari.HikariDataSource;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@RunWith(SpringRunner.class)
@JdbcTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles("it")
public class HeroServiceIT {
		
    @Autowired
    private HeroService heroService;
	
	@Test
	public void deveSalvarHero() {
		PowerStats powerStats = PowerStats.builder()
				.id(UUID.randomUUID())
				.agility(1)
				.dexterity(1)
				.intelligence(1)
				.strength(1)
				.created_at(LocalDateTime.now())
				.updated_at(LocalDateTime.now())
				.build();
		
		Hero hero = Hero.builder()
				.id(UUID.randomUUID())
				.name("teste")
				.race("HUMAN")
				.powerStats(powerStats)
				.enable(true)
				.created_at(LocalDateTime.now())
				.updated_at(LocalDateTime.now())
				.build();
		
		Hero heroAtual = heroService.save(hero);
		
		Assert.assertNotNull(heroAtual);
	}
	
	@TestConfiguration
    static class DataSourceTestContextConfiguration {
		
		//FIXME get from external configuration
		@Bean
		public DataSource dataSource() {
			HikariDataSource dataSource = new HikariDataSource();
			dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
		    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
		    dataSource.setUsername("brainweb");
		    dataSource.setPassword("brainweb");
		    dataSource.setSchema("interview_service");
			return dataSource;
		}
    }
	
	@TestConfiguration
    static class HeroRepositoryTestContextConfiguration {
  
		@Bean
		public HeroRepository heroRepository() {
			return new HeroRepository();
		}
    }
	
	@TestConfiguration
    static class PowerStatsRepositoryTestContextConfiguration {
  
		@Bean
		public PowerStatsRepository powerStatsRepository() {
			return new PowerStatsRepository();
		}
    }
	
	@TestConfiguration
    static class HeroServiceTestContextConfiguration {
  
		@Bean
		public HeroService heroService() {
			return new HeroService();
		}
    }
}
