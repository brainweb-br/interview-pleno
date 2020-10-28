package br.com.brainweb.interview.model;

import java.util.Date;
import java.util.UUID;

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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hero")
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	@Column(name = "name", nullable = false)
	@NotNull(message = "Name nao pode ser nullo")
	@Size(max = 255, message = "Valor do campo(name) maior que o permitido(255)")
	private String name;

	@Column(name = "race", nullable = false)
	@NotNull(message = "race nao pode ser nullo")
	@Pattern(regexp = "^(HUMAN|ALIEN|DIVINE|CYBORG)$", message = "Race inválido! valores possiveis (HUMAN|ALIEN|DIVINE|CYBORG)")
	private String race;


	@OneToOne
	@JoinColumn(name = "power_stats_id", nullable = false, updatable = false)
	private PowerStats power;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@PrePersist
	public void prePersist() {
		setCreatedAt(new Date());
		setUpdatedAt(new Date());
	}

	@Override
	public String toString() {
		return "Hero [getId()=" + getId() + ", getName()=" + getName() + ", getRace()=" + getRace() + ", getPower()=" + getPower() + ", isEnabled()=" + isEnabled() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}

	

}
