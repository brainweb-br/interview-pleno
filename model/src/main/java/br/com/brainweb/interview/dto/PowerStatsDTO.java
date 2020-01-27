package br.com.brainweb.interview.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerStatsDTO {

	private Integer strength;

	private Integer agility;

	private Integer dexterity;

	private Integer intelligence;

}
