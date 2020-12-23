package br.com.brainweb.interview.model.enums;

import lombok.Getter;

@Getter
public enum EnumPositiveNegative {

    POSITIVE("positivo"),
    SAMEVALUE("-"),
    NEGATIVE("negativo");

    private String value;

    EnumPositiveNegative(String value){
        this.value = value;
    }
}
