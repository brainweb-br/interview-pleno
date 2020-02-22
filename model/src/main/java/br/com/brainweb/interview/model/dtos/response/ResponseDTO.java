package br.com.brainweb.interview.model.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public abstract class ResponseDTO {

    private UUID id;

    private LocalDateTime created;

    private LocalDateTime updated;
}
