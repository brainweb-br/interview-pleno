package br.com.brainweb.interview.core.features.hero.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.brainweb.interview.core.features.hero.dto.HeroDto;
import br.com.brainweb.interview.core.features.hero.dto.PowerStatsDto;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroMapper {
	
	public static HeroDto toDto(Hero hero) {
		return HeroDto.builder()
				.id(hero.getId())
				.name(hero.getName())
				.race(hero.getRace())
				.enable(hero.isEnable())
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
							.agility(hero.getPowerStats().getAgility())
							.build())
					.build();
		}).collect(Collectors.toList());
	}
	
	public static Hero toNewEntity(HeroDto heroDto) {
		PowerStats power = PowerStats.builder()
				.agility(heroDto.getPowerStats().getAgility())
				.intelligence(heroDto.getPowerStats().getIntelligence())
				.dexterity(heroDto.getPowerStats().getDexterity())
				.agility(heroDto.getPowerStats().getAgility())
				.strength(heroDto.getPowerStats().getStrength())
				.build();
		
		Hero hero = Hero.builder()
				.name(heroDto.getName())
				.race(heroDto.getRace())
				.powerStats(power)
				.enable(heroDto.isEnable())
				.build();
		return hero;
	}
	
	public static Hero toUpdateEntity(HeroDto heroDto) {
		PowerStats power = PowerStats.builder()
				.agility(heroDto.getPowerStats().getAgility())
				.intelligence(heroDto.getPowerStats().getIntelligence())
				.dexterity(heroDto.getPowerStats().getDexterity())
				.agility(heroDto.getPowerStats().getAgility())
				.build();
		
		Hero hero = Hero.builder()
				.id(heroDto.getId())
				.name(heroDto.getName())
				.race(heroDto.getRace())
				.powerStats(power)
				.enable(heroDto.isEnable())
				.created_at(LocalDateTime.now())
				.updated_at(LocalDateTime.now())
				.build();
		return hero;
	}

	public static HeroDto emptyBody() {
		return HeroDto.builder()
				.build();
	}
}
