package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.core.features.hero.model.HeroResumModel;
import br.com.brainweb.interview.core.features.hero.model.view.CompareHerosView;
import br.com.brainweb.interview.model.enums.EnumPositiveNegative;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PowerStatsService {

    public CompareHerosView compareTo(Hero firstHero, Hero secondHero) {
        return CompareHerosView.builder()
                .firstHero(new HeroResumModel(firstHero))
                .secondeHero(new HeroResumModel(secondHero))
                .compareFirstToSecond(compare(firstHero.getPowerStatsId(), secondHero.getPowerStatsId()))
                .build();
    }

    private Map<String, String> compare(PowerStats firstHeroSts, PowerStats secondHeroSts) {

        Map<String, String> value = new HashMap<String, String>();
        value.putAll(retMap("strength", firstHeroSts.getStrength(), secondHeroSts.getStrength()));
        value.putAll(retMap("agility", firstHeroSts.getAgility(), secondHeroSts.getAgility()));
        value.putAll(retMap("dexterity", firstHeroSts.getDexterity(), secondHeroSts.getDexterity()));
        value.putAll(retMap("intelligence", firstHeroSts.getIntelligence(), secondHeroSts.getIntelligence()));
        return value;
    }

    private Map<String, String> retMap(String type, Integer first, int second) {
        EnumPositiveNegative e = first.compareTo(second)
                == -1 ? EnumPositiveNegative.NEGATIVE : first.compareTo(second)
                == 0 ? EnumPositiveNegative.SAMEVALUE : EnumPositiveNegative.POSITIVE;
        return Map.of(type, e.getValue());
    }
}
