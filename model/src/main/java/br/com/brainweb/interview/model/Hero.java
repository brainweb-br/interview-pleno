package br.com.brainweb.interview.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "hero")
public class Hero {
		
	@GeneratedValue
	@Id
	private UUID id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "race")
	private String race;
	
	@Column(name = "enable")
	private boolean enable;
	
	@OneToOne
    @JoinColumn(name = "power_stats_id", referencedColumnName = "id")
	private PowerStats powerStats;
	
	@Column(name = "created_at")
	private LocalDateTime created_at;
	
	@Column(name = "updated_at")
	private LocalDateTime updated_at;
}
