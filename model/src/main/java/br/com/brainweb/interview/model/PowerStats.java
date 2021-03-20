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
public class PowerStats {
	
	private UUID id;
	private Integer strength;
	private Integer agility;
	private Integer dexterity;
	private Integer intelligence;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
