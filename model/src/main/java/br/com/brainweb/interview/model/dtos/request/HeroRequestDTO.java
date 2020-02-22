package br.com.brainweb.interview.model.dtos.request;


import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class HeroRequestDTO {

    @NotBlank(message = "Name may not be blank, empty or null")
    @Length(max = 255, message = "Name can't be longer then 255 char")
    private String name;

    @NotNull(message = "Race may not be null")
    private Race race;

    @NotNull(message = "Power stats id may not be null")
    private PowerStatsRequestDTO powerStats;

    @NotNull(message = "Enabled may not be null")
    private Boolean enabled;

}
