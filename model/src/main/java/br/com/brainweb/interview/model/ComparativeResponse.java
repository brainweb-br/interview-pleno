package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparativeResponse {
    @JsonProperty("id_hero_1")
    private UUID idHeroOne;

    @JsonProperty("name_hero_1")
    private String nameHeroOne;

    @JsonProperty("str_diff_hero_1")
    private Integer strengthHeroOne;

    @JsonProperty("agi_diff_hero_1")
    private Integer agilityHeroOne;

    @JsonProperty("dex_diff_hero_1")
    private Integer dexterityHeroOne;

    @JsonProperty("int_diff_hero_1")
    private Integer intelligenceHeroOne;

    @JsonProperty("id_hero_2")
    private UUID idHeroTwo;

    @JsonProperty("name_hero_2")
    private String nameHeroTwo;

    @JsonProperty("str_diff_hero_2")
    private Integer strengthHeroTwo;

    @JsonProperty("agi_diff_hero_2")
    private Integer agilityHeroTwo;

    @JsonProperty("dex_diff_hero_2")
    private Integer dexterityHeroTwo;

    @JsonProperty("int_diff_hero_2")
    private Integer intelligenceHeroTwo;

    public ComparativeResponse compare(Hero heroOne, Hero heroTwo) {
        ComparativeResponse comparativeResponse = new ComparativeResponse();
        int result = 0;
        comparativeResponse.setIdHeroOne(heroOne.getId());
        comparativeResponse.setIdHeroTwo(heroTwo.getId());
        comparativeResponse.setNameHeroOne(heroOne.getName());
        comparativeResponse.setNameHeroTwo(heroTwo.getName());

        if (heroOne.getPowerStats().getStrength().equals(heroTwo.getPowerStats().getStrength())) {
            comparativeResponse.setStrengthHeroOne(result);
            comparativeResponse.setStrengthHeroTwo(result);
            result = 0;
        } else if (heroOne.getPowerStats().getStrength() > heroTwo.getPowerStats().getStrength()) {
            result = heroOne.getPowerStats().getStrength() - heroTwo.getPowerStats().getStrength();
            comparativeResponse.setStrengthHeroOne(result);
            comparativeResponse.setStrengthHeroTwo(result * -1);
            result = 0;
        } else {
            result = heroTwo.getPowerStats().getStrength() - heroOne.getPowerStats().getStrength();
            comparativeResponse.setStrengthHeroOne(result * -1);
            comparativeResponse.setStrengthHeroTwo(result);
            result = 0;
        }


        if (heroOne.getPowerStats().getAgility().equals(heroTwo.getPowerStats().getAgility())) {
            comparativeResponse.setAgilityHeroOne(result);
            comparativeResponse.setAgilityHeroTwo(result);
            result = 0;
        } else if (heroOne.getPowerStats().getAgility() > heroTwo.getPowerStats().getAgility()) {
            result = heroOne.getPowerStats().getAgility() - heroTwo.getPowerStats().getAgility();
            comparativeResponse.setAgilityHeroOne(result);
            comparativeResponse.setAgilityHeroTwo(result * -1);
            result = 0;
        } else {
            result = heroTwo.getPowerStats().getAgility() - heroOne.getPowerStats().getAgility();
            comparativeResponse.setAgilityHeroOne(result * -1);
            comparativeResponse.setAgilityHeroTwo(result);
            result = 0;
        }

        if (heroOne.getPowerStats().getDexterity().equals(heroTwo.getPowerStats().getDexterity())) {
            comparativeResponse.setDexterityHeroOne(result);
            comparativeResponse.setDexterityHeroTwo(result);
            result = 0;
        } else if (heroOne.getPowerStats().getDexterity() > heroTwo.getPowerStats().getDexterity()) {
            result = heroOne.getPowerStats().getDexterity() - heroTwo.getPowerStats().getDexterity();
            comparativeResponse.setDexterityHeroOne(result);
            comparativeResponse.setDexterityHeroTwo(result * -1);
            result = 0;
        } else {
            result = heroTwo.getPowerStats().getDexterity() - heroOne.getPowerStats().getDexterity();
            comparativeResponse.setDexterityHeroOne(result * -1);
            comparativeResponse.setDexterityHeroTwo(result);
            result = 0;
        }

        if (heroOne.getPowerStats().getIntelligence().equals(heroTwo.getPowerStats().getIntelligence())) {
            comparativeResponse.setIntelligenceHeroOne(result);
            comparativeResponse.setIntelligenceHeroTwo(result);
        } else if (heroOne.getPowerStats().getIntelligence() > heroTwo.getPowerStats().getIntelligence()) {
            result = heroOne.getPowerStats().getIntelligence() - heroTwo.getPowerStats().getIntelligence();
            comparativeResponse.setIntelligenceHeroOne(result);
            comparativeResponse.setIntelligenceHeroTwo(result * -1);
        } else {
            result = heroTwo.getPowerStats().getIntelligence() - heroOne.getPowerStats().getIntelligence();
            comparativeResponse.setIntelligenceHeroOne(result * -1);
            comparativeResponse.setIntelligenceHeroTwo(result);
        }
        return comparativeResponse;
    }

}
