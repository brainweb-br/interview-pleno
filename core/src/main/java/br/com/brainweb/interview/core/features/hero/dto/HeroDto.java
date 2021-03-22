package br.com.brainweb.interview.core.features.hero.dto;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HeroDto {
	
	private UUID id;
	
	@NotEmpty(message = "Informe o atributo name")
	private String name;
	
	@NotEmpty(message = "Informe o atributo race")
	private String race;
	
	private boolean enable;
	
	@Valid
	@JsonProperty("powerStats")
	private PowerStatsDto powerStats;
}
