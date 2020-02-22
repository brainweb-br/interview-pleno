package br.com.brainweb.interview.model.dtos.response;

import lombok.Data;

@Data
public class PowerStatsResponseDTO extends ResponseDTO {

    private Integer agility;

    private Integer dexterity;

    private Integer intelligence;

    private Integer strength;


}
