package br.com.brainweb.interview.model.dtos.request;

import br.com.brainweb.interview.model.dtos.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class PowerStatsRequestDTO {

    @NotNull(message = "agility may not be null")
    private Integer agility;

    @NotNull(message = "dexterity may not be null")
    private Integer dexterity;

    @NotNull(message = "intelligence may not be null")
    private Integer intelligence;

    @NotNull(message = "strength may not be null")
    private Integer strength;
}
