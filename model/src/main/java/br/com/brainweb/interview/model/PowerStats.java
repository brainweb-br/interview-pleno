package br.com.brainweb.interview.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.brainweb.interview.model.commons.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "power_stats")
public class PowerStats extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull()
	@Column(name = "strength", nullable = false)
	private Integer strength;

	@NotNull()
	@Column(name = "agility", nullable = false)
	private Integer agility;

	@NotNull()
	@Column(name = "dexterity", nullable = false)
	private Integer dexterity;

	@NotNull()
	@Column(name = "intelligence", nullable = false)
	private Integer intelligence;

	@NotNull()
	@Column(name = "created_at", nullable = false)
	@Builder.Default
	private Date created_at = new Date();

	@NotNull()
	@Column(name = "updated_at", nullable = false)
	@Builder.Default
	private Date updated_at = new Date();
}
