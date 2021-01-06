package br.com.brainweb.interview.core.features.hero.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Integer status;

    @JsonProperty("data")
    private String date;

    @JsonProperty("mensagem")
    private String Title;
}
