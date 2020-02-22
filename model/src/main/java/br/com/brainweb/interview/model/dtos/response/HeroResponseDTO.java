package br.com.brainweb.interview.model.dtos.response;

import br.com.brainweb.interview.model.enums.Race;
import lombok.Data;

@Data
public class HeroResponseDTO extends ResponseDTO {

    private String name;

    private Race race;

    private PowerStatsResponseDTO powerStats;

    private Boolean enabled;

}
