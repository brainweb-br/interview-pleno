package br.com.brainweb.interview.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.brainweb.interview.model.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "hero")
public class Hero implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private UUID id;
	
	@NotBlank(message = "Preenchimento obrigatório")
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(unique = true)
	private String name;
	
	@JsonProperty("powerStats")
	@NotNull(message = "Preenchimento obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "power_stats_id")
	private PowerStats powerStats;
	
	@NotNull
	private boolean enabled;
	
	@JsonIgnore
	@NotNull
	private LocalDateTime createdAtDateTime;
	
	@JsonIgnore
	@NotNull
	private LocalDateTime updatedAtDateTime;
	
	@NotNull(message = "Preenchimento obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Race race;	

    @JsonProperty("createdAt")
    public String getCreatedAt() {
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return format.format(createdAtDateTime);
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return format.format(updatedAtDateTime);
    }
}
