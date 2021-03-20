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
}
