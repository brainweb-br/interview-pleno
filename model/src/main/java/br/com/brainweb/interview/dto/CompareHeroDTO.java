package br.com.brainweb.interview.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CompareHeroDTO {

	private UUID firstHero;

	private UUID secondHero;

	private PowerStatsDTO statsDiference;

}
