package br.com.brainweb.interview.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "hero")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Hero {

	@Id
	@GeneratedValue(generator = "UUID")
	@Getter
	private UUID id;

	@Column(name = "name", nullable = false, unique = true)
	@Getter @Setter
	private String name;

	@Column(name = "race", nullable = false)
	@Getter @Setter
	private String race;

	@OneToOne
	@JoinColumn(name = "power_stats_id")
	@Getter @Setter
	private PowerStats powerStats;

	@Column(name = "enabled", nullable = false)
	@Getter @Setter
	private Boolean enabled;

	@Column(name = "created_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Getter @Setter
	private Date dateCreated;

	@Column(name = "updated_at" , nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Getter @Setter
	private Date dateUpdated;
}
