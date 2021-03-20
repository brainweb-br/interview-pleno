package br.com.brainweb.interview.core.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeroDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants(asEnum = true)
    public static class HeroOut {

        private UUID id;
        private String name;
        private String race;
        private Boolean enabled;
        private UUID powerStatsId;
        private Long strength;
        private Long agility;
        private Long dexterity;
        private Long intelligence;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private OffsetDateTime createdAt;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private OffsetDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HeroFilter {

        @Size(message = "E obrigatorio informar 2  heroes", min = 2, max = 2)
        private List<String> name;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants(asEnum = true)
    public static class HeroStronger {

        private UUID id;
        private String strength;
        private String agility;
        private String dexterity;
        private String intelligence;
    }

}
