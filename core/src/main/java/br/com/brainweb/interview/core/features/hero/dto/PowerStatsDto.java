package br.com.brainweb.interview.core.features.hero.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PowerStatsDto {
	
	private UUID id;
	
	@NotNull(message = "Informe o atributo strength")
	private Integer strength;
	
	@NotNull(message = "Informe o atributo agility")
	private Integer agility;
	
	@NotNull(message = "Informe o atributo dexterity")
	private Integer dexterity;
	
	@NotNull(message = "Informe o atributo intelligence")
	private Integer intelligence;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
