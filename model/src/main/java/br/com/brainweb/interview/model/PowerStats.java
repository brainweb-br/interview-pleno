package br.com.brainweb.interview.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "power_stats")
public class PowerStats {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	@Column(name = "strength", nullable = false)
	@NotNull(message = "strength nao pode ser nullo")
	private int strength;

	@Column(name = "agility", nullable = false)
	@NotNull(message = "agility nao pode ser nullo")
	private int agility;

	@Column(name = "dexterity", nullable = false)
	@NotNull(message = "dexterity nao pode ser nullo")
	private int dexterity;

	@Column(name = "intelligence", nullable = false)
	@NotNull(message = "intelligence nao pode ser nullo")
	private int intelligence;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@PrePersist
	public void prePersist() {
		setCreatedAt(new Date());
		setUpdatedAt(new Date());
	}

	@Override
	public String toString() {
		return "PowerStats [getId()=" + getId() + ", getStrength()=" + getStrength() + ", getAgility()=" + getAgility() + ", getDexterity()=" + getDexterity() + ", getIntelligence()=" + getIntelligence() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
}
