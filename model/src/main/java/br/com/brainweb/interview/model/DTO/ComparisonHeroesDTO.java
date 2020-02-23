package br.com.brainweb.interview.model.DTO;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.utils.CompareHeroUtil;
import lombok.Data;

@Data
public class ComparisonHeroesDTO {
    private AttributesHeroDTO attributesHero1;
    private AttributesHeroDTO attributesHero2;

    public ComparisonHeroesDTO(){
        this.attributesHero1 = new AttributesHeroDTO();
        this.attributesHero2 = new AttributesHeroDTO();
    }

    public static ComparisonHeroesDTO create(Hero hero1, Hero hero2){
        ComparisonHeroesDTO comparisonHeroesDTO = new ComparisonHeroesDTO();
        CompareHeroUtil compareHeroUtil = new CompareHeroUtil();
        comparisonHeroesDTO.attributesHero1.setId(hero1.getId());
        comparisonHeroesDTO.attributesHero2.setId(hero2.getId());

        //Agility
        comparisonHeroesDTO.attributesHero1.setAgility(compareHeroUtil.compare(hero1.getPowerStats().getAgility(), hero2.getPowerStats().getAgility()));
        comparisonHeroesDTO.attributesHero2.setAgility(compareHeroUtil.compare(hero2.getPowerStats().getAgility(), hero1.getPowerStats().getAgility()));

        //Dexterity
        comparisonHeroesDTO.attributesHero1.setDexterity(compareHeroUtil.compare(hero1.getPowerStats().getDexterity(), hero2.getPowerStats().getDexterity()));
        comparisonHeroesDTO.attributesHero2.setDexterity(compareHeroUtil.compare(hero2.getPowerStats().getDexterity(), hero1.getPowerStats().getDexterity()));

        //Intelligence
        comparisonHeroesDTO.attributesHero1.setIntelligence(compareHeroUtil.compare(hero1.getPowerStats().getIntelligence(), hero2.getPowerStats().getIntelligence()));
        comparisonHeroesDTO.attributesHero2.setIntelligence(compareHeroUtil.compare(hero2.getPowerStats().getIntelligence(), hero1.getPowerStats().getIntelligence()));

        //Strength
        comparisonHeroesDTO.attributesHero1.setStrength(compareHeroUtil.compare(hero1.getPowerStats().getStrength(), hero2.getPowerStats().getStrength()));
        comparisonHeroesDTO.attributesHero2.setStrength(compareHeroUtil.compare(hero2.getPowerStats().getStrength(), hero1.getPowerStats().getStrength()));

        return comparisonHeroesDTO;
    }
}
