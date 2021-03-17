package br.com.brainweb.interview.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "power_stats")
public class PowerStats {
	
	@GeneratedValue
	@Id
	private UUID id;
	
	@Column(name = "strength")
	private Integer strength;
	
	@Column(name = "agility")
	private Integer agility;
	
	@Column(name = "dexterity")
	private Integer dexterity;
	
	@Column(name = "intelligence")
	private Integer intelligence;
	
	@Column(name = "created_at")
	private LocalDateTime created_at;
	
	@Column(name = "updated_at")
	private LocalDateTime updated_at;
}
