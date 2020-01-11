package br.com.brainweb.interview.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.brainweb.interview.enums.Race;
import br.com.brainweb.interview.model.commons.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "hero")
public class Hero extends BaseEntity {

	private static final long serialVersionUID = -7829328481218786497L;

	@NotNull()
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull()
	@Column(name = "race", nullable = false)
	@Enumerated(EnumType.STRING)
	private Race race;

	@OneToOne(targetEntity = PowerStats.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "power_stats_id")
	private PowerStats powerStats;

	@NotNull()
	@Column(name = "enabled", nullable = false)
	@Builder.Default
	private Boolean enabled = true;

	@NotNull()
	@Column(name = "created_at", nullable = false)
	@Builder.Default
	private Date created_at = new Date();

	@NotNull()
	@Column(name = "updated_at", nullable = false)
	@Builder.Default
	private Date updated_at = new Date();
}
