package br.com.brainweb.interview.core.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {
    private final String type;
    private final String title;
    private final String detail;
    private final Integer status;
    private final String userMessage;
    private final LocalDateTime timestamp;

    @Getter
    @Builder
    public static class Object {
        private final String name;
        private final String userMessage;
    }
}
