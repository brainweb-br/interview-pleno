package br.com.brainweb.interview.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "power_stats", schema = "interview_service")
public class PowerStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5194899765398796646L;
	
	@Id
	@Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotNull 
	private short strength;
	
	@NotNull 
	private short agility;
	
	@NotNull 
	private short dexterity;
	
	@NotNull 
	private short intelligence;

	@Column(name="created_at")
	@NotNull 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime updatedAt;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public short getStrength() {
		return strength;
	}

	public void setStrength(short strength) {
		this.strength = strength;
	}

	public short getAgility() {
		return agility;
	}

	public void setAgility(short agility) {
		this.agility = agility;
	}

	public short getDexterity() {
		return dexterity;
	}

	public void setDexterity(short dexterity) {
		this.dexterity = dexterity;
	}

	public short getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(short intelligence) {
		this.intelligence = intelligence;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agility;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + dexterity;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + intelligence;
		result = prime * result + strength;
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerStats other = (PowerStats) obj;
		if (agility != other.agility)
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (dexterity != other.dexterity)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intelligence != other.intelligence)
			return false;
		if (strength != other.strength)
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerStats [id=" + id + ", strength=" + strength + ", agility=" + agility + ", dexterity=" + dexterity
				+ ", intelligence=" + intelligence + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
