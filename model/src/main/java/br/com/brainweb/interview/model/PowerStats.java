package br.com.brainweb.interview.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PowerStats extends BaseEntity{

    @Id
    @GeneratedValue
    private UUID id;
    private short strength;
    private short agility;
    private short dexterity;
    private short intelligence;

}
