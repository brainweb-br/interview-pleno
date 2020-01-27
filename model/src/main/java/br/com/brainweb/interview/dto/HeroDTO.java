package br.com.brainweb.interview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
public class HeroDTO {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private UUID id;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String race;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private PowerStatsDTO powerStats;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private Boolean enabled;



}
