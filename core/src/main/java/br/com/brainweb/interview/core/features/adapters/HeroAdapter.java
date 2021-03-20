package br.com.brainweb.interview.core.features.adapters;

import br.com.brainweb.interview.core.features.hero.HeroModel;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;
import org.springframework.stereotype.Component;

@Component
public class HeroAdapter {

    public static HeroModel heroToHeroModel(Hero hero) {
        return HeroModel.builder()
                .id(hero.getId())
                .name(hero.getName())
                .enabled(hero.getEnable())
                .race(hero.getRace().ordinal())
                .powerStatsId(hero.getPowerStats() != null ? hero.getPowerStats().getId():null)
                .createdAt(hero.getCreatedAt())
                .updatedAt(hero.getUpdatedAt())
                .build();
    }

    public static HeroModel heroToHeroModelWithoutId(Hero hero) {
        return HeroModel.builder()
                .name(hero.getName())
                .enabled(hero.getEnable())
                .race(hero.getRace().ordinal())
                .powerStatsId(hero.getPowerStats() != null ? hero.getPowerStats().getId():null)
                .createdAt(hero.getCreatedAt())
                .updatedAt(hero.getUpdatedAt())
                .build();
    }

    public static Hero heroModelToHero(HeroModel heroModele){
        return Hero.builder()
                .id(heroModele.getId())
                .name(heroModele.getName())
                .enable(heroModele.getEnabled())
                .race(Race.getValue(heroModele.getRace()))
                .powerStats(PowerStats.
                        builder().id(heroModele.getPowerStatsId()).build())
                .createdAt(heroModele.getCreatedAt())
                .updatedAt(heroModele.getUpdatedAt())
                .build();
    }

    public static Hero heroModelToHeroWithoutId(HeroModel heroModele){
        return Hero.builder()
                .name(heroModele.getName())
                .enable(heroModele.getEnabled())
                .race(Race.getValue(heroModele.getRace()))
                .powerStats(PowerStats.
                        builder().id(heroModele.getPowerStatsId()).build())
                .createdAt(heroModele.getCreatedAt())
                .updatedAt(heroModele.getUpdatedAt())
                .build();
    }

}
