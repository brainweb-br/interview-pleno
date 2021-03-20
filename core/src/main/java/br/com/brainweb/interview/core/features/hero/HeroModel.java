package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.adapters.UUIDConverter;
import br.com.brainweb.interview.model.Hero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Hero")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeroModel{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;
    private int race;
    private Boolean enabled;
    private UUID powerStatsId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
