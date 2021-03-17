package br.com.brainweb.interview.core.features.hero;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private Integer strength;
	private Integer agility;
	private Integer dexterity;
	private Integer intelligence;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public PowerStats toEntity() {
		return PowerStats.builder()
				.id(UUID.randomUUID())
				.agility(1)
				.dexterity(1)
				.intelligence(1)
				.strength(1)
				.created_at(LocalDateTime.now())
				.updated_at(LocalDateTime.now())
				.build();
	}
}
