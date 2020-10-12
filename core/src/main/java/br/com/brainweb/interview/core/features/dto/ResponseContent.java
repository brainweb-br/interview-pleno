package br.com.brainweb.interview.core.features.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseContent<T> {

    private T body;
    private String message;
    private String[] erros;

}
