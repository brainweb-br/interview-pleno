package br.com.brainweb.interview.core.features.hero.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.brainweb.interview.core.features.hero.HeroDto;
import br.com.brainweb.interview.core.features.hero.PowerStatsDto;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroMapper {
	
	public static HeroDto toDto(Hero hero) {
		return HeroDto.builder()
				.id(hero.getId())
				.name(hero.getName())
				.race(hero.getRace())
				.powerStats(PowerStatsDto.builder()
						.id(hero.getPowerStats().getId())
						.agility(hero.getPowerStats().getAgility())
						.intelligence(hero.getPowerStats().getIntelligence())
						.dexterity(hero.getPowerStats().getDexterity())
						.build())
				.build();
		
	}
	
	public static List<HeroDto> toDto(List<Hero> heroes) {
		return heroes.stream().map(hero -> {
			return HeroDto.builder()
					.id(hero.getId())
					.name(hero.getName())
					.race(hero.getRace())
					.powerStats(PowerStatsDto.builder()
							.id(hero.getPowerStats().getId())
							.agility(hero.getPowerStats().getAgility())
							.intelligence(hero.getPowerStats().getIntelligence())
							.dexterity(hero.getPowerStats().getDexterity())
							.build())
					.build();
		}).collect(Collectors.toList());
	}
}
