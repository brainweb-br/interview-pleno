package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.adapter.dto.CompareHeroQuery;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroCommand;
import br.com.brainweb.interview.core.features.hero.adapter.dto.HeroQuery;
import br.com.brainweb.interview.core.features.hero.adapter.dto.PowerStatsQuery;
import br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields;
import br.com.brainweb.interview.model.CompareHero;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.UPDATED_AT;
import static br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields.*;

public class HeroDTOAssembler {



    public static Hero toHero(HeroCommand heroCommand) {
        var powerStatsCommand = heroCommand.getPowerStats();
        var powerStats = new PowerStats(
                powerStatsCommand.getStrength(),
                powerStatsCommand.getAgility(),
                powerStatsCommand.getDexterity(),
                powerStatsCommand.getIntelligence());
        return new Hero(
                heroCommand.getName(),
                heroCommand.getRace(),
                powerStats);
    }

    public static HeroQuery toHeroQuery(Hero hero) {
        var powerStats = hero.getPowerStats();
        var powerStatsQuery = PowerStatsQuery.builder()
                .id(powerStats.getId())
                .agility(powerStats.getAgility())
                .dexterity(powerStats.getDexterity())
                .intelligence(powerStats.getIntelligence())
                .strength(powerStats.getStrength())
                .createdDt(powerStats.getCreatedDt().toString())
                .updatedDt(powerStats.getUpdatedDt().toString())
                .build();
        return HeroQuery.builder()
                .id(hero.getId())
                .name(hero.getName())
                .race(hero.getRace())
                .enabled(hero.isEnabled())
                .powerStats(powerStatsQuery)
                .createdDt(hero.getCreatedDt().toString())
                .updatedDt(hero.getUpdatedDt().toString())
                .build();
    }

    public static CompareHeroQuery toCompareHeroQuery(CompareHero compareHero){
        return CompareHeroQuery.builder()
                .heroId1(compareHero.getHeroId1())
                .heroId2(compareHero.getHeroId2())
                .diffStrength(compareHero.getDiffStrength())
                .diffAgility(compareHero.getDiffAgility())
                .diffDexterity(compareHero.getDiffDexterity())
                .diffIntelligence(compareHero.getDiffIntelligence())
                .build();
    }


}
