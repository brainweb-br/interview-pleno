package br.com.gubee.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ComparisonRequest {
    private UUID hero1;
    private UUID hero2;

}
