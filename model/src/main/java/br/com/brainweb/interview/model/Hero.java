package br.com.brainweb.interview.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hero")
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name", nullable = false)
	@NotNull(message = "Name nao pode ser nullo")
	@Size(max = 255, message = "Valor do campo maior que o permitido(255)")
	private String name;

	@Column(name = "race", nullable = false)
	@NotNull(message = "race nao pode ser nullo")
	@Size(max = 255, message = "Valor do campo maior que o permitido(255)")
	private String race;

	@OneToOne
	@JoinColumn(name = "power_stats_id", nullable = false, updatable = false)
	private PowerStats powerStats;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "created_at", nullable = false, updatable = false)
	@NotNull(message = "created_at nao pode ser nullo")
	private Date createdAt;

	@Column(name = "updated_at")
	@NotNull(message = "updated_at nao pode ser nullo")
	private Date updatedAt;

	@PrePersist
	public void prePersist() {
		updatedAt = new Date();
	}

}
