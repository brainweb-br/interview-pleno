package br.com.brainweb.interview.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Hero {
		
	private UUID id;
	private String name;
	private String race;
	private boolean enable;
	private PowerStats powerStats;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public void atualizarPowerStatsId(UUID uuid) {
		if (powerStats != null) {
			powerStats.setId(uuid);
		}
	}
	
	public void atualizarDataCriacaoAtualizacao(LocalDateTime periodo) {
		this.created_at = periodo;
		this.updated_at = periodo;
		
		if (powerStats != null) {
			powerStats.setCreated_at(periodo);
			powerStats.setUpdated_at(periodo);
		}
	}
	
	public UUID getPowerStatsId() {
		if (powerStats != null) {
			return powerStats.getId();
		}
		
		return null;
	}
}
