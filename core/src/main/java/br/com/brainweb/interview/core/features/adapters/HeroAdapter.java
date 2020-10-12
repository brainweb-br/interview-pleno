package br.com.brainweb.interview.core.features.adapters;

import br.com.brainweb.interview.core.features.hero.HeroModel;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

public class HeroAdapter {

    HeroModel heroToHeroModel(Hero hero) {
        return HeroModel.builder()
                .id(hero.getId())
                .name(hero.getName())
                .enable(hero.getEnable())
                .race(hero.getRace().ordinal())
                .powerStatsId(hero.getPowerStats().getId())
                .createdAt(hero.getCreatedAt())
                .updatedAt(hero.getUpdatedAt())
                .build();
    }

    Hero heroModelToHero(HeroModel heroModele){
        return Hero.builder()
                .id(heroModele.getId())
                .name(heroModele.getName())
                .enable(heroModele.getEnable())
                .race(Race.getValue(heroModele.getRace()))
                .powerStats(PowerStats.
                        builder().id(heroModele.getPowerStatsId()).build())
                .createdAt(heroModele.getCreatedAt())
                .updatedAt(heroModele.getUpdatedAt())
                .build();
    }

}
