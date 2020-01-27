package br.com.brainweb.interview.model;

import br.com.brainweb.interview.dto.PowerStatsDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class PowerStats {
	@Id
	@GeneratedValue(generator = "UUID")
	@Getter
	private UUID id;

	@Column(name = "strength", nullable = false)
	@Getter @Setter
	private Integer strength;

	@Column(name = "agility", nullable = false)
	@Getter @Setter
	private Integer agility;

	@Column(name = "dexterity", nullable = false)
	@Getter @Setter
	private Integer dexterity;

	@Column(name = "intelligence", nullable = false)
	@Getter @Setter
	private Integer intelligence;

	@Column(name = "created_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Getter @Setter
	private Date dateCreated;

	@Column(name = "updated_at" , nullable = false, columnDefinition = "")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Getter @Setter
	private Date dateUpdated;

	public PowerStatsDTO diferenceStats(PowerStats powerStats){
		PowerStatsDTO powerStatsDTO = new PowerStatsDTO();

		powerStatsDTO.setStrength(this.strength - powerStats.getStrength());

		powerStatsDTO.setAgility(this.agility - powerStats.getAgility());

		powerStatsDTO.setDexterity(this.dexterity - powerStats.getDexterity());

		powerStatsDTO.setIntelligence(this.intelligence - powerStats.getIntelligence());

		return powerStatsDTO;
	}


}
