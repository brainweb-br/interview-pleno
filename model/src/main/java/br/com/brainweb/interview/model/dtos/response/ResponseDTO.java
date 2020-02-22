package br.com.brainweb.interview.model.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ResponseDTO {

    private UUID id;

    private LocalDateTime created;

    private LocalDateTime updated;
}
