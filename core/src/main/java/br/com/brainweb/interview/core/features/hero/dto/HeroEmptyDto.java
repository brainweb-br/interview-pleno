package br.com.brainweb.interview.core.features.hero.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"name", "race", "enable", "powerStats"})
public class HeroEmptyDto extends HeroDto {
}


