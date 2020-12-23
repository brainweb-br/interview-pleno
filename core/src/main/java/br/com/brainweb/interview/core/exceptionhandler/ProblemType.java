package br.com.brainweb.interview.core.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    SYSTEM_ERROR("/system-error", "Erro de sistema."),
    INVALID_PARAM("/invalid-param", "Parâmetro inválido"),
    CONFLICT("/conflict","Há um conflito."),
    INVALID_DATA("/invalid-data", "Dados inválidos."),
    RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado.");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://gotham.braimweb.com.br" + path;
        this.title = title;
    }

}
