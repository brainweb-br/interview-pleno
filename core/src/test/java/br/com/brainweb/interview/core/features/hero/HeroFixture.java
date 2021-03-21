package br.com.brainweb.interview.core.features.hero;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class HeroFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Hero.class).addTemplate("valid-hero", new Rule() {{
            add("id", UUID.fromString("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"));
            add("name", "Batman");
            add("race", "HUMAN");
            add("enable", true);
            add("powerStats",  one(PowerStats.class, "valid-powerstats"));
            add("created_at", LocalDateTime.now());
            add("updated_at", LocalDateTime.now());
        }});
		
		Fixture.of(PowerStats.class).addTemplate("valid-powerstats", new Rule() {{
            add("id", UUID.fromString("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"));
            add("strength", 5);
            add("agility", 5);
            add("dexterity", 5);
            add("intelligence", 5);
            add("created_at", LocalDateTime.now());
            add("updated_at", LocalDateTime.now());
        }});
		
		Fixture.of(HeroDto.class).addTemplate("valid-hero-dto", new Rule() {{
            add("id", UUID.fromString("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"));
            add("name", "Batman");
            add("race", "HUMAN");
            add("enable", true);
            add("powerStats",  one(PowerStatsDto.class, "valid-powerstats-dto"));
        }});
		
		Fixture.of(PowerStatsDto.class).addTemplate("valid-powerstats-dto", new Rule() {{
            add("id", UUID.fromString("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3"));
            add("strength", 5);
            add("agility", 5);
            add("dexterity", 5);
            add("intelligence", 5);
        }});
	}
}
