package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "power_stats")
public class PowerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, nullable = false)
    private UUID id;

    @NotNull private Integer strength;
    @NotNull private Integer agility;
    @NotNull private Integer dexterity;
    @NotNull private Integer intelligence;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void addingValues(){ this.createdAt = this.updatedAt = Timestamp.from(Instant.now()); }

    @PreUpdate
    public void updatingValues(){
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
