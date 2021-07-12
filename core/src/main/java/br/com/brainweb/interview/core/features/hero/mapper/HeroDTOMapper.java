package br.com.brainweb.interview.core.features.hero.mapper;

import br.com.brainweb.interview.core.features.hero.dto.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HeroDTOMapper {

    @Mapping( target = "powerStatsId", source = "hero.powerStats.id" )
    @Mapping( target = "strength", source = "hero.powerStats.strength" )
    @Mapping( target = "agility", source = "hero.powerStats.agility" )
    @Mapping( target = "dexterity", source = "hero.powerStats.dexterity" )
    @Mapping( target = "intelligence", source = "hero.powerStats.intelligence" )
    HeroDTO toDto (Hero hero);

    @Mapping( target = "powerStats.id", source = "powerStatsId")
    @Mapping( target = "powerStats.strength", source = "strength")
    @Mapping( target = "powerStats.agility", source = "agility")
    @Mapping( target = "powerStats.dexterity", source = "dexterity")
    @Mapping( target = "powerStats.intelligence", source = "intelligence")
    Hero toModel (HeroDTO heroDto);
}
